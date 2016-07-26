<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		all();
		querySiteType(0);
		
	  });
	 function querySiteType(pid){
		 var url = "<%=path %>/datasiteitem/querysitetype?pid="+pid;
		 $.post(url,function(data){
			 json=eval('('+data+')');
			 $("#siteType1").empty();
			 //$("#siteType1").append("<option value='0'>"+'---请选择---'+"</option>");
			 $.each(json,function(i){
				 	$("#siteType1").append("<option value='"+json[i].ID+"'>"+json[i].NAME+"</option>");
			 });
		 });
	 }
	 function siteChange(){
		 $("#siteType2").empty();
		 var pid = $("#siteType1").val();
		 var url = "<%=path %>/datasiteitem/querysitetype?pid="+pid;
		 $.post(url,function(data){
			 json=eval('('+data+')');
			 $.each(json,function(i){
				 	$("#siteType2").append("<option value='"+json[i].ID+"'>"+json[i].NAME+"</option>");
			 });
		 });
	 }
	 
	 function all(){
		 var name = $("#inputName").val();
		 var type = $("#inputType").val();
		 var url = "<%=path %>/datasiteitem/allsiteitem?pages=1&name="+name+"&type="+type;
			/* $.post(url,function(data){
				json = eval('('+data+')');
				 $("#tbody").html(json[0].sb);
				 $("#zys").html(json[0].zys);
				 $("#dq").html(json[0].pages);
				 $('.tree').treegrid();
				 if(json[0].zys>10){
					 $("#nfy").removeAttr("class");
				 }
			}); */
			$.ajax({
				 type : "POST",
				 url : url,
				 contentType: "application/json; charset=utf-8",
				 dataType: "text",
				 data : "",
				 success : function(data) {
				  	json = eval('('+data+')');
					 $("#tbody").html(json[0].sb);
					 $("#zys").html(json[0].zys);
					 $("#dq").html(json[0].pages);
					 $('.tree').treegrid();
					 if(json[0].zys>10){
						 $("#nfy").removeAttr("class");
					 }
				 }
				});
	 };
	 
	 function searchInput(){
		 var name = $("#inputName").val();
		 var type = $("#inputType").val();
		 var url = "<%=path %>/datasiteitem/allsiteitem?pages=1&name="+name+"&type="+type;
		 $.ajax({
			 type : "POST",
			 url : url,
			 contentType: "application/json; charset=utf-8",
			 dataType: "text",
			 data : "",
			 success : function(data) {
				json = eval('('+data+')');
				 $("#tbody").html(json[0].sb);
				 $("#zys").html(json[0].zys);
				 $("#dq").html(json[0].pages);
				 $('.tree').treegrid();
				 if(json[0].zys>10){
					 $("#nfy").removeAttr("class");
				 }
				 if(parseInt(json[0].zys)<=parseInt($("#dq").html())*10){
						$("#nfy").attr("class","disabled");
					}
			 }
			});
	 }
	 
	 function lfy(){
		 if($("#lfy").attr("class")!="disabled"){
			 var name = $("#inputName").val();
			 var type = $("#inputType").val();
			 var url = "<%=path %>/datasiteitem/allsiteitem?pages="+(parseInt($("#dq").html())-1)+"&name="+name+"&type="+type;
			 $.ajax({
				 type : "POST",
				 url : url,
				 contentType: "application/json; charset=utf-8",
				 dataType: "text",
				 data : "",
				 success : function(data) {
					json = eval('('+data+')');
					 $("#tbody").html(json[0].sb);
					 $("#zys").html(json[0].zys);
					 $("#dq").html(json[0].pages);
					 $('.tree').treegrid();
				 if(parseInt(json[0].zys)>parseInt($("#dq").html())*10){
						$("#nfy").removeAttr("class");
					}
				 if(parseInt($("#dq").html())==1){
						$("#lfy").attr("class","disabled");
					}
				 }
				});
		 }
	 }
	 function nfy(){
		 if($("#nfy").attr("class")!="disabled"&&$("#dq").html()!=''){
			 var name = $("#inputName").val();
			 var type = $("#inputType").val();
			 var url = "<%=path %>/datasiteitem/allsiteitem?pages="+(parseInt($("#dq").html())+1)+"&name="+name+"&type="+type;
			 $.ajax({
				 type : "POST",
				 url : url,
				 contentType: "application/json; charset=utf-8",
				 dataType: "text",
				 data : "",
				 success : function(data) {
					json = eval('('+data+')');
					 $("#tbody").html(json[0].sb);
					 $("#zys").html(json[0].zys);
					 $("#dq").html(json[0].pages);
					 $('.tree').treegrid();
				 if(parseInt(json[0].zys)<=parseInt($("#dq").html())*10){
						$("#nfy").attr("class","disabled");
					}
				 if(parseInt($("#dq").html())!=1){
						$("#lfy").removeAttr("class");
					}
				 }
				});
		 }
	 }
	 function del(id){
		 if(confirm("确定删除选中项目吗？")){
			var url = "<%=path %>/datasiteitem/delrealsite?id="+id;
			$.post(url,function(data){
				if(data=="-1"){
					alert("有下级菜单，无法删除！");
				}else if(data=="1"){
					alert("删除成功！");
				}else{
					alert("删除失败！");
				}
				window.location.href="<%=path %>/datasiteitem/setsiteitem"; 
			},"text");
		 }
	 };
	 function update(){
		 var siteType1 = $("#siteType1").find('option:selected').text();
		 var siteType2 = $("#siteType2").find('option:selected').text();
		 $("#siteType").attr("value",siteType1+"--"+siteType2);
		 var url = "<%=path %>/datasiteitem/update";
		 var data1 = $("#form").serialize();
		$.post(url,data1,function(data){
			if(data=='1'){
				alert('修改成功！');
				window.location.href="<%=path %>/datasiteitem/setsiteitem"; 
			}else{
				alert('修改失败！');
			}
		});
	 }
	 function addNext(){
		var siteType1 = $("#siteType1").find('option:selected').text();
		var siteType2 = $("#siteType2").find('option:selected').text();
		$("#siteType").attr("value",siteType1+"--"+siteType2);
		var status = demo.check(false);
		if(status==true){
			 var url = "<%=path %>/datasiteitem/addnextrsc";
			 var data1 = $("#form").serialize();
				$.post(url,data1,function(data){
					if(data=='1'){
						alert('增加成功！');
						window.location.href="<%=path %>/datasiteitem/setsiteitem"; 
					}else if(data=='2'){
						alert('名称已经存在！');
					}else{
						alert('增加失败！');
					}
				});
		}
	 }
	 function OpenAddModel(parentId){
		 $("#parentId").attr("value",parentId);
		 if(parentId==0){
			 $("#myModalLabel").text("增加站点");
		 }else{
			 $("#myModalLabel").text("增加站点分支");
		 }
		 $("#div2").attr("style","display:none");
		 $("#div1").attr("style","display:block");
		 $("#button").text("增加");
		 $("#button").attr("onclick","addNext()");
		 $("#id").attr("value","");
		 $("#name").attr("value","");
		 $("#url").attr("value","");
		 $("#siteDescription").attr("value","");
		 //$("#publisherId").attr("value","");
		 siteChange();
		}
	 function OpenTurnModel(id,name){
		 window.location.href="<%=path %>/datasiteitem/turntositeitem?id="+id+"&name="+name; 
	 }
	 function OpenEditModel(id,name,url,siteType1,siteType2,siteDescription,publisherId,executionCycle,dueTime,otherDescription,parentId){
		 $("#myModalLabel").text("站点编辑");
		 $("#button").text("修改");
		 $("#button").attr("onclick","update()");
		 $("#div2").attr("style","display:none");
		 $("#div1").attr("style","display:block");
		 $("#id").attr("value",id);
		 $("#parentId").attr("value",parentId);
		 $("#executionCycle").attr("value",executionCycle);
		 $("#dueTime").attr("value",dueTime);
		 $("#otherDescription").attr("value",otherDescription);
		 $("#name").attr("value",name);
		 $("#url").attr("value",url);
		 $("#siteDescription").attr("value",siteDescription);
		 var all_options1 = document.getElementById("siteType1").options;
		 for(i = 0;i<all_options1.length;i++){
			 if(all_options1[i].innerHTML == siteType1){
				 all_options1[i].selected = true;
			 }
		 }
		 siteChange();
		 setTimeout(function(){var all_options2 = document.getElementById("siteType2").options;
		 for(i = 0;i<all_options2.length;i++){
			 if(all_options2[i].innerHTML == siteType2){
				 all_options2[i].selected = true;
			 }
		 }},800);
		 //$("#publisherId").attr("value",publisherId);
		}
	 function OpenViewModel(id,name,url,siteType1,siteType2,siteDescription,publisherId,executionCycle,dueTime,otherDescription){
		 $("#myModalLabel").text("站点查看");
		 $("#div1").attr("style","display:none");
		 $("#div2").attr("style","display:block");
		 $("#button").attr("onclick","update()");
		 $("#id").attr("value",id);
		 $("#executionCycle").attr("value",executionCycle);
		 $("#dueTime").attr("value",dueTime);
		 $("#otherDescription").attr("value",otherDescription);
		 $("#name").attr("value",name);
		 $("#url").attr("value",url);
		 $("#siteDescription").attr("value",siteDescription);
		 var all_options1 = document.getElementById("siteType1").options;
		 for(i = 0;i<all_options1.length;i++){
			 if(all_options1[i].innerHTML == siteType1){
				 all_options1[i].selected = true;
			 }
		 }
		 siteChange();
		 setTimeout(function(){var all_options2 = document.getElementById("siteType2").options;
		 for(i = 0;i<all_options2.length;i++){
			 if(all_options2[i].innerHTML == siteType2){
				 all_options2[i].selected = true;
			 }
		 }},800);
		 //$("#publisherId").attr("value",publisherId);
		}
	</script>
