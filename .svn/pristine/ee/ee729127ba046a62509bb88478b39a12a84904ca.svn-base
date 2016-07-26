//添加菜单
function Addzy(id){
	OpenAddcdModel(id);
}
function clear(){
	$("#cdmc1").attr("value","");
	$("#zyurl1").attr("value","");
	$("#yxj1").attr("value","");
	$("#zid").attr("value","");
}
//打开添加页面model
function OpenAddcdModel(id){
	clear();
	$("#"+id).attr("data-am-modal","{target: '#doc-modal-2', closeViaDimmer: 0, width: 500}");
}
function AddInfo(){
	var url = $("#form1").attr("action");
	var data1 = $("#form1").serialize();
	$.post(url,data1,function(data){
		alert(data);
	});
}
function BjCd(){
	$("#bjcdmodel").attr("data-am-modal","{target: '#doc-modal-3', closeViaDimmer: 0, width: 500}");
}
function updatecd(){
	var url = $("#form2").attr("action");
	var data1 = $("#form2").serialize();
	$.post(url,data1,function(data){
		alert(data);
	});
}
function DelCd(url){
	$.post(url,function(data){
		alert(data);
	});
}
//实现选中时全选，取消时反选
function SelectAll(){
	var chk_all = $("input[name='chk_all']").attr("checked");
	if (chk_all == "checked"){ //读取所有name为'chk_all'对象的状态（是否选中） 
		$("input[name='chk_list']").attr("checked", false);
		$("input[name='chk_all']").attr("checked", false);	
	}
	else{// 取消全选 
		$("input[name='chk_list']").attr("checked", true);	
		$("input[name='chk_all']").attr("checked", true);	
	}
}
function DelZy(url){
	var del_id="";
	if(confirm("确定删除选中项目吗？")){
		$("input[name='chk_list']:checked").each(function(val){	
			del_id +=$(this).val()+",";
		});
		if(del_id==null||del_id==""){
			alert("请选择要删除项！");
			return false;
		}
		$.post(url+del_id,function(data){
			alert(data);
		});
	}
}
