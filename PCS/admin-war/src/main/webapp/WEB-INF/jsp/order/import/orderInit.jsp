<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>订单管理</title>
		<script type="text/javascript">
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			
			function query(pageNo,totalCount,pageSize) {
				$('#infoLoadingDiv').dialog('open');
				var pars = $("#userSearchForm").serialize() + "&pageNo=" + pageNo + "&totalCount=" + totalCount+"&pageSize="+pageSize;
				$.ajax({
					type: "POST",
					url: "${ctx}/todoOrder.do?method=search",
					data: pars,
					success: function(result) {
						$('#infoLoadingDiv').dialog('close');
						$('#resultListDiv').html(result);
					}
				});
			}
			
		function sub(flag) {
			$('#uploadForm').submit();				
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
						<font class="titletext">导入订单</font>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
		</table>
		
		<form id="uploadForm" method="post" action="${ctx}/importOrder.do?method=importOrder" enctype="multipart/form-data" >
		<table border="0" style="width: 900px; " class="inputTable" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td style="text-align:right;">
					平台：<select id="platform" name="platform">
						<option value="">请选择</option>
						<option value="Amazon">Amazon</option>
						<option value="suMaiTong">速卖通</option>
						<option value="ebay">ebay</option>
						<option value="wish">wish</option>
					</select>
				</td>
				<td style="text-align:left;"><input type="file" name="file"/></td>
			</tr>
			
			<tr>
				<td style="text-align:center; height: 35px;" colspan="2">
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="sub()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;导&nbsp;&nbsp;入&nbsp;
					</a>
					&nbsp;&nbsp;&nbsp;
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="query()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;查&nbsp;&nbsp;询&nbsp;
					</a>
				</td>
			</tr>
		</table>
		</form>
		<div>${responseDesc }</div>
		<div id="resultListDiv" class="listFence"></div>
		<div id="allOverlayDiv" class="ui-widget-overlay" style="display: none;"></div>
		<div id="infoLoadingDiv" title="加载信息" style="display: none; width: 200px; padding-top: 30px; height: 70px;">
			<img src="${ctx}/images/page/blue-loading.gif" />&nbsp;&nbsp;信息加载中, 请稍候...
		</div>
	</body>
</html>
		
