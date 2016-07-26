package inspur.crawl.diagnoseCrawl.mapper;

import java.util.List;

import inspur.crawl.diagnoseCrawl.pojo.TaskAndTaskInstance;

public interface DiagnoseCrawlMapper {
	List<TaskAndTaskInstance> getInstanceID(String task_id);
	List<TaskAndTaskInstance> getRule(String task_id);
	TaskAndTaskInstance getDetail(String rule_id);
}
