<#macro page page>

  
<#--声明一个函数，根据传入的分页对象得到分页信息   -->
<#assign pagerTemplate = "com.ttf.app.common.template.PagerTemplate"?new()>
<#assign new_uri=pagerTemplate()>

	<#if page.totalCount?exists && page.totalCount gt 0>
	
	<div class="page">
	<#assign pageIndex=page.curPageNo>
	<#assign pageCount=page.getmaxPageNo()>
	
	<#if (pageIndex>pageCount)>
		<#assign pageIndex=pageCount>
	</#if>
	
	<#if (pageIndex lt 0)>
		<#assign pageIndex=1>
	</#if>
	 
	<#if (pageIndex>1)>
		<a href="javascript:page_query(1)"  title="首页" >首页</a>
		<a href="javascript:page_query(${pageIndex-1})">上一页</a>
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
		<a href="javascript:page_query(${prevPages})" title="向前5页">...</a>
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
			<a href="javascript:page_query(${index})">${index}</a>
		</#if>
	</#list>

	<#--如果后面页数过多,显示"...":-->
	<#if (end lt pageCount)>
		<#assign endend=end+5>
		<#if (end>pageCount)>
			<#assign end=pageCount>
		</#if>
		<a href="javascript:page_query(${end})" title="向后5页">...</a>
	</#if>

	<#-- 显示"下一页":-->
	<#if (pageIndex lt pageCount)>
		<a href="javascript:page_query(${pageIndex+1})" title="下一页">下一页</a>
		<a href="javascript:page_query(${pageCount})" title="尾页">尾页</a>
	<#else>
		<span title="已经是最后一页"  class="off" title="下一页">下一页</span>
		<span title="已经是最后一页"  class="off" title="尾页">尾页</span>
	</#if>
       
	</div>
	</#if>
	
</#macro>