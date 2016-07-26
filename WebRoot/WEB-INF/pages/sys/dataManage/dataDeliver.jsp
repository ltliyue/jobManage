<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fs" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
	<link href="<%=path %>/resources/css/dashboard.css" rel="stylesheet">
	<link href="<%=path %>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<link href="<%=path %>/resources/css/bootstrap-select.css" rel="stylesheet">
	<link rel="stylesheet" href="<%=path %>/resources/css/zTreeStyle/zTreeStyle.css" type="text/css">
<!-- Bootstrap core JavaScript
    ================================================== -->
    <script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    
    <script src="<%=path%>/resources/js/new_version/site.js"></script>
	<script src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.js"></script>
	<script src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.zh-CN.js"></script>	
	<script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/bootstrap3.3.5/bootstrap-select.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery-ui-min-1.9.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/Chinese-characters-to-Pinyin/py.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/formats.js"></script>
	<script src="<%=path%>/resources/js/new_version/script.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery.ztree.excheck-3.5.js"></script>
</head>
<script type="text/javascript">
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
			onClick: zTreeOnClick,
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
    
    function zTreeOnClick(event, treeId, treeNode) {
    	$("#where").val("");
    	var flag = treeNode.flag;
    	if(flag=='1'){
	    	var bmm = treeNode.name;
	    	$("#bmoc").val(bmm);
	    	hivec('s');
	    	var url = "<%=path%>/datasource/listd?id=${taskid}&bm="+bmm+"&yeshu=1"+"&where=";
	    	$.post(url,function(data){
	    		json = eval('('+data+')');
	    		$("#th").html(json.th);
	    		$("#td").html(json.td);
	    		$("#dq").html(json.yeshu);
	    		$("#zys").html(json.zys);
	    		$("#obm").val(json.bm);
	    		$("#upsele").html(json.bm);
	    		$("#delsele").html(json.bm);
	    		$("#tjsel").html(json.option);
	    		$("#tjsel").selectpicker('refresh');
	    		$("#tjsel2").html(json.option);
	    		$("#tjsel2").selectpicker('refresh');
	    		$("#tjsel2_").html(json.option);
	    		$("#tjsel2_").append('<option value="task_url">任务URL</option>');
	    		$("#tjsel2_").selectpicker('refresh');
	    		
	    		$("#tjsel3").html(json.option);
	    		$("#tjsel3").append('<option value="task_url">任务URL</option>');
	    		$("#tjsel3").selectpicker('refresh');
	    		$("#wbiao").text("where cf['task_id'] = ${taskid} and cf['page_extract_rule_id'] = '"+json.ruleid+"' ");
	    		$("#qcheck").html(json.check);
	    		$("#obiao").val(json.bm);
	    		$("#ruleid").val(json.ruleid);
	    		$("#lfy").attr("class","disabled");
	    		if(parseInt(json.zys)<11){
	    			$("#nfy").attr("class","disabled");
	    		}else{
	    			$("#nfy").removeAttr("class");
	    		}
	    	});
    	}
    };
    //获取所有选中节点的值
    function GetCheckedAll() {
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = treeObj.getCheckedNodes(true);
        var table_name = "";
        var tableName = "";
        var demandId = "";
        for (var i = 0; i < nodes.length; i++) {
        	var parentNode = treeObj.getNodesByFilter(function (node) { return node.level == 0 }, true);
        	table_name += parentNode.name+":"+nodes[i].name+";";
        	tableName += parentNode.id+":"+nodes[i].name+";";
        	demandId += parentNode.id+";";
        }
        $("#table_name").val(table_name);
        $("#tableName").val(tableName);
        $("#demandId").val(demandId);
    }  
    
    function hivec(v){
    	if(v=='s'){
    		$("#s").attr("disabled","disabled");
    		var url = "<%=path%>/datasource/hiveshows?bm="+$("#bmoc").val();
    		$.post(url,function(data){
    			json = eval('(' + data + ')');
    			var y = json.ybiao;
    			var p = json.pbiao;
    			var b1 = "hive映射表：";
    			var b2 = "hdfs表：";
    			
    			if(y==null){
    				y = "为空>><a href='#' onclick='addy()'>添加</a>";
    				$("#shbiao").html(b1+y);
    			}else{
    				var arr = y.split(";");
    				var op = "";
    				for(var i=0;i<arr.length;i++)
    				{
    					op +="<option>"+arr[i]+"</option>";
    				}
    				$("#hbiao").html(op);
    				$("#hbiao").selectpicker('refresh');
    				$("#ybiao").attr("disabled","disabled");
    				$("#y").attr("disabled","disabled");
    				if(p==null){
    					p="空>><a href='#' onclick='addp()'>添加</a>";
    				}else{
    					p="<textarea readonly class='form-control' id='hive_table' style='width: 95%' rows='3'>"+p+"</textarea><label><a href='#' onclick='clear1()'>清空</a></label>";
    				}
    				
    				$("#shbiao").html(b1+y+"<br>"+b2+p);
    			}
    			$("#s").removeAttr("disabled");
    			
    		});
    	}
    	if(v=='y'){
    		$("#y").attr("disabled","disabled");
    		var url = "<%=path%>/datasource/hivehbase?id=${taskid}&hbtable="+$("#ybiao").val();
    		$.post(url,function(data){
    			json = eval('(' + data + ')');
    			alert(json);
    			$("#h").removeAttr("disabled");
    			hivec('s');
    		});
    	}
    	if(v=='h'){
    		$("#h").attr("disabled","disabled");
    		var url = "<%=path%>/datasource/hdfs?n="+$("#hbiao").val()+"&w="+$("#wbiao").val()+"&bm="+$("#bmoc").val();
    		$.post(url,function(data){
    			json = eval('(' + data + ')');
    			alert(json);
    			$("#h").removeAttr("disabled");
    			hivec('s');
    		});
    	}
    	if(v=='d'){
    		var s = $("#dbiao").val();
    		if(s!=null&&s!=""){
    			//如果通过，执行自己的方法
    			$("#d").attr("disabled","disabled");
    			var url = "<%=path%>/datasource/sqoop?n="+$("#dbiao").val()+"&o="+$("#obiao").val()+"&id=${taskid}&ruleid="+$("#ruleid").val();
    			$.post(url,function(data){
    				json = eval('(' + data + ')');
    				$("#d").removeAttr("disabled");
    				alert(json);
    			});
    		}else{
    			alert("hdfs表不能为空!");
    		}
    		
    	}
    	if(v=='p'){
    		var url = "<%=path%>/datasource/progress?id=${taskid}";
    		$.post(url,function(data){
    			json = eval('(' + data + ')');
    			$("#change").attr("hidden","hidden");
    			$("#change1").removeAttr("hidden");
    			$("#jindu").html(json);
    		});
    	}
    	if(v=='r'){
    		$("#change1").attr("hidden","hidden");
    		$("#change").removeAttr("hidden");
    	}
    	if(v=='f'){
    		var url = "<%=path%>/datasource/progress?id=${taskid}";
    		$.post(url,function(data){
    			json = eval('(' + data + ')');
    			$("#jindu").html(json);
    		});
    	}
    }
    
    function lfy(){
    	if($("#lfy").attr("class")!="disabled"){
    		var url = "<%=path%>/datasource/listd?id=${taskid}&bm="+$("#obm").val()+"&yeshu="+(parseInt($("#dq").html())-1);
    		var where = $("#where").val();
    		var datas = {"where":where};
    		$.post(url,datas,function(data){
    			json = eval('('+data+')');
    			$("#th").html(json.th);
    			$("#td").html(json.td);
    			$("#dq").html(json.yeshu);
    			$("#zys").html(json.zys);
    			$("#obm").val(json.bm);
    			
    			if(parseInt(json.zys)>parseInt($("#dq").html())*10){
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
    		
    		var url = "<%=path%>/datasource/listd?id=${taskid}&bm="+$("#obm").val()+"&yeshu="+(parseInt($("#dq").html())+1);
    		var where = $("#where").val();
    		var datas = {"where":where};
    		$.post(url,datas,function(data){
    			json = eval('('+data+')');
    			$("#th").html(json.th);
    			$("#td").html(json.td);
    			$("#dq").html(json.yeshu);
    			$("#zys").html(json.zys);
    			$("#obm").val(json.bm);
    			$("#lfy").removeAttr("class");
    			if(parseInt(json.zys)<=parseInt($("#dq").html())*10){
    				$("#nfy").attr("class","disabled");
    			}
    		});
    	}
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
            url: '<%=path %>/datadeliver/datalist', //url  action是方法的名称
            type: 'POST',
            dataType: "text", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            ContentType: "application/json; charset=utf-8",
            success: function (data) {
            	
               zNodes = eval('('+data+')');
               
               $.fn.zTree.init($("#treeDemo"), setting, zNodes).expandAll(false);
               //id 查询  role内容
               <%-- var url = "<%=path %>/datadeliver/datalist?id=${id}";
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
          			}); --%>
            },
            error: function (msg) {
                alert("失败");
            }
        });
        
    }
    
    function saveData(){
    	var table_name = $("#table_name").val();
    	var tableName = $("#tableName").val();
    	var demandId = $("#demandId").val();
    	if(tableName.length>0){
	    	if(confirm("确定将选中表  "+table_name+"交付吗？")){
	    		var url = '<%=path %>/datadeliver/savedata?tableName='+tableName+'&demandId='+demandId;
				$.post(url,function(data){
					if(data=="1"){
						alert("交付成功！");
					}else{
						alert("交付失败！");
					}
				});
	    	}
    	}else{
    		alert("还没有选择需要交付的表！");
    	}
    }
    
    ///页面加载后初始化zTree数据且默认展开所有节点  
    $(document).ready(function () {  
      createTree();  
    });
