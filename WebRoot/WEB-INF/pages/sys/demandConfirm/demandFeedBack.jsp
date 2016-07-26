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

<title>增加需求</title>

<!-- Bootstrap core CSS -->
<link href="<%=path%>/resources/css/new_version/bootstrap.min.css" rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="<%=path%>/resources/css/new_version/site.css" rel="stylesheet">
<link href="<%=path %>/resources/css/treegridCSS/jquery.treegrid.css" rel="stylesheet">
<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">

<!-- Bootstrap core JavaScript
    ================================================== -->
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
	var demo;
	$(function(){
		demo=$("#form1").Validform({
			tiptype:3,
			showAllError:true
		});
		$('input[name=method]').each(function(){
			if(this.value == "${feedback.type}" ){
				$(this).attr('checked','true');
		}
		});
	});
	function save(){
		 var status = demo.check(false);
			if(status==true){
				var value = $('input[name="method"]:checked').val();
				$("#methodNew").attr("value",value);
				document.getElementById("form1").submit();
				<%-- var url = "<%=path %>/demandConfirm/save?id=${feedback.id}&type="+value;
				var data1 = $("#form1").serialize();
				$.post(url,data1,function(data){
					if(data=='1'){
						alert('反馈意见提交成功！');
						window.close();
					}else{
						alert('反馈意见提交失败！');
					}
				}); --%> 
			}
	}
	
	</script>
  <body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
  <!--内容开始-->
    <div  class="content_waikuang">
<div class="container-fluid search_select select-result" style="padding-left:0">
<h2 class="content_title">意见反馈</h2>
<div class="container-fluid search_select select-result" style="padding-left:0; margin:0 auto">
		<div class="table-responsive">
          <form action="<%=path %>/demandConfirm/save" id="form1" method="POST" enctype="multipart/form-data">
          <input type="hidden" name="demandId" id="demandId" value="${feedback.demandId }">
          <input type="hidden" name="id" id="id" value="${feedback.id }">
          <input type="hidden" name="methodNew" id="methodNew" >
            <div class="form-group">
				<label for="exampleInputEmail1">反馈类型</label>
				<br>
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
			<div class="form-group">
				<label for="exampleInputEmail1">附件</label>
				<input type = "file" class="filestyle" name="feedfile" multiple id="feedfile" data-icon="false" data-buttonText="选择文件">
			</div>
            </form>
            <br>
            <div align="center">
				<button type="button" class="btn btn-primary" id="save" onclick="save()">保  存</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-primary" id="close" onclick="window.close()">取  消</button>
			</div>
            </div>
            </div>
          </div>
        </div>
    </body>
</html>