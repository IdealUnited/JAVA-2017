var targetLinkBtn;
$( function() {
	var pId = $("#publishingId").val();
	var currentNodeId = $("#currentNodeId").val();
	var actType = $("#actType").val();
	//加载发布回滚信息
	$.ajax({
		url : _ctx + '/publishing/loadRollbacks.jhtml',
		type : 'POST',
		data : "publishingId=" + pId,
		dataType : 'json',
		success : function(data) {
			if(data.rtnCode == '0000') {
				var ctHtml = "";
				ctHtml += "<tbody>";
				$.each(data.rtnData, function(idx, ctData){
					if( idx%2 == 0 ){
						ctHtml += "<tr>";
					}
					ctHtml += "<td class='text-left' style='padding-left:30px;'>"+ctData.name+"</td>";
					ctHtml += "<td>";
					ctHtml += '<div class="col-md-8">';
					ctHtml += '<select id="rollbacks" disabled name="rollbacks" style="width:100%;" data-toggle="select" class="form-control renderLaterForSelect select select-default mrs mbm">';
					if( ctData.rollback == 1 ){
						ctHtml += '<option value="1">需要回滚</option>';
					}else{
						ctHtml += '<option value="0">不需要回滚</option>';
					}
					ctHtml += "</select></div>";
					ctHtml += "</td>";
					if( idx%2 == 1 ){
						ctHtml += "</tr>";
					}
					
				});
				ctHtml += "</tbody>";
				$("#tblRollback").append(ctHtml);
				$(".renderLaterForSelect").select2();
			}
		}
	});
	
	//加载发布模块
	var resetModuletr;

//	var linkHTML = false;
	$.ajax({
		url : _ctx + '/publishing/loadPModules.jhtml',
		type : 'POST',
		data : "publishingId=" + pId,
		dataType : 'json',
		success : function(data) {
			if(data.rtnCode == '0000') {
				var ctHtml = "";
				if( data.rtnData.length > 0 ){
					$.each(data.rtnData, function(idx, ctData){ 
						ctHtml += "<tr><td style='color:#999;padding-left:15px;'>"+(idx+1)+"</td>";
						ctHtml += "<td>";
						if( (currentNodeId != 'qaSignature' && currentNodeId != 'addPublishingModule') && (actType == 'add' || actType == 'modify') ){
							ctHtml += "<input type='hidden' id='moduleIds' name='moduleIds' value='"+ctData.id+"' />";
						}
						ctHtml += '<input type="text" data-placement="top" disabled class="moduleName-input2 form-control text-center" value="'+ctData.name+'" title="'+ctData.name+'" name="moduleName" />';
						ctHtml += "</td>";
						ctHtml += "<td>";
						ctHtml += '<input type="text" data-toggle="tooltip" disabled class="moduleName-input2 form-control text-center" name="moduleContent" value="'+ctData.content+'" title="'+ctData.content+'" />';
						ctHtml += "</td>";
						ctHtml += "<td class='col-xs-2'>";
						ctHtml += '<select id="dutyUsers" name="dutyUsers" disabled data-toggle="select" class="form-control  modifyPublishingProcessModule select select-default mrs mbm">';
						ctHtml += '<option value="">请选择</option>';
						$.each(ctData.dutyUsers, function(idx, userData){ 
							var selVal = "";
							if( userData.id == ctData.userId ){
								selVal = "selected='selected'";
							}
							ctHtml += '<option value="'+userData.id+'" '+selVal+'>'+userData.userName;
							ctHtml += '</option>';
						});
						ctHtml += "</select></td>";
						if( (currentNodeId != 'qaSignature' && currentNodeId != 'addPublishingModule' && currentNodeId != 'addVersionNum' && currentNodeId != 'endevent')
							|| (currentNodeId == 'addVersionNum' && actType == 'add') ){
							var versionVal = "";
							var disableVal = "";
							if( ctData.pubVersion != "" ){
								versionVal = ctData.pubVersion;
								disableVal = "disabled";
							}
							if( actType == 'add'){
								ctHtml += '<td>';
								ctHtml += '<input type="text" '+disableVal+' value="'+versionVal+'" data-toggle="tooltip" name="moduleVersion" notCheckValidate="0" class="moduleName-input2 form-control addPublishingProcessVersionNum text-center" placeholder="请填写" />';
								ctHtml += '</td>';
								if(currentNodeId == 'addVersionNum'){
									if(ctData.url == "" || ctData.url == null){
										ctHtml += "<td><a href='javascript:void(0);' class='btn btn-primary btn-xs yzgLink2' attrDomailUrlModuleId='"+ctData.id+"' name='linkDesc'>添加链接</a>";
										ctHtml += "<input type='hidden' id='domainUrls' name='domainUrls' value=''  />";
										ctHtml += "</td>";
									}else{
										ctHtml += "<td><a href='javascript:void(0);' class='btn btn-primary btn-xs yzgLink1' attrDomailUrlModuleId='"+ctData.id+"'>链接详情</a></td>";
									}
								}else{
									ctHtml += "<td><a href='javascript:void(0);' class='btn btn-primary btn-xs yzgLink' attrDomailUrlModuleId='"+ctData.id+"'>链接详情</a></td>";
								}
							}
							//有查看权限
							if( actType == 'show'){
								ctHtml += '<td>';
								ctHtml += '<input type="text" '+disableVal+' value="'+versionVal+'" data-toggle="tooltip" disabled name="moduleVersion" notCheckValidate="1" class="moduleName-input2 form-control addPublishingProcessVersionNum text-center" placeholder="请填写" />';
								ctHtml += '</td>';
								ctHtml += "<td><a href='javascript:void(0);' class='btn btn-primary btn-xs yzgLink' attrDomailUrlModuleId='"+ctData.id+"'>链接详情</a></td>";
							//有修改权限
							}else if(actType == 'modify'){
								ctHtml += '<td>';
								ctHtml += '<input type="text" '+disableVal+' value="'+versionVal+'" data-toggle="tooltip" disabled name="moduleVersion" notCheckValidate="1" class="moduleName-input2 form-control addPublishingProcessVersionNum text-center" placeholder="请填写" />';
								ctHtml += '</td>';
								if(ctData.url == "" || ctData.url == null){
									ctHtml += "<td><a href='javascript:void(0);' class='btn btn-primary btn-xs yzgLink1' attrDomailUrlModuleId='"+ctData.id+"' name='linkDesc'>添加链接</a>";
									ctHtml += "<input type='hidden' id='domainUrls' name='domainUrls' value=''  />";
									ctHtml += "</td>";
								}else{
									ctHtml += "<td><a href='javascript:void(0);' class='btn btn-primary btn-xs yzgLink1' attrDomailUrlModuleId='"+ctData.id+"' name='linkDesc' data-url='"+ctData.url+"'>链接详情</a>";
									ctHtml += "<input type='hidden' id='domainUrls' name='domainUrls' value='"+ctData.url+"'  />";
									ctHtml += "</td>";
								}
								
							}
						}
						var showDisabled = ""; 
						if( actType == 'show' || actType == 'add' || currentNodeId == 'qaSignature' || currentNodeId == 'addPublishingModule' || currentNodeId == 'addVersionNum' ){
							showDisabled = "disabled='disabled'";
						}
						
						ctHtml += "<td>";
						ctHtml += '<a class="plus-btn" '+showDisabled+'><span class="glyphicon glyphicon-plus "></span></a>';
						ctHtml += '<a class="minus-btn col-md-offset-1" attrDelModuleId="'+ctData.id+'"><span class="glyphicon glyphicon-minus"></span></a>';
						ctHtml += "</td>";
						ctHtml += "</tr>";
					});
				}else{
					ctHtml += "<tr><td style='color:#999;padding-left:15px;'>1</td>";
					ctHtml += "<td>";
					var disableVal = "";
					if( actType != 'modify' ){
						disableVal = "disabled='disabled'";
					}
					ctHtml += '<input type="text" '+disableVal+' data-placement="top" class="moduleName-input2 form-control text-center" name="moduleName" />';
					ctHtml += "</td>";
					ctHtml += "<td>";
					ctHtml += '<input type="text" '+disableVal+' data-toggle="tooltip" class="moduleName-input2 form-control text-center" name="moduleContent" />';
					ctHtml += "</td>";
					ctHtml += "<td class='col-xs-2'>";
					ctHtml += '<select id="dutyUsers" '+disableVal+' name="dutyUsers" data-toggle="select" class="form-control  modifyPublishingProcessModule select select-default mrs mbm">';
					ctHtml += '<option value="">请选择</option>';
					$.each(data.dutyUsers, function(idx, userData){ 
						ctHtml += '<option value="'+userData.id+'">'+userData.userName;
						ctHtml += '</option>';
					});
					ctHtml += "</select></td>";
					if( (currentNodeId != 'qaSignature' && currentNodeId != 'addPublishingModule' && currentNodeId != 'addVersionNum' )
						|| (currentNodeId == 'addVersionNum' && actType == 'add') ){
						ctHtml += '<td>';
						ctHtml += '<input type="text" '+disableVal+' data-toggle="tooltip" name="moduleVersion" notCheckValidate="0" class="moduleName-input2 form-control addPublishingProcessVersionNum text-center" placeholder="请填写" />';
						ctHtml += '</td>';
						ctHtml += "<td><a href='javascript:void(0);' name='linkDesc' "+disableVal+" class='btn btn-primary btn-xs yzgLink1'>添加链接</a>";
						ctHtml += "<input type='hidden' id='domainUrls' name='domainUrls' />";
						ctHtml += '</td>';
					}
					ctHtml += "<td>";
					ctHtml += '<a class="plus-btn" '+disableVal+'><span class="glyphicon glyphicon-plus"></span></a>';
					ctHtml += '<a class="minus-btn col-md-offset-1" '+disableVal+'><span class="glyphicon glyphicon-minus"></span></a>';
					ctHtml += "</td>";
					ctHtml += "</tr>";
				}
				
				$("#trModules").append(ctHtml);
				if( actType == 'modify' || currentNodeId == 'addPublishingModule' ){
					$("#trModules").find("tr").find(".minus-btn").on("click",function(){
						if($("#trModules").find("tr").length == 1){
							deleteModuleById($("#trModules").find("tr td"), $(this).attr("attrDelModuleId"), "otherDel");
							return;
						}
						deleteModuleById(this, $(this).attr("attrDelModuleId"), "delFirst")
					});
				}
				
				//查看权限的打开查看权限dialog
				$(".yzgLink").on("click",function(){
					moduleId = $(this).attr("attrDomailUrlModuleId");
					dialogDemo_noAuth.open();
				});
				//修改权限的打开修改权限dialog
				$(".yzgLink1").on("click",function(){
					moduleId = $(this).attr("attrDomailUrlModuleId");
					//$(this).parent().find("input[type=hidden]");
					targetLinkBtn = $(this);
					dialogDemo.open();
				});
				//版本确定环节，进行预主干链接添加，保存至数据库
				$(".yzgLink2").on("click",function(){
					moduleId = $(this).attr("attrDomailUrlModuleId");
					targetLinkBtn = $(this);
					yzgLinkAdd.open();
				});
				
				$(".modifyPublishingProcessModule").select2();
				$(".module-edit .plus-btn:not(.disabled)").on("click", plus_handler);
				var firstTr = $("#trModules").find("tr:first");
				if( actType == 'modify' || currentNodeId == 'addPublishingModule' ){
					firstTr.find(".minus-btn").on("click",function(){
						targetLinkBtn.attr("attrdomailurlmoduleid","");
					});
					resetModuletr();
				}
			}
		}
	});
	
	//模块添加，删除js
	
	resetModuletr = function(){
		
		var md = $("#trModules"); 
		md.find("tr:not(:last)").find(".plus-btn").css("color","#ccc").attr("disabled","true");
		if( actType == 'modify' || currentNodeId == 'addPublishingModule' ){
			md.find("tr:last").find(".plus-btn").removeAttr("disabled").removeAttr("style");
		}
		md.find("tr").each(function(i){
			$(this).find("td:first").html(i+1);
		})
		$(".yzgLink1").on("click",function(){
			moduleId = $(this).attr("attrDomailUrlModuleId");
			$(this).parent().parent().find("input[type=hidden]");
			targetLinkBtn =  $(this);
			dialogDemo.open();
		});
	}
	
	var plus_handler = function(){
		if(!!$(this).attr("disabled")) return;
		var moduleTr = $(this).parent().parent();
		moduleTr.after(
			moduleTr.clone().find(".plus-btn:not(.disabled)")
			.click(plus_handler).end().find(".yzgLink1").html("添加链接").attr("attrdomailurlmoduleid","").attr("name","linkDesc").removeAttr("data-url").end()
			.find(".minus-btn").click(function(){
				$(this).parent().parent().remove();
				resetModuletr();
			}).end()
			.find(".moduleName-input2").attr("notCheckValidate", "0").attr("placeholder","请填写").val("").removeAttr("disabled").end()
			.find(".select2-container").remove().end()
			.find('[data-toggle="select"]').removeAttr("disabled").find("option").removeAttr("selected").end().select2().end()
			.find("input[type=hidden]").val("").end()
		);
		resetModuletr();
	};

	
	//加载数据库附件
	$.ajax({
		url : _ctx + '/publishing/loadAttachments.jhtml',
		type : 'POST',
		data : "publishingId=" + pId,
		dataType : 'json',
		success : function(data) {
			if(data.rtnCode == '0000') {
				var ctHtml = "";
				$.each(data.rtnData, function(idx, ctData){ 
					ctHtml += '<div role="alert" class="alert file-handler alert-dismissible">';
					ctHtml += '<a href="'+ctData.downPath+'" target="_blank">'+ctData.name+'</a>';
					ctHtml += '<button class="close disabled" type="button"><span aria-hidden="true">×</span>';
					ctHtml += '<span class="sr-only">Close</span></button>';
					ctHtml += '</div>';
				});
				$("#dvAttachments").append(ctHtml);
			}
		}
	});
	
	var taskId = $("#taskId").val();
	var instanceId = $("#instanceId").val();
	var url = _ctx + '/publishing/loadSignatures.jhtml';
	var paramData = "publishingId=" + pId+"&taskId="+taskId+"&actType="+actType;
	if( actType == 'show' ){
		url = _ctx + '/publishing/loadSignatureByInstanceId.jhtml';
		paramData = "publishingId=" + pId+"&instanceId="+instanceId;
	}
	//加载签字信息
	$.ajax({
		url : url,
		type : 'POST',
		data : paramData,
		dataType : 'json',
		success : function(data) {
			if(data.rtnCode == '0000') {
				var ctHtml = "";
				var grayTestIndex = 0;
				var prodTestIndex = 0;
				var isLoadBalanceIndex = 0;
				$.each(data.rtnData, function(idx, ctData){ 
					var explain = "";
					var processStatus = ctData.publishingExplain;
					var nodeId = ctData.nodeId;
					if( nodeId == "qaSignature" ){
						processStatus = "发布流程发起";
						explain = "QA签字";
					}else if(nodeId == "addVersionNum"){
						explain = "配置管理员签字";
					}else if(nodeId == "grayVerifyDb" || nodeId == 'prodVerifyDb' ){
						processStatus += "通过";
						explain = "DBA签字";
					}else if(nodeId == "grayVerifyEvn" || nodeId == 'prodVerifyEvn' 
						|| nodeId == 'loadBalanceVerifyEvn' ){
						explain = "运维人员签字";
						processStatus += "通过";
					}else if(nodeId == "grayAssignTester"){
						explain = "测试人员";
					}else if( nodeId == 'isVerifyLoadBalanceEvn' ){
						explain = "发布负责人签字";
					}else if( nodeId == 'bizTestEvnVerify' ){
						processStatus += "通过";
						explain = "商户主管签字";
					}else if( nodeId == 'confirmProcessFinished' ){
						processStatus = "确认发布流程已经结束";
						explain = "发布负责人签字";
					}else{
						explain = "签字";
					}
					
					if( nodeId == "grayAssignTester" || nodeId == 'grayApproveTest' ){
						if( nodeId == "grayAssignTester" ){
							ctHtml += '<div class="panel panel-default">';
							ctHtml += '<div class="panel-heading">灰度机验证</div><div class="panel-body"><div class="row">';
							ctHtml += '<table class="table tablelet">';
							ctHtml += '<thead><tr><th class="col-xs-2" style="border:0px;height:0px"></th ><th style="border:0px;height:0px;"></th></tr></thead>';
						}
						if( nodeId == "grayAssignTester" ){
							ctHtml += '<tr><th colspan="2">灰度机验证-指定测试人员</th></tr>';
							ctHtml += '<tr><td class="col-xs-2 text-right bottomLine">测试人员：</td>';	
							ctHtml += '<td class="col-xs-10 bottomLine">';	
							$.each(ctData.sign, function(idx, grayCt){
								var checked = "";
								var disabled = "";
								if( grayCt.isChecked == 1 ){
									checked = "checked='checked'";
								}
								if( currentNodeId != 'grayAssignTester' ){
									disabled = "disabled";
								}
								ctHtml += '<label class="checkbox">';
								ctHtml += '<input type="checkbox" '+disabled+' attrUserName="'+grayCt.userName+'" onchange="setTesters(this);" data-toggle="checkbox" name="grayTesters" '+checked+' value="'+grayCt.userId+'" class="custom-checkbox" />'+grayCt.userName;
								ctHtml += '<span class="icons"><span class="icon-unchecked"></span><span class="icon-checked"></span></span>';
								ctHtml += '</label>';
							});
							ctHtml += '</td></tr>';
						}

						//加载测试人员
						if( nodeId == 'grayApproveTest' ){
							if( grayTestIndex == 0 ){
								grayTestIndex = 1;
								ctHtml += '<tr><td colspan="2" class="divider"></td></tr>';
								ctHtml += '<tr><th colspan="2">灰度机验证-灰度机测试通过（测试人员签字及测试情况描述）</th></tr>';
							}
							if( Object.prototype.toString.call(ctData.sign) === '[object Array]' ){
								$.each(ctData.sign, function(idx, ct){
									ctHtml += '<tr><td class="col-xs-2 text-right">测试人员签字：</td>';
									ctHtml += '<td class="col-xs-10 ">'+ct.userName+'</td></tr>';
									ctHtml += '<td class="col-xs-2 text-right ">测试情况描述：</td>';
									ctHtml += '<td class="col-xs-10 ">'+ct.remark+'</td></tr>';
								});
							}else{
								ctHtml += '<tr><td class="col-xs-2 text-right">测试人员签字：</td>';
								ctHtml += '<td class="col-xs-10 ">'+ctData.sign+'</td></tr>';
								ctHtml += '<td class="col-xs-2 text-right ">测试情况描述：</td>';
								ctHtml += '<td class="col-xs-10 "><input type="text" id="remark" name="remark"></td></tr>';
								ctHtml += '</table></div></div></div>';
							}
							if( Object.prototype.toString.call(ctData.sign) === '[object Array]' && currentNodeId != 'grayApproveTest' ){
								ctHtml += '</table></div></div></div>';
							}
						}
					}else if(nodeId == "prodAssignTester" || nodeId == 'prodApproveTest'){
						if( Object.prototype.toString.call(ctData.sign) === '[object Array]' && nodeId == "prodAssignTester" ){
							ctHtml += '<div class="panel panel-default">';
							ctHtml += '<div class="panel-heading">正式环境验证</div><div class="panel-body"><div class="row">';
							ctHtml += '<table class="table tablelet table-fixed">';
							ctHtml += '<thead><tr><th class="col-xs-2" style="border:0px;height:0px"></th ><th style="border:0px;height:0px;"></th></tr></thead>';
						}
						
						if( nodeId == "prodAssignTester" ){
							ctHtml += '<tr><th colspan="2"> 正式环境验证-指定测试人员 </th></tr>';
							ctHtml += '<tr><td class="col-xs-2 text-right bottomLine">测试人员：</td>';	
							ctHtml += '<td class="col-xs-10 bottomLine">';	
							$.each(ctData.sign, function(idx, ct){
								var checked = "";
								var disabled = "";
								if( ct.isChecked == 1 ){
									checked = "checked='checked'";
								}
								if( currentNodeId != 'prodAssignTester' ){
									disabled = "disabled";
								}
								ctHtml += '<label class="checkbox">';
								ctHtml += '<input type="checkbox" '+disabled+' attrUserName="'+ct.userName+'" data-toggle="checkbox" onchange="setTesters(this);" name="prodTesters" '+checked+' value="'+ct.userId+'" class="custom-checkbox" />'+ct.userName;
								ctHtml += '<span class="icons"><span class="icon-unchecked"></span><span class="icon-checked"></span></span>';
								ctHtml += '</label>';
							});
							ctHtml += '</td></tr>';
						}

						//加载测试人员
						if( nodeId == 'prodApproveTest' ){
							if( prodTestIndex == 0 ){
								prodTestIndex = 1;
								ctHtml += '<tr><td colspan="2" class="divider"></td></tr>';
								ctHtml += '<tr><th colspan="2">正式环境验证-正式环境版本测试通过（测试人员签字及测试情况描述） </th></tr>';
							}

							if( Object.prototype.toString.call(ctData.sign) === '[object Array]' ){
								$.each(ctData.sign, function(idx, ct){
									ctHtml += '<tr><td class="col-xs-2 text-right">测试人员签字：</td>';
									ctHtml += '<td class="col-xs-10 ">'+ct.userName+'</td></tr>';
									ctHtml += '<td class="col-xs-2 text-right ">测试情况描述：</td>';
									ctHtml += '<td class="col-xs-10 ">'+ct.remark+'</td></tr>';
								});
							}else{
								ctHtml += '<tr><td class="col-xs-2 text-right">测试人员签字：</td>';
								ctHtml += '<td class="col-xs-10 ">'+ctData.sign+'</td></tr>';
								ctHtml += '<td class="col-xs-2 text-right ">测试情况描述：</td>';
								ctHtml += '<td class="col-xs-10 "><input type="text" id="remark" name="remark"></td></tr>';
								ctHtml += '</table></div></div></div>';
							}
							if( Object.prototype.toString.call(ctData.sign) === '[object Array]' && currentNodeId != 'prodApproveTest'){
								ctHtml += '</table></div></div></div>';
							}
						}
					}else if( nodeId=='isVerifyLoadBalanceEvn' || nodeId == 'loadBalanceVerifyEvn' ||
						nodeId == "loadBalanceAssignTester" || nodeId == 'loadBalanceApproveTest' ){
						var emailTitle = $("#emailTitle").val();
						if( nodeId=='isVerifyLoadBalanceEvn' ){
							ctHtml += '<div class="panel panel-default">';
							ctHtml += '<div class="panel-heading">发送上线通知</div>';
							ctHtml += '<div class="panel-body">';
							ctHtml += '<div class="row"><table class="table tablelet">';
							ctHtml += '<tr><td class="col-xs-2 text-right ">发布通知内容：</td>';
							ctHtml += '<td class="col-xs-10 "><table class="table table-fixed table-bordered"><tr><th class="text-center col-xs-1">标题</th>';
							var currentTime = $("#currentTime").text();
							ctHtml += '<th class="text-center" title="标题"><span id="sendPublishingTitle">'+currentTime+emailTitle+'</span>';
							ctHtml += '</th></tr>';	
							
							if( actType == 'show' || actType == 'modify' || currentNodeId == 'loadBalanceAssignTester' || currentNodeId == 'loadBalanceApproveTest' || currentNodeId == 'loadBalanceVerifyEvn' || currentNodeId =='bizTestEvnVerify' || currentNodeId == 'confirmProcessFinished'){
								var emailNotfication = ctData.emailNotification;
								if( typeof(emailNotfication) == 'undefined' ){
									emailNotfication = "";
								}
								if( emailNotfication != "" ){
									ctHtml += '<tr><td class="text-center" colspan="2">';
									ctHtml += "<span>"+emailNotfication+"</span>";
								}
							}else{
								ctHtml += '<tr><td class="text-center" colspan="2">';
								ctHtml += '<textarea class="moduleName-input form-control text-left" style="text-indent:2em;height:80px;" placeholder="请填写" type="text" name="emailContent" id="emailContent" />';
							}
							ctHtml += '</td></tr></table></td></tr>';
							ctHtml += '</table>';
							ctHtml += '</div></div></div>';
							ctHtml += '<div class="panel panel-default"><div class="panel-heading">负载均衡验证</div>';
							ctHtml += '<div class="panel-body">';
							ctHtml += '<table class="table tablelet">';
						}
						if( nodeId == 'isVerifyLoadBalanceEvn' ){
							ctHtml += '<tr><th class="col-xs-3" >确认是否进行负载均衡验证</th>';
							ctHtml += '<td class="col-xs-9">';	
							ctHtml += '<label class="radio pull-left">';
							var rdChecked = "";
							var rd2Checked = "checked='checked'";
							var isLoadBalance = ctData.isLoadBalance;
							if( isLoadBalance== 1 ){
								rdChecked = "checked='checked'";
								rd2Checked = "";
							}
							var disabled = "";
							if( actType == 'modify' || actType == 'show' ){
								disabled = "disabled='disabled'";
							}
							if( currentNodeId != 'isVerifyLoadBalanceEvn' ){
								disabled = "disabled='disabled'";
							}
							ctHtml += '<input type="radio" onchange="setIsLoadBalanceMessages();" onclick="$(this).parent().parent().parent().siblings().show();" data-toggle="radio" name="isLoadBalance" id="optionsRadios1" value="1" data-radiocheck-toggle="radio" '+rdChecked+' '+disabled+'>是</label>';		
							ctHtml += '<label class="radio pull-left col-xs-offset-1">';
							ctHtml += '<input type="radio" onchange="setNotIsLoadBalanceMessages();" onclick="$(this).parent().parent().parent().siblings('+"':not(.nothide)'"+').hide();" data-toggle="radio" name="isLoadBalance" id="optionsRadios2" value="0" data-radiocheck-toggle="radio" required '+rd2Checked+' '+disabled+'>否</label>';
							ctHtml += '</td></tr>';
							ctHtml += '<tr class="nothide"><th class="text-center bottomLine">'+explain;
							ctHtml += '：</th><td class="bottomLine">'+ctData.sign+'</td></tr><tr><td class="driver"></td></tr>';
							setTimeout(function(){$("#optionsRadios2").trigger("click");},50);
							if( isLoadBalance== 1 ){
								setTimeout(function(){$("#optionsRadios1").trigger("click");},50);
							}
							if( currentNodeId == 'isVerifyLoadBalanceEvn' || isLoadBalance != 1 ){
								ctHtml += '</table></div></div></div>';
							}
						}
						
						if( nodeId == 'loadBalanceVerifyEvn' ){
							ctHtml += '<tr><th colspan="2">负载均衡验证-环境确认及版本发布通过</th></tr>';
							ctHtml += '<tr><td class="col-xs-2 text-center bottomLine">'+explain+'：</td>';
							ctHtml += '<td class="col-xs-10 bottomLine">'+ctData.sign+'</td></tr>';
							ctHtml += '<tr><td colspan="2" class="divider"></td></tr>';
						}
						
						if( nodeId == "loadBalanceAssignTester" ){
							ctHtml += '<tr><th colspan="2">负载均衡验证-指定测试人员 </th></tr>';
							ctHtml += '<tr><td class="col-xs-2 text-center bottomLine">测试人员：</td>';	
							ctHtml += '<td class="col-xs-10 bottomLine">';	
							$.each(ctData.sign, function(idx, ct){
								var checked = "";
								var disabled = "";
								if( ct.isChecked == 1 ){
									checked = "checked='checked'";
								}
								if( currentNodeId != 'loadBalanceAssignTester' ){
									disabled = "disabled";
								}
								ctHtml += '<label class="checkbox">';
								ctHtml += '<input type="checkbox" '+disabled+' attrUserName="'+ct.userName+'" data-toggle="checkbox" onchange="setTesters(this);" name="loadBalanceTesters" '+checked+' value="'+ct.userId+'" class="custom-checkbox" />'+ct.userName;
								ctHtml += '<span class="icons"><span class="icon-unchecked"></span><span class="icon-checked"></span></span>';
								ctHtml += '</label>';
							});
							ctHtml += '</td></tr>';
						}

						//加载测试人员
						if( nodeId == 'loadBalanceApproveTest' ){
							if( isLoadBalanceIndex == 0 ){
								isLoadBalanceIndex = 1;
								ctHtml += '<tr><td colspan="2" class="divider"></td></tr>';
								ctHtml += '<tr><th colspan="2">负载均衡验证-负载均衡版本测试通过（测试人员签字及测试情况描述） </th></tr>';
							}

							if( Object.prototype.toString.call(ctData.sign) === '[object Array]' ){
								$.each(ctData.sign, function(idx, ct){
									ctHtml += '<tr><td class="col-xs-2 text-center">测试人员签字：</td>';
									ctHtml += '<td class="col-xs-10 ">'+ct.userName+'</td></tr>';
									ctHtml += '<td class="col-xs-2 text-center">测试情况描述：</td>';
									ctHtml += '<td class="col-xs-10 ">'+ct.remark+'</td></tr>';
								});
							}else{
								ctHtml += '<tr><td class="col-xs-2 text-center">测试人员签字：</td>';
								ctHtml += '<td class="col-xs-10 ">'+ctData.sign+'</td></tr>';
								ctHtml += '<td class="col-xs-2 text-center">测试情况描述：</td>';
								ctHtml += '<td class="col-xs-10 "><input type="text" id="remark" name="remark"></td></tr>';
								ctHtml += '</table></div></div></div>';
							}
							if( Object.prototype.toString.call(ctData.sign) === '[object Array]' && currentNodeId != 'loadBalanceApproveTest' ){
								ctHtml += '</table></div></div></div>';
							}
						}
					}else{
						ctHtml += '<div class="panel panel-default">';
						ctHtml += '<div class="panel-heading">'+processStatus+'</div>';
						ctHtml += '<div class="panel-body">';
						ctHtml += '<div class="col-xs-2 text-right">';
						ctHtml += explain+'：';
						ctHtml += '</div><div class="col-xs-8">'+ctData.sign+'</div></div></div>';
					}
				});
				$("#dvSignatures").append(ctHtml);
			}
		}
	});
	
	//保存
	$("#btnNext").click(function() {
		var nextNodeExecutorMessage = $("#nextNodeExecutorMessage").val();
		if( nextNodeExecutorMessage != "" ){
			alert(nextNodeExecutorMessage);
			document.location = _ctx + '/index/main.jhtml';
			return;
		}
		var currentNodeId = $("#currentNodeId").val();
		var actType = $("#actType").val();
		var isSubmint = true;
		var dutyMsg = "";
		//if( currentNodeId == 'addVersionNum' || actType  == 'modify' ){
			var moduleVersion = $("[name='moduleVersion']");
			moduleVersion.each(function(){
				var versionCheck = $(this).attr("notCheckValidate");
				if($(this).val() == "" && versionCheck != 1){
					$("#spnMsg").text("请添加版本号");
					isSubmint = false;
				}
				var moduleVersionLen = $(this).val().length;
				if( moduleVersionLen >255 ){
					$("#spnMsg").text("版本号不能大于255");
					isSubmint = false;
				}
			});
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
			
			//预主干链接
			if( actType == 'modify' || actType == 'add'){
				var linkDesc = $("[name='linkDesc']");
				linkDesc.each(function(){
					if($(this).html() == "添加链接"){
						$("#spnMsg").text("请添加预主干链接");
						isSubmint = false;
					}
					var linkDescLen = $(this).val().length;
					if(linkDescLen > 255){
						$("#spnMsg").text("预主干链接长度不能大于255");
						isSubmint = false;
					}
				});
			}
			
			
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
					}
				}
			}
		//}
		
		if( actType == 'modify' ){
			var title = $("#publishingTitle").val();
			if( title == "" ){
				$("#spnMsg").text("发布标题不能空");
				isSubmint = false;
			}
			var titleLen = title.length;
			if( titleLen > 255 ){
				$("#spnMsg").text("发布标题不能大于255");
				isSubmint = false;
			}
		}
		
		if( actType  == 'modify' && currentNodeId != 'addPublishingModule' ){
			var moduleRemark = $("#moduleRemark").val().replace(/[\r\n]/g,"");
			moduleRemark = $.trim(moduleRemark.replace(/[ ]/g,""));
			var moduleRemarkLen = moduleRemark.length;
			var moduleRemarkMsg = "";
			if( moduleRemark == "" ){
				moduleRemarkMsg = "模块发布优先级说明不能为空";
				isSubmint = false;
			}
			if( moduleRemarkLen > 255 ){
				moduleRemarkMsg = "模块发布优先级说明内容不能大于255";
				isSubmint = false;
			}
			if( moduleRemarkMsg != "" ){
				$("#spnMsg").text(moduleRemarkMsg);
			}
		}
		
		//灰度环境指定测试人员
		if( currentNodeId == 'grayAssignTester' && actType == 'add' ){
			var grayTesters = $("input[name='grayTesters']:checked");
			var userIds = [];
			grayTesters.each(function(){
				userIds.push($(this).val());
			});
			if( userIds.length == 0 ){
				$("#spnMsg").text("请指定测试人员");
				isSubmint = false;
			}
			$("#assignTesters").val(userIds);

		}
		//测试情况描述
		if( currentNodeId == 'grayApproveTest' && actType == 'add' ){
			var remarkLen = $("#remark").val().length;
			if(remarkLen>255){
				$("#spnMsg").text("测试情况描述不能大于255");
				isSubmint = false;
			}
		}
		//正式环境指定测试人员
		if( currentNodeId == 'prodAssignTester' && actType == 'add' ){
			var grayTesters = $("input[name='prodTesters']:checked");
			var userIds = [];
			grayTesters.each(function(){
				userIds.push($(this).val());
			});
			if( userIds.length == 0 ){
				$("#spnMsg").text("请指定测试人员");
				isSubmint = false;
			}
			$("#assignProdTesters").val(userIds);
		}
		//测试情况描述
		if( currentNodeId == 'prodApproveTest' && actType == 'add' ){
			var remarkLen = $("#remark").val().length;
			if(remarkLen>255){
				$("#spnMsg").text("测试情况描述不能大于255");
				isSubmint = false;
			}
		}
		if( currentNodeId == 'isVerifyLoadBalanceEvn' && actType == 'add' ){
			$("#isLoadBalanceStatus").val($('input[name="isLoadBalance"]:checked').val());
			var emaiiContentLen = $("#emailContent").val().length;
			if(emaiiContentLen>255){
				$("#spnMsg").text("发送通知内容不能大于255");
				isSubmint = false;
			}
		}
		
		//负载均衡指定测试人员
		if( currentNodeId == 'loadBalanceAssignTester' && actType == 'add' ){
			var loadBalanceTesters = $("input[name='loadBalanceTesters']:checked");
			var userIds = [];
			loadBalanceTesters.each(function(){
				userIds.push($(this).val());
			});
			if( userIds.length == 0 ){
				$("#spnMsg").text("请指定测试人员");
				isSubmint = false;
			}
			$("#assignLoadBalanceTesters").val(userIds);

		}
		
		//测试情况描述
		if( currentNodeId == 'loadBalanceApproveTest' && actType == 'add' ){
			var remarkLen = $("#remark").val().length;
			if(remarkLen>255){
				$("#spnMsg").text("测试情况描述不能大于255");
				isSubmint = false;
			}
		}
		
		if( isSubmint ){
			$("#btnNext").attr("disabled", true);
			$("#frmPublish").submit();
		}
	});
});

