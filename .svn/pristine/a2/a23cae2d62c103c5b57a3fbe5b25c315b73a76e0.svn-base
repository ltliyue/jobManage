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
<link href="<%=path%>/resources/validform/css/style.css" rel="stylesheet">
<link href="<%=path%>/resources/css/dashboard.css" rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-select.css" rel="stylesheet">
<!-- Bootstrap core JavaScript ================================================== -->
<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
<script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
<script src="<%=path%>/resources/js/new_version/site.js"></script>
<script src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.js"></script>
<script src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-select.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/jquery-ui-min-1.9.js"></script>
<script src="<%=path%>/resources/js/new_version/script.js"></script>
<script src="<%=path%>/resources/js/echarts/echarts.js"></script>
<link href="<%=path%>/resources/css/new_version/index.css" rel="stylesheet">
</head>
<script type="text/javascript">
$(document).ready(function(){
	init();
	initChart1();
	//loadCharts();//载入图表
});
var chart1Xdata = new Array();
var chart1Ydata = new Array();
function init(){
	var taskId = $("#taskId").val();
	var url = "<%=path %>/urlMonitor/getTaskInfoByTaskId?taskId="+taskId;
 	$.ajax({
	 	type : "POST",
	 	url : url,
	 	contentType: "application/json; charset=utf-8",
	 	dataType: "text",
	 	data : "",
	 	success : function(data) {
			json = eval('('+data+')');
			//获取到任务实例详情
			$("#OTHER").html(json[0].other+"条");
			$("#NAV").html(json[0].nav+"条");
			$("#LIST").html(json[0].list+"条");
			$("#INFO").html(json[0].info+"条");
	 	}
	});
}
function initChart1(){
	var taskId = $("#taskId").val();
	var url = "<%=path %>/urlMonitor/getTaskInstanceInfoByTaskId?taskId="+taskId;
	$.ajax({
		type : "POST",
		url : url,
		contentType: "application/json; charset=utf-8",
		dataType: "text",
		data : "",
		success : function(data) {
			json = eval('('+data+')');	
			console.log(json[0].xdata);
			console.log(json[0].ydata);
			var xdata = json[0].xdata.toString();
			var ydata = json[0].ydata.toString();
			console.log(xdata);
			console.log(ydata);
			chart1Ydata = createChartConf(ydata);
			chart1Xdata = createChartConf(xdata); 
			loadChart1();
		}
	});
}
function createChartConf(data){
	var dataArr = new Array();
	dataArr = data.split(",");
	return dataArr;
}
function loadCharts(){
	loadChart1();
}
function loadChart1(){
	require.config({
		paths: {
			echarts: '<%=path%>/resources/js/echarts'
		}
	});
    require(['echarts', 'echarts/chart/pie', 'echarts/chart/bar' ],
    function(ec) {
		var myChart = ec.init(document.getElementById('chart1'));
        option = {
			calculable : true,
			xAxis : [{
	    		axisLine:{show:false},
	    		axisTick:{show:false},
	  			splitLine:{show:false},
	   			axisLabel:{
	        		textStyle:{
	            		fontSize:18,
	            		fontWeight:700
	        		}
	    		},
	    		type : 'category',
	    		data : chart1Xdata,
	    		//data : ['83d94c97-5e8a-4c8f-80dd-110737520fd0','c9d83a73-627d-40ad-a1ca-de6fad0dd531','52e7c238-c100-4f51-a7c7-6b28f4e0971b','0490a426-a50c-42fc-a1a3-afb6904371bc','e16bbf6f-9dd7-4c4b-8a73-f48c79d7ff51','505874aa-6a60-41f4-a61a-e84fb7ee6ed3','597a6a13-1b4f-4fc7-9d54-2c347d878c77','54944bf0-007d-44de-b838-2632347753f9']				
	    		//data :['实例e16bbf6','实例c9d83a7','实例83d94c9','实例52e7c23','实例54944bf','实例597a6a1','实例505874a','实例0490a42']
			}],
			yAxis : [{
			    axisLine:{show:false},
			    axisTick:{show:false},
			    splitLine:{show:false},
			    show:false,
			    splitLine:{show:false},
			    type : 'value'
			}],
			series : [{
			   	itemStyle:{
				    normal:{
				        color:'#244f6f',
				        label:{
				            show:true,
				            formatter:'{c}条',
				             textStyle: {
				                fontSize:20
				            }
				        }
				    }
			   	},
			    name:'下载量',
			    type:'bar',
			    data:chart1Ydata,
			    //data:['18387','18648','3759','8579','1','16563','2735','16530'] 
			},
			]};
		myChart.setOption(option);   
    });
}
function all(){		 
 	var url = "<%=path %>/urlMonitor/getTaskInstanceListByPageNo?page=1";
 	$.ajax({
	 	type : "POST",
	 	url : url,
	 	contentType: "application/json; charset=utf-8",
	 	dataType: "text",
	 	data : "",
	 	success : function(data) {
			json = eval('('+data+')');
		 	$("#tbody").html(json[0].sb);
	 		$("#zys").html(json[0].zys);
		 	$("#dq").html(json[0].pages);
		 	$('.tree').treegrid();
		 	if(json[0].zys>10){
			 	$("#nfy").removeAttr("class");
		 	}
	 	}
	});
};

