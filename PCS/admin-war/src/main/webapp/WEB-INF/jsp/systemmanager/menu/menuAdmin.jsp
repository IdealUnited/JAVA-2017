<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/ztree/zTreeStyle.css" />
<link rel="stylesheet" href="css/ztree/demoStyle/demo.css">
<script src="js/jquery//js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="js/jquery/plugins/ztree/jquery.ztree-2.6.min.js" type="text/javascript"></script>
<style type="text/css">
.border_top_right4, .border_top_right4 input{
	font-size: 15px;
}
</style>

<script language="javascript">

/**
 * DDR 2012-05-08 配置产品功能
 * 修改成树型结果，ajax
 */
var node_ = {};
var simpleNodes = []; //菜单功能数组

var curNode = null;
var zTree1;
var setting1 ={
			isSimpleData : true,
			rootPID : -1,
			treeNodeKey : "id",
			treeNodeParentKey : "pId",
			editable : true,
			edit_removeBtn : true,
			edit_renameBtn : false,
			callback : {
			   click: editMenu,
			   beforeRemove: removeCurNode
			}
	};
	
	//加载树的方法
	function reloadTree() {
		zTree1 = $("#treeDemo").zTree(setting1,simpleNodes);
		zTree1.expandAll(true);
	}
	
	var level = 0;
	function editMenu(event, treeId, treeNode) {
		level = treeNode.level;
		curNode = treeNode;
		if(treeNode.parentNode){
			$("#parent_name").val(treeNode.parentNode.name);
		}else{
			$("#parent_name").val('菜单配置');
		}
		
		$("#parent").val(treeNode.pId );
		$("#code").val(treeNode.menuCode );
		$("#url").val(treeNode.menuUrl );
		$("#position").val(treeNode.position);
		$("#name").val(treeNode.name );
		$("#resKy").val(treeNode.id);
		if(null != treeNode.authorityFlag && '' != treeNode.authorityFlag){
			if(parseInt(treeNode.authorityFlag)==1){
				$('input:radio:last').attr('checked','checked');
			}else{
				$('input:radio:first').attr('checked','checked');
			}
		}else{
			$('input:radio:first').attr('checked','checked');
		}
		return false;
	}
	
	function removeCurNode(treeId,treeNode){
		if(confirm("删除"+treeNode.name+"吗？")){
			$.post("${ctx}/menuAdmin.do",{method:"delete",id:treeNode.id},function (msg){
				if(msg=="S"){
					zTree1.removeNode(treeNode);		
					alert("删除成功");
					curNode = treeNode.parentNode;
					$("#code").val("");
					$("#url").val("" );
					$("#position").val("" );
					$("#name").val("");
					$('input:radio:first').attr('checked','checked');
				}
				else{
					alert(msg);
				}
			});
		}
		return false;
	}
	
$(function(){
	simpleNodes.push( { id:0, pId:-1, name:"菜单配置", ename:"ent"});
	<c:forEach items="${treeMenuList}" var="menu">
 node_ = { id:${menu.resKy}, pId:${menu.parent}, name:"${menu.name}",menuCode:"${menu.code}", menuUrl:"${menu.url}",position:"${menu.position}",authorityFlag:"${menu.authorityFlag}"};
	 simpleNodes.push(node_); 	
	</c:forEach>
	//加载树
	reloadTree();
});

function formValidte(){
	var code = $("#code").val();
	var name = $("#name").val();
	var position = $("#position").val();
	if(code.length == 0 ){
		alert("菜单编号不能为空");
		return false;
	}
	if(name.length == 0 ){
		alert("菜单名称不能为空");
		return false;
	}
	if(position.length == 0 ){
		alert("同级菜单优先级不能为空");
		return false;
	}	
	return true
}

function formSubmit(){
	if(!formValidte()){
		return false;
	}
	if(level > 2) {
		$("#status").val("0");
	}
	var paras  =$("#menuFormBean").serialize();
	var isSave = true;
	if($("#resKy").val().length==0){
		paras+="&method=save";
		isSave = true;
	}else{
		paras+="&method=update";
		isSave = false;
	}
	$.post("${ctx}/menuAdmin.do",paras,function (msg){
		if(msg=="S"||parseInt(msg)>=0){
			
			if(isSave){
				alert("新增成功！");
				var nNo = { id:msg, pId:curNode.id, name:$("#name").val(),menuCode:$("#code").val(), menuUrl:$("#url").val(),position:$("#position").val(),authorityFlag:$("input[name='authorityFlag']:checked").val()};
				var nNos = [nNo];
				var nodes = zTree1.addNodes(curNode, nNos);
				$("#code").val("");
				$("#url").val("" );
				$("#position").val("" );
				$("#name").val("");
				$("#rdo0").attr("checked","checked");
				return false;
			}else{
				alert("更新成功！");
				curNode.name=$("#name").val();
				curNode.menuUrl=$("#url").val();
				curNode.menuCode=$("#code").val();
				curNode.position=$("#position").val();
				curNode.authorityFlag=$("input[name='authorityFlag']:checked").val();
				zTree1.updateNode(curNode, true);
			}
		}
		else{
			alert(msg);
		}
	});
	return false;
}