function modifyVersion(obj){
	var moduleId = $(obj).attr('attrModuleId');
	var version = $(obj).val();
	$.ajax({
		url : _ctx + '/publishing/modifyModule.jhtml',
		type : 'POST',
		data : "moduleId=" + moduleId+"&version="+version,
		dataType : 'json',
		success : function(data) {
			if(data.rtnCode == '0000') {
				if(data.rtnCode == '0000') {
					//alert(data.retMsg);
				}else{
					alert(data.retMsg);
				}
			}
		}
	});
}

//添加测试描述
function modifySignatureRemark(obj){
	var signatureId = $(obj).attr('attrModifySignatureId');
	var remark = $(obj).val();
	$.ajax({
		url : _ctx + '/publishing/modifySignatureRemark.jhtml',
		type : 'POST',
		data : "signatureId=" + signatureId+"&remark="+remark,
		dataType : 'json',
		success : function(data) {
			if(data.rtnCode == '0000') {
				if(data.rtnCode == '0000') {
					//alert(data.retMsg);
				}else{
					alert(data.retMsg);
				}
			}
		}
	});
}

//修改发布标题
function modifyPublishingTitle(obj){
	var title = $(obj).val();
	var orgTitle = $(obj).attr("attrPublishingTitle");
	if( title == orgTitle )return false;
	var dutyId = $("#dutyId").val();
	var publishingAt = $("#publishingAt").val();
	var platForm = $("#platForm").val();
	$.ajax({
		url : _ctx + '/publishing/repeat.jhtml',
		type : 'POST',
		data : "platForm=" + platForm+"&title="+title+"&dutyId="+dutyId+"&publishingAt="+publishingAt,
		dataType : 'json',
		success : function(data) {
			if(data.rtnCode == '0000') {
				if( !data.retData ) {
					alert("存在相同记录");
					$("#btnNext").attr("disabled", true);
				}else{
					$("#btnNext").removeAttr("disabled");
				}
			}
		}
	});
}

