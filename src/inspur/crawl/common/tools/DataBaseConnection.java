package inspur.crawl.common.tools;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 建立连接到指定数据库
 */
public class DataBaseConnection {
	public static void main(String[] args) {
		getConnection("jdbc:oracle:thin:@60.208.102.69:1521:tmsjcj","crawlm","crawlm");
	}
	public static Connection getConnection(String url, String userName, String password) {
		Connection conn = null;
		try{  
            Class.forName("oracle.jdbc.driver.OracleDriver");  
            conn= DriverManager.getConnection(url,userName,password);  
            System.out.println("连接数据库成功");  
        }catch(Exception e) {  
            e.printStackTrace();  
            System.out.println("建立数据库发生错误！");  
        }finally {  
            return conn;  
        }  
	}
}
