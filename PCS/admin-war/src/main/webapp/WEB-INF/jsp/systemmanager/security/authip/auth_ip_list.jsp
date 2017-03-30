<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<body>
<form id="formBean" name="formBean" action="frozenLog.do" method="post">
	<table class="border_all2" width="95%" border="0" cellspacing="0"
		cellpadding="1" align="center">
		<tr class="trbgcolorForTitle" align="center" valign="middle">
			<td class="border_right4"  nowrap><a class="s03"><font
				color="#FFFFFF">登录名</font> </a></td>
			<td class="border_right4"  nowrap><a class="s03"><font
				color="#FFFFFF">IP地址</font></a></td>
			<td class="border_right4"  nowrap><a class="s03"><font
				color="#FFFFFF">姓名</font></a></td>	
			<td class="border_right4"  nowrap><a class="s03"><font
				color="#FFFFFF">部门</font></a></td>		
			<td class="border_right4"  nowrap><a class="s03"><font
				color="#FFFFFF">创建时间</font></a></td>
			<td class="border_right4"  nowrap><a class="s03"><font
				color="#FFFFFF">更新时间</font></a></td>	
			<td class="border_right4"  nowrap><a class="s03"><font
				color="#FFFFFF">状态</font></a></td>	
			<td class="border_right4" nowrap ><a class="s03"><font
				color="#FFFFFF">操作</font></a></td>				
		</tr>
		<c:forEach items="${page.result}" var="dto" varStatus = "status">
		<c:choose>
	       <c:when test="${status.index%2==0}">
	             <tr class="trForContent1">
	       </c:when>
	       <c:otherwise>
	             <tr class="trForContent2">
	       </c:otherwise>
		</c:choose>
				<td class="border_top_right4" align="center" nowrap>${dto.loginName}</td>
				<td class="border_top_right4" align="center" nowrap>${dto.authIp}&nbsp;</td>	
				<td class="border_top_right4" align="center" nowrap>${dto.authUser}&nbsp;</td>
				<td class="border_top_right4" align="center" nowrap>${dto.authDept}&nbsp;</td>
				<td class="border_top_right4" align="center" nowrap><fmt:formatDate value="${dto.creationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
				<td class="border_top_right4" align="center" nowrap><fmt:formatDate value="${dto.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>	
				<td class="border_top_right4" align="center" nowrap>${dto.status==1?"有效":"无效"}&nbsp;</td>	
					<td class="border_top_right4" align="center" nowrap>
						<a href="javascript:void(0)" onclick="editAuthIp('${dto.authId}')">修改<a/>
						<a href="javascript:void(0)" onclick="deleteAuthIp('${dto.authId}')">删除<a/>
					</td>						
			</tr>
		</c:forEach>
	</table>	
</form>

<li:pagination methodName="indexQuery" pageBean="${page}" sytleName="black2"/>

</body>

