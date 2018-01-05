<%@ page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>永乐生活通讯有限公司</title>
<style>
.errorWrap{
	width:380px;
	margin:0 auto;
	margin-top:20%;
	font:normal normal 16px normal  "微软雅黑";
}
</style>
<script type="text/javascript">
var i = 4; 
function shownum(){
	i=i-1; 
	document.getElementById("time").innerHTML=i;
	if(i == 0){
		window.top.location.href=document.getElementById("url").value;
	}else{
		setTimeout('shownum()',1000); 
	}
}
</script>
</head>
<body onload="shownum()">
	<input type="hidden" id="url" value="${redirectUrl}"> 
	<div class="errorWrap">
		<span>${loginError}</span>
		<span id="time"></span> 
		秒后将自动跳转登录页面
	</div>
</body>
</html>
