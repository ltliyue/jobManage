<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>采集结果搜索</title>
<!-- Bootstrap core CSS -->
<link href="<%=path%>/resources/css/new_version/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/new_version/site.css"
	rel="stylesheet">
<!-- Bootstrap core JavaScript
    ================================================== -->
<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
<script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
<script src="<%=path%>/resources/js/new_version/script.js"></script>
<script src="<%=path%>/resources/js/new_version/site.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>

</head>

<script type="text/javascript">
function lfy()
{
	var searcText = $("#searcText").val();
	
	var page=(parseInt($("#dq").html())-1);
	
	if(page<=0)
	{
		//alert("超过了， 不能再上一页了");
		 return;
	}
	
	//alert(page);
	
	if(searcText=="")
		{
			alert("请输入搜索企业名称！");
			return;
		}
	
	dataSearch(page,'*');
}

function nfy()
{
	var searcText = $("#searcText").val();
	
	var page=(parseInt($("#dq").html())+1);
	var pageCount=(parseInt($("#zs").html()));
	
	if(page>pageCount)
		{
		//alert("超过了， 不能再下一页了");
			return;
		}
	
	//alert(page);
	
	if(searcText=="")
		{
			alert("请输入搜索企业名称！");
			return;
		}
	
	dataSearch(page,'*');
}

function searchSetUp(flag)
{
	$("#sssz").val(flag);
	
	//alert($("#sssz").val());
}


	function dataVersion(flag) {
		$("#sjbb").val(flag);
		//alert($("#sjbb").val());
	}

	function searchTerms(flag) {
		$("#sstj").val(flag);
		//alert($("#sstj").val());
	}

	function dataSearch(currentpage, siteType) {
		/* var searcTextw = $("#searcText").val();
		alert(searcTextw);
		
		$("#hidschtext").val(searcTextw);
		
		var searcText = $("#hidschtext").val(); */
		
		var searcText = $("#searcText").val();
		//alert(searcText);
		var searchTerms=$("#sstj").val();
		var dataVersion=$("#sjbb").val();
		var searchSetUp=$("#sssz").val();
		

		if (searcText == "") {
			//alert("请输入搜索企业名称！");
			//return;
			searcText="*";
		}

		//alert(siteType);
		//var siteType2 = $("#siteType2").find('option:selected').text();
		//var status = demo.check(false);
		//	if(status==true){
		//var url = "<%=path%>/datasearch/searchResult?searcText=" + searcText+ "&pageno=" + currentpage;
		var url = "<%=path%>/datasearch/searchResult?pageno=" + currentpage;
		//+"&siteType2="+siteType2;
		var data1 = {
			"siteType" : siteType,
			"searchTerms":searchTerms,
			"dataVersion":dataVersion,
			"searchSetUp":searchSetUp,
			"searcText":searcText
		};
		//$("#form").serialize(); 
		//alert(data1);
		$.post(url, data1, function(data) {
			json = eval('(' + data + ')');
			//alert(json);
			//alert(json[0].sb); 
			$("#divResult").css('display', 'block');
			$("#divResult").html(json[0].sb);
			$("#zys").html(json[0].zys);
			$("#dq").html(currentpage);
			$("#zs").html(json[0].PageCount);
			if (siteType == "*") {
				$("#divFacet").html(json[0].facetResult);
			}

			if ((parseInt(json[0].PageCount)) > 0) {
				$("#navdiv").css('display', 'block');
			} else {
				$("#navdiv").css('display', 'none');
			}
			if ((parseInt(json[0].PageCount)) > 1) {
				$("#nfy").removeAttr("class");
				$("#lfy").removeAttr("class");
			}
			if ((parseInt(json[0].PageCount)) == 1) {
				$("#lfy").attr("class", "disabled");
				$("#nfy").attr("class", "disabled");
			}

			if ((parseInt(json[0].PageCount)) == (parseInt(currentpage))) {
				$("#nfy").attr("class", "disabled");
			}
			if ((parseInt(currentpage)) == 1) {
				$("#lfy").attr("class", "disabled");
			}
			
			$(".nav-list dd").click(function(){
		  		$(this).addClass('active')
		  		.siblings().removeClass('active');
			 });
		});
	}
</script>

