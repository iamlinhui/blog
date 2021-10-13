<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@include file="/WEB-INF/templates/common/base.jsp" %>
    <link href="static/css/signin.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/offcanvas.css" rel="stylesheet" type="text/css"/>
    <title>${ title} - 文章管理</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigator_admin.jsp" %>
<div class="container">
    <div class="list-group">
        <form action="admin/delete" method="post">
            <input type="hidden" name="pageNum" value="${pageNum}">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span>所有文章</span>
                    <input class="btn btn-xs btn-danger navbar-right" type="submit" value="删除文章" style="width: 100px;">
                </div>
                <div class="panel-body">
                    <ul class="list-group">
                        <c:forEach items="${pageInfo.list }" var="post">
                            <li class="list-group-item">
                                <span class="badge glyphicon glyphicon-time" aria-hidden="true">
                                    <fmt:formatDate pattern="yy年MM月dd日 " value="${post.postDate }"/>
                                </span>
                                <span class="badge">
                                    <c:if test="${post.postStatus =='publish'}">
                                        已发布
                                    </c:if>
                                    <c:if test="${post.postStatus =='draft'}">
                                        草稿
                                    </c:if>
                                </span>
                                <c:forEach items="${post.term }" var="term">
                                    <span class="badge">${term.name }</span>
                                </c:forEach>
                                <input value="${post.id }" id="${post.id}" type="checkbox" name="delete"/>
                                <a href="admin/edit/${post.id}">${post.postTitle} - ${post.postExcerpt}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${not empty pageInfo.list and pageInfo.pages!=1 }">
                            <c:set value="admin/article" var="targetUrl" scope="page"/>
                            <%@include file="/WEB-INF/templates/common/pagebar.jsp" %>
                        </c:if>
                    </ul>
                </div>
            </div>
        </form>
    </div>
    <%@include file="/WEB-INF/templates/common/foot.jsp" %>
</div>
</body>
</html>
