<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>产品信息复核</title>
		<script type="text/javascript">
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			
			function query(pageNo,totalCount,pageSize) {
				$('#infoLoadingDiv').dialog('open');
				var pars = $("#stockSearchForm").serialize() + "&pageNo=" + pageNo + "&totalCount=" + totalCount+"&pageSize="+pageSize;
				$.ajax({
					type: "POST",
					url: "${ctx}/productStockAudit.do?method=search",
					data: pars,
					success: function(result) {
						$('#infoLoadingDiv').dialog('close');
						$('#resultListDiv').html(result);
					}
				});
			}
			
			//取消一个选择单位，去掉全选框的勾
			function selectAllcheckBoxunchecked(obj){
			  if(!obj.checked){
				  $("#checkAll").attr("checked",false);
				}
			}
			
			function audit(flag) {
				
				if(0 == $("input[name=id]:checked").size()){
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
					url: "${ctx}/productStockAudit.do?method=audit",
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
						<font class="titletext">产品信息复核</font>
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
				</td>
			</tr>
			<tr>
				<td class="textRight">排版尽寸：</td>
				<td class="textLeft">
					<select name="size">
						<option value="">请选择</option>
					</select>
				</td>
				<td class="textRight">状态：</td>
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
		</table>
		</form>
		<div align="right">
			<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="audit(1)">
				<span class="ui-icon ui-icon-search"></span>审核通过
			</a>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="audit(2)">
				<span class="ui-icon ui-icon-search"></span>审核拒绝
			</a>
		</div>
		<div id="resultListDiv" class="listFence"></div>
		<div id="allOverlayDiv" class="ui-widget-overlay" style="display: none;"></div>
		<div id="infoLoadingDiv" title="加载信息" style="display: none; width: 200px; padding-top: 30px; height: 70px;">
			<img src="${ctx}/images/page/blue-loading.gif" />&nbsp;&nbsp;信息加载中, 请稍候...
		</div>
	</body>
</html>
		
