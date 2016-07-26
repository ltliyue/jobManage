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

    <title>站点类型</title>
	
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
		 var url = "<%=path %>/datacode/allsite?pages=1";
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
	 }
	 function lfy(){
		 if($("#lfy").attr("class")!="disabled"){
			 var url = "<%=path %>/datacode/allsite?pages="+(parseInt($("#dq").html())-1);
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
			 var url = "<%=path %>/datacode/allsite?pages="+(parseInt($("#dq").html())+1);
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
			var url = "<%=path %>/datacode/delsite?id="+id;
			$.post(url,function(data){
				if(data=="-1"){
					alert("有下级站点类型，无法删除！");
				}else if(data=="1"){
					alert("删除成功！");
				}else{
					alert("删除失败！");
				}
				window.location.href="<%=path %>/datacode/setsitecode"; 
			},"text");
		 }
	 };
	 function update(){
		 var url = "<%=path %>/datacode/update";
		 var data1 = $("#form").serialize();
		$.post(url,data1,function(data){
			if(data=='1'){
				alert('修改成功！');
				window.location.href="<%=path %>/datacode/setsitecode"; 
			}else if(data=='2'){
				alert('名称已经存在！');
			}else{
				alert('修改失败！');
			}
		});
	 }
	 function addNext(){
		var status = demo.check(false);
		if(status==true){
			 var url = "<%=path %>/datacode/addnextsc";
			 var data1 = $("#form").serialize();
				$.post(url,data1,function(data){
					if(data=='1'){
						alert('增加成功！');
						window.location.href="<%=path %>/datacode/setsitecode"; 
					}else if(data=='2'){
						alert('名称已经存在！');
					}else{
						alert('增加失败！');
					}
				});
		}
	 }
	 function OpenAddModel(i,levelCode){
		 if(i==0){
			 $("#parentId").attr("value",i);
			 $("#levelCode").attr("value",1);
			 $("#myModalLabel").text("新建一级站点类型");
			 $("#button").text("添加");
			 $("#button").attr("onclick","addNext()");
			 //$("#snamediv").attr("style","display:none");
			 $("#name").attr("value","");
		 }else{
			 $("#parentId").attr("value",i);
			 $("#levelCode").attr("value",levelCode);
			 $("#myModalLabel").text("增加下级站点类型");
			 $("#button").text("添加");
			 $("#button").attr("onclick","addNext()");
			 //$("#snamediv").attr("style","display:block");
			 //$("#sname").attr("value","");
			 $("#name").attr("value","");
		 }
		}
	 function OpenEditModel(id,name,levelCode,publisherId,parentId){
		 $("#myModalLabel").text("编辑站点类型");
		 $("#id").attr("value",id);
		 $("#parentId").attr("value",parentId);
		 $("#button").text("修改");
		 $("#button").attr("onclick","update()");
		 $("#levelCode").attr("value",levelCode);
		 //$("#snamediv").attr("style","display:block");
		 //$("#sname").attr("value",);
		 $("#name").attr("value",name);
		}
	</script>
<body style="padding-top:0" >
<jsp:include page="../topBase.jsp"></jsp:include>

 <!-- <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> -->
       
         <!-- <h1 class="page-header">站点类型管理</h1> -->
	<div  class="content_waikuang">
	<div class="container-fluid search_select " style="padding-left:0">
   		<h2 class="content_title">${title }</h2>
   		<div  class="renwu_caozuo"><a  class="renwu_select" style="text-decoration:none" href="#" data-toggle="modal" id="add_0" data-target="#addModal" onclick="OpenAddModel(0,1)"  >新建站点类型</a></div>
    </div>
   
          
		  <div class="row" style="clear:both;margin-left:0">
				
		  <div class="span9" style="width:100%; margin-left:0">
          <table class="tree table table-hover table-bordered table-condensed">    
          <!-- <table id='tree-1' border="1" cellpadding="0" cellspacing="0"  class="table content_rwgl content_xhtable" style="width:95%"> -->
		    <tr class='success'><th>序号</th><th>站点类型名称</th><th>创建人</th><th>创建时间</th><th style="width:200px">操作</th></tr>
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
	  	 </div>
		<!-- Button trigger modal -->
<!-- Modal -->
<div class="modal hide fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">增加下级站点类型</h4>
      </div>
      <form id="form" method="POST">
      <input type="hidden" name="id" id="id">
      <input type="hidden" name="parentId" id="parentId">
      <input type="hidden" name="levelCode" id="levelCode">
      <div class="modal-body">
      		<div class="form-group" id="snamediv" style="display:none" >
				<label class="content_zjnrcqlabel">上级站点类型名称</label> <input type="text"
					class="form-control" name='sname' id="sname" placeholder="上级站点类型名称" readonly="readonly" >
			</div>
        	<div class="form-group">
				<label class="content_zjnrcqlabel">站点类型名称</label> <input type="text"
					class="form-control" name='name' id="name" placeholder="站点类型名称" datatype="*" nullmsg="请输入站点类型名称！" errormsg=" ">
			</div>
			<!-- <div class="form-group">
				<label for="exampleInputEmail1">创建人</label> <input type="text"
					class="form-control" name='publisherId' id="publisherId" placeholder="创建人" datatype="*" nullmsg="请输入创建人！" errormsg=" ">
			</div> -->
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
