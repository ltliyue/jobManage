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

<title>互联网数据采集平台</title>

<!-- Bootstrap core CSS -->
<link href="<%=path%>/resources/css/new_version/bootstrap.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<link href="<%=path%>/resources/css/new_version/site.css"
	rel="stylesheet">
<link href="<%=path %>/resources/css/treegridCSS/jquery.treegrid.css"
	rel="stylesheet">
<link href="<%=path %>/resources/validform/css/style.css"
	rel="stylesheet">
<!-- Bootstrap core jss -->
<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
<script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
<script src="<%=path%>/resources/js/new_version/script.js"></script>
<script src="<%=path%>/resources/js/new_version/site.js"></script>
<script src="<%=path%>/resources/js/bootstrap-filestyle.js"></script>
<script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/treegrid/jquery.treegrid.bootstrap3.js"></script>
<script type="text/javascript"
	src="<%=path%>/resources/js/treegrid/jquery.treegrid.js"></script>
<script type="text/javascript"
	src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>

</head>
<script type="text/javascript">
	function delDemand() {
		if ($("input[name='chk_list']:checked").length > 0) {
			if (confirm("确定删除选中需求吗？")) {
				var del_id = '';
				$("input[name='chk_list']:checked").each(function(val) {
					del_id += $(this).val() + ";";
				});
				var url = "<%=path %>/demandAnaly/demanddel?id=" + del_id;
				$.post(url, function(data) {
					if(data=="1"){
						alert("删除成功！");
						window.location.href = "<%=path %>/demandAnaly/setdemand";
					}else{
						alert("删除失败！");
					}
				}, "text");
			}
		} else {
			alert("请选择一项需求进行删除！");
		}
	};
	
	function editDemand(){
		if ($("input[name='chk_list']:checked").length > 1) {
			alert("需求修改不能选中多条！");
		}else if($("input[name='chk_list']:checked").length == 1){
			var id = $("input[name='chk_list']:checked").val();
			if (confirm("确定跳转到需求修改页面吗？")) {
				window.location.href = "<%=path %>/demandAnaly/demandupdate?id="+id;
			}
		}else{
			alert("请选中一条需求进行修改！");
		}
	}
	
	function editDemandNew(demandId,demandName){
		if (confirm("确定跳转到名称为："+demandName+"  需求修改页面吗？")) {
			window.location.href = "<%=path %>/demandAnaly/demandupdate?id="+demandId;
		}
	}
	function delDemandNew(demandId,demandName){
		if (confirm("确定删除名称为："+demandName+"需求吗？")) {
			var url = "<%=path %>/demandAnaly/demanddel?id=" + demandId;
			$.post(url, function(data) {
				if(data=="1"){
					alert("删除成功！");
					window.location.href = "<%=path %>/demandAnaly/setdemand";
				}else{
					alert("删除失败！");
				}
			}, "text");
		}
	}
	
	function demandview(demandId,demandName,demandDetail,demandFilePath){
		$("#demandId").attr("value",demandId);
		$("#demandName").attr("value",demandName);
		$("#demandDetail").val(demandDetail);
		$("#demandFilePath").attr("value",demandFilePath); 
		file(demandId,demandFilePath);
	}
	
	function file(demandId,demandFilePath){
		var fileNameArr = demandFilePath.split(";");
		var code = "";
		if(demandFilePath.length!==0){
			for(i=0;i<fileNameArr.length;i++){
				code += "<a href='<%=path %>/demandAnaly/download?fileName="+fileNameArr[i]+"&demandId="+demandId+"'>"+fileNameArr[i]+"</a><br />";
			}
			$("#div").html(code);
		}else{
			$("#div").html("无附件可下载！");
		}
	}
	
	function datause(demandId,dataFeedback){
		$("#demandId3").attr("value",demandId);
		$("#datafeedback").text(dataFeedback);
	}
	
	function demandAnalyse(){
		window.location.href="<%=path %>/demandAnaly/demandAnalyse"
				+ "?demandId=" + $("#demandId").val();
	}
	
	function demandAnaly(demandId){
		window.location.href="<%=path %>/demandAnaly/demandAnalyse?demandId=" + demandId;
	}
	
	function feedback(demandId){
		<%-- window.open("<%=path %>/demandAnaly/feedback?demandId=" + demandId); --%>
		var url = "<%=path %>/demandAnaly/feedback?demandId=" + demandId;
		$.post(url, function(data) {
			var json = eval ('('+data+')');
			//一次eval竟然解决不了问题只好两次了
			var json1 = eval('('+json+')');
			var fileName = json1.filePath;
			var fileNameArr = fileName.split(";");
			var code = "";
			if(fileName.length!==0){
				for(i=0;i<fileNameArr.length;i++){
					code += "<a href='<%=path %>/demandAnaly/download?fileName="+fileNameArr[i]+"&demandId="+demandId+"'>"+fileNameArr[i]+"</a><br />";
				}
				$("#div2").html(code);
			}else{
				$("#div2").html("无附件可下载！");
			}
			$("#feedback").val(json1.feedback);
			$('input[name=method]').each(function(){
				if(this.value == json1.type ){
					$(this).attr('checked','true');
			}
			});
		}, "text");
	}
	
	
	</script>
