<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>

<link rel="stylesheet" href="${ctx}/css/main.css" >
<script type="text/javascript" src="${ctx}/js/prototype/prototype.js"></script>
<script type="text/javascript" >

function processBack(){
	location.href = "roleForUser.do";
}

/*单个移动*/
function moveOptions(objPush,objGet) {   
    for (var i = 0;i < objPush.length;i++) {   
        if (objPush.options[i].selected) {   
              var pushOpt = objPush.options[i]; 
              //兼容firefox和ie
              var text = (!!pushOpt.innerText)?pushOpt.innerText:pushOpt.text;
              objGet.options.add(new Option(text, pushOpt.value));   
              objPush.remove(i);   
              i = i - 1;   
        }   
    }   
}

function select_all(){
    for(var i=0;i<document.roleJoinUserFormBean.funcno.length; i++){
       document.roleJoinUserFormBean.funcno.options[i].selected=true;
    }
 }

 function checkForm(){
    select_all();
	document.roleJoinUserFormBean.submit();

 }
 
/*多个移动*/
function moveAllOptions(objPush,objGet) {   
    for (var i = 0;i < objPush.length;i++) {   
          var pushOpt = objPush.options[i]; 
          //兼容firefox和ie
          var text = (!!pushOpt.innerText)?pushOpt.innerText:pushOpt.text;
          objGet.options.add(new Option(text, pushOpt.value));   
          objPush.remove(i);   
          i = i - 1;         
    }   
}

</script>

</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td height="6"></td>
	</tr>
</table>
<p align="center">
<table width="25%" border="0" cellspacing="0" cellpadding="0" align="center" height="21">
	<tr>
		<td height="1" bgcolor="#000000"></td>
	</tr>
	<tr>
		<td height="18">
		<div align="center"><font class="titletext">用 户 角 色 管 理</font></div>
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#000000"></td>
	</tr>
</table>
<br>
<br>

<table class="border_all2" width="60%" border="0" cellspacing="0" cellpadding="1" align="center">
	<form name="roleJoinUserFormBean" action="roleJoinUserAction.do" method="post">
	<input name="userId" type="hidden" value="${userId}">
	<tr align="left" class=trForContent1>
		<td width="643" valign="middle" nowrap class="border_top_right4">&nbsp;&nbsp;&nbsp;&nbsp;用户名称：${userName}</td>
	</tr>
	<tr valign="top" class="trForContent1">
		<td align="center" nowrap class="border_top_right4">
		<table width="617" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr align="center">
				<td align="left">未分配角色</td>
				<td>&nbsp;</td>
				<td align="left">已分配角色</td>
			</tr>
			<tr align="center">

				<td width="192" align="left">
					<select name="nofuncno" id="nofuncno" class="input1" size="25" multiple="multiple" style="width: 235px;" ondblclick="moveOptions($('nofuncno'),$('funcno'));">
						<c:forEach items="${roleNoList}" var="role">
							<option value="${role.roleKy}">${fn:escapeXml(role.roleName)}</option>
						</c:forEach>
					</select>
				</td>
				<td width="121">
				<table width="58" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="58" align="center">
							<input name="addB" type="button" class="button1" value="添 加 &gt;" onClick="moveOptions($('nofuncno'),$('funcno'));">
						</td>
					</tr>
					<tr>
						<td align="center">&nbsp;</td>
					</tr>
					<tr>
						<td align="center">
							<input name="delB" type="button" class="button1" value="&lt; 删 除" onClick="moveOptions($('funcno'),$('nofuncno'));">
						</td>
					</tr>
					<tr>
						<td align="center">&nbsp;</td>
					</tr>
					<tr>
						<td align="center">&nbsp;</td>
					</tr>
					<tr>
						<td align="center">&nbsp;</td>
					</tr>
					<tr>
						<td align="center">&nbsp;</td>
					</tr>
					<tr>
						<td align="center">&nbsp;</td>
					</tr>
					<tr>
						<td align="center">
							<input name="saveSubmit" type="button" class="button1" value=" 保 存 " onClick="checkForm();">
						</td>
					</tr>
					<tr>
						<td align="center">&nbsp;</td>
					</tr>
	
					<tr>
						<td align="center">
							<input type="button" class="button1" value=" 返 回 " onclick="javascript:processBack()" />
						</td>
					</tr>
				</table>
				</td>
				<td width="192" align="right">
					<select name="funcno" id="funcno" size="25" class="input1" multiple style="width: 235px;" onDblClick="moveOptions($('funcno'),$('nofuncno'));">
					<c:forEach items="${roleList}" var="role">
						<option value="${role.roleKy}">${fn:escapeXml(role.roleName)}</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr align="center">
				<td colspan="3">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	</form>
</table>
</body>
</html>
