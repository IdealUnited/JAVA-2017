<#function hasMenu codeDefault="" codeParam=""> 
<#if codeParam?exists && codeParam?has_content>
<#assign codeArray=codeParam?split(",")> 
<#list codeArray as c>
<#if codeDefault==c><#return true></#if>
</#list>
</#if>
<#return false>
</#function>   