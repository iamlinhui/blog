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
<script src="static/script/ie-emulation-modes-warning.js"></script>
<script src="static/script/ie10-viewport-bug-workaround.js"></script>
<style type="text/css">
a{
	color: #000;
}
</style>
<title>${ title} - 站点设置</title>
</head>
<body>
<%@include file="/WEB-INF/common/navigator_admin.jsp" %>
	<%--巨幕开始 --%>
	<div class="container">
		<div class="list-group">
			<form action="admin/website" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<img alt="" src="static/img/${logoPath }" class="img-rounded">
				    <label for="exampleInputFile">上传站点图标</label>
				    <input type="file" name="file" accept="image/*" multiple="multiple"/>
			   </div>
				<div class="form-group">
					<label class="col-md-2 control-label">站点注册用户身份</label> 
				</div>
				<div class="radio">
					<label class="radio-inline">
  						<input type="radio" name="userStatus" id="inlineRadio1" value="0" <c:if test="${userStatus==0 }">checked="checked"</c:if> >管理员
					</label>
					<label class="radio-inline">
					  <input type="radio" name="userStatus" id="inlineRadio2" value="1" <c:if test="${userStatus!=0 }">checked="checked"</c:if> >普通会员
					</label>
				</div>
				<div class="form-group">
					<label class="col-md-1 control-label">友言ID</label> 
					<input type="text" class="form-control" placeholder="友言评论用户ID" name="commentId" value="${commentId}" required autofocus/>
				</div>
				<div class="form-group">
					<label class="col-md-1 control-label">站点标题</label> 
					<input type="text" class="form-control" placeholder="站点标题" name="title" value="${title }" required/>
				</div>
				<div class="form-group">
					<label class="control-label">站点副标题</label> 
					<input type="text" class="form-control" placeholder="站点副标题" name="subtitle" value="${subtitle }"required/>
				</div>
				<button type="submit" class="btn btn-primary">提交上传</button>
			</form>
		</div>
		<%@include file="/WEB-INF/common/foot.jsp" %>
	</div>
	<%--巨幕结束 --%>
</body>
</html>