<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

	<head>
		<script type="text/javascript">
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			
			function edit() {
				
				if(!confirm("确认修改吗？")){
					return false;
				}
				
				var stockCode = $('#stockCode').val();
				if(''== stockCode){
					alert('请填写库位号!');
					return;
				}
				
				var productName = $('#productName').val();
				if(''== productName){
					alert('请填写产品名称!');
					return;
				}
				
				var stockNum = $('#stockNum').val();
				if(''== stockNum){
					alert('请填写库存数!');
					return;
				}
				
				var pars = $("#userSearchForm").serialize();
				$.ajax({
					type: "POST",
					url: "${ctx}/productStockEdit.do?method=edit",
					data: pars,
					success: function(result) {
						if(1== result){
							alert('修改成功！');	
						}else{
							alert('修改失败！');
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
				<td style="text-align:right;">缩略图：</td>
				<td class="textLeft">
					<input type="text" name="thumbnails" id="thumbnails" style="width: 100px;" value="${stock.thumbnails }"/>
				</td>
				<td style="text-align:right;">产品名称：</td>
				<td class="textLeft">
					<input type="text" name="name" id="name" style="width: 100px;" value="${stock.name }"/>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;">库位号：</td>
				<td class="textLeft">
					<input type="text" name="stockCode" id="stockCode" style="width: 100px;" value="${stock.stockCode }"/>
				</td>
				<td style="text-align:right;">重量(g)：</td>
				<td class="textLeft"><input type="text" name="weight" id="weight" style="width: 100px;" value="${stock.weight }"/></td>
			</tr>
			<tr>
				<td style="text-align:right;">中文品名：</td>
				<td class="textLeft">
					<input type="text" name="typeZh" id="typeZh" style="width: 100px;" value="${stock.typeZh }"/>
				</td>
				<td style="text-align:right;">英文品名：</td>
				<td class="textLeft"><input type="text" name="typeEn" id="typeEn" style="width: 100px;" value="${stock.typeEn }"/></td>
			</tr>
			<tr>
				<td style="text-align:right;">库存数：</td>
				<td class="textLeft">
					<input type="text" name="stockNum" id="stockNum" style="width: 100px;" value="${stock.stockNum }"/>
				</td>
				<td style="text-align:right;">安全库存数：</td>
				<td class="textLeft">
					<input type="text" name="alertStockNum" id="alertStockNum" style="width: 100px;" value="${stock.alertStockNum }"/>
				</td>
			</tr>
			<tr>
				<td style="text-align:center; height: 35px;" colspan="4">
					<a href="${ctx}/productStockEdit.do" class="dialog_link ui-state-default ui-corner-all">
						<span class="ui-icon ui-icon-search"></span>&nbsp;返&nbsp;&nbsp;回&nbsp;
					</a>
					&nbsp;&nbsp;
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="edit()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;保&nbsp;&nbsp;存&nbsp;
					</a>
				</td>
			</tr>
		</table>
		<input type="hidden" name="id" value="${stock.id }"/>
		</form>
		
</body>

