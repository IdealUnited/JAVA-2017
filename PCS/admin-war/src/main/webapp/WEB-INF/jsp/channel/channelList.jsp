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
	<table id="userTable" class="tablesorter" border="0" cellpadding="0" cellspacing="1" >
		<thead> 
			<tr>     
				
				<th  width="8%">渠道编号</th>  
				<th>渠道名称</th>        
				<th>渠道编号</th>        
				<th width="8%">渠道状态</th>
				<th width="15%">操作</th>
			</tr> 
		</thead> 
		<tbody>
			<c:forEach items="${resultList}" var="ls"> 
			<tr>     
				 
				<td>${ls.id}</td>   
				<td>${ls.name}</td>
				<td>${ls.code}</td>
				<td>
					<c:if test="${ls.status==0 }">关闭</c:if>
					<c:if test="${ls.status==1 }">正常</c:if>
				</td>
				<td>
					<a href="${ctx}/channel.do?method=editInit">编辑</a>&nbsp;&nbsp;
					<a href="${ctx}/channelItem.do?channelId=${ls.id}">查看明细</a>
				</td>
			</tr>
			</c:forEach>
		</tbody> 
	</table>
</body>

