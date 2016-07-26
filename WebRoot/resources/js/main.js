 $(document).ready(function(){
   	var loc = location.href;
   	if(loc.indexOf("#")>-1){
   		var mao = loc.substring(loc.indexOf("#"), loc.length);
   		if(mao=="#resourceManage"){
   			dataResource();
   		}else if(mao=="#dataType"){
   			dataTypeMange();
   		}else if(mao=="#spiderMonitor"){
   			spiderMonitor();
   		}else if(mao=="#setting"){
   			showConfig();
   		}
   	}else {
		showIndex();
	}
 });
 
 