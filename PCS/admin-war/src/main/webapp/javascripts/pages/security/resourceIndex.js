var dialog_;
$( function() {
	$.ajaxSetup({cache:false});
	
	//间距
	$('td a').css({'margin-left': "12px"});
	
	dialog_ = $("#changeParentNodeDialog").dialog({
		title:"节点移动",
		backdrop:"static",
		modalSize:"", /*large, medium, small*/
		width:"280",
		height:"200",
		keyboard:false,
		autoOpen:false,
		buttons:[
			{
				text:"关闭",
				btnClass:"btn-default",
				click:function(){
					this.close();
				}
			},
			{
				text:"确定",
				btnClass:"btn-success",
				click:function(){
					var id = $("body").data('current-node-id');
					var parentId = $("#parentNodeSelect").val();
					if (parentId == id) {
						$.alert('父节点不能为节点本身！');
						dialog_.close();
						return false;
					}
					
					$.post(_ctx + "/security/resource/moveResource.jhtml?id="+id+"&parentId=" + parentId).done(function(){
						alert('移动操作成功，点击确认将重新载入页面');
						location.href = _ctx  + '/security/resource/index.jhtml';
					}).fail(function(){
						alert('与服务器通信失败');
					}).always(function(){
						dialog_.close();
					});
				}
			}
		]
	});
	dialog_.on('shown', function(){
		$('#parentNodeName').text($("body").data('parent-node-name'));
	});
	
	//查询
	$('#queryBtn').on('click', function(e) {		
		if (typeof queryFlag == 'undefined') {	
			e.preventDefault();
			alert(hasNotAuthTipMsg);
			return false;
		}
	});	
	
	//添加
	$('a.resourceAdd').on('click', function(e) {
		if (typeof addFlag == 'undefined') {	
			e.preventDefault();
			alert(hasNotAuthTipMsg);
			return false;
		}
		
		location.href = _ctx + "/security/resource/addResource.jhtml?stepType=add&id=" + $(this).data('id');		
	});
	
	//移动
	$('a.resourceMove').on('click', function(e) {		
		if (typeof moveFlag == 'undefined') {	
			e.preventDefault();
			alert(hasNotAuthTipMsg);
			return false;
		}
		
		var id = $(this).data('id');
		$.ajax({
			url : _ctx + '/security/resource/getResourceById.jhtml',
			type : 'POST',
			data : "id=" + id,
			dataType : 'json',
			success : function(data) {
				if(data.rtnCode == '0000') {				
					dialog_.open();					
					$("body").data('current-node-id', id);
					$("body").data('parent-node-name', data.rtnDataName);
					
				}
			}
		});
	});
	
	//修改
	$('a.resourceUpd').on('click', function(e) {
		if (typeof editFlag == 'undefined') {	
			e.preventDefault();
			alert(hasNotAuthTipMsg);
			return false;
		}
		
		location.href = _ctx + "/security/resource/modifyResource.jhtml?stepType=modify&id=" + $(this).data('id');		
	});
	
	//删除
	$('a.resourceDel').on('click', function(e) {
		if (typeof delFlag == 'undefined') {	
			e.preventDefault();
			alert(hasNotAuthTipMsg);
			return false;
		}
		
		if(confirm("此操作会同时删除该资源的子资源，是否继续？")) {
			location.href = _ctx + "/security/resource/delResource.jhtml?id=" + $(this).data('id');
		}
	});	
	
	$('.tree').treegrid({
        expanderExpandedClass: 'glyphicon glyphicon-minus',
        expanderCollapsedClass: 'glyphicon glyphicon-plus'
    });
	
});
