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
     <link href="<%=path%>/resources/css/new_version/bootstrap.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="<%=path%>/resources/css/new_version/site.css" rel="stylesheet">
	<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
	 <!-- Bootstrap core jss -->
	   <script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
     <script src="<%=path%>/resources/js/new_version/script.js"></script>
     <script src="<%=path%>/resources/js/new_version/site.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
  </head>
	<script type="text/javascript">
	$(document).mouseup(function(){
		$("div.popover").css({display:"none"});
		
		});

	$(document).ready(function(){
		
		$("tr").bind("click",function(){
				var id = $(this).children('td').eq(0).html();
				window.location.href="<%=path%>/datasource/detail?id="+id; 
			});
		  $("#example").popover({placement:'bottom'});
		  
		  demo = $("#form").Validform({
			tiptype:3,
			showAllError:true
		});
		$("#button").click(function(){
			var status = demo.check(false);
			if(status==true){
				//如果通过，执行自己的方法
				add();
			}
		});
 	 });
	function add(){
		var url = "<%=path%>/datasource/adddata";
		var data1 = $("#form").serialize();
		$.post(url,data1,function(data){
			if(data=='1'){
				alert('增加成功!');
				history.go(0);
			}else{
				alert('增加失败!');
			}
			
		});
	}
	function searchInput() {
		$("#hiddenName").val($("#inputName").val());
		$("#hiddenUrl").val($("#inputUrl").val());
		$("#form1").submit();
	}
	
	function searchSelect(type, val) {
		$("#hiddenName").val($("#inputName").val());
		if($("#hidden"+type).val()==val) {
			$("#hidden"+type).val("");
		}else {
			$("#hidden"+type).val(val);
		}
		$("#form1").submit();
	}
	</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
   <!--内容开始-->
    <div  class="content_waikuang">      
    <div class="container-fluid search_select select-result" style="padding-left:0">
<h2 class="content_title">${title }</h2>
</div>
          <div class="row" style="clear:both">
				<div class="span3 " style=" width:17%">
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
         <div class="span9" style="width:78%;">
            <table class="table  table-striped content_rwgl">
              <thead>
                <tr>
                  <th>任务ID</th>
                  <th>任务名称</th>
                  <th>URL</th>
                  <th>最后操作时间</th>
                  <th>状态</th>
                  <th>操作</th>
                </tr>
              </thead>
              <form action="<%=path%>/datatask/taskresult" id="form1">
              <input type="hidden" id="hiddenName" name="crawlerTask.taskName"
							value="${crawlerTask.taskName }">
						<input type="hidden"
							id="hiddenStatus" name="crawlerTask.status"
							value="${crawlerTask.status }">
						<input type="hidden"
							id="hiddenPeriod" name="crawlerTask.period"
							value="${crawlerTask.period }">
				<tbody id="tbody">
					<c:if test="${not empty tasks}">
						<c:forEach var="task" items="${tasks }" varStatus="ts">
							<tr align="left" style="cursor: pointer;">
								
								<td style="width:7%"  id="taskId">${task.taskId }</td>
								<td id='taskName'>${task.taskName } </td>
								<td class="content_rwgl_pzurl"  style="width:40%" id='url'>${task.url }</td>
								<td id='lastOperateTime'><fmt:formatDate value="${task.lastOperateTime }" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
								<td id='status'>
								<c:choose>
										<c:when test="${task.status == 0}">未采集</c:when>
										<c:when test="${task.status == 1}">采集中</c:when>
										<c:when test="${task.status == 2}">采集停止</c:when>
										<c:when test="${task.status == 3}">已删除</c:when>
										<c:when test="${task.status == 9}">待配置</c:when>
									</c:choose>
								</td>
								<td><a href="#" class="content_xuqiumcan">查看</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				
			</form>
            </table>
             <div  class="listtablebot">
				${page.pageStr }
				</div>
				</div>
          </div>
        </div>
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    </body>
</html>
