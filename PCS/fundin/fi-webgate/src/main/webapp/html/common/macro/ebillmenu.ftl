
<#macro ebillmenu channel="0">  
<div id="sidermenu">
	 <#assign menuTemplate = "com.ttf.controller.app.common.EbillMenuTemplate"?new()>
     <#assign selfPmList=menuTemplate()>
<#if  selfPmList?exists && selfPmList?has_content>
	<ul class="steward_nav">
	     <#list  selfPmList as pm>
	     <#assign  rootIndex=pm_index+1>
	     <#assign  channelIndex=channel?index_of("_")>
	     <#if channelIndex gte 0>
	 		<#assign  channelIndex=channel[0..channelIndex]>
	     </#if>
			<li><a <#if channel="1_"+rootIndex>class="select"</#if> href="<@contextPath/>${pm.menuUrl}"><i class="${pm.description}" title="${pm.menuName}"></i>${pm.menuName}</a></li>
		 </#list>
	</ul>
</#if>
</div>
</#macro> 			