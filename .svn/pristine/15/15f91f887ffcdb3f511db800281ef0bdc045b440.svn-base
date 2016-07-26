package com.inspur.bigdata.hbaseio.pojo;

/**
 * 对Number类型进行编码 其中000表示1byte,010表示2byte,011表示4byte,100表示8byte
 * 这样就能表示所有的基本的数据类型了。例如01表示占1个字节的整形，也就是byte,02表示sort, 14表示float,18表示double.
 * 
 * @author maolh
 *
 */
public enum NumberEncode {
	BYTE("0001"), SHORT("0010"), INTEGER("0011"), LONG("0100"), FLOAT("1011"), DOUBLE("1100");
	private String encode;

	private NumberEncode(String encode) {
		this.encode = encode;
	}
	
	public String getEncode(){
		return this.encode;
	}

	public static NumberEncode getByEncode(String encode) {
		if (encode == null)
			return null;
		for (NumberEncode ne : NumberEncode.values()) {
			if (ne.encode.equals(encode)) {
				return ne;
			}
		}
		return null;
	}
}
