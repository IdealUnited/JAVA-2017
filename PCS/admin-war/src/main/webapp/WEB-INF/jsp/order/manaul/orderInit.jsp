<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="./js/common.js"></script>
<html>
<head>
<title>订单管理</title>
<script type="text/javascript">
			
			function query(pageNo,totalCount,pageSize) {
				$('#infoLoadingDiv').dialog('open');
				var pars = $("#userSearchForm").serialize() + "&pageNo=" + pageNo + "&totalCount=" + totalCount+"&pageSize="+pageSize;
				$.ajax({
					type: "POST",
					url: "${ctx}/productStock.do?method=search",
					data: pars,
					success: function(result) {
						$('#infoLoadingDiv').dialog('close');
						$('#resultListDiv').html(result);
					}
				});
			}
			
			function add(){
				
				var orderId = $('#orderId').val();
				var platform = $('#platform').val();
				var orderAmount = $('#orderAmount').val();
				var earliestshipdate = $('#earliestshipdate').val();
				var name = $('#name').val();
				var phone = $('#phone').val();
				var country = $('#country').val();
				var postalCode = $('#postalCode').val();
				var stateOrRegion = $('#stateOrRegion').val();
				var city = $('#city').val();
				var addressLine1 = $('#addressLine1').val();
				var zhType = $('#zhType').val();
				var enType = $('#enType').val();
				var numberOfItemsUnshipped = $('#numberOfItemsUnshipped').val();
				var currencyCode = $('#currencyCode').val();
				var declargPrices = $('#declargPrices').val();
				var productName = $('#productName').val();
				
				if('' == orderId){
					alert('请输入订单号！');
					return;
				}
				if('' == platform){//如果为空，默认手工单
					//alert('请输入平台！');
					$('#platform').val("手工单");
					//return;
				}
				if('' == orderAmount){//手工单订单金额默认为0；
					var orderAmount = $('#orderAmount').val(0);
					/* alert('请输入订单金额！');
					return; */
				}
				
				if('' == earliestshipdate){
					alert('请输入发货日期！');
					return;
				}
				if('' == name){
					alert('请输入收货人姓名！');
					return;
				}
				if('' == phone){
					alert('请输入收货人电话！');
					return;
				}
				if('' == country){
					alert('请选择国家！');
					return;
				}
				
				var pars = $("#userSearchForm").serialize();
				$.ajax({
					type: "POST",
					url: "${ctx}/createOrderByManual.do?method=add",
					data: pars,
					success: function(result) {
						if(1==result){
							alert('添加成功！');
						}else{
							if (result == 'true') {
								alert("手工单处理成功");
								window.location.href='createExpressOrder.do';
							}else{
								alert("手工单处理失败:"+result.substring(6,result.length));
							} 
						}
					}
				});
			}
			function getCountrys(){
					var searchKey = $('#searchKey').val();
					var url = "${ctx}/createOrderByManual.do";
					var data = {"method":"getCountrys","searchKey":searchKey};
					jQuery.post(url+"?date="+new Date(),data,function(response){
						//if(""!=response){
							var selOpt = $("#country option");
							selOpt.remove();
							$("#country").append(response);
						//}
					});
			} 
			
			$(function(){
				$("#zhType1").focus(function(){
					var zhType = $('#zhType').val();
					var pars = "zhType=" + zhType;
					$.ajax({
						type: "POST",
						url: "${ctx}/createOrderByManual.do?method=isExistsProductType",
						data: pars,
						success: function(result) {
							if(0==result){
								$('#zhTypeErr').html('<font color=red>中文品名不存在！</font>');
							}else{
								$('#zhTypeErr').html('');
							}
						}
					});
				 });
				
				
				$("#productName").focus(function(){
					var productName = $('#productName').val();
					if('' == productName){
						return;
					}
					
					var pars = "productName=" + productName;
					$.ajax({
						type: "POST",
						url: "${ctx}/productStockQuery.do?method=queryStockCode",
						data: pars,
						success: function(result) {
							$('#remark').val(result);
						}
					});
				 });
				
				
			});
					
			function changeExpressChannel(father,son){
				var expressChannelId = $("#expressChannelId").find("option:selected").val();
				//alert(expressChannelId);
				if(expressChannelId==null||expressChannelId==""){
					return;
				}
				var relationArray = new Array();
				<c:forEach items = "${relationList}" var = "relation" varStatus = "relationStatus">
					relationArray[${relationStatus.index}] = new dropDownListMode('${relation.fatherCode}','${relation.code}','${relation.name}');	
				</c:forEach>
				
				this.changeFatherSelect(father,son,relationArray,null);
			} 
			$(document).ready(function(){changeExpressChannel('expressChannelId','expressChannelItemId');});
		</script>
</head>

