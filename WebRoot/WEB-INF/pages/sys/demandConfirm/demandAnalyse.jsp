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
    <script src="<%=path%>/resources/js/bootstrap-filestyle.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.bootstrap3.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
	
</head>
<script type="text/javascript">
var demo1;
var demo;
$(function(){
	demo=$("#formn").Validform({
		tiptype:3,
		showAllError:true
	});
	demo1=$("#form").Validform({
		tiptype:3,
		showAllError:true
	});
	querySiteType(0);
	var fileName = "${analyse.filePath }";
	var fileNameArr = fileName.split(";");
	var code = "";
	if(fileName.length!==0){
		for(i=0;i<fileNameArr.length;i++){
			code += "<a href='<%=path %>/demandAnaly/download?fileName="+fileNameArr[i]+"&demandId=${analyse.id }"+"'>"+fileNameArr[i]+"</a><br><br>";
		}
		$("#div").html(code);
	}else{
		$("#div").html("无附件可下载！");
	}
	/* $("#ajaxpost").click(function(){
		var status = demo.check(false);
		if(status==true){
			//如果通过，执行自己的方法
			submit();
		}
	}); */
});

function OpenAddModel(parentId){
	 $("#parentId").attr("value",parentId);
	 $("#button").text("增加");
	 $("#button").attr("onclick","addNext()");
	 $("#id").attr("value","");
	 $("#name").attr("value","");
	 $("#url").attr("value","");
	 $("#siteDescription").attr("value","");
	 siteChange();
	}
	
function querySiteType(pid){
	 var url = "<%=path %>/datasiteitem/querysitetype?pid="+pid;
	 $.post(url,function(data){
		 json=eval('('+data+')');
		 $("#siteType1").empty();
		 //$("#siteType1").append("<option value='0'>"+'---请选择---'+"</option>");
		 $.each(json,function(i){
			 	$("#siteType1").append("<option value='"+json[i].ID+"'>"+json[i].NAME+"</option>");
		 });
	 });
}

function siteChange(){
	 $("#siteType2").empty();
	 var pid = $("#siteType1").val();
	 var url = "<%=path %>/datasiteitem/querysitetype?pid="+pid;
	 $.post(url,function(data){
		 json=eval('('+data+')');
		 $.each(json,function(i){
			 	$("#siteType2").append("<option value='"+json[i].ID+"'>"+json[i].NAME+"</option>");
		 });
	 });
}

 function save(){
	 var priority = $("#priorityS").find('option:selected').text();
	 $("#priority").attr("value",priority);
	 var status = demo.check(false);
		if(status==true){
			var url = "<%=path %>/demandAnaly/save?id=${analyse.id}"
			var data1 = $("#formn").serialize();
			$.post(url,data1,function(data){
				if(data=='1'){
					alert('需求分析提交成功！');
					window.close();
				}else{
					alert('需求分析提交失败！');
				}
			});
		}
 }
 function demandview(id,name,url,siteType,siteDescription,executionCycle,dueTime,otherDescription){
	 var url = "<%=path %>/demandConfirm/getItem?siteId=" + id;
	 $.post(url, function(data) {
		 var json = eval('('+data+')');
		 $("#siteTbody").empty();
		 $.each(json,function(i){
			 $("#siteTbody").append("<tr align='left'><td>"+(i+1)+"</td><td>"
					 				+json[i].fieldName+"</td><td>"
					 				+json[i].fieldCode+"</td><td>"
					 				+json[i].fieldDescription+"</td><td>");
		 });
	 });
	 $("#siteName").text(name);
	 $("#siteTypeN").text(siteType);
	 $("#siteDescription").text(siteDescription);
	 $("#executionCycle").text(executionCycle);
	 $("#dueTime").text(dueTime);
	 $("#otherDescription").text(otherDescription);
	 
 }
 
 function cancelWin(){
	 window.location.href="<%=path %>/demandConfirm/setconfirm";
 }
 
 function feedback(demandId){
		<%-- window.open("<%=path %>/demandAnaly/feedback?demandId=" + demandId); --%>
		var url = "<%=path %>/demandAnaly/feedback?demandId=" + demandId;
		$.post(url, function(data) {
			
			var json = eval ('('+data+')');
			//一次eval竟然解决不了问题只好两次了
			var json1 = eval('('+json+')');
			$("#demand").attr("value",json1.demandId);
			$("#idN").attr("value",json1.id);
		}, "text");
	}
 function saveOpinion(){
	 var status = demo.check(false);
		if(status==true){
			var value = $('input[name="method"]:checked').val();
			$("#methodNew").attr("value",value);
			document.getElementById("form2").submit();
		}
}
</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
  <!--内容开始-->
    <div  class="content_waikuang">
<div class="container-fluid search_select select-result" style="padding-left:0">
<h2 class="content_title">需求分析</h2>

