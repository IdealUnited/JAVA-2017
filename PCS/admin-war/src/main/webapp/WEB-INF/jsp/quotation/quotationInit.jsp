<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<html>
	<head>
		<script type="text/javascript">
			var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";
			
			function query(pageNo,totalCount,pageSize) {
				$('#infoLoadingDiv').dialog('open');
				var pars = $("#searchForm").serialize() + "&pageNo=" + pageNo + "&totalCount=" + totalCount+"&pageSize="+pageSize;
				$.ajax({
					type: "POST",
					url: "${ctx}/quotation.do?method=search",
					data: pars,
					success: function(result) {
						$('#infoLoadingDiv').dialog('close');
						$('#resultListDiv').html(result);
					}
				});
			}
			
			function upload(){
				var channelCode = $('#channelCode').val();
				if(channelCode == ''){
					alert('请选择渠道！');
					return;
				}
				$("#stockUploadForm").submit();
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
						<font class="titletext">报价单管理</font>
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
						action="${ctx}/quotation.do?method=upload">
					物流渠道：<select id="channelCode" name="channelCode">
						<option value="">请选择</option>
						<c:forEach items="${expressChannels }" var="r">
						<option value="${r.code }">${r.name }</option>
						</c:forEach>
					</select>
					<input type="file" name="file"/>
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="upload()">
						导入
					</a>
					</form>
				</td>
			</tr>
		</table>
		<br/>
		<form id="searchForm">
		<table border="0" style="width: 1100px; " class="inputTable" align="center" cellpadding="3" cellspacing="0">
			<tr>
				<td style="text-align:right;">物流渠道：</td>
				<td class="textLeft">
					<select id="channelId" name="channelId">
						<option value="">请选择</option>
						<c:forEach items="${expressChannels }" var="r">
						<option value="${r.id}">${r.name }</option>
						</c:forEach>
					</select>
				</td>
				<td style="text-align:right;">渠道产品名称：</td>
				<td class="textLeft">
					<input type="text" name="title" id="title" style="width: 100px;" />
				</td>
				<td style="text-align:right;">国家二位缩写：</td>
				<td class="textLeft">
					<input type="text" name="countryCode" id="countryCode" style="width: 100px;" />
				</td>
				<td style="text-align:right;">类型：</td>
				<td class="textLeft">
					<select name="type" id="type">
						<option value="">请选择</option>
						<option value="1">挂号</option>
						<option value="2">平邮</option>
						<option value="3">专线</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:center; height: 35px;" colspan="8">
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="query()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;查&nbsp;&nbsp;询&nbsp;
					</a>
				</td>
			</tr>
		</table>
		</form>
		<p></p>
		<div id="resultListDiv" class="listFence" align="center"></div>
		<div id="allOverlayDiv" class="ui-widget-overlay" style="display: none;"></div>
		<div id="infoLoadingDiv" title="加载信息" style="display: none; width: 200px; padding-top: 30px; height: 70px;">
			<img src="${ctx}/images/page/blue-loading.gif" />&nbsp;&nbsp;信息加载中, 请稍候...
		</div>
	</body>
</html>
		
