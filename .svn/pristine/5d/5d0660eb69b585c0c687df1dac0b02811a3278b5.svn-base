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
	 <!-- Bootstrap core jss -->
	 <script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/script.js"></script> 
     <script src="<%=path%>/resources/js/new_version/site.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
	
  </head>
	<script type="text/javascript">
	 $(document).ready(function(){
		 allrole();
	  });
	 function allrole(){
		 var url = "${webRoot}/datarole/allrole";
			$.post(url,function(data){
				json=eval('('+data+')');
				  $.each(json,function(i){
					  var enable = "可用";
					  if(json[i].enable.toString()=="0"){
						  enable = "不可用";
					  }
					 
					  $("#tbody").append("<tr> <td><input type='checkbox' name='chk_list' value='"+json[i].id+"' ></td><td>"+(i+1)+"</td><td id='name'"+i+">"+json[i].name+"</td><td>"+enable+"</td></tr>");
				  });
			});
	 };
	 function delrole(){
		 if($("input[name='chk_list']:checked").length>0){
		  		if(confirm("确定删除选中项目吗？")){
		  			var del_id = '';
		  			$("input[name='chk_list']:checked").each(function(val){	
		  				del_id +=$(this).val()+";";
		  			});
		  			var url = "<%=path%>/datarole/roledel?id="+del_id;
		  			$.post(url,function(data){
		  				alert("删除完成！");
		  				window.location.href="<%=path%>/datarole/setrole"; 
		  			},"text");
		  		}
	  		}else{
	  	  			alert("请选择一项进行删除！");
	  	  		}
	 };
	 function update(){
		 if($("input[name='chk_list']:checked").length==1){
			 window.location.href="<%=path%>/datarole/role?t=2&id="+$("input[name='chk_list']:checked").val(); 
  		}else{
  			alert("请选择一项进行修改！");
  		}
	 }
	</script>
  <body style="margin:0; padding:0">
<jsp:include page="../topBase.jsp"></jsp:include>
 <!--内容开始-->
          <div class="content_waikuang">
          <div class="container-fluid search_select select-result" style="padding-left:0">
          <h2 class="content_title">${title }</h2>
          
		<div  class="renwu_caozuo"><a  class="renwu_select"  href="<%=path%>/datarole/role?t=1&id=0">添加角色</a><a  class="renwu_select"  href="#" onclick="update()">修改角色</a><a  class="renwu_select" href="javascript:void(0)" onclick="delrole()">删除角色</a> </div>
<div class="container-fluid search_select select-result" style="padding-left:0; margin:0 auto">
          <div class="table-responsive">
            <table class="table  table-striped content_rwgl">
              <thead>
                <tr>
               	  <th><input type="checkbox" name="chk_all" id="chk_all" onclick="SelectAll()" /></th>
                  <th>序号</th>
                  <th>名称</th>
                  <th>是否可用</th>
                </tr>
              </thead>
              <tbody id="tbody">
              </tbody>
            </table>
          </div>
          </div>
        </div>
	</div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    </body>
</html>
