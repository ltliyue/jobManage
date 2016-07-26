package inspur.crawl.diagnoseCrawl.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ParseRuleDiaCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int top = -1;

    protected int skipCount = 0;

    public ParseRuleDiaCriteria() {
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

        public Criteria andTaskIdEqualTo(Long value) {
            addCriterion("TASK_ID =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Long value) {
            addCriterion("TASK_ID <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Long value) {
            addCriterion("TASK_ID >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Long value) {
            addCriterion("TASK_ID >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Long value) {
            addCriterion("TASK_ID <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Long value) {
            addCriterion("TASK_ID <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Long> values) {
            addCriterion("TASK_ID in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Long> values) {
            addCriterion("TASK_ID not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Long value1, Long value2) {
            addCriterion("TASK_ID between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Long value1, Long value2) {
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

        public Criteria andExtractTypeIsNull() {
            addCriterion("EXTRACT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andExtractTypeIsNotNull() {
            addCriterion("EXTRACT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andExtractTypeEqualTo(String value) {
            addCriterion("EXTRACT_TYPE =", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeNotEqualTo(String value) {
            addCriterion("EXTRACT_TYPE <>", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeGreaterThan(String value) {
            addCriterion("EXTRACT_TYPE >", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeGreaterThanOrEqualTo(String value) {
            addCriterion("EXTRACT_TYPE >=", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeLessThan(String value) {
            addCriterion("EXTRACT_TYPE <", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeLessThanOrEqualTo(String value) {
            addCriterion("EXTRACT_TYPE <=", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeLike(String value) {
            addCriterion("EXTRACT_TYPE like", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeNotLike(String value) {
            addCriterion("EXTRACT_TYPE not like", value, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeIn(List<String> values) {
            addCriterion("EXTRACT_TYPE in", values, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeNotIn(List<String> values) {
            addCriterion("EXTRACT_TYPE not in", values, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeBetween(String value1, String value2) {
            addCriterion("EXTRACT_TYPE between", value1, value2, "extractType");
            return (Criteria) this;
        }

        public Criteria andExtractTypeNotBetween(String value1, String value2) {
            addCriterion("EXTRACT_TYPE not between", value1, value2, "extractType");
            return (Criteria) this;
        }

        public Criteria andRegexIsNull() {
            addCriterion("REGEX is null");
            return (Criteria) this;
        }

        public Criteria andRegexIsNotNull() {
            addCriterion("REGEX is not null");
            return (Criteria) this;
        }

        public Criteria andRegexEqualTo(String value) {
            addCriterion("REGEX =", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexNotEqualTo(String value) {
            addCriterion("REGEX <>", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexGreaterThan(String value) {
            addCriterion("REGEX >", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexGreaterThanOrEqualTo(String value) {
            addCriterion("REGEX >=", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexLessThan(String value) {
            addCriterion("REGEX <", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexLessThanOrEqualTo(String value) {
            addCriterion("REGEX <=", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexLike(String value) {
            addCriterion("REGEX like", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexNotLike(String value) {
            addCriterion("REGEX not like", value, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexIn(List<String> values) {
            addCriterion("REGEX in", values, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexNotIn(List<String> values) {
            addCriterion("REGEX not in", values, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexBetween(String value1, String value2) {
            addCriterion("REGEX between", value1, value2, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexNotBetween(String value1, String value2) {
            addCriterion("REGEX not between", value1, value2, "regex");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdIsNull() {
            addCriterion("REGEX_GROUP_ID is null");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdIsNotNull() {
            addCriterion("REGEX_GROUP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdEqualTo(String value) {
            addCriterion("REGEX_GROUP_ID =", value, "regexGroupId");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdNotEqualTo(String value) {
            addCriterion("REGEX_GROUP_ID <>", value, "regexGroupId");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdGreaterThan(String value) {
            addCriterion("REGEX_GROUP_ID >", value, "regexGroupId");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("REGEX_GROUP_ID >=", value, "regexGroupId");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdLessThan(String value) {
            addCriterion("REGEX_GROUP_ID <", value, "regexGroupId");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdLessThanOrEqualTo(String value) {
            addCriterion("REGEX_GROUP_ID <=", value, "regexGroupId");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdLike(String value) {
            addCriterion("REGEX_GROUP_ID like", value, "regexGroupId");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdNotLike(String value) {
            addCriterion("REGEX_GROUP_ID not like", value, "regexGroupId");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdIn(List<String> values) {
            addCriterion("REGEX_GROUP_ID in", values, "regexGroupId");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdNotIn(List<String> values) {
            addCriterion("REGEX_GROUP_ID not in", values, "regexGroupId");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdBetween(String value1, String value2) {
            addCriterion("REGEX_GROUP_ID between", value1, value2, "regexGroupId");
            return (Criteria) this;
        }

        public Criteria andRegexGroupIdNotBetween(String value1, String value2) {
            addCriterion("REGEX_GROUP_ID not between", value1, value2, "regexGroupId");
            return (Criteria) this;
        }

        public Criteria andXpathIsNull() {
            addCriterion("XPATH is null");
            return (Criteria) this;
        }

        public Criteria andXpathIsNotNull() {
            addCriterion("XPATH is not null");
            return (Criteria) this;
        }

        public Criteria andXpathEqualTo(String value) {
            addCriterion("XPATH =", value, "xpath");
            return (Criteria) this;
        }

        public Criteria andXpathNotEqualTo(String value) {
            addCriterion("XPATH <>", value, "xpath");
            return (Criteria) this;
        }

        public Criteria andXpathGreaterThan(String value) {
            addCriterion("XPATH >", value, "xpath");
            return (Criteria) this;
        }

        public Criteria andXpathGreaterThanOrEqualTo(String value) {
            addCriterion("XPATH >=", value, "xpath");
            return (Criteria) this;
        }

        public Criteria andXpathLessThan(String value) {
            addCriterion("XPATH <", value, "xpath");
            return (Criteria) this;
        }

        public Criteria andXpathLessThanOrEqualTo(String value) {
            addCriterion("XPATH <=", value, "xpath");
            return (Criteria) this;
        }

        public Criteria andXpathLike(String value) {
            addCriterion("XPATH like", value, "xpath");
            return (Criteria) this;
        }

        public Criteria andXpathNotLike(String value) {
            addCriterion("XPATH not like", value, "xpath");
            return (Criteria) this;
        }

        public Criteria andXpathIn(List<String> values) {
            addCriterion("XPATH in", values, "xpath");
            return (Criteria) this;
        }

        public Criteria andXpathNotIn(List<String> values) {
            addCriterion("XPATH not in", values, "xpath");
            return (Criteria) this;
        }

        public Criteria andXpathBetween(String value1, String value2) {
            addCriterion("XPATH between", value1, value2, "xpath");
            return (Criteria) this;
        }

        public Criteria andXpathNotBetween(String value1, String value2) {
            addCriterion("XPATH not between", value1, value2, "xpath");
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

        public Criteria andUrlWithLoopIsNull() {
            addCriterion("URL_WITH_LOOP is null");
            return (Criteria) this;
        }

        public Criteria andUrlWithLoopIsNotNull() {
            addCriterion("URL_WITH_LOOP is not null");
            return (Criteria) this;
        }

        public Criteria andUrlWithLoopEqualTo(Short value) {
            addCriterion("URL_WITH_LOOP =", value, "urlWithLoop");
            return (Criteria) this;
        }

        public Criteria andUrlWithLoopNotEqualTo(Short value) {
            addCriterion("URL_WITH_LOOP <>", value, "urlWithLoop");
            return (Criteria) this;
        }

        public Criteria andUrlWithLoopGreaterThan(Short value) {
            addCriterion("URL_WITH_LOOP >", value, "urlWithLoop");
            return (Criteria) this;
        }

        public Criteria andUrlWithLoopGreaterThanOrEqualTo(Short value) {
            addCriterion("URL_WITH_LOOP >=", value, "urlWithLoop");
            return (Criteria) this;
        }

        public Criteria andUrlWithLoopLessThan(Short value) {
            addCriterion("URL_WITH_LOOP <", value, "urlWithLoop");
            return (Criteria) this;
        }

        public Criteria andUrlWithLoopLessThanOrEqualTo(Short value) {
            addCriterion("URL_WITH_LOOP <=", value, "urlWithLoop");
            return (Criteria) this;
        }

        public Criteria andUrlWithLoopIn(List<Short> values) {
            addCriterion("URL_WITH_LOOP in", values, "urlWithLoop");
            return (Criteria) this;
        }

        public Criteria andUrlWithLoopNotIn(List<Short> values) {
            addCriterion("URL_WITH_LOOP not in", values, "urlWithLoop");
            return (Criteria) this;
        }

        public Criteria andUrlWithLoopBetween(Short value1, Short value2) {
            addCriterion("URL_WITH_LOOP between", value1, value2, "urlWithLoop");
            return (Criteria) this;
        }

        public Criteria andUrlWithLoopNotBetween(Short value1, Short value2) {
            addCriterion("URL_WITH_LOOP not between", value1, value2, "urlWithLoop");
            return (Criteria) this;
        }

        public Criteria andUrlTypeIsNull() {
            addCriterion("URL_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andUrlTypeIsNotNull() {
            addCriterion("URL_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andUrlTypeEqualTo(BigDecimal value) {
            addCriterion("URL_TYPE =", value, "urlType");
            return (Criteria) this;
        }

        public Criteria andUrlTypeNotEqualTo(BigDecimal value) {
            addCriterion("URL_TYPE <>", value, "urlType");
            return (Criteria) this;
        }

        public Criteria andUrlTypeGreaterThan(BigDecimal value) {
            addCriterion("URL_TYPE >", value, "urlType");
            return (Criteria) this;
        }

        public Criteria andUrlTypeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("URL_TYPE >=", value, "urlType");
            return (Criteria) this;
        }

        public Criteria andUrlTypeLessThan(BigDecimal value) {
            addCriterion("URL_TYPE <", value, "urlType");
            return (Criteria) this;
        }

        public Criteria andUrlTypeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("URL_TYPE <=", value, "urlType");
            return (Criteria) this;
        }

        public Criteria andUrlTypeIn(List<BigDecimal> values) {
            addCriterion("URL_TYPE in", values, "urlType");
            return (Criteria) this;
        }

        public Criteria andUrlTypeNotIn(List<BigDecimal> values) {
            addCriterion("URL_TYPE not in", values, "urlType");
            return (Criteria) this;
        }

        public Criteria andUrlTypeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("URL_TYPE between", value1, value2, "urlType");
            return (Criteria) this;
        }

        public Criteria andUrlTypeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("URL_TYPE not between", value1, value2, "urlType");
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

        public Criteria andDownloadTypeIsNull() {
            addCriterion("DOWNLOAD_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeIsNotNull() {
            addCriterion("DOWNLOAD_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeEqualTo(Short value) {
            addCriterion("DOWNLOAD_TYPE =", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeNotEqualTo(Short value) {
            addCriterion("DOWNLOAD_TYPE <>", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeGreaterThan(Short value) {
            addCriterion("DOWNLOAD_TYPE >", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("DOWNLOAD_TYPE >=", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeLessThan(Short value) {
            addCriterion("DOWNLOAD_TYPE <", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeLessThanOrEqualTo(Short value) {
            addCriterion("DOWNLOAD_TYPE <=", value, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeIn(List<Short> values) {
            addCriterion("DOWNLOAD_TYPE in", values, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeNotIn(List<Short> values) {
            addCriterion("DOWNLOAD_TYPE not in", values, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeBetween(Short value1, Short value2) {
            addCriterion("DOWNLOAD_TYPE between", value1, value2, "downloadType");
            return (Criteria) this;
        }

        public Criteria andDownloadTypeNotBetween(Short value1, Short value2) {
            addCriterion("DOWNLOAD_TYPE not between", value1, value2, "downloadType");
            return (Criteria) this;
        }

        public Criteria andScopeRegexIsNull() {
            addCriterion("SCOPE_REGEX is null");
            return (Criteria) this;
        }

        public Criteria andScopeRegexIsNotNull() {
            addCriterion("SCOPE_REGEX is not null");
            return (Criteria) this;
        }

        public Criteria andScopeRegexEqualTo(String value) {
            addCriterion("SCOPE_REGEX =", value, "scopeRegex");
            return (Criteria) this;
        }

        public Criteria andScopeRegexNotEqualTo(String value) {
            addCriterion("SCOPE_REGEX <>", value, "scopeRegex");
            return (Criteria) this;
        }

        public Criteria andScopeRegexGreaterThan(String value) {
            addCriterion("SCOPE_REGEX >", value, "scopeRegex");
            return (Criteria) this;
        }

        public Criteria andScopeRegexGreaterThanOrEqualTo(String value) {
            addCriterion("SCOPE_REGEX >=", value, "scopeRegex");
            return (Criteria) this;
        }

        public Criteria andScopeRegexLessThan(String value) {
            addCriterion("SCOPE_REGEX <", value, "scopeRegex");
            return (Criteria) this;
        }

        public Criteria andScopeRegexLessThanOrEqualTo(String value) {
            addCriterion("SCOPE_REGEX <=", value, "scopeRegex");
            return (Criteria) this;
        }

        public Criteria andScopeRegexLike(String value) {
            addCriterion("SCOPE_REGEX like", value, "scopeRegex");
            return (Criteria) this;
        }

        public Criteria andScopeRegexNotLike(String value) {
            addCriterion("SCOPE_REGEX not like", value, "scopeRegex");
            return (Criteria) this;
        }

        public Criteria andScopeRegexIn(List<String> values) {
            addCriterion("SCOPE_REGEX in", values, "scopeRegex");
            return (Criteria) this;
        }

        public Criteria andScopeRegexNotIn(List<String> values) {
            addCriterion("SCOPE_REGEX not in", values, "scopeRegex");
            return (Criteria) this;
        }

        public Criteria andScopeRegexBetween(String value1, String value2) {
            addCriterion("SCOPE_REGEX between", value1, value2, "scopeRegex");
            return (Criteria) this;
        }

        public Criteria andScopeRegexNotBetween(String value1, String value2) {
            addCriterion("SCOPE_REGEX not between", value1, value2, "scopeRegex");
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

        public Criteria andDownloadToolIsNull() {
            addCriterion("DOWNLOAD_TOOL is null");
            return (Criteria) this;
        }

        public Criteria andDownloadToolIsNotNull() {
            addCriterion("DOWNLOAD_TOOL is not null");
            return (Criteria) this;
        }

        public Criteria andDownloadToolEqualTo(Short value) {
            addCriterion("DOWNLOAD_TOOL =", value, "downloadTool");
            return (Criteria) this;
        }

        public Criteria andDownloadToolNotEqualTo(Short value) {
            addCriterion("DOWNLOAD_TOOL <>", value, "downloadTool");
            return (Criteria) this;
        }

        public Criteria andDownloadToolGreaterThan(Short value) {
            addCriterion("DOWNLOAD_TOOL >", value, "downloadTool");
            return (Criteria) this;
        }

        public Criteria andDownloadToolGreaterThanOrEqualTo(Short value) {
            addCriterion("DOWNLOAD_TOOL >=", value, "downloadTool");
            return (Criteria) this;
        }

        public Criteria andDownloadToolLessThan(Short value) {
            addCriterion("DOWNLOAD_TOOL <", value, "downloadTool");
            return (Criteria) this;
        }

        public Criteria andDownloadToolLessThanOrEqualTo(Short value) {
            addCriterion("DOWNLOAD_TOOL <=", value, "downloadTool");
            return (Criteria) this;
        }

        public Criteria andDownloadToolIn(List<Short> values) {
            addCriterion("DOWNLOAD_TOOL in", values, "downloadTool");
            return (Criteria) this;
        }

        public Criteria andDownloadToolNotIn(List<Short> values) {
            addCriterion("DOWNLOAD_TOOL not in", values, "downloadTool");
            return (Criteria) this;
        }

        public Criteria andDownloadToolBetween(Short value1, Short value2) {
            addCriterion("DOWNLOAD_TOOL between", value1, value2, "downloadTool");
            return (Criteria) this;
        }

        public Criteria andDownloadToolNotBetween(Short value1, Short value2) {
            addCriterion("DOWNLOAD_TOOL not between", value1, value2, "downloadTool");
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