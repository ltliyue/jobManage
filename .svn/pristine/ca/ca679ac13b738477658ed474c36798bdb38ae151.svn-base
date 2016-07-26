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
<link href="<%=path%>/resources/css/new_version/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/new_version/site.css"
	rel="stylesheet">
<link href="<%=path%>/resources/validform/css/style.css"
	rel="stylesheet">

<link href="<%=path%>/resources/css/bootstrap-treeview.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=path%>/resources/css/zTreeStyle/zTreeStyle.css"
	type="text/css">

<!-- Bootstrap core jss -->
<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
<script src="<%=path%>/resources/js/jquery.min.js"></script>
<script src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap.min.js"></script>

<script src="<%=path%>/resources/js/new_version/site.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/treegrid/jquery.treegrid.bootstrap3.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/treegrid/jquery.treegrid.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/validform/js/Validform_v5.3.2_min.js"></script>

</head>

<script type="text/javascript">
	var demo;
	//表单验证
	var storeColumnSelect="<option >请选择</option>";
	var storeColumnMap={};
	
	$(document).ready(function() { 
		var url = '<%=path %>/datasiteitem/siteitem?siteId=${siteId}'
			$.post(url,function(data){
			 	json=eval('('+data+')');
			 	  $.each(json,function(i){
			 		  storeColumnSelect = storeColumnSelect+ "\r\n<option  value='"+json[i].code+"'>"+ json[i].name+"</option>";
			 		 storeColumnMap[json[i].name]=1;
			 	  });
				 $("#storeColumnName0").html(storeColumnSelect);
				 $("#storeColumnName1").html(storeColumnSelect);
			 });
	});
	
	$(function() {
		demo = $("#formn").Validform({
			tiptype : 3,
			showAllError : true
		});
		$("#ajaxpost").click(function() {
			//检查是否存在重复的存储的列
			var columnNameMap={}
			var unique = true;
			var duplicateColumnComment="";
			
			$("[id^='storeColumnComment']").each(function(){
				if($(this).val()!=null && $(this).val()!=""){
					if(columnNameMap[$(this).val()]==null){
						columnNameMap[$(this).val()] = 1;
					}else{
						columnNameMap[$(this).val()] = 2;
						unique = false;
						duplicateColumnComment=duplicateColumnComment+", "+$(this).val();
				}}});
			
			var notSetColumnComment="";//没有配置的项
			for(var key in storeColumnMap ){ 
				if(columnNameMap[key]==null){
					notSetColumnComment=notSetColumnComment+","+key;
				}
			}
			if(unique==false){
				alert("存在重复存储列名："+duplicateColumnComment.substring(1));
				return;
			}
			if (confirm("警告，还有配置的字段："+notSetColumnComment.substring(1)+"\r\n是否继续?")==false){
				return;
			}
			
			var status = demo.check(false);
			if (status == true) {
				//如果通过，执行自己的方法
				if ($("#containHtml").val() == 'null') {
					$("#containHtml  option:selected").val("-1");
				}
				submit();
			}
		});

		$("#return").click(function() {
			window.location.href = "${webRoot}/pageExtractRule/setExtractRule";
		});
		$("#testurl")
				.click(
						function() {
							var status = demo.check(false);
							if (status == true) {
								var ischecks = $("input[name='check']:checked").length;
								var url = '${webRoot}/pageExtractRule/testExtractRule';

								//把elementExtractRule进行list化 
								var tableObj = document
										.getElementById("elementExtractRules0");
								for (var i = 1; i < tableObj.rows.length; i++) { //遍历Table的所有Row
									//修改其id，name,property，,使得该页面能够映射为ParseRuleMidMap
									var rowElement = tableObj.rows[i];
									rowElement.children[0].setAttribute("name",
											"rules[" + (i - 1) + "]." + "id");
									rowElement.cells[0].firstChild
											.setAttribute("name", "rules["
													+ (i - 1) + "]." + "xpath");
									rowElement.cells[1].firstChild
											.setAttribute("name", "rules["
													+ (i - 1) + "]."
													+ "containHtml");
									rowElement.cells[2].firstChild
											.setAttribute("name", "rules["
													+ (i - 1) + "]."
													+ "isKeyInTable");
									rowElement.cells[3].children[0]
											.setAttribute("name", "rules["
													+ (i - 1) + "]."
													+ "storeColumnName");
									rowElement.cells[3].children[1]
											.setAttribute("name", "rules["
													+ (i - 1) + "]."
													+ "storeColumnComment");
									rowElement.cells[5].firstChild
											.setAttribute("name", "rules["
													+ (i - 1) + "]."
													+ "extractType");
								}

								var j = tableObj.rows.length - 1;//前一个表的行的个数
								tableObj = document
										.getElementById("elementExtractRules1");
								for (var i = 1; i < tableObj.rows.length; i++) { //遍历Table的所有Row
									//修改其id，name,property，,使得该页面能够映射为ParseRuleMidMap
									var rowElement = tableObj.rows[i];
									if (rowElement.cells[0].firstChild.value == null
											|| rowElement.cells[0].firstChild.value == "") {
										continue;
									}
									rowElement.children[0].setAttribute("name",
											"rules[" + (j + i - 1) + "]."
													+ "id");
									rowElement.cells[0].firstChild
											.setAttribute("name", "rules["
													+ (j + i - 1) + "]."
													+ "regex");
									rowElement.cells[1].firstChild
											.setAttribute("name", "rules["
													+ (j + i - 1) + "]."
													+ "regexGroupId");
									rowElement.cells[2].firstChild
											.setAttribute("name", "rules["
													+ (j + i - 1) + "]."
													+ "isKeyInTable");
									rowElement.cells[3].children[0]
											.setAttribute("name", "rules["
													+ (j + i - 1) + "]."
													+ "storeColumnName");
									rowElement.cells[3].children[1]
											.setAttribute("name", "rules["
													+ (j + i - 1) + "]."
													+ "storeColumnComment");
									rowElement.cells[5].firstChild
											.setAttribute("name", "rules["
													+ (j + i - 1) + "]."
													+ "extractType");
								}

								var data1 = $("#formn").serialize();
								$("#srcUrl").val($("#testUrl").val());
								$.post(url, data1, function(data) {
									var json = eval("(" + data + ")");
									$("#extract_size").val(json.extract_size);
									$("#log").val(json.log);
									$("#extract_content").val(
											json.extract_content.toString()
													.replace(/,/g, "\r\n"));
									$("#html").val(json.html);
								});
							}
						});

		$('#modifyInstance')
				.on(
						'show.bs.modal',
						function(event) {
							var element = $(event.relatedTarget)
							var id = element.data('id')
							var modal = $(this)
							if ($("#taskId").val() == null
									|| $("#taskId").val() == "") {
								alert("任务id为空！需要先填写任务id!");
								modal.close();
							} else {
								modal.find('#siteItemChooseIFrame').attr(
										"src",
										"${webRoot}/extractRule/siteItemChoose?taskId="
												+ $("#taskId").val()
												+ "&eventOwner=" + id);
							}
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

	function showModal(value) {
		alert(value + "," + $("#taskId").val());
		if ($("#taskId").val() == null || $("#taskId").val() == "") {
			alert("任务id为空！请先填写任务id，再选择字段!");
			return;
		}
		$("#modifyInstance").load(
				"${webRoot}/extractRule/siteItemChoose?taskId="
						+ $("#taskId").val() + "&eventOwner=" + value);
		$("#modifyInstance").modal("show");
	}
	
	function initStoreColumn(){
			window.location.href= "<%=path %>/extractRule/extractRule?t=1&id="+$("#taskId").val();
	}
	
	function alertInputTaskId(selectVar){
		if($("#taskId").val()==null||$("#taskId").val()==""){
			alert("请先填写任务Id!");
			return;
		}
	}

	function setStoreColumnComment(selectVar){
		var id = selectVar.getAttribute("id");
		id = id.replace("Name","Comment");
		$("#"+id).val(selectVar[selectVar.selectedIndex].text);
	}
	
	function addElementExtractRule(value) {
		//创建tr元素
		var trElemnet = document.createElement("tr");
		//创建td元素
		var td1Element = document.createElement("td");
		var td2Element = document.createElement("td");
		var td3Element = document.createElement("td");
		var td4Element = document.createElement("td");
		var td5Element = document.createElement("td");
		var td6Element = document.createElement("td");

		td1Element.class = "form-control";
		td2Element.class = "form-control";
		td3Element.class = "form-control";
		td4Element.class = "form-control";
		td5Element.class = "form-control";

		//第1列，xpath内容或正则表达式内容，是一个input
		var inner1Element = document.createElement("input");
		inner1Element.type = "text";
		inner1Element.setAttribute("class", "form-control");
		if (value == 0) {
			inner1Element.setAttribute("placeholder", "xpath内容");
		}
		if (value == 1) {
			inner1Element.setAttribute("placeholder", "内容");
		}

		//第2列，包含HTML或者正则表达式组号,是一个input或者select
		var inner2Element = null;
		if (value == 0) {
			inner2Element = document.createElement("select");
			inner2Element.setAttribute("class", "form-control");
			var option0Element = document.createElement("option");
			option0Element.value = "0";
			option0Element.text = "否";
			var option1Element = document.createElement("option");
			option1Element.value = "1";
			option1Element.text = "是";
			inner2Element.appendChild(option0Element);
			inner2Element.appendChild(option1Element);
		}
		if (value == 1) {
			inner2Element = document.createElement("input");
			inner2Element.type = "text";
			inner2Element.value = "0";
			inner2Element.setAttribute("placeholder", "组号");
			inner2Element.setAttribute("class", "form-control");
			inner2Element.setAttribute("datatype", "n");
			inner2Element.setAttribute("nullmsg", "组号不能为空!");
			inner2Element.setAttribute("errormsg", "组号只能是数字!");
		}

		//第3列：是否是主键
		var inner3Element = document.createElement("select");
		inner3Element.setAttribute("class", "form-control");
		var option0Element = document.createElement("option");
		option0Element.value = "0";
		option0Element.text = "否";
		var option1Element = document.createElement("option");
		option1Element.value = "1";
		option1Element.text = "是";
		inner3Element.appendChild(option0Element);
		inner3Element.appendChild(option1Element);

		//第四列：存储字段
		var inner4Element = document.createElement("select");
		inner4Element.innerHTML=storeColumnSelect;
		inner4Element.setAttribute("onchange","setStoreColumnComment(this)");
		inner4Element.setAttribute("onclick","alertInputTaskId(this)");
		//获取当前的行号 ，作为 id

		if (value == 0) {
			var rowsize = document.getElementById("elementExtractRulesTbody0").rows.length;
			inner4Element.setAttribute("id", "storeColumnName0" + rowsize);
		}
		if (value == 1) {
			var rowsize = document.getElementById("elementExtractRulesTbody1").rows.length;
			inner4Element.setAttribute("id", "storeColumnName1" + rowsize);
		}

		//第4列：不显示的storeColumnComment
		var inner4Element1 = document.createElement("input");
		inner4Element1.type = "text";
		inner4Element1.setAttribute("class", "form-control");
		inner4Element1.setAttribute("style", "display:none");
		if (value == 0) {
			var rowsize = document.getElementById("elementExtractRulesTbody0").rows.length;
			inner4Element1.setAttribute("id", "storeColumnComment0" + rowsize);
		}
		if (value == 1) {
			var rowsize = document.getElementById("elementExtractRulesTbody1").rows.length;
			inner4Element1.setAttribute("id", "storeColumnComment1" + rowsize);
		}

		//第5列：删除
		var inner5Element = document.createElement("a");
		inner5Element.innerHTML = "<i class=\"icon-minus\"></i>";
		inner5Element.setAttribute("href", "javascript:void(0);");
		inner5Element.setAttribute("style",
				"float:none;background:none;border:none")
		inner5Element.onclick = function() {
			delElementExtractRule(this);
		};

		//第6列：隐藏的抽取类型
		var inner6Element = document.createElement("input");
		inner6Element.setAttribute("style", "display:none");
		inner6Element.type = "text";
		if (value == 0) {
			inner6Element.value = "2";//正则表达式
		}
		if (value == 1) {
			inner6Element.value = "1";//正则表达式
		}

		td1Element.appendChild(inner1Element)
		td2Element.appendChild(inner2Element)
		td3Element.appendChild(inner3Element);
		td4Element.appendChild(inner4Element);
		td4Element.appendChild(inner4Element1);
		td5Element.appendChild(inner5Element);
		td6Element.appendChild(inner6Element);

		//将td元素添加到tr元素中
		trElemnet.appendChild(td1Element);
		trElemnet.appendChild(td2Element);
		trElemnet.appendChild(td3Element);
		trElemnet.appendChild(td4Element);
		trElemnet.appendChild(td5Element);
		trElemnet.appendChild(td6Element);

		//将tr元素添加到tbody元素中

		if (value == 0) {
			document.getElementById("elementExtractRulesTbody0").appendChild(
					trElemnet);
		}
		if (value == 1) {
			document.getElementById("elementExtractRulesTbody1").appendChild(
					trElemnet);
		}
	}

	function chooseContainsMulti(value) {
		if ("1" == value || value == 'null') {
			$("#multiRowXpathDiv").css('display', 'none');
			$("#multiRowXpath").removeAttr("datatype");
		} else if ("2" == value) {//regex
			$("#multiRowXpathDiv").css('display', '');
			demo.addRule([ {
				ele : "#multiRowXpath",
				datatype : "*",
				nullmsg : "请输入每一条数据的根节点的xpath！",
				errormsg : "每一条数据的根节点的xpath不能为空！"
			} ]);
		}
	}

	//删除一行
	function delElementExtractRule(row) {
		var tr = row.parentNode.parentNode;
		tr.parentNode.removeChild(tr);
	}

	function submit() {
		//当有多条数据时检查只能有一个主键
		var containsMulti = document.getElementById("containsMulti");
		if (containsMulti.value != "1") {
			var isKeyInTableNum = 0;
			var tableObj = document.getElementById("elementExtractRules0");
			for (var i = 1; i < tableObj.rows.length; i++) {
				var rowElement = tableObj.rows[i];
				var isKeyInTableColumn = rowElement.cells[2].firstChild;
				if (isKeyInTableColumn.value == 1)
					isKeyInTableNum++;
			}
			tableObj = document.getElementById("elementExtractRules1");
			for (var i = 1; i < tableObj.rows.length; i++) {
				var rowElement = tableObj.rows[i];
				var isKeyInTableColumn = rowElement.cells[2].firstChild;
				if (isKeyInTableColumn.value == 1)
					isKeyInTableNum++;
			}
			if (isKeyInTableNum > 1) {
				alert("必须只有一个主键!默认行号作为主键!");
				return;
			}
		}

		var url = '${webRoot}/pageExtractRule/addPageExtractRule';
		if ($("#check").prop("checked")) {
			$("#enabled").attr("value", "1");
		} else {
			$("#enabled").attr("value", "0");
		}
		var data1 = $("#formn").serialize();

		$
				.post(
						url,
						data1,
						function(data) {
							if (data != "" && data.length < 20) {

								document.getElementById("pageExtractRuleId").value = data;
								//获取插入的id,然后插入该页面中的动态增加的数据
								var tableObj = document
										.getElementById("elementExtractRules0");

								var lastRow = null;
								for (var i = 1; i < tableObj.rows.length; i++) {
									var rowElement = tableObj.rows[i];
									lastRow = rowElement;
									rowElement.cells[0].firstChild
											.setAttribute("name", "xpath");
									if (rowElement.cells[0].firstChild.value == null
											|| rowElement.cells[0].firstChild.value == "") {
										continue;
									}
									rowElement.cells[1].firstChild
											.setAttribute("name", "containHtml");
									rowElement.cells[2].firstChild
											.setAttribute("name",
													"isKeyInTable");
									rowElement.cells[3].children[0]
											.setAttribute("name",
													"storeColumnName");
									rowElement.cells[3].children[1]
											.setAttribute("name",
													"storeColumnComment");
									rowElement.cells[5].firstChild
											.setAttribute("name", "extractType");
									var url = '${webRoot}/elementExtractRule/addElementExtractRule';
									data1 = $("#formn").serialize();
									$.post(url, data1, function(data) {
										if (data != "" && data.length < 20) {
										}
									});
									rowElement.cells[0].firstChild
											.setAttribute("name", "");
									rowElement.cells[1].firstChild
											.setAttribute("name", "");
									rowElement.cells[2].firstChild
											.setAttribute("name", "");
									rowElement.cells[3].children[0]
											.setAttribute("name", "");
									rowElement.cells[3].children[1]
											.setAttribute("name", "");
									rowElement.cells[5].firstChild
											.setAttribute("name", "");
								}
								if(lastRow!=null){
									lastRow.cells[1].firstChild.setAttribute(
										"name", "containHtml");
								}
								var tableObj = document
										.getElementById("elementExtractRules1");
								for (var i = 1; i < tableObj.rows.length; i++) {
									var rowElement = tableObj.rows[i];
									if (rowElement.cells[0].firstChild.value == null
											|| rowElement.cells[0].firstChild.value == "") {
										continue;
									}
									rowElement.cells[0].firstChild
											.setAttribute("name", "regex");
									rowElement.cells[1].firstChild
											.setAttribute("name",
													"regexGroupId");
									rowElement.cells[2].firstChild
											.setAttribute("name",
													"isKeyInTable");
									rowElement.cells[3].children[0]
											.setAttribute("name",
													"storeColumnName");
									rowElement.cells[3].children[1]
											.setAttribute("name",
													"storeColumnComment");
									rowElement.cells[5].firstChild
											.setAttribute("name", "extractType");
									var url = '${webRoot}/elementExtractRule/addElementExtractRule';
									data1 = $("#formn").serialize();
									$.post(url, data1, function(data) {
										if (data != "" && data.length < 20) {
										}
									});
									rowElement.cells[0].firstChild
											.setAttribute("name", "");
									rowElement.cells[1].firstChild
											.setAttribute("name", "");
									rowElement.cells[2].firstChild
											.setAttribute("name", "");
									rowElement.cells[3].children[0]
											.setAttribute("name", "");
									rowElement.cells[3].children[1]
											.setAttribute("name", "");
									rowElement.cells[5].firstChild
											.setAttribute("name", "");
								}
								alert("增加成功！");
								window.location.href = "${webRoot}/pageExtractRule/setExtractRule";
							} else {
								alert("增加失败！");
							}
						});

	}
</script>

<body style="margin: 0; padding: 0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
	<div class="content_waikuang">
		<div class="container-fluid search_select select-result"
			style="padding-left: 0">
			<h2 class=".content_title">增加内容抽取规则</h2>
			<form action="${webRoot}/datauser/useradd" id='formn'>
				<input type="text" style="display: none" id="pageExtractRuleId"
					name="pageExtractRuleId"></input>
				<div>
					<label for="name" class="content_zjnrcqlabel">规则名称</label> <input
						type="text" class="form-control content_zjnrcqtext" name='name'
						id="name" placeholder="规则名称，描述出该规则的用途，非空" datatype="*"
						nullmsg="请输入规则名称" errormsg="规则名称必须为字符串">
				</div>
				<div>
					<label for="taskId" class="content_zjnrcqlabel">任务ID</label> <input
						type="text" class="form-control content_zjnrcqtext" name='taskId'
						id="taskId" placeholder="任务Id" datatype="*" nullmsg="请输入任务id" onchange="initStoreColumn()" 
						errormsg="任务Id必须为字符串,不能包含标点符号" value="${taskId}">
				</div>

				<div>
					<label for="urlPattern" class="content_zjnrcqlabel">适用的URL模式</label><textarea style="text-align: left"
						class="form-control content_zjnrcqtextarea" name='urlPattern'
						id="urlPattern" cols="100" rows="4"
						placeholder="适用的URL模式,+后面可以跟必须满足的正则表达式，-后面跟不满足的正则表达式"
						datatype="urlpattern"
						nullmsg="请输入URL模式; +后面可以跟必须满足的正则表达式，-后面跟不满足的正则表达式，回车换行分割"
						errormsg="+需要匹配的url地址-需要过滤的url地址，使用回车换行符分隔"></textarea>
				</div>

				<div>
					<label for="extractType" class="content_zjnrcqlabel">抽取数据的条数</label>
					<select class="form-control content_zjnrcqselect"
						name='containsMulti' id="containsMulti" datatype="n"
						nullmsg="请选择抽取数据的条数" errormsg="必须抽取数据的条数"
						onclick="chooseContainsMulti(this.value)">
						<option value=null>请选择</option>
						<option value="1">一条数据</option>
						<option value="2">多条数据</option>
					</select>
				</div>

				<div class="form-group" id="multiRowXpathDiv" style="display: none;">
					<label for="regex" class="content_zjnrcqlabel">每一条数据的根节点</label> <input
						type="text" class="form-control content_zjnrcqtext"
						name='multiRowXpath' id="multiRowXpath" placeholder="输入根节点的xpath">
					<label>默认行号作为主键</label>
				</div>
				<br>
				<div class="form-group" id="midMapDiv">
					<h5 class=".content_title">
						基于xpath的字段抽取规则 <a href="javascript:void(0);" id="addButton"
							style="float: none; background: none; border: none"
							onclick="addElementExtractRule(0)"><i class="icon-plus"></i></a>
					</h5>
					<hr>
					<table class="table table-striped" id="elementExtractRules0">
						<thead>
							<th style="width: 60%">xpath内容</th>
							<th style="width: 10%">包含HTML</th>
							<th style="width: 10%">主键</th>
							<th style="width: 10%">存储字段</th>
							<th style="width: 10%">删除</th>
						</thead>
						<tbody id="elementExtractRulesTbody0">
							<tr>
								<td><input type="text" class="form-control" name='xpath'
									id="xpath" placeholder="xpath内容"></td>
								<td><select class="form-control" name='containHtml'
									id="containHtml">
										<option value=0>否</option>
										<option value=1>是</option>
								</select>
								<td><select class="form-control" name="isKeyInTable0"
									id="isKeyInTable0">
										<option value=0>否</option>
										<option value=1>是</option>
								</select></td>

								<td>
									<select class="from-control" id="storeColumnName0" onclick="alertInputTaskId(this)" onchange="setStoreColumnComment(this)">
									</select>
									<input type="hidden" class="form-control"  id="storeColumnComment0">
								</td>

								<td><a href="#" onclick="delElementExtractRule(this)"
									style="float: none; background: none; border: none"><i
										class="icon-minus"></i></a></td>
								<td><input type="text" style="display: none" value="2"></td>
							</tr>
						</tbody>

					</table>
				</div>

				<div class="form-group" id="midMapDiv">
					<h5 class=".content_title">
						基于正则表达式的字段抽取规则 <a href="javascript:void(0);" id="addButton"
							style="float: none; background: none; border: none"
							onclick="addElementExtractRule(1)"> <i class="icon-plus"></i>
						</a>
					</h5>
					<hr>
					<table class="table table-striped" id="elementExtractRules1">
						<thead>
							<th style="width: 60%">正则表达式内容</th>
							<th style="width: 10%">正则表达式组号</th>
							<th style="width: 10%">主键</th>
							<th style="width: 10%">存储字段</th>
							<th style="width: 10%">删除</th>
						</thead>
						<tbody id="elementExtractRulesTbody1">
							<tr>
								<td><input type="text" class="form-control" name='regex'
									id="regex" placeholder="内容"></td>
								<td><input type="text" class="form-control"
									name='regexGroupId' id="regexGroupId" placeholder="组号"
									value="0"></td>
								<td><select class="form-control" name="isKeyInTable1"
									id="isKeyInTable1">
										<option value=0>否</option>
										<option value=1>是</option>
								</select></td>
								<td>
									<select class="from-control" id="storeColumnName1" onclick="alertInputTaskId(this)"
									onchange="setStoreColumnComment(this)">
									</select>
									<input type="hidden" class="form-control"  id="storeColumnComment1"></input>
								</td>
								<td><a href="#"
									style="float: none; background: none; border: none"
									onclick="delElementExtractRule(this)"><i class="icon-minus"></i></a></td>
								<td><input type="text" style="display: none" value="1"></td>
							</tr>
						</tbody>
					</table>
				</div>

				<div id="testUrlDiv">
					<label for="testUrl" class="content_zjnrcqlabel">测试url</label> <input
						type="text" class="form-control content_zjnrcqtext" name='testUrl'
						id="testUrl" placeholder="测试url" /> <span
						class="Validform_checktip"></span>
				</div>
				<input type="checkbox" checked="checked" id="check">启用 <input
					type="hidden" id="enabled" name="enabled" value="1">

			</form>
			<button type="button" class="btn btn-primary" id="ajaxpost">提交</button>
			<button type="button" class="btn btn-primary" id="return">返回</button>
			<button type="button" id="testurl" class="btn btn-primary"
				data-toggle="modal" data-target="#modifyInstance2">测试</button>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="modifyInstance" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" align="center"
		style="display: block; height: 400px; overflow-y: scroll; overflow-x: auto;">
		<iframe id="siteItemChooseIFrame" style="margin-top: 10%" width=80%
			height=80%> </iframe>
		<!-- 	src=${webRoot}/extractRule/siteItemChooseModal?taskId=${
			'#taskId'} -->
	</div>
	
	<!-- Modal -->
	<div class="modal fade hide in" id="modifyInstance2" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">内容抽取测试结果</h4>
				</div>
				<form id="form" method="POST">
					<input type="hidden" name="flag" id="flag">
					<div class="modal-body">
						<div class="form-group">
							<label for="taskId" class="content_zjnrcqlabel">url</label> <input
								type="text" class="form-control content_zjnrcqtext"
								name="srcUrl" id="srcUrl" value="">
						</div>
						<div class="form-group">
							<label for="log" class="content_zjnrcqlabel">日志</label>
							<textarea type="text" class="form-control content_zjnrcqtext"
								name='log' id="log" style="text-align: left" rows=5 cols=100>后台正在拼命处理ing....
								</textarea>

						</div>
						<div class="form-group">
							<label for="extractUrls" class="content_zjnrcqlabel">抽取结果</label>
							<textarea type="text" class="form-control content_zjnrcqtext"
								name='extract_content' id="extract_content"
								value="后台正在拼命处理ing...." rows=10 cols=100>后台正在拼命处理ing....
							</textarea>
						</div>

						<div class="form-group">
							<label for="extractUrls" class="content_zjnrcqlabel">网页源码</label>
							<textarea type="text" class="form-control content_zjnrcqtext"
								name='html' id="html" value="后台正在拼命处理ing...." rows=10 cols=100>后台正在拼命处理ing....
							</textarea>
						</div>

					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
