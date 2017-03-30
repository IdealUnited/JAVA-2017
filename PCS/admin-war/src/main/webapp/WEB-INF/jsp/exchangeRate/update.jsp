<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<table width="25%" border="0" cellspacing="0" cellpadding="0"
	align="center" height="21" style="">
	<tr>
		<td height="1" bgcolor="#000000"></td>
	</tr>
	<tr>
		<td height="18">
		<div align="center"><font class="titletext">汇率修改</font></div>
		</td>
	</tr>
	<tr>
		<td height="1" bgcolor="#000000"></td>
	</tr>
</table>
<form action="${ctx}/exchangeRate.do?method=update" method="post" id="mainfromId">
	<table class="border_all2" width="90%" border="0" cellspacing="0"
		cellpadding="1" align="center">	
	    <tr class="trForContent1">
	      <td align="right" class="border_top_right4" >币种：</td>
	      <td class="border_top_right4">
	     	<input type="text" id="currency" name="currency" value="${rate.currency }" readonly="readonly">
	      </td>
	      <td align="right" class="border_top_right4" >目标币种：</td>
	      <td class="border_top_right4">
	     	<select name="targetCurrency" id="targetCurrency">
	      		<option value="CNY" selected>人民币</option>
	      	</select>
	      </td>
	      <td align="right" class="border_top_right4" >汇率：</td>
	      <td class="border_top_right4">
	     	<input type="text" id="exchangeRate" name="exchangeRate" value="${rate.exchangeRate }">
	      </td>
	     </tr>
	    <tr class="trForContent1">
	      <td align="center" class="border_top_right4" colspan="6">
	      <input type="button"  value="修  改" class="button2" onclick="sub()">
	      <input type="button"  value="返  回" class="button2" onclick="toIndex();">
	      </td>
	    </tr>
   </table>
   <input type="hidden" name="id" value="${rate.id }"/>
</form>

<script type="text/javascript">

	function toIndex(){
		window.location.href="${ctx}/exchangeRate.do";
	}
	
	function sub(){
		var pars = $("#mainfromId").serialize();
		$.ajax({
			type: "POST",
			url: "${ctx}/exchangeRate.do?method=update",
			data: pars,
			success: function(result) {
				if(result==1){
					alert('修改成功！');
				}else{
					alert('修改失败！');
				}
			}
		});
		
	}

  </script>