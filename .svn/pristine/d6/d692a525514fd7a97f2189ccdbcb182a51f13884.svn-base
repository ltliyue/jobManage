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
	$(document).ready(function(){
		var url = '<%=path %>/datauser/userq?id=${id}';
		$.post(url,function(data){
			json = eval('('+data+')');
			$("#name").val(json.name);
			$("#email").val(json.email);
			$("#username").val(json.username);
			if(json.enable==1){
				$("#check").attr("checked","checked");
				$("#enable").attr("value","1");
			}else{
				$("#check").removeAttr("checked");
				$("#enable").attr("value","0");
			}
			
		});
		
	 });
	function check1(){
		var ischecks = $("input[name='check']:checked").length;
		if(ischecks==0){ 
			$("#ypassword").attr("readonly","readonly");
			$("#xpw").attr("readonly","readonly");
			$("#configpw").attr("readonly","readonly");
			$("#ypassword").removeAttr("datatype");
			$("#xpw").removeAttr("datatype");
			$("#configpw").removeAttr("datatype");
		}else{
			$("#ypassword").removeAttr("readonly");
			$("#xpw").removeAttr("readonly");
			$("#configpw").removeAttr("readonly");
			$("#ypassword").attr("datatype","s4-18");
			$("#xpw").attr("datatype","s4-18");
			$("#configpw").attr("datatype","s4-18");
			
		}
	}
	function submit(){
		//表单验证
		var demo=$("#formn").Validform({
			tiptype:3,
			 postonce:true,
			showAllError:true
		});
		var status = demo.check(false);
		if(status==true){
			//如果通过，执行自己的方法
			var ischecks = $("input[name='check']:checked").length;
			var url = '<%=path %>/datauser/useru?i='+ischecks;
			if($("#check").prop("checked")){
				$("#enable").attr("value","1");
			}else{
				$("#enable").attr("value","0");
			}
			var data1 = $("#formn").serialize();
			$.post(url,data1,function(data){
				if(data=="1"){
					alert("修改成功！");
					window.location.href="<%=path %>/datauser/setuser";
				}else if(data=="2"){
					alert("原始密码错误！");
				}else{
					alert("修改失败!");
				}
			});
		}
	}
</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
          <div class="content_waikuang">
          <div class="container-fluid search_select select-result" style="padding-left:0">
		<h2 class="content_title">用户修改</h2>
		
		<form action="<%=path %>/datauser/useru" id='formn' method="post">
			<input name = "id" type="hidden" value="${id }">
			<div class="form-group">
				<label class="content_zjnrcqlabel"><span class="content_zjnrcqspan"></span>姓名</label> 
				 <input type="text"
					class="form-control content_zjnrcqtext" datatype="*" nullmsg="请输入姓名！" errormsg="" name='name' id="name" placeholder="姓名">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">邮箱</label> <input type="email"
					class="form-control content_zjnrcqtext" name='email' id="email" placeholder="邮箱"  datatype="e" nullmsg="请输入邮箱！" errormsg="邮箱格式不正确！">
			</div>
			<hr>
			<input type="checkbox"   id="check"  >可用
			<input type="hidden" id="enable" name="enable" value="1">
			   &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="check" value="1" onclick="check1()">修改密码
			<hr>
			<div class="form-group">
				<label for="exampleInputEmail1">帐户名</label> <input type="text" readonly="readonly"
					class="form-control content_zjnrcqtext" name='username' id="username" placeholder="账户名">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">原始密码</label> <input type="password"
					class="form-control content_zjnrcqtext"  id="ypassword" name="ypassword" placeholder="原始密码"  readonly="readonly"     errormsg="昵称至少4个字符,最多18个字符！">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">新密码</label> <input
					type="password" name='password'  class="form-control content_zjnrcqtext" id="xpw" readonly="readonly"     errormsg="昵称至少4个字符,最多18个字符！"
					placeholder="新密码">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">确认密码</label> <input readonly="readonly"
					type="password"  class="form-control content_zjnrcqtext" id="configpw"   recheck="password"  errormsg="两次密码不一样！"
					placeholder="确认密码">
			</div>
		</form>
	<button type="button" class="btn btn-primary" onclick="submit()">提交</button>
	</div>
</div>
</body>
</html>
