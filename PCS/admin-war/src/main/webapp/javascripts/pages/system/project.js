var _formProject;
$( function() {

	$("#queryBtn").click(function() {
		if (typeof queryFlag == 'undefined') {
			alert(hasNotAuthTipMsg);
			return false;
		}
		var qName = $("#q_name").val();
		var qCode = $("#q_code").val();
		var qPlatform = $("#q_platform").val();
		var queryParams = "";
		var params = $("#project_grid").bs_grid("getAllOptions");
		params.pageNum = 1;
		if(qName != '') {
			queryParams = "name|=|" + qName;
		} 
		if(qCode != '') {
			if(queryParams == "") queryParams = "code|=|" + qCode;
			else queryParams += ",code|=|" + qCode;
		}
		if(qPlatform != '') {
			if(queryParams == "") queryParams = "platform|=|" + qPlatform;
			else queryParams += ",platform|=|" + qPlatform;
		}
		if(queryParams != "") {
			params.filterOptions.filter_rules = queryParams;
		}else {
			params.filterOptions.filter_rules = "";
		}
		$("#project_grid").bs_grid("displayGrid", true);
	});
	
	$("#project_grid").bs_grid({
        ajaxFetchDataURL: _ctx + '/system/project/loadProjectList.jhtml',
        row_primary_key: "id",
        rowSelectionMode: false,
        useFilters: false,
        useSortableLists: false,
        showSortingIndicator: false,
        columns: [
            {field: "name", header: "名称", width: "150"},
            {field: "code", header: "代码", width: "150"},
            {field: "platform", header: "所属平台", width: "100", 
            	renderer : function(record){
            		if(record['platform'] == '2') return "旧平台";
            		else if(record['platform'] == '1') return "新平台";
            		else return "";
            	}
            },
            {field: "url", header: "SVN地址", width: "400"},
            {field: "", header : "操作" ,
   			renderer : function(record) {
   				var html = '';
   				if (typeof delFlag == 'undefined') {
   					html += "<a href='javascript:void(0);' class='btn btn-default btn-xs' onclick='alert(\""+hasNotAuthTipMsg+"\")'>删除</a>";   					
   				} else {
   					html += "<a href='javascript:void(0);' class='btn btn-default btn-xs' onclick='delProject(\""+record["id"]+"\")'>删除</a>";
   				}
   				
   				if (typeof editFlag == 'undefined') {
   					html += "<a href='javascript:void(0);' class='btn btn-default btn-xs' onclick='alert(\""+hasNotAuthTipMsg+"\")'>修改</a>";
   				} else {
   					html += "<a href='javascript:void(0);' class='btn btn-default btn-xs' onclick='updProject(\""+record["id"]+"\")'>修改</a>";
   				}

   				return html;
   			 }
   	       }
        ]
    });
	
	$("#createDialog").on("hide.bs.modal",function(e){
		$("#creatProjectForm").data("bootstrapValidator").resetForm();
	}).on("shown.bs.modal",function(){
		if(!!$("#creatProjectForm").data("bootstrapValidator")) return;
		checkProjectValidate();
	});
	
	$("#cancelProjectBtn").click(function(){
		var attrVal = "";
		$("#c_id").val("-1");
		$("#c_name").val(attrVal);
		$("#c_code").val(attrVal);
	    $("#c_platform").val(attrVal);
		$("#c_platform option[value='']").attr("selected", "selected").siblings().removeAttr('selected').end().parent().siblings(":not(i)").remove().end().select2();
		$("#c_url").val(attrVal);
		$("#c_description").val(attrVal);
	});
	$('#createDialog').modal({backdrop:"static",keyboard:false,show:false});

});

function delProject(id) {
	$.confirm("是否确认删除！", {confirm:function(){delP(id);},cancel:function(){return false;}});
}

function delP(id) {
	$.ajax({
		url : _ctx + '/system/project/delProject.jhtml',
		type : 'POST',
		data : "id=" + id,
		dataType : 'json',
		success : function(data) {
			if(data.rtnCode == '0000') {
				alert("操作成功!");
				//重新加载表格数据
				$("#project_grid").bs_grid("displayGrid", true);
			}
		}
	});
}

function updProject(id) {
	$.ajax({
		url : _ctx + '/system/project/loadProjectById.jhtml',
		type : 'POST',
		data : "id=" + id,
		dataType : 'json',
		success : function(data) {
			if(data.rtnCode == '0000') {
				$('#createDialog').modal({backdrop:"static",keyboard:false,show:true});
				//弹开修改对话框
				$("#creatProjectForm #c_id").val(data.rtnData.id);
				$("#creatProjectForm #c_name").val(data.rtnData.name);
				$("#creatProjectForm #c_code").val(data.rtnData.code);
				$("#creatProjectForm #c_platform").find("option[value='"+data.rtnData.platform+"']").prop("selected", "selected").siblings(":not(i)").removeAttr('selected').end().parent().siblings(":not(i)").remove().end().select2();
				$("#creatProjectForm #c_url").val(data.rtnData.url);
				$("#creatProjectForm #c_description").val(data.rtnData.description);				
			}
		}
	});
}

function checkProjectValidate(){
	_formProject = $('#creatProjectForm').bootstrapValidator({
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
					}
				}
			},
			c_code: {
				validators: {
					notEmpty: {
						message: '请输入代码'
					}
				}
			},
			c_platform: {
				validators: {
					notEmpty: {
						message: '请选择平台'
					}
				}
			}
		}   
	}).on("success.form.bv",function(e,target){
		e.preventDefault();
		var c_id = $("#c_id").val();
		var c_name = $("#c_name").val();
		var c_code = $("#c_code").val();
		var c_platform = $("#c_platform").val();
		var c_url = $("#c_url").val();
		var c_description = $("#c_description").val();
		$.ajax({
			url : _ctx + '/system/project/addProject.jhtml',
			type : 'POST',
			data : "id=" + c_id + "&name=" + c_name + "&code=" + c_code + "&platform=" + c_platform + "&url=" + c_url + "&description=" + c_description,
			dataType : 'json',
			success : function(data) {
				if(data.rtnCode == '0000') {
					alert("操作成功!");
					//清空表单数据
					$('#creatProjectForm input').val("");
					$("#c_id").val(-1);
					var attrVal = "";
					$("#c_name").val(attrVal);
					$("#c_code").val(attrVal);
					$("#c_platform option[value=2]").attr("selected", "selected");
					$("#c_url").val(attrVal);
					$("#c_description").val(attrVal);
					//重新加载表格数据
					$("#project_grid").bs_grid("displayGrid", true);
					$("#cancelProjectBtn").click();
				}else{
					alert(data.retMsg);
				}
			}
		});

	});
	
	$("#saveProjectBtn").click(function(){ $('#creatProjectForm').submit(); });
	
}