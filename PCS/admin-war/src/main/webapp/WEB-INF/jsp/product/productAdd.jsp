<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>产品管理</title>
		<script type="text/javascript">
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
		
		function getStockCode() {
			var pars = "productName=" + $('#productName').val();
			$.ajax({
				type: "POST",
				url: "${ctx}/productSku.do?method=getStockCode",
				data: pars,
				success: function(result) {
					if(0 != result){
						$('#stockCode').val(result);
					}
				}
			});
		}
		
		function save() {
			
			if(!confirm("确认添加吗？")){
				return false;
			}
			
			var skuCode = $('#skuCode').val();
			if(''== skuCode){
				alert('请填写SKU!');
				return;
			}
			
			var productName = $('#productName').val();
			if(''== productName){
				alert('请填写产品名称!');
				return;
			}
			
			var platform = $('#platform').val();
			var asin = $('#asin').val();
			if(''== platform){
				alert('请填写销售平台!');
				return;
			}
			
			if(''==asin && (platform == 'Amazon' || platform == 'amazon')){
				alert('Amazon平台请填写asin!');
				return;
			}
			
			$('#infoLoadingDiv').dialog('open');
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			var pars = $("#userSearchForm").serialize();
			$.ajax({
				type: "POST",
				url: "${ctx}/productSku.do?method=add",
				data: pars,
				success: function(result) {
					$('#infoLoadingDiv').dialog('close');
					$('#resultListDiv').html(result);
				}
			});
		}
		
		$(document).ready(function(){query();});
		
		function importSku(){
			window.location.href="${ctx}/importProductSku.do";
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
						<font class="titletext">货品信息添加</font>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
		</table>
		<br/>
		<form id="userSearchForm">
		<table border="0" style="width: 700px; " class="inputTable" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td style="text-align:right;">SKU：</td>
				<td class="textLeft">
					<input type="text" name="skuCode" id="skuCode" style="width: 100px;" />
				</td>
				<td style="text-align:right;">Asin：</td>
				<td class="textLeft">
					<input type="text" name="asin" id="asin" style="width: 100px;" /><i>注：亚玛逊为必填</i>
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
			<tr>
				<td style="text-align:right;">中文品名：</td>
				<td class="textLeft">
					<input type="text" name="zhName" id="zhName" style="width: 100px;" />
				</td>
				<td style="text-align:right;">英文品名：</td>
				<td class="textLeft"><input type="text" name="enName" id="enName" style="width: 100px;"/></td>
			</tr>
			<tr>
				<td style="text-align:right;">销售平台：</td>
				<td class="textLeft">
					<input type="text" name="platform" id="platform" style="width: 100px;" />
				</td>
				<td class="textRight" colspan="2"></td>
			</tr>
			<tr>
				<td style="text-align:center; height: 35px;" colspan="4">
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="importSku()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;批量导入&nbsp;
					</a>
					&nbsp;&nbsp;
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="query()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;查&nbsp;&nbsp;询&nbsp;
					</a>
					&nbsp;&nbsp;
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="save()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;保&nbsp;&nbsp;存&nbsp;
					</a>
				</td>
			</tr>
		</table>
		</form>
		<div id="resultListDiv" class="listFence"></div>
		<div id="infoLoadingDiv" title="加载信息" style="display: none; width: 200px; padding-top: 30px; height: 70px;">
			<img src="${ctx}/images/page/blue-loading.gif" />&nbsp;&nbsp;信息加载中, 请稍候...
		</div>
	</body>
</html>
		
