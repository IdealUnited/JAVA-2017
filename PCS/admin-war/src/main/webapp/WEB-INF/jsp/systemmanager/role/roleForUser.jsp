<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!--<script type="text/javascript" src="${ctx}/js/prototype/prototype.js"></script>-->

<head>
	<%--引入用于格式化页面的CSS文件--%>
	<link rel="stylesheet" href="${ctx}/css/main.css">
	<style type="text/css">
		.groupSelectArea{
			height: 445px;
			width:300px;
		} 
	</style>
	<script type="text/javascript">
		//搜索用户
		function processSearch(){
			var userSearchForm = $("#userSearchForm");
			userSearchForm.submit();
		}
		//分配角色
		function processRoleForUser(id,userName){
			location.href = "roleJoinUserAction.do?userId=" + id;
		}
		//id的全选或全部取消
		function selectAll(obj) {
			
			
			if($("#checkAll").attr("checked")){
				$("input[name='choose']").each(function(){
					this.checked = true;
				});
			} else {
				$("input[name='choose']").each(function() {
					this.checked = false;
				});
			}

			if(obj.checked == true){
				alert("查询选中的");
			}else{
				alert("查询未选中的");
			}
		}

		$(function(){
			 $(".border_all2 tbody tr").mouseover(function(){$(this).find("td").css({"background":"#cec"});} )
			 .mouseout(function(){$(this).find("td").css({"background":"#fff"});} ) ;    
		     $("#add").click(function(){
		              if($("#fb_list option:selected").length>0)
		              {
		                       $("#fb_list option:selected").each(function(){
		                            $("#select_list").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option");
		                            $(this).remove();  
		                       }) 
		              }
		              else
		              {
		                       alert("请选择要添加的角色 ！");
		              }
		       })

		      
		})


		$(function(){ 
             $("#delete").click(function(){
                      if($("#select_list option:selected").length>0)
                      {
                               $("#select_list option:selected").each(function(){
                                          $("#fb_list").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option");
                                           $(this).remove();  
                               }) 
                      }
                      else
                      {
                               alert("请选择要删除的角色！");
                      } 
           })
		})
		
		function selectThis(obj){
			var value = obj.value;
			
			$.post("roleForUser.do?method=optional&id=" + value,
				  function(data){
						$("#fb_list option").remove();
				 		$("#fb_list").append(data);
				   }
			);
			
			$.post("roleForUser.do?method=select&id=" + value,
				  function(data){
				 		$("#select_list").append(data);
				   }
			); 

			
			
			
		}
	</script>

</head>

<body>

	<form action="roleForUser.do" id="userSearchForm" method="post">
		<table align="center">
			<tr>
				<td>用户ID：</td>
				<td><input type="text" name="userId"/></td>
				<td>姓名：</td>
				<td><input type="text" name="userName"/></td>
				<td>状态：</td>
				<td>
					<select name="status">
						<option value="1">激活</option>
						<option value="0">禁用</option>
						<option value="2">全部</option>
					</select>
				</td>
				<td> 
					<input type="button" value="搜索" onclick="processSearch();">
				</td>
			</tr>
		</table>
	</form>

	<br>
	<br>
	
	<table class="border_all2" width="80%" border="0" cellspacing="0" cellpadding="1" align="center">
		<tr class="trbgcolorForTitle" align="center" valign="middle">
			<td class="border_right4" width="8%" nowrap align="left">
				<input type="checkbox" name="checkAll" id="checkAll" onclick="selectAll(this);" />全选/反选
			</td>
			<td class="border_right4" width="8%" nowrap>
				<a class="s03"><font color="#FFFFFF">员工姓名</font>
				</a>
			</td>
			<td class="border_right4" width="8%" nowrap>
				<a class="s03"><font color="#FFFFFF">用户名</font>
				</a>
			</td>
			<td class="border_right4" width="5%" nowrap>
				<a class="s03"><font color="#FFFFFF">部门</font>
				</a>
			</td>
			<td class="border_right4" width="5%" nowrap>
				<a class="s03"><font color="#FFFFFF">职位</font>
				</a>
			</td>
			<td class="border_right4" width="5%" nowrap>
				<a class="s03"><font color="#FFFFFF">状态</font>
				</a>
			</td>
			<td class="border_right4" width="15%" nowrap>
				<a class="s03"><font color="#FFFFFF">最后登录时间</font>
				</a>
			</td>
			<td class="border_right4" width="5%" nowrap colspan="2">
				<a class="s03"><font color="#FFFFFF">操作</font>
				</a>
			</td>
		</tr>

		<tbody>
		<c:forEach items="${users}" var="use">
			<tr	class= "trForContent1">
				<td class="border_top_right4" align="left"><input type="checkbox" value="${use.userKy}" name="choose" id="choose" onclick="selectThis(this)"/></td>
				<td class="border_top_right4" align="left"><c:out value="${use.userName}" escapeXml="true"></td>
				<td class="border_top_right4" align="left"><c:out value="${use.userCode}" escapeXml="true"></td>
				<td class="border_top_right4" align="left"><c:out value="${use.userOrgName}" escapeXml="true"></td>
				<td class="border_top_right4" align="left"><c:out value="${use.userDutyName}" escapeXml="true"></td>
				<td class="border_top_right4" align="left"><c:out value="${use.userStatus}" escapeXml="true"></td>
				<td class="border_top_right4" align="left"><c:out value="${use.lastLoginTime}" escapeXml="true"></td>
				<td><a href="javascript:processRoleForUser('${use.userKy}')">分配角色</a></td>
			</tr>
		</c:forEach>
			</tbody>
	</table>

	<br>
	<br>

	<table class="border_all4" width="75%" border="0" cellspacing="0" cellpadding="0" align="center" id="buttonDisplay">
		<tr class="trbgcolor6" align="center">
			<td>
				<a class="s03" href="">首页
				</a>&nbsp;
				<a class="s03" href="">上一页
				</a>&nbsp;
				<a class="s03" href="">下一页
				</a>&nbsp;
				<a class="s03" href="">尾页
			</td>
		</tr>
	</table>
	
	<table width="75%" cellpadding="0" align="center" class="listshow" border="1" cellspacing="0">
	 <tr>
	     <td colspan="4" align="center">分配角色</td>
	 </tr>
	 <tr>
	     <td colspan="2" align="center">所有角色</td>
	     <td colspan="2" align="center">已分配角色</td>
	 </tr>
	 <tr>
		  <td class="black" width="30%" align="center" height="150">
	           <select id="fb_list" multiple="multiple"  style="text-align:center;width:300px;height:150px;">
	           		<c:forEach items="${roles}" var="role">
						<option value="${role.roleKy}"><c:out value="${role.roleName}" escapeXml="true"></option>
					</c:forEach>
	          </select>  
		  </td>
	      <td align="center" width="5%">
	       	  <input type="button" id="add" value="添加>>" />
	          <br/>
	          <br/>
	          <input type="button" id="delete" value="<<删除" />
	      </td>
	      <td class="black" width="30%" align="center">
		       <select id="select_list" multiple="multiple" name="" style=" text-align:center;width:300px;height:150px;">
		       </select>
	      </td>
	 </tr> 
	</table>
	
	<table align="center">
		<tr>
			<td colspan="4">
				<input type="button" name="submitBtn" value="提交" onclick="processSelected($('selectAreaUser'));">
				<input type="button" value="返回" onclick="history.back();"/>
			</td>
		</tr>
	</table>
</body>

