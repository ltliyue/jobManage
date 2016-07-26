<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	 <script src="<%=path%>/resources/js/new_version/script.js"></script></head></head>
<script type="text/javascript">

	$(function(){
		var demo=$("#formn").Validform({
			tiptype:3,
			showAllError:true
		});
		$("#ajaxpost").click(function(){
			var status = demo.check(false);
			if(status==true){
				//如果通过，执行自己的方法
				submit();
			}
		});
		
	});

	function copySite() {
		var url = $("#siteId").find("option:selected").attr("id");
		$("#url").val(url);
	}
	function submit(){
		var url = '${webRoot}/datatask/taskaddsub';
		
		var data1 = $("#formn").serialize();
		
		$.post(url,data1,function(data){
			if(data!=null && data!=''){
				if(confirm("任务新建成功，是否进入任务配置页面？")){
					window.location.href="${webRoot}/datatask/settask?pageType=0&goSetting=true&crawlerTask.taskId="+data;
				}
			}else{
				alert("新建任务失败！");
			}
		});
	}
	
	function goBack(){
		history.go(-1);
	}
</script>
<body style="margin:0; padding:0">
<jsp:include page="../topBase.jsp"></jsp:include>
<div class="content_waikuang">
          <div class="container-fluid search_select select-result" style="padding-left:0">

		<form action="${webRoot}/datatask/taskaddsub" id='formn' method="post">
		<h2 class="content_title">新建任务</h2>
			<div>
				<label class="content_zjnrcqlabel"><span class="content_zjnrcqspan">任务名称不能为空</span>任务名称</label>
				<input type="text" class="form-control content_zjnrcqtext" name='crawlerTask.taskName' 
					id="taskName" datatype="*" nullmsg="请输入任务名称！" errormsg="请输入任务名称！" placeholder="">
			</div>
			<div>
				<label class="content_zjnrcqlabel"><span class="content_zjnrcqspan">请选择任务网站</span>任务网站</label>
				<select class="form-control content_zjnrcqlabel"
						name='crawlerTask.siteId' id="siteId" datatype="s" onchange="copySite()" style="width: 100%"
						nullmsg="请选择网站" errormsg="必须选择网站">
						<option value="">请选择种子网站</option>
						<c:forEach items="${sites }" var="site">
							<option value="${site.id }" id="${site.url }">${site.name }-${site.url }<c:if test="${not empty site.executionCycle }">(${site.executionCycle })</c:if></option>
						</c:forEach>
					</select>
			</div>
			<div>
				<label class="content_zjnrcqlabel"><span class="content_zjnrcqspan">URL不能为空</span>URL</label>
				 <input type="text" class="form-control content_zjnrcqlabel" style="width: 100%"
				 	name='crawlerTask.url' id="url" datatype="url" nullmsg="请输入采集种子URL！" 
				 	errormsg="URL不符合规范！" placeholder="">
			</div>
			<div>
				<label class="content_zjnrcqlabel">任务描述</label>
				 <textarea style="text-align: left;width: 100%" class="content_zjnrcqlabel"
						name='crawlerTask.taskDescribe' id="taskDescribe" cols="500" rows="4"
						placeholder="任务描述"></textarea>
			</div>	
		</form>
	<button type="button" class="btn btn-primary" id="ajaxpost">提交</button>
	<button type="button" class="btn btn-secondary" onclick="goBack()">返回</button>
	</div>
</div>
</body>
</html>
