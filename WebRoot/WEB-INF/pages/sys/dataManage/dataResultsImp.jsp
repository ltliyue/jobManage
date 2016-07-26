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
	 <!-- Bootstrap core jss -->
	   <script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
     <script src="<%=path%>/resources/js/new_version/script.js"></script>
     <script src="<%=path%>/resources/js/new_version/site.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/js/formats.js"></script>
  </head>
	<script type="text/javascript">
	$(document).mouseup(function(){
		$("div.popover").css({display:"none"});
		
		});

	$(document).ready(function(){
		
	
		  $("#example").popover({placement:'bottom'});
		 
		});
	function see(id){
		window.location.href="<%=path%>/datasource/detailimp?id="+id; 
	};
	function complete(taskid){
		seleinstance(taskid);
		
		
	};
	function seleinstance(taskid){
		var url = "<%=path%>/datasource/cxinstance?taskid="+taskid;
		$("#tbody2").html("");
		$.post(url,function(data){
			json = eval('('+data+')');
			$.each(json,function(i) {
			var status = "";
				if(json[i].status==0){
					status = "未采集";
				}else if(json[i].status==1){
					status = "采集中";
				}else if(json[i].status==2){
					status = "采集终止";
				}else if(json[i].status==3){
					status = "已删除";
				}else if(json[i].status==9){
					status = "待配置";
				}
				$("#tbody2").append('<tr align="left" style="cursor: pointer;"><td><input type="radio" name="radio_list" value="'+json[i].instanceId+'"></td><td >'+json[i].instanceId+'</td><td >'+new Date(json[i].publishTime).Format("yyyy-MM-dd hh:mm:ss")+'</td><td >'+status+'</td></tr>');
			});
		});
	}
	function sjjf(){
		var r = $("input[name='radio_list']:checked").val();
		if(r!=null){
			var url = "<%=path%>/datasource/processLook?instanceId="+r;
			$.post(url,function(data){
				json = eval('('+data+')');
					if(json=="2"){
						if(confirm("已经交付，是否撤回？")){
							var url1 = "<%=path%>/datasource/processMon?instanceId="+r+"&s=0";
							$.post(url1,function(data){
								json1 = eval('('+data+')');
				  				alert(json1);
				  			},"text");
						}
					}else if(json=="0"){
						if(confirm("任务是否交付？")){
							var url2 = "<%=path%>/datasource/processMon?instanceId="+r+"&s=3";
							$.post(url2,function(data){
								json2 = eval('('+data+')');
				  				alert(json2);
				  			},"text");
						}
					}else{
						alert(json);
					}
				},"text");
		}else{
			alert("请选择实例ID");
		}
		
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
              <form action="<%=path%>/dataclear/dataimp" id="form1">
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
								<td class="content_rwgl_pzurl"  style="width:30%" id='url'>${task.url }</td>
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
								<td><a href="#" onclick="see(${task.taskId })" class="content_xuqiumcan" >查看</a>
								<a href="#" onclick="complete(${task.taskId })" class="content_xuqiumcan" data-toggle="modal"    data-target="#modifyInstance7">交付</a>
								</td>
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
    <!-- Modal sql窗口-->
<div class="modal hide fade" id="modifyInstance7" role="dialog" aria-labelledby="myModalLabel">
 <div class="modal-dialog" role="document">
    <div  class="modal-body">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel2">实例ID列表</h4>
      </div>
      <form action="" method="post">
      <div class="" style="padding-left:0;margin:0 auto">
          <div class="table-responsive">
            <table class="table  table-striped">
              <thead>
                <tr>
               	  <th></th>
                  <th>实例ID</th>
                  <th>操作时间</th>
                  <th>状态</th>
                </tr>
              </thead>
				<tbody id="tbody2">
				</tbody>
				
            </table>
            </div>
            </div>
      </form>
       </div>
	<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="close7">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="sjjf()">交付</button>
      </div>		
 </div>
</div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    </body>
</html>
