<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		//getAllExtractRules();
		initStatus("${containsMulti}");
	});
	function getAllExtractRules() {
		var url = "${webRoot}/extractRule/getAllExtractRule";
		$
				.post(
						url,
						function(data) {
							json = eval('(' + data + ')');
							$
									.each(
											json,
											function(i) {
												var enable = "可用";
												if (json[i].enabled.toString() == "0") {
													enable = "不可用";
												}
												var type = null;
												if (json[i].extractType
														.toString() == "1") {
													extractType = "根据正则表达式抽取内容";
												}
												if (json[i].extractType
														.toString() == "2") {
													extractType = "根据xpath抽取内容";
												}
												$("#tbody")
														.append(
																"<tr> <td><input type='checkbox' name='chk_list' value='"+
							  json[i].id+"' ></td><td>"
																		+ (i + 1)
																		+ "</td><td id='taskId'"+i+">"
																		+ json[i].taskId
																		+ "</td>"
																		+ "<td id='urlPattern'"+i+">"
																		+ json[i].urlPattern
																		+ "</td>"
																		+ "<td id='extractType'"+i+">"
																		+ extractType
																		+ "</td>"
																		+ "<td id='regex'"+i+">"
																		+ json[i].regex
																		+ "</td>"
																		+ "<td id='regexGroupId'"+i+">"
																		+ json[i].regexGroupId
																		+ "</td>"
																		+ "<td id='xpath'"+i+">"
																		+ json[i].xpath
																		+ "</td>"
																		+ "<td id='enabled'"+i+">"
																		+ enable
																		+ "</td>"
																		+ "</tr>");
											});
						});
	};
	function deleteExtractRule(ruleid) {
		if (confirm("确定删除选中项目吗？")) {
			var url = "${path}/crawlManage/pageExtractRule/deleteExtractRule?id="
					+ ruleid;
			$
					.post(
							url,
							function(data) {
								alert("删除完成！");
								window.location.href = "${path}/pageExtractRule/extractRuleManage?taskId="
										+ $("#taskId").val();
								location.reload();
							}, "text");
		}
	};
	function updateExtractRule(ruleid) {
		window.location.href = "${path}/crawlManage/extractRule/extractRuleModal?t=2&modal=0&id="
				+ ruleid;
	}

	function searchInput() {
		$("#hiddenName").val("");
		$("#hiddenTaskId").val("");
		$("#containsMulti").val("");
		if ((/\d+/g).test($("#inputName").val())) {
			$("#hiddenTaskId").val($("#inputName").val());
		} else {
			$("#hiddenName").val($("#inputName").val());
		}
		if ($("#containsMulti") == "") {
			$("#containsMulti").val("-1");//必须是数字
		}

		$("#form1").submit();
	}

	function searchSelect(type, val) {
		$("#" + type).val(val);
		$("#form1").submit();
	}

	function initStatus(status) {
		$("#select1>dd").removeClass("active");
		$("#s" + status).attr("class", "active");
	}
</script>
<body style="margin: 0; padding: 0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
	<div class="content_waikuang">
		<div class="container-fluid search_select" style="padding-left: 0">

			<h2 class="content_title">内容抽取规则展示和管理</h2>
			<input id="taskId" type="text" style="display: none"
				value="${taskId}"></input>
			<div class="renwu_caozuo"
				style="text-align: right; margin-right: 15px">
				<a class="renwu_select"
					href="${path}/crawlManage/extractRule/extractRuleModal?t=1&modal=0&id=${taskId}">添加</a>
			</div>
		</div>
		<div class="row" style="clear: both">
			<div class="span3 " style="width: 17%">
				<div class="well content_left">
					<h4>筛选器</h4>
					<dl class="nav nav-list nav_headerxs">
						<dt class="nav-header">搜索</dt>
						<dd class="nav_leftinput" style="display: block">
							<input id="inputName" name="inputName" value="${inputName}"
								placeholder="规则名称或任务id" type="text" style="width: 94%">
							<img
								src="${webRoot }/crawlManage/resources/img/new_version/search1.png"
								onclick="searchInput()" />
						</dd>
					</dl>
					<dl class="nav nav-list nav_headerxs" id="select1">
						<dt class="nav-header ">数据条数</dt>
						<dd id="s1">
							<span>${countStatus["1"] }</span><a
								href="javascript:searchSelect('containsMulti',1)">一条数据</a>
						</dd>
						<dd id="s2">
							<span>${countStatus["2"] }</span><a
								href="javascript:searchSelect('containsMulti',2)">多条数据</a>
						</dd>
					</dl>
				</div>
			</div>
			<div class="span9" style="width: 74%; margin-top: -20px">
				<form id="form1" action="<%=path%>/pageExtractRule/searchExtractRule">
					<input type="hidden" id="hiddenName" name="name" value="${ruleName}"> 
					<input type="hidden" id="hiddenTaskId" name="taskId" value="${taskId}"> 
					<input type="hidden" id="containsMulti" name="containsMulti" value="${containsMulti}">

					<table class="table  table-striped content_rwgl"style="width: 100%;">
						<thead>
							<tr>
								<th>规则名称</th>
								<th>任务id</th>
								<th>数据条数</th>
								<th>创建人</th>
								<th>创建时间</th>
								<th>启用</th>
								<th>操作</th>
							</tr>
						</thead>

						<tbody id="tbody">
							<c:if test="${not empty rules}">
								<c:forEach var="rule" items="${rules}" varStatus="ts">
									<tr>
										<td>${rule.name }</td>
										<td>${rule.taskId }</td>
										<td><c:if test="${rule.containsMulti  ==1}">
								一条数据
								</c:if> <c:if test="${rule.containsMulti  ==2}">
								多条数据
								</c:if></td>
										<td>${rule.creator }</td>
										<td><fmt:formatDate value="${rule.time}"
												pattern="yyyy-MM-dd hh:mm:ss" /></td>
										<td><c:if test="${rule.enabled  ==0}">
							  否
							</c:if> <c:if test="${rule.enabled  ==1}">
							  是
							</c:if></td>
										<td><a onclick="updateExtractRule('${rule.id}')">修改</a> <a
											onclick="deleteExtractRule('${rule.id}')">删除</a></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</form>
				<div class="listtablebot">${page.pageStr }</div>
			</div>
		</div>
	</div>


	<!-- Bootstrap core JavaScript
    ================================================== -->
</body>
</html>