<div class="container-fluid search_select select-result" style="padding-left:0; margin:0 auto">
		<div class="table-responsive">
		<form action="<%=path%>/demandAnaly/demandAnalyseSub" id='formn'>
			<input type="hidden" name="siteId" id="siteId">
			<input type="hidden" name="demandId" id="demandId" value="${analyse.demandId }">
			<input type="hidden" name="priority" id="priority">
			<div class="form-group">
				<label for="name">分析结果描述</label> 
				<textarea class="form-control content_zjnrcqtext" disabled="disabled" name="resultDescription" id="resultDescription" rows="4" placeholder="请输入需求分析结果描述" nullmsg="请输入需求分析结果描述" errormsg="" datatype="*" >${analyse.resultDescription }</textarea>
			</div>
			<label for="demandDetail">附件</label>
			<div class="form-group" id="div">
				
				<!-- <h4><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="uploadFileBtn" data-target="" onclick="uploadFile()" >浏  览</button> </h4> -->
			</div>
		</form>
		<table class="tree table table-hover table-bordered table-condensed">    
			<tr style="height:40px; line-height:40px"><th colspan="5">站点信息</th></tr>
		    <tr class='success'><th>序号</th><th>站点名称</th><th>URL</th><th>站点类型</th><th>预交付日期</th></tr>
		    <tbody id="tbody">
				<c:if test="${not empty sites}">
						<c:forEach var="site" items="${sites}" varStatus="ts">
							<tr>
								<td>${ts.index+1 }</td>
								<td id='name' +ts><a class='content_xuqiumcan' onclick="demandview('${site.id }','${site.name }','${site.url }','${site.siteType }','${site.siteDescription }','${site.executionCycle }','${site.dueTime }','${site.otherDescription }')" data-target="#addModal" data-toggle="modal">${site.name }</a></td>
								<td class="content_rwgl_pzurl" style="width:40%">${site.url }</td>
								<td>${site.siteType }</td>
								<td>${site.dueTime }</td>
							</tr>
						</c:forEach>
					</c:if>
			</tbody>
		</table>
		<br>
		<div align="center">
			<button type="button" class="btn btn-primary" id="save" data-target="#feedback" data-toggle="modal" onclick="feedback('${analyse.demandId }')">意见反馈</button>&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-primary" id="close" onclick="cancelWin()">取  消</button>
		</div>
	</div>
	</div>
	</div>
	</div>
	<!-- Modal -->
<div class="modal hide fade" style="width:600px;left:50%" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width:600px" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">站点信息</h4>
      </div>
      <form id="form" method="POST">
      <input type="hidden" name="id" id="id">
      <input type="hidden" name="parentId" id="parentId">
      <input type="hidden" name="siteType" id="siteType">
      <div class="modal-body">
      		<table class="tree table table-hover table-bordered table-condensed">    
				<tr ><th colspan="4">基本信息</th></tr>
		    	<!-- <tr class='success'><th>序号</th><th>站点名称</th><th>URL</th><th>站点类型</th><th>预交付日期</th></tr> -->
     			<tr><th>站点名称</th><td id="siteName"></td><th>站点类型</th><td id="siteTypeN"></td></tr>
      			<tr><th>站点描述</th><td id="siteDescription"></td><th>执行周期</th><td id="executionCycle"></td></tr>
      			<tr><th>预期交付日期</th><td id="dueTime"></td><th>其他描述</th><td id="otherDescription"></td></tr>
      			<tr ><th colspan="4">数据项信息</th></tr>
      			<tr class='success'><th>序号</th><th>数据项名称</th><th>数据项代码</th><th>数据项描述</th></tr>
      			<tbody id="siteTbody">
      				
      			</tbody>
      		</table>
      </div>
      </form>
      <div id="div1" class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
      </div>
    </div>
  </div>
</div>

<div class="modal hide fade" id="feedback" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">意见反馈</h4>
      </div>
      <form action="<%=path %>/demandConfirm/save" id="form2" method="POST" enctype="multipart/form-data">
          <input type="hidden" name="demand" id="demand" >
          <input type="hidden" name="idN" id="idN" >
          <input type="hidden" name="methodNew" id="methodNew" >
          <div class="modal-body">
            <div class="form-group">
				<label for="exampleInputEmail1">反馈类型</label>
				<input name="method" type="radio" value="reconfirm" checked="checked">再次分析后确认
				<br>
				<input name="method" type="radio" value="confirm">确认采集
				<br>
				<input name="method" type="radio" value="cancel">废弃本次需求
				<br>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">反馈意见</label>
				<textarea class="form-control content_zjnrcqtext" name="feedback" id="feedback" rows="4" placeholder="请输入反馈意见" nullmsg="请输入反馈意见" errormsg="" datatype="*" >${feedback.feedback }</textarea>
			</div>
			<div class="form-group" >
				<label for="exampleInputEmail1">附件</label>
				<input type = "file" class="filestyle" name="feedfile" multiple id="feedfile" data-icon="false" data-buttonText="选择文件">
			</div>
			</div>
            </form>
            <div class="modal-footer">
				<button type="button" class="btn btn-primary" id="save" onclick="saveOpinion()">保  存</button>
				<button type="button" class="btn btn-primary" id="close" data-dismiss="modal">取  消</button>
			</div>
    </div>
  </div>
</div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
</body>
</html>