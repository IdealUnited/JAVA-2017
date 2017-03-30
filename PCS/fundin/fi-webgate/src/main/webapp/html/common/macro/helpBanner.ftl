<#macro helpbanner toptit="账户充值" toptit2="" glide=false>  
	<div class="crumbs">当前位置：<a href="${rc.contextPath}/index.htm">安捷支付</a> &gt;<span>${toptit}</span>
	<#if toptit2?has_content && toptit2!="">
	&gt; <span>${toptit2}</span>
	</#if>
	</div>

</#macro>