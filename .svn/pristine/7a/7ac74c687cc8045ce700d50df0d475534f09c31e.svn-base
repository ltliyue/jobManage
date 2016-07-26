package inspur.crawl.dataManage.pojo;

import java.util.Date;

public class DataSource {
    private String id;

    private String soruceName;

    private String dataAddress;

    private String userName;

    private String passName;

    private String dataType;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSoruceName() {
        return soruceName;
    }

    public void setSoruceName(String soruceName) {
        this.soruceName = soruceName == null ? null : soruceName.trim();
    }

    public String getDataAddress() {
        return dataAddress;
    }

    public void setDataAddress(String dataAddress) {
        this.dataAddress = dataAddress == null ? null : dataAddress.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassName() {
        return passName;
    }

    public void setPassName(String passName) {
        this.passName = passName == null ? null : passName.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends DataSource> T copy(T bean) {
        bean.setId(getId());
        bean.setSoruceName(getSoruceName());
        bean.setDataAddress(getDataAddress());
        bean.setUserName(getUserName());
        bean.setPassName(getPassName());
        bean.setDataType(getDataType());
        bean.setCreateTime(getCreateTime());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", id:" + getId() + 
        	", soruceName:" + getSoruceName() + 
        	", dataAddress:" + getDataAddress() + 
        	", userName:" + getUserName() + 
        	", passName:" + getPassName() + 
        	", dataType:" + getDataType() + 
        	", createTime:" + getCreateTime() + 
        "}";
    }
}