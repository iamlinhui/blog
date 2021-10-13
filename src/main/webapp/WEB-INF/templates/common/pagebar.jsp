<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav aria-label="Page navigation">
	<ul class="pagination .pagination-lg">
		<c:choose>
			<c:when test="${pageInfo.pageNum!=1 }">
				<li><a href="${pageScope.targetUrl}" aria-label="Previous"><span aria-hidden="true">&laquo;首页</span></a></li>
			</c:when>
			<c:otherwise>
				<li class="disabled"><span aria-hidden="true">&laquo;首页</span></li>
			</c:otherwise>
		</c:choose>
		<c:forEach items="${pageInfo.navigatepageNums }" var="navigatepageNum">
			<c:if test="${navigatepageNum==pageInfo.pageNum }">
				<li class="active"><span>${navigatepageNum }</span></li>
			</c:if>
			<c:if test="${navigatepageNum!=pageInfo.pageNum }">
				<li><a href="${pageScope.targetUrl}/${navigatepageNum}">${navigatepageNum }</a></li>
			</c:if>
		</c:forEach>
		<c:choose>
			<c:when test="${pageInfo.pageNum!=pageInfo.pages}">
				<li><a href="${pageScope.targetUrl}/${pageInfo.pages }" aria-label="Next"> <span aria-hidden="true">末页&raquo;</span></a></li>
			</c:when>
			<c:otherwise>
				<li class="disabled"><span aria-hidden="true">末页&raquo;</span></li>
			</c:otherwise>
		</c:choose>
	</ul>
</nav>
