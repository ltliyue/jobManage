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
<script type="text/javascript"
	src="<%=path%>/resources/js/jquery-ui-min-1.9.js"></script>
</head>
<script type="text/javascript">

	function searchSetUp(flag)
	{
		$("#sssz").val(flag);		
		//alert($("#sssz").val());
	}

	function dataVersion(flag) {
		if(flag!='global_deliver_version') {
			$("#demandId").val("");
			$("#publishId").val("");
		}
		$("#sjbb").val(flag);
		//alert($("#sjbb").val());
	}

	function searchTerms(flag) {
		$("#sstj").val(flag);
		//alert($("#sstj").val());
	}

function dataSearch2(text){
	var searcText = $("#searcText").val();
	
	 if(searcText=="")
		{
			alert("请输入搜索文字！");
			return;
		} 
	 
	 $("#divLoding").css('display', 'block');	 
	 var searchTerms=$("#sstj").val();
	 var dataVersion=$("#sjbb").val();
	 var searchSetUp=$("#sssz").val();
	
	//alert(searcText);
	//var siteType2 = $("#siteType2").find('option:selected').text();
	//var status = demo.check(false);
  //	if(status==true){

 <%--  	var url = "<%=path%>/datasearch/searchResultEnterInfo2?searcText=";	
 	 if(text==0)
			 {
			  url=url+searcText;
			 }
		 else
			 {
			   url=url+text;
			   //如果需要保留上次的输入
			   url=url+"&flag:1";
			 } --%>
 	 
 	var url = "<%=path%>/datasearch/searchResultEnterInfo3";	
 	var endsearcText="";
	 if(text==0)
			 {
		 		endsearcText=searcText;
			  //url=url+searcText;
			 }
		 else
			 {
			   //url=url+text;
			   //如果需要保留上次的输入
			   endsearcText=text;
			   url=url+"?flag:1";
			 }
		 
		 //+"&siteType2="+siteType2;
		 var data1 ={"searcText":endsearcText,
				    "searchTerms":searchTerms,
					"dataVersion":dataVersion,
					"searchSetUp":searchSetUp,
					"deliver.demandId":$("#demandId").val(),
					"deliver.publishId":$("#publishId").val()
				 };
			 //$("#form").serialize();
			$.post(url,data1,function(data){		
				//alert(data);
				// $("#divResults").append(data);
				$("#divLoding").css('display', 'none');
				$("#divResults").css('display', 'block');
				json = eval('('+data+')');
				 $("#divResults").html(json);
				
			
				 var allEle = $(':header[class*="headline"]');
				    var headLen = allEle.length;
				    var ddArr = [];
				    //根据页面内容生成slide导航；
				    allEle.each(function(i){
				        var sideIndex,
				            sideName,
				            ddId,
				            highlight='',
				            ddClass,
				            sideAnchor;
				        sideName = $(this).find('.headline-content').text();
				        if($(this).hasClass('headline-1')){
				            sideAnchor = sideIndex = $(this).find('.anchor-1').eq(0).attr('name');
				            ddClass = 'sideCatalog-item1';
				        }else{
				            sideAnchor = $(this).find('.anchor-2').eq(0).attr('name');
				            sideIndex = sideAnchor.replace('-','.');
				            ddClass = 'sideCatalog-item2';
				        }
				        ddId = 'sideToolbar-item-0-'+ sideAnchor;
				        if(i==0){
				            highlight = 'highlight';
				        }
				        var ddHtml = '<dd id="'+ ddId +'" class="'+ddClass + ' ' + highlight +'">'
				                +       '<span class="sideCatalog-index1">'+ sideIndex +'</span>'
				                +       '<a class="nslog:1026" onclick="return false;" title="part'+sideAnchor+'" href="#'+sideAnchor+'">'+ sideName +'</a>'
				                +       '<span class="sideCatalog-dot"></span>'
				                +    '</dd>';
				        ddArr.push(ddHtml);
				    });
				    $('#sideCatalog-catalog dl').html(ddArr.join(''));

				    //设置导航的位置
				    var slideTop = $(window).height() - $('.slide').height();
				    $('.slide').css('top',slideTop);

				    var slideInnerHeight = $('#sideCatalog-catalog dl').height();
				    var slideOutHeight = $('#sideCatalog-catalog').height();
				    var enableTop = slideInnerHeight - slideOutHeight;
				    var step = 50;
				    //点击向上的按钮
				    $('#sideCatalog-down').bind('click', function () {
				        //alert('shang');
				    	if ($(this).hasClass('sideCatalog-down-enable')) {
				            if ((enableTop - Math.abs(parseInt($('#sideCatalog-catalog dl').css('top')))) > step) {
				                $('#sideCatalog-catalog dl').stop().animate({'top': '-=' + step}, 'fast');
				                $('#sideCatalog-up').removeClass('sideCatalog-up-disable').addClass('sideCatalog-up-enable');
				            } else {
				                $('#sideCatalog-catalog dl').stop().animate({'top': -enableTop}, 'fast');
				                $(this).removeClass('sideCatalog-down-enable').addClass('sideCatalog-down-disable');
				            }
				        } else {
				            return false;
				        }
				    }) 
				    //点击向下的按钮
				     $('#sideCatalog-up').bind('click', function () {
				        if ($(this).hasClass('sideCatalog-up-enable')) {
				            if (Math.abs(parseInt($('#sideCatalog-catalog dl').css('top'))) > step) {
				                $('#sideCatalog-catalog dl').stop().animate({'top': '+=' + step}, 'fast');
				                $('#sideCatalog-down').removeClass('sideCatalog-down-disable').addClass('sideCatalog-down-enable');
				            } else {
				                $('#sideCatalog-catalog dl').stop().animate({'top': '0'}, 'fast');
				                $(this).removeClass('sideCatalog-up-enable').addClass('sideCatalog-up-disable');
				            }
				        } else {
				            return false;
				        }
				    }) 

				    //点击导航中的各个目录
				    $('#sideCatalog-catalog dl').delegate('dd', 'click', function () {
				        var index = $('#sideCatalog-catalog dl dd').index($(this));
				        scrollSlide($(this), index);
				        var ddIndex = $(this).find('a').stop().attr('href').lastIndexOf('#');
				        var ddId = $(this).find('a').stop().attr('href').substring(ddIndex+1);
				        var windowTop = $('a[name="' + ddId + '"]').offset().top;
				        $('body,html').animate({scrollTop: windowTop}, 'fast');
				    })

				    //滚动页面，即滚动条滚动
				    $(window).scroll(function () {
				        if($(this).scrollTop()>$(this).height()){
				            $('.content_slide').show();
				        }else{
				            $('.content_slide').show();
				        }
				        for (var i=headLen-1; i>=0; i--) {
				            if ($(this).scrollTop() >=allEle.eq(i).offset().top - allEle.eq(i).height()) {
				                var index = i;
				                $('#sideCatalog-catalog dl dd').eq(index).addClass('highlight').siblings('dd').removeClass('highlight');
				                scrollSlide($('#sideCatalog-catalog dl dd').eq(index), index);
				                return false;
				            } else {
				                $('#sideCatalog-catalog dl dd').eq(0).addClass('highlight').siblings('dd').removeClass('highlight');
				            }
				        }
				    })

				    //导航的滚动，以及向上，向下按钮的显示隐藏
				    function scrollSlide(that, index){
				        if (index < 9) {
				            $('#sideCatalog-catalog dl').stop().animate({'top': '0'}, 'fast');
				            $('#sideCatalog-down').removeClass('sideCatalog-down-disable').addClass('sideCatalog-down-enable');
				            $('#sideCatalog-up').removeClass('sideCatalog-up-enable').addClass('sideCatalog-up-disable');
				        } 
				        /*  else if (index > 21) {
				            $('#sideCatalog-catalog dl').stop().animate({'top': -enableTop}, 'fast');
				            $('#sideCatalog-down').removeClass('sideCatalog-down-enable').addClass('sideCatalog-down-disable');
				             $('#sideCatalog-up').removeClass('sideCatalog-up-disable').addClass('sideCatalog-up-enable');
				         } */
				        else {
				            var dlTop = parseInt($('#sideCatalog-catalog dl').css('top')) + slideOutHeight / 2 - (that.offset().top - $('#sideCatalog-catalog').offset().top);
				            $('#sideCatalog-catalog dl').stop().animate({'top': dlTop}, 'fast');
				            $('#sideCatalog-down').removeClass('sideCatalog-down-disable').addClass('sideCatalog-down-enable');
				            $('#sideCatalog-up').removeClass('sideCatalog-up-disable').addClass('sideCatalog-up-enable');
				        }
				    }

				    //置顶
				    $('#sideToolbar-up').bind('click', function(){
				        $('body,html').animate({scrollTop: 0}, 'fast');
				    })

				    //slide内容的显示与隐藏
				    $('#sideCatalogBtn').bind('click', function(){
				        if($(this).hasClass('sideCatalogBtnDisable')){
				            $(this).removeClass('sideCatalogBtnDisable');
				            $('#sideCatalog').css('visibility','visible');
				        }else{
				            $(this).addClass('sideCatalogBtnDisable');
				            $('#sideCatalog').css('visibility','hidden');
				        }
				    })
			

				}
			);
	}
  
  //选择需求和版本号
  function versionSelect(demandId, publishId,demandName) {
	  $("#myModal").modal('hide');
	  $("#demandId").val(demandId);
	  $("#publishId").val(publishId);
	  $("#selectB").html($("#selectB").html()+"("+demandName+"-"+publishId+")");
  }
