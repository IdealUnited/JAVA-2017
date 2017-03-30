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
				
				<th>登录名</th>  
				<th>用户IP</th>        
				<th>时间</th>     
				<th>访问链接</th>     
				<th>提交类型</th>     
			</tr> 
		</thead> 
		<tbody>
			<c:forEach items="${page.result}" var="log"> 
			<tr>     
				<td>${log.loginUser}</td>   
				<td>${log.userIp}</td>      
				<td><fmt:formatDate value="${log.creationDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>     
				<td>${log.actionUrl}</td>     
				<td>${log.urlMethod}</td> 
			</tr>
			</c:forEach>
		</tbody> 
	</table>
	<li:pagination methodName="userQuery" pageBean="${page}" sytleName="black2"/>
</body>

