<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <title>任务采集url监控</title>

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
	var demo ;
 	$(document).ready(function(){
		demo = $("#form").Validform({
			tiptype:3,
			showAllError:true
		});
		all();		
  	});
 	
 	function all(){		 
	 	var url = "<%=path %>/urlMonitor/getTaskListByPageNo?page=1";
	 	$.ajax({
		 	type : "POST",
		 	url : url,
		 	contentType: "application/json; charset=utf-8",
		 	dataType: "text",
		 	data : "",
		 	success : function(data) {
				json = eval('('+data+')');
			 	$("#tbody").html(json[0].sb);
		 		$("#zys").html(json[0].zys);
			 	$("#dq").html(json[0].pages);
			 	$('.tree').treegrid();
			 	if(json[0].zys>10){
				 	$("#nfy").removeAttr("class");
			 	}
		 	}
		});
 	}; 	
 	function lfy(){
	 	if($("#lfy").attr("class")!="disabled"){
		 	var url = "<%=path %>/urlMonitor/getTaskListByPageNo?page="+(parseInt($("#dq").html())-1);
		 	$.ajax({
			 	type : "POST",
			 	url : url,
			 	contentType: "application/json; charset=utf-8",
			 	dataType: "text",
			 	data : "",
			 	success : function(data) {
					json = eval('('+data+')');
				 	$("#tbody").html(json[0].sb);
				 	$("#zys").html(json[0].zys);
				 	$("#dq").html(json[0].pages);
			 		$('.tree').treegrid();
				 	if(parseInt(json[0].zys)>parseInt($("#dq").html())*10){
						$("#nfy").removeAttr("class");
					}
				 	if(parseInt($("#dq").html())==1){
						$("#lfy").attr("class","disabled");
					}
				}
			});
 		}
 	}
 	
	function nfy(){
		if($("#nfy").attr("class")!="disabled"&&$("#dq").html()!=''){
			var url = "<%=path %>/urlMonitor/getTaskListByPageNo?page="+(parseInt($("#dq").html())+1);
			$.ajax({
				type : "POST",
				url : url,
				contentType: "application/json; charset=utf-8",
				dataType: "text",
				data : "",
				success : function(data) {
					json = eval('('+data+')');
					$("#tbody").html(json[0].sb);
					$("#zys").html(json[0].zys);
					$("#dq").html(json[0].pages);
					$('.tree').treegrid();
					if(parseInt(json[0].zys)<=parseInt($("#dq").html())*10){
						$("#nfy").attr("class","disabled");
					}
					if(parseInt($("#dq").html())!=1){
						$("#lfy").removeAttr("class");
					}
				}
			});
		}
	}
	function getTaskInstanceByTaskId(no,taskId){
		var tr = document.getElementsByName(taskId+"_li");
		if(tr.length!=0){				
			if( $("tr[name='"+taskId+"_li']").css("display")!="none"){
				$("tr[name='"+taskId+"_li']").css("display","none");
			}else{
				$("tr[name='"+taskId+"_li']").css("display","");
			}					
		}else{
			var url = "<%=path %>/urlMonitor/getTaskInstanceByTaskIdAndNo?taskId="+taskId+"&ii="+no;
			$.ajax({
				type : "POST",
				url : url,
				contentType: "application/text; charset=utf-8",
				dataType: "text",
				data : "",
				success : function(data) {
					$("#"+taskId+"").after(data);
				}
			});			
		}
	}
	
	function taskDetail(taskId){
		var url="<%=path%>/urlMonitor/taskDetail?taskId="+taskId;
		location.href=url; 
	}
	
	function taskInstanceDetail(taskInstanceId){
		var url="<%=path%>/urlMonitor/taskInstanceDetail?taskInstanceId="+taskInstanceId;
		location.href=url; 
	}
	</script>
  <body style="padding-top:0" >
  
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> -->   
	<div  class="content_waikuang">
	<div class="container-fluid search_select " style="padding-left:0">
   		<h2 class="content_title">${title }</h2>
   		<div  class="renwu_caozuo"><a  class="renwu_select" style="text-decoration:none" href="#" data-toggle="modal" id="add_0" data-target="#addModal" onclick="OpenAddModel(0,0,1)"  >添加一级菜单</a></div>
    </div>
	<div class="row" style="clear:both; margin-left:0">
		<div class="span9" style="width:100%; margin-left:0">
          <table class="tree table table-hover table-bordered table-condensed">    
		    <tr class='success'><th width="10%">序号</th><th width="70%">任务名称</th><th width="20%">下载量</th></tr>
		    <tbody id="tbody"></tbody>
		  </table>
		</div>
	</div>
		
	<div>
		<nav>
	  	<ul class="pager">
			<li id="lfy" class="disabled">
				<a href="#" aria-label="Previous "  onclick="lfy()"><span aria-hidden="true">&larr;上一页</span></a>
			</li>
			   	
			<li id="nfy" class="disabled">
			    <a  href="#" aria-label="Next" style="margin-left:  20px" onclick="nfy()"><span aria-hidden="false">下一页&rarr;</span> </a>
			</li>
			<label style="margin-left: 20px">当前页:<span id="dq"></span></label>
			<label style="margin-left: 20px">总数量:<span id="zys"></span></label>
		</ul>
		</nav>
	</div>
</div>
</body>
</html>
