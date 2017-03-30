<#macro bill imgList>
	<#-- see:js/v2/banner.js -->
	<div class="slide">
		<div class="J_slide_clip">
			<ul class="J_slide_list">
			<#list imgList as img>
			<#if img_index lt 4>
				<li class="J_slide_item">
				<#-- if img.parameters?has_content && img.parameters != "#">
				<a href="${img.parameters?default("javascript:void(0);")}" target="${img.code?default("_self")}"><img width="710" height="185" alt="" title="" src="${img.imgpath?default("")}" /></a>
				<#else>
				<img width="710" height="185" alt="" title="" src="${img.imgpath?default("")}" />
				</#if -->
				<img width="710" height="185" alt="" title="" src="${img.imgpath?default("")}" />
				</li>
			</#if>
			</#list>
			</ul>
		</div>
		<ul class="J_slide_trigger clearfix">
			<#list imgList as img>
			<#if img_index lt 4>
			<li <#if img_index=0>class="cur"</#if> >${img.title?default("")}</li>
			</#if>
			</#list>
		</ul>
	</div>
</#macro> 