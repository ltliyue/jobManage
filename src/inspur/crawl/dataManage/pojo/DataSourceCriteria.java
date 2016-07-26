package inspur.crawl.dataManage.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataSourceCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int top = -1;

    protected int skipCount = 0;

    public DataSourceCriteria() {
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

        public Criteria andSoruceNameIsNull() {
            addCriterion("SORUCE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSoruceNameIsNotNull() {
            addCriterion("SORUCE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSoruceNameEqualTo(String value) {
            addCriterion("SORUCE_NAME =", value, "soruceName");
            return (Criteria) this;
        }

        public Criteria andSoruceNameNotEqualTo(String value) {
            addCriterion("SORUCE_NAME <>", value, "soruceName");
            return (Criteria) this;
        }

        public Criteria andSoruceNameGreaterThan(String value) {
            addCriterion("SORUCE_NAME >", value, "soruceName");
            return (Criteria) this;
        }

        public Criteria andSoruceNameGreaterThanOrEqualTo(String value) {
            addCriterion("SORUCE_NAME >=", value, "soruceName");
            return (Criteria) this;
        }

        public Criteria andSoruceNameLessThan(String value) {
            addCriterion("SORUCE_NAME <", value, "soruceName");
            return (Criteria) this;
        }

        public Criteria andSoruceNameLessThanOrEqualTo(String value) {
            addCriterion("SORUCE_NAME <=", value, "soruceName");
            return (Criteria) this;
        }

        public Criteria andSoruceNameLike(String value) {
            addCriterion("SORUCE_NAME like", value, "soruceName");
            return (Criteria) this;
        }

        public Criteria andSoruceNameNotLike(String value) {
            addCriterion("SORUCE_NAME not like", value, "soruceName");
            return (Criteria) this;
        }

        public Criteria andSoruceNameIn(List<String> values) {
            addCriterion("SORUCE_NAME in", values, "soruceName");
            return (Criteria) this;
        }

        public Criteria andSoruceNameNotIn(List<String> values) {
            addCriterion("SORUCE_NAME not in", values, "soruceName");
            return (Criteria) this;
        }

        public Criteria andSoruceNameBetween(String value1, String value2) {
            addCriterion("SORUCE_NAME between", value1, value2, "soruceName");
            return (Criteria) this;
        }

        public Criteria andSoruceNameNotBetween(String value1, String value2) {
            addCriterion("SORUCE_NAME not between", value1, value2, "soruceName");
            return (Criteria) this;
        }

        public Criteria andDataAddressIsNull() {
            addCriterion("DATA_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andDataAddressIsNotNull() {
            addCriterion("DATA_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andDataAddressEqualTo(String value) {
            addCriterion("DATA_ADDRESS =", value, "dataAddress");
            return (Criteria) this;
        }

        public Criteria andDataAddressNotEqualTo(String value) {
            addCriterion("DATA_ADDRESS <>", value, "dataAddress");
            return (Criteria) this;
        }

        public Criteria andDataAddressGreaterThan(String value) {
            addCriterion("DATA_ADDRESS >", value, "dataAddress");
            return (Criteria) this;
        }

        public Criteria andDataAddressGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_ADDRESS >=", value, "dataAddress");
            return (Criteria) this;
        }

        public Criteria andDataAddressLessThan(String value) {
            addCriterion("DATA_ADDRESS <", value, "dataAddress");
            return (Criteria) this;
        }

        public Criteria andDataAddressLessThanOrEqualTo(String value) {
            addCriterion("DATA_ADDRESS <=", value, "dataAddress");
            return (Criteria) this;
        }

        public Criteria andDataAddressLike(String value) {
            addCriterion("DATA_ADDRESS like", value, "dataAddress");
            return (Criteria) this;
        }

        public Criteria andDataAddressNotLike(String value) {
            addCriterion("DATA_ADDRESS not like", value, "dataAddress");
            return (Criteria) this;
        }

        public Criteria andDataAddressIn(List<String> values) {
            addCriterion("DATA_ADDRESS in", values, "dataAddress");
            return (Criteria) this;
        }

        public Criteria andDataAddressNotIn(List<String> values) {
            addCriterion("DATA_ADDRESS not in", values, "dataAddress");
            return (Criteria) this;
        }

        public Criteria andDataAddressBetween(String value1, String value2) {
            addCriterion("DATA_ADDRESS between", value1, value2, "dataAddress");
            return (Criteria) this;
        }

        public Criteria andDataAddressNotBetween(String value1, String value2) {
            addCriterion("DATA_ADDRESS not between", value1, value2, "dataAddress");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andPassNameIsNull() {
            addCriterion("PASS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPassNameIsNotNull() {
            addCriterion("PASS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPassNameEqualTo(String value) {
            addCriterion("PASS_NAME =", value, "passName");
            return (Criteria) this;
        }

        public Criteria andPassNameNotEqualTo(String value) {
            addCriterion("PASS_NAME <>", value, "passName");
            return (Criteria) this;
        }

        public Criteria andPassNameGreaterThan(String value) {
            addCriterion("PASS_NAME >", value, "passName");
            return (Criteria) this;
        }

        public Criteria andPassNameGreaterThanOrEqualTo(String value) {
            addCriterion("PASS_NAME >=", value, "passName");
            return (Criteria) this;
        }

        public Criteria andPassNameLessThan(String value) {
            addCriterion("PASS_NAME <", value, "passName");
            return (Criteria) this;
        }

        public Criteria andPassNameLessThanOrEqualTo(String value) {
            addCriterion("PASS_NAME <=", value, "passName");
            return (Criteria) this;
        }

        public Criteria andPassNameLike(String value) {
            addCriterion("PASS_NAME like", value, "passName");
            return (Criteria) this;
        }

        public Criteria andPassNameNotLike(String value) {
            addCriterion("PASS_NAME not like", value, "passName");
            return (Criteria) this;
        }

        public Criteria andPassNameIn(List<String> values) {
            addCriterion("PASS_NAME in", values, "passName");
            return (Criteria) this;
        }

        public Criteria andPassNameNotIn(List<String> values) {
            addCriterion("PASS_NAME not in", values, "passName");
            return (Criteria) this;
        }

        public Criteria andPassNameBetween(String value1, String value2) {
            addCriterion("PASS_NAME between", value1, value2, "passName");
            return (Criteria) this;
        }

        public Criteria andPassNameNotBetween(String value1, String value2) {
            addCriterion("PASS_NAME not between", value1, value2, "passName");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("DATA_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("DATA_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(String value) {
            addCriterion("DATA_TYPE =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(String value) {
            addCriterion("DATA_TYPE <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(String value) {
            addCriterion("DATA_TYPE >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_TYPE >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(String value) {
            addCriterion("DATA_TYPE <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(String value) {
            addCriterion("DATA_TYPE <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLike(String value) {
            addCriterion("DATA_TYPE like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotLike(String value) {
            addCriterion("DATA_TYPE not like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<String> values) {
            addCriterion("DATA_TYPE in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<String> values) {
            addCriterion("DATA_TYPE not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(String value1, String value2) {
            addCriterion("DATA_TYPE between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(String value1, String value2) {
            addCriterion("DATA_TYPE not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
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