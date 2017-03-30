<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div id="listContent">



<table cellSpacing="0" cellPadding="0" width="100%" align="center" border="0">
	<tr>
		<td class="mframe-t-left"><FONT face="宋体"></FONT></td>
		<td class="mframe-t-mid"><span class="mframe-t-text">&nbsp; 浏览角色信息</span>
		</td>
		<td class="mframe-t-right"></td>
	</tr>
</table>

	<table cellSpacing="0" cellPadding="0" width="100%" align="center" border="1">
	<tr>
		<th><b>名称</b></th>
		<th colspan="2"><b>操作</b></th>
	</tr>
	
	<c:forEach items="${roleList}" var="role">
		<tr>
			<td class="mframe-m-mid">${role.roleName}</td>
			
			<td class="mframe-m-mid">
				<a href="javascript:processEdit('${role.roleKy}')">修改</a>
			</td>
			<td class="mframe-m-mid">
				<a href="javascript:processDelete('${role.roleKy}')">删除</a>
			</td>
		</tr>
	</c:forEach>
</table>
</div>

