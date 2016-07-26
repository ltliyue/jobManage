package inspur.crawl.codeManage.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StandardCodeContentCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int top = -1;

    protected int skipCount = 0;

    public StandardCodeContentCriteria() {
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

        public Criteria andFieldCodeIsNull() {
            addCriterion("FIELD_CODE is null");
            return (Criteria) this;
        }

        public Criteria andFieldCodeIsNotNull() {
            addCriterion("FIELD_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andFieldCodeEqualTo(String value) {
            addCriterion("FIELD_CODE =", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeNotEqualTo(String value) {
            addCriterion("FIELD_CODE <>", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeGreaterThan(String value) {
            addCriterion("FIELD_CODE >", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_CODE >=", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeLessThan(String value) {
            addCriterion("FIELD_CODE <", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeLessThanOrEqualTo(String value) {
            addCriterion("FIELD_CODE <=", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeLike(String value) {
            addCriterion("FIELD_CODE like", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeNotLike(String value) {
            addCriterion("FIELD_CODE not like", value, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeIn(List<String> values) {
            addCriterion("FIELD_CODE in", values, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeNotIn(List<String> values) {
            addCriterion("FIELD_CODE not in", values, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeBetween(String value1, String value2) {
            addCriterion("FIELD_CODE between", value1, value2, "fieldCode");
            return (Criteria) this;
        }

        public Criteria andFieldCodeNotBetween(String value1, String value2) {
            addCriterion("FIELD_CODE not between", value1, value2, "fieldCode");
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

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("PARENT_ID like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("PARENT_ID not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionIsNull() {
            addCriterion("FIELD_DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionIsNotNull() {
            addCriterion("FIELD_DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionEqualTo(String value) {
            addCriterion("FIELD_DESCRIPTION =", value, "fieldDescription");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionNotEqualTo(String value) {
            addCriterion("FIELD_DESCRIPTION <>", value, "fieldDescription");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionGreaterThan(String value) {
            addCriterion("FIELD_DESCRIPTION >", value, "fieldDescription");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("FIELD_DESCRIPTION >=", value, "fieldDescription");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionLessThan(String value) {
            addCriterion("FIELD_DESCRIPTION <", value, "fieldDescription");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionLessThanOrEqualTo(String value) {
            addCriterion("FIELD_DESCRIPTION <=", value, "fieldDescription");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionLike(String value) {
            addCriterion("FIELD_DESCRIPTION like", value, "fieldDescription");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionNotLike(String value) {
            addCriterion("FIELD_DESCRIPTION not like", value, "fieldDescription");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionIn(List<String> values) {
            addCriterion("FIELD_DESCRIPTION in", values, "fieldDescription");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionNotIn(List<String> values) {
            addCriterion("FIELD_DESCRIPTION not in", values, "fieldDescription");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionBetween(String value1, String value2) {
            addCriterion("FIELD_DESCRIPTION between", value1, value2, "fieldDescription");
            return (Criteria) this;
        }

        public Criteria andFieldDescriptionNotBetween(String value1, String value2) {
            addCriterion("FIELD_DESCRIPTION not between", value1, value2, "fieldDescription");
            return (Criteria) this;
        }

        public Criteria andPublisherIdIsNull() {
            addCriterion("PUBLISHER_ID is null");
            return (Criteria) this;
        }

        public Criteria andPublisherIdIsNotNull() {
            addCriterion("PUBLISHER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPublisherIdEqualTo(String value) {
            addCriterion("PUBLISHER_ID =", value, "publisherId");
            return (Criteria) this;
        }

        public Criteria andPublisherIdNotEqualTo(String value) {
            addCriterion("PUBLISHER_ID <>", value, "publisherId");
            return (Criteria) this;
        }

        public Criteria andPublisherIdGreaterThan(String value) {
            addCriterion("PUBLISHER_ID >", value, "publisherId");
            return (Criteria) this;
        }

        public Criteria andPublisherIdGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISHER_ID >=", value, "publisherId");
            return (Criteria) this;
        }

        public Criteria andPublisherIdLessThan(String value) {
            addCriterion("PUBLISHER_ID <", value, "publisherId");
            return (Criteria) this;
        }

        public Criteria andPublisherIdLessThanOrEqualTo(String value) {
            addCriterion("PUBLISHER_ID <=", value, "publisherId");
            return (Criteria) this;
        }

        public Criteria andPublisherIdLike(String value) {
            addCriterion("PUBLISHER_ID like", value, "publisherId");
            return (Criteria) this;
        }

        public Criteria andPublisherIdNotLike(String value) {
            addCriterion("PUBLISHER_ID not like", value, "publisherId");
            return (Criteria) this;
        }

        public Criteria andPublisherIdIn(List<String> values) {
            addCriterion("PUBLISHER_ID in", values, "publisherId");
            return (Criteria) this;
        }

        public Criteria andPublisherIdNotIn(List<String> values) {
            addCriterion("PUBLISHER_ID not in", values, "publisherId");
            return (Criteria) this;
        }

        public Criteria andPublisherIdBetween(String value1, String value2) {
            addCriterion("PUBLISHER_ID between", value1, value2, "publisherId");
            return (Criteria) this;
        }

        public Criteria andPublisherIdNotBetween(String value1, String value2) {
            addCriterion("PUBLISHER_ID not between", value1, value2, "publisherId");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNull() {
            addCriterion("PUBLISH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNotNull() {
            addCriterion("PUBLISH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeEqualTo(Date value) {
            addCriterion("PUBLISH_TIME =", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotEqualTo(Date value) {
            addCriterion("PUBLISH_TIME <>", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThan(Date value) {
            addCriterion("PUBLISH_TIME >", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("PUBLISH_TIME >=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThan(Date value) {
            addCriterion("PUBLISH_TIME <", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThanOrEqualTo(Date value) {
            addCriterion("PUBLISH_TIME <=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIn(List<Date> values) {
            addCriterion("PUBLISH_TIME in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotIn(List<Date> values) {
            addCriterion("PUBLISH_TIME not in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeBetween(Date value1, Date value2) {
            addCriterion("PUBLISH_TIME between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotBetween(Date value1, Date value2) {
            addCriterion("PUBLISH_TIME not between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andValidateFlagIsNull() {
            addCriterion("VALIDATE_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andValidateFlagIsNotNull() {
            addCriterion("VALIDATE_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andValidateFlagEqualTo(String value) {
            addCriterion("VALIDATE_FLAG =", value, "validateFlag");
            return (Criteria) this;
        }

        public Criteria andValidateFlagNotEqualTo(String value) {
            addCriterion("VALIDATE_FLAG <>", value, "validateFlag");
            return (Criteria) this;
        }

        public Criteria andValidateFlagGreaterThan(String value) {
            addCriterion("VALIDATE_FLAG >", value, "validateFlag");
            return (Criteria) this;
        }

        public Criteria andValidateFlagGreaterThanOrEqualTo(String value) {
            addCriterion("VALIDATE_FLAG >=", value, "validateFlag");
            return (Criteria) this;
        }

        public Criteria andValidateFlagLessThan(String value) {
            addCriterion("VALIDATE_FLAG <", value, "validateFlag");
            return (Criteria) this;
        }

        public Criteria andValidateFlagLessThanOrEqualTo(String value) {
            addCriterion("VALIDATE_FLAG <=", value, "validateFlag");
            return (Criteria) this;
        }

        public Criteria andValidateFlagLike(String value) {
            addCriterion("VALIDATE_FLAG like", value, "validateFlag");
            return (Criteria) this;
        }

        public Criteria andValidateFlagNotLike(String value) {
            addCriterion("VALIDATE_FLAG not like", value, "validateFlag");
            return (Criteria) this;
        }

        public Criteria andValidateFlagIn(List<String> values) {
            addCriterion("VALIDATE_FLAG in", values, "validateFlag");
            return (Criteria) this;
        }

        public Criteria andValidateFlagNotIn(List<String> values) {
            addCriterion("VALIDATE_FLAG not in", values, "validateFlag");
            return (Criteria) this;
        }

        public Criteria andValidateFlagBetween(String value1, String value2) {
            addCriterion("VALIDATE_FLAG between", value1, value2, "validateFlag");
            return (Criteria) this;
        }

        public Criteria andValidateFlagNotBetween(String value1, String value2) {
            addCriterion("VALIDATE_FLAG not between", value1, value2, "validateFlag");
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