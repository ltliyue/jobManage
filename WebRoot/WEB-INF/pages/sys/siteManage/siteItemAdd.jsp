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
	<link href="<%=path%>/resources/css/new_version/site.css" rel="stylesheet">
	<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
	<link href="<%=path %>/resources/css/bootstrap-treeview.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=path %>/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<style>*{  margin:0; padding:0}</style>
<!-- Bootstrap core JavaScript
    ================================================== -->
	<script src="<%=path %>/resources/js/jquery.min.js"></script>
	<script src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap.min.js"></script>
	<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
	<script src="<%=path%>/resources/js/new_version/script.js"></script>
    <script src="<%=path%>/resources/js/new_version/site.js"></script>
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
			selectedMulti: false,
			fontCss: setFontCss_ztree
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
    }  
    /**
     * 收起树：只展开根节点下的一级节点
     * @param treeId
     */
    function close_ztree(treeId){
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        var nodes = treeObj.transformToArray(treeObj.getNodes());
        var nodeLength = nodes.length;
        for (var i = 0; i < nodeLength; i++) {
            if (nodes[i].id == '0') {
                //根节点：展开
                treeObj.expandNode(nodes[i], true, true, false);
            } else {
                //非根节点：收起
                treeObj.expandNode(nodes[i], false, true, false);
            }
        }
    }
    /**
     * 搜索树，高亮显示并展示【模糊匹配搜索条件的节点s】
     * @param treeId
     * @param searchConditionId 文本框的id
     */
    function search_ztree(treeId, searchConditionId){
        searchByFlag_ztree(treeId, searchConditionId, "");
    }
     
    /**
     * 搜索树，高亮显示并展示【模糊匹配搜索条件的节点s】
     * @param treeId
     * @param searchConditionId     搜索条件Id
     * @param flag                  需要高亮显示的节点标识
     */
    function searchByFlag_ztree(treeId, searchConditionId, flag){
        //<1>.搜索条件
        var searchCondition = $('#' + searchConditionId).val();
        //<2>.得到模糊匹配搜索条件的节点数组集合
        var highlightNodes = new Array();
        if (searchCondition != "") {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            highlightNodes = treeObj.getNodesByParamFuzzy("name", searchCondition, null);
        }
        //<3>.高亮显示并展示【指定节点s】
        highlightAndExpand_ztree(treeId, highlightNodes, flag);
    }
    /**
     * 高亮显示并展示【指定节点s】
     * @param treeId
     * @param highlightNodes 需要高亮显示的节点数组
     * @param flag           需要高亮显示的节点标识
     */
    function highlightAndExpand_ztree(treeId, highlightNodes, flag){
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
        //<1>. 先把全部节点更新为普通样式
        var treeNodes = treeObj.transformToArray(treeObj.getNodes());
        for (var i = 0; i < treeNodes.length; i++) {
            treeNodes[i].highlight = false;
            treeObj.updateNode(treeNodes[i]);
        }
        //<2>.收起树, 只展开根节点下的一级节点
        close_ztree(treeId);
        //<3>.把指定节点的样式更新为高亮显示，并展开
        if (highlightNodes != null) {
            for (var i = 0; i < highlightNodes.length; i++) {
                if (flag != null && flag != "") {
                    if (highlightNodes[i].flag == flag) {
                        //高亮显示节点，并展开
                        highlightNodes[i].highlight = true;
                        treeObj.updateNode(highlightNodes[i]);
                        //高亮显示节点的父节点的父节点....直到根节点，并展示
                        var parentNode = highlightNodes[i].getParentNode();
                        var parentNodes = getParentNodes_ztree(treeId, parentNode);
                        treeObj.expandNode(parentNodes, true, false, true);
                        treeObj.expandNode(parentNode, true, false, true);
                    }
                } else {
                    //高亮显示节点，并展开
                    highlightNodes[i].highlight = true;
                    treeObj.updateNode(highlightNodes[i]);
                    //高亮显示节点的父节点的父节点....直到根节点，并展示
                    var parentNode = highlightNodes[i].getParentNode();
                    var parentNodes = getParentNodes_ztree(treeId, parentNode);
                    treeObj.expandNode(parentNodes, true, false, true);
                    treeObj.expandNode(parentNode, true, false, true);
                }
            }
        }
    }
     
    /**
     * 递归得到指定节点的父节点的父节点....直到根节点
     */
    function getParentNodes_ztree(treeId, node){
        if (node != null) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var parentNode = node.getParentNode();
            return getParentNodes_ztree(treeId, parentNode);
        } else {
            return node;
        }
    }
     
    /**
     * 设置树节点字体样式
     */
    function setFontCss_ztree(treeId, treeNode) {
        if (treeNode.id == 0) {
            //根节点
            return {color:"#333", "font-weight":"bold"};
        } else if (treeNode.isParent == false){
            //叶子节点
            return (!!treeNode.highlight) ? {color:"#ff0000", "font-weight":"bold"} : {color:"#660099", "font-weight":"normal"};
        } else {
            //父节点
            return (!!treeNode.highlight) ? {color:"#ff0000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
        }
    }
    function createTree () {
        var zNodes;
        $.ajax({
            url: '<%=path %>/datasiteitem/allitem', //url  action是方法的名称
            type: 'POST',
            dataType: "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
            	
               zNodes = eval('('+data+')');
               
               $.fn.zTree.init($("#treeDemo"), setting, zNodes).expandAll(false);
               //id 查询  role内容
               var url = "<%=path %>/datasiteitem/itemq?id=${id}";
               $.post(url,function(data){
            	   json = eval('('+data+')');
          			var fieldName = json.fieldName;
          			var fieldId = json.fieldId;
          			var id = json.id;
          			$("#id").val(id);
          			if(fieldId!=null&&fieldId!=""){
          				var s = fieldId.split(";");
          				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
              			s.forEach(function(e){
              				if(e!=""){
              					 var n = zTree.getNodeByParam("id",e,null);
                     			n.checked=true;
                     			zTree.updateNode(n);
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
		var fieldCode = $("#fieldCode").val().split(";");
		var fieldName = $("#fieldName").val().split(";");
		var flag = true;
		var fieldCodeN=fieldCode.sort();  
		for(var i=0;i<fieldCode.length;i++){  
		if (fieldCodeN[i]==fieldCodeN[i+1]){  
		  alert("数据项代码存在重复，重复内容："+fieldCodeN[i]);
		  flag = false;
		}  
		}
		var fieldNameN=fieldName.sort();  
		for(var i=0;i<fieldName.length;i++){  
		if (fieldNameN[i]==fieldNameN[i+1]){  
		  alert("数据项名称存在重复，重复内容："+fieldNameN[i]);
		  flag = false;
		}  
		}
		var status = demo.check(false);
		if(flag){
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
	}
</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<div style="width:100%; margin:0 1%;" > 
	<!-- <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> -->

		<h1 class="page-header">数据项维护</h1>
		<form action="<%=path %>/datarole/roleup" id='formn' method="post">
		<input type="hidden" id="siteId" name="siteId" value="${id }">
			<div class="form-group">
				<label class="content_zjnrcqlabel" >站点名称</label> <input type="text"
					class="form-control" name='name' id="name" placeholder="站点名称" value="${name }" datatype="*" nullmsg="请输入站点名称！" errormsg=" ">
			</div>
			<hr>
			<h4>添加数据项</h4>
			<hr>
			<!-- <label for="exampleInputEmail1">数据项：</label><span id="msg_name"></span> -->
			<input type="hidden" id="fieldName" name="fieldName">
			<input type="hidden" id="id" name="id">
			<input type="hidden" id="msg_id" name="fieldId">
			<input type="hidden" id="fieldCode" name="fieldCode">
	<div class="padd" style="padding-bottom: 0px;width:400px">
        <div class="input-append row-fluid" style="margin-bottom: 0px;width:400px">
            <input id="search_condition" type="text" placeholder="请输入搜索条件" class="span8" style="font-size:12px"/>
            <button type="button" class="btn btn-info" onclick="search_ztree('treeDemo', 'search_condition')">搜索</button>
        </div>
    </div>
    <div class="content_wrap" style="width:400px;height:360px;float:left">  
        <div class="zTreeDemoBackground left" style="width:400px;height:360px;overflow-y:scroll;overflow-x:auto;">  
            <ul id="treeDemo" class="ztree">  
            </ul>  
        </div>  
    </div>
    <div style="width:800px; height:360px; overflow:scroll;">	
    	<table class="tree table table-hover table-bordered table-condensed" >    
		    <tr class='success'><th>数据项名称</th><th>数据项代码</th><th>数据项描述</th></tr>
		    <tbody id="tbody">
				
			</tbody>
		</table>
    </div>  
    	<!-- <div class="form-group">
				<label for="exampleInputEmail1">创建人</label> <input type="text"
					class="form-control" name='publisherId' id="publisherId" placeholder="创建人" datatype="*" nullmsg="请输入创建人！" errormsg=" ">
		</div> -->
		</form>
	<button type="button" class="btn btn-primary" onclick="submit()">提交</button>
	</div>

</body>
</html>