//设置测试人员
function setTesters(obj){
	var coll = [];
	var spanData = $("#spanNextMsg").text();
	var explain = spanData.split("（");
	$(obj).parent().parent().find("input").each(function(){
		if($(this).is(":checked") === true){
			coll.push($(this).attr("attrUserName"));
		}
	});
	$("#spanNextMsg").text(explain[0]+"（"+coll.join(', ')+"）");
}

//是否负载均衡下一步提示
function setNotIsLoadBalanceMessages(){
	var msg = $("#bizTestEvnVerifyMessage").val();
	$("#spanNextMsg").text(msg);
}

function setIsLoadBalanceMessages(){
	var msg = $("#prodDbApproveMessage").val();
	$("#spanNextMsg").text(msg);
}

//预主干链接弹出框
var moduleId;
var dialogDemo = $("#dialogDemo").dialog({
	title:"预主干链接",
	backdrop:"static",
	modalSize:"medium", /*large, medium, small*/
	width:"550",
	height:"160",
	keyboard:false,
	autoOpen:false,
	buttons:[
		{
			text:"保存",
			btnClass:"btn-success",
			click:function(){
				var _this = this;
				var url = $("#url").val().trim();
				if(url == ""){
					$.alert("预主干链接地址不能为空！");
				}else if(url.length > 500){
					$.alert("长度不能超过500！");
				}else{
					if( typeof(moduleId) == 'undefined' || moduleId == "" || moduleId == null){
						$.alert("更新预主干链接地址成功",function(){ 
							targetLinkBtn.parent().find("input[type=hidden]").val(url);
							targetLinkBtn.html("链接详情");
							targetLinkBtn.attr("data-url",url);
							targetLinkBtn = null;
							_this.close();
						});

					}else{
						$.alert("更新预主干链接地址成功",function(){
							targetLinkBtn.parent().find("input[type=hidden]").val(url);
							targetLinkBtn.html("链接详情");
							targetLinkBtn.attr("data-url",url);
							targetLinkBtn = null;
							_this.close();
					    });
//						$.ajax({
//							url : _ctx + '/publishing/publishingProcess/addUrl.jhtml',
//							type : 'POST',
//							data : "moduleId=" +moduleId+"&url="+url,
//							dataType : 'json',
//							success : function(data) {
//								if(data.rtnCode == '0000') {
//									$.alert("更新预主干链接地址成功",function(){ 
//										_this.close();
//										targetLinkBtn.html("链接详情");
//										targetLinkBtn.parent().find("input[type=hidden]").val(url);
////										targetLinkBtn.attr("db_newUrl",url);
//									});
//								}else{
//									$.alert("更新预主干链接地址失败",function(){ 
//										_this.close();
//										targetLinkBtn.html("链接详情");
//										targetLinkBtn.parent().find("input[type=hidden]").val(url);
//									});
//								}
//							}
//						});
					}
				}
			}
		},
		{
			text:"取消",
			btnClass:"btn-default",
			click:function(){
				console.log("取消！");
				this.close();
			}
		}
	]
});

