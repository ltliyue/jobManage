package inspur.crawl.ruleManage.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.SgmlPage;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.inspur.avro.model.java.UrlType;

import inspur.crawl.ruleManage.pojo.LoopParseRule;
import inspur.crawl.ruleManage.pojo.LoopVarRule;
import inspur.crawl.ruleManage.pojo.ParseRuleMidMap;
import inspur.crawl.ruleManage.pojo.ParseRuleTemp;
import inspur.crawl.ruleManage.service.HtmlUtils;

/**
 * 网页中的url解析器
 * 
 * @author maolh
 *
 */
public class UrlParser {

	private static final Logger LOGGER = LoggerFactory.getLogger(UrlParser.class);

	public static String ruleJarHdfsDir = "/user/jstorm/lib";
	public static String ruleJarLocalDirWin = "D:/lib/crawlManage/";
	public static String ruleJarLocalDirLinux = "/home/jstorm/lib/crawlManage/";

	// 加载配置文件
	static {
		Properties prop = new Properties();
		try {
			InputStream s = UrlParser.class.getResourceAsStream("/rule.properties");
			if (s == null) {
				throw new RuntimeException("Cannot find cassandra.properties");
			}
			prop.load(s);
			ruleJarHdfsDir = prop.getProperty("rule.jar.hdfs.dir", "/user/jstorm/lib");
			ruleJarLocalDirWin = prop.getProperty("rule.jar.local.dir.win", "D:/lib/crawlManage/");
			ruleJarLocalDirLinux = prop.getProperty("rule.jar.local.dir.linux", "/home/jstorm/lib/crawlManage/");
		} catch (Exception e) {
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
					addJar(new File(outFileName).toURI().toURL());
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

	/**
	 * 
	 * @param pattern
	 * @param url
	 * @return
	 */
	public static boolean urlPatternFilter(String pattern, String url) {
		if (pattern == null || url == null)
			return false;
		String[] subPatterns = pattern.split("\\s+");

		try {
			// 至少满足一条+开头的模式
			boolean containsCond = false;
			for (String subPattern : subPatterns) {
				if (subPattern == null || subPattern.length() == 0)
					continue;
				char includeChar = subPattern.charAt(0);
				subPattern = subPattern.substring(1);
				if (includeChar == '+' && url.matches(subPattern)) {
					containsCond = true;
					break;
				}
			}
			if (containsCond == false)
				return false;

			// 一条-开头的都不能满足
			boolean notContainsCond = true;
			for (String subPattern : subPatterns) {
				char includeChar = subPattern.charAt(0);
				subPattern = subPattern.substring(1);
				if (includeChar == '-' && url.matches(subPattern)) {
					notContainsCond = false;
					break;
				}
			}
			return notContainsCond;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	static class Point {
		int start;
		int end;

		public Point(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	private static Point getLoopStartEnd(LoopVarRule rule, String loopSample) {
		if (rule == null || loopSample == null)
			return null;
		String loopVar = rule.getLoopVarName();
		if (loopVar.matches("-?\\d+")) {
			int posStart = Short.MAX_VALUE;
			int posEnd = Short.MAX_VALUE;
			Matcher matcher = Pattern.compile("\\d+").matcher(loopSample);
			List<Integer> matchStarts = new ArrayList<Integer>();
			List<Integer> matchEnds = new ArrayList<Integer>();
			while (matcher.find()) {
				matchStarts.add(matcher.start());
				matchEnds.add(matcher.end());
			}
			if (Math.abs(Integer.valueOf(loopVar)) <= matchStarts.size()
					&& Math.abs(Integer.valueOf(loopVar)) <= matchEnds.size()
					&& Integer.valueOf(loopVar).intValue() != 0) {
				if (Integer.valueOf(loopVar).intValue() > 0) {
					posStart = matchStarts.get(Integer.valueOf(loopVar).intValue() - 1);
					posEnd = matchEnds.get(Integer.valueOf(loopVar).intValue() - 1);
				} else {
					posStart = matchStarts.get(matchStarts.size() + Integer.valueOf(loopVar).intValue());
					posEnd = matchEnds.get(matchEnds.size() + Integer.valueOf(loopVar).intValue());
				}
				return new Point(posStart, posEnd);
			}
		} else if (loopVar.trim().isEmpty() == false) {

			if (loopVar.equals("$")) {
				// 直接拼接到最后
				return new Point(loopSample.length(), loopSample.length());
			} else if (loopVar.equals("$$")) {
				// 放到最后一个文件的文件名后，例如index.html改变为index_1.html
				if (loopSample.lastIndexOf("/") >= 0) {
					return new Point(loopSample.lastIndexOf("."), loopSample.lastIndexOf("."));
				}
			} else {
				// 将该url用&和=分割
				int start = 0;
				int index;
				while ((index = loopSample.indexOf(loopVar + "=", start)) > 0) {
					if (loopSample.charAt(index - 1) == '&' || loopSample.charAt(index - 1) == '?') {
						start = index + (loopVar + "=").length();
						int end = start;
						while (end < loopSample.length() && loopSample.charAt(end) != '&') {
							end++;
						}
						if (end > start) {
							return new Point(start, end);
						}
						start = end;
					} else {
						start = index + (loopVar + "=").length();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 解析循环样本，如果其中存在循环变量，就将其进行替换
	 * loopSample中也可以引用变量，但是loopSample只能有一个，因此只会取出其中第一个匹配的
	 * 
	 * @param loopSample
	 * @param midVarValueMap
	 * @return
	 */
	public static String getLoopSample(String loopSample, Map<String, List<String>> midVarValueMap) {
		if (loopSample != null && loopSample.contains("{{") && loopSample.contains("}}")) {
			Pattern pattern = Pattern.compile("\\{\\{(.*?)}}");
			Matcher matcher = pattern.matcher(loopSample);
			StringBuffer buffer = new StringBuffer();
			List<String> list = new ArrayList<String>();
			int start = 0;
			while (matcher.find()) {
				String valName = matcher.group(1);
				list = midVarValueMap.get(valName);
				if (list != null && list.size() > 0) {
					buffer.append(loopSample.substring(start, matcher.start()) + list.get(0));
				}
				start = matcher.end();
			}
			buffer.append(loopSample.substring(start));
			return buffer.toString();
		}
		return loopSample;
	}

	private static List<String> getUrls(LoopParseRule loopParseRule, List<LoopVarRule> loopVarRules,
			final String loopSample, Map<String, List<String>> midVarValueMap, StringBuffer buffer)
					throws UnsupportedEncodingException {

		List<String> urls = new ArrayList<String>();
		int prevEnd = 0;
		// 把loopVarRules根据改变的位置进行排列
		urls.add("");

		Collections.sort(loopVarRules, new Comparator<LoopVarRule>() {

			@Override
			public int compare(LoopVarRule o1, LoopVarRule o2) {
				Point p1 = getLoopStartEnd(o1, loopSample);
				Point p2 = getLoopStartEnd(o2, loopSample);
				if (p1 == null && p2 == null)
					return 0;
				if (p1 == null)
					return -1;
				if (p2 == null)
					return 1;
				return p1.start - p2.start;
			}

		});

		for (LoopVarRule loopVarRule : loopVarRules) {
			Point point = getLoopStartEnd(loopVarRule, loopSample);
			if (point == null) {
				if (buffer != null) {
					buffer.append("没有找到循环变量的位置!循环变量为：" + loopVarRule.getLoopVarName());
				}
				return new ArrayList<String>();
			}
			String loopVar = loopVarRule.getLoopVarName();//
			int[] loopStartS = getLoopVars(loopVarRule.getLoopStart(), midVarValueMap);
			int[] loopEndS = getLoopVars(loopVarRule.getLoopEnd(), midVarValueMap);
			if (loopEndS != null && loopStartS != null && loopEndS.length != loopStartS.length) {
				// 进行复制
				if (loopStartS.length < loopEndS.length) {
					int[] tmp = new int[loopEndS.length];
					for (int i = 0; i < loopEndS.length; i++) {
						if (i < loopStartS.length) {
							tmp[i] = loopStartS[i];
						} else {
							tmp[i] = loopStartS[loopStartS.length - 1];
						}
					}
					loopStartS = tmp;
				}

				if (loopStartS.length > loopEndS.length) {
					int[] tmp = new int[loopStartS.length];
					for (int i = 0; i < loopEndS.length; i++) {
						if (i < loopStartS.length) {
							tmp[i] = loopStartS[i];
						} else {
							tmp[i] = loopStartS[loopStartS.length - 1];
						}
					}
					loopEndS = tmp;
				}
			}
			int loopStep = -1;
			if (loopVarRule.getLoopStep() != null) {
				loopStep = loopVarRule.getLoopStep().intValue();
			}
			String loopFormat = loopVarRule.getLoopFormat();
			String loopStrList = getLoopStrList(loopVarRule.getLoopStrList(), midVarValueMap);
			String loopStrEncode = loopVarRule.getLoopStrEncode();
			if (loopStrEncode != null && (loopStrEncode.equals("null") || loopStrEncode.equals("undefined"))) {
				loopStrEncode = null;
			}

			List<String> tmpUrls = new ArrayList<String>();
			if (loopVarRule.getLoopVarType().intValue() == 1) {// 字符串
				if (loopVar == null || loopStrList == null)
					continue;

				for (String tmpUrl : urls) {
					for (String var : loopStrList.split(",")) {
						if (loopStrEncode == null) {
							String loopUrl = tmpUrl + loopSample.substring(prevEnd, point.start) + var;
							tmpUrls.add(loopUrl);
						} else {
							String loopUrl = tmpUrl + loopSample.substring(prevEnd, point.start)
									+ URLEncoder.encode(var, loopStrEncode);
							tmpUrls.add(loopUrl);
						}
					}
				}

			} else {// 数字
				if (loopVar == null || loopStartS == null || loopEndS == null)
					continue;
				for (int ii = 0; ii < loopStartS.length; ii++) {
					if (loopStep == 0 || loopStartS[ii] < 0 || loopEndS[ii] < 0) {
						if (buffer != null) {
							buffer.append("循环生成变量有错：初始值：" + loopStartS[ii] + "，结束值：" + loopEndS[ii] + "，递增值：" + loopStep
									+ "\r\n");
						}
						LOGGER.info("循环生成变量有错：初始值：" + loopStartS[ii] + "，结束值：" + loopEndS[ii] + "，递增值：" + loopStep);
						return urls;// 防止死循环
					}

					for (String tmpUrl : urls) {
						for (int i = loopStartS[ii]; i <= loopEndS[ii]; i += loopStep) {
							String loopUrl = tmpUrl + loopSample.substring(prevEnd, point.start)
									+ String.format(loopFormat, i);
							tmpUrls.add(loopUrl);
						}
					}
				}
			}
			prevEnd = point.end;
			urls.clear();
			urls = tmpUrls;
		}

		if (prevEnd < loopSample.length()&&prevEnd>0) {
			List<String> tmpUrls = new ArrayList<String>();
			for (String tmpUrl : urls) {
				String loopUrl = tmpUrl + loopSample.substring(prevEnd);
				tmpUrls.add(loopUrl);
			}
			urls.clear();
			urls = tmpUrls;
		}
		
		if(prevEnd==0){//说明没有内容
			urls.clear();
		}

		return urls;
	}

	/**
	 * 如果使用循环生成，那么就会考虑中间结果映射、及循环变量的映射。 如果不适用循环生成，那么就考虑基本的xpath、正则表达式的抽取的结果。
	 * 解析网页内容，并且记录日志 版本2 增加了多个循环变量，因此多了循环变量的设置信息，具体为LoopParseRule和LoopVarRule
	 * 
	 * @param rules
	 *            每一条parseRuleTemp
	 * @param midMaps
	 *            前面的parseRuleTemp所对应的parseRuleMidMap
	 * @param loopParseRule
	 *            前面的parseRuleTemp所对应的loopParseRule
	 * @param loopVarRules，
	 *            前面的parseRule所对应的loopVarRule
	 *            一条parseRuleTemp会有多条parseRuleMidMapTemp,
	 *            一条parseRuleTemp会有多条loopParseRule(内容可能是重复的)
	 *            一条loopParseRule会有多条loopVarRule
	 * @param content
	 * @param url
	 * @return
	 */
	public static Map<String, UrlType> parseAndLog(ParseRuleTemp parseRule, List<ParseRuleMidMap> midMaps,
			List<LoopParseRule> loopParseRules, List<LoopVarRule> loopVarRules, String content, String url,
			StringBuffer buffer) {
		Map<String, UrlType> urlTypeMap = new LinkedHashMap<String, UrlType>();
		if (parseRule == null || url == null)
			return urlTypeMap;

		List<String> urls = new ArrayList<String>();
		String urlPattern = parseRule.getUrlPattern();
		// url模式过滤
		if (urlPatternFilter(urlPattern, url) == false) {
			LOGGER.info(url + "不符合模式" + urlPattern);
			if (buffer != null) {
				buffer.append(url + "不符合模式" + urlPattern + "\r\n");
			}
			return urlTypeMap;
		}
		LOGGER.info(url + "符合模式" + urlPattern);
		if (buffer != null) {
			buffer.append(url + "符合模式" + urlPattern + "\r\n");
		}
		// 通过url筛选
		String extractType = parseRule.getExtractType();
		// 映射的变量可能是多个，例如总页数 ，可能有多个
		Map<String, List<String>> midVarValueMap = new HashMap<String, List<String>>();
		List<String> regMatches = new ArrayList<String>();
		List<String> xpathMatches = new ArrayList<String>();
		if (content != null) {
			if (extractType.equals("1")) {
				// regex
				String scopeRegex = parseRule.getScopeRegex();
				String regex = parseRule.getRegex();
				// regex = Encoding.encoding(regex);
				String regexGroupId = parseRule.getRegexGroupId();
				Pattern pattern = null;
				try {
					if (scopeRegex != null && scopeRegex.trim().isEmpty() == false) {
						pattern = Pattern.compile(scopeRegex);// 捕获异常，插入日志
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (buffer != null) {
						buffer.append(e.getLocalizedMessage());
					}
					return urlTypeMap;
				}
				String scopeContent = content;
				if (scopeRegex != null && scopeRegex.isEmpty() == false) {
					Matcher matcher = pattern.matcher(content);
					if (matcher.find()) {
						scopeContent = matcher.group();
					}
				}
				try {
					pattern = Pattern.compile(regex);// 捕获异常，插入日志
				} catch (Exception e) {
					if (buffer != null) {
						buffer.append(e.getLocalizedMessage());
					}
					return urlTypeMap;
				}
				Matcher matcher = pattern.matcher(scopeContent);
				int id = Integer.valueOf(regexGroupId);
				Map<Integer, List<String>> groupContentMap = new HashMap<Integer, List<String>>();
				while (matcher.find()) {
					if (matcher.groupCount() >= id) {
						regMatches.add(matcher.group(id));
					}
					for (int i = 0; i <= matcher.groupCount(); i++) {
						if (groupContentMap.containsKey(i) == false) {
							groupContentMap.put(i, new ArrayList<String>());
						}
						groupContentMap.get(i).add(matcher.group(i));
					}
				}
				if (buffer != null) {
					buffer.append("抽取到内容：" + regMatches.toString() + "\r\n");
				}
				LOGGER.info("抽取到内容：" + regMatches.toString());
				if (midMaps != null) {
					for (ParseRuleMidMap midMap : midMaps) {
						if (midMap.getGroupId() == null || midMap.getVarName() == null)
							continue;
						int groupId = midMap.getGroupId().intValue();
						String varName = midMap.getVarName();
						midVarValueMap.put(varName, groupContentMap.get(groupId));
					}
				}
			}
			if (extractType.equals("2")) {
				// xpath
				// content = HtmlUtils.clean(content);
				SgmlPage page = null;
				try {
					page = HtmlUtils.getPage(content, url);
				} catch (IOException e1) {
					if (buffer != null) {
						buffer.append("生成DOM树结构出错！错误信息:" + e1.getMessage() + "\r\n");
					}
					e1.printStackTrace();
				}

				if (page != null) {
					String xpath = parseRule.getXpath();
					List<DomNode> nodes = (List<DomNode>) page.getByXPath(xpath);
					for (int i = 0; i < nodes.size(); i++) {
						Node node = nodes.get(i);
						xpathMatches.add(node.getTextContent());
					}
					if (buffer != null) {
						buffer.append("抽取到内容：" + xpathMatches.toString() + "\r\n");
					}
					LOGGER.info("抽取到内容：" + xpathMatches.toString());
					if (midMaps != null) {
						for (ParseRuleMidMap midMap : midMaps) {
							if (midMap.getGroupId() == null || midMap.getVarName() == null)
								continue;
							int groupId = midMap.getGroupId().intValue();
							String varName = midMap.getVarName();
							if (xpathMatches.size() > groupId) {
								if (midVarValueMap.containsKey(varName) == false) {
									midVarValueMap.put(varName, new ArrayList<String>());
								}
								midVarValueMap.get(varName).add(xpathMatches.get(groupId));
							}
						}
					}
				}
			}
			if (extractType.equals("3")) {// 使用自定义的方式
				String jarPath = parseRule.getJarPath();
				String className = parseRule.getClassName();

				// 下载jar到本地
				if (jarPath == null || className == null) {
					if (buffer != null) {
						buffer.append("自定义规则有内容为空：jarPath=" + jarPath + ",className=" + className + "\r\n");
					}
					return new HashMap<String, UrlType>();
				}
				try {
					FileSystem fs = FileSystem.get(new Configuration());
					jarPath = jarPath.substring("hdfs://".length());
					String jarName = jarPath.substring(jarPath.lastIndexOf("/") + 1);

					String localJarDir = ruleJarLocalDirLinux;
					if (System.getProperties().get("os.name").toString().toLowerCase().contains("windows")) {
						localJarDir = ruleJarLocalDirWin;
					}
					if (new File(localJarDir).exists() == false) {
						new File(localJarDir).mkdirs();
					} else if (new File(localJarDir).isDirectory() == false) {
						new File(localJarDir).deleteOnExit();
						new File(localJarDir).mkdirs();
					}
					if (new File(localJarDir + jarName).exists() == false) {
						fs.copyToLocalFile(new Path(jarPath), new Path(localJarDir));
					}
					URL[] urlsx = new URL[] {};

					MyClassLoader classLoader = new MyClassLoader(urlsx, null);
					classLoader.addJar(new File(localJarDir + jarName).toURI().toURL());
					Class<?> clazz = classLoader.loadClass(className);
					Method[] methods = clazz.getDeclaredMethods();
					Object object = clazz.newInstance();
					Method method = clazz.getDeclaredMethod("parse", String.class,String.class);
					Map<String, UrlType> map = (Map<String, UrlType>) method.invoke(object, content,url);
					classLoader.close();
					return map;
				} catch (Exception e) {
					e.printStackTrace();
					if (buffer != null) {
						buffer.append("执行自定义插件规则抽取url失败！" + e.getMessage() + "\r\n");
					}
					return new HashMap<String, UrlType>();
				}
			}
		}
		if (buffer != null) {
			buffer.append("抽取到映射内容：" + midVarValueMap.toString() + "\r\n");
		}
		LOGGER.info("抽取到映射内容：" + midVarValueMap.toString());

		// 是否循环生成url
		short urlWithLoop = parseRule.getUrlWithLoop();
		if (urlWithLoop == 1) {

			int i = 0;
			int loopVarSize = 0;
			if (loopParseRules.size() > 0) {
				loopVarSize = loopParseRules.get(0).getLoopVars().split(",").length;
			}
			for (LoopParseRule loopParRule : loopParseRules) {
				// 将loopVarRules进行区分，如果有parseRuleId，那么就按id来找，如果没有，就按顺序来找
				if (loopVarRules.isEmpty())
					break;
				List<LoopVarRule> relateLoopVarRules = new ArrayList<LoopVarRule>();
				if (loopVarRules.get(0).getLoopParseRuleId() != null) {
					for (LoopVarRule varRule : loopVarRules) {
						if (varRule.getLoopParseRuleId().equals(loopParRule.getId())) {
							relateLoopVarRules.add(varRule);
						}
					}
				} else {
					for (int j = loopVarSize * i; j < loopVarSize * (i + 1); j++) {
						relateLoopVarRules.add(loopVarRules.get(j));
					}
				}
				try {
					String loopSample = loopParRule.getLoopSample();
					if (loopSample == null || loopSample.isEmpty())
						loopSample = url; // 如果循环的样本为空，则处理当前的url
					urls.addAll(getUrls(loopParRule, relateLoopVarRules, loopSample, midVarValueMap, buffer));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					if (buffer != null) {
						buffer.append(e.getMessage());
					}
				}
			}

		} else {
			if (extractType.equals("1")) {
				urls.addAll(regMatches);
			}
			if (extractType.equals("2")) {
				urls.addAll(xpathMatches);
			}
		}

		UrlType urlType = null;
		if (parseRule.getUrlType().intValue() >= 0 && parseRule.getUrlType().intValue() < UrlType.values().length) {
			urlType = UrlType.values()[parseRule.getUrlType().intValue()];
		}
		// 地址规范化
		for (String urlt : urls) {
			String xurl = StringUtils.getURL(urlt, url);
			if (xurl != null) {
				urlTypeMap.put(xurl, urlType);
			}
		}
		LOGGER.info("共生成" + urlTypeMap.size() + "条数据.");

		return urlTypeMap;
	}

	public static Map<String, UrlType> parse(ParseRuleTemp rule, List<ParseRuleMidMap> midMaps,
			List<LoopParseRule> loopParseRules, List<LoopVarRule> loopVarRules, String content, String url) {
		return parseAndLog(rule, midMaps, loopParseRules, loopVarRules, content, url, null);
	}

	/**
	 * 
	 * @param var
	 * @param midVarValueMap
	 * @return
	 */
	private static String getLoopStrList(String var, Map<String, List<String>> midVarValueMap) {
		if (var != null) {
			if (var.matches("\\{\\{.*}}") && midVarValueMap != null) {
				Pattern pattern = Pattern.compile("\\{\\{(.*)}}");
				Matcher matcher = pattern.matcher(var);
				List<String> list = new ArrayList<String>();
				if (matcher.find()) {
					String valName = matcher.group(1);
					list = midVarValueMap.get(valName);
				}
				if (list != null) {
					StringBuffer buffer = new StringBuffer();
					if (list.size() > 0)
						buffer.append(list.get(0));
					for (int i = 1; i < list.size(); i++) {
						buffer.append("," + list.get(i));
					}
					return buffer.toString();
				}else{
					return null;
				}
			}
			
			if(var.matches("\\{\\{.*}}") && midVarValueMap == null){
				return null;
			}
		}
		return var;
	}

	/**
	 * 循环变量可以为映射的内容，也可以为数字，当循环变量为映射的内容时，也可以有多个， 但是每个开始都对应着每个结束
	 * 
	 * @param var
	 * @param midVarValueMap
	 * @return
	 */
	private static int[] getLoopVars(String var, Map<String, List<String>> midVarValueMap) {
		if (var != null) {
			if (var.matches("\\{\\{.*}}") && midVarValueMap != null) {
				Pattern pattern = Pattern.compile("\\{\\{(.*)}}");
				Matcher matcher = pattern.matcher(var);
				List<String> list = new ArrayList<String>();
				if (matcher.find()) {
					String valName = matcher.group(1);
					list = midVarValueMap.get(valName);
				}
				if (list != null) {
					int[] array = new int[list.size()];
					Arrays.fill(array, -1);
					for (int i = 0; i < list.size(); i++) {
						try {
							array[i] = Integer.valueOf(list.get(i));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					return array;
				}
			}
			if (var != null && var.matches("\\{\\{.*}}\\s*(\\+|\\-|\\*|/)\\s*(\\d+)") && midVarValueMap != null) {
				Pattern pattern = Pattern.compile("\\{\\{(.*)}}\\s*(\\+|\\-|\\*|/)\\s*(\\d+)");
				Matcher matcher = pattern.matcher(var);
				String op = null;
				String opV = null;
				if (matcher.find()) {
					String valName = matcher.group(1);
					op = matcher.group(2);
					opV = matcher.group(3);
					List<String> list = midVarValueMap.get(valName);
					int[] array = new int[list.size()];
					Arrays.fill(array, -1);
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i) != null && opV != null) {
							try {
								int value = Integer.valueOf(list.get(i));
								int opValue = Integer.valueOf(opV);
								if (op != null && op.charAt(0) == '+') {
									array[i] = value + opValue;
								}
								if (op != null && op.charAt(0) == '-') {
									array[i] = value - opValue;
								}
								if (op != null && op.charAt(0) == '*') {
									array[i] = value * opValue;
								}
								if (op != null && op.charAt(0) == '/') {
									array[i] = value / opValue;
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					return array;
				}
			}
			try {
				int[] array = new int[1];
				array[0] = Integer.valueOf(var);
				return array;
			} catch (Exception e) {
			}
		}
		return null;
	}
}
