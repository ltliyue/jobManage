package inspur.crawl.dataManage.pojo;

import java.util.Date;

public class CjSysNotes {
    private String id;

    private String tableName;

    private String ruleId;

    private String taskId;

    private Date noteDate;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public Date getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Date noteDate) {
        this.noteDate = noteDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends CjSysNotes> T copy(T bean) {
        bean.setId(getId());
        bean.setTableName(getTableName());
        bean.setRuleId(getRuleId());
        bean.setTaskId(getTaskId());
        bean.setNoteDate(getNoteDate());
        bean.setRemark(getRemark());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", id:" + getId() + 
        	", tableName:" + getTableName() + 
        	", ruleId:" + getRuleId() + 
        	", taskId:" + getTaskId() + 
        	", noteDate:" + getNoteDate() + 
        	", remark:" + getRemark() + 
        "}";
    }
}