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
<link href="<%=path %>/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=path %>/resources/css/dashboard.css" rel="stylesheet">
<link href="<%=path %>/resources/css/bootstrap-treeview.css" rel="stylesheet">
<link rel="stylesheet" href="<%=path %>/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
<!-- Bootstrap core JavaScript
    ================================================== -->
	<script src="<%=path %>/resources/js/jquery.min.js"></script>
	<script src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
</head>
<SCRIPT type="text/javascript">
var setting = {  
        check: {  
        	chkboxType: {"Y" : "s", "N" : "ps"},
            enable: true,  
            nocheckInherit: false  
        },
        view: {
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
        var msg_code = "";
        $("#tbody").empty();
        for (var i = 0; i < nodes.length; i++) {
           // msg += nodes[i].name+"--"+nodes[i].id+"--"+nodes[i].pId+"\n";
           if(nodes[i].flag=="2"){
        	   msg_name += nodes[i].name+";";
               msg_id +=nodes[i].id+";";
               msg_code +=nodes[i].code+";";
               $("#tbody").append("<tr align='left'><td>"
						 +nodes[i].name+"</td><td>"
						 +nodes[i].code+"</td><td>"
						 +nodes[i].description+"</td></tr>");
           }
        }
        $("#msg_name").text(msg_name);
        $("#fieldName").val(msg_name);
        $("#fieldCode").val(msg_code);
        $("#msg_id").val(msg_id);
        
		var idx = msg_name.indexOf(";",0);
		if(idx>=0 && msg_name.indexOf(";",idx+1)>=0){
			alert("只能选择一个字段！");
			treeObj.checkAllNodes(false);
		}else{
        	parent.document.getElementById($("#eventOwner").val()).value = msg_name.substr(0,msg_name.length-1);
        	parent.document.getElementById($("#eventOwner").val().replace("Comment","Name")).value = msg_code.substr(0,msg_code.length-1);
		}
    } 
    //清除所欲的选中值
       //获取所有选中节点的值
    function clearAllChecked() {
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        treeObj.checkAllNodes(false);
    } 
    
    function createTree () {
        var zNodes;
        $.ajax({
            url: '<%=path %>/datasiteitem/siteitem?siteId=${id}', //url  action是方法的名称
            type: 'POST',
            dataType: "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
            	
               zNodes = eval('('+data+')');
               
               $.fn.zTree.init($("#treeDemo"), setting, zNodes).expandAll(true);
               //id 查询  role内容
               var url = "<%=path %>/datasiteitem/itemq?id=${id}";
               $.post(url,function(data){
              		 json = eval('('+data+')');
          			var fieldName = json.fieldName;
          			var id = json.id;
          			$("#id").val(id);
          			if(fieldName!=null&&fieldName!=""){
          				var s = fieldName.split(";");
          				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
              			s.forEach(function(e){
              				if(e!=""){
              					 var n = zTree.getNodeByParam("name",e,null);
                     			//n.checked=true;
                     			//zTree.updateNode(n);
              				}
                            
              			});
          			}
          			GetCheckedAll();
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
      clearAllChecked();
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
			var url = '<%=path %>/datasiteitem/itemup';
			var data1 = $("#formn").serialize();
			$.post(url,data1,function(data){
				if(data=="1"){
					alert("修改成功！");
					window.location.href="<%=path %>/datasiteitem/setsiteitem";
				}else{
					alert("修改失败！");
				}
			});
		}
	}
</script>
<body>
	<div class="col-sm-9 col-sm-offset-0 col-md-10 col-md-offset-2 main"  align="center">
		<input type="text" style="display:none" id="eventOwner" name="eventOwner" value="${eventOwner}"></input>
		<form action="<%=path %>/datarole/roleup" id='formn' method="post">
			<input type="hidden" id="siteId" name="siteId" value="${id }">
			<input type="hidden" id="fieldName" name="fieldName">
			<input type="hidden" id="id" name="id">
			<input type="hidden" id="msg_id" name="fieldId">
			<input type="hidden" id="fieldCode" name="fieldCode">
        <div class="zTreeDemoBackground left" style="width:300px;height:360px;overflow-y:scroll;overflow-x:auto;">  
            <ul id="treeDemo" class="ztree">  
            </ul>  
        </div>  
		</form>
	</div>

</body>
</html>

