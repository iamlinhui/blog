<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<link href="static/css/signin.css" rel="stylesheet" type="text/css" />
	<link href="static/css/offcanvas.css" rel="stylesheet" type="text/css" />
	<link href="static/editor/css/editormd.css" rel="stylesheet" type="text/css"/>
	<script src="static/editor/lib/marked.min.js"></script>
	<script src="static/editor/lib/prettify.min.js"></script>
	<script src="static/editor/lib/raphael.min.js"></script>
	<script src="static/editor/lib/underscore.min.js"></script>
	<script src="static/editor/lib/sequence-diagram.min.js"></script>
	<script src="static/editor/lib/flowchart.min.js"></script>
	<script src="static/editor/lib/jquery.flowchart.min.js"></script>
	<script src="static/editor/editormd.js"></script>
	<title>${article.postTitle } - ${article.postExcerpt}</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigation_pages.jsp" %>
<%--
<%@include file="/WEB-INF/templates/common/cursor.jsp" %>
--%>
	<%--容器开始 --%>
	<div class="container">
		<div class="list-group"></div>		
		<div class="list-group">		
			<div class="panel panel-info">
			  <div class="panel-heading">
			    <h3 class="panel-title">${article.postTitle} - ${article.postExcerpt}</h3>
			  </div>
			  <div id="panel-body" class="panel-body">
				  <textarea style="display:none;">${article.postContent}</textarea>
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
			<div class="list-group">
				<div id="SOHUCS" sid="${article.id}"></div>
			</div>
			<script charset="utf-8" type="text/javascript" src="https://cy-cdn.kuaizhan.com/upload/changyan.js"></script>
			<script type="text/javascript">
				window.changyan.api.config({
					appid: '${commentId}',
					conf: '${commentKey}'
				});
			</script>
       	</c:if>
		
		<%@include file="/WEB-INF/templates/common/foot.jsp" %>
	</div>
	<%--容器结束 --%>
</body>
<script>
	$(function () {
		editormd.markdownToHTML("panel-body", {
					htmlDecode: "style,script,iframe",
					emoji: true,
					taskList: true,
					tocm: true,
					tex: true,
					flowChart: true,
					sequenceDiagram: true,
					codeFold: true
				}
		)
	})
</script>
</html>
