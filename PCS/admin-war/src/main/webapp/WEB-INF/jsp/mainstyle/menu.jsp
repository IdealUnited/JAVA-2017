<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="css/main.css">

<link rel="stylesheet" href="css/ztree/zTreeStyle.css" />
<script src="js/jquery/plugins/ztree/jquery.ztree-2.6.min.js" type="text/javascript"></script>
<script language="javascript">
	function open1(title,url){
		if ($('#tabs').tabs('exists',title)){
			$('#tabs').tabs('select', title);
		} else {
			if(url.indexOf('http')>=0) {
				window.open(url);
			}else {
				$('#tabs').tabs('add',{
					title:title,
					content:'<iframe scrolling="auto" frameborder="0" src="'+getUrl(url)+'" style="width:100%;height:100%;"></iframe>',
					closable:true
				});
			}
		}
	}

	/*
	 * 以下为兼容poss老脚本
	 */
	var index = 0;

	/*
	 * MA调用
	 */
	function addMenu(title,url) {
		if ($('#tabs').tabs('exists',title)){
			index++;
			title +=index;
		} 
		$('#tabs').tabs('add',{
			title:title,
			content:'<iframe scrolling="auto" frameborder="0" src="'+getUrl(url)+'" style="width:100%;height:100%;"></iframe>',
			closable:true
		});
	}
	
	/*
	 * FO调用
	 */
	function addTabMenu(title,url) {
		addMenu(title,url);
	}
	function closePage(url) {
		var tab = $('#tabs').tabs('getSelected');
		var title = tab.panel('options').title;
		$('#tabs').tabs('close',title);
	}

	function getUrl(url){
		var appName = getContextPath();
		if(url.indexOf(appName)==0){
			return url;
		}else if(url.indexOf("/")==0){
			url = appName + url;
		}
		return url;
	}

	function getContextPath() {
	    return "${ctx}";
	}
	
</script>
<!-- DDR 2012-6-3  -->
<!-- 更新原来的直接write html的原始模式，利用ztree模式 jquery  -->
<div class="easyui-accordion panel-title"  style="width:200%;" animate="false" collapsed="true" border="false" selected="false">
	<ul id="zTreeMenu" class="tree panel-title" style="width:250px; height:100%;overflow:auto;padding:5px;" ></ul>
</div>
<script type="text/javascript">

<!--

var node_ = {};
var simpleNodes = []; //菜单功能数组

var zTreeMenu;
var settingMenu ={
			isSimpleData : true,
			rootPID : -1,
			treeNodeKey : "id",
			treeNodeParentKey : "pId",
			callback : {
				click: openCurUrl
			}
	};

$(function(){
	//simpleNodes.push( { id:0, pId:-1, name:"poss", ename:"ent"});
	<c:forEach items="${menuList}" var="menu">
		<c:if test="${menu.authorityFlag == null || menu.authorityFlag == 0}">
			node_ = { id:${menu.resKy}, pId:${menu.parent}, name:"${menu.name}",menuCode:"${menu.code}", menuUrl:"${menu.url}",position:"${menu.position}",authorityFlag:"${menu.authorityFlag}"};
			simpleNodes.push(node_);
		</c:if>
	</c:forEach>
	//加载树
	reloadTree();
});

//加载树的方法
function reloadTree() {
	zTreeMenu = $("#zTreeMenu").zTree(settingMenu,simpleNodes);
	//zTreeMenu.expandAll(true);
	zTreeMenu.expandNode(zTreeMenu.getNodeByParam("name","综合管理", null), true, false, true);
}

/**
 * 打开当前
 */
function openCurUrl(event, treeId, treeNode) {
	if(treeNode.menuUrl.length == 0){
		//展开
		zTreeMenu.expandNode(treeNode, true, false);
	}else if(treeNode.level>1 || treeNode.menuUrl.length > 0){
		//打开url
		open1(treeNode.name,treeNode.menuUrl);
	}
	return false;
}

//-->

</script>
