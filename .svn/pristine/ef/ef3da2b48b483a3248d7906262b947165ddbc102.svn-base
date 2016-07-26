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
<link href="/crawlManage/resources/css/new_version/bootstrap.min.css"
	rel="stylesheet">
<link href="/crawlManage/resources/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="/crawlManage/resources/css/new_version/site.css"
	rel="stylesheet">
<link href="/crawlManage/resources/validform/css/style.css"
	rel="stylesheet">
<link href="/crawlManage/resources/css/dashboard.css" rel="stylesheet">
<link href="/crawlManage/resources/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link href="/crawlManage/resources/css/bootstrap-select.css"
	rel="stylesheet">
	
<!-- Bootstrap core JavaScript
    ================================================== -->
<script src="/crawlManage/resources/js/new_version/jquery.min.js"></script>
<script src="/crawlManage/resources/js/new_version/bootstrap.min.js"></script>

<script src="/crawlManage/resources/js/new_version/site.js"></script>
<script
	src="/crawlManage/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.js"></script>
<script
	src="/crawlManage/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="/crawlManage/resources/validform/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript"
	src="/crawlManage/resources/js/bootstrap3.3.5/bootstrap-select.js"></script>
<script type="text/javascript"
	src="/crawlManage/resources/js/jquery-ui-min-1.9.js"></script>
<script src="<%=path%>/resources/js/bootstrap-filestyle.js"></script>

<script type="text/javascript"
	src="/crawlManage/resources/js/Chinese-characters-to-Pinyin/py.js"></script>
<script src="/crawlManage/resources/js/new_version/script.js"></script>

