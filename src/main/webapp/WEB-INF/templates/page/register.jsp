<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<link href="static/css/signin.css" rel="stylesheet" type="text/css" />
	<link href="static/css/offcanvas.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript"  src="static/layer/layer.js"></script>
	<script type="text/javascript"  src="static/script/account.js"></script>
	<title>${title } - 注册</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/" >${title}</a>
          <a href="register" class="navbar-brand"><small>站点注册</small></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
			  <li><a href="/">首页</a></li>
            <li><a target="_blank" href="https://github.com/iamlinhui">关于</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
				<c:if test="${empty sessionScope.user}">
					<li><a href="login">登录</a></li>
					<li><a href="register">注册</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.user and sessionScope.user.userStatus==0}">
					<li><a href="admin">管理站点</a></li>
					<li><a href="user">${sessionScope.user.userNicename }</a></li>
					<li><a href="logout">退出登录</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.user and sessionScope.user.userStatus!=0}">
					<li><a href="user">${sessionScope.user.userNicename }</a></li>
					<li><a href="logout">退出登录</a></li>
				</c:if>
			</ul>
        </div>
      </div>
    </nav>
    <br>
    <br>
    <br>
    <br>
	<%--巨幕开始 --%>
	<div class="container">
		<form id="login" method="post" action="register" class="form-signin">
			<c:if test="${not empty error }">
				<div style="text-align: center;">${error.message}<a href="forget">忘记密码？</a></div>
			</c:if>
			<div class="form-group">
				<input name="username" type="text" class="form-control" id="username" placeholder="用户名" required autofocus>
			</div>
			<div class="form-group">
				<input name="email" type="email" class="form-control" id="email" placeholder="电子邮件地址" required autofocus>
			</div>
			<div class="form-group">
				<div class="input-group">
					<input maxlength="8" name="registerCode" class="form-control" type="text" id="captcha" placeholder="请输入邮箱验证码" autocomplete="false" required/>
					<div class="input-group-btn" id="getCode">
						<a class="btn btn-lg btn-default"><small id="getCodeTxt">获取验证码</small></a>
					</div>
				</div>
			</div>
			<div class="form-group">
				<input name="password" type="password" class="form-control" id="password" placeholder="登录密码" required/>
			</div>
			<button type="submit" class="btn btn-lg btn-primary btn-block">注册</button>
			<div >
				<label>
					<a href="login">登录</a>|<a href="forget">忘记密码？</a>
				</label>
			</div>
		</form>
	</div>
	<%@include file="/WEB-INF/templates/common/foot.jsp" %>
	<%--巨幕结束 --%>
</body>
</html>