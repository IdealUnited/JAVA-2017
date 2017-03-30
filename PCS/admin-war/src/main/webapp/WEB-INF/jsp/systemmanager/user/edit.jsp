<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>编辑用户信息</title>
		<script type="text/javascript">
			function save() {
				var pars = $("#userInfoFrom").serialize();
				var url = "${ctx}/userAdd.do";

				if ($('#userKy').attr("value") != '')
					url = "user.do?method=update";
					
				$.ajax({
					type: "POST",
					url: url,
					data: pars,
					success: function(result) {
						if (result == 'success') {
							$('#closeBtn').click();
							userQuery();
							
							$('#handleMessageDiv').html("操作成功!");
							$('#handleMessageDiv').dialog('open');
						} else {
							$('#handleMessageDiv').html("操作失败!");
							$('#handleMessageDiv').dialog('open');
						}
					}
				});
			}

			//页面validate
			$(document).ready(function(){
				//聚焦第一个输入框
				$("#userName").focus();
				//为userInfoFrom注册validate函数
				$("#userInfoFrom").validate({
					rules: { 
						userName:"required",
						userCode:"required",
						userPassword:"required",
						userEmail:"email",
						userMobile:{
							number:true, 
							rangelength:[2,8]
						}
					}
				});
			});

		</script>
		
		
	</head>

	<body class="ModalDialog">
		<form id="userInfoFrom" action="userAdd.do">
			<input type="hidden" name="userKy" id="userKy" value="${user.userId}"/>
			<input type="hidden" name="personKy" id="personKy" value="${user.personKy}"/>
			<input type="hidden" name="staffKy" id="staffKy" value="${user.staffKy}"/>
		<table class="border_all2 inputTable" width="500" border="0" cellspacing="0" cellpadding="3" align="center">
	<tr class="trbgcolor10">
		<th class="border_top_right4 must">用户姓名</th>
		<td class="border_top_right4">
			<input type="text" name="userName" id="userName"/>
		</td>
	</tr>
	<tr>	
		<th class="border_top_right4 must">账号</th>
		<td class="border_top_right4">
			<input type="text" name="userCode" id="userCode"/>
		</td>
	</tr>
	<tr class="trbgcolor10">
		<th class="border_top_right4 must">密码</th>
		<td class="border_top_right4">
			<input type="password" name="userPassword" id="userPassword"/>&nbsp;&nbsp;
		</td>
		</tr>
	<tr>
		<th class="border_top_right4 must">状态</th>
		<td class="border_top_right4">
			<input type="radio" value="1" name="userStatus" checked="checked">激活
			<input type="radio" value="0" name="userStatus">禁用
		 </td>
	</tr>
	<tr class="trbgcolor10">
		<th class="border_top_right4">邮箱</th>
		<td class="border_top_right4">
			<input type="text" name="userEmail" id="userEmail">
		</td></tr>
	<tr>
		<th class="border_top_right4">电话</th>
		<td class="border_top_right4">
			<input type="text" name="userPhone" id="userPhone">
		</td>
	</tr>
	<tr class="trbgcolor10">
		<th class="border_top_right4" id="userMobile">手机</th>
		<td class="border_top_right4">
			<input type="text" name="userMobile">
		</td>
		</tr>
	<tr>
		<th class="border_top_right4">员工编号</th>
		<td class="border_top_right4">
			<input type="text" name="userRTX" id="userRTX">
		</td>
	</tr>
</table>
		</form>
	</body>
</html>
