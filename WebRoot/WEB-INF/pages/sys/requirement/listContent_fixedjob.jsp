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
<script src="<%=path%>/resources/laydate/laydate.js"></script>
<script>
laydate({
    elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
});
</script>
<script>
var total;
var currentPage;
var pageSize;
var pageCount;
var type=0;
$(document).ready(function(){
	initStatus("${containsMulti}");
	getjobproperties();
	getjobtype();
	getjobstatus();
	return false;
});

function getjobproperties(){
	var url = '<%=path%>/rcode/listContentq';
	console.log(url);
	$.ajax({
	 	type : "POST",
	 	url : url,
	 	contentType: "application/json; charset=utf-8",
	 	dataType: "text",
	 	data : "",
	 	success : function(data) {
	 		console.log(data);
	 		json = eval('('+data+')');
			$("#properties").empty();
			$("#properties").append('<option value ="" >请选择</option>');			
			$.each(json,function(i){
				if(json[i].id!=""){
					$("#properties").append(
							'<option value ="'+json[i].name+'">'+json[i].name+'</option>');					
				}
			});
	 	}
	});
}

function getjobtype(){
	
	var url = '<%=path%>/rcode/listContentTypeq';
	console.log(url);
	$.ajax({
	 	type : "POST",
	 	url : url,
	 	contentType: "application/json; charset=utf-8",
	 	dataType: "text",
	 	data : "",
	 	success : function(data) {
	 		console.log(data);
	 		json = eval('('+data+')');
			$("#type").empty();
			$("#type").append('<option value ="" >请选择</option>');
			$.each(json,function(i){
				if(json[i].id!=""){
					$("#type").append(
							'<option value ="'+json[i].name+'">'+json[i].name+'</option>');					
				}
			});
	 	}
	});
}
function getjobstatus(){
	var url = '<%=path%>/rcode/listContentStatusq';
	console.log(url);
	$.ajax({
	 	type : "POST",
	 	url : url,
	 	contentType: "application/json; charset=utf-8",
	 	dataType: "text",
	 	data : "",
	 	success : function(data) {
	 		console.log(data);
	 		json = eval('('+data+')');
			$("#status").empty();
			$("#status").append('<option value ="" >请选择</option>');
			$.each(json,function(i){
				if(json[i].id!=$("#status").val()){
					$("#status").append(
							'<option value ="'+json[i].name+'">'+json[i].name+'</option>');			
				}
			});
			$("#status").val(mainstatus);
	 	}
	});
}
function initStatus(status) {
	$("#select1>dd").removeClass("active");
	$("#s"+status).attr("class","active");
}
function searchinfo(){
// 	$("#hiddenName").val($("#inputName").val());
// 	alert($("#hiddenName").val());
	$("#form1").submit();
}
function excelexport(){
	if(confirm("确定要导出？")){
		var url = "<%=path%>/requirementSplit/export?bm=固定任务";
		$("#exportF").attr("action",url);
		$("#exportF").submit();
	};
}
</script>
</head>

<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
	<div class="content_waikuang" style="width: 100%;">
		<div class="container-fluid search_select">

			<h2 class="content_title">固定任务</h2>
			<input id="taskId" type="text" style="display: none"
				value="${taskId}"></input>
		</div>
		<!-- 		<div class="row" style="clear: both"> -->
		<form action="" id="exportF" method="post">
			<input type="hidden" id="bmoc">
		</form>
		<div class="span" style="margin-right: 10px">
			<form action="<%=path%>/requirementSplit/listContent_fixedjob"
				id="form1">
				<table style="clear: both">
					<tr>
						<td>任务性质 <select class="index_kcglxtselect1"
							name="requireMentSplit.properties" id="properties">'
						</select></td>
						<td>任务类型 <select class="index_kcglxtselect1"
							name="requireMentSplit.type" id="type">'
						</select></td>
						<td><span>任务名称</span> <input id="inputName"
							name="requireMentSplit.name" type="text"></td>
					</tr>
					<tr>
						<td><span>本周是否响应</span><select class="index_kcglxtselect1"
							name="isalive" id="requireMentSplit.isalive">'
								<option value=""></option>
								<option value="1">是</option>
								<option value="2">否</option></td>
						<td><span>任务状态</span> <select class="index_kcglxtselect1"
							name="requireMentSplit.status" id="status">
						</select></td>
						<td><span>任务负责人</span> <input id="inputHeader"
							name="requireMentSplit.header" type="text"></td>
					</tr>
					<tr>
						<td>提出时间 <input class="laydate-icon"
							onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"
							name='requireMentSplit.proposeTimeF' id="proposeTime"
							style="width: 150px" nullmsg="请输入提出时间！"> &nbsp-&nbsp <input
							class="laydate-icon"
							onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"
							name='requireMentSplit.proposeTimeT' id="proposeTime"
							style="width: 150px" nullmsg="请输入提出时间！"></td>
						<td>执行时间 <input class="laydate-icon"
							onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"
							name='requireMentSplit.doTimeF' id="doTime" style="width: 150px"
							nullmsg="请输入执行时间！"> &nbsp-&nbsp <input
							class="laydate-icon"
							onclick="laydate({istime: true, format: 'YYYY-MM-DD'})"
							style="width: 150px" name='requireMentSplit.doTimeT'
							id="proposeTime" style="width: 150px" nullmsg="请输入执行时间！"></td>
						<td><div class="renwu_caozuo"
								style="text-align: right; margin-right: 15px">
								<!-- 									<button class="renwu_select" onclick="search()">搜索</button> -->
								<!-- 									<button class="renwu_select" onclick="excelexport()">导出excel</button> -->
							</div></td>
					</tr>
				</table>
				<div class="renwu_caozuo"
					style="text-align: right; margin-right: 15px">
					<a class="renwu_select" onclick="searchinfo()" href="#">搜索</a> <a
						class="renwu_select" onclick="excelexport()" href="#">导出excel</a>
				</div>
				<table class="table  table-striped content_rwgl"
					style="width: 100%;">
					<thead>
						<tr>
							<th style="width: 20px">序号</th>
							<th style="width: 60px">任务名称</th>
							<th>任务内容</th>
							<th style="width: 30px">任务类型</th>

							<th style="width: 30px">本周是否响应</th>
							<th style="width: 30px">任务频率</th>
							<th style="width: 30px">任务优先级</th>
							<!-- <th>提取数据内容</th>-->
							<th style="width: 40px">任务性质</th>
							<th style="width: 40px">对应客户</th>
							<th style="width: 40px">任务提出人</th>
							<th style="width: 40px">提出时间</th>
							<th style="width: 40px">执行时间</th>

							<th style="width: 40px">负责人</th>
							<th style="width: 40px">任务状态</th>
							<th style="width: 60px">提取数据范围</th>
							<th style="width: 50px">数据是否满足需求</th>
							<th style="width: 50px">是否进行数据采集</th>
							<th style="width: 50px">是否需要数据报告</th>
							<th style="width: 20px"></th>
							<th style="width: 20px"></th>
							<th style="width: 20px"></th>
						</tr>
					</thead>
					<tbody id="tbody">

						<c:if test="${not empty accounts}">
							<c:forEach var="account" items="${accounts}" varStatus="ts">
								<tr>
									<td>${ts.index+1 }</td>
									<td>${account.name}</td>
									<td>${account.content}</td>
									<td>${account.type }</td>

									<td>${account.isalive}</td>
									<td>${account.jobFrequency}</td>
									<td>${account.jobDegree}</td>

									<td>${account.properties}</td>
									<td>${account.customer}</td>
									<td>${account.proposeUser}</td>
									<td>${account.proposeTime}</td>
									<td>${account.doTime}</td>

									<td>${account.header}</td>
									<td>${account.status}</td>

									<td>${account.jobRange}</td>
									<td>${account.ismatch}</td>
									<td>${account.iscollection}</td>
									<td>${account.isreport}</td>
									<c:if test="${account.plantform  =='1'}">

										<td><c:if test="${account.type  =='数据采集'}">
												<a id="edit_${account.rid}" tyle="text-decoration:none"
													href="#" data-toggle="modal" data-target=""
													onclick="showlist('${account.rid }');">查看站点</a>
											</c:if></td>
										<td><a href="javascript:"
											onclick="checkDetail('${account.id }');">编辑</a></td>
										<td><a href="javascript:"
											onclick="del('${account.id }');">删除</a></td>
									</c:if>

								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</form>
			<div class="listtablebot">${page.page.pageStr }</div>
		</div>
		<!-- 		</div> -->
		<!--内容结束-->
		<!-- Modal -->
		<div class="modal hide fade" id="addModal" tabindex="-1"
			style="width: 60% ;text-align: center;">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">站点列表</h4>
					</div>
					<table class="table  table-striped content_rwgl"
						style="width: 100%; margin-top: 20px"" >
						<thead>
							<tr>
								<th>序号</th>
								<th>网站名称</th>
								<th>URL</th>
								<th>站点类型</th>
								<th>描述</th>
								<th>创建人</th>
								<th>创建时间</th>
							</tr>
						</thead>
						<tbody id="tlistbody">
						</tbody>
					</table>

					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关
							闭</button>
						<!-- <button type="button" class="btn btn-primary" id="button"
							onclick="addNext()">增 加</button> -->
					</div>
				</div>
			</div>
		</div>
</body>

<script type="text/javascript">
var line=0;
function showlist(id){
	line=0;
	getdemandlist(id);
	$("#edit_"+id).attr("data-target","#addModal");
}

function getdemandlist(id){
	var url = "<%=path%>/requirement/requirementDemand?id="+id;
	console.log(url);
	$.ajax({
	 	type : "POST",
	 	url : url,
	 	contentType: "application/json; charset=utf-8",
	 	dataType: "text",
	 	data : "",
	 	success : function(data) {
	 		console.log(data);
	 		json = eval('('+data+')');
	 		console.log(json);
			$("#tlistbody").empty();
			$.each(json,function(i){
					line++;
					$("#tlistbody").append('<tr>'
							+'<td>'+line+'</td>'
							+'<td>'+json[i].name+'</td>'
							+'<td>'+json[i].url+'</td>'
							+'<td>'+json[i].siteType+'</td>'
							+'<td>'+json[i].siteDescription+'</td>'
							+'<td>'+json[i].publisherId+'</td>'
							+'<td>'+json[i].publishTime+'</td>'
							+'"</tr>');					
			});
	 	}
	});
}

function checkDetail(id) {
	window.location.href="<%=path%>/requirementSplit/fixedjobedit?id=" + id;
}

function del(id) {
	var url="<%=path%>/requirementSplit/deletebyid?id=" + id;
	$.post(url,function(data){
		if(data=="1"){
			window.location.href="<%=path%>/requirementSplit/listContent_fixedjob";
							} else {
								alert("系统错误");
							}
						});
	}
</script>
<!-- 代码部分end -->
<!--分页列表-->
</html>