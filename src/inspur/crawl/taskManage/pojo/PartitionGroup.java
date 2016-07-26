package inspur.crawl.taskManage.pojo;

import java.util.Date;

public class PartitionGroup {
    private String groupId;

    private String groupName;

    private Date createTime;

    private String creator;

    private Date modifyTime;

    private String modifier;

    private Short orderLevel;

    private String partitionId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Short getOrderLevel() {
        return orderLevel;
    }

    public void setOrderLevel(Short orderLevel) {
        this.orderLevel = orderLevel;
    }

    public String getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(String partitionId) {
        this.partitionId = partitionId == null ? null : partitionId.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends PartitionGroup> T copy(T bean) {
        bean.setGroupId(getGroupId());
        bean.setGroupName(getGroupName());
        bean.setCreateTime(getCreateTime());
        bean.setCreator(getCreator());
        bean.setModifyTime(getModifyTime());
        bean.setModifier(getModifier());
        bean.setOrderLevel(getOrderLevel());
        bean.setPartitionId(getPartitionId());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", groupId:" + getGroupId() + 
        	", groupName:" + getGroupName() + 
        	", createTime:" + getCreateTime() + 
        	", creator:" + getCreator() + 
        	", modifyTime:" + getModifyTime() + 
        	", modifier:" + getModifier() + 
        	", orderLevel:" + getOrderLevel() + 
        	", partitionId:" + getPartitionId() + 
        "}";
    }
}