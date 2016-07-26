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

<title>互联网数据采集平台</title>

<!-- Bootstrap core CSS -->
    <link href="<%=path%>/resources/css/new_version/bootstrap.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/css/new_version/site.css" rel="stylesheet">
	<link href="<%=path %>/resources/css/treegridCSS/jquery.treegrid.css" rel="stylesheet">
	<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
	<link href="<%=path %>/resources/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
	 <!-- Bootstrap core jss -->
	<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/script.js"></script>
    <script src="<%=path%>/resources/js/new_version/site.js"></script>
    <script src="<%=path%>/resources/js/bootstrap-filestyle.min.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.bootstrap3.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
	
	<script type="text/javascript" src="<%=path %>/resources/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery.ztree.excheck-3.5.js"></script>

</head>
<SCRIPT type="text/javascript">
var setting = {  
		treeId:"treeDemo",
        check: {  
        	chkboxType: {"Y" : "", "N" : ""},
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
        var siteId = "";
        $("#tbodyItem").empty();
        $("#tbody").empty();
        for (var i = 0; i < nodes.length; i++) {
           // msg += nodes[i].name+"--"+nodes[i].id+"--"+nodes[i].pId+"\n";
               siteId += nodes[i].id+";";
               var j = i+1;
               $("#tbodyItem").append("<tr align='left'><td>"
						 +nodes[i].name+"</td><td class='content_rwgl_pzurl' style='width:200px'>"
						 +nodes[i].url+"</td><td>"
						 +nodes[i].description+"</td></tr>");
               $("#tbody").append("<tr align='left'><td><input type='checkbox' name='chk_list_1' value='"+nodes[i].id+"'></td><td>"
						 +j+"</td><td><a class='content_xuqiumcan' onclick=\"OpenViewModel('"+nodes[i].id+"','"+nodes[i].name+"','"+nodes[i].url+"','"+nodes[i].type+"','"+nodes[i].description+"','"+nodes[i].publisherId+"','"+nodes[i].executionCycle+"','"+nodes[i].dueTime+"','"+nodes[i].otherDescription+"')\" data-target=\"#addModal\" data-toggle=\"modal\">"
            		   	 +nodes[i].name+"</a></td><td class='content_rwgl_pzurl' style='width:35%'>"
						 +nodes[i].url+"</td><td>"
						 +nodes[i].type+"</td><td>"
						 +"<button type='button' class='content_rwgl_pzanniu' data-target='#fieldModel' data-toggle='modal' onclick='createTreeN(\""
						 +nodes[i].id+"\")'>数据项维护</button></td><td>"
						 +"<button type='button' class='content_rwgl_pzanniu' data-target='#modifyCondition' data-toggle='modal' onclick='setDelivery(\""
						 +nodes[i].id+"\")'>设置</button></td></tr>");
        }
        /* $("#msg_name").text(msg_name);
        $("#fieldName").val(msg_name);
        $("#fieldCode").val(msg_code);*/
        $("#siteId").val(siteId);
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
            url: '<%=path %>/demandAnaly/listsite', //url  action是方法的名称
            type: 'POST',
            dataType: "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
            	
               zNodes = eval('('+data+')');
               
               $.fn.zTree.init($("#treeDemo"), setting, zNodes).expandAll(false);
               //id 查询  role内容
               var siteId = "${analyse.siteId }";
          			if(siteId!=null&&siteId!=""){
          				var s = siteId.split(";");
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
            },
            error: function (msg) {
                alert("失败");
            }
        });
        
    }
    ///页面加载后初始化zTree数据且默认展开所有节点  
    $(document).ready(function () {  
      createTree();  
      createTreeN("");
     
    });   
</SCRIPT>
<script type="text/javascript">
var i = 0;
var demo1;
var demo;
$(function(){
	demo=$("#formn").Validform({
		tiptype:3,
		showAllError:true
	});
	demo1=$("#form").Validform({
		tiptype:3,
		showAllError:true
	});
	querySiteType(0);
	$("#priorityS").val('${analyse.priority}');
});

function OpenAddModel(parentId){
	 $("#parentId").attr("value",parentId);
	 $("#myModalLabel").text("增加站点");
	 $("#div2").attr("style","display:none");
	 $("#div1").attr("style","display:block");
	 $("#button").attr("onclick","addNext()");
	 $("#id").attr("value","");
	 $("#name").attr("value","");
	 $("#url").attr("value","");
	 $("#siteDescription").attr("value","");
	 siteChange();
	}