dialogDemo.on("shown", function(){
	if( typeof(moduleId) == 'undefined' || moduleId == "" || moduleId == null){
		$("#url").val(targetLinkBtn.attr(""));
		if(!targetLinkBtn) return;
		$("#url").val(targetLinkBtn.attr("data-url"));
	}else{
		$("#url").val(targetLinkBtn.attr("data-url"));
	}
	
});


//没有修改权限的
var dialogDemo_noAuth = $("#dialogDemo_noAuth").dialog({
	title:"预主干链接",
	backdrop:"static",
	modalSize:"medium", /*large, medium, small*/
	width:"550",
	height:"160",
	keyboard:false,
	autoOpen:false,
	buttons:[
		{
			text:"返回",
			btnClass:"btn-default",
			click:function(){
				this.close();
			}
		}
	]
});

dialogDemo_noAuth.on("shown",function(){
	$.ajax({
		url : _ctx + '/publishing/publishingProcess/getModule.jhtml',
		type : 'POST',
		data : "moduleId=" +moduleId,
		dataType : 'json',
		success : function(data) {
			if(data.rtnCode == '0000') {
				$("#url_noAuth").val(data.url);
			}else{
				$.alert("查询预主干链接地址失败",function(){ 
					location.reload();
				});
			}
		}
	});
});

