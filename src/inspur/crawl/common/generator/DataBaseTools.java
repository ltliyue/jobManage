package inspur.crawl.common.generator;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 */
public class DataBaseTools {
	static Logger logger = LoggerFactory.getLogger(DataBaseTools.class);
	
	Connection conn;
	/**
	 * @param conn2
	 */
	public DataBaseTools(Connection conn) {
		this.conn = conn;
	}


	/**
	 * @param driverClass
	 * @param connectionURL
	 * @param userId
	 * @param password
	 */
	public static DataBaseTools getInstanc(String driverClass, String connectionURL, String userId, String password) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connectionURL, userId, password);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new DataBaseTools(conn);
	}
}

class DbConfig {
	DbConfig(String url, String uid, String pwd) {
		this.url = url;
		this.uid = uid;
		this.pwd = pwd;
	}
	public String url = "";
	public String uid = "";
	public String pwd = "";
}
