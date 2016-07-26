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

$(document).ready(function(){
	var url = '<%=path %>/requirement/requirementq?id=${id}';
	$.post(url,function(data){
		json = eval('('+data+')');
		$("#name").val(json.name);
		
		$("#name").val(json.name);
		$("#content").val(json.content);
		$("#customer").val(json.customer);
		$("#proposeUser").val(json.proposeUser);
		$("#proposeTime").val(json.proposeTime);
		$("#requestTime").val(json.requestTime);
		getjobproperties(json.properties);
		getjobtype(json.type);
		if(json.status=='被驳回'||json.status=='被拒绝'){
			$("#button_resubmit").attr("style","");
		}
	});
});
function getjobproperties(propertiestemp){
	var url = '<%=path %>/rcode/listContentq';
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
			$.each(json,function(i){
				if(json[i].id!=""){
					$("#properties").append(
							'<option>'+json[i].name+'</option>');					
				}
			});
			$("#properties").val(propertiestemp);
	 	}
	});
}
	
function getjobtype(typetemp){
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
			$("#type").val(typetemp);
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

			<h2 class="content_title">需求编辑</h2>
			
			<form id='formn' method="post">
				<input name = "id" type="hidden" value="${id }">
				<div class="form-group">
					<label class="content_zjnrcqlabel">
					<span class="content_zjnrcqspan"></span>需求性质</label> 
					<select class="index_kcglxtselect1" name="properties" id="properties">
		  			</select>
		  		</div>
				<div class="form-group">
					<label for="exampleInputEmail1">需求类型</label> 
					<select class="index_kcglxtselect1" name="type" id="type">
		  			</select>
		  		</div>
				
				<div class="form-group">
					<label for="exampleInputEmail1">需求名称</label> 
					<input type="text"
						class="form-control content_zjnrcqtext" name='name' id="name" nullmsg="请输入需求名称！" >
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">对应客户</label> 
					<input type="text"
						class="form-control content_zjnrcqtext" name='customer' id="customer" nullmsg="请输入对应客户！" >
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">提出人</label> 
					<input type="text"
						class="form-control content_zjnrcqtext" name='proposeUser' id="proposeUser" nullmsg="请输入提出人！" >
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">提出时间</label> 
					<input class="laydate-icon" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" name='proposeTime' id="proposeTime" nullmsg="请输入提出时间！">
				</div>
				<br>
				<div class="form-group">
					<label for="exampleInputEmail1">要求时间</label> 
					<input class="laydate-icon" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" name='requestTime' id="requestTime" nullmsg="请输入要求时间！">
				</div>
				<br>
				<div class="form-group">
					<label for="exampleInputEmail1">需求内容</label> 
					<input type="text"
						class="form-control content_zjnrcqtext" name='content' id="content" nullmsg="请输入需求内容！" >
				</div>
			</form>
			
			<button type="button" class="btn btn-primary" onclick="submit()">修改</button>
			<button type="button" id="button_resubmit"  style="display: none" class="btn btn-primary" onclick="resubmit()">重新提交审核</button>
		</div>
    </div>
</body>

<script type="text/javascript">
function submit(){
	var properties = $("#properties").val();
	var type = $("#type").val();
	var name = $("#name").val();
	var content = $("#content").val();
	var customer = $("#customer").val();
	var propose_user = $("#proposeUser").val();
	var propose_time = $("#proposeTime").val();
	var request_time = $("#requestTime").val();
	var data = "id="+${id}+
		"&properties="+properties+"&type="+type+
		"&name="+name+"&content="+content+
		"&customer="+customer+"&propose_user="+propose_user+
		"&propose_time="+propose_time+"&request_time="+request_time;
	var url = "<%=path %>/requirement/updateInfo?"+data;
	$.post(url,function(data){
		if(data=="01"){
			window.location.href="<%=path %>/requirement/listContent";
		}else{
			var e1 = document.getElementById('error');			
			e1.style.display="";
			$("#error").html("用户名/密码错误！");
		}		
	});
}
function resubmit(){
	var properties = $("#properties").val();
	var type = $("#type").val();
	var name = $("#name").val();
	var content = $("#content").val();
	var customer = $("#customer").val();
	var propose_user = $("#proposeUser").val();
	var propose_time = $("#proposeTime").val();
	var request_time = $("#requestTime").val();
	var data = "id="+${id}+
		"&properties="+properties+"&type="+type+
		"&name="+name+"&content="+content+
		"&customer="+customer+"&propose_user="+propose_user+
		"&propose_time="+propose_time+"&request_time="+request_time+"&status=待审核&reason=";
	var url = "<%=path %>/requirement/updateInfo?"+data;
	$.post(url,function(data){
		if(data=="01"){
			window.location.href="<%=path %>/requirement/listContent";
		}else{
			var e1 = document.getElementById('error');			
			e1.style.display="";
			$("#error").html("用户名/密码错误！");
		}		
	});
}
</script> 
</html>

