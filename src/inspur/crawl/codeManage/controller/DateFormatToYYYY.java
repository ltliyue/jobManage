package inspur.crawl.codeManage.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatToYYYY {
	public static String dateFormate(String oldDate) throws ParseException{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String formatedDate = sdf.format(oldDate);
		
//		方式二
		DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy",Locale.US);
		Date date = (Date)formatter.parse(oldDate);
		System.out.println(date);        

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String formatedDate = cal.get(Calendar.YEAR) + "-" + ((cal.get(Calendar.MONTH) + 1)-9>0?(cal.get(Calendar.MONTH) + 1):"0"+(cal.get(Calendar.MONTH) + 1)) + "-" + (cal.get(Calendar.DATE)-9>0?cal.get(Calendar.DATE):"0"+cal.get(Calendar.DATE)) + " " +(cal.get(Calendar.HOUR_OF_DAY)-9>0?cal.get(Calendar.HOUR_OF_DAY):"0"+cal.get(Calendar.HOUR_OF_DAY))+":"+(cal.get(Calendar.MINUTE)-9>0?cal.get(Calendar.MINUTE):"0"+cal.get(Calendar.MINUTE))+":"+(cal.get(Calendar.SECOND)-9>0?cal.get(Calendar.SECOND):"0"+cal.get(Calendar.SECOND));
		return formatedDate;
		
	} 
}
