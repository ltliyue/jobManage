package inspur.crawl.common.constant;

public class DataTypeConstant {

	//邮件提醒类型
	public static class NotifyType {
		//登记人
		public static final String REGISTER = "REGISTER";
		//负责人
		public static final String OWNER = "OWNER";
		//验证人
		public static final String VALIDATE = "VALIDATE";
		//报告人
		public static final String REPORT = "REPORT";
	}

	//数据类型
	public static class DataTypeType {
		//数据大类
		public static final Integer GENERAL = 1;
		//网站
		public static final Integer WEBSITE = 2;
		//采集需求
		public static final Integer TASK = 3;
	}
	
	//类型状态
	public static class State {
		//启用
		public static final String ON = "1";
		//停用
		public static final String OFF = "0";
		
	}
	
	//任务状态
	public static class Status {
		//未采集
		public static final Integer UNCOLLECT = 0;
		//已采集
		public static final Integer COLLECTED = 1;
		//采集中
		public static final Integer COLLECTTING = 2;
		//任务终止
		public static final Integer STOPED = 3;
		//无法采集
		public static final Integer CANNOT_COLLECT = 4;
	}
	
	//验证状态
	public static final class ValidateStatus {
		//未采集
		public static final Integer UNVALID = 0;
		//验证通过
		public static final Integer PASS = 1;
		//验证未通过
		public static final Integer REJECT = 2;
	}
	
	//操作成功
	public static final String SUCCESS = "SUCCESS";
	
	//缓存key
	public static class CacheKey {
		public static final String ALLTYPE = "ALLTYPE";
		
		public static final String ALLTASK = "ALLTASK";
		
		public static final String TASKCOUNT = "TASKCOUNT";
		
	}
	
}
