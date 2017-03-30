<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>产品管理</title>
		<script type="text/javascript">
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			
			function query(pageNo,totalCount,pageSize) {
				$('#infoLoadingDiv').dialog('open');
				var pars = $("#stockSearchForm").serialize() + "&pageNo=" + pageNo + "&totalCount=" + totalCount+"&pageSize="+pageSize;
				$.ajax({
					type: "POST",
					url: "${ctx}/productStockEdit.do?method=search",
					data: pars,
					success: function(result) {
						$('#infoLoadingDiv').dialog('close');
						$('#resultListDiv').html(result);
					}
				});
			}
			
			function exportStock(){
				var pars = $("#stockSearchForm").serialize();
				var url = "${ctx}/productStockQuery.do?method=export&"+pars;
				location.href = url;
			}
			
			function del(id){
				if(confirm("确认删除吗？")){
					$('#infoLoadingDiv').dialog('open');
					var pars = "id=" + id;
					$.ajax({
						type: "POST",
						url: "${ctx}/productStockEdit.do?method=del",
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
						<font class="titletext">产品信息查询</font>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
		</table>
		
		<form id="stockSearchForm">
		<table class="searchTermTable">
			<tr>
				<td class="textRight">SKU：</td>
				<td class="textLeft"><input type="text" name="sku" style="width: 100px;" /></td>
				<td class="textRight">产品类别：</td>
				<td class="textLeft">
					<select name="typeEn">
						<option value="">请选择</option>
					</select>
				</td>
				<td class="textRight">重量：</td>
				<td class="textLeft">
					<input type="text" name="weightStart" style="width: 50px;" />至
					<input type="text" name="weightEnd" style="width: 50px;" />
				</td>
				
				<td class="textLeft">
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="query()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;查&nbsp;&nbsp;询&nbsp;
					</a>
					&nbsp;&nbsp;
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="exportStock()">
						<span class="ui-icon ui-icon-search"></span>导出产品库存
					</a>
				</td>
			</tr>
			<tr>
				<td class="textRight">排版尽寸：</td>
				<td class="textLeft" colspan="6">
					<select name="size">
						<option value="">请选择</option>
					</select>
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
		
