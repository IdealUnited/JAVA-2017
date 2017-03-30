<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>产品管理</title>
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
		
		function update() {
			
			if(!confirm("确认修改吗？")){
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
			
			var pars = $("#userSearchForm").serialize();
			$.ajax({
				type: "POST",
				url: "${ctx}/productSku.do?method=update",
				data: pars,
				success: function(result) {
					if(result==1){
						alert('更新成功！');
					}else{
						alert('更新失败！');
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
						<font class="titletext">货品信息修改</font>
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
					<input type="text" name="skuCode" id="skuCode" value="${productSku.skuCode }" readonly="readonly" style="width: 100px;" />
				</td>
				<td style="text-align:right;">Asin：</td>
				<td class="textLeft">
					<input type="text" name="asin" id="asin" style="width: 100px;" value="${productSku.asin }"/><i>注：亚玛逊为必填</i>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;">产品名称：</td>
				<td class="textLeft">
					<input type="text" name="productName" id="productName" value="${productSku.productName }" style="width: 100px;" onblur="getStockCode();"/>
				</td>
				<td style="text-align:right;">库位编号：</td>
				<td class="textLeft"><input type="text" name="stockCode" id="stockCode" style="width: 100px;" readonly="readonly" value="${productSku.stockCode }"/></td>
			</tr>
			<tr>
				<td style="text-align:right;">中文品名：</td>
				<td class="textLeft">
					<input type="text" name="zhName" id="zhName" value="${productSku.zhName }" style="width: 100px;" />
				</td>
				<td style="text-align:right;">英文品名：</td>
				<td class="textLeft"><input type="text" name="enName" id="enName" value="${productSku.enName }" style="width: 100px;"/></td>
			</tr>
			<tr>
				<td style="text-align:right;">销售平台：</td>
				<td class="textLeft">
					<input type="text" name="platform" id="platform" value="${productSku.platform }" style="width: 100px;" />
				</td>
				<td style="text-align:right;">状态：</td>
				<td class="textLeft">
					<select name="status" id="status">
						<option value="1" <c:if test="${productSku.status == 2}">selected</c:if>>审核通过</option>
						<option value="2" <c:if test="${productSku.status == 3}">selected</c:if>>审核拒绝</option>
						<option value="3" <c:if test="${productSku.status == 3}">selected</c:if>>下架</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align:center; height: 35px;" colspan="4">
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="update()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;保&nbsp;&nbsp;存&nbsp;
					</a>
					&nbsp;&nbsp;
					<a href="${ctx}/productSku.do" class="dialog_link ui-state-default ui-corner-all">
						<span class="ui-icon ui-icon-search"></span>&nbsp;返&nbsp;&nbsp;回&nbsp;
					</a>
				</td>
			</tr>
		</table>
		</form>
	</body>
</html>
		
