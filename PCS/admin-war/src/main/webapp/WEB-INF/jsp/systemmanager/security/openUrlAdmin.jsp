<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>角色管理</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/images/css/style.css" type="text/css" rel="stylesheet">
	
	<script type="text/javascript">
		<!--
		$(document).ready(function(){
			

			});

		

		function confimModfiy(groupId){
			var $tr = $("#ipGroup_"+groupId);

			var startIp = $.trim($tr.find("input[name=startIp]").val());
			var endIp = $.trim($tr.find("input[name=endIp]").val());
			if(startIp.length==0 || endIp.length== 0){
				alert("开始IP或结束IP都必须填 写");
				return false;
			}
			if(! validateIp(startIp)){
				alert("开始IP填写不正确");
				return false;
			}
			if(! validateIp(endIp)){
				alert("结束IP填写不正确");
				return false;
			}
			
			if(startIp ===  endIp){
				alert("开始IP与结束IP不能相等，并且结束IP必须大于开始IP");
				return false;
			}
			
			var objSub  = {
					startIp : ($tr.find("input[name=startIp]").val()),
					endIp : ($tr.find("input[name=endIp]").val()),
					remark : ($tr.find("input[name=remark]").val()),
					configId : ($tr.find("input[name=configId]").val()),
					"groupId":groupId
				};
			$.post("${ctx}/security/modfiyIpGroup.do",
				objSub,
				function callBackFun(msg){
						if(msg=="S"){
							alert("修改成功！");
							reflushConfig();
						}else{
							alert(msg);
						}
					}
				);
		}
		function removeIpGroup(groupId){
			var $tr = $("#ipGroup_"+groupId);
			var objSub  = {
					"groupId":groupId
				};
			$.post("${ctx}/security/removeIpGroup.do",
				objSub,
				function callBackFun(msg){
						if(msg=="S"){
							alert("删除成功");
							$tr.remove();
							reflushConfig();
						}else{
							alert(msg);
						}
					}
				);
		}
		function addNewConfig(){
			$("#configBody").append("<tr>"
					+"<td><input type='text' name='openUrl' size='30' value='' class='input_txt' /></td>"   
					+"<td><select name='ipCheck'><option value='1'  >是</option><option value='0' >否</option></select></td>"      
					+"<td><textarea rows='3' cols='17' name='ips' ></textarea></td>"    
					+"<td></td>"     
					+"<td><textarea rows='3' cols='17' name='remark' ></textarea></td>"
					+"<td><input  type='button' value='确定新增' onclick='newConfigSubmit(this)' /></td>" 
					+"</tr>");
		}
		function addNewIpGroup(configId){
			$("#"+"configIpGr_"+configId).append("<tr>"
					+"<td><input type='hidden' name='configId' value='"+configId+"'  style='width:96px'/>"   
					+"<input type='text' name='startIp' value=''  style='width:96px'/></td>"      
					+"<td><input type='text' name='endIp' value='' style='width:96px'/></td>"    
					+"<td><input type='text' name='remark' value='' style='width:106px'/></td>"    
					+"<td><input  type='button' value='确定新增' onclick='newIpGroupSubmit(this)' /></td>" 
					+"</tr>");

		}
		function newConfigSubmit(btn){
	
			var $tr = $(btn).parent().parent();
			var objSub  = {
					openUrl : ($tr.find("input[name=openUrl]").val()),
					ips : ($tr.find("textarea[name=ips]").val()),
					ipCheck : ($tr.find("select[name=ipCheck]").val()),
					remark : ($tr.find("textarea[name=remark]").val())
				};
			$.post("${ctx}/security/newOpenUrlConfig.do",
				objSub,
				function callBackFun(msg){
						if(msg=="S"){
							window.location.href = "${ctx}/security/openUrlAdmin.do";
						}else{
							alert(msg);
						}
					}
				);
			
		}

		function newIpGroupSubmit(btn){
			
			var $tr = $(btn).parent().parent();
			//alert(($tr.find("input[name=configId]").val()));
		
			var startIp = $.trim($tr.find("input[name=startIp]").val());
			var endIp = $.trim($tr.find("input[name=endIp]").val());
			if(startIp.length==0 || endIp.length== 0){
				alert("开始IP或结束IP都必须填 写");
				return false;
			}
			if(! validateIp(startIp)){
				alert("开始IP填写不正确");
				return false;
			}
			if(! validateIp(endIp)){
				alert("结束IP填写不正确");
				return false;
			}
			
			if(startIp ===  endIp){
				alert("开始IP与结束IP不能相等，并且结束IP必须大于开始IP");
				return false;
			}
			var objSub  = {
					configId : ($tr.find("input[name=configId]").val()),
					startIp : ($tr.find("input[name=startIp]").val()),
					endIp : ($tr.find("input[name=endIp]").val()),
					remark : ($tr.find("input[name=remark]").val())
				};
			$.post("${ctx}/security/addConfigIpGroup.do",
				objSub,
				function callBackFun(msg){
						if(/^\d+$/.test(msg)){
							window.location.href = "${ctx}/security/openUrlAdmin.do";
						}else{
							alert(msg);
						}
					}
				);
			
		}

		function removeUrlConfig(configId){
			var $tr = $("#configId_"+configId);
			if(!confirm("确定删除吗？")){
				return false;
			}
			var objSub  = {
					"configId":configId
				};
			$.post("${ctx}/security/removeUrlConfig.do",
				objSub,
				function callBackFun(msg){
						if(msg=="S"){
							alert("删除成功");
							$tr.remove();
							reflushConfig();
						}else{
							alert(msg);
						}
					}
				);
		}
		function modifyUrlConfig(configId){

			var $tr = $("#configId_"+configId);

			var objSub  = {
					openUrl : ($tr.find("input[name=openUrl]").val()),
					ips : ($tr.find("textarea[name=ips]").val()),
					ipCheck : ($tr.find("select[name=ipCheck]").val()),
					remark : ($tr.find("textarea[name=remark]").val()),
					configId:configId
				};
			$.post("${ctx}/security/modifyUrlConfig.do",
				objSub,
				function callBackFun(msg){
						if(msg=="S"){
							alert("修改成功！");
							reflushConfig();
						}else{
							alert(msg);
						}
					}
				);
		}

		function reflushConfig(){
			$.get("${ctx}/security/openUrlAdmin.do?d="+Math.random(),{},function(){});
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

		
		//-->
	</script>
</head>

 
<body>

<div id="listContent">



<table width="25%" border="0" cellspacing="0" cellpadding="0" align="center" height="21">
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
			<tr>
				<td height="18">
					<div align="center">
						<font class="titletext">开放的URL配置</font>
					</div>
				</td>
			</tr>
			<tr>
				<td height="1" bgcolor="#000000"></td>
			</tr>
		</table>

	<form id="form1" name="form1" action="${ctx}/security/openUrlAdmin.do">
	
	<table id="userTable" class="tablesorter" border="0" cellpadding="0" cellspacing="1">
		<thead> 
			<tr>     
				<th><span class="must">*</span>URL(必须是"/"开头)</th>  
				<th><span class="must">*</span>是否需IP校验</th>        
				<th>可访问的IP地址</th>     
				<th>可访问的IP段</th>        
				<th>描述</th>  
				<th>操作</th>  
				
				
			</tr> 
		</thead> 
		<tbody id="configBody">
		
			<c:forEach items="${openUrlList}" var="url"> 
			<c:set value="${configMaps[url]}" var="openUrlConfig"></c:set>
			<tr id="configId_${openUrlConfig.configId}">     
				<td><input type="text" name="openUrl" size="30" value="${openUrlConfig.openUrl}" class="input_txt" /></td>   
				<td>
				<select name="ipCheck">
					<c:if test="${openUrlConfig.ipCheck == 1}">
						<option value="1" selected="selected" >是</option>
					<option value="0" >否</option>
					</c:if>
					<c:if test="${openUrlConfig.ipCheck == 0}">
						<option value="1"  >是</option>
					<option value="0" selected="selected">否</option>
					</c:if>
				</select>
				</td>      
				<td><textarea rows="3" cols="17" name="ips" >${openUrlConfig.ips}</textarea> </td>     
				<td>
						<table id="ipGroupTable" class="tablesorter" border="0" cellpadding="0" cellspacing="1">
							<thead> 
								<tr>     
									<th><span class="must">*</span>开始IP</th>  
									<th><span class="must">*</span>结束IP</th>        
									<th>IP段描述</th>
									<th>操作</th>
									</tr>
							</thead>
								
						
								<tbody id="configIpGr_${openUrlConfig.configId}">
								<c:if test="${not empty openUrlConfig.ipGroups}">
									<c:forEach items="${openUrlConfig.ipGroups}" var="ipGroup"> 
									<tr id="ipGroup_${ipGroup.groupId}">
										<td>
										<input type="hidden" name="configId" value="${ipGroup.configId}"  style="width:96px"/>
										<input type="text" name="startIp" value="${ipGroup.startIp}"  style="width:96px"/></td>
										<td><input type="text" name="endIp" value="${ipGroup.endIp }" style="width:96px"/></td>
										<td><input type="text" name="remark" value="${ipGroup.remark }" style="width:106px"/></td>
										<td><input  type="button" value="修改" onclick="confimModfiy('${ipGroup.groupId}')"/> 
										<input  type="button" value="删除" onclick="removeIpGroup('${ipGroup.groupId}')"/></td>
									</tr>
									</c:forEach>
									</c:if>
									
									</tbody>
								
									<tr>   
										<td colspan="4"> <input  type="button" value="添加IP段" onclick="addNewIpGroup('${openUrlConfig.configId}')" />  </td>  
									</tr>
								</table>
					
				</td>     
				<td><textarea rows="3" cols="17" name="remark" >${openUrlConfig.remark}</textarea></td>
				<td><input  type="button" value="修改" onclick="modifyUrlConfig('${openUrlConfig.configId}')" /> <input  type="button" value="删除" onclick="removeUrlConfig('${openUrlConfig.configId}')"/></td> 
				
			</tr>
			</c:forEach>
		</tbody> 
	</table>
	<div style="width:100%;text-align: center">	
		<input  type="button" value="添加配置" style="background: #ccc"  onclick="addNewConfig()"  /> 
	</div>
	
</form>
</div>

</body>
</html>
