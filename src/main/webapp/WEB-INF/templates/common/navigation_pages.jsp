<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%--页头开始 --%>
	<nav class="navbar navbar-inverse navbar-fixed-top ">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/" >${title}</a>
          <a href="/" class="navbar-brand"><small>${subtitle }</small></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/">首页</a></li>
            <li><a target="_blank" href="https://github.com/iamlinhui">关于</a></li>
          </ul>
          <ul class="nav navbar-nav">
	          <form class="navbar-form" action="search" method="post">
	            <div class="form-group">
	              <input value="${key }" autocomplete="off"  name="key" type="text" placeholder="关键词" class="form-control" required/>
	            </div>
	            <button type="submit" class="btn btn-success">
	           		<span class="glyphicon glyphicon-search" aria-hidden="true">搜索</span>
	            </button>
	          </form>
          </ul>
          <ul class="nav navbar-nav navbar-right">
				<c:if test="${empty sessionScope.user}">
					<li><a href="login">登录</a></li>
					<li><a href="register">注册</a></li>
				</c:if>
				<c:if test="${not empty sessionScope.user and sessionScope.user.userStatus==0}">
					<li><a href="admin/article">管理站点</a></li>
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
<%--页头结束 --%>
