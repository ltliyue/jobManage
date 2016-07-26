<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script type="text/javascript">
	$(document).ready(function() {
		var demo = $("#form").Validform({
			tiptype : 3,
			showAllError : true
		});
		$("#button").click(function() {
			var status = demo.check(false);
			if (status == true) {
				//如果通过，执行自己的方法
				modify();
			}
		});
		initStatus("${crawlerTask.status}");
		initPeriod("${crawlerTask.period}");
		$("#demandId").val("${crawlerTask.demandId}");
	});

	function startTask(taskId) {
		var url = "${webRoot}/datatask/startTask?taskId=" + taskId;
		$
				.post(
						url,
						null,
						function(data) {
							if (data == "SUCCESS" || data == "\"SUCCESS\"") {
								alert("任务已启动");
								window.location.href = "${webRoot}/datatask/settask?pageType=${pageType}";
							} else {
								alert(data);
							}
						});
	}

	function instanceTerminate(taskId, instanceId) {
		var url = "${webRoot}/datatask/instanceTerminate?taskId=" + taskId
				+ "&instanceId=" + instanceId;
		$
				.post(
						url,
						null,
						function(data) {
							if (data == "SUCCESS" || data == "\"SUCCESS\"") {
								alert("已发送停止消息!");
								window.location.href = "${webRoot}/datatask/settask?pageType=${pageType}";
							} else {
								alert(data);
							}
						});
	}

	function editTask(taskId, taskName, url, status, siteId) {
		$("#myModalLabel").text("编辑任务");
		$("#button").text("确认修改");
		$("#flag").attr("value", 2);
		$("#taskId").attr("value", taskId);
		$("#taskName").attr("value", taskName);
		$("#url").attr("value", url);
		$("#status").attr("value", status);
		$("#taskDescribe").html($("#des_" + taskId).val());
		$("#siteId").val(siteId);
	}

	function copySite() {
		var url = $("#siteId").find("option:selected").attr("id");
		$("#url").val(url);
	}

	//打开任务配置页面
	function taskSettings(taskId) {
		window.location.href = "${webRoot}/datatask/taskConfigModal?taskId="
				+ taskId;
	}

	function taskControls(taskId) {
		window.location.href = "${webRoot}/datatask/taskControlConfig?taskId="
				+ taskId;
	}
	function deltask() {
		if ($("input[name='chk_list']:checked").length > 0) {
			if (confirm("确定终止选中任务吗？")) {
				var del_id = '';
				$("input[name='chk_list']:checked").each(function(val) {
					del_id += $(this).val() + ";";
					alert(del_id);
				});
				var url = "${webRoot}/datatask/taskdel?id=" + del_id;
				$
						.post(
								url,
								function(data) {
									alert("任务终止完成！");
									window.location.href = "${webRoot}/datatask/settask?pageType=${pageType}";
								}, "text");
			}
		} else {
			alert("请选择一项任务进行终止！");
		}
	};
	function modify() {
		var url = "${webRoot}/datatask/taskupdate";
		var data1 = $("#form").serialize();

		$
				.post(
						url,
						data1,
						function(data) {
							if (data == "SUCCESS" || data == "\"SUCCESS\"") {
								alert("修改任务成功");
								window.location.href = "${webRoot}/datatask/settask?pageType=${pageType}";
							} else {
								alert(data);
							}
						});
	}

	function taskInstances(taskId) {
		window.location.href = "${webRoot}/datatask/taskInstanceList?taskInstance.taskId="
				+ taskId;
	}

	function searchInput() {
		$("#form1").submit();
	}

	function searchSelect(type, val) {
		if ($("#hidden" + type).val() == val) {
			$("#hidden" + type).val("");
		} else {
			$("#hidden" + type).val(val);
		}
		$("#form1").submit();
	}

	function initStatus(status) {
		$("#select1>dd").removeClass("active");
		$("#s" + status).attr("class", "active");
	}

	function initPeriod(period) {
		$("#select2>dd").removeClass("active");
		if (period != null) {
			$("#select2").addClass("nav_headerxs");
		}
		$("#p" + period).attr("class", "active");
	}
