<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@include file="/WEB-INF/templates/common/base.jsp" %>
    <link href="static/css/signin.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/offcanvas.css" rel="stylesheet" type="text/css"/>
    <link href="static/editor/css/editormd.css" rel="stylesheet" type="text/css"/>
    <script src="static/editor/editormd.js" type="text/javascript"></script>
    <script src="static/script/continue.js" type="text/javascript"></script>
    <title>${title} - 新建文章</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigator_admin.jsp" %>
<%--巨幕开始 --%>
<div class="container">
    <div class="list-group">
        <form action="admin/create" method="post">
            <div class="input-group" style="padding: 10px">
                <label class="input-group-addon" for="postTitle">文章标题</label>
                <input id="postTitle" name="postTitle" type="text" class="form-control input-sm" required autofocus/>
            </div>
            <div class="input-group" style="padding: 10px">
                <label class="input-group-addon" for="postExcerpt">文章简介</label>
                <input id="postExcerpt" name="postExcerpt" type="text" class="form-control input-sm" required/>
            </div>
            <div class="input-group" style="padding: 10px">
                <label>允许评论:</label>
                <div style="display:inline;padding: 10px">
                    <input id="commentOn" type="radio" name="commentStatus" value="true">
                    <label for="commentOn">是</label>
                </div>
                <div style="display:inline;padding: 10px">
                    <input id="commentOff" type="radio" name="commentStatus" value="false" checked="checked">
                    <label for="commentOff">否</label>
                </div>
            </div>
            <div class="input-group" style="padding: 10px">
                <label>文章分类:</label>
                <c:forEach items="${terms}" var="term">
                    <div style="display:inline;padding: 10px">
                        <input value="${term.termId }" id="terms${term.termId }" type="checkbox" name="termId">
                        <label for="terms${term.termId }">${term.name}</label>
                    </div>
                </c:forEach>
            </div>
            <!-- 加载编辑器的容器 -->
            <div id="editor" style="padding: 10px">
                <textarea style="display: none"></textarea>
            </div>
            <input class="btn btn-success" type="submit" name="publish" value="发布" style="width: 100px;">
            <input class="btn btn-info navbar-right" type="submit" name="draft" value="保存草稿" style="width: 100px;">
        </form>
    </div>
    <%@include file="/WEB-INF/templates/common/foot.jsp" %>
</div>
<%--巨幕结束 --%>
</body>
</html>