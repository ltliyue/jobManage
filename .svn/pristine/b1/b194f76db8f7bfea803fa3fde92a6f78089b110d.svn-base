package inspur.crawl.ruleManage.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonParseException;

import com.google.common.base.Strings;

import inspur.crawl.common.generator.StringUtil;

public class TestStringUtils {
	
	public static void testGetUrl(){
		JsonParseException e;
		System.out.println(StringUtils.getURL("/cate/kuaican", "http://anyang.lashou.com"));
	}
	
	public static byte[] hexStr2Bytes(String src)    
	{    
	    int m=0,n=0;    
	    int l=src.length()/2;    
	    System.out.println(l);    
	    byte[] ret = new byte[l];    
	    for (int i = 0; i < l; i++)    
	    {    
	        m=i*2+1;    
	        n=m+1;    
	        ret[i] = Byte.decode("0x" + src.substring(i*2, m) + src.substring(m,n));    
	    }    
	    return ret;    
	}    
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(StringUtils.getURL("../../../zibo/bj4010200/p5/", "http://jobs.zhaopin.com/zibo/bj4010200/p06/"));
		Pattern  pattern = Pattern.compile("");
		System.out.println("http://api.s.m.taobao.com/search.json?vm=nw&search_wap_mall=false&ttid=700171%40taobao_android_5.1.3&search_wap_mall=false&vm=nw&sort=_sale&itemfields=distance%2Carea%2CcommentCount&propertyMenu=on&info=wifi&utsid=U3hUdYvmdjwDAHvy6do%2FBKmQ_12278902_1418029672792&page=1&apptimestamp=1418029681&longitude=117.12766&st=1418029681443&deviceId=AtLtx8-GVSBP8FIwWEfB3_4yRqjz_LaUnsQiXp5kNB_W&filterEmpty=true&sk=3c880ff6bd702e418e534b9f5715741b&app=inshop&imei=863165024789957&refpid=&n=30&setting_on=imgBanners%2Cuserdoc&m=shopitemsearch&sputips=on&v=*&utd_id=U3hUdYvmdjwDAHvy6do%2FBKmQ&t=1418029675&filterUnused=true&sellerId=436200192&imsi=460015181610948&latitude=36.662727&active_bd=1&appKey=21646297".
		matches("http://api.s.m.taobao.com/search.json\\?.*?page=1.*?"));
		}
}
