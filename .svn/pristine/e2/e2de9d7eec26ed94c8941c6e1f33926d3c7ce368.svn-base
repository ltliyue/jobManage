package inspur.crawl.codeManage.pojo;

public class OralceKeyWords {
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    /** 
     * 拷贝，将对象中的字段全部拷贝到子对象中
     * @param bean 接收对象的子类
     * @return 拷贝完成后的子类
     */ 
    public  <T extends OralceKeyWords> T copy(T bean) {
        bean.setKeyword(getKeyword());
        return bean;
    }

    /** 
     * 格式化显示
     */ 
    @Override
    public String toString() {
        return "{" + 
        	", keyword:" + getKeyword() + 
        "}";
    }
}