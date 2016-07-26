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

    <title>漏采分析——对比分析</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=path%>/resources/css/new_version/bootstrap.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/css/new_version/site.css" rel="stylesheet">
	<link href="<%=path %>/resources/css/treegridCSS/jquery.treegrid.css" rel="stylesheet">
	<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
	 <!-- Bootstrap core jss -->
	<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/script.js"></script>
    <script src="<%=path%>/resources/js/new_version/site.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.bootstrap3.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
  </head>
	<script type="text/javascript">
	var demo ;
 	$(document).ready(function(){
		demo = $("#form").Validform({
			tiptype:3,
			showAllError:true
		});
  	});
 	
 	$(document).ready(function(){
		$("#queryurl").bind('input propertychange', function() {
			if($("#queryurl").val()==null || $("#queryurl").val()==""){
				window.location.href="<%=path %>/urlrelation/showDifferUrls?instanceId="+
				$("#instanceId").val()+"&parseRuleId1="+$("#parseRuleId1").val()+"&parseRuleId2="+$("#parseRuleId2").val();
			}
		}); 
		
		//$("#slfDefPageNo").bind('input propertychange', function() {
		//	jumpToPage();
		//}); 
  	});
 	
 	function add0(m){return m<10?'0'+m:m }
 	function format(date)
 	{
 		//shijianchuo是整数，否则要parseInt转换
	 	var y = date.getFullYear();
	 	var m = date.getMonth()+1;
	 	var d = date.getDate();
	 	var h = date.getHours();
	 	var mm = date.getMinutes();
	 	var s = date.getSeconds();
	 	return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
 	}

 	
 	function buildTable(data){
 		//删除tbody中的所有的行，重新建立
		json = eval('('+data+')');
		$("#tbody").empty();
		for(var i=0;i<json.length;i++){
			var tr = document.createElement("tr");
			var td2 = document.createElement("td");
			var td3 = document.createElement("td");
			var td4 = document.createElement("td");
			var td5 = document.createElement("td");
			var td6 = document.createElement("td");
			var fmt = document.createElement("fmt:formatDate");
			
			td2.innerHTML = json[i].parseRuleId;
			td3.innerHTML = json[i].subUrl;
			td4.innerHTML = json[i].parentUrl;
			td5.innerHTML = json[i].depth;
			var date= new Date(json[i].publishTime);
			td6.innerHTML = format(date);
			
			tr.appendChild(td2);
			tr.appendChild(td3);
			tr.appendChild(td4);
			tr.appendChild(td5);
			tr.appendChild(td6);

			document.getElementById("tbody").appendChild(tr);
		}
 	}
 	
 	function prevPage(){
	 	if(parseInt($("#pageNo").html())>1){
	 		var url = "<%=path %>/urlrelation/getDifferUrlsByPageNo?instanceId="+
				$("#instanceId").val()+"&parseRuleId1="+$("#parseRuleId1").val()+
				"&pageNo="+(parseInt($("#pageNo").html())-1)+"&parseRuleId2="+$("#parseRuleId2").val();
	 		if($("#queryurl").val()!=null && $("#queryurl").val()!=""){
				url = "<%=path %>/urlrelation/serchDifferUrlsByPageNo?instanceId="+
					$("#instanceId").val()+"&parseRuleId="+$("#parseRuleId1").val()+
					"&url="+encodeURIComponent($("#queryurl").val())+
					"&pageNo="+(parseInt($("#pageNo").html())-1);
			}
			$.ajax({
				type : "POST",
				url : url,
				contentType: "application/json; charset=utf-8",
				dataType: "text",
				data : "",
				success : function(data) {
					buildTable(data);
					$("#pageNo").html(parseInt($("#pageNo").html())-1);
				}
			});
 		}else{
 			alert("已经是第一页!")
 		}
 	}
 	
	function jumpToPage(){
		
		if($("#slfDefPageNo").val()!='' && parseInt($("#slfDefPageNo").val())>0 && parseInt($("#slfDefPageNo").val())<parseInt($("#totalpage").html())){
			
			var url = "<%=path %>/urlrelation/getDifferUrlsByPageNo?instanceId="+
					$("#instanceId").val()+"&parseRuleId1="+$("#parseRuleId1").val()+
					"&pageNo="+(parseInt($("#slfDefPageNo").val()))+"&parseRuleId2="+$("#parseRuleId2").val();
			
			if($("#queryurl").val()!=null && $("#queryurl").val()!=""){
				url = "<%=path %>/urlrelation/serchDifferUrlsByPageNo?instanceId="+
					$("#instanceId").val()+"&parseRuleId="+$("#parseRuleId1").val()+
					"&url="+encodeURIComponent($("#queryurl").val())+
					"&pageNo="+(parseInt($("#slfDefPageNo").val())+1);
			}
			
			$.ajax({
				type : "POST",
				url : url,
				contentType: "application/json; charset=utf-8",
				dataType: "text",
				data : "",
				success : function(data) {
					buildTable(data);
					$("#pageNo").html(parseInt($("#slfDefPageNo").val()));
					$("#slfDefPageNo").val("");
				}
			});
		}else{
			alert("填写的页码不正确!");
		}
	}
	
	function searchMissingUrl(){
		if($("#queryurl").val()!=null && $("#queryurl").val()!=""){
				var url = "<%=path %>/urlrelation/searchDifferUrls?instanceId="+
					$("#instanceId").val()+"&parseRuleId1="+$("#parseRuleId1").val()+
					"&url="+encodeURIComponent($("#queryurl").val())+
					"&parseRuleId2="+$("#parseRuleId2").val();
				window.location.href = url;
		}
		else{
			window.location.href="<%=path %>/urlrelation/showDifferUrls?instanceId="+
			$("#instanceId").val()+"&parseRuleId1="+$("#parseRuleId1").val()+"&parseRuleId2="+$("#parseRuleId2").val();
		}
	}
	
	function nextPage(){
		if($("#pageNo").html()!='' && parseInt($("#pageNo").html())<parseInt($("#totalpage").html())){
			var url = "<%=path %>/urlrelation/getDifferUrlsByPageNo?instanceId="+
					$("#instanceId").val()+"&parseRuleId1="+$("#parseRuleId1").val()+
					"&pageNo="+(parseInt($("#pageNo").html())+1)+"&parseRuleId2="+$("#parseRuleId2").val();
			
			if($("#queryurl").val()!=null && $("#queryurl").val()!=""){
				url = "<%=path %>/urlrelation/serchDifferUrlsByPageNo?instanceId="+
					$("#instanceId").val()+"&parseRuleId="+$("#parseRuleId1").val()+
					"&url="+encodeURIComponent($("#queryurl").val())+
					"&pageNo="+(parseInt($("#pageNo").html())+1);
			}
			$.ajax({
				type : "POST",
				url : url,
				contentType: "application/json; charset=utf-8",
				dataType: "text",
				data : "",
				success : function(data) {
					buildTable(data);
					$("#pageNo").html(parseInt($("#pageNo").html())+1);
				}
			});
		}else{
			alert("已经是最后一页!");
		}
	}
	
	</script>
  <body style="padding-top:0" >
  
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--  <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> -->   
	
	<input type="hidden" id="businessType" name="businessType" value="${businessType}">
	<input type="hidden" id="instanceId" name="instanceId" value="${instanceId}">
	<input type="hidden" id="parseRuleId" name="parseRuleId" value="${parseRuleId}">
	<input type="hidden" id="parentUrl" name="parentUrl" value="${parentUrl}">
	<input type="hidden" id="parseRuleId1" name="parseRuleId1" value="${parseRuleId1}">
	<input type="hidden" id="parseRuleId2" name="parseRuleId2" value="${parseRuleId2}">
	<div  class="content_waikuang">
	<div class="container-fluid search_select " style="padding-left:0">
   		<h2 class="content_title">对比漏采分析</h2>   
   		<dl class="nav nav-list nav_headerxs">
   			<span>${parseRuleName1}&rarr;${parseRuleName2}</span>
			<br/><br/>
		</dl>
   	</div>
   	
	<div class="row" style="clear:both; margin-left:0">
		<div class="span9" style="width:100%; margin-left:0">
		<dl class="nav nav-list nav_headerxs">
			<dd class="nav_leftinput" style="display: block">		
				<input id="queryurl" name="queryurl"  value="${queryurl}" placeholder="输入某个url，查询其是否在规则对接时被漏采" type="text" style="width: 94%">
				<img src="${path}/crawlManage/resources/img/new_version/search1.png"
								onclick="searchMissingUrl()" />
			</dd>
		</dl>
         <table class="table  table-striped content_rwgl"
						style="width: 100%;">
						<thead>
							<tr>
								<th>规则id</th>
								<th>漏采url</th>
								<th>父url</th>
								<th>深度</th>
								<th>发布时间</th>
							</tr>
						</thead>

						<tbody id="tbody">
							<c:if test="${not empty histories}">
								<c:forEach var="history" items="${histories}" varStatus="ts">
									<tr>
										<td>${history.parseRuleId}</td>
										<td>${history.subUrl }</td>
										<td>${history.parentUrl }</td>
										<td>${history.depth }</td>
										<td><fmt:formatDate value="${history.publishTime}"
										pattern="yyyy-MM-dd hh:mm:ss" /></td>
									</tr>
											
								</c:forEach>
							</c:if>
						</tbody>
					</table>
		</div>
	</div>
		
	<div>
		<nav>
	  	<ul class="pager">
			<li id="lfy">
				<a href="#" aria-label="Previous "  onclick="prevPage()"><span aria-hidden="true">&larr;上一页</span></a>
			</li>
			   	
			<li id="nfy" >
			    <a  href="#" aria-label="Next" style="margin-left:  20px" onclick="nextPage()"><span aria-hidden="false">下一页&rarr;</span> </a>
				转到<input type="text" class="kkpager_btn_go_input" value="" id="slfDefPageNo" style="height:22px">
					页   <a href="#" class="kkpager_queren" onclick="jumpToPage()">GO</a>
			</li>
			<label style="margin-left: 20px">当前页:<span id="pageNo">${curpage}</span>
				 &nbsp;  总页数:<span id="totalpage">${totalpage}</span>
				 &nbsp;  总条量:<span id="totalcount">${totalcount}</span></label>
		</ul>
		</nav>
	</div>
</div>
</body>
</html>
