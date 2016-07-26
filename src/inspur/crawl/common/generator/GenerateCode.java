package inspur.crawl.common.generator;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 数据库操作代码生
 */
public class GenerateCode {

	public static void main(String[] args) throws Exception {
		new GenerateCode().generateMain("generatorConfig.xml");
	}

	/**
	 * 生成主数据代
	 * @throws Exception
	 */
	public void generateMain(String fileName) throws Exception {
		String f = GenerateCode.class.getResource(fileName).getPath();
		generate(f);
	}
	
	/**
	 * @param f
	 * @throws Exception
	 */
	public static void generate(String f) throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File(f);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);

		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for(String s : warnings) {
			System.out.println(s);
		}
	}

}
