<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <link href="<%=path%>/resources/css/new_version/bootstrap.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="<%=path%>/resources/css/new_version/site.css" rel="stylesheet">
	<link href="<%=path %>/resources/css/treegridCSS/jquery.treegrid.css" rel="stylesheet">
	<link href="<%=path %>/resources/validform/css/style.css" rel="stylesheet">
	 <!-- Bootstrap core jss -->
	<script src="<%=path%>/resources/js/new_version/jquery.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/bootstrap.min.js"></script>
    <script src="<%=path%>/resources/js/new_version/script.js"></script>
    <script src="<%=path%>/resources/js/new_version/site.js"></script>
    <script src="<%=path%>/resources/js/bootstrap-filestyle.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/checkbox.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.bootstrap3.js"></script>
    <script type="text/javascript" src="<%=path%>/resources/js/treegrid/jquery.treegrid.js"></script>
    <script type="text/javascript" src="<%=path %>/resources/validform/js/Validform_v5.3.2_min.js"></script>
	
  </head>
	<script type="text/javascript">
	var demo;
	var demo1;
	$(function(){
		demo=$("#form2").Validform({
			tiptype:3,
			showAllError:true
		});
		demo1=$("#form3").Validform({
			tiptype:3,
			showAllError:true
		});
		$('input[name=method]').each(function(){
			if(this.value == "${feedback.type}" ){
				$(this).attr('checked','true');
		}
		});
	});
	
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
		for(i=0;i<fileNameArr.length;i++){
			code += "<a href='<%=path %>/demandAnaly/download?fileName="+fileNameArr[i]+"&demandId="+demandId+"'>"+fileNameArr[i]+"</a><br />";
		}
		$("#div").html(code);
	}
	
	function demandAnalyse(){
		window.location.href="<%=path %>/demandConfirm/demandAnalyse"
				+ "?demandId=" + $("#demandId").val();
	}
	
	function demandAnaly(demandId,demandStatus){
		if(demandStatus=='01'){
			alert("需求尚未分析，请到需求分析页面进行需求分析！");
		}else{
			window.location.href="<%=path %>/demandConfirm/demandAnalyse?demandId=" + demandId;
		}
	}
	
	function feedback(demandId){
		<%-- window.open("<%=path %>/demandAnaly/feedback?demandId=" + demandId); --%>
		var url = "<%=path %>/demandAnaly/feedback?demandId=" + demandId;
		$.post(url, function(data) {
			var json = eval ('('+data+')');
			//一次eval竟然解决不了问题只好两次了
			var json1 = eval('('+json+')');
			$("#demand").attr("value",json1.demandId);
			$("#idN").attr("value",json1.id);
		}, "text");
	}
	
	function datause(demandId,dataFeedback){
		$("#demandId3").attr("value",demandId);
		$("#datafeedback").text(dataFeedback);
	}
	
	function savedatause(){
		var status = demo1.check(false);
		if(status==true){
			var value = $("#datafeedback").val();
			var demandId = $("#demandId3").val();
			var url = "<%=path %>/demandConfirm/datafeedback?demandId="+demandId+"&dataFeedback="+value;
			$.post(url, function(data){
				if(data=='1'){
					alert("数据使用反馈保存成功！");
				}else{
					alert("数据使用反馈保存失败！");
				}
			}, "text");
		}
	}
	
	function save(){
		 var status = demo.check(false);
			if(status==true){
				var value = $('input[name="method"]:checked').val();
				$("#methodNew").attr("value",value);
				document.getElementById("form2").submit();
			}
	}
	</script>
 <body style="margin:0; padding:0">
	<jsp:include page="../topBase.jsp"></jsp:include>
  <!--内容开始-->
    <div  class="content_waikuang">
