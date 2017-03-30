
<#macro matrixcard pos="" imgId="0" type="" > 
	<#if pos?has_content && pos!="">
	   <img class="psw_card" alt="安捷支付令卡" src="${rc.contextPath}/matrixCardView.htm?method=tagSupport&pos=${pos}&type=${type}" width="350" height="275" />
	<#else>
	   <img class="psw_card" alt=安捷支付支付口令卡" src="${rc.contextPath}/matrixCardView.htm?method=tagSupport&imgId=${imgId}&type=${type}" width="350" height="275" />
	</#if>
</#macro> 	