package inspur.crawl.demandAna.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrawlerOpinionCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int top = -1;

    protected int skipCount = 0;

    public CrawlerOpinionCriteria() {
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

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andFeedbackIsNull() {
            addCriterion("FEEDBACK is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackIsNotNull() {
            addCriterion("FEEDBACK is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackEqualTo(String value) {
            addCriterion("FEEDBACK =", value, "feedback");
            return (Criteria) this;
        }

        public Criteria andFeedbackNotEqualTo(String value) {
            addCriterion("FEEDBACK <>", value, "feedback");
            return (Criteria) this;
        }

        public Criteria andFeedbackGreaterThan(String value) {
            addCriterion("FEEDBACK >", value, "feedback");
            return (Criteria) this;
        }

        public Criteria andFeedbackGreaterThanOrEqualTo(String value) {
            addCriterion("FEEDBACK >=", value, "feedback");
            return (Criteria) this;
        }

        public Criteria andFeedbackLessThan(String value) {
            addCriterion("FEEDBACK <", value, "feedback");
            return (Criteria) this;
        }

        public Criteria andFeedbackLessThanOrEqualTo(String value) {
            addCriterion("FEEDBACK <=", value, "feedback");
            return (Criteria) this;
        }

        public Criteria andFeedbackLike(String value) {
            addCriterion("FEEDBACK like", value, "feedback");
            return (Criteria) this;
        }

        public Criteria andFeedbackNotLike(String value) {
            addCriterion("FEEDBACK not like", value, "feedback");
            return (Criteria) this;
        }

        public Criteria andFeedbackIn(List<String> values) {
            addCriterion("FEEDBACK in", values, "feedback");
            return (Criteria) this;
        }

        public Criteria andFeedbackNotIn(List<String> values) {
            addCriterion("FEEDBACK not in", values, "feedback");
            return (Criteria) this;
        }

        public Criteria andFeedbackBetween(String value1, String value2) {
            addCriterion("FEEDBACK between", value1, value2, "feedback");
            return (Criteria) this;
        }

        public Criteria andFeedbackNotBetween(String value1, String value2) {
            addCriterion("FEEDBACK not between", value1, value2, "feedback");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("FILE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("FILE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("FILE_PATH =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("FILE_PATH <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("FILE_PATH >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_PATH >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("FILE_PATH <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("FILE_PATH <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("FILE_PATH like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("FILE_PATH not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("FILE_PATH in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("FILE_PATH not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("FILE_PATH between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("FILE_PATH not between", value1, value2, "filePath");
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