<body style="margin:0; padding:0">
<jsp:include page="../topBase.jsp"></jsp:include>
      <div  class="content_waikuang">
	  	<div class="container-fluid search_select select-result" style="padding-left:0">
      
      	<h2 class="content_title">${title }</h2>
      	<div  class="renwu_caozuo" style="margin-right: 50px"><a  class="renwu_select" href="#" data-toggle="modal" id="add_0" data-target="#addModal" onclick="OpenAddModel(0)"  >新建站点</a></div>
      
      	</div> 
	    <div class="row" style="clear:both">
	    	<div class="span3 " style="width: 17%">
			<div class="well content_left">
					<h4>筛选器</h4>
					<dl class="nav nav-list nav_headerxs">
						<dt class="nav-header">按名称搜索</dt>
						<dd class="nav_leftinput" style="display: block">
							<input id="inputName" name="inputName" type="text" style="width: 94%">
							<img src="${webRoot }/resources/img/new_version/search1.png"
								onclick="searchInput()" />
						</dd>
					</dl>
					<dl class="nav nav-list nav_headerxs">
						<dt class="nav-header">按站点类型搜索</dt>
						<dd class="nav_leftinput" style="display: block">
							<input id="inputType" name="inputType" type="text" style="width: 94%">
							<img src="${webRoot }/resources/img/new_version/search1.png"
								onclick="searchInput()" />
						</dd>
					</dl>
				</div>
			</div>
		    <div class="span9" style="width:74%;margin-top:-20px">
	            <table class="tree table table-bordered table-condensed "> 
		           <tr id="tree-head-1" ><th>序号</th><th>网站名称</th><th>URL</th><th>站点类型</th><th>描述</th><th>创建人</th><th>创建时间</th><th>操作</th></tr>
		           <tbody id="tbody">
				
			       </tbody>
				</table>
			</div>
		</div>
		</div>
		<div>
	  	 <nav align="center">
	  		<ul class="pager">
			    <li id="lfy" class="disabled">
			      <a href="#" aria-label="Previous "  onclick="lfy()"><span aria-hidden="true">&larr;上一页</span></a>
			    </li>
			   	
			    <li id="nfy" class="disabled">
			     <a  href="#" aria-label="Next" style="margin-left:  20px" onclick="nfy()"><span aria-hidden="false">下一页&rarr;</span> </a>
			    </li>
			     <label style="margin-left: 20px" >当前页:<span id="dq"></span></label>
			     <label  style="margin-left: 20px">总数量:<span id="zys"></span></label>
			  </ul>
			</nav>
	  	</div>
		<!-- </div> -->
		<!-- Button trigger modal -->
