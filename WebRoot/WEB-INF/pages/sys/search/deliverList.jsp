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
		$("#demandId").val("${deliver.demandId}");
	});
	function searchInput() {
		$("#form1").submit();
	}

	//返回选择实例
	function selectDeliver(demandId, publishId, demandName) {
		parent.versionSelect(demandId, publishId, demandName);
		$("#tbody input[type=checkbox]").attr('checked', false);
	}
</script>
<body style="margin: 0; padding: 0">
	<div class="content_waikuang">
		<div class="container-fluid search_select select-result"
			style="padding-left: 0">
			<h2 class="content_title">需求交付版本选择</h2>
		</div>
		<div class="row" style="clear: both">
			<div class="span3 ">
				<div class="well content_left" style="height:60px">
					<h4>需求选择</h4>
					<dl class="nav nav-list nav_headerxs" id="select1">
						<dd class="nav_leftinput" style="display: block">
							<form action="${webRoot }/datasearch/deliverList" id="form1">
								<select id="demandId" name="deliver.demandId"
									onchange="searchInput()">
									<option value="">全部</option>
									<c:forEach var="demand" items="${demands }">
										<option value="${demand.demandId }">${demand.demandName }</option>
									</c:forEach>
								</select>
							</form>
						</dd>
					</dl>
				</div>
			</div>
			<div class="span9" style="margin-top: -20px">
				<table class="table  table-striped content_rwgl">
					<thead>
						<tr>
							<th></th>
							<th>需求名称</th>
							<th>交付版本</th>
							<th>交付时间</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<c:if test="${not empty delivers}">
							<c:forEach var="deliver" items="${delivers }" varStatus="ts">
								<tr align="left"
									onclick="selectDeliver('${deliver.demandId }','${deliver.publishId}','${deliver.demandName}')">
									<td><input type='checkbox' name='chk_list'
										value="${deliver.demandId }"></td>
									<td>${deliver.demandName }</td>
									<td>${deliver.publishId }</td>
									<td><fmt:formatDate value="${deliver.deliverTime }"
											pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<div class="listtablebot">${page.pageStr }</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
</body>
</html>
