package com.inspur.kafka.helpers;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * byte工具
 */
public class ByteUtils {

	/**
	 * 移位方法将int转为byte数组
	 * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToInt（）配套使用
	 * @param value 要转换的int值
	 * @return byte数组
	 */
	public static byte[] intToBytes(int value) {
		byte[] src = new byte[4];
		src[3] = (byte) ((value >> 24) & 0xFF);
		src[2] = (byte) ((value >> 16) & 0xFF);
		src[1] = (byte) ((value >> 8) & 0xFF);
		src[0] = (byte) (value & 0xFF);
		return src;
	}

	/**
	 * 移位方法将byte数组转回int
	 * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
	 * @param src  byte数组
	 * @param offset 从数组的第offset位开始
	 * @return int数值
	 */
	public static int bytesToInt(byte[] src, int offset) {
		int value;
		value = (int) ((src[offset] & 0xFF) | ((src[offset + 1] & 0xFF) << 8) | ((src[offset + 2] & 0xFF) << 16)
				| ((src[offset + 3] & 0xFF) << 24));
		return value;
	}
	
	/**
	 * 使用ButeBuffer完成int到byte数组的转换
	 * @param value
	 * @return
	 */
	public static byte[] intToBytesByByteBuffer(int value) {
		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.putInt(value);
		return bb.array();
	}
	
	/**
	 *使用ButeBuffer完成byte数组到int的转换
	 * @param value
	 * @return
	 */
	public static int byteBufferReadIntBytes(byte[] value) {
		ByteBuffer bb = ByteBuffer.wrap(value);
		return bb.getInt();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(Arrays.toString(intToBytesByByteBuffer(-64)));
	}
}
