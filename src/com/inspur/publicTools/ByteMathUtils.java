package com.inspur.publicTools;

/**
 * 进行一些byte类型的操作
 * @author maolh
 *
 */
public class ByteMathUtils {
	
	/**
	 * 
	 * @param value
	 * @param n 位数
	 * @return
	 */
	public static byte[] encodeToByte(long value,int n){
		StringBuffer zeros = new StringBuffer();
		for(int i = 0;i<(n<<3);i++){
			zeros.append("0");
		}
		zeros.append(Long.toBinaryString(value));
		String binary = zeros.substring(zeros.length()-(n<<3));
		byte[] bytes=  new byte[n];
		for(int i =0;i<n;i++){
			bytes[i]=(byte)Integer.valueOf(binary.substring(i<<3,(i+1)<<3),2).intValue();
		}
		return bytes;
	}
	
	public static long encodeToLong(byte[] bytes) throws ArrayIndexOutOfBoundsException{
		if(bytes.length>4) throw new ArrayIndexOutOfBoundsException("输入byte数组的长度不能超过4！");
		long value = 0;
		for(int i=0;i<bytes.length;i++){
			value = (value<<3)+(bytes[i]& 0xff);
		}
		return value;
	}

	public static String  encodeToBinaryString(byte[] qualifier) {
		StringBuffer buffer = new StringBuffer();
		for(byte q:qualifier){
			int z = q;
			z |= 256;
			String str = Integer.toBinaryString(z);
			int len = str.length();
			buffer.append(str.substring(len - 8, len));
		}
		return buffer.toString();
	}
}
