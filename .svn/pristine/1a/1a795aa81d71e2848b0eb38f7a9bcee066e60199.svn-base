package inspur.crawl.ruleManage.pojo;

import java.util.ArrayList;
import java.util.List;

public class ElementExtractRuleCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int top = -1;

    protected int skipCount = 0;

    public ElementExtractRuleCriteria() {
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

        public Criteria andPageExtractRuleIdIsNull() {
            addCriterion("PAGE_EXTRACT_RULE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdIsNotNull() {
            addCriterion("PAGE_EXTRACT_RULE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdEqualTo(String value) {
            addCriterion("PAGE_EXTRACT_RULE_ID =", value, "pageExtractRuleId");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdNotEqualTo(String value) {
            addCriterion("PAGE_EXTRACT_RULE_ID <>", value, "pageExtractRuleId");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdGreaterThan(String value) {
            addCriterion("PAGE_EXTRACT_RULE_ID >", value, "pageExtractRuleId");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdGreaterThanOrEqualTo(String value) {
            addCriterion("PAGE_EXTRACT_RULE_ID >=", value, "pageExtractRuleId");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdLessThan(String value) {
            addCriterion("PAGE_EXTRACT_RULE_ID <", value, "pageExtractRuleId");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdLessThanOrEqualTo(String value) {
            addCriterion("PAGE_EXTRACT_RULE_ID <=", value, "pageExtractRuleId");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdLike(String value) {
            addCriterion("PAGE_EXTRACT_RULE_ID like", value, "pageExtractRuleId");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdNotLike(String value) {
            addCriterion("PAGE_EXTRACT_RULE_ID not like", value, "pageExtractRuleId");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdIn(List<String> values) {
            addCriterion("PAGE_EXTRACT_RULE_ID in", values, "pageExtractRuleId");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdNotIn(List<String> values) {
            addCriterion("PAGE_EXTRACT_RULE_ID not in", values, "pageExtractRuleId");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdBetween(String value1, String value2) {
            addCriterion("PAGE_EXTRACT_RULE_ID between", value1, value2, "pageExtractRuleId");
            return (Criteria) this;
        }

        public Criteria andPageExtractRuleIdNotBetween(String value1, String value2) {
            addCriterion("PAGE_EXTRACT_RULE_ID not between", value1, value2, "pageExtractRuleId");
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

        public Criteria andContainHtmlIsNull() {
            addCriterion("CONTAIN_HTML is null");
            return (Criteria) this;
        }

        public Criteria andContainHtmlIsNotNull() {
            addCriterion("CONTAIN_HTML is not null");
            return (Criteria) this;
        }

        public Criteria andContainHtmlEqualTo(Short value) {
            addCriterion("CONTAIN_HTML =", value, "containHtml");
            return (Criteria) this;
        }

        public Criteria andContainHtmlNotEqualTo(Short value) {
            addCriterion("CONTAIN_HTML <>", value, "containHtml");
            return (Criteria) this;
        }

        public Criteria andContainHtmlGreaterThan(Short value) {
            addCriterion("CONTAIN_HTML >", value, "containHtml");
            return (Criteria) this;
        }

        public Criteria andContainHtmlGreaterThanOrEqualTo(Short value) {
            addCriterion("CONTAIN_HTML >=", value, "containHtml");
            return (Criteria) this;
        }

        public Criteria andContainHtmlLessThan(Short value) {
            addCriterion("CONTAIN_HTML <", value, "containHtml");
            return (Criteria) this;
        }

        public Criteria andContainHtmlLessThanOrEqualTo(Short value) {
            addCriterion("CONTAIN_HTML <=", value, "containHtml");
            return (Criteria) this;
        }

        public Criteria andContainHtmlIn(List<Short> values) {
            addCriterion("CONTAIN_HTML in", values, "containHtml");
            return (Criteria) this;
        }

        public Criteria andContainHtmlNotIn(List<Short> values) {
            addCriterion("CONTAIN_HTML not in", values, "containHtml");
            return (Criteria) this;
        }

        public Criteria andContainHtmlBetween(Short value1, Short value2) {
            addCriterion("CONTAIN_HTML between", value1, value2, "containHtml");
            return (Criteria) this;
        }

        public Criteria andContainHtmlNotBetween(Short value1, Short value2) {
            addCriterion("CONTAIN_HTML not between", value1, value2, "containHtml");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameIsNull() {
            addCriterion("STORE_COLUMN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameIsNotNull() {
            addCriterion("STORE_COLUMN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameEqualTo(String value) {
            addCriterion("STORE_COLUMN_NAME =", value, "storeColumnName");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameNotEqualTo(String value) {
            addCriterion("STORE_COLUMN_NAME <>", value, "storeColumnName");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameGreaterThan(String value) {
            addCriterion("STORE_COLUMN_NAME >", value, "storeColumnName");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameGreaterThanOrEqualTo(String value) {
            addCriterion("STORE_COLUMN_NAME >=", value, "storeColumnName");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameLessThan(String value) {
            addCriterion("STORE_COLUMN_NAME <", value, "storeColumnName");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameLessThanOrEqualTo(String value) {
            addCriterion("STORE_COLUMN_NAME <=", value, "storeColumnName");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameLike(String value) {
            addCriterion("STORE_COLUMN_NAME like", value, "storeColumnName");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameNotLike(String value) {
            addCriterion("STORE_COLUMN_NAME not like", value, "storeColumnName");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameIn(List<String> values) {
            addCriterion("STORE_COLUMN_NAME in", values, "storeColumnName");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameNotIn(List<String> values) {
            addCriterion("STORE_COLUMN_NAME not in", values, "storeColumnName");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameBetween(String value1, String value2) {
            addCriterion("STORE_COLUMN_NAME between", value1, value2, "storeColumnName");
            return (Criteria) this;
        }

        public Criteria andStoreColumnNameNotBetween(String value1, String value2) {
            addCriterion("STORE_COLUMN_NAME not between", value1, value2, "storeColumnName");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentIsNull() {
            addCriterion("STORE_COLUMN_COMMENT is null");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentIsNotNull() {
            addCriterion("STORE_COLUMN_COMMENT is not null");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentEqualTo(String value) {
            addCriterion("STORE_COLUMN_COMMENT =", value, "storeColumnComment");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentNotEqualTo(String value) {
            addCriterion("STORE_COLUMN_COMMENT <>", value, "storeColumnComment");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentGreaterThan(String value) {
            addCriterion("STORE_COLUMN_COMMENT >", value, "storeColumnComment");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentGreaterThanOrEqualTo(String value) {
            addCriterion("STORE_COLUMN_COMMENT >=", value, "storeColumnComment");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentLessThan(String value) {
            addCriterion("STORE_COLUMN_COMMENT <", value, "storeColumnComment");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentLessThanOrEqualTo(String value) {
            addCriterion("STORE_COLUMN_COMMENT <=", value, "storeColumnComment");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentLike(String value) {
            addCriterion("STORE_COLUMN_COMMENT like", value, "storeColumnComment");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentNotLike(String value) {
            addCriterion("STORE_COLUMN_COMMENT not like", value, "storeColumnComment");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentIn(List<String> values) {
            addCriterion("STORE_COLUMN_COMMENT in", values, "storeColumnComment");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentNotIn(List<String> values) {
            addCriterion("STORE_COLUMN_COMMENT not in", values, "storeColumnComment");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentBetween(String value1, String value2) {
            addCriterion("STORE_COLUMN_COMMENT between", value1, value2, "storeColumnComment");
            return (Criteria) this;
        }

        public Criteria andStoreColumnCommentNotBetween(String value1, String value2) {
            addCriterion("STORE_COLUMN_COMMENT not between", value1, value2, "storeColumnComment");
            return (Criteria) this;
        }

        public Criteria andIsKeyInTableIsNull() {
            addCriterion("IS_KEY_IN_TABLE is null");
            return (Criteria) this;
        }

        public Criteria andIsKeyInTableIsNotNull() {
            addCriterion("IS_KEY_IN_TABLE is not null");
            return (Criteria) this;
        }

        public Criteria andIsKeyInTableEqualTo(Short value) {
            addCriterion("IS_KEY_IN_TABLE =", value, "isKeyInTable");
            return (Criteria) this;
        }

        public Criteria andIsKeyInTableNotEqualTo(Short value) {
            addCriterion("IS_KEY_IN_TABLE <>", value, "isKeyInTable");
            return (Criteria) this;
        }

        public Criteria andIsKeyInTableGreaterThan(Short value) {
            addCriterion("IS_KEY_IN_TABLE >", value, "isKeyInTable");
            return (Criteria) this;
        }

        public Criteria andIsKeyInTableGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_KEY_IN_TABLE >=", value, "isKeyInTable");
            return (Criteria) this;
        }

        public Criteria andIsKeyInTableLessThan(Short value) {
            addCriterion("IS_KEY_IN_TABLE <", value, "isKeyInTable");
            return (Criteria) this;
        }

        public Criteria andIsKeyInTableLessThanOrEqualTo(Short value) {
            addCriterion("IS_KEY_IN_TABLE <=", value, "isKeyInTable");
            return (Criteria) this;
        }

        public Criteria andIsKeyInTableIn(List<Short> values) {
            addCriterion("IS_KEY_IN_TABLE in", values, "isKeyInTable");
            return (Criteria) this;
        }

        public Criteria andIsKeyInTableNotIn(List<Short> values) {
            addCriterion("IS_KEY_IN_TABLE not in", values, "isKeyInTable");
            return (Criteria) this;
        }

        public Criteria andIsKeyInTableBetween(Short value1, Short value2) {
            addCriterion("IS_KEY_IN_TABLE between", value1, value2, "isKeyInTable");
            return (Criteria) this;
        }

        public Criteria andIsKeyInTableNotBetween(Short value1, Short value2) {
            addCriterion("IS_KEY_IN_TABLE not between", value1, value2, "isKeyInTable");
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