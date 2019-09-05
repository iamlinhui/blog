<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<link href="static/css/signin.css" rel="stylesheet" type="text/css" />
	<link href="static/css/offcanvas.css" rel="stylesheet" type="text/css" />
	<title>${ title} - <c:if test="${not empty termName }">${ termName}</c:if><c:if test="${empty termName }">暂无文章</c:if></title>
</head>
<body>
	<%@include file="/WEB-INF/templates/common/navigation_pages.jsp" %>
<%--
	<%@include file="/WEB-INF/templates/common/cursor.jsp" %>
--%>
	<%-- 容器开始 --%>
	<div class="container">
		<div class="list-group" ></div>
			<c:if test="${empty pageInfo.list }">
				<div style="font-size: 40px;font-family: 楷体; text-align: center;">暂无该类文章</div>
			</c:if>
			<ul class="list-group">
			<c:forEach items="${pageInfo.list }" var="post">
				  <li class="list-group-item">
				    <a href="article/${post.id}">${post.postTitle} - ${post.postExcerpt }</a>
				    <%-- <span class="badge">${post.commentCount }</span> --%>
				    <span class="badge"><fmt:formatDate pattern="yyyy-MM-dd" value="${post.postDate }"/></span>
				   <%-- <c:forEach items="${post.term }" var="term">
				   		<span class="badge">${term.name }</span>
				    </c:forEach> --%>
				  </li>
			</c:forEach>
			<c:if test="${not empty pageInfo.list and pageInfo.pages!=1}">
				<c:set value="classify/${slug }" var="targetUrl" scope="page"/>
				<%@include file="/WEB-INF/templates/common/pagebar.jsp" %>
			</c:if>
			</ul>
		<%@include file="/WEB-INF/templates/common/foot.jsp" %>
	</div>
	<%--容器结束 --%>
</body>
</html>
