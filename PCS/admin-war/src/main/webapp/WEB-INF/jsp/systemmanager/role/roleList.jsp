<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>角色管理</title>
	<script type="text/javascript">
		<!--
		/*新增角色*/
		function inputAction(){
			location.href = "roleAdmin.do?method=init&roleKy=";
		}
		/*修改角色*/
		function processEdit(roleKy){
			location.href = "roleAdmin.do?method=init&roleKy="+roleKy;
		}
		/*删除角色*/
		//function processDelete(roleKy) {
			//if (confirm("确定删除吗？删除后不可恢复！")) {
				//location.href = "roleAdmin.do?method=delete&roleKy="+roleKy;
			//}
		//}
		//-->
		$(document).ready(function(){
			 $("#roleTable").tablesorter({
				 headers: {
				 	1: {sorter: false},
				 	2: {sorter: false}
				 }}); 

			 $(".tablesorter tbody tr").mouseover(function(){$(this).find("td").css({"background":"#cec"});} )
			 .mouseout(function(){$(this).find("td").css({"background":"#fff"});} ) ;
		});
	</script>
</head>

<body>
	<table id="roleTable" class="tablesorter" border="0" cellpadding="0" cellspacing="1">
		<thead> 
			<tr>     
				<th>名称</th>     
				<th colspan="2">操作</th>  
			</tr> 
		</thead> 
		<tbody>
			<c:forEach items="${page.result}" var="role"> 
			<tr>     
				<td>${role.roleName}</td>
				<td><a href="javascript:processEdit('${role.roleKy}')">修改</a></td>
				<td><a href="javascript:processDelete(deleteRole,'${role.roleKy}','确认删 除：${role.roleName} 角色信息吗')">删除</a></td>
			</tr>
			</c:forEach>
		</tbody> 
	</table>
	<li:pagination methodName="roleQuery" pageBean="${page}" sytleName="black2"/>
</body>
</html>
