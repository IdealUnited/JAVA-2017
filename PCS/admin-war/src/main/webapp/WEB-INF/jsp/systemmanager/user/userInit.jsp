<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>用户管理</title>
		<script type="text/javascript">
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			
			function userQuery(pageNo,totalCount,pageSize) {
				$('#infoLoadingDiv').dialog('open');
				var pars = $("#userSearchForm").serialize() + "&pageNo=" + pageNo + "&totalCount=" + totalCount+"&pageSize="+pageSize;
				$.ajax({
					type: "POST",
					url: "${ctx}/user.do?method=search",
					data: pars,
					success: function(result) {
						$('#infoLoadingDiv').dialog('close');
						$('#resultListDiv').html(result);
					}
				});
			}
			$(document).ready(function(){userQuery();});
			function deleteUser(code) {
				$('#deleteRoleDiv').html(loadImgStr);
				var url = "user.do?method=delete";
				var pars = {"id": code};
				$.ajax({
					type: "POST",
					url: url,
					data: pars,
					success: function(result) {
						$('#deleteRoleDiv').dialog('close');
						if (result == 'success') {
							$('#handleMessageDiv').html("操作成功!");
							$('#handleMessageDiv').dialog('open');
							userQuery();
						} else {
							alert(result);
							//$('#handleMessageDiv').dialog('open');
						}
					}
				});
			}
		</script>
	</head>

	<body>
		<table width="25%" border="0" cellspacing="0" cellpadding="0" align="center" height="21">
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
			<tr>
				<td height="18">
					<div align="center">
						<font class="titletext">用 户 维 护</font>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
		</table>
		
		<form id="userSearchForm">
		<table class="searchTermTable">
			<tr>
				<td class="textRight">用户名：</td>
				<td class="textLeft"><input type="text" name="userId" style="width: 150px;" /></td>
				<td class="textRight">用户姓名：</td>
				<td class="textLeft"><input type="text" name="userName" style="width: 150px;" /></td>
				<td class="textRight">状态：</td>
				<td class="textLeft">
					<select name="status">
						<option value="1">激活</option>
						<option value="2">全部</option>
						<option value="0">禁用</option>
					</select>
				</td>
				<td class="textRight">
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="userQuery()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;查&nbsp;&nbsp;询&nbsp;
					</a>&nbsp;&nbsp;&nbsp;
					<a href="${ctx}/userAdd.do" class="dialog_link ui-state-default ui-corner-all">
						<span class="ui-icon ui-icon-newwin"></span>&nbsp;添加用户&nbsp;
					</a>
				</td>
			</tr>
		</table>
		</form>
		<div id="resultListDiv" class="listFence"></div>
		<div id="deleteRoleDiv" title="删除用户信息"></div>
		<div id="allOverlayDiv" class="ui-widget-overlay" style="display: none;"></div>
		<div id="infoLoadingDiv" title="加载信息" style="display: none; width: 200px; padding-top: 30px; height: 70px;">
			<img src="${ctx}/images/page/blue-loading.gif" />&nbsp;&nbsp;信息加载中, 请稍候...
		</div>
	</body>
</html>
		
