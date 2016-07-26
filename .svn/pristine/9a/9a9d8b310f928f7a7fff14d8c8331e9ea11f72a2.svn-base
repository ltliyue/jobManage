package inspur.crawl.ruleManage.pojo;

import java.util.Date;

import inspur.crawl.common.interceptor.Page;

public class PageExtractRule {
    private String id;

    private String name;

    private String taskId;

    private String urlPattern;

    private Date time;

    private Short enabled;

    private Short containsMulti;

    private String multiRowXpath;

    private String creator;
    
    private String testUrl;
    
    private Short extractType;

    private String jarPath;

    private String className;
    
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

    public Short getContainsMulti() {
        return containsMulti;
    }

    public void setContainsMulti(Short containsMulti) {
        this.containsMulti = containsMulti;
    }

    public String getMultiRowXpath() {
        return multiRowXpath;
    }

    public void setMultiRowXpath(String multiRowXpath) {
        this.multiRowXpath = multiRowXpath == null ? null : multiRowXpath.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
    
    

    public String getTestUrl() {
		return testUrl;
	}

	public void setTestUrl(String testUrl) {
		this.testUrl = testUrl;
	}

    public Short getExtractType() {
        return extractType;
    }

    public void setExtractType(Short extractType) {
        this.extractType = extractType;
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath == null ? null : jarPath.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends PageExtractRule> T copy(T bean) {
        bean.setId(getId());
        bean.setName(getName());
        bean.setTaskId(getTaskId());
        bean.setUrlPattern(getUrlPattern());
        bean.setTime(getTime());
        bean.setEnabled(getEnabled());
        bean.setContainsMulti(getContainsMulti());
        bean.setMultiRowXpath(getMultiRowXpath());
        bean.setCreator(getCreator());
        bean.setTestUrl(getTestUrl());
        bean.setExtractType(getExtractType());
        bean.setJarPath(getJarPath());
        bean.setClassName(getClassName());
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
        	", time:" + getTime() + 
        	", enabled:" + getEnabled() + 
        	", containsMulti:" + getContainsMulti() + 
        	", multiRowXpath:" + getMultiRowXpath() + 
        	", creator:" + getCreator() + 
        	", testUrl:" + getTestUrl() + 
        	", extractType:" + getExtractType() + 
        	", jarPath:" + getJarPath() + 
        	", className:" + getClassName() + 
        "}";
    }
}