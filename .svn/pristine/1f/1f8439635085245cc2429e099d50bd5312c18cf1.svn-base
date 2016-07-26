<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>错误请求</title>
	<script type="text/javascript">
	var msg = "${ex.message}";
	var message = msg.split(":");
	if(message.length>1) {
		alert(message[1]);
	}else {
		alert(msg);
	}
	</script>
  </head>
  <body>
  	<div id="msg" style="width: 100%;height:100%; text-align: center;vertical-align: middle;">
  	系统错误:${ex.message }
     </div>
  </body>
</html>
