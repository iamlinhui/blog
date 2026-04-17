<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%--页头开始 --%>
<nav class="navbar navbar-expand-lg navbar-modern fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/">${title}<small>${subtitle}</small></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item"><a class="nav-link active" href="/">首页</a></li>
                <li class="nav-item"><a class="nav-link" target="_blank" href="https://github.com/iamlinhui">关于</a></li>
            </ul>
            <form class="search-modern d-flex me-3" action="search" method="post">
                <input value="${key}" autocomplete="off" name="key" type="text" placeholder="搜索文章..." class="form-control" required/>
                <button type="submit"><i class="bi bi-search"></i></button>
            </form>
            <ul class="navbar-nav">
                <c:if test="${empty sessionScope.user}">
                    <li class="nav-item"><a class="nav-link" href="login"><i class="bi bi-box-arrow-in-right me-1"></i>登录</a></li>
                    <li class="nav-item"><a class="nav-link" href="register"><i class="bi bi-person-plus me-1"></i>注册</a></li>
                </c:if>
                <c:if test="${not empty sessionScope.user and sessionScope.user.userStatus==0}">
                    <li class="nav-item"><a class="nav-link" href="admin/article"><i class="bi bi-gear me-1"></i>管理</a></li>
                    <li class="nav-item"><a class="nav-link" href="user">${sessionScope.user.userNicename}</a></li>
                    <li class="nav-item"><a class="nav-link" href="logout">退出</a></li>
                </c:if>
                <c:if test="${not empty sessionScope.user and sessionScope.user.userStatus!=0}">
                    <li class="nav-item"><a class="nav-link" href="user">${sessionScope.user.userNicename}</a></li>
                    <li class="nav-item"><a class="nav-link" href="logout">退出</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>
<%--页头结束 --%>
