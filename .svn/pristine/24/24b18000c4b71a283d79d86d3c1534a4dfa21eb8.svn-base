<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>运营需求</title>
<meta name="description" content="运营需求">
<meta name="keywords" content="table">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
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
    <script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.bootstrap3.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
	
	</head>
<script type="text/javascript">

$(document).ready(function(){ 
	var url = "<%=path%>/requirement/listContent";
	$("#maincontent").load(url);
});

$(function(){
    $('div.l_left li').click(function(){
        $(this).addClass('active').siblings('li').removeClass('active');
    });
});
</script>
</head>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
 <!--内容开始-->
    <div  class="content_waikuang">
	<div class="container-fluid search_select " style="padding-left:0">
	<h2 class="content_title">${title }</h2>

      <div  class="renwu_caozuo" style="padding-bottom:20px"><a  class="renwu_select" style="text-decoration:none" href="#" data-toggle="modal" id="add_0" data-target="" onclick="OpenAddModel(0)"  >添加一级菜单</a></div>
         <!--  <table class="table  table-striped content_rwgl">   -->  
          <table class="tree table table-hover table-bordered table-condensed" > 
		    <tr class='success'><th>序号</th><th>菜单名称</th><th>菜单url</th><th>菜单位置</th><th style="width:200px">操作</th></tr>
		    <tbody id="tbody">
				
			 </tbody>
		</table>
		</div>
		</div>
		<!-- Button trigger modal -->
	<form id="form1">
		<!--z左侧开始-->
		<!-- <div id="framecontentLeft"style="background-color:#eef7ff">
			<div class="l_left">
				<UL>
					<li id="li1" class="active"><a href="#">运营需求</a></li>
					<li id="li2"><a href="#">任务评估</a></li>
					<li id="li3"><a href="#">任务进度</a></li>
					<li id="li4"><a href="#">固定任务</a></li>
					<li id="li5"><a href="#">历史任务</a></li>
				</UL>
			</div>
		</div> -->

		<!--z左侧结束-->
		<!-- <div style="margin-left: 110px;margin-top:66px"> -->
		<!-- 	<ul id="tabs"> -->
		<!-- 	    <li><a href="#" id="tab1" style="display:none" onclick="tab1()">数据监测统计</a></li> -->
		<!-- 	    <li><a href="#" id="tab2" style="display:none" onclick="tab2()">数据查看</a></li> -->
		<!-- 	</ul> -->
		<!-- </div> -->
		<div id="maincontent"></div>
		<!--内容结束-->
	</form>
</body>
<script type="text/javascript">
$("#li1").click(function(){
	var url = "<%=path%>/requirement/listContent";
	$("#maincontent").load(url);
	return false;
});
$("#li2").click(function(){
	var url = "<%=path%>/requirement/listContent_admin";
	$("#maincontent").load(url);
	return false;
});

function exit(){
	var url = "<%=path%>/sysManage/loginout";
	$.post(url,function(data){
		window.location.href=
			"<%=path%>/sysManage/loginpage";
		});
	}
</script>
</html>