</script>
<script type="text/javascript">
        	$(function () {
  $('[data-toggle="tooltip"]').tooltip();
  var width=$(window).width()*1.8;
  $('.content_rwgl').css('cssText','max-width:'+width+'px !important;width:'+width+'px !important');
})
</script>
<body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
 <div  class="content_waikuang">
          <div class="container-fluid search_select select-result" style="padding-left:0;">
          		<h2>${title}</h2>
<!-- <dl>
				   <dt style="line-height: 27px">已选表：</dt>
				<dd class="select-no"></dd>
			    </dl> -->
			<div  class="renwu_caozuo" style="margin-right:50px">
				<!-- <a href="#" class='renwu_select' data-toggle="modal"    data-target="#modifyInstance">创建表
				</a>
				<a href="#" class='renwu_select' data-toggle="modal" id="ddata"  data-target="#modifyInstance1">sqoop导入
				</a> -->
				<a href="#" class='renwu_select' onclick="saveData()">交付
				</a>
			</div>
          </div>
<div class="row" style="clear:both">
	<div class="span3 ">
		<div class="padd" style="padding-bottom: 0px;width:300px">
	        <div class="input-append row-fluid" style="margin-bottom: 0px;width:300px">
	            <input id="search_condition" type="text" placeholder="请输入搜索条件" class="span8" style="font-size:12px"/>
	            <button type="button" class="btn btn-info" onclick="search_ztree('treeDemo', 'search_condition')">搜索</button>
	        </div>
	    </div>
		<div class="content_wrap" style="width:300px;height:500px;float:left">
            <div class="zTreeDemoBackground left" style="width:300px;height:500px;overflow-y:scroll;overflow-x:auto;">  
	            <ul id="treeDemo" class="ztree">  
	            
	            </ul>  
        	</div>		
		</div>
		</div>