<!-- Modal -->
<div class="modal hide fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">增加站点</h4>
      </div>
      <form id="form" method="POST">
      <input type="hidden" name="id" id="id">
      <input type="hidden" name="parentId" id="parentId">
      <input type="hidden" name="siteType" id="siteType">
      <div class="modal-body">
        	<div class="form-group">
				<label class="content_zjnrcqlabel" >网站名称</label> <input type="text"
					class="form-control content_zjnrcqtext" name='name' id="name" placeholder="网站名称(全中文名称长度小于50个汉字)" datatype="*" nullmsg="请输入网站名称！" errormsg=" ">
			</div>
			<div class="form-group">
				<label class="content_zjnrcqlabel">URL</label> <input type="text"
					class="form-control content_zjnrcqtext" name='url' id="url" placeholder="url" datatype="*" nullmsg="请输入url！" errormsg=" ">
			</div>
			<div class="form-group">
				<label class="content_zjnrcqlabel">站点类型</label> 
				<select id="siteType1" class="form-control content_zjnrcqtext" onchange="siteChange()">
					<!-- <option value="0">---请选择---</option> -->
				</select>
			</div>
			<div class="form-group">
				<label class="content_zjnrcqlabel">站点类型</label> 
				<select id="siteType2" class="form-control content_zjnrcqtext">
					<!-- <option value="0">---请选择---</option> -->
				</select>
			</div>
			<div class="form-group">
				<label class="content_zjnrcqlabel">站点描述</label> <input type="text"
					class="form-control content_zjnrcqtext" name='siteDescription' id="siteDescription" placeholder="站点描述" datatype="*" nullmsg="请输入站点描述！" errormsg=" ">
			</div>
			<div class="form-group">
				<label class="content_zjnrcqlabel">执行周期</label> <input type="text"
					class="form-control content_zjnrcqtext" name='executionCycle' id="executionCycle" placeholder="执行周期" datatype="*" nullmsg="请输入执行周期！" errormsg=" ">
			</div>
			<div class="form-group">
				<label class="content_zjnrcqlabel">任务交付时间</label> <input type="text"
					class="form-control content_zjnrcqtext" name='dueTime' id="dueTime" placeholder="任务交付时间" datatype="*" nullmsg="请输入任务交付时间！" errormsg=" ">
			</div>
			<div class="form-group">
				<label class="content_zjnrcqlabel">任务其他描述</label> <input type="text"
					class="form-control content_zjnrcqtext" name='otherDescription' id="otherDescription" placeholder="任务其他描述" datatype="*" nullmsg="请输入任务其他描述！" errormsg=" ">
			</div>
			<!-- <div class="form-group">
				<label for="exampleInputEmail1">创建人</label> <input type="text"
					class="form-control" name='publisherId' id="publisherId" placeholder="创建人" datatype="*" nullmsg="请输入创建人！" errormsg=" ">
			</div> -->
      </div>
      </form>
      <div id="div1" class="modal-footer">
      	<button type="button" class="btn btn-primary" id="button" onclick="addNext()">增 加</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
      </div>
      <div id="div2" class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
      </div>
    </div>
  </div>
</div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    </body>
</html>
