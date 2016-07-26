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

  </head>
  <body>
  	<div style="width: 100%;height:100%; text-align: center;vertical-align: middle;">
  	<br><br><br><br>
   	 <b style="color:red"> 系统错误:${ex.message }${ex }</b><br>
     <b style="color:blue">点击返回<a href="<%=path%>">系统首页</a></b>
     </div>
  </body>
</html>