<title>采集搜索</title>
</head>
<body style="margin: 0; padding: 0">
	<!-- 头部 -->
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!-- 内容-->

	<div class="content_waikuang">
		 <div class="container-fluid search_select select-result"
			style="padding-left: 0">
			<h2>数据搜索</h2>

			<dl>
				<dt style="line-height: 28px">已选条件：</dt>
				<dd class="select-no"></dd>
			</dl>

		</div>
		<!-- 搜索文字隐藏域 -->
		<input type="hidden" id="hidschtext" ></input>
		<!-- 搜索设置隐藏域 -->
		<input type="hidden" id="sssz" value='1'></input>
		<!-- 数据版本隐藏域 -->
		<input type="hidden" id="sjbb" value='global_extract_content'></input>
		<!-- 搜索条件隐藏域 -->
		<input type="hidden" id="sstj" value='hbase_corp_name'></input>

		<div style="width: 100%; margin: 0 1%;">
			<div class="row" style="clear: both;margin-left:-40px !important;">
				<div class="span3 " style="width: 18%; margin-left:22px">
					<div class="well content_left">
						<h4>筛选器</h4>
						<!-- <dl class="nav nav-list">
						<dt class="nav-header">搜索</dt>
						<dd class="nav_leftinput" style="display: block">
							<input name="" type="text" style="width: 94%; border-radius: 0px">
							<img src="../../../img/new_version/search1.png" />
						</dd>
					</dl> -->
						<dl class="nav nav-list" id="select1">
							<dt class="nav-header">搜索设置</dt>

							<dd class="active">
								<span></span><a href="javascript:void(0)"
									onclick="searchSetUp(1)">精确匹配</a>
							</dd>
							<dd>
								<span></span><a href="javascript:void(0)"
									onclick="searchSetUp(2)">模糊匹配</a>
							</dd>

							<dd>
								<span></span><a href="javascript:void(0)"
									onclick="searchSetUp(3)">精确模糊</a>
							</dd>

						</dl>
						<dl class="nav nav-list" id="select2">
							<dt class="nav-header">数据版本</dt>

							<dd class="active">
								<span></span><a href="javascript:void(0)"
									onclick="dataVersion('global_main_version')">主数据版本</a>
							</dd>

							<dd>
								<span></span><a href="javascript:void(0)"
									onclick="dataVersion('global_deliver_version')">交付数据版本</a>
							</dd>

						</dl>

						<dl class="nav nav-list" id="select3">
							<dt class="nav-header">搜索条件</dt>

							<dd class="active">
								<span></span> <a href="javascript:void(0)"
									onclick="searchTerms('hbase_corp_name')">企业名称</a>
							</dd>
							<!-- <dd>
								<span></span><a href="javascript:void(0)"
									onclick="searchTerms('hbase_shop_name')">店铺名称</a>
							</dd> -->
							<dd>
								<span></span><a href="javascript:void(0)"
									onclick="searchTerms('hbase_address')">企业地址</a>
							</dd>
							<dd>
								<span></span><a href="javascript:void(0)"
									onclick="searchTerms('hbase_corp_ind')">法定代表人</a>
							</dd>

						</dl>
					</div>
				</div>

				<div class="span9" style="width: 75%">


					<input name="" id="searcText" type="text" style="width: 84%"
						placeholder="请输入企业名称" class="content_qyjyxxinput">
					<button type="button" class="btn btn-primary content_qyjyxx"
						onclick="dataSearch(1,'*')">搜索</button>
					<div class="content_zhxxcx">
						<div class="content_zhxxcxr" id="divFacet">
							<!-- <ul>网站类型
							<li><a>喝咖啡啥1</a><span>1</span></li>
							<li><a>喝咖啡啥2</a><span>10</span></li>
							<li><a>喝咖啡啥3</a><span>10</span></li>
							<li><a>喝咖啡啥4</a><span>10</span></li>							
						</ul> -->
						</div>

						<div class="content_zhxxcx" id="divResult" style="display:none"></div>

					</div>
				</div>
			</div>
			<div id="navdiv" style="display: none">
				<nav align="center">
					<ul class="pager">

						<li id="lfy" class="disabled"><a href="#"
							aria-label="Previous " onclick="lfy()"><span
								aria-hidden="true">&larr;上一页</span></a></li>

						<li id="nfy" class="disabled"><a href="#" aria-label="Next"
							style="margin-left: 20px" onclick="nfy()"><span
								aria-hidden="false">下一页&rarr;</span> </a></li>
						<span style="margin-left: 20px">当前页:<span id="dq"></span></span>
						<span style="margin-left: 20px">总页数:<span id="zs"></span></span>
						<span style="margin-left: 20px">总数量:<span id="zys"></span></span>
					</ul>
				</nav>
			</div>
		</div>
</body>
</html>
