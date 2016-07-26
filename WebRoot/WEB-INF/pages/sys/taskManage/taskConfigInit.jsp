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
<link href="<%=path %>/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
<link href="<%=path %>/resources/css/dashboard.css" rel="stylesheet">
<link href="<%=path %>/resources/css/bootstrap-treeview.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path %>/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!-- Bootstrap core JavaScript
    ================================================== -->
	<script src="<%=path %>/resources/js/jquery.min.js"></script>
	<script src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
</head>
<script type="text/javascript">

	$(function(){
		//$("#taskConfig").load("${webRoot}/datatask/taskConfigView?taskId=${taskId}");
		$.get("${webRoot}/datatask/taskConfigView?taskId=${taskId}&initFlag=true", function(data){
  			$(data).appendTo("#taskConfig");
		});
	});

</script>
<body>
	<jsp:include page="../topBase.jsp"></jsp:include>
	<jsp:include page="../leftBase.jsp"></jsp:include>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" id="taskConfig">
		<h1 class="page-header">任务配置</h1>
	</div>

</body>
</html>
