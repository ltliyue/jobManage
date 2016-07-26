package inspur.crawl.ruleManage.pojo;

import java.math.BigDecimal;

public class LoopVarRule {
    private String id;

    private String loopParseRuleId;

    private String loopStart;

    private BigDecimal loopVarType;

    private String loopEnd;

    private BigDecimal loopStep;

    private String loopFormat;

    private String loopStrEncode;

    private String loopVarName;

    private String loopStrList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLoopParseRuleId() {
        return loopParseRuleId;
    }

    public void setLoopParseRuleId(String loopParseRuleId) {
        this.loopParseRuleId = loopParseRuleId == null ? null : loopParseRuleId.trim();
    }

    public String getLoopStart() {
        return loopStart;
    }

    public void setLoopStart(String loopStart) {
        this.loopStart = loopStart == null ? null : loopStart.trim();
    }

    public BigDecimal getLoopVarType() {
        return loopVarType;
    }

    public void setLoopVarType(BigDecimal loopVarType) {
        this.loopVarType = loopVarType;
    }

    public String getLoopEnd() {
        return loopEnd;
    }

    public void setLoopEnd(String loopEnd) {
        this.loopEnd = loopEnd == null ? null : loopEnd.trim();
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

    public String getLoopStrEncode() {
        return loopStrEncode;
    }

    public void setLoopStrEncode(String loopStrEncode) {
        this.loopStrEncode = loopStrEncode == null ? null : loopStrEncode.trim();
    }

    public String getLoopVarName() {
        return loopVarName;
    }

    public void setLoopVarName(String loopVarName) {
        this.loopVarName = loopVarName == null ? null : loopVarName.trim();
    }

    public String getLoopStrList() {
        return loopStrList;
    }

    public void setLoopStrList(String loopStrList) {
        this.loopStrList = loopStrList == null ? null : loopStrList.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends LoopVarRule> T copy(T bean) {
        bean.setId(getId());
        bean.setLoopParseRuleId(getLoopParseRuleId());
        bean.setLoopStart(getLoopStart());
        bean.setLoopVarType(getLoopVarType());
        bean.setLoopEnd(getLoopEnd());
        bean.setLoopStep(getLoopStep());
        bean.setLoopFormat(getLoopFormat());
        bean.setLoopStrEncode(getLoopStrEncode());
        bean.setLoopVarName(getLoopVarName());
        bean.setLoopStrList(getLoopStrList());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", id:" + getId() + 
        	", loopParseRuleId:" + getLoopParseRuleId() + 
        	", loopStart:" + getLoopStart() + 
        	", loopVarType:" + getLoopVarType() + 
        	", loopEnd:" + getLoopEnd() + 
        	", loopStep:" + getLoopStep() + 
        	", loopFormat:" + getLoopFormat() + 
        	", loopStrEncode:" + getLoopStrEncode() + 
        	", loopVarName:" + getLoopVarName() + 
        	", loopStrList:" + getLoopStrList() + 
        "}";
    }
}