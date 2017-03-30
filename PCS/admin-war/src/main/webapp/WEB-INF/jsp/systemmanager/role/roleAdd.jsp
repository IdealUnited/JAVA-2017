<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>新增角色</title>
	<link href="${ctx}/images/css/style.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" href="${ctx}/css/main.css">
<link rel="stylesheet" href="${ctx}/css/ztree/zTreeStyle.css" />
<link rel="stylesheet" href="${ctx}/css/ztree/demoStyle/demo.css">
<script src="${ctx}/js/jquery/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery/plugins/ztree/jquery.ztree-2.6.min.js" type="text/javascript"></script>

<script language="javascript">

/**
 * DDR 2012-05-08 配置产品功能
 * 修改成树型结果，ajax
 */
var node_ = {};
var simpleNodes = []; //菜单功能数组
var pdSipNodes = []; //产品菜单数组

var checkIds = "${checkIdsStr}"+",";

var zTree1, zTree2;
var setting1 ={
		 checkable : true,
			isSimpleData : true,
			  rootPID : -1,
			  treeNodeKey : "id",
			 treeNodeParentKey : "pId",
		editable: false,
		edit_renameBtn:false,
		edit_removeBtn:false,
		dragCopy: true,
		dragMove: false,
		callback: {
			click:	null
		
		}
	};
	
	var setting2 ={
			isSimpleData : true,
			  rootPID : -1,
			  treeNodeKey : "id",
			 treeNodeParentKey : "pId",
		editable: false
		
		
	};

	//加载树的方法
	function reloadTree() {
		zTree1 = $("#treeDemo").zTree(setting1,simpleNodes);
		zTree2 = $("#treeDemo2").zTree(setting2,pdSipNodes);
		zTree2.expandAll(true);
	}
	
	//保存菜单到后台,并刷新右菜单  
	function saveRole(){
		var roleKy = $("#roleKy").val();
		if(roleKy =='') {
			$.post('roleAdmin.do?method=isExsit',$("#roleFormBean").serialize(),function(data){
				if(data==1) {
					alert('角色名称已经存在');
				}else {
					var nodes = zTree1.getCheckedNodes(); 
					var	data = packageNodes(nodes)+$("#roleFormBean").serialize();
					if(confirm("确定提交当前权限角色对应的菜单资源吗?")){
						$.post("roleAdmin.do?method=save",data,function cbk(msg){
								if(msg == "S"){
									alert("操作成功！");
								}else{
									alert(msg);
								}
							});
					}	
				}
			});
		}else {
			var nodes = zTree1.getCheckedNodes(); 
			var	data = packageNodes(nodes)+$("#roleFormBean").serialize();
			if(confirm("确定提交当前权限角色对应的菜单资源吗?")){
				$.post("roleAdmin.do?method=save",data,function cbk(msg){
						if(msg == "S"){
							alert("操作成功！");
						}else{
							alert(msg);
						}
					});
			}	
		}
		
		
	}

	//包装提交参数
	function packageNodes(nodes){
		var str = "";
		for(var i=0;i<nodes.length;i++){
				str+="menuId="+nodes[i].id+"&";
		}
		return str;
	}
	
$(function(){

	
	//simpleNodes.push( { id:0, pId:-1, name:"菜单", ename:"ent",checked:pdSipNodes.length>1});
	<c:forEach items="${treeMenuList}" var="menu">
		 node_ = { id:${menu.resKy}, pId:${menu.parent}, name:"${menu.name}",checked: (checkIds.indexOf(","+${menu.resKy}+",")>=0) };
		 simpleNodes.push(node_);	
	</c:forEach>
	
	//加载树
	reloadTree();
	
});


</script>
	
</head>


<body>
<div id="inputContent" style="margin: 0px auto;">

<form id="roleFormBean" name="roleFormBean" method="post" action="roleAdmin.do?method=save" style="margin: 0px auto;width: 50%">
	<input type="hidden" name="roleKy"  id="roleKy" value="${role.roleKy}" />

<!--<table width="80%" align="center" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td></td>
		<td>
			<span class="mframe-t-text">
			<s:if test="roleKy == null">创建</s:if><s:else>修改</s:else>角色
		</span>
		</td>
		<td></td>
	</tr>
</table>

-->

<table width="25%" border="0" cellspacing="0" cellpadding="0" align="center" height="21" >
	<tr>
		<td height="1" bgcolor="#000000"></td>
	</tr>
	<tr>
		<td height="18">
		<div align="center"><font class="titletext"><c:if test="${role == null}">创建</c:if><c:if test="${role != null}">修改</c:if>角色</font></div>
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#000000"></td>
	</tr>
</table>

<table width="80%" align="center" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td></td>
		<td>
			<table align="center" style="background: #E0ECFF">
			
				<tr>
					<td align="right" >角色名:</td>
					<td><input type="text" maxlength="22" id="roleName" name="roleName" size="22" value="${role.roleName}" class="required"/></td>
				</tr>
				<tr>
					<td align="right">角色说明:</td>
					<td><textArea id="roleRemarks" maxlength="200" name="roleRemarks">${role.roleRemarks}</textArea></td>
				</tr>
				<tr>
					<td align="right">授权:</td>
					<td width=340px  height="500" align=left valign=top style="width:300px; overflow:auto;color:#333333">
					
				<ul id="treeDemo" class="tree" style="width:250px; height:100%;overflow:auto;border: 1px solid #ccc;background: #E0ECFF"></ul>
				</tr>
				<tr>
					<td colspan="2">
						<table width="100%">
							<tr>
								<td align="right">
									<input type="button" value="提交" onclick="saveRole();"/>&nbsp;  
									&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
								<td align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" value="取消" onclick="history.back()"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
		<td></td>
	</tr>
</table>

</form>
</div>

</body>
</html>