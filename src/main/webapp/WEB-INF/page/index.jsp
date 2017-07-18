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
<title>${title } - ${subtitle }</title>
</head>
<body>
	<%@include file="/WEB-INF/common/navigation_pages.jsp" %>
	<%@include file="/WEB-INF/common/cursor.jsp" %>

	<%-- 容器结束 --%>
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="list-group" >
				<p class="pull-right visible-xs">
	            	<button style="width: 150px;" type="button" class="btn btn-info btn-xs" data-toggle="offcanvas">近期文章</button>
	          	</p>
			</div>
	        <div class="col-xs-12 col-sm-9">
				<c:if test="${empty pageInfo.list }">
					<div style="font-size: 40px;font-family: 楷体; text-align: center;">暂无文章</div>
				</c:if>
				<c:forEach var="article" items="${pageInfo.list }">
					<div class="panel panel-info">
					  <div class="panel-heading">
						<a href="article/${article.id }">
						    <h3 class="panel-title">${article.postTitle} - ${article.postExcerpt}</h3>
						</a>
					  </div>
					  <c:if test="${not empty article.postContent}">
						  <div class="panel-body">
						   <p>${article.postContent}</p>
						  </div>
					  </c:if>
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
				</c:forEach>
				<c:if test="${not empty pageInfo.list and pageInfo.pages!=1}">
					<c:set value="posts" var="targetUrl" scope="page"/>
					<%@include file="/WEB-INF/common/pagebar.jsp" %>
				</c:if>
			</div>
			<%--侧边框开始 --%>		
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
			  <%--第一个侧边框开始 --%>
	          <div class="list-group" >
	          	<li class="list-group-item list-group-item-info"><b>近期文章</b></li>
	            <c:forEach items="${recentlyInfo.list }" var="post">
	            	<a href="article/${post.id}">
	            	 <li class="list-group-item">
					    <span class="badge"><fmt:formatDate pattern="yyyy-MM-dd" value="${post.postDate }"/></span>
					    <c:forEach items="${post.term }" var="term">
					   		<span class="badge">${term.name }</span>
					    </c:forEach>
					    ${post.postTitle}
					  </li>
					</a>
	            </c:forEach>
	          </div>
	   		  <%--第一个侧边框结束 --%>
	   		  
	        </div>
	        <%--侧边框结束--%>		
        </div>
	<%@include file="/WEB-INF/common/foot.jsp" %>
	</div>
	<%--容器结束 --%>
</body>
<script type="text/javascript">
	$(document).ready(function () {
		  $('[data-toggle="offcanvas"]').click(function () {
		    $('.row-offcanvas').toggleClass('active')
		  });
		});
</script>
</html>