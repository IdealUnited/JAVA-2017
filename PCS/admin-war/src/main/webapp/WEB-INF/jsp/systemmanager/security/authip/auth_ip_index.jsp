<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>



<head>

<script language="javascript">
var loadImgStr = "<img border=\"0\" src=\"${ctx}/images/page/blue-loading.gif\" />操作中, 请稍候...";

$(document).ready(
		function(){
			indexQuery();
		}
);

function indexQuery(pageNo,totalCount,pageSize) {
	

	$('#infoLoadingDiv').dialog('open');
	var pars = $("#searchFormBean").serialize() +"&pageNo=" + pageNo + "&totalCount=" + totalCount + "&pageSize=" + pageSize;
	$.ajax({
		type: "POST",
		url: "${ctx}/security/authIp.do?method=search",
		data: pars,
		success: function(result) {
			$('#infoLoadingDiv').dialog('close');
			$('#resultListDiv').html(result);
		}
	});
} 
function validateIp(ip){
	var ipSeg = ip.split(".");
	if (ipSeg.length != 4) {
		return false;
	}
	for(var i=3; i>=0;i--){
		if(! (/^\d{1,3}$/.test(ipSeg[i]))){
			return false;
		}	
		  if (parseInt( ipSeg[i]) > 256){
			  return false;
		  }
	}
	return true;
}


function editAuthIp(id){
	var targetMethod = "addAuthIp" ;
	if(id !=null ){
		$.getJSON("${ctx}/security/authIp.do?method=getAuthIpJson",{"authId":id},function cbk(obj){
			$("#ip").val(obj.authIp);
			$("#loginUser").val(obj.loginName);
			$("#authUser").val(obj.authUser);
			$("#authDept").val(obj.authDept);
		});
		targetMethod = "updateAuthIp";
	}
	$("#ip").val("");
	$("#loginUser").val("");
	$("#authUser").val("");
	$("#authDept").val("");
	$('#addLogDiv').dialog( 
			{ 
				modal:true, 		
			autoOpen:true,	
			height:270, 
			width:500, 
			title:'编辑授权信息', 
			overlay: {opacity: 0.5, background: "black" ,overflow:'auto'}, 
			buttons:{ 
			'确定':function(){ 

					
					var authId = id;
					var authIp = $.trim($("#ip").val());
					var loginName = $.trim($("#loginUser").val());
					var authUser = $.trim($("#authUser").val());
					var authDept = $.trim($("#authDept").val());
					var status = $("#ipStatus").val();
					if(!validateIp(authIp)){
						alert("IP地址不正确，请输入正确IP。");
						return false;
					}
					var objs = {"authIp":authIp,"loginName":loginName,"authUser":authUser,"status":status,"authDept":authDept,"authId":id,
							method:targetMethod
							};
				
					$.post("${ctx}/security/authIp.do",objs
						,function (msg){
								if(msg=="S"){
									$('#addLogDiv').dialog("close")
									alert("操作成功");
									indexQuery();
									
								}
								else{
									alert(msg);
								}
						}
					);
			}, 
			'取消':function(){ 
				$("#addLogDiv").dialog("close");
			} 
			} 
} );}


function deleteAuthIp(id){
	if(id && confirm("确定删除吗？") ){
		var objs = {"authId":id,method:"deleteAuthIp"};
		$.post("${ctx}/security/authIp.do",objs
				,function (msg){
						if(msg=="S"){
							$('#addLogDiv').dialog("close")
							alert("删除成功");
							indexQuery();
							
						}
						else{
							alert(msg);
						}
				}
			);
	}
	
}
/*
function showDetail(id){
	$('#detailDiv').load("${ctx}/frozenLog.do?method=getFrozenDetial",{id:id},function(msg){
		});
	

		$('#detailDiv').dialog( 
				{ 
				width:500,	
				modal:true, 	
				title:'详细页面', 
				overlay: {opacity: 0.5, background: "black" ,overflow:'auto' }
				
		} );

}
*/




</script>
</head>

<body>

<table width="35%" border="0" cellspacing="0" cellpadding="0"
	align="center" height="21" >
	<tr>
		<td height="1" bgcolor="#000000"></td>
	</tr>
	<tr>
		<td height="18">
		<div align="center"><font class="titletext">授 权 I P 管 理</font></div>
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#000000"></td>
	</tr>
	
</table>

	<form id="searchFormBean">
		<table class="searchTermTable" class="inputTable" style="width:80%" >
			<tr>
				<th >登录名：<input type="text" id="loginName" name="loginName" style="width: 150px;" maxlength="50" /></th>
				<th >授权IP地址：<input type="text" id="authIp" name="authIp" style="width: 150px;" maxlength="20"/></th>
				<th >限制状态：
					<select name="status">
						<option value="">全部</option>
						<option value="1">有效</option>
						<option value="0">无效</option>
					</select>
				</th>
				
				
			</tr>
			<tr ><td height="10"></td>
			</tr>
			<tr>
					<td  colspan="4" style="text-align: center;  ">
					<a href="#" class="dialog_link ui-state-default ui-corner-all" onclick="indexQuery()">
						<span class="ui-icon ui-icon-search"></span>&nbsp;查&nbsp;&nbsp;询&nbsp;
					</a>&nbsp;&nbsp;&nbsp;
					<a href="javascript:void(0)" onclick="editAuthIp()" class="dialog_link ui-state-default ui-corner-all">
						<span class="ui-icon ui-icon-newwin"></span>&nbsp;添加授权IP&nbsp;
					</a>
				</td>
			</tr>
		</table>
		</form>
<c:if test="${sign!=null}">
<br><br>
	<center>
		<font color="red">${sign}</font>
	</center>

</c:if>

<div id="resultListDiv" class="listFence"></div>		
<div id="allOverlayDiv" class="ui-widget-overlay" style="display: none;"></div>
<div id="infoLoadingDiv" title="加载信息" style="display: none; width: 200px; padding-top: 30px; height: 70px;">
	<img src="${ctx}/images/page/blue-loading.gif" />&nbsp;&nbsp;信息加载中, 请稍候...
</div>
<div id="addLogDiv" style="display: none">
	<table class="inputTable" width="400" height="180" border="0" cellspacing="0" cellpadding="3" align="center">
	<tr>	
		<th><span class="must">*</span>IP地址</th>
		<td >
			<input type="text" name="ip" id="ip" />
		</td>
	</tr>
	
	<tr>
		<th >用户登录名</th>
		<td >
			<input type="text" name="loginUser" id="loginUser"  />
		</td>
	</tr>
	<tr>
		<th >用户真实姓名</th>
		<td >
			<input type="text" name="authUser" id="authUser"  />
		</td>
	</tr>
	<tr>
		<th >所在部名名称</th>
		<td >
			<input type="text" name="authDept" id="authDept"  />
		</td>
	</tr>
	<tr>
		<th><span class="must">*</span>是否有效</th>
		<td>
			<select name="ipStatus" id="ipStatus">
						<option value="1" selected="selected">有效</option>
						<option value="0">无效</option>
				</select>
		</td>
	</tr>
	</table>
</div>

<div id="detailDiv" style="display: none;width: 500px;height: 500px ">
	
</div>

</body>