<div class="container-fluid search_select select-result" style="padding-left:0; margin-top:10px">
<h2 class="content_title">${title}</h2>
<div class="container-fluid search_select select-result" style="padding-left:0; margin:0 auto">
		<div class="table-responsive">
          <form action="<%=path %>/demandConfirm/setconfirm" id="form1">
            <table class="table table-striped" id = "demandlist">
              <thead>
                <tr>
               	  <th><input type="checkbox" name="chk_all" id="chk_all" onclick="allDemand()" /></th>
                  <th>序号</th>
                  <th>需求名称</th>
                  <th>需求状态</th>
                  <th>更新时间</th>
                  <th>需求分析</th>
                  <th>需求反馈</th>
                  <th>数据使用反馈</th>
                </tr>
              </thead>
              <tbody id="tbody">
              	<c:if test="${not empty demands}">
						<c:forEach var="demand" items="${demands}" varStatus="ts">
							<tr>
								<td><input type='checkbox' name='chk_list'
									value='${demand.demandId }'></td>
								<td>${ts.index+1 }</td>
								<td id='name' +ts><a  class="content_xuqiumcan" onclick="demandview('${demand.demandId }','${demand.demandName }','${demand.demandDetail }','${demand.demandFilePath }')" data-target="#demandView" data-toggle="modal">${demand.demandName }</a></td>
								<td>
								<c:choose>
									<c:when test="${demand.demandStatus == '01' }">需求提出</c:when>
									<c:when test="${demand.demandStatus == '02' }">需求分析</c:when>
									<c:when test="${demand.demandStatus == '03' }">需求确认</c:when>
									<c:when test="${demand.demandStatus == '04' }">需求关闭</c:when>
									<c:when test="${demand.demandStatus == '05' }">数据采集</c:when>
									<c:when test="${demand.demandStatus == '06' }">清洗验证</c:when>
									<c:when test="${demand.demandStatus == '07' }">数据交付</c:when>
								</c:choose>
								</td>
								<td><fmt:formatDate value="${demand.demandTime}"
										pattern="yyyy-MM-dd hh:mm:ss" /></td>
								<td><button type="button" id="anaylse_${demand.demandId }"
											class="content_rwgl_pzanniu2"
											onclick="demandAnaly('${demand.demandId }','${demand.demandStatus }')">查看</button></td>
								<td><button type="button" id="feedback_${demand.demandId }"
									class="content_rwgl_pzanniu2" data-target="#feedback" data-toggle="modal"
									onclick="datause('${demand.demandId }')">编辑</button></td>
								<td><button type="button" id="datause_${demand.demandId }"
									class="content_rwgl_pzanniu2" data-target="#datause" data-toggle="modal"
									onclick="datause('${demand.demandId }','${demand.dataFeedback }')">编辑</button></td>
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
     <div class="modal fade" id="demandView" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">需求查看</h4>
      </div>
      <form id="form" method="POST">
      <input type="hidden" name="demandId" id="demandId">
      <div class="modal-body">
        	<div class="form-group">
				<label for="exampleInputEmail1">需求名称</label>
				<input type="text" class="form-control" name='demandName' id="demandName"
					placeholder="需求名称" datatype="*" nullmsg="请输入需求名称" errormsg="" style="width:96%;font-family:'Microsoft JhengHei'">
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">需求详情</label>
				<textarea style="width:96%;font-family:'Microsoft JhengHei'" class="form-control" name="demandDetail" id="demandDetail" rows="4" placeholder="请输入需求详情" nullmsg="请输入需求内容" errormsg="" datatype="*" ></textarea>
			</div>
			<label for="demandDetail">附件</label>
			<div class="form-group" id="div">
			
			</div>
      </div>
      </form>
      <div id="div1" class="modal-footer">
      	<button type="button" class="btn btn-primary" id="button" onclick="demandAnalyse()">分析结果</button>
      	<button type="button" class="btn btn-default" data-dismiss="modal">关  闭</button>
      </div>
    </div>
  </div>
</div>

<div class="modal hide fade" id="feedback" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">意见反馈</h4>
      </div>
      <form action="<%=path %>/demandConfirm/save" id="form2" method="POST" enctype="multipart/form-data">
          <input type="hidden" name="demand" id="demand" >
          <input type="hidden" name="idN" id="idN" >
          <input type="hidden" name="methodNew" id="methodNew" >
          <div class="modal-body">
            <div class="form-group">
				<label for="exampleInputEmail1">反馈类型</label>
				<input name="method" type="radio" value="reconfirm" checked="checked">再次分析后确认
				<br>
				<input name="method" type="radio" value="confirm">确认采集
				<br>
				<input name="method" type="radio" value="cancel">废弃本次需求
				<br>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">反馈意见</label>
				<textarea class="form-control content_zjnrcqtext" name="feedback" id="feedback" rows="4" placeholder="请输入反馈意见" nullmsg="请输入反馈意见" errormsg="" datatype="*" >${feedback.feedback }</textarea>
			</div>
			<div class="form-group" >
				<label for="exampleInputEmail1">附件</label>
				<input type = "file" class="filestyle" name="feedfile" multiple id="feedfile" data-icon="false" data-buttonText="选择文件">
			</div>
			</div>
            </form>
            <div class="modal-footer">
				<button type="button" class="btn btn-primary" id="save" onclick="save()">保  存</button>
				<button type="button" class="btn btn-primary" id="close" data-dismiss="modal">取  消</button>
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
          <input type="hidden" name="demandId3" id="demandId3" >
          <div class="modal-body">
          	<div class="form-group">
			<label for="exampleInputEmail1">数据使用反馈意见</label>
          	<textarea class="form-control content_cjhbtextarea" style="width: 95%" rows="4" name='datafeedback' id="datafeedback" placeholder="请输入数据使用反馈意见" nullmsg="请输入数据使用反馈意见" errormsg="" datatype="*" ></textarea>
			</div>
			</div>
            </form>
            <div class="modal-footer">
				<button type="button" class="btn btn-primary" id="savedatause" onclick="savedatause()">保  存</button>
				<button type="button" class="btn btn-primary" id="close" data-dismiss="modal">取  消</button>
			</div>
    </div>
  </div>
</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
    </body>
</html>