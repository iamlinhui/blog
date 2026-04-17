<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<title>${title} - 用户管理</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigator_admin.jsp" %>
<div class="container py-4">
	<h4 class="fw-bold mb-4"><i class="bi bi-people me-2"></i>用户管理</h4>
	<div class="table-modern">
		<table class="table table-hover mb-0">
			<thead>
				<tr>
					<th>#</th>
					<th>用户名 <small class="text-muted">(不可修改)</small></th>
					<th>昵称</th>
					<th>邮箱</th>
					<th>注册时间</th>
					<th>角色</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="user" varStatus="i">
					<tr>
						<th scope="row">${i.count}</th>
						<td>${user.userLogin}</td>
						<td>${user.userNicename}</td>
						<td>${user.userEmail}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${user.userRegistered}"/></td>
						<td>
							<c:if test="${user.userStatus==0}"><span class="badge-modern">管理员</span></c:if>
							<c:if test="${user.userStatus==1}"><span class="badge-modern badge-status-publish">普通会员</span></c:if>
							<c:if test="${user.userStatus==2}"><span class="badge-modern badge-status-draft">已冻结</span></c:if>
						</td>
						<td>
							<button type="button" data-status="${user.userStatus}" data-userid="${user.id}" data-nicename="${user.userNicename}" data-loginname="${user.userLogin}" class="btn btn-sm btn-outline-modern" data-bs-toggle="modal" data-bs-target="#exampleModal">
								<i class="bi bi-pencil"></i>
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@include file="/WEB-INF/templates/common/foot.jsp" %>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"></h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="admin/user" method="post">
        <div class="modal-body form-modern">
          <input id="userId" type="hidden" name="id" value="">
          <div class="mb-3">
            <label for="recipient-name" class="form-label">修改别名</label>
            <input type="text" class="form-control" id="recipient-name" name="userNicename" required/>
          </div>
          <div class="mb-3">
            <label for="form-control" class="form-label">角色</label>
            <select id="form-control" name="userStatus" class="form-select">
              <option value="0">管理员</option>
              <option value="1">普通用户</option>
              <option value="2">冻结用户</option>
            </select>
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
var userModal = document.getElementById('exampleModal');
userModal.addEventListener('show.bs.modal', function (event) {
	$("#recipient-name").val("");
	$("#exampleModalLabel").text("");
	$("#userId").attr("value","");
	$("#form-control").val("0");

	var button = $(event.relatedTarget);
	var userid = button.data('userid');
	var nicename = button.data('nicename');
	var loginname = button.data('loginname');
	var status = button.data('status');

	$(this).find('.modal-title').text("修改用户" + loginname + "的信息");
	$("#userId").attr("value", userid);
	$("#recipient-name").val(nicename);
	$("#form-control").val(String(status));
});
</script>
</html>
