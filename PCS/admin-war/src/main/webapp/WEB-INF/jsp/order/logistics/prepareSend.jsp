<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="./js/common.js"></script>
<table width="25%" border="0" cellspacing="0" cellpadding="0"
	align="center" height="21" style="">
	<tr>
		<td height="1" bgcolor="#000000"></td>
	</tr>
	<tr>
		<td height="18">
			<div align="center">
				<font class="titletext">手工生成物流订单</font>
			</div>
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#000000"></td>
	</tr>
</table>
<form action="" name="generateForm" method="post" id="genForm">
	<input type="hidden" name="sequenceList" id="sequenceList" value="${sequenceIdList }" />
	<input type="hidden" name="channelOrderItemId" id="channelOrderItemId" value="${channelOrderItemId }" />
	<input type="hidden" name="status" id="status" value="${status }" />
	<table class="border_all2" width="80%" border="0" cellspacing="0"
		cellpadding="1" align="center">
		<tr class="trForContent1">
			<td align="right" class="border_top_right4">请选择物流渠道：</td>
			<td align="left" class="border_top_right4"><select
				name="expressChannelId" id="expressChannelId" style="width: 100px;"
				onchange="changeExpressChannel('expressChannelId','expressChannelItemId')">
					<option value="">请选择</option>
					<c:forEach items="${expressChannelList}" var="ls">
						<option value="${ls.id}">${ls.name}</option>
					</c:forEach>
			</select></td>
		</tr>

		<tr class="trForContent1">
			<td align="right" class="border_top_right4">请选择具体物流通道：</td>
			<td align="left" class="border_top_right4"><select
				name="expressChannelItemId" id="expressChannelItemId"
				style="width: 100px;">
					<option value="">请选择</option>
					<c:forEach items="${expressChannelItem}" var="lsItem">
						<option value="${lsItem.id}">${lsItem.name}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr class="trForContent1">
			<td align="center" class="border_top_right4"><a href="javascript:validateFrom(this);" class="dialog_link ui-state-default ui-corner-all">
						<span class="ui-icon ui-icon-search"></span>&nbsp;确&nbsp;&nbsp;定&nbsp;</td>
				<td align="center" class="border_top_right4"><a href="${ctx}/createExpressOrder.do" class="dialog_link ui-state-default ui-corner-all">
						<span class="ui-icon ui-icon-search"></span>&nbsp;返&nbsp;&nbsp;回&nbsp;</td>
					</a>
		</tr>
	</table>
</form>

<c:if test="${not empty errorMsg}">
	<li style="color: red">${errorMsg }</li>
</c:if>
<script type="text/javascript">
	function validateFrom(_this) {
		var sequenceList = $("#sequenceList").val();
		var expressChannelId = $("select[name='expressChannelId']").val();
		var expressChannelItemId = $("select[name='expressChannelItemId']").val();
		var channelOrderItemId = $("input[name='channelOrderItemId']").val();
		var status = $("input[name='status']").val();
		if ("" == expressChannelId || null == expressChannelId) {
			alert("请选择物流渠道");
			return false;
		}
		if ("" == expressChannelItemId || null == expressChannelItemId) {
			alert("请选择具体物流通道");
			return false;
		}
		$(_this).attr("disabled", true);
		var pars = "sequenceList="+sequenceList+"&expressChannelId="+expressChannelId+"&expressChannelItemId="+expressChannelItemId+"&channelOrderItemId="+channelOrderItemId+"&status="+status;
			$.ajax({
				type: "POST",
				url: "createExpressOrder.do?method=createExpressOrder",
				data: pars,
				success: function(result) {
					if (result == 'true') {
						alert("手工生成物流订单成功");
						window.location.href='createExpressOrder.do';
					}else{
						
						alert("手工生成物流订单失败:"+result.substring(6,result.length));
						$(_this).attr("disabled", false);
					} 
				}
		})
	}
	

	function changeExpressChannel(father,son){
		//var expressChannelId = $("#expressChannelId").find("option:selected").val();
		//alert(expressChannelId);
		if(expressChannelId==null||expressChannelId==""){
			return;
		}
		var relationArray = new Array();
		<c:forEach items = "${relationList}" var = "relation" varStatus = "relationStatus">
			relationArray[${relationStatus.index}] = new dropDownListMode('${relation.fatherCode}','${relation.code}','${relation.name}');	
		</c:forEach>
		
		this.changeFatherSelect(father,son,relationArray,null);
	} 
	
</script>

