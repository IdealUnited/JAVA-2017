<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head> 
		<title>角色查询</title>
		<script type="text/javascript">
				var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
				
				function roleQuery(pageNo,totalCount,pageSize) {
					$('#infoLoadingDiv').dialog('open');
					var pars = $("#searchForm").serialize() + "&pageNo=" + pageNo + "&totalCount=" + totalCount+"&pageSize="+pageSize;
					$.ajax({
						type: "POST",
						url: "${ctx}/roleAdmin.do",
						data: pars,
						success: function(result) {
							$('#infoLoadingDiv').dialog('close');
							$('#resultListDiv').html(result);
						}
					});
				}
	
				$(document).ready(function(){
					roleQuery();
				
				$("#roleName").bind('keydown', function (e) {

		            var key = e.which;

		            if (key == 13) {

		            	roleQuery();

		            }

		        });
				
				
				}); 
				
				function deleteRole(code) {
					$('#deleteRoleDiv').html(loadImgStr);
					var url = "roleAdmin.do?method=delete";
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
								
								roleQuery();
							} else {
								$('#handleMessageDiv').html("操作失败!");
								$('#handleMessageDiv').dialog('open');
							}
						}
					});
				}
		</script>
</head>
<body>
	<table width="25%" border="0" cellspacing="0" cellpadding="0" align="center" height="21" style="">
		<tr>
			<td height="1" bgcolor="#000000"></td>
		</tr>
		<tr>
			<td height="18">
				<div align="center">
					<font class="titletext">角色管理</font>
				</div>
			</td>
		</tr>
		<tr>
			<td height="1" bgcolor="#000000"></td>
		</tr>
	</table>
	
	<form action="${ctx }/roleAdmin.do" method="post" id="searchForm">
	<table class="searchTermTable">
		<tr>
			<td class="textRight">		
				          角色名称: 
			</td>
			<td class="textLeft">
				<input type="text" name="roleName" id="roleName" value="${roleName}" size="20" />(输入关键字查询，支持模糊查询)
			</td>
			<td class="textRight">
				<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="roleQuery()" >
						<span class="ui-icon ui-icon-search"></span>&nbsp;查&nbsp;&nbsp;询&nbsp;
				</a>&nbsp;&nbsp;&nbsp;
				<a href="${ctx}/roleAdmin.do?method=init&roleKy=" class="dialog_link ui-state-default ui-corner-all">
						<span class="ui-icon ui-icon-newwin"></span>&nbsp;添加角色&nbsp;
				</a>
			</td>
		</tr>
	</table>
	<input type="hidden" name="method"  value="search" size="20" onkeyup="catchEnter()"/>
	</form>
	<div id="resultListDiv" class="listFence"></div>
	<div id="deleteRoleDiv" title="删除角色信息"></div>
	<div id="allOverlayDiv" class="ui-widget-overlay" style="display: none;"></div>
	<div id="infoLoadingDiv" title="加载信息" style="display: none; width: 200px; padding-top: 30px; height: 70px;">
		<img src="${ctx}/images/page/blue-loading.gif" />&nbsp;&nbsp;信息加载中, 请稍候...
	</div>
</body>
</html>