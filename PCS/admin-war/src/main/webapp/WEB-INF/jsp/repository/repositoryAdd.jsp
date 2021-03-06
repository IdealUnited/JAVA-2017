<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>产品管理</title>
		<script type="text/javascript">
		
		function save() {
			
			if(!confirm("确认添加吗？")){
				return false;
			}
			
			var name = $('#name').val();
			if(''== name){
				alert('请填写仓库名称!');
				return;
			}
			
			$('#infoLoadingDiv').dialog('open');
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			var pars = $("#userSearchForm").serialize();
			$.ajax({
				type: "POST",
				url: "${ctx}/repository.do?method=add",
				data: pars,
				success: function(result) {
					$('#infoLoadingDiv').dialog('close');
					if(1==result){
						alert('添加成功！');
					}
				}
			});
		}
		
		function returnBack(){
			window.location.href='${ctx}/repository.do';
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
						<font class="titletext">仓库添加</font>
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
				<td style="text-align:right;">仓库名称：</td>
				<td class="textLeft">
					<input type="text" name="name" id="name" style="width: 100px;" />
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
		</form>
	</body>
</html>
		
