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
<link href="<%=path%>/resources/css/dashboard.css" rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-select.css"
	rel="stylesheet">
<!-- Bootstrap core JavaScript
    ================================================== -->
<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
<script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>

<script src="<%=path%>/resources/js/new_version/site.js"></script>
<script
	src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.js"></script>
<script
	src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/bootstrap3.3.5/bootstrap-select.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/jquery-ui-min-1.9.js"></script>
<script src="<%=path%>/resources/js/new_version/script.js"></script>

<script src="<%=path%>/resources/js/echarts/echarts.js"></script>
<link href="<%=path%>/resources/css/new_version/index.css"
	rel="stylesheet">
</head>
<script type="text/javascript">


$(document).ready(function(){
	var d = ${mapData};
	drawChart(d);
	loadGeneralNum();
});

function drawChart(mapData){
	//var mapData = ${mapData};
	
	var axisData4 = mapData["taskDownloadIncr"]["axisData"];
	var valueArray4 = mapData["taskDownloadIncr"]["data"];
	var axisData3 = mapData["taskParseIncr"]["axisData"];
	var dataArrays3 = mapData["taskParseIncr"]["data"];
	
	var axisData7Item = mapData["extractItemInc"]["axisData"];
	var dataArrays7Item = mapData["extractItemInc"]["data"];
	var axisData7Record = mapData["extractRecordInc"]["axisData"];
	var dataArrays7Record = mapData["extractRecordInc"]["data"];
	
	var axisDataArray5 = mapData["wholeUrlParseDumpIncrNum"]["axisData"];
	var dataArray5 = mapData["wholeUrlParseDumpIncrNum"]["data"];
	var axisDataArray5ParseCap = mapData["wholeUrlParseDumpIncrCap"]["axisData"];
	var dataArray5ParseCap = mapData["wholeUrlParseDumpIncrCap"]["data"];
	
	var axisDataArray6 = mapData["wholeMergeEventNum"]["axisData"];
	var dataArray6 = mapData["wholeMergeEventNum"]["data"];
	
	var dataArrays9_sum = mapData["taskHandleIncr"]["sum"];
	var dataArrays9_dis = mapData["taskHandleIncr"]["dis"];
	var dataArrays9_update = mapData["taskHandleIncr"]["update"];
	var dataArrays9_del = mapData["taskHandleIncr"]["del"];
	
	var dataArrays8_succ = mapData["taskPrecIncr"]["success"];
	var dataArrays8_fail = mapData["taskPrecIncr"]["fail"];
var axisData = [
                                        "2016/1/24", "2016/1/25", "2016/1/28", "2016/1/29", "2016/1/30",
                                        "2016/1/31", "2016/2/1", "2016/2/4", "2016/2/5", "2016/2/6", 
                                        "2016/2/7", "2016/2/8", "2016/2/18", "2016/2/19", "2016/2/20", 
                                        "2016/2/21", "2016/2/22", "2016/2/25", "2016/2/26", "2016/2/27", 
                                        "2016/2/28", "2016/3/1", "2016/3/4", "2016/3/5", "2016/3/6", 
                                        "2016/3/7", "2016/3/8", "2016/3/11", "2016/3/12", "2016/3/13", 
                                        "2016/3/14", "2016/3/15", "2016/3/18", "2016/3/19", "2016/3/20", 
                                        "2016/3/21", "2016/3/22", "2016/3/25", "2016/3/26", "2016/3/27", 
                                        "2016/3/28", "2016/3/29", "2016/4/1", "2016/4/2", "2016/4/3", 
                                        "2016/4/8", "2016/4/9", "2016/4/10", "2016/4/11", "2016/4/12", 
                                        "2016/4/15", "2016/4/16", "2016/4/17", "2016/4/18", "2016/4/19", 
                                        "2016/4/22", "2016/4/23", "2016/4/24", "2016/4/25", "2016/4/26", 
                                        "2016/5/2", "2016/5/3", "2016/5/6", "2016/5/7", "2016/5/8", 
                                        "2016/5/9", "2016/5/10", "2016/5/13", "2016/5/14", "2016/5/15", 
                                        "2016/5/16", "2016/5/17", "2016/5/20", "2016/5/21", "2016/5/22", 
                                        "2016/5/23", "2016/5/24", "2016/5/27", "2016/5/28", "2016/5/29", 
                                        "2016/5/30", "2016/5/31", "2016/6/3", "2016/6/4", "2016/6/5", 
                                        "2016/6/6", "2016/6/7", "2016/6/13"
                                    ];
//liyuan(option,option2两个图x轴js生成系统时间)
var xDataTime=[];
var myData = new Date();
time = myData.getHours();
	//for(var i=6;i>-1;i--){
		xDataTime.push(time);
	//}
//console.log(xDataTime);
	//TODO 判断非空
	

            require.config({
                paths: {
                	echarts: '<%=path%>/resources/js/echarts'
                }
            });
            require(['echarts', 'echarts/chart/bar', 'echarts/chart/line','echarts/chart/k','echarts/chart/pie'],

                    function (ec) {                      
                        var myChart = ec.init(document.getElementById('chart1'));

                        option = {
                                tooltip : {
                                    trigger: 'axis',
                                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                                    }
                                },
                                legend: {
                                    data:['需求','采集','交付'],
                                    y:175
                                },
                                dataZoom : {
                                    show : true,
                                    realtime: true,
                                    start : 50,
                                    end : 100,
                                    // position:"top"
                                    height:25,
                                    showDetail:false,

                                    x:0,
                                    z:5,
                                    y:52,
                                    
                                    
                                },
                                      grid: {
                                        x: 40,
                                        y:190,
                                        x2:20,
                                        y2:25
                                    },

                                calculable : true,
                                xAxis : [
                                    {
                                        type : 'category',
                                        data :xDataTime
                                    }
                                ],
                                yAxis : [
                                    {
                                        type : 'value'
                                    }
                                ],
                                series : [
                                 
                                    {
                                        name:'需求',
                                        type:'bar',
                                        barWidth:50,
                                        stack: '堆积',
                                        itemStyle:{
                                        normal:{
                                            color:'#32d2e8',
                                            label:{
                                                show:true,
                                                position:'inside',
                                                 textStyle: {
                                                    color: '#fff',
                                                    fontFamily:'Microsoft YaHei'
                                                },
                                               formatter: "{c}个",

                                            },
                                        emphasis: {
                                           label:{
                                            show:true,
                                             textStyle: {
                                                    color: '#fff'
                                                },
                                             formatter: "{c}个"
                                            
                                         },
                                       
                                        }
                                        }
                                    },
                                        data:mapData["demandMap"]["demandNum"]
                                    },
                                    {
                                        name:'采集',
                                        type:'bar',
                                        barWidth:50,
                                        stack: '堆积',
                                        itemStyle:{
                                        normal:{
                                            color:'#29b5f3',
                                            label:{
                                                show:true,
                                                position:'inside',
                                                 textStyle: {
                                                    color: '#fff',
                                                    fontFamily:'Microsoft YaHei'
                                                },
                                               formatter: "{c}个",

                                            },
                                        emphasis: {
                                           label:{
                                            show:true,
                                             textStyle: {
                                                    color: '#fff'
                                                },
                                             formatter: "{c}个"
                                            
                                         },
                                       
                                        }
                                        }
                                    },
                                        data:mapData["demandMap"]["crawlNum"]
                                    },
                                    {
                                        name:'交付',
                                        type:'bar',
                                        barWidth:50,
                                        stack: '堆积',
                                        itemStyle:{
                                        normal:{
                                            color:'#99cc49',
                                            label:{
                                                show:true,
                                                position:'inside',
                                                 textStyle: {
                                                    color: '#fff',
                                                    fontFamily:'Microsoft YaHei'
                                                },
                                               formatter: "{c}个",

                                            },
                                        emphasis: {
                                           label:{
                                            show:true,
                                             textStyle: {
                                                    color: '#fff'
                                                },
                                             formatter: "{c}个"
                                            
                                         },
                                       
                                        }
                                        }
                                    },
                                        data:mapData["demandMap"]["deliverNum"]
                                    }
                                 
                                  
                                ]
                            };                
                  







                        option2 = {
                                tooltip : {
                                    trigger: 'axis',
                                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                                    }
                                },
                                legend: {
                                    data:['需求','采集','交付']
                                },dataZoom : {
                    		        show : false,
                    		        realtime: true,
                    		        start : 50,
                    		        end : 100,
                    		        position:"top"
                    		    },
                              
                                      grid: {
                                        x: 40,
                                        y:20,
                                        x2:20,
                                        y2:20
                                    },

                                calculable : true,
                                xAxis : [
                                    {
                                        type : 'category',
                                        data :xDataTime
                                    }
                                ],
                                yAxis : [
                                    {
                                        type : 'value'
                                    }
                                ],
                                series : [
                                 
                                    {
                                        name:'需求',
                                        type:'bar',
                                        barWidth:50,
                                        stack: '堆积',
                                        itemStyle:{
                                        normal:{
                                            color:'#32d2e8',
                                            label:{
                                                show:true,
                                                position:'inside',
                                                 textStyle: {
                                                    color: '#fff',
                                                    fontFamily:'Microsoft YaHei'
                                                },
                                               formatter: "{c}个",

                                            },
                                        emphasis: {
                                           label:{
                                            show:true,
                                             textStyle: {
                                                    color: '#fff'
                                                },
                                             formatter: "{c}个"
                                            
                                         },
                                       
                                        }
                                        }
                                    },
                                        data:mapData["siteMap"]["demandNum"]
                                    },
                                    {
                                        name:'采集',
                                        type:'bar',
                                        barWidth:50,
                                        stack: '堆积',
                                        itemStyle:{
                                        normal:{
                                            color:'#29b5f3',
                                            label:{
                                                show:true,
                                                position:'inside',
                                                 textStyle: {
                                                    color: '#fff',
                                                    fontFamily:'Microsoft YaHei'
                                                },
                                               formatter: "{c}个",

                                            },
                                        emphasis: {
                                           label:{
                                            show:true,
                                             textStyle: {
                                                    color: '#fff'
                                                },
                                             formatter: "{c}个"
                                            
                                         },
                                       
                                        }
                                        }
                                    },
                                        data:mapData["siteMap"]["crawlNum"]
                                    },
                                    {
                                        name:'交付',
                                        type:'bar',
                                        barWidth:50,
                                        stack: '堆积',
                                        itemStyle:{
                                        normal:{
                                            color:'#99cc49',
                                            label:{
                                                show:true,
                                                position:'inside',
                                                 textStyle: {
                                                    color: '#fff',
                                                    fontFamily:'Microsoft YaHei'
                                                },
                                               formatter: "{c}个",

                                            },
                                        emphasis: {
                                           label:{
                                            show:true,
                                             textStyle: {
                                                    color: '#fff'
                                                },
                                             formatter: "{c}个"
                                            
                                         },
                                       
                                        }
                                        }
                                    },
                                        data:mapData["siteMap"]["deliverNum"]
                                    }
                                 
                                  
                                ]
                            };                
                  
                        myChart2 = ec.init(document.getElementById('chart2'));
                        myChart2.setOption(option2);

                      


                        option3 = {
                    		    tooltip : {
                    		        trigger: 'axis',
                    		        showDelay: 0             // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
                    		    },
                    		    legend: {
                    		        y : -30,
                    		        data:['全局解析','解析增量','解析数据']
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
                    		        //  {
                    		        //     type : 'category',
                    		        //     boundaryGap : true,
                    		        //     axisTick: {onGap:false},
                    		        //     splitLine: {show:false},
                    		        //     data : axisData
                    		        // }
                    		        {
                    		            type : 'category',
                    		            // position:'bottom',
                    		            boundaryGap : true,
                    		            axisLabel:{show:true},
                    		            axisTick: {onGap:false},
                    		            splitLine: {show:false},
                    		            data : axisData3
                    		        }
                    		    ],
                    		    yAxis : [
                    		        {
                    		            type : 'value',
                    		            scale:true,
                    		            /*splitNumber: 3,
                    		             boundaryGap: [0.05, 0.05],
                    		            axisLabel: {
                    		                formatter: function (v) {
                    		                    return Math.round(v/10000) 
                    		                }
                    		            }, */
                    		            splitArea : {show : true}
                    		        }
                    		    ],
                    		    series : [
                    		        {
                    		            name:'解析增量',
                    		            type:'line',
                    		            symbol: 'none',
                    		        // 这里为后台传来的数据、、、、、、、、、、、、、、、、、、
                    		            data:dataArrays3,
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
                        myChart3 = ec.init(document.getElementById('chart3'));
                        myChart3.setOption(option3);


                     

                        option4 = {
                    		    tooltip : {
                    		        trigger: 'axis',
                    		        showDelay: 0             // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
                    		    },
                    		    legend: {
                    		        y : -30,
                    		        data:['全局下载','采集增量','虚拟数据']
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
                    		        // position:"top"
                    		        z:5,
                    		        y:0
                    		        
                    		    },
                    		    grid: {
                    		        x: 40,
                    		        y:5,
                    		        x2:20,
                    		        y2:25
                    		    },
                    		
                    		    xAxis : [
                    		        //  {
                    		        //     type : 'category',
                    		        //     boundaryGap : true,
                    		        //     axisTick: {onGap:false},
                    		        //     splitLine: {show:false},
                    		        //     data : axisData
                    		        // }
                    		        {
                    		            type : 'category',
                    		            // position:'bottom',
                    		            boundaryGap : true,
                    		            axisLabel:{show:true},
                    		            axisTick: {onGap:false},
                    		            splitLine: {show:false},
                    		            data : axisData4
                    		        }
                    		    ],
                    		    yAxis : [
                    		        {
                    		            type : 'value',
                    		            scale:true,
                    		            /* boundaryGap: [0.05, 0.05],
                    		            axisLabel: {
                    		                formatter: function (v) {
                    		                    return Math.round(v/10000)
                    		                }
                    		            }, */
                    		            splitArea : {show : true},
                    		          
                    		        }
                    		    ],
                    		    series : [
                    		        {
                    		            name:'采集增量',
                    		            type:'line',
                    		            symbol: 'none',
                    		        // 这里为后台传来的数据、、、、、、、、、、、、、、、、、、
                    		            data:valueArray4,
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
                        myChart4 = ec.init(document.getElementById('chart4'));
                        myChart4.setOption(option4);







                        option5 = {
                                tooltip : {
                                    trigger: 'axis'
                                },
                                
                                calculable : true,
                                legend: {
                                    data:['文件容量','文件个数']
                                },
                                dataZoom : {
                    		        show : false,
                    		        realtime: true,
                    		        start : 50,
                    		        end : 100,
                    		        position:"top"
                    		    },
                                grid: {
                                    x: 50,
                                    y:25,
                                    x2:50,
                                    y2:20
                                },
                                xAxis : [
                                    {
                                        type : 'category',
                                        boundaryGap:false,
                                        data : axisDataArray5ParseCap
                                    }
                                ],
                                yAxis : [
                                    {
                                        type : 'value',
                                        name : '文件容量（Mb）',
                                         splitNumber: 5,
                                         min:0,
                                        axisLabel : {
                                            formatter: '{value}'
                                        },
                                        splitArea : {
                                            show: true,
                                            areaStyle:{
                                                color:['#ffffff','#ededed']
                                            }
                                        },
                                    },
                                    {
                                        type : 'value',
                                        name : '文件数量（个）',
                                         splitNumber: 5,
                                         min:0,
                                        axisLabel : {
                                            formatter: '{value}'
                                        }
                                    }
                                ],
                                series : [

                                    {
                                        name:'文件容量',
                                        smooth:true,
                                        type:'line',
                                        data:dataArray5ParseCap
                                    },
                                  
                                    {
                                        name:'文件个数',
                                        type:'line',
                                         smooth:true,
                                        yAxisIndex: 1,
                                        data:dataArray5
                                    }
                                ]
                            };
                        myChart5 = ec.init(document.getElementById('chart5'));
                        myChart5.setOption(option5);


                        option6 = {
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
                                    //  {
                                    //     type : 'category',
                                    //     boundaryGap : true,
                                    //     axisTick: {onGap:false},
                                    //     splitLine: {show:false},
                                    //     data : axisData
                                    // }
                                    {
                                        type : 'category',
                                        // position:'bottom',
                                        boundaryGap : true,
                                        axisLabel:{show:true},
                                        axisTick: {onGap:false},
                                        splitLine: {show:false},
                                        data : axisDataArray6
                                    }
                                ],
                                yAxis : [
                                    {
                                        type : 'value',
                                        scale:true,
                                        splitNumber: 3,
                                        boundaryGap: [0.05, 0.05],
                                        axisLabel: {
                                            formatter: function (v) {
                                                return Math.round(v/10000)
                                            }
                                        },
                                        splitArea : {show : true}
                                    }
                                ],
                                series : [
                                    {
                                        name:'合并条数',
                                        type:'line',
                                        symbol: 'none',


                                    // 这里为后台传来的数据、、、、、、、、、、、、、、、、、、

                                        data:dataArray6,
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
                            myChart6 = ec.init(document.getElementById('chart6'));
                            myChart6.setOption(option6);




                         option7 = {
                                 tooltip : {
                                     trigger: 'axis'
                                 },
                                 
                                 calculable : true,
                                 legend: {
                                     data:['数据项数','结构化网页数量']
                                 },
                                 grid: {
                                     x: 50,
                                     y:25,
                                     x2:50,
                                     y2:20
                                 },
                                 dataZoom : {
                     		        show : false,
                     		        realtime: true,
                     		        start : 50,
                     		        end : 100,
                     		        position:"top"
                     		    },
                                 xAxis : [
                                     {
                                         type : 'category',
                                         boundaryGap:false,
                                         data : axisData7Item
                                     }
                                 ],
                                 yAxis : [
                                     {
                                         type : 'value',
                                         name : '数据项数',
                                          splitNumber: 5,
                                         axisLabel : {
                                             formatter: '{value}'
                                         },
                                         splitArea : {
                                             show: true,
                                             areaStyle:{
                                                 color:['#ffffff','#ededed']
                                             }
                                         },
                                     },
                                     {
                                         type : 'value',
                                         name : '结构化网页数量',
                                          splitNumber: 5,
                                         axisLabel : {
                                             formatter: '{value}'
                                         }
                                     }
                                 ],
                                 series : [

                                     {
                                         name:'数据项数',
                                         smooth:true,
                                         type:'line',
                                         data:dataArrays7Item
                                     },
                                   
                                     {
                                         name:'结构化网页数量',
                                         type:'line',
                                          smooth:true,
                                         yAxisIndex: 1,
                                         data:dataArrays7Record
                                     }
                                 ]
                             };
                        myChart7 = ec.init(document.getElementById('chart7'));
                        myChart7.setOption(option7);



                         option8 = {
                        		// title : {
                                 //     text: '数据预处理',
                                
                                 //     x:'center'
                                 // },
                                 tooltip : {
                                     trigger: 'item',
                                     formatter: "{a} <br/>{b} : {c} ({d}%)"
                                 },
                                 legend: {
                                     orient : 'horizontal',
                                     x : 'center',
                                     y:10,
                                     data:['成功','失败']
                                 },
                               
                                 calculable : true,

                                 series : [
                                     {
                                         name:'数据预处理',
                                         type:'pie',
                                         radius : '65%',
                                         center: ['50%', '50%'],
                                         itemStyle:{
                                             normal:{
                                            	
                                                 label:{
                                                     position:'outer',
                                                      formatter: "{b} : {c}个"
                                                 },
                                                 labelLine:{
                                                     show:true
                                                 }
                                             },
                                              emphasis: {
                                                label:{
                                                 show:true,
                                                   formatter: "{b} : {c}个"
                                              },
                                            
                                             }
                                         },

                                         data:[
                                             {value:dataArrays8_succ, name:'成功',itemStyle:{normal:{ color:['#99cc49']}}},
                                             {value:dataArrays8_fail, name:'失败',itemStyle:{normal:{ color:['#e47f31']}}},
                                        
                                         ]
                                     }
                                 ]
                             };
                        myChart8 = ec.init(document.getElementById('chart8'));
                        myChart8.setOption(option8);
                        option9 = {
                      		  
                       		 tooltip : {
                                    show:false,
                                    trigger: 'axis',
                                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                                    }
                                },
                                grid: {
                                    x: 60,
                                    y:5,
                                    x2:20,
                                    y2:25
                                },
                                legend: {
                                    data:['数据总量','数据去重','批量修改','批量删除'],
                                    x:'right'
                                },
                             		
                                calculable : true,
                                xAxis : [
                                    {
                                        type : 'category',
                                        axisTick:false,
                                        splitLine:{
                                            show:false
                                        },
                                        data : ['','']
                                    }
                                ],
                                yAxis : [

                                    {
                                        type : 'value',
                                         // name : '个数',
                                          splitLine:{
                                            show:true
                                        },
                                           axisLabel : {
                                            formatter: '{value}'
                                        },
                                      
                                    }
                                ],
                                series : [
                                    {
                                        name:'数据总量',
                                        type:'bar',
                                        barWidth:60,
                                   
                                       barCategoryGap:'50%',
                                        itemStyle:{
                                            normal:{
                                                color:'#87cffb',
                                                label:{
                                                    show:true,
                                                    position:'inside',
                                                     textStyle: {
                                                        color: '#fff',
                                                        fontFamily:'Microsoft YaHei'
                                                    },
                                                   formatter: "{c}",

                                                },
                                            emphasis: {
                                               label:{
                                                show:true,
                                                 textStyle: {
                                                        color: '#fff'
                                                    },
                                                 formatter: "{c}"
                                                
                                             },
                                           
                                            }
                                            }
                                        },
                                        data:[dataArrays9_sum]
                                    },
                                    {
                                        name:'数据去重',
                                        type:'bar',
                                        stack: '清洗总量',
                                        barWidth:40,
                                         
                                         barCategoryGap:'50%',

                                         itemStyle:{
                                            normal:{
                                                color:'#e47f31',
                                                label:{
                                                    show:true,
                                                    position:'inside',
                                                    textStyle: {
                                                        color: '#fff',
                                                        fontFamily:'Microsoft YaHei'
                                                    },
                                                   formatter: "{c}"
                                                },
                                            emphasis: {
                                               label:{
                                                show:true,
                                                 formatter: "{c}" 
                                             },
                                            }
                                            }
                                        },
                                        data:[dataArrays9_dis]
                                    },
                                    {
                                        name:'批量修改',
                                        type:'bar',
                                        stack: '清洗总量',
                                        barWidth:40,

                                         barCategoryGap:'10%',
                                             itemStyle:{
                                            normal:{
                                                 color:'#99cc49',
                                                label:{
                                                    show:true,
                                                    position:'inside',
                                                    textStyle: {
                                                        color: '#fff',
                                                        fontFamily:'Microsoft YaHei'
                                                    },
                                                     formatter: "{c}"
                                                },
                                            emphasis: {
                                               label:{
                                                show:true,
                                                 formatter: "{c}" 
                                             },
                                            }
                                            }
                                        },
                                        data:[dataArrays9_update]
                                    },
                                    {
                                        name:'批量删除',
                                        type:'bar',
                                        stack: '清洗总量',
                                        barWidth:40,
                                             itemStyle:{
                                            normal:{
                                                color:'#f0c430',
                                                label:{
                                                    show:true,
                                                    position:'inside',
                                                    textStyle: {
                                                        color: '#fff',
                                                        fontFamily:'Microsoft YaHei'
                                                    },
                                                    formatter: "{c}"
                                                },
                                            emphasis: {
                                               label:{
                                                show:true,
                                                 formatter: "{c}" 
                                             },
                                            }
                                            }
                                        },
                                        data:[dataArrays9_del]
                                    },
                                      {
                                        name:'数据清洗',
                                        type:'pie',
                                        radius : '40%',
                                        center: ['70%', '50%'],
                                        itemStyle:{
                                            normal:{
                                                label:{
                                                    position:'outer',
                                                     formatter: "{b} : {c}个"
                                                },
                                                labelLine:{
                                                    show:true
                                                }
                                            },
                                             emphasis: {
                                               label:{
                                                show:true,
                                                  formatter: "{b} : {c}个"
                                             },
                                           
                                            }
                                        },

                                        data:[
                                            {value:dataArrays9_dis, name:'数据去重'},
                                            {value:dataArrays9_update, name:'批量修改'},
                                            {value:dataArrays9_del, name:'批量删除'},
                                        
                                       
                                        ]
                                    }
                                  
                                ]
                            };
                      
                       myChart9 = ec.init(document.getElementById('chart9'));
                       myChart9.setOption(option9);


                       option10 = {
                               tooltip : {
                                   trigger: 'axis'
                               },
                               
                               calculable : true,
                               legend: {
                                   data:['企业数','记录数','政府网站','企业经营','事业单位','工商数据','电商数据']
                               },
           					  grid: {
                                                x:45,
                                                y:40,
                                                x2:230,
                                                y2:30
                                            },
                               xAxis : [
                                   {
                                       type : 'category',
                                       boundaryGap:false,
                                       data : xDataTime
                                   }
                               ],
                               yAxis : [
                                   {
                                       type : 'value',
                                       name : '企业数',
                                        splitNumber: 5,
                                       axisLabel : {
                                           formatter: '{value}'
                                       }
                                   },
                                   {
                                       type : 'value',
                                       name : '记录数',
                                        splitNumber: 5,
                                       axisLabel : {
                                           formatter: '{value}'
                                       }
                                   }
                               ],
           					
                               series : [

                                   {
                                       name:'企业数',
                                       smooth:true,
                                       type:'line',
                                       data:[8371, 9234, 9435, 10428, 10454, 11463, 12783, 12453, 14345, 15214, 16529, 16529]
                                   },
                                 
                                   {
                                       name:'记录数',
                                       type:'line',
                                        smooth:true,
                                       yAxisIndex: 1,
                                       data:[161035, 184131, 194650, 216549, 239794, 264862, 284647, 306447, 311685, 331864, 339130, 347586]
                                   },
           						 {
                                       name:'数据分类',
                                       type:'pie',
                                       radius : '60%',
                                       center: ['85%', '50%'],
                                       itemStyle:{
                                           normal:{
                                               label:{
                                            	   show:false,
                                                   position:'inner',
                                                    formatter: "{b} : {c}个"
                                               },
                                               labelLine:{
                                                   show:false
                                               }
                                           },
                                            emphasis: {
                                              label:{
                                               show:true,
                                                 formatter: "{b} : {c}个"
                                            },
                                          
                                           }
                                       },

                                       data:[
                                           {value:28982, name:'政府网站'},
                                           {value:2559, name:'企业经营'},
                                           {value:810, name:'事业单位'},
                                           {value:59265, name:'工商数据'},
                                           {value:255970, name:'电商数据'},
                                      
                                       ]
                                   }
                               ]
                           };
                       myChart10 = ec.init(document.getElementById('chart10'));
                       myChart10.setOption(option10);

                    //滑块自适应
                    var awidth = $('#chart1').width()
                    option.dataZoom.width = awidth;

                    //datazoom滑动实时变化
                    function changeData(opt,chart){

                        var data = opt.series[0].data;
                         start = Math.floor(data.length * 0.5);
                         end  =Math.floor(data.length *0.99999999);

                          var num=0;
                            for (var i = start; i<=end; i++) {
                                num += Number(data[i]);
                            };
                           // myChart.setOption(option);
                         $('.'+chart).html(num);
                    }
                    // var data = option.series[0].data;
                    //  start = Math.floor(data.length * 0.5);
                    //  end  =Math.floor(data.length *0.99999999);

                    //   var num=0;
                    //     for (var i = start; i<=end; i++) {
                    //         num += data[i];
                    //     };
                  
                    //    // myChart.setOption(option);
                    //  $('.chart1Num').html(num);

                   /*  changeData(option,'chart1Num');
                    changeData(option2,'chart2Num'); */
                     changeData(option3,'chart3Num');
                     changeData(option4,'chart4Num');
                     changeData(option5,'chart5Num');
                     changeData(option6,'chart6Num');
                      changeData(option7,'chart7Num');
                     myChart.setOption(option);




                      function changeDataZoom(opt,chart,param){

                        var data = opt.series[0].data;
                        /*  start = Math.floor(data.length * 0.5);
                         end  =Math.floor(data.length *0.99999999); */
                        var start =param.zoom.start;
                      	var end  =param.zoom.end==100? 99.99999:param.zoom.end ;
                         
                         start = Math.floor(data.length * start /100);
                         end  =Math.floor(data.length * end /100);

                          var num=0;
                            for (var i = start; i<=end; i++) {
                                num += Number(data[i]);
                            };
                      		
                           // myChart.setOption(option);
                         $('.'+chart).html(num);
                         
                         return num;
                    }



                 //滑块自动计算
                 var ecConfig = require('echarts/config');
                 myChart.on(ecConfig.EVENT.DATA_ZOOM, function(param){
                   //  var data = option.series[0].data;
                   //  var start = param.zoom.start;
                   //  var end  =param.zoom.end==100? 99.99999:param.zoom.end ;
                   //  start = Math.floor(data.length * start /100);
                   //  end  =Math.floor(data.length * end /100);

                   //  // if (end==data.length+1) {end=data.length};
                   //  var num=0;
                   //  for (var i = start; i<=end; i++) {
                   //      num += data[i];
                   //  };
              
                   // // myChart.setOption(option);
                   // $('.chart1Num').html(num);
                   
                 /*  changeDataZoom(option,'chart1Num',param);
                  changeDataZoom(option2,'chart2Num',param); */
                  var chart3 = changeDataZoom(option3,'chart3Num',param);
                    var chart4 = changeDataZoom(option4,'chart4Num',param);
                    var chart5 = changeDataZoom(option5,'chart5Num',param);
                    var chart6 = changeDataZoom(option6,'chart6Num',param);
                    var chart7 = changeDataZoom(option7,'chart7Num',param);
                    
                    //单元计算次数总和
                    var unit =chart3+chart4+chart5+chart6+chart7
                    $('.index_cishuleft').find('.index_cishuleft2').html(unit);
                    
                    //数据处理容量
                    var content = Number(chart5) * 5.3;
                    content = parseInt(content);
                    $('.index_cishuright').find('.index_cishuleft2').html(content)
                    
                 });




                        myChart.connect([myChart2, myChart3,myChart4,myChart5,myChart6,myChart7,myChart10]);
                        myChart2.connect([myChart, myChart3,myChart4,myChart5,myChart6,myChart7,myChart10]);
                        myChart3.connect([myChart, myChart2,myChart4,myChart5,myChart6,myChart7,myChart10]);
                        myChart4.connect([myChart, myChart2,myChart3,myChart5,myChart6,myChart7,myChart10]);
                        myChart5.connect([myChart, myChart2,myChart3,myChart4,myChart6,myChart7,myChart10]);
                        setTimeout(function (){
                            window.onresize = function () {
                                myChart.resize();
                                
                                var bwidth = $('#chart1').width()
                                option.dataZoom.width = bwidth;
                                myChart.setOption(option);

                                myChart2.resize();
                                myChart3.resize();
                                myChart4.resize();
                                myChart5.resize();
                            }
                        },200)
                 

              
                


                        
                     
                    });



}

function instanceSelect(instanceId){
	$("#myModal").modal('hide');
	$("#targetId").val(instanceId);
	$("#type").val("TASK");
	//$("#instanceTargetId").val("已选实例"+instanceId);
	//$("#instanceTargetId").show();
	reloadData();
}

function global(){
	$("#targetId").val("");
	//$("#instanceTargetId").val("");
	//$("#instanceTargetId").hide();
	$("#type").val("WHOLE");
	reloadData();
}
function changePeriod(pType) {
	var curDate = new Date();
	var t = curDate.getTime();
	var startDate;
	$("#timeUnit").val("MINUTES");
	//30分钟
	if(pType==1){
		startDate = new Date(t - 1000*60*30);
	}else if(pType==2){//1小时
		startDate = new Date(t - 1000*60*60);
	}else if(pType==3){//1天
		startDate = new Date(t - 1000*60*60*24);
	}else if(pType==4){//7天
		$("#timeUnit").val("HOURS");
		startDate = new Date(t - 1000*60*60*24*7);
	}else if(pType==5){//1一个月
		startDate = new Date();
		startDate.setMonth(startDate.getMonth()-1);
		$("#timeUnit").val("HOURS");
	}
	$("#start").val(formatDate(startDate));
	$("#end").val(formatDate(curDate));
	reloadData();
}

function formatDate(date){
	return (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
}
function reloadData(){
	var type=$("#type").val();
	var bt = $("#start").val();
	var et = $("#end").val();
	var tu = $("#timeUnit").val();
	var ti = $("#targetId").val();
	var url = '${webRoot}/monitor/monitorData?type='+type+'&beginTime='+bt+'&endTime='+et+'&timeUnit='+tu;
	if(ti!='' && ti!=null) {
		url = url +'&targetId='+ti;
	}
	$.ajax({
		url:url,
		dataType:'json',
		success:function(d){
			drawChart(d);
		}
	});
}

function loadGeneralNum(){
	url = '${webRoot}/monitor/generalNum';
	$.ajax({
		url:url,
		dataType:'json',
		success:function(d){
			$("#typeAll").html(d.typeAll+"个");
			$("#numAll").html(d.numAll+"项");
			$("#dumpAll").html(d.dumpAll+"G");
			$("#dataAll").html(d.dataAll+"条");
			$("#typeNew").html(d.typeNew+"个");
			$("#numNew").html(d.numNew+"项");
			$("#dumpNew").html(d.dumpNew+"G");
			$("#dataNew").html(d.dataNew+"条");
			if(d.typeIncr<0){
				$("#typeIncr").addClass("huanbi_lvse");
			}
			$("#typeIncr").html("环比:"+d.typeIncr+"("+d.typeIncrPer+"%)");
			if(d.numIncr<0){
				$("#numIncr").addClass("huanbi_lvse");
			}
			$("#numIncr").html("环比:"+d.numIncr+"("+d.numIncrPer+"%)");
			if(d.dumpIncr<0){
				$("#dumpIncr").addClass("huanbi_lvse");
			}
			$("#dumpIncr").html("环比:"+d.dumpIncr+"G("+d.dumpIncrPer+"%)");
			if(d.dataIncr<0){
				$("#dataIncr").addClass("huanbi_lvse");
			}
			$("#dataIncr").html("环比:"+d.dataIncr+"("+d.dataIncrPer+"%)");
		}
	});
}
</script>
<body style="margin: 0; padding: 0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<input type="hidden" id="type" name="type" value="${type }">
	<input type="hidden" id="timeUnit" name="timeUnit" value="${timeUnit }">
	<input type="hidden" id="targetId" name="targetId" value="${targetId }">
            <!--内容开始-->
          <div  class="content_waikuang">
            <div class="row" style="clear:both;margin-left:0">
            <!--数据资源开始-->
            	<div class="index_top">
            		<h2>数据资源</h2>
                    
                    <div class="well summary"  id="index_top_top"> 
						<ul>
                             <li class="index_top_rizi" style="padding-top:6px !important">
								实时
							</li>
							<li>
								<a href="javascript:void(0)"><span class="count">主题数<b id="typeAll"></b></span> </a>
							</li>
							<li>
								<a href="javascript:void(0)"><span class="count">数据项数<b id="numAll"></b></span></a>
							</li>
							<li>
								<a href="javascript:void(0)"><span class="count">数据储存<b id="dumpAll"></b></span></a>
							</li>
                            <li>
								<a href="javascript:void(0)"><span class="count">数据记录<b id="dataAll"></b></span></a>
							</li>
						</ul>
                         
					</div>
                    <div class="well summary" id="index_top_bottom">
                        <ul>
                             <li class="index_top_rizi" >
								 月增
							</li>
							<li>
								<a href="javascript:void(0)"><span class="count">主题数<b id="typeNew"></b></span><em id="typeIncr"></em></a>
							</li>
							<li>
								<a href="javascript:void(0)"><span class="count">数据项数<b id="numNew"></b></span><em id="numIncr"></em></a>
							</li>
							<li>
								<a href="javascript:void(0)"><span class="count">数据储存<b id="dumpNew"></b></span><em id="dumpIncr"></em></a>
							</li>
							<li>
								<a href="javascript:void(0)"><span class="count">数据记录<b id="dataNew"></b></span><em id="dataIncr"></em></a>
							</li>
						</ul>
                        </div>
                </div><!--数据资源结束-->
                
              <div class="index_content">
                 	<div class="index_content_two" >
                    <div class="index_title">
                      <h4>图表展示</h4>
			<input type="button" class="content_rwgl_pzanniu1" data-toggle="modal" data-target="#myModal" value="任务实例选择">
			<input type="button" class="content_rwgl_pzanniu1" onclick="global()" value="全局查询">
			<input type="text" readonly="readonly" id="instanceTargetId" style="display: none">
  </div>


							
                                    
                                    
                             
		



<div style="height:400px; float:left; width:49%">
            <div class="index_tubiaozhanshi">
            <div class="index_cishu">
               	  <div class="index_cishuleft">
                    	<div class="index_cishuleft1">单元计算次数：</div>
                        <div class="index_cishuleft2"></div>
                    </div>
                    <div class="index_cishuright">
                      <div class="index_cishuleft1">数据处理容量：</div>
                        <div class="index_cishuleft2"></div>
                    
                    </div>
                 </div>
                 </div>
                     
                          <h2 class="index_content_twolefttoptitle"><em>需求 </em> <em class="qujianleiji"> <span class="chart1Num"></span></em></h2>
                          
                    	   <div class="index_content_twoleft index_content_twolefttop"  style="height:400px; width:100%" id="chart1">

                            </div>
                       
                        </div>
                        <div class="index_content_tworight" style="position:absolute; top:140px; right:0; height:260px">
                        <div class="index_riqi">
                          <div class="index_riqileft">
                            <ul>
                              <li onclick="changePeriod(1)">30分钟</li>
                              <li onclick="changePeriod(2)">1小时</li>
                              <li onclick="changePeriod(3)" class="riqi_active">1天</li>
                              <li onclick="changePeriod(4)">7天</li>
                              <li onclick="changePeriod(5)">1个月</li>
                            </ul>
                          </div>
                          <div class="index_riqiright">
                            <form>
                              <input id="start" value="${beginTime }" name="start" class="laydate-icon" onClick="laydate({istime: true,isclear: false, format: 'YYYY-MM-DD hh:mm:ss'})" type="text">
                              <b>-</b>
                              <input class="laydate-icon" value="${endTime }" onClick="laydate({istime: true,isclear: false, format: 'YYYY-MM-DD hh:mm:ss',choose:function(datas){reloadData();}});" id="end"  name="end" type="text">
                            </form>
                          </div>
                        </div>
                        <h2>站点 <!-- <em>区间累计<span class="chart2Num"></span></em> --></h2>
                           <div id="chart2" style="height:220px;"></div>
                        </div>
                        <div style="clear:both"></div>
                    </div>
                    
                    
                    
                    
          <div class="index_content_three">
                    	<div class="index_content_threeleft">
                        	<h2>解析 <em>区间累计<span class="chart3Num"></span></em></h2>
                            <div id="chart3" style="height:220px;"></div>
                        </div>
                        <div class="index_content_threemid">
                           <h2>下载 <em>区间累计<span class="chart4Num"></span></em></h2>
                           <div id="chart4" style="height:220px;"></div>
                        </div>
                        <div class="index_content_threeright">
                           <h2>转储 <em>区间累计<span class="chart5Num"></span></em></h2>
                           <div id="chart5" style="height:220px;"></div>
                        </div>
                    </div>
                    
                    <div class="index_content_three">
                    	<div class="index_content_threeleft">
                        	<h2>合并 <em>区间累计<span class="chart6Num"></span></em></h2>
                            <div id="chart6" style="height:220px;"></div>
                        </div>
                        <div class="index_content_threemid">
                           <h2>内容抽取 <em>区间累计<span class="chart7Num"></span></em></h2>
                           <div id="chart7" style="height:220px;"></div>
                        </div>
                        <div class="index_content_threeright">
                           <h2>预处理<!-- <em>区间累计<span class="chart8Num"></span></em> --></h2>
                           <div id="chart8" style="height:220px;"></div>
                        </div>
                    </div>
                    
                  
                  <div class="index_content_two" style="margin-bottom:20px; clear:both">
                    	<div class="index_content_twoleft" style="height:260px">
                        	<h2>清洗处理</h2>
                            <div id="chart9" style="height:230px;"></div>
                        </div>
                        <div class="index_content_tworight" style="height:260px">
                           <h2>数据交付</h2>
                           <div id="chart10" style="height:230px;"></div>
                        </div>
                    </div>  
                 
                 
                 
                 </div>
                 
            
            </div>
          </div>
             <!--内容结束-->
		</div>
        
        <!-- 弹出框 -->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display:none;">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
     <!--  <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">x</span></button>
        <h4 class="modal-title" id="myModalLabel">任务实例选择</h4>
      </div>
      <div class="modal-body">
   
    
      </div> -->
      <iframe src="${webRoot }/monitor/instanceList" width="100%" height="600px" frameborder="0"></iframe>
    </div>
  </div>
  </div>

        <script type="text/javascript">
	
      $(function() {
	var sss='';
			$("#ulcd").html(sss);
		});


</script>
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
</script>
</body>
</html>
