package inspur.crawl.ruleManage.pojo;

import java.math.BigDecimal;
import java.util.Date;

import inspur.crawl.common.interceptor.Page;

public class ParseRule {
    private String id;

    private String name;

    private String taskId;

    private String urlPattern;

    private String extractType;

    private String regex;

    private String regexGroupId;

    private String xpath;

    private Date time;

    private Short enabled;

    private Short urlWithLoop;

    private String loopStart;

    private String loopEnd;

    private String loopVar;

    private BigDecimal loopStep;

    private String loopFormat;

    private BigDecimal urlType;

    private String creator;

    private String loopSample;

    private String testUrl;
    
    private Page page;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern == null ? null : urlPattern.trim();
    }

    public String getExtractType() {
        return extractType;
    }

    public void setExtractType(String extractType) {
        this.extractType = extractType == null ? null : extractType.trim();
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex == null ? null : regex.trim();
    }

    public String getRegexGroupId() {
        return regexGroupId;
    }

    public void setRegexGroupId(String regexGroupId) {
        this.regexGroupId = regexGroupId == null ? null : regexGroupId.trim();
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath == null ? null : xpath.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Short getEnabled() {
        return enabled;
    }

    public void setEnabled(Short enabled) {
        this.enabled = enabled;
    }

    public Short getUrlWithLoop() {
        return urlWithLoop;
    }

    public void setUrlWithLoop(Short urlWithLoop) {
        this.urlWithLoop = urlWithLoop;
    }

    public String getLoopStart() {
        return loopStart;
    }

    public void setLoopStart(String loopStart) {
        this.loopStart = loopStart == null ? null : loopStart.trim();
    }

    public String getLoopEnd() {
        return loopEnd;
    }

    public void setLoopEnd(String loopEnd) {
        this.loopEnd = loopEnd == null ? null : loopEnd.trim();
    }

    public String getLoopVar() {
        return loopVar;
    }

    public void setLoopVar(String loopVar) {
        this.loopVar = loopVar == null ? null : loopVar.trim();
    }

    public BigDecimal getLoopStep() {
        return loopStep;
    }

    public void setLoopStep(BigDecimal loopStep) {
        this.loopStep = loopStep;
    }

    public String getLoopFormat() {
        return loopFormat;
    }

    public void setLoopFormat(String loopFormat) {
        this.loopFormat = loopFormat == null ? null : loopFormat.trim();
    }

    public BigDecimal getUrlType() {
        return urlType;
    }

    public void setUrlType(BigDecimal urlType) {
        this.urlType = urlType;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getLoopSample() {
        return loopSample;
    }

    public void setLoopSample(String loopSample) {
        this.loopSample = loopSample == null ? null : loopSample.trim();
    }

    public String getTestUrl() {
        return testUrl;
    }

    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl == null ? null : testUrl.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends ParseRule> T copy(T bean) {
        bean.setId(getId());
        bean.setName(getName());
        bean.setTaskId(getTaskId());
        bean.setUrlPattern(getUrlPattern());
        bean.setExtractType(getExtractType());
        bean.setRegex(getRegex());
        bean.setRegexGroupId(getRegexGroupId());
        bean.setXpath(getXpath());
        bean.setTime(getTime());
        bean.setEnabled(getEnabled());
        bean.setUrlWithLoop(getUrlWithLoop());
        bean.setLoopStart(getLoopStart());
        bean.setLoopEnd(getLoopEnd());
        bean.setLoopVar(getLoopVar());
        bean.setLoopStep(getLoopStep());
        bean.setLoopFormat(getLoopFormat());
        bean.setUrlType(getUrlType());
        bean.setCreator(getCreator());
        bean.setLoopSample(getLoopSample());
        bean.setTestUrl(getTestUrl());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", id:" + getId() + 
        	", name:" + getName() + 
        	", taskId:" + getTaskId() + 
        	", urlPattern:" + getUrlPattern() + 
        	", extractType:" + getExtractType() + 
        	", regex:" + getRegex() + 
        	", regexGroupId:" + getRegexGroupId() + 
        	", xpath:" + getXpath() + 
        	", time:" + getTime() + 
        	", enabled:" + getEnabled() + 
        	", urlWithLoop:" + getUrlWithLoop() + 
        	", loopStart:" + getLoopStart() + 
        	", loopEnd:" + getLoopEnd() + 
        	", loopVar:" + getLoopVar() + 
        	", loopStep:" + getLoopStep() + 
        	", loopFormat:" + getLoopFormat() + 
        	", urlType:" + getUrlType() + 
        	", creator:" + getCreator() + 
        	", loopSample:" + getLoopSample() + 
        	", testUrl:" + getTestUrl() + 
        "}";
    }
}