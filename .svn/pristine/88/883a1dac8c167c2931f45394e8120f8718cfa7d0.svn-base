<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	});

	//搜索输入条件
	function searchInput() {
		$("#hiddenName").val($("#inputName").val());
		$("#hiddenUrl").val($("#inputUrl").val());
		$("#form1").submit();
	}
	//搜索选择条件和输入条件
	function searchSelect(type, val) {
		$("#hiddenName").val($("#inputName").val());
		if($("#hidden"+type).val()==val) {
			$("#hidden"+type).val("");
		}else {
			$("#hidden"+type).val(val);
		}
		$("#form1").submit();
	}
	//初始化状态选择
	function initStatus(status) {
		$("#select1>dd").removeClass("active");
		$("#s"+status).attr("class","active");
	}
	//初始化频率选择
	function initPeriod(period) {
		$("#select2>dd").removeClass("active");
		if(period!=null) {
			$("#select2").addClass("nav_headerxs");
		}
		$("#p"+period).attr("class","active");
	}
	//返回选择实例
	function selectInstance(instanceId){
		parent.instanceSelect(instanceId);
		$("#tbody input[type=checkbox]").attr('checked',false);
	}
</script>
<body style="margin: 0; padding: 0">
	<div class="content_waikuang">
		<div class="container-fluid search_select select-result"
			style="padding-left: 0">
			<h2 class="content_title">
				实例选择
			</h2>
		</div>
		<div class="row" style="clear: both">
			<div class="span3 " style="width: 17%">
				<div class="well content_left">
					<h4>筛选器</h4>
					<dl class="nav nav-list nav_headerxs">
						<dt class="nav-header">搜索</dt>
						<dd class="nav_leftinput" style="display: block">
							<input id="inputName" name="inputName" value="${crawlerTask.taskName }" type="text" style="width: 94%">
							<img src="${webRoot }/resources/img/new_version/search1.png"
								onclick="searchInput()" />
						</dd>
					</dl>
					<dl class="nav nav-list nav_headerxs" id="select1">
						<dt class="nav-header ">采集状态</dt>
						<dd id="s1">
							<span>${countStatus["1"] }</span><a href="javascript:searchSelect('Status','1')">采集中</a>
						</dd>
						<dd id="s2">
							<span>${countStatus["2"] }</span><a href="javascript:searchSelect('Status','2')">采集停止</a>
						</dd>
						<dd id="s0">
							<span>${countStatus["0"] }</span><a href="javascript:searchSelect('Status','0')">未采集</a>
						</dd>
						<dd id="s9">
							<span>${countStatus["9"] }</span><a href="javascript:searchSelect('Status','9')">未配置</a>
						</dd>
					</dl>
					<dl class="nav nav-list" id="select2">
						<dt class="nav-header ">采集频率</dt>
						<dd id="p0">
							<span>${countPeriod["0"] }</span><a href="javascript:searchSelect('Period','0')">每日</a>
						</dd>
						<dd id="p1">
							<span>${countPeriod["1"] }</span><a href="javascript:searchSelect('Period','1')">每周</a>
						</dd>
						<dd id="p2">
							<span>${countPeriod["2"] }</span><a href="javascript:searchSelect('Period','2')">每月</a>
						</dd>
						<dd id="p3">
							<span>${countPeriod["3"] }</span><a href="javascript:searchSelect('Period','3')">每季度</a>
						</dd>
						<dd id="p4">
							<span>${countPeriod["4"] }</span><a href="javascript:searchSelect('Period','4')">每年</a>
						</dd>
					</dl>
				</div>
			</div>
			<div class="span9" style="width: 74%; margin-top: -20px">
				<table class="table  table-striped content_rwgl">
					<thead>
						<tr>
							<th></th>
							<th>任务ID</th>
							<th>任务名称</th>
							<th>实例ID</th>
							<th>采集状态</th>
							<th>采集开始时间</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:if test="${not empty instances}">
							<c:forEach var="instance" items="${instances }" varStatus="ts">
								<tr align="left" onclick="selectInstance('${instance.instanceId }')">
									<td><input type='checkbox' name='chk_list'
										value="${instance.instanceId }"></td>
									<td class="content_rwgl_pzurl" style="width:8%">${instance.taskId }</td>
									<td>${instance.taskName }</td>
									<td>${instance.instanceId }</td>
									<td style="width:10%" ><c:choose>
											<c:when test="${instance.status == 0}">未采集</c:when>
											<c:when test="${instance.status == 1}">采集中</c:when>
											<c:when test="${instance.status == 2}">采集停止</c:when>
											<c:when test="${instance.status == 3}">已删除</c:when>
											<c:when test="${instance.status == 9}">未配置</c:when>
										</c:choose></td>
									<td>
										<fmt:formatDate value="${instance.publishTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>	
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<form action="${webRoot }/monitor/instanceList" id="form1">
						<input type="hidden" id="hiddenName" name="crawlerTask.taskName"
							value="${crawlerTask.taskName }">
						<input type="hidden"
							id="hiddenStatus" name="crawlerTask.status"
							value="${crawlerTask.status }">
						<input type="hidden"
							id="hiddenPeriod" name="crawlerTask.period"
							value="${crawlerTask.period }">
						<input type="hidden"
							id="hiddenStatus" name="pageType"
							value="${pageType }">
					</form>
				<div class="listtablebot">${page.pageStr }</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
</body>
</html>
