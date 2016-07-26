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
								updateConfig
							}
						});
						$("#nextStepButton").click(function() {
							var status = demo.check(false);
							if (status == true) {
								//$("#urlParseRuleConfig").load("${webRoot}/parseRule/parseRule?t=2&id=${config.taskId}");
							}
						});

						$("#nextStep")
								.click(
										function() {
											//先提交
											updateConfig();
											window.location.href = "${webRoot}/parseRuleTemp/parseRuleManageModal?taskId="
													+ $("#taskId").val();
										});

					});

	//提交更新
	function updateConfig() {
		var url = "${webRoot}/datatask/taskConfigSub";
		var data1 = $("#configForm").serialize();
		$.post(url, data1, function(data) {
			if (data == "SUCCESS" || data == "\"SUCCESS\"") {
				//alert("基本配置修改成功");
				//window.location.href="${webRoot}/datatask/settask";
			} else {
				alert("基本配置修改失败:" + data);
			}
		});
	}
	
	function changeExecuteJs(crawlType){
		if(crawlType == 1) {
			$("#jsConfigDiv").show();
		}else {
			$("#jsConfigDiv").hide();
		}
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
					<label for="maxDepth" class="content_zjnrcqlabel">采集范围</label>
					<select class="form-control content_zjnrcqselect"
						name="taskConfig.crawlerScope" nullmsg="请选择采集范围"
						errormsg="必须选择采集范围">
						<option value="0" <c:if test="${taskConfig.crawlerScope==0 }">selected</c:if>>全量采集</option>
						<option value="1" <c:if test="${taskConfig.crawlerScope==1 }">selected</c:if>>增量采集</option>
					</select>
				</div>
				<div class="form-group">
					<label for="maxDepth" class="content_zjnrcqlabel">采集方式</label>
					<select class="form-control content_zjnrcqselect"
						name="taskConfig.crawlerType" nullmsg="请选择采集方式"
						errormsg="必须选择采集方式" onchange="changeExecuteJs(this.value)">
						<option value="1" <c:if test="${config.crawlerType==1 }">selected</c:if>>HtmlUnit</option>
						<option value="0" <c:if test="${config.crawlerType==0 }">selected</c:if>>HttpClient</option>
					</select>
				</div>
				<div class="form-group" id="jsConfigDiv" <c:if test="${config.crawlerType==0 }"> style="display:none" </c:if> >
					<label for="executeJs" class="content_zjnrcqlabel">是否执行JS</label>
					<select class="form-control content_zjnrcqselect"
						name="taskConfig.executeJs" nullmsg="选择是否执行JS">
						<option value="0" <c:if test="${config.executeJs==0 }">selected</c:if>>不执行</option>
						<option value="1" <c:if test="${config.executeJs==1 }">selected</c:if>>执行</option>
					</select>
				</div>
				<div class="form-group">
					<label for="maxDepth" class="content_zjnrcqlabel">最大深度</label> <input
						type="text" class="form-control content_zjnrcqtext"
						name="taskConfig.maxDepth" value="${config.maxDepth }"
						datatype="n" nullmsg="请输入最大深度的值！" errormsg="最大深度为数字！"
						placeholder="">
				</div>
				<div class="form-group">
					<label for="failureCount" class="content_zjnrcqlabel">最大失败次数</label>
					<input type="text" class="form-control content_zjnrcqtext"
						name="taskConfig.failureCount" value="${config.failureCount }"
						datatype="n" nullmsg="请输入允许最大失败次数的值！" errormsg="允许最大失败次数为数字！"
						placeholder="">
				</div>
				<div class="form-group">
					<label for="interval" class="content_zjnrcqlabel">采集间隔(毫秒)</label>
					<input type="text" class="form-control content_zjnrcqtext"
						name="taskConfig.interval" value="${config.interval }"
						datatype="n" nullmsg="请输入采集间隔的值！" errormsg="采集间隔为数字！"
						placeholder="">
				</div>
				<%-- <div class="form-group">
					<label for="interval" class="content_zjnrcqlabel">执行周期</label>
					<select class="form-control content_zjnrcqselect"
						name="taskConfig.period" nullmsg="选择是否执行JS">
						<option value="9" <c:if test="${config.period==9 }">selected</c:if>>每小时</option>
						<option value="0" <c:if test="${config.period==0 }">selected</c:if>>每日</option>
						<option value="1" <c:if test="${config.period==1 }">selected</c:if>>每周</option>
						<option value="2" <c:if test="${config.period==2 }">selected</c:if>>每月</option>
						<option value="3" <c:if test="${config.period==3 }">selected</c:if>>每季度</option>
						<option value="4" <c:if test="${config.period==4 }">selected</c:if>>每年</option>
					</select>
				</div> --%>
				<div class="form-group">
					<label for="transmitMode" class="content_zjnrcqlabel">发送方式</label>
					<select class="form-control content_zjnrcqselect"
						name="taskConfig.transmitMode" nullmsg="请选择发送方式"
						errormsg="必须选择发送方式">
						<option value="GET" <c:if test="${config.transmitMode=='GET' }">selected</c:if>>GET</option>
						<option value="POST" <c:if test="${config.transmitMode=='POST' }">selected</c:if>>POST</option>
					</select>
				</div>
				<%-- <div class="form-group">
					<label for="transmitMode" class="content_zjnrcqlabel">占用分区(现有分区:${partitions })</label>
					<input type="hidden" name="taskConfig.partitions" value="${partitions }">
					<select class="form-control"
							name='crawlerTask.partitions' multiple="multiple">
							<option value="" <c:if test="${fun:length(partitions)<=0 }">selected</c:if>>所有分区</option>
							<c:forEach items="${partitions }" var="partition">
								<option value="${partition.partitionId }" <c:if test="${partition.taskId!=null }">selected</c:if>>${partition.partitionId }</option>
							</c:forEach>
						</select>
					<select class="form-control content_zjnrcqselect"
						name='crawlerTask.groups' multiple="multiple">
						<option value="">所有分区</option>
						<c:forEach items="${groups }" var="group">
							<option value="${group.groupId }">${group.groupName }</option>
						</c:forEach>
					</select>
				</div> --%>
				<div class="form-group">
					<label for="transmitMode"
						class="content_zjnrcqlabel content_zjnrcqtext">过滤规则</label> <input
						type="text" class="form-control" name='crawlerTask.urlFilters'
						value="${urlFilters }">
				</div>
			</form>
			<button type="button" class="btn btn-primary" onclick="goBack()">返回</button>
			<button type="button" class="btn btn-primary" id="nextStep">下一步</button>
			<button type="button" class="btn btn-primary" id="configButton"
				style="display: none">确认修改</button>
		</div>
	</div>
</body>
</html>


