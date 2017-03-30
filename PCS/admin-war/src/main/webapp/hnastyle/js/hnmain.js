$(function() {
	$(".tabs-inner").live("dblclick", function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	});
	
	/*
	 * 结点切换
	 */
	$(".tree-node").live("click", function(){
		var node = $(this).parent().parent().tree('getSelected');
		$(this).parent().parent().tree('toggle',node.target);
	});
	
});