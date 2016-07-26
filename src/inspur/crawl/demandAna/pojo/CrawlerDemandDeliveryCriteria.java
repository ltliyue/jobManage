package inspur.crawl.demandAna.pojo;

import java.util.ArrayList;
import java.util.List;

public class CrawlerDemandDeliveryCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int top = -1;

    protected int skipCount = 0;

    public CrawlerDemandDeliveryCriteria() {
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

        public Criteria andDemandIdIsNull() {
            addCriterion("DEMAND_ID is null");
            return (Criteria) this;
        }

        public Criteria andDemandIdIsNotNull() {
            addCriterion("DEMAND_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDemandIdEqualTo(String value) {
            addCriterion("DEMAND_ID =", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdNotEqualTo(String value) {
            addCriterion("DEMAND_ID <>", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdGreaterThan(String value) {
            addCriterion("DEMAND_ID >", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEMAND_ID >=", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdLessThan(String value) {
            addCriterion("DEMAND_ID <", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdLessThanOrEqualTo(String value) {
            addCriterion("DEMAND_ID <=", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdLike(String value) {
            addCriterion("DEMAND_ID like", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdNotLike(String value) {
            addCriterion("DEMAND_ID not like", value, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdIn(List<String> values) {
            addCriterion("DEMAND_ID in", values, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdNotIn(List<String> values) {
            addCriterion("DEMAND_ID not in", values, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdBetween(String value1, String value2) {
            addCriterion("DEMAND_ID between", value1, value2, "demandId");
            return (Criteria) this;
        }

        public Criteria andDemandIdNotBetween(String value1, String value2) {
            addCriterion("DEMAND_ID not between", value1, value2, "demandId");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNull() {
            addCriterion("SITE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSiteIdIsNotNull() {
            addCriterion("SITE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSiteIdEqualTo(String value) {
            addCriterion("SITE_ID =", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotEqualTo(String value) {
            addCriterion("SITE_ID <>", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThan(String value) {
            addCriterion("SITE_ID >", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_ID >=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThan(String value) {
            addCriterion("SITE_ID <", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLessThanOrEqualTo(String value) {
            addCriterion("SITE_ID <=", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdLike(String value) {
            addCriterion("SITE_ID like", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotLike(String value) {
            addCriterion("SITE_ID not like", value, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdIn(List<String> values) {
            addCriterion("SITE_ID in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotIn(List<String> values) {
            addCriterion("SITE_ID not in", values, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdBetween(String value1, String value2) {
            addCriterion("SITE_ID between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andSiteIdNotBetween(String value1, String value2) {
            addCriterion("SITE_ID not between", value1, value2, "siteId");
            return (Criteria) this;
        }

        public Criteria andFieldIdIsNull() {
            addCriterion("FIELD_ID is null");
            return (Criteria) this;
        }

        public Criteria andFieldIdIsNotNull() {
            addCriterion("FIELD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFieldIdEqualTo(String value) {
            addCriterion("FIELD_ID =", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdNotEqualTo(String value) {
            addCriterion("FIELD_ID <>", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdGreaterThan(String value) {
            addCriterion("FIELD_ID >", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_ID >=", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdLessThan(String value) {
            addCriterion("FIELD_ID <", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdLessThanOrEqualTo(String value) {
            addCriterion("FIELD_ID <=", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdLike(String value) {
            addCriterion("FIELD_ID like", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdNotLike(String value) {
            addCriterion("FIELD_ID not like", value, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdIn(List<String> values) {
            addCriterion("FIELD_ID in", values, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdNotIn(List<String> values) {
            addCriterion("FIELD_ID not in", values, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdBetween(String value1, String value2) {
            addCriterion("FIELD_ID between", value1, value2, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldIdNotBetween(String value1, String value2) {
            addCriterion("FIELD_ID not between", value1, value2, "fieldId");
            return (Criteria) this;
        }

        public Criteria andFieldNameIsNull() {
            addCriterion("FIELD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFieldNameIsNotNull() {
            addCriterion("FIELD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFieldNameEqualTo(String value) {
            addCriterion("FIELD_NAME =", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotEqualTo(String value) {
            addCriterion("FIELD_NAME <>", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameGreaterThan(String value) {
            addCriterion("FIELD_NAME >", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_NAME >=", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLessThan(String value) {
            addCriterion("FIELD_NAME <", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLessThanOrEqualTo(String value) {
            addCriterion("FIELD_NAME <=", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameLike(String value) {
            addCriterion("FIELD_NAME like", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotLike(String value) {
            addCriterion("FIELD_NAME not like", value, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameIn(List<String> values) {
            addCriterion("FIELD_NAME in", values, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotIn(List<String> values) {
            addCriterion("FIELD_NAME not in", values, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameBetween(String value1, String value2) {
            addCriterion("FIELD_NAME between", value1, value2, "fieldName");
            return (Criteria) this;
        }

        public Criteria andFieldNameNotBetween(String value1, String value2) {
            addCriterion("FIELD_NAME not between", value1, value2, "fieldName");
            return (Criteria) this;
        }

        public Criteria andConditionDesIsNull() {
            addCriterion("CONDITION_DES is null");
            return (Criteria) this;
        }

        public Criteria andConditionDesIsNotNull() {
            addCriterion("CONDITION_DES is not null");
            return (Criteria) this;
        }

        public Criteria andConditionDesEqualTo(String value) {
            addCriterion("CONDITION_DES =", value, "conditionDes");
            return (Criteria) this;
        }

        public Criteria andConditionDesNotEqualTo(String value) {
            addCriterion("CONDITION_DES <>", value, "conditionDes");
            return (Criteria) this;
        }

        public Criteria andConditionDesGreaterThan(String value) {
            addCriterion("CONDITION_DES >", value, "conditionDes");
            return (Criteria) this;
        }

        public Criteria andConditionDesGreaterThanOrEqualTo(String value) {
            addCriterion("CONDITION_DES >=", value, "conditionDes");
            return (Criteria) this;
        }

        public Criteria andConditionDesLessThan(String value) {
            addCriterion("CONDITION_DES <", value, "conditionDes");
            return (Criteria) this;
        }

        public Criteria andConditionDesLessThanOrEqualTo(String value) {
            addCriterion("CONDITION_DES <=", value, "conditionDes");
            return (Criteria) this;
        }

        public Criteria andConditionDesLike(String value) {
            addCriterion("CONDITION_DES like", value, "conditionDes");
            return (Criteria) this;
        }

        public Criteria andConditionDesNotLike(String value) {
            addCriterion("CONDITION_DES not like", value, "conditionDes");
            return (Criteria) this;
        }

        public Criteria andConditionDesIn(List<String> values) {
            addCriterion("CONDITION_DES in", values, "conditionDes");
            return (Criteria) this;
        }

        public Criteria andConditionDesNotIn(List<String> values) {
            addCriterion("CONDITION_DES not in", values, "conditionDes");
            return (Criteria) this;
        }

        public Criteria andConditionDesBetween(String value1, String value2) {
            addCriterion("CONDITION_DES between", value1, value2, "conditionDes");
            return (Criteria) this;
        }

        public Criteria andConditionDesNotBetween(String value1, String value2) {
            addCriterion("CONDITION_DES not between", value1, value2, "conditionDes");
            return (Criteria) this;
        }

        public Criteria andConditionValIsNull() {
            addCriterion("CONDITION_VAL is null");
            return (Criteria) this;
        }

        public Criteria andConditionValIsNotNull() {
            addCriterion("CONDITION_VAL is not null");
            return (Criteria) this;
        }

        public Criteria andConditionValEqualTo(String value) {
            addCriterion("CONDITION_VAL =", value, "conditionVal");
            return (Criteria) this;
        }

        public Criteria andConditionValNotEqualTo(String value) {
            addCriterion("CONDITION_VAL <>", value, "conditionVal");
            return (Criteria) this;
        }

        public Criteria andConditionValGreaterThan(String value) {
            addCriterion("CONDITION_VAL >", value, "conditionVal");
            return (Criteria) this;
        }

        public Criteria andConditionValGreaterThanOrEqualTo(String value) {
            addCriterion("CONDITION_VAL >=", value, "conditionVal");
            return (Criteria) this;
        }

        public Criteria andConditionValLessThan(String value) {
            addCriterion("CONDITION_VAL <", value, "conditionVal");
            return (Criteria) this;
        }

        public Criteria andConditionValLessThanOrEqualTo(String value) {
            addCriterion("CONDITION_VAL <=", value, "conditionVal");
            return (Criteria) this;
        }

        public Criteria andConditionValLike(String value) {
            addCriterion("CONDITION_VAL like", value, "conditionVal");
            return (Criteria) this;
        }

        public Criteria andConditionValNotLike(String value) {
            addCriterion("CONDITION_VAL not like", value, "conditionVal");
            return (Criteria) this;
        }

        public Criteria andConditionValIn(List<String> values) {
            addCriterion("CONDITION_VAL in", values, "conditionVal");
            return (Criteria) this;
        }

        public Criteria andConditionValNotIn(List<String> values) {
            addCriterion("CONDITION_VAL not in", values, "conditionVal");
            return (Criteria) this;
        }

        public Criteria andConditionValBetween(String value1, String value2) {
            addCriterion("CONDITION_VAL between", value1, value2, "conditionVal");
            return (Criteria) this;
        }

        public Criteria andConditionValNotBetween(String value1, String value2) {
            addCriterion("CONDITION_VAL not between", value1, value2, "conditionVal");
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