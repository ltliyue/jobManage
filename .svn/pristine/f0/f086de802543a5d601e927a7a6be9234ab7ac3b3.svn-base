package inspur.crawl.ruleManage.pojo;

public class ElementExtractRule {
    private String id;

    private String pageExtractRuleId;

    private String extractType;

    private String regex;

    private String regexGroupId;

    private String xpath;

    private Short containHtml;

    private String storeColumnName;

    private String storeColumnComment;

    private Short isKeyInTable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPageExtractRuleId() {
        return pageExtractRuleId;
    }

    public void setPageExtractRuleId(String pageExtractRuleId) {
        this.pageExtractRuleId = pageExtractRuleId == null ? null : pageExtractRuleId.trim();
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

    public Short getContainHtml() {
        return containHtml;
    }

    public void setContainHtml(Short containHtml) {
        this.containHtml = containHtml;
    }

    public String getStoreColumnName() {
        return storeColumnName;
    }

    public void setStoreColumnName(String storeColumnName) {
        this.storeColumnName = storeColumnName == null ? null : storeColumnName.trim();
    }

    public String getStoreColumnComment() {
        return storeColumnComment;
    }

    public void setStoreColumnComment(String storeColumnComment) {
        this.storeColumnComment = storeColumnComment == null ? null : storeColumnComment.trim();
    }

    public Short getIsKeyInTable() {
        return isKeyInTable;
    }

    public void setIsKeyInTable(Short isKeyInTable) {
        this.isKeyInTable = isKeyInTable;
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends ElementExtractRule> T copy(T bean) {
        bean.setId(getId());
        bean.setPageExtractRuleId(getPageExtractRuleId());
        bean.setExtractType(getExtractType());
        bean.setRegex(getRegex());
        bean.setRegexGroupId(getRegexGroupId());
        bean.setXpath(getXpath());
        bean.setContainHtml(getContainHtml());
        bean.setStoreColumnName(getStoreColumnName());
        bean.setStoreColumnComment(getStoreColumnComment());
        bean.setIsKeyInTable(getIsKeyInTable());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", id:" + getId() + 
        	", pageExtractRuleId:" + getPageExtractRuleId() + 
        	", extractType:" + getExtractType() + 
        	", regex:" + getRegex() + 
        	", regexGroupId:" + getRegexGroupId() + 
        	", xpath:" + getXpath() + 
        	", containHtml:" + getContainHtml() + 
        	", storeColumnName:" + getStoreColumnName() + 
        	", storeColumnComment:" + getStoreColumnComment() + 
        	", isKeyInTable:" + getIsKeyInTable() + 
        "}";
    }
}