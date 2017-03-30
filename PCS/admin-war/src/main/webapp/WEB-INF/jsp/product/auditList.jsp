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
				<th>SKU</th>  
				<th nowrap="nowrap">销售平台</th>        
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">库位编号</th>
				<!-- <th>中文品名1</th>
				<th>英文品名</th> -->
				<th>UK</th>
				<th>DE</th>
				<th>FR</th>
				<th>ES</th>
				<th>IT</th>
				<th>US</th>
				<th>AU</th>
				<th>CA</th>
				<th>AT</th>
				<th>CH</th>
				<th>BE</th>
				<th>IE</th>
				<th nowrap="nowrap">创建时间</th>
				<th nowrap="nowrap">提成方式</th>
				<th nowrap="nowrap">提成比例</th>
				<th nowrap="nowrap">仓号</th>
				<th nowrap="nowrap">状态</th>
			</tr> 
		</thead> 
		<tbody>
			<c:forEach items="${resultList}" var="ls"> 
			<tr>     
				<td>
					<c:if test="${ls.status == 0}">
						<input type="checkbox" value="${ls.skuCode}" id="skuCode" name="skuCode"/>
					</c:if>
				</td>
				<td>${ls.skuCode}</td>   
				<td>${ls.platform}</td>      
				<td>${ls.productName}</td> 
				<td>${ls.stockCode}</td>
				<td>${ls.uk}</td>
				<td>${ls.de}</td>
				<td>${ls.fr}</td>
				<td>${ls.es}</td>
				<td>${ls.it}</td>
				<td>${ls.us}</td>
				<td>${ls.au}</td>
				<td>${ls.ca}</td>
				<td>${ls.at}</td>
				<td>${ls.ch}</td>
				<td>${ls.be}</td>
				<td>${ls.ie}</td>
				<%-- <td>${ls.zhName}</td>
				<td>${ls.enName}</td> --%>
				<td>
					<fmt:formatDate value="${ls.createDate}" type="both"/>
				</td>
				<td align="center"><c:if test="${ls.percentageType ==1}">%</c:if><c:if test="${ls.percentageType ==2}">Flat</c:if></td>
				<td align="center">${ls.percentage}</td>
				<td>${ls.repository}</td>
				<td>
					<c:if test="${ls.status == 0}">
						新建
					</c:if>
					<c:if test="${ls.status == 1}">
						审核通过
					</c:if>
					<c:if test="${ls.status == 2}">
						审核拒绝
					</c:if>
					<c:if test="${ls.status == 3}">
						已下架
					</c:if>
				</td>  
			</tr>
			</c:forEach>
		</tbody> 
	</table>
</form>
	<li:pagination methodName="query" pageBean="${page}" sytleName="black2"/>
</body>

