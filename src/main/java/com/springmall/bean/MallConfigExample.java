package com.springmall.bean;

import java.util.ArrayList;
import java.util.List;

public class MallConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MallConfigExample() {
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

        public Criteria andLitemall_mall_nameIsNull() {
            addCriterion("litemall_mall_name is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameIsNotNull() {
            addCriterion("litemall_mall_name is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameEqualTo(String value) {
            addCriterion("litemall_mall_name =", value, "litemall_mall_name");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameNotEqualTo(String value) {
            addCriterion("litemall_mall_name <>", value, "litemall_mall_name");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameGreaterThan(String value) {
            addCriterion("litemall_mall_name >", value, "litemall_mall_name");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_mall_name >=", value, "litemall_mall_name");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameLessThan(String value) {
            addCriterion("litemall_mall_name <", value, "litemall_mall_name");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameLessThanOrEqualTo(String value) {
            addCriterion("litemall_mall_name <=", value, "litemall_mall_name");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameLike(String value) {
            addCriterion("litemall_mall_name like", value, "litemall_mall_name");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameNotLike(String value) {
            addCriterion("litemall_mall_name not like", value, "litemall_mall_name");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameIn(List<String> values) {
            addCriterion("litemall_mall_name in", values, "litemall_mall_name");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameNotIn(List<String> values) {
            addCriterion("litemall_mall_name not in", values, "litemall_mall_name");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameBetween(String value1, String value2) {
            addCriterion("litemall_mall_name between", value1, value2, "litemall_mall_name");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_nameNotBetween(String value1, String value2) {
            addCriterion("litemall_mall_name not between", value1, value2, "litemall_mall_name");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressIsNull() {
            addCriterion("litemall_mall_address is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressIsNotNull() {
            addCriterion("litemall_mall_address is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressEqualTo(String value) {
            addCriterion("litemall_mall_address =", value, "litemall_mall_address");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressNotEqualTo(String value) {
            addCriterion("litemall_mall_address <>", value, "litemall_mall_address");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressGreaterThan(String value) {
            addCriterion("litemall_mall_address >", value, "litemall_mall_address");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_mall_address >=", value, "litemall_mall_address");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressLessThan(String value) {
            addCriterion("litemall_mall_address <", value, "litemall_mall_address");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressLessThanOrEqualTo(String value) {
            addCriterion("litemall_mall_address <=", value, "litemall_mall_address");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressLike(String value) {
            addCriterion("litemall_mall_address like", value, "litemall_mall_address");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressNotLike(String value) {
            addCriterion("litemall_mall_address not like", value, "litemall_mall_address");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressIn(List<String> values) {
            addCriterion("litemall_mall_address in", values, "litemall_mall_address");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressNotIn(List<String> values) {
            addCriterion("litemall_mall_address not in", values, "litemall_mall_address");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressBetween(String value1, String value2) {
            addCriterion("litemall_mall_address between", value1, value2, "litemall_mall_address");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_addressNotBetween(String value1, String value2) {
            addCriterion("litemall_mall_address not between", value1, value2, "litemall_mall_address");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneIsNull() {
            addCriterion("litemall_mall_phone is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneIsNotNull() {
            addCriterion("litemall_mall_phone is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneEqualTo(String value) {
            addCriterion("litemall_mall_phone =", value, "litemall_mall_phone");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneNotEqualTo(String value) {
            addCriterion("litemall_mall_phone <>", value, "litemall_mall_phone");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneGreaterThan(String value) {
            addCriterion("litemall_mall_phone >", value, "litemall_mall_phone");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_mall_phone >=", value, "litemall_mall_phone");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneLessThan(String value) {
            addCriterion("litemall_mall_phone <", value, "litemall_mall_phone");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneLessThanOrEqualTo(String value) {
            addCriterion("litemall_mall_phone <=", value, "litemall_mall_phone");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneLike(String value) {
            addCriterion("litemall_mall_phone like", value, "litemall_mall_phone");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneNotLike(String value) {
            addCriterion("litemall_mall_phone not like", value, "litemall_mall_phone");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneIn(List<String> values) {
            addCriterion("litemall_mall_phone in", values, "litemall_mall_phone");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneNotIn(List<String> values) {
            addCriterion("litemall_mall_phone not in", values, "litemall_mall_phone");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneBetween(String value1, String value2) {
            addCriterion("litemall_mall_phone between", value1, value2, "litemall_mall_phone");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_phoneNotBetween(String value1, String value2) {
            addCriterion("litemall_mall_phone not between", value1, value2, "litemall_mall_phone");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqIsNull() {
            addCriterion("litemall_mall_qq is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqIsNotNull() {
            addCriterion("litemall_mall_qq is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqEqualTo(String value) {
            addCriterion("litemall_mall_qq =", value, "litemall_mall_qq");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqNotEqualTo(String value) {
            addCriterion("litemall_mall_qq <>", value, "litemall_mall_qq");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqGreaterThan(String value) {
            addCriterion("litemall_mall_qq >", value, "litemall_mall_qq");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_mall_qq >=", value, "litemall_mall_qq");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqLessThan(String value) {
            addCriterion("litemall_mall_qq <", value, "litemall_mall_qq");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqLessThanOrEqualTo(String value) {
            addCriterion("litemall_mall_qq <=", value, "litemall_mall_qq");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqLike(String value) {
            addCriterion("litemall_mall_qq like", value, "litemall_mall_qq");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqNotLike(String value) {
            addCriterion("litemall_mall_qq not like", value, "litemall_mall_qq");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqIn(List<String> values) {
            addCriterion("litemall_mall_qq in", values, "litemall_mall_qq");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqNotIn(List<String> values) {
            addCriterion("litemall_mall_qq not in", values, "litemall_mall_qq");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqBetween(String value1, String value2) {
            addCriterion("litemall_mall_qq between", value1, value2, "litemall_mall_qq");
            return (Criteria) this;
        }

        public Criteria andLitemall_mall_qqNotBetween(String value1, String value2) {
            addCriterion("litemall_mall_qq not between", value1, value2, "litemall_mall_qq");
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