<#function hasProduct productCode> 
<#assign hasProductTemplate = "com.ttf.app.common.template.HasProductTemplate"?new()>
<#assign flag=hasProductTemplate(productCode)/> 
 <#return flag>
</#function>   