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
<!-- Bootstrap core JavaScript
    ================================================== -->
   <script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/script.js"></script> 
     <script src="<%=path%>/resources/js/new_version/site.js"></script>
</head>
<script type="text/javascript">
	function submit(){
		var url = '<%=path %>/datauser/userupdate';
		var data1 = $("#formn").serialize();
		$.post(url,data1,function(data){
			if(data=="1"){
				alert("绑定成功！");
				window.location.href="<%=path %>/datauser/setuser";
			}else{
				alert("绑定失败！");
			}
		});
	}
	 $(document).ready(function(){
		 var url = "<%=path %>/datarole/enablerole";
		 $.post(url,function(data){
			  json=eval('('+data+')');
			  $.each(json,function(i){ 
				  $("#select").append("<option value='"+json[i].id+"'>"+json[i].name+"</option>");
			  });
			});
	  });
</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
          <div class="content_waikuang">
          <div class="container-fluid search_select " style="padding-left:0">
		<form action="<%=path %>/datauser/userupdate" id='formn' method="post">
			<h4>角色绑定</h4>
			<input type="hidden" id="userid" name="id" value="${map.id }">
			<div class="form-group">
				<label class="content_zjnrcqlabel">姓名</label> <p>
				<input type="text"   class="form-control content_zjnrcqtext" id="xpw" readonly="readonly" value="${map.name}"></p>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">邮箱</label> <p>
				<input type="text"   class="form-control content_zjnrcqtext" id="xpw" readonly="readonly" value="${map.email}"></p>
			</div>
			<hr>
			<div class="form-group">
				 <label for="name">绑定角色</label>
				      <select class="form-control" id="select" name="roleid">
				      </select>
			</div>
			<a href="<%=path %>/datarole/role?t=1&id=0">>>增加角色</a>
		</form>
	<button type="button" class="btn btn-primary" onclick="submit()">提交</button>
	</div>
</div>
</body>
</html>
