<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<script type="text/javascript">
		$(document).ready(function(){
			 $("#userTable").tablesorter({
				 headers: {
				 	6: {sorter: false},
				 	7: {sorter: false}
				 }}); 

			 $(".tablesorter tbody tr").mouseover(function(){$(this).find("td").css({"background":"#cec"});})
			 .mouseout(function(){$(this).find("td").css({"background":"#fff"});} ) ;         
		});
		
	</script>
</head>

<body>
<form id="orderForm">
	<table id="userTable" class="tablesorter" border="0" cellpadding="0" cellspacing="1">
		<thead> 
			<tr>     
				<th></th>
				<th>销售平台</th>  
				<th>订单号</th>        
				<th>创建时间</th>        
				<th>产品名称</th>
				<th>产品类别</th>
				<th>待发货数量</th>
				<th>订单总金额</th>
				<th>地址</th>
				<th>目的国家</th>
				<th>总重量</th>
				<th>预计物流成本</th>
				<th>预计毛利</th>
				<th>物流方式</th>
			</tr> 
		</thead> 
		<tbody>
			<c:forEach items="${page.result}" var="ls"> 
			<tr>     
				<td>
					<c:if test="${ls.status != 2}">
						<input type="checkbox" value="${ls.tradeOrderItemNo}" id="tradeOrderItemNo" name="tradeOrderItemNo"/>
					</c:if>
				</td>
				<td>${ls.platform}</td>   
				<td>${ls.orderId}</td>      
				<td>${ls.channelCreateDate}</td>      
				<td>${ls.productName}</td> 
				<td>${ls.zhType}</td>
				<td>${ls.numberOfItemsUnshipped}</td>
				<td>${ls.orderAmount}</td>
				<td>${ls.address}</td>
				<td>${ls.country}</td>
				<td>${ls.weight}</td>
				<td>${ls.cost}</td>
				<td>${ls.profit}</td>
				<td>
					${ls.logistics }
				</td>  
			</tr>
			</c:forEach>
		</tbody> 
	</table>
</form>
	<li:pagination methodName="query" pageBean="${page}" sytleName="black2"/>
</body>

