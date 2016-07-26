<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>错误请求</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		setTimeout("window.close()", 3000);
	</script>
  </head>
  <body style="text-align: center;vertical-align: middle;">
  	<br><br><br><br>
   	 <h3>系统异常:${ex.message }</h3><br>
     <b style="color:blue"><span >3</span> 秒钟后页面将关闭页面...</b>
  </body>
</html>
