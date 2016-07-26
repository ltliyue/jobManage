package com.inspur.publicTools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MethodUtil {
	
	/**
	 * 读取Properties文件
	 * 
	 * @param ob 读取资源的类
	 * @param resources 文件名称
	 * @return
	 */
	public static Properties loadProperties(Object ob ,String resources) {
		// 使用InputStream得到一个资源文件
		InputStream inputstream = ob.getClass().getClassLoader()
				.getResourceAsStream(resources);
		// new 一个Properties
		Properties properties = new Properties();
		try {
			// 加载配置文件
			properties.load(inputstream);
			return properties;
		} catch (IOException e) {
			throw new RuntimeException(e);
			
		} finally {
			System.out.println("--------------------------------------------------");
			try {
				inputstream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
