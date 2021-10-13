<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<%@include file="/WEB-INF/templates/common/base.jsp"%>
	<link href="static/css/signin.css" rel="stylesheet" type="text/css" />
	<link href="static/css/offcanvas.css" rel="stylesheet" type="text/css" />
	<title>${title } - 导航管理</title>
</head>
<body>
<%@include file="/WEB-INF/templates/common/navigator_admin.jsp" %>
	<%--巨幕开始 --%>
	<div class="container">
		<div class="list-group">
			<button id="addNavigator" style="width: 100px;" type="button" class="btn btn-success btn-sm " data-toggle="modal" data-target="#exampleModal" data-whatever="添加导航栏">
			 <span class="glyphicon glyphicon-plus" aria-hidden="true">&nbsp;添加</span>
			</button>
			<div class="table-responsive">
			    <table class="table table-bordered table-striped table-hover" >
			      <thead>
			        <tr class="warning">
			          <th></th>
			          <th>导航名</th>
			          <th>导航别名<small>(访问后缀)</small></th>
			          <th>上移</th>
			          <th>下移</th>
			          <th>修改</th>
			          <th>删除</th>
			        </tr>
			      </thead>
			      <tbody>
			      	<c:forEach items="${terms }" var="term" varStatus="i">
				        <tr>
				          <th class="text-nowrap" scope="row">${i.count}</th>
				          <td>${term.name }</td>
				          <td>${term.slug }</td>
				          <td>
				          	<c:if test="${not i.first}">
					          	<a class="btn btn-primary btn-sm" href="admin/up/${term.termId }" role="button">
					        		<span class="glyphicon glyphicon-arrow-up" aria-hidden="true">&nbsp;上移</span>
					        	</a>
				        	</c:if>
				          </td>
				          <td>
				         	 <c:if test="${not i.last}">
				          	<a class="btn btn-warning btn-sm" href="admin/down/${term.termId }" role="button">
				        		<span class="glyphicon glyphicon-arrow-down" aria-hidden="true">&nbsp;下移</span>
				        	</a>
				        	</c:if>
				          </td>
				          <td>
				          	<button type="button" data-termid="${term.termId }" data-slug="${term.slug }" data-name="${term.name }" class="btn btn-info btn-sm" data-toggle="modal" data-target="#exampleModal" data-whatever="修改导航信息">
				          	<span class="glyphicon glyphicon-pencil" aria-hidden="true">&nbsp;修改</span>
				          	</button>
				          </td>
				          <td>
				          	<button data-toggle="modal" data-target="#mySmallModalLabel" data-termid="${term.termId }" data-name="${term.name }" class="btn btn-danger btn-sm"  role="button">
				        		<span class="glyphicon glyphicon-trash" aria-hidden="true">&nbsp;删除</span>
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
        <form action="admin/navigation" method="post">
          <input id="termId" type="hidden" name="termId" value="">
          <div class="form-group">
            <label for="recipient-name" class="control-label">导航名:</label>
            <input type="text" class="form-control" id="recipient-name" name="name" required/>
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">导航别名<small>(访问后缀)</small>:</label>
            <input type="text" class="form-control" id="message-text" name="slug" required/>
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
<%--删除确认模态框 --%>
<!-- Modal -->
<div class="modal fade bs-example-modal-sm" id="mySmallModalLabel" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
    	<form action="admin/delete" method="get">
    	<input id="deleteTermId" type="hidden" value="" name="termId">
	    <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="gridSystemModalLabel">确认删除?</h4>
     	</div>
     	 <div class="modal-body">
     		<div class="row">
     			<div class="col-md-4">导航名:</div>
          		<div id="body" class="col-md-4"></div>
     		</div>
     	 </div>
	    <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">否</button>
	        <button type="submit" class="btn btn-primary">是</button>
	      </div>
	     </form>
    </div>
  </div>
</div>
<%--删除确认模态框 --%>
</body>
<script type="text/javascript">
    $('#exampleModal').on('show.bs.modal', function (event) {
        $("#recipient-name").val("");
        $("#message-text").val("");
        $("#termId").attr("value", "");

        var button = $(event.relatedTarget);
        var recipient = button.data('whatever');
        var termId = button.data('termid');
        //console.log(termId);
        var name = button.data('name');
        var slug = button.data('slug');

        var modal = $(this)
        modal.find('.modal-title').text(recipient);
        $("#termId").attr("value", termId);
        $("#recipient-name").val(name);
        $("#message-text").val(slug);
    });
    $('#mySmallModalLabel').on('show.bs.modal', function (event) {
        $("#deleteTermId").attr("value", "");

        var button = $(event.relatedTarget);
        var termId = button.data('termid');
        //console.log(termId);
        var name = button.data('name');

        $("#deleteTermId").attr("value", termId);
        $("#body").html(name);
    });
</script>
</html>