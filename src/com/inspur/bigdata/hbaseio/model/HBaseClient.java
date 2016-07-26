package com.inspur.bigdata.hbaseio.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FilterList.Operator;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.filter.KeyOnlyFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import inspur.crawl.ruleManage.controller.StringUtils;

public class HBaseClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(HBaseClient.class);

	// 声明静态配置 HBaseConfiguration
	private Configuration cfg = HBaseConfiguration.create();
	private HBaseAdmin admin = null;
	private static HBaseClient client = null;

	private HBaseClient() {
		cfg.set("hbase.client.retries.number", "3");
		cfg.set("zookeeper.recovery.retry", "0");
		// cfg.set("zookeeper.znode.parent", "/hbase-unsecure");
		try {
			InputStream s = getClass().getResourceAsStream("/hbase-client.properties");
			if (s == null) {
				throw new RuntimeException("Cannot find hbase-client.properties");
			}
			Properties prop = new Properties();
			prop.load(s);
			cfg.set("hbase.zookeeper.property.clientPort",
					prop.getProperty("hbase.zookeeper.property.clientPort", "2181"));
			cfg.set("hbase.zookeeper.quorum",
					prop.getProperty("hbase.zookeeper.quorum", "Hai:2181,Dan:2181,Peng:2181"));
			admin = new HBaseAdmin(cfg);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public static HBaseClient getInstance() {
		if (client == null) {
			synchronized (HBaseClient.class) {
				if (client == null) {
					try {
						client = new HBaseClient();
					} catch (Exception e) {
						LOGGER.error("" + e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
		return client;
	}

	public boolean tableExists(String tableName) {
		try {
			if (admin != null) {
				return admin.tableExists(tableName);
			}
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.info(Thread.currentThread().getName() + "重新连接！");
			try {
				admin = new HBaseAdmin(cfg);
				return admin.tableExists(tableName);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

	public boolean columnFamilyExists(String tableName, String columnFamily) {
		if (tableExists(tableName)) {
			try {
				if (admin != null) {
					HTableDescriptor tableDesc = admin.getTableDescriptor(Bytes.toBytes(tableName));
					HColumnDescriptor[] colFamiliesDes = tableDesc.getColumnFamilies();
					for (HColumnDescriptor des : colFamiliesDes) {
						if (des.getNameAsString().equals(columnFamily)) {
							return true;
						}
					}
				}
			} catch (TableNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public List<String> columnFamilyExists(String tableName, String... columnFamilies) {
		List<String> notExistColFamilies = new ArrayList<String>();
		if (tableExists(tableName)) {
			try {
				HTableDescriptor tableDesc = admin.getTableDescriptor(Bytes.toBytes(tableName));
				HColumnDescriptor[] colFamiliesDes = tableDesc.getColumnFamilies();
				boolean exist = false;
				for (String columnFamily : columnFamilies) {
					for (HColumnDescriptor des : colFamiliesDes) {
						if (des.getNameAsString().equals(columnFamily)) {
							exist = true;
							break;
						}
					}
					if (exist == false) {
						notExistColFamilies.add(columnFamily);
					}
				}
			} catch (TableNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return notExistColFamilies;
	}

	// 创建一张表，通过HBaseAdmin HTableDescriptor来创建
	public void creat(String tablename, String columnFamily) throws Exception {
		if (admin.tableExists(tablename)) {
			LOGGER.info("table Exists!");
			// if (true == HBaseClient.delete(tablename))
			// LOGGER.info("Delete table:" + tablename + "success!");
			// System.exit(0);
			return;
		} else {
			HTableDescriptor tableDesc = new HTableDescriptor(tablename);
			HColumnDescriptor hcd = new HColumnDescriptor(columnFamily);
			hcd.setMaxVersions(3);
			// hcd.setTimeToLive(5184000);
			// 设置数据保存在内存中以提高响应速度
			hcd.setInMemory(true);
			// hcd.setMinVersions(5);

			tableDesc.addFamily(hcd);
			// 设置一个region中的store文件的最大size;当一个region中的最大store文件达到这个size时，region就开始分裂
			tableDesc.setMaxFileSize(1024 * 1024 * 1024);
			// 设置region内存中的memstore的最大值，当memstore达到这个值时，开始往磁盘中刷数据
			tableDesc.setMemStoreFlushSize(512 * 1024 * 1024);
			// 如果在数据还没有flush进硬盘时，regionserver
			// down掉了，内存中的数据将丢失。要想解决这个场景的问题就需要用到WAL（Write-Ahead-Log）
			// 设置写WAL日志的级别，示例中设置的是同步写WAL，该方式安全性较高，但无疑会一定程度影响性能，需要根据具体场景选择使用
			// tableDesc.setDurability(Durability.SYNC_WAL);

			// tableDesc.addFamily(new HColumnDescriptor(columnFamily));

			admin.createTable(tableDesc);
			LOGGER.info("create table success!");
		}
	}

	public void addColumnFamily(String table, String colFamily) {
		addColumnFamilies(table, colFamily);
	}

	public void addColumnFamilies(String table, String... colFamilies) {
		try {
			admin.disableTable(table);
			for (String colFamily : colFamilies) {
				HColumnDescriptor hcd = new HColumnDescriptor(colFamily);
				admin.addColumn(table, hcd);
			}
			admin.enableTable(table);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 添加一条数据，通过HTable Put为已经存在的表来添加数据
	public void put(String tablename, String row, String columnFamily, String column, String data) throws Exception {
		if (data != null) {
			HTable table = new HTable(cfg, tablename);
			Put p1 = new Put(Bytes.toBytes(row));
			p1.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(data));
			table.put(p1);
			// LOGGER.info("put
			// '"+row+"','"+columnFamily+":"+column+"','"+data.substring(0,5)+"'");
			table.close();
		}
	}

	/**
	 * 添加一条数据
	 * 
	 * @param tablename
	 * @param row
	 * @param map
	 * @throws Exception
	 */
	public void put(String tablename, String row, String columnFamily, Map<String, String> map) throws Exception {
		HTable table = new HTable(cfg, tablename);
		Put p1 = new Put(Bytes.toBytes(row));
		for (String column : map.keySet()) {
			p1.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(map.get(column)));
		}
		table.put(p1);
		table.close();
	}

	// 添加一条数据，通过HTable Put为已经存在的表来添加数据
	public void put(String tablename, String row, String columnFamily, String column, byte[] data) throws IOException {
		HTable table = new HTable(cfg, tablename);
		Put p1 = new Put(Bytes.toBytes(row));
		p1.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), data);
		table.put(p1);
		// LOGGER.info("put
		// '"+row+"','"+columnFamily+":"+column+"','"+data.substring(0,5)+"'");
		table.close();
	}

	public void put(String tablename, byte[] row, String columnFamily, String column, String data) throws IOException {
		if (tablename == null || row == null || columnFamily == null || column == null)
			return;
		HTable table = new HTable(cfg, tablename);
		Put p1 = new Put(row);
		p1.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(data));
		table.put(p1);
		// LOGGER.info("put
		// '"+row+"','"+columnFamily+":"+column+"','"+data.substring(0,5)+"'");
		table.close();
	}

	public void put(HTable table, String row, String columnFamily, String column, byte[] data) throws Exception {
		Put p1 = new Put(Bytes.toBytes(row));
		p1.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), data);
		table.put(p1);
		// LOGGER.info("put
		// '"+row+"','"+columnFamily+":"+column+"','"+data.substring(0,5)+"'");
	}

	public HTable openTable(String tablename) throws IOException {
		return new HTable(cfg, tablename);
	}

	public void closeTable(HTable table) throws IOException {
		if (table != null) {
			table.close();
		}
	}

	// 添加一条数据，通过HTable Put为已经存在的表来添加数据
	public void put(HTable table, String row, String columnFamily, String column, String data) throws Exception {
		if (data == null)
			return;
		Put p1 = new Put(Bytes.toBytes(row));
		p1.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(data));
		table.put(p1);
		// LOGGER.info("put
		// '"+row+"','"+columnFamily+":"+column+"','"+data.substring(0,5)+"'");
	}

	// 添加或更新多条数据，通过HTable Put为已经存在的表来添加数据
	/*
	 * 批量写入多行记录，这样做的好处是批量执行，只需要一次网络I/O开销， 这对于对数据实时性要求高，网络传输RTT高的情景下可能带来明显的性能提升
	 * tablename 表名 hashmapKeyValue 要存储的Rowkey 数值 对 columnFamily 列簇 column 列名
	 */
	public void putList(String tablename, HashMap<String, String> hashmapKeyValue, String columnFamily, String column)
			throws Exception {
		HashMap<String, String> hashmap = hashmapKeyValue;
		HTable table = new HTable(cfg, tablename);
		/*
		 * for(String k:hashmap.keySet()) { hashmap.get(k); }
		 */
		Set set = hashmap.entrySet();
		Iterator iterator = set.iterator();
		List<Put> puts = new ArrayList<Put>();
		while (iterator.hasNext()) {
			Map.Entry mapentry = (Map.Entry) iterator.next();

			Put p1 = new Put(Bytes.toBytes(mapentry.getKey().toString()));
			p1.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(mapentry.getValue().toString()));
			puts.add(p1);
		}

		table.put(puts);
		table.close();
		// LOGGER.info("put
		// '"+row+"','"+columnFamily+":"+column+"','"+data+"'");
	}

	// 添加 文档图片 等 各类文档
	public void putDoc(String tablename, String row, String columnFamily, String column, String data)
			throws IOException {
		HTable table = new HTable(cfg, tablename);
		File file = new File(data);

		byte[] bytes = FileUtils.readFileToByteArray(file);

		Put put = new Put(Bytes.toBytes(row));

		put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), bytes);
		table.put(put);

		/*
		 * 
		 * FileInputStream in = null; FileOutputStream out = null;
		 * 
		 * 
		 * 
		 * 
		 * byte[] buf = new byte[1024];
		 * 
		 * FileUtils.readFileToByteArray(file)
		 * 
		 * 
		 * int len = 0; in = new FileInputStream(file); while((len =
		 * in.read(buf)) == -1) { break; } Put put=new Put(Bytes.toBytes(row));
		 * put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), buf);
		 * table.put(put);
		 */

	}

	// 读取HBASE Cell信息 存储为文档
	public void getCellValuToFile(String tableName, String rowKey, String familyName, String columnName)
			throws IOException {
		HTable table = new HTable(cfg, tableName);
		Get get = new Get(rowKey.getBytes());
		get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName)); // 获取指定列族和列修饰符对应的列
		Result result = table.get(get);
		int i = 0;
		for (org.apache.hadoop.hbase.KeyValue kv : result.list()) {
			byte[] bs = kv.getValue();

			File file = new File("E:/hbase_OUT" + i + ".xls");

			FileUtils.writeByteArrayToFile(file, bs);

			i++;

			/*
			 * FileOutputStream out = null; i++; out = new
			 * FileOutputStream("E:/hbase_OUT"+i+".doc",true);
			 * out.write(bs,0,bs.length); out.close();
			 */
			LOGGER.info("bs.length: " + bs.length);
			// FieldUtils.writeByteArrayToFile(new
			// java.io.File("D:\\crawlimage"+destinationfileName),bs);
			/*
			 * LOGGER.info("family:" + Bytes.toString(kv.getFamily()));
			 * LOGGER.info("qualifier:" + Bytes.toString(kv.getQualifier()));
			 * LOGGER.info("value:" + Bytes.toString(kv.getValue()));
			 * LOGGER.info("Timestamp:" + kv.getTimestamp());
			 */
			LOGGER.info("-------------------------------------------");
		}
	}

	// 获取某一行信息
	public Result get(String tablename, String row) throws IOException {
		HTable table = new HTable(cfg, tablename);
		Get g = new Get(Bytes.toBytes(row));

		Result result = table.get(g);
		return result;
		// LOGGER.info("Get: "+result+"__"+result.value());
	}

	// 显示所有数据，通过HTable Scan来获取已有表的信息
	public void scan(String tablename) throws Exception {
		HTable table = new HTable(cfg, tablename);
		Scan s = new Scan();
		ResultScanner rs = table.getScanner(s);
		for (Result r : rs) {
			LOGGER.info("Scan: " + r);
		}
	}

	// 删除表
	public boolean delete(String tablename) throws IOException {

		HBaseAdmin admin = new HBaseAdmin(cfg);
		if (admin.tableExists(tablename)) {
			try {
				admin.disableTable(tablename);
				admin.deleteTable(tablename);
			} catch (Exception ex) {
				ex.printStackTrace();
				return false;
			}

		}
		return true;
	}

	// 查询某列数据的多个版本
	public void getResultByVersion(String tableName, String rowKey, String familyName, String columnName)
			throws IOException {
		HTable table = new HTable(cfg, tableName);
		Get get = new Get(Bytes.toBytes(rowKey));
		get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
		get.setMaxVersions(5);
		Result result = table.get(get);
		for (org.apache.hadoop.hbase.KeyValue kv : result.list()) {
			LOGGER.info("family:" + Bytes.toString(kv.getFamily()));
			System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));
			LOGGER.info("value:" + Bytes.toString(kv.getValue()));
			LOGGER.info("Timestamp:" + kv.getTimestamp());
			LOGGER.info("-------------------------------------------");
		}
	}

	/**
	 * 为表添加数据（适合知道有多少列族的固定表）
	 * 
	 * @rowKey rowKey
	 * @tableName 表名
	 * @column1 第一个列族列表
	 * @value1 第一个列的值的列表
	 * @column2 第二个列族列表
	 * @value2 第二个列的值的列表
	 **/
	public void addData(String rowKey, String tableName, String[] column1, String[] value1, String[] column2,
			String[] value2) throws IOException {
		Put put = new Put(Bytes.toBytes(rowKey));// 设置rowkey
		HTable table = new HTable(cfg, tableName);// HTabel负责跟记录相关的操作如增删改查等//
		HColumnDescriptor[] columnFamilies = table.getTableDescriptor().getColumnFamilies();// 获取所有的列族
		for (int i = 0; i < columnFamilies.length; i++) {
			String familyName = columnFamilies[i].getNameAsString(); // 获取列族名
			if (familyName.equals("article")) { // article列族put数据
				for (int j = 0; j < column1.length; j++) {
					put.add(Bytes.toBytes(familyName), Bytes.toBytes(column1[j]), Bytes.toBytes(value1[j]));
				}
			}
			if (familyName.equals("author")) { // author列族put数据
				for (int j = 0; j < column2.length; j++) {
					put.add(Bytes.toBytes(familyName), Bytes.toBytes(column2[j]), Bytes.toBytes(value2[j]));

				}
			}
		}
		table.put(put);
		LOGGER.info("add data Success!");

	}

	/**
	 * 更新表中的某一列
	 * 
	 **/
	public void updateRow(String tableName, String rowKey, String familyName, String columnName, String value)
			throws IOException {

		HTable table = new HTable(cfg, tableName);
		Put put = new Put(Bytes.toBytes(rowKey));
		put.add(Bytes.toBytes(familyName), Bytes.toBytes(columnName), Bytes.toBytes(value));
		table.put(put);
		LOGGER.info("update table Success!");

	}

	/**
	 * 查询表中簇下面额某一列值
	 * 
	 **/
	public void getResultByColumn(String tableName, String rowKey, String familyName, String columnName)
			throws IOException {
		HTable table = new HTable(cfg, tableName);
		Get get = new Get(Bytes.toBytes(rowKey));
		get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName)); // 获取指定列族和列修饰符对应的列
		Result result = table.get(get);
		for (org.apache.hadoop.hbase.KeyValue kv : result.list()) {
			LOGGER.info("family:" + Bytes.toString(kv.getFamily()));
			System.out.println("qualifier:" + Bytes.toString(kv.getQualifier()));
			LOGGER.info("value:" + Bytes.toString(kv.getValue()));
			LOGGER.info("Timestamp:" + kv.getTimestamp());
			LOGGER.info("-------------------------------------------");
		}
	}

	/**
	 * 遍历HBASE表
	 * 
	 **/
	public ResultScanner getResultScann(String tableName, String start_rowkey, String stop_rowkey) throws IOException {
		Scan scan = new Scan();
		scan.setStartRow(Bytes.toBytes(start_rowkey));
		scan.setStopRow(Bytes.toBytes(stop_rowkey));
		ResultScanner rs = null;
		HTable table = new HTable(cfg, tableName);
		try {
			rs = table.getScanner(scan);
			return rs;
		} finally {
			rs.close();
		}
	}

	/*
	 * 输入纳税人名称获取网页摘要 strTableName 表名 strColumnFamily 列簇名 Strqualifier 行名 strName
	 * 纳税人名称
	 */
	public void getZYByName(String strTableName, String strColumnFamily, String Strqualifier, String strName)
			throws IOException {
		String tablename = strTableName;
		String columnFamily = strColumnFamily;
		String qualifier = Strqualifier;
		HTable table = new HTable(cfg, tablename);
		FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
		SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes(columnFamily),
				Bytes.toBytes(qualifier), CompareOp.EQUAL, Bytes.toBytes(strName));
		filterList.addFilter(filter);
		Scan scan = new Scan();
		scan.setCacheBlocks(true);
		scan.setCaching(3000);
		scan.setFilter(filterList);
		ResultScanner rs = table.getScanner(scan);
		int i = 0;
		for (Result r : rs) {
			if (Bytes.toString(r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(Strqualifier))) != null) {
				LOGGER.info("Scan: " + Bytes.toString(r.getRow())
						+ Bytes.toString(r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier))) + "==" + r);
				i++;
			}
		}
		LOGGER.info(String.valueOf(i));
		table.close();

		// return "";
	}

	/*
	 * 输入行键获取网页内容 strTableName 表名 strColumnFamily 列簇名 strKey 行键 StrQualifier
	 * 网页内容对应的 Qualifier Result 返回网页内容字符串
	 */
	public String getContentByKey(String strTableName, String strColumnFamily, String strKey, String StrQualifier)
			throws IOException {
		String tablename = strTableName;
		String columnFamily = strColumnFamily;
		String Strqualifier = StrQualifier;
		String StrReurn = null;
		byte[] startColumn = Bytes.toBytes(strKey);
		byte[] endColumn = Bytes.toBytes(strKey);
		HTable table = new HTable(cfg, tablename);
		Scan scan = new Scan(startColumn, endColumn);
		ResultScanner rs = table.getScanner(scan);
		int i = 0;
		for (Result r : rs) {
			if (Bytes.toString(r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(Strqualifier))) != null) {
				StrReurn = Bytes.toString(r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(Strqualifier)));
			}
		}
		rs.close();
		table.close();
		return StrReurn;
	}

	public byte[] getByteContentByKey(String strTableName, String strColumnFamily, String strKey, String StrQualifier)
			throws IOException {
		String tablename = strTableName;
		String columnFamily = strColumnFamily;
		String Strqualifier = StrQualifier;
		byte[] startColumn = Bytes.toBytes(strKey);
		byte[] endColumn = Bytes.toBytes(strKey);
		HTable table = new HTable(cfg, tablename);
		Scan scan = new Scan(startColumn, endColumn);
		ResultScanner rs = table.getScanner(scan);
		byte[] bytes = null;
		int i = 0;
		for (Result r : rs) {
			if (Bytes.toString(r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(Strqualifier))) != null) {
				bytes = r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(Strqualifier));
			}
		}
		rs.close();
		table.close();
		return bytes;
	}

	public String getContentByKey(HTable table, String strColumnFamily, String strKey, String StrQualifier)
			throws IOException {
		String columnFamily = strColumnFamily;
		String Strqualifier = StrQualifier;
		String StrReurn = null;
		byte[] startColumn = Bytes.toBytes(strKey);
		byte[] endColumn = Bytes.toBytes(strKey);
		Get get = new Get(Bytes.toBytes(strKey));
		get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(Strqualifier));
		Result r = table.get(get);
		if (r != null && Bytes.toString(r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(Strqualifier))) != null) {
			StrReurn = Bytes.toString(r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(Strqualifier)));
		}
		return StrReurn;
	}

	/**
	 * HBASE API直接使用utf-8编码String 生成byte，可能会存在问题
	 * 
	 * @param table
	 * @param strColumnFamily
	 * @param strKey
	 * @param StrQualifier
	 * @return
	 * @throws IOException
	 */
	public String getContentByKey(HTable table, String strColumnFamily, byte[] strKey, String StrQualifier)
			throws IOException {
		String columnFamily = strColumnFamily;
		String Strqualifier = StrQualifier;
		String StrReurn = null;
		byte[] startColumn = strKey;
		byte[] endColumn = strKey;
		Get get = new Get(strKey);
		get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(Strqualifier));
		Result r = table.get(get);
		if (r != null && Bytes.toString(r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(Strqualifier))) != null) {
			StrReurn = Bytes.toString(r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(Strqualifier)));
		}
		return StrReurn;
	}

	// 获取所有数据，通过HTable Scan来获取所有的
	public ArrayList getAllKeyscan(String tablename) throws Exception {
		HTable table = new HTable(cfg, tablename);
		Scan s = new Scan();
		s.setFilter(new FirstKeyOnlyFilter());
		s.setCaching(1000);
		s.setCacheBlocks(false);
		s.setFilter(new FirstKeyOnlyFilter());
		ResultScanner rs = table.getScanner(s);
		ArrayList strList = new ArrayList();
		for (Result r : rs) {
			strList.add(Bytes.toString(r.getRow()));

		}
		LOGGER.info("Scan: " + strList.size());
		rs.close();
		table.close();
		return strList;
	}

	public ResultScanner getResultScanner(String tablename) throws Exception {
		HTable table = new HTable(cfg, tablename);
		Scan s = new Scan();
		ResultScanner rs = table.getScanner(s);
		return rs;
	}

	public List<String> getResultByColumnValueFilter(String tableName, String columnFamily, String column,
			CompareOp compareOp, String columnValue) throws IOException {

		List<String> keys = new ArrayList<String>();
		HTable table = new HTable(cfg, tableName);
		Scan scan = new Scan();
		SingleColumnValueFilter scvf = null;
		if (columnValue == null) {
			scvf = new SingleColumnValueFilter(Bytes.toBytes(columnFamily), Bytes.toBytes(column), compareOp,
					(byte[]) null);
		} else {
			scvf = new SingleColumnValueFilter(Bytes.toBytes(columnFamily), Bytes.toBytes(column), compareOp,
					Bytes.toBytes(columnValue));
		}
		Filter rf = new KeyOnlyFilter();
		scan.setFilter(new FilterList(Operator.MUST_PASS_ALL, scvf, rf));
		ResultScanner rs = table.getScanner(scan);
		for (Result r : rs) {
			if (Bytes.toString(r.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column))) != null) {
				String key = Bytes.toString(r.getRow());
				keys.add(key);
			}
		}
		rs.close();
		return keys;
	}

	public boolean containsKey(String tableName, String rowKey) throws IOException {
		String StrReurn = null;
		byte[] startColumn = Bytes.toBytes(rowKey);
		byte[] endColumn = Bytes.toBytes(rowKey);
		HTable table = new HTable(cfg, tableName);
		Scan scan = new Scan(startColumn, endColumn);
		scan.setFilter(new KeyOnlyFilter());
		ResultScanner rs = table.getScanner(scan);
		int i = 0;
		for (Result r : rs) {
			i++;
		}
		rs.close();
		table.close();
		return i > 0;
	}

	public boolean containsKey(HTable table, String rowKey) throws IOException {
		String StrReurn = null;
		byte[] startColumn = Bytes.toBytes(rowKey);
		byte[] endColumn = Bytes.toBytes(rowKey);
		Scan scan = new Scan(startColumn, endColumn);
		scan.setFilter(new KeyOnlyFilter());
		ResultScanner rs = table.getScanner(scan);
		int i = 0;
		for (Result r : rs) {
			i++;
		}
		rs.close();
		return i > 0;
	}

	/**
	 * 
	 * @param tableName
	 * @throws IOException
	 */
	public void deleteNullQualifer(String tableName, String columnFamily, String column) throws IOException {

		Scan scan = new Scan();
		scan.setCaching(1000);
		// scan.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
		HTable table = new HTable(cfg, tableName);
		ResultScanner rs = table.getScanner(scan);
		int count = 0;
		int size = 0;
		try {
			for (Result result : rs) {
				size++;
				String row = new String(result.getRow());
				byte[] valueBuffer = result.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
				System.out.println(row + "\t" + (valueBuffer == null ? null : new String(valueBuffer)));
				if (valueBuffer == null || valueBuffer.length == 0) {
					count++;
					Delete ds = new Delete(result.getRow());
					table.delete(ds);
					System.out.println(row + "已被删除   " + count);
				}
			}
			System.out.println(size);

		} finally {
			table.close();
		}
	}

	public void deleteRow(String tableName, byte[] row) throws IOException {
		if(tableName==null||row==null) return;
		HTable table = new HTable(cfg, tableName);
		Delete ds = new Delete(row);
		table.delete(ds);
	}

	public void deleteByQualifer(String tableName, String columnFamily, String cell, CompareOp op, String value)
			throws IOException {
		List<String> rowkeys = getResultByColumnValueFilter(tableName, columnFamily, cell, op, value);
		System.out.println("delete size:" + rowkeys.size());

		HTable table = new HTable(cfg, tableName);
		int count = 0;
		try {
			for (String rowkey : rowkeys) {
				count++;
				byte[] row = Bytes.toBytes(rowkey);
				Delete ds = new Delete(row);
				table.delete(ds);
				System.out.println(rowkey + "已被删除   " + count);

			}
		} finally {
			table.close();
		}
		rowkeys = getResultByColumnValueFilter(tableName, columnFamily, cell, op, value);
		System.out.println("after delete,left size:" + rowkeys.size());
	}

	/**
	 * 导出数据，把每条数据存入一个文本文件中，文本文件的名字为rowkey
	 * 
	 * @param tableName
	 * @param columnFamily
	 * @param cell
	 * @param filterCell
	 * @param compare
	 * @param filterValue
	 * @param outDir
	 * @throws IOException
	 */
	public void export(String tableName, String columnFamily, String cell, String filterCell, CompareOp op,
			String filterValue, File outDir) throws IOException {
		LOGGER.info(Thread.currentThread().getName() + "开始从HBase中导出数据!");
		List<String> rowkeys = new ArrayList<String>();
		if (outDir.exists() == false) {
			outDir.mkdirs();
		}
		try {
			rowkeys = getResultByColumnValueFilter(tableName, columnFamily, filterCell, op, filterValue);
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOGGER.info(Thread.currentThread().getName() + "总共有" + rowkeys.size() + "条数据");
		HTable htable = openTable(tableName);
		for (String rowkey : rowkeys) {
			String content = getContentByKey(htable, columnFamily, rowkey, cell);
			if (content != null && content.length() > 100) {
				File outF = new File(outDir, rowkey);
				FileUtils.writeStringToFile(outF, content, "utf-8");
			}
		}
	}

	/**
	 * 获取最后一行
	 * 
	 * @param tableName
	 * @param columnFamily
	 * @param qualifier
	 * @return 最后一行数据的rowkey
	 */
	public static byte[] getLastRow(String tableName, String columnFamily, String qualifier) {
		HBaseClient client = HBaseClient.getInstance();
		try {
			HTable table = client.openTable(tableName);
			Scan scan = new Scan();
			scan.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier));
			scan.setReversed(true);
			ResultScanner rs = table.getScanner(scan);
			for (Result r : rs) {
				return r.getRow();
			}
			rs.close();
			table.close();
			// 倒序scan，获取最后一条的rowkey
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static long incrementColumnValue(String tableName, final byte[] row, final byte[] family,
			final byte[] qualifier, final long amount) throws IOException {
		HTable table = client.openTable(tableName);
		return table.incrementColumnValue(row, family, qualifier, amount);
	}

	/**
	 * 根据RowFilter获取数据
	 * 
	 * @param tableName
	 *            表名字
	 * @param rf
	 *            行过滤器
	 * @param reversed
	 *            数据扫描的顺序
	 * @param size
	 *            总共获取多好数据，如果size=-1,就表示获取所有的满足条件的数据
	 * @return
	 * @throws IOException
	 */
	public List<byte[]> getByRowFilter(String tableName, RowFilter rf, boolean reversed, int size) throws IOException {

		List<byte[]> keys = new ArrayList<byte[]>();
		HTable table = new HTable(cfg, tableName);
		Scan scan = new Scan();
		scan.setReversed(reversed);
		if (size >= 0) {
			scan.setMaxResultSize(size);
		}
		Filter kof = new KeyOnlyFilter();
		scan.setFilter(new FilterList(Operator.MUST_PASS_ALL, kof, rf));
		ResultScanner rs = table.getScanner(scan);
		for (Result r : rs) {
			keys.add(r.getRow());
		}
		rs.close();
		return keys;
	}

	/**
	 * 
	 * @param tableName
	 * @param startKey
	 * @param endKey
	 * @param filters
	 * @param reversed
	 * @return
	 * @throws IOException
	 */
	public ResultScanner getByFilters(String tableName, byte[] startKey, byte[] endKey, FilterList filters,
			boolean reversed) throws IOException {
		if (tableName == null || startKey == null || endKey == null)
			return null;
		List<byte[]> keys = new ArrayList<byte[]>();
		HTable table = new HTable(cfg, tableName);
		Scan scan = new Scan();
		scan.setStartRow(startKey);
		scan.setStopRow(endKey);
		scan.setReversed(reversed);
		if (filters != null) {
			scan.setFilter(filters);
		}
		return table.getScanner(scan);
	}

	public ResultScanner getByFilters(String tableName, FilterList filters, boolean reversed) throws IOException {
		if (tableName == null)
			return null;
		List<byte[]> keys = new ArrayList<byte[]>();
		HTable table = new HTable(cfg, tableName);
		Scan scan = new Scan();
		scan.setReversed(reversed);
		if (filters != null) {
			scan.setFilter(filters);
		}
		return table.getScanner(scan);
	}

	/**
	 * @param args
	 * @throws Exception
	 * @throws IOExcepftion
	 */
	public static void main(String[] args) throws Exception {

		// HBaseClient.export("xwzx", "f",
		// "mainContent", "websource",
		// CompareOp.EQUAL, "txcjHg",
		// new File("D:/maolh/语料/正文"));

		// HBaseClient.getInstance().put("tsdb-counter", "metrics", "cf",
		// "counter", Bytes.toBytes(0L));
		// HBaseClient.getInstance().put("tsdb-counter", "tagk", "cf",
		// "counter", Bytes.toBytes(0L));
		// HBaseClient.getInstance().put("tsdb-counter", "tagv", "cf",
		// "counter", Bytes.toBytes(0L));

		byte[] bytes = HBaseClient.getInstance().getLastRow("testcounter", "cf", "z");
		System.out.println(Bytes.toString(bytes));

		String keyt = StringUtils.md5("http://data.eastmoney.com/Notice/Noticelist.aspx?type=0&market=sh_a&date=&page=492");
		System.out.println(keyt);

		// HBaseClient.deleteByQualifer("ssxw", "f", "title", CompareOp.EQUAL,
		// "错误：您所请求的网址（URL）无法获取");
		// HBaseClient.deleteNullQualifer("imagetab", "f", "contentTime");
		// HBaseClient.deleteNullQualifer("csstab", "f", "websource");

		// HBaseClient.creat("imagetab", "f");
		// HBaseClient.creat("csstab", "f");
		// System.out.println(keyt);
		// 23d221cbfabd00a662aca82155618fa4_ec61ec57b14a49511c770f812e6eb35b
		String result = HBaseClient.getInstance().getContentByKey("global_extract_content", "cf",
				"317_6e456ff79f2a3eccffb4e98948738a65_L7hwDZ3QV6knrrsAxtia652491c-5754-4cca-b3f1-99c1cc452655_0", "task_url");
		System.out.println(result);
		//
		// Document document = HtmlUtils.getDocument(result);
		// Node body = HtmlUtils.getFirstNodeByTagName(document, "body");
		// System.out.println(HtmlUtils.getTextContentWithoutLinks(body));
		// System.out.println(HtmlUtils.getTextContentWithoutLinks(body).length());
		//
		// List<String> keys = HBaseClient.getResultByColumnValueFilter("qybk",
		// "f", "indexStatus", CompareOp.LESS_OR_EQUAL, "0");
		// for (String key : keys) {
		// // System.out.println(key);
		// }
		// System.out.println(keys.size());

	}

}
