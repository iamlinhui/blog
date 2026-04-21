<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="_q" value="${not empty pageScope.targetQuery ? '?'.concat(pageScope.targetQuery) : ''}" scope="page"/>
<nav aria-label="Page navigation" class="mt-4">
	<ul class="pagination pagination-modern justify-content-center">
		<c:choose>
			<c:when test="${pageInfo.pageNum!=1}">
				<li class="page-item"><a class="page-link" href="${pageScope.targetUrl}${_q}">&laquo; 首页</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled"><span class="page-link">&laquo; 首页</span></li>
			</c:otherwise>
		</c:choose>
		<c:forEach items="${pageInfo.navigatepageNums}" var="navigatePageNum">
			<c:if test="${navigatePageNum==pageInfo.pageNum}">
				<li class="page-item active"><span class="page-link">${navigatePageNum}</span></li>
			</c:if>
			<c:if test="${navigatePageNum!=pageInfo.pageNum}">
				<li class="page-item"><a class="page-link" href="${pageScope.targetUrl}/${navigatePageNum}${_q}">${navigatePageNum}</a></li>
			</c:if>
		</c:forEach>
		<c:choose>
			<c:when test="${pageInfo.pageNum!=pageInfo.pages}">
				<li class="page-item"><a class="page-link" href="${pageScope.targetUrl}/${pageInfo.pages}${_q}">末页 &raquo;</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled"><span class="page-link">末页 &raquo;</span></li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>
