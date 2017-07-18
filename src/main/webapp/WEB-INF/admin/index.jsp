<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="/WEB-INF/common/base.jsp"%>
<script type="text/javascript" src="static/script/jquery-3.2.1.js"></script>
<link href="static/css/bootstrap.admin.css" rel="stylesheet" type="text/css" />
<link href="static/css/offcanvas.css" rel="stylesheet" type="text/css" />
<link href="static/css/signin.css" rel="stylesheet" type="text/css" />
<link href="static/css/cover.css" rel="stylesheet" type="text/css" />
<script src="static/script/bootstrap.js"></script>
<script src="static/script/ie-emulation-modes-warning.js"></script>
<script src="static/script/ie10-viewport-bug-workaround.js"></script>
<title>${ title} - 后台页面</title>
</head>
<body background="http://orbbqhs3w.bkt.clouddn.com/<fmt:formatDate pattern="yyyy-MM-dd" value="<%=new Date()%>"/>.jpg">
	<div class="site-wrapper">
		<div class="site-wrapper-inner">
		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
			<div class="cover-container">
				<div class="masthead clearfix">
					<div class="inner">
						<%@include file="/WEB-INF/common/navigator_admin.jsp"%>
					</div>
				</div>
				
				<div class="inner cover">
					<h1 style="font-size: 80px; font-family: 楷体;" class="cover-heading">欢迎使用</h1>
				</div>
				<div class="mastfoot">
					<div class="inner">
						<%@include file="/WEB-INF/common/foot.jsp"%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>