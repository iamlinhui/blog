<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<title>${title} - 站点设置</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigator_admin.jsp" %>
<div class="container py-4">
	<h4 class="fw-bold mb-4"><i class="bi bi-sliders me-2"></i>站点设置</h4>
	<div class="card-modern">
		<div class="card-body-modern">
			<form action="admin/website" method="post" class="form-modern">
				<div class="mb-4">
					<label class="form-label">站点注册用户身份</label>
					<div class="d-flex gap-4">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="userStatus" id="inlineRadio1" value="0" <c:if test="${userStatus==0}">checked</c:if>>
							<label class="form-check-label" for="inlineRadio1">管理员</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="userStatus" id="inlineRadio2" value="1" <c:if test="${userStatus==1}">checked</c:if>>
							<label class="form-check-label" for="inlineRadio2">普通会员</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="userStatus" id="inlineRadio3" value="2" <c:if test="${userStatus==2}">checked</c:if>>
							<label class="form-check-label" for="inlineRadio3">冻结账户</label>
						</div>
					</div>
				</div>
				<div class="mb-3">
					<label class="form-label">评论的个人ID</label>
					<input type="text" class="form-control" placeholder="评论用户ID" name="commentId" value="${commentId}" autofocus/>
				</div>
				<div class="mb-3">
					<label class="form-label">天气查询AppCode</label>
					<input type="text" class="form-control" placeholder="AppCode" name="appcode" value="${appcode}"/>
				</div>
				<div class="mb-3">
					<label class="form-label">站点标题</label>
					<input type="text" class="form-control" placeholder="站点标题" name="title" value="${title}"/>
				</div>
				<div class="mb-3">
					<label class="form-label">站点副标题</label>
					<input type="text" class="form-control" placeholder="站点副标题" name="subtitle" value="${subtitle}"/>
				</div>
				<button type="submit" class="btn btn-modern"><i class="bi bi-check-lg me-1"></i>保存设置</button>
			</form>
		</div>
	</div>
	<%@include file="/WEB-INF/templates/common/foot.jsp" %>
</div>
</body>
</html>
