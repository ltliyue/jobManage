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
var mainstatus;
$(document).ready(function(){
	
	getjobinfo();
});
function getjobinfo(){
	var url = '<%=path %>/requirementSplit/requirementsplitq?id=${id}';
	$.post(url,function(data){
		json = eval('('+data+')');
		mainstatus=json.status;
		$("#dotime").val(json.doTime);
	});
	getjobstatus();
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
</script>

</head>
</head>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	
	 <!--内容开始-->
    <div  class="content_waikuang">
    	<div class="container-fluid search_select" style="padding-left: 0">

			<h2 class="content_title">固定任务编辑</h2>
			
			<form action="<%=path %>/requirement/updatejobInfo" id='formn' method="post">
				<input name = "id" type="hidden" value="${id }">
				<div class="form-group">
					<label class="content_zjnrcqlabel">
					<span class="content_zjnrcqspan"></span>任务执行时间</label> 
					<input class="laydate-icon" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})" name='dotime' id="dotime">
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

	var status = $("#status").val();
	var dotime = $("#dotime").val();
	var data = "id="+${id}+
		"&status="+status+"&doTime="+dotime;
	
	var url = '<%=path %>/requirementSplit/updatejobInfo?'+data;
	$.post(url,function(data){
		if(data=="01"){
			window.location.href="<%=path %>/requirementSplit/listContent_fixedjob";
		}else{
			var e1 = document.getElementById('error');			
			e1.style.display="";
			$("#error").html("错误！");
		}		
	});
}

</script> 
</html>

