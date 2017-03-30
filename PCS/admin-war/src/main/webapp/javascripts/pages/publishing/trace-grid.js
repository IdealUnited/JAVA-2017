var dialogDemo;
var dialogQualityEvaluationProcess;
jQuery(document).ready(function($) {
/************日历控件Begin*****************/
$(".form_date").datetimepicker({language:'zh-CN',
	format:"yyyy-mm-dd",
	weekStart: 1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 2,
	minView: 2,
	forceParse:0
}).on('changeDate', function(ev){
	if (ev.target.id === 'startAtFrom') {
		$('#startAtTo').datetimepicker('setStartDate', ev.target.value);
	} else if (ev.target.id === 'startAtTo') {
		$('#startAtFrom').datetimepicker('setEndDate', ev.target.value);
	}
});

/************日历控件End*****************/

/************data grid begin*****************/
$.ajaxSetup({cache:false});

function buildFilterCondition() {
	var form = $('#dataFilter');
	//console.log(form.serializeArray());	
	var filter = [];	
	$.each(form.serializeArray(), function(i, o) {
		var processType = '';
		if (o.name == 'platformType' && o.value != '') {
			filter.push(o.name + "|=|" + o.value);
		} else if (o.name == 'title' && o.value != '') {
			filter.push(o.name + "|like|" + o.value);
		} else if (o.name == 'dutyUser' && o.value != '') {
			filter.push(o.name + "|like|" + o.value);
		} else if (o.name == 'currentNodeId' && o.value != '') {
			if (o.value === 'canceled') {//【注销】理论上一个状态，需求却把它归应到一个节点
				filter.push("status|=|canceled");
			} else if (o.value === 'endevent') {
				filter.push("status|=|finished");
			} else {
				filter.push(o.name + "|=|" + o.value);
			}
			
		} else if (o.name == 'continuousDay' && o.value != '') {
			filter.push(o.name + '|'+$('#continuousOp').val()+'|' + o.value);//操作符拼接
		} else if (o.name == 'planPublishAt' && o.value != '') {
			filter.push(o.name + "|=|" + o.value);
		} else if (o.name == 'startAtFrom' && o.value != '') {
			filter.push(o.name + "|>=|" + o.value);
		} else if (o.name == 'startAtTo' && o.value != '') {
			filter.push(o.name + "|<=|" + o.value);
		} else if (o.name == 'isDelay' && o.value != '') {
			filter.push(o.name + "|=|" + o.value);
		}

	});	
	//console.log(filter);
	return filter;
}
$("#queryBtn").click(function(e) {
	if (typeof queryFlag == 'undefined') {		
		alert(hasNotAuthTipMsg);
		return false;
	}
	var filter = buildFilterCondition();
	var params = $("#publishingProcessDataGrid").bs_grid("getAllOptions");
	params.pageNum = 1;//fix bug #13381
	if (filter.length) {
		params.filterOptions.filter_rules = filter.join(',');
	} else {
		params.filterOptions.filter_rules = '';
	}
	
	$("#publishingProcessDataGrid").bs_grid("displayGrid", true);
});

$("#publishingProcessDataGrid").bs_grid({
    ajaxFetchDataURL: _ctx + '/publishing/trace/fill-data.jhtml',
    row_primary_key: "id",
    rowSelectionMode: false,
    useFilters: false,
    useSortableLists: true,
    showSortingIndicator: true,
    columns: [
        {field: "platformType", header: "发布平台", sortable: "no"},
        {field: "startAt", header: "发起日期", sortable: "no"},
        {field: "title", header: "发布标题", sortable: "no"},
        {field: "dutyUser", header: "发布负责人", sortable: "no"},
        {field: "currentNodeName", header: "当前环节", sortable: "no"},
        {field: "planPublishAt", header: "发布日期", sortable: "yes"},
        {field: "isDelay", header: "流程是否延迟", sortable: "no"},
        {field: "continuousTime", header: "流程持续时间", sortable: "no"},
        {field: "", header : "操作", width:"150", renderer : function(record) {	
        	//console.log(record['currentNodeId'] + ":" + record['id']);
			var endevent = 'endevent';
			var actHtml = '';
			if (typeof viewFormFlag == 'undefined') {
				actHtml += '<a class="btn btn-default btn-xs" href="javascript:alert(\''+hasNotAuthTipMsg+'\');">过程查看</a>';
			} else {
				actHtml += '<a class="btn btn-default btn-xs" href="'+_ctx+'/publishing/environmentVerify.jhtml?publishingId='+record["id"]+'&instanceId='+record["instanceId"]+'&actType=show">过程查看</a>';	
			}
			
			
			
			if (record['currentNodeId'] == endevent) {
				if (typeof evaluateFlag == 'undefined') {
					actHtml += '<a class="btn btn-default btn-xs" href="javascript:alert(\''+hasNotAuthTipMsg+'\');">质量评价</a>';
				}else{
					actHtml += '<a class="btn btn-default btn-xs" href="javascript:void(0);" onclick="qualityEvaluationProcess('+record["id"]+');">质量评价</a>';
				}
				
				if(typeof summaryFlag == 'undefined') {
					actHtml += '<a class="btn btn-default btn-xs" href="javascript:alert(\''+hasNotAuthTipMsg+'\');">总结查看</a>';
				}else{
					actHtml += '<a class="btn btn-default btn-xs" href="'+_ctx+'/publishing/publishingProcessHistorySummary.jhtml?publishingId='+record["id"]+'&instanceId='+record["instanceId"]+'&actType=show">总结查看</a>';
				}
			}
			
			
			
			
			
			if (record['currentNodeId'] == endevent) {
				//actHtml += '<span>删除</span>';
			} else {
				if (typeof delPFlag == 'undefined') {
					actHtml += '<a class="btn btn-default btn-xs" href="javascript:alert(\''+hasNotAuthTipMsg+'\');">删除</a>';
				} else {
					actHtml += '<a class="btn btn-default btn-xs deleteBtn" href="javascript:void(0);" data-href="'+_ctx+'/publishing/delete.jhtml?id='+record["id"]+'">删除</a>';
				}
			}
			
			if (record['currentNodeId'] == endevent) {
				//actHtml += '<span>注销</span>';
			} else {
				if (typeof cancelPFlag == 'undefined') {
					actHtml += '<a class="btn btn-default btn-xs" href="javascript:alert(\''+hasNotAuthTipMsg+'\');">注销</a>';
				} else {
					actHtml += '<a class="btn btn-default btn-xs" href="javascript:void(0);" onclick="logoutPublishingProcess('+record["id"]+');">注销</a>';
				}
			}

			if( record['currentNodeId'] == endevent){
				//actHtml += '<span>编辑</span>';
			} else {
				if (typeof editPFlag == 'undefined') {
					actHtml += '<a class="btn btn-default btn-xs" href="javascript:alert(\''+hasNotAuthTipMsg+'\');">编辑</a>';
				} else {
					actHtml += '<a class="btn btn-default btn-xs" href="'+_ctx+'/publishing/publishingProcessExecutor.jhtml?publishingId='+record["id"]+'&taskId='+record["taskId"]+'&actType=modify">编辑</a>';
				}
			}
			
			if( record['currentNodeId'] == endevent){
				//actHtml += '<span>编辑</span>';
			} else {
				//actHtml += '<a href="javascript:void(0);" onclick="rollbackProcess('+record["taskId"]+','+record["id"]+', "/'+record["prevNodeName"]+'/");">回滚</a>';
				if (typeof rPFlag == 'undefined') {
					actHtml += '<a class="btn btn-default btn-xs" href="javascript:alert(\''+hasNotAuthTipMsg+'\');">回滚</a>';
				} else {
					if( record['currentNodeId'] != "addPublishingModule" ){
						actHtml += "<a class='btn btn-default btn-xs' href='javascript:void(0);' onclick='rollbackProcess("+record['taskId']+","+record['id']+", \""+record['prevNodeName']+"\", \""+record['prevNodeId']+"\", \""+record['currentNodeId']+"\", \""+record['currentNodeName']+"\");'>";
						actHtml += "回滚</a>";
					}else{
						actHtml += "<a class='btn btn-default btn-xs' href='javascript:void(0);' style='color:gray;cursor:default;' disabled>";
						actHtml += "回滚</a>";
					}
				}
			}			
			
			return actHtml;
		}}    	
    ],
    sorting: [
		{sortingName: "planPublishAt", field: "planPublishAt", order: "descending"}
	]

});
/************data grid End*****************/

/************按钮功能 Begin *****************/
$('#publishingProcessDataGrid').on('click', 'a.deleteBtn', function(e) {	
	e.preventDefault();
	if (confirm("删除后将无法恢复，确认删除？")) {
		$.post($(this).data('href')).done(function(){
			location.reload();
		});
	}	
});
/************按钮功能 End *****************/

/************导出 Begin*****************/

jQuery(function($){
	$('#export').on('click', function(e) {
		if (typeof exportFlag == 'undefined') {
			return false;
		}
		
		var filter = buildFilterCondition();
		var params = $("#publishingProcessDataGrid").bs_grid("getAllOptions");
		if (filter.length) {
			params.filterOptions.filter_rules = filter.join(',');
		} else {
			params.filterOptions.filter_rules = '';
		}
		var filter_rules = $("#publishingProcessDataGrid").bs_grid("getAllOptions").filterOptions.filter_rules;
		$tmpForm = $('<form name="export-excel" method="POST" action="'+location.href.replace('grid.jhtml', 'export.jhtml')+'"><input type="hidden" name="filter_rules" value="'+filter_rules+'" /></form>').appendTo(document.body);
		//console.log($tmpForm);
		$tmpForm.submit();
		$tmpForm.remove();
		//$.post(location.href.replace('grid.jhtml', 'export.jhtml'), {"filter_rules":filter_rules})
		//location.href = location.href.replace('grid.jhtml', 'export.jhtml?filter_rules=' + filter_rules);
	});
});
/************导出 End *****************/

dialogDemo = $("#dialogDemo").dialog({
	title:"注销",
	backdrop:"static",
	modalSize:"", 
	width:"480",
	height:"300",
	keyboard:false,
	autoOpen:false,
	buttons:[
		{
			text:"取消",
			btnClass:"btn-default",
			click:function(){
				$("#logoutRemark").val("");
				this.close()
			}
		},
		{
			text:"保存",
			btnClass:"btn-success",
			click:function(){
				var _this = this;
				var pId =  $("#publishiId").val();
				var remark = $("#logoutRemark").val();
				if( remark == "" ){
					$.alert("注销原因不能为空");
				}else{
					if( remark.length >255 ){
						$.alert("注销原因不能大于255");
					}else{
						$.ajax({
							url : _ctx + '/publishing/logoutPublishingProcess.jhtml',
							type : 'POST',
							data : "publishingId=" +pId+"&remark="+remark,
							dataType : 'json',
							success : function(data) {
								if(data.retCode == '0000') {
									if( data.retData == 1 ) {
										$.alert("注销成功",function(){ 
											_this.close();
											$("#logoutRemark").val("");
											location.reload();
										});
									}else{
										$.alert("注销失败",function(){ 
											_this.close();
											$("#logoutRemark").val("");
											location.reload();
										});
									}
								}
							}
						});
					}
				}
			}
		}
	]
});


dialogQualityEvaluationProcess = $("#dialogQualityEvaluationProcess").dialog({
	title:"质量评价",
	backdrop:"static",
	modalSize:"", 
	width:"480",
	height:"300",
	keyboard:false,
	autoOpen:false,
	buttons:[
		{
			text:"取消",
			btnClass:"btn-default",
			click:function(){
				$("#qualityEvaluation").val("");
				this.close()
			}
		},
		{
			text:"保存",
			btnClass:"btn-success",
			click:function(){
				var _this = this;
				var pId =  $("#publishiId").val();
				var remark = $("#qualityEvaluation").val();
				if( remark == "" ){
					$.alert("质量评价不能为空");
				}else{
					if( remark.length >255 ){
						$.alert("质量评价不能大于255");
					}else{
						$.ajax({
							url : _ctx + '/publishing/qualityEvaluationProcess.jhtml',
							type : 'POST',
							data : "publishingId=" +pId+"&remark="+remark,
							dataType : 'json',
							success : function(data) {
								if(data.rtnCode == '0000') {
									if( data.retData == 1 ) {
										$.alert("评价成功",function(){ 
											_this.close();
											$("#logoutRemark").val("");
											location.reload();
										});
									}else{
										$.alert("评价失败",function(){ 
											_this.close();
											$("#logoutRemark").val("");
											location.reload();
										});
									}
								}
							}
						});
					}
				}
			}
		}
	]
});



//带出质量评价
dialogQualityEvaluationProcess.on("shown",function(){
	var pId =  $("#publishiId").val();
	$.ajax({
		url : _ctx + '/publishing/trace/getQuality.jhtml',
		type : 'POST',
		data : "publishingId=" +pId,
		dataType : 'json',
		success : function(data){
			if(data.rtnCode == '0000'){
				$("#qualityEvaluation").val(data.quality);
			}else{
				$.alert("查询质量评价失败",function(){ 
					location.reload();
				});
			}
		}
	});
});









});

