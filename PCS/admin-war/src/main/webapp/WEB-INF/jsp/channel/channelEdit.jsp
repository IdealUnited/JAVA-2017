<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<script type="text/javascript">
		
		function save() {
			
			var name = $('#name').val();
			if(''== name){
				alert('请填写渠道名称!');
				return;
			}
			
			var code = $('#code').val();
			if(''== code){
				alert('请填写渠道code!');
				return;
			}
			
			var status = $('#status').val();
			if(''== status){
				alert('请选择状态!');
				return;
			}
			
			if(!confirm("确认修改吗？")){
				return false;
			}
			
			$('#infoLoadingDiv').dialog('open');
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			var pars = $("#userSearchForm").serialize();
			$.ajax({
				type: "POST",
				url: "${ctx}/channel.do?method=edit",
				data: pars,
				success: function(result) {
					$('#infoLoadingDiv').dialog('close');
					if(1==result){
						alert('修改成功！');
					}
				}
			});
		}
		
		function returnBack(){
			window.location.href='${ctx}/channel.do';
		}
		
		</script>
	</head>

	<body>
		<table width="25%" border="0" cellspacing="0" cellpadding="0" align="center" height="21">
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
			<tr>
				<td height="18">
					<div align="center">
						<font class="titletext">渠道修改</font>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
		</table>
		<br/>
		<form id="userSearchForm">
		<table border="0" style="width: 700px; " class="inputTable" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td style="text-align:right;">渠道名称：</td>
				<td class="textLeft">
					<input type="text" name="name" id="name" style="width: 100px;" value="${channel.name }"/>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;">渠道code：</td>
				<td class="textLeft">
					<input type="text" name="code" id="code" style="width: 100px;" value="${channel.code }"/>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;">状态：</td>
				<td class="textLeft">
					<select name="status" id="status">
						<option value="1" <c:if test="${channel.status==1 }">selected</c:if>>有效</option>
						<option value="0" <c:if test="${channel.status==0 }">selected</c:if>>关闭</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style="text-align:center; height: 35px;" colspan="2">
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="save()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;保&nbsp;&nbsp;存&nbsp;
					</a>
					&nbsp;&nbsp;
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="returnBack()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;返&nbsp;&nbsp;回&nbsp;
					</a>
				</td>
			</tr>
		</table>
		<input type="hidden" name="id" id="id" value="${channel.id }"/>
		</form>
	</body>
</html>
		
