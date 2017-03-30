<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<script language="javascript">
$(function(){
	$('#loginOut').click(function() {
		$.messager.confirm('系统提示', '您确定要退出登录吗?', function(r) {
			if (r) {
				location.href = 'main.do?method=logout';
			}
		});
	});

	$('#editPwd').click(function() {
		open1('修改密码','updatepassword.do');
	});
});
</script>
<span style="float:right; padding-right:20px;" class="head">编号：${userKy} 用户：${userName }<a href="#" id="loginOut" style="padding-left:50px;">退出登录</a> <a href="#" id="editPwd">修改密码</a> </span>
<span style="padding-left:10px; font-size: 15px; "><img src="hnastyle/images/CategorizeMenu.png" style="vertical-align:text-bottom;padding-top:2px;" width="20" height="20" align="absmiddle" />后台管理系统</span>