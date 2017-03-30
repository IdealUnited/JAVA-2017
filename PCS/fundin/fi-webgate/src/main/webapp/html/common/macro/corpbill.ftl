<#macro corpbill imgList>  

<div id="fader" >
		<ul>
		<#list imgList as img>
				<li><a <#if img_index=0>class="cur"</#if> href="${img.parameters?default("javascript:void(0);")}" target="${img.code?default("_self")}"><img width="720" height="180" alt="" src="${img.imgpath?default("")}" /></a>${img.title?default("")}</li>
				<#--
				<li><a href="#" target="_blank"><img width="720" height="180" alt="" title="" src="<@sp.static/>img/ttf/index/focus2.jpg" /></a></li>
				<li><a href="#" target="_blank"><img width="720" height="180" alt="" title="" src="<@sp.static/>img/ttf/index/f_3.jpg" /></a></li>-->
			</#list>
		</ul>
	</div>
				

<script type="text/javascript">
var Hongru={};
function H$(id){return document.getElementById(id)}
function H$$(c,p){return p.getElementsByTagName(c)}
Hongru.fader = function(){
	function init(anthor,options){this.anchor=anthor; this.init(options);}
	init.prototype = {
		init:function(options){
			var wp = H$(options.id),ul = H$$('ul',wp)[0],li = this.li = H$$('li',ul);this.a = options.auto?options.auto:4;
			this.index = options.position?options.position:0;
			this.curC = options.curNavClass?options.curNavClass:'fader_cur_nav'; 
			this.l = li.length;
			this.cur = this.z = 0;
			nav_wp = document.createElement('div');
			nav_wp.style.cssText = 'position:absolute;right:0;bottom:0;padding:8px 0;';
			for(var i=0;i<this.l;i++){
				this.li[i].o = 100;
				this.li[i].style.opacity = this.li[i].o/100;
				this.li[i].style.filter = 'alpha(opacity='+this.li[i].o+')';
				var nav = document.createElement('a');
				nav.className = options.navClass?options.navClass:'fader_nav';
				nav.innerHTML = i+1;
				nav.onclick = new Function(this.anchor+'.pos('+i+')');
				nav_wp.appendChild(nav);
			}
			wp.appendChild(nav_wp);
			this.pos(this.index);
		},
		auto:function(){
			this.li.a = setInterval(new Function(this.anchor+'.move(1)'),this.a*1000); 
		},
		move:function(i){
			var n = this.cur+i; 
			var m = i==1?n==this.l?0:n:n<0?this.l-1:n;
			this.pos(m);
		},
		pos:function(i){
			clearInterval(this.li.a);
			clearInterval(this.li[i].f);
			this.z++;
			this.li[i].style.zIndex = this.z;
			nav_wp.style.zIndex = this.z+1;
			this.cur = i;
			this.li.a = false;
			if(this.li[i].o>=100){
				this.li[i].o = 0;
				this.li[i].style.opacity = 0;
				this.li[i].style.filter = 'alpha(opacity=0)';
			}
			for(var x=0;x<this.l;x++){
				nav_wp.getElementsByTagName('a')[x].className = x==i?this.curC:'fader_nav'; //绑定当前控制器样式
			}
			this.li[i].f = setInterval(new Function(this.anchor+'.fade('+i+')'),25);
		},
		fade:function(i){
			if(this.li[i].o>=100){
				clearInterval(this.li[i].f);
				if(!this.li.a) this.auto();
			}
			else{
				this.li[i].o+=5;
				this.li[i].style.opacity = this.li[i].o/100;
				this.li[i].style.filter = 'alpha(opacity='+this.li[i].o+')';
			}
		}
	}
	return {init:init}
}();
</script>
<script type="text/javascript">
var fader = new Hongru.fader.init('fader',{
	id:'fader'
});
</script> 

</#macro> 