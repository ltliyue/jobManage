package inspur.crawl.ruleManage.controller;


import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author maolihua
 * 
 */
public class StringUtils {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StringUtils.class);

	public static String simplifyPath(String path) {
		if (path.length() == 0) {
			return path;
		}
		String protocol = null;
		if(path.contains("://")){
			protocol = path.substring(0,path.indexOf("://"));
			path = path.substring(path.indexOf("://")+3);
		}

		String[] splits = path.split("/");
		LinkedList<String> stack = new LinkedList<String>();
		for (String s : splits) {
			if (s.length() == 0 || s.equals(".")) {
				continue;
			} else if (s.equals("..")) {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			} else {
				stack.push(s);
			}
		}

		if (stack.isEmpty()) {
			stack.push("");
		}
		String ret = "";
		while (!stack.isEmpty()) {
			ret += "/" + stack.removeLast();
		}
		if(ret.length()>0){
			ret = ret.substring(1);
		}
		if(protocol!=null){
			ret = protocol+"://"+ret;
		}
		//当路径以/结尾时，不能去掉，例如http://company.gongchang.com/p-110000/
		if(path.endsWith("/")){
			ret = ret+"/";
		}
		return ret;
	}

	public static String getURL(String refURL, String hostURL) {
		if (refURL != null && refURL.isEmpty() == false
				&& refURL.contains("://") == false) {
			if(refURL.startsWith("//")){
				String protocal="";
				if(hostURL!=null && hostURL.contains("://")){
					protocal = hostURL.substring(0,hostURL.indexOf("://"));
				}
				return protocal+":"+refURL;
			}
			
			if(hostURL.lastIndexOf("?")>=0){
				hostURL = hostURL.substring(0,hostURL.lastIndexOf("?"));
			}
			
			if(hostURL.lastIndexOf("#")>=0){
				hostURL = hostURL.substring(0,hostURL.lastIndexOf("#"));
			}
			
			refURL = refURL.replaceAll("'", "");
			if (refURL.endsWith("\\") || refURL.endsWith("/")) {
				refURL = refURL.substring(0, refURL.length() - 1);
			}
			if (refURL.startsWith("\\")) {
				refURL = "/" + refURL.substring(1);
			}
			if(refURL.isEmpty()) return null;
			if (refURL.charAt(0) == '/') {
				refURL = getHost(hostURL) + refURL;
			} else if(refURL.charAt(0)=='?'){
				refURL = hostURL + refURL;
			}else{
				refURL = getSite(hostURL) + '/' + refURL;
			}
			
			// norm, process all the .. or . or //
			String type = getProtocolType(refURL);
			if (refURL.contains("://")) {
				String path = refURL.substring(refURL.indexOf("://") + 3);
				path = type + "://" + simplifyPath(path);
			}
		}

		// 如果refURL中包含了跳转链接,获取该跳转地址
		// TODO:应该check 每个参数，是否是一个加密的地址
		// 暂时写死吧，将url=部分截取出来，使用Base64解码
		/**
		if (refURL != null && refURL.contains("url=")) {
			Pattern pattern = Pattern.compile("url=([^&]+?)(&|$)");
			Matcher matcher = pattern.matcher(refURL);
			if (matcher.find()) {
				String redirectUrl = matcher.group(1);
				refURL = new String(Base64.decodeBase64(redirectUrl));
			}
		}
		*/
		return simplifyPath(refURL);
	}
	
	public static String getProtocolType(String url) {

		String urlType = "http";

		if (url != null) {
			if (url.indexOf("://") >= 0) {
				urlType = url.substring(0, url.indexOf("://"));
			}

		}
		return urlType;
	}

	public static String getSite(String url) {
		String site = url;
		if (url != null && url.contains("/")) {
			String urlFname = url.substring(url.lastIndexOf("/") + 1);
			if (urlFname.contains(".html") || urlFname.contains(".htm")
					|| urlFname.contains(".shtml") || urlFname.contains(".stm")
					|| urlFname.contains(".jsp") || urlFname.contains(".asp")
					|| urlFname.contains(".php") || urlFname.contains("shtm")
					|| urlFname.contains("asp") || urlFname.contains(".bmp")
					|| urlFname.contains(".jpg") || urlFname.contains(".jpeg")
					|| urlFname.contains(".png") || urlFname.contains(".gif")
					|| urlFname.contains(".swf") || urlFname.contains(".fla")) {
				String urlType = null;
				if (url.indexOf("://") >= 0) {
					urlType = url.substring(0, url.indexOf("://"));
					url = url.substring(url.indexOf("://") + 3);
				}
				if (url.lastIndexOf("/") >= 0) {
					site = url.substring(0, url.lastIndexOf("/"));
				}
				if (urlType != null) {
					site = urlType + "://" + site;
				} else {
					site = "http://" + site;
				}
			}
		}
		return site;
	}

	public static String getHost(String url) {
		String host = null;
		if (url != null) {
			String urlType = null;
			if (url.indexOf("://") >= 0) {
				urlType = url.substring(0, url.indexOf("://"));
				url = url.substring(url.indexOf("://") + 3);
			}
			if (url.indexOf("/") >= 0) {
				host = url.substring(0, url.indexOf("/"));
			}else{
				host = url;
			}
			if (urlType != null) {
				host = urlType + "://" + host;
			} else {
				host = "http://" + host;
			}
		}
		return host;
	}

	public static String md5To16(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.substring(8, 24);
	}
	public static void main(String[] args) {
		System.out.println(md5("admin"));
	}
	public static String md5(String plainText) {
		if(plainText==null) return null;
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	public static String toCSV(String[] array) {
		if (array == null || array.length == 0)
			return null;
		StringBuffer buffer = new StringBuffer();
		buffer.append(array[0]);
		for (int i = 1; i < array.length; i++) {
			buffer.append("," + array[i]);
		}
		return buffer.toString();
	}

	public static String toMysql(String str) {
		if (str == null || str.isEmpty())
			return str;
		str = str.replace("\\", "\\\\");
		str = str.replaceAll("'", "\\\\'");
		// return str;
		// str = str.replace("\\", "\\\\");
		// str = str.replaceAll("'", "\\\\'");
		// str = str.replaceAll("\r\n", "");
		return str;
	}

	public static String subString(String str, int endIndex) {
		if (endIndex <= 0)
			return null;
		if (endIndex > str.length()) {
			endIndex = str.length();
		}
		return str.substring(0, endIndex);
	}

	public static int countUnique(String content, String[] tokens) {
		int c = 0;
		for (String token : tokens) {
			if (content.contains(token)) {
				c++;
			}
		}
		return c;
	}

	/**
	 * whether title contains the string in the collection.
	 * 
	 * @param title
	 * @param collections
	 * @return
	 */
	public static boolean contains(String title, Collection<String> collections) {
		for (String sub : collections) {
			if (title.contains(sub)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String getChineseCharacters(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		} else {
			str = str.replaceAll("[^\u4e00-\u9f5a]", "\u0020");
			str = str.replaceAll("\\s+", "\u0020");
			return str;
		}
	}

	/**
	 * Count how many times the substr occurs in the target str.
	 * 
	 * @param target
	 * @param substr
	 * @return
	 */
	public static int count(String target, String substr) {
		int c = 0;
		if (target == null || target.isEmpty() || substr == null
				|| substr.isEmpty())
			return 0;
		int fromIndex = target.indexOf(substr, 0);

		while (fromIndex >= 0 && fromIndex < target.length()) {
			c++;
			fromIndex += substr.length();
			fromIndex = target.indexOf(substr, fromIndex);
			if (fromIndex < 0)
				break;
		}
		return c;
	}

	/**
	 * Remove all the unprintable characters.
	 * 
	 * @param string
	 * @return
	 */
	public static String removeUnprintableChar(String string) {
		if (string == null)
			return string;
		return string
				.replaceAll(
						"[^\u4e00-\u9fa5\uF900-\uFA2D○\\w\\p{Punct}\\s\r\n\u3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff1f\u300a\u300b\u00bb]",
						"");
	}

	/**
	 * Replace all the unprintable characters.
	 * 
	 * @param string
	 * @return
	 */
	public static String removeUnprintableChar(String string, String rep) {
		if (string == null)
			return string;
		return string
				.replaceAll(
						"[^\u4e00-\u9fa5\uF900-\uFA2D○\\d\\w\\p{Punct}\\s\r\nu3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff1f\u300a\u300b]",
						rep);
	}

	/**
	 * Remove all the white chars.
	 * 
	 * @param string
	 * @return
	 */
	public static String removeWhiteChar(String string) {
		if (string == null)
			return string;
		return string.replaceAll("[\\s\r\n]", "");
	}

	/**
	 * Remove all the marks in string.
	 * 
	 * @param string
	 * @return
	 */
	public static String removeMarks(String string) {
		if (string == null)
			return string;
		String result = string
				.replaceAll(
						"[\\p{Punct}\u3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff1f\u300a\u300b]",
						"");
		return result;
	}

	/**
	 * Count the number of all the marks in string.
	 * 
	 * @param string
	 * @return
	 */
	public static int countMarks(String string) {
		if (string == null)
			return 0;
		Pattern pattern = Pattern
				.compile("[\\p{Punct}\u3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff1f\u300a\u300b]");
		Matcher matcher = pattern.matcher(string);
		int count = 0;
		while (matcher.find()) {
			matcher.group();
			count++;
		}
		return count;
	}

	/**
	 * Count the number of all the end marks in string.
	 * 
	 * @param string
	 * @return
	 */
	public static int countEndMarks(String string) {
		if (string == null)
			return 0;
		Pattern pattern = Pattern.compile("[?.!,\u3002\uff1f\uff01\uff0c]");
		Matcher matcher = pattern.matcher(string);
		int count = 0;
		while (matcher.find()) {
			matcher.group();
			count++;
		}
		return count;
	}

	/**
	 * Get the length of the longest common sub sequence of two strings.
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static int getLCSLength(String s1, String s2) {
		if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()) {
			return 0;
		}
		// to char array, to visit faster.
		char[] strArray1 = s1.toCharArray();
		char[] strArray2 = s2.toCharArray();
		int len1 = strArray1.length;
		int len2 = strArray2.length;

		// only reserve one line.
		int[] dpMaxLen = new int[len1 + 2];
		// dpMaxLen[len1+1] is the previous old value.
		Arrays.fill(dpMaxLen, 0);

		for (int i = 1; i <= len2; i++) {
			dpMaxLen[len1 + 1] = 0;
			for (int j = 1; j <= len1; j++) {
				int old = dpMaxLen[j];
				if (strArray2[i - 1] == strArray1[j - 1]) {
					dpMaxLen[j] = dpMaxLen[len1 + 1] + 1;
				} else if (dpMaxLen[j] < dpMaxLen[j - 1]) {
					dpMaxLen[j] = dpMaxLen[j - 1];
				}
				dpMaxLen[len1 + 1] = old;

			}
		}

		return dpMaxLen[len1];
	}

	/**
	 * 
	 * @param nodeValue
	 * @param regex
	 * @return
	 */
	public static int countRegex(String nodeValue, String regex) {
		if (nodeValue == null || nodeValue.isEmpty())
			return 0;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(nodeValue);
		int start = 0;
		int c = 0;
		while (matcher.find(start)) {
			c++;
			int end = matcher.end();
			start = end;
		}
		return c;
	}

	/**
	 * Remove all the digit in the string.
	 * 
	 * @param nodeValue
	 * @return
	 */
	public static String removeDigit(String string) {
		if (string == null)
			return string;
		String result = string.replaceAll("[0-9]", "");
		return result;
	}

	public static boolean isLetterOrDigit(char c) {
		return isLetter(c) || isDigit(c);
	}

	public static boolean isHexDigit(char c) {
		return isHexLetter(c) || isDigit(c);
	}

	public static boolean isLetter(char c) {
		return ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'));
	}

	public static boolean isHexLetter(char c) {
		return ((c >= 'a') && (c <= 'f')) || ((c >= 'A') && (c <= 'F'));
	}

	public static boolean isDigit(char c) {
		return (c >= '0') && (c <= '9');
	}

	/**
	 * Compact the continous spaces to one space.
	 * 
	 * @param s
	 * @return
	 */
	public static String compact(String s) {
		if (s == null)
			return s;
		s = s.replaceAll("[\\s\r\n]", " ");
		s = s.replaceAll("[\\s\r\n]{2,}", " ");
		return s;
	}

	/**
	 * 
	 * @param nodeContent
	 * @param minTokenLen
	 * @return
	 */
	public static String removeShortTokens(String str, int minTokenLen) {
		if (str == null || str.isEmpty())
			return str;

		String[] tokens = str.split("[\t\u0020]");
		StringBuffer newStrBuffer = new StringBuffer(str.length());
		for (String token : tokens) {
			if (StringUtils.isEnglishToken(token)
					|| token.length() > minTokenLen) {
				newStrBuffer.append(token);
				newStrBuffer.append("\u0020");
			}
		}

		str = newStrBuffer.toString();
		tokens = str.split("\r\n");
		newStrBuffer = new StringBuffer(str.length());
		for (String token : tokens) {
			if (StringUtils.isEnglishToken(token)
					|| (token.length() > minTokenLen && (token.length() > StringUtils
							.removeMarks(token).length()))) {
				newStrBuffer.append(token);
				newStrBuffer.append("\r\n");
			}
		}
		return newStrBuffer.toString();
	}

	public static boolean isEnglishToken(String token) {
		if (token == null || token.isEmpty())
			return false;
		return token.matches("[a-zA-Z]+");
	}

	/**
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static double lcsDistance(String str1, String str2) {
		if (str1 == str2)
			return 0;
		if (str1.isEmpty() || str1 == null || str2.isEmpty() || str2 == null)
			return 1;
		int len = StringUtils.getLCSLength(str1, str2);
		if (len == 0)
			return 1.0;
		double precision = 1.0 * len / str1.length();
		double recall = 1.0 * len / str2.length();
		double fscore = 2 * precision * recall / (precision + recall);
		return 1 - fscore;
	}


	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isURL(String str) {
		if (str.startsWith("http://") || str.startsWith("https://")
				|| str.startsWith("ftp://")) {
			try {
				new URL(str);
				return true;
			} catch (MalformedURLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public static boolean containsIgnoreCase(String str1, String str2) {
		if (str1 == null || str2 == null || str1.isEmpty() || str2.isEmpty()) {
			return false;
		}
		str1 = str1.toLowerCase();
		str2 = str2.toLowerCase();
		return str1.contains(str2);
	}

	/**
	 * Replace all the unprintable characters.
	 * 
	 * @param string
	 * @return
	 */
	public static String replaceUnprintableChar(String string, String rep) {
		if (string == null)
			return string;
		return string
				.replaceAll(
						"[^\u4e00-\u9fa5\uF900-\uFA2D\\w\\p{Punct}\\s\r\nu3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff1f\u300a\u300b]",
						rep);
	}

	public static Date extractDateFromUrl(String url) throws ParseException {
		if (url != null && url.isEmpty() == false) {
			String regex = "(^|\\s|:|：|〖|>|日期|时间|/)(\\d{4}-((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1])))(<|$|\\s|/)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(url);
			if (matcher.find()) {
				Date extractDate = new SimpleDateFormat("yyyy-MM-dd")
						.parse(matcher.group(2));
				int end = matcher.end();
				if (extractDate.after(new Date())) { // 在今天之后
					if (url.length() > end + 1) {
						url = url.substring(end + 1);
						return extractDate(url);
					} else {
						return null;
					}
				} else {
					return extractDate;
				}
			}

			regex = "(^|\\s|:|：|〖|>|日期|时间|/)(\\d{4}/((0?[1-9])|(1[0-2]))/((0?[1-9])|([1-2][0-9])|(3[0-1])))(<|$|\\s|/)";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(url);
			if (matcher.find()) {
				Date extractDate = new SimpleDateFormat("yyyy/MM/dd")
						.parse(matcher.group(2));
				int end = matcher.end();
				if (extractDate.after(new Date())) { // 在今天之后
					if (url.length() > end + 1) {
						url = url.substring(end + 1);
						return extractDate(url);
					} else {
						return null;
					}
				} else {
					return extractDate;
				}
			}

			regex = "(^|\\s|:|：|〖|>|日期|时间|/)(\\d{4}((0?[1-9])|(1[0-2]))((0?[1-9])|([1-2][0-9])|(3[0-1])))(<|$|\\s|/)";
			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(url);
			if (matcher.find()) {
				Date extractDate = new SimpleDateFormat("yyyyMMdd")
						.parse(matcher.group(2));
				int end = matcher.end();
				if (extractDate.after(new Date())) { // 在今天之后
					if (url.length() > end + 1) {
						url = url.substring(end + 1);
						return extractDate(url);
					} else {
						return null;
					}
				} else {
					return extractDate;
				}
			}
		}

		return null;
	}

	// TODO: 获取所有的日期
	public static Date extractDate(String str) {
		if (str != null && str.isEmpty() == false) {
			try {

				str = str.replaceAll("二十一", "21");
				str = str.replaceAll("二十二", "22");
				str = str.replaceAll("二十三", "23");
				str = str.replaceAll("二十四", "24");
				str = str.replaceAll("二十五", "25");
				str = str.replaceAll("二十六", "26");
				str = str.replaceAll("二十七", "27");
				str = str.replaceAll("二十八", "28");
				str = str.replaceAll("二十九", "29");
				str = str.replaceAll("二十", "20");

				str = str.replaceAll("三十一", "31");
				str = str.replaceAll("三十", "30");

				str = str.replaceAll("十月", "10月");
				str = str.replaceAll("十日", "10日");

				str = str.replaceAll("○", "0");
				str = str.replaceAll("零", "0");
				str = str.replaceAll("一", "1");
				str = str.replaceAll("壹", "1");
				str = str.replaceAll("二", "2");
				str = str.replaceAll("贰", "2");
				str = str.replaceAll("三", "3");
				str = str.replaceAll("仨", "3");
				str = str.replaceAll("四", "4");
				str = str.replaceAll("肆", "4");
				str = str.replaceAll("五", "5");
				str = str.replaceAll("伍", "5");
				str = str.replaceAll("六", "6");
				str = str.replaceAll("陆", "6");
				str = str.replaceAll("七", "7");
				str = str.replaceAll("柒", "7");
				str = str.replaceAll("八", "8");
				str = str.replaceAll("捌", "8");
				str = str.replaceAll("九", "9");
				str = str.replaceAll("玖", "9");

				str = str.replaceAll("十", "1");

				str = StringUtils.replaceUnprintableChar(str, " ");
				// ///////////////contains hour info///////////////////////
				String regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}-((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))(:|：)((0?[0-9])|([1-5][0-9]))(:|：)((0?[0-9])|([1-5][0-9])))(<|$|〗|\\s)";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("\\s+", " ");
					dateStr = dateStr.replaceAll("：", ":");
					int end = matcher.end();
					Date extractDate = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss").parse(dateStr);
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：〖|>)(\\d{4}-((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))时((0?[0-9])|([1-5][0-9]))分((0?[0-9])|([1-5][0-9]))秒)(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat(
							"yyyy-MM-dd HH时mm分ss秒").parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}-((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))(:|：)((0?[0-9])|([1-5][0-9])))(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("：", ":");
					dateStr = dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat("yyyy-MM-dd HH:mm")
							.parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}-((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))时((0?[0-9])|([1-5][0-9]))分)(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat("yyyy-MM-dd HH时mm分")
							.parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}/((0?[1-9])|(1[0-2]))/((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))(:|：)((0?[0-9])|([1-5][0-9]))(:|：)((0?[0-9])|([1-5][0-9])))(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("：", ":");
					dateStr = dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss").parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}/((0?[1-9])|(1[0-2]))/((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))时((0?[0-9])|([1-5][0-9]))分((0?[0-9])|([1-5][0-9]))秒)(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat(
							"yyyy/MM/dd HH时mm分ss秒").parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}/((0?[1-9])|(1[0-2]))/((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))(:|：)((0?[0-9])|([1-5][0-9])))(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("：", ":");
					dateStr = dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat("yyyy/MM/dd HH:mm")
							.parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}/((0?[1-9])|(1[0-2]))/((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))时((0?[0-9])|([1-5][0-9]))分)(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat("yyyy/MM/dd HH时mm分")
							.parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}年((0?[1-9])|(1[0-2]))月((0?[1-9])|([1-2][0-9])|(3[0-1]))日\\s*((0?[1-9])|(1[0-9])|(2[0-4]))(:|：)((0?[0-9])|([1-5][0-9]))(:|：)((0?[0-9])|([1-5][0-9])))(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("：", ":");
					dateStr = dateStr.replaceAll("\\s+", "");
					Date extractDate = new SimpleDateFormat(
							"yyyy年MM月dd日HH:mm:ss").parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}年((0?[1-9])|(1[0-2]))月((0?[1-9])|([1-2][0-9])|(3[0-1]))日\\s*((0?[1-9])|(1[0-9])|(2[0-4]))时((0?[0-9])|([1-5][0-9]))分((0?[0-9])|([1-5][0-9]))秒)(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("\\s+", "");
					Date extractDate = new SimpleDateFormat(
							"yyyy/MM/ddHH时mm分ss秒").parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}年((0?[1-9])|(1[0-2]))月((0?[1-9])|([1-2][0-9])|(3[0-1]))日\\s*((0?[1-9])|(1[0-9])|(2[0-4]))(:|：)((0?[0-9])|([1-5][0-9])))(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("：", ":");
					dateStr = dateStr.replaceAll("\\s+", "");
					Date extractDate = new SimpleDateFormat("yyyy年MM月dd日HH:mm")
							.parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}年((0?[1-9])|(1[0-2]))月((0?[1-9])|([1-2][0-9])|(3[0-1]))日\\s*((0?[1-9])|(1[0-9])|(2[0-4]))时((0?[0-9])|([1-5][0-9]))分)(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("\\s+", "");
					Date extractDate = new SimpleDateFormat("yyyy年MM月dd日HH时mm分")
							.parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				// /////////////////////contains only date
				// info////////////////////////////////
				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}-((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1])))(<|$|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					Date extractDate = new SimpleDateFormat("yyyy-MM-dd")
							.parse(matcher.group(2));
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}/((0?[1-9])|(1[0-2]))/((0?[1-9])|([1-2][0-9])|(3[0-1])))(<|$|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					Date extractDate = new SimpleDateFormat("yyyy/MM/dd")
							.parse(matcher.group(2));
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(\\d{4}年(0?[1-9]|1[0-2])月((0?[1-9])|([1-2][0-9])|(3[0-1]))日)(<|$|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					Date extractDate = new SimpleDateFormat("yyyy年MM月dd日")
							.parse(matcher.group(2));
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							if (str.length() > end + 1) {
								str = str.substring(end + 1);
								return extractDate(str);
							} else {
								return null;
							}
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				// ////////////////contains no
				// year//////////////////////////////////
				regex = "(^|\\s|:|：|〖|>|日期|时间)(((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))(:|：)((0?[0-9])|([1-5][0-9]))(:|：)((0?[0-9])|([1-5][0-9])))(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				int date = Calendar.getInstance().get(Calendar.YEAR);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("：", ":");
					dateStr = date + "-" + dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss").parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))时((0?[0-9])|([1-5][0-9]))分((0?[0-9])|([1-5][0-9]))秒)(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = date + "-" + dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat(
							"yyyy-MM-dd HH时mm分ss秒").parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))(:|：)((0?[0-9])|([1-5][0-9])))(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("：", ":");
					dateStr = date + "-" + dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat("yyyy-MM-dd HH:mm")
							.parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(((0?[1-9])|(1[0-2]))-((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))时((0?[0-9])|([1-5][0-9]))分)(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = date + "-" + dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat("yyyy-MM-dd HH时mm分")
							.parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(((0?[1-9])|(1[0-2]))/((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))(:|：)((0?[0-9])|([1-5][0-9]))(:|：)((0?[0-9])|([1-5][0-9])))(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("：", ":");
					dateStr = date + "/" + dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss").parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(((0?[1-9])|(1[0-2]))/((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))时((0?[0-9])|([1-5][0-9]))分((0?[0-9])|([1-5][0-9]))秒)(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = date + "/" + dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat(
							"yyyy/MM/dd HH时mm分ss秒").parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(((0?[1-9])|(1[0-2]))/((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))(:|：)((0?[0-9])|([1-5][0-9])))(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("：", ":");
					dateStr = date + "/" + dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat("yyyy/MM/dd HH:mm")
							.parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(((0?[1-9])|(1[0-2]))/((0?[1-9])|([1-2][0-9])|(3[0-1]))\\s+((0?[1-9])|(1[0-9])|(2[0-4]))时((0?[0-9])|([1-5][0-9]))分)(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = date + "/" + dateStr.replaceAll("\\s+", " ");
					Date extractDate = new SimpleDateFormat("yyyy/MM/dd HH时mm分")
							.parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(((0?[1-9])|(1[0-2]))月((0?[1-9])|([1-2][0-9])|(3[0-1]))日\\s*((0?[1-9])|(1[0-9])|(2[0-4]))(:|：)((0?[0-9])|([1-5][0-9]))(:|：)((0?[0-9])|([1-5][0-9])))(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("：", ":");
					dateStr = date + "年" + dateStr.replaceAll("\\s+", "");
					Date extractDate = new SimpleDateFormat(
							"yyyy年MM月dd日HH:mm:ss").parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(((0?[1-9])|(1[0-2]))月((0?[1-9])|([1-2][0-9])|(3[0-1]))日\\s*((0?[1-9])|(1[0-9])|(2[0-4]))时((0?[0-9])|([1-5][0-9]))分((0?[0-9])|([1-5][0-9]))秒)(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = date + "/" + dateStr.replaceAll("\\s+", "");
					Date extractDate = new SimpleDateFormat(
							"yyyy/MM/ddHH时mm分ss秒").parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(((0?[1-9])|(1[0-2]))月((0?[1-9])|([1-2][0-9])|(3[0-1]))日\\s*((0?[1-9])|(1[0-9])|(2[0-4]))(:|：)((0?[0-9])|([1-5][0-9])))(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = dateStr.replaceAll("：", ":");
					dateStr = date + "年" + dateStr.replaceAll("\\s+", "");
					Date extractDate = new SimpleDateFormat("yyyy年MM月dd日HH:mm")
							.parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(^|\\s|:|：|〖|>|日期|时间)(((0?[1-9])|(1[0-2]))月((0?[1-9])|([1-2][0-9])|(3[0-1]))日\\s*((0?[1-9])|(1[0-9])|(2[0-4]))时((0?[0-9])|([1-5][0-9]))分)(<|$|〗|\\s)";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					String dateStr = matcher.group(2);
					dateStr = date + "年" + dateStr.replaceAll("\\s+", "");
					Date extractDate = new SimpleDateFormat("yyyy年MM月dd日HH时mm分")
							.parse(dateStr);
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(\\d+)个?小时以?前";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					Date extractDate = DateUtils.addHours(new Date(),
							-Integer.valueOf(matcher.group(1)));
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

				regex = "(\\d+)个?分钟以?前";
				pattern = Pattern.compile(regex);
				matcher = pattern.matcher(str);
				if (matcher.find()) {
					Date extractDate = DateUtils.addMinutes(new Date(),
							-Integer.valueOf(matcher.group(1)));
					int end = matcher.end();
					if (extractDate.after(new Date())) { // 在今天之后
						if (str.length() > end + 1) {
							str = str.substring(end + 1);
							return extractDate(str);
						} else {
							return null;
						}
					} else {
						return extractDate;
					}
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static String reverse(String str) {
		if (str == null)
			return null;
		StringBuffer buffer = new StringBuffer("");
		for (int i = str.length() - 1; i >= 0; i--) {
			buffer.append(str.charAt(i));
		}
		return buffer.toString();
	}

}
