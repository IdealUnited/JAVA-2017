<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<script type="text/javascript">
		$(document).ready(function(){
			 $(".tablesorter tbody tr").mouseover(function(){$(this).find("td").css({"background":"#cec"});} )
			 .mouseout(function(){$(this).find("td").css({"background":"#fff"});} ) ;   
			 $("#userTable").tablesorter({
				 headers: {
				 	6: {sorter: false}
				 }});      
		});
		function processSearch(){
			var userSearchForm = $("#userSearchForm");
			userSearchForm.submit();
		}
		function processEdit(id){
			location.href = "user.do?method=updateInit&id=" + id;
		}
		function processAdd(){
			location.href = "userAdd.do";
		}
		//分配角色
		function processRoleForUser(id,userName){
			//location.href = "roleJoinUserAction.do?userId=" + id;
			location.href = "roleForUser.do?method=roleForUser&userId=" + id;
		}
	</script>
</head>

<body>
	<table id="userTable" class="tablesorter" border="0" cellpadding="0" cellspacing="1">
		<thead> 
			<tr>     
				
				<th>用户名</th>  
				<th>用户姓名</th>        
				<th>部门</th>     
				<th>职位</th>     
				<th>状态</th>
				<th>最后登录时间</th>
				<th>操作</th>  
			</tr> 
		</thead> 
		<tbody>
			<c:forEach items="${page.result}" var="use"> 
			<tr>     
			 
				<td>${use.userCode}</td>  
				<td>${use.userName}</td>       
				<td>${use.userOrgName}</td>     
				<td>${use.userDutyName}</td>     
				<td>${use.userStatusName}</td> 
				<td>${use.lastLoginTime}</td>
				<td>
					<c:if test="${use.userStatus==1}" >
					<a href="javascript:processRoleForUser('${use.userKy}')">分配角色</a>
					</c:if>
					
				</td> 
			</tr>
			</c:forEach>
		</tbody> 
	</table>
	<li:pagination methodName="userQuery" pageBean="${page}" sytleName="black2"/>
</body>

