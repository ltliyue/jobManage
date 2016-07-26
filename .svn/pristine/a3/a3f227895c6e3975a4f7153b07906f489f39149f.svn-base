<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link href="<%=path%>/resources/css/new_version/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/new_version/site.css"
	rel="stylesheet">
<link href="<%=path%>/resources/validform/css/style.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/dashboard.css" rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-select.css"
	rel="stylesheet">
<!-- Bootstrap core JavaScript
    ================================================== -->
<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
<script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>

<script src="<%=path%>/resources/js/new_version/site.js"></script>
<script
	src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.js"></script>
<script
	src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/validform/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-select.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/jquery-ui-min-1.9.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/Chinese-characters-to-Pinyin/py.js"></script>
<script src="<%=path%>/resources/js/new_version/script.js"></script>
</head>

</head>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var demo = $("#configForm").Validform({
							tiptype : 3,
							showAllError : true
						});
						//all();
						$("#configButton").click(function() {
							var status = demo.check(false);
							if (status == true) {
								//如果通过，执行自己的方法
								updateConfig();
							}
						});

					});

	//提交更新
	function updateConfig() {
		var url = "${webRoot}/datatask/taskControlConfigSub";
		var data1 = $("#configForm").serialize();
		$.post(url, data1, function(data) {
			if (data == "SUCCESS" || data == "\"SUCCESS\"") {
				//alert("基本配置修改成功");
				window.location.href="${webRoot}/datatask/settask?pageType=1";
			} else {
				alert("基本配置修改失败:" + data);
			}
		});
	}
	
	function goBack(){
		history.go(-1);
	}
</script>


<body style="margin: 0; padding: 0; border: 0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
	<div class="content_waikuang">
		<div class="container-fluid search_select select-result"
			style="padding-left: 0">
			<h2 class="content_title">任务基本配置</h2>
			<form action="${webRoot}/extractRule/updateExtractRule"
				id='configForm'>
				<input type="hidden" name="taskConfig.taskId" id="taskId"
					value="${config.taskId }">
				<c:if test="${task.status==9 }">
					<input type="hidden" name="crawlerTask.status" value="0">
				</c:if>

				<div class="form-group">
					<label for="exampleInputEmail1" class="content_zjnrcqlabel">任务ID</label>
					<input type="text" class="form-control content_zjnrcqtext"
						readonly="readonly" name='crawlerTask.taskId'
						value="${task.taskId }">
				</div>
				<div class="form-group">
					<label for="taskName" class="content_zjnrcqlabel">任务名称</label> <input
						type="text" class="form-control content_zjnrcqtext"
						readonly="readonly" value="${task.taskName }">
				</div>
				<div class="form-group">
					<label for="interval" class="content_zjnrcqlabel">执行周期</label>
					<select class="form-control content_zjnrcqselect"
						name="taskConfig.period" nullmsg="选择执行频率">
						<option value="-1" <c:if test="${config.period==-1 }">selected</c:if>>单次执行</option>
						<option value="9" <c:if test="${config.period==9 }">selected</c:if>>每小时</option>
						<option value="0" <c:if test="${config.period==0 }">selected</c:if>>每日</option>
						<option value="1" <c:if test="${config.period==1 }">selected</c:if>>每周</option>
						<option value="2" <c:if test="${config.period==2 }">selected</c:if>>每月</option>
						<option value="3" <c:if test="${config.period==3 }">selected</c:if>>每季度</option>
						<option value="4" <c:if test="${config.period==4 }">selected</c:if>>每年</option>
					</select>
				</div>
				<div class="form-group">
					<label for="transmitMode" class="content_zjnrcqlabel">占用分区(现有分区:${partitions })</label>
					<input type="hidden" name="taskConfig.partitions" value="${partitions }">
					<%-- <select class="form-control"
							name='crawlerTask.partitions' multiple="multiple">
							<option value="" <c:if test="${fun:length(partitions)<=0 }">selected</c:if>>所有分区</option>
							<c:forEach items="${partitions }" var="partition">
								<option value="${partition.partitionId }" <c:if test="${partition.taskId!=null }">selected</c:if>>${partition.partitionId }</option>
							</c:forEach>
						</select> --%>
					<select class="form-control content_zjnrcqselect"
						name='crawlerTask.groups' multiple="multiple">
						<c:forEach items="${groups }" var="group">
							<option value="${group.groupId }">${group.groupName }</option>
						</c:forEach>
					</select>
				</div>
			</form>
			<button type="button" class="btn btn-primary" onclick="goBack()">返回</button>
			<button type="button" class="btn btn-primary" id="configButton" >确认修改</button>
		</div>
	</div>
</body>
</html>


