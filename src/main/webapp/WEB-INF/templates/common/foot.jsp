<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<div class="footer text-center">
    <em style="font-family: 楷体;">Copyright © <fmt:formatDate value="<%=new Date()%>" pattern="yyyy"/> by 林省之. All Rights Reserved.</em>
</div>
<div class="footer text-center">
    <em style="font-family: 楷体;"><a href="http://www.miibeian.gov.cn/" target="_blank">鄂ICP备16017858号-2</a></em>
</div>
<div hidden>${script}</div>
<style type="text/css">
    a {
        color: #000;
    }

    @media only screen and (max-width: 768px) {
        #panel-body img {
            width: 100%;
        }
    }
</style>