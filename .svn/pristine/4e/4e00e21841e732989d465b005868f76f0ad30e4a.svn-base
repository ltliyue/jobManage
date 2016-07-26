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

<script src="taskInstanceDetail.js"></script>
<link href="<%=path%>/resources/css/new_version/index.css" rel="stylesheet">
</head>
<script type="text/javascript">
$(document).ready(function(){
	initChart();
	all();
});
var downloadNum,navPageDownload,listPageDownload,detailPageDownload;
var pieXdata,pieYdata;
var chart2xdata,chart2ydata,chart3xdata,chart3ydata,chart4xdata,chart4ydata;
function initChart(){
	var taskInstanceId = $("#taskInstanceId").val();
	var url = "<%=path %>/urlMonitor/getTaskInstanceInfoByTaskInstanceId?taskInstanceId="+taskInstanceId;
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
			navPageDownload = parseInt(json[0].nav);
			listPageDownload = parseInt(json[0].list);
			detailPageDownload = parseInt(json[0].info);
			if(navPageDownload==0&&listPageDownload==0&&detailPageDownload==0){
				downloadNum = parseInt(json[0].other);
			}
			if(navPageDownload==0&&listPageDownload==0&&detailPageDownload==0){
				pieXdata = new Array('未区分URL类型');	
				pieYdata = new Array({value:downloadNum,name:'未区分URL类型'});			
			}else{
				pieXdata = new Array('导航页','列表页','详情页');
				pieYdata = new Array({value:navPageDownload,name:'导航页'},{value:listPageDownload,name:'列表页'},{value:detailPageDownload,name:'详情页'});
			}	
			loadChart1();//载入图表
	 	}
	});
}

function changePeriod(type){
	initChart234(type);
}

function loadChart1(){	
	require.config({
		paths: {
			echarts: '<%=path%>/resources/js/echarts'
		}
	});
	require(['echarts', 'echarts/chart/pie', 'echarts/chart/bar','echarts/chart/line','echarts/chart/map' ],
	function(ec) {	
		var myChart = ec.init(document.getElementById('chart1'));
        option = {
	        title : {
	            text: 'URL类型分布',
	            show:false,
	            x:'center'
	        },
	        tooltip : {
	            trigger: 'item',
	            formatter: "{a} <br/>{b} : {c} ({d}%)"
	        },
	        legend: {
	            orient : 'vertical',
	             x : '400',
	            y:'320',
	            data:pieXdata
	        },
	        
	        calculable : true,
	        series : [
	            {
	                name:'页面下载量',
	                type:'pie',
	                radius : '55%',
	                center: ['50%', '60%'],
	               
	                itemStyle:{
	                    normal:{
	                        label:{
	                            show:true,
	                            formatter:'{b}:{c}({d}%)'
	                        }
	                    }
	                },
	                data:pieYdata
	            }
	        ]
	    };
		myChart.setOption(option);
	});
}

