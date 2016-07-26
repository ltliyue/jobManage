Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function zero(num, s) {

	var tmp = " " + num;

	for (;tmp.length <s; tmp = "0 " + tmp);

	return tmp;

	}

	function formatDate(d, fmt) {

	var yyyy = d.getUTCFullYear();

	var mm = zero(d.getUTCMonth() + 1, 2);

	var dd = zero(d.getUTCDate(), 2);

	var hh = zero(d.getUTCHours(), 2);

	var mi = zero(d.getUTCMinutes(), 2);

	var ss = zero(d.getUTCSeconds(), 2);

	return fmt.replace(/yyyy/ig, yyyy).replace(/mm/ig, mm).replace(/dd/ig, dd).replace(/hh/ig, hh).replace(/mi/ig, mi).replace(/ss/ig, ss);

	} 
function onsub(url){
    		var datas= $('#form').serialize();
    		
			$.post(url,datas,function(data){
				$("#container").empty();
				json=eval('('+data+')');
				var pagecount = json[0].pagecount;
				var currentpage = json[0].currentpage;
				var pagesize = json[0].pagesize;
				if(pagecount!=0){
					$.each(json,function(i){ 
					$("#container").append(""
					+"<div  >"
		          +"<h4 ><a href='searchlog/findId/"+json[i].id+"' style='text-decoration:underline;color:blue' target='_black'>"+json[i].fwName+"</a></h4>"
		          +"<p style='height: 30px;'>"+json[i].logNr+"<p style='color: #339933'  align='right'>"+json[i].sdate+"</p></div>"); 
				}); 
			}else{
			var searh = $("#seach").attr("value");
				$("#container").append(""
					+"<div  >"
		          +"<h4 >抱歉，没有找到与“"+searh+"”相关的数据</h4>"
		          +"</div>"); 
			}
			url = url.substring(0,url.indexOf("/log/")+5);
			fenye(url,pagesize,pagecount,currentpage);
			},
			"text"
			);
    	}
  function fenye(url,pagesize,pagecount,currentpage){
 	 var maxPageCount=10;//最大显示页数
  	 if($("#pagination")){  
        var counts,pagehtml="";  
        if(pagecount%pagesize==0){  
            counts = parseInt(pagecount/pagesize);  
        }else{  
            counts = parseInt(pagecount/pagesize)+1;  
        }  
        //只有一页内容  
        if(pagecount<=pagesize){pagehtml="";}  
        //大于一页内容  
        if(pagecount>pagesize){  
            if(currentpage>1){  
             var s = "'"+url+(currentpage-1)+"'";
                pagehtml+= '<li><a onclick="onsub('+s+')" href="javascript:void(0)" style="margin-left: 15px">上一页</a></li>';  
            }  
            if(counts<maxPageCount){
	             for(var i=0;i<counts;i++){  
	                var ss = "'"+url+(i+1)+"'";
	                    if(i==currentpage-1){  
	                        pagehtml+= '<li class="active"><a onclick="onsub('+ss+')" href="javascript:void(0)" style="margin-left: 15px" >'+(i+1)+'</a></li>';  
	                    }else{  
	                        pagehtml+= '<li><a onclick="onsub('+ss+')" href="javascript:void(0)" style="margin-left: 15px" >'+(i+1)+'</a></li>';  
	                    }  
	            }  
            }else{
             	 var begin=0;
				 var end = 0;
				 if(currentpage-5>0){
					 begin = currentpage-5;
					 end = currentpage+5;
					 if(currentpage>counts-5){
						 begin=counts-maxPageCount;
						 end=counts;
					 }
	            for(var i=begin;i<end;i++){  
	                var ss = "'"+url+(i+1)+"'";
	                    if(i==currentpage-1){  
	                        pagehtml+= '<li class="active"><a onclick="onsub('+ss+')" href="javascript:void(0)" style="margin-left: 15px" >'+(i+1)+'</a></li>';  
	                    }else{  
	                        pagehtml+= '<li><a onclick="onsub('+ss+')" href="javascript:void(0)" style="margin-left: 15px" >'+(i+1)+'</a></li>';  
	                    }  
	            }  
	          }else{
	          		 for(var i=0;i<maxPageCount;i++){  
	                var ss = "'"+url+(i+1)+"'";
	                    if(i==currentpage-1){  
	                        pagehtml+= '<li class="active"><a onclick="onsub('+ss+')" href="javascript:void(0)" style="margin-left: 15px" >'+(i+1)+'</a></li>';  
	                    }else{  
	                        pagehtml+= '<li><a onclick="onsub('+ss+')" href="javascript:void(0)" style="margin-left: 15px" >'+(i+1)+'</a></li>';  
	                    }  
	            }  
	          
	          }
            }
            if(currentpage<counts){  
            var s1 = "'"+url+(currentpage+1)+"'";
                pagehtml+= '<li><a onclick="onsub('+s1+')" href="javascript:void(0)" style="margin-left: 15px">下一页</a></li>';  
            }  
        }
        $("#pagination").html(pagehtml+"&nbsp;&nbsp;&nbsp;&nbsp;<span style='color: #999999;font-size: 11px'>为您找到相关结果为 "+pagecount+" 个</span>");  
    }  
  }