</script>
<body style="margin: 0; padding: 0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<div class="content_waikuang">
		<div class="container-fluid search_select select-result"
			style="padding-left: 0">
			<h2 class="content_title">
				<c:if test="${pageType==0 }">任务确立</c:if>
				<c:if test="${pageType==1 }">任务调度</c:if>
			</h2>
			<c:if test="${pageType==0 }">
				<div class="renwu_caozuo" style="margin-right: 50px">
					<a class="renwu_select" href="<%=path%>/datatask/taskadd">新建任务</a>
				</div>
			</c:if>
		</div>

		<div class="row" style="clear: both">
			<form action="${webRoot }/datatask/settask" id="form1">
				<input type="hidden" id="hiddenStatus" name="crawlerTask.status" value="${crawlerTask.status }">
				<input type="hidden" id="hiddenPeriod" name="crawlerTask.period" value="${crawlerTask.period }">
				<input type="hidden" id="hiddenStatus" name="pageType" value="${pageType }">
				<div class="span3 " style="width: 17%">
					<div class="well content_left">
						<h4>筛选器</h4>
						<dl class="nav nav-list nav_headerxs">
							<dt class="nav-header">搜索</dt>
							<dd class="nav_leftinput" style="display: block">
								<input id="inputName" name="crawlerTask.taskName"
									value="${crawlerTask.taskName }" type="text" style="width: 94%">
								<img src="${webRoot }/resources/img/new_version/search1.png"
									onclick="searchInput()" />
							</dd>
							<dt class="nav-header">需求</dt>
							<dd class="nav_leftinput" style="display: block">
								<select id="demandId" name="crawlerTask.demandId" style="width: 193px" onchange="searchInput()">
									<option value="">全部</option>
									<c:forEach var="demand" items="${demands }">
										<option value="${demand.demandId }">${demand.demandName }</option>
									</c:forEach>
								</select>
							</dd>
						</dl>
						<dl class="nav nav-list nav_headerxs" id="select1">
							<dt class="nav-header ">采集状态</dt>
							<dd id="s1">
								<span>${countStatus["1"] }</span><a
									href="javascript:searchSelect('Status','1')">采集中</a>
							</dd>
							<dd id="s2">
								<span>${countStatus["2"] }</span><a
									href="javascript:searchSelect('Status','2')">采集停止</a>
							</dd>
							<dd id="s0">
								<span>${countStatus["0"] }</span><a
									href="javascript:searchSelect('Status','0')">未采集</a>
							</dd>
							<dd id="s9">
								<span>${countStatus["9"] }</span><a
									href="javascript:searchSelect('Status','9')">未配置</a>
							</dd>
						</dl>
						<dl class="nav nav-list" id="select2">
							<dt class="nav-header ">采集频率</dt>
							<dd id="p0">
								<span>${countPeriod["0"] }</span><a
									href="javascript:searchSelect('Period','0')">每日</a>
							</dd>
							<dd id="p1">
								<span>${countPeriod["1"] }</span><a
									href="javascript:searchSelect('Period','1')">每周</a>
							</dd>
							<dd id="p2">
								<span>${countPeriod["2"] }</span><a
									href="javascript:searchSelect('Period','2')">每月</a>
							</dd>
							<dd id="p3">
								<span>${countPeriod["3"] }</span><a
									href="javascript:searchSelect('Period','3')">每季度</a>
							</dd>
							<dd id="p4">
								<span>${countPeriod["4"] }</span><a
									href="javascript:searchSelect('Period','4')">每年</a>
							</dd>
						</dl>
					</div>
				</div>
			</form>
			<div class="span9" style="width: 74%; margin-top: -20px">
				<table class="table  table-striped content_rwgl">
					<thead>
						<tr>
							<th><input type="checkbox" name="chk_all" id="chk_all"
								onclick="SelectAll()" /></th>
							<th>任务ID</th>
							<th>任务名称</th>
							<th>当前实例ID</th>
							<th>URL</th>
							<th>创建人</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:if test="${not empty tasks}">
							<c:forEach var="task" items="${tasks }" varStatus="ts">
								<tr align="left">
									<td><input type='checkbox' name='chk_list'
										value="${task.taskId }"></td>
									<td style="width: 7%">${task.taskId }</td>
									<td>${task.taskName }</td>
									<td style="width: 15%">${task.currentInstance }</td>
									<td class="content_rwgl_pzurl">${task.url }</td>
									<td>${task.creator }</td>
									<td><c:choose>
											<c:when test="${task.status == 0}">未采集</c:when>
											<c:when test="${task.status == 1}">采集中</c:when>
											<c:when test="${task.status == 2}">采集停止</c:when>
											<c:when test="${task.status == 3}">已删除</c:when>
											<c:when test="${task.status == 9}">未配置</c:when>
										</c:choose></td>
									<td class="content_rwgl_pz" style="width: 80px"><c:if
											test="${pageType==1 }">
											<c:if
												test="${task.status==0 || task.status==1 || task.status==2 }">
												<button type="button" id="start_${task.taskId }"
													class="content_rwgl_pzanniu"
													onclick="startTask('${task.taskId }')">启动任务</button>
											</c:if>
											<c:if test="${task.status==1 }">
												<button type="button" id="stop_${task.taskId }"
													class="content_rwgl_pzanniu"
													onclick="instanceTerminate('${task.taskId }','${task.currentInstance}')">停止任务</button>
											</c:if>
											<button type="button" id="instance_${task.taskId }"
												class="content_rwgl_pzanniu1"
												onclick="taskInstances('${task.taskId }')">实例</button>
											<button type="button" id="config_${task.taskId }"
												class="content_rwgl_pzanniu1"
												onclick="taskControls('${task.taskId }')">配置</button>
										</c:if> <c:if test="${pageType==0 }">
											<a type="button" id="edit_${task.taskId }"
												data-target="#modifyInstance" data-toggle="modal"
												style="cursor: pointer"
												onclick="editTask('${task.taskId }','${task.taskName}','${task.url}','${task.status }','${task.siteId }')">编辑</a>

											<input type="hidden" id="des_${task.taskId }"
												value="${task.taskDescribe }">
											<a type="button" id="config_${task.taskId }"
												onclick="taskSettings('${task.taskId }')">配置</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<div class="listtablebot">${page.pageStr }</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal hide fade" id="modifyInstance" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">编辑任务</h4>
				</div>
				<form id="form" method="POST">
					<input type="hidden" name="flag" id="flag">
					<div class="modal-body">
						<div class="form-group">
							<label class="content_zjnrcqlabel">任务ID</label> <input
								type="text" class="form-control" readonly="readonly"
								name='taskId' style="width: 80%" id="taskId" value=""
								placeholder="任务ID">
						</div>
						<div class="form-group">
							<label class="content_zjnrcqlabel">任务名称</label> <input
								type="text" class="form-control" name='crawlerTask.taskName'
								id="taskName" style="width: 80%" datatype="*" nullmsg="请输入任务名称！"
								errormsg="请输入任务名称！" placeholder="">
						</div>
						<div>
							<label class="content_zjnrcqlabel"><span
								class="content_zjnrcqspan">请选择任务网站</span>任务网站</label> <select
								class="form-control content_zjnrcqlabel"
								name='crawlerTask.siteId' id="siteId" datatype="s"
								onchange="copySite()" style="width: 100%" nullmsg="请选择网站"
								errormsg="必须选择网站">
								<option value="">请选择种子网站</option>
								<c:forEach items="${sites }" var="site">
									<option value="${site.id }" id="${site.url }">${site.name }-${site.url }<c:if
											test="${not empty site.executionCycle }">(${site.executionCycle })</c:if></option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label class="content_zjnrcqlabel">URL</label> <input type="text"
								class="form-control" name='crawlerTask.url' id="url"
								style="width: 80%" datatype="url" nullmsg="请输入采集种子URL！"
								errormsg="URL不符合规范！" placeholder="">
						</div>
						<div class="form-group">
							<label class="content_zjnrcqlabel">任务描述</label>
							<textarea style="text-align: left; width: 80%"
								class="form-control" name='crawlerTask.taskDescribe'
								id="taskDescribe" cols="100" rows="4" placeholder="任务描述"></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关
					闭</button>
				<button type="button" class="btn btn-primary" id="button">确认修改</button>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
</body>
<script type="text/javascript">
	$(document).ready(function() {
		var goSetting = $
		{
			goSetting
		}
		;
		if (goSetting && "${crawlerTask.taskId}" != "") {
			$("#config_${crawlerTask.taskId}").click();
		}
	});
</script>
</html>
