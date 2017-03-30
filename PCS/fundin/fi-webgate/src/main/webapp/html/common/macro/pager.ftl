  <#macro pagination pu>
  
       <#--声明一个函数，根据传入的分页对象得到分页信息   -->
       <#assign pagerTemplate = "com.ttf.app.common.template.PagerTemplate"?new()>
       <#assign new_uri=pagerTemplate()>

       <#--得到当前的URI和请求参数,得到当前的页码
       <#if request.queryString?exists>  
           <#assign new_uri=request.requestURI+"?"+request.queryString+"&pager_offset=">  
           
       <#else>  
           <#assign new_uri=request.requestURI+"?pager_offset=">  
            
       </#if>  --> 
       
     <#--  <#if pu.totalCount?has_content && pu.totalCount gt 0> -->
     <#if pu.totalCount?exists && pu.totalCount gt 0>
     
        <div class="page">
       <#assign pageIndex=pu.curPageNo>
       <#assign pageCount=pu.getmaxPageNo()>
       <#if (pageIndex>pageCount)>  
          <#assign pageIndex=pageCount>  
        </#if>
        <#if (pageIndex lt 0)>  
          <#assign pageIndex=1>  
        </#if>    
      <#if (pageIndex>1)>  
         <a href="${new_uri+1}"  title="首页" >首页</a>
         <a href="${new_uri+(pageIndex-1)}">上一页</a> 
      <#else>
     	<span title="已经是第一页" class="off" title="首页">首页</span>
     	<span title="已经是第一页" class="off" title="上一页">上一页</span> 
      </#if>  
      <#--如果前面页数过多,显示"..."-->  
      <#if (pageIndex>5)>  
          <#assign prevPages=pageIndex-9>  
          <#if prevPages lt 1>  
              <#assign prevPages=1>  
          </#if>  
          <#assign start=pageIndex-4>  
          <a href="${new_uri+prevPages}" title="向前5页">...</a>  
      <#else>  
          <#assign start=1>  
       </#if>  

      <#-- 显示当前页附近的页-->  
      <#assign end=pageIndex+4>  
      <#if (end>pageCount)>  
          <#assign end=pageCount>  
      </#if>  
      <#list start..end as index>  
          <#if pageIndex==index>  
               <a href="javascript:void(0);" class="cur">${index}</a> 
          <#else>  
            	<a href="${new_uri+index}">${index}</a> 
          </#if>  
      </#list>  
      <#--如果后面页数过多,显示"...":-->  
      <#if (end lt pageCount)>  
          <#assign endend=end+5>  
          <#if (end>pageCount)>  
              <#assign end=pageCount>  
          </#if>  
          <a href="${new_uri+end}" title="向后5页">...</a>  
      </#if>  
      <#-- 显示"下一页":--> 
   
	<#if (pageIndex lt pageCount)>  
	   	  <a href="${new_uri+(pageIndex+1)}" title="下一页">下一页</a>  
      	  <a href="${new_uri+pageCount}" title="尾页">尾页</a>
      <#else>
         <span title="已经是最后一页"  class="off" title="下一页">下一页</span>  
         <span title="已经是最后一页"  class="off" title="尾页">尾页</span>
       
      </#if>  
       

</div>

  
   </#if>
     
         
  
     <#--
        <#if (pageIndex>pageCount)>  
           <#assign pageIndex=pageCount>  
       </#if>  
     
      <form action="${new_uri}"  class="fl" type="post" onsubmit="javascript:var _pagego=$(this).children('input:first').val();var _maxpage=$(this).children('input').eq(1).val();if(parseInt(_pagego)>0){if(_pagego> parseInt(_maxpage)){alert('输入页码不能超过最大页码'+_maxpage);}else{ document.location.href=this.action+_pagego;} }else{alert('输入页码必须是大于0的正整数');}return false; " >
   
       <#if (pageIndex>1)>  
          <#-- <a href="${new_uri+1}" title="首页">&lt;&lt;</a>  
             <a href="${new_uri+(pageIndex-1)}" class="pre">上一页</a>  
             
       </#if>  
          
       <#--如果前面页数过多,显示"..." 
       <#if (pageIndex>5)>  
           <#assign prevPages=pageIndex-9>  
           <#if prevPages lt 1>  
               <#assign prevPages=1>  
           </#if>  
           <#assign start=pageIndex-4>  
           <a href="${new_uri+prevPages}" title="向前5页">...</a>  
       <#else>  
           <#assign start=1>  
        </#if>  
       <#-- 显示当前页附近的页
       <#assign end=pageIndex+4>  
       <#if (end>pageCount)>  
           <#assign end=pageCount>
             
       </#if>  
       
       <#list start..end as index>  
          <#if pageIndex==index >
                <a href="javascript:void(0);" class="current">${index}</a>                
           <#else>  
               <a href="${new_uri+index}">${index}</a>  
           </#if>  
       </#list>  
       
       <#--如果后面页数过多,显示"...":
       <#if (end lt pageCount)>  
           <#assign endend=end+5>  
           <#if (end>pageCount)>  
               <#assign end=pageCount>  
           </#if>  
           <a href="${new_uri+end}" title="向后5页">...</a>  
       </#if>  
       <#-- 显示"下一页": 
       <#if (pageIndex lt pageCount)>  
            <a href="${new_uri+(pageIndex+1)}" class="next">下一页</a>  
             <#--
           <a href="${new_uri+pageCount}" title="末页">&gt;&gt;</a>  
           
       </#if>  
     
    
     到第<input name="pager_offset" type="text" class="page_go" value="${pageIndex}"/> 页 
        <input name="pager_pageCount" type="hidden" class="page_go" value="${pageCount}"/>
         <input name="page_sub" type="submit" class="go" value="确定" />
     </form>
       
     -->
     
     
  </#macro>
