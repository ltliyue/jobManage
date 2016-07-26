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

<title>增加数据项</title>

<!-- Bootstrap core CSS -->
	<link href="<%=path%>/resources/css/new_version/bootstrap.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/css/new_version/site.css" rel="stylesheet">
<!-- Bootstrap core JavaScript
    ================================================== -->
	<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/script.js"></script>
    <script src="<%=path%>/resources/js/new_version/site.js"></script>
	<script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
</head>
<script type="text/javascript">
	var i = 0;
	var demo;
	$(function(){
		demo=$("#formn").Validform({
			tiptype:3,
			showAllError:true
		});
		
	});
	
	function delField(id,parentId,fieldCode){
		if(confirm("确定删除选中项目吗？")){
		var url = "<%=path %>/datastandard/delfield?id="+id;
		var name = $("#name").val();
		$.post(url,function(data){
			if(data=="1"){
				alert("删除字段成功！");
				window.location.href="<%=path %>/datastandard/addfield?id="+parentId+"&name="+name;
			}else{
				alert("删除字段失败！");
			}
			});
		}
	}
	
	function addNext(){
		var id=$("#id").val();
		var fieldDescription = $("#efieldDescription").val();
		var parentId = $("#parentId").val();
		var fieldCode = $("#efieldCode").val();
		var fieldName = $("#efieldName").val();
		//var publisherId = $("#epublisherId").val();
		var oldFieldCode = $("#oldFieldCode").val();
		var oraKeyWords = $("#oraKeyWords").val();
		var keyWords = oraKeyWords.split(",");
		var flag = true;
		for(var i=0;i<keyWords.length;i++){
			if(fieldCode==keyWords[i]){
				flag = false;
			}
		}
		if(flag==false){
			alert("字段代码"+fieldCode+"与Oralce关键字重复！！！");
		}else{
			var url = "<%=path %>/datastandard/editfield?id="+id+"&fieldCode="+fieldCode+"&fieldName="+fieldName+"&parentId="+parentId+"&fieldDescription="+fieldDescription;
			$.post(url,function(data){
				if(data=="1"){
					alert("字段修改成功！");
					window.location.href="<%=path %>/datastandard/addfield?id="+parentId+"&name="+name;;
				}else{
					alert("字段修改失败！");
				}
			});
		}
	}
	
	function editField(id,parentId,fieldCode,fieldDescription,fieldName){
		$("#id").attr("value",id);
		$("#oldFieldCode").attr("value",fieldCode);
		$("#efieldCode").attr("value",fieldCode);
		$("#efieldName").attr("value",fieldName);
		$("#efieldDescription").attr("value",fieldDescription);
	}
	
	function addOne(){
		i++;
		$("#stbody").append("<tr align='left'><td><input type='checkbox' name='chk_list' value='"
				 +i+"' ></td><td>"
				 +"<input type='text' class='form-control' name='fieldCode' id='fieldCode' value=''></input></td><td>"
				 +"<input type='text' class='form-control' name='fieldName' id='fieldName' value=''></input></td><td>"
				 +"<input type='text' class='form-control' name='fieldDescription' id='fieldDescription' value=''></input></td><td></tr>");
	}
	
	function delOne(){
		obj = document.getElementsByName("chk_list");
		check_val = [];
		for(k in obj){
			if(obj[k].checked){
				var tr = obj[k].parentNode.parentNode;
				tr.parentNode.removeChild(tr);
			}
		}
	}

	function submit(){
		var status = demo.check(false);
		var parentId = $("#parentId").val();
		var name = $("#name").val();
		var oraKeyWords = $("#oraKeyWords").val();
		var keyWords = oraKeyWords.split(",");
		var flag = true;
		var fieldCodes = document.getElementsByName("fieldCode");
		var fieldCode="";
		for(var j=0;j<fieldCodes.length;j++){
			for(var i=0;i<keyWords.length;i++){
				if(fieldCodes[j].value==keyWords[i]){
					flag = false;
					fieldCode=fieldCode+","+fieldCodes[j].value;
				}
			}
		}
		if(flag==false){
			alert("字段代码"+fieldCode+"与Oralce关键字重复！！！");
		}else{
			if(status==true){
				var url = "<%=path %>/datastandard/addfieldsub";
				 var data1 = $("#form").serialize();
					$.post(url,data1,function(data){
						if(data=='1'){
							alert('增加成功！');
							window.location.href="<%=path %>/datastandard/addfield?id="+parentId+"&name="+name;
						}else if(data=='2'){
							alert('名称已经存在！');
						}else{
							alert('增加失败！');
						}
					});
			}
		}
	}