<div class="span9" style="width:73%;height:500px;overflow-y:scroll;overflow-x:scroll;">
					
  	<input type="hidden" id="obm">
    <input type="hidden" id="where">
	<input type="hidden" id="ruleid">
	<input type="hidden" id="table_name">
	<input type="hidden" id="tableName">
	<input type="hidden" id="demandId">
    <table class="table  table-striped content_rwgl">
      <thead>
        <tr id="th" class="content_rwglmc">
        </tr>
      </thead>
      <tbody id="td">
       
      </tbody>
    </table>
    <form action="" id="exportF" method="post">   
	 <input type="hidden" id="we" name="we">
	 <input type="hidden" id="bmoc">
	</form> 
  	 <nav align="center">
  		<ul class="pager">
  		
    <li id="lfy" class="disabled">
      <a href="#" aria-label="Previous "  onclick="lfy()"><span aria-hidden="true">&larr;上一页</span></a>
    </li>
   	
    <li id="nfy" class="disabled">
     <a  href="#" aria-label="Next" style="margin-left:  20px" onclick="nfy()"><span aria-hidden="false">下一页&rarr;</span> </a>
    </li>
     <span style="margin-left: 20px" >当前页:<span id="dq"></span></span>
     <span  style="margin-left: 20px">总数量:<span id="zys"></span></span>
  </ul>
