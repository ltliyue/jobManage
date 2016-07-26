package inspur.crawl.sysManage.pojo;

import java.util.ArrayList;
import java.util.List;

public class AuthorityCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int top = -1;

    protected int skipCount = 0;

    public AuthorityCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setTop(int top) {
        this.top=top;
    }

    public int getTop() {
        return top;
    }

    /**
     * 设置查询数据库时掠过前面多少条记录
     * @param skipCount 忽略前面记录的数量
     */
    public void setSkipCount(int skipCount) {
        this.skipCount = skipCount;
    }

    public int getSkipCount() {
        return skipCount;
    }

    public int getEndCount() {
        return skipCount + top;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Short value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Short value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Short value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Short value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Short value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Short value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Short> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Short> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Short value1, Short value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Short value1, Short value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andEnableIsNull() {
            addCriterion("ENABLE is null");
            return (Criteria) this;
        }

        public Criteria andEnableIsNotNull() {
            addCriterion("ENABLE is not null");
            return (Criteria) this;
        }

        public Criteria andEnableEqualTo(Short value) {
            addCriterion("ENABLE =", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotEqualTo(Short value) {
            addCriterion("ENABLE <>", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThan(Short value) {
            addCriterion("ENABLE >", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableGreaterThanOrEqualTo(Short value) {
            addCriterion("ENABLE >=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThan(Short value) {
            addCriterion("ENABLE <", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableLessThanOrEqualTo(Short value) {
            addCriterion("ENABLE <=", value, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableIn(List<Short> values) {
            addCriterion("ENABLE in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotIn(List<Short> values) {
            addCriterion("ENABLE not in", values, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableBetween(Short value1, Short value2) {
            addCriterion("ENABLE between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andEnableNotBetween(Short value1, Short value2) {
            addCriterion("ENABLE not between", value1, value2, "enable");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andLevelcodeIsNull() {
            addCriterion("LEVELCODE is null");
            return (Criteria) this;
        }

        public Criteria andLevelcodeIsNotNull() {
            addCriterion("LEVELCODE is not null");
            return (Criteria) this;
        }

        public Criteria andLevelcodeEqualTo(String value) {
            addCriterion("LEVELCODE =", value, "levelcode");
            return (Criteria) this;
        }

        public Criteria andLevelcodeNotEqualTo(String value) {
            addCriterion("LEVELCODE <>", value, "levelcode");
            return (Criteria) this;
        }

        public Criteria andLevelcodeGreaterThan(String value) {
            addCriterion("LEVELCODE >", value, "levelcode");
            return (Criteria) this;
        }

        public Criteria andLevelcodeGreaterThanOrEqualTo(String value) {
            addCriterion("LEVELCODE >=", value, "levelcode");
            return (Criteria) this;
        }

        public Criteria andLevelcodeLessThan(String value) {
            addCriterion("LEVELCODE <", value, "levelcode");
            return (Criteria) this;
        }

        public Criteria andLevelcodeLessThanOrEqualTo(String value) {
            addCriterion("LEVELCODE <=", value, "levelcode");
            return (Criteria) this;
        }

        public Criteria andLevelcodeLike(String value) {
            addCriterion("LEVELCODE like", value, "levelcode");
            return (Criteria) this;
        }

        public Criteria andLevelcodeNotLike(String value) {
            addCriterion("LEVELCODE not like", value, "levelcode");
            return (Criteria) this;
        }

        public Criteria andLevelcodeIn(List<String> values) {
            addCriterion("LEVELCODE in", values, "levelcode");
            return (Criteria) this;
        }

        public Criteria andLevelcodeNotIn(List<String> values) {
            addCriterion("LEVELCODE not in", values, "levelcode");
            return (Criteria) this;
        }

        public Criteria andLevelcodeBetween(String value1, String value2) {
            addCriterion("LEVELCODE between", value1, value2, "levelcode");
            return (Criteria) this;
        }

        public Criteria andLevelcodeNotBetween(String value1, String value2) {
            addCriterion("LEVELCODE not between", value1, value2, "levelcode");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("POSITION is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(Long value) {
            addCriterion("POSITION =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(Long value) {
            addCriterion("POSITION <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(Long value) {
            addCriterion("POSITION >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(Long value) {
            addCriterion("POSITION >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(Long value) {
            addCriterion("POSITION <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(Long value) {
            addCriterion("POSITION <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<Long> values) {
            addCriterion("POSITION in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<Long> values) {
            addCriterion("POSITION not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(Long value1, Long value2) {
            addCriterion("POSITION between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(Long value1, Long value2) {
            addCriterion("POSITION not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andThevalueIsNull() {
            addCriterion("THEVALUE is null");
            return (Criteria) this;
        }

        public Criteria andThevalueIsNotNull() {
            addCriterion("THEVALUE is not null");
            return (Criteria) this;
        }

        public Criteria andThevalueEqualTo(String value) {
            addCriterion("THEVALUE =", value, "thevalue");
            return (Criteria) this;
        }

        public Criteria andThevalueNotEqualTo(String value) {
            addCriterion("THEVALUE <>", value, "thevalue");
            return (Criteria) this;
        }

        public Criteria andThevalueGreaterThan(String value) {
            addCriterion("THEVALUE >", value, "thevalue");
            return (Criteria) this;
        }

        public Criteria andThevalueGreaterThanOrEqualTo(String value) {
            addCriterion("THEVALUE >=", value, "thevalue");
            return (Criteria) this;
        }

        public Criteria andThevalueLessThan(String value) {
            addCriterion("THEVALUE <", value, "thevalue");
            return (Criteria) this;
        }

        public Criteria andThevalueLessThanOrEqualTo(String value) {
            addCriterion("THEVALUE <=", value, "thevalue");
            return (Criteria) this;
        }

        public Criteria andThevalueLike(String value) {
            addCriterion("THEVALUE like", value, "thevalue");
            return (Criteria) this;
        }

        public Criteria andThevalueNotLike(String value) {
            addCriterion("THEVALUE not like", value, "thevalue");
            return (Criteria) this;
        }

        public Criteria andThevalueIn(List<String> values) {
            addCriterion("THEVALUE in", values, "thevalue");
            return (Criteria) this;
        }

        public Criteria andThevalueNotIn(List<String> values) {
            addCriterion("THEVALUE not in", values, "thevalue");
            return (Criteria) this;
        }

        public Criteria andThevalueBetween(String value1, String value2) {
            addCriterion("THEVALUE between", value1, value2, "thevalue");
            return (Criteria) this;
        }

        public Criteria andThevalueNotBetween(String value1, String value2) {
            addCriterion("THEVALUE not between", value1, value2, "thevalue");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andMatchurlIsNull() {
            addCriterion("MATCHURL is null");
            return (Criteria) this;
        }

        public Criteria andMatchurlIsNotNull() {
            addCriterion("MATCHURL is not null");
            return (Criteria) this;
        }

        public Criteria andMatchurlEqualTo(String value) {
            addCriterion("MATCHURL =", value, "matchurl");
            return (Criteria) this;
        }

        public Criteria andMatchurlNotEqualTo(String value) {
            addCriterion("MATCHURL <>", value, "matchurl");
            return (Criteria) this;
        }

        public Criteria andMatchurlGreaterThan(String value) {
            addCriterion("MATCHURL >", value, "matchurl");
            return (Criteria) this;
        }

        public Criteria andMatchurlGreaterThanOrEqualTo(String value) {
            addCriterion("MATCHURL >=", value, "matchurl");
            return (Criteria) this;
        }

        public Criteria andMatchurlLessThan(String value) {
            addCriterion("MATCHURL <", value, "matchurl");
            return (Criteria) this;
        }

        public Criteria andMatchurlLessThanOrEqualTo(String value) {
            addCriterion("MATCHURL <=", value, "matchurl");
            return (Criteria) this;
        }

        public Criteria andMatchurlLike(String value) {
            addCriterion("MATCHURL like", value, "matchurl");
            return (Criteria) this;
        }

        public Criteria andMatchurlNotLike(String value) {
            addCriterion("MATCHURL not like", value, "matchurl");
            return (Criteria) this;
        }

        public Criteria andMatchurlIn(List<String> values) {
            addCriterion("MATCHURL in", values, "matchurl");
            return (Criteria) this;
        }

        public Criteria andMatchurlNotIn(List<String> values) {
            addCriterion("MATCHURL not in", values, "matchurl");
            return (Criteria) this;
        }

        public Criteria andMatchurlBetween(String value1, String value2) {
            addCriterion("MATCHURL between", value1, value2, "matchurl");
            return (Criteria) this;
        }

        public Criteria andMatchurlNotBetween(String value1, String value2) {
            addCriterion("MATCHURL not between", value1, value2, "matchurl");
            return (Criteria) this;
        }

        public Criteria andItemiconIsNull() {
            addCriterion("ITEMICON is null");
            return (Criteria) this;
        }

        public Criteria andItemiconIsNotNull() {
            addCriterion("ITEMICON is not null");
            return (Criteria) this;
        }

        public Criteria andItemiconEqualTo(String value) {
            addCriterion("ITEMICON =", value, "itemicon");
            return (Criteria) this;
        }

        public Criteria andItemiconNotEqualTo(String value) {
            addCriterion("ITEMICON <>", value, "itemicon");
            return (Criteria) this;
        }

        public Criteria andItemiconGreaterThan(String value) {
            addCriterion("ITEMICON >", value, "itemicon");
            return (Criteria) this;
        }

        public Criteria andItemiconGreaterThanOrEqualTo(String value) {
            addCriterion("ITEMICON >=", value, "itemicon");
            return (Criteria) this;
        }

        public Criteria andItemiconLessThan(String value) {
            addCriterion("ITEMICON <", value, "itemicon");
            return (Criteria) this;
        }

        public Criteria andItemiconLessThanOrEqualTo(String value) {
            addCriterion("ITEMICON <=", value, "itemicon");
            return (Criteria) this;
        }

        public Criteria andItemiconLike(String value) {
            addCriterion("ITEMICON like", value, "itemicon");
            return (Criteria) this;
        }

        public Criteria andItemiconNotLike(String value) {
            addCriterion("ITEMICON not like", value, "itemicon");
            return (Criteria) this;
        }

        public Criteria andItemiconIn(List<String> values) {
            addCriterion("ITEMICON in", values, "itemicon");
            return (Criteria) this;
        }

        public Criteria andItemiconNotIn(List<String> values) {
            addCriterion("ITEMICON not in", values, "itemicon");
            return (Criteria) this;
        }

        public Criteria andItemiconBetween(String value1, String value2) {
            addCriterion("ITEMICON between", value1, value2, "itemicon");
            return (Criteria) this;
        }

        public Criteria andItemiconNotBetween(String value1, String value2) {
            addCriterion("ITEMICON not between", value1, value2, "itemicon");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("PARENTID is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("PARENTID is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(String value) {
            addCriterion("PARENTID =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(String value) {
            addCriterion("PARENTID <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(String value) {
            addCriterion("PARENTID >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(String value) {
            addCriterion("PARENTID >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(String value) {
            addCriterion("PARENTID <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(String value) {
            addCriterion("PARENTID <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLike(String value) {
            addCriterion("PARENTID like", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotLike(String value) {
            addCriterion("PARENTID not like", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<String> values) {
            addCriterion("PARENTID in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<String> values) {
            addCriterion("PARENTID not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(String value1, String value2) {
            addCriterion("PARENTID between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(String value1, String value2) {
            addCriterion("PARENTID not between", value1, value2, "parentid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}