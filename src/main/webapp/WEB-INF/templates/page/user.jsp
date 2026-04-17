<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<title>${title} - 个人信息</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigation_pages.jsp" %>
<div class="container py-4">
	<div class="row justify-content-center">
		<div class="col-lg-8">
			<div class="card-modern">
				<div class="card-header-modern">
					<h3><i class="bi bi-person-circle me-2"></i>个人信息</h3>
				</div>
				<div class="card-body-modern">
					<div class="row mb-3">
						<div class="col-sm-3 text-muted fw-medium">用户名</div>
						<div class="col-sm-9">${sessionScope.user.userLogin}</div>
					</div>
					<div class="row mb-3">
						<div class="col-sm-3 text-muted fw-medium">昵称</div>
						<div class="col-sm-9">${sessionScope.user.userNicename}</div>
					</div>
					<div class="row mb-3">
						<div class="col-sm-3 text-muted fw-medium">注册时间</div>
						<div class="col-sm-9"><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${sessionScope.user.userRegistered}"/></div>
					</div>
					<div class="row mb-3">
						<div class="col-sm-3 text-muted fw-medium">邮箱</div>
						<div class="col-sm-9" id="email">${sessionScope.user.userEmail}</div>
					</div>
					<div class="d-flex gap-3 mt-4">
						<button type="button" class="btn btn-modern" data-bs-toggle="modal" data-bs-target="#exampleModal">
							<i class="bi bi-key me-1"></i>修改密码
						</button>
						<button type="button" class="btn btn-outline-modern" data-bs-toggle="modal" data-bs-target="#nameModal">
							<i class="bi bi-pencil me-1"></i>修改昵称
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/templates/common/foot.jsp" %>
</div>
<!-- 修改密码 Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">修改密码</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form id="modifyPsw" action="user" method="post">
        <div class="modal-body form-modern">
          <div class="mb-3">
            <label for="password" class="form-label">新的密码</label>
            <input type="password" class="form-control" id="password" name="password" required/>
          </div>
          <div class="mb-3">
            <label for="repassword" class="form-label">确认密码</label>
            <input type="password" class="form-control" id="repassword" name="repassword" required/>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
          <button type="submit" class="btn btn-modern">提交</button>
        </div>
      </form>
    </div>
  </div>
</div>
<!-- 修改昵称 Modal -->
<div class="modal fade" id="nameModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">修改昵称</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="user" method="post">
        <div class="modal-body form-modern">
          <div class="mb-3">
            <label for="userNiceName" class="form-label">新的昵称</label>
            <input value="${sessionScope.user.userNicename}" type="text" class="form-control" id="userNiceName" name="userNiceName" required/>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
          <button type="submit" class="btn btn-modern">提交</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
$(function () {
	$('#modifyPsw').on('submit', function(e) {
		var pw = $('#password').val();
		var rpw = $('#repassword').val();
		if (pw.length < 6 || pw.length > 30) {
			alert('密码长度必须在6到30之间');
			e.preventDefault(); return false;
		}
		if (pw !== rpw) {
			alert('两次密码不一致');
			e.preventDefault(); return false;
		}
		if (!/^[a-zA-Z0-9_.]+$/.test(pw)) {
			alert('密码格式不正确');
			e.preventDefault(); return false;
		}
	});
});
</script>
</html>
