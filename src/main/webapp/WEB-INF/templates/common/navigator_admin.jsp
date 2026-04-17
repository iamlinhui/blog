<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-modern fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/">${title}<small>后台管理</small></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav admin-nav me-auto">
                <li class="nav-item"><a class="nav-link <c:if test="${pageContext.request.requestURI.contains('article')}">active-nav</c:if>" href="admin/article"><i class="bi bi-file-text me-1"></i>所有文章</a></li>
                <li class="nav-item"><a class="nav-link <c:if test="${pageContext.request.requestURI.contains('create')}">active-nav</c:if>" href="admin/create"><i class="bi bi-plus-circle me-1"></i>新建文章</a></li>
                <li class="nav-item"><a class="nav-link <c:if test="${pageContext.request.requestURI.contains('navigation')}">active-nav</c:if>" href="admin/navigation"><i class="bi bi-signpost me-1"></i>导航管理</a></li>
                <li class="nav-item"><a class="nav-link <c:if test="${pageContext.request.requestURI.contains('user')}">active-nav</c:if>" href="admin/user"><i class="bi bi-people me-1"></i>用户管理</a></li>
                <li class="nav-item"><a class="nav-link <c:if test="${pageContext.request.requestURI.contains('website')}">active-nav</c:if>" href="admin/website"><i class="bi bi-sliders me-1"></i>站点设置</a></li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="logout"><i class="bi bi-box-arrow-right me-1"></i>退出登录</a></li>
            </ul>
        </div>
    </div>
</nav>
