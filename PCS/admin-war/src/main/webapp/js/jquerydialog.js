$(function(){
	$('#infoLoadingDiv').dialog({
		autoOpen: false,
		width: 200,
		close: (function () {
			allUnoverlay();
		}),
		open: (function () {
			allOverlay();
		})
	});
});

/*
 * methodName scprit方法名
 * code Ky或code
 * msg 提示信息
 */
function processDelete(methodName,code,msg) {
	$('#deleteRoleDiv').html(msg);
	$('#deleteRoleDiv').dialog({
		autoOpen: false,
		width: 400,
		close: (function () {
			allUnoverlay();
		}),
		open: (function () {
			allOverlay();
		}),
		buttons: {
			"确 认": function() { 
				methodName(code);
			},
			"取 消": function() { 
				$(this).dialog("close");
			}
		}
	});
	$('#deleteRoleDiv').dialog("open");
}

(function($){	
	$.fo={
		alert:function(m,opt){
			var def = {title:"系统消息",position:"center",t:"3",w:380,h:"auto",success:function(){},close:function(){} };	        
			var o = $.extend(def, opt);
			var id = (new Date()).getTime();			  
			$("<div id='dialog_"+id+"' title='"+o.title+"'><strong>"+m+"</strong></div>").appendTo($('body'));
			$('#dialog_'+id).dialog({				
	 	      		autoOpen:open,			
					width: o.w,
					height:"auto",
					modal:true,			
					bgiframe:true,
					draggable:false,
					resizable:false,
					//show:'slide',
					//hide:'slide',
					close:function(){o.close();$(this).remove();},
					position:o.position,
					buttons: {
						"关闭": function() { 
							$(this).dialog("close"); 
						}
					}								
				});	
		}	
	}})(jQuery);;

function allOverlay() {
	$("#allOverlayDiv").css("display", "block");
}
function allUnoverlay() {
	$("#allOverlayDiv").css("display", "none");
}