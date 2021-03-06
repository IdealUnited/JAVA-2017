<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<script type="text/javascript">
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			
			function query(pageNo,totalCount,pageSize) {
				$('#infoLoadingDiv').dialog('open');
				var pars = $("#userSearchForm").serialize() + "&pageNo=" + pageNo + "&totalCount=" + totalCount+"&pageSize="+pageSize;
				$.ajax({
					type: "POST",
					url: "${ctx}/channelItem.do?method=search&channelId=${channel.id}",
					data: pars,
					success: function(result) {
						$('#infoLoadingDiv').dialog('close');
						$('#resultListDiv').html(result);
					}
				});
			}
			
			function returnBack(){
				window.location.href='${ctx}/channel.do';
			}
			
			$(document).ready(function(){query();});
			
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
						<font class="titletext">渠道明细</font>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
		</table>
		<form id="userSearchForm">
		<table border="0" style="width: 700px; " class="inputTable" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td style="text-align:center;">
					渠道名称：
					${channel.name }
				</td>
			</tr>
			
			<tr>
				<td style="text-align:center; height: 35px;" colspan="4">
					<a href="${ctx}/channelItem.do?method=addInit&channelId=${channel.id}" class="dialog_link ui-state-default ui-corner-all">
						<span class="ui-icon ui-icon-search"></span>&nbsp;添&nbsp;&nbsp;加&nbsp;
					</a>
					&nbsp;&nbsp;
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="query()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;查&nbsp;&nbsp;询&nbsp;
					</a>
					&nbsp;&nbsp;
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="returnBack()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;返&nbsp;&nbsp;回&nbsp;
					</a>
				</td>
			</tr>
		</table>
		</form>
		<div id="resultListDiv" class="listFence"></div>
		<div id="allOverlayDiv" class="ui-widget-overlay" style="display: none;"></div>
		<div id="infoLoadingDiv" title="加载信息" style="display: none; width: 200px; padding-top: 30px; height: 70px;">
			<img src="${ctx}/images/page/blue-loading.gif" />&nbsp;&nbsp;信息加载中, 请稍候...
		</div>
	</body>
</html>
		
