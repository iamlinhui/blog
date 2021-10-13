<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<link href="static/css/signin.css" rel="stylesheet" type="text/css" />
	<link href="static/css/offcanvas.css" rel="stylesheet" type="text/css" />
	<title>${ title} - 站点设置</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigator_admin.jsp" %>
	<%--巨幕开始 --%>
	<div class="container">
		<div class="list-group">
			<form action="admin/website" method="post">
				<div class="form-group">
					<label class="control-label">站点注册用户身份</label> 
				</div>
				<div class="radio">
					<label class="radio-inline">
  						<input type="radio" name="userStatus" id="inlineRadio1" value="0" <c:if test="${userStatus==0}">checked="checked"</c:if> >管理员
					</label>
					<label class="radio-inline">
					  <input type="radio" name="userStatus" id="inlineRadio2" value="1" <c:if test="${userStatus==1}">checked="checked"</c:if> >普通会员
					</label>
					<label class="radio-inline">
						<input type="radio" name="userStatus" id="inlineRadio3" value="2" <c:if test="${userStatus==2}">checked="checked"</c:if> >冻结账户
					</label>
				</div>
				<div class="form-group">
					<label class="control-label">评论的个人ID</label> 
					<input type="text" class="form-control" placeholder="评论用户ID" name="commentId" value="${commentId}" autofocus/>
				</div>
				<div class="form-group">
					<label class="control-label">天气查询AppCode</label> 
					<input type="text" class="form-control" placeholder="AppCode" name="appcode" value="${appcode}"/>
				</div>
				<div class="form-group">
					<label class="control-label">站点标题</label> 
					<input type="text" class="form-control" placeholder="站点标题" name="title" value="${title }"/>
				</div>
				<div class="form-group">
					<label class="control-label">站点副标题</label> 
					<input type="text" class="form-control" placeholder="站点副标题" name="subtitle" value="${subtitle }"/>
				</div>
				<button type="submit" class="btn btn-primary">提交上传</button>
			</form>
		</div>
		<%@include file="/WEB-INF/templates/common/foot.jsp" %>
	</div>
	<%--巨幕结束 --%>
</body>
</html>