function lfy(){
 	if($("#lfy").attr("class")!="disabled"){
	 	var url = "<%=path %>/urlMonitor/getTaskInstanceListByPageNo?page="+(parseInt($("#dq").html())-1);
	 	$.ajax({
		 	type : "POST",
		 	url : url,
		 	contentType: "application/json; charset=utf-8",
		 	dataType: "text",
		 	data : "",
		 	success : function(data) {
				json = eval('('+data+')');
			 	$("#tbody").html(json[0].sb);
			 	$("#zys").html(json[0].zys);
			 	$("#dq").html(json[0].pages);
		 		$('.tree').treegrid();
			 	if(parseInt(json[0].zys)>parseInt($("#dq").html())*10){
					$("#nfy").removeAttr("class");
				}
			 	if(parseInt($("#dq").html())==1){
					$("#lfy").attr("class","disabled");
				}
			}
		});
	}
}
	
function nfy(){
	if($("#nfy").attr("class")!="disabled"&&$("#dq").html()!=''){
		var url = "<%=path %>/urlMonitor/getTaskInstanceListByPageNo?page="+(parseInt($("#dq").html())+1);
		$.ajax({
			type : "POST",
			url : url,
			contentType: "application/json; charset=utf-8",
			dataType: "text",
			data : "",
			success : function(data) {
				json = eval('('+data+')');
				$("#tbody").html(json[0].sb);
				$("#zys").html(json[0].zys);
				$("#dq").html(json[0].pages);
				$('.tree').treegrid();
				if(parseInt(json[0].zys)<=parseInt($("#dq").html())*10){
					$("#nfy").attr("class","disabled");
				}
				if(parseInt($("#dq").html())!=1){
					$("#lfy").removeAttr("class");
				}
			}
		});
	}
}
</script>
<body style="margin: 0; padding: 0">
<input type="hidden" id="taskId" value="${taskId}"/>
<jsp:include page="../topBase.jsp"></jsp:include>
<!--内容开始-->
<div class="content_waikuang">
<div class="row" style="clear:both;margin-left:0">
<!--数据资源开始-->
<div class="index_top">
<h2>任务详情 - 任务Id ${taskId}</h2>
<div class="well summary"  id="index_top_top"> 
	<ul>
        <li>
			<a href="javascript:void(0)"><span class="count">下载量总数<b id="OTHER"></b></span> </a>
		</li>
		<li>
			<a href="javascript:void(0)"><span class="count">导航页采集数<b id="NAV"></b></span> </a>
		</li>
		<li>
			<a href="javascript:void(0)"><span class="count">列表页采集数<b id="LIST"></b></span></a>
		</li>
		<li>
			<a href="javascript:void(0)"><span class="count">详情页采集数<b id="INFO"></b></span></a>
		</li>
	</ul>
</div>
</div>
<!--数据资源结束-->
<div class="index_content">
	<div class="index_content_two" style="margin-bottom:20px; clear:both;height:500px;">
		<div class="index_content_twoleft" style="width:100%;height:550px">
			<h2>任务实例柱状图</h2>
			<div id="chart1" style="width:100%;height:500px;"></div>
        </div>
	</div>
</div>        
</div> 
</div>
</body>
</html>
