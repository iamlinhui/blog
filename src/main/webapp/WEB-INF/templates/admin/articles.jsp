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
    <form action="admin/delete" method="post">
        <input type="hidden" name="pageNum" value="${pageNum}">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h4 class="fw-bold mb-0"><i class="bi bi-file-text me-2"></i>所有文章</h4>
            <button class="btn btn-danger-modern" type="submit"><i class="bi bi-trash me-1"></i>删除选中</button>
        </div>
        <div class="card-modern">
            <div class="list-group list-group-flush">
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
            <c:set value="admin/article" var="targetUrl" scope="page"/>
            <%@include file="/WEB-INF/templates/common/pagebar.jsp" %>
        </c:if>
    </form>
    <%@include file="/WEB-INF/templates/common/foot.jsp" %>
</div>
</body>
</html>
