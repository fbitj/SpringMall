package com.springmall.bean;

import java.util.ArrayList;
import java.util.List;

public class OrderConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderConfigExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentIsNull() {
            addCriterion("litemall_order_comment is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentIsNotNull() {
            addCriterion("litemall_order_comment is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentEqualTo(String value) {
            addCriterion("litemall_order_comment =", value, "litemall_order_comment");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentNotEqualTo(String value) {
            addCriterion("litemall_order_comment <>", value, "litemall_order_comment");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentGreaterThan(String value) {
            addCriterion("litemall_order_comment >", value, "litemall_order_comment");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_order_comment >=", value, "litemall_order_comment");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentLessThan(String value) {
            addCriterion("litemall_order_comment <", value, "litemall_order_comment");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentLessThanOrEqualTo(String value) {
            addCriterion("litemall_order_comment <=", value, "litemall_order_comment");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentLike(String value) {
            addCriterion("litemall_order_comment like", value, "litemall_order_comment");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentNotLike(String value) {
            addCriterion("litemall_order_comment not like", value, "litemall_order_comment");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentIn(List<String> values) {
            addCriterion("litemall_order_comment in", values, "litemall_order_comment");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentNotIn(List<String> values) {
            addCriterion("litemall_order_comment not in", values, "litemall_order_comment");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentBetween(String value1, String value2) {
            addCriterion("litemall_order_comment between", value1, value2, "litemall_order_comment");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_commentNotBetween(String value1, String value2) {
            addCriterion("litemall_order_comment not between", value1, value2, "litemall_order_comment");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidIsNull() {
            addCriterion("litemall_order_unpaid is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidIsNotNull() {
            addCriterion("litemall_order_unpaid is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidEqualTo(String value) {
            addCriterion("litemall_order_unpaid =", value, "litemall_order_unpaid");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidNotEqualTo(String value) {
            addCriterion("litemall_order_unpaid <>", value, "litemall_order_unpaid");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidGreaterThan(String value) {
            addCriterion("litemall_order_unpaid >", value, "litemall_order_unpaid");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_order_unpaid >=", value, "litemall_order_unpaid");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidLessThan(String value) {
            addCriterion("litemall_order_unpaid <", value, "litemall_order_unpaid");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidLessThanOrEqualTo(String value) {
            addCriterion("litemall_order_unpaid <=", value, "litemall_order_unpaid");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidLike(String value) {
            addCriterion("litemall_order_unpaid like", value, "litemall_order_unpaid");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidNotLike(String value) {
            addCriterion("litemall_order_unpaid not like", value, "litemall_order_unpaid");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidIn(List<String> values) {
            addCriterion("litemall_order_unpaid in", values, "litemall_order_unpaid");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidNotIn(List<String> values) {
            addCriterion("litemall_order_unpaid not in", values, "litemall_order_unpaid");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidBetween(String value1, String value2) {
            addCriterion("litemall_order_unpaid between", value1, value2, "litemall_order_unpaid");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unpaidNotBetween(String value1, String value2) {
            addCriterion("litemall_order_unpaid not between", value1, value2, "litemall_order_unpaid");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmIsNull() {
            addCriterion("litemall_order_unconfirm is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmIsNotNull() {
            addCriterion("litemall_order_unconfirm is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmEqualTo(String value) {
            addCriterion("litemall_order_unconfirm =", value, "litemall_order_unconfirm");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmNotEqualTo(String value) {
            addCriterion("litemall_order_unconfirm <>", value, "litemall_order_unconfirm");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmGreaterThan(String value) {
            addCriterion("litemall_order_unconfirm >", value, "litemall_order_unconfirm");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_order_unconfirm >=", value, "litemall_order_unconfirm");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmLessThan(String value) {
            addCriterion("litemall_order_unconfirm <", value, "litemall_order_unconfirm");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmLessThanOrEqualTo(String value) {
            addCriterion("litemall_order_unconfirm <=", value, "litemall_order_unconfirm");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmLike(String value) {
            addCriterion("litemall_order_unconfirm like", value, "litemall_order_unconfirm");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmNotLike(String value) {
            addCriterion("litemall_order_unconfirm not like", value, "litemall_order_unconfirm");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmIn(List<String> values) {
            addCriterion("litemall_order_unconfirm in", values, "litemall_order_unconfirm");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmNotIn(List<String> values) {
            addCriterion("litemall_order_unconfirm not in", values, "litemall_order_unconfirm");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmBetween(String value1, String value2) {
            addCriterion("litemall_order_unconfirm between", value1, value2, "litemall_order_unconfirm");
            return (Criteria) this;
        }

        public Criteria andLitemall_order_unconfirmNotBetween(String value1, String value2) {
            addCriterion("litemall_order_unconfirm not between", value1, value2, "litemall_order_unconfirm");
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