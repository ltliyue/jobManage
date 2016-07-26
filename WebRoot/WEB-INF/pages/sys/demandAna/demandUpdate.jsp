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
	<link href="<%=path %>/resources/css/dashboard.css" rel="stylesheet">
	<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
<!-- Bootstrap core JavaScript
    ================================================== -->
    <script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/script.js"></script>
    <script src="<%=path%>/resources/js/new_version/site.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
</head>
<script type="text/javascript">
$(function(){
	var demo=$("#formn").Validform({
		tiptype:3,
		showAllError:true
	});
	var fileName = "${demand.demandFilePath }";
	var fileNameArr = fileName.split(";");
	var code = "";
	for(i=0;i<fileNameArr.length;i++){
		code += "<a href='<%=path %>/demandAnaly/download?fileName="+fileNameArr[i]+"&demandId=${demand.demandId }"+"'>"+fileNameArr[i]+"</a><br /><br>";
	}
	$("#div").html(code);
	$("#ajaxpost").click(function(){
		var status = demo.check(false);
		if(status==true){
			//如果通过，执行自己的方法
			submit();
		}
	});
	
	function submit(){
		var url = '<%=path %>/demandAnaly/demandUpdateSub';
		var data1 = $("#formn").serialize();
		$.post(url, data1, function(data) {
			if (data=='1') {
				alert("修改成功！");
				window.location.href = "<%=path %>/demandAnaly/setdemand";
			} else {
				alert("修改失败！");
			}
		});
	}
	
});
</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
    <div  class="content_waikuang">
<div class="container-fluid search_select select-result" style="padding-left:0">
<h2 class="content_title">需求修改</h2>
<div class="container-fluid search_select select-result" style="padding-left:0; margin:0 auto">
		<div class="table-responsive">
		<form action="<%=path%>/demandAnaly/demandUpdateSub" id='formn'>
			<input type="hidden" name="demandId" id="demandId" value="${demand.demandId }">
			<input type="hidden" name="demandStatus" id="demandStatus" value="${demand.demandStatus }">
			<div class="form-group">
				<label for="name">需求名称</label> 
				<input type="text" class="form-control content_zjnrcqtext" name='demandName' id="demandName" value="${demand.demandName }"
					placeholder="需求名称" datatype="*" nullmsg="请输入需求名称" errormsg="">
			</div>
			<div class="form-group">
				<label for="demandDetail">需求详情</label>
				<textarea class="form-control content_zjnrcqtext" name="demandDetail" id="demandDetail" rows="4" value="${demand.demandDetail }" placeholder="请输入需求详情" nullmsg="请输入需求内容" errormsg="" datatype="*" >${demand.demandDetail }</textarea>
			</div>
			<label for="demandDetail">文件下载</label>
			<div class="form-group" id="div">
				<!-- <h4><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="addFileBtn" data-target="" onclick="addFile()" value="show">添加附件</button> </h4>
				<input type = "file"  name = "demandFile" id = "demandFile" style ="display:none">
				<h4><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="uploadFileBtn" data-target="" onclick="uploadFile()" style ="display:none" >上传</button> </h4> -->
			</div>
		</form>
		<button type="button" class="btn btn-primary" id="ajaxpost">保存</button>
	</div>
	</div>
	</div>
	</div>
</body>
</html>