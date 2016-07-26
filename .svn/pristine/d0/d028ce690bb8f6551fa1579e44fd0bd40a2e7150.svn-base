package inspur.crawl.urlMonitor.service;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Cassandra数据操作基类
 */
public abstract class CassandraOpsBase {
	protected static Cluster cluster = null;
	protected static Session session = null;

	private String CassandraClusterContactPoints = null;
	private String CrawlerDbKeyspaceName = null;

	public CassandraOpsBase() {
		if (this.session == null || this.cluster == null) {
			prepare();
		}
	}

	/**
	 * 从配置文件加载Cassandra相关的信息，如Cluster地址，登录信息，Keyspace名称，pooling选项，等等。
	 */
	protected void loadConfiguration() {
		Properties prop = new Properties();
		try {
			// load a properties file from class path
			InputStream s = getClass().getResourceAsStream("/cassandra.properties");
			if (s == null) {
				throw new RuntimeException("Cannot find cassandra.properties");
			}
			prop.load(s);
			this.CassandraClusterContactPoints = prop.getProperty("cluster.contactPoints", "10.19.22.133");
			this.CrawlerDbKeyspaceName = prop.getProperty("keyspace.name", "crawler_db");
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}

	}

	private void prepare() {
		loadConfiguration();

		this.cluster = Cluster.builder().addContactPoints(this.CassandraClusterContactPoints.split(",")).build();

		this.session = this.cluster.connect(this.CrawlerDbKeyspaceName);
		System.out.println("session::" + session);
	}

	public static void close() {
		if (cluster != null && !cluster.isClosed()) {
			cluster.close();
		}
	}

	public String getCrawlerDbKeyspaceName() {
		return CrawlerDbKeyspaceName;
	}  
	
}
