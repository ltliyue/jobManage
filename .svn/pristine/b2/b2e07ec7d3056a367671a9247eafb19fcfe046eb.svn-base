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
//表单验证
$(function(){
	var demo=$("#formn").Validform({
		tiptype:3,
		showAllError:true
	});
	$("#ajaxpost").click(function(){
		var status = demo.check(false);
		if(status==true){
			//如果通过，执行自己的方法
			postF();
		}
	});
	
});


	function conf(){
		var r = true;
		var un = $("#username").val();
		var url = '<%=path %>/home/usernameq?username='+un;
		$.post(url,function(data){
			json = eval('('+data+')');
			if(json!=null){
				alert('该用户名已存在!');
				r = false;
				return r;
			}
			});
		return r;
	}

	function postF(){
		if(conf()==true){
			var url = '<%=path %>/datauser/useradd';
			if($("#check").prop("checked")){
				$("#enable").attr("value","1");
			}else{
				$("#enable").attr("value","0");
			}
			var data1 = $("#formn").serialize();
			
			$.post(url,data1,function(data){
				json = eval('('+data+')');
				if(json!=""&&json.length<20){
					alert("增加成功！");
					window.location.href="<%=path %>/datauser/userrole?id="+json.toString()+"&name="+$("#name").val()+"&email="+$("#email").val();
				}else{
					alert("增加失败！");
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
          	<form action="<%=path %>/datauser/useradd" id='formn'>
          		<h2 class="content_title">个人信息</h2>
			<div>
			<label class="content_zjnrcqlabel"><span class="content_zjnrcqspan">姓名不能为空</span>姓名</label> 
			<input type="text"
					class="form-control content_zjnrcqtext" name='name'  datatype="*" nullmsg="请输入姓名！" errormsg=""  id="name"    placeholder="姓名">
					</div>
					<div>
				<label class="content_zjnrcqlabel"><span class="content_zjnrcqspan">请输入正确的邮箱地址</span>邮箱</label>  
				<input type="email"
					class="form-control content_zjnrcqtext" name='email' id="email" placeholder="邮箱"  datatype="e" nullmsg="请输入邮箱！" errormsg="邮箱格式不正确！"  >
					</div>
					<hr>
			<h2 class=".content_title">账户信息</h2>
			<div>
				<label class="content_zjnrcqlabel"><span class="content_zjnrcqspan">账户名4-18个英文</span>帐户名</label>  
				<input type="text"
					class="form-control content_zjnrcqtext" name='username' id="username" placeholder="账户名"  datatype="s4-18" nullmsg="请输入帐户名！" errormsg="至少4个字符,最多18个字符！">
					</div>
					<div>
				<label class="content_zjnrcqlabel"><span class="content_zjnrcqspan">密码4-18个英文</span>密码</label>  
				 <input type="password"
					class="form-control content_zjnrcqtext" name='password' id="password" placeholder="密码"  datatype="s4-18" nullmsg="请输入密码！" errormsg="昵称至少4个字符,最多18个字符！">
					</div>
					<div>
				<label class="content_zjnrcqlabel"><span class="content_zjnrcqspan">与密码一样</span>确认密码</label>   <input
					type="password" datatype="*" recheck="password"  errormsg="两次密码不一样！" class="form-control content_zjnrcqtext" id="configpw"
					placeholder="确认密码">
					</div>
			<input type="checkbox" checked="checked" id="check" >可用
			<input type="hidden" id="enable" name="enable" value="1">
		</form>
	<button type="button" class="btn btn-primary" id="ajaxpost">提交</button>
	</div>
	</div>
</body>
</html>
