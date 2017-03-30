<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>产品管理</title>
		<script type="text/javascript">
		
		function update() {
			
			if(!confirm("确认修改吗？")){
				return false;
			}
			
			var zhType = $('#zhType').val();
			if(''== zhType){
				alert('请填写中文类别!');
				return;
			}
			
			var enType = $('#enType').val();
			if(''== enType){
				alert('请填写英文类别!');
				return;
			}
			
			var pars = $("#userSearchForm").serialize();
			$.ajax({
				type: "POST",
				url: "${ctx}/productType.do?method=update",
				data: pars,
				success: function(result) {
					if(result==1){
						alert('更新成功！');
					}else{
						alert('更新失败！');
					}
					
				}
			});
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
						<font class="titletext">产品类别修改</font>
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
				<td style="text-align:right;">中文类别：</td>
				<td class="textLeft">
					<input type="text" name="zhType" id="zhType" value="${productType.zhType }" style="width: 100px;" />
				</td>
				<td style="text-align:right;">英文类别：</td>
				<td class="textLeft">
					<input type="text" name="enType" id="enType" style="width: 100px;" value="${productType.enType }"/><i>注：亚玛逊为必填</i>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:center; height: 35px;" colspan="4">
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="update()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;保&nbsp;&nbsp;存&nbsp;
					</a>
					&nbsp;&nbsp;
					<a href="${ctx}/productType.do" class="dialog_link ui-state-default ui-corner-all">
						<span class="ui-icon ui-icon-search"></span>&nbsp;返&nbsp;&nbsp;回&nbsp;
					</a>
				</td>
			</tr>
		</table>
		<input type="hidden" id="id" name="id" value="${productType.id}"/>
		</form>
	</body>
</html>
		
