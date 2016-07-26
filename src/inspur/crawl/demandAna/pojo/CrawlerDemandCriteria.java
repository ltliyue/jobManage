package inspur.crawl.demandAna.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrawlerDemandCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int top = -1;

    protected int skipCount = 0;

    public CrawlerDemandCriteria() {
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

        public Criteria andDemandNameIsNull() {
            addCriterion("DEMAND_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDemandNameIsNotNull() {
            addCriterion("DEMAND_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDemandNameEqualTo(String value) {
            addCriterion("DEMAND_NAME =", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameNotEqualTo(String value) {
            addCriterion("DEMAND_NAME <>", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameGreaterThan(String value) {
            addCriterion("DEMAND_NAME >", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEMAND_NAME >=", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameLessThan(String value) {
            addCriterion("DEMAND_NAME <", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameLessThanOrEqualTo(String value) {
            addCriterion("DEMAND_NAME <=", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameLike(String value) {
            addCriterion("DEMAND_NAME like", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameNotLike(String value) {
            addCriterion("DEMAND_NAME not like", value, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameIn(List<String> values) {
            addCriterion("DEMAND_NAME in", values, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameNotIn(List<String> values) {
            addCriterion("DEMAND_NAME not in", values, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameBetween(String value1, String value2) {
            addCriterion("DEMAND_NAME between", value1, value2, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandNameNotBetween(String value1, String value2) {
            addCriterion("DEMAND_NAME not between", value1, value2, "demandName");
            return (Criteria) this;
        }

        public Criteria andDemandDetailIsNull() {
            addCriterion("DEMAND_DETAIL is null");
            return (Criteria) this;
        }

        public Criteria andDemandDetailIsNotNull() {
            addCriterion("DEMAND_DETAIL is not null");
            return (Criteria) this;
        }

        public Criteria andDemandDetailEqualTo(String value) {
            addCriterion("DEMAND_DETAIL =", value, "demandDetail");
            return (Criteria) this;
        }

        public Criteria andDemandDetailNotEqualTo(String value) {
            addCriterion("DEMAND_DETAIL <>", value, "demandDetail");
            return (Criteria) this;
        }

        public Criteria andDemandDetailGreaterThan(String value) {
            addCriterion("DEMAND_DETAIL >", value, "demandDetail");
            return (Criteria) this;
        }

        public Criteria andDemandDetailGreaterThanOrEqualTo(String value) {
            addCriterion("DEMAND_DETAIL >=", value, "demandDetail");
            return (Criteria) this;
        }

        public Criteria andDemandDetailLessThan(String value) {
            addCriterion("DEMAND_DETAIL <", value, "demandDetail");
            return (Criteria) this;
        }

        public Criteria andDemandDetailLessThanOrEqualTo(String value) {
            addCriterion("DEMAND_DETAIL <=", value, "demandDetail");
            return (Criteria) this;
        }

        public Criteria andDemandDetailLike(String value) {
            addCriterion("DEMAND_DETAIL like", value, "demandDetail");
            return (Criteria) this;
        }

        public Criteria andDemandDetailNotLike(String value) {
            addCriterion("DEMAND_DETAIL not like", value, "demandDetail");
            return (Criteria) this;
        }

        public Criteria andDemandDetailIn(List<String> values) {
            addCriterion("DEMAND_DETAIL in", values, "demandDetail");
            return (Criteria) this;
        }

        public Criteria andDemandDetailNotIn(List<String> values) {
            addCriterion("DEMAND_DETAIL not in", values, "demandDetail");
            return (Criteria) this;
        }

        public Criteria andDemandDetailBetween(String value1, String value2) {
            addCriterion("DEMAND_DETAIL between", value1, value2, "demandDetail");
            return (Criteria) this;
        }

        public Criteria andDemandDetailNotBetween(String value1, String value2) {
            addCriterion("DEMAND_DETAIL not between", value1, value2, "demandDetail");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIsNull() {
            addCriterion("DEMAND_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIsNotNull() {
            addCriterion("DEMAND_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andDemandStatusEqualTo(String value) {
            addCriterion("DEMAND_STATUS =", value, "demandStatus");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNotEqualTo(String value) {
            addCriterion("DEMAND_STATUS <>", value, "demandStatus");
            return (Criteria) this;
        }

        public Criteria andDemandStatusGreaterThan(String value) {
            addCriterion("DEMAND_STATUS >", value, "demandStatus");
            return (Criteria) this;
        }

        public Criteria andDemandStatusGreaterThanOrEqualTo(String value) {
            addCriterion("DEMAND_STATUS >=", value, "demandStatus");
            return (Criteria) this;
        }

        public Criteria andDemandStatusLessThan(String value) {
            addCriterion("DEMAND_STATUS <", value, "demandStatus");
            return (Criteria) this;
        }

        public Criteria andDemandStatusLessThanOrEqualTo(String value) {
            addCriterion("DEMAND_STATUS <=", value, "demandStatus");
            return (Criteria) this;
        }

        public Criteria andDemandStatusLike(String value) {
            addCriterion("DEMAND_STATUS like", value, "demandStatus");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNotLike(String value) {
            addCriterion("DEMAND_STATUS not like", value, "demandStatus");
            return (Criteria) this;
        }

        public Criteria andDemandStatusIn(List<String> values) {
            addCriterion("DEMAND_STATUS in", values, "demandStatus");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNotIn(List<String> values) {
            addCriterion("DEMAND_STATUS not in", values, "demandStatus");
            return (Criteria) this;
        }

        public Criteria andDemandStatusBetween(String value1, String value2) {
            addCriterion("DEMAND_STATUS between", value1, value2, "demandStatus");
            return (Criteria) this;
        }

        public Criteria andDemandStatusNotBetween(String value1, String value2) {
            addCriterion("DEMAND_STATUS not between", value1, value2, "demandStatus");
            return (Criteria) this;
        }

        public Criteria andDemandTimeIsNull() {
            addCriterion("DEMAND_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDemandTimeIsNotNull() {
            addCriterion("DEMAND_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDemandTimeEqualTo(Date value) {
            addCriterion("DEMAND_TIME =", value, "demandTime");
            return (Criteria) this;
        }

        public Criteria andDemandTimeNotEqualTo(Date value) {
            addCriterion("DEMAND_TIME <>", value, "demandTime");
            return (Criteria) this;
        }

        public Criteria andDemandTimeGreaterThan(Date value) {
            addCriterion("DEMAND_TIME >", value, "demandTime");
            return (Criteria) this;
        }

        public Criteria andDemandTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DEMAND_TIME >=", value, "demandTime");
            return (Criteria) this;
        }

        public Criteria andDemandTimeLessThan(Date value) {
            addCriterion("DEMAND_TIME <", value, "demandTime");
            return (Criteria) this;
        }

        public Criteria andDemandTimeLessThanOrEqualTo(Date value) {
            addCriterion("DEMAND_TIME <=", value, "demandTime");
            return (Criteria) this;
        }

        public Criteria andDemandTimeIn(List<Date> values) {
            addCriterion("DEMAND_TIME in", values, "demandTime");
            return (Criteria) this;
        }

        public Criteria andDemandTimeNotIn(List<Date> values) {
            addCriterion("DEMAND_TIME not in", values, "demandTime");
            return (Criteria) this;
        }

        public Criteria andDemandTimeBetween(Date value1, Date value2) {
            addCriterion("DEMAND_TIME between", value1, value2, "demandTime");
            return (Criteria) this;
        }

        public Criteria andDemandTimeNotBetween(Date value1, Date value2) {
            addCriterion("DEMAND_TIME not between", value1, value2, "demandTime");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterIsNull() {
            addCriterion("DEMAND_CREATER is null");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterIsNotNull() {
            addCriterion("DEMAND_CREATER is not null");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterEqualTo(String value) {
            addCriterion("DEMAND_CREATER =", value, "demandCreater");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterNotEqualTo(String value) {
            addCriterion("DEMAND_CREATER <>", value, "demandCreater");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterGreaterThan(String value) {
            addCriterion("DEMAND_CREATER >", value, "demandCreater");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("DEMAND_CREATER >=", value, "demandCreater");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterLessThan(String value) {
            addCriterion("DEMAND_CREATER <", value, "demandCreater");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterLessThanOrEqualTo(String value) {
            addCriterion("DEMAND_CREATER <=", value, "demandCreater");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterLike(String value) {
            addCriterion("DEMAND_CREATER like", value, "demandCreater");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterNotLike(String value) {
            addCriterion("DEMAND_CREATER not like", value, "demandCreater");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterIn(List<String> values) {
            addCriterion("DEMAND_CREATER in", values, "demandCreater");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterNotIn(List<String> values) {
            addCriterion("DEMAND_CREATER not in", values, "demandCreater");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterBetween(String value1, String value2) {
            addCriterion("DEMAND_CREATER between", value1, value2, "demandCreater");
            return (Criteria) this;
        }

        public Criteria andDemandCreaterNotBetween(String value1, String value2) {
            addCriterion("DEMAND_CREATER not between", value1, value2, "demandCreater");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathIsNull() {
            addCriterion("DEMAND_FILE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathIsNotNull() {
            addCriterion("DEMAND_FILE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathEqualTo(String value) {
            addCriterion("DEMAND_FILE_PATH =", value, "demandFilePath");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathNotEqualTo(String value) {
            addCriterion("DEMAND_FILE_PATH <>", value, "demandFilePath");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathGreaterThan(String value) {
            addCriterion("DEMAND_FILE_PATH >", value, "demandFilePath");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("DEMAND_FILE_PATH >=", value, "demandFilePath");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathLessThan(String value) {
            addCriterion("DEMAND_FILE_PATH <", value, "demandFilePath");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathLessThanOrEqualTo(String value) {
            addCriterion("DEMAND_FILE_PATH <=", value, "demandFilePath");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathLike(String value) {
            addCriterion("DEMAND_FILE_PATH like", value, "demandFilePath");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathNotLike(String value) {
            addCriterion("DEMAND_FILE_PATH not like", value, "demandFilePath");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathIn(List<String> values) {
            addCriterion("DEMAND_FILE_PATH in", values, "demandFilePath");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathNotIn(List<String> values) {
            addCriterion("DEMAND_FILE_PATH not in", values, "demandFilePath");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathBetween(String value1, String value2) {
            addCriterion("DEMAND_FILE_PATH between", value1, value2, "demandFilePath");
            return (Criteria) this;
        }

        public Criteria andDemandFilePathNotBetween(String value1, String value2) {
            addCriterion("DEMAND_FILE_PATH not between", value1, value2, "demandFilePath");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackIsNull() {
            addCriterion("DATA_FEEDBACK is null");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackIsNotNull() {
            addCriterion("DATA_FEEDBACK is not null");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackEqualTo(String value) {
            addCriterion("DATA_FEEDBACK =", value, "dataFeedback");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackNotEqualTo(String value) {
            addCriterion("DATA_FEEDBACK <>", value, "dataFeedback");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackGreaterThan(String value) {
            addCriterion("DATA_FEEDBACK >", value, "dataFeedback");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_FEEDBACK >=", value, "dataFeedback");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackLessThan(String value) {
            addCriterion("DATA_FEEDBACK <", value, "dataFeedback");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackLessThanOrEqualTo(String value) {
            addCriterion("DATA_FEEDBACK <=", value, "dataFeedback");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackLike(String value) {
            addCriterion("DATA_FEEDBACK like", value, "dataFeedback");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackNotLike(String value) {
            addCriterion("DATA_FEEDBACK not like", value, "dataFeedback");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackIn(List<String> values) {
            addCriterion("DATA_FEEDBACK in", values, "dataFeedback");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackNotIn(List<String> values) {
            addCriterion("DATA_FEEDBACK not in", values, "dataFeedback");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackBetween(String value1, String value2) {
            addCriterion("DATA_FEEDBACK between", value1, value2, "dataFeedback");
            return (Criteria) this;
        }

        public Criteria andDataFeedbackNotBetween(String value1, String value2) {
            addCriterion("DATA_FEEDBACK not between", value1, value2, "dataFeedback");
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