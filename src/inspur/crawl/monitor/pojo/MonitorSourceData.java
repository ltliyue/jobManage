package inspur.crawl.monitor.pojo;

import java.math.BigDecimal;

public class MonitorSourceData extends MonitorSourceDataKey {
    private BigDecimal dataAll;

    private BigDecimal dataNew;

    private BigDecimal dataIncr;

    private BigDecimal dataIncrPer;

    public BigDecimal getDataAll() {
        return dataAll;
    }

    public void setDataAll(BigDecimal dataAll) {
        this.dataAll = dataAll;
    }

    public BigDecimal getDataNew() {
        return dataNew;
    }

    public void setDataNew(BigDecimal dataNew) {
        this.dataNew = dataNew;
    }

    public BigDecimal getDataIncr() {
        return dataIncr;
    }

    public void setDataIncr(BigDecimal dataIncr) {
        this.dataIncr = dataIncr;
    }

    public BigDecimal getDataIncrPer() {
        return dataIncrPer;
    }

    public void setDataIncrPer(BigDecimal dataIncrPer) {
        this.dataIncrPer = dataIncrPer;
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends MonitorSourceData> T copy(T bean) {
        bean.setDataType(getDataType());
        bean.setDataTime(getDataTime());
        bean.setDataAll(getDataAll());
        bean.setDataNew(getDataNew());
        bean.setDataIncr(getDataIncr());
        bean.setDataIncrPer(getDataIncrPer());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", dataType:" + getDataType() + 
        	", dataTime:" + getDataTime() + 
        	", dataAll:" + getDataAll() + 
        	", dataNew:" + getDataNew() + 
        	", dataIncr:" + getDataIncr() + 
        	", dataIncrPer:" + getDataIncrPer() + 
        "}";
    }
}