package com.springmall.bean;

import java.util.ArrayList;
import java.util.List;

public class WxConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WxConfigExample() {
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

        public Criteria andLitemall_wx_index_newIsNull() {
            addCriterion("litemall_wx_index_new is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newIsNotNull() {
            addCriterion("litemall_wx_index_new is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newEqualTo(String value) {
            addCriterion("litemall_wx_index_new =", value, "litemall_wx_index_new");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newNotEqualTo(String value) {
            addCriterion("litemall_wx_index_new <>", value, "litemall_wx_index_new");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newGreaterThan(String value) {
            addCriterion("litemall_wx_index_new >", value, "litemall_wx_index_new");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_wx_index_new >=", value, "litemall_wx_index_new");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newLessThan(String value) {
            addCriterion("litemall_wx_index_new <", value, "litemall_wx_index_new");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newLessThanOrEqualTo(String value) {
            addCriterion("litemall_wx_index_new <=", value, "litemall_wx_index_new");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newLike(String value) {
            addCriterion("litemall_wx_index_new like", value, "litemall_wx_index_new");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newNotLike(String value) {
            addCriterion("litemall_wx_index_new not like", value, "litemall_wx_index_new");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newIn(List<String> values) {
            addCriterion("litemall_wx_index_new in", values, "litemall_wx_index_new");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newNotIn(List<String> values) {
            addCriterion("litemall_wx_index_new not in", values, "litemall_wx_index_new");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newBetween(String value1, String value2) {
            addCriterion("litemall_wx_index_new between", value1, value2, "litemall_wx_index_new");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_newNotBetween(String value1, String value2) {
            addCriterion("litemall_wx_index_new not between", value1, value2, "litemall_wx_index_new");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsIsNull() {
            addCriterion("litemall_wx_catlog_goods is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsIsNotNull() {
            addCriterion("litemall_wx_catlog_goods is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsEqualTo(String value) {
            addCriterion("litemall_wx_catlog_goods =", value, "litemall_wx_catlog_goods");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsNotEqualTo(String value) {
            addCriterion("litemall_wx_catlog_goods <>", value, "litemall_wx_catlog_goods");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsGreaterThan(String value) {
            addCriterion("litemall_wx_catlog_goods >", value, "litemall_wx_catlog_goods");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_wx_catlog_goods >=", value, "litemall_wx_catlog_goods");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsLessThan(String value) {
            addCriterion("litemall_wx_catlog_goods <", value, "litemall_wx_catlog_goods");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsLessThanOrEqualTo(String value) {
            addCriterion("litemall_wx_catlog_goods <=", value, "litemall_wx_catlog_goods");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsLike(String value) {
            addCriterion("litemall_wx_catlog_goods like", value, "litemall_wx_catlog_goods");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsNotLike(String value) {
            addCriterion("litemall_wx_catlog_goods not like", value, "litemall_wx_catlog_goods");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsIn(List<String> values) {
            addCriterion("litemall_wx_catlog_goods in", values, "litemall_wx_catlog_goods");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsNotIn(List<String> values) {
            addCriterion("litemall_wx_catlog_goods not in", values, "litemall_wx_catlog_goods");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsBetween(String value1, String value2) {
            addCriterion("litemall_wx_catlog_goods between", value1, value2, "litemall_wx_catlog_goods");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_goodsNotBetween(String value1, String value2) {
            addCriterion("litemall_wx_catlog_goods not between", value1, value2, "litemall_wx_catlog_goods");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listIsNull() {
            addCriterion("litemall_wx_catlog_list is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listIsNotNull() {
            addCriterion("litemall_wx_catlog_list is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listEqualTo(String value) {
            addCriterion("litemall_wx_catlog_list =", value, "litemall_wx_catlog_list");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listNotEqualTo(String value) {
            addCriterion("litemall_wx_catlog_list <>", value, "litemall_wx_catlog_list");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listGreaterThan(String value) {
            addCriterion("litemall_wx_catlog_list >", value, "litemall_wx_catlog_list");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_wx_catlog_list >=", value, "litemall_wx_catlog_list");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listLessThan(String value) {
            addCriterion("litemall_wx_catlog_list <", value, "litemall_wx_catlog_list");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listLessThanOrEqualTo(String value) {
            addCriterion("litemall_wx_catlog_list <=", value, "litemall_wx_catlog_list");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listLike(String value) {
            addCriterion("litemall_wx_catlog_list like", value, "litemall_wx_catlog_list");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listNotLike(String value) {
            addCriterion("litemall_wx_catlog_list not like", value, "litemall_wx_catlog_list");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listIn(List<String> values) {
            addCriterion("litemall_wx_catlog_list in", values, "litemall_wx_catlog_list");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listNotIn(List<String> values) {
            addCriterion("litemall_wx_catlog_list not in", values, "litemall_wx_catlog_list");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listBetween(String value1, String value2) {
            addCriterion("litemall_wx_catlog_list between", value1, value2, "litemall_wx_catlog_list");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_catlog_listNotBetween(String value1, String value2) {
            addCriterion("litemall_wx_catlog_list not between", value1, value2, "litemall_wx_catlog_list");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareIsNull() {
            addCriterion("litemall_wx_share is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareIsNotNull() {
            addCriterion("litemall_wx_share is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareEqualTo(String value) {
            addCriterion("litemall_wx_share =", value, "litemall_wx_share");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareNotEqualTo(String value) {
            addCriterion("litemall_wx_share <>", value, "litemall_wx_share");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareGreaterThan(String value) {
            addCriterion("litemall_wx_share >", value, "litemall_wx_share");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_wx_share >=", value, "litemall_wx_share");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareLessThan(String value) {
            addCriterion("litemall_wx_share <", value, "litemall_wx_share");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareLessThanOrEqualTo(String value) {
            addCriterion("litemall_wx_share <=", value, "litemall_wx_share");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareLike(String value) {
            addCriterion("litemall_wx_share like", value, "litemall_wx_share");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareNotLike(String value) {
            addCriterion("litemall_wx_share not like", value, "litemall_wx_share");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareIn(List<String> values) {
            addCriterion("litemall_wx_share in", values, "litemall_wx_share");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareNotIn(List<String> values) {
            addCriterion("litemall_wx_share not in", values, "litemall_wx_share");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareBetween(String value1, String value2) {
            addCriterion("litemall_wx_share between", value1, value2, "litemall_wx_share");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_shareNotBetween(String value1, String value2) {
            addCriterion("litemall_wx_share not between", value1, value2, "litemall_wx_share");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandIsNull() {
            addCriterion("litemall_wx_index_brand is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandIsNotNull() {
            addCriterion("litemall_wx_index_brand is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandEqualTo(String value) {
            addCriterion("litemall_wx_index_brand =", value, "litemall_wx_index_brand");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandNotEqualTo(String value) {
            addCriterion("litemall_wx_index_brand <>", value, "litemall_wx_index_brand");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandGreaterThan(String value) {
            addCriterion("litemall_wx_index_brand >", value, "litemall_wx_index_brand");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_wx_index_brand >=", value, "litemall_wx_index_brand");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandLessThan(String value) {
            addCriterion("litemall_wx_index_brand <", value, "litemall_wx_index_brand");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandLessThanOrEqualTo(String value) {
            addCriterion("litemall_wx_index_brand <=", value, "litemall_wx_index_brand");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandLike(String value) {
            addCriterion("litemall_wx_index_brand like", value, "litemall_wx_index_brand");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandNotLike(String value) {
            addCriterion("litemall_wx_index_brand not like", value, "litemall_wx_index_brand");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandIn(List<String> values) {
            addCriterion("litemall_wx_index_brand in", values, "litemall_wx_index_brand");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandNotIn(List<String> values) {
            addCriterion("litemall_wx_index_brand not in", values, "litemall_wx_index_brand");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandBetween(String value1, String value2) {
            addCriterion("litemall_wx_index_brand between", value1, value2, "litemall_wx_index_brand");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_brandNotBetween(String value1, String value2) {
            addCriterion("litemall_wx_index_brand not between", value1, value2, "litemall_wx_index_brand");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotIsNull() {
            addCriterion("litemall_wx_index_hot is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotIsNotNull() {
            addCriterion("litemall_wx_index_hot is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotEqualTo(String value) {
            addCriterion("litemall_wx_index_hot =", value, "litemall_wx_index_hot");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotNotEqualTo(String value) {
            addCriterion("litemall_wx_index_hot <>", value, "litemall_wx_index_hot");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotGreaterThan(String value) {
            addCriterion("litemall_wx_index_hot >", value, "litemall_wx_index_hot");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_wx_index_hot >=", value, "litemall_wx_index_hot");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotLessThan(String value) {
            addCriterion("litemall_wx_index_hot <", value, "litemall_wx_index_hot");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotLessThanOrEqualTo(String value) {
            addCriterion("litemall_wx_index_hot <=", value, "litemall_wx_index_hot");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotLike(String value) {
            addCriterion("litemall_wx_index_hot like", value, "litemall_wx_index_hot");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotNotLike(String value) {
            addCriterion("litemall_wx_index_hot not like", value, "litemall_wx_index_hot");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotIn(List<String> values) {
            addCriterion("litemall_wx_index_hot in", values, "litemall_wx_index_hot");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotNotIn(List<String> values) {
            addCriterion("litemall_wx_index_hot not in", values, "litemall_wx_index_hot");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotBetween(String value1, String value2) {
            addCriterion("litemall_wx_index_hot between", value1, value2, "litemall_wx_index_hot");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_hotNotBetween(String value1, String value2) {
            addCriterion("litemall_wx_index_hot not between", value1, value2, "litemall_wx_index_hot");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicIsNull() {
            addCriterion("litemall_wx_index_topic is null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicIsNotNull() {
            addCriterion("litemall_wx_index_topic is not null");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicEqualTo(String value) {
            addCriterion("litemall_wx_index_topic =", value, "litemall_wx_index_topic");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicNotEqualTo(String value) {
            addCriterion("litemall_wx_index_topic <>", value, "litemall_wx_index_topic");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicGreaterThan(String value) {
            addCriterion("litemall_wx_index_topic >", value, "litemall_wx_index_topic");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicGreaterThanOrEqualTo(String value) {
            addCriterion("litemall_wx_index_topic >=", value, "litemall_wx_index_topic");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicLessThan(String value) {
            addCriterion("litemall_wx_index_topic <", value, "litemall_wx_index_topic");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicLessThanOrEqualTo(String value) {
            addCriterion("litemall_wx_index_topic <=", value, "litemall_wx_index_topic");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicLike(String value) {
            addCriterion("litemall_wx_index_topic like", value, "litemall_wx_index_topic");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicNotLike(String value) {
            addCriterion("litemall_wx_index_topic not like", value, "litemall_wx_index_topic");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicIn(List<String> values) {
            addCriterion("litemall_wx_index_topic in", values, "litemall_wx_index_topic");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicNotIn(List<String> values) {
            addCriterion("litemall_wx_index_topic not in", values, "litemall_wx_index_topic");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicBetween(String value1, String value2) {
            addCriterion("litemall_wx_index_topic between", value1, value2, "litemall_wx_index_topic");
            return (Criteria) this;
        }

        public Criteria andLitemall_wx_index_topicNotBetween(String value1, String value2) {
            addCriterion("litemall_wx_index_topic not between", value1, value2, "litemall_wx_index_topic");
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