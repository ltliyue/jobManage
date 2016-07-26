package inspur.crawl.ruleManage.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.TransformerException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.SgmlPage;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.inspur.avro.model.java.UrlType;

import inspur.crawl.common.tools.Encoding;
import inspur.crawl.ruleManage.controller.UrlParser.MyClassLoader;
import inspur.crawl.ruleManage.pojo.ElementExtractRule;
import inspur.crawl.ruleManage.pojo.PageExtractRule;
import inspur.crawl.ruleManage.pojo.ParseRule;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;
import inspur.crawl.ruleManage.service.HtmlUtils;
import inspur.crawl.ruleManage.service.RemoveSlibingProcessor;

public class ContentExtractor {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentExtractor.class);
	
	public static String ruleJarHdfsDir = "/user/jstorm/lib";
	public static String ruleJarLocalDirWin = "D:/lib/crawlManage/";
	public static String ruleJarLocalDirLinux = "/home/inspur/lib/crawlManage";
	
	//加载配置文件
	static{
		Properties prop = new Properties();
        try {
            InputStream s = UrlParser.class.getResourceAsStream("/rule.properties");
            if (s == null) {
                throw new RuntimeException("Cannot find cassandra.properties");
            }
            prop.load(s);
            ruleJarHdfsDir = prop.getProperty("rule.jar.hdfs.dir", "/user/jstorm/lib");
            ruleJarLocalDirWin = prop.getProperty("rule.jar.local.dir.win", "D:/lib/crawlManage/");
            ruleJarLocalDirLinux = prop.getProperty("rule.jar.local.dir.linux", "/home/inspur/lib/crawlManage/");
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	static class MyClassLoader extends URLClassLoader {

		public MyClassLoader(URL[] urls) {
			super(urls);
		}

		public MyClassLoader(URL[] urls, ClassLoader parent) {
			super(urls, parent);
		}

		public void addJar(URL url) {
			this.addURL(url);
			// 解压缩jar，并添加jar内部的jar
			try {
				unZipFile(url.getPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private  void makeSupDir(String outFileName) {
			Pattern p = Pattern.compile("[/\\" + File.separator + "]");
			Matcher m = p.matcher(outFileName);
			while (m.find()) {
				int index = m.start();
				String subDir = outFileName.substring(0, index);
				File subDirFile = new File(subDir);
				if (!subDirFile.exists())
					subDirFile.mkdir();
			}
		}

		private  void unZipFile(String jarPath) throws IOException {
			JarFile jarFile = new JarFile(jarPath);
			String fileName = jarFile.getName();
			fileName = fileName.substring(0, fileName.lastIndexOf(".jar"));			
			String outputDir = fileName+"/lib";
			//如果已经存在不需要再进行解压缩
			if(new File(outputDir).exists()){
				//直接加载子目录中的jar
				for(File file: new File(outputDir).listFiles()){
					if(file.getName().endsWith(".jar")){
						this.addURL(file.toURI().toURL());
					}
				}
				return;
			}
			System.out.println("解压缩"+jarPath);
			makeSupDir(outputDir);
			Enumeration<JarEntry> jarEntrys = jarFile.entries();
			while (jarEntrys.hasMoreElements()) {
				JarEntry jarEntry = jarEntrys.nextElement();
				if (jarEntry.getName().startsWith("lib/") && jarEntry.getName().endsWith(".jar")) {
					String outFileName = fileName+"/"+jarEntry.getName();
					File f = new File(outFileName);
					makeSupDir(outFileName);
					if (jarEntry.isDirectory()) {
						continue;
					}
					writeFile(jarFile.getInputStream(jarEntry), f);
					//加入该文件
					this.addURL(new File(outFileName).toURI().toURL());
				}
			}
		}

		private  void writeFile(File inputFile, File outputFile) throws IOException {
			writeFile(new FileInputStream(inputFile), outputFile);
		}

		private  void writeFile(InputStream ips, File outputFile) throws IOException {
			OutputStream ops = new BufferedOutputStream(new FileOutputStream(outputFile));
			try {
				byte[] buffer = new byte[1024];
				int nBytes = 0;
				while ((nBytes = ips.read(buffer)) > 0) {
					ops.write(buffer, 0, nBytes);
				}
			} catch (IOException ioe) {
				throw ioe;
			} finally {
				try {
					if (null != ops) {
						ops.flush();
						ops.close();
					}
				} catch (IOException ioe) {
					throw ioe;
				} finally {
					if (null != ips) {
						ips.close();
					}
				}
			}
		}
	}

	public static List<Map<String, String>> extract(PageExtractRule rule, List<ElementExtractRule> elementRules,
			Map<String, String> storeColumnMap,
			String content, String url) {
		return extract(rule, elementRules, storeColumnMap, content, url, null);
	}

	public static List<Map<String, String>> extract(PageExtractRule rule, List<ElementExtractRule> elementRules,
			Map<String, String> storeColumnMap, 
			String content, String url, StringBuffer logBuffer) {
		if (url == null || elementRules.isEmpty()) {
			return new ArrayList<Map<String, String>>();
		}

		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();

		String urlPattern = rule.getUrlPattern();
		// url模式过滤
		if (UrlParser.urlPatternFilter(urlPattern, url) == false) {
			LOGGER.info(url + "不符合模式" + urlPattern);
			if (logBuffer != null) {
				logBuffer.append(url + "不符合模式" + urlPattern + "\r\n");
			}
			return mapList;
		} else {
			LOGGER.info(url + "符合模式" + urlPattern);
			if (logBuffer != null) {
				logBuffer.append(url + "符合模式" + urlPattern + "\r\n");
			}
		}

		if (content == null || content.trim().isEmpty()) {
			LOGGER.info(url + "的网页内容为空");
			if (logBuffer != null) {
				logBuffer.append(url + "的网页内容为空");
			}
			return mapList;
		}
		Short extractType = rule.getExtractType();
		if(extractType!=null && extractType==2){
			//自定义方式
			String jarPath = rule.getJarPath();
			String className = rule.getClassName();
			
			//下载jar到本地
			if(jarPath==null||className==null){
				if(logBuffer!=null){
					logBuffer.append("自定义规则有内容为空：jarPath="+jarPath+",className="+className+"\r\n");
				}
				return mapList;
			}
			try {
				FileSystem fs = FileSystem.get(new Configuration());
				jarPath = jarPath.substring("hdfs://".length());
				String jarName = jarPath.substring(jarPath.lastIndexOf("/")+1);
				
				String localJarDir = ruleJarLocalDirLinux;
				if(System.getProperties().get("os.name").toString().toLowerCase().contains("windows")){
					localJarDir = ruleJarLocalDirWin;
				}
				if(new File(localJarDir).exists()==false){
					new File(localJarDir).mkdirs();
				}else if(new File(localJarDir).isDirectory()==false){
					new File(localJarDir).deleteOnExit();
					new File(localJarDir).mkdirs();
				}
				if(new File(localJarDir+jarName).exists()==false){
					fs.copyToLocalFile(new Path(jarPath), new Path(localJarDir));
				}
				URL[] urlsx = new URL[] {};
				
				MyClassLoader classLoader = new MyClassLoader(urlsx, null);
				classLoader.addJar(new File(localJarDir+jarName).toURI().toURL());
				Class<?> clazz = classLoader.loadClass(className);
				Method[] methods = clazz.getDeclaredMethods();
				Object object= clazz.newInstance();
				Method method = clazz.getDeclaredMethod("extract",String.class);
				List<Map<String, String>>  map =(List<Map<String, String>>) method.invoke(object,content);
				
				//转换成字段名
				List<Map<String, String>> rsMaps = new ArrayList<>();
				for(Map<String, String> tmap:map) {
					Map<String, String>  rsMap = new HashMap<>();
					for(String key:tmap.keySet()){
						String value = tmap.get(key);
						if(storeColumnMap.containsKey(key)){
							rsMap.put(storeColumnMap.get(key), value);
						}
					}
					if(rsMap.size()>0){
						rsMaps.add(rsMap);
					}
				}
				
				classLoader.close();
				return rsMaps;
			}catch(Exception e){
				e.printStackTrace();
				if(logBuffer!=null){
					logBuffer.append("执行自定义方法抽取内容失败！"+e.getMessage()+"\r\n");
				}
				return mapList;
			}
		}
		
		Short containsMulti = rule.getContainsMulti();
		boolean suc = true;
		SgmlPage page = null;
		if (containsMulti == 1) {// 只有一条数据
			Map<String, String> map = new HashMap<String, String>();
			for (ElementExtractRule elementRule : elementRules) {
				if (suc == false)
					break;
				String regex = elementRule.getRegex();
				String regexGroupId = elementRule.getRegexGroupId();
				String storeColumnComment = elementRule.getStoreColumnComment();
				if(storeColumnComment==null || storeColumnComment.isEmpty()){
					continue;
				}
				String xpath = elementRule.getXpath();
				Short containsHtml = elementRule.getContainHtml();
				String extract_content = null;
				if (elementRule.getExtractType().equals("1")) {
					// 正则表达式
					if (regex != null && regexGroupId.matches("\\d+")) {
						Pattern pattern = Pattern.compile(regex);
						int regexGroupIdInt = Integer.valueOf(regexGroupId);
						Matcher matcher = pattern.matcher(content);
						StringBuffer buffer = new StringBuffer();
						while (matcher.find()) {
							if(matcher.groupCount()<regexGroupIdInt){
								LOGGER.info("正则表达式标号边界溢出，字段名：" + storeColumnComment + "，正则表达式内容："+
										regex+"，正则表达式组号："+regexGroupId);
								logBuffer.append("正则表达式标号边界溢出，字段名：" + storeColumnComment + "，正则表达式内容："+
										regex+"，正则表达式组号："+regexGroupId+"\r\n");
								return new ArrayList<Map<String, String>>();
							}
							buffer.append(matcher.group(regexGroupIdInt));
						}
						extract_content = buffer.toString().trim();
					}
				}
				if (elementRule.getExtractType().equals("2")) {// xpath
					
					try {
						if(page==null){
							page = HtmlUtils.getPage(content, url);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
						if (logBuffer != null) {
							logBuffer.append("生成DOM树结构出错！错误信息:" + e1.getMessage() + "\r\n");
						}
					}
					if (page != null && xpath != null) {
						List<DomNode> nodeList = new ArrayList<DomNode>();
						try{
							nodeList=(List<DomNode>) page.getByXPath(xpath);
						}catch(Exception e){
							e.printStackTrace();
							logBuffer.append("解析xpath出错！错误信息:" + e.getMessage()+"\r\n");
						}
						StringBuffer buffer = new StringBuffer();
						if (nodeList != null) {
							if (containsHtml == 1) {
								Node node = nodeList.get(0);
								Document document = RemoveSlibingProcessor.process(url, "utf-8", page, node);
								try {
									extract_content = HtmlUtils.asXml(document);
								} catch (TransformerException e) {
									LOGGER.error(url + "抽取带html样式的内容出错，" + xpath + "," + e.getMessage());
									if (logBuffer != null) {
										logBuffer.append("抽取带html样式的内容出错，" + xpath + "，具体信息：" + e.getMessage());
									}
									e.printStackTrace();
								}
							} else {
								for (int i = 0; i < nodeList.size(); i++) {
									Node node = nodeList.get(i);
									buffer.append("\r\n" + HtmlUtils.getTextContent(node));
								}
								extract_content = buffer.toString().trim();
							}
						}
					}
				}
				//TODO:警告：在distributeCrawling里面使用该类时，此处是storeColumnName，请仔细检查
				if (storeColumnComment != null) {
					map.put(storeColumnComment, extract_content);
				}

			}
			mapList.add(map);
		} else if(containsMulti==2){ //为2表示有多条数据
			// 多条数据，现获取每一条数据的根节点
			try {
				page = HtmlUtils.getPage(content, url);
			} catch (IOException e1) {
				if (logBuffer != null) {
					logBuffer.append("生成DOM树结构出错！错误信息:" + e1.getMessage()+"\r\n");
				}
				e1.printStackTrace();
			}
			
			String xpath = rule.getMultiRowXpath();
			if (page != null && xpath != null) {

				List<DomNode> nodeList = (List<DomNode>) page.getByXPath(xpath);
				if (nodeList != null) {
					for (int i = 0; i < nodeList.size(); i++) {
						if (suc == false)
							break;
						Node rowNode = nodeList.get(i);
						content = HtmlUtils.asXml(rowNode);
						// 取出该节点下的一条数据
						Map<String, String> map = new HashMap<String, String>();
						for (ElementExtractRule elementRule : elementRules) {
							if (suc == false)
								break;
							String regex = elementRule.getRegex();
							String regexGroupId = elementRule.getRegexGroupId();
							String storeColumnComment = elementRule.getStoreColumnComment();
							xpath = elementRule.getXpath();
							Short containsHtml = elementRule.getContainHtml();
							String extract_content = null;
							if (elementRule.getExtractType().equals("1")) {
								// 正则表达式
								if (regex != null && regexGroupId.matches("\\d+")) {
									Pattern pattern = Pattern.compile(regex);
									int regexGroupIdInt = Integer.valueOf(regexGroupId);
									Matcher matcher = pattern.matcher(content);
									StringBuffer buffer = new StringBuffer();
									while (matcher.find()) {
										buffer.append(matcher.group(regexGroupIdInt));
									}
									extract_content = buffer.toString();
								}
							}
							if (elementRule.getExtractType().equals("2")) {
								if (page != null && xpath != null) {
									List<DomNode> list = null;
									list = (List<DomNode>) ((DomNode)rowNode).getByXPath(xpath);
									if (list != null) {
										if (containsHtml == 1) {
											Node node = list.get(0);
											Document document = RemoveSlibingProcessor.process(url, "utf-8", page, node);
											extract_content = HtmlUtils.printDOMTree(node);
										} else {
											StringBuffer buffer = new StringBuffer();
											for (int j = 0; j < list.size(); j++) {
												buffer.append(list.get(j).getTextContent().replaceAll("\r\n", "\t"));
											}
											extract_content = buffer.toString().trim();
										}
									}
								}
							}

							//TODO:警告：在distributeCrawling里面使用该类时，此处是storeColumnName，请仔细检查
							if (storeColumnComment != null) {
								extract_content = extract_content.replaceAll("\r\n", " ");
								extract_content = extract_content.replaceAll("\r", " ");
								extract_content = extract_content.replaceAll("\n", " ");
								extract_content = extract_content.replaceAll("\\s+", " ");
								map.put(storeColumnComment, extract_content);
							}
						}
						mapList.add(map);
					}
				}
			}

		}
		LOGGER.info(url + "网页内容抽取成功!");
		if (logBuffer != null) {
			logBuffer.append("网页内容抽取成功!\r\n");
		}
		return mapList;
	}
}
