<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fs" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<link href="<%=path %>/resources/css/dashboard.css" rel="stylesheet">
<link href="<%=path %>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="<%=path %>/resources/css/bootstrap-select.css" rel="stylesheet">
<!-- Bootstrap core JavaScript
    ================================================== -->
   <script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    
     <script src="<%=path%>/resources/js/new_version/site.js"></script>
	<script src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.js"></script>
	<script src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.zh-CN.js"></script>	
	<script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap-select.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery-ui-min-1.9.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/Chinese-characters-to-Pinyin/py.js"></script>
	 <script src="<%=path%>/resources/js/new_version/script.js"></script>
</head>
<script type="text/javascript">
var demo;
$(document).ready(function(){
	$("#modifyInstance6").draggable({   
	    handle: ".modal-header",   
	    cursor: 'move',   
	    refreshPositions: false  
	}); 
	$('#modifyInstance6').modal({backdrop: 'static',show:false, keyboard: false});
	 demo = $("#form").Validform({
			tiptype:3,
			showAllError:true
		});
	 $('.form_date').datetimepicker({
	        language: 'zh-CN',
	        weekStart: 1,
	        todayBtn: 1,
	  autoclose: 1,
	  todayHighlight: 1,
	  startView: 2,
	  minView: 2,
	  forceParse: 0
	    });
	 if($("#select1 dd:eq(0)").length>0){
		 $("#select1 dd:eq(0) a").click();
		 $("#ddata").removeAttr("hidden");
		 $("#ddata1").removeAttr("hidden");
		 $("#ddata2").removeAttr("hidden");
		 $("#ddata3").removeAttr("hidden");
		 $("#ddata4").removeAttr("hidden");
		 $("#ddata5").removeAttr("hidden");
		 $("#ddata6").removeAttr("hidden");
	 }else{
		 $("#nfy").attr("class","disabled");
	 }
});
function listd(obj){
	$("#where").val("");
	var bmm;
	if(window.navigator.userAgent.toLowerCase().indexOf("firefox")!=-1)
	{
		bmm =obj.textContent;
	}
	else
	{
		bmm =obj.innerText;
	}
	$("#bmoc").val(bmm);
	var url = "<%=path%>/datasource/listd?id=${taskid}&bm="+bmm+"&yeshu=1"+"&where=";
	$.post(url,function(data){
		json = eval('('+data+')');
		$("#th").html(json.th);
		$("#td").html(json.td);
		$("#dq").html(json.yeshu);
		$("#zys").html(json.zys);
		$("#obm").val(json.bm);
		$("#upsele").html(json.bm);
		$("#delsele").html(json.bm);
		$("#tjsel").html(json.option);
		$("#tjsel").selectpicker('refresh');
		$("#tjsel2").html(json.option);
		$("#tjsel2").selectpicker('refresh');
		$("#tjsel2_").html(json.option);
		$("#tjsel2_").append('<option value="task_url">任务URL</option>');
		$("#tjsel2_").selectpicker('refresh');
		
		$("#tjsel3").html(json.option);
		$("#tjsel3").append('<option value="task_url">任务URL</option>');
		$("#tjsel3").selectpicker('refresh');
		$("#wbiao").text("where cf['task_id'] = ${taskid} and cf['page_extract_rule_id'] = '"+json.ruleid+"'");
		$("#qcheck").html(json.check);
		$("#obiao").val(json.bm);
		$("#lfy").attr("class","disabled");
		if(parseInt(json.zys)<11){
			$("#nfy").attr("class","disabled");
		}else{
			$("#nfy").removeAttr("class");
		}
	});
}
function lfy(){
	if($("#lfy").attr("class")!="disabled"){
		var url = "<%=path%>/datasource/listd?id=${taskid}&bm="+$("#obm").val()+"&yeshu="+(parseInt($("#dq").html())-1);
		var where = $("#where").val();
		var datas = {"where":where};
		$.post(url,datas,function(data){
			json = eval('('+data+')');
			$("#th").html(json.th);
			$("#td").html(json.td);
			$("#dq").html(json.yeshu);
			$("#zys").html(json.zys);
			$("#obm").val(json.bm);
			
			if(parseInt(json.zys)>parseInt($("#dq").html())*10){
				$("#nfy").removeAttr("class");
			}
			if(parseInt($("#dq").html())==1){
				$("#lfy").attr("class","disabled");
			}
		});
	}
}
function nfy(){
	if($("#nfy").attr("class")!="disabled"&&$("#dq").html()!=''){
		
		var url = "<%=path%>/datasource/listd?id=${taskid}&bm="+$("#obm").val()+"&yeshu="+(parseInt($("#dq").html())+1);
		var where = $("#where").val();
		var datas = {"where":where};
		$.post(url,datas,function(data){
			json = eval('('+data+')');
			$("#th").html(json.th);
			$("#td").html(json.td);
			$("#dq").html(json.yeshu);
			$("#zys").html(json.zys);
			$("#obm").val(json.bm);
			$("#lfy").removeAttr("class");
			if(parseInt(json.zys)<=parseInt($("#dq").html())*10){
				$("#nfy").attr("class","disabled");
			}
		});
	}
}
</script>
 <script type="text/javascript">
        	$(function () {
  $('[data-toggle="tooltip"]').tooltip();
  var width=$(window).width()*1.8;
  $('.content_rwgl').css('cssText','max-width:'+width+'px !important;width:'+width+'px !important');
})
</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
 <div  class="content_waikuang">
          <div class="container-fluid search_select select-result" style="padding-left:0;">
          		<h2>数据查询</h2>
