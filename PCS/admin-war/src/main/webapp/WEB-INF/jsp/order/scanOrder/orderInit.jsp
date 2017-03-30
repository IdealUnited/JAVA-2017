<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>订单管理</title>
		<script type="text/javascript">
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			
			$(function(){
				
				$("#expressSysCode").focus();
				
		        $('#expressSysCode').bind('keypress',function(event){
		            if(event.keyCode == "13")    
		            {
		               // alert('你输入的内容为：' + $('#expressSysCode').val());
		                comfirmExpress();
		                $("#expressSysCode").val('');
		                $("#expressSysCode").focus();
		            }
		        });
		    });
			function comfirmExpress() {
				
				$('#tips').html("");
				$('#resultListDiv').html("");
				
				if($("#expressSysCode").val()==""){
					//alert("物流订单号不能为空！");
					 $('#tips').html("<font color='red'>物流订单号不能为空！</font>");
					return;
				}
				var pars = "expressSysCode="+$("#expressSysCode").val();
				$.ajax({
					type: "POST",
					url: "${ctx}/scanExpressOrder.do?method=comfirmExpress",
					data: pars,
					success: function(result) {
						
						if (result == 'true') {
							query();
						} else if (result == '8000') {
							 $('#tips').html("<font color='red'>库存不足，请联系库存管理员</font>");
							//alert("库存不足，请联系库存管理员");
						}  else if (result == '404') {
							 $('#tips').html("<font color='red'>查无此订单！</font>");
							//alert("查无此订单！");
						}
						else {
							$('#tips').html("<font color='red'>确认出货失败，请联系库存管理员！</font>");
							//alert("确认出货失败，请联系库存管理员");
						}
					}
				});
			}
			
			function query(pageNo,totalCount,pageSize) {
				if($("#expressSysCode").val()==""){
					alert("物流订单号不能为空！");
					return;
				}
				
				$('#infoLoadingDiv').dialog('open');
				var pars = "expressSysCode="+$("#expressSysCode").val();
				$.ajax({
					type: "POST",
					url: "${ctx}/scanExpressOrder.do?method=search",
					data: pars,
					success: function(result) {
						$('#infoLoadingDiv').dialog('close');
						$('#resultListDiv').html(result);
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
						<font class="titletext">订单出库扫描</font>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
		</table>
		
		<form id="userSearchForm" onsubmit="return false;">
		<table border="0" style="width: 900px; " class="inputTable" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td style="text-align:right;" colspan="3">物流公司运单号：</td>
				<td class="textLeft" colspan="3">
					<input type="text" name="expressSysCode" id="expressSysCode" style="width: 200px;" />
				</td>
			</tr>
			<tr>
				<td style="text-align:center; height: 35px;" colspan="6">
	<!-- 				<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="query()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;确认发货&nbsp;
					</a> -->
					<input type="button" id="comfirmExpressInput" name="comfirmExpressInput" onclick="comfirmExpress()" value="确认发货"/>
				</td>
			</tr>
		</table>
		</form>
		<!--<div align="right">
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
		</div>-->
		<div id="resultListDiv" class="listFence"></div>
		<div id="allOverlayDiv" class="ui-widget-overlay" style="display: none;"></div>
		<div id="infoLoadingDiv" title="加载信息" style="display: none; width: 200px; padding-top: 30px; height: 70px;">
			<img src="${ctx}/images/page/blue-loading.gif" />&nbsp;&nbsp;信息加载中, 请稍候...
		</div>
		<div id ="tips">
			
		</div>
	</body>
</html>		
