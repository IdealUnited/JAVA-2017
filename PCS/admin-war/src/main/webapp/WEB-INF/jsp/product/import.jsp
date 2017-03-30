<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>批量导入sku</title>
		<script type="text/javascript">
		
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
		
		function back(){
			window.location.href="${ctx}/productSku.do";
		}
		
		function importSku(){
			$('#userSearchForm').submit();
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
		<form id="userSearchForm" enctype="multipart/form-data" method="post" action="${ctx}/importProductSku.do?method=upload">
		<table border="0" style="width: 700px; " class="inputTable" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td style="text-align:right;">销售平台：</td>
				<td class="textLeft">
					<input type="text" name="platform"
					id="platform" style="width: 200px;" required="required" />
				</td>
			</tr>
			<tr>
				<td style="text-align:right;">批量导入销售sku：</td>
				<td class="textLeft">
					<input type="file" name="file"/><font color="red">${errorMsg}</font>
				</td>
			</tr>
			<tr>
				<td style="text-align:center; height: 35px;" colspan="4">
					<a href="${ctx}/importProductSku.do?method=download" class="dialog_link ui-state-default ui-corner-all">
						模版下载
					</a>
					&nbsp;&nbsp;
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="importSku()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;导入&nbsp;
					</a>
					&nbsp;&nbsp;
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="back()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;返回&nbsp;
					</a>
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>
		
