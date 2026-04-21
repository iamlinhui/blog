<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<link href="https://cdn.jsdelivr.net/npm/vditor@3.10.7/dist/index.css" rel="stylesheet"/>
	<script src="https://cdn.jsdelivr.net/npm/vditor@3.10.7/dist/index.min.js"></script>
	<script src="static/script/continue.js" type="text/javascript"></script>
	<title>${title} - 编辑文章</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigator_admin.jsp" %>
<div class="container py-4">
	<h4 class="fw-bold mb-4"><i class="bi bi-pencil-square me-2"></i>编辑文章</h4>
	<div class="card-modern">
		<div class="card-body-modern">
			<form action="admin/edit" method="post" class="form-modern">
				<input type="hidden" name="id" value="${postsId}">
				<div class="row g-3 mb-3">
					<div class="col-md-6">
						<label class="form-label" for="postTitle">文章标题</label>
						<input value="${article.postTitle}" id="postTitle" name="postTitle" type="text" class="form-control" required autofocus/>
					</div>
					<div class="col-md-6">
						<label class="form-label" for="postExcerpt">文章简介</label>
						<input value="${article.postExcerpt}" id="postExcerpt" name="postExcerpt" type="text" class="form-control" required/>
					</div>
				</div>
				<div class="d-flex flex-wrap gap-4 mb-3 align-items-start">
					<div>
						<label class="form-label">允许评论</label>
						<div class="d-flex gap-3" style="min-height:34px;align-items:center;">
							<div class="form-check">
								<input class="form-check-input" id="commentOn" type="radio" name="commentStatus" value="true" <c:if test="${article.commentStatus=='open'}">checked</c:if>/>
								<label class="form-check-label" for="commentOn">是</label>
							</div>
							<div class="form-check">
								<input class="form-check-input" id="commentOff" type="radio" name="commentStatus" value="false" <c:if test="${article.commentStatus=='close'}">checked</c:if>/>
								<label class="form-check-label" for="commentOff">否</label>
							</div>
						</div>
					</div>
					<div>
						<label class="form-label">文章分类</label>
						<div class="tag-picker">
							<div class="d-flex flex-wrap gap-2 align-items-center" style="min-height:34px;">
								<c:forEach items="${termName}" var="term">
									<input type="checkbox" name="termId" value="${term.termId}" id="tag_e_${term.termId}" class="tag-picker-cb" hidden <c:if test="${term.checked}">checked</c:if>>
								</c:forEach>
								<div class="tag-selected-list d-flex flex-wrap gap-2"></div>
								<button type="button" class="tag-add-btn" onclick="this.closest('.tag-picker').querySelector('.tag-picker-menu').classList.toggle('show')"><i class="bi bi-plus-lg"></i></button>
							</div>
							<div class="tag-picker-menu">
								<c:forEach items="${termName}" var="term">
									<label class="tag-picker-item" data-for="tag_e_${term.termId}">
										<span class="tag-picker-dot"></span>
										<span>${term.name}</span>
										<i class="bi bi-check2 tag-picker-check"></i>
									</label>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div id="vditor" class="mb-3"></div>
				<textarea id="postContent" name="postContent" style="display:none;">${article.postContent}</textarea>
				<div class="d-flex gap-3">
					<button class="btn btn-modern" type="submit" name="publish" value="publish"><i class="bi bi-send me-1"></i>发布</button>
					<button class="btn btn-outline-modern" type="submit" name="draft" value="draft"><i class="bi bi-save me-1"></i>保存</button>
				</div>
			</form>
		</div>
	</div>
	<%@include file="/WEB-INF/templates/common/foot.jsp" %>
</div>
<script>
function refreshTagPicker(picker) {
    var list = picker.querySelector('.tag-selected-list');
    list.innerHTML = '';
    picker.querySelectorAll('.tag-picker-cb').forEach(function(cb) {
        var item = picker.querySelector('[data-for="' + cb.id + '"]');
        if (cb.checked) {
            item.classList.add('selected');
            var chip = document.createElement('span');
            chip.className = 'tag-chip';
            chip.innerHTML = item.querySelector('span:nth-child(2)').textContent + '<i class="bi bi-x tag-remove"></i>';
            chip.querySelector('.tag-remove').onclick = function(e) {
                e.stopPropagation();
                cb.checked = false;
                refreshTagPicker(picker);
            };
            list.appendChild(chip);
        } else {
            item.classList.remove('selected');
        }
    });
}
document.addEventListener('click', function(e) {
    document.querySelectorAll('.tag-picker-menu.show').forEach(function(m) {
        if (!m.contains(e.target) && !e.target.closest('.tag-add-btn')) m.classList.remove('show');
    });
});
document.querySelectorAll('.tag-picker-item').forEach(function(item) {
    item.addEventListener('click', function() {
        var cb = document.getElementById(item.getAttribute('data-for'));
        cb.checked = !cb.checked;
        refreshTagPicker(item.closest('.tag-picker'));
    });
});
document.querySelectorAll('.tag-picker').forEach(function(p) { refreshTagPicker(p); });
</script>
</body>
</html>
