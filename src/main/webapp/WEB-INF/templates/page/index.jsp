<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<link href="https://cdn.jsdelivr.net/npm/vditor@3.10.7/dist/index.css" rel="stylesheet"/>
	<script src="https://cdn.jsdelivr.net/npm/vditor@3.10.7/dist/index.min.js"></script>
	<title>${title} - ${subtitle}</title>
</head>
<body>
	<%@include file="/WEB-INF/templates/common/navigation_pages.jsp" %>
	<div class="container py-4">
		<div class="row g-4">
			<div class="col-lg-8">
				<c:if test="${empty pageInfo.list}">
					<div class="empty-state">
						<i class="bi bi-journal-text"></i>
						<p>暂无文章</p>
					</div>
				</c:if>
				<c:forEach var="article" items="${pageInfo.list}">
					<div class="card-modern">
						<div class="card-header-modern">
							<h3><a href="article/${article.id}">${article.postTitle}<c:if test="${not empty article.postExcerpt}"> - ${article.postExcerpt}</c:if></a></h3>
						</div>
						<c:if test="${not empty article.postContent}">
							<div class="card-body-modern">
								<div class="vditor-preview" style="display:none; opacity:0; transition:opacity 0.3s ease;">
									<script type="text/markdown">${article.postContent}</script>
								</div>
								<div class="md-loading" style="padding:20px; text-align:center; color:#aaa;">
									<i class="bi bi-arrow-repeat spin"></i> 加载中...
								</div>
							</div>
						</c:if>
						<div class="card-footer-modern">
							<span><i class="bi bi-calendar3 me-1"></i><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm" value="${article.postDate}"/></span>
							<c:if test="${article.postAuthor == user.id}">
								<a class="btn btn-outline-modern btn-sm" href="admin/edit/${article.id}">
									<i class="bi bi-pencil me-1"></i>编辑
								</a>
							</c:if>
						</div>
					</div>
				</c:forEach>
				<c:if test="${not empty pageInfo.list and pageInfo.pages!=1}">
					<c:set value="posts" var="targetUrl" scope="page"/>
					<%@include file="/WEB-INF/templates/common/pagebar.jsp" %>
				</c:if>
			</div>
			<div class="col-lg-4">
				<div class="sidebar-card">
					<div class="sidebar-title"><i class="bi bi-clock-history me-2"></i>近期文章</div>
					<div class="list-group list-group-flush">
						<c:forEach items="${recentlyInfo.list}" var="post">
							<a href="article/${post.id}" class="list-group-item list-group-item-action">${post.postTitle}</a>
						</c:forEach>
					</div>
				</div>
				<div class="sidebar-card">
					<div class="sidebar-title"><i class="bi bi-folder me-2"></i>文章分类</div>
					<div class="list-group list-group-flush">
						<c:forEach items="${terms}" var="term">
							<a href="classify/${term.slug}" class="list-group-item list-group-item-action <c:if test='${not empty slug and slug == term.slug}'>active</c:if>">${term.name}</a>
						</c:forEach>
					</div>
				</div>
				<div id="weather" class="sidebar-card" style="display:none;">
					<div class="sidebar-title"><i class="bi bi-cloud-sun me-2"></i>天气</div>
					<div class="list-group list-group-flush">
						<div class="list-group-item text-center py-3">
							<img alt="" src="" style="max-width:64px;">
						</div>
						<div class="list-group-item text-center"></div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="/WEB-INF/templates/common/foot.jsp" %>
	</div>
</body>
<script type="text/javascript">
	$(function () {
		$.ajax({
			url: "weather",
			type: "post",
			success: function (msg) {
				if (msg.status === 0) {
					$("#weather img").attr("src", "static/img/weather/" + msg.result.img + ".png");
					$("#weather .list-group-item:eq(1)").html(msg.result.city + " " + msg.result.weather);
					$("#weather").show();
				}
			},
			dataType: "json"
		});
		// Render markdown with Vditor
		document.querySelectorAll('.vditor-preview').forEach(function(el) {
			var scriptTag = el.querySelector('script[type="text/markdown"]');
			if (scriptTag) {
				var md = scriptTag.textContent;
				scriptTag.remove();
				var offscreen = document.createElement('div');
				offscreen.style.cssText = 'position:fixed;left:-9999px;top:0;visibility:hidden;width:' + el.parentNode.offsetWidth + 'px;';
				document.body.appendChild(offscreen);
				Vditor.preview(offscreen, md, {
					markdown: { toc: true },
					hljs: { lineNumber: true },
					math: { engine: 'KaTeX' },
					mermaid: { enable: true },
					after: function() {
						var timer = null;
						var observer = new MutationObserver(function() {
							clearTimeout(timer);
							timer = setTimeout(show, 500);
						});
						observer.observe(offscreen, { childList: true, subtree: true, attributes: true, characterData: true });
						timer = setTimeout(show, 500);
						function show() {
							observer.disconnect();
							el.innerHTML = offscreen.innerHTML;
							offscreen.remove();
							var loading = el.parentNode.querySelector('.md-loading');
							if (loading) loading.remove();
							el.style.display = '';
							el.offsetHeight;
							el.style.opacity = '1';
						}
					}
				});
			}
		});
	});
</script>
</html>
