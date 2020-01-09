<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<link href="static/css/signin.css" rel="stylesheet" type="text/css" />
	<link href="static/css/offcanvas.css" rel="stylesheet" type="text/css" />
	<title>${title } - 用户管理</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigator_admin.jsp" %>
	<%--巨幕开始 --%>
	<div class="container">
		<div class="list-group">
			<div class="table-responsive">
			    <table class="table table-bordered table-striped table-hover" >
			      <thead>
			        <tr class="info">
			          <th></th>
			          <th>用户登陆名<small>(不可修改)</small></th>
			          <th>昵称</th>
			          <th>邮箱</th>
			          <th>注册时间</th>
			          <th>角色</th>
			          <th>修改</th>
			        </tr>
			      </thead>
			      <tbody>
			      	<c:forEach items="${userList }" var="user" varStatus="i">
				        <tr>
				          <th class="text-nowrap" scope="row">${i.count}</th>
				          <td>${user.userLogin }</td>
				          <td>${user.userNicename }</td>
				          <td>${user.userEmail}</td>
				          <td><fmt:formatDate pattern="yyyy年MM月dd日HH:mm:ss" value="${user.userRegistered}"/></td>
				          <td>
							  <c:if test="${user.userStatus==0}">管理员</c:if>
							  <c:if test="${user.userStatus==1}">普通会员</c:if>
							  <c:if test="${user.userStatus==2}">已冻结</c:if>
				          </td>
				         <td>
				          	<button type="button" data-status="${user.userStatus }" data-userid="${user.id}" data-nicename="${user.userNicename}" data-loginname="${user.userLogin}" class="btn btn-info btn-sm" data-toggle="modal" data-target="#exampleModal">
				          	<span class="glyphicon glyphicon-pencil" aria-hidden="true">修改</span>
				          	</button>
				          </td>
				        </tr>
			        </c:forEach>
			      </tbody>
			    </table>
			  </div>
		</div>
		<%@include file="/WEB-INF/templates/common/foot.jsp" %>
	</div>
<%--巨幕结束 --%>
<%--模态框开始 --%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel"></h4>
      </div>
      <div class="modal-body">
        <form action="admin/user" method="post">
          <input id="userId" type="hidden" name="id" value="">
          <div class="form-group">
            <label for="recipient-name" class="control-label">修改别名:</label>
            <input type="text" class="form-control" id="recipient-name" name="userNicename" required/>
          </div>
          <div class="form-group">
          	<label for="form-control" class="control-label">角色</label>
            <select id="form-control" name="userStatus" class="form-control">
			  <option value="0" >管理员</option>
			  <option value="1" >普通用户</option>
			  <option value="2" >冻结用户</option>
			</select>
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
<script type="text/javascript">
$('#exampleModal').on('show.bs.modal', function (event) {
	$("#recipient-name").val("");
	$("#exampleModalLabel").val("");
	$("#userId").attr("value","");
	$("select :eq(0)").prop("selected",false);
	$("select :eq(1)").prop("selected",false);
	$("select :eq(2)").prop("selected",false);

	  var button = $(event.relatedTarget);
	  var userid = button.data('userid');
	  var nicename = button.data('nicename');
	  var loginname = button.data('loginname');
	  var status = button.data('status');
	  
	  var modal = $(this)
	  modal.find('.modal-title').text("修改用户"+loginname+"的信息");
	  $("#userId").attr("value",userid);
	  $("#recipient-name").val(nicename);
	  
	  if(status===0){
		  $("select :eq(0)").prop("selected",true);
	  }else if (status===1){
		  $("select :eq(1)").prop("selected",true);
	  }else if (status===2){
		  $("select :eq(2)").prop("selected",true);
	  }
	  
	});
</script>
</html>