</nav>
  </div>
  </div>
  </div>
      <!-- Modal -->
<div class="modal hide fade" id="modifyInstance" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">增加表名</h4>
      </div>
      <form id="form" method="POST">
    
      <input type="hidden" name="flag" id="flag">
       <div class="modal-body">
			<div class="form-group">
			  <label class="content_zjnrcqlabel">选择一种规则</label>
      <select class="form-control content_zjnrcqselect " id="ruleselect" onchange="rulechange()" datatype="*" nullmsg="请选择规则！" errormsg="">
	</select>
			</div>
			</div>
      <div class="modal-body">
			<div class="form-group">
			
				<label for="exampleInputEmail1">表名称:</label> <span id="bm" >${bm }</span><input type="text"
					class="form-control" id="cname" value="" oninput="change()" datatype="/^[a-zA-Z0-9_]{1,20}$/" nullmsg="请输入名称！" errormsg="表名称英文或数字！" placeholder="表名">
					
			</div>
      </div>
      </form>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="submit()">提交</button>
      </div>
    </div>
  </div>
</div>

      <!-- Modal 导入数据-->
<div class=" modal hide fade" style="width: 650px" id="modifyInstance1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" >
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel1">导入数据</h4>
      </div>
      <div id="change" class="modal-body">
      <div class="modal-header">
			<div class="form-group">
				<label for="exampleInputEmail1">1.查看hive表:</label>
				<div><span id="shbiao"></span></div>
				
			</div>
      </div>
      <div class="modal-header" hidden="hidden">
			<div class="form-group" >
				<label for="exampleInputEmail1">2.生成hive映射表:</label>
				<span><input type="text" id="ybiao" value="global_extract_content" placeholder="请输入要映射的hbase表名" disabled="disabled"></span>
				<button  class="btn btn-default btn-sm"  onclick="hivec('y')" id="y" disabled="disabled">点击映射</button >
			</div>
      </div>
      <div class="modal-header" >
			<div class="form-group">
				<label for="exampleInputEmail1">2.新增hdfs表:</label>
				<div>hive映射表：<select id="hbiao" class="selectpicker" data-style="btn-info"></select></div>
				<div>请输入sql语句的where条件：<label style="width: 600px" >（常用条件：where cf['task_id']=113 and unix_timestamp(cf['extract_time'],'yyyyMMdd')><br>unix_timestamp('20160101','yyyyMMdd')；通用字段为：cf['extract_time']（抽取时间）、cf['instance_id']（实例id）、cf['task_id']（任务id））</label>
				<a href="#" data-toggle="modal"    data-target="#modifyInstance2" onclick="seleinstance()">选择实例ID</a>
				<textarea style="width: 95%" rows='3' id="wbiao"  placeholder="">where cf['task_id'] = ${taskid } and cf['page_extract_rule_id'] = </textarea></div>
				<button class="btn btn-small" onclick="hivec('h')"  id="h">点击生成</button >
			</div>
      </div>
      <div class="modal-header">
			<div class="form-group">
				<label for="exampleInputEmail1">3.导入oralce表:</label>
				<span><input type="text" id="dbiao" value="" placeholder="请输入hdfs表名" datatype="s1-2" nullmsg="请输入hdfs表！" errormsg="昵称至少4个字符,最多18个字符！" ><input type="text" id="obiao" disabled="disabled" value="" placeholder="请输入oracle表名" ></span>
				<button class="btn btn-small" onclick="hivec('d')" id="d" style="margin-bottom: 4px">点击导入</button >
				<br><a href="#" onclick="hivec('p')">>>查看导入进度</a>
			</div>
      </div>
      </div>
      <div id="change1" hidden="hidden">
	      <div class="modal-header">
	      <button  class="btn btn-default btn-sm" onclick="hivec('r')" id="d" >返回</button >&nbsp;&nbsp;&nbsp;&nbsp;
	      <button  class="btn btn-success btn-sm" onclick="hivec('f')" id="d" >刷新</button >
	      </div>
      		<div class="modal-header">
      		 <textarea style="width: 95%" rows="20" id="jindu"></textarea>
      		 </div>
      </div>
    </div>
  </div>
