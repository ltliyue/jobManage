<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
	<link href="<%=path %>/resources/css/treegridCSS/jquery.treegrid.css" rel="stylesheet">
	<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
	 <!-- Bootstrap core jss -->
	<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/script.js"></script>
    <script src="<%=path%>/resources/js/new_version/site.js"></script>
    <script src="<%=path%>/resources/js/bootstrap-filestyle.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.bootstrap3.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
	

</head>
<script type="text/javascript">
j = 1;
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

function submit(){
	<%-- var url = '<%=path %>/demandAnaly/demandAddSub';
	var path = $("#demandFilePath").val();
	var data1 = $("#formn").serialize(); --%>
	document.getElementById("formn").submit();
	<%-- $.post(url, data1, function(data) {
		if (data=='1') {
			alert("增加成功！");
			window.location.href = "<%=path %>/demandAnaly/setdemand";
		} else {
			alert("增加失败！");
		}
	}); --%>
}

</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
  <!--内容开始-->
    <div  class="content_waikuang">
<div class="container-fluid search_select select-result" style="padding-left:0">
<h2 class="content_title">${title}</h2>

<div class="container-fluid search_select select-result" style="padding-left:0; margin:0 auto">
		<div class="table-responsive">
		<form action="<%=path%>/demandAnaly/demandAddSub" enctype="multipart/form-data" id='formn' method="POST">

			<div class="form-group">
				<label for="name">需求名称</label> 
				<input type="text" class="form-control content_zjnrcqtext" name='demandName' id="demandName"
					placeholder="需求名称" datatype="*" nullmsg="请输入需求名称" errormsg="">
			</div>
			<div class="form-group">
				<label for="demandDetail">需求详情</label>
				<textarea class="form-control content_zjnrcqtext" name="demandDetail" id="demandDetail" rows="4" placeholder="请输入需求详情" nullmsg="请输入需求内容" errormsg="" datatype="*" ></textarea>
			</div>
			<div class="form-group" id="select_wenjian">
				<!-- <h4><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="addFileBtn" data-target="#demandView" value="show">添加附件</button> </h4> -->
				<input type="file" class="filestyle" data-icon="false" name="demandFile" multiple id="demandFile"  data-buttonText="选择文件">
				<!-- <h4><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="uploadFileBtn" data-target="" onclick="uploadFile()" >上传</button> </h4> -->
			</div>
		</form>
		<div class="content_zjnrcqanniu"><button type="button" class="btn btn-primary" id="ajaxpost">保存</button><button type="button" class="btn btn-default">取消</button></div>
	</div>
	</div>
	</div>
	</div>
</body>
</html>