//版本确定环节，添加预主干链接，弹出框
var yzgLinkAdd = $("#yzgLinkAdd").dialog({
	title:"预主干链接",
	backdrop:"static",
	modalSize:"medium", /*large, medium, small*/
	width:"550",
	height:"160",
	keyboard:false,
	autoOpen:false,
	buttons:[
	 		{
	 			text:"保存",
	 			btnClass:"btn-success",
	 			click:function(){
	 				var _this = this;
	 				var url = $("#url_add").val().trim();
	 				if(url == ""){
	 					$.alert("预主干链接地址不能为空！");
	 				}else if(url.length > 500){
	 					$.alert("长度不能超过500！");
	 				}else{
 						$.alert("更新预主干链接地址成功",function(){ 
 							targetLinkBtn.parent().find("input[type=hidden]").val(url);
 							targetLinkBtn.html("链接详情");
 							targetLinkBtn.attr("data-url",url);
 							targetLinkBtn = null;
 							_this.close();
 						});
 						_this.close();
 						return;
	 					if( typeof(moduleId) == 'undefined' || moduleId == "" || moduleId == null){

	 					}else{
	 						$.ajax({
	 							url : _ctx + '/publishing/publishingProcess/addUrl.jhtml',
	 							type : 'POST',
	 							data : "moduleId=" +moduleId+"&url="+url,
	 							dataType : 'json',
	 							success : function(data) {
	 								if(data.rtnCode == '0000') {
	 									$.alert("更新预主干链接地址成功",function(){ 
	 										_this.close();
	 										targetLinkBtn.html("链接详情");
	 										targetLinkBtn.parent().find("input[type=hidden]").val(url);
	 									});
	 								}else{
	 									$.alert("更新预主干链接地址失败",function(){ 
	 										_this.close();
	 										targetLinkBtn.html("链接详情");
	 										targetLinkBtn.parent().find("input[type=hidden]").val(url);
	 									});
	 								}
	 							}
	 						});
	 					}
	 				}
	 			}
	 		},
	 		{
	 			text:"取消",
	 			btnClass:"btn-default",
	 			click:function(){
	 				console.log("取消！");
	 				this.close();
	 			}
	 		}
	 	]
	 });

