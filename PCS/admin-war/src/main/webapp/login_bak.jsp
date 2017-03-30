<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<title>国际贸易管理平台</title>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="${ctx}/js/jquery/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/login/login.js"></script>
<link href="${ctx}/css/login/login2.css" rel="stylesheet" type="text/css" />
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
				error =  "用户或密码不正确！";
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

		function login(){
			var un = $("#j_username").val();
			var pwd = $("#j_password").val();
			var rand = $("#rand").val();
			if(!un){
				$("#errorMsg").text("用户名不能为空");
				return ;
			}
			if(!pwd){
				$("#errorMsg").text("密码不能为空");
				return ;
			}
			if(!rand){
				$("#errorMsg").text("验证码不能为空");
				return ;
			}
			$("#loginform").submit();
		}
		
</script>
</head>
<body>
<h1>我们自己的国际贸易管理平台<sup>2016</sup></h1>

<div class="login" style="margin-top:50px;">
    
    <div class="header">
        <div class="switch" id="switch"><a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
			<a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">&nbsp;</a><div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
        </div>
    </div>    
  
    
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 290px;">    

            <!--登录-->
            <div class="web_login" id="web_login">
               
               
               <div class="login-box">
    
            
			<div class="login_form">
				<form method="post" action="${ctx}/j_spring_security_check" id="loginform">
				<!-- form action="" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post" -->
                <div class="uinArea" id="uinArea">
                <label class="input-tips" for="u">帐&nbsp;&nbsp;&nbsp;号：</label>
                <div class="inputOuter" id="uArea">
                    <input type="text" id="j_username" name="j_username" class="inputstyle" onKeyDown="if (event.keyCode == 13)this.form.j_password.focus();"/>
                </div>
                </div>
                
                <div class="pwdArea" id="pwdArea">
               	<label class="input-tips" for="p">密&nbsp;&nbsp;&nbsp;码：</label> 
               	<div class="inputOuter" id="pArea">
                    <input type="password" id="j_password" name="j_password" class="inputstyle"/>
                </div>
                </div>
                
                <div class="uinArea" id="uinArea">
                <label class="input-tips" for="u">验证码：</label>
                <div class="inputOuter" id="uArea">
                    <input type="text" id="rand" name="rand" class="inputstyle" style="width:100px;" onKeyDown="onkeyD(event);" title="输入右边的验证码,点图换一张" maxlength="5"/>
                    <img alt="验证码" src="${ctx}/validatecode/validatecode.htm" height="33" align="top" title="点图换一张" onClick="this.src='${ctx}/validatecode/validatecode.htm?d='+Math.random()">
                </div>
                </div>
                
                <div style="padding-left:50px;margin-top:20px;"><input type="button" value="登 录" style="width:150px;" class="button_blue" onClick="login()"/></div>
              </form>
           </div>
           
            	</div>
               
            </div>
            <!--登录end-->
  </div>

</div>
</body>
</html>