<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>update password</title>
<script type="text/javascript">
$(document).ready(function(){
	//聚焦第一个输入框
	$("#currentPassword").focus();
	//为inputForm注册validate函数
	
	$("#inputForm").validate({
		rules: { 
			currentPassword: "required",
			newPassword: {
				required: true,
				minlength:8
			}, 
			changePasswordConfirm: {
				equalTo:"#newPassword"
			}
		},
		messages: {
			changePasswordConfirm: {
				equalTo: "输入与上面相同的密码"
			}
		}
	});
	// 密码复杂度
	jQuery.validator.addMethod("passwordHard", function(pwd, element) {
	  var length = pwd.length;
	  var p1= (pwd.search(/[a-zA-Z]/)!=-1) ? 1 : 0; 
	  var p2= (pwd.search(/[0-9]/)!=-1) ? 1 : 0; 
	 // var p3= (pwd.search(/[^A-Za-z0-9_]/)!=-1) ? 1 : 0; 
			  
	  return  this.optional(element)  ||(p1 && p2);    
	}, "密码必须是8位以上，并且包含大小写字母和数字!"); 
});
</script>
</head>
<div id="message" align="center">
	<font color="red">${msg}</font>
</div>
<body>
	<form action="${ctx}/updatepassword.do?method=update" method="post" id="inputForm">
		<table align="center" border="0" cellpadding="0" cellspacing="1" class="tablesorter"
		style="width:40%; text-align:right;">
			<tr>
				<td>当前密码</td>
				<td align="left"><input type="password" name="currentPassword" id="currentPassword"/></td>
			</tr>
			<tr>
				<td>新密码</td>
				<td align="left"><input type="password" name="newPassword" id="newPassword"/></td>
			</tr>
			<tr>
				<td>确认密码</td>
				<td align="left"><input type="password" name="changePasswordConfirm" id="changePasswordConfirm"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="提交" />
				<input type="button" value="关闭"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>