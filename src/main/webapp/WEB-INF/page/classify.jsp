<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/WEB-INF/common/base.jsp"%>
<script type="text/javascript" src="static/script/jquery-3.2.1.js"></script>
<link href="static/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="static/css/signin.css" rel="stylesheet" type="text/css" />
<link href="static/css/offcanvas.css" rel="stylesheet" type="text/css" />
<script src="static/script/bootstrap.js"></script>
<script src="static/script/ie-emulation-modes-warning.js"></script>
<script src="static/script/ie10-viewport-bug-workaround.js"></script>
<style type="text/css">
a{
	color: #000;
}
</style>
<title>${ title} - <c:if test="${not empty termName }">${ termName}</c:if><c:if test="${empty termName }">暂无文章</c:if></title>
</head>
<body>
	<%@include file="/WEB-INF/common/navigation_pages.jsp" %>
	<%@include file="/WEB-INF/common/cursor.jsp" %>
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
				    <c:forEach items="${post.term }" var="term">
				   		<span class="badge">${term.name }</span>
				    </c:forEach>
				  </li>
			</c:forEach>
			<c:if test="${not empty pageInfo.list and pageInfo.pages!=1}">
				<c:set value="classify/${slug }" var="targetUrl" scope="page"/>
				<%@include file="/WEB-INF/common/pagebar.jsp" %>
			</c:if>
			</ul>
		<%@include file="/WEB-INF/common/foot.jsp" %>
	</div>
	<%--容器结束 --%>
</body>
</html>