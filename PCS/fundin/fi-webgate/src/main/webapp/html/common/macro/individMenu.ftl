
<#macro individmenu channel=0>  
<style>
.acur{
 color:#F97601;
 cursor:pointer;
 text-decoration:underline; 
}
</style>


<div class="sider w160 con">
				<div class="side-menu">
					<h3><span>个人服务</span></h3>
					<ul>
						<li><a href="javascript:void(0);" <#if channel=1>class="acur"</#if>>实名认证</a></li>
						<li><a href="javascript:void(0);" <#if channel=2>class="acur"</#if>>客户服务</a></li>
						<li><a href="javascript:void(0);" <#if channel=3>class="acur"</#if>>安全设置</a></li>
					</ul>
				</div>
			</div>
</#macro> 			