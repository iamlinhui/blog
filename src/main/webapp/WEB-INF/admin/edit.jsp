<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"> </script>
<style type="text/css">
a{
	color: #000;
}
</style>
<title>${title } - 编辑文章</title>
</head>
<body>
<%@include file="/WEB-INF/common/navigator_admin.jsp" %>
	<%--巨幕开始 --%>
	<div class="container">
		<div class="list-group">
			<form action="admin/edit" method="post">
				<input type="hidden" name="id" value="${postsId }">
				<div class="input-group">
				  <span class="input-group-addon" id="basic-addon3">文章标题</span>
				  <input value="${article.postTitle }" name="postTitle" type="text" class="form-control input-sm" id="basic-url" aria-describedby="basic-addon3">
				</div>
				<br>
				<div class="input-group">
				  <span class="input-group-addon" id="basic-addon3">文章简介</span>
				  <input value="${article.postExcerpt}" name="postExcerpt" type="text" class="form-control input-sm" id="basic-url" aria-describedby="basic-addon3">
				</div>
				<br>
				<div>
		          <label for="commentStatus">允许评论</label>
		          <input id="commentStatus" type="checkbox" name="commentStatus"  <c:if test="${article.commentStatus =='open'}">checked="checked"</c:if> />
		        </div>
				<br>
				<div>
				  <label>文章分类 :</label>
		          <c:forEach items="${terms }" var="term">
		         	<input value="${term.termId }" id="terms${term.termId }" type="checkbox" name="termId" <c:if test="${term.checked }">checked="checked"</c:if> >
		          	<label  for="terms${term.termId }">${term.name }</label>
		          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		          </c:forEach>
				</div>
				<br>
			 	<!-- 加载编辑器的容器 -->
				 <div style="width:100%;">
	   				 <script id="editor" type="text/plain" style="height: 1080px;" name="postContent">${article.postContent}</script>
				</div>
				<br>
				<input class="btn btn-success" type="submit" name="publish" value="发布" style="width: 100px;">
				<input class="btn btn-info navbar-right" type="submit" name="draft" value="保存草稿" style="width: 100px;">
			 </form>
		</div>
		<%@include file="/WEB-INF/common/foot.jsp" %>
	</div>
	<%--巨幕结束 --%>
</body>
<script type="text/javascript">
	window.UEDITOR_HOME_URL ="ueditor/";
	var ue = UE.getEditor('editor');
</script>
</html>