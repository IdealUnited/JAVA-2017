<#-- 参数permissions 定义
     lock(0,"锁定"),
     unmaxtrix(1,"口令卡用户非口令卡登录"),
     normal(2,"普通登录"),
     maxtrix(3,"口令卡登录"),
     certificate(4,"数字证书"),
     u_shield(5,"u盾");
-->

<#function hasFeature permissions> 
<#assign securitylvl=0>
<#assign flag=false/> 
   <#if Session["userSession"]?exists>
        <#assign t = Session["userSession"].securityLvl/>
        <#list permissions?split(",") as p>
               <#if p?trim='unmaxtrix'>
                  <#assign securitylvl=1>
                <#elseif p?trim='normal'>
                  <#assign securitylvl=2>
                <#elseif p?trim='maxtrix'>
                  <#assign securitylvl=3>
                <#else>
                  <#assign securitylvl=0>
                </#if>
              <#if t=securitylvl>
              <#assign flag=true/> 
              <#break>
          </#if>
        </#list>
    </#if>

  <#return flag>
</#function>   