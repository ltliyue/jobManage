package inspur.crawl.common.generator;

/**
 * 
 */
public class StringUtil {
	
	/**
	 * 返回拆分后的最后一个字符
	 * @param str
	 * @param regex
	 * @return
	 */
	public static String splitLast(String str, String regex) {
		String[] split = str.split(regex);
		return split[split.length - 1];
	}
	
	/**
	 * 判断一个字符串对象是否是 null 或者 是 empty
	 * @param str
	 * @return str == null || str.isEmpty()
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}
	
	/**
	 * 判断字符串不为 空 串
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return str != null && !str.trim().isEmpty();
	}
}
