package inspur.crawl.demandAna.pojo;

public class CrawlerDemandDelivery extends CrawlerDemandDeliveryKey {
    private String fieldName;

    private String conditionDes;

    private String conditionVal;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getConditionDes() {
        return conditionDes;
    }

    public void setConditionDes(String conditionDes) {
        this.conditionDes = conditionDes == null ? null : conditionDes.trim();
    }

    public String getConditionVal() {
        return conditionVal;
    }

    public void setConditionVal(String conditionVal) {
        this.conditionVal = conditionVal == null ? null : conditionVal.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends CrawlerDemandDelivery> T copy(T bean) {
        bean.setDemandId(getDemandId());
        bean.setSiteId(getSiteId());
        bean.setFieldId(getFieldId());
        bean.setFieldName(getFieldName());
        bean.setConditionDes(getConditionDes());
        bean.setConditionVal(getConditionVal());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", demandId:" + getDemandId() + 
        	", siteId:" + getSiteId() + 
        	", fieldId:" + getFieldId() + 
        	", fieldName:" + getFieldName() + 
        	", conditionDes:" + getConditionDes() + 
        	", conditionVal:" + getConditionVal() + 
        "}";
    }
}