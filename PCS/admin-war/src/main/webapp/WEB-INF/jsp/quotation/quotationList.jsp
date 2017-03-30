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
	<table id="userTable" class="tablesorter" border="0" cellpadding="0" cellspacing="1" style="width: 1100px;">
		<thead> 
			<tr>     
				<th width="10%">序号</th>  
				<th width="10%" nowrap="nowrap">渠道</th>  
				<th width="10%" nowrap="nowrap">通道</th>        
				<th width="10%" nowrap="nowrap">国家名称</th>
				<th width="10%" nowrap="nowrap">国家二位缩写</th>
				<th width="10%">类型</th>
				<th width="10%">克重(g)</th>
				<th width="10%">报价(元)</th>
				<th width="10%" nowrap="nowrap">处理费(元)</th>
				<th width="10%" nowrap="nowrap">状态</th>
				<th width="10%" nowrap="nowrap">电费</th>
				<th width="15%" nowrap="nowrap">周长(cm)</th>
				<th width="25%" nowrap="nowrap">单边长(cm)</th>
				<th width="25%" nowrap="nowrap">备注</th>
				<th width="25%" nowrap="nowrap">时效</th>
				<th width="25%" nowrap="nowrap">续重计价单位</th>
				<th width="25%" nowrap="nowrap">续重单价</th>
				<th width="25%" nowrap="nowrap">起重</th>
				<th width="25%" nowrap="nowrap">限重</th>
				<th width="25%" nowrap="nowrap">操作</th>
			</tr> 
		</thead> 
		<tbody>
			<c:forEach items="${page.result}" var="ls"> 
			<tr>     
				<td>${ls.id }</td>
				<td nowrap="nowrap">
					${ls.channelName }
				</td>   
				<td nowrap="nowrap">${ls.channelItemName}</td>      
				<td nowrap="nowrap">${ls.countryName}</td> 
				<td>${ls.countryCode}</td> 
				<td>
					<c:if test="${ls.type==1 }">挂号</c:if>
					<c:if test="${ls.type==2 }">平邮</c:if>
					<c:if test="${ls.type==3 }">专线</c:if>
				</td>
				<td>${ls.calculateUnit}</td>
				<td>${ls.price}</td>
				<td>${ls.fee}</td>
				<td nowrap="nowrap">
					<c:if test="${ls.status==1 }">有效</c:if>
					<c:if test="${ls.status==0 }">无效</c:if>
				</td>
				<td>
					<c:if test="${ls.flag==1 }">有</c:if>
					<c:if test="${ls.flag==0 }">无</c:if>
				</td>
				<td nowrap="nowrap">${ls.circumference}</td>
				<td nowrap="nowrap">${ls.length}</td>
				<td nowrap="nowrap">${ls.remark}</td>
				<td nowrap="nowrap">${ls.timeliness}</td>
				<td nowrap="nowrap">${ls.continuedUnit}</td>
				<td nowrap="nowrap">${ls.continuedPrice}</td>
				<td nowrap="nowrap">${ls.minWeight}</td>
				<td nowrap="nowrap">${ls.maxWeight}</td>
				<td nowrap="nowrap"><a href="#">修改</a></td>
			</tr>
			</c:forEach>
		</tbody> 
	</table>
	<li:pagination methodName="query" pageBean="${page}" sytleName="black2"/>
</body>

