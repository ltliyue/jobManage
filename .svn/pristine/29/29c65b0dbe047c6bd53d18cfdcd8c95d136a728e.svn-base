package com.inspur.kafka.helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.common.serialization.Serializer;

import inspur.crawl.taskManage.controller.TaskConstant;

/**
 * kafka序列化接口avro对象的实现类
 * @author zhang_yuchao
 */
public class AvroSerializer implements Serializer<Object> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * avro序列化方法
	 */
	@Override
	public byte[] serialize(String topic, Object data) {
		if(topic.equals(TaskConstant.TOPIC_TASK_CONFIG)) {
			return ((String) data).getBytes();
		}
		byte[] avroByte = "AVRO".getBytes();
		try {
			//通过反射获取avro对象的shema
			Class clazz = data.getClass();
			Method m = clazz.getDeclaredMethod("getClassSchema");
			org.apache.avro.Schema schema = (Schema) m.invoke(data);
			int schemaHash = schema.toString(false).hashCode();
			byte[] schemaByte = ByteUtils.intToBytes(schemaHash);
			
			SpecificDatumWriter<Object> writer = new SpecificDatumWriter<>(schema);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			Encoder encoder = EncoderFactory.get().binaryEncoder(output, null);
			writer.write(data, encoder);
			encoder.flush();
			byte[] dataByte = output.toByteArray();
			
			byte bufferType = 00;	//00代表二进制，01代表json
			
			byte[] resultByte = new byte[avroByte.length + schemaByte.length + 1 + dataByte.length];
			//前4位代表字符串avro
			System.arraycopy(avroByte, 0, resultByte, 0, 4);
			//5-8位存储Avro对象schema的hash code
			System.arraycopy(schemaByte, 0, resultByte, 4, 4);
			//第9位 00表示对象数据采用二进制编码，01表示JSON编码。
			resultByte[8] = bufferType;
			//第10字节起至结束存储Avro序列化编码数据
			System.arraycopy(dataByte, 0, resultByte, 9, dataByte.length);
			
			return resultByte;
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	//[65, 86, 82, 79, -64, 96, 64, -80, 1, 0, -114, -38, -106, 1, 2, 0, 40, 104, 116, 116, 112, 58, 47, 47, 119, 119, 119, 46, 98, 97, 105, 100, 117, 46, 99, 111, 109, 2, 2, 2, 2, 2, 2, 2]
//	public static void main(String[] args) {
//		byte[] avroByte = "AVRO".getBytes();
//		try {
//			UrlFetchRequest a = new UrlFetchRequest();
//			a.setTaskId(Long.valueOf("1234567"));
//			a.setUrl("http://www.baidu.com");
//			Class clazz = a.getClass();
//			Method m = clazz.getDeclaredMethod("getClassSchema");
//			org.apache.avro.Schema schema = (Schema) m.invoke(a);
//			int schemaHash = schema.toString(false).hashCode();
//			byte[] schemaByte = ByteUtils.intToBytes(schemaHash);
//			GenericDatumWriter<Object> writer = new GenericDatumWriter<>(schema);
//			ByteArrayOutputStream output = new ByteArrayOutputStream();
//			Encoder encoder = EncoderFactory.get().binaryEncoder(output, null);
//			writer.write(a, encoder);
//			encoder.flush();
//			byte[] dataByte = output.toByteArray();
//			byte bufferType = 01;	//00代表二进制，01代表json
//			byte[] resultByte = new byte[avroByte.length + schemaByte.length + 1 + dataByte.length];
//			System.out.println(Arrays.toString(avroByte));
//			System.out.println(Arrays.toString(schemaByte));
//			System.arraycopy(avroByte, 0, resultByte, 0, 4);
//			System.arraycopy(schemaByte, 0, resultByte, 4, 4);
//			resultByte[8] = bufferType;
//			System.out.println(Arrays.toString(resultByte));
//			System.arraycopy(dataByte, 0, resultByte, 9, dataByte.length);
//			System.out.println(Arrays.toString(dataByte));
//			System.out.println(Arrays.toString(resultByte));
//		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
