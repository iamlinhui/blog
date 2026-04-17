<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<script type="text/javascript" src="static/layer/layer.js"></script>
	<script type="text/javascript" src="static/script/account.js"></script>
	<title>${title} - 找回密码</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigation_pages.jsp" %>
<div class="auth-container">
	<div class="auth-card">
		<h2><i class="bi bi-key me-2"></i>找回密码</h2>
		<c:if test="${not empty error}">
			<div class="alert alert-danger text-center" role="alert">${error.message}</div>
		</c:if>
		<form id="login" method="post" action="forget" class="form-modern">
			<div class="mb-3">
				<label class="form-label">邮箱</label>
				<input name="email" type="email" class="form-control" id="email" placeholder="请输入邮箱地址" required autofocus>
			</div>
			<div class="mb-3">
				<label class="form-label">验证码</label>
				<div class="input-group">
					<input maxlength="8" name="registerCode" class="form-control" type="text" placeholder="请输入邮箱验证码" autocomplete="off" required/>
					<button class="btn btn-outline-secondary" type="button" id="getCode"><span id="getCodeTxt">获取验证码</span></button>
				</div>
			</div>
			<div class="mb-3">
				<label class="form-label">新密码</label>
				<input name="password" type="password" class="form-control" placeholder="请输入新密码" required/>
			</div>
			<button type="submit" class="btn btn-primary w-100 mt-2">重置密码</button>
			<div class="text-center mt-3">
				<a href="login" class="text-decoration-none">登录</a>
				<span class="text-muted mx-2">|</span>
				<a href="register" class="text-decoration-none">注册</a>
			</div>
		</form>
	</div>
</div>
<%@include file="/WEB-INF/templates/common/foot.jsp" %>
</body>
</html>