<body style="margin: 0; padding: 0">
	<jsp:include page="../topBase.jsp"></jsp:include>
	<!--内容开始-->
	<div class="content_waikuang">
		<div class="container-fluid search_select select-result"
			style="padding-left: 0">
			<h2 class="content_title">${title}</h2>

			<%-- <div class="renwu_caozuo">
				<a class="renwu_select" href="<%=path%>/demandAnaly/demandAdd">新建需求</a>
				<!-- <a  class="renwu_select"  href="#" onclick="editDemand()">修改需求</a><a  class="renwu_select" href="#" onclick="delDemand()">删除需求</a> -->
			</div> --%>

			<div class="container-fluid search_select select-result"
				style="padding-left: 0; margin: 0 auto">
				<div class="table-responsive">
					<form action="<%=path%>/demandAnaly/setdemand" id="form1">
						<table class="table table-striped" id="demandlist">
							<thead>
								<tr>
									<th><input type="checkbox" name="chk_all" id="chk_all"
										onclick="allDemand()" /></th>
									<th>序号</th>
									<th>需求名称</th>
									<th>需求状态</th>
									<th>更新时间</th>
									<th>需求分析</th>
									<th>需求反馈</th>
									<th>数据使用反馈</th>
									<th>需求操作</th>
								</tr>
							</thead>
							<tbody id="tbody">
								<c:if test="${not empty demands}">
									<c:forEach var="demand" items="${demands}" varStatus="ts">
										<tr>
											<td><input type='checkbox' name='chk_list'
												value='${demand.demandId }'></td>
											<td>${ts.index+1 }</td>
											<td id='name' +ts><a class="content_xuqiumcan"
												onclick="demandview('${demand.demandId }','${demand.demandName }','${demand.demandDetail }','${demand.demandFilePath }')"
												data-target="#demandView" data-toggle="modal">${demand.demandName }</a></td>
											<td><c:choose>
													<c:when test="${demand.demandStatus == '01' }">需求提出</c:when>
													<c:when test="${demand.demandStatus == '02' }">需求分析</c:when>
													<c:when test="${demand.demandStatus == '03' }">需求确认</c:when>
													<c:when test="${demand.demandStatus == '04' }">需求关闭</c:when>
													<c:when test="${demand.demandStatus == '05' }">数据采集</c:when>
													<c:when test="${demand.demandStatus == '06' }">清洗验证</c:when>
													<c:when test="${demand.demandStatus == '07' }">数据交付</c:when>
												</c:choose></td>
											<td><fmt:formatDate value="${demand.demandTime}"
													pattern="yyyy-MM-dd hh:mm:ss" /></td>
											<td><button type="button"
													id="anaylse_${demand.demandId }"
													class="content_rwgl_pzanniu2"
													onclick="demandAnaly('${demand.demandId }')">编辑</button></td>
											<td><button type="button"
													id="feedback_${demand.demandId }"
													class="content_rwgl_pzanniu2" data-target="#feedbackView"
													data-toggle="modal"
													onclick="feedback('${demand.demandId }')">查看</button></td>
											<td><button type="button" id="datause_${demand.demandId }"
												class="content_rwgl_pzanniu2" data-target="#datause" data-toggle="modal"
												onclick="datause('${demand.demandId }','${demand.dataFeedback }')">查看</button></td>
											<td><a type="button" id="editDemand_${demand.demandId }"
												class="content_rwgl_pzanniu2" style="margin-right:5px"
												onclick="editDemandNew('${demand.demandId }','${demand.demandName }')">修改</a>
												<a type="button" id="delDemand_${demand.demandId }"
												class="content_rwgl_pzanniu2"
												onclick="delDemandNew('${demand.demandId }','${demand.demandName }')">删除</a>
											</td>
										</tr>

									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal hide fade" id="demandView" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">需求查看</h4>
				</div>
				<form id="form" method="POST">
					<input type="hidden" name="demandId" id="demandId">
					<div class="modal-body">
						<div class="form-group">
							<label for="exampleInputEmail1">需求名称</label> <input type="text"
								class="form-control" name='demandName' id="demandName"
								placeholder="需求名称" datatype="*" nullmsg="请输入需求名称" errormsg=""
								style="width: 96%; font-family: 'Microsoft JhengHei'">

						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">需求详情</label>
							<textarea style="width: 96%; font-family: 'Microsoft JhengHei'"
								class="form-control" name="demandDetail" id="demandDetail"
								rows="4" placeholder="请输入需求详情" nullmsg="请输入需求内容" errormsg=""
								datatype="*"></textarea>
						</div>
						<label for="demandDetail">附件</label>
						<div class="form-group" id="div"></div>
					</div>
				</form>
				<div id="div1" class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">返回列表</button>
					<button type="button" class="btn btn-primary" id="button"
						onclick="demandAnalyse()">需求分析</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal hide fade" id="feedbackView" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">意见反馈</h4>
				</div>
				<form action="<%=path%>/demandConfirm/save" id="form2">
					<input type="hidden" name="demandId" id="demandId">
					<div class="modal-body">
						<div class="form-group">
							<label for="exampleInputEmail1">反馈类型</label> <br> <input
								name="method" type="radio" value="reconfirm" checked="checked">再次分析后确认
							<br> <input name="method" type="radio" value="confirm">确认采集
							<br> <input name="method" type="radio" value="cancel">废弃本次需求
							<br>
						</div>
						<br>
						<div class="form-group">
							<label for="exampleInputEmail1">反馈意见</label>
							<textarea class="form-control content_zjnrcqtext" name="feedback"
								id="feedback" rows="4" placeholder="请输入反馈意见" nullmsg="请输入反馈意见"
								errormsg="" datatype="*"></textarea>
						</div>
						<label for="exampleInputEmail1">附件</label>
						<div class="form-group" id="div2"></div>
					</div>
				</form>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="close"
						data-dismiss="modal">关 闭</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal hide fade" id="datause" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">数据使用反馈</h4>
      </div>
      <form action="<%=path %>/demandConfirm/savedatause" id="form3" method="POST">
          <div class="modal-body">
          	<div class="form-group">
			<label for="exampleInputEmail1">数据使用反馈意见</label>
          	<textarea class="form-control content_cjhbtextarea" style="width: 95%" rows="4" name='datafeedback' id="datafeedback" placeholder="请输入数据使用反馈意见" nullmsg="请输入数据使用反馈意见" errormsg="" datatype="*" ></textarea>
			</div>
			</div>
            </form>
            <div class="modal-footer">
				<button type="button" class="btn btn-primary" id="close" data-dismiss="modal">关  闭</button>
			</div>
    </div>
  </div>
</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
</body>
</html>