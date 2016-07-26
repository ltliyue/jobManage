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
		
	  });
	 function all(){
		 
		 var url = "${webRoot}/dataauthority/queryau";
			$.post(url,function(data){
				json = eval('('+data+')');
				 $("#tbody").append(json);
				 $('.tree').treegrid();
			});
	 };
	 function del(id){
		 if(confirm("确定删除选中项目吗？")){
			var url = "${webRoot}/dataauthority/del?id="+id;
			$.post(url,function(data){
				if(data=="-1"){
					alert("有下级菜单，无法删除！");
				}else if(data=="1"){
					alert("删除成功！");
				}else{
					alert("删除失败！");
				}
				window.location.href="${webRoot}/dataauthority/setauthority"; 
			},"text");
		 }
	 };
	 function update(){
		 var p = $("#position1").val();
		 var p1 = $("#position").val();
		 var k=0;
		 if(p==p1){
			 k=0;
		 }else{
			 k=1;
		 }
		 var url = "${webRoot}/dataauthority/update?k="+k;
		 var data1 = $("#form").serialize();
		$.post(url,data1,function(data){
			if(data=='1'){
				alert('修改成功！');
				window.location.href="${webRoot}/dataauthority/setauthority"; 
			}else{
				alert('修改失败！');
			}
		});
	 }
	 function addNext(){
		 var status = demo.check(false);
		if(status==true){
			 var url = "${webRoot}/dataauthority/addnext";
			 var data1 = $("#form").serialize();
				$.post(url,data1,function(data){
					if(data=='1'){
						alert('增加成功！');
					}else{
						alert('增加失败！');
					}
				});
		}
	 }
	 function OpenAddModel(i){
		 $("#parentid").attr("value",i);
		 $("#myModalLabel").text("增加下级");
		 $("#idh").attr("value","");
		 $("#name").attr("value","");
		 $("#url").attr("value","");
		 $("#position").attr("value","");
		 $("#position1").attr("value","");
		 $("#add_"+i).attr("data-target","#addModal");
		}
	 function OpenEditModel(id,name,url,position,pid){
		 $("#myModalLabel").text("编辑菜单");
		 $("#parentid").attr("value",pid);
		 $("#button").text("修改");
		 $("#button").attr("onclick","update()");
		 $("#idh").attr("value",id);
		 $("#name").attr("value",name);
		 $("#url").attr("value",url);
		 $("#position").attr("value",position);
		 $("#position1").attr("value",position);
		 $("#edit_"+id).attr("data-target","#addModal");
		}
	</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
 <!--内容开始-->
    <div  class="content_waikuang">
	<div class="container-fluid search_select " style="padding-left:0">
	<h2 class="content_title">${title }</h2>

      <div  class="renwu_caozuo" style="padding-bottom:20px"><a  class="renwu_select" style="text-decoration:none" href="#" data-toggle="modal" id="add_0" data-target="" onclick="OpenAddModel(0)"  >添加一级菜单</a></div>
         <!--  <table class="table  table-striped content_rwgl">   -->  
          <table class="tree table table-hover table-bordered table-condensed" > 
		    <tr class='success'><th>序号</th><th>菜单名称</th><th>菜单url</th><th>菜单位置</th><th style="width:200px">操作</th></tr>
		    <tbody id="tbody">
				
			 </tbody>
		</table>
		</div>
		</div>
		<!-- Button trigger modal -->
<!-- Modal -->
<div class="modal hide fade" id="addModal" tabindex="-1" >
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">增加菜单</h4>
      </div>
      <form id="form" method="POST">
      <input type="hidden" name="parentid" id="parentid">
      <input type="hidden" name="id" id="idh">
      <div class="modal-header">
        	<div class="form-group">
				<label for="exampleInputEmail1">菜单名</label> <input type="text"
					class="form-control content_zjnrcqtext" name='name' id="name" placeholder="菜单名" datatype="*" nullmsg="请输入菜单名！" errormsg=" ">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">菜单地址</label> <input type="text"
					class="form-control content_zjnrcqtext" name='url' id="url" placeholder="菜单url" >
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">菜单位置</label> <input type="text"
					class="form-control content_zjnrcqtext" name='position' id="position" placeholder="请输入数字" datatype="n" nullmsg="请输入位置！" errormsg="请输入数字">
					<input type="hidden" id="position1">
			</div>
      </div>
      </form>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="button" onclick="addNext()">增 加</button>
      </div>
    </div>
  </div>
</div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    </body>
</html>