</script>
<body style="margin: 0; padding: 0">
	<!-- 头部 -->
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!-- 内容-->
	 <div  class="content_waikuang">
	  <div class="container-fluid search_select select-result" style="padding-left:0">
          		<h2>企业整合</h2>
                  
                 <dl>
				   <dt style="line-height: 28px">已选条件：</dt>
				    <dd class="select-no"></dd>
			    </dl>             
            
          </div> 
          
         <!-- 搜索设置隐藏域 -->
		<input type="hidden" id="sssz" value='1'></input>
		<!-- 数据版本隐藏域 -->
		<input type="hidden" id="sjbb" value='global_main_version'></input>
		<!-- 搜索条件隐藏域 -->
		<input type="hidden" id="sstj" value='hbase_corp_name'></input>
		<!-- 需求ID -->
		<input type="hidden" id="demandId" name="deliver.demandId" value=''></input>
		<!-- 交付版本号 -->
		<input type="hidden" id="publishId" name="deliver.publishId" value=''></input>
		
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
								<span></span><a href="javascript:void(0)" data-toggle="modal" data-target="#myModal"
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
	             <input name="" id="searcText" type="text" style="width: 84%" placeholder="请输入企业名称"
	  				class="content_qyjyxxinput">
				<button type="button" class="btn btn-primary content_qyjyxx"  onclick="dataSearch2(0)">搜索</button>
