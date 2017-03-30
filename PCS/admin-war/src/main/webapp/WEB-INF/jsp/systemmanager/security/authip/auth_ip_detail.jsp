<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<body>

	<table class="inputTable" width="400" border="0" cellspacing="0" cellpadding="3" align="center">
	<tr>	
		<th width="80" nowrap="nowrap">会员号</th>
		<td >
			${dto.memberCode}
		</td>
	</tr>
	<tr>	
		<th >登录名</th>
		<td >
			${dto.loginName}
		</td>
	</tr>
	<tr>	
		<th >冻结金额</th>
		<td >
		<fmt:formatNumber value="${dto.amountDouble/1000}"  type="currency" />
		</td>
	</tr>
	<tr>	
		<th >可用金额</th>
		<td >
		<fmt:formatNumber value="${dto.balanceDouble/1000}"  type="currency" />
		</td>
	</tr>
	<tr>	
		<th >创建时间</th>
		<td >
			<fmt:formatDate value="${dto.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<tr>	
		<th >更新时间</th>
		<td >
			<fmt:formatDate value="${dto.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
		</td>
	</tr>
	<tr>	
		<th >流水号</th>
		<td >
			${dto.serialNo}
		</td>
	</tr>
	<tr>	
		<th >原流水号</th>
		<td >
			${dto.oldSerialId!=null?dto.oldSerialId:"&nbsp;"}
		</td>
	</tr>
	<tr>	
		<th >冻结/解冻</th>
		<td >
			${dto.frozenType==1?"冻结":"解冻"}
		</td>
	</tr>
	<tr>	
		<th >状态</th>
		<td >
			${dto.status==1?"成功":(dto.status==2?"已解冻":"失败")}
		</td>
	</tr>
	<tr>	
		<th >备注</th>
		<td >
		<div  style="width:100%;word-break : break-all ">
			${dto.description}
		</div>
			
			
		</td>
	</tr>
	
	
	</table>


</body>

