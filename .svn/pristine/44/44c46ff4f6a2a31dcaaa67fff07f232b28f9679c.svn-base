package inspur.crawl.ruleManage.pojo;

import inspur.crawl.common.interceptor.Page;

public class LoopParseRule {
    private String id;

    private String parseRuleId;

    private String loopSample;

    private String loopVars;
    
	private Page page;
	
	public LoopParseRule(LoopParseRule loopParseRule){
		this.id = loopParseRule.id;
		this.parseRuleId = loopParseRule.parseRuleId;
		this.loopSample = loopParseRule.loopSample;
		this.loopVars = loopParseRule.loopVars;
	}
	
	public LoopParseRule(){}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParseRuleId() {
        return parseRuleId;
    }

    public void setParseRuleId(String parseRuleId) {
        this.parseRuleId = parseRuleId == null ? null : parseRuleId.trim();
    }

    public String getLoopSample() {
        return loopSample;
    }

    public void setLoopSample(String loopSample) {
        this.loopSample = loopSample == null ? null : loopSample.trim();
    }

    public String getLoopVars() {
        return loopVars;
    }

    public void setLoopVars(String loopVars) {
        this.loopVars = loopVars == null ? null : loopVars.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends LoopParseRule> T copy(T bean) {
        bean.setId(getId());
        bean.setParseRuleId(getParseRuleId());
        bean.setLoopSample(getLoopSample());
        bean.setLoopVars(getLoopVars());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", id:" + getId() + 
        	", parseRuleId:" + getParseRuleId() + 
        	", loopSample:" + getLoopSample() + 
        	", loopVars:" + getLoopVars() + 
        "}";
    }
}