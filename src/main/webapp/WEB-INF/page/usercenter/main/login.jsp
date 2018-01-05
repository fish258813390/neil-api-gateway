<%@ page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>永乐生活通讯有限公司</title>
<link rel="icon" type="image/x-icon" href="<c:url value ='/platform-resource/images/favicon.ico'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/platform-resource/login.css'/>">
<script type="text/javascript" src="<c:url value='/platform-resource/jquery-1.8.0.min.js'/>"></script>
<script type="text/javascript">
	$(function(){
		sendSms();
		$("#forgetPwd").click(function(){
			window.location.href="<c:url value='/usercenter/main/forgetPwd.do'/>"
		});
	});
	if (window != top) {
		top.location.href = location.href;
	}
	var leftTime = 60;
	var invertalid;
	function CountDown() {
		if ($('#smsbtn')) {
			if (leftTime <= 0) {
				$('#smsbtn').attr('value', '重新获取校验码');
				$('#smsbtn').removeAttr("disabled");
			} else {
				$('#smsbtn').attr('value', '重新获取校验码' + leftTime + '');
				leftTime--;
			}
		}
	}
	
	function sendSms() {
		$("#loginError").text("");
		$.post("<c:url value='/usercenter/main/sendSmsCode.do'/>",function(data) {
			var obj = jQuery.parseJSON(data);
			if(obj.status){
				leftTime = 60;
				CountDown();
				intervalId = setInterval("CountDown()", 1000); 
			}else{
				if(obj.result != null){
					if(obj.result.redirect){
						$("#loginError").text(obj.externalMessage);
						setTimeout(function(){
							window.location.href=obj.result.url;
						},4000);
					}					
				}else{
					$("#loginError").text(obj.externalMessage);
				}
			}
		});
	}
	
	function validateSmsCode(){
		var smsCode = $("#smsCode").val();
		if(smsCode == null || "" == smsCode){
			$("#loginError").text("请输入短信校验码");
			return;
		}
		$.post("<c:url value='/usercenter/main/validateSmsCode.do'/>",{smsCode:smsCode},function(data) {
			var obj = jQuery.parseJSON(data);
			if(obj.status){
				window.location.href="<c:url value='/usercenter/main/desktop.do'/>";
			}else{
				if(obj.result != null){
					if(obj.result.redirect){
						$("#loginError").text(obj.externalMessage);
						setTimeout(function(){
							window.location.href=obj.result.url;
						},4000);
					}					
				}else{
					$("#loginError").text(obj.externalMessage);
				}
			}
		});
	}
	
	function time() {
		CountDown();
		intervalId = setInterval("CountDown()", 1000);
	}
</script>
</head>
<body onload="time()">
		<div class=" w1" id="entry" style="margin-top: 10%">
			<div class="mc " id="bgDiv">
				<div class="logbg"></div>
				<div class="form ">
					<br />
					<div class="item fore1">
						<span>登录账号</span>
						<div class="item-ifo">
							<input type="text" id="loginname"
								name="operatorAccount" value="**********" class="text" tabindex="1"
								autocomplete="off" autofocus="autofocus" readonly="readonly"/>
							<div class="i-name ico"></div>
						</div>
					</div>
					<div class="item fore2">
						<span>密码</span>
						<div class="item-ifo">
							<input type="password" id="loginpwd" name="operatorPassword" value="**********"
								class="text" tabindex="2" autocomplete="off" readonly="readonly"/>
							<div class="i-pass ico"></div>
						</div>
					</div>
					<div class="item fore3" id="o-authcode">
						<span>短信校验码</span>
						<div class="item-ifo">
							<input type="text" id="smsCode" class="text text-1" name="smsCode" tabindex="3" autocomplete="off" 
							style="ime-mode: disabled; width: 155px;" maxlength="6"/> 
							<input type="button"  disabled="disabled" id="smsbtn" name="smsbtn" value="获取短信校验码"
							style="cursor: pointer; width: 125px; height: 35px; margin: 0; padding: 0" onclick="sendSms()" />
						</div>
					</div>
					<div class="item fore4">
						<input type="button" class="btn-img btn-entry"  tabindex="4" value="登录" onClick="validateSmsCode()"/>
					</div>
					<div class="item fore5">
						<span id="forgetPwd" style="cursor: pointer;float:right">忘记密码？</span>
						<span id="loginError"></span>
					</div>
				</div>
			</div>
		</div>
</body>
</html>