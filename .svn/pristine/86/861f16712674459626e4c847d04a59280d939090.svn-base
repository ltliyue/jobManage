package inspur.crawl.ruleManage.pojo;

import java.math.BigDecimal;
import java.util.Date;

import inspur.crawl.common.interceptor.Page;

public class ParseRuleMidMapTemp {
    private String id;

    private String ruleId;

    private Date time;

    private BigDecimal groupId;

    private String varName;

    private String datatype;
    
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

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getGroupId() {
        return groupId;
    }

    public void setGroupId(BigDecimal groupId) {
        this.groupId = groupId;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName == null ? null : varName.trim();
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype == null ? null : datatype.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends ParseRuleMidMapTemp> T copy(T bean) {
        bean.setId(getId());
        bean.setRuleId(getRuleId());
        bean.setTime(getTime());
        bean.setGroupId(getGroupId());
        bean.setVarName(getVarName());
        bean.setDatatype(getDatatype());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", id:" + getId() + 
        	", ruleId:" + getRuleId() + 
        	", time:" + getTime() + 
        	", groupId:" + getGroupId() + 
        	", varName:" + getVarName() + 
        	", datatype:" + getDatatype() + 
        "}";
    }
}