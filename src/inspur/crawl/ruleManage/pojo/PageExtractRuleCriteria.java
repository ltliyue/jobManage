package inspur.crawl.ruleManage.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PageExtractRuleCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int top = -1;

    protected int skipCount = 0;

    public PageExtractRuleCriteria() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andTaskIdIsNull() {
            addCriterion("TASK_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("TASK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("TASK_ID =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("TASK_ID <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("TASK_ID >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("TASK_ID >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("TASK_ID <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("TASK_ID <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("TASK_ID like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("TASK_ID not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("TASK_ID in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("TASK_ID not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("TASK_ID between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("TASK_ID not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andUrlPatternIsNull() {
            addCriterion("URL_PATTERN is null");
            return (Criteria) this;
        }

        public Criteria andUrlPatternIsNotNull() {
            addCriterion("URL_PATTERN is not null");
            return (Criteria) this;
        }

        public Criteria andUrlPatternEqualTo(String value) {
            addCriterion("URL_PATTERN =", value, "urlPattern");
            return (Criteria) this;
        }

        public Criteria andUrlPatternNotEqualTo(String value) {
            addCriterion("URL_PATTERN <>", value, "urlPattern");
            return (Criteria) this;
        }

        public Criteria andUrlPatternGreaterThan(String value) {
            addCriterion("URL_PATTERN >", value, "urlPattern");
            return (Criteria) this;
        }

        public Criteria andUrlPatternGreaterThanOrEqualTo(String value) {
            addCriterion("URL_PATTERN >=", value, "urlPattern");
            return (Criteria) this;
        }

        public Criteria andUrlPatternLessThan(String value) {
            addCriterion("URL_PATTERN <", value, "urlPattern");
            return (Criteria) this;
        }

        public Criteria andUrlPatternLessThanOrEqualTo(String value) {
            addCriterion("URL_PATTERN <=", value, "urlPattern");
            return (Criteria) this;
        }

        public Criteria andUrlPatternLike(String value) {
            addCriterion("URL_PATTERN like", value, "urlPattern");
            return (Criteria) this;
        }

        public Criteria andUrlPatternNotLike(String value) {
            addCriterion("URL_PATTERN not like", value, "urlPattern");
            return (Criteria) this;
        }

        public Criteria andUrlPatternIn(List<String> values) {
            addCriterion("URL_PATTERN in", values, "urlPattern");
            return (Criteria) this;
        }

        public Criteria andUrlPatternNotIn(List<String> values) {
            addCriterion("URL_PATTERN not in", values, "urlPattern");
            return (Criteria) this;
        }

        public Criteria andUrlPatternBetween(String value1, String value2) {
            addCriterion("URL_PATTERN between", value1, value2, "urlPattern");
            return (Criteria) this;
        }

        public Criteria andUrlPatternNotBetween(String value1, String value2) {
            addCriterion("URL_PATTERN not between", value1, value2, "urlPattern");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("TIME is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("TIME is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterionForJDBCDate("TIME =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("TIME <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("TIME >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("TIME >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterionForJDBCDate("TIME <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("TIME <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterionForJDBCDate("TIME in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("TIME not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("TIME between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("TIME not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNull() {
            addCriterion("ENABLED is null");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNotNull() {
            addCriterion("ENABLED is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledEqualTo(Short value) {
            addCriterion("ENABLED =", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotEqualTo(Short value) {
            addCriterion("ENABLED <>", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThan(Short value) {
            addCriterion("ENABLED >", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThanOrEqualTo(Short value) {
            addCriterion("ENABLED >=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThan(Short value) {
            addCriterion("ENABLED <", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThanOrEqualTo(Short value) {
            addCriterion("ENABLED <=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledIn(List<Short> values) {
            addCriterion("ENABLED in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotIn(List<Short> values) {
            addCriterion("ENABLED not in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledBetween(Short value1, Short value2) {
            addCriterion("ENABLED between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotBetween(Short value1, Short value2) {
            addCriterion("ENABLED not between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andContainsMultiIsNull() {
            addCriterion("CONTAINS_MULTI is null");
            return (Criteria) this;
        }

        public Criteria andContainsMultiIsNotNull() {
            addCriterion("CONTAINS_MULTI is not null");
            return (Criteria) this;
        }

        public Criteria andContainsMultiEqualTo(Short value) {
            addCriterion("CONTAINS_MULTI =", value, "containsMulti");
            return (Criteria) this;
        }

        public Criteria andContainsMultiNotEqualTo(Short value) {
            addCriterion("CONTAINS_MULTI <>", value, "containsMulti");
            return (Criteria) this;
        }

        public Criteria andContainsMultiGreaterThan(Short value) {
            addCriterion("CONTAINS_MULTI >", value, "containsMulti");
            return (Criteria) this;
        }

        public Criteria andContainsMultiGreaterThanOrEqualTo(Short value) {
            addCriterion("CONTAINS_MULTI >=", value, "containsMulti");
            return (Criteria) this;
        }

        public Criteria andContainsMultiLessThan(Short value) {
            addCriterion("CONTAINS_MULTI <", value, "containsMulti");
            return (Criteria) this;
        }

        public Criteria andContainsMultiLessThanOrEqualTo(Short value) {
            addCriterion("CONTAINS_MULTI <=", value, "containsMulti");
            return (Criteria) this;
        }

        public Criteria andContainsMultiIn(List<Short> values) {
            addCriterion("CONTAINS_MULTI in", values, "containsMulti");
            return (Criteria) this;
        }

        public Criteria andContainsMultiNotIn(List<Short> values) {
            addCriterion("CONTAINS_MULTI not in", values, "containsMulti");
            return (Criteria) this;
        }

        public Criteria andContainsMultiBetween(Short value1, Short value2) {
            addCriterion("CONTAINS_MULTI between", value1, value2, "containsMulti");
            return (Criteria) this;
        }

        public Criteria andContainsMultiNotBetween(Short value1, Short value2) {
            addCriterion("CONTAINS_MULTI not between", value1, value2, "containsMulti");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathIsNull() {
            addCriterion("MULTI_ROW_XPATH is null");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathIsNotNull() {
            addCriterion("MULTI_ROW_XPATH is not null");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathEqualTo(String value) {
            addCriterion("MULTI_ROW_XPATH =", value, "multiRowXpath");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathNotEqualTo(String value) {
            addCriterion("MULTI_ROW_XPATH <>", value, "multiRowXpath");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathGreaterThan(String value) {
            addCriterion("MULTI_ROW_XPATH >", value, "multiRowXpath");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathGreaterThanOrEqualTo(String value) {
            addCriterion("MULTI_ROW_XPATH >=", value, "multiRowXpath");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathLessThan(String value) {
            addCriterion("MULTI_ROW_XPATH <", value, "multiRowXpath");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathLessThanOrEqualTo(String value) {
            addCriterion("MULTI_ROW_XPATH <=", value, "multiRowXpath");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathLike(String value) {
            addCriterion("MULTI_ROW_XPATH like", value, "multiRowXpath");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathNotLike(String value) {
            addCriterion("MULTI_ROW_XPATH not like", value, "multiRowXpath");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathIn(List<String> values) {
            addCriterion("MULTI_ROW_XPATH in", values, "multiRowXpath");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathNotIn(List<String> values) {
            addCriterion("MULTI_ROW_XPATH not in", values, "multiRowXpath");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathBetween(String value1, String value2) {
            addCriterion("MULTI_ROW_XPATH between", value1, value2, "multiRowXpath");
            return (Criteria) this;
        }

        public Criteria andMultiRowXpathNotBetween(String value1, String value2) {
            addCriterion("MULTI_ROW_XPATH not between", value1, value2, "multiRowXpath");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("CREATOR is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("CREATOR is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("CREATOR =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("CREATOR <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("CREATOR >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("CREATOR <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("CREATOR <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("CREATOR like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("CREATOR not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("CREATOR in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("CREATOR not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("CREATOR between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("CREATOR not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andTestUrlIsNull() {
            addCriterion("TEST_URL is null");
            return (Criteria) this;
        }

        public Criteria andTestUrlIsNotNull() {
            addCriterion("TEST_URL is not null");
            return (Criteria) this;
        }

        public Criteria andTestUrlEqualTo(String value) {
            addCriterion("TEST_URL =", value, "testUrl");
            return (Criteria) this;
        }

        public Criteria andTestUrlNotEqualTo(String value) {
            addCriterion("TEST_URL <>", value, "testUrl");
            return (Criteria) this;
        }

        public Criteria andTestUrlGreaterThan(String value) {
            addCriterion("TEST_URL >", value, "testUrl");
            return (Criteria) this;
        }

        public Criteria andTestUrlGreaterThanOrEqualTo(String value) {
            addCriterion("TEST_URL >=", value, "testUrl");
            return (Criteria) this;
        }

        public Criteria andTestUrlLessThan(String value) {
            addCriterion("TEST_URL <", value, "testUrl");
            return (Criteria) this;
        }

        public Criteria andTestUrlLessThanOrEqualTo(String value) {
            addCriterion("TEST_URL <=", value, "testUrl");
            return (Criteria) this;
        }

        public Criteria andTestUrlLike(String value) {
            addCriterion("TEST_URL like", value, "testUrl");
            return (Criteria) this;
        }

        public Criteria andTestUrlNotLike(String value) {
            addCriterion("TEST_URL not like", value, "testUrl");
            return (Criteria) this;
        }

        public Criteria andTestUrlIn(List<String> values) {
            addCriterion("TEST_URL in", values, "testUrl");
            return (Criteria) this;
        }

        public Criteria andTestUrlNotIn(List<String> values) {
            addCriterion("TEST_URL not in", values, "testUrl");
            return (Criteria) this;
        }

        public Criteria andTestUrlBetween(String value1, String value2) {
            addCriterion("TEST_URL between", value1, value2, "testUrl");
            return (Criteria) this;
        }

        public Criteria andTestUrlNotBetween(String value1, String value2) {
            addCriterion("TEST_URL not between", value1, value2, "testUrl");
            return (Criteria) this;
        }

        public Criteria andExtractTypeIsNull() {
            addCriterion("EXTRACT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andExtractTypeIsNotNull() {
            addCriterion("EXTRACT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andExtractTypeEqualTo(Short value) {
            addCriterion("EXTRACT_TYPE =", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeNotEqualTo(Short value) {
            addCriterion("EXTRACT_TYPE <>", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeGreaterThan(Short value) {
            addCriterion("EXTRACT_TYPE >", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("EXTRACT_TYPE >=", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeLessThan(Short value) {
            addCriterion("EXTRACT_TYPE <", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeLessThanOrEqualTo(Short value) {
            addCriterion("EXTRACT_TYPE <=", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeIn(List<Short> values) {
            addCriterion("EXTRACT_TYPE in", values, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeNotIn(List<Short> values) {
            addCriterion("EXTRACT_TYPE not in", values, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeBetween(Short value1, Short value2) {
            addCriterion("EXTRACT_TYPE between", value1, value2, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeNotBetween(Short value1, Short value2) {
            addCriterion("EXTRACT_TYPE not between", value1, value2, "extractType");
            return (Criteria) this;
        }

        public Criteria andJarPathIsNull() {
            addCriterion("JAR_PATH is null");
            return (Criteria) this;
        }

        public Criteria andJarPathIsNotNull() {
            addCriterion("JAR_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andJarPathEqualTo(String value) {
            addCriterion("JAR_PATH =", value, "jarPath");
            return (Criteria) this;
        }

        public Criteria andJarPathNotEqualTo(String value) {
            addCriterion("JAR_PATH <>", value, "jarPath");
            return (Criteria) this;
        }

        public Criteria andJarPathGreaterThan(String value) {
            addCriterion("JAR_PATH >", value, "jarPath");
            return (Criteria) this;
        }

        public Criteria andJarPathGreaterThanOrEqualTo(String value) {
            addCriterion("JAR_PATH >=", value, "jarPath");
            return (Criteria) this;
        }

        public Criteria andJarPathLessThan(String value) {
            addCriterion("JAR_PATH <", value, "jarPath");
            return (Criteria) this;
        }

        public Criteria andJarPathLessThanOrEqualTo(String value) {
            addCriterion("JAR_PATH <=", value, "jarPath");
            return (Criteria) this;
        }

        public Criteria andJarPathLike(String value) {
            addCriterion("JAR_PATH like", value, "jarPath");
            return (Criteria) this;
        }

        public Criteria andJarPathNotLike(String value) {
            addCriterion("JAR_PATH not like", value, "jarPath");
            return (Criteria) this;
        }

        public Criteria andJarPathIn(List<String> values) {
            addCriterion("JAR_PATH in", values, "jarPath");
            return (Criteria) this;
        }

        public Criteria andJarPathNotIn(List<String> values) {
            addCriterion("JAR_PATH not in", values, "jarPath");
            return (Criteria) this;
        }

        public Criteria andJarPathBetween(String value1, String value2) {
            addCriterion("JAR_PATH between", value1, value2, "jarPath");
            return (Criteria) this;
        }

        public Criteria andJarPathNotBetween(String value1, String value2) {
            addCriterion("JAR_PATH not between", value1, value2, "jarPath");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("CLASS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("CLASS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("CLASS_NAME =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("CLASS_NAME <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("CLASS_NAME >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_NAME >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("CLASS_NAME <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("CLASS_NAME <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("CLASS_NAME like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("CLASS_NAME not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("CLASS_NAME in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("CLASS_NAME not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("CLASS_NAME between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("CLASS_NAME not between", value1, value2, "className");
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