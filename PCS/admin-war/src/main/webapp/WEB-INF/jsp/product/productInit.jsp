<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>产品管理</title>
		<script type="text/javascript">
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			
			function query(pageNo,totalCount,pageSize) {
				$('#infoLoadingDiv').dialog('open');
				var pars = $("#userSearchForm").serialize() + "&pageNo=" + pageNo + "&totalCount=" + totalCount+"&pageSize="+pageSize;
				$.ajax({
					type: "POST",
					url: "${ctx}/productSku.do?method=search",
					data: pars,
					success: function(result) {
						$('#infoLoadingDiv').dialog('close');
						$('#resultListDiv').html(result);
					}
				});
			}
			
			$(document).ready(function(){query();});
			
			
			function del(skuCode){
				if(confirm("确认删除吗？")){
					$('#infoLoadingDiv').dialog('open');
					var pars = "skuCode=" + skuCode;
					$.ajax({
						type: "POST",
						url: "${ctx}/productSku.do?method=del",
						data: pars,
						success: function(d) {
							$('#infoLoadingDiv').dialog('close');
							if(d == 1){
								alert('删除成功！');
								query();
							}else{
								alert('删除失败！');
							}
							
						}
					});
					
				}
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
						<font class="titletext">产 品 维 护</font>
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
				<td style="text-align:right;">SKU：</td>
				<td class="textLeft">
					<input type="text" name="skuCode" id="skuCode" style="width: 100px;" />
				</td>
				<td style="text-align:right;">Asin：</td>
				<td class="textLeft">
					<input type="text" name="asin" id="asin" style="width: 100px;" />
				</td>
			</tr>
			<tr>
				<td style="text-align:right;">产品名称：</td>
				<td class="textLeft">
					<input type="text" name="productName" id="productName" style="width: 100px;" onblur="getStockCode();"/>
				</td>
				<td style="text-align:right;">库位编号：</td>
				<td class="textLeft"><input type="text" name="stockCode" id="stockCode" style="width: 100px;" readonly="readonly"/></td>
			</tr>
			<!-- <tr>
				<td style="text-align:right;">中文品名：</td>
				<td class="textLeft">
					<input type="text" name="zhName" id="zhName" style="width: 100px;" />
				</td>
				<td style="text-align:right;">英文品名：</td>
				<td class="textLeft"><input type="text" name="enName" id="enName" style="width: 100px;"/></td>
			</tr> -->
			<tr>
				<td style="text-align:right;">销售平台：</td>
				<td class="textLeft">
					<input type="text" name="platform" id="platform" style="width: 100px;" />
				</td>
				<td style="text-align:right;">状态：</td>
				<td class="textLeft" colspan="4">
					<select name="status" id="status">
						<option value="">请选择</option>
						<option value="0">新建</option>
						<option value="1">审核通过</option>
						<option value="2">审核拒绝</option>
						<option value="3">下架</option>
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
		