</head>
<script type="text/javascript">
       
      <!--全局同步提交 -->
	  $.ajaxSetup({ 
	     async: false 
	  }); 
	  
	var demo;
	//表单验证
	$(function() {
		demo = $("#formn").Validform({
			tiptype : 3,
			showAllError : true
		}

		);
		$("#ajaxpost").click(function() {
			var status = demo.check(false);
			if (status == true) {
				submit();
			}
		});
		
		$("#return").click(function() {
			if("${modal}"=="1"){
				window.location.href = "${webRoot}/parseRuleTemp/parseRuleManageModal?taskId="
				+ $("#taskId").val();
			}else{
				window.location.href = "${webRoot}/parseRule/setParseRule";
			}
		});

		$("#testurl").click(function() {
		//如果通过，执行自己的方法
			var ischecks = $("input[name='check']:checked").length;
			var url = '${webRoot}/parseRule/testParseRule';
		
			if ($("#urlWithLoopCheck").prop("checked")) {
				$("#urlWithLoop").val("1");
			} else {
				$("#urlWithLoop").val("0");
			}
			//把midMap进行list化 
			var tableObj = document.getElementById("midMapTable");
	    	for (var i = 1; i < tableObj.rows.length; i++) {  //遍历Table的所有Row
	       		//修改其id，name,property，,使得该页面能够映射为ParseRuleMidMap
	        	var rowElement = tableObj.rows[i];
	        	rowElement.children[0].setAttribute("name","midMaps["+(i-1)+"].id");
	        	rowElement.cells[0].firstChild.setAttribute("name","midMaps["+(i-1)+"].groupId");
	        	rowElement.cells[1].firstChild.setAttribute("name","midMaps["+(i-1)+"].varName");
	        	rowElement.cells[2].firstChild.setAttribute("name","midMaps["+(i-1)+"].datatype");
	    	}
	    	
			var formData = new FormData($("#formn")[0]);
			if($("#loopParseRuleForMultiDiv").attr('style')==null || $("#loopParseRuleForMultiDiv").attr('style')==""){
				var tableObj = document.getElementById("loopParseRuleTBody");
				for (var i = 0; i < tableObj.rows.length; i++) { //遍历Table的所有Row
					var rowElement = tableObj.rows[i];
					for(var j=1; j<rowElement.cells.length-1;j++){
						if(rowElement.cells[j].firstChild.id!=null && rowElement.cells[j].firstChild.id!=""){
							formData.append("loopVarRules",
								rowElement.cells[0].firstChild.value+":"+
								rowElement.cells[j].firstChild.id+":"+rowElement.cells[j].firstChild.value);
						}else{
							if(rowElement.cells[0].firstChild.value==null || rowElement.cells[0].firstChild.value==""){
								formData.append("loopVarRules",
										i+":"+
										""+":"+rowElement.cells[j].firstChild.value);
							}else{
								formData.append("loopVarRules",
									rowElement.cells[0].firstChild.value+":"+
									""+":"+rowElement.cells[j].firstChild.value);
							}
						}
					}
				}
			}
			
			//如果是单个循环变量
			
			if($("#loopParseRuleForOneDiv").attr('style')==null || $("#loopParseRuleForOneDiv").attr('style')==""){//显示
				var mvalue=$("#loopParseRuleForOneId").val()+":"+$("#loopVarRuleForOneId").val()+":";
				if($("#loopParseRuleForOneId").val()==null || $("#loopParseRuleForOneId").val()==""){
					mvalue="0:"+$("#loopVarRuleForOneId").val()+":";
				}
				if($("#loopVarType").val()=='0'){//数字
					mvalue=mvalue+$("#loopStart").val()+"|"+$("#loopEnd").val()+"|"+$("#loopStep").val()+"|"+$("#loopFormat").val();
		    	}else{
		    		mvalue=mvalue+$("#loopStrList").val()+"|"+$("#loopVarStrEncode").val();
		    	}
				formData.append("loopVarRules",mvalue);
			}
	    	
	    	$("#srcUrl").val($("#testUrl").val());
	     	$.ajax({
	          url: '${webRoot}/parseRuleTemp/testParseRule' ,
	          type: 'POST',
	          data: formData,
	          async: false,
	          cache: false,
	          contentType: false,
	          processData: false,
	          success: function (data) {
	        	  var json = eval("(" + data + ")");
					$("#extractUrlSize").val(json.extractUrlSize);
					$("#log").val(json.log);
					$("#extractUrls").val(
							json.extractUrls.toString().replace(/,(?=http)/g,
									"\r\n"));
					$("#testUrlHtml").val(json.testUrlHtml);
	          },
	          error: function (returndata) {
	              alert(returndata);
	          }
	     	});
		});
	});
	
	
	$(function() {
		//$(".registerform").Validform();  //就这一行代码！;

		$("#formn")
				.Validform(
						{
							datatype : {
								"urlpattern" : function(gets, obj, curform,
										regxp) {
									/*参数gets是获取到的表单元素值，
									  obj为当前表单元素，
									  curform为当前验证的表单，
									  regxp为内置的一些正则表达式的引用。([+\-]https?:\/\/[^\r\n]+))*/

									var reg2 = /^([+\-]https?:\/\/[^\s]+)(\s+([+\-]https?:\/\/[^\s]+))*$/, pattern = curform
											.find("#urlPattern");

									var s = reg2.exec(gets);
									//alert(gets+"\r\n"+reg2);
									//alert("匹配结果；"+s);

									if (reg2.test(gets)) {
										return true;
									}

									return false;
								}
							},
							ajaxPost : true
						});

	})
	
	$(document).ready(function() {
		
		var url = '${webRoot}/parseRuleTemp/getParseRule?id=${id}';
		$.post(url, function(data) {
			json = eval('(' + data + ')');
			$("#ruleId").val(json.id);
			$("#taskId").val(json.taskId);
			$("#name").val(json.name);
			$("#urlPattern").val(json.urlPattern);
			$("#extractType").val(json.extractType);
			$("#regex").val(json.regex);
			$("#regexGroupId").val(json.regexGroupId);
			$("#xpath").val(json.xpath);
			$("#loopVar").val(json.loopVar);
			$("#urlType").val(json.urlType);
			$("#testUrl").val(json.testUrl);
			$("#downloadType").val(json.downloadType);
			$("#downloadTool").val(json.downloadTool);
			$("#scopeRegex").val(json.scopeRegex);
			$("#jarPath").val(json.jarPath);
			$("#className").val(json.className);

			if (json.enabled == 1) {
				$("#check").attr("checked", "checked");
				$("#enabled").attr("value", "1");
			} else {
				$("#check").removeAttr("checked");
				$("#enabled").attr("value", "0");
			}

			choseExtractType(json.extractType);
			
			if (json.urlWithLoop == 1) {
				$("#urlWithLoopCheck").attr("checked", "checked");
				$("#urlWithLoop").attr("value", "1");
			} else {
				$("#urlWithLoopCheck").removeAttr("checked");
				$("#urlWithLoop").attr("value", "0");
			}
			loop_generate(json.urlWithLoop);

		});
		
		var url = '${webRoot}/parseRuleMidMapTemp/getMidMapByRule?id=${id}';
		$.post(url, function(data) {
			json = eval('(' + data + ')');
			if(json.length>0){
				var firstRule = json[0];
				$("#firstMidMapId").val(firstRule.id);
				$("#firstMidMapTd1").val(firstRule.groupId);
				$("#firstMidMapTd2").val(firstRule.varName);
				$("#firstMidMapTd3").val(firstRule.datatype);

				//增加一行数据
				for(var i=1;i<json.length;i++){
					var midMap= json[i];
					var elements = addMidMap();
					var idElement = elements[0];
					var groupIdElement = elements[1];
					var varNameElement= elements[2];
					var datatypeElement = elements[3];
					idElement.value=midMap.id;
					groupIdElement.value=midMap.groupId;
					varNameElement.value=midMap.varName;
					datatypeElement.value = midMap.datatype;
				}
			}
		});
		
		var url = '${webRoot}/loopParseRule/getByParseRuleId?id=${id}';
		$.post(url, function(data) {
			var tjson = eval('(' + data + ')');
			if(tjson.length==0) return;
			$("#loopSample").val(tjson[0].loopSample);
			$("#loopVar").val(tjson[0].loopVars);
			
			var ids = new Array();
			for(var j=0;j<tjson.length;j++){
				ids.push(tjson[j].id);
			}
			
			$.ajax({
					type:'POST',
					url:'${webRoot}/loopParseRule/getByLoopParseRuleIds',
					dataType:'json',
					async:false,
					data:{'ids':ids
					},
					success: function(data){
						for(var i=0;i<data.length;i++){
							appendLoopParseRule(data[i]);
						}
						
					},
					error: function(data){
						alert("循环变量规则插入失败！");
					}
				    
				});
		});
		
		$("#loopStrList").bind('input propertychange', function() {loopStrListRestFlag=true;}); 
		$("#loopVar").bind('input propertychange', function() {changeLoopVar();}); 
		document.getElementById("uploadFileButton").addEventListener('click',function(evt){
	        evt.preventDefault();
	        uploadFileButtonClick(false);
		});
		
		document.getElementById("modalUploadFileButton").addEventListener('click',function(evt){
	        evt.preventDefault();
	        uploadFileButtonClick(true);
		});
		
		//jarPathFileButton
		document.getElementById("uploadJarButton").addEventListener('click',function(evt){
	        evt.preventDefault();
	        uploadJarButtonClick(true);
		});
	});
	
	
	function appendLoopParseRule(json){
		if(json.length==1){
	    	$("#loopParseRuleForOneDiv").css('display','');
	    	$("#loopVarRuleForOneId").val(json[0].id);
	    	$("#loopParseRuleForOneId").val(json[0].loopParseRuleId);
	    	$("#loopVarType").val(json[0].loopVarType);
	    	choseLoopVarType(false,json[0].loopVarType);
	    	if(json[0].loopVarType=="0"){
	    		$("#loopStart").val(json[0].loopStart);
	    		$("#loopEnd").val(json[0].loopEnd);
	    		$("#loopStep").val(json[0].loopStep);
	    		$("#loopFormat").val(json[0].loopFormat);
	    	}else{
	    		$("#loopStrList").val(json[0].loopStrList);
	    		$("#loopStrList").attr("title",json[0].loopStrList);
	    		$("#loopVarStrEncode").val(json[0].loopStrEncode);
	    	}
	    }else if(json.length>0){
	    	$("#loopParseRuleForMultiDiv").css('display','');
	    	var elements = addLoopParseRule();
			elements[0].value=json[0].loopParseRuleId;
			//这里需要是有顺序的
			var loopvars = $("#loopVar").val().split(",");
			for(var j=0; j<loopvars.length;j++){
				for(var i=1;i<=json.length;i++){
					if(json[i-1].loopVarName==loopvars[j]){
						if(json[i-1].loopVarType=="0"){
							elements[j+1].value=json[i-1].loopStart+"|"+json[i-1].loopEnd+"|"+json[i-1].loopStep+"|"+json[i-1].loopFormat;
		    			}else{
		    				elements[j+1].value=json[i-1].loopStrList+"|"+json[i-1].loopStrEncode;
		    			}
						elements[j+1].id=json[j].id;
						elements[j+1].title = elements[i].value;
					}
				}
			}
	    }
	}
	
	//批量生成url
	function loop_generate(val) {
		if (val == true) {
			$("#urlWithLoop").val('1');
			$("#loopSampleDiv").css('display', '');
		
			$("#loopVarDiv").css('display', '');
			
			demo.addRule([  {
				ele : "#loopVar",
				datatype : "*",
				nullmsg : "请输入循环变量名！",
			}]);
			$("#loopParseRuleTHead").empty();
			$("#loopParseRuleTBody").empty();
			if($("#loopVar").val()!=null && $("#loopVar").val()!=""){
				if($("#loopVar").val().indexOf(",")>=0){
					//多个变量
					$("#loopParseRuleForOneDiv").css('display', 'none');
					$("#loopParseRuleForMultiDiv").css('display', '');

				}else{
					//一组变量
					$("#loopVarTypeDiv").css('display','');
					$("#loopParseRuleForOneDiv").css('display', '');
					$("#loopParseRuleForMultiDiv").css('display', 'none');
				}
			}
		} else {
			$("#urlWithLoop").val('0');
			$("#loopSampleDiv").css('display', 'none');
			$("#loopVarTypeDiv").css('display','none');
			$("#loopVarDiv").css('display', 'none');
			$("#loopVarTypeDiv").val('-1');
			$("#loopParseRuleForMultiDiv").css('display','none');
			$("#loopParseRuleForOneDiv").css('display','none');
			//清除所有值
		}

	}
	
	function choseLoopVarType(ismodal,value) {
		if(ismodal){
			//数字类型
			if ("0" == value) {
				$("#modalLoopVarStrEncodeDiv").css('display','none');
				$("#modalLoopStartDiv").css('display', '');
				$("#modalLoopEndDiv").css('display', '');
				$("#modalLoopStepDiv").css('display', '');
				$("#modalLoopFormatDiv").css('display', '');
				
				$("#modalWenJianDiv").css('display', 'none');
				$("#modalWenJianDiv").removeAttr("datatype");
				$("#modalLoopStrListDiv").css('display','none');
				
				demo.addRule([ {
					ele : "#modalLoopStart",
					datatype : "*",
					nullmsg : "请输入循环起始值！",
				}, {
					ele : "#modalLoopEnd",
					datatype : "*",
					nullmsg : "请输入循环结束值！",
				}, {
					ele : "#modalLoopStep",
					datatype : "n",
					nullmsg : "请输入循环变量的递增值！不能为0！",
				}, {
					ele : "#modalLoopFormat",
					datatype : "*",
					nullmsg : "请输入循环变量的格式！",
				} ]);
				
			} else if ("1" == value) {//字符串类型
				$("#modalLoopVarStrEncodeDiv").css('display','');
				$("#modalWenJianDiv").css('display', 'none');
				$("#modalLoopStrListDiv").css('display','');

				$("#modalLoopStartDiv").css('display', 'none');
				$("#modalLoopEndDiv").css('display', 'none');
				$("#modalLoopStepDiv").css('display', 'none');
				$("#modalLoopFormatDiv").css('display', 'none');

				$("#modalLoopStart").removeAttr("datatype");
				$("#modalLoopEnd").removeAttr("datatype");
				$("#modalLoopVar").removeAttr("datatype");
				$("#modalLoopStep").removeAttr("datatype");
				$("#modalLoopFormat").removeAttr("datatype");
			}
			else if("-1"==value){
				//删除掉所有类型
				$("#modalLoopVarStrEncodeDiv").css('display','none');
				$("#modalLoopStrListDiv").css('display','none');
				$("#modalWenJianDiv").css('display', 'none');
				$("#modalWenJianDiv").removeAttr("datatype");
				$("#modalLoopStartDiv").css('display', 'none');
				$("#modalLoopEndDiv").css('display', 'none');
				$("#modalLoopStepDiv").css('display', 'none');
				$("#modalLoopFormatDiv").css('display', 'none');

				$("#modalLoopStart").removeAttr("datatype");
				$("#modalLoopEnd").removeAttr("datatype");
				$("#modalLoopVar").removeAttr("datatype");
				$("#modalLoopStep").removeAttr("datatype");
				$("#modalLoopFormat").removeAttr("datatype");
			}
		}else{
			//数字类型
			if ("0" == value) {
				$("#loopStartDiv").css('display', '');
				$("#loopEndDiv").css('display', '');
				$("#loopStepDiv").css('display', '');
				$("#loopFormatDiv").css('display', '');
				
				$("#loopVarStrEncodeDiv").css('display','none');
				$("#loopStrListDiv").css('display', 'none');
				$("#select_wenjian").css('display', 'none');
				$("#select_wenjian").removeAttr("datatype");
				
				$("#loopStrList").val('');
				
				demo.addRule([ {
					ele : "#loopStart",
					datatype : "*",
					nullmsg : "请输入循环起始值！",
				}, {
					ele : "#loopEnd",
					datatype : "*",
					nullmsg : "请输入循环结束值！",
				}, {
					ele : "#loopStep",
					datatype : "n",
					nullmsg : "请输入循环变量的递增值！不能为0！",
				}, {
					ele : "#loopFormat",
					datatype : "*",
					nullmsg : "请输入循环变量的格式！",
				} ]);
				
			} else if ("1" == value) {//字符串类型
				$("#loopVarStrEncodeDiv").css('display','');
				$("#select_wenjian").css('display', 'none');
				$("#loopStrListDiv").css('display', '');

				$("#loopStartDiv").css('display', 'none');
				$("#loopEndDiv").css('display', 'none');
				$("#loopStepDiv").css('display', 'none');
				$("#loopFormatDiv").css('display', 'none');

				$("#loopStart").removeAttr("datatype");
				$("#loopEnd").removeAttr("datatype");
				$("#loopVar").removeAttr("datatype");
				$("#loopStep").removeAttr("datatype");
				$("#loopFormat").removeAttr("datatype");
				
				$("#loopStart").val('');
				$("#loopEnd").val('');
				$("#loopStep").val('');
				$("#loopFormat").val('');
			}
			else if("-1"==value){
				//删除掉所有类型
				
			}
		}
	}
	
	var setLoopVarRuleTarget;
	
	function setLoopVarRule(target){
		//首先清空所有设置的数据
		$("#modalLoopStart").val("");
		$("#modalLoopEnd").val("");
		$("#modalLoopStep").val("");
		$("#modalLoopVarType").val("-1");
		$("#modalLoopStrList").val("");
		$("#modalLoopVarStrEncode").val("null");
		$("input[name='modalLoopStrListFile']").val("");
		
		//隐藏所有的显示
		$("#modalLoopStartDiv").css('display', 'none');
		$("#modalLoopEndDiv").css('display', 'none');
		$("#modalLoopStepDiv").css('display', 'none');
		$("#modalLoopFormatDiv").css('display', 'none');
		$("#modalWenJianDiv").css('display', 'none');
		$("#modalLoopVarStrEncodeDiv").css('display', 'none');
		$("#modalLoopStrListDiv").css('display', 'none');
		
		$('#modifyLoopVarRule').modal('show');
		$('#modifyLoopVarRule').on('shown', function (e) {
			setLoopVarRuleTarget = target;
		});
	}
	
	function addLoopParseRule(){
		//如果没有表头，就先创建表头
		var loopVarArray = $("#loopVar").val().trim().split(",");
		if($("#loopParseRuleTHead").children().length!=loopVarArray.length+1 ){
			//重新创建表头
			$("#loopParseRuleTHead").empty();
			var thElement = document.createElement("th");
			document.getElementById("loopParseRuleTHead").appendChild(thElement);
			
			for(var i=0;i<loopVarArray.length;i++){
				thElement = document.createElement("th");
				thElement.innerHTML = loopVarArray[i];
				document.getElementById("loopParseRuleTHead").appendChild(thElement);
			}
			var thElement = document.createElement("th");
			document.getElementById("loopParseRuleTHead").appendChild(thElement);
		}
		//否则直接增加行
		var trElement = document.createElement("tr");
		var inputList = new Array();
		//创建一个不显示的列，存储id
		var tdElement = document.createElement("td");
		//创建按钮
		
		var trElement = document.createElement("tr");
		var inputList = new Array();
		var tdElement = document.createElement("td");
		//创建按钮
		var inputElement = document.createElement("input");
		inputElement.type = "text";
		inputElement.setAttribute("style", "display:none");
		inputElement.setAttribute("readonly", "readonly");
		tdElement.appendChild(inputElement);
		trElement.appendChild(tdElement);
		inputList.push(inputElement);

		for(var i=0;i<loopVarArray.length;i++){
			 tdElement = document.createElement("td");
			//创建按钮
			inputElement = document.createElement("input");
			inputElement.type = "text";
			inputElement.setAttribute("class", "form-control");
			inputElement.setAttribute("placeholder", "点击配置");
			inputElement.setAttribute("readonly", "readonly");
			inputElement.setAttribute("data-toggle","tooltip");
			inputElement.setAttribute("data-placement","top");
			inputElement.setAttribute("onclick","setLoopVarRule(this)");
			tdElement.appendChild(inputElement);
			trElement.appendChild(tdElement);
			inputList.push(inputElement);
		}
		var tdElement = document.createElement("td");
		var deleteButton = document.createElement("a");
		deleteButton.innerHTML = "<i class=\"icon-minus\"></i>";
		deleteButton.setAttribute("href", "javascript:void(0);");
		deleteButton.setAttribute("style",
				"float:none;background:none;border:none");
		deleteButton.onclick = function() {
			delLoopParseRule(this);
		};
		tdElement.appendChild(deleteButton);
		trElement.appendChild(tdElement);
		document.getElementById("loopParseRuleTBody").appendChild(trElement);
		
		return inputList;

	}
	
	//改变输入的值
	function changeLoopVar(){
			//删除多条规则的数据
			var trs = document.getElementById("loopParseRuleTBody").children;
			if(trs.length>0){
				var ids="";
				for(var i=0;i<trs.length;i++){
					var input = trs[i].firstChild.firstChild;
					ids = ids+";"+input.value;
					input.value="";
				}
				if(ids.length>1){
					var url = "${path}/crawlManage/loopParseRule/deleteParseRule?id="+ids.substring(1);
  					$.post(url,function(data){
  					//window.location.href="${path}/crawlManage/parseRule/setParseRule"; 
  					},"text");
				}
			}
			//删除单条规则的数据
			
			if($("#loopParseRuleForOneId").val()!="" && $("#loopParseRuleForOneId").val()!=null){
				var url = "${path}/crawlManage/loopParseRule/deleteParseRule?id="+$("#loopParseRuleForOneId").val();
				$("#loopParseRuleForOneId").val("");
	  			$.post(url,function(data){
	  				//alert("删除完成！");
	  				//window.location.href="${path}/crawlManage/parseRule/setParseRule"; 
	  			},"text");
			}
	  		
			$("#loopParseRuleTHead").empty();
			$("#loopParseRuleTBody").empty();
			if($("#loopVar").val()!=null){
				if($("#loopVar").val().indexOf(",")>=0){
					//多个变量
					$("#loopParseRuleForOneDiv").css('display', 'none');
					$("#loopParseRuleForMultiDiv").css('display', '');

				}else{
					//一组变量
					$("#loopParseRuleForOneDiv").css('display', '');
					$("#loopParseRuleForMultiDiv").css('display', 'none');
				}
			}
	}
	
	var loopStrListRestFlag = false;
	
	function getLoopVarRule(ismodal){
		if(ismodal){
			if($("#modalLoopVarType").val()=="0"){
				return $("#modalLoopStart").val()+"|"+
				$("#modalLoopEnd").val()+"|"+
				$("#modalLoopStep").val()+"|"+
				$("#modalLoopFormat").val();
			}
			if($("#modalLoopVarType").val()=="1"){
				if($("#modalLoopStrList").css('display')=='none'){				
					return $("#modalWenjianName").val()+"|"+$("#modalLoopVarStrEncode").val();
				}else{
					return $("#modalLoopStrList").val()+"|"+$("#modalLoopVarStrEncode").val();
				}
			}
		}else{
			if($("#loopVarType").val()=="0"){
				return $("#loopStart").val()+"|"+
				$("#loopEnd").val()+"|"+
				$("#loopStep").val()+"|"+
				$("#loopFormat").val();
			}
			if($("#loopVarType").val()=="1"){
				if($("#loopStrList").css('display')=='none'){				
					return $("#wenjianName").val()+"|"+$("#loopVarStrEncode").val();
				}else{
					//检查是否重新设置过
					if(loopStrListRestFlag){
						return $("#loopStrList").val()+"|"+$("#loopVarStrEncode").val();
					}else{
						return "|"+$("#loopVarStrEncode").val();
					}
				}
			}
		}
	}
	
	function addLoopVarRule(){
		//在操作的当前的input框里面增加内容
		if(setLoopVarRuleTarget!=null){
			setLoopVarRuleTarget.value= getLoopVarRule(true);
			setLoopVarRuleTarget.title = setLoopVarRuleTarget.value;
			if(setLoopVarRuleTarget.id!=null && setLoopVarRuleTarget.id!=""){
				//更新每一个LoopVarRule
				$.ajax({
			          url: '${webRoot}/loopParseRule/updateVarRule' ,
			          type: 'POST',
			          dataType:'json',
			          data: {'loopVarRule':setLoopVarRuleTarget.value,
			        	  	'taskId':$("#taskId").val(),
							'id': setLoopVarRuleTarget.id
						},
			          async: false,
			          success: function (returndata) {
			              //alert(returndata);
			          },
			          error: function (returndata) {
			              alert(returndata);
			          }
			    });
			}
		}
		$('#modifyLoopVarRule').modal('hide');
	}
	
	function setUploadFile(ismodal){
		if(ismodal){
			//e.preventDefault(); 
			$("#modalLoopStrList").val("");
			$("#modalLoopStrList").css('display','none');
			$("#modalWenJianDiv").css('display','');
			$("#modalLoopStrList").removeAttr('data-target');
			//return false;
		}else{
			//e.preventDefault(); 
			$("#loopStrList").val("");
			$("#loopStrList").css('display','none');
			$("#select_wenjian").css('display','');
			$("#loopStrList").removeAttr('data-target');
			//return false;
		}
	}
	
	
	function setJarFileName(inputVar){
		$('#jarPath').val("file:////"+inputVar.value);
		//上传文件
		alert("正在上传文件，请等待......");
		//document.getElementById("uploadFiles").submit();
		 
     	var formData = new FormData($( "#uploadJar" )[0]);
     	formData.append("taskId","${taskId}");
     	$.ajax({
          url: '${webRoot}/parseRuleTemp/uploadJar' ,
          type: 'POST',
          data: formData,
          async: false,
          cache: false,
          contentType: false,
          processData: false,
          success: function (returndata) {
        	  //将hdfs的路径返回
        	  if(returndata.substring(0,7)=="hdfs://"){
              	alert("文件上传成功！");
        	  }
              $('#jarPath').val(returndata);
          },
          error: function (returndata) {
              alert(returndata);
          }
     	});
	}
	
	
	//根据参数设置变量的值
	function setWenjianName(inputVar,ismodal){
		if(ismodal){
			$('#modalWenjianName').val("file:////"+inputVar.value);
		}else{
			$('#wenjianName').val("file:////"+inputVar.value);
			$('#loopStrList').val("file:////"+inputVar.value);
		}
		//上传文件
		alert("正在上传文件，请等待......");
		//document.getElementById("uploadFiles").submit();
		 
     	var formData = new FormData($( "#uploadFiles" )[0]);
     	formData.append("taskId",$("#taskId").val());
     	$.ajax({
          url: '${webRoot}/parseRuleTemp/uploadFile' ,
          type: 'POST',
          data: formData,
          async: false,
          cache: false,
          contentType: false,
          processData: false,
          success: function (returndata) {
              alert(returndata);
          },
          error: function (returndata) {
              alert(returndata);
          }
     	});
	}
	
	//上传jar的文件
	function uploadJarButtonClick(){
		var input = document.createElement("input");
		input.setAttribute("type","file");
		input.setAttribute("name","jarFile");
		input.setAttribute("multiple","multiple");
		input.setAttribute("class","filestyle");
		
		input.setAttribute("onchange","setJarFileName(this)");
		$("#hidden_jar").empty();
		document.getElementById("hidden_jar").appendChild(input);
		$(":file").filestyle();
		input.click();
	}


	function uploadFileButtonClick(ismodal){
		//wenjianName
		var input = document.createElement("input");
		input.setAttribute("type","file");
		input.setAttribute("name","loopStrListFile");
		input.setAttribute("multiple","multiple");
		input.setAttribute("class","filestyle");
		if(ismodal){
			input.setAttribute("onchange","setWenjianName(this,true)");
		}else{
			input.setAttribute("onchange","setWenjianName(this,false)");
		}
		$("#hidden_select_wenjian").empty();
		document.getElementById("hidden_select_wenjian").appendChild(input);
		$(":file").filestyle();
		//.bootstrapFileInput()
		input.click();
	}
	
	function setMannualEdit(ismodal){
		if(ismodal){
			//e.preventDefault(); 
			$("#modalLoopStrList").val("");
			$("#modalLoopStrList").css('display','');
			$("#modalWenJianDiv").css('display','none');
			$("input[name='modalLoopStrListFile']").val("");
			demo.addRule([ {
				ele : "#modalLoopStrList",
				datatype : "*",
				nullmsg : "循环字符串不能为空",
			}]);			
		}else{
			//e.preventDefault(); 
			$("#loopStrList").val("");
			$("#loopStrList").css('display','');
			$("#select_wenjian").css('display','none');
			$("input[name='loopStrListFile']").val("");
			demo.addRule([ {
				ele : "#loopStrList",
				datatype : "*",
				nullmsg : "循环字符串不能为空",
			}]);
		}
		//return false;
	}
	
    //设置了抽取方式之后对页面做出的动态修改
	function choseExtractType(value) {

		if ("0" == value || value == 'null') {
			$("#regexDiv").css('display', 'none');
			$("#scopeRegexDiv").css('display','none');
			$("#regexGroupIdDiv").css('display', 'none');
			$("#xpathDiv").css('display', 'none');
			$("#jarPathDiv").css("display","none");
			$("#classNameDiv").css("display","none");
		} else if ("1" == value) {//regex

			//$("#firstMidMapSet").attr("disabled", false);
			$("#regexDiv").css('display', '');
			$("#scopeRegexDiv").css('display','');
			$("#regexGroupIdDiv").css('display', '');
			$("#xpathDiv").css('display', 'none');
			$("#jarPathDiv").css("display","none");
			$("#classNameDiv").css("display","none");

			$("#xpath").removeAttr("datatype");
			$("#xpath").removeAttr("nullmsg");
			$("#xpath").removeAttr("errormsg");

			demo.addRule([ {
				ele : "#regex",
				datatype : "*",
				nullmsg : "请输入正则表达式内容！",
				errormsg : "正则表达式内容不能为空！"
			}, {
				ele : "#regexGroupId",
				datatype : "n",
				nullmsg : "请输入正则表达式组号！",
				errormsg : "正则表达式组号必须为数字，0表示整个正则表达式所匹配的所有内容！"
			} ]);

		} else if ("2" == value) {//xpath
			//删除增加按钮
			//$("#addButton").remove();
			//结果片段默认为0
			//$("#firstMidMapSet").val("0");
			//$("#firstMidMapSet").attr("disabled", true);

			$("#regexDiv").css('display', 'none');
			$("#scopeRegexDiv").css('display','none');
			$("#regexGroupIdDiv").css('display', 'none');
			$("#xpathDiv").css('display', '');
			$("#containHtmlDiv").css('display', '');
			$("#jarPathDiv").css("display","none");
			$("#classNameDiv").css("display","none");

			$("#regex").removeAttr("datatype");
			$("#regex").removeAttr("nullmsg");
			$("#regex").removeAttr("errormsg");

			$("#regexGroupId").removeAttr("datatype");
			$("#regexGroupId").removeAttr("nullmsg");
			$("#regexGroupId").removeAttr("errormsg");

			demo.addRule([ {
				ele : "#xpath",
				datatype : "*",
				nullmsg : "请输入xpath！",
				errormsg : "xpath表达式必须为字符串！"
			} ]);

		} else if ("3" == value) {//自定义
			
			$("#jarPathDiv").css("display","");
			$("#classNameDiv").css("display","");
			$("#regexDiv").css('display', 'none');
			$("#scopeRegexDiv").css('display','none');
			$("#regexGroupIdDiv").css('display', 'none');
			$("#xpathDiv").css('display', 'none');
			$("#containHtmlDiv").css('display', 'none');

			$("#regex").removeAttr("datatype");
			$("#regex").removeAttr("nullmsg");
			$("#regex").removeAttr("errormsg");

			$("#regexGroupId").removeAttr("datatype");
			$("#regexGroupId").removeAttr("nullmsg");
			$("#regexGroupId").removeAttr("errormsg");
			
			$("#xpath").removeAttr("datatype");
			$("#xpath").removeAttr("nullmsg");
			$("#xpath").removeAttr("errormsg");

		}
	}

	function clearInvalidExtractType(value) {
		if ("0" == value) {
			$("#regex").val(null);
			$("#regexGroupId").val(null);
			$("#xpath").val(null);
		} else if ("1" == value) {//regex
			$("#xpath").val(null);
		} else if ("2" == value) {//xpath
			$("#regex").val(null);
			$("#regexGroupId").val(null);
		}
	}
	
	function clearInvalidLoopWithUrl(value) {
		if ("0" == value) {
			$("#loopStart").val(null);
			$("#loopEnd").val(null);
			$("#loopVar").val(null);
			$("#loopStep").val(null);
			$("#loopFormat").val(null);

		}
	}

	function containsRegex(str){
		if(str==null||str==""){
			return false;
		}
		str = str.replace(/\\\./g,"");
		str = str.replace(/\\\?/g,"");
		str = str.replace(/\\\*/g,"");
		//alert(str); 最关键的就是这三个，其余的?和。不检查了
		if(str.indexOf("\\d")>=0||str.indexOf("\\w")>=0||str.indexOf("\\*")>=0){
			return true;
		}
		return false;
	}
	
	function submit() {
		
		var ischecks = $("input[name='check']:checked").length;
		var url = '${webRoot}/parseRuleTemp/updateParseRule';
		if ($("#check").prop("checked")) {
			$("#enabled").attr("value", "1");
		} else {
			$("#enabled").attr("value", "0");
		}
		if ($("#urlWithLoopCheck").prop("checked")) {
			$("#urlWithLoop").attr("value", "1");
		} else {
			$("#urlWithLoop").attr("value", "0");
		}
		clearInvalidExtractType($("#extractType").val());
		clearInvalidLoopWithUrl($("#urlWithLoop").val());
		
		//当使用了循环变量生成时，检查匹配的url中是否包含正则项目，如果包含，则提示
		if($("#urlWithLoop").val()=="1" && containsRegex($("#urlPattern").val()) 
				&& confirm("如果使用了循环生成url，适用的URL中不应该再包含正则，否则会在每个匹配项目中都进行循环生成数据!是否继续?")==false){
			return ;
		}
		
		$("#ruleId").attr("name","id");;//先将ruleId设置为id
		var data1 = $("#formn").serialize();
		$.post(url, data1, function(data) {
			if (data == "1") {
				//将parseRule的ID修改为ruleId
				$("#ruleId").attr("name","ruleId");
				var tableObj = document.getElementById("midMapTbody");
				var midMapModifySuc = true;
                for (var i = 0; i < tableObj.rows.length; i++) {  //遍历Table的所有Row
                	//修改其id，name,property，,使得该页面能够映射为ParseRuleMidMap
                	var rowElement = tableObj.rows[i];
                	rowElement.children[0].setAttribute("name","id");
                	rowElement.cells[0].firstChild.setAttribute("name","groupId");
                	rowElement.cells[1].firstChild.setAttribute("name","varName");
                	rowElement.cells[2].firstChild.setAttribute("name","datatype");
                	if(rowElement.children[0].value==null||
                			rowElement.children[0].value==""){
                		if(rowElement.cells[0].firstChild.value!="" && rowElement.cells[1].firstChild.value!=""){
							var url = '${webRoot}/parseRuleMidMapTemp/addParseRuleMidMap';
							data1 = $("#formn").serialize();
							$.post(url, data1, function(data) {
								if (data != "" && data=="0") {
									midMapModifySuc = false;
								}
							});
                		}
                	}else{
                		var url = '${webRoot}/parseRuleMidMapTemp/updateParseRuleMidMap';
						data1 = $("#formn").serialize();
						$.post(url, data1, function(data) {
							if (data != "" && data=="0") {
								midMapModifySuc = false;
							}
						});
                	}
					rowElement.children[0].setAttribute("name","");
					rowElement.cells[0].firstChild.setAttribute("name","");
                	rowElement.cells[1].firstChild.setAttribute("name","");
                	rowElement.cells[2].firstChild.setAttribute("name","");
                }
                if(midMapModifySuc==false){
                	alert("变量映射修改失败");
                }
                
                tableObj = document
				.getElementById("loopParseRuleTable");
                $("#ruleId").attr("name","parseRuleId");
				
				//提交单个变量时候的数据
				if($("#loopVar").val()!=null && $("#loopVar").val()!="" && $("#loopVar").val().indexOf(",")<0){
					var varRuleList = new Array();
					var varRule = getLoopVarRule(false);
					if(varRule){
						varRuleList.push(varRule);
					}
					if(varRuleList.length==0){
						alert("没有配置循环变量的详细信息！");
						return;
					}
					if($("#loopVarRuleForOneId").val()==null||
							$("#loopVarRuleForOneId").val()==""){
						$.ajax({
							type:"POST",
							url:"${webRoot}/loopParseRule/addParseRule",
							dataType:'json',
							async:false,
							data:{'loopVarRules':varRuleList,'taskId':$("#taskId").val(),
								'loopSample':$("#loopSample").val(),
								'loopVars':$("#loopVar").val(),
								'parseRuleId': $("#ruleId").val()
							},
							success: function(data){
							},
							error: function(data){
								alert("循环变量规则插入失败！");
							}
						});
					}else{
						$.ajax({
							type:"POST",
							url:"${webRoot}/loopParseRule/updateParseRuleAndVarRule",
							dataType:'json',
							async:false,
							data:{'loopVarRule':varRuleList[0],'taskId':$("#taskId").val(),
								'id':$("#loopParseRuleForOneId").val(),
								'parseRuleTempId': $("#ruleId").val(),
								'varRuleId':$("#loopVarRuleForOneId").val(),
								'loopSample':$("#loopSample").val(),
								'loopVars':$("#loopVar").val()
							},
							success: function(data){
							},
							error: function(data){
								alert("循环变量规则插入失败！");
							}
						});
					}
				}
				
				if($("#loopVar").val()!=null && $("#loopVar").val()!="" && $("#loopVar").val().indexOf(",")>=0){
					if(tableObj.rows.length==0){
						alert("没有配置循环变量的详细信息！");
						return;
					}
					if($("#loopVar").val()!=null && $("#loopVar").val()!="" && $("#loopVar").val().indexOf(",")>=0){
						for (i = 0; i < tableObj.rows.length; i++) { //遍历Table的所有Row
							//修改其id，name,property，,使得该页面能够映射为ParseRuleMidMap
							var rowElement = tableObj.rows[i];
							var varRuleList = new Array();
							for(j = 1; j < rowElement.cells.length-1; j++){
								varRuleList.push(rowElement.cells[j].firstChild.value);
							}
							if(varRuleList.length==0){
								alert("没有配置循环变量的详细信息！");
								continue;
							}
							if(rowElement.children[0].firstChild.value==null||
		                			rowElement.children[0].firstChild.value==""){
								$.ajax({
									type:"POST",
									url:"${webRoot}/loopParseRule/addParseRule",
									dataType:'json',
									async:false,
									data:{'loopVarRules':varRuleList,'taskId':$("#taskId").val(),
										'parseRuleId': $("#ruleId").val(),
										'loopSample':$("#loopSample").val(),
										'loopVars':$("#loopVar").val()
									},
									success: function(data){
									},
									error: function(data){
										alert("循环变量规则插入失败！");
									}
								});
							}else{
								varRuleList = new Array();
								$.ajax({
									type:"POST",
									url:"${webRoot}/loopParseRule/updateParseRule",
									dataType:'json',
									async:false,
									data:{'loopVarRules':varRuleList,
										'id': rowElement.children[0].firstChild.value,
										'parseRuleTempId': $("#ruleId").val(),
										'loopSample':$("#loopSample").val(),
										'loopVars':$("#loopVar").val()
									},
									success: function(data){
									},
									error: function(data){
										alert("循环变量规则插入失败！");
									}
								});
							}
						}
					}
				}
				alert("修改成功！");
				if($("#modal").val()=="1"){
					window.location.href =  "${webRoot}/parseRuleTemp/parseRuleManageModal?taskId="
					+ $("#taskId").val();
				}else{
					window.location.href = "${webRoot}/parseRule/setParseRule";
				}
			} else {
				alert("修改失败!");
			}
		});
		
	}

	//动态增加行
	function addMidMap() {
		//创建tr元素
		var trElemnet = document.createElement("tr");
		//创建td元素
		var input0Element = document.createElement("input");
		input0Element.type="hidden";
		input0Element.setAttribute("name","midMapId");
		
		var td1Element = document.createElement("td");
		var td2Element = document.createElement("td");
		var td3Element = document.createElement("td");
		var td4Element = document.createElement("td");
		td1Element.class="form-control";
		td2Element.class="form-control";
		td3Element.class="form-control";
		td4Element.class="form-control";
		
		//创建按钮
		var input1Element = document.createElement("input");
		input1Element.type = "text";
		input1Element.setAttribute("class","form-control");
		input1Element.setAttribute("placeholder","结果片段的序号，只能为数字");
		
		var input2Element = document.createElement("input");
		input2Element.type = "text";
		input2Element.setAttribute("class","form-control");
		
		var selectElement = document.createElement("select");
		selectElement.setAttribute("class","form-control");
		var optionNullElement = document.createElement("option");
		optionNullElement.value="null";
		optionNullElement.text="请选择";
		var option0Element = document.createElement("option");
		option0Element.value="0";
		option0Element.text="数字";
		var option1Element = document.createElement("option");
		option1Element.value="1";
		option1Element.text="文本";
		selectElement.appendChild(optionNullElement);
		selectElement.appendChild(option0Element);
		selectElement.appendChild(option1Element);
		
		var deleteButton = document.createElement("button");
		deleteButton.innerHTML = "<i class=\"icon-minus\"></i>";
		deleteButton.setAttribute("href", "javascript:void(0);");
		deleteButton.setAttribute("style",
				"float:none;background:none;border:none");
		deleteButton.onclick = function() {
			delMidMap(this);
		};
		
		td1Element.appendChild(input1Element)
		td2Element.appendChild(input2Element)
		td3Element.appendChild(selectElement);
		td4Element.appendChild(deleteButton);

		//将td元素添加到tr元素中
		trElemnet.appendChild(input0Element);
		trElemnet.appendChild(td1Element);
		trElemnet.appendChild(td2Element);
		trElemnet.appendChild(td3Element);
		trElemnet.appendChild(td4Element);
		//将tr元素添加到tbody元素中
		
		document.getElementById("midMapTbody").appendChild(trElemnet);
		return [input0Element,input1Element,input2Element,selectElement];
	}

	
	function delLoopParseRule(row){
		var tr = row.parentNode.parentNode;
		//获取删除的行的id
		var idInput = tr.firstChild.firstChild;
		var id = idInput.value;
		//弹出提示框
		if(confirm("确定删除该循环变量规则吗？")){
			tr.parentNode.removeChild(tr);
	  		var url = "${path}/crawlManage/loopParseRule/deleteParseRule?id="+id;
	  		$.post(url,function(data){
	  			alert("删除完成！");
	  			//window.location.href="${path}/crawlManage/parseRule/setParseRule"; 
	  		},"text");
		}
	}
	
	
	//删除一行
	function delMidMap(row) {
		var tr = row.parentNode.parentNode;
		//获取删除的行的id
		var idInput = tr.children[0];
		var id = idInput.value;
		//弹出提示框
		if(confirm("确定删除该映射吗吗？")){
			tr.parentNode.removeChild(tr);
	  		var url = "${path}/crawlManage/parseRuleMidMapTemp/deleteParseRuleMidMap?id="+id;
	  		$.post(url,function(data){
	  			alert("删除完成！");
	  			//window.location.href="${path}/crawlManage/parseRule/setParseRule"; 
	  		},"text");
		}
	}
