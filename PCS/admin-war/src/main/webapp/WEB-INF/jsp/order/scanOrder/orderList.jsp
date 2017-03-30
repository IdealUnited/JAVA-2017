<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
<script type="text/javascript">
	$(document).ready(function() {
		$("#userTable").tablesorter({
			headers : {
				0 : {
					sorter : false
				},
				6 : {
					sorter : false
				},
				7 : {
					sorter : false
				},
				14 : {
					sorter : false
				}
			}
		});
		if ($("#dataObject").val() == '[]') {
			$("#comfirmDIV").attr("hidden", "hidden");
		}

		$(".tablesorter tbody tr").mouseover(function() {
			$(this).find("td").css({
				"background" : "#cec"
			});
		}).mouseout(function() {
			$(this).find("td").css({
				"background" : "#fff"
			});
		});
	});
</script>
</head>

<body>
	<input type="hidden" value="${page.result}" id="dataObject"
		name="dataObject" />
	<input type="hidden" value="${channelOrderItemId}"
		id="channelOrderItemId" name="channelOrderItemId" />
	<form id="orderForm">
		<table id="userTable" class="tablesorter" border="0" cellpadding="0"
			cellspacing="1">
			<thead>
				<tr>
					<th>销售平台</th>
					<th>订单号</th>
					<th>物流订单号</th>
					<th>产品名称</th>
					<th>产品类别</th>
					<th>待发货数量</th>
					<th>订单总金额</th>
					<th>状态</th>
					<th>收货人</th>
					<th>地址</th>
					<th>目的国家</th>
					<th>总重量</th>
				<!-- 	<th>预计物流成本</th>
					<th>预计毛利</th> -->
					<th>物流方式</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.result}" var="ls">
					<tr>
						<td>${ls.platform}</td>
						<td>${ls.orderItemId}</td>
						<td>${ls.channelOrderItemId}</td>
						<td>${ls.productName}</td>
						<td>${ls.zhType}</td>
						<td>${ls.numberOfItemsUnshipped}</td>
						<td>${ls.orderAmount}</td>
						<td><c:if test="${ls.status == 0}">待确认发货</c:if> <c:if
								test="${ls.status == 1}">已发货</c:if> <c:if
								test="${ls.status == 2}">发货失败</c:if></td>
						<td>${ls.shippedName}</td>
						<td>${ls.address}</td>
						<td>${ls.country}</td>
						<td>${ls.weight}</td>
					<%-- 	<td>${ls.cost}</td>
						<td>${ls.profit}</td> --%>
						<td>${ls.expressName }</td>

						<input type="hidden" value="${ls.quantityOrdered}"
							id="quantityOrdered" name="quantityOrdered" />

						<input type="hidden" value="${ls.quantityShipped}"
							id="quantityShipped" name="quantityShipped" />
						<input type="hidden" value="${ls.stockCode}" id="stockCode"
							name="stockCode" />
						<input type="hidden" value="${ls.status}" id="expStatus"
							name="expStatus" />
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- <div align="center" id="comfirmDIV">
			&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:confirm(this)"
				class="dialog_link ui-state-default ui-corner-all" id="comfirUrl"></span>确定发货 </a>
		</div> -->
	</form>


	<script type="text/javascript">
		function confirm() {
			if ($("#dataObject").val() == '[]') {
				alert("记录为空！");
				return;
			}
			if ($("#expStatus").val() !=0) {
				alert("订单已发货或已失效（失败）");
				return;
			}
			var pars = "channelorderItemId=" + $("#channelOrderItemId").val()
					+ "&quantityOrdered=" + $("#quantityOrdered").val()
					+ "&quantityShipped=" + $("#quantityShipped").val()
					+ "&stockCode=" + $("#stockCode").val();
			$.ajax({
				type : "POST",
				url : "scanExpressOrder.do?method=confirmScanOrder",
				data : pars,
				success : function(result) {
					if (result == 'true') {
						alert("已确认出货");
						query();
					} else if (result == '8000') {
						alert("库存不足，请联系库存管理员");
					} else {
						alert("确认出货失败，请联系库存管理员");
					}
				}
			})
		}
	</script>
</body>

