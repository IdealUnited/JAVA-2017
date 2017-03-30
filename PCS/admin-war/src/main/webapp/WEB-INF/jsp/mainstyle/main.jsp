<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>后台管理系统</title>
    <link href="hnastyle/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="hnastyle/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="hnastyle/js/themes/icon.css" />
    <script type="text/javascript" src="hnastyle/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="hnastyle/js/jquery.easyui.min.1.2.2.js"></script>
	<script type="text/javascript" src='hnastyle/js/hnmain.js'> </script>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
	<noscript>
		<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
		    浏览器不支持JS
		</div>
	</noscript>
	<div region="north" split="true" border="false" href="main.do?method=topFrame" style="overflow: hidden; height: 40px;
        background: url(hnastyle/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
	</div>
    <div region="west" hide="true" animate="false" title="导航菜单" style="width:270%;height:100%;overflow-x:hidden;position:relative;" id="west" href="main.do?method=menuFrame">
    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs" fit="true" border="false" >
			<div title="工作面板"></div>
		</div>
    </div>
</body>
</html>