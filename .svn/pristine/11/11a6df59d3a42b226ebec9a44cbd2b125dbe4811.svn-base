package inspur.crawl.ruleManage.pojo;

import java.util.ArrayList;
import java.util.List;

public class LoopParseRuleCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int top = -1;

    protected int skipCount = 0;

    public LoopParseRuleCriteria() {
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

        public Criteria andParseRuleIdIsNull() {
            addCriterion("PARSE_RULE_ID is null");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdIsNotNull() {
            addCriterion("PARSE_RULE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdEqualTo(String value) {
            addCriterion("PARSE_RULE_ID =", value, "parseRuleId");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdNotEqualTo(String value) {
            addCriterion("PARSE_RULE_ID <>", value, "parseRuleId");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdGreaterThan(String value) {
            addCriterion("PARSE_RULE_ID >", value, "parseRuleId");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARSE_RULE_ID >=", value, "parseRuleId");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdLessThan(String value) {
            addCriterion("PARSE_RULE_ID <", value, "parseRuleId");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdLessThanOrEqualTo(String value) {
            addCriterion("PARSE_RULE_ID <=", value, "parseRuleId");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdLike(String value) {
            addCriterion("PARSE_RULE_ID like", value, "parseRuleId");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdNotLike(String value) {
            addCriterion("PARSE_RULE_ID not like", value, "parseRuleId");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdIn(List<String> values) {
            addCriterion("PARSE_RULE_ID in", values, "parseRuleId");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdNotIn(List<String> values) {
            addCriterion("PARSE_RULE_ID not in", values, "parseRuleId");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdBetween(String value1, String value2) {
            addCriterion("PARSE_RULE_ID between", value1, value2, "parseRuleId");
            return (Criteria) this;
        }

        public Criteria andParseRuleIdNotBetween(String value1, String value2) {
            addCriterion("PARSE_RULE_ID not between", value1, value2, "parseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopSampleIsNull() {
            addCriterion("LOOP_SAMPLE is null");
            return (Criteria) this;
        }

        public Criteria andLoopSampleIsNotNull() {
            addCriterion("LOOP_SAMPLE is not null");
            return (Criteria) this;
        }

        public Criteria andLoopSampleEqualTo(String value) {
            addCriterion("LOOP_SAMPLE =", value, "loopSample");
            return (Criteria) this;
        }

        public Criteria andLoopSampleNotEqualTo(String value) {
            addCriterion("LOOP_SAMPLE <>", value, "loopSample");
            return (Criteria) this;
        }

        public Criteria andLoopSampleGreaterThan(String value) {
            addCriterion("LOOP_SAMPLE >", value, "loopSample");
            return (Criteria) this;
        }

        public Criteria andLoopSampleGreaterThanOrEqualTo(String value) {
            addCriterion("LOOP_SAMPLE >=", value, "loopSample");
            return (Criteria) this;
        }

        public Criteria andLoopSampleLessThan(String value) {
            addCriterion("LOOP_SAMPLE <", value, "loopSample");
            return (Criteria) this;
        }

        public Criteria andLoopSampleLessThanOrEqualTo(String value) {
            addCriterion("LOOP_SAMPLE <=", value, "loopSample");
            return (Criteria) this;
        }

        public Criteria andLoopSampleLike(String value) {
            addCriterion("LOOP_SAMPLE like", value, "loopSample");
            return (Criteria) this;
        }

        public Criteria andLoopSampleNotLike(String value) {
            addCriterion("LOOP_SAMPLE not like", value, "loopSample");
            return (Criteria) this;
        }

        public Criteria andLoopSampleIn(List<String> values) {
            addCriterion("LOOP_SAMPLE in", values, "loopSample");
            return (Criteria) this;
        }

        public Criteria andLoopSampleNotIn(List<String> values) {
            addCriterion("LOOP_SAMPLE not in", values, "loopSample");
            return (Criteria) this;
        }

        public Criteria andLoopSampleBetween(String value1, String value2) {
            addCriterion("LOOP_SAMPLE between", value1, value2, "loopSample");
            return (Criteria) this;
        }

        public Criteria andLoopSampleNotBetween(String value1, String value2) {
            addCriterion("LOOP_SAMPLE not between", value1, value2, "loopSample");
            return (Criteria) this;
        }

        public Criteria andLoopVarsIsNull() {
            addCriterion("LOOP_VARS is null");
            return (Criteria) this;
        }

        public Criteria andLoopVarsIsNotNull() {
            addCriterion("LOOP_VARS is not null");
            return (Criteria) this;
        }

        public Criteria andLoopVarsEqualTo(String value) {
            addCriterion("LOOP_VARS =", value, "loopVars");
            return (Criteria) this;
        }

        public Criteria andLoopVarsNotEqualTo(String value) {
            addCriterion("LOOP_VARS <>", value, "loopVars");
            return (Criteria) this;
        }

        public Criteria andLoopVarsGreaterThan(String value) {
            addCriterion("LOOP_VARS >", value, "loopVars");
            return (Criteria) this;
        }

        public Criteria andLoopVarsGreaterThanOrEqualTo(String value) {
            addCriterion("LOOP_VARS >=", value, "loopVars");
            return (Criteria) this;
        }

        public Criteria andLoopVarsLessThan(String value) {
            addCriterion("LOOP_VARS <", value, "loopVars");
            return (Criteria) this;
        }

        public Criteria andLoopVarsLessThanOrEqualTo(String value) {
            addCriterion("LOOP_VARS <=", value, "loopVars");
            return (Criteria) this;
        }

        public Criteria andLoopVarsLike(String value) {
            addCriterion("LOOP_VARS like", value, "loopVars");
            return (Criteria) this;
        }

        public Criteria andLoopVarsNotLike(String value) {
            addCriterion("LOOP_VARS not like", value, "loopVars");
            return (Criteria) this;
        }

        public Criteria andLoopVarsIn(List<String> values) {
            addCriterion("LOOP_VARS in", values, "loopVars");
            return (Criteria) this;
        }

        public Criteria andLoopVarsNotIn(List<String> values) {
            addCriterion("LOOP_VARS not in", values, "loopVars");
            return (Criteria) this;
        }

        public Criteria andLoopVarsBetween(String value1, String value2) {
            addCriterion("LOOP_VARS between", value1, value2, "loopVars");
            return (Criteria) this;
        }

        public Criteria andLoopVarsNotBetween(String value1, String value2) {
            addCriterion("LOOP_VARS not between", value1, value2, "loopVars");
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