<dl>
				   <dt style="line-height: 27px">已选表：</dt>
				<dd class="select-no"></dd>
			    </dl>
          
          </div>
<div class="row" style="clear:both">
				<div class="span3 " style=" width:17%">
					<div class="well content_left">
                    	<h4></h4>
				<dl class="nav nav-list nav_headerxs" id="select1">			
			  		<dt class="nav-header active" id="nav-header">表名</dt>
              		<c:forEach var="data" items="${list}" varStatus="ts">
              		<c:if test="${ts.index == 0}">
              		  <dd class="active">
			          <a href="#" onclick="listd(this)">${data}</a>
			        </dd>
              		</c:if>
              		<c:if test="${ts.index > 0}">
			       <dd >
			          <a href="#" onclick="listd(this)">${data}</a>
			        </dd>
			        </c:if>
			        </c:forEach>
					</dl>		
					</div>
					</div>
					<div class="span9" style="width:78%;height:500px;overflow:scroll;">
					
   
  <input type="hidden" id="obm">
  <input type="hidden" id="where">

    <table class="table  table-striped content_rwgl">
      <thead>
        <tr id="th" class="content_rwglmc">
        </tr>
      </thead>
      <tbody id="td">
       
      </tbody>
    </table>
    <form action="" id="exportF" method="post">   
 <input type="hidden" id="we" name="we">
 <input type="hidden" id="bmoc">
</form> 
  	 <nav align="center">
  		<ul class="pager">
  		
    <li id="lfy" class="disabled">
      <a href="#" aria-label="Previous "  onclick="lfy()"><span aria-hidden="true">&larr;上一页</span></a>
    </li>
   	
    <li id="nfy" class="disabled">
     <a  href="#" aria-label="Next" style="margin-left:  20px" onclick="nfy()"><span aria-hidden="false">下一页&rarr;</span> </a>
    </li>
     <span style="margin-left: 20px" >当前页:<span id="dq"></span></span>
     <span  style="margin-left: 20px">总数量:<span id="zys"></span></span>
  </ul>
</nav>
  </div>
  </div>
  </div>
      <!-- Modal -->
<div class="modal hide fade" id="modifyInstance" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">增加表名</h4>
      </div>
      <form id="form" method="POST">
    
      <input type="hidden" name="flag" id="flag">
       <div class="modal-body">
			<div class="form-group">
			  <label class="content_zjnrcqlabel">选择一种规则</label>
      <select class="form-control content_zjnrcqselect " id="ruleselect" onchange="rulechange()" datatype="*" nullmsg="请选择规则！" errormsg="">
	</select>
			</div>
			</div>
      <div class="modal-body">
			<div class="form-group">
			
				<label for="exampleInputEmail1">表名称:</label> <span id="bm" >${bm }</span><input type="text"
					class="form-control" id="cname" value="" oninput="change()" datatype="/^[a-zA-Z0-9_]{1,20}$/" nullmsg="请输入名称！" errormsg="表名称英文或数字！" placeholder="表名">
					
			</div>
      </div>
      </form>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="submit()">提交</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>
