package com.blog.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UsersExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserLoginIsNull() {
            addCriterion("user_login is null");
            return (Criteria) this;
        }

        public Criteria andUserLoginIsNotNull() {
            addCriterion("user_login is not null");
            return (Criteria) this;
        }

        public Criteria andUserLoginEqualTo(String value) {
            addCriterion("user_login =", value, "userLogin");
            return (Criteria) this;
        }

        public Criteria andUserLoginNotEqualTo(String value) {
            addCriterion("user_login <>", value, "userLogin");
            return (Criteria) this;
        }

        public Criteria andUserLoginGreaterThan(String value) {
            addCriterion("user_login >", value, "userLogin");
            return (Criteria) this;
        }

        public Criteria andUserLoginGreaterThanOrEqualTo(String value) {
            addCriterion("user_login >=", value, "userLogin");
            return (Criteria) this;
        }

        public Criteria andUserLoginLessThan(String value) {
            addCriterion("user_login <", value, "userLogin");
            return (Criteria) this;
        }

        public Criteria andUserLoginLessThanOrEqualTo(String value) {
            addCriterion("user_login <=", value, "userLogin");
            return (Criteria) this;
        }

        public Criteria andUserLoginLike(String value) {
            addCriterion("user_login like", value, "userLogin");
            return (Criteria) this;
        }

        public Criteria andUserLoginNotLike(String value) {
            addCriterion("user_login not like", value, "userLogin");
            return (Criteria) this;
        }

        public Criteria andUserLoginIn(List<String> values) {
            addCriterion("user_login in", values, "userLogin");
            return (Criteria) this;
        }

        public Criteria andUserLoginNotIn(List<String> values) {
            addCriterion("user_login not in", values, "userLogin");
            return (Criteria) this;
        }

        public Criteria andUserLoginBetween(String value1, String value2) {
            addCriterion("user_login between", value1, value2, "userLogin");
            return (Criteria) this;
        }

        public Criteria andUserLoginNotBetween(String value1, String value2) {
            addCriterion("user_login not between", value1, value2, "userLogin");
            return (Criteria) this;
        }

        public Criteria andUserPassIsNull() {
            addCriterion("user_pass is null");
            return (Criteria) this;
        }

        public Criteria andUserPassIsNotNull() {
            addCriterion("user_pass is not null");
            return (Criteria) this;
        }

        public Criteria andUserPassEqualTo(String value) {
            addCriterion("user_pass =", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassNotEqualTo(String value) {
            addCriterion("user_pass <>", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassGreaterThan(String value) {
            addCriterion("user_pass >", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassGreaterThanOrEqualTo(String value) {
            addCriterion("user_pass >=", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassLessThan(String value) {
            addCriterion("user_pass <", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassLessThanOrEqualTo(String value) {
            addCriterion("user_pass <=", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassLike(String value) {
            addCriterion("user_pass like", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassNotLike(String value) {
            addCriterion("user_pass not like", value, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassIn(List<String> values) {
            addCriterion("user_pass in", values, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassNotIn(List<String> values) {
            addCriterion("user_pass not in", values, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassBetween(String value1, String value2) {
            addCriterion("user_pass between", value1, value2, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserPassNotBetween(String value1, String value2) {
            addCriterion("user_pass not between", value1, value2, "userPass");
            return (Criteria) this;
        }

        public Criteria andUserNicenameIsNull() {
            addCriterion("user_nicename is null");
            return (Criteria) this;
        }

        public Criteria andUserNicenameIsNotNull() {
            addCriterion("user_nicename is not null");
            return (Criteria) this;
        }

        public Criteria andUserNicenameEqualTo(String value) {
            addCriterion("user_nicename =", value, "userNicename");
            return (Criteria) this;
        }

        public Criteria andUserNicenameNotEqualTo(String value) {
            addCriterion("user_nicename <>", value, "userNicename");
            return (Criteria) this;
        }

        public Criteria andUserNicenameGreaterThan(String value) {
            addCriterion("user_nicename >", value, "userNicename");
            return (Criteria) this;
        }

        public Criteria andUserNicenameGreaterThanOrEqualTo(String value) {
            addCriterion("user_nicename >=", value, "userNicename");
            return (Criteria) this;
        }

        public Criteria andUserNicenameLessThan(String value) {
            addCriterion("user_nicename <", value, "userNicename");
            return (Criteria) this;
        }

        public Criteria andUserNicenameLessThanOrEqualTo(String value) {
            addCriterion("user_nicename <=", value, "userNicename");
            return (Criteria) this;
        }

        public Criteria andUserNicenameLike(String value) {
            addCriterion("user_nicename like", value, "userNicename");
            return (Criteria) this;
        }

        public Criteria andUserNicenameNotLike(String value) {
            addCriterion("user_nicename not like", value, "userNicename");
            return (Criteria) this;
        }

        public Criteria andUserNicenameIn(List<String> values) {
            addCriterion("user_nicename in", values, "userNicename");
            return (Criteria) this;
        }

        public Criteria andUserNicenameNotIn(List<String> values) {
            addCriterion("user_nicename not in", values, "userNicename");
            return (Criteria) this;
        }

        public Criteria andUserNicenameBetween(String value1, String value2) {
            addCriterion("user_nicename between", value1, value2, "userNicename");
            return (Criteria) this;
        }

        public Criteria andUserNicenameNotBetween(String value1, String value2) {
            addCriterion("user_nicename not between", value1, value2, "userNicename");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNull() {
            addCriterion("user_email is null");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNotNull() {
            addCriterion("user_email is not null");
            return (Criteria) this;
        }

        public Criteria andUserEmailEqualTo(String value) {
            addCriterion("user_email =", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotEqualTo(String value) {
            addCriterion("user_email <>", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThan(String value) {
            addCriterion("user_email >", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThanOrEqualTo(String value) {
            addCriterion("user_email >=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThan(String value) {
            addCriterion("user_email <", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThanOrEqualTo(String value) {
            addCriterion("user_email <=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLike(String value) {
            addCriterion("user_email like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotLike(String value) {
            addCriterion("user_email not like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailIn(List<String> values) {
            addCriterion("user_email in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotIn(List<String> values) {
            addCriterion("user_email not in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailBetween(String value1, String value2) {
            addCriterion("user_email between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotBetween(String value1, String value2) {
            addCriterion("user_email not between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserRegisteredIsNull() {
            addCriterion("user_registered is null");
            return (Criteria) this;
        }

        public Criteria andUserRegisteredIsNotNull() {
            addCriterion("user_registered is not null");
            return (Criteria) this;
        }

        public Criteria andUserRegisteredEqualTo(Date value) {
            addCriterion("user_registered =", value, "userRegistered");
            return (Criteria) this;
        }

        public Criteria andUserRegisteredNotEqualTo(Date value) {
            addCriterion("user_registered <>", value, "userRegistered");
            return (Criteria) this;
        }

        public Criteria andUserRegisteredGreaterThan(Date value) {
            addCriterion("user_registered >", value, "userRegistered");
            return (Criteria) this;
        }

        public Criteria andUserRegisteredGreaterThanOrEqualTo(Date value) {
            addCriterion("user_registered >=", value, "userRegistered");
            return (Criteria) this;
        }

        public Criteria andUserRegisteredLessThan(Date value) {
            addCriterion("user_registered <", value, "userRegistered");
            return (Criteria) this;
        }

        public Criteria andUserRegisteredLessThanOrEqualTo(Date value) {
            addCriterion("user_registered <=", value, "userRegistered");
            return (Criteria) this;
        }

        public Criteria andUserRegisteredIn(List<Date> values) {
            addCriterion("user_registered in", values, "userRegistered");
            return (Criteria) this;
        }

        public Criteria andUserRegisteredNotIn(List<Date> values) {
            addCriterion("user_registered not in", values, "userRegistered");
            return (Criteria) this;
        }

        public Criteria andUserRegisteredBetween(Date value1, Date value2) {
            addCriterion("user_registered between", value1, value2, "userRegistered");
            return (Criteria) this;
        }

        public Criteria andUserRegisteredNotBetween(Date value1, Date value2) {
            addCriterion("user_registered not between", value1, value2, "userRegistered");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNull() {
            addCriterion("user_status is null");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNotNull() {
            addCriterion("user_status is not null");
            return (Criteria) this;
        }

        public Criteria andUserStatusEqualTo(Integer value) {
            addCriterion("user_status =", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotEqualTo(Integer value) {
            addCriterion("user_status <>", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThan(Integer value) {
            addCriterion("user_status >", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_status >=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThan(Integer value) {
            addCriterion("user_status <", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThanOrEqualTo(Integer value) {
            addCriterion("user_status <=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusIn(List<Integer> values) {
            addCriterion("user_status in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotIn(List<Integer> values) {
            addCriterion("user_status not in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusBetween(Integer value1, Integer value2) {
            addCriterion("user_status between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("user_status not between", value1, value2, "userStatus");
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