function loadChart234(){	
	require.config({
		paths: {
			echarts: '<%=path%>/resources/js/echarts'
		}
	});
	require(['echarts', 'echarts/chart/pie', 'echarts/chart/bar','echarts/chart/line','echarts/chart/map' ],
	function(ec) {			
		var myChart2 = ec.init(document.getElementById('chart2'));
		var myChart3 = ec.init(document.getElementById('chart3'));
		var myChart4 = ec.init(document.getElementById('chart4'));
		option2 = {
            tooltip : {
                trigger: 'axis',
                showDelay: 0             // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
            },
            legend: {
                y : -30,
                data:['全局合并','合并增量（条）','合并数据']
            },
            toolbox: {
                y : -30,
                show : true,
                feature : {
                    mark : {show: true},
                    dataZoom : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            dataZoom : {
                show : false,
                realtime: true,
                start : 50,
                end : 100,
                position:"top"
            },
           	grid: {
                x: 40,
                y:5,
                x2:20,
                y2:25
            },
            xAxis : [
                {
                    type : 'category',
                    //position:'bottom',
                    boundaryGap : true,
                    axisLabel:{show:true},
                    axisTick: {onGap:false},
                    splitLine: {show:true},
                    //x轴数据
                    data : chart2xdata
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    scale:true,
                    splitNumber: 8,
                    boundaryGap: [0.05, 0.05],
//                     axisLabel: {
//                         formatter: function (v) {
//                             return Math.round(v/10000)
//                         }
//                     },
                    splitArea : {show : true}
                }
            ],
            series : [
                {
                    name:'下载总数',
                    type:'line',
                    symbol: 'none',
                	// 每个时间段的下载总数数据
                    data:chart2ydata,
                    markLine : {
                        symbol : 'none',
                        itemStyle : {
                            normal : {
                                color:'#1e90ff',
                                label : {
                                    show:false
                                }
                            }
                        },
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                }
            ]
        };      
		myChart2.setOption(option2);
		
		option3 = {
            tooltip : {
                trigger: 'axis',
                showDelay: 0             // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
            },
            legend: {
                y : -30,
                data:['全局合并','合并增量（条）','合并数据']
            },
            toolbox: {
                y : -30,
                show : true,
                feature : {
                    mark : {show: true},
                    dataZoom : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            dataZoom : {
                show : false,
                realtime: true,
                start : 50,
                end : 100,
                position:"top"
            },
           	grid: {
                x: 40,
                y:5,
                x2:20,
                y2:25
            },
            xAxis : [
                {
                    type : 'category',
                    //position:'bottom',
                    boundaryGap : true,
                    axisLabel:{show:true},
                    axisTick: {onGap:false},
                    splitLine: {show:true},
                    //x轴数据
                    data: chart3xdata
                    //data : ['2016-05-01','2016-05-02','2016-05-03','2016-05-04','2016-05-05','2016-05-06','2016-05-07','2016-05-08']
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    scale:true,
                    splitNumber: 8,
                    boundaryGap: [0.05, 0.05],
//                     axisLabel: {
//                         formatter: function (v) {
//                             return Math.round(v/10000)
//                         }
//                     },
                    splitArea : {show : true}
                }
            ],
            series : [
                {
                    name:'下载总数',
                    type:'line',
                    symbol: 'none',
                	// 每个时间段的下载总数数据
                    //data:['100000','200000','300000','150000','100000','200000','300000','150000'],
                    data:chart3ydata,
                    markLine : {
                        symbol : 'none',
                        itemStyle : {
                            normal : {
                                color:'#1e90ff',
                                label : {
                                    show:false
                                }
                            }
                        },
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                }
            ]
        };      
		myChart3.setOption(option3);
			
		option4 = {
            tooltip : {
                trigger: 'axis',
                showDelay: 0             // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
            },
            legend: {
                y : -30,
                data:['全局合并','合并增量（条）','合并数据']
            },
            toolbox: {
                y : -30,
                show : true,
                feature : {
                    mark : {show: true},
                    dataZoom : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            dataZoom : {
                show : false,
                realtime: true,
                start : 50,
                end : 100,
                position:"top"
            },
           	grid: {
                x: 40,
                y:5,
                x2:20,
                y2:25
            },
            xAxis : [
                {
                    type : 'category',
                    //position:'bottom',
                    boundaryGap : true,
                    axisLabel:{show:true},
                    axisTick: {onGap:false},
                    splitLine: {show:true},
                    //x轴数据
                    //data : ['2016-05-01','2016-05-02','2016-05-03','2016-05-04','2016-05-05','2016-05-06','2016-05-07','2016-05-08']
                	data:chart4xdata
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    scale:true,
                    splitNumber: 8,
                    boundaryGap: [0.05, 0.05],
//                     axisLabel: {
//                         formatter: function (v) {
//                             return Math.round(v/10000)
//                         }
//                     },
                    splitArea : {show : true}
                }
            ],
            series : [
                {
                    name:'下载总数',
                    type:'line',
                    symbol: 'none',
                	// 每个时间段的下载总数数据
                    //data:['100000','200000','300000','150000','100000','200000','300000','150000'],
                    data:chart4ydata,
                    markLine : {
                        symbol : 'none',
                        itemStyle : {
                            normal : {
                                color:'#1e90ff',
                                label : {
                                    show:false
                                }
                            }
                        },
                        data : [
                            {type : 'average', name: '平均值'}
                        ]
                    }
                }
            ]
        };      
		myChart4.setOption(option4);
		
	 	myChart2.connect([myChart3, myChart4]);
 		myChart3.connect([myChart2, myChart4]);
 		myChart4.connect([myChart2, myChart3]);
	});
}
function all(){
	var taskInstanceId = $("#taskInstanceId").val();
 	var url = "<%=path %>/urlMonitor/getTaskInstanceUrlListByPageNo?page=1&taskInstanceId="+taskInstanceId;
 	$.ajax({
	 	type : "POST",
	 	url : url,
	 	contentType: "application/json; charset=utf-8",
	 	dataType: "text",
	 	data : "",
	 	success : function(data) {
			json = eval('('+data+')');
			$("#tbody").empty();
		 	$("#tbody").html(json[0].sb);
	 		$("#zys").html(json[0].zys);
		 	$("#dq").html(json[0].pages);
		 	//$('.tree').treegrid();
		 	if(json[0].zys>10){
			 	$("#nfy").removeAttr("class");
		 	}
	 	}
	});
};
	
function lfy(){
	var taskInstanceId = $("#taskInstanceId").val();
 	if($("#lfy").attr("class")!="disabled"){
	 	var url = "<%=path %>/urlMonitor/getTaskInstanceUrlListByPageNo?page="+(parseInt($("#dq").html())-1)+"&taskInstanceId="+taskInstanceId;
	 	$.ajax({
		 	type : "POST",
		 	url : url,
		 	contentType: "application/json; charset=utf-8",
		 	dataType: "text",
		 	data : "",
		 	success : function(data) {
				json = eval('('+data+')');
				$("#tbody").empty();
			 	$("#tbody").html(json[0].sb);
			 	$("#zys").html(json[0].zys);
			 	$("#dq").html(json[0].pages);
		 		//$('.tree').treegrid();
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
	var taskInstanceId = $("#taskInstanceId").val();
	if($("#nfy").attr("class")!="disabled"&&$("#dq").html()!=''){
		var url = "<%=path %>/urlMonitor/getTaskInstanceUrlListByPageNo?page="+(parseInt($("#dq").html())+1)+"&taskInstanceId="+taskInstanceId;
		$.ajax({
			type : "POST",
			url : url,
			contentType: "application/json; charset=utf-8",
			dataType: "text",
			data : "",
			success : function(data) {
				json = eval('('+data+')');
				$("#tbody").empty();
				$("#tbody").html(json[0].sb);
				$("#zys").html(json[0].zys);
				$("#dq").html(json[0].pages);
				//$('.tree').treegrid();
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
<input type="hidden" id="taskInstanceId" value="${taskInstanceId} "/>
<jsp:include page="../topBase.jsp"></jsp:include>
<!--内容开始-->
<div class="content_waikuang">
<div class="row" style="clear:both;margin-left:0">
<!--数据资源开始-->
<div class="index_top">
<h2>任务实例详情 - 任务实例Id ${taskInstanceId}</h2>
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

	<div class="index_content_two" style="margin-top:30px">
		<div class="index_riqileft" style="float:left;width:25%">
            <ul>
              <li onclick="changePeriod(0)" class="riqi_active">1小时</li>
              <li onclick="changePeriod(1)" >1天</li>
            </ul>
        </div>
        <div class="index_riqiright" style="float:left;width:25%">
            <form>
              <input id="start" value="${beginTime }" name="start" class="laydate-icon" onClick="laydate({istime: true,isclear: false, format: 'YYYY-MM-DD hh:mm:ss'})" type="text">
              <b>-</b>
              <input class="laydate-icon" value="${endTime }" onClick="laydate({istime: true,isclear: false, format: 'YYYY-MM-DD hh:mm:ss',choose:function(datas){reloadData();}});" id="end"  name="end" type="text">
            </form>
        </div>
    </div>
    
<div class="index_content">
	<div class="index_content_two" style="margin-top:50px; clear:both;height:280px;">
		<div class="index_content_twoleft" style="height:310px">
			<h2>URL类型分布</h2>
			<div id="chart1" style="height:280px"></div>
        </div>
        <div class="index_content_tworight" style="height:310px">
			<h2>导航页</h2>
			<div id="chart2" style="height:280px;"></div>
		</div>
	</div>
		
          
	<div class="index_content_two" style="; margin-top:50px; clear:both;height:280px;">
		<div class="index_content_twoleft" style="height:310px">
			<h2>列表页</h2>
			<div id="chart3" style="height:280px;"></div>
        </div>
		<div class="index_content_tworight" style="height:310px">
			<h2>详情页</h2>
			<div id="chart4" style="width:90%;height:280px;"></div>
		</div>
	</div>
</div>    
    
</div>
	<!--列表展示开始-->
	<div class="row" style="clear:both; margin-left:0; margin-top:50px">
		<div class="span9" style="width:100%; margin-left:0">
          <table class="tree table table-hover table-bordered table-condensed">    
		    <tr class='success'><th width="60%">URL</th><th width="20%">URL类型</th><th width="20%">下载时间</th></tr>
		    <tbody id="tbody"></tbody>
		  </table>
		</div>
	</div>
		
	<div>
		<nav><ul class="pager">
			<li id="lfy" class="disabled">
				<a href="#" aria-label="Previous "  onclick="lfy()"><span aria-hidden="true">&larr;上一页</span></a>
			</li>
			   	
			<li id="nfy" class="disabled">
			    <a  href="#" aria-label="Next" style="margin-left:  20px" onclick="nfy()"><span aria-hidden="false">下一页&rarr;</span> </a>
			</li>
			<label style="margin-left: 20px">当前页:<span id="dq"></span></label>
			<label style="margin-left: 20px">总数量:<span id="zys"></span></label>
		</ul></nav>
	</div>
	<!--列表展示结束-->
</div>
<!--内容结束-->
<script src="<%=path %>/resources/laydate/laydate.js"></script>
<script>
!function(){
laydate.skin('molv');//切换皮肤，请查看skins下面皮肤库
laydate({elem: '#demo'});//绑定元素
}();
//日期范围限制
var start = {
    elem: '#start',
    format: 'YYYY-MM-DD',
    min: laydate.now(), //设定最小日期为当前日期
    max: '2099-06-16', //最大日期
    istime: true,
    istoday: false,
    choose: function(datas){
         end.min = datas; //开始日选好后，重置结束日的最小日期
         end.start = datas //将结束日的初始值设定为开始日
    }
};
var end = {
    elem: '#end',
    format: 'YYYY-MM-DD',
    min: laydate.now(),
    max: '2099-06-16',
    istime: true,
    istoday: false,
    choose: function(datas){
        start.max = datas; //结束日选好后，充值开始日的最大日期
    }
};
laydate(start);
laydate(end);
//自定义日期格式
laydate({
    elem: '#test1',
    format: 'YYYY年MM月DD日',
    festival: true, //显示节日
    choose: function(datas){ //选择日期完毕的回调
        alert('得到：'+datas);
    }
});
//日期范围限定在昨天到明天
laydate({
    elem: '#hello3',
    min: laydate.now(-1), //-1代表昨天，-2代表前天，以此类推
    max: laydate.now(+1) //+1代表明天，+2代表后天，以此类推
});
$(document).ready(function(){
	initDatePicker();
});
function initDatePicker(){
	var taskInstanceId = $("#taskInstanceId").val();
	var url = "<%=path %>/urlMonitor/getTaskTimeByTaskInstanceId?taskInstanceId="+taskInstanceId;
 	$.ajax({
	 	type : "POST",
	 	url : url,
	 	contentType: "application/json; charset=utf-8",
	 	dataType: "text",
	 	data : "",
	 	success : function(data) {
			json = eval('('+data+')');
			$("#start").attr("value",json[0].startTime);
			$("#end").attr("value",json[0].endTime);
			initChart234(0);
	 	}
	});
}

function initChart234(type){
	var taskInstanceId = $("#taskInstanceId").val();
	var startTime = $("#start").val();
	var endTime = $("#end").val();	
	var url = "<%=path %>/urlMonitor/getUrlDownloadListByTaskInstancceIdAndType?taskInstanceId="+taskInstanceId+"&startTime="+startTime+"&endTime="+endTime+"&type="+type;
 	$.ajax({
	 	type : "POST",
	 	url : url,
	 	contentType: "application/json; charset=utf-8",
	 	dataType: "text",
	 	data : "",
	 	success : function(data) {
			json = eval('('+data+')');
			//获取到任务实例详情
			chart2xdata = createChartConf(json[0].list2x.toString());
			chart2ydata = createChartConf(json[0].list2y.toString());
			chart3xdata = createChartConf(json[0].list3x.toString());
			chart3ydata = createChartConf(json[0].list3y.toString());
			chart4xdata = createChartConf(json[0].list4x.toString());
			chart4ydata = createChartConf(json[0].list4y.toString());
			loadChart234();
	 	}
	});
}
function createChartConf(data){
	var dataArr = new Array(data);
	dataArr = data.split(",");
	return dataArr;
}
</script>
</body>
</html>
