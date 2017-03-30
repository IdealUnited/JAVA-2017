// Some general UI pack related JS
// Extend JS String with repeat method
String.prototype.repeat = function (num) {
  return new Array(Math.round(num) + 1).join(this);
};

(function ($) {
	
  $(function () {

    /* Todo list
    $('.todo').on('click', 'li', function () {
      $(this).toggleClass('todo-done');
    });
	*/
	
	//Nav menubar
	var st;
	$(".beacon-nav >.dropdown").on('mouseleave',function(){
		var _this = this;
		st = setTimeout(function(){$(_this).removeClass("open");$(_this).find(">a").blur();},1000);
	}).on('mouseenter',function(){
		var _this = this;
		clearTimeout(st);
	});
	
    // Custom Selects
    if ($('[data-toggle="select"]').length) {
      $('[data-toggle="select"]').select2();
    }

    // Checkboxes and Radio buttons
    $('[data-toggle="checkbox"]').radiocheck();
    $('[data-toggle="radio"]').radiocheck();

    // Tooltips
    $('[data-toggle=tooltip]').tooltip();
	

    // Focus state for append/prepend inputs
    $('.input-group').on('focus', '.form-control', function () {
      $(this).closest('.input-group, .form-group').addClass('focus');
    }).on('blur', '.form-control', function () {
      $(this).closest('.input-group, .form-group').removeClass('focus');
    });

    // Make pagination demo work
    $('.pagination').on('click', 'a', function () {
      $(this).parent().siblings('li').removeClass('active').end().addClass('active');
    });

    $('.btn-group').on('click', 'a', function () {
      $(this).siblings().removeClass('active').end().addClass('active');
    });

    // Disable link clicks to prevent page scrolling
    $(document).on('click', 'a[href="#fakelink"]', function (e) {
      e.preventDefault();
    });

    // Switches
    if ($('[data-toggle="switch"]').length) {
      $('[data-toggle="switch"]').bootstrapSwitch();
    }
    

  });
  
  /*alert, confirm, prompt, dialog based bootstrap modal, create by Quan*/
	(function(){
		var alertTmp = $('<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"><div class="modal-dialog modal-sm"><div class="modal-content"><div class="modal-header"><button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><div class="modal-title" id="myModalLabel">&nbsp;</div></div><div class="modal-body">内容在此...</div><div class="modal-footer"><button type="button" class="btn btn-default btn-sm btn-cancel" data-dismiss="modal">取消</button><button type="button" class="btn btn-warning btn-sm">确定</button></div></div></div></div>');
		$.alert = function(message,callback){
			alertTmp.find(".modal-body").html(message).end()
					.find(".btn-cancel").hide();
			alertTmp.modal({backdrop:"static",keyboard:false,show:true});
			alertTmp.find(".btn-warning").click(function(){
				alertTmp.modal("hide");
				if(callback) callback();
			});
			alertTmp.on("hidden.bs.modal",function(e){
					alertTmp.remove();
				});
		}
		$.confirm = function(message,callbacks){
			alertTmp.find(".modal-body").html(message).end()
					.find(".btn-cancel").show();
			alertTmp.modal({backdrop:"static",keyboard:false,show:true});
			alertTmp.find(".btn-warning").click(function(){
				alertTmp.modal("hide");
				if(callbacks.confirm) callbacks.confirm();
			}).end()
			.find(".btn-cancel").click(function(){
				if(callbacks.cancel) callbacks.cancel();
			});
			alertTmp.on("hidden.bs.modal",function(e){
					alertTmp.remove();
				});
		}
		$.prompt = function(message,callbacks){
			var ipt = $('<input type="text" style="margin-top:5px;" class="form-control" placeholder="'+ message +'">');
			alertTmp.find(".modal-body").html(message).append(ipt).end()
					.find(".btn-cancel").show();
			alertTmp.modal({backdrop:"static",keyboard:false,show:true});
			alertTmp.find(".btn-warning").click(function(){
				alertTmp.modal("hide");
				if(callbacks.confirm) callbacks.confirm(ipt.val());
			}).end()
			.find(".btn-cancel").click(function(){
				if(callbacks.cancel) callbacks.cancel(ipt.val());
			});
			alertTmp.on("hidden.bs.modal",function(e){
					alertTmp.remove();
				});
		}
		
		
		$.fn.dialog = function(options){
			
			var apiObj = {},
				instances = [];
			
			var _title 		= options.title || "",
				_backdrop	= options.backdrop || true,
				_keyboard	= options.keyboard || true,
				_autoOpen	= options.autoOpen || false,
				_buttons	= options.buttons || false,
				modalSize	= function(size){
									var _size;
									switch(size){
										case "small":
											_size = "modal-sm";
											break;
										case "large":
											_size = "modal-lg";
											break;
										default:
											_size = "medium";
									}
									return _size;
								},
				_modalSize	= modalSize(options.modalSize),
				_width		= options.width || false,
				_height		= options.height || false;
				
			$(this).each(function(){
				var dialogTmp = alertTmp.clone();
				var apiObj = {};
				$(this).removeClass("hidden");
				_title		= $(this).attr("title") || _title;
				_modalSize	= modalSize($(this).attr("data-modalSize"));
				
				var modalFooter = dialogTmp.find(".modal-body").html("").append(this).end().find(".modal-footer").empty();
					dialogTmp.find(".modal-title").html(_title).end()
							 .find(".modal-dialog").removeClass("modal-sm").addClass(_modalSize);
					if(_width) dialogTmp.find(".modal-dialog").css({width:_width});
					if(_height) dialogTmp.find(".modal-body").css({height:_height, overflow:"auto"});
					
				instances.push(dialogTmp.modal({backdrop:_backdrop,keyboard:_keyboard,show:_autoOpen}));
				if(_buttons && _buttons.length>0){
					$(_buttons).each(function(){
						var self = this,
							btn_class = this.btnClass || "btn-warning";
						
						var _btn = $('<button class="btn btn-sm">'+ this.text +'</button>').addClass(btn_class)
																		.click(function(){
																			apiObj.click = self.click;
																			apiObj.click();
																			
																		}).appendTo(modalFooter);
					});
				}
				
				
				apiObj.open = function(){
					if(!dialogTmp){
							alert("错误！窗口已被销毁！");
							return false;
						}
					dialogTmp.modal("show");
				}
				apiObj.close = function(){
					return dialogTmp.modal("hide");
				}
				apiObj.destroy = function(){
					if(!dialogTmp){
							alert("错误！窗口已被销毁！");
							return false;
						}
					dialogTmp.modal("hide").on("hidden.bs.modal",function(e){
						dialogTmp.remove();
						dialogTmp = null;
						instances.splice(instances.indexOf(dialogTmp),1);
					});
				}
			});
			
			
			var onceShownHandlers=[],onceHidenHandlers=[];
			
			instances[0].on('shown.bs.modal', function(){
				for(k in onceShownHandlers)	if(typeof onceShownHandlers[k] === "function") onceShownHandlers[k]();
			});
			instances[0].on('hiden.bs.modal', function(){
				for(k in onceHidenHandlers) if(typeof onceHidenHandlers[k] === "function") onceHidenHandler[k]();
			});
			
			
			apiObj = {
				open:		function(){
									$(instances).each(function(){
										this.modal("show");
									});
									return true;
								},
				close:		function(){
									$(instances).each(function(){
										this.modal("hide");
									});
									return true;
								},
				destroy:	function(){
									$(instances).each(function(){
										this.remove();
										this.trigger("hidden.bs.modal");
									});
									instances = null;
									return true;
								},
				on:			function(evt,callback){
									switch(evt){
										case "shown":
											$(instances).each(function(){
												this.on('shown.bs.modal', callback);
											});
											break;
										case "hidden":
											$(instances).each(function(){
												this.on('hidden.bs.modal', callback);
											});
											break;
										case "show":
											$(instances).each(function(){
												this.on('show.bs.modal', callback);
											});
											break;
										case "hide":
											$(instances).each(function(){
												this.on('hide.bs.modal', callback);
											});
											break;
									}
					
								},
				once:		function(evt,callback){
									switch(evt){
										case "shown":
											if(onceShownHandlers.indexOf(callback) < 0) onceShownHandlers.push(callback);
										break;
										case "hidden":
											if(onceHidenHandlers.indexOf(callback) < 0) onceHidenHandlers.push(callback);
										break;
									}
								},
				getBtModal:	function(){
									return instances[0];
							},
				getBtModals:function(){
									return instances;
					},
				setTitle:	function(title){
					instances[0].find(".modal-title").html(title);
				}
			}
			
			return apiObj;
		}
		

	})();

	/*paginationFree 2015.6.12 Quan*/
	(function(){

		$.fn.freePaginator = function(options){
			
			console.log($(this));
			var _this = $(this);
			var jumpCallback;
			var totalPageNum = _this.find(".pagination .num").length, eachCircle = 10, currentPageNum = _this.find(".pagination-jumper .pages-num").val();
			
			_this.find(".pagination .num").eq(currentPageNum-1).addClass("active").end().hide();
			var pagination_m = (function(){
				return Math.floor((currentPageNum-1)/eachCircle);
			})();
			
			function toPage(multi){
				_this.find(".pagination .num").hide(); 
				var l = _this.find(".pagination .num:gt("+ multi*eachCircle + "):lt(" + (eachCircle-1) + ")").show();
				if(l.length< eachCircle-1) _this.find(".pagination .num:gt("+ (totalPageNum-eachCircle-1) + ")").show();
				_this.find(".pagination .num").eq(multi*eachCircle).show();
			}
			toPage(pagination_m);
			
			function pageTurning(){
				_this.find(".pagination .num").hide();
				var _start = _this.find(".pagination .num:visible").last().index();
				_this.find(".pagination .num:gt("+ _start + "):lt(" + (_start+10) + ")").show();
			}

			_this.find(".pagination .num a").click(function(){
				var targetNum = $(this).html();
				_this.find(".pagination-jumper .pages-num").val(targetNum);
				if(typeof jumpCallback === "function") jumpCallback(targetNum);
				currentPageNum = targetNum;
				
				/*
				var _start = _this.find(".pagination .num:visible").first().index();
				if((_start + eachCircle -1) > totalPageNum){
					_this.find(".pagination a[aria-label=Next]").css("color","#ccc");
				}
				else{
					_this.find(".pagination a[aria-label=Next]").removeAttr("style");
				}
				*/
				
				if(_this.find(".pagination .num:visible").eq(0).index() == targetNum){
					if(targetNum == 1){
						_this.find(".pagination a[aria-label=Previous]").css("color","#ccc");
						return;
					}
					_this.find(".pagination a[aria-label=Next]").removeAttr("style");
					_this.find(".pagination .num:visible").eq(0).prev().show().end().end().last().hide();
				}
				if(_this.find(".pagination .num:visible:last").index() == targetNum){
					if(targetNum == totalPageNum){
						_this.find(".pagination a[aria-label=Next]").css("color","#ccc");
						return;
					}
					_this.find(".pagination a[aria-label=Previous]").removeAttr("style");
					_this.find(".pagination .num:visible").last().next().show().end().end().first().hide();
				}
			});
			
			_this.find(".pagination a[aria-label=Previous]").click(function(){
				var _start = _this.find(".pagination .num:visible").first().index();
				_this.find(".pagination .num").hide();
				
				if((_start-eachCircle-2) <0){
					_this.find(".pagination .num:lt(" + eachCircle + ")").show();
					_this.find(".pagination a[aria-label=Previous]").css("color","#ccc");
				}
				else{
					_this.find(".pagination .num:gt("+ (_start-eachCircle-2) + "):lt(" + eachCircle + ")").show();
					_this.find(".pagination a[aria-label=Next]").removeAttr("style");
				}
				
				return false;

				if(pagination_m == 0) return false;
				toPage(--pagination_m);
				if(pagination_m == 0){
					_this.find(".pagination a[aria-label=Previous]").css("color","#ccc");
				}else{
					_this.find(".pagination a[aria-label=Next]").removeAttr("style");
				}
					return false;
			});
			_this.find(".pagination a[aria-label=Next]").click(function(){
				var _start = _this.find(".pagination .num:visible").last().index();
				_this.find(".pagination .num").hide();
				if((_start + eachCircle) > totalPageNum){
					_start = totalPageNum - eachCircle;
					_this.find(".pagination a[aria-label=Next]").css("color","#ccc");
				}
				else{
					_this.find(".pagination a[aria-label=Previous]").removeAttr("style");
				}
				_this.find(".pagination .num:gt("+ (_start-1) + "):lt(" + eachCircle + ")").show();
				
				return false;
				
				if(pagination_m >= Math.floor((totalPageNum-1)/eachCircle)) return false;
				toPage(++pagination_m);
				if(pagination_m >= Math.floor((totalPageNum-1)/eachCircle)){
					_this.find(".pagination a[aria-label=Next]").css("color","#ccc");
				}else{
					_this.find(".pagination a[aria-label=Previous]").removeAttr("style");
				}
				return false;
			});
			_this.find(".pagination-jumper .pages-num").keydown(function(e){
				if(e.keyCode === 13){
					var targetNum = _this.find(".pagination-jumper .pages-num").val();
					if(typeof jumpCallback === "function") jumpCallback(targetNum);
					toPage(Math.floor((targetNum-1)/eachCircle));
					_this.find(".pagination .num a").eq(targetNum-1).click();
					
					if(_this.find(".pagination .num:visible").first().index()<=1){
						_this.find(".pagination a[aria-label=Previous]").css("color","#ccc");
					}else{
						_this.find(".pagination a[aria-label=Previous]").removeAttr("style");
					}
					
					if(_this.find(".pagination .num:visible").last().index()>=totalPageNum){
						_this.find(".pagination a[aria-label=Next]").css("color","#ccc");
					}else{
						_this.find(".pagination a[aria-label=Next]").removeAttr("style");
					}
					
					return false;
				}
			});
			
			return {
				jumper:function(callback){
					console.log("callback" + typeof callback);
					if(typeof callback === "function") jumpCallback = callback;
				}
			
			};
		}





	})();



  
  
})(jQuery);
