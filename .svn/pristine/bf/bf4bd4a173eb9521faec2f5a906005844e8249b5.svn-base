<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">

<title>互联网数据采集平台</title>

<!-- Bootstrap core CSS -->
  <link href="<%=path%>/resources/css/new_version/bootstrap.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="<%=path%>/resources/css/new_version/site.css" rel="stylesheet">
<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
<link href="<%=path %>/resources/css/dashboard.css" rel="stylesheet">
<link href="<%=path %>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="<%=path %>/resources/css/bootstrap-select.css" rel="stylesheet">
<!-- Bootstrap core JavaScript
    ================================================== -->
   <script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    
     <script src="<%=path%>/resources/js/new_version/site.js"></script>
	<script src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.js"></script>
	<script src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.zh-CN.js"></script>	
	<script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap-select.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery-ui-min-1.9.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/Chinese-characters-to-Pinyin/py.js"></script>
	 <script src="<%=path%>/resources/js/new_version/script.js"></script></head>
<script type="text/javascript">

	$(document).ready(function() {
		$(".partitionForm").Validform({
			datatype:{//传入自定义datatype类型，可以是正则，也可以是函数（函数内会传入一个参数）;
				"partition": /^[\d+,{0,1}]*$/,
			}
		});
		var demo = $("#updateForm").Validform({
			tiptype : 3,
			showAllError : true
		});
		var addDemo = $("#addForm").Validform({
			tiptype : 3,
			showAllError : true
		});
		//all();
		$("#updateButton").click(function() {
			var Status = demo.check(false);
			if (Status == true) {
				//如果通过，执行自己的方法
				update();
			}
		});
		$("#addButton").click(function() {
			var addStatus = addDemo.check(false);
			if (addStatus == true) {
				//如果通过，执行自己的方法
				insert();
			}
		});
	});

	function updateGroup(groupId, groupName, partitionId) {
		$("#groupId").attr("value", groupId);
		$("#groupName").attr("value", groupName);
		$("#partitionId").attr("value", partitionId);
	}

	
	function delGroup() {
		if ($("input[name='chk_list']:checked").length > 0) {
			if (confirm("确定终止选中任务吗？")) {
				var del_id = '';
				$("input[name='chk_list']:checked").each(function(val) {
					del_id += $(this).val() + ";";
					alert(del_id);
				});
				var url = "${webRoot}/partition/del?ids=" + del_id;
				$.post(url, function(data) {
					alert(data);
					window.location.href = "${webRoot}/partition/manage";
				}, "text");
			}
		} else {
			alert("请选择一项任务进行终止！");
		}
	};
	function update() {
		var url = "${webRoot}/partition/update";
		var data1 = $("#updateForm").serialize();

		$.post(url, data1, function(data) {
			alert(data);
			window.location.href = "${webRoot}/partition/manage";
		});
	}

	function insert(){
		var url = "${webRoot}/partition/add";
		var data1 = $("#addForm").serialize();

		$.post(url, data1, function(data) {
			alert(data);
			window.location.href = "${webRoot}/partition/manage";
		});
	}
	
</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	  <div  class="content_waikuang">  
<div class="container-fluid search_select select-result" style="padding-left:0">
<h2 class="content_title">${title}</h2>
	<div  class="renwu_caozuo">
		<a class="renwu_select" href="#" data-target="#addGroup" data-toggle="modal">新建分组</a>
		<a href="#" onclick="delGroup()">删除分组</a>
	</div>

<div class="container-fluid search_select select-result" style="padding-left:0; margin:0 auto">
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th><input type="checkbox" name="chk_all" id="chk_all"
							onclick="SelectAll()" /></th>
						<th>分组名称</th>
						<th>分组分区</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<form action="${webRoot }/partition/manage" id="form1">
					<tbody id="tbody">
						<c:if test="${not empty groups}">
							<c:forEach var="group" items="${groups }" varStatus="ts">
								<tr align="left">
									<td><input type='checkbox' name='chk_list'
										value="${group.groupId }"></td>
									<td>${group.groupName }</td>
									<td>${group.partitionId }</td>
									<td><fmt:formatDate value="${group.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>
										<button type="button"
											class="btn btn-primary btn-sm"
											data-target="#updateGroup" data-toggle="modal"
											onclick="updateGroup('${group.groupId }','${group.groupName }','${group.partitionId }')">编辑分组</button>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>

				</form>
			</table>
			<div class="listtablebot">${page.pageStr }</div>
		</div>
	</div>
</div>
	<!-- Modal -->
	<div class="modal hide fade" id="updateGroup" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">编辑任务</h4>
				</div>
				<form id="updateForm" method="POST" class="partitionForm">
					<div class="modal-body">
						<input type="hidden" name="partitionGroup.groupId" id="groupId" value="">
						<div class="form-group">
							<label class="content_zjnrcqlabel">分组名称</label> <input type="text" style="width: 80%"
								class="form-control content_zjnrcqselect" name='partitionGroup.groupName' id="groupName"
								datatype="*" nullmsg="请输入分组名称！" errormsg="请输入分组名称！"
								placeholder="">
						</div>
						<div class="form-group">
							<label class="content_zjnrcqlabel">拥有分区</label> <input type="text" style="width: 80%"
								class="form-control content_zjnrcqselect" name='partitionGroup.partitionId' id="partitionId"
								datatype="partition" nullmsg="请输入分区，以“,”分隔！" errormsg="请输入分区，以“,”分隔！"
								placeholder="">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关
					闭</button>
				<button type="button" class="btn btn-primary" id="updateButton">确认修改</button>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal hide fade" id="addGroup" tabindex="-2" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="addGroupLabel">新增分组</h4>
				</div>
				<form id="addForm" method="POST" class="partitionForm">
					<div class="modal-body">
						<div class="form-group">
							<label class="content_zjnrcqlabel">分组名称</label> <input type="text" style="width: 80%"
								class="form-control content_zjnrcqselect" name='partitionGroup.groupName'
								datatype="*" nullmsg="请输入分组名称！" errormsg="请输入分组名称！"
								placeholder="">
						</div>
						<div class="form-group">
							<label class="content_zjnrcqlabel">拥有分区</label> <input type="text" style="width: 80%"
								class="form-control content_zjnrcqselect" name='partitionGroup.partitionId'
								datatype="partition" nullmsg="请输入分区，以“,”分隔！" errormsg="请输入分区，以“,”分隔！"
								placeholder="">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关
					闭</button>
				<button type="button" class="btn btn-primary" id="addButton">保存分组</button>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
