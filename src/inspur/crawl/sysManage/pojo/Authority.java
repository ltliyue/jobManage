package inspur.crawl.sysManage.pojo;

public class Authority {
    private String id;

    private Short version;

    private Short enable;

    private String name;

    private String levelcode;

    private Long position;

    private String thevalue;

    private String url;

    private String matchurl;

    private String itemicon;

    private String parentid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Short getVersion() {
        return version;
    }

    public void setVersion(Short version) {
        this.version = version;
    }

    public Short getEnable() {
        return enable;
    }

    public void setEnable(Short enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLevelcode() {
        return levelcode;
    }

    public void setLevelcode(String levelcode) {
        this.levelcode = levelcode == null ? null : levelcode.trim();
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public String getThevalue() {
        return thevalue;
    }

    public void setThevalue(String thevalue) {
        this.thevalue = thevalue == null ? null : thevalue.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getMatchurl() {
        return matchurl;
    }

    public void setMatchurl(String matchurl) {
        this.matchurl = matchurl == null ? null : matchurl.trim();
    }

    public String getItemicon() {
        return itemicon;
    }

    public void setItemicon(String itemicon) {
        this.itemicon = itemicon == null ? null : itemicon.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends Authority> T copy(T bean) {
        bean.setId(getId());
        bean.setVersion(getVersion());
        bean.setEnable(getEnable());
        bean.setName(getName());
        bean.setLevelcode(getLevelcode());
        bean.setPosition(getPosition());
        bean.setThevalue(getThevalue());
        bean.setUrl(getUrl());
        bean.setMatchurl(getMatchurl());
        bean.setItemicon(getItemicon());
        bean.setParentid(getParentid());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", id:" + getId() + 
        	", version:" + getVersion() + 
        	", enable:" + getEnable() + 
        	", name:" + getName() + 
        	", levelcode:" + getLevelcode() + 
        	", position:" + getPosition() + 
        	", thevalue:" + getThevalue() + 
        	", url:" + getUrl() + 
        	", matchurl:" + getMatchurl() + 
        	", itemicon:" + getItemicon() + 
        	", parentid:" + getParentid() + 
        "}";
    }
}