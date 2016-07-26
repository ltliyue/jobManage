$(document).ready(function(){
	if($("#h1").length>0){
		$("#h1").append('<span id="span_1" class="nav navbar-nav navbar-right" style="float: right;margin-right: 20px;margin-top:0px"><a href="#dataType" class="dropdown-toggle" onclick="dataTypeMange()" title="分类查询">'
				+'<span aria-hidden="true" class="am-icon-search"></span></a><a href="#setting" class="dropdown-toggle" onclick="showConfig()" title="数据专题配置"><span aria-hidden="true" class="glyphicon glyphicon-cog"></span></a>'
				+'</span>');
	}
});