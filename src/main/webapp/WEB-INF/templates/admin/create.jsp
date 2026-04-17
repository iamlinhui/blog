<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@include file="/WEB-INF/templates/common/base.jsp" %>
    <link href="https://cdn.jsdelivr.net/npm/vditor@3.10.7/dist/index.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/vditor@3.10.7/dist/index.min.js"></script>
    <script src="static/script/continue.js" type="text/javascript"></script>
    <title>${title} - 新建文章</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigator_admin.jsp" %>
<div class="container py-4">
    <h4 class="fw-bold mb-4"><i class="bi bi-plus-circle me-2"></i>新建文章</h4>
    <div class="card-modern">
        <div class="card-body-modern">
            <form action="admin/create" method="post" class="form-modern">
                <div class="row g-3 mb-3">
                    <div class="col-md-6">
                        <label class="form-label" for="postTitle">文章标题</label>
                        <input id="postTitle" name="postTitle" type="text" class="form-control" required autofocus/>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label" for="postExcerpt">文章简介</label>
                        <input id="postExcerpt" name="postExcerpt" type="text" class="form-control" required/>
                    </div>
                </div>
                <div class="row g-3 mb-3">
                    <div class="col-auto">
                        <label class="form-label">允许评论</label>
                        <div class="d-flex gap-3">
                            <div class="form-check">
                                <input class="form-check-input" id="commentOn" type="radio" name="commentStatus" value="true">
                                <label class="form-check-label" for="commentOn">是</label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" id="commentOff" type="radio" name="commentStatus" value="false" checked>
                                <label class="form-check-label" for="commentOff">否</label>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <label class="form-label">文章分类</label>
                        <div class="d-flex flex-wrap gap-3">
                            <c:forEach items="${terms}" var="term">
                                <div class="form-check">
                                    <input class="form-check-input" value="${term.termId}" id="terms${term.termId}" type="checkbox" name="termId">
                                    <label class="form-check-label" for="terms${term.termId}">${term.name}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div id="vditor" class="mb-3"></div>
                <textarea id="postContent" name="postContent" style="display:none;"></textarea>
                <div class="d-flex gap-3">
                    <button class="btn btn-modern" type="submit" name="publish" value="publish"><i class="bi bi-send me-1"></i>发布</button>
                    <button class="btn btn-outline-modern" type="submit" name="draft" value="draft"><i class="bi bi-save me-1"></i>保存草稿</button>
                </div>
            </form>
        </div>
    </div>
    <%@include file="/WEB-INF/templates/common/foot.jsp" %>
</div>
</body>
</html>
