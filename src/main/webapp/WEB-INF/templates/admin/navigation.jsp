<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<script src="https://cdn.jsdelivr.net/npm/sortablejs@1.15.6/Sortable.min.js"></script>
	<title>${title} - 导航管理</title>
	<style>
		.nav-item-card {
			display: flex;
			align-items: center;
			background: #fff;
			border: 1px solid #e8ecf1;
			border-radius: 10px;
			padding: 14px 18px;
			margin-bottom: 10px;
			transition: box-shadow 0.2s, border-color 0.2s;
			cursor: grab;
			user-select: none;
		}
		.nav-item-card:hover { border-color: #c0c8d4; box-shadow: 0 2px 12px rgba(0,0,0,0.06); }
		.nav-item-card:active { cursor: grabbing; }
		.nav-item-card .drag-handle { color: #bbb; font-size: 1.2rem; margin-right: 14px; flex-shrink: 0; }
		.nav-item-card .nav-info { flex: 1; min-width: 0; }
		.nav-item-card .nav-name { font-weight: 600; font-size: 0.95rem; color: #333; }
		.nav-item-card .nav-slug { font-size: 0.82rem; color: #999; margin-top: 2px; }
		.nav-item-card .nav-actions { display: flex; gap: 6px; flex-shrink: 0; margin-left: 12px; }
		.sortable-ghost { opacity: 0.4; background: #f0f4ff; border-color: #7c9ef7; border-style: dashed; }
		.sortable-drag { box-shadow: 0 8px 24px rgba(0,0,0,0.12); }
		.reorder-toast {
			position: fixed; bottom: 24px; left: 50%; transform: translateX(-50%);
			background: #333; color: #fff; padding: 8px 20px; border-radius: 8px;
			font-size: 0.85rem; z-index: 9999; opacity: 0; transition: opacity 0.3s;
			pointer-events: none;
		}
		.reorder-toast.show { opacity: 1; }
	</style>
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
	<p class="text-muted mb-3" style="font-size:0.85rem;"><i class="bi bi-grip-vertical me-1"></i>拖拽调整导航顺序</p>
	<div id="navSortable">
		<c:forEach items="${terms}" var="term">
			<div class="nav-item-card" data-id="${term.termId}">
				<span class="drag-handle"><i class="bi bi-grip-vertical"></i></span>
				<div class="nav-info">
					<div class="nav-name">${term.name}</div>
					<div class="nav-slug">/${term.slug}</div>
				</div>
				<div class="nav-actions">
					<button type="button" data-termid="${term.termId}" data-slug="${term.slug}" data-name="${term.name}" class="btn btn-sm btn-outline-modern" data-bs-toggle="modal" data-bs-target="#exampleModal" data-whatever="修改导航信息">
						<i class="bi bi-pencil"></i>
					</button>
					<button data-bs-toggle="modal" data-bs-target="#mySmallModalLabel" data-termid="${term.termId}" data-name="${term.name}" class="btn btn-sm btn-danger-modern">
						<i class="bi bi-trash"></i>
					</button>
				</div>
			</div>
		</c:forEach>
	</div>
	<div id="reorderToast" class="reorder-toast">排序已保存</div>
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
    // Drag and drop sorting
    Sortable.create(document.getElementById('navSortable'), {
        animation: 200,
        handle: '.drag-handle',
        ghostClass: 'sortable-ghost',
        dragClass: 'sortable-drag',
        onEnd: function () {
            var ids = [];
            document.querySelectorAll('#navSortable .nav-item-card').forEach(function(el) {
                ids.push(parseInt(el.getAttribute('data-id')));
            });
            $.ajax({
                url: 'admin/navigation/reorder',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(ids),
                success: function () {
                    var toast = document.getElementById('reorderToast');
                    toast.classList.add('show');
                    setTimeout(function() { toast.classList.remove('show'); }, 1500);
                }
            });
        }
    });
    // Edit/Add modal
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
    // Delete modal
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
