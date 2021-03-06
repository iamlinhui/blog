<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<link href="static/css/signin.css" rel="stylesheet" type="text/css" />
	<link href="static/css/offcanvas.css" rel="stylesheet" type="text/css" />
	<title>${title } - 注册</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/" >${title}</a>
          <a href="register" class="navbar-brand"><small>站点注册</small></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="index.html">首页</a></li>
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
			<form id="thisform" method="post" action="register" class="form-signin">
			<div style="text-align: center;">${error.message }
				<c:if test="${not empty error }">
					<a href="forget">忘记密码？</a>
				</c:if>
			</div>
				<h2 class="form-signin-heading">注册</h2>
				<label class="sr-only" for="exampleInputName">用户名</label> 
				<input name="username" type="text" class="form-control" id="exampleInputName" placeholder="用户名" required autofocus>
				<label class="sr-only" for="exampleInputEmail1">电子邮件</label> 
				<input name="email" type="email" class="form-control" id="exampleInputEmail1" placeholder="电子邮件地址" required>
				<div>
					<label> 
					注册确认信将会被寄给您。
					</label>
				</div>
				<button type="submit" class="btn btn-lg btn-primary btn-block">注册</button>
			<div>
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