package inspur.crawl.common.tools;

import java.io.UnsupportedEncodingException;

public class Encoding {
	public static String encoding(String s) {
		try {
			s = new String(s.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}
