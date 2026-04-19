<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<link href="https://cdn.jsdelivr.net/npm/vditor@3.10.7/dist/index.css" rel="stylesheet"/>
	<script src="https://cdn.jsdelivr.net/npm/vditor@3.10.7/dist/index.min.js"></script>
	<title>${article.postTitle} - ${article.postExcerpt}</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigation_pages.jsp" %>
	<div class="container py-4">
		<div class="row justify-content-center">
			<div class="col-lg-9">
				<div class="card-modern">
					<div class="card-header-modern">
						<h3>${article.postTitle}<c:if test="${not empty article.postExcerpt}"> - ${article.postExcerpt}</c:if></h3>
					</div>
					<div class="card-body-modern">
						<div id="panel-body" style="display:none; opacity:0; transition:opacity 0.3s ease;">
							<script type="text/markdown">${article.postContent}</script>
						</div>
						<div class="md-loading" style="padding:40px; text-align:center; color:#aaa;">
							<i class="bi bi-arrow-repeat spin"></i> 加载中...
						</div>
					</div>
					<div class="card-footer-modern">
						<span><i class="bi bi-calendar3 me-1"></i><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${article.postDate}"/></span>
						<c:if test="${article.postAuthor == user.id}">
							<a class="btn btn-outline-modern btn-sm" href="admin/edit/${article.id}">
								<i class="bi bi-pencil me-1"></i>编辑
							</a>
						</c:if>
					</div>
				</div>
				<c:if test="${article.commentStatus=='open'}">
					<div class="card-modern">
						<div class="card-body-modern">
							<div id="SOHUCS" sid="${article.id}"></div>
						</div>
					</div>
					<script charset="utf-8" type="text/javascript" src="https://cy-cdn.kuaizhan.com/upload/changyan.js"></script>
					<script type="text/javascript">
						window.changyan.api.config({
							appid: '${commentId}',
							conf: '${commentKey}'
						});
					</script>
				</c:if>
			</div>
		</div>
		<%@include file="/WEB-INF/templates/common/foot.jsp" %>
	</div>
</body>
<script>
	$(function () {
		var el = document.getElementById('panel-body');
		var scriptTag = el.querySelector('script[type="text/markdown"]');
		if (scriptTag) {
			var md = scriptTag.textContent;
			scriptTag.remove();
			Vditor.preview(el, md, {
				markdown: { toc: true },
				hljs: { lineNumber: true },
				math: { engine: 'KaTeX' },
				mermaid: { enable: true },
				after: function() {
					setTimeout(function() {
						var loading = el.parentNode.querySelector('.md-loading');
						if (loading) loading.remove();
						el.style.display = '';
						el.offsetHeight;
						el.style.opacity = '1';
					}, 300);
				}
			});
		}
	});
</script>
</html>
