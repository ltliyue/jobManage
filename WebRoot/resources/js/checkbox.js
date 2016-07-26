 //实现选中时全选，取消时反选
  	 function SelectAll(){
      	var chk_all = $("input[name='chk_all']").attr("checked");
   
   	if (chk_all != "checked"){ //读取所有name为'chk_all'对象的状态（是否选中） 
   		$("input[name='chk_list']").attr("checked", false);
   		
   	}
   	else{
   		$("input[name='chk_list']").each(function(index,chk){chk.checked=true;});	
   		$("input[name='chk_all']").attr("checked", true);
   	}
   }
   function SelectAll2(){
	   	var chk_all = $("input[name='chk_all2']").attr("checked");
	   	if (chk_all == "checked"){ //读取所有name为'chk_all'对象的状态（是否选中） 
	   		$("input[name='chk_list2']").attr("checked", false);
	   		$("input[name='chk_all2']").attr("checked", false);
	   	}
	   	else{
	   		$("input[name='chk_list2']").each(function(index,chk){chk.checked=true;});	
	   		$("input[name='chk_all2']").attr("checked", true);
	   	}
	   }