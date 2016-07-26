package com.inspur.bigdata.hbaseio.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Properties;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inspur.bigdata.hbaseio.exceptions.RowkeyGenerateException;
import com.inspur.bigdata.hbaseio.pojo.DataPoint;
import com.inspur.bigdata.hbaseio.pojo.Metric;
import com.inspur.bigdata.hbaseio.pojo.NumberEncode;
import com.inspur.bigdata.hbaseio.pojo.TimeSeriesData;
import com.inspur.publicTools.ByteMathUtils;

/**
 * 参见《OpenTSDB详解》
 * 
 * @author maolh
 *
 */
public class OpenTSDB {

	private static String defaultColumnFamily;
	public static final String TSDB_DATA_TABLENAME = "tsdb";
	public static final String TSDB_TSUID_TABLENAME = "tsdb-uid";
	public static final String TSDB_TSUID_COLUMNFAMILY_ID = "id";
	public static final String TSDB_TSUID_COLUMNFAMILY_NAME = "name";
	public static final String TSDB_TSUID_METRIC_QUALIFIER = "metrics";
	public static final String TSDB_TSUID_TAGK_QUALIFIER = "tagk";
	public static final String TSDB_TSUID_TAGV_QUALIFIER = "tagv";
	public static final String TSDB_COUNTER_TABLENAME = "tsdb-counter";
	public static final String TSDB_COUNTER_QUALIFIER = "counter";

	public static final int TSDB_UID_BYTE_SIZE = 3;

	private static final Logger logger = LoggerFactory.getLogger(OpenTSDB.class);

	static {
		try {
			// load a properties file from class path
			InputStream s = OpenTSDB.class.getResourceAsStream("/hbase-client.properties");
			if (s == null) {
				throw new RuntimeException("Cannot find hbase-client.properties");
			}
			Properties prop = new Properties();
			prop.load(s);
			defaultColumnFamily = prop.getProperty("hbase.default.columnfamily", "cf");
			s.close();
			// ODO:检查相应的表盒列是否存在，如果不存在，就创建

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * 把src插入到dst从start开始的地方，返回插入结束的位置
	 * 
	 * @param dst
	 * @param src
	 * @param start
	 * @return
	 */
	private static int appendBytes(byte[] dst, byte[] src, int start) {
		if (src == null)
			return start;
		for (int i = start; i < dst.length && i - start < src.length; i++) {
			dst[i] = src[i - start];
		}
		return start + src.length;
	}

	public static byte[] generateKey(DataPoint point) throws RowkeyGenerateException {
		//point.getTargetId()可以为空
		if (point == null || point.getMetric() == null || point.getMetricTarget() == null
				|| point.getStage() == null)
			return null;
		long time = point.getTime();
		// 时间取整点值
		long time_within_hour = time - time % 3600000;
		// 时间截取，去掉表示毫秒的后三位
		time_within_hour = time_within_hour / 1000;
		byte[] keyBuffer = new byte[128];// key must be less than 128 byte;
		int keyLen = 0;
		// 生成各种需要的uid
		byte[] metric = null;
		byte[] metric_target_tagk_uid = null;
		byte[] metric_target_tagv_uid = null;
		byte[] target_id_tagk_uid = null;
		byte[] target_id_tagv_uid = null;
		byte[] stage_tagk_uid = null;
		byte[] stage_tagv_uid = null;
		try {
			metric = getMetricUID(point.getMetric());
			metric_target_tagk_uid = getTagKUID(DataPoint.METRIC_TARGET_TAG);
			metric_target_tagv_uid = getTagVUID(point.getMetricTarget().toString().toLowerCase());
			target_id_tagk_uid = getTagKUID(DataPoint.TARGET_ID_TAG);
			target_id_tagv_uid = getTagVUID(point.getTargetId());
			stage_tagk_uid = getTagKUID(DataPoint.STAGE_TAG);
			stage_tagv_uid = getTagVUID(point.getStage().toString().toLowerCase());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RowkeyGenerateException(e, "生成UID出错");
		}

		// 首先放入Metric度量
		keyLen = appendBytes(keyBuffer, metric, keyLen);
		// 放入时间戳
		keyLen = appendBytes(keyBuffer, com.inspur.publicTools.ByteMathUtils.encodeToByte(time_within_hour, 4), keyLen);

		// 放入各种tag
		// 首先放入metric_target
		keyLen = appendBytes(keyBuffer, metric_target_tagk_uid, keyLen);
		keyLen = appendBytes(keyBuffer, metric_target_tagv_uid, keyLen);

		// 然后放入target_id
		keyLen = appendBytes(keyBuffer, target_id_tagk_uid, keyLen);
		keyLen = appendBytes(keyBuffer, target_id_tagv_uid, keyLen);

		// 然后放入stage_id
		keyLen = appendBytes(keyBuffer, stage_tagk_uid, keyLen);
		keyLen = appendBytes(keyBuffer, stage_tagv_uid, keyLen);
		//target_id_tagv_uid == null可以为空，不判断
		if (metric_target_tagk_uid == null || metric_target_tagv_uid == null || target_id_tagk_uid == null
				 || stage_tagk_uid == null || stage_tagv_uid == null) {
			throw new RowkeyGenerateException("TSDB的各组成uid不能为空，metric_target_tagk_uid=" + metric_target_tagk_uid
					+ ",metric_target_tagv_uid=" + metric_target_tagv_uid + ",target_id_tagk_uid=" + target_id_tagk_uid
					+ ",target_id_tagv_uid=" + target_id_tagv_uid + ",stage_tagk_uid=" + stage_tagk_uid
					+ ",stage_tagv_uid=" + stage_tagv_uid);
		}

		if (keyLen >= 128) {
			throw new RowkeyGenerateException("TSDB的row key 不应该超过128个Byte！");
		}
		return Arrays.copyOfRange(keyBuffer, 0, keyLen);
	}

	// 所有的tag在数据库中转换为小写
	private static byte[] getTagVUID(String tagv) throws IOException {
		if (tagv == null)
			return null;
		byte[] uid = HBaseClient.getInstance().getByteContentByKey(TSDB_TSUID_TABLENAME, TSDB_TSUID_COLUMNFAMILY_ID,
				tagv.toLowerCase(), TSDB_TSUID_TAGV_QUALIFIER);
		// 如果不存在该string
		if (uid == null) {
			uid = getNextCounterValue(TSDB_TSUID_TAGV_QUALIFIER);
			// 将该映射关系写入表中
			// 正向关系 id
			HBaseClient.getInstance().put(TSDB_TSUID_TABLENAME, tagv.toLowerCase(), TSDB_TSUID_COLUMNFAMILY_ID,
					TSDB_TSUID_TAGV_QUALIFIER, uid);
			// 反向关系 name
			HBaseClient.getInstance().put(TSDB_TSUID_TABLENAME, uid, TSDB_TSUID_COLUMNFAMILY_NAME,
					TSDB_TSUID_TAGV_QUALIFIER, tagv.toLowerCase());
		}
		return uid;
	}

	// 所有的tag在数据库中转换为小写
	private static byte[] getTagKUID(String tagk) throws IOException {
		if (tagk == null)
			return null;
		byte[] uid = HBaseClient.getInstance().getByteContentByKey(TSDB_TSUID_TABLENAME, TSDB_TSUID_COLUMNFAMILY_ID,
				tagk.toLowerCase(), TSDB_TSUID_TAGK_QUALIFIER);
		// 如果不存在该string
		if (uid == null) {
			uid = getNextCounterValue(TSDB_TSUID_TAGK_QUALIFIER);
			// 将该映射关系写入表中
			// 正向关系 id
			HBaseClient.getInstance().put(TSDB_TSUID_TABLENAME, tagk.toLowerCase(), TSDB_TSUID_COLUMNFAMILY_ID,
					TSDB_TSUID_TAGK_QUALIFIER, uid);
			// 反向关系 name
			HBaseClient.getInstance().put(TSDB_TSUID_TABLENAME, uid, TSDB_TSUID_COLUMNFAMILY_NAME,
					TSDB_TSUID_TAGK_QUALIFIER, tagk.toLowerCase());
		}
		return uid;
	}

	// 所有的tag在数据库中转换为小写
	public static byte[] getMetricUID(Metric mt) throws IOException {
		if (mt == null)
			return null;
		// 其中的标签名称，全部为小写字母，即不区分大小写
		byte[] uid = HBaseClient.getInstance().getByteContentByKey(TSDB_TSUID_TABLENAME, TSDB_TSUID_COLUMNFAMILY_ID,
				mt.toString().toLowerCase(), TSDB_TSUID_METRIC_QUALIFIER);
		// 如果不存在该string
		if (uid == null) {
			uid = getNextCounterValue(TSDB_TSUID_METRIC_QUALIFIER);
			// 将该映射关系写入表中
			// 正向关系 id
			HBaseClient.getInstance().put(TSDB_TSUID_TABLENAME, mt.toString().toLowerCase(), TSDB_TSUID_COLUMNFAMILY_ID,
					TSDB_TSUID_METRIC_QUALIFIER, uid);
			// 反向关系 name
			HBaseClient.getInstance().put(TSDB_TSUID_TABLENAME, uid, TSDB_TSUID_COLUMNFAMILY_NAME,
					TSDB_TSUID_METRIC_QUALIFIER, mt.toString().toLowerCase());
		}
		return uid;
	}

	/**
	 * UID从0开始，占3个字节
	 * 
	 * @param tableName
	 * @param columnFamily
	 * @param qualifier
	 * @return
	 * @throws IOException
	 */
	private static byte[] getNextCounterValue(String conterName) throws IOException {
		// 使用Hbase的计数器
		long nextValue = HBaseClient.incrementColumnValue(TSDB_COUNTER_TABLENAME, Bytes.toBytes(conterName),
				Bytes.toBytes(defaultColumnFamily), Bytes.toBytes(TSDB_COUNTER_QUALIFIER), 1);
		// 将nextValue转换成3个字节的byte数组
		byte[] counterBytes = ByteMathUtils.encodeToByte(nextValue, TSDB_UID_BYTE_SIZE);
		return counterBytes;
	}

	/**
	 * 生成列的qualifier, 每一列占2个bytes, 表示以秒为单位的偏移，格式为： 12bit:
	 * 相对row表示的小时的delta，最多2^12=4096>3600， 4bit:format flags,其中： 1bit：an integer
	 * or floating point, 3 bit:标明数据的长度， 其长度必须是1、2、4、8.
	 * 其中000表示1byte,010表示2byte,011表示4byte,100表示8byte
	 * 这样就能表示所有的基本的数据类型了。例如01表示占1个字节的整形，也就是byte,02表示sort, 14表示float,18表示double.
	 * 
	 * @param point
	 * @return
	 */
	public static byte[] generateQualifier(DataPoint point) {
		if (point == null)
			return null;
		long time = point.getTime();
		// 获取相对小时的秒数
		long delta = time % 3600000 / 1000;
		String binaryString = "000000000000" + Long.toBinaryString(delta);
		binaryString = binaryString.substring(binaryString.length() - 12);
		Number value = point.getValue();
		if (value instanceof Byte) {
			binaryString = binaryString + NumberEncode.BYTE.getEncode();
		}
		if (value instanceof Short) {
			binaryString = binaryString + NumberEncode.SHORT.getEncode();
		}
		if (value instanceof Integer) {
			binaryString = binaryString + NumberEncode.INTEGER.getEncode();
		}
		if (value instanceof Long) {
			binaryString = binaryString + NumberEncode.LONG.getEncode();
		}
		if (value instanceof Float) {
			binaryString = binaryString + NumberEncode.FLOAT.getEncode();
		}
		if (value instanceof Double) {
			binaryString = binaryString + NumberEncode.DOUBLE.getEncode();
		}
		return ByteMathUtils.encodeToByte(Long.parseLong(binaryString, 2), 2);
	}

	public static List<TimeSeriesData> createTimeSeries(Result r) {
		List<TimeSeriesData> ts = new ArrayList<TimeSeriesData>();
		if (r == null)
			return ts;
		byte[] row = r.getRow();
		// 获取第4到8个字节最为到小时的秒数
		String hourS = ByteMathUtils.encodeToBinaryString(Arrays.copyOfRange(row, 3, 7));
		long htime = Long.parseLong(hourS, 2) * 1000;
		NavigableMap<byte[], byte[]> qualifierValueMaps = r.getFamilyMap(Bytes.toBytes(defaultColumnFamily));
		Iterator<Entry<byte[], byte[]>> iter = qualifierValueMaps.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<byte[], byte[]> entry = iter.next();
			byte[] qualifier = entry.getKey();
			byte[] value = entry.getValue();
			// qualifier有2个字节，前面12个bit是秒数，后面的4个字节为value的类型
			String qualifierS = ByteMathUtils.encodeToBinaryString(qualifier);
			String sencondS = qualifierS.substring(0, 12);
			String formatFlagS = qualifierS.substring(12);
			long time = htime + Long.parseLong(sencondS, 2) * 1000;
			Number data = null;
			if (NumberEncode.getByEncode(formatFlagS) == NumberEncode.BYTE) {
				data = value[0];
			}
			if (NumberEncode.getByEncode(formatFlagS) == NumberEncode.SHORT) {
				data = Bytes.toShort(value);
			}
			if (NumberEncode.getByEncode(formatFlagS) == NumberEncode.INTEGER) {
				data = Bytes.toInt(value);
			}
			if (NumberEncode.getByEncode(formatFlagS) == NumberEncode.LONG) {
				data = Bytes.toLong(value);
			}
			if (NumberEncode.getByEncode(formatFlagS) == NumberEncode.FLOAT) {
				data = Bytes.toFloat(value);
			}
			if (NumberEncode.getByEncode(formatFlagS) == NumberEncode.DOUBLE) {
				data = Bytes.toDouble(value);
			}
			TimeSeriesData timeSeriesData = new TimeSeriesData();
			timeSeriesData.setTime(time);
			timeSeriesData.setData(data);
			ts.add(timeSeriesData);
		}
		return ts;
	}

	/**
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static List<TimeSeriesData> getTimeSeries(byte[] key) throws IOException {
		List<TimeSeriesData> ts = new ArrayList<TimeSeriesData>();
		if (key == null) {
			return ts;
		}
		HTable hTable = HBaseClient.getInstance().openTable(TSDB_DATA_TABLENAME);
		Scan scan = new Scan(key, key);
		ResultScanner rs = hTable.getScanner(scan);
		for (Result r : rs) {
			ts.addAll(createTimeSeries(r));
		}
		rs.close();
		hTable.close();
		return ts;
	}

	public static String getDefaultColumnFamily() {
		return defaultColumnFamily;
	}

	/**
	 * 匹配两个row-key,不包含时间戳
	 * @param row
	 * @param newKey
	 * @return
	 */
	public static boolean matchWithNoTime(byte[] row, byte[] newKey) {
		if(row==null||newKey==null||row.length!=newKey.length) return false;
		//将newKey的时间戳设置成与row的相同，即第4-8个字节
		byte[] copy = Arrays.copyOf(newKey, newKey.length);
		for(int i = 3;i<7;i++){
			copy[i] = row[i];
		}
		return Arrays.equals(row, copy);
	}
}
