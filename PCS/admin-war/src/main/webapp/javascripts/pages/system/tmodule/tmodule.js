var	dialog_, _data;
$( function() {
	$.ajaxSetup({cache:false});
	
	dialog_ = $("#createDialog").dialog({
		title:"新增/修改平台项目",
		backdrop:"static",
		modalSize:"", /*large, medium, small*/
		width:"550",
		height:"320",
		keyboard:false,
		autoOpen:false,
		buttons:[
			{
				text:"取消",
				btnClass:"btn-default",
				click:function(){
					$('#creatTModuleForm').data("bootstrapValidator").resetForm();
					this.close();
					//if(/msie/.test(navigator.userAgent.toLowerCase())) location.reload();
				}
			},
			{
				text:"保存",
				btnClass:"btn-success",
				click:function(){
					$("#creatTModuleForm").submit();
				}
			}
		]
	});
	
	dialog_.on('shown', function (event){
		//console.log("dialog show");
		if(!!$('#creatTModuleForm').data("bootstrapValidator")) return;
		$('#creatTModuleForm').bootstrapValidator({
			feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				c_name: {
					validators: {
						notEmpty: {
							message: '请输入名称'
						},
						stringLength: {
							min: 1,
							max: 255,
							message: '名称必须在1到255位之间'
						}
					}
				},
				c_code: {
					validators: {
						notEmpty: {
							message: '请输入代码'
						},
						stringLength: {
							min: 1,
							max: 255,
							message: '代码必须在1到255位之间'
						}
					}
				}
			}   
		}).on("success.form.bv",function(e,target){
			e.preventDefault();
			var c_id = $("#c_id").val();
			var c_name = $("#c_name").val();
			var c_code = $("#c_code").val();
			var c_status = $("#c_status").val();
			var c_remark = $("#c_remark").val();	
			$.ajax({
				url : _ctx + '/system/tmodule/addTModule.jhtml',
				type : 'POST',
				data : "id=" + c_id + "&name=" + c_name + "&code=" + c_code + "&status=" + c_status + "&remark=" + c_remark,
				dataType : 'json',
				success : function(data) {
					if(data.rtnCode == '0000') {
						$.alert("操作成功!",function(){ if(/msie/.test(navigator.userAgent.toLowerCase())) location.reload();});
						//清空表单数据
						$('#creatTModuleForm input').val("");
						$("#c_id").val(-1);
						//重新加载表格数据
						$("#tmodule_grid").bs_grid("displayGrid", true);
						dialog_.close();
					}else{
						$.alert(data.rtnMsg,function(){ if(/msie/.test(navigator.userAgent.toLowerCase())) location.reload();});
						//清空表单数据
						$('#creatTModuleForm input').val("");
						$("#c_id").val(-1);				
						
						//重新加载表格数据
						$("#tmodule_grid").bs_grid("displayGrid", true);						
						dialog_.close();
					}
					
				}
			});
		});
	});

	$("#queryBtn").click(function() {
		if (typeof queryFlag == 'undefined') {
			alert(hasNotAuthTipMsg);
			return false;
		}
		var qName = $("#q_name").val();
		var qCode = $("#q_code").val();
		var qStatus = $("#q_status").val();
		var queryParams = "";
		var params = $("#tmodule_grid").bs_grid("getAllOptions");
		params.pageNum = 1;
		if(qName != '') {
			queryParams = "name|like|" + qName;
		} 
		if(qCode != '') {
			if(queryParams == "") queryParams = "code|like|" + qCode;
			else queryParams += ",code|like|" + qCode;
		}
		if(qStatus != '') {
			if(queryParams == "") queryParams = "status|=|" + qStatus;
			else queryParams += ",status|=|" + qStatus;
		}
		if(queryParams != "") {
			params.filterOptions.filter_rules = queryParams;
		}else {
			params.filterOptions.filter_rules = "";
		}
		$("#tmodule_grid").bs_grid("displayGrid", true);
	});
	
	$("#tmodule_grid").bs_grid({
        ajaxFetchDataURL: _ctx + '/system/tmodule/loadList.jhtml',
        row_primary_key: "id",
        rowSelectionMode: false,
        useFilters: false,
        useSortableLists: false,
        showSortingIndicator: false,
        columns: [
            {field: "name", header: "名称", width: "250"},
            {field: "code", header: "代码", width: "250"},
            {field: "status", header: "状态", width: "100", 
            	renderer : function(record){
            		if(record['status'] == '0') return "启用";
            		else if(record['status'] == '1') return "停用";
            		else return "";
            	}
            },
            {field: "", header : "操作" ,
   			renderer : function(record) {
   				var html = '';
   				if (typeof delFlag == 'undefined') {
   					html += "<a class='btn btn-default btn-xs' href='javascript:void(0);' onclick='alert(\""+hasNotAuthTipMsg+"\")'>删除</a>";
   				} else {
   					html += "<a class='btn btn-default btn-xs' href='javascript:void(0);' onclick='del(\""+record["id"]+"\")'>删除</a>";
   				}
   				if (typeof editFlag == 'undefined') {
   					html += "<a class='btn btn-default btn-xs' href='javascript:void(0);' onclick='alert(\""+hasNotAuthTipMsg+"\")'>修改</a>";
   				} else {
   					html += "<a class='btn btn-default btn-xs' href='javascript:void(0);' onclick='upd(\""+record["id"]+"\")'>修改</a>";
   				}

   				 return html;
   			 }
   	       }
        ]
    });
	
	
	$('#creatBtn').on('click', function(e){
		if (typeof addFlag == 'undefined') {
			alert(hasNotAuthTipMsg);
			e.preventDefault();
			e.stopPropagation();
			return false;
		}
		//重设数据
		_data = {};
		_data.rtnCode = "0000";
		_data.rtnData = {id:-1, status:'', name:'', code:''};
		
		dialog_.setTitle("新增平台项目");
		dialog_.open();
	});
	
	dialog_.on("shown",function(){
		var data = _data;
		if(!_data) return;
		if(data.rtnCode == '0000') {
			//弹开修改对话框
			$("#creatTModuleForm #c_id").val(data.rtnData.id);
			$("#creatTModuleForm #c_name").val(data.rtnData.name);
			$("#creatTModuleForm #c_code").val(data.rtnData.code);
			
			
		//	$("#creatTModuleForm #c_status").children().each(function(i,o){
		//		$(this).removeAttr('selected');
		//		if ($(this).attr('value') == data.rtnData.status) {
		//			$(this).attr('selected', 'selected');
		//		}
		//	});
			//2015-4-10 qxf
			//修改select2控件状态
				$("#creatTModuleForm #c_status").children().removeAttr('selected');
				$("#creatTModuleForm #c_status").children().eq(data.rtnData.status).attr("selected","selected");
				console.log(data.rtnData.status);
				
				if(!(/msie/.test(navigator.userAgent.toLowerCase()))){
					
					var _select = $("#creatTModuleForm #c_status").clone();
					var _container = $("#creatTModuleForm #c_status").parent();			
					$("#creatTModuleForm #c_status").parent().empty(":not(i)");			
					_container.append(_select);
					_select.select2();
				}
				else{
					setTimeout(function(){$("#creatTModuleForm #c_status").select2();},50);
				}

			
			$("#creatTModuleForm #c_remark").val(data.rtnData.remark);
		}
	});
	
});

function del(id) {
	if (typeof delFlag == 'undefined') {
		alert(hasNotAuthTipMsg);
		return false;
	}
	$.confirm("是否确认删除！", {confirm:function(){delP(id);},cancel:function(){return false;}});
}

function delP(id) {
	$.ajax({
		url : _ctx + '/system/tmodule/delTModule.jhtml',
		type : 'POST',
		data : "id=" + id,
		dataType : 'json',
		success : function(data) {
			if(data.rtnCode == '0000') {
				alert("操作成功!");
				//重新加载表格数据
				$("#tmodule_grid").bs_grid("displayGrid", true);
			}
		}
	});
}


function upd(id) {
	$.ajax({
		url : _ctx + '/system/tmodule/loadById.jhtml',
		type : 'POST',
		data : "id=" + id,
		dataType : 'json',
		success : function(data) {
			if(data.rtnCode == '0000') {
				//弹开修改对话框
				_data = data;
				dialog_.setTitle("修改平台项目");
				dialog_.open();
			}
		}
	});
}