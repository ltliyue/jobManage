package inspur.crawl.common.tools;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 提取数字
 * @author toshiba
 *
 */
public class Tqsz {
	public static void main(String[] args) {
		String s = "num3_name:连接1;&3num3_url:null&3";
		String pat_date = "(?=)";
		System.out.println(zhengze1("num3_name:(.*?)&3", s));
	}
	public static String tq_sz(String num){
		String regEx="[^0-9.]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(num);
		return m.replaceAll("").trim();
	}
	public static String tq_csz(String num){
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(num);
		return m.replaceAll("").trim();
	}
	//锟桔革拷锟�-
	public static String tq_jg(String num){
		String regEx = "[^0-9.-]";
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(num);
		return m.replaceAll("").trim();
	}
	public static String zhengze(String pat,String s){
		Pattern pattern = Pattern.compile(pat);
		Matcher matcher = pattern.matcher(s);
		if(matcher.find()){
			return matcher.group();
		}
		return "";
	}
	public static String zhengze1(String pat,String s){
		Pattern pattern = Pattern.compile(pat);
		Matcher matcher = pattern.matcher(s);
		if(matcher.find()){
			return matcher.group(1);
		}
		return "";
	}
}
