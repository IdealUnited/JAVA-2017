$( function() {
	$("#btnHoliday").click(function() {
		var days = $.trim($("#day").val());
		var month = $("#month").val();
		var year = $("#year").val();
		var lastDay = parseInt($("#lastDay").val());
		if( year == '' ){
			alert("年份不能为空");
			return false;
		}
		if( month == '' ){
			alert("月份不能为空");
			return false;
		}
		if( days == '' ){
			alert("假日日期不能为空");
			return false;
		}
		if( days == 0 ){
			alert("日期非法，请重新输入");
			return false;
		}
		if(/^[\d,]*$/.test(days)){
			var dayArray = days.split(",");
			var isSubmit = true;
			var exisitDays = [];
			for(var i in dayArray){
				var dayVal = parseInt(dayArray[i]);
				if( dayArray[i] == 0 || dayArray[i] == '' ){
					alert("日期非法，请重新输入");
					isSubmit = false;
					break;
				}
				if( !(dayVal <= lastDay) ){
					alert("日期非法，本月最后一天为"+lastDay);
					isSubmit = false;
					break;
				}
				if( exisitDays.length > 1 ){
					for(var d in exisitDays){
						if( exisitDays[d] == dayVal ){
							alert("日期非法，请重新输入");
							isSubmit = false;
							break;
						}
					}
				}
				exisitDays[dayArray[i]] = parseInt(dayArray[i]);
			}
			if( isSubmit ){
				var id = $("#id").val();
				$.ajax({
					url : _ctx + '/system/holiday/updateHoliday.jhtml',
					type : 'POST',
					data : "year="+year+"&month="+month+"&days="+days+"&id="+id,
					dataType : 'json',
					success : function(data) {
						if(data.retCode == '2222') {
							alert(data.retData+"存在发布计划，请移除");
						}
						location.href = _ctx + "/system/holiday/list.jhtml";
					}
				});
				//$("#frmHoliday").submit();
			}
		}else{
			alert("输入日期非法，请重新输入");
		}
	});
	
	//根据年，月，获取当月最后一天
	
	$("#year").change(function(){
		var year = $("#year").val();
		var month = $("#month").val();		
		if( year == '' || month == '' )return;
		$.ajax({
			url : _ctx + '/system/holiday/getLastDayByMonth.jhtml',
			type : 'POST',
			data : "year="+year+"&month="+month,
			dataType : 'json',
			success : function(data) {
				if(data.retCode == '0000') {
					$("#lastDay").val(data.retData);
				}else{
					$("#lastDay").val(31);
				}
			}
		});
	});
	
	$("#month").change(function(){
		var year = $("#year").val();
		var month = $("#month").val();
		if( year == '' || month == '' )return;
		$.ajax({
			url : _ctx + '/system/holiday/getLastDayByMonth.jhtml',
			type : 'POST',
			data : "year="+year+"&month="+month,
			dataType : 'json',
			success : function(data) {
				if(data.retCode == '0000') {
					$("#lastDay").val(data.retData);
				}else{
					$("#lastDay").val(31);
				}
			}
		});
	});
});

function getLastDayByMonth(){
	alert();
}

