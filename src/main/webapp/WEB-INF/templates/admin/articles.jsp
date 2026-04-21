<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@include file="/WEB-INF/templates/common/base.jsp" %>
    <title>${title} - 文章管理</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigator_admin.jsp" %>
<div class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h4 class="fw-bold mb-0"><i class="bi bi-file-text me-2"></i>所有文章</h4>
    </div>
    <!-- 筛选区域 + 操作 -->
    <div class="card-modern" style="transform:none;">
        <div class="card-body-modern py-3">
            <div class="d-flex flex-wrap align-items-end gap-2">
                <form id="filterForm" action="admin/article" method="get" class="form-modern d-flex flex-wrap align-items-end gap-2 flex-grow-1">
                    <div>
                        <label class="form-label">发布状态</label>
                        <select name="status" class="form-select">
                            <option value="">全部</option>
                            <option value="publish" <c:if test="${filter.status == 'publish'}">selected</c:if>>已发布</option>
                            <option value="draft" <c:if test="${filter.status == 'draft'}">selected</c:if>>草稿</option>
                        </select>
                    </div>
                    <div>
                        <label class="form-label">所属分类</label>
                        <select name="slug" class="form-select">
                            <option value="">全部</option>
                            <c:forEach items="${terms}" var="term">
                                <option value="${term.slug}" <c:if test="${filter.slug == term.slug}">selected</c:if>>${term.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label class="form-label">开始日期</label>
                        <input type="date" name="dateFrom" class="form-control" value="${filter.dateFrom}"/>
                    </div>
                    <div>
                        <label class="form-label">结束日期</label>
                        <input type="date" name="dateTo" class="form-control" value="${filter.dateTo}"/>
                    </div>
                    <div class="d-flex gap-2">
                        <button type="submit" class="btn btn-modern"><i class="bi bi-funnel me-1"></i>筛选</button>
                        <a href="admin/article" class="btn btn-outline-modern"><i class="bi bi-x-lg me-1"></i>重置</a>
                    </div>
                </form>
                <button class="btn btn-danger-modern" type="button" onclick="document.getElementById('deleteForm').submit()"><i class="bi bi-trash me-1"></i>删除</button>
            </div>
        </div>
    </div>
    <!-- 文章列表 -->
    <form id="deleteForm" action="admin/delete" method="post">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <div class="card-modern">
            <div class="list-group list-group-flush">
                <c:if test="${empty pageInfo.list}">
                    <div class="list-group-item text-center text-muted py-4">暂无匹配文章</div>
                </c:if>
                <c:forEach items="${pageInfo.list}" var="post">
                    <label class="list-group-item list-group-item-action d-flex align-items-center py-3" style="cursor:pointer;">
                        <input class="form-check-input me-3 flex-shrink-0" value="${post.id}" type="checkbox" name="delete"/>
                        <div class="flex-grow-1">
                            <a href="admin/edit/${post.id}" class="fw-medium text-decoration-none">${post.postTitle} - ${post.postExcerpt}</a>
                        </div>
                        <div class="d-flex gap-2 ms-3 flex-shrink-0">
                            <c:forEach items="${post.term}" var="term">
                                <span class="badge-modern">${term.name}</span>
                            </c:forEach>
                            <c:if test="${post.postStatus == 'publish'}">
                                <span class="badge-modern badge-status-publish">已发布</span>
                            </c:if>
                            <c:if test="${post.postStatus == 'draft'}">
                                <span class="badge-modern badge-status-draft">草稿</span>
                            </c:if>
                            <span class="text-muted" style="font-size:.8rem;white-space:nowrap;">
                                <fmt:formatDate pattern="yy/MM/dd" value="${post.postDate}"/>
                            </span>
                        </div>
                    </label>
                </c:forEach>
            </div>
        </div>
        <c:if test="${not empty pageInfo.list and pageInfo.pages!=1}">
            <c:set var="filterQuery" value="" scope="page"/>
            <c:if test="${not empty filter.status}"><c:set var="filterQuery" value="${filterQuery}&status=${filter.status}" scope="page"/></c:if>
            <c:if test="${not empty filter.slug}"><c:set var="filterQuery" value="${filterQuery}&slug=${filter.slug}" scope="page"/></c:if>
            <c:if test="${not empty filter.dateFrom}"><c:set var="filterQuery" value="${filterQuery}&dateFrom=${filter.dateFrom}" scope="page"/></c:if>
            <c:if test="${not empty filter.dateTo}"><c:set var="filterQuery" value="${filterQuery}&dateTo=${filter.dateTo}" scope="page"/></c:if>
            <c:set value="admin/article" var="targetUrl" scope="page"/>
            <c:set value="${filterQuery}" var="targetQuery" scope="page"/>
            <%@include file="/WEB-INF/templates/common/pagebar.jsp" %>
        </c:if>
    </form>
    <%@include file="/WEB-INF/templates/common/foot.jsp" %>
</div>
</body>
</html>
