<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link href="${webRoot }/resources/css/bootstrap-treeview.css" rel="stylesheet">
<link rel="stylesheet" href="${webRoot }/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
<!-- Bootstrap core JavaScript
    ================================================== -->
	 <script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
     <script src="<%=path%>/resources/js/new_version/script.js"></script>
     <script src="<%=path%>/resources/js/new_version/site.js"></script>
	<script type="text/javascript" src="${webRoot }/resources/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${webRoot }/resources/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
</head>
<SCRIPT type="text/javascript">
var setting = {  
        check: {  
            enable: true,  
            nocheckInherit: false  
        },view: {
			dblClickExpand: false,
			showLine: true,
			selectedMulti: false
		},  
        data: {  
            simpleData: {  
                enable: true  
            } 
        },
		callback: {
			onCheck: GetCheckedAll
		}   
    };  

    //过滤节点的机制 直接return node表示不做任何过滤  
    function filter(node) {  
        return node;  
    }  
    var zTree ;
    ///动态设置zTree的所有节点有checkbox  
    function DynamicUpdateNodeCheck() {  
    	zTree= $.fn.zTree.getZTreeObj("treeDemo");  
        //根据过滤机制获得zTree的所有节点              
        var nodes = zTree.getNodesByFilter(filter);  
       
        //遍历每一个节点然后动态更新nocheck属性值  
        for (var i = 0; i < nodes.length; i++) {  
            var node = nodes[i];  
            node.nocheck = false; //表示显示checkbox  
            zTree.updateNode(node);  
        }  
       
    }  
    //获取所有选中节点的值
    function GetCheckedAll() {
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        var msg_name = "";
        var msg_id = "";
        for (var i = 0; i < nodes.length; i++) {
           // msg += nodes[i].name+"--"+nodes[i].id+"--"+nodes[i].pId+"\n";
           msg_name += nodes[i].name+"；";
           msg_id +=nodes[i].id+";";
        }
        $("#msg_name").text(msg_name);
        $("#msg_id").val(msg_id);
    }    
    function createTree () {
        var zNodes;
        $.ajax({
            url: '${webRoot}/dataauthority/allau', //url  action是方法的名称
            type: 'POST',
            dataType: "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
            
               zNodes = eval('(' + data + ')');

               $.fn.zTree.init($("#treeDemo"), setting, zNodes).expandAll(true);
               //id 查询  role内容
               var url = "${webRoot}/datarole/roleq?id=${id}";
               $.post(url,function(data){
              		 json = eval('('+data+')');
          			$("#name").val(json.name);
          			if(json.enable==1){
          				$("#check").attr("checked","checked");
          				$("#enable").attr("value","1");
          			}else{
          				$("#check").removeAttr("checked");
          				$("#enable").attr("value","0");
          			}
          			var id = json.authorityid;
          			$("#msg_id").val(id);
          			if(id!=null&&id!=""){
          				var s = id.split(";");
          				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
              			s.forEach(function(e){
              				if(e!=""){
              					 var n = zTree.getNodeByParam("id",e,null);
                     			n.checked=true;
                     			zTree.updateNode(n);
              				}
                            
              			});
          			}
          			
          			});
              
            },
            error: function (msg) {
                alert("失败");
            }
        });
    }
    ///页面加载后初始化zTree数据且默认展开所有节点  
    $(document).ready(function () {  
      createTree();  
     
    });  
</SCRIPT>
<script type="text/javascript">
var demo;
$(function () {
		demo=$("#formn").Validform({
		tiptype:3,
		showAllError:true
		});
});
	function submit(){
		var status = demo.check(false);
		if(status==true){
			var url = '${webRoot}/datarole/roleup';
			if($("#check").prop("checked")){
				$("#enable").attr("value","1");
			}else{
				$("#enable").attr("value","0");
			}
			var data1 = $("#formn").serialize();
			
			$.post(url,data1,function(data){
				if(data=="1"){
					alert("修改成功！");
					window.location.href="${webRoot}/datarole/setrole";
				}else{
					alert("修改失败！");
				}
			});
		}
	}
</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
    <div  class="content_waikuang">
	<div class="container-fluid search_select" style="padding-left:0">
	<h2 class="content_title">角色修改</h2>
		<form action="<%=path %>/datarole/roleup" id='formn' method="post">
		<input type="hidden" name="id" value="${id }">
			<div class="form-group">
				<label class="content_zjnrcqlabel">角色名</label> <input type="text"
					class="form-control content_zjnrcqtext" name='name' id="name" placeholder="角色名" datatype="*" nullmsg="请输入角色名！" errormsg=" ">
			</div>
			<div class="form-group">
			<label for="exampleInputEmail1"><input type="checkbox" checked="checked" id="check" >
			<input type="hidden" id="enable" name="enable" value="1">可用</label> 
			</div>
			<hr>
			<h4>权限绑定</h4>
			<hr>
			<label for="exampleInputEmail1">选中的权限：</label><span id="msg_name" ></span>
			<input type="hidden" id="msg_id" name="authorityid"> 
    <div class="content_wrap">  
        <div class="zTreeDemoBackground left">  
            <ul id="treeDemo" class="ztree">  
            </ul>  
        </div>  
    </div>  
		</form>
	<button type="button" class="btn btn-primary" onclick="submit()">提交</button>
	</div>
</div>
</body>
</html>
