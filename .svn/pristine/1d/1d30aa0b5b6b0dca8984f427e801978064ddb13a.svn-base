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
<!-- Bootstrap core jss -->
<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
<script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
<script src="<%=path%>/resources/js/new_version/script.js"></script>
<script src="<%=path%>/resources/js/new_version/site.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		//getAllParseRules();
		initStatus("${extractType}");
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
									window.location.href = "${path}/crawlManage/parseRuleTemp/setParseRule";
									localtion.reload(true);
								}, "text");
			}
	};
	function updateParseRule(ruleid) {
			window.location.href = "${path}/crawlManage/parseRuleTemp/parseRuleModal?t=2&modal=0&id="
					+ ruleid;
	}
	function selectInstanceId(ruleId,taskId,Name){
		var re;		
		var id={ruleId:ruleId,taskId:taskId};
	 	$.ajax({
	      url: '${path}/crawlManage/diagnoseCrawl/getInstanceList' ,
	      type: 'post',
	      datatype: String,
	      data: id,
	      async:false,
	      success: function (data) {
	    	  re=data;
	      },
	      error: function (returndata) {
	    	  alert("fail");
	      }
	 	});
	 	var arr = new Array();
	 	arr=re.split("|");	 	
		$("#myModalLabel").text("规则名称:"+Name);
		$("#tableInstance tbody").html("");
		for(var i=0; i<arr.length-1;i++){
			 $("#tbodyInstance").append("<tr><td><a class=\"instance-list\" href=\"#\" onclick=\"goDetail('"+ruleId+"','"+arr[i].split("---")[0]+"','"+taskId+"')\">"+arr[i].split("---")[0]+"</a></td><td>"+arr[i].split("---")[1]+"</td></tr>");
		}
	}
	function goDetail(ruleId,instanceId,taskId){
		window.location.href = "${path}/crawlManage/diagnoseCrawl/initPurl?pager=1"+"&rule_id="
			+ ruleId+"&task_instance_id="+instanceId+"&task_id="+taskId;
	}
	function searchInput() {
		$("#hiddenName").val("");
		$("#hiddenTaskId").val("");
		$("#extractType").val("");
		if((/\d+/g).test($("#inputName").val())){
			$("#hiddenTaskId").val($("#inputName").val());
		}else{
			$("#hiddenName").val($("#inputName").val());
		}
		$("#form1").submit();
	}
	
	//1是正则表达式，2是xpath
	function searchSelect(type, val) {
		$("#"+type).val(val);
		$("#form1").submit();
	}
	
	function initStatus(status) {
		$("#select1>dd").removeClass("active");
		$("#s"+status).attr("class","active");
	}
</script>
<body style="margin: 0; padding: 0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
	<div class="content_waikuang">
		<div class="container-fluid search_select "
			style="padding-left: 0">

			<h2 class="content_title">URL解析规则查询</h2>
			<input id="taskId" type="text" style="display: none"
				value="${taskId}"></input>
		</div>
		<div class="row" style="clear: both">
			<div class="span3 " style="width: 17%">
				<div class="well content_left">
					<h4>筛选器</h4>
					<dl class="nav nav-list nav_headerxs">
						<dt class="nav-header">搜索</dt>
						<dd class="nav_leftinput" style="display: block">
							<input id="inputName" name="inputName"  value="${inputName}" placeholder="任务名称或任务id" type="text" style="width: 94%">
							<img src="${path}/crawlManage/resources/img/new_version/search1.png"
								onclick="searchInput()" />
						</dd>
					</dl>
					<dl class="nav nav-list nav_headerxs" id="select1">
						<dt class="nav-header ">抽取方式</dt>
						<dd id="s1">
							<span>${countStatus["1"] }</span><a
								href="javascript:searchSelect('extractType','1')">正则表达式</a>
						</dd>
						<dd id="s2">
							<span>${countStatus["2"] }</span><a
								href="javascript:searchSelect('extractType','2')">xpath</a>
						</dd>
					</dl>
				</div>
			</div>
			<div class="span9" style="width: 74%; margin-top: -20px">
				<form id="form1" action="<%=path%>/diagnoseCrawl/searchParseRule">
					<input type="hidden" id="hiddenName" name="name" value="${ruleName}">
					<input type="hidden" id="hiddenTaskId" name="taskId" value="${taskId}">
					<input type="hidden" id="extractType" name="extractType" value="${extractType}">
					
					<table class="table  table-striped content_rwgl"
						style="width: 100%;">
						<thead>
							<tr>
								<th>规则名称</th>
								<th>任务id</th>
								<th>任务名称</th>
								<th>抽取方式</th>
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
										<td>${rule.taskId}</td>
										<td>${rule.taskName }</td>
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
										<a class="renwu_select" type="button" id="edit"  style="cursor:pointer"	 data-toggle="modal" data-target="#modifyDemand" onclick="selectInstanceId('${rule.id }','${rule.taskId }','${rule.name }')">选择所属实例</a>
									</td>
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
	<div class="modal fade" id="modifyDemand" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">任务实例列表</h4>
	      </div>
	      <div class="modal-body" >
	      	 <table class="table" id="tableInstance" style="width: 100%;">
				<thead>
					<tr>
						<th>任务实例id</th>
						<th>发布时间</th>
					</tr>
				</thead>	
				<tbody id="tbodyInstance"></tbody>
			</table>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>	        
	      </div>
	    </div>
	  </div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
</body>
</html>