function editNewSon(){
	$("#resKy").val("");
	$("#parent_name").val(curNode.name );
	$("#parent").val(curNode.id );
	$("#code").val("");
	$("#url").val("" );
	$("#position").val("");
	$("#name").val("");
}

</script>

</head>
<body>
<table class="border_all2" width="60%" border="0" cellspacing="0" cellpadding="1" align="center" style="height:100%">
	<tr align="left" class=trForContent1>
		<td width="643" height="20px" valign="middle" nowrap class="border_top_right4">&nbsp;&nbsp;&nbsp;&nbsp;菜单配置
		<span id="opTip" style="color: red"></span></td>
	</tr>
	<tr valign="top" class="trForContent1">
		<td align="center" nowrap class="border_top_right4">
		<table width="617" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan="3">&nbsp;</td>
			</tr>
			<tr align="center">
				<td align="left">菜单配置根 <input type="button" style="font-size:11px " value="全部展开 " onclick="zTree1.expandAll(true);" />
									  <input type="button"  style="font-size:11px " value="全部闭合" onclick="zTree1.expandAll(false);" />	
					</td>
				<td>&nbsp;</td>
				<td align="left">编辑菜单区<div style="float: right"><input type="button"  style="font-size:11px " name="addBtn" value="添加子菜单"  onclick="editNewSon()" > </div>
									  </td>
			</tr>
			<tr align="center">

				<td width=340px  height="500" align=center valign=top style="width:300px; overflow:auto;">
					
				<ul id="treeDemo" class="tree" style="width:250px; height:100%;overflow:auto;border: 1px solid #ccc;"></ul>
				</td>
				<td width="121">
				</td>
				<td width=340px  height="500" align=center valign=top style="width:300px; overflow:auto;">
					<form method="post" name="menuFormBean" id="menuFormBean" >
					<table class="border_all2 inputTable" width="380" border="0" cellspacing="0" cellpadding="3" align="center" >
						<tr class="trbgcolor10">
							<th class="border_top_right4 must" style="width:120px;"  >父菜单：</td>
							<td class="border_top_right4">
								<input type="hidden" name="status" id="status" value="1" />
								<input type="text" name="parent_name" id="parent_name" title="必填字段" readonly="readonly" style="color: gray;background-color: #ccc">
							</td>
						</tr>
						<tr class="trbgcolor10">
							<th class="border_top_right4 must">菜单编号：</td>
							<td class="border_top_right4">
								<input type="text" name="code" title="必填字段"  id="code" />
							</td>
						</tr>
						<tr class="trbgcolor10">
							<th class="border_top_right4 must">菜单名称：</td>
							<td class="border_top_right4"><input type="text" name="name" title="必填字段"  id="name" /></td>
						</tr>
						<tr class="trbgcolor10">
							<th class="border_top_right4">URL：</td>
							<td class="border_top_right4"><input type="text" id="url"  name="url"/></td>
						</tr>
						<tr class="trbgcolor10">
							<th class="border_top_right4">同级菜单优先级：</td>
							<td class="border_top_right4"><input type="text" id="position" class="validate-number" title="必填字段,必须为数字" name="position"></td>
						</tr>
						<tr class="trbgcolor10">
							<th class="border_top_right4">是否为权限菜单：</td>
							<td class="border_top_right4">
								否<input type="radio" id="rdo0" name="authorityFlag" value="0" checked="checked">
								是<input type="radio" id="rdo1" name="authorityFlag" value="1">
							</td>
						</tr>
						<tr class="trbgcolor10">
							<th class="border_top_right4" colspan="2" style="text-align: center">
								<input type="button" name="sbBtn" value="保存"  onclick="formSubmit()" > 
						</th>
						</tr>
					</table>
						<input type="hidden" name="resKy" id="resKy">
						<input type="hidden" name="parent" id="parent">
					</form>
				</td>
			</tr>
			<tr align="center">
				<td colspan="3">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>