<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<link href="static/css/signin.css" rel="stylesheet" type="text/css" />
	<link href="static/css/offcanvas.css" rel="stylesheet" type="text/css" />
	<link href="static/editor/css/editormd.css" rel="stylesheet" type="text/css"/>
	<script src="static/editor/lib/marked.min.js"></script>
	<script src="static/editor/lib/prettify.min.js"></script>
	<script src="static/editor/lib/raphael.min.js"></script>
	<script src="static/editor/lib/underscore.min.js"></script>
	<script src="static/editor/lib/sequence-diagram.min.js"></script>
	<script src="static/editor/lib/flowchart.min.js"></script>
	<script src="static/editor/lib/jquery.flowchart.min.js"></script>
	<script src="static/editor/editormd.js"></script>
	<title>${title} - ${subtitle}</title>
</head>
<body>
	<%@include file="/WEB-INF/templates/common/navigation_pages.jsp" %>
	<%-- 容器结束 --%>
	<div class="container">
		<div class="row row-offcanvas row-offcanvas-right">
			<div class="list-group" >
				<p class="pull-right visible-xs">
	            	<button style="width: 150px;" type="button" class="btn btn-info btn-xs" data-toggle="offcanvas">近期文章</button>
	          	</p>
			</div>
	        <div class="col-xs-12 col-sm-9">
				<c:if test="${empty pageInfo.list }">
					<div style="font-size: 40px;font-family: 楷体; text-align: center;">暂无文章</div>
				</c:if>
				<c:forEach var="article" items="${pageInfo.list }">
					<div class="panel panel-info">
					  <div class="panel-heading">
						<a href="article/${article.id }">
						    <h3 class="panel-title">${article.postTitle} - ${article.postExcerpt}</h3>
						</a>
					  </div>
					  <c:if test="${not empty article.postContent}">
						  <div id="panel-body" class="panel-body">
						   <textarea style="display:none;">${article.postContent}</textarea>
						  </div>
					  </c:if>
					  <div class="panel-footer">
						  <span class="glyphicon glyphicon-calendar" aria-hidden="true">
						  	<fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${article.postDate}"/>
					  	  </span>
					  	<c:if test="${article.postAuthor == user.id }">
					  		<a style="width: 150px;" class="btn btn-warning btn-xs navbar-right" href="admin/edit/${article.id }" role="button">
					  			<span class="glyphicon glyphicon-pencil" aria-hidden="true">编辑</span>
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
			<%--侧边框开始 --%>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
			  <%--第一个侧边框开始 --%>
	          <div class="list-group" >
	          	<li class="list-group-item list-group-item-info"><b>近期文章</b></li>
	            <c:forEach items="${recentlyInfo.list }" var="post">
	            	<a href="article/${post.id}">
	            	 <li class="list-group-item">
<%--					    <span class="badge"><fmt:formatDate pattern="yyyy/MM/dd" value="${post.postDate }"/></span>
					    <c:forEach items="${post.term }" var="term">
					   	 <span class="badge">${term.name }</span>
					    </c:forEach>--%>
					   ${post.postTitle}
					  </li>
				</a>
	            </c:forEach>
	          </div>

			  <%--第一个侧边框开始 --%>
			  <div class="list-group" >
				  <li class="list-group-item list-group-item-info"><b>文章分类</b></li>
				  <c:forEach items="${terms }" var="term">
					  <a href="classify/${term.slug }">
					  <li class="list-group-item" role="presentation" <c:if test="${not empty slug and slug == term.slug}">class="active"</c:if> >
						  ${term.name }
					  </li></a>
				  </c:forEach>
			  </div>

	   		   <%--第二个侧边框开始 --%>
	   		  <div id="weather" class="list-group" hidden>
	   		  	<li class="list-group-item list-group-item-info"><b>天气</b></li>
	   		  	 <li class="list-group-item">
		   		  	<img alt="" src="">
	   		  	</li>
	   		  	<li class="list-group-item">
	   		  	</li>
	   		  </div>

	        </div>
	        <%--侧边框结束--%>
        </div>
	<%@include file="/WEB-INF/templates/common/foot.jsp" %>
	</div>
	<%--容器结束 --%>
</body>
<script type="text/javascript">
    $(function () {

        $('[data-toggle="offcanvas"]').click(function () {
            $('.row-offcanvas').toggleClass('active')
        });

        $.ajax({
            url: "weather",
            type: "post",
            success: function (msg) {
                if (msg.status === 0) {
                    $("#weather img").attr("src", "static/img/weather/" + msg.result.img + ".png");
					$("#weather li:eq(2)").html(msg.result.city + msg.result.weather);
                    $("#weather").show();
                }
            },
            dataType: "json"
        });

        editormd.markdownToHTML("panel-body", {
                htmlDecode: "style,script,iframe",
                emoji: false,
                taskList: true,
			    atLink: false,
                tocm: true,
                tex: true,
                flowChart: true,
                sequenceDiagram: true,
                codeFold: true
            }
        )
    });
</script>
</html>
