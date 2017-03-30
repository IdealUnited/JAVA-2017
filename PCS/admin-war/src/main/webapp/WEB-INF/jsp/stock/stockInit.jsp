<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<title>货品管理</title>
		<script type="text/javascript">
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			
			function query(pageNo,totalCount,pageSize) {
				$('#infoLoadingDiv').dialog('open');
				var pars = $("#stockSearchForm").serialize() + "&pageNo=" + pageNo + "&totalCount=" + totalCount+"&pageSize="+pageSize;
				$.ajax({
					type: "POST",
					url: "${ctx}/productStock.do?method=search",
					data: pars,
					success: function(result) {
						$('#infoLoadingDiv').dialog('close');
						$('#resultListDiv').html(result);
					}
				});
			}
			
			function downloadTemplate(){
				$.post("${ctx}/productStock.do?method=download",function(data,status){
					
				});
			}
			
			function upload(){
				var repositoryId = $('#repositoryId').val();
				if(repositoryId == ''){
					alert('请选择仓库！');
					return;
				}
				$('#infoUploadingDiv').dialog('open');
				$("#stockUploadForm").submit();
				$('#infoUploadingDiv').dialog('close');
			}
			
			
			$(document).ready(function(){query();});
			
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
						<font class="titletext">货品信息</font>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
		</table>
		<br/>
		<br/>
		<table width="86%" border="0" cellspacing="0" cellpadding="0" align="center" height="21">
			<tr>
				<td class="textLeft">
					<form id="stockUploadForm" method="POST" enctype="multipart/form-data" 
						action="${ctx}/productStock.do?method=upload">
					仓库：<select id="repositoryId" name="repositoryId">
						<option value="">请选择</option>
						<c:forEach items="${repositorys }" var="r">
						<option value="${r.id }">${r.name }</option>
						</c:forEach>
					</select>
					<input type="file" name="file"/>
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="upload()">
						批量导入
					</a>
					</form>
				</td>
				<td class="textRight">
					<a href="${ctx}/productStock.do?method=download" class="dialog_link ui-state-default ui-corner-all">
						模版下载
					</a>
				</td>
			</tr>
			<tr>
				<td><br/></td><td></td>
			</tr>
		</table>
		<br/>
		<br/>
		<form id="stockSearchForm">
		<table class="searchTermTable">
			<tr>
				<td class="textRight">SKU：</td>
				<td class="textLeft"><input type="text" name="sku" style="width: 100px;" /></td>
				<td class="textRight">产品类别：</td>
				<td class="textLeft">
					<select name="typeEn">
						<option value="">请选择</option>
					</select>
				</td>
				<td class="textRight">重量：</td>
				<td class="textLeft">
					<input type="text" name="weightStart" style="width: 50px;" />至
					<input type="text" name="weightEnd" style="width: 50px;" />
				</td>
				
				<td class="textLeft">
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="query()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;查&nbsp;&nbsp;询&nbsp;
					</a>
				</td>
			</tr>
			<tr>
				<td class="textRight">排版尽寸：</td>
				<td class="textLeft" colspan="6">
					<select name="size">
						<option value="">请选择</option>
					</select>
				</td>
			</tr>
		</table>
		</form>
		<div id="resultListDiv" class="listFence"></div>
		<div id="allOverlayDiv" class="ui-widget-overlay" style="display: none;"></div>
		<div id="infoLoadingDiv" title="加载信息" style="display: none; width: 200px; padding-top: 30px; height: 70px;">
			<img src="${ctx}/images/page/blue-loading.gif" />&nbsp;&nbsp;信息加载中, 请稍候...
		</div>
		<div id="infoUploadingDiv" title="加载信息" style="display: none; width: 200px; padding-top: 30px; height: 70px;">
			<img src="${ctx}/images/page/blue-loading.gif" />&nbsp;&nbsp;正在上传信息, 请稍候...
		</div>
	</body>
</html>
		
