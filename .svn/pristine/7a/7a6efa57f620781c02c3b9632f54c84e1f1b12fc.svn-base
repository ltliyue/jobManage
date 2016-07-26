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
<script type="text/javascript"
	src="/crawlManage/resources/js/Chinese-characters-to-Pinyin/py.js"></script>
<script src="/crawlManage/resources/js/new_version/script.js"></script>
</head>
<script type="text/javascript">
	var demo;
	//表单验证
	$(function() {
		demo = $("#formn").Validform({
			tiptype : 3,
			showAllError : true
		});
		$("#ajaxpost").click(function() {
			var status = demo.check(false);
			//alert(status);
			if (status == true) {
				submit();
			}
		});

		$("#return").click(function() {
			window.location.href = "${webRoot}/parseRule/setParseRule";
		});

		$("#testurl").click(
				function() {
					var status = demo.check(false);
					if (status == true) {
						//如果通过，执行自己的方法
						if ($("#containHtml").val() == 'null') {
							$("#containHtml  option:selected").val("-1");
						}
						var ischecks = $("input[name='check']:checked").length;
						var url = '${webRoot}/parseRule/testParseRule';

						if ($("#urlWithLoopCheck").prop("checked")) {
							$("#urlWithLoop").val("1");
						} else {
							$("#urlWithLoop").val("0");
						}
						//把midMap进行list化 

						var tableObj = document.getElementById("midMapTable");
						for (var i = 1; i < tableObj.rows.length; i++) { //遍历Table的所有Row
							//修改其id，name,property，,使得该页面能够映射为ParseRuleMidMap
							var rowElement = tableObj.rows[i];
							rowElement.children[0].setAttribute("name",
									"midMaps[" + (i - 1) + "].id");
							rowElement.cells[0].firstChild.setAttribute("name",
									"midMaps[" + (i - 1) + "].groupId");
							rowElement.cells[1].firstChild.setAttribute("name",
									"midMaps[" + (i - 1) + "].varName");
							rowElement.cells[2].firstChild.setAttribute("name",
									"midMaps[" + (i - 1) + "].datatype");
						}

						var data1 = $("#formn").serialize();
						$("#srcUrl").val($("#testUrl").val());
						$.post(url, data1, function(data) {
							var json = eval("(" + data + ")");
							$("#extractUrlSize").val(json.extractUrlSize);
							$("#log").val(json.log);
							$("#extractUrls").val(
									json.extractUrls.toString().replace(/,/g,
											"\r\n"));
							$("#testUrlHtml").val(json.testUrlHtml);
						});
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
									//参数gets是获取到的表单元素值，
									// obj为当前表单元素，
									// curform为当前验证的表单，
									// regxp为内置的一些正则表达式的引用。([+\-]https?:\/\/[^\r\n]+))

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

	function choseExtractType(value) {

		if ("0" == value || value == 'null') {
			$("#regexDiv").css('display', 'none');
			$("#regexGroupIdDiv").css('display', 'none');
			$("#xpathDiv").css('display', 'none');
		} else if ("1" == value) {//regex

			//$("#firstMidMapSet").attr("disabled", false);

			$("#regexDiv").css('display', '');
			$("#regexGroupIdDiv").css('display', '');
			$("#xpathDiv").css('display', 'none');

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
			$("#regexGroupIdDiv").css('display', 'none');
			$("#xpathDiv").css('display', '');
			$("#containHtmlDiv").css('display', '');

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

		}
	}

	function submit() {
		var url = '${webRoot}/parseRule/addParseRule';
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

		var data1 = $("#formn").serialize();

		$.post(url, data1, function(data) {
			if (data != "" && data.length == 19) {//19位的uuid
				alert("增加成功！");
				document.getElementById("ruleId").value = data;
				//获取插入的id,然后插入该页面中的动态增加的数据
				var tableObj = document.getElementById("midMapTable");
				for (var i = 1; i < tableObj.rows.length; i++) { //遍历Table的所有Row
					//修改其id，name,property，,使得该页面能够映射为ParseRuleMidMap
					var rowElement = tableObj.rows[i];
					rowElement.cells[0].firstChild.setAttribute("name",
							"groupId");
					rowElement.cells[1].firstChild.setAttribute("name",
							"varName");
					rowElement.cells[2].firstChild.setAttribute("name",
							"datatype");
					var url = '${webRoot}/parseRuleMidMap/addParseRuleMidMap';
					data1 = $("#formn").serialize();
					$.post(url, data1, function(data) {
						if (data != "" && data.length < 20) {
						}
					});
					rowElement.cells[0].firstChild.setAttribute("name", "");
					rowElement.cells[1].firstChild.setAttribute("name", "");
					rowElement.cells[2].firstChild.setAttribute("name", "");
				}
				window.location.href = "${webRoot}/parseRule/setParseRule";
			} else {
				alert("增加失败！");
			}
		});
	}

	//批量生成url
	function loop_generate(val) {
		if (val == true) {
			$("#urlWithLoop").val('1');
			$("#loopSampleDiv").css('display', '');
			$("#loopStartDiv").css('display', '');
			$("#loopEndDiv").css('display', '');
			$("#loopVarDiv").css('display', '');
			$("#loopStepDiv").css('display', '');
			$("#loopFormatDiv").css('display', '');

			demo.addRule([ {
				ele : "#loopStart",
				datatype : "*",
				nullmsg : "请输入循环起始值！",
			}, {
				ele : "#loopEnd",
				datatype : "*",
				nullmsg : "请输入循环结束值！",
			}, {
				ele : "#loopVar",
				datatype : "*",
				nullmsg : "请输入循环变量名！",
			}, {
				ele : "#loopStep",
				datatype : "/^-?[0-9]+$/i",
				errormsg : "请输入循环变量的递增值！只能为数字，不能为0！",
				nullmsg : "请输入循环变量的递增值！不能为0！",
			}, {
				ele : "#loopFormat",
				datatype : "*",
				nullmsg : "请输入循环变量的格式！",
			} ]);

		} else {
			$("#urlWithLoop").val('0');
			$("#loopSampleDiv").css('display', 'none');
			$("#loopStartDiv").css('display', 'none');
			$("#loopEndDiv").css('display', 'none');
			$("#loopVarDiv").css('display', 'none');
			$("#loopStepDiv").css('display', 'none');
			$("#loopFormatDiv").css('display', 'none');

			$("#loopStart").removeAttr("datatype");
			$("#loopEnd").removeAttr("datatype");
			$("#loopVar").removeAttr("datatype");
			$("#loopStep").removeAttr("datatype");
			$("#loopFormat").removeAttr("datatype");
		}
		alert($("#urlWithLoop").val());
	}
	//动态增加行
	function addMidMap() {
		//创建tr元素
		var trElemnet = document.createElement("tr");
		//创建td元素
		var td1Element = document.createElement("td");
		var td2Element = document.createElement("td");
		var td3Element = document.createElement("td");
		var td4Element = document.createElement("td");
		td1Element.class = "form-control";
		td2Element.class = "form-control";
		td3Element.class = "form-control";
		td4Element.class = "form-control";

		//创建按钮
		var input1Element = document.createElement("input");
		input1Element.type = "text";
		input1Element.setAttribute("class", "form-control");
		input1Element.setAttribute("placeholder", "结果片段的序号，只能为数字");

		var input2Element = document.createElement("input");
		input2Element.type = "text";
		input2Element.setAttribute("class", "form-control");

		var selectElement = document.createElement("select");
		selectElement.setAttribute("class", "form-control");
		var optionNullElement = document.createElement("option");
		optionNullElement.value = "null";
		optionNullElement.text = "请选择";
		var option0Element = document.createElement("option");
		option0Element.value = "0";
		option0Element.text = "数字";
		var option1Element = document.createElement("option");
		option1Element.value = "1";
		option1Element.text = "文本";
		selectElement.appendChild(optionNullElement);
		selectElement.appendChild(option0Element);
		selectElement.appendChild(option1Element);

		var deleteButton = document.createElement("a");
		deleteButton.innerHTML = "<i class=\"icon-minus\"></i>";
		deleteButton.setAttribute("href", "#");
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
		trElemnet.appendChild(td1Element);
		trElemnet.appendChild(td2Element);
		trElemnet.appendChild(td3Element);
		trElemnet.appendChild(td4Element);
		//将tr元素添加到tbody元素中

		document.getElementById("midMapTbody").appendChild(trElemnet);
	}

	//删除一行
	function delMidMap(row) {
		var tr = row.parentNode.parentNode;
		tr.parentNode.removeChild(tr);
	}
	//*/
</script>
<body style="margin: 0; padding: 0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
	<div class="content_waikuang">
		<div class="container-fluid search_select select-result"
			style="padding-left: 0">

			<h2 class=".content_title">增加URL解析规则</h2>
			<form action="${webRoot}/datauser/useradd" id='formn'>

				<input type="hidden" name="ruleId" id="ruleId">
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
						placeholder="适用的URL模式,+后面可以跟必须满足的正则表达式，-后面跟不满足的正则表达式"
						datatype="urlpattern"
						nullmsg="请输入URL模式; +后面可以跟必须满足的正则表达式，-后面跟不满足的正则表达式，回车换行分割"
						errormsg="+需要匹配的url地址-需要过滤的url地址，使用回车换行符分隔"></textarea>
				</div>

				<div class="form-group">
					<label for="extractType" class="content_zjnrcqlabel">抽取方式</label> <select
						class="form-control" name='extractType' id="extractType"
						onchange="choseExtractType(this.value)">
						<option value="null">请选择</option>
						<option value="1">按正则表达式抽取</option>
						<option value="2">按xpath抽取</option>
					</select>
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
						href="#" id="addButton"
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
								<td><input type="text" class="form-control"
									id="firstMidMapSet" placeholder="结果片段的序号，只能为数字"></input></td>
								<td><input type="text" class="form-control"></input></td>
								<td><select type="text" class="form-control"
									onchange="choseDataType(this.value)" nullmsg="请选择抽取方式"
									errormsg="必须选择数据类型">
										<option value="null">请选择</option>
										<option value="0">数字</option>
										<option value="1">文本</option>
								</select>
								<td><a href="#" id="addButton"
									style="float: none; background: none; border: none"
									onclick="delMidMap(this)"> <i class="icon-minus"></i></a></td>

							</tr>
						</tbody>
					</table>
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
				<div class="form-group" id="loopStartDiv" style="display: none;">
					<label for="loopStart" class="content_zjnrcqlabel">循环起始值</label> <input
						type="text" class="form-control content_zjnrcqtext"
						name='loopStart' id="loopStart" placeholder="循环起始值，可以为数字，或引用抽取的内容">
				</div>
				<div class="form-group" id="loopEndDiv" style="display: none;">
					<label for="loopEnd" class="content_zjnrcqlabel">循环结束值</label> <input
						type="text" class="form-control content_zjnrcqtext" name="loopEnd"
						id="loopEnd"
						placeholder="循环结束值，可以为数字，或引用抽取的内容{{总页数}}，或者简单的表达式，例如{{总页数}}-1">
				</div>
				<div class="form-group" id="loopVarDiv" style="display: none;">
					<label for="loopVar" class="content_zjnrcqlabel">循环变量名或改变的数字的位置</label>
					<input type="text" class="form-control content_zjnrcqtext"
						name="loopVar" id="loopVar" placeholder="循环变量名或改变的数字的位置">
				</div>
				<div class="form-group" id="loopStepDiv" style="display: none;">
					<label for="loopStep" class="content_zjnrcqlabel">循环递增值</label> <input
						type="text" class="form-control content_zjnrcqtext"
						name="loopStep" id="loopStep"
						placeholder="请输入循环变量的递增值，只能为数字，但不能为0">
				</div>
				<div class="form-group" id="loopFormatDiv" style="display: none;">
					<label for="loopFormat" class="content_zjnrcqlabel">循环变量格式</label>
					<input type="text" class="form-control content_zjnrcqtext"
						name="loopFormat" id="loopFormat" placeholder="url生成模板" value="%d">
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
					<input type="checkbox" checked="checked" id="check">启用 <input
						type="hidden" id="enabled" name="enabled" value="1">
				</div>
				<br>
				<div class="form-group" id="testUrlDiv">
					<label for="testUrl" class="content_zjnrcqlabel">测试url</label><br>
					<input type="text" class="form-control content_zjnrcqtext"
						name='testUrl' id="testUrl" style="width: 70%; float: left"
						placeholder="测试url" /><br> <br>
				</div>
			</form>
			<button type="button" class="btn btn-primary" id="ajaxpost">提交</button>
			<button type="button" class="btn btn-primary" id="testurl"
				data-toggle="modal" data-target="#modifyInstance">测试</button>
			<button type="button" class="btn btn-primary" id="return">返回</button>
		</div>
	</div>
	<!-- Modal -->
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
							<label for="extractUrls">抽取结果</label>
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