<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<title>${title} - 导航管理</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigator_admin.jsp" %>
<div class="container py-4">
	<div class="d-flex justify-content-between align-items-center mb-4">
		<h4 class="fw-bold mb-0"><i class="bi bi-signpost me-2"></i>导航管理</h4>
		<button class="btn btn-modern" data-bs-toggle="modal" data-bs-target="#exampleModal" data-whatever="添加导航栏">
			<i class="bi bi-plus-lg me-1"></i>添加
		</button>
	</div>
	<div class="table-modern">
		<table class="table table-hover mb-0">
			<thead>
				<tr>
					<th>#</th>
					<th>导航名</th>
					<th>导航别名 <small class="text-muted">(访问后缀)</small></th>
					<th>上移</th>
					<th>下移</th>
					<th>修改</th>
					<th>删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${terms}" var="term" varStatus="i">
					<tr>
						<th scope="row">${i.count}</th>
						<td>${term.name}</td>
						<td>${term.slug}</td>
						<td>
							<c:if test="${not i.first}">
								<a class="btn btn-sm btn-outline-modern" href="admin/up/${term.termId}"><i class="bi bi-arrow-up"></i></a>
							</c:if>
						</td>
						<td>
							<c:if test="${not i.last}">
								<a class="btn btn-sm btn-outline-modern" href="admin/down/${term.termId}"><i class="bi bi-arrow-down"></i></a>
							</c:if>
						</td>
						<td>
							<button type="button" data-termid="${term.termId}" data-slug="${term.slug}" data-name="${term.name}" class="btn btn-sm btn-outline-modern" data-bs-toggle="modal" data-bs-target="#exampleModal" data-whatever="修改导航信息">
								<i class="bi bi-pencil"></i>
							</button>
						</td>
						<td>
							<button data-bs-toggle="modal" data-bs-target="#mySmallModalLabel" data-termid="${term.termId}" data-name="${term.name}" class="btn btn-sm btn-danger-modern">
								<i class="bi bi-trash"></i>
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<%@include file="/WEB-INF/templates/common/foot.jsp" %>
</div>
<!-- 编辑/添加 Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"></h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="admin/navigation" method="post">
        <div class="modal-body form-modern">
          <input id="termId" type="hidden" name="termId" value="">
          <div class="mb-3">
            <label for="recipient-name" class="form-label">导航名</label>
            <input type="text" class="form-control" id="recipient-name" name="name" required/>
          </div>
          <div class="mb-3">
            <label for="message-text" class="form-label">导航别名 <small class="text-muted">(访问后缀)</small></label>
            <input type="text" class="form-control" id="message-text" name="slug" required/>
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
<!-- 删除确认 Modal -->
<div class="modal fade" id="mySmallModalLabel" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <form action="admin/delete" method="get">
        <input id="deleteTermId" type="hidden" value="" name="termId">
        <div class="modal-header">
          <h5 class="modal-title">确认删除?</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-4 text-muted">导航名:</div>
            <div id="body" class="col-8 fw-medium"></div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">否</button>
          <button type="submit" class="btn btn-danger-modern">是</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
    var exModal = document.getElementById('exampleModal');
    exModal.addEventListener('show.bs.modal', function (event) {
        $("#recipient-name").val("");
        $("#message-text").val("");
        $("#termId").attr("value", "");
        var button = $(event.relatedTarget);
        var recipient = button.data('whatever');
        var termId = button.data('termid');
        var name = button.data('name');
        var slug = button.data('slug');
        $(this).find('.modal-title').text(recipient);
        $("#termId").attr("value", termId);
        $("#recipient-name").val(name);
        $("#message-text").val(slug);
    });
    var delModal = document.getElementById('mySmallModalLabel');
    delModal.addEventListener('show.bs.modal', function (event) {
        $("#deleteTermId").attr("value", "");
        var button = $(event.relatedTarget);
        var termId = button.data('termid');
        var name = button.data('name');

        $("#deleteTermId").attr("value", termId);
        $("#body").html(name);
    });
</script>
</html>