</div>
      <!-- Modal 筛选数据-->
<div class="modal hide fade" id="modifyInstance2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div  class="modal-body">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel2">实例ID列表</h4>
      </div>
      <form action="" method="post">
      <div class="" style="padding-left:0;margin:0 auto">
          <div class="table-responsive">
            <table class="table  table-striped">
              <thead>
                <tr>
               	  <th></th>
                  <th>实例ID</th>
                  <th>操作时间</th>
                  <th>状态</th>
                </tr>
              </thead>
				<tbody id="tbody2">
				</tbody>
				
            </table>
            </div>
            </div>
      </form>
       </div>
	<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="cx()">提交</button>
      </div>		
 
</div>
</div>
  <!-- Modal 数据去重-->
<div class="modal hide fade" id="modifyInstance3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel3">数据去重</h4>
      </div>
      <form class="form-inline" action="" method="post">
    	
      <div class="modal-header" >
      <span>请选择要去重的项:</span><br>
			<div class="form-group" id="qcheck">
			</div>
      </div>
      </form>
	<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="cqcheck()">提交</button>
      </div>		
  </div>
</div>
</div>
 <!-- Modal 批量修改-->
<div class="modal hide fade" id="modifyInstance4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel4">批量修改</h4>
      </div>
      <form class="form-inline" action="" method="post">
    	
      <div class="modal-body" >
      <div class="form-group" >
				update <span id="upsele"></span> set <br><select class="selectpicker" data-style="" data-hide-disabled="true"  id="tjsel2">
				</select>=<input class="form-control" id="xghz"  type="text" value="" placeholder="请输入要修改的值">
				
			</div>
			
			<div class="form-group" id="">
				<br>
				  自定义增加修改条件： <select class="selectpicker" data-style="" data-hide-disabled="true"  id="tjsel2_">
				</select>
				<a href="javascript:void(0)" onclick="selectup()"><i class="icon-plus"></i>
				</a>
			</div><br>
		
			<div class="form-group" id="zdydiv1">
				
			</div>
      </div>
      </form>
	<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="plup()">提交</button>
      </div>		
  </div>
</div>
</div>
<!-- Modal 批量删除-->
<div class="modal hide fade" id="modifyInstance5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-body">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel5">批量删除</h4>
      </div>
      <div  class="modal-header">
    	
     <div class="form-group" >
				delete from <span id="delsele"></span> where 
			</div>
			
			<div class="form-group" id="">
				<br>
				  自定义增加删除条件： <select class="selectpicker" data-style="" data-hide-disabled="true"  id="tjsel3">
				</select>
				<a href="javascript:void(0)" onclick="selectdel()"><i class="icon-plus"></i>
				</a>
			</div><br>
			<div class="form-group" id="zdydiv2">
				
	</div>
      </div>
      </div>
	<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="pldel()">提交</button>
      </div>		
  
</div>
</div>

<!-- Modal sql窗口-->
<div class="modal hide fade" id="modifyInstance6" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel5">SQL窗口</h4>
      </div>
    	
      <div class="modal-header">
      <input type="text" class="form-control" id="bmzd" style="width: 50%;float: left;" placeholder="表名">&nbsp;&nbsp;<button class="btn btn-success btn-sm" onclick="cxbzd()">查询表字段</button><span id="ablecolumn"></span>
      <hr>
      		 <textarea style="width: 95%" rows="10" id="sqlid" name="sql" placeholder="可以执行update、alter语句，不能执行select"></textarea>
      		 </div>
	<div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关 闭</button>
        <button type="button" class="btn btn-primary"  id="button" onclick="zdysql()">提交</button>
      </div>		
  </div>
</div>
</div>
</body>
</html>