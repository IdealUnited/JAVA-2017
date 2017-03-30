<#macro reClass className="pop_war">
<#assign classes="pop_war">
<#if className?has_content>
	<#if className?index_of("succeed") gte 0>
		<#assign classes="pop_s">
	<#elseif className?index_of("warning") gte 0>
		<#assign classes="pop_war">
	<#else>
		<#assign classes="pop_fail">	 
	</#if>
							
</#if>
${classes} 
</#macro>