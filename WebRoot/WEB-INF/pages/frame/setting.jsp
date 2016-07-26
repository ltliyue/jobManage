<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
function showTypeConfig(){
	$("#monitorContent").load("${webRoot}/dataType/showConfig");
}
function showReportConfig(){
	$("#monitorContent").load("${webRoot}/custReport/showConfig");
}
function showAllReports(){
	$("#monitorContent").load("${webRoot}/custReport/showAllReports");
}

$(document).ready(function(){
	showTypeConfig();
});
</script>
<!-- sidebar start -->
<div class="kfptny_left">
<div  class="menu_list"  style="margin-top: 25px;">  
       	<ul class="leftArea" id="monitorPane">
             	<li>
             		<a  href="javascript:void(0)">
             			<h2>
             				系统配置
             			</h2>
           			</a>
           			<div id="collapse-nav-c" class="am-collapse am-in">
           				<div style="display: block;" class="sub">
           					<h4>
           						<a style="margin-top:-15px;color:#s;font-size:14px;font-weight:bold;" href="javascript:void(0)" onclick="showTypeConfig()">专题配置</a>
       						</h4>
   							<hr >
   							<h4>
   								<a style="margin-top:-15px;color:#s;font-size:14px;font-weight:bold;" href="javascript:void(0)" onclick="showReportConfig()">客户配置</a>
							</h4>
							<hr >
							<h4>
   								<a style="margin-top:-15px;color:#s;font-size:14px;font-weight:bold;" href="javascript:void(0)" onclick="showAllReports()">报告管理</a>
							</h4>
							<hr >
						</div>
           			</div>
           		</li>
            </ul>    
     </div>
     </div>
 <!-- sidebar end -->

 <!-- monitorContent start -->
 <div class="kfselect" style="margin-left:280px;margin-top: 26px;margin-right: 20px"  id="monitorContent">
	 </div>
  <!-- monitorContent end -->