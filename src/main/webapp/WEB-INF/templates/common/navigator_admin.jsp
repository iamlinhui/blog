<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">${title}</a>
      <a href="#" class="navbar-brand"><small>后台页面</small></a>
    </div>
    <div id="navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li <c:if test="${pageContext.request.requestURI.contains('article')}">class="active"</c:if>><a href="admin/article">所有文章</a></li>
        <li <c:if test="${pageContext.request.requestURI.contains('create')}">class="active"</c:if>><a href="admin/create">新建文章</a></li>
        <li <c:if test="${pageContext.request.requestURI.contains('navigation')}">class="active"</c:if>><a href="admin/navigation">导航管理</a></li>
        <li <c:if test="${pageContext.request.requestURI.contains('user')}">class="active"</c:if>><a href="admin/user">用户管理</a></li>
        <li <c:if test="${pageContext.request.requestURI.contains('website')}">class="active"</c:if>><a href="admin/website">站点设置</a></li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <c:if test="${empty sessionScope.user}">
            <li><a href="login">登录</a></li>
            <li><a href="register">注册</a></li>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <li><a href="admin">管理站点</a></li>
            <li><a href="logout">退出登录</a></li>
        </c:if>
      </ul>
    </div>
  </div>
</nav>