<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@include file="/WEB-INF/templates/common/base.jsp" %>
    <script type="text/javascript" src="static/script/account.js"></script>
    <title>${title} - 登录</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigation_pages.jsp" %>
<div class="auth-container">
    <div class="auth-card">
        <h2><i class="bi bi-box-arrow-in-right me-2"></i>登录</h2>
        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center" role="alert">${error.message} <a href="forget">忘记密码？</a></div>
        </c:if>
        <form id="login" method="post" action="login" class="form-modern">
            <div class="mb-3">
                <label class="form-label">用户名或邮箱</label>
                <input name="loginName" class="form-control" type="text" placeholder="请输入用户名或邮箱" required autofocus/>
            </div>
            <div class="mb-3">
                <label class="form-label">密码</label>
                <input name="password" type="password" class="form-control" placeholder="请输入密码" required/>
            </div>
            <div class="mb-3">
                <label class="form-label">验证码</label>
                <div class="input-group">
                    <input pattern="^\d{4}$" maxlength="4" name="loginCode" class="form-control" type="text" placeholder="请输入验证码" autocomplete="off" required/>
                    <span class="input-group-text p-0" style="cursor:pointer;overflow:hidden;">
                        <img height="38" id="captcha-image" src="code.jpg" alt="验证码"/>
                    </span>
                </div>
            </div>
            <button type="submit" class="btn btn-primary w-100 mt-2">立即登录</button>
            <div class="text-center mt-3">
                <a href="register" class="text-decoration-none">注册</a>
                <span class="text-muted mx-2">|</span>
                <a href="forget" class="text-decoration-none">忘记密码？</a>
            </div>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/templates/common/foot.jsp" %>
</body>
</html>
