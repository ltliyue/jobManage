package inspur.crawl.monitor.pojo;

import java.util.Date;

public class MonitorSourceDataKey {
    private String dataType;

    private Date dataTime;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }
}