<body>
	<table width="25%" border="0" cellspacing="0" cellpadding="0"
		align="center" height="21">
		<tr>
			<td height="1" bgcolor="#000000"></td>
		</tr>
		<tr>
			<td height="18">
				<div align="center">
					<font class="titletext">创建手工单</font>
				</div>
			</td>
		</tr>
		<tr>
			<td height="1" bgcolor="#000000"></td>
		</tr>
	</table>
	<br />
	<br />

	<br />
	<br />
	<form id="userSearchForm">
		<table class="inputTable" width="900" border="0" cellspacing="0"
			cellpadding="3" align="center">
			<tr>
				<td colspan="4"><strong>运单信息</strong></td>
			</tr>
			<tr>
				<td style="align: right;">物流渠道：</td>
				<td class="textLeft"><select name="expressChannelId"
					id="expressChannelId" style="width: 200px;"
					onchange="changeExpressChannel('expressChannelId','expressChannelItemId')">
						<c:forEach items="${expressChannelList}" var="ls">
							<option value="${ls.id}">${ls.name}</option>
						</c:forEach>
				</select></td>
				<td class="textRight">物流通道：</td>
				<td class="textLeft"><select name="expressChannelItemId"
					id="expressChannelItemId" style="width: 200px;">
						<c:forEach items="${expressChannelItem}" var="lsItem">
							<option value="${lsItem.id}">${lsItem.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="textRight">电商平台：</td>
				<td class="textLeft"><input type="text" name="platform"
					id="platform" style="width: 200px;" required="required" /></td>
				<td class="textRight">订单号：</td>
				<td class="textLeft"><input type="text" name="orderId"
					id="orderId" style="width: 200px;" required="required" /></td>
			</tr>
			<tr>
				<td class="textRight">发货日期：</td>
				<td class="textLeft" colspan="3"><input class="Wdate"
					type="text" id="earliestshipdate" value="${earliestshipdate}"
					name="earliestshipdate"
					onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 200px;">
				</td>
				<!-- 		<td class="textRight">订单金额：</td>
				<td class="textLeft">-->
					<input type="hidden" name="orderAmount" id="orderAmount" style="width: 200px;" />
				<!--</td> -->
			</tr>
			<tr>
				<td colspan="4"><strong>收货人信息</strong></td>
			</tr>
			<tr>
				<td class="textRight">姓名：</td>
				<td class="textLeft"><input type="text" name="name" id="name"
					style="width: 200px;" /></td>
				<td class="textRight">电话：</td>
				<td class="textLeft"><input type="text" name="phone" id="phone"
					style="width: 200px;" /></td>
			</tr>
			<tr>
				<td class="textRight">国家：</td>
				<td class="textLeft"><select id="country" name="country"
					style="width: 130px;">
						<option value="">请选择</option>
						<c:forEach items="${countrys }" var="c">
							<option value="${c.code }">${c.zhName }</option>
						</c:forEach>
				</select> <input type="text" id="searchKey" value="" placeholder="关键字检索"
					style="width: 70px;" onblur="javacript:getCountrys();"/></td>
				<td class="textRight">邮编：</td>
				<td class="textLeft"><input type="text" name="postalCode"
					id="postalCode" style="width: 200px;" /></td>
			</tr>
			<tr>
				<td class="textRight">州/编码：</td>
				<td class="textLeft"><input type="text" name="stateOrRegion"
					id="stateOrRegion" style="width: 200px;" /></td>
				<td class="textRight">城市：</td>
				<td class="textLeft"><input type="text" name="city" id="city"
					style="width: 200px;" /></td>
			</tr>
			<tr>
				<td class="textRight">地址：</td>
				<td class="textLeft" colspan="3"><input type="text"
					name="addressLine1" id="addressLine1" style="width: 600px;" /></td>
			</tr>
			<tr>
				<td colspan="4"><strong>报关信息</strong></td>
			</tr>
			<tr>
				<td class="textRight">中文品名：</td>
				<td class="textLeft" colspan="3">
					<select id="enType" name="enType" style="width: 160px;">
						<option value="">请选择</option>
						<c:forEach items="${productTypes }" var="c">
							<option value="${c.enType }">${c.zhType }(${c.enType })</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="textRight">货品数量：</td>
				<td class="textLeft"><input type="text"
					name="numberOfItemsUnshipped" id="numberOfItemsUnshipped"
					style="width: 200px;" /></td>
				<td class="textRight">总重量：</td>
				<td class="textLeft"><input type="text" name="weight"
					id="weight" style="width: 200px;" /></td>
			</tr>
			<tr>
				<td class="textRight">货币种类：</td>
				<td class="textLeft"><select id="currencyCode"
					name="currencyCode" style="width: 200px;">
						<c:forEach items="${currencys }" var="currency">
							<option value="${currency.code }" <c:if test="${currency.code == 'USD'}">selected</c:if>>${currency.desc }</option>
						</c:forEach>
				</select></td>
				<td class="textRight">申请价值：</td>
				<td class="textLeft"><input type="text" name="declargPrices"
					id="declargPrices" style="width: 200px;" /></td>
			</tr>
			<tr>
				<td class="textRight" nowrap="nowrap">具体品名：</td>
				<td class="textLeft" colspan="3"><input type="text"
					name="productName" id="productName" style="width: 600px;" /></td>
			</tr>
			<tr>
				<td class="textRight">备注：</td>
				<td class="textLeft" colspan="3"><textarea rows="3" cols="115"
						name="remark" id="remark"></textarea></td>
			</tr>
			<tr>
				<td colspan="4"><a class="s03" href="javascript:add();"><img
						src="./images/add.jpg" border="0"></a></td>
			</tr>
		</table>
	</form>
</body>
</html>

