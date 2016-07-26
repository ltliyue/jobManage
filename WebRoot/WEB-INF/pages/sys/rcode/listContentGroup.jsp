<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">

<title>运营需求管理系统</title>

<!-- Bootstrap core CSS -->
<link href="<%=path%>/resources/css/new_version/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/new_version/site.css"
	rel="stylesheet">
<link href="<%=path%>/resources/validform/css/style.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/dashboard.css" rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-select.css"
	rel="stylesheet">
<!-- Bootstrap core JavaScript
    ================================================== -->
<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
<script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>

<script src="<%=path%>/resources/js/new_version/site.js"></script>
<script
	src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.js"></script>
<script
	src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/validform/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-select.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/jquery-ui-min-1.9.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/Chinese-characters-to-Pinyin/py.js"></script>
<script src="<%=path%>/resources/js/new_version/script.js"></script>

<script>
var line=0;
$(document).ready(function(){
	initStatus("${containsMulti}");
	return false;
});

function OpenAddModel(){
	 $("#myModalLabel").text("增加");
	 $("#idh").attr("value","");
	 $("#name").attr("value","");
	 $("#add_0").attr("data-target","#addModal");
}

function initStatus(status) {
	$("#select1>dd").removeClass("active");
	$("#s"+status).attr("class","active");
}
</script>
</head>

<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
	<div class="content_waikuang">
		<div class="container-fluid search_select" style="padding-left: 0">

			<h2 class="content_title">运营需求管理</h2>
			<input id="taskId" type="text" style="display: none"
				value="${taskId}"></input>
			<div class="renwu_caozuo"
				style="text-align: right; margin-right: 15px">
				<a class="renwu_select" style="text-decoration:none" href="#"
					data-toggle="modal" id="add_0" data-target=""
					onclick="OpenAddModel()">添加</a>
			</div>
		</div>

		<div class="container-fluid search_select select-result"
			style="padding-left:0; margin:0 auto">

			<div class="table-responsive">
				<form action="<%=path%>/requirement/getResourceAll">
					<table class="table  table-striped content_rwgl"
						style="width: 80%;">
						<thead>
							<tr>
								<th>ID</th>
								<th>任务类型</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody id="tbody">
							<c:if test="${not empty accounts}">
								<c:forEach var="account" items="${accounts}" varStatus="ts">
									<tr>
										<td id="id_${account.id}" value="${account.id}">${ts.index+1 }</td>
										<td id="name_${account.id}" value="${account.name}">${account.name}</td>
										<td><a id="edit_${account.id}"
											tyle="text-decoration:none" href="#" data-toggle="modal"
											data-target="" onclick="openEditModel(${account.id });">编辑</a></td>
										<td><a id="del_${account.id}" tyle="text-decoration:none"
											href="#" data-toggle="modal" data-target=""
											onclick="openDelModel('${account.id }');">删除</a></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</form>
				<div class="listtablebot">${page.page.pageStr }</div>
			</div>
		</div>
		<!--内容结束-->
		<!-- Modal -->
		<div class="modal hide fade" id="addModal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">增加菜单</h4>
					</div>
					<form id="form" method="POST">
						<input type="hidden" name="parentid" id="parentid"> <input
							type="hidden" name="id" id="idh">
						<div class="modal-header">
							<div class="form-group">
								<label for="exampleInputEmail1">菜单名</label> <input type="text"
									class="form-control content_zjnrcqtext" name='name' id="name"
									placeholder="菜单名" datatype="*" nullmsg="请输入菜单名！" errormsg=" ">
							</div>
						</div>
					</form>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关
							闭</button>
						<button type="button" class="btn btn-primary" id="button"
							onclick="addNext()">增 加</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
function checkDetail(id) {
	window.location.href="<%=path%>/requirement/editGroup?id=" + id;
}

function addNext(){
	 var url = "${webRoot}/rcode/saveInfoGroup?name="+$("#name").val();
	 $.post(url,function(data){
		if(data=="01"){
			window.location.href="<%=path%>/rcode/listContentGroup";
		}else{
			var e1 = document.getElementById('error');			
			e1.style.display="";
			$("#error").html("错误！");
		}
	}); 
}

function openEditModel(id){
	var name1 = $("#name_"+id).attr("value");
	$("#button").text("修改");
	$("#button").attr("onclick","update("+id+")");
	$("#name").attr("value",name1);
	$("#edit_"+id).attr("data-target","#addModal");
}

function update(i){
	var id1 = $("#id_"+i).attr("value");
	 var url = "${webRoot}/rcode/updateInfoGroup?id="+id1+"&name="+$("#name").val();
	 $.post(url,function(data){
		if(data=="01"){
			window.location.href="<%=path%>/rcode/listContentGroup";
		}else{
			var e1 = document.getElementById('error');			
			e1.style.display="";
			$("#error").html("错误！");
		}
	}); 
}

function openDelModel(id){
	var id1 = $("#id_"+id).attr("value");
	var url = "${webRoot}/rcode/deletebyidGroup?id="+id1;
	$.post(url,function(data){
		if(data=="1"){
			window.location.href="<%=path%>/rcode/listContentGroup";
		} else {
			var e1 = document.getElementById('error');
			e1.style.display = "";
			$("#error").html("错误！");
		}
	});
}
</script>
<!-- 代码部分end -->
<!--分页列表-->
</html>