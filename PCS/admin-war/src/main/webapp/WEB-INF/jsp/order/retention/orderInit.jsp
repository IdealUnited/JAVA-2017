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
			
		function process(flag) {
				
				if(0 == $("input[name=skuCode]:checked").size()){
					$.fo.alert('请您选择记录后再进行提交！');	
					//btnDisabledSetFalse();
					return false;
				}
				
				if(!confirm("确认审核吗？")){
					return false;
				}
				
				$('#infoLoadingDiv').dialog('open');
				var pars = $("#orderForm").serialize() + "&auditFlag=" + flag;
				$.ajax({
					type: "POST",
					url: "${ctx}/productSku.do?method=audit",
					data: pars,
					success: function(result) {
						$('#infoLoadingDiv').dialog('close');
						if(0==result){
							alert('审核失败！');
						}else{
							$('#resultListDiv').html(result);
						}
						
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
						<font class="titletext">滞留订单</font>
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
				<td style="text-align:right;">订单号：</td>
				<td class="textLeft">
					<input type="text" name="orderId" id="orderId" style="width: 100px;" />
				</td>
				<td style="text-align:right;">产品类别：</td>
				<td class="textLeft">
					<input type="text" name="skuCode" id="skuCode" style="width: 100px;" />
				</td>
				<td style="text-align:right;">目的国家：</td>
				<td class="textLeft">
					<input type="text" name="countryCode" id="countryCode" style="width: 100px;" />
				</td>
			</tr>
			<tr>
				<td style="text-align:right;">销售平台：</td>
				<td class="textLeft">
					<select name="platform" id="platform" style="width: 100px;">
						<option value="">请选择</option>
						<option value="amazon">亚玛逊</option>
					</select>
				</td>
				<td style="text-align:right;">订单总金额：</td>
				<td class="textLeft">
					<select name="orderAmountOperator" id="orderAmountOperator" style="width: 100px;">
						<option value="">请选择</option>
						<option value=">=">大于等于</option>
						<option value="<=">小于等于</option>
					</select>
					<input type="text" name="orderAmount" id="orderAmount" style="width: 100px;"/>
				</td>
				<td style="text-align:right;">总重量：</td>
				<td class="textLeft">
					<select name="weightOperator" id="weightOperator" style="width: 100px;">
						<option value="">请选择</option>
						<option value=">=">大于等于</option>
						<option value="<=">小于等于</option>
					</select>
					<input type="text" name="weight" id="weight" style="width: 100px;"/>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;">滞留原因：</td>
				<td class="textLeft">
					<select name="retentionReason" id="retentionReason" style="width: 100px;">
						<option value="">请选择</option>
					</select>
				</td>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td style="text-align:center; height: 35px;" colspan="6">
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="query()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;查&nbsp;&nbsp;询&nbsp;
					</a>
				</td>
			</tr>
		</table>
		</form>
		<div align="right">
			<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="process(1)">
				<span class="ui-icon ui-icon-search"></span>拆分订单
			</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="process(2)">
				<span class="ui-icon ui-icon-search"></span>合并订单
			</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="process(3)">
				<span class="ui-icon ui-icon-search"></span>批量处理
			</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" class="dialog_link ui-state-default ui-corner-all">
				<span class="ui-icon ui-icon-search"></span>费用试算
			</a>
		</div>
		<div id="resultListDiv" class="listFence"></div>
		<div id="allOverlayDiv" class="ui-widget-overlay" style="display: none;"></div>
		<div id="infoLoadingDiv" title="加载信息" style="display: none; width: 200px; padding-top: 30px; height: 70px;">
			<img src="${ctx}/images/page/blue-loading.gif" />&nbsp;&nbsp;信息加载中, 请稍候...
		</div>
	</body>
</html>
		
