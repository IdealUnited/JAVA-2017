<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>汇率管理</title>
		<script type="text/javascript">
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			
			function query(pageNo,totalCount,pageSize) {
				$('#infoLoadingDiv').dialog('open');
				var pars = $("#userSearchForm").serialize() + "&pageNo=" + pageNo + "&totalCount=" + totalCount+"&pageSize="+pageSize;
				$.ajax({
					type: "POST",
					url: "${ctx}/exchangeRate.do?method=search",
					data: pars,
					success: function(result) {
						$('#infoLoadingDiv').dialog('close');
						$('#resultListDiv').html(result);
					}
				});
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
						<font class="titletext">汇率信息</font>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
		</table>
		
		<form id="userSearchForm">
		<table border="0" style="width: 900px; " class="inputTable" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td align="right" class="border_top_right4" >币种：</td>
	      		<td class="border_top_right4">
			      	<select name="currency" id="currency">
			      		<option value="" selected>---请选择---</option>
			      		<c:forEach var="currency" items="${currencys}" varStatus="v">
							<option value="${currency.code}">${currency.desc}</option>
						</c:forEach>
			      	</select>
			      </td>
			      <td align="right" class="border_top_right4" >目标币种：</td>
			      <td class="border_top_right4">
			      	<select name="targetCurrency" id="targetCurrency">
			      		<option value="" selected>---请选择---</option>
			      		<c:forEach var="currency" items="${currencys}" varStatus="v">
							<option value="${currency.code}">${currency.desc}</option>
						</c:forEach>
			      	</select>
			      </td>
			</tr>
			
			<tr>
				<td style="text-align:center; height: 35px;" colspan="4">
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="query()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;查&nbsp;&nbsp;询&nbsp;
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
		