<div id="divLoding"  style="display:none"  class="loading02"><span>数据整合中...</span><img src="<%=path%>/resources/img/loading02.gif" /> </div>
				<div class="content_qyjyxxnr1">
					<div class="content_qyjyxxnr2"></div>

					<div class="content_qyjyxxnr" id="divResults" style="display:none">

						

					</div>
				
				</div>
			</div>
		<div class="content_slide" id="divSlide" style="display:none">
				<div id="sideToolbar" style="position: fixed; bottom: 0">
					<div id="sideCatalog" class="sideCatalogBg"
						style="visibility: visible;">
						<div id="sideCatalog-sidebar">
							<div class="sideCatalog-sidebar-top"></div>
							<div class="sideCatalog-sidebar-bottom"></div>
						</div>
						<div id="sideCatalog-updown" style="visibility: visible;">
							<div id="sideCatalog-up" class="sideCatalog-up-disable"
								title="向上翻页"></div>
							<div id="sideCatalog-down" class="sideCatalog-down-enable"
								title="向下翻页"></div>
						</div>
						<div id="sideCatalog-catalog">
							<dl style="width: 175px; zoom: 1">
								
                    <dd id="sideToolbar-item-0-1" class="sideCatalog-item2 heightlight">
                        <span class="sideCatalog-index1">1</span>
                        <a class="nslog:1026" onclick="return false;" title="part1" href="#1">part1</a>
                        <span class="sideCatalog-dot"></span>
                    </dd>
                    <dd id="sideToolbar-item-0-2" class="sideCatalog-item1">
                        <span class="sideCatalog-index1">2</span>
                        <a class="nslog:1026" onclick="return false;" title="part2" href="#2">part2</a>
                        <span class="sideCatalog-dot"></span>
                    </dd>
                    <dd id="sideToolbar-item-0-2-1" class="sideCatalog-item2">
                        <span class="sideCatalog-index1">2-1</span>
                        <a class="nslog:1026" onclick="return false;" title="part2" href="#2-1">part2-1</a>
                        <span class="sideCatalog-dot"></span>
                    </dd>
                   
							</dl>
						</div>
					</div>
					<a id="sideCatalogBtn" class="" style="visibility: visible;"
						href="javascript:void(0);"></a> <a id="sideToolbar-up"
						style="visibility: visible;" title="返回顶部"
						href="javascript:void(0)"></a>
				</div>
			</div>
		
		</div>
	</div>
	</div>
	<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display:none;">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <iframe src="${webRoot }/datasearch/deliverList" width="100%" height="600px" frameborder="0"></iframe>
    </div>
  </div>
  </div>
</body>
</html>
