<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<%--引入用于格式化页面的CSS文件--%>
<link rel="stylesheet" href="./css/main.css">
<script type="text/javascript">
	function processBack(){
		location.href = "user.do";
	}
	//页面validate
	$(document).ready(function(){
		// 手机号码验证    
		jQuery.validator.addMethod("isMobile", function(value, element) {
		  value = jQuery.trim(value);
		  var length = value.length;
		  return this.optional(element) || (length == 11 && /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(value));    
		}, "请输入正确的手机号码!");

		// 密码复杂度
		jQuery.validator.addMethod("passwordHard", function(pwd, element) {
		  var length = pwd.length;
		  var p1= (pwd.search(/[a-zA-Z]/)!=-1) ? 1 : 0; 
		  var p2= (pwd.search(/[0-9]/)!=-1) ? 1 : 0; 
		  var p3= (pwd.search(/[^A-Za-z0-9_]/)!=-1) ? 1 : 0; 
				  
		  return  this.optional(element)  ||(p1 && p2);    
		}, "密码必须是8位以上，并且包含大小写字母和数字!"); 

		// 不能包含全角字符
		jQuery.validator.addMethod("hasChn", function(value, element) {
		  return  (escape(value).indexOf("%u") < 0);    
		}, "不能包含中文字符"); 

		// 只能输入中文
		jQuery.validator.addMethod("onlyChn", function(value, element) {
		  return  ((/^[\u4e00-\u9fa5]+$/.test(value)));
		  }, "只能输入中文字符"); 
		
		
		
		//聚焦第一个输入框
		$("#userCode").focus();
		//为userInfoFrom注册validate函数
		$("#userInfoFrom").validate({
			rules: { 
			userCode:{
			required:true,
			rangelength:[4,40],
			hasChn:true
		},
				userName:{
				required:true,
				rangelength:[2,10],
				onlyChn:true
			},
		
				userPassword:{
					rangelength:[8,16]
				},
				userOrgKy:"required",
				userDutyKy:"required",
				userEmail:"email",
				userPhone:{
					number:true, 
					rangelength:[2,8]
				},
				userRTX:{
					number:true
				},
				userMobile:{
					isMobile:true
				}
			}
		});
		$(".must").each(function(i){
			$(this).html("<span style='color:red'>*</span>"+$(this).html());
		 });

		<c:if test="${user.userId != null}">
			$("#userName").val("${user.userName}");
			$("#userCode").val("${user.userCode}");
			$(":radio[name=userStatus]").val(["${user.userStatus}"]);
			$("#userOrgCode").val("${user.userOrgCode}");
			$("#userDutyCode").val("${user.userDutyCode}");
			$("#userPhone").val("${user.userPhone}");
			$("#userMobile").val("${user.userMobile}");
			$("#userRTX").val("${user.userRTX}");
			$("#userEmail").val("${user.userEmail}");
			
		</c:if>
	});
</script>
</head>

<body>
<br>
<br>
<p align="center">
<table width="25%" border="0" cellspacing="0" cellpadding="0" align="center" height="21">
	<tr>
		<td height="1" bgcolor="#000000"></td>
	</tr>
	<tr>
		<td height="18">
		<div align="center"><font class="titletext">修改用户</font></div>
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#000000"></td>
	</tr>
</table>



<form method="post" action="user.do?method=update" id="userInfoFrom">
		<input type="hidden" name="userKy" value="${user.userId}"/>
<table class="inputTable" width="500" border="0" cellspacing="0" cellpadding="3" align="center">
	
	<tr>
		<th class="must">登录账号</th>
		<td >
			<input type="text" name="userCode" id="userCode" readonly="readonly" style="color: gray;background-color: #ccc"/>
		</td>
	</tr>
	<tr >
		<th class="must">用户姓名</th>
		<td >
			<input type="text" name="userName" id="userName"/>
		</td>
	</tr>
	<tr >
		<th class="must">密码</th>
		<td>
			<input type="password" name="userPassword" id="userPassword"/>&nbsp;&nbsp;
		</td>
		</tr>
	<tr >
		<th class="must">状态</th>
		<td>
			<input type="radio" value="1" name="userStatus" checked="checked">激活
			<input type="radio" value="0" name="userStatus">禁用
		 </td>
	</tr>
	<tr >
		<th >邮箱</th>
		<td >
			<input type="text" name="userEmail" id="userEmail">
		</td></tr>
		<tr >
		<th >电话</th>
		<td >
			<input type="text" name="userPhone" id="userPhone">
		</td>
	</tr>
	<tr >
		<th>手机</th>
		<td >
			<input type="text" name="userMobile" id="userMobile">
		</td>
		</tr>
	<tr >
		<th >员工编号</th>
		<td >
			<input type="text" name="userRTX" id="userRTX">
		</td>
	</tr>
	<tr>
		<th >仓库编号</th>
		<td >
			<select id="repositoryId" name="repositoryId">
				<c:forEach items="${repositorys }" var="r">
				<option value="${r.id }" <c:if test="${user.repositoryId==r.id }">selected</c:if>>${r.name }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
</table>


<br>
<br>

<table class="border_all4" width="75%" border="0" cellspacing="0" cellpadding="0" align="center" id="buttonDisplay">
	<tr class="trbgcolor6" align="center">
		<td>
		 	<input type="submit" name="Submit" value="提交">
		 	<input type="button" onclick="javascript:processBack()" name="Submit2" value="返回">
		</td>
	</tr>
</table>
</form>
</body>