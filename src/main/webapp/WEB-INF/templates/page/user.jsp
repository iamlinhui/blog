<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<link href="static/css/dashboard.css" rel="stylesheet" type="text/css" />
	<link href="static/css/signin.css" rel="stylesheet" type="text/css" />
	<link href="static/css/offcanvas.css" rel="stylesheet" type="text/css" />
	<title>${title} - 个人信息</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigation_pages.jsp" %>
<%--容器开始 --%>
<div class="container">
	<div class="list-group">
		<div class="row">

	        <div class="col-sm-3 col-md-2 sidebar">
	          <ul class="nav nav-sidebar">
	          	<div class="row placeholders">
	             <div class="placeholder">
	               <img src="" width="80" height="80" class="img-responsive" alt="">
	               <h4>${sessionScope.user.userLogin}</h4>
	              <span class="text-muted">${sessionScope.user.userNicename}</span>
	             </div>
	           </div>
	            <li class="active"><a><span class="glyphicon glyphicon-user" aria-hidden="true">&nbsp;个人信息</span></a></li>
	            <li><a target="_blank" href="https://en.gravatar.com/emails/"><span class="glyphicon glyphicon-picture" aria-hidden="true">&nbsp;Gravatar头像</span></a></li>
	          </ul>
	        </div>
        	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	           
	        	<div class="thumbnail">
			      <div class="caption">
	        		<img src="" width="80" height="80" class="img-responsive" alt="">
			        <h3>${sessionScope.user.userLogin}<small>(${sessionScope.user.userNicename})</small></h3>
			        <p>注册时间:</p>
			        <p><fmt:formatDate pattern="yyyy年MM月dd日 HH:mm:ss" value="${sessionScope.user.userRegistered}"/></p>
			        <p>登陆邮箱:</p>
			        <p id="email">${sessionScope.user.userEmail}</p>
			        <p>
			        	<button type="button" data-userid="${user.id}" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#exampleModal">
				      	  <span class="glyphicon glyphicon-pencil" aria-hidden="true">&nbsp;修改密码</span>
				    	</button>
			        	<button style="margin-left: 50px;" type="button" data-userid="${user.id}" data-nicename="${user.userNicename}"  class="btn btn-primary btn-sm" data-toggle="modal" data-target="#nameModal">
				      	  <span class="glyphicon glyphicon-info-sign" aria-hidden="true">&nbsp;修改昵称</span>
				    	</button>
			        </p>
			      </div>
			    </div>
          		
        	</div><%--col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main --%>
      	</div><%--<div class="row">结束 --%>
	</div><%--<div class="list-group">结束 --%>
	<%@include file="/WEB-INF/templates/common/foot.jsp" %>
</div>
<%--容器结束 --%>
<%--模态框开始 --%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">修改密码</h4>
      </div>
      <div class="modal-body">
        <form id="modifyPsw" action="user" method="post">
          <div class="form-group">
            <label for="password" class="control-label">新的密码:</label>
            <input type="password" class="form-control" id="password" name="password" required/>
          </div>
          <div class="form-group">
            <label for="repassword" class="control-label">确认密码:</label>
            <input type="password" class="form-control" id="repassword" name="repassword"  required/>
          </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="submit" class="btn btn-primary">提交</button>
	      </div>
        </form>
      </div>
    </div>
  </div>
</div>
<%--模态框结束 --%>
<%--模态框开始 --%>
<div class="modal fade" id="nameModal" tabindex="-1" role="dialog" aria-labelledby="nameModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">修改昵称</h4>
      </div>
      <div class="modal-body">
        <form action="user" method="post">
          <div class="form-group">
            <label for="userNiceName" class="control-label">新的昵称:</label>
            <input value="${sessionScope.user.userNicename}" type="text" class="form-control" id="userNiceName" name="userNiceName" required/>
          </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="submit" class="btn btn-primary">提交</button>
	      </div>
        </form>
      </div>
    </div>
  </div>
</div>
<%--模态框结束 --%>
</body>
<script src="static/script/md5.min.js"></script>
<script type="text/javascript">
$(function(){
	var hash = md5($("#email").text());
	console.log(hash);
	$("img").attr("src","http://www.gravatar.com/avatar/"+hash);
    $('#modifyPsw')
    .bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            password: {
                message:'密码无效',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '密码长度必须在6到30之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_.]+$/,
                        message: '密码格式不正确'
                    }
                }
            },
            repassword: {
                message: '密码无效',
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '长度必须在6到30之间'
                    },
                    identical: {//相同
                        field: 'password',
                        message: '两次密码不一致'
                    },
                    regexp: {//匹配规则
                        regexp: /^[a-zA-Z0-9_.]+$/,
                        message: '密码格式不正确'
                    }
                }
            }
        }
    })
});
</script>
</html>