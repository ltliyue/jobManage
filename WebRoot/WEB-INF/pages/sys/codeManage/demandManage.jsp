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

    <title>需求状态</title>

	<link href="<%=path%>/resources/css/new_version/bootstrap.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/css/new_version/site.css" rel="stylesheet">
	 <!-- Bootstrap core jss -->
  	<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/script.js"></script>
    <script src="<%=path%>/resources/js/new_version/site.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
  </head>
	<script type="text/javascript">
	var demo;
	$(document).ready(function(){
		demo=$("#form").Validform({
			tiptype:3,
			showAllError:true
		});
		all();
	  });
	
	 function all(){
		 var url = "<%=path %>/datademand/alldemand?pages=1";
		 $.post(url,function(data){
			 json = eval('('+data+')');
				 $("#tbody").html(json[0].sb);
				 $("#zys").html(json[0].zys);
				 $("#dq").html(json[0].pages);
				 if(json[0].zys>20){
					 $("#nfy").removeAttr("class");
				 }
			  });
	 };
	 function lfy(){
		 if($("#lfy").attr("class")!="disabled"){
			 var url = "<%=path %>/datademand/alldemand?pages="+(parseInt($("#dq").html())-1);
			 $.post(url,function(data){
				 json = eval('('+data+')');
					 $("#tbody").html(json[0].sb);
					 $("#zys").html(json[0].zys);
					 $("#dq").html(json[0].pages);
				 if(parseInt(json[0].zys)>parseInt($("#dq").html())*20){
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
			 var url = "<%=path %>/datademand/alldemand?pages="+(parseInt($("#dq").html())+1);
			 $.post(url,function(data){
				 json = eval('('+data+')');
					 $("#tbody").html(json[0].sb);
					 $("#zys").html(json[0].zys);
					 $("#dq").html(json[0].pages);
			 if(parseInt(json[0].zys)<=parseInt($("#dq").html())*20){
					$("#nfy").attr("class","disabled");
				}
			 if(parseInt($("#dq").html())!=1){
					$("#lfy").removeAttr("class");
				}
			});
		 }
	 }
	 
	 function editDemand(id,statusName,statusCode,publisherId){
		 $("#myModalLabel").text("编辑需求状态");
		 $("#button").text("确认修改");
		 $("#id").attr("value",id);
		 $("#statusName").attr("value",statusName);
		 $("#statusCode").attr("value",statusCode);
		 //$("#publisherId").attr("value",publisherId);
		 $("#button").attr("onclick","modify()");
	 }
	 function OpenAddModel(){
		 $("#myModalLabel").text("添加需求状态");
		 $("#statusName").attr("value","");
		 $("#statusCode").attr("value","");
		 //$("#publisherId").attr("value","");
		 $("#button").text("添加");
		 $("#button").attr("onclick","addDemand()");
		}
	 
	 function addDemand(){
		 var status = demo.check(false);
			if(status==true){
				 var url = "<%=path %>/datademand/adddemand";
				 var data1 = $("#form").serialize();
					$.post(url,data1,function(data){
						if(data=='1'){
							alert('增加成功！');
							window.location.href="<%=path %>/datademand/setdemandcode"; 
						}else if(data=='2'){
							alert('名称已经存在！');
						}else{
							alert('增加失败！');
						}
					});
			}
	 }
	 
	 function deldemand(id){
		 if(confirm("确定删除选中项目吗？")){
				var url = "<%=path %>/datademand/deldemand?id="+id;
				$.post(url,function(data){
					if(data=="1"){
						alert("删除成功！");
					}else{
						alert("删除失败！");
					}
					window.location.href="<%=path %>/datademand/setdemandcode"; 
				},"text");
			 }
	 };
	 function modify(){
		var url = "<%=path %>/datademand/demandupdate";
		var data1 = $("#form").serialize();
		
		$.post(url,data1,function(data){
			if(data=="1"){
				alert("修改需求状态成功");
				window.location.href="<%=path %>/datademand/setdemandcode";
			}else{
				alert("任务失败！");
			}
		});
	 }
	</script>
  <body style="margin:0; padding:0">
<jsp:include page="../topBase.jsp"></jsp:include>

<%-- <h2 class="content_title">${title}</h2> --%>
	<div  class="content_waikuang">
	   <div class="container-fluid search_select " style="padding-left:0">
   		 
   		<h2 class="content_title">${title }</h2>
   		<div  class="renwu_caozuo">
   		     <a  class="renwu_select" style="text-decoration:none" href="#" data-toggle="modal" id="add_0" data-target="#modifyDemand" onclick="OpenAddModel()">添加需求状态</a>
   		</div>
     </div>
   

          <!-- <div  class="container-fluid search_select select-result" style="padding-left:0">
              <h4><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="add_0" data-target="#modifyDemand" onclick="OpenAddModel()"  >添加需求状态</button> </h4>
          </div> -->
		  <div class="row" style="clear:both; margin-left:0">
				
		  <div class="span9" style="width:100%;margin-left:0">
          <!-- <div class="table-responsive"> -->
            <table class="table table-striped">
              <thead>
                <tr>
               	  <th><input type="checkbox" name="chk_all" id="chk_all" onclick="SelectAll()" /></th>
                  <th>序号</th>
                  <th>状态名称</th>
                  <th>状态代码</th>
                  <th>创建人</th>
                  <th>创建时间</th>
                  <th>操作</th>
                </tr>
                <tbody id="tbody">
				
			 	</tbody>
              </thead>
            </table>
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
	 </div>
    <!-- Modal -->
<div class="modal fade" id="modifyDemand" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">编辑任务</h4>
      </div>
      <form id="form" method="POST">
      <input type="hidden" name="id" id="id">
      <div class="modal-body">
      		<!-- <div class="form-group" id="iddiv" style="display:none">
				<label for="exampleInputEmail1">ID</label> <input type="text"
					class="form-control" name='ID' id="ID" value="" placeholder="ID为自动生成" >
			</div> -->
        	<div class="form-group">
				<label for="exampleInputEmail1">状态名称</label> <input type="text"
					class="form-control content_zjnrcqtext" name='statusName' id="statusName" value="" placeholder="状态名称" datatype="s" nullmsg="请输入状态名称！" errormsg="状态名称为英文或者汉字！">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">状态代码</label> <input type="text"
					class="form-control content_zjnrcqtext" name='statusCode' id="statusCode" value="" placeholder="状态代码" datatype="n" nullmsg="请输入最状态代码！" errormsg="状态代码为数字！" >
			</div>
			<!-- <div class="form-group">
				<label for="exampleInputEmail1">创建人</label> <input type="text"
					class="form-control" name='publisherId' id="publisherId" value="" datatype="s" nullmsg="请输入创建人！" errormsg="创建人为英文或者汉字！" placeholder="创建人">
			</div> -->
      </div>
      </form>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="button" onclick="addDemand()">确认修改</button>
      </div>
    </div>
  </div>
</div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    </body>
</html>
