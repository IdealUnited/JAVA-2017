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
				
				<th>ID</th>  
				<th>中文类别</th>        
				<th>英文类别</th>
				<th>操作</th>
			</tr> 
		</thead> 
		<tbody>
			<c:forEach items="${resultList}" var="ls"> 
			<tr>     
				 
				<td>${ls.id}</td>   
				<td>${ls.zhType}</td>      
				<td>${ls.enType}</td> 
				<td><a href="${ctx}/productType.do?method=updateInit&id=${ls.id}">修改</a></td>
			</tr>
			</c:forEach>
		</tbody> 
	</table>
	<li:pagination methodName="query" pageBean="${page}" sytleName="black2"/>
</body>

