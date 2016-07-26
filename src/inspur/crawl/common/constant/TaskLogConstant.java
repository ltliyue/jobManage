package inspur.crawl.common.constant;

public class TaskLogConstant {

	/**
	 *操作类型
	 */
	public static class OperateType {
		//创建
		public static final Integer CREATE = 0;
		//采集
		public static final Integer COLLECT = 1;
		//验证
		public static final Integer VERIFY = 2;
		//重置
		public static final Integer RESET = 3;
		//更新
		public static final Integer UPDATE = 4;
		//终止任务
		public static final Integer STOP = 5;
	}
	
	/**
	 *操作状态
	 */
	public static class OperateStatus {
		//采集中
		public static final Integer COLLECTING = 0;
		//采集完成
		public static final Integer COLLECTTED = 1;
		//无法采集
		public static final Integer CANNOT_COLLECT = 2;
		//验证通过
		public static final Integer VERIFY_PASS = 3;
		//验证不通过
		public static final Integer VERIFY_REJECT = 4;
	}
}