yzgLinkAdd.on("shown", function() {
	$.ajax({
		url : _ctx + '/publishing/publishingProcess/getModule.jhtml',
		type : 'POST',
		data : "moduleId=" + moduleId,
		dataType : 'json',
		success : function(data) {
			if (data.rtnCode == '0000') {
				if( data.url == null || data.url == '' ){
					$("#url_add").val(targetLinkBtn.attr('data-url'));
				}else{
					$("#url_add").val(data.url);
				}
			} else {
				$.alert("查询预主干链接地址失败", function() {
					location.reload();
				});
			}
		}
	});
});


//删除模块根据id
function deleteModuleById(_confirmObj, moduleId, type){
	if( typeof(moduleId) == 'undefined' )return;
	$.confirm("确认是否删除！", {
		confirm:function(){
			var _trs = $("#trModules").find("tr");
			if( type == 'otherDel' ){
				_trs.eq(0).find(".moduleName-input2").attr("notCheckValidate", "0").attr("placeholder","请填写").val("").removeAttr("disabled").end().find(".yzgLink1").html("添加链接").end()
				_trs.eq(0).find(".moduleName-input2").attr("notCheckValidate", "0").attr("placeholder","请填写").val("").removeAttr("disabled").end()
				.find(".select2-container").remove().end()
				.find('[data-toggle="select"]').removeAttr("disabled").find("option").removeAttr("selected").end().select2();
				_confirmObj.find("input[type=hidden]").val("");
				_trs.eq(0).find(".yzgLink1").attr("data-url", "")
			}else{
				$(_confirmObj).parent().parent().prev().find(".plus-btn").removeAttr("style").removeAttr("disabled");
				$(_confirmObj).parent().parent().remove();
			}
			
			$("#trModules").find("tr").each(function(k){
				$(this).find("td:first").html(k+1);
			});
			
			$.ajax({
				url : _ctx+"/publishing/deletePModuleById.jhtml",
				type : 'POST',
				data : "moduleId="+moduleId,
				dataType : 'json',
				success : function(data) {
					if( data.rtnCode != '0000' ){
						setTimeout(function(){
							$.alert("数据错误", function(){
								location.reload();
							});
							
						},500);
					}
				}
			});
		},
		cancel:function(){
			return false;
		}
	});
}