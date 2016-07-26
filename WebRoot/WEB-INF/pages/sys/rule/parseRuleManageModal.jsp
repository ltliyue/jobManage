<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<link href="/crawlManage/resources/css/new_version/bootstrap.min.css"
	rel="stylesheet">
<link href="/crawlManage/resources/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="/crawlManage/resources/css/new_version/site.css"
	rel="stylesheet">
<link href="/crawlManage/resources/validform/css/style.css"
	rel="stylesheet">
<link href="/crawlManage/resources/css/dashboard.css" rel="stylesheet">
<link href="/crawlManage/resources/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link href="/crawlManage/resources/css/bootstrap-select.css"
	rel="stylesheet">
<!-- Bootstrap core JavaScript
    ================================================== -->
<script src="/crawlManage/resources/js/new_version/jquery.min.js"></script>
<script src="/crawlManage/resources/js/new_version/bootstrap.min.js"></script>

<script src="/crawlManage/resources/js/new_version/site.js"></script>
<script
	src="/crawlManage/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.js"></script>
<script
	src="/crawlManage/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="/crawlManage/resources/validform/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript"
	src="/crawlManage/resources/js/bootstrap3.3.5/bootstrap-select.js"></script>
<script type="text/javascript"
	src="/crawlManage/resources/js/jquery-ui-min-1.9.js"></script>
<script type="text/javascript"
	src="/crawlManage/resources/js/Chinese-characters-to-Pinyin/py.js"></script>
<script src="/crawlManage/resources/js/new_version/script.js"></script>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#nextStep").click(function() {
			window.location.href =
				"${webRoot}/crawlManage/pageExtractRule/extractRuleManageModal?taskId="+ $("#taskId").val();
		});
		
		$("#prevStep").click(function() {
			window.location.href =
				"${path}/crawlManage/datatask/taskConfigModal?taskId=" + $("#taskId").val();
		});
	});

	function deleteParseRule(ruleid) {
		if (confirm("确定删除选中项目吗？")) {
			var url = "${path}/crawlManage/parseRuleTemp/deleteParseRule?id="
					+ ruleid;
			$
					.post(
							url,
							function(data) {
								alert("删除完成！");
								window.location.href = "${path}/crawlManage/parseRuleTemp/parseRuleManageModal?taskId="
									+ $("#taskId").val();
								localtion.reload(true);
							}, "text");
		}
	};
	function updateParseRule(ruleid) {
		window.location.href = "${path}/crawlManage/parseRuleTemp/parseRuleModal?t=2&modal=1&id="
				+ ruleid;
	}
	
	$("#nextStep").click(function() {
		window.location.href = "${webRoot}/pageExtractRule/extractRuleManageModal?taskId="
			+ $("#taskId").val();
	});

	
</script>
<body style="margin: 0; padding: 0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
	<div class="content_waikuang">
		<div class="container-fluid search_select"
			style="padding-left: 0">

			<h2 class="content_title">URL解析规则展示和管理</h2>
			<input id="taskId" type="text" style="display: none"
				value="${taskId}"></input>
			<div class="renwu_caozuo">
				<a class="renwu_select"
					href="${path}/crawlManage/parseRuleTemp/parseRuleModal?t=1&modal=1&id=${taskId}">添加</a>
			</div>

			<div class="table-responsive">
				<form action="<%=path%>/crawlManage/parseRule/setParseRule">
					<table class="table  table-striped content_rwgl"
						style="width: 100%;">
						<thead>
							<tr>
								<th>规则名称</th>
								<th>任务id</th>
								<th>抽取方式</th>
								<th>创建人</th>
								<th>创建时间</th>
								<th>启用</th>
							</tr>
						</thead>

						<tbody id="tbody">
							<c:if test="${not empty rules}">
								<c:forEach var="rule" items="${rules}" varStatus="ts">
									<tr>
										<td>${rule.name }</td>
										<td>${rule.taskId}</td>
										<td><c:if test="${rule.extractType  =='1'}">
								正则
								</c:if> <c:if test="${rule.extractType  =='2'}">
								xpath
								</c:if></td>
										<td>${rule.creator }</td>
										<td><fmt:formatDate value="${rule.time}"
										pattern="yyyy-MM-dd hh:mm:ss" /></td>
										
										<td><c:if test="${rule.enabled  ==0}">
							  否
							</c:if> <c:if test="${rule.enabled  ==1}">
							  是
							</c:if></td>
									<td>
										<a class="renwu_select" href="#" onclick="updateParseRule('${rule.id }')">修改</a>
										<a class="renwu_select" onclick="deleteParseRule('${rule.id }')">删除</a>
									</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</form>
				<div class="listtablebot">${page.page.pageStr }</div>
				<button type="button" class="btn btn-primary" id="prevStep">上一步</button>
				<button type="button" class="btn btn-primary" id="nextStep">下一步</button>
				
			</div>
		</div>
	</div>
	
	<!-- Bootstrap core JavaScript
    ================================================== -->
</body>
</html>

