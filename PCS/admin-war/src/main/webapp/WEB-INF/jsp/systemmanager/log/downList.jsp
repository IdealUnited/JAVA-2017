<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<style type="text/css"> 
.excel_txt {
	padding-top: 1px;
	padding-right: 1px;
	padding-left: 1px;
	mso-ignore: padding;
	color: black;
	font-size: 11.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-number-format: "\@";
	text-align: general;
	vertical-align: middle;
	mso-background-source: auto;
	mso-pattern: auto;
	white-space: nowrap;
}
</style> 
<meta charset="utf-8">
<title>支付平台支付后台登陆导出的excle表格</title>
</head>
<body>
	<table width="800" border="1">
		<thead>
		   <tr>
		       <td colspan="6" style="text-align: middle;">#==============支付平台支付后台登录日志==============</td>
		   </tr>
			<tr>
				<th width="18%">登录名</th>
				<th width="18%">用户IP</th>
				<th width="15%">时间</th>
				<th width="18%">访问链接</th>
				<th>提交类型</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.result}" var="log"> 
			<tr>     
				<td class="excel_txt">${log.loginUser}</td>   
				<td class="excel_txt">${log.userIp}</td>      
				<td class="excel_txt"><fmt:formatDate value="${log.creationDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>     
				<td class="excel_txt">${log.actionUrl}</td>     
				<td class="excel_txt">${log.urlMethod}</td> 
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>