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
	<table id="userTable" class="tablesorter" border="0" cellpadding="0" cellspacing="1">
		<thead> 
			<tr>     
				
				<th>缩略图</th>  
				<th>产品名称</th>        
				<th>库位号</th>
				<th>重量(g)</th>
				<th>进价</th>
				<th>长(cm)</th>
				<th>宽(cm)</th>
				<th>高(cm)</th>
				<th>英文类别</th>
				<th>中文类别</th>
				<th>库存数</th>
				<th>安全库存</th>
				<th>状态</th>
			</tr> 
		</thead> 
		<tbody>
			<c:forEach items="${resultList}" var="ls"> 
			<tr>     
				 
				<td>
					<img src="/pic/${ls.thumbnails}" style="width:80px;height:80px;" />
				</td>   
				<td>${ls.name}</td>      
				<td>${ls.stockCode}</td> 
				<td>${ls.weight}</td>
				<td>${ls.prices}</td>
				<td>${ls.l}</td>
				<td>${ls.w}</td>
				<td>${ls.h}</td>
				<td>${ls.typeEn}</td>
				<td>${ls.typeZh}</td>
				<td>${ls.stockNum}</td>
				<td>${ls.alertStockNum}</td>
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
	<li:pagination methodName="query" pageBean="${page}" sytleName="black2"/>
</body>