</script>
<body>
	<jsp:include page="../topBase.jsp"></jsp:include>
	<div  class="content_waikuang">
	<div class="container-fluid search_select select-result" style="padding-left:0">
	<h2 class="content_title">${title}</h2>
    <div  class="renwu_caozuo"><a  class="renwu_select" data-toggle="modal" id="add_0" data-target="#addModal" >添加字段信息</a></div>    
        <!-- <h4><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="add_0" data-target="#addModal" >添加字段信息</button> </h4> -->
		<form action="<%=path %>/datastandard/fieldadd" id='formn' method="post">
			<div class="form-group">
				<label for="exampleInputEmail1">上级菜单名称</label> <input type="text"
					class="form-control" name='sname' id="sname" value="${sname }" placeholder="上级菜单名称" readonly="readonly" >
			</div>
			<table class="table table-striped">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>字段代码</th>
                  <th>字段名称</th>
                  <th>字段描述</th>
                  <th>创建人</th>
                  <th>创建时间</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody id="tbody">
              	<c:if test="${not empty fields}">
						<c:forEach var="field" items="${fields }" varStatus="ts">
							<tr align="left">
								<%-- <td><input type='checkbox' name='chk_list'
									value="${field.parentId }"></td> --%>
								<td id="xh">${ts.count }</td>
								<td id="fieldCode">${field.fieldCode }</td>
								<td id='fieldName'>${field.fieldName }</td>
								<td id='fieldDescription'>${field.fieldDescription }</td>
								<td id='publisherId'>${field.publisherId }</td>
								<td id='publishTime'>${field.publishTime }</td>
								<td><button type="button" id="del_${field.fieldCode }" class="btn btn-primary btn-sm" data-toggle="modal" onclick="delField('${field.id }','${field.parentId }','${field.fieldCode }')" >删除</button>  <button type="button" id="edit_${field.fieldCode }" data-target="#modifyField" class="btn btn-info btn-sm" data-toggle="modal" onclick="editField('${field.id }','${field.parentId }','${field.fieldCode }','${field.fieldDescription }','${field.fieldName }')">编辑</button></td>
							</tr>
						</c:forEach>
					</c:if>
              </tbody>
              </table>
		</form>
	<!-- <button type="button" class="btn btn-primary" id="ajaxpost">提交</button> -->
	</div>
	</div>
	
	<div class="modal hide fade" id="addModal" tabindex="-1" role="dialog" style="width:800px;" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width:800px;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">增加字段</h4>
      </div>
      <form id="form" method="POST">
      <input type="hidden" name="parentId" id="parentId" value="${parentId }">
      <input type="hidden" name="name" id="name" value="${sname }">
      <input type="hidden" name="oraKeyWords" id="oraKeyWords" value="${oraKeyWords }">
      <div class="modal-body">
      <table class="table table-striped"><tr>
      	<th><input type="checkbox" name="chk_all" id="chk_all" onclick="SelectAll()" /></th>
      	<th>字段代码</th>
        <th>字段名称</th>
        <th>字段描述</th>
        <!-- <th>创建人</th> --></tr>
        <tbody id="stbody">
        	
        </tbody>
       </table>
      </div>
      </form>
      <div class="modal-footer">
      	<button type="button" class="btn btn-primary" id="addOne" onclick="addOne()">添加一行</button>
      	<button type="button" class="btn btn-primary" id="delOne" onclick="delOne()">删除</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="button" onclick="submit()">提 交</button>
      </div>
    </div>
  </div>
</div>

<div class="modal hide fade" id="modifyField" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog"  role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel1">修改字段</h4>
      </div>
      <form id="nform" method="POST">
      <input type="hidden" name="oldFieldCode" id="oldFieldCode" >
      <input type="hidden" name="id" id="id" >
      <div class="modal-body">
      		<div class="form-group" >
				<label for="exampleInputEmail1">字段代码</label> <input type="text"
					class="form-control" name='efieldCode' id="efieldCode" placeholder="字段代码" datatype="n" nullmsg="请输入字段名称！" errormsg="字段代码为数字">
			</div>
        	<div class="form-group">
				<label for="exampleInputEmail1">字段名称</label> <input type="text"
					class="form-control" name='efieldName' id="efieldName" placeholder="字段名称" datatype="s" nullmsg="请输入字段名称！" errormsg="字段名称为英文或汉字">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">字段描述</label> <input type="text"
					class="form-control" name='efieldDescription' id="efieldDescription" placeholder="字段描述" datatype="s" nullmsg="请输入字段描述！" errormsg="字段描述为英文或汉字">
			</div>
			<!-- <div class="form-group">
				<label for="exampleInputEmail1">创建人</label> <input type="text"
					class="form-control" name='epublisherId' id="epublisherId" placeholder="创建人" datatype="s" nullmsg="请输入创建人！" errormsg="创建人为英文活汉字！">
			</div> -->
      </div>
      </form>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary" id="button" onclick="addNext()">修 改</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>
