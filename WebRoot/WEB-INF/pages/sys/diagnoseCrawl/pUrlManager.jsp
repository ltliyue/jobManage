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

	function updateParseRule(ruleid) {
			window.location.href = "${path}/crawlManage/parseRuleTemp/parseRuleModal?t=2&modal=0&id="
					+ ruleid;
	}
	function searchInput(ruleId,instanceId,taskId) {
		window.location.href = "${path}/crawlManage/diagnoseCrawl/initPurl?pager=1"
			+"&rule_id="
			+ ruleId+"&task_instance_id="+instanceId+"&task_id="+taskId+"&p_url="+$("#inputName").val();
	}
	
	function initStatus(status) {
		$("#select1>dd").removeClass("active");
		$("#s"+status).attr("class","active");
	}
	 function nfy(pager,ruleId,instanceId,taskId){			
		window.location.href = "${path}/crawlManage/diagnoseCrawl/initPurl?pager="
				+ (parseInt(pager)+1)+"&rule_id="
				+ ruleId+"&task_instance_id="+instanceId+"&task_id="+taskId;;
	}
	 function lfy(pager,ruleId,instanceId,taskId){			
		window.location.href = "${path}/crawlManage/diagnoseCrawl/initPurl?pager="
				+ (parseInt(pager)-1)+"&rule_id="
				+ ruleId+"&task_instance_id="+instanceId+"&task_id="+taskId;
	}
	 function hunterPageContrlNextJump(pager,ruleId,instanceId,taskId){
		var pager;
		if($("#num").val()==''){
			alert("请输入页数");
		}else{
			pager=$("#num").val();
			window.location.href = "${path}/crawlManage/diagnoseCrawl/initPurl?pager="
				+ (parseInt(pager))+"&rule_id="
				+ ruleId+"&task_instance_id="+instanceId+"&task_id="+taskId;
		}
		
	 }
	 function contrast(instanceId,ruleid,otherid){
// 		 alert(ruleid+","+otherid);
		 window.location.href = "${path}/crawlManage/urlrelation/showDifferUrls?instanceId="+instanceId+"&parseRuleId1="+ruleid+"&parseRuleId2="+otherid;;
	 }
	 function query(instanceId, parseRuleId,parentUrl){
		window.location.href ="${path}/crawlManage/urlrelation/showSubUrls?instanceId="+instanceId+"&parseRuleId="+parseRuleId+"&parentUrl="+encodeURIComponent(parentUrl);
	 }
</script>
<body style="margin: 0; padding: 0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
	<div class="content_waikuang">
		<div class="row" style="clear: both">
			<div class="index_top">
				<h5>url漏采详情列表:</h5>
					<div class="well summary"  id="index_top_top" style="font-size:6px" > 
						<ul >
					        <li >
								<span class="count"  style="height:5px">规则名称:${detail.name }</span>
							</li>
							<li >
								<span class="count" style="height:5px">任务名称:${detail.task_name}</span>
							</li>
							<li >
								<span class="count" style="height:5px">任务实例id:${task_instance_id }</span>
							</li>
						</ul>
					</div>
			</div>
			<div class="span3 " style="width: 17%">
				<div class="well content_left">
					<h4>筛选器</h4>
					<dl class="nav nav-list nav_headerxs">
						<dt class="nav-header">搜索</dt>
						<dd class="nav_leftinput" style="display: block">
							<input id="inputName" name="inputName"  value="${inputName}" placeholder="p_url" type="text" style="width: 94%">
							<img src="${path}/crawlManage/resources/img/new_version/search1.png"
								onclick="searchInput('${detail.id}','${task_instance_id}','${detail.task_id}')" />
						</dd>
					</dl>
					<dl class="nav nav-list nav_headerxs" id="select1">
						<dt class="nav-header ">规则对比列表</dt>
						<c:if test="${not empty ruleDetails}">
							<c:forEach var="rule" items="${ruleDetails}" varStatus="ts">
									<dd><a href="#" onclick="contrast('${task_instance_id}','${detail.id}','${rule.id }')">${rule.name}</a></dd>
							</c:forEach>
						</c:if>
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
								<th>url地址</th>
								<th>子url地址</th>
								<th>处理</th>
							</tr>
						</thead>

						<tbody id="tbody">
							<c:if test="${not empty rules}">
								<c:forEach var="rule" items="${rules}" varStatus="ts">
									<tr>
										<td>${rule.p_url}</td>
										<td>${rule.c_url}</td>
										<td><a href="#" onclick="query('${task_instance_id}','${detail.id }','${rule.p_url }')">查询子url</a></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</form>
				<div>
			  	 <nav align="center">
			  		<ul class="pager">
					    <li id="lfy" class="${lable }">
					      <a href="#" aria-label="Previous "  onclick="lfy('${pager}','${detail.id}','${task_instance_id}','${detail.task_id}')"><span aria-hidden="true">&larr;上一页</span></a>
					    </li>					   	
					    <li id="nfy" class="${nable }">
					     <a  href="#" aria-label="Next" style="margin-left:  20px" onclick="nfy('${pager}','${detail.id}','${task_instance_id}','${detail.task_id}')"><span aria-hidden="false">下一页&rarr;</span> </a>
					    </li>
					    <a href="#" class="kkpager_queren" onclick="hunterPageContrlNextJump('${pager}','${detail.id}','${task_instance_id}','${detail.task_id}')">转到</a>
				    	 <span class="goPageBox" >&nbsp;
							<span id="kkpager_gopage_wrap">
								<input type="text" class="kkpager_btn_go_input" value="" id="num" style="height:22px">
							</span>
								页
						 </span>					    
					     <label style="margin-left: 20px" >当前页:<span id="dq">${pager }</span></label>
					     <label  style="margin-left: 20px">总页数:<span id="zys">${allpage }</span></label>
					  </ul>
					</nav>
			  	</div>
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

