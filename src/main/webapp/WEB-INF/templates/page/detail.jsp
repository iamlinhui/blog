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
						<div id="panel-body" style="visibility:hidden; height:0; overflow:hidden;">
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
						<script src="https://giscus.app/client.js"
								data-repo="iamlinhui/blog"
								data-repo-id="${giscusRepoId}"
								data-category="Q&A"
								data-category-id="${giscusCategoryId}"
								data-mapping="specific"
								data-term="${article.id}"
								data-strict="0"
								data-reactions-enabled="1"
								data-emit-metadata="0"
								data-input-position="bottom"
								data-theme="preferred_color_scheme"
								data-lang="zh-CN"
								crossorigin="anonymous"
								async>
						</script>
					</div>
				</div>
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
			// Render directly in the element (visibility:hidden + height:0 hides process but allows layout calculation for mermaid)
			Vditor.preview(el, md, {
				markdown: { toc: true },
				hljs: { lineNumber: true },
				math: { engine: 'KaTeX' },
				mermaid: { enable: true },
				after: function() {
					// Wait for mermaid async SVG rendering to stabilize
					var timer = null;
					var observer = new MutationObserver(function() {
						clearTimeout(timer);
						timer = setTimeout(show, 500);
					});
					observer.observe(el, { childList: true, subtree: true, attributes: true, characterData: true });
					timer = setTimeout(show, 500);
					function show() {
						observer.disconnect();
						var loading = el.parentNode.querySelector('.md-loading');
						if (loading) loading.remove();
						el.style.height = '';
						el.style.overflow = '';
						el.style.visibility = 'visible';
						el.style.opacity = '0';
						el.style.transition = 'opacity 0.3s ease';
						el.offsetHeight;
						el.style.opacity = '1';
					}
				}
			});
		}
	});
</script>
</html>
