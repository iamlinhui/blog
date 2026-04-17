<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<div class="footer-modern">
    <a href="https://beian.miit.gov.cn" target="_blank">鄂ICP备2021018033号</a>
    &nbsp;&middot;&nbsp;
    Copyright &copy; <fmt:formatDate value="<%=new Date()%>" pattern="yyyy"/> by 林省之. All Rights Reserved.
</div>
<div hidden>${script}</div>
