<#macro cfca>
	<#assign cafaTemplate = "com.ttf.app.common.template.CfcaTemplate"?new()>
	<#assign result = cafaTemplate()>
	<#--如果使用安全控件-->
	<#if Session["userSession"]?exists >
		<#if result.ie == 1>
			<#include "/common/include/cfcaControl.html">
		<#else>
			<#include "/common/include/cfcaControlFF.html">
		</#if>
	</#if>
</#macro>