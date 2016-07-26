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
    <script src="<%=path%>/resources/js/bootstrap-filestyle.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.bootstrap3.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
 </head>
	<script type="text/javascript">
		function combine(){
			var inputPath = $("#inputPath").val();
			var outputPath = $("#outputPath").val();
			var url = "<%=path%>/avrocombine/runcombine?inputPath="+inputPath+"&outputPath="+outputPath;
			$.post(url,function(data){
				json = eval('('+data+')');
				alert(json);
			});
			
		}
		function saveFre(){
			var frequency = $("#frequency").val();
			var id = $("#id").val();
			var url = "<%=path%>/avrocombine/savefre?frequency="+frequency+"&id="+id;
			$.post(url,function(data){
				if(data=="1"){
					alert("修改合并频率成功！");
				}else{
					alert("修改合并频率失败！");
				}
			});
		}
	</script>
<body style="margin:0; padding:0">
<jsp:include page="../topBase.jsp"></jsp:include>
 <!--内容开始-->
    <div  class="content_waikuang">
<div class="container-fluid search_select select-result" style="padding-left:0">
<h2 class="content_title">Avro文件合并</h2>


<div class="container-fluid search_select select-result" style="padding-left:0; margin:0 auto">
		<div class="table-responsive">
			<div class="form-group">
				<label for="name">自动合并频率(单位：分钟)</label> 
				<input type="hidden"id="id" name="id" value="${id }"/>
				<input class="form-control content_cjhbtext" style="height:28px" id="frequency" name="frequency" value="${frequency }"/>
			</div>
				<div class="content_zjnrcqanniu"><button type="button" class="btn btn-primary" id="button" onclick="saveFre()">保存</button><button type="button" class="btn btn-default">取消</button></div>
        </div>
        <hr color="blue" width="100%" style="...."/>
		<div class="table-responsive">
			<div class="form-group">
				<label for="name">输入文件路径</label> 
				<input class="form-control content_cjhbtext" style="height:28px" id="inputPath" name="inputPath" value="${input }"/>
			</div>
			<div class="form-group">
				<label for="name">输出文件路径</label>
				<input class="form-control content_cjhbtext" id="outputPath" style="height:28px" name="outputPath" value="${output }"/>
				<label id="color" style="color:red;">（说明：合并当天文件的手动合并，其输出目录不能设置为16-04-15这种形式，可以加上一个后缀，如：16-04-15-temp，同时，手动合并也要注意这个问题，合并输出目录已存在会导致合并失败！）</label> 
			</div>
				<div class="content_zjnrcqanniu"><button type="button" class="btn btn-primary" id="button" onclick="combine()">合并</button><button type="button" class="btn btn-default">取消</button></div>
          </div>
          </div>
        </div>
        </div>
    </body>
</html>
