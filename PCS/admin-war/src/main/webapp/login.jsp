<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" class="no-js">
<head>
	<meta charset="utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<meta name="description" content=""/>
	<meta name="author" content=""/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<title>Console Login</title>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<script src="${ctx}/js/jquery/js/jquery-1.4.2.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx}/js/login/login.js"></script>

	<!-- CSS -->
	<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
	<link rel="stylesheet" href="assets/css/reset.css">
	<link rel="stylesheet" href="assets/css/supersized.css">
	<link rel="stylesheet" href="assets/css/style.css">
<!-- 	 <style type="text/css">
	  *{margin:0px; padding:0px;}
	  img{border:0px;}
	  .inp,#authImg{
	  display:inlne;
	  float:left;
	  }
	  </style> -->
	<!-- Javascript -->
	<script src="assets/js/jquery-1.8.2.min.js"></script>
	<script src="assets/js/supersized.3.2.7.min.js"></script>
	<script src="assets/js/supersized-init.js"></script>
	<script src="assets/js/scripts.js"></script>
	<script type="text/javascript">
		//页面validate
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#j_username").focus();
			//为userInfoFrom注册validate函数
			var error = "";
			<c:if test="${not empty param.login_error || not empty param.access_error }">
			<c:choose>
			<c:when test="${param.login_error == 'error_rand_code'}">
			error = "验证码输入不正确！";
			</c:when>
			<c:when test="${param.login_error == 'error_auth_ip'}">
			error = "未授权的IP地址，不能登录！";
			</c:when>
			<c:when test="${param.login_error == 'user_not_active'}">
			error = "用户未激活，无法登录！请与系统管理员联系。";
			</c:when>
			<c:when test="${param.login_error == 'user_not_found'}">
			error = " 用户不存在！";
			</c:when>
			<c:when test="${param.login_error == 'userAuth_not_found'}">
			error = "用户或密码不正确！";
			</c:when>
			<c:when test="${param.login_error == 'securityStratety_not_found'}">
			error = " 用户的安全策略不存在！";
			</c:when>
			<c:when test="${param.login_error == 'user_locked'}">
			error = " 用户已被锁定，无法登录！请与系统管理员联系。";
			</c:when>
			<c:when test="${param.login_error == 'user_psw_error'}">
			error = " 无效的用户密码，请重试！";
			</c:when>
			<c:when test="${param.login_error == 'code_error'}">
			error = " 验证码输入错误！";
			</c:when>
			<c:when test="${param.login_error == 'too_many_error_login_times'}">
			error = " 用户已被锁定，因连续多次的错误登录！请与系统管理员联系。";
			</c:when>
			<c:when test="${param.login_error == 'too_many_user_error'}">
			error = " 该用户已经登录，请先退出！";
			</c:when>
			<c:when test="${param.login_error == 'error_extend_auth'}">
			error = " 扩展权限信息错误！";
			</c:when>
			<c:when test="${param.login_error == 'error_logined_user'}">
			error = "用户已经其他客户端登录，换个账户或联系管理员！";
			</c:when>
			<c:when test="${param.access_error == 'no_user'}">
			error = "未登录或用户超时了，请重新登录！";
			</c:when>
			<c:when test="${param.access_error == 'no_resource'}">
			error = "您没有权限访问，可以更改用户！";
			</c:when>

			</c:choose>
			$("#errorMsg").text(error);
			</c:if>

		});

		function onkeyD(e) {
			var theEvent = window.event || e;
			var code = theEvent.keyCode || theEvent.which;
			if (code == 13) {
				login();
			}
		}

		function login() {
	        var username = $("#j_username").val();
			var password = $("#j_password").val();
			var rand = $("#rand").val();
	        if(username == '') {
	        	$("#errorSign").fadeOut('fast', function(){
	                $(this).css('top', '27px');
	            });
	        	$("#errorSign").fadeIn('fast', function(){
	            	 $("#j_username").focus()
	            });
	            return false;
	        }
	  		if(password == '') {
	  			$("#errorSign").fadeOut('fast', function(){
	                $(this).css('top', '96px');
	            });
	  			$("#errorSign").fadeIn('fast', function(){
	  				 $("#j_password").focus()
	            });
	            return false;
	        }
	        if(rand == '') {
	        	$("#errorSign").fadeOut('fast', function(){
	                $(this).css('top', '155px');
	            });
	        	$("#errorSign").fadeIn('fast', function(){
	        		 $("#rand").focus()
	            });
	            return false;
	        }
			$("#loginform").submit();
		}
	</script>
</head>

<body>
	<div class="page-container">
		<h1>Login</h1>
		<form action="${ctx}/j_spring_security_check" id="loginform">
			<input type="text" name="j_username" id="j_username" class="username" placeholder="Username" onKeyDown="if (event.keyCode == 13)this.form.j_password.focus();">
			<input type="password" id="j_password" name="j_password" class="password" placeholder="Password">
			<input type="text" class="Captcha" name="rand" id="rand" placeholder="Validatecode" onKeyDown="onkeyD(event);"/>
			<img alt="验证码"  src="${ctx}/validatecode/validatecode.htm" height="42px" align="left" title="点图换一张" onClick="this.src='${ctx}/validatecode/validatecode.htm?d='+Math.random()"/></span>
			<input type="button" class="button" onClick="login()" value="Sign me in">
			<div id="errorSign" class="error"><span>+</span></div>
		</form>
		<!-- <div class="connect">
                <p>Or connect with:</p>
                <p>
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p>
            </div> -->
	</div>
</body>
</html>