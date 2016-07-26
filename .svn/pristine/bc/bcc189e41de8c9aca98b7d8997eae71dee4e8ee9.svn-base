<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">

<title>运营需求编辑</title>

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
<script src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.js"></script>
<script src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=path%>/resources/validform/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-select.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/jquery-ui-min-1.9.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/Chinese-characters-to-Pinyin/py.js"></script>
<script src="<%=path%>/resources/js/new_version/script.js"></script>
<script src="<%=path %>/resources/laydate/laydate.js"></script>
<script>
laydate({
    elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
    event: 'focus' //响应事件。如果没有传入event，则按照默认的click
});
</script>
<script>
var maintype;
var mainstatus;
var mainfrequency;
$(document).ready(function(){
	
	getjobinfo();
});

function getjobinfo(){
	var url = '<%=path %>/requirementSplit/requirementsplitq?id=${id}';
	$.post(url,function(data){
		json = eval('('+data+')');
		$("#name").val(json.name);
		maintype=json.type;
		mainstatus=json.status;
		$("#content").val(json.content);
		$("#header").val(json.header);
		$("#jobdegree").val(json.jobDegree);
		$("#isalive").val(json.isalive);
		mainfrequency=json.jobFrequency;
		$("#plantime").val(json.planTime);
	});
	getjobtype();
	getjobstatus();
	getjobfrequency();
}

function getjobtype(){
	var url = '<%=path %>/rcode/listContentTypeq';
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
			$.each(json,function(i){
				if(json[i].id!=$("#type").val()){
					$("#type").append(
							'<option>'+json[i].name+'</option>');					
				}
			});
			$("#type").val(maintype);
	 	}
	});
}

function getjobstatus(){
	var url = '<%=path %>/rcode/listContentStatusq';
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
			$.each(json,function(i){
				if(json[i].id!=$("#status").val()){
					$("#status").append(
							'<option>'+json[i].name+'</option>');					
				}
			});
			$("#status").val(mainstatus);
	 	}
	});
}

function getjobfrequency(){
	var url = '<%=path%>/rcode/listContentFrequencyq';
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
			$("#jobFrequency").empty();
			$.each(json,function(i){
				if(json[i].id!=""){
					$("#jobFrequency").append(
							'<option>'+json[i].name+'</option>');					
				}
			});
			$("#jobFrequency").val(mainfrequency);
	 	}
	});
}
</script>

</head>
</head>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	
	 <!--内容开始-->
    <div  class="content_waikuang">
    	<div class="container-fluid search_select" style="padding-left: 0">

			<h2 class="content_title">任务编辑</h2>
			
			<form action="<%=path %>/requirement/updatejobInfo" id='formn' method="post">
				<input name = "id" type="hidden" value="${id }">
				<div class="form-group">
					<label class="content_zjnrcqlabel">
					<span class="content_zjnrcqspan"></span>任务名称</label> 
					<input type="text" disabled 
						class="form-control content_zjnrcqtext" errormsg="" name='name' id="name" >
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">任务类型</label> 
					<select class="index_kcglxtselect1" name="type" id="type">'
		  			</select>
				</div>
				
				<div class="form-group">
					<label for="exampleInputEmail1">任务内容</label> 
					<input type="text"
						class="form-control content_zjnrcqtext" name='content' id="content" >
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">任务负责人</label> 
					<input type="text"
						class="form-control content_zjnrcqtext" name='header' id="header" >
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">任务优先级</label> 
					<select class="index_kcglxtselect1" name="jobdegree" id="jobdegree">'
		  			<option value ="1">高</option>'
		  			<option value ="2">中</option>'
		  			<option value ="3">低</option>'
		  			</select>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">本周是否响应</label> 
					<select class="index_kcglxtselect1" name="isalive" id="isalive">'
		  			<option value ="1">是</option>'
		  			<option value ="2">否</option>'
		  			</select>
				</div>
				
				<div class="form-group">
					<label for="exampleInputEmail1">任务频率</label> 
					<select class="index_kcglxtselect1" name="jobFrequency" id="jobFrequency">'
		  			</select>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">任务完成时间</label> 
					<input class="laydate-icon" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" name='plantime' id="plantime">
				</div>
				<br>
				<div class="form-group">
					<label for="exampleInputEmail1">任务状态</label> 
					<select class="index_kcglxtselect1" name="status" id="status">'
		  			
		  			</select>
				</div>
			</form>
			
			<button type="button" class="btn btn-primary" onclick="submit()">提交</button>
			
		</div>
    </div>
</body>

<script type="text/javascript">
function submit(){

	var name = $("#name").val();
	var type = $("#type").val();
	var content = $("#content").val();
	var header = $("#header").val();
	var jobdegree = $("#jobdegree").val();
	var isalive = $("#isalive").val();
	var jobFrequency = $("#jobFrequency").val();
	var plantime = $("#plantime").val();
	var status = $("#status").val();
	var data = "id="+${id}+
		"&name="+name+"&content="+content+
		"&type="+type+"&planTime="+plantime+"&isalive="+isalive+
		"&jobFrequency="+jobFrequency+
		"&jobDegree="+jobdegree+"&header="+header+
		"&status="+status;
	
	var url = '<%=path %>/requirementSplit/updatejobInfo?'+data;
// 	alert(url);
	$.post(url,function(data){
		if(data=="01"){
			window.location.href="<%=path %>/requirementSplit/listContent_jobstatus";
		}else{
			var e1 = document.getElementById('error');			
			e1.style.display="";
			$("#error").html("错误！");
		}		
	});
}

</script> 
</html>