function OpenViewModel(id,name,url,siteType,siteDescription,publisherId,executionCycle,dueTime,otherDescription){
	 var siteArr = siteType.split("--");
	 var siteType1 = siteArr[0];
	 var siteType2= siteArr[1];
	 $("#myModalLabel").text("站点信息");
	 $("#div1").attr("style","display:none");
	 $("#div2").attr("style","display:block");
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
	 var all_options2 = document.getElementById("siteType2").options;
	 for(i = 0;i<all_options2.length;i++){
		 if(all_options2[i].innerHTML == siteType2){
			 all_options2[i].selected = true;
		 }
	 }
	}
	
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

function setDelivery(siteId){
	$("#siteIdOne").attr("value",siteId);
	var demandId = "${analyse.demandId }";
	$("#stbody").empty();
	var url = "<%=path %>/demandAnaly/setDelivery?siteId="+siteId+"&demandId="+demandId;
	$.post(url,function(data){
		 json=eval('('+data+')');
		 $.each(json,function(k){
		 	$("#stbody").append("<tr align='left'><td><input type='checkbox' name='chk_list' value='"
					 +k+"' ></td><td>"
					 +"<select id='fieldChange_"+k+"' name='fieldList' class='form-control content_zjnrcqtext'></select></td><td>"
					 +"<input type='text' class='form-control' name='ConditionDes' id='ConditionDes' value='"+json[k].conditionDes+"'></input></td><td>"
					 +"<input type='text' class='form-control' name='ConditionVal' id='ConditionVal' value='"+json[k].conditionVal+"'></input></td><td></tr>");
		 	var siteId = $("#siteIdOne").val();
			var url = "<%=path %>/datasiteitem/itemq?id="+siteId;
			var id = "fieldChange_"+k;
		    $.post(url,function(data1){
		 	   json1 = eval('('+data1+')');
					var fieldName = json1.fieldName;
					var fieldId = json1.fieldId;
					var fieldCode = json1.fieldCode;
				 $("#"+id).empty();
				 if(fieldId!=null&&fieldId!=""){
					 var s = fieldId.split(";");
					 var m = fieldName.split(";");
					 if(fieldName.length!==0){
						 for(j=0;j<s.length-1;j++){
							 $("#"+id).append("<option value='"+s[j]+"'>"+m[j]+"</option>");
						 } 
					 }
				 }
			 });
		    //因为异步加载导致不能给下拉框赋值，此处延时1秒执行
		    setTimeout(function(){
		    	 var all_options1 = document.getElementById(id).options;
				 for(ii = 0;ii<all_options1.length;ii++){
					 if(all_options1[ii].innerHTML == json[k].fieldName){
						 all_options1[ii].selected = true;
					 }
				 }
		    },1200);
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

function addNext(){
	var siteType1 = $("#siteType1").find('option:selected').text();
	var siteType2 = $("#siteType2").find('option:selected').text();
	$("#siteType").attr("value",siteType1+"--"+siteType2);
	var status = demo1.check(false);
	if(status==true){
		 var url = "<%=path %>/datasiteitem/addnextrsc";
		 var data1 = $("#form").serialize();
			$.post(url,data1,function(data){
				if(data=='1'){
					alert('增加成功！');
					$('#addModal').modal('hide');
				}else if(data=='2'){
					alert('名称已经存在！');
				}else{
					alert('增加失败！');
				}
			});
	}
 }
 
function addOne(){
	i++;
	$("#stbody").append("<tr align='left'><td><input type='checkbox' name='chk_list' value='"
			 +i+"' ></td><td>"
			 +"<select id='fieldChange_"+i+"' name='fieldList' class='form-control content_zjnrcqtext'></select></td><td>"
			 +"<input type='text' class='form-control' name='ConditionDes' id='ConditionDes' value=''></input></td><td>"
			 +"<input type='text' class='form-control' name='ConditionVal' id='ConditionVal' value=''></input></td><td></tr>");
	var siteId = $("#siteIdOne").val();
	var url = "<%=path %>/datasiteitem/itemq?id="+siteId;
	var id = "fieldChange_"+i;
    $.post(url,function(data){
 	   json = eval('('+data+')');
			var fieldName = json.fieldName;
			var fieldId = json.fieldId;
			var fieldCode = json.fieldCode;
		 $("#"+id).empty();
		 if(fieldId!=null&&fieldId!=""){
			 var s = fieldId.split(";");
			 var m = fieldName.split(";");
			 if(fieldName.length!==0){
				 for(j=0;j<s.length-1;j++){
					 $("#"+id).append("<option value='"+s[j]+"'>"+m[j]+"</option>");
				 } 
			 }
		 }
	 });
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

function delSite(){
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	$("input[name='chk_list_1']:checkbox").each(function(){ 
        if($(this).attr("checked")){
			if($(this).val()!=""){
				 var n = zTree.getNodeByParam("id",$(this).val(),null);
      			n.checked=false;
      			zTree.updateNode(n);
			}
        }
    });
	GetCheckedAll();
}
 
 function save(){
	 var priority = $("#priorityS").find('option:selected').text();
	 $("#priority").attr("value",priority);
	 var status = demo.check(false);
		if(status==true){
	 		document.getElementById("formn").submit();
		}
 }
 function cancelWin(){
	 window.location.href="<%=path %>/demandAnaly/setdemand";
 }
 
 function itemManage(id,name){
	 var demandId = $("#demandId").val();
	 <%-- window.open("<%=path %>/datasiteitem/turntositeitem?id="+id+"&name="+name); --%>
	 window.location.href="<%=path %>/datasiteitem/turntoitemDemand?id="+id+"&name="+name+"&demandId="+demandId;
 }
 
 function submitField(){
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
		if(flag){
			var url = '<%=path %>/datasiteitem/itemupNew';
			var data1 = $("#form3").serialize();
			$.post(url,data1,function(data){
				if(data=="1"){
					alert("修改成功！");
				}else{
					alert("修改失败！");
				}
			});
		}
	}
 
 function submit(){
		var status = demo.check(false);
		var flag = true; 
		var siteId = $("#siteIdOne").val();
		var demandId = "${analyse.demandId }";
		var svalue = "";
		var stext = "";
		$("select[name=fieldList]").each(function(e){
			svalue = svalue + $(this).val()+";";
			stext = stext + $(this).find("option:selected").text()+";";
		});
		var fieldCode = svalue.split(";");
		var fieldCodeN=fieldCode.sort();  
		for(var i=0;i<fieldCode.length;i++){  
		if (fieldCodeN[i]==fieldCodeN[i+1]){  
		  alert("数据项不允许重复，请仔细查阅！");
		  flag = false;
		}  
		}
		/* $("#fieldId").attr("value",svalue);
		$("#fieldName").attr("value",stext); */
		if(flag){
			if(status==true){
				var url = "<%=path %>/demandAnaly/addfieldsub?siteId="+siteId+"&demandId="+demandId+"&fieldId="+svalue+"&fieldName="+stext;
				 var data1 = $("#formm").serialize();
					$.post(url,data1,function(data){
						if(data=='1'){
							alert('增加成功！');
							$('#modifyCondition').modal('hide');
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
<script type="text/javascript">
var settings = {  
		treeId:"treeDemo2",
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
			onCheck: GetCheckedAllN
		}   
    };  

    //过滤节点的机制 直接return node表示不做任何过滤  
    function filter(node) {  
        return node;  
    }  
    var zTree ;
    ///动态设置zTree的所有节点有checkbox  
    function DynamicUpdateNodeCheckN() {  
    	zTree= $.fn.zTree.getZTreeObj("treeDemo2");  
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
    function GetCheckedAllN() {
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo2");
        var nodes = treeObj.getCheckedNodes(true);
        var msg_name = "";
        var msg_id = "";
        var msg_code = "";
        $("#fieldTbody").empty();
        for (var i = 0; i < nodes.length; i++) {
           // msg += nodes[i].name+"--"+nodes[i].id+"--"+nodes[i].pId+"\n";
           if(nodes[i].flag=="2"){
        	   msg_name += nodes[i].name+";";
               msg_id +=nodes[i].id+";";
               msg_code +=nodes[i].code+";";
               $("#fieldTbody").append("<tr align='left'><td>"
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
    
    function createTreeN (singleSite) {
        var zNodes;
        $.ajax({
            url: '<%=path %>/datasiteitem/allitem', //url  action是方法的名称
            type: 'POST',
            dataType: "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
            	
               zNodes = eval('('+data+')');
               
               $.fn.zTree.init($("#treeDemo2"), settings, zNodes).expandAll(false);
               //id 查询  role内容
               if(singleSite.length>0||singleSite!=""){
               $("#singleSite").val(singleSite);
               var url = "<%=path %>/datasiteitem/itemq?id="+singleSite;
               $.post(url,function(data){
            	   json = eval('('+data+')');
          			var fieldName = json.fieldName;
          			var fieldId = json.fieldId;
          			var id = json.id;
          			$("#id").val(id);
          			if(fieldId!=null&&fieldId!=""){
          				var s = fieldId.split(";");
          				var zTree = $.fn.zTree.getZTreeObj("treeDemo2");
              			s.forEach(function(e){
              				if(e!=""){
              					var n = zTree.getNodeByParam("id",e,null);
                     			n.checked=true;
                     			zTree.updateNode(n);
              				}
                            
              			});
          			}
          			GetCheckedAllN();
          			});
               }
            },
            error: function (msg) {
                alert("失败");
            }
        });
        
    }


</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
  <!--内容开始-->
    <div  class="content_waikuang">
<div class="container-fluid search_select select-result" style="padding-left:0">
<h2 class="content_title">需求分析</h2>

<div class="container-fluid search_select select-result" style="padding-left:0; margin:0 auto">
		<div class="table-responsive">
		<form action="<%=path%>/demandAnaly/save" id='formn' method="POST" enctype="multipart/form-data">
			<input type="hidden" name="siteId" id="siteId">
			<input type="hidden" name="analyseId" id="analyseId" value="${analyse.id }">
			<input type="hidden" name="demandId" id="demandId" value="${analyse.demandId }">
			<input type="hidden" name="priority" id="priority">
			<div class="form-group">
				<label for="name">分析结果描述</label> 
				<textarea class="form-control content_zjnrcqtext" name="resultDescription" id="resultDescription" rows="4" placeholder="请输入需求分析结果描述" nullmsg="请输入需求分析结果描述" errormsg="" datatype="*" >${analyse.resultDescription }</textarea>
			</div>
			<div class="form-group" id="select_wenjian">
				<label for="demandDetail">附件</label>
				<input type = "file" class="filestyle" data-icon="false" name="anafilePath" id="anafilePath" multiple data-buttonText="选择文件">
				<!-- <h4><button type="button" class="btn btn-primary btn-sm" data-toggle="modal" id="uploadFileBtn" data-target="" onclick="uploadFile()" >浏  览</button> </h4> -->
			</div>
			<div class="form-group">
				<label style="clear:both" for="demandDetail">优先级</label>
				<select id="priorityS" class="form-control content_zjnrcqtext">
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="C">C</option>
					<option value="D">D</option>
				</select>
			</div>
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal" onclick="OpenAddModel(0)" id="addSite">新建站点</button>
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#insertModal" id="foundSite" >已有站点</button>
			<button type="button" class="btn btn-primary" onclick="delSite()">删除</button>
		</form>
		<table class="tree table table-hover table-bordered table-condensed">    
		    <tr class='success'><th>选择</th><th>序号</th><th>站点名称</th><th>URL</th><th>站点类型</th><th>数据项维护</th><th>交付条件设置</th></tr>
		    <tbody id="tbody">
		    
			</tbody>
		</table>
		<br>
		<div align="center">
			<button type="button" class="btn btn-primary" id="save" onclick="save()">保  存</button>
			<!-- <button type="button" class="btn btn-primary" id="ajaxpost">提  交</button> -->
			<button type="button" class="btn btn-primary" id="close" onclick="cancelWin()">取  消</button>
		</div>
	</div>
	</div>
	</div>
	</div>
	<!-- Modal -->
<div class="modal hide fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document" >
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
				<label for="exampleInputEmail1">网站名称</label> <input type="text"
					class="form-control content_zjnrcqtext" name='name' id="name" placeholder="网站名称" datatype="*" nullmsg="请输入网站名称！" errormsg=" ">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">URL</label> <input type="text"
					class="form-control  content_zjnrcqtext" name='url' id="url" placeholder="url" datatype="*" nullmsg="请输入url！" errormsg=" ">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">站点类型</label> 
				<select id="siteType1" class="form-control content_zjnrcqtext" onchange="siteChange()">
					<!-- <option value="0">---请选择---</option> -->
				</select>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">站点类型</label> 
				<select id="siteType2" class="form-control content_zjnrcqtext">
					<!-- <option value="0">---请选择---</option> -->
				</select>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">站点描述</label> <input type="text"
					class="form-control content_zjnrcqtext" name='siteDescription' id="siteDescription" placeholder="站点描述" datatype="*" nullmsg="请输入站点描述！" errormsg=" ">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">执行周期</label> <input type="text"
					class="form-control content_zjnrcqtext" name='executionCycle' id="executionCycle" placeholder="执行周期" datatype="*" nullmsg="请输入执行周期！" errormsg=" ">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">任务交付时间</label> <input type="text"
					class="form-control content_zjnrcqtext" name='dueTime' id="dueTime" placeholder="任务交付时间" datatype="*" nullmsg="请输入任务交付时间！" errormsg=" ">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">任务其他描述</label> <input type="text"
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
<div class="modal hide fade" style="width:1050px;left:32%" id="insertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width:1050px;left:32%" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">站点选择</h4>
      </div>
      <form id="form2" method="POST">
      <div class="modal-body">
	    <div class="padd" style="padding-bottom: 0px;width:350px">
	        <div class="input-append row-fluid" style="margin-bottom: 0px;width:350px">
	            <input id="search_condition" type="text" placeholder="请输入搜索条件" class="span8" style="font-size:12px"/>
	            <button type="button" class="btn btn-info" onclick="search_ztree('treeDemo', 'search_condition')">搜索</button>
	        </div>
	    </div>
      	<div class="content_wrap" style="width:300px;height:360px;float:left">  
        <div class="zTreeDemoBackground left" style="width:300px;height:360px;overflow-y:scroll;overflow-x:auto;">  
            <ul id="treeDemo" class="ztree">  
            </ul>  
        </div>  
    </div>
    <div style="width:700px; height:360px; overflow:scroll;">	
    	<table class="tree table table-hover table-bordered table-condensed" >    
		    <tr class='success'><th width='100px'>站点名称</th><th width='300px'>URL</th><th width='250px'>站点描述</th></tr>
		    <tbody id="tbodyItem">
				
			</tbody>
		</table>
    </div>  
      </div>
      </form>
      <!-- <div id="div1" class="modal-footer">
      	<button type="button" class="btn btn-primary" id="button" onclick="addNext()">增 加</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
      </div> -->
    </div>
  </div>
</div>

<div class="modal hide fade" style="width:1050px;left:32%" id="fieldModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width:1050px;left:32%" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">数据项维护</h4>
      </div>
      <form id="form3" method="POST">
      <input type="hidden" id="fieldName" name="fieldName">
	  <input type="hidden" id="msg_id" name="fieldId">
	  <input type="hidden" id="singleSite" name="singleSite">
	  <input type="hidden" id="fieldCode" name="fieldCode">
	<div class="modal-body">
    <div class="padd" style="padding-bottom: 0px;width:300px">
        <div class="input-append row-fluid" style="margin-bottom: 0px;width:300px">
            <input id="search_condition2" type="text" placeholder="请输入搜索条件" class="span8" style="font-size:12px"/>
            <button type="button" class="btn btn-info" onclick="search_ztree('treeDemo2', 'search_condition2')">搜索</button>
        </div>
    </div>
    <div class="content_wrap" style="width:300px;height:360px;float:left">  
        <div class="zTreeDemoBackground left" style="width:300px;height:360px;overflow-y:scroll;overflow-x:auto;">  
            <ul id="treeDemo2" class="ztree">  
            </ul>  
        </div>  
    </div>
    <div style="width:700px; height:360px; overflow:scroll;">	
    	<table class="tree table table-hover table-bordered table-condensed" >    
		    <tr class='success'><th>数据项名称</th><th>数据项代码</th><th>数据项描述</th></tr>
		    <tbody id="fieldTbody">
				
			</tbody>
		</table>
    </div>
    </div>
    </form>
      <!-- <div id="div1" class="modal-footer">
      	<button type="button" class="btn btn-primary" id="button" onclick="addNext()">增 加</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
      </div> -->
      <div id="div1" class="modal-footer">
      	<button type="button" class="btn btn-primary" onclick="submitField()">提交</button>
      </div>
    </div>
  </div>
</div>

<div class="modal hide fade" id="modifyCondition" tabindex="-1" role="dialog" style="width:800px;" aria-labelledby="myModalLabel">
  <div class="modal-dialog" style="width:800px;" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">交付条件设置</h4>
      </div>
      <form id="formm" method="POST">
      <input type="hidden" name="siteIdOne" id="siteIdOne" value="">
      <!-- <input type="hidden" name="fieldId" id="fieldId" value="">
      <input type="hidden" name="fieldName" id="fieldName" value=""> -->
      <div class="modal-body">
      <table class="table table-striped"><tr>
      	<th><input type="checkbox" name="chk_all" id="chk_all" onclick="SelectAll()" /></th>
      	<th>数据项</th>
        <th>条件描述</th>
        <th>条件值</th>
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
    <!-- Bootstrap core JavaScript
    ================================================== -->
</body>
</html>