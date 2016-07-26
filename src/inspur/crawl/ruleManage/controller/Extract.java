package inspur.crawl.ruleManage.controller;

import java.util.List;
import java.util.Map;

/**
 * 用户自定义的抽取方法的接口
 * @author maolh
 *
 */
public interface Extract {
	/**
	 * 
	 * @param content  网页的内容
	 * @return 从网页中解析出来的字段名称、字段内容，因为一个网页中也可能有多条数据，所以返回list
	 * 需要注意的是，Map的key，是数据库中的列的名字，为英文代码，需要仔细检查
	 */
	public List<Map<String, String>> extract(String content);
}
