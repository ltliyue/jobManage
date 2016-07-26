<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fs" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<script type="text/javascript" src="<%=path %>/resources/js/formats.js"></script>
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
	 hivec('s');
	 ruleselect();
});
function ruleselect(){
	var url = "<%=path%>/datasource/listrule?task_id=${taskid}";
	$.post(url,function(data){
		json = eval('('+data+')');
		var op = "";
		$.each(json,function(i) {
			var n = pinyin.getCamelChars(json[i].name);
			if(n.indexOf("（")>-1){
				n = n.substring(0,n.indexOf("（"));
			}
			if(n.indexOf("-")>-1){
				n = n.substring(0,n.indexOf("-"));
			}
			if(n.length>8){
				n = n.substring(0,8);
			}
			if(i==0){
				$("#bm").html('${bm}'+n);
			}
			
			op += "<option value='"+json[i].id+";"+n+"'>"+json[i].name+"</option>";
		});
	$("#ruleselect").html(op);
	
	});
}
function rulechange(){
	var ss1 = $("#ruleselect").val();
	var ss = ss1.split(";");
	$("#bm").html('${bm}'+ss[1]+"_"+$("#cname").val());
}
function submit(){
	var status = demo.check(false);
	if(status==true){
		var ss1 = $("#ruleselect").val();
		var ss = ss1.split(";");
		//如果通过，执行自己的方法
		var url = "<%=path%>/datasource/addtable?name="+$("#bm").html()+"&id=${taskid}&ruleid="+ss[0];
		$.post(url,function(data){
			if(data==0){
				alert("增加成功!");
				history.go(0);
			}else{
				alert("增加失败!");
			}
		});
	}
}
function change(){
	var ss1 = $("#ruleselect").val();
	var ss = ss1.split(";");
	$("#bm").html('${bm}'+ss[1]+"_"+$("#cname").val());
}
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
	hivec('s');
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
		$("#ruleh").val(json.ruleid);
		$("#wbiao").text("where cf['task_id'] = ${taskid} and key >='"+json.ruleid+"_' and key <='"+json.ruleid+"~'");
		$("#qcheck").html(json.check);
		$("#obiao").val(json.bm);
		$("#ruleid").val(json.ruleid);
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
function hivec(v){
	if(v=='s'){
		$("#s").attr("disabled","disabled");
		var url = "<%=path%>/datasource/hiveshows?bm="+$("#bmoc").val()+"&type=extract";
		$.post(url,function(data){
			json = eval('(' + data + ')');
			var y = json.ybiao;
			var p = json.pbiao;
			var b1 = "hive映射表：";
			var b2 = "hdfs表：";
			
			if(y==null){
				y = "为空>><a href='#' onclick='addy()'>添加</a>";
				$("#shbiao").html(b1+y);
			}else{
				var arr = y.split(";");
				var op = "";
				for(var i=0;i<arr.length;i++)
				{
					op +="<option>"+arr[i]+"</option>";
				}
				$("#hbiao").html(op);
				$("#hbiao").selectpicker('refresh');
				$("#ybiao").attr("disabled","disabled");
				$("#y").attr("disabled","disabled");
				if(p==null){
					p="空>><a href='#' onclick='addp()'>添加</a>";
				}else{
					p="<textarea readonly class='form-control' id='hive_table' style='width: 95%' rows='3'>"+p+"</textarea><label><a href='#' onclick='clear1()'>清空</a></label>";
				}
				
				$("#shbiao").html(b1+y+"<br>"+b2+p);
			}
			$("#s").removeAttr("disabled");
			
		});
	}
	if(v=='y'){
		$("#y").attr("disabled","disabled");
		var url = "<%=path%>/datasource/hivehbase?id=${taskid}&hbtable="+$("#ybiao").val();
		$.post(url,function(data){
			json = eval('(' + data + ')');
			alert(json);
			$("#h").removeAttr("disabled");
			hivec('s');
		});
	}
	if(v=='h'){
		$("#h").attr("disabled","disabled");
		var url = "<%=path%>/datasource/hdfs?n="+$("#hbiao").val()+"&w="+$("#wbiao").val()+"&bm="+$("#bmoc").val();
		$.post(url,function(data){
			json = eval('(' + data + ')');
			alert(json);
			$("#h").removeAttr("disabled");
			hivec('s');
		});
	}
	if(v=='d'){
		var s = $("#dbiao").val();
		if(s!=null&&s!=""){
			//如果通过，执行自己的方法
			$("#d").attr("disabled","disabled");
			var url = "<%=path%>/datasource/sqoop?n="+$("#dbiao").val()+"&o="+$("#obiao").val()+"&id=${taskid}&ruleid="+$("#ruleid").val();
			$.post(url,function(data){
				json = eval('(' + data + ')');
				$("#d").removeAttr("disabled");
				alert(json);
			});
		}else{
			alert("hdfs表不能为空!");
		}
		
	}
	if(v=='p'){
		var url = "<%=path%>/datasource/progress?id=${taskid}";
		$.post(url,function(data){
			json = eval('(' + data + ')');
			$("#change").attr("hidden","hidden");
			$("#change1").removeAttr("hidden");
			$("#jindu").html(json);
		});
	}
	if(v=='r'){
		$("#change1").attr("hidden","hidden");
		$("#change").removeAttr("hidden");
	}
	if(v=='f'){
		var url = "<%=path%>/datasource/progress?id=${taskid}";
		$.post(url,function(data){
			json = eval('(' + data + ')');
			$("#jindu").html(json);
		});
	}
}
	function addy(){
		$("#ybiao").removeAttr('disabled');
		$("#y").removeAttr('disabled');
	}
	function addp(){
		$("#wbiao").removeAttr('disabled');
		$("#h").removeAttr('disabled');
	}
	function cx(){
		var r = $("input[name='radio_list']:checked").val();
		if(r!=null&&r!=''){
			$("#wbiao").text("where  key >='"+$("#ruleh").val()+"_"+r+"_${taskid}_' and key <='"+$("#ruleh").val()+"_"+r+"_${taskid}~'");;
		}
		$("#close").click();
	}
	function cqcheck(){
		 if($("input[name='ischeck']:checked").length>0){
		  		if(confirm("确定要去重？")){
		  			var where = '';
		  			$("input[name='ischeck']:checked").each(function(val){	
		  				where +=$(this).val()+";";
		  			});
		  			var url = "<%=path%>/datasource/dis?bm="+$("#obm").val()+"&where="+where;
		  			$.post(url,function(data){
		  				alert("去重了 "+data+" 条数据!");
		  			//	window.location.href="${path}/crawlManage/parseRule/setParseRule"; 
		  			},"text");
		  		}
	  		}else{
	  	  			alert("请选择一项进行去重！");
	  	  		}
	}
	function plup(){
		if(confirm("确定要修改？")){
			var where = '';
			$("#zdydiv1 input").each(function(){
				var tjid = $(this).attr("id").substring(4);
				var other = $(this).val();
				if(other!=null&&other!=""){
					if(tjid!=null&&tjid!=""){
						where+=tjid+"="+other+";";
					}
				}
			});
			var url = "<%=path%>/datasource/plup?bm="+$("#obm").val()+"&col="+$("#tjsel2").val()+"&set="+$("#xghz").val();
			
			var datas = {"where":where};
  			$.post(url,datas,function(data){
  				alert("修改了 "+data+" 条数据!");
  			//	window.location.href="${path}/crawlManage/parseRule/setParseRule"; 
  			},"text");
		};
	}
	function pldel(){
		if(confirm("确定要删除？")){
			var where = '';
			$("#zdydiv2 input").each(function(){
				var tjid = $(this).attr("id").substring(5);
				var other = $(this).val();
				if(other!=null&&other!=""){
					if(tjid!=null&&tjid!=""){
						where+=tjid+"="+other+";";
					}
				}
			});
			var url = "<%=path%>/datasource/pldel?bm="+$("#obm").val();
			var datas = {"where":where};
			if(where==''){
				if(confirm("确定要全部删除？")){
					$.post(url,datas,function(data){
		  				alert("删除了 "+data+" 条数据!");
		  			//	window.location.href="${path}/crawlManage/parseRule/setParseRule"; 
		  			},"text");
				}
			}else{
				$.post(url,datas,function(data){
	  				alert("删除了 "+data+" 条数据!");
	  			//	window.location.href="${path}/crawlManage/parseRule/setParseRule"; 
	  			},"text");
			}
  			
		};
	}
	function excelexport(){
		if(confirm("确定要导出？")){
			var url = "<%=path%>/datasource/export?id=${taskid}&bm="+$("#obm").val();
			$("#exportF").attr("action",url);
			var where = $("#where").val();
			$("#we").attr("value",where);
			$("#exportF").submit();
		};
	}
	function selectadd(){
		var sp = $("#tjsel").find("option:selected").text();
		if($("#zdydiv label").length==0){
			$("#zdydiv").append("<label>"+sp+":<input class='form-control' id='"+$("#tjsel").val()+"'  type='text' value='' placeholder=''></label>");
		}else{
			var state = "0";
			$("#zdydiv label").each(function () {
	            if ($(this).text() == sp+":") {
	            	state = "1";
	                alert("已存在");
	                return;
	            }
	        });
			if(state!='1'){
            	$("#zdydiv").append("<label>"+sp+":<input class='form-control' id='"+$("#tjsel").val()+"'  type='text' value='' placeholder=''></label>");
			}
		}
	}
	function selectup(){
		var sp = $("#tjsel2_").find("option:selected").text();
		if($("#zdydiv1 label").length==0){
			$("#zdydiv1").append("<label>"+sp+"=<input class='form-control' id='_up_"+$("#tjsel2_").val()+"'  type='text' value='' placeholder=''></label>");
		}else{
			var state = "0";
			$("#zdydiv1 label").each(function () {
	            if ($(this).text() == sp+"=") {
	            	state = "1";
	                alert("已存在!");
	                return;
	            }
	        });
			if(state!='1'){
            	$("#zdydiv1").append("<label>"+sp+"=<input class='form-control' id='_up_"+$("#tjsel2_").val()+"'  type='text' value='' placeholder=''></label>");
			}
		}
	}
	function selectdel(){
		var sp = $("#tjsel3").find("option:selected").text();
		if($("#zdydiv2 label").length==0){
			$("#zdydiv2").append("<lable>"+sp+"=<input class='form-control' id='_del_"+$("#tjsel3").val()+"'  type='text' value='' placeholder=''></label><br> ");
		}else{
			var state = "0";
			$("#zdydiv2 label").each(function () {
	            if ($(this).text() == sp+"=") {
	            	state = "1";
	                alert("已存在!");
	                return;
	            }
	        });
			if(state!='1'){
            	$("#zdydiv2").append("<label>"+sp+"=<input class='form-control' id='_del_"+$("#tjsel3").val()+"'  type='text' value='' placeholder=''></label><br>");
			}
		}
	}
	function zdysql(){
		var url = "<%=path%>/datasource/zdysql";
		var datas = {"sql":$("#sqlid").val()};
		$.post(url,datas,function(data){
			alert("执行了"+data+"条结果");
		});
	}
	function cxbzd(){
		var url = "<%=path%>/datasource/cxbzd?bm="+$("#bmzd").val();
		$.post(url,function(data){
			json = eval('('+data+')');
			$("#sqlid").text(json);
		});
	}
	function seleinstance(){
		var url = "<%=path%>/datasource/cxinstance?taskid=${taskid}";
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
	function clear1(){
		var table = $("#hive_table").html();
		var url = "<%=path%>/datasource/drophive?tables="+table.replace("\n","");
		$.post(url,function(data){
			alert(data);
			hivec('s');
		});
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
          		<h2>主数据管理</h2>
<dl>
				   <dt style="line-height: 27px">已选表：</dt>
				<dd class="select-no"></dd>
			    </dl>
<div  class="renwu_caozuo" style="margin-right:50px">
<a href="#" class='renwu_select' data-toggle="modal"    data-target="#modifyInstance">创建表
</a>
<a href="#" class='renwu_select' data-toggle="modal" id="ddata" hidden="hidden"  data-target="#modifyInstance1">oracle导入
</a></div>
          
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
	<input type="hidden" id="ruleid">
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

      <!-- Modal 导入数据-->
<div class=" modal hide fade" style="width: 650px" id="modifyInstance1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel1">导入数据</h4>
      </div>
      <div id="change" class="modal-body">
      <div class="modal-header">
			<div class="form-group">
				<label for="exampleInputEmail1">1.查看hive表:</label>
				<div><span id="shbiao"></span></div>
				
			</div>
      </div>
      <div class="modal-header" hidden="hidden">
			<div class="form-group" >
				<label for="exampleInputEmail1">2.生成hive映射表:</label>
				<span><input type="text" id="ybiao" value="global_extract_content" placeholder="请输入要映射的hbase表名" disabled="disabled"></span>
				<button  class="btn btn-default btn-sm"  onclick="hivec('y')" id="y" disabled="disabled">点击映射</button >
			</div>
      </div>
      <div class="modal-header" >
			<div class="form-group">
			<input type="hidden" id="ruleh">
				<label for="exampleInputEmail1">2.新增hdfs表:</label>
				<div>hive映射表：<select id="hbiao" class="selectpicker" data-style="btn-info"></select></div>
				<div>请输入sql语句的where条件：<label style="width: 600px" >（常用条件：where cf['task_id']=113 and unix_timestamp(substr(cf['extract_time'],0,8),'yyyyMMdd')><br>unix_timestamp('20160101','yyyyMMdd')；通用字段为：cf['extract_time']（抽取时间）、cf['instance_id']（实例id）、cf['task_id']（任务id））</label>
				<a href="#" data-toggle="modal"    data-target="#modifyInstance2" onclick="seleinstance()">选择实例ID</a>
				<textarea style="width: 95%" rows='3' id="wbiao"  placeholder="">where cf['task_id'] = ${taskid } and cf['page_extract_rule_id'] = </textarea></div>
				<button class="btn btn-small" onclick="hivec('h')"  id="h">点击生成</button >
			</div>
      </div>
      <div class="modal-header">
			<div class="form-group">
				<label for="exampleInputEmail1">3.导入oralce表:</label>
				<span><input type="text" id="dbiao" value="" placeholder="请输入hdfs表名" datatype="s1-2" nullmsg="请输入hdfs表！" errormsg="昵称至少4个字符,最多18个字符！" ><input type="text" id="obiao" disabled="disabled" value="" placeholder="请输入oracle表名" ></span>
				<button class="btn btn-small" onclick="hivec('d')" id="d" style="margin-bottom: 4px">点击导入</button >
				<br><a href="#" onclick="hivec('p')">>>查看导入进度</a>
			</div>
      </div>
      </div>
      <div id="change1" hidden="hidden">
	      <div class="modal-header">
	      <button  class="btn btn-default btn-sm" onclick="hivec('r')" id="d" >返回</button >&nbsp;&nbsp;&nbsp;&nbsp;
	      <button  class="btn btn-success btn-sm" onclick="hivec('f')" id="d" >刷新</button >
	      </div>
      		<div class="modal-header">
      		 <textarea style="width: 95%" rows="20" id="jindu"></textarea>
      		 </div>
      </div>
    </div>
  </div>
</div>
      <!-- Modal 筛选数据-->
<div class="modal hide fade" id="modifyInstance2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
        <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="cx()">提交</button>
      </div>		
 
</div>
</div>
  <!-- Modal 数据去重-->
<div class="modal hide fade" id="modifyInstance3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel3">数据去重</h4>
      </div>
      <form class="form-inline" action="" method="post">
    	
      <div class="modal-header" >
      <span>请选择要去重的项:</span><br>
			<div class="form-group" id="qcheck">
			</div>
      </div>
      </form>
	<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="cqcheck()">提交</button>
      </div>		
  </div>
</div>
</div>
 <!-- Modal 批量修改-->
<div class="modal hide fade" id="modifyInstance4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel4">批量修改</h4>
      </div>
      <form class="form-inline" action="" method="post">
    	
      <div class="modal-body" >
      <div class="form-group" >
				update <span id="upsele"></span> set <br><select class="selectpicker" data-style="" data-hide-disabled="true"  id="tjsel2">
				</select>=<input class="form-control" id="xghz"  type="text" value="" placeholder="请输入要修改的值">
				
			</div>
			
			<div class="form-group" id="">
				<br>
				  自定义增加修改条件： <select class="selectpicker" data-style="" data-hide-disabled="true"  id="tjsel2_">
				</select>
				<a href="javascript:void(0)" onclick="selectup()"><i class="icon-plus"></i>
				</a>
			</div><br>
		
			<div class="form-group" id="zdydiv1">
				
			</div>
      </div>
      </form>
	<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="plup()">提交</button>
      </div>		
  </div>
</div>
</div>
<!-- Modal 批量删除-->
<div class="modal hide fade" id="modifyInstance5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-body">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel5">批量删除</h4>
      </div>
      <div  class="modal-header">
    	
     <div class="form-group" >
				delete from <span id="delsele"></span> where 
			</div>
			
			<div class="form-group" id="">
				<br>
				  自定义增加删除条件： <select class="selectpicker" data-style="" data-hide-disabled="true"  id="tjsel3">
				</select>
				<a href="javascript:void(0)" onclick="selectdel()"><i class="icon-plus"></i>
				</a>
			</div><br>
			<div class="form-group" id="zdydiv2">
				
	</div>
      </div>
      </div>
	<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="pldel()">提交</button>
      </div>		
  
</div>
</div>

<!-- Modal sql窗口-->
<div class="modal hide fade" id="modifyInstance6" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel5">SQL窗口</h4>
      </div>
    	
      <div class="modal-header">
      <input type="text" class="form-control" id="bmzd" style="width: 50%;float: left;" placeholder="表名">&nbsp;&nbsp;<button class="btn btn-success btn-sm" onclick="cxbzd()">查询表字段</button><span id="ablecolumn"></span>
      <hr>
      		 <textarea style="width: 95%" rows="10" id="sqlid" name="sql" placeholder="可以执行update、alter语句，不能执行select"></textarea>
      		 </div>
	<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="zdysql()">提交</button>
      </div>		
  </div>
</div>
</div>
</body>
</html>
