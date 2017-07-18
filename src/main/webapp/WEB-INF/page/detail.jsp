<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>${article.postTitle } - ${article.postExcerpt}</title>
</head>
<body>
<%@include file="/WEB-INF/common/navigation_pages.jsp" %>
<%@include file="/WEB-INF/common/cursor.jsp" %>
	<%--容器开始 --%>
	<div class="container">
		<div class="list-group"></div>		
		<div class="list-group">		
			<div class="panel panel-info">
			  <div class="panel-heading">
			    <h3 class="panel-title">${article.postTitle} - ${article.postExcerpt}</h3>
			  </div>
			  <div class="panel-body">
			   <p>${article.postContent}</p>
			  </div>
			  <div class="panel-footer">
				  <span class="glyphicon glyphicon-calendar" aria-hidden="true">
				  	<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${article.postDate}"/>
			  	  </span>
			  	<c:if test="${article.postAuthor == user.id }">
			  		<a style="width: 150px;" class="btn btn-warning btn-xs navbar-right" href="admin/edit/${article.id }" role="button">
			  			<span class="glyphicon glyphicon-pencil" aria-hidden="true">编辑</span>
			  		</a>
			  	</c:if>
			  </div>
			</div>	
		</div>
		<c:if test="${article.commentStatus=='open'}">
		<!-- UY BEGIN -->
		<div class="list-group">
			<div id="uyan_frame"></div>
		</div>
		<script type="text/javascript" src="http://v2.uyan.cc/code/uyan.js?uid=${commentId }"></script>
		<!-- UY END -->
		
        </c:if>
		
	<%@include file="/WEB-INF/common/foot.jsp" %>
	</div>
	<%--容器结束 --%>
</body>
</html>