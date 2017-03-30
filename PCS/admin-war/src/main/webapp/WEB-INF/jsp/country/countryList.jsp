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
				<th>编号</th>
				<th>中文名称</th>  
				<th>英文名称</th>        
				<th>二位缩写</th>
			</tr> 
		</thead> 
		<tbody>
			<c:forEach items="${page.result}" var="ls"> 
			<tr>     
				<td>
					${ls.id }
				</td>
				<td>${ls.zhName}</td>   
				<td>${ls.enName}</td>      
				<td>${ls.code}</td> 
			</tr>
			</c:forEach>
		</tbody> 
	</table>
</form>
	<li:pagination methodName="query" pageBean="${page}" sytleName="black2"/>
</body>

