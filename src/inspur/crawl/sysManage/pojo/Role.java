package inspur.crawl.sysManage.pojo;

public class Role {
    private String id;

    private String name;

    private Short enable;

    private String authorityid;

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

    public Short getEnable() {
        return enable;
    }

    public void setEnable(Short enable) {
        this.enable = enable;
    }

    public String getAuthorityid() {
        return authorityid;
    }

    public void setAuthorityid(String authorityid) {
        this.authorityid = authorityid == null ? null : authorityid.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends Role> T copy(T bean) {
        bean.setId(getId());
        bean.setName(getName());
        bean.setEnable(getEnable());
        bean.setAuthorityid(getAuthorityid());
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
        	", enable:" + getEnable() + 
        	", authorityid:" + getAuthorityid() + 
        "}";
    }
}