$( function() {
	$.ajaxSetup({cache:false});
	
	$("#queryBtn").click(function() {
		if (typeof queryFlag == 'undefined') {
			alert(hasNotAuthTipMsg);
			return false;
		}
		var status = $("#status").val();
		var name = $("#name").val();
		var email = $("#email").val();
		var params = $("#userGrid").bs_grid("getAllOptions");
		params.filterOptions.filter_rules = "";
		params.pageNum = 1;
		var queryParams = "";
		if(name != '') {
			queryParams += "name|like|" + name;
		}
		if(email != ''){
			if( queryParams == "" ) queryParams = "email|like|" + email;
			else queryParams += ",email|like|" + email;
			
		}
		if(status != ''){
			if( queryParams == "" ) queryParams = "status|=|" + status;
			else queryParams += ",status|=|" + status;
			
		}
		if(queryParams != "") {
			params.filterOptions.filter_rules = queryParams;
		}else {
			params.filterOptions.filter_rules = "";
		}
		$("#userGrid").bs_grid("displayGrid", true);
	});
	
	$("#userGrid").bs_grid({
        ajaxFetchDataURL: _ctx + '/user/loadUserList.jhtml',
        row_primary_key: "code",
        rowSelectionMode: false,
        useFilters: false,
        useSortableLists: false,
        showSortingIndicator: false,
        columns: [
            {field: "login_name", header: "登录名"},
            {field: "name", header: "中文名"},
            {field: "email", header: "邮箱"},
            {field: "statusName", header: "用户类型"},
            {field: "", header : "操作" ,
   			renderer : function(record){
   				var actHtml = "";
   				if (typeof editFlag == 'undefined') {
   					actHtml += '<a class="btn btn-default btn-xs" href="javascript:alert(\''+hasNotAuthTipMsg+'\');">编辑</a>';
   				} else {
   					actHtml += "<a class='btn btn-default btn-xs' href=\""+_ctx+"/user/addUser.jhtml?id="+record["id"]+"\">编辑</a>";
   				}
   				 
   				if (typeof resetFlag == 'undefined') {
   					actHtml += '<a class="btn btn-default btn-xs" href="javascript:alert(\''+hasNotAuthTipMsg+'\');">密码重置</a>';
   				} else {
   					var disabled = "";
   					if( record["status"] == "02" ){
   						disabled = "disabled";
   					}
   					actHtml += "<a class='btn btn-default btn-xs' "+disabled+" href='javascript:void(0);' onclick='sendResetPwdEmail(this);' attrUserId="+record["id"]+">密码重置</a>";
   				}   				 
   				
   			   	return  actHtml;
   			 }
   	       }
        ]
    });
});

//重置密码
function sendResetPwdEmail(obj) {
	var userId = $(obj).attr("attrUserId");
	$.confirm("是否执行重置密码操作！", {
		confirm:function(){
			$.ajax({
				url : _ctx+"/user/resetUserPassword.jhtml",
				type : 'POST',
				data : "userId="+userId,
				dataType : 'json',
				success : function(data) {
					if(data.rtnCode == '0000') {
						alert(data.rtnData);
						$("#userGrid").bs_grid("displayGrid", true);
					}else{
						alert("重置失败");
					}
				}
			});
		},
		cancel:function(){
			return false;
		}
	});
}

