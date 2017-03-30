$( function() {
	$("#btnNext").click(function() {
		var nextNodeExecutorMessage = $("#nextNodeExecutorMessage").val();
		if( nextNodeExecutorMessage != ""){
			alert(nextNodeExecutorMessage);
			document.location = _ctx + '/index/main.jhtml';
			return;
		}
		
		//选择是否需要回滚
		var isSubmint = true; 
		var rollbackMsg = "";
		var rollbacks = $("[name='rollbacks']");
		rollbacks.each(function(){
			if($(this).val() == ""){
				rollbackMsg = "请填写是否需要回滚";
				isSubmint = false;
			}
		});
		if( rollbackMsg != "" ){
			$("#spnMsg").text(rollbackMsg);
		}else{
			//模块名
			var moduleMsg = "";
			var moduleNames = $("[name='moduleName']");
			moduleNames.each(function(){
				if($(this).val() == ""){
					moduleMsg = "请填写模块名";
					isSubmint = false;
				}
				var moduleNameLen = $(this).val().length;
				if( moduleNameLen >255 ){
					moduleMsg = "模块名不能大于255";
				}
			});
			if( moduleMsg != "" ){
				$("#spnMsg").text(moduleMsg);
			}else{
				//程序变更内容
				var moduleContentMsg = "";
				var moduleContents = $("[name='moduleContent']");
				moduleContents.each(function(){
					if($(this).val() == ""){
						moduleContentMsg = "请填写程序变更内容";
						isSubmint = false;
					}
					var moduleContentLen = $(this).val().length;
					if(moduleContentLen>255){
						moduleContentMsg = "程序变更内容不能大于255";
					}
				});
				if( moduleContentMsg != "" ){
					$("#spnMsg").text(moduleContentMsg);
				}else{
					//选择模块负责人
					var dutyMsg = "";
					var dutyUsers = $("[name='dutyUsers']");
					dutyUsers.each(function(){
						if($(this).val() == ""){
							dutyMsg = "请选择模块负责人";
							isSubmint = false;
						}
					});
					if( dutyMsg != "" ){
						$("#spnMsg").text(dutyMsg);
					}else{
						//module remark
						var moduleRemark = $("#moduleRemark").val().replace(/[\r\n]/g,"");
						moduleRemark = $.trim(moduleRemark);
						var moduleRemarkLen = moduleRemark.length;
						var moduleRemarkMsg = "";
						if( moduleRemark == "" ){
							moduleRemarkMsg = "模块发布优先级说明不能为空";
						}
						if( moduleRemarkLen > 255 ){
							moduleRemarkMsg = "模块发布优先级说明内容不能大于255";
						}
						if( moduleRemarkMsg != "" ){
							$("#spnMsg").text(moduleRemarkMsg);
						}else{
							//是否有数据库变更内容
							var isChangeDb = $("#dbChange").val()
							var dbFile = $("#dbFile").val();
							var attachmentMsg = "";
							if( isSubmint && isChangeDb == 1 ){
								var dbFiles = $(".dbFileAttechments");
								dbFiles.each(function(){
									if($(this).val() == "" || $(this).val() == "undefined"){
										isSubmint = false;
										return false;
									}
								});
								if( !isSubmint ){
									attachmentMsg = "请添加数据库变更附件";
								}
							}
							if( attachmentMsg != "" ){
								$("#spnMsg").text(attachmentMsg);
							}else{
								if( isSubmint ){
									$("#spnMsg").text();
									$("#btnNext").attr("disabled", true);
									$("#frmPublish").submit();
								}
								
							}
						}
					}
				}
			}
		}
	});
	
	//模块添加，删除js
	var resetModuletr = function(){
		var md = $(".module-edit tbody"); 
		md.find("tr:not(:last)").find(".plus-btn").css("color","#ccc").attr("disabled","true");
		md.find("tr:last").find(".plus-btn").removeAttr("disabled").removeAttr("style");
		md.find("tr").each(function(i){
			$(this).find("td:first").html(i+1);
		})
	}
	resetModuletr();

	var plus_handler = function(){
		if(!!$(this).attr("disabled")) return;
		var moduleTr = $(this).parent().parent();

		moduleTr.after(
			moduleTr.clone().find(".plus-btn:not(.disabled)")
			.click(plus_handler).end()
			.find(".minus-btn").click(function(){
				$(this).parent().parent().remove();
				resetModuletr();
			}).end()
			.find(".moduleName-input2").attr("notCheckValidate", "0").attr("placeholder","请填写").val("").removeAttr("disabled").end()
			.find(".select2-container").remove().end()
			.find('[data-toggle="select"]').removeAttr("disabled").find("option").removeAttr("selected").end().select2().end()
		);
		resetModuletr();
	};
	
	$(".module-edit .plus-btn").on("click", plus_handler);
	
	//添加file
	$("#addTrigger").click(function(){
		var num = GetRandomNum(1, 1000);
        var filebutton = "<input type='file' id='dbFile"+num+"' name='dbFile"+num+"' class='dbFileAttechments' />";
        document.getElementById('FileList').insertAdjacentHTML("beforeEnd",filebutton);
	});
	
	//生成随机数
	function GetRandomNum(Min,Max){   
		var Range = Max - Min;   
		var Rand = Math.random();   
		return(Min + Math.round(Rand * Range));   
	}
  //删除file
	$("#delteTrigger").click(function(){
		if($("#FileList input").length<=1) return;
		$("#FileList input").last().remove();
	});
	
	//显示隐藏数据库附件
	$("#dbChange").change(function(){
		if( $(this).val() != 1 ){
			$("#dvChange").hide();
		}else{
			$("#dvChange").show();
		}
	});
});

