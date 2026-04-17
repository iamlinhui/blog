<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<title>${title} - <c:if test="${not empty termName}">${termName}</c:if><c:if test="${empty termName}">暂无文章</c:if></title>
</head>
<body>
	<%@include file="/WEB-INF/templates/common/navigation_pages.jsp" %>
	<div class="container py-4">
		<div class="row justify-content-center">
			<div class="col-lg-9">
				<c:if test="${not empty termName}">
					<h4 class="mb-4 fw-bold"><i class="bi bi-folder me-2"></i>${termName}</h4>
				</c:if>
				<c:if test="${empty pageInfo.list}">
					<div class="empty-state">
						<i class="bi bi-journal-text"></i>
						<p>暂无该类文章</p>
					</div>
				</c:if>
				<div class="card-modern">
					<div class="list-group list-group-flush">
						<c:forEach items="${pageInfo.list}" var="post">
							<a href="article/${post.id}" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center py-3">
								<span class="fw-medium">${post.postTitle} - ${post.postExcerpt}</span>
								<span class="badge-modern"><fmt:formatDate pattern="yyyy-MM-dd" value="${post.postDate}"/></span>
							</a>
						</c:forEach>
					</div>
				</div>
				<c:if test="${not empty pageInfo.list and pageInfo.pages!=1}">
					<c:set value="classify/${slug}" var="targetUrl" scope="page"/>
					<%@include file="/WEB-INF/templates/common/pagebar.jsp" %>
				</c:if>
			</div>
		</div>
		<%@include file="/WEB-INF/templates/common/foot.jsp" %>
	</div>
</body>
</html>