//流程注销
function logoutPublishingProcess(pId){
	$("#publishiId").val(pId);
	if(!!dialogDemo){
		 dialogDemo.open();
	}
}
//质量评价
function qualityEvaluationProcess(pId){
	$("#publishiId").val(pId);
	if(!!dialogQualityEvaluationProcess){
		dialogQualityEvaluationProcess.open();
	}
}
//流程回滚
function rollbackProcess(taskId, pId, prevNodeName, prevNodeId, currentNodeId, currentNodeName){
	if( prevNodeId == "addPublishingModule" ){
		alert("该流程已经为发布流程操作第一步，无法进行回滚操作");
		return;
	}
	if(confirm('是否确定将流程回滚到“'+prevNodeName+'”环节？')){
		$.ajax({
			url : _ctx + '/publishing/trace/rollbackTask.jhtml',
			type : 'POST',
			data : "publishingId=" +pId+"&taskId="+taskId+"&prevNodeId="+prevNodeId+"&prevNodeName="+prevNodeName+"&currentNodeId="+currentNodeId+"&currentNodeName="+currentNodeName,
			dataType : 'json',
			success : function(data) {
				if( data.retCode == '0000' ){
					alert("回滚成功");
				}else{
					alert("回滚失败");
				}
				location.reload();
			}
		});
	}
}