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
    <link href="<%=path %>/resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=path %>/resources/css/dashboard.css" rel="stylesheet">
	<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
	 <!-- Bootstrap core jss -->
	<script src="<%=path %>/resources/js/jquery.min.js"></script>
    <script src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
  </head>
	<script type="text/javascript">
		$(document).ready(function(){
			setInterval("loadTaskMonitorInfo()", 1000);
		});
		var taskId = ${taskId};
		var instanceId = ${instanceId};
		function loadTaskMonitorInfo() {
			$.ajax({
				url:"${webRoot}/datatask/taskMonitorLoad?crawlerTask.taskId="+taskId+"&crawlerTask.instanceId="+instanceId,
				dataType:"json",
				success:function(data){
					$("#tbody").append('<tr align="left"><td>'+data.publisher+'</td><td>'+data.time+'</td><td>'+data.url+'</td></tr>');
				}
			});
		}
	</script>
  <body>
<jsp:include page="../topBase.jsp"></jsp:include>
<jsp:include page="../leftBase.jsp"></jsp:include>
 <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
       
          <h1 class="page-header">任务监控</h1>

          <div class="row placeholders">
            <div class="col-xs-6 col-sm-3 placeholder">
              <h4><a href="<%=path%>/datatask/taskadd">新建任务</a></h4>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <h4><a href="#" onclick="deltask()">删除任务</a></h4>
            </div>
            <!-- <div class="col-xs-6 col-sm-3 placeholder">
              <h4><a href="#" onclick="update()">修改角色</a></h4>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <h4><a href="#" onclick="delrole()">删除角色</a></h4>
            </div>
          </div> -->

          <h2 class="sub-header">采集网页</h2>
          <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>采集节点</th>
                  <th>采集时间</th>
                  <th>采集网页</th>
                </tr>
              </thead>
			  <tbody id="tbody">
			  </tbody>
            </table>
          </div>
        </div>
        </div>
    </body>
</html>
