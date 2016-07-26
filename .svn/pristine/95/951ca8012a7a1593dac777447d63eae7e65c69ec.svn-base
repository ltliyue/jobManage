package inspur.crawl.ruleManage.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LoopVarRuleCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int top = -1;

    protected int skipCount = 0;

    public LoopVarRuleCriteria() {
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

        public Criteria andLoopParseRuleIdIsNull() {
            addCriterion("LOOP_PARSE_RULE_ID is null");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdIsNotNull() {
            addCriterion("LOOP_PARSE_RULE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdEqualTo(String value) {
            addCriterion("LOOP_PARSE_RULE_ID =", value, "loopParseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdNotEqualTo(String value) {
            addCriterion("LOOP_PARSE_RULE_ID <>", value, "loopParseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdGreaterThan(String value) {
            addCriterion("LOOP_PARSE_RULE_ID >", value, "loopParseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOOP_PARSE_RULE_ID >=", value, "loopParseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdLessThan(String value) {
            addCriterion("LOOP_PARSE_RULE_ID <", value, "loopParseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdLessThanOrEqualTo(String value) {
            addCriterion("LOOP_PARSE_RULE_ID <=", value, "loopParseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdLike(String value) {
            addCriterion("LOOP_PARSE_RULE_ID like", value, "loopParseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdNotLike(String value) {
            addCriterion("LOOP_PARSE_RULE_ID not like", value, "loopParseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdIn(List<String> values) {
            addCriterion("LOOP_PARSE_RULE_ID in", values, "loopParseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdNotIn(List<String> values) {
            addCriterion("LOOP_PARSE_RULE_ID not in", values, "loopParseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdBetween(String value1, String value2) {
            addCriterion("LOOP_PARSE_RULE_ID between", value1, value2, "loopParseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopParseRuleIdNotBetween(String value1, String value2) {
            addCriterion("LOOP_PARSE_RULE_ID not between", value1, value2, "loopParseRuleId");
            return (Criteria) this;
        }

        public Criteria andLoopStartIsNull() {
            addCriterion("LOOP_START is null");
            return (Criteria) this;
        }

        public Criteria andLoopStartIsNotNull() {
            addCriterion("LOOP_START is not null");
            return (Criteria) this;
        }

        public Criteria andLoopStartEqualTo(String value) {
            addCriterion("LOOP_START =", value, "loopStart");
            return (Criteria) this;
        }

        public Criteria andLoopStartNotEqualTo(String value) {
            addCriterion("LOOP_START <>", value, "loopStart");
            return (Criteria) this;
        }

        public Criteria andLoopStartGreaterThan(String value) {
            addCriterion("LOOP_START >", value, "loopStart");
            return (Criteria) this;
        }

        public Criteria andLoopStartGreaterThanOrEqualTo(String value) {
            addCriterion("LOOP_START >=", value, "loopStart");
            return (Criteria) this;
        }

        public Criteria andLoopStartLessThan(String value) {
            addCriterion("LOOP_START <", value, "loopStart");
            return (Criteria) this;
        }

        public Criteria andLoopStartLessThanOrEqualTo(String value) {
            addCriterion("LOOP_START <=", value, "loopStart");
            return (Criteria) this;
        }

        public Criteria andLoopStartLike(String value) {
            addCriterion("LOOP_START like", value, "loopStart");
            return (Criteria) this;
        }

        public Criteria andLoopStartNotLike(String value) {
            addCriterion("LOOP_START not like", value, "loopStart");
            return (Criteria) this;
        }

        public Criteria andLoopStartIn(List<String> values) {
            addCriterion("LOOP_START in", values, "loopStart");
            return (Criteria) this;
        }

        public Criteria andLoopStartNotIn(List<String> values) {
            addCriterion("LOOP_START not in", values, "loopStart");
            return (Criteria) this;
        }

        public Criteria andLoopStartBetween(String value1, String value2) {
            addCriterion("LOOP_START between", value1, value2, "loopStart");
            return (Criteria) this;
        }

        public Criteria andLoopStartNotBetween(String value1, String value2) {
            addCriterion("LOOP_START not between", value1, value2, "loopStart");
            return (Criteria) this;
        }

        public Criteria andLoopVarTypeIsNull() {
            addCriterion("LOOP_VAR_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLoopVarTypeIsNotNull() {
            addCriterion("LOOP_VAR_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLoopVarTypeEqualTo(BigDecimal value) {
            addCriterion("LOOP_VAR_TYPE =", value, "loopVarType");
            return (Criteria) this;
        }

        public Criteria andLoopVarTypeNotEqualTo(BigDecimal value) {
            addCriterion("LOOP_VAR_TYPE <>", value, "loopVarType");
            return (Criteria) this;
        }

        public Criteria andLoopVarTypeGreaterThan(BigDecimal value) {
            addCriterion("LOOP_VAR_TYPE >", value, "loopVarType");
            return (Criteria) this;
        }

        public Criteria andLoopVarTypeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LOOP_VAR_TYPE >=", value, "loopVarType");
            return (Criteria) this;
        }

        public Criteria andLoopVarTypeLessThan(BigDecimal value) {
            addCriterion("LOOP_VAR_TYPE <", value, "loopVarType");
            return (Criteria) this;
        }

        public Criteria andLoopVarTypeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LOOP_VAR_TYPE <=", value, "loopVarType");
            return (Criteria) this;
        }

        public Criteria andLoopVarTypeIn(List<BigDecimal> values) {
            addCriterion("LOOP_VAR_TYPE in", values, "loopVarType");
            return (Criteria) this;
        }

        public Criteria andLoopVarTypeNotIn(List<BigDecimal> values) {
            addCriterion("LOOP_VAR_TYPE not in", values, "loopVarType");
            return (Criteria) this;
        }

        public Criteria andLoopVarTypeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOOP_VAR_TYPE between", value1, value2, "loopVarType");
            return (Criteria) this;
        }

        public Criteria andLoopVarTypeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOOP_VAR_TYPE not between", value1, value2, "loopVarType");
            return (Criteria) this;
        }

        public Criteria andLoopEndIsNull() {
            addCriterion("LOOP_END is null");
            return (Criteria) this;
        }

        public Criteria andLoopEndIsNotNull() {
            addCriterion("LOOP_END is not null");
            return (Criteria) this;
        }

        public Criteria andLoopEndEqualTo(String value) {
            addCriterion("LOOP_END =", value, "loopEnd");
            return (Criteria) this;
        }

        public Criteria andLoopEndNotEqualTo(String value) {
            addCriterion("LOOP_END <>", value, "loopEnd");
            return (Criteria) this;
        }

        public Criteria andLoopEndGreaterThan(String value) {
            addCriterion("LOOP_END >", value, "loopEnd");
            return (Criteria) this;
        }

        public Criteria andLoopEndGreaterThanOrEqualTo(String value) {
            addCriterion("LOOP_END >=", value, "loopEnd");
            return (Criteria) this;
        }

        public Criteria andLoopEndLessThan(String value) {
            addCriterion("LOOP_END <", value, "loopEnd");
            return (Criteria) this;
        }

        public Criteria andLoopEndLessThanOrEqualTo(String value) {
            addCriterion("LOOP_END <=", value, "loopEnd");
            return (Criteria) this;
        }

        public Criteria andLoopEndLike(String value) {
            addCriterion("LOOP_END like", value, "loopEnd");
            return (Criteria) this;
        }

        public Criteria andLoopEndNotLike(String value) {
            addCriterion("LOOP_END not like", value, "loopEnd");
            return (Criteria) this;
        }

        public Criteria andLoopEndIn(List<String> values) {
            addCriterion("LOOP_END in", values, "loopEnd");
            return (Criteria) this;
        }

        public Criteria andLoopEndNotIn(List<String> values) {
            addCriterion("LOOP_END not in", values, "loopEnd");
            return (Criteria) this;
        }

        public Criteria andLoopEndBetween(String value1, String value2) {
            addCriterion("LOOP_END between", value1, value2, "loopEnd");
            return (Criteria) this;
        }

        public Criteria andLoopEndNotBetween(String value1, String value2) {
            addCriterion("LOOP_END not between", value1, value2, "loopEnd");
            return (Criteria) this;
        }

        public Criteria andLoopStepIsNull() {
            addCriterion("LOOP_STEP is null");
            return (Criteria) this;
        }

        public Criteria andLoopStepIsNotNull() {
            addCriterion("LOOP_STEP is not null");
            return (Criteria) this;
        }

        public Criteria andLoopStepEqualTo(BigDecimal value) {
            addCriterion("LOOP_STEP =", value, "loopStep");
            return (Criteria) this;
        }

        public Criteria andLoopStepNotEqualTo(BigDecimal value) {
            addCriterion("LOOP_STEP <>", value, "loopStep");
            return (Criteria) this;
        }

        public Criteria andLoopStepGreaterThan(BigDecimal value) {
            addCriterion("LOOP_STEP >", value, "loopStep");
            return (Criteria) this;
        }

        public Criteria andLoopStepGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LOOP_STEP >=", value, "loopStep");
            return (Criteria) this;
        }

        public Criteria andLoopStepLessThan(BigDecimal value) {
            addCriterion("LOOP_STEP <", value, "loopStep");
            return (Criteria) this;
        }

        public Criteria andLoopStepLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LOOP_STEP <=", value, "loopStep");
            return (Criteria) this;
        }

        public Criteria andLoopStepIn(List<BigDecimal> values) {
            addCriterion("LOOP_STEP in", values, "loopStep");
            return (Criteria) this;
        }

        public Criteria andLoopStepNotIn(List<BigDecimal> values) {
            addCriterion("LOOP_STEP not in", values, "loopStep");
            return (Criteria) this;
        }

        public Criteria andLoopStepBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOOP_STEP between", value1, value2, "loopStep");
            return (Criteria) this;
        }

        public Criteria andLoopStepNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LOOP_STEP not between", value1, value2, "loopStep");
            return (Criteria) this;
        }

        public Criteria andLoopFormatIsNull() {
            addCriterion("LOOP_FORMAT is null");
            return (Criteria) this;
        }

        public Criteria andLoopFormatIsNotNull() {
            addCriterion("LOOP_FORMAT is not null");
            return (Criteria) this;
        }

        public Criteria andLoopFormatEqualTo(String value) {
            addCriterion("LOOP_FORMAT =", value, "loopFormat");
            return (Criteria) this;
        }

        public Criteria andLoopFormatNotEqualTo(String value) {
            addCriterion("LOOP_FORMAT <>", value, "loopFormat");
            return (Criteria) this;
        }

        public Criteria andLoopFormatGreaterThan(String value) {
            addCriterion("LOOP_FORMAT >", value, "loopFormat");
            return (Criteria) this;
        }

        public Criteria andLoopFormatGreaterThanOrEqualTo(String value) {
            addCriterion("LOOP_FORMAT >=", value, "loopFormat");
            return (Criteria) this;
        }

        public Criteria andLoopFormatLessThan(String value) {
            addCriterion("LOOP_FORMAT <", value, "loopFormat");
            return (Criteria) this;
        }

        public Criteria andLoopFormatLessThanOrEqualTo(String value) {
            addCriterion("LOOP_FORMAT <=", value, "loopFormat");
            return (Criteria) this;
        }

        public Criteria andLoopFormatLike(String value) {
            addCriterion("LOOP_FORMAT like", value, "loopFormat");
            return (Criteria) this;
        }

        public Criteria andLoopFormatNotLike(String value) {
            addCriterion("LOOP_FORMAT not like", value, "loopFormat");
            return (Criteria) this;
        }

        public Criteria andLoopFormatIn(List<String> values) {
            addCriterion("LOOP_FORMAT in", values, "loopFormat");
            return (Criteria) this;
        }

        public Criteria andLoopFormatNotIn(List<String> values) {
            addCriterion("LOOP_FORMAT not in", values, "loopFormat");
            return (Criteria) this;
        }

        public Criteria andLoopFormatBetween(String value1, String value2) {
            addCriterion("LOOP_FORMAT between", value1, value2, "loopFormat");
            return (Criteria) this;
        }

        public Criteria andLoopFormatNotBetween(String value1, String value2) {
            addCriterion("LOOP_FORMAT not between", value1, value2, "loopFormat");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeIsNull() {
            addCriterion("LOOP_STR_ENCODE is null");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeIsNotNull() {
            addCriterion("LOOP_STR_ENCODE is not null");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeEqualTo(String value) {
            addCriterion("LOOP_STR_ENCODE =", value, "loopStrEncode");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeNotEqualTo(String value) {
            addCriterion("LOOP_STR_ENCODE <>", value, "loopStrEncode");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeGreaterThan(String value) {
            addCriterion("LOOP_STR_ENCODE >", value, "loopStrEncode");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeGreaterThanOrEqualTo(String value) {
            addCriterion("LOOP_STR_ENCODE >=", value, "loopStrEncode");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeLessThan(String value) {
            addCriterion("LOOP_STR_ENCODE <", value, "loopStrEncode");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeLessThanOrEqualTo(String value) {
            addCriterion("LOOP_STR_ENCODE <=", value, "loopStrEncode");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeLike(String value) {
            addCriterion("LOOP_STR_ENCODE like", value, "loopStrEncode");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeNotLike(String value) {
            addCriterion("LOOP_STR_ENCODE not like", value, "loopStrEncode");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeIn(List<String> values) {
            addCriterion("LOOP_STR_ENCODE in", values, "loopStrEncode");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeNotIn(List<String> values) {
            addCriterion("LOOP_STR_ENCODE not in", values, "loopStrEncode");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeBetween(String value1, String value2) {
            addCriterion("LOOP_STR_ENCODE between", value1, value2, "loopStrEncode");
            return (Criteria) this;
        }

        public Criteria andLoopStrEncodeNotBetween(String value1, String value2) {
            addCriterion("LOOP_STR_ENCODE not between", value1, value2, "loopStrEncode");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameIsNull() {
            addCriterion("LOOP_VAR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameIsNotNull() {
            addCriterion("LOOP_VAR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameEqualTo(String value) {
            addCriterion("LOOP_VAR_NAME =", value, "LoopVarName");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameNotEqualTo(String value) {
            addCriterion("LOOP_VAR_NAME <>", value, "LoopVarName");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameGreaterThan(String value) {
            addCriterion("LOOP_VAR_NAME >", value, "LoopVarName");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameGreaterThanOrEqualTo(String value) {
            addCriterion("LOOP_VAR_NAME >=", value, "LoopVarName");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameLessThan(String value) {
            addCriterion("LOOP_VAR_NAME <", value, "LoopVarName");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameLessThanOrEqualTo(String value) {
            addCriterion("LOOP_VAR_NAME <=", value, "LoopVarName");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameLike(String value) {
            addCriterion("LOOP_VAR_NAME like", value, "LoopVarName");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameNotLike(String value) {
            addCriterion("LOOP_VAR_NAME not like", value, "LoopVarName");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameIn(List<String> values) {
            addCriterion("LOOP_VAR_NAME in", values, "LoopVarName");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameNotIn(List<String> values) {
            addCriterion("LOOP_VAR_NAME not in", values, "LoopVarName");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameBetween(String value1, String value2) {
            addCriterion("LOOP_VAR_NAME between", value1, value2, "LoopVarName");
            return (Criteria) this;
        }

        public Criteria andLoopVarNameNotBetween(String value1, String value2) {
            addCriterion("LOOP_VAR_NAME not between", value1, value2, "LoopVarName");
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