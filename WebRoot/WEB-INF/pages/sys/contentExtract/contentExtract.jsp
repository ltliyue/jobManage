<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link href="/crawlManage/resources/validform/css/style.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/new_version/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/new_version/site.css"
	rel="stylesheet">
<link href="<%=path %>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/css/treegridCSS/jquery.treegrid.css"
	rel="stylesheet">
<link href="<%=path%>/resources/validform/css/style.css"
	rel="stylesheet">
<!-- Bootstrap core jss -->
<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
<script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
<script src="<%=path%>/resources/js/new_version/script.js"></script>
<script src="<%=path%>/resources/js/new_version/site.js"></script>
<script src="<%=path%>/resources/js/bootstrap-filestyle.min.js"></script>
<script src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.js"></script>
	<script src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.zh-CN.js"></script>	
<script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/treegrid/jquery.treegrid.bootstrap3.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/treegrid/jquery.treegrid.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/validform/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="<%=path %>/resources/js/formats.js"></script>
</head>

<script type="text/javascript">
	var demo = false;
	//表单验证
	$(function() {
		demo = $("#formn").Validform({
			tiptype : 3,
			showAllError : true
		});
	});
	function extract(){
		var status = demo.check(false);
			if (status == true) {
				//var inputPath = $("#inputPath").val();
				var instanceId = $("input[name='radio_list']:checked").val();
				var extractType = $("#extractType").val();
				alert("开始发送rest请求，请等待！");
				var url = "<%=path%>/contentextract/runextract?"
					+ "instanceId=" + instanceId+"&extractType="+extractType;
				$.post(url, function(data) {
					if(data.indexOf("日志文件名")>0){
						alert("开始执行！\r\n点击\"查看进度按钮\"可以查看任务执行的进度!");
						$("#logFileName").val("SUCCESS");
					}else{
						alert(data);
					}
				});
			}
	}
	function viewProgress(){
			document.getElementById("processButton").setAttribute(
					"data-target","#viewProgress"
			);
			var instanceId = $("input[name='radio_list']:checked").val();
			var url = "<%=path%>/contentextract/progress?instanceId="
				+ instanceId;
			$.post(url, function(data) {
				data = data.replace(/\\n/g, "\n");
				data = data.replace(/\\t/g, "\t");
				$("#log").val(data);
			});
	}
	
	function dateToString(date){
		if(date==null) return "";
		return new Date(date).Format("yyyy-MM-dd hh:mm:ss");
	}
	
	function seleinstance(){
			$("#chooseInstanceId").removeAttr("style");
			var url = "<%=path%>/contentextract/cxinstance?taskid="+$("#taskId").val();
			$.post(url,function(data){
				json = eval('('+data+')');
				$("#tbody2").html("");
				$.each(json,function(i) {
					var status = "";
					if(json[i][0].status==0){
						status = "未抽取";
					}else if(json[i][0].status==1){
						status = "抽取中";
					}else if(json[i][0].status==2){
						status = "抽取结束";
					}else if(json[i][0].status==3){
						status = "已删除";
					}else if(json[i][0].status==9){
						status = "待配置";
					}
					$("#tbody2").append('<tr align="left" style="cursor: pointer;"><td><input type="radio" name="radio_list" value="'
					+json[i][0].instanceId+'"></td><td >'+json[i][0].instanceId+'</td><td >'
					+dateToString(json[i][1].publishTime)+'</td><td >'
					+dateToString(json[i][1].finishTime)+'</td><td >'
					+dateToString(json[i][0].publishTime)+'</td><td >'+status+'</td><td >'
					+dateToString(json[i][0].finishTime)+'</td> </tr>');
				});
				
				 var selectInstanceId = "<%=(String) session.getAttribute("selectInstanceId")%>";
				 var radioList = document.getElementsByName("radio_list");
				 console.log(radioList);
				 for(var j=0; j< radioList.length;j++){
					 if(radioList[j].getAttribute("value")==selectInstanceId){
						 $("input[value='"+radioList[j].getAttribute("value")+"']").attr("checked",true); 
					 }
				 }
			});
	}
	
	
	$(document).ready(
			function(){
				load();
				//$("#taskId").mouseleave(seleinstance);
				$("#taskId").bind('input propertychange', function() {seleinstance();}); 
			}
	);
	
	function load(){
		 var selectTaskId = "<%=session.getAttribute("selectTaskId")%>";
		 var selectInstanceId = "<%=(String) session.getAttribute("selectInstanceId")%>";
		 
		 if(selectTaskId!="null" && selectInstanceId!="null"){
		 	$("#taskId").val(selectTaskId);
		 	seleinstance();
		 }
	}
	
	
</script>
<body style="margin: 0; padding: 0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
	<div class="content_waikuang" onload="load()">
		<div class="container-fluid search_select select-result"
			style="padding-left: 0">
			<h2 class="content_title">离线内容抽取</h2>
			<form id='formn'>
				<div class="container-fluid search_select select-result"
					style="padding-left: 0; margin: 0 auto">
					<div class="table-responsive">

						<div class="form-group">
							<label for="name">抽取方式</label> 
							<select class="form-control" name='extractType' id="extractType">
								<option value="0">增量抽取</option>
								<option value="1">全量抽取</option>
							</select>
						</div>

						<div class="form-group">
							<label for="name">任务id</label> <input id="taskId" name="taskId"
								style="height: 28px" class="form-control content_cjhbtext"
								datatype="*" nullmsg="任务id不能为空" />
						</div>

						<div class="form-group" style="display:none" id="chooseInstanceId">
							<label for="name">选择实例Id</label>
							<table class="table  table-striped">
             			 		<thead>
                					<tr>
               	  						<th></th>
                  						<th>实例ID</th>
                  						<th>开始采集时间</th>
                  						<th>结束采集时间</th>
                 						<th>开始抽取时间</th>
                  						<th>状态</th>
                  						<th>结束抽取时间</th>
                					</tr>
              						</thead>
									<tbody id="tbody2">
									</tbody>
				
            				</table>
						</div>

						<div class="content_zjnrcqanniu">
							<button type="button" class="btn btn-primary" id="button"
								onclick="extract()">抽取</button>
							<button type="button" class="btn btn-default">取消</button>

							<button type="button" class="btn btn-primary" id="processButton"
								data-toggle="modal"
								onclick="viewProgress()">查看进度</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal hide fade" id="viewProgress" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">内容抽取任务进度</h4>
				</div>
				<form id="form" method="POST">
					<input type="hidden" name="id" id="id">
					<div class="modal-body">
						<div class="form-group" id="iddiv">
							<label for="exampleInputEmail1">mapreduce日志</label>
							<textarea class="form-control content_cjhbtextarea"
								style="width: 95%" rows="20" name='log' id="log">
							</textarea>
						</div>
					</div>
				</form>
				<div class="modal-footer">
					<button class="btn btn-success btn-sm" onclick="viewProgress()"
						id="d">刷新</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">关
						闭</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
</body>
</html>