</script>
<body style="margin: 0; padding: 0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
	<div class="content_waikuang">
		<div class="container-fluid search_select select-result"
			style="padding-left: 0">

			<h2 class=".content_title">增加URL解析规则</h2>
			
			<!-- 
			<form id="uploadFiles" action="${webRoot}/parseRuleTemp/uploadFile"  enctype="multipart/form-data" method="POST">
					<input type="file" class="filestyle" name="loopStrListFile" id="wenjianName" readonly="readonly">
					<button >提交</button>
			</form>
			 -->
			
			<form  id='formn' >

				<input type="hidden" name="ruleId" id="ruleId">
				<input type="hidden" name="modal" id="modal" value="${modal}">
				<div class="form-group">
					<label for="name" class="content_zjnrcqlabel">规则名称</label> <input
						type="text" class="form-control content_zjnrcqtext" name='name'
						id="name" placeholder="规则名称，描述出该规则的用途，非空" datatype="*"
						nullmsg="请输入规则名称" errormsg="规则名称必须为字符串">
				</div>
				<div class="form-group">
					<label for="taskId" class="content_zjnrcqlabel">任务ID</label> <input
						type="text" class="form-control content_zjnrcqtext" name='taskId'
						id="taskId" placeholder="任务Id" datatype="*" nullmsg="请输入任务id"
						errormsg="任务Id必须为字符串,不能包含标点符号" value="${taskId}">
				</div>

				<div class="form-group">
					<label for="urlPattern" class="content_zjnrcqlabel">适用的URL模式</label>
					<textarea style="text-align: left"
						class="form-control content_zjnrcqtext" name='urlPattern'
						id="urlPattern" cols="100" rows="4"
						placeholder="适用的URL模式,+后面可以跟必须满足的正则表达式，-后面跟不满足的正则表达式，回车换行分割"
						datatype="urlpattern"
						nullmsg="请输入URL模式; +后面可以跟必须满足的正则表达式，-后面跟不满足的正则表达式，回车换行分割"
						errormsg="+需要匹配的url地址-需要过滤的url地址，使用回车换行符分隔"></textarea>
				</div>

				<div class="form-group">
					<label for="downloadType" class="content_zjnrcqlabel">请求提交方式 </label><select
						class="form-control" name='downloadType' id="downloadType">
						<option value="0">GET</option>
						<option value="1">POST</option>
					</select><span style="color:#DDDDDD;font-family:NSimSun">注意：应用于解析得到的url,而非测试url(使用任务中的配置)</span>
				</div>
				<div class="form-group">
					<label for="downloadType" class="content_zjnrcqlabel">下载工具</label>
					<select
						class="form-control" name='downloadTool' id="downloadTool">
						<option value="0">HttpClient</option>
						<option value="1">HtmlUnit</option>
					</select><span style="color:#DDDDDD;font-family:NSimSun;font-size:10">注意：应用于解析得到的url,而非测试url(使用任务中的配置)</span>
				</div>
					
				<div class="form-group">
					<label for="extractType" class="content_zjnrcqlabel">抽取方式</label> <select
						class="form-control" name='extractType' id="extractType"
						onchange="choseExtractType(this.value)">
						<option value="null">请选择</option>
						<option value="1">按正则表达式抽取</option>
						<option value="2">按xpath抽取</option>
						<option value="3">用户自定义</option>
					</select>
				</div>
				
				<div class="form-group" id="jarPathDiv" style="display: none;">
						<button id="uploadJarButton" >选择文件(*.jar)</button>
						<input type="text" name="jarPath" id="jarPath" readonly="readonly" placeholder="" style="margin-bottom:2px"></input>
						<a  target="_blank" style="color: #08c;text-decoration: underline;background:none;border:none;padding-top:7px" href="http://223.99.170.18:8981/platform/distributed-crawling/wikis/how-to-define-your-rules-by-programming">参考</a>
				</div>
				<div class="form-group" id="classNameDiv" style="display: none;">
					<label for="className" class="content_zjnrcqlabel">类名字</label> <input
						type="text" class="form-control content_zjnrcqtext" name='className'
						id="className" placeholder="类名，需要加上包名，例如com.inspur.test.MyUrlParser">
				</div>
				
				<div class="form-group" id="scopeRegexDiv" style="display: none;">
					<label for="scopeRegex" class="content_zjnrcqlabel">正则表达式内容</label> <input
						type="text" class="form-control content_zjnrcqtext" name='scopeRegex'
						id="scopeRegex" placeholder="圈定范围的正则表达式内容，下面的正则表达式基于此正则表达式的范围，如果是下面的正则匹配整个页面，就不需要填写">
				</div>
				<div class="form-group" id="regexDiv" style="display: none;">
					<label for="regex" class="content_zjnrcqlabel">正则表达式内容</label> <input
						type="text" class="form-control content_zjnrcqtext" name='regex'
						id="regex" placeholder="正则表达式内容">
				</div>
				<div class="form-group" id="regexGroupIdDiv" style="display: none;">
					<label for="regexGroupId" class="content_zjnrcqlabel">正则表达式组号</label>
					<input type="text" class="form-control content_zjnrcqtext"
						name='regexGroupId' id="regexGroupId" placeholder="正则表达式组号">
				</div>
				<div class="form-group" id="xpathDiv" style="display: none;">
					<label for="xpath" class="content_zjnrcqlabel">xpath内容</label> <input
						type="text" class="form-control content_zjnrcqtext" name='xpath'
						id="xpath" placeholder="xpath内容">
				</div>
				<div class="form-group" id="midMapDiv">
					<label for="xpath" class="content_zjnrcqlabel">抽取结果映射 <a
						href="javascript:void(0);" id="addButton"
						style="float: none; background: none; border: none"
						onclick="addMidMap()"> <i class="icon-plus"></i></a>
					</label>

					<table class="table table-striped" id="midMapTable">
						<thead>
							<th>结果片段</th>
							<th>变量名</th>
							<th>数据类型</th>
							<th></th>
						</thead>
						<tbody id="midMapTbody">
							<tr>
								<input type="hidden" name="midMapId" id="firstMidMapId"></input>
								<td><input type="text" class="form-control"
									id="firstMidMapTd1" placeholder="结果片段的序号，只能为数字"></input></td>
								<td><input type="text" class="form-control"
									id="firstMidMapTd2"></input></td>
								<td><select type="text" class="form-control"
									id="firstMidMapTd3" onchange="choseDataType(this.value)"
									nullmsg="请选择抽取方式" errormsg="必须选择数据类型">
										<option value="null">请选择</option>
										<option value="0">数字</option>
										<option value="1">文本</option>
								</select>
								<td><a href="javascript:void(0);" id="addButton"
									style="float: none; background: none; border: none"
									onclick="delMidMap(this)"> <i class="icon-minus"></i></a></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="form-group">
					<label for="urlType" class="content_zjnrcqlabel">生成的URL的类型</label>
					<select class="form-control" name='urlType' id="urlType"
						datatype="n" nullmsg="请选择生成的URL的类型" errormsg="必须选择生成的URL的类型">
						<option value="-1">请选择</option>
						<option value="0">其它</option>
						<option value="1">导航页</option>
						<option value="2">翻页列表页</option>
						<option value="3">详情页</option>
					</select>
				</div>
				
				<div class="form-group">
					<input type="checkbox" id="urlWithLoopCheck"
						onchange="loop_generate(this.checked)">使用循环批量生成URL <input
						type="hidden" id="urlWithLoop" name="urlWithLoop" value="0"></input>
				</div>
				<br>
				
				
				<div class="form-group" id="loopSampleDiv" style="display: none;">
					<label for="loopSample" class="content_zjnrcqlabel">循环URL样本</label>
					<input type="text" class="form-control content_zjnrcqtext"
						name='loopSample' id="loopSample"
						placeholder="基于该值为样本进行批量生成URL，当不指定时，以默认需要处理的url为样本">
				</div>
				
				<div class="form-group" id="loopVarDiv" style="display: none;">
					<label for="loopVar" class="content_zjnrcqlabel">循环变量名或改变的数字的位置</label>
					<input type="text" class="form-control content_zjnrcqtext"
						name="loopVars" id="loopVar" placeholder="循环变量名或改变的数字的位置，如果有多个，请使用英文逗号分隔，例如'-1,pageno,4'"
						data-toggle="tooltip" data-placement="top" title="如果修改内容，那么将会删除已经有的配置"
					>
				</div>
				
				
				<!-- 多个变量同时变化时候的配置 -->
				<div class="form-group" id="loopParseRuleForMultiDiv" style="display:none">
					<label for="xpath" class="content_zjnrcqlabel">循环变量配置 <a
						href="javascript:void(0);" id="addButton"
						style="float: none; background: none; border: none"
						onclick="addLoopParseRule()"> <i class="icon-plus"></i></a>
					</label>
					<table class="table table-striped" id="loopParseRuleTable">
						<thead id="loopParseRuleTHead">
						</thead>
						<tbody id="loopParseRuleTBody">
						</tbody>
					</table>
				</div>
				
				<!-- 一个变量同时变化时候的配置 -->
				<div class="form-group" id="loopParseRuleForOneDiv" style="display:none">
					<input type="text" style="display:none" id="loopParseRuleForOneId"></input>
					<input type="text" style="display:none" id="loopVarRuleForOneId"></input>
					<div class="form-group" id="loopVarTypeDiv">
						<label for="loopVarType" class="content_zjnrcqlabel">循环变量类型</label> 
						<select
							class="form-control" name="loopVarType" id="loopVarType"
							onchange="choseLoopVarType('',this.value)">
							<option value="-1">请选择</option>
							<option value="0">数字</option>
							<option value="1">字符串</option>
						</select>
					</div>
				
				
					<div class="form-group" id="loopStartDiv" style="display:none">
						<label for="loopStart" class="content_zjnrcqlabel">循环起始值</label> 
						<input
						type="text" class="form-control content_zjnrcqtext"
						name='loopStart' id="loopStart" placeholder="循环起始值，可以为数字，或引用抽取的内容">
					</div>
					<div class="form-group" id="loopEndDiv" style="display:none">
						<label for="loopEnd" class="content_zjnrcqlabel">循环结束值</label>
						<input
						type="text" class="form-control content_zjnrcqtext" name="loopEnd"
						id="loopEnd"
						placeholder="循环结束值，可以为数字，或引用抽取的内容{{总页数}}，或者简单的表达式，例如{{总页数}}-1">
					</div>
				
				
					<div class="form-group" id="loopStepDiv" style="display:none">
						<label for="loopStep" class="content_zjnrcqlabel">循环递增值</label> 
						<input
						type="text" class="form-control content_zjnrcqtext"
						name="loopStep" id="loopStep"
						placeholder="请输入循环变量的递增值，只能为数字，但不能为0">
					</div>
					<div class="form-group" id="loopFormatDiv" style="display:none">
						<label for="loopFormat" class="content_zjnrcqlabel">循环变量格式</label>
						<input type="text" class="form-control content_zjnrcqtext"
						name="loopFormat" id="loopFormat" placeholder="url生成模板，%d表示数字，%s表示字符串，_%d表示在数字签名增加_" value="%d">
					</div>
					
					<div class="form-group" id="loopVarStrEncodeDiv" style="display:none">
						<label for="loopFormat" class="content_zjnrcqlabel">循环变量的字符编码</label>
						<select class="form-control" name='loopVarStrEncode' id="loopVarStrEncode"
						>
						<option value="null">无</option>
						<option value="gbk">gbk</option>
						<option value="utf-8">utf-8</option>
					</select>
					</div>
					
					<div class="form-group" id="loopStrListDiv" style="display:none">
						<label for="loopStrList" class="content_zjnrcqlabel">循环变量-字符串列表      <a href="#"  onclick="setUploadFile('')"
						style="float:none;background:none;border:none" ><i class="icon-upload"
						></i></a><a href="#"  onclick="setMannualEdit('')"
						style="float:none;background:none;border:none" ><i class="icon-edit"
						></i></a></label>
						<input type="text" class="form-control content_zjnrcqtext" data-toggle="tooltip" data-placement="top"
						name="loopStrList" id="loopStrList" placeholder="循环变量为字符串列表，使用英文逗号分隔，最好不要超过10个，如果太多，点击上方按钮上传文件">
					</div>
					
					<div class="form-group" id="select_wenjian" style="display:none">
						<button id="uploadFileButton" >选择文件</button>
						<input type="text" id="wenjianName" placeholder="文本文件，必须为UTF-8编码，每行作为一个变量值" style="width:500px"></input>
					</div>
				
					<br><br>
				</div>
			
				<div class="form-group">
					<input type="checkbox" checked="checked" id="check">启用 <input
						type="hidden" id="enabled" name="enabled" value="1">
				</div>
				<br>
				<div class="form-group" id="testUrlDiv">
					<label for="testUrl" class="content_zjnrcqlabel">测试url</label><br>
					<input type="text" class="form-control content_zjnrcqtext"
						name='testUrl' id="testUrl" style="width: 70%; float: left"
						placeholder="测试url"   datatype="*" nullmsg="请输入测试url，必须进行测试"
						errormsg="请输入测试url，必须进行测试"/><br> <br>
				</div>
				
			</form>
			
			<form id="uploadFiles" action="${webRoot}/parseRuleTemp/uploadFile" style="display:none" enctype="multipart/form-data" method="POST">
				<div id="hidden_select_wenjian"> </div>
				<button id="uploadFileButton"></button>
			</form>
			
			<!-- 上传到Jar -->
			<form id="uploadJar" action="${webRoot}/parseRuleTemp/uploadJar" style="display:none" enctype="multipart/form-data" method="POST">
				<div id="hidden_jar"> </div>
				<button id="uploadJarButton"></button>
			</form>
			
			<button type="button" class="btn btn-primary" id="ajaxpost">提交</button>
			<button type="button" class="btn btn-primary" id="testurl"
				data-toggle="modal" data-target="#modifyInstance">测试</button>
			<button type="button" class="btn btn-primary" id="return">返回</button>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal hide fade" id="modifyLoopVarRule" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">循环变量取值范围配置</h4>
				</div>
				<form id="form" method="POST">
					<div class="modal-body">
						<div class="form-group" id="modalLoopVarTypeDiv">
							<label for="loopVarType" class="content_zjnrcqlabel">循环变量类型</label> 
							<select
								class="form-control" name="loopVarType" id="modalLoopVarType"
								onchange="choseLoopVarType(true,this.value)">
								<option value="-1">请选择</option>
								<option value="0">数字</option>
								<option value="1">字符串</option>
							</select>
						</div>
				
				
						<div class="form-group" id="modalLoopStartDiv" style="display:none">
							<label for="loopStart" class="content_zjnrcqlabel">循环起始值</label> 
							<input type="text" class="form-control content_zjnrcqtext"
							name='loopStart' id="modalLoopStart" placeholder="循环起始值，可以为数字，或引用抽取的内容">
						</div>
						<div class="form-group" id="modalLoopEndDiv" style="display:none">
							<label for="loopEnd" class="content_zjnrcqlabel">循环结束值</label>
							<input type="text" class="form-control content_zjnrcqtext" name="loopEnd"
							id="modalLoopEnd" placeholder="循环结束值，可以为数字，或引用抽取的内容{{总页数}}，或者简单的表达式，例如{{总页数}}-1">
						</div>
				
				
						<div class="form-group" id="modalLoopStepDiv" style="display:none">
							<label for="loopStep" class="content_zjnrcqlabel">循环递增值</label> 
							<input type="text" class="form-control content_zjnrcqtext"
							name="loopStep" id="modalLoopStep" placeholder="请输入循环变量的递增值，只能为数字，但不能为0">
						</div>
						<div class="form-group" id="modalLoopFormatDiv" style="display:none">
							<label for="loopFormat" class="content_zjnrcqlabel">循环变量格式</label>
							<input type="text" class="form-control content_zjnrcqtext"
							name="loopFormat" id="modalLoopFormat" placeholder="url生成模板" value="%d">
						</div>
				
						<div class="form-group" id="modalLoopVarStrEncodeDiv" style="display:none">
							<label for="loopFormat" class="content_zjnrcqlabel">循环变量的字符编码</label>
							<select class="form-control" name='modalLoopVarStrEncode' id="modalLoopVarStrEncode">
							<option value="null">无</option>
							<option value="gbk">gbk</option>
							<option value="utf-8">utf-8</option>
							</select>
						</div>
					
						<div class="form-group" id="modalLoopStrListDiv" style="display:none">
							<label for="loopStrList" class="content_zjnrcqlabel">循环变量-字符串列表      <a href="#"  onclick="setUploadFile(true)"
								style="float:none;background:none;border:none" ><i class="icon-upload"
								></i></a><a href="#"  onclick="setMannualEdit(true)"
								style="float:none;background:none;border:none" ><i class="icon-edit"
								></i></a></label>
								<input type="text" class="form-control content_zjnrcqtext"
							name="modalLoopStrList" id="modalLoopStrList" 
							placeholder="循环变量为字符串列表，使用英文逗号分隔，最好不要超过10个，如果太多，点击上方按钮上传文件">
						</div>
							
						<div class="form-group" id="modalWenJianDiv" style="display:none">
							 <button id="modalUploadFileButton" >选择文件</button>
							 <input type="text" id="modalWenjianName" placeholder="文本文件，必须为UTF-8编码，每行作为一个变量值"></input>
							 
						</div>
						
						<button type="button" class="btn btn-primary" id="submitButton" onclick="addLoopVarRule()">提交</button>
						
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<div class="modal hide fade" id="modifyInstance" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">url解析测试结果</h4>
				</div>
				<form id="form" method="POST">
					<input type="hidden" name="flag" id="flag">
					<div class="modal-body">
						<div class="form-group">
							<label for="taskId">url</label> <input type="text"
								class="form-control content_zjnrcqtext" name='"srcUrl"'
								id="srcUrl" value="">
						</div>
						<div class="form-group">
							<label for="extractUrlSize">条数</label> <input type="text"
								class="form-control content_zjnrcqtext" name='extractUrlSize'
								id="extractUrlSize" value="后台正在拼命处理ing....">
						</div>
						<div class="form-group">
							<label for="log">日志</label>
							<textarea type="text" class="form-control content_zjnrcqtext"
								name='log' id="log" style="text-align: left" rows=5 cols=100>后台正在拼命处理ing....
								</textarea>

						</div>
						<div class="form-group">
							<label for="extractUrls">抽取结果(前500条)</label>
							<textarea type="text" class="form-control content_zjnrcqtext"
								name='extractUrls' id="extractUrls" value="后台正在拼命处理ing...."
								rows=10 cols=100>后台正在拼命处理ing....
							</textarea>
						</div>

						<div class="form-group">
							<label for="extractUrls">网页源码</label>
							<textarea type="text" class="form-control content_zjnrcqtext"
								name='testUrlHtml' id="testUrlHtml" value="后台正在拼命处理ing...."
								rows=10 cols=100>后台正在拼命处理ing....
							</textarea>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>


