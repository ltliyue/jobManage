package inspur.crawl.common.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCompare {
	public static int turnTime(Date time){
		SimpleDateFormat st=new SimpleDateFormat("yyyy-MM-dd");
		return Integer.parseInt(st.format(time));
		}
	public static String turnTime1(Date time){
		SimpleDateFormat st=new SimpleDateFormat("yyyyMMddHHmm");
		return st.format(time);
		}
	public static String turnTime4(Date time){
		SimpleDateFormat st=new SimpleDateFormat("yyyy-MM-dd");
		return st.format(time);
		}
	public static String turnTime3(Date time){
		SimpleDateFormat st=new SimpleDateFormat("HHmmss");
		return st.format(time);
		}
	public static String turnTime2(Date time){
		SimpleDateFormat st=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return st.format(time);
		}
	public static Date StringToDate(String s){
		Date time=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			time=sd.parse(s);
			}catch (ParseException e) { 
				System.out.println("��������ڸ�ʽ����0��"); 
				}
			return time;
			}
	public static Date StringToDate1(String s){
		Date time=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		try{
			time=sd.parse(s);
			}catch (ParseException e) { 
				System.out.println("��������ڸ�ʽ����1��"); 
				}
			return time;
			}
	public static Date StringToDate2(String s){
		Date time=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy��MM��dd��");
		try{
			time=sd.parse(s);
			}catch (ParseException e) { 
				System.out.println("��������ڸ�ʽ����2��::"+s); 
				}
			return time;
			}
	public static void main(String[] args) {
		String noticeid = "2015-06-01";
		String noticeid1 = "2015-05-01";
		System.out.println(turnTime3(new Date()));
	}
	} 
