package inspur.crawl.sysManage.pojo;



import inspur.crawl.common.interceptor.Page;
import inspur.crawl.common.tools.JsonDateSerializer;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;



public class Account {
    private String id;

    private Short enable;

    private String name;

    private String email;

    private String username;

    private String password;
   
    private Date registertime;

    private String roleid;
    
    private String ypassword;
    
    private Role role ;
    
    private Page page;
    
    public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getYpassword() {
		return ypassword;
	}

	public void setYpassword(String ypassword) {
		this.ypassword = ypassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends Account> T copy(T bean) {
        bean.setId(getId());
        bean.setEnable(getEnable());
        bean.setName(getName());
        bean.setEmail(getEmail());
        bean.setUsername(getUsername());
        bean.setPassword(getPassword());
        bean.setRegistertime(getRegistertime());
        bean.setRoleid(getRoleid());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", id:" + getId() + 
        	", enable:" + getEnable() + 
        	", name:" + getName() + 
        	", email:" + getEmail() + 
        	", username:" + getUsername() + 
        	", password:" + getPassword() + 
        	", registertime:" + getRegistertime() + 
        	", roleid:" + getRoleid() + 
        "}";
    }
}