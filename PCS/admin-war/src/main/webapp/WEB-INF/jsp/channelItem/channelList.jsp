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
	<table id="userTable" class="tablesorter" border="0" cellpadding="0" cellspacing="1"  width="25%">
		<thead> 
			<tr>     
				
				<th>通道编号</th>  
				<th>渠道编号</th>
			</tr> 
		</thead> 
		<tbody>
			<c:forEach items="${resultList}" var="ls"> 
			<tr>     
				 
				<td>${ls.id}</td>   
				<td>${ls.name}</td>      
			</tr>
			</c:forEach>
		</tbody> 
	</table>
</body>

