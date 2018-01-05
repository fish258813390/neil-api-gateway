<%@ page import="java.util.UUID" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台系统</title>
    <%--<link rel="icon" type="image/x-icon" href="<c:url value ='/platform-resource/images/favicon.ico'/>"/>--%>
    <link rel="stylesheet" type="text/css" href="<c:url value='/platform-resource/login.css'/>">
    <script type="text/javascript" src="<c:url value='/platform-resource/jquery-1.8.0.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/platform-resource/encrypt.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/platform-resource/rsa.js'/>"></script>
    <script type="text/javascript">

        $(function () {


        })


        function submit() {
//            if ($.trim($("#loginname").val()) == "") {
//                $("#loginname").focus();
//                $("#error").text("请输入登录账号");
//                return;
//            }
//            if ($.trim($("#loginpwd").val()) == "") {
//                $("#loginpwd").focus();
//                $("#error").text("请输入登录密码");
//                return;
//            }
//            if ($.trim($("#authcode").val()) == "") {
//                $("#authcode").focus();
//                $("#error").text("请输入验证码");
//                return;
//            }
//            var key = RSAUtils.getKeyPair($("#exponent").val(), '', $("#modulus").val());
//            var loginName = RSAUtils.encryptedString(key, $("#loginname").val());
//            var loginPassword = RSAUtils.encryptedString(key, $.md5($("#loginpwd").val()));
            <%--$.ajax({--%>
            <%--url:"<c:url value='/user/main/doLogin.do'/>",--%>
            <%--type:'POST',--%>
            <%--async:true,--%>
            <%--data:{--%>
            <%--loginName:loginName,--%>
            <%--loginPassword:loginPassword,--%>
            <%--verifyCode:$("#authcode").val()--%>
            <%--},--%>
            <%--success:function(data){--%>
            <%--var obj = jQuery.parseJSON(data);--%>
            <%--if(obj.status){--%>
            <%--window.location.href="<c:url value='/user/main/desktop.do'/>"--%>
            <%--}else{--%>
            <%--reloadcode();--%>
            <%--$("#error").text(obj.externalMessage);--%>
            <%--}--%>
            <%--},--%>
            <%--error: function(XMLHttpRequest, textStatus, errorThrown) {--%>
            <%--if(errorThrown=="timeout"){--%>
            <%--$("#error").text("您的网速不给力，请求超时");--%>
            <%--}else{--%>
            <%--$("#error").text("请求服务器出错，请刷新重试");--%>
            <%--}--%>
            <%--}--%>
            <%--});--%>
            //$("#loginpwd").val($.md5($.md5($("#loginpwd").val())+$("#loginToken").val()));

            window.location.href = "<c:url value='/usercenter/main/desktop.do'/>"

        }


    </script>


</head>

<body>
<div class=" w1" id="entry" style="margin-top: 10%">
    <input type="hidden" id="modulus" value=""/>
    <input type="hidden" id="exponent" value=""/>
    <div class="mc " id="bgDiv">
        <div class="logbg"></div>
        <div class="form" id="">
            <%-- <form method="post"> --%>
            <br/>
            <div class="item fore1">
                <span>登录账号</span>
                <div class="item-ifo">
                    <input type="text" id="loginname" name="operatorAccount" class="text" tabindex="1"
                           autocomplete="off" autofocus="autofocus"/>
                    <div class="i-name ico"></div>
                </div>
            </div>
            <div class="item fore2">
                <span>密码</span>
                <div class="item-ifo">
                    <input type="password" id="loginpwd" name="operatorPassword" class="text" tabindex="2"
                           autocomplete="off"/>
                    <div class="i-pass ico"></div>
                </div>
            </div>
            <div class="item fore3" id="o-authcode">
                <span>验证码</span>
                <div class="item-ifo">
                    <input type="text" id="authcode" class="text text-1" name="authcode" tabindex="3" autocomplete="off"
                           maxlength="4" style="ime-mode: disabled; width: 155px;"/>
                    <label class="img">
                        <img style="cursor: pointer; width: 125px; height: 34px; margin: 0; padding: 0"
                             src="<c:url value='/usercenter/main/verifyCode/getVerifyCode.do'/>" onclick="reloadcode()"
                             id="codevalidate"/>
                    </label>
                </div>
            </div>
            <%--</form> --%>
            <div class="item fore4">
                <input type="button" class="btn-img btn-entry" id="loginsubmit" onclick="submit()" tabindex="4"
                       value="登录">
            </div>
            <div class="item fore5">
                <span id="error" style="color: red;">${error}</span>
            </div>
        </div>
    </div>
</div>
</body>
</html>