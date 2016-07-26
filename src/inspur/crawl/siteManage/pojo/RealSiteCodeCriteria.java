package inspur.crawl.siteManage.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RealSiteCodeCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int top = -1;

    protected int skipCount = 0;

    public RealSiteCodeCriteria() {
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

        public Criteria andSiteTypeIsNull() {
            addCriterion("SITE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSiteTypeIsNotNull() {
            addCriterion("SITE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSiteTypeEqualTo(String value) {
            addCriterion("SITE_TYPE =", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeNotEqualTo(String value) {
            addCriterion("SITE_TYPE <>", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeGreaterThan(String value) {
            addCriterion("SITE_TYPE >", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_TYPE >=", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeLessThan(String value) {
            addCriterion("SITE_TYPE <", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeLessThanOrEqualTo(String value) {
            addCriterion("SITE_TYPE <=", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeLike(String value) {
            addCriterion("SITE_TYPE like", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeNotLike(String value) {
            addCriterion("SITE_TYPE not like", value, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeIn(List<String> values) {
            addCriterion("SITE_TYPE in", values, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeNotIn(List<String> values) {
            addCriterion("SITE_TYPE not in", values, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeBetween(String value1, String value2) {
            addCriterion("SITE_TYPE between", value1, value2, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteTypeNotBetween(String value1, String value2) {
            addCriterion("SITE_TYPE not between", value1, value2, "siteType");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionIsNull() {
            addCriterion("SITE_DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionIsNotNull() {
            addCriterion("SITE_DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionEqualTo(String value) {
            addCriterion("SITE_DESCRIPTION =", value, "siteDescription");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionNotEqualTo(String value) {
            addCriterion("SITE_DESCRIPTION <>", value, "siteDescription");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionGreaterThan(String value) {
            addCriterion("SITE_DESCRIPTION >", value, "siteDescription");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("SITE_DESCRIPTION >=", value, "siteDescription");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionLessThan(String value) {
            addCriterion("SITE_DESCRIPTION <", value, "siteDescription");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionLessThanOrEqualTo(String value) {
            addCriterion("SITE_DESCRIPTION <=", value, "siteDescription");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionLike(String value) {
            addCriterion("SITE_DESCRIPTION like", value, "siteDescription");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionNotLike(String value) {
            addCriterion("SITE_DESCRIPTION not like", value, "siteDescription");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionIn(List<String> values) {
            addCriterion("SITE_DESCRIPTION in", values, "siteDescription");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionNotIn(List<String> values) {
            addCriterion("SITE_DESCRIPTION not in", values, "siteDescription");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionBetween(String value1, String value2) {
            addCriterion("SITE_DESCRIPTION between", value1, value2, "siteDescription");
            return (Criteria) this;
        }

        public Criteria andSiteDescriptionNotBetween(String value1, String value2) {
            addCriterion("SITE_DESCRIPTION not between", value1, value2, "siteDescription");
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

        public Criteria andExecutionCycleIsNull() {
            addCriterion("EXECUTION_CYCLE is null");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleIsNotNull() {
            addCriterion("EXECUTION_CYCLE is not null");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleEqualTo(String value) {
            addCriterion("EXECUTION_CYCLE =", value, "executionCycle");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleNotEqualTo(String value) {
            addCriterion("EXECUTION_CYCLE <>", value, "executionCycle");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleGreaterThan(String value) {
            addCriterion("EXECUTION_CYCLE >", value, "executionCycle");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleGreaterThanOrEqualTo(String value) {
            addCriterion("EXECUTION_CYCLE >=", value, "executionCycle");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleLessThan(String value) {
            addCriterion("EXECUTION_CYCLE <", value, "executionCycle");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleLessThanOrEqualTo(String value) {
            addCriterion("EXECUTION_CYCLE <=", value, "executionCycle");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleLike(String value) {
            addCriterion("EXECUTION_CYCLE like", value, "executionCycle");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleNotLike(String value) {
            addCriterion("EXECUTION_CYCLE not like", value, "executionCycle");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleIn(List<String> values) {
            addCriterion("EXECUTION_CYCLE in", values, "executionCycle");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleNotIn(List<String> values) {
            addCriterion("EXECUTION_CYCLE not in", values, "executionCycle");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleBetween(String value1, String value2) {
            addCriterion("EXECUTION_CYCLE between", value1, value2, "executionCycle");
            return (Criteria) this;
        }

        public Criteria andExecutionCycleNotBetween(String value1, String value2) {
            addCriterion("EXECUTION_CYCLE not between", value1, value2, "executionCycle");
            return (Criteria) this;
        }

        public Criteria andDueTimeIsNull() {
            addCriterion("DUE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDueTimeIsNotNull() {
            addCriterion("DUE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDueTimeEqualTo(String value) {
            addCriterion("DUE_TIME =", value, "dueTime");
            return (Criteria) this;
        }

        public Criteria andDueTimeNotEqualTo(String value) {
            addCriterion("DUE_TIME <>", value, "dueTime");
            return (Criteria) this;
        }

        public Criteria andDueTimeGreaterThan(String value) {
            addCriterion("DUE_TIME >", value, "dueTime");
            return (Criteria) this;
        }

        public Criteria andDueTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DUE_TIME >=", value, "dueTime");
            return (Criteria) this;
        }

        public Criteria andDueTimeLessThan(String value) {
            addCriterion("DUE_TIME <", value, "dueTime");
            return (Criteria) this;
        }

        public Criteria andDueTimeLessThanOrEqualTo(String value) {
            addCriterion("DUE_TIME <=", value, "dueTime");
            return (Criteria) this;
        }

        public Criteria andDueTimeLike(String value) {
            addCriterion("DUE_TIME like", value, "dueTime");
            return (Criteria) this;
        }

        public Criteria andDueTimeNotLike(String value) {
            addCriterion("DUE_TIME not like", value, "dueTime");
            return (Criteria) this;
        }

        public Criteria andDueTimeIn(List<String> values) {
            addCriterion("DUE_TIME in", values, "dueTime");
            return (Criteria) this;
        }

        public Criteria andDueTimeNotIn(List<String> values) {
            addCriterion("DUE_TIME not in", values, "dueTime");
            return (Criteria) this;
        }

        public Criteria andDueTimeBetween(String value1, String value2) {
            addCriterion("DUE_TIME between", value1, value2, "dueTime");
            return (Criteria) this;
        }

        public Criteria andDueTimeNotBetween(String value1, String value2) {
            addCriterion("DUE_TIME not between", value1, value2, "dueTime");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionIsNull() {
            addCriterion("OTHER_DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionIsNotNull() {
            addCriterion("OTHER_DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionEqualTo(String value) {
            addCriterion("OTHER_DESCRIPTION =", value, "otherDescription");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionNotEqualTo(String value) {
            addCriterion("OTHER_DESCRIPTION <>", value, "otherDescription");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionGreaterThan(String value) {
            addCriterion("OTHER_DESCRIPTION >", value, "otherDescription");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("OTHER_DESCRIPTION >=", value, "otherDescription");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionLessThan(String value) {
            addCriterion("OTHER_DESCRIPTION <", value, "otherDescription");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionLessThanOrEqualTo(String value) {
            addCriterion("OTHER_DESCRIPTION <=", value, "otherDescription");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionLike(String value) {
            addCriterion("OTHER_DESCRIPTION like", value, "otherDescription");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionNotLike(String value) {
            addCriterion("OTHER_DESCRIPTION not like", value, "otherDescription");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionIn(List<String> values) {
            addCriterion("OTHER_DESCRIPTION in", values, "otherDescription");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionNotIn(List<String> values) {
            addCriterion("OTHER_DESCRIPTION not in", values, "otherDescription");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionBetween(String value1, String value2) {
            addCriterion("OTHER_DESCRIPTION between", value1, value2, "otherDescription");
            return (Criteria) this;
        }

        public Criteria andOtherDescriptionNotBetween(String value1, String value2) {
            addCriterion("OTHER_DESCRIPTION not between", value1, value2, "otherDescription");
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