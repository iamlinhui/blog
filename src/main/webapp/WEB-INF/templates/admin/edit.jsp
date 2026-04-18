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
				<div class="row g-3 mb-3">
					<div class="col-auto">
						<label class="form-label">允许评论</label>
						<div class="d-flex gap-3">
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
					<div class="col">
						<label class="form-label">文章分类</label>
						<div class="tag-select-wrap">
							<div class="tag-select-trigger form-control" onclick="this.parentNode.classList.toggle('open')">
								<span class="tag-select-placeholder">请选择分类...</span>
								<i class="bi bi-chevron-down"></i>
							</div>
							<div class="tag-select-dropdown">
								<c:forEach items="${terms}" var="term">
									<label class="tag-select-option">
										<input type="checkbox" name="termId" value="${term.termId}" <c:if test="${term.checked}">checked</c:if> onchange="updateSelectedTags(this.closest('.tag-select-wrap'))">
										<span>${term.name}</span>
									</label>
								</c:forEach>
							</div>
							<div class="tag-selected-list d-flex flex-wrap gap-2 mt-2"></div>
						</div>
					</div>
				</div>
				<div id="vditor" class="mb-3"></div>
				<textarea id="postContent" name="postContent" style="display:none;">${article.postContent}</textarea>
				<div class="d-flex gap-3">
					<button class="btn btn-modern" type="submit" name="publish" value="publish"><i class="bi bi-send me-1"></i>发布</button>
					<button class="btn btn-outline-modern" type="submit" name="draft" value="draft"><i class="bi bi-save me-1"></i>保存草稿</button>
				</div>
			</form>
		</div>
	</div>
	<%@include file="/WEB-INF/templates/common/foot.jsp" %>
</div>
<script>
function updateSelectedTags(wrap) {
    var list = wrap.querySelector('.tag-selected-list');
    var placeholder = wrap.querySelector('.tag-select-placeholder');
    var checks = wrap.querySelectorAll('input[type=checkbox]');
    list.innerHTML = '';
    var count = 0;
    checks.forEach(function(cb) {
        if (cb.checked) {
            count++;
            var chip = document.createElement('span');
            chip.className = 'tag-chip';
            chip.innerHTML = cb.nextElementSibling.textContent + '<i class="bi bi-x tag-remove"></i>';
            chip.querySelector('.tag-remove').onclick = function() {
                cb.checked = false;
                updateSelectedTags(wrap);
            };
            list.appendChild(chip);
        }
    });
    placeholder.textContent = count > 0 ? '已选择 ' + count + ' 个分类' : '请选择分类...';
}
document.addEventListener('click', function(e) {
    document.querySelectorAll('.tag-select-wrap.open').forEach(function(w) {
        if (!w.contains(e.target)) w.classList.remove('open');
    });
});
document.querySelectorAll('.tag-select-wrap').forEach(function(w) { updateSelectedTags(w); });
</script>
</body>
</html>
