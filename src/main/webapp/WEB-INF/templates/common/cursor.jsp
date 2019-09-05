<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<div class="container">
	<ul class="nav nav-tabs nav-justified">
		<li role="presentation" <c:if test="${empty slug}">class="active"</c:if> style="border:0;background: #CFCFCF;"><a href="/">Home</a></li>
		<c:forEach items="${terms }" var="term">
			<li role="presentation" <c:if test="${not empty slug and slug == term.slug}">class="active"</c:if> style="border:0;background: #CFCFCF;"><a href="classify/${term.slug }">${term.name }</a></li>
		</c:forEach>
	</ul>
</div>
