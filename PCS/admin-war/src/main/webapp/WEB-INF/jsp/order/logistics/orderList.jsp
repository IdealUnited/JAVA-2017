<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="./js/common.js"></script>

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
		<input type="hidden" name="sequenceList" /> <input type="hidden"
			name="channelOrderItemId" /> <input type="hidden" name="status" />
	</form>
	<form id="orderForm">
		<table id="userTable" class="tablesorter" border="0" cellpadding="0"
			cellspacing="1">
			<thead>
				<tr>
					<th></th>
					<th nowrap="nowrap">销售平台</th>
					<th nowrap="nowrap">订单时间</th>
					<th nowrap="nowrap">订单号</th>
					<th nowrap="nowrap">物流订单号</th>
					<th nowrap="nowrap">产品名称</th>
					<th nowrap="nowrap">产品类别</th>
					<th nowrap="nowrap">待发货数量</th>
					<th nowrap="nowrap">订单总金额</th>
					<th nowrap="nowrap">状态</th>
					<th>收货人</th>
					<th>地址</th>
					<th>目的国家</th>
					<th nowrap="nowrap">总重量</th>
					<th nowrap="nowrap">优先物流</th>
					<th nowrap="nowrap">预计物流成本</th>
					<!-- <th>预计毛利</th>
					<th>物流方式</th> -->
					<!-- <th nowrap="nowrap">操作</th> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.result}" var="ls">
					<tr>
						<td><c:if test="${empty ls.status}">
								<input type="checkbox" value="${ls.tradeOrderItemNo}" id="orderId"
									name="orderId" />
							</c:if></td>
						<td>${ls.platform}</td>
						<td>${ls.titemCreateDate}</td>
						<td nowrap="nowrap">${ls.tradeOrderItemNo}</td>
						<td>${ls.expressSysCode}</td>
						<td>${ls.productName}</td>
						<td>${ls.zhType}</td>
						<td>${ls.numberOfItemsUnshipped}</td>
						<td>${ls.orderAmount}</td>
						<td nowrap="nowrap"><c:if test="${empty ls.status}">待处理</c:if>
							<c:if test="${ls.status == 0}">创建</c:if> <c:if
								test="${ls.status == 1}">成功</c:if> <c:if
								test="${ls.status == 2}">失败</c:if></td>
						<td>${ls.shippedName}</td>
						<td>${ls.address}</td>
						<td>${ls.country}</td>
						<td>${ls.weight}</td>
						<%-- 	<td>${ls.cost}</td>
						<td>${ls.profit}</td> --%>
						<td nowrap="nowrap"><select
							name="expressChannelId${ls.tradeOrderItemNo}"
							id="expressChannelId${ls.tradeOrderItemNo}" style="width: 100px;"
							onchange="changeExpressChannel('expressChannelId${ls.tradeOrderItemNo}','expressChannelItemId${ls.tradeOrderItemNo}',this.value)">
								<c:if test="${empty ls.channelId}">
									<option value="">请选择</option>
								</c:if>
								<c:forEach items="${expressChannelList}" var="channel">
									<option value="${channel.id}"
										<c:if test="${ls.channelId == channel.id}">selected</c:if>>${channel.name}</option>
								</c:forEach>
						</select> <select name="expressChannelItemId${ls.tradeOrderItemNo}"
							id="expressChannelItemId${ls.tradeOrderItemNo}"
							style="width: 100px;">
								<c:if test="${empty ls.expressChannelItem}">
									<option value="">请选择</option>
								</c:if>
								<c:forEach items="${expressChannelItem}" var="lsItem">
									<option value="${lsItem.id}">${lsItem.name}</option>
								</c:forEach>
						</select>
						<%-- <input type="hidden" id="${ls.orderItemId}" name="${ls.orderItemId}" value=""/> --%>
						</td>
						<td nowrap="nowrap">${ls.expressName }</td>
						<%-- <td nowrap="nowrap"><c:if
								test="${empty ls.status  or ls.status==2}">
								<a
									href="javascript:send(${ls.orderItemId},${ls.channelOrderItemId},${ls.status})">发货</a>
							</c:if></td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	<li:pagination methodName="query" pageBean="${page}" sytleName="black2" />


	<div align="center">
		&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:nextStep()"
			class="dialog_link ui-state-default ui-corner-all"></span>发 货 </a>
	</div>

	<script type="text/javascript">
		function send(orderItemId,channelOrderItemId,status) {
			var sequenceId = orderItemId;
			$("input[name='sequenceList']").val(orderItemId);
			$("input[name='channelOrderItemId']").val(channelOrderItemId);
			$("input[name='status']").val(status);
			var expressChannelId=$("#expressChannelId"+orderItemId).find("option:selected").val();
			var expressChannelItemId=$("#expressChannelItemId"+orderItemId).find("option:selected").val();
			/* document.nextSetpFrom.action = 'createExpressOrder.do?method=nextStep';
			document.nextSetpFrom.submit();
 */
			var pars = $("#nextSetpFrom").serialize();
			$.ajax({
				type: "POST",
				url: "${ctx}/createExpressOrder.do?method=createExpressOrder",
				data: pars,
				success: function(result) {
					if(1==result){
						alert('添加成功！');
					}else{
						if (result == 'true') {
							alert("发货处理成功");
							query();
						}else{
							alert("发货处理失败:"+result.substring(6,result.length));
						} 
					}
				}
			});
		}
		function nextStep() {
			var num = 0;
			var sequenceId = "";
			$("input[name='orderId']").each(function() {
				if (this.checked == true) {
					var expressChannelId=$("#expressChannelId"+this.value).find("option:selected").val();
					var expressChannelItemId=$("#expressChannelItemId"+this.value).find("option:selected").val();
					num++;
					/* alert(expressChannelId);
					alert(expressChannelItemId); */
					if(expressChannelId==""||expressChannelId==undefined||expressChannelItemId==""||expressChannelItemId==undefined){
						alert("选中的订单必须选择优先物流！");
						return false;
					}
					sequenceId += this.value + "|"+expressChannelId+"|"+expressChannelItemId+",";
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
			//alert(sequenceId);
			$("input[name='sequenceList']").val(sequenceId);
			/* document.nextSetpFrom.action = 'createExpressOrder.do?method=createExpressOrder';
			document.nextSetpFrom.submit(); 
			 */
			var pars=$("input[name='sequenceList']").val();
			/*  alert(test);
			 alert($("#nextSetpFrom"));
			var pars = $("#nextSetpFrom").FormSerialize();
			alert(pars); */
			$.ajax({
				type: "POST",
				url: "${ctx}/createExpressOrder.do?method=createExpressOrder&sequenceList="+pars,
				data: pars,
				success: function(result) {
					if(1==result){
						alert('添加成功！');
					}else{
						if (result == 'true') {
							alert("发货处理成功");
							query();
						}else{
							alert("发货处理失败:"+result.substring(6,result.length));
						} 
					}
				}
			});
		}
		
	        function test(o) {  
	            if (!o.checked) {  
	                return;  
	            }  
	            var tr = o.parentNode.parentNode;  
	            var tds = tr.cells;  
	            var str = "you click ";  
	            for(var i = 0;i < tds.length;i++){  
	                if (i < 3) {  
	                    strstr = str + tds[i].innerHTML + "--";  
	                }  
	            }  
	            alert(str);  
	        }  
		
		function changeExpressChannel(father,son,val){
			var expressChannelId = $("#expressChannelId").find("option:selected").val();
			var expressChannelId = val;
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
	</script>
</body>








