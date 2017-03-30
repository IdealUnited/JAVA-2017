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
	<form action="" method="post" name="nextSetpFrom">
		<input type="hidden" name="sequenceList" />
	</form>
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
					<th>物流下单时间</th>
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
						<td>
							<c:if test="${empty ls.status}">待处理</c:if>
							<c:if test="${ls.status == 0}">待发货订单</c:if>
							<c:if test="${ls.status == 1}">成功</c:if>
							<c:if test="${ls.status == 2}">失败</c:if>
						</td>
						<td>${ls.shippedName}</td>
						<td>${ls.address}</td>
						<td>${ls.country}</td>
						<td>${ls.weight}</td>
					<%-- 	<td>${ls.cost}</td>
						<td>${ls.profit}</td> --%>
						<td>${ls.expressName }</td>
						<td>${ls.channelCreateDate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<li:pagination methodName="query" pageBean="${page}" sytleName="black2" />
	<script type="text/javascript">
		function send(id) {
			alert("发货" + id);
		}
		function nextStep() {
			var num = 0;
			var sequenceId = "";
			$("input[name='orderId']").each(function() {
				if (this.checked == true) {
					num++;
					sequenceId += this.value + ",";
				}
			});
			if (num <= 0) {
				alert("请选择数据");
				return false;
			}
			if (num > 1000) {
				alert("一次处理请不要超过1000条");
				return false;
			}
			/* alert(sequenceId); */
			$("input[name='sequenceList']").val(sequenceId);
			document.nextSetpFrom.action = 'createExpressOrder.do?method=nextStep';
			document.nextSetpFrom.submit();
		}
	</script>
</body>

