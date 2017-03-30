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
			<th class="item15">货币代码</th>
			<th class="item15">货币名称</th>
			<th class="item10">交易单位</th>
			<th class="item10">汇率(100人民币兑换外币)</th>
			<th class="item10">汇率(100外币兑换人民币)</th>
			<th class="item10">目标币种</th>
			<th class="item10">目标币种名称</th>
			<th class="item10">状态</th>
			<th class="item10">生效时间</th>
			<th class="item10">失效时间</th>
			<th class="item10">创建时间</th>
			<th class="item30">操作人</th>
			<th class="item30">操作</th>
		</tr>
		</thead> 
		<tbody>
		<c:forEach items="${rateList}" var="rate" varStatus="index">
		<tr class="even" align="center">
			<td><span></span>${rate.currency}<span></span></td>
			<td>
				<c:forEach var="currency" items="${currencys}" varStatus="v">
					<c:if test="${currency.code == rate.currency}">${currency.desc}</c:if>
				</c:forEach>
			</td>
			<td>
				${rate.currencyUnit }				
			</td>
			<td>
				${rate.exchangeRate }				
			</td>
			<td>
				${rate.reverseExchangeRate }				
			</td>
			<td><span></span>${rate.targetCurrency}<span></span></td>
			<td>
				<c:forEach var="currency" items="${currencys}" varStatus="v">
					<c:if test="${currency.code == rate.targetCurrency}">${currency.desc}</c:if>
				</c:forEach>
			</td>
			<td nowrap="nowrap">
			   <c:choose>
			         <c:when test="${rate.status=='1'}">
			                                             生效
			         </c:when>
			         <c:when test="${rate.status=='0'}">
			                                             失效
			         </c:when>
			   </c:choose>
			</td>
			<td><date:date value="${rate.effectDate}"/></td>
			<td><date:date value="${rate.expireDate}"/></td>
			<td><date:date value="${rate.createDate}"/></td>
			<td align="left">${rate.operator}</td>
			<td nowrap="nowrap"><a href="${ctx}/exchangeRate.do?method=updateInit&id=${rate.id}">修改</a></td>
		</tr>
		</c:forEach>
		</tbody> 
	</table>
</form>
</body>

