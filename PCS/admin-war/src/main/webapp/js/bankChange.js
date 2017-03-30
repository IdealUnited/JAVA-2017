
//Map结构
var ThisMap = function () {       
	var struct = function(key, value) {       
		this.key = key;       
		this.value = value;       
	}        
	var put = function(key, value){       
		for (var i = 0; i < this.arr.length; i++) {       
			if ( this.arr[i].key === key ) {       
				this.arr[i].value = value;       
				return;       
			}       
		}       
		this.arr[this.arr.length] = new struct(key, value);       
	}         
	var get = function(key) {       
		for (var i = 0; i < this.arr.length; i++) {       
			if ( this.arr[i].key === key ) {       
				return this.arr[i].value;       
			}       
		}       
		return null;       
	}         
	var remove = function(key) {       
		var v;       
		for (var i = 0; i < this.arr.length; i++) {       
			v = this.arr.pop();       
			if ( v.key === key ) {       
				continue;       
			}       
			this.arr.unshift(v);       
		}       
	}       
	var size = function() {       
		return this.arr.length;       
	} 
	var getAll = function (){
		return this.arr ;
	}
	var isEmpty = function() {       
		return this.arr.length <= 0;       
	}       
	this.arr = new Array();       
	this.get = get;       
	this.put = put;       
	this.remove = remove;       
	this.size = size;       
	this.isEmpty = isEmpty;       
}           
//银行别名
var bankCodeArray = new Array ( 	
									"CNPY",//银联
									"CCB",//建设银行
									"HXB",//华夏银行
									"CIB",//兴业银行
									"JCARD",//骏网
									"ABC",//农业银行
									"SDB",//深发展银行
									"ECITIC",//中信银行
									"ICBC",//工商银行
									"CMBC",//民生银行
									"CEB",//光大银行
									"BJRCB",//北京农村商业银行
									"POST",//邮政储蓄银行
									"COMM",//交通银行
									"SPDB",//浦发银行
									"BCCB",//北京银行
									"BEA",//东亚银行
									"BOC",//中国银行
									"NBB",//宁波银行
									"GDB"//广发银行
									) ;
//银行名称
var bankArray =  new Array ( 	
									"银联",
									"建设银行",
									"华夏银行",
									"兴业银行",
									"骏网支付",
									"农业银行",
									"深发展银行",
									"中信银行",
									"工商银行",
									"民生银行",
									"光大银行",
									"北京农村商业银行",
									"邮政储蓄银行",
									"交通银行",
									"浦发银行",
									"北京银行",
									"东亚银行",
									"中国银行",
									"宁波银行",
									"广发银行"
									) ;
//网关对应的解析器类型编号
var valueArray = new Array ( 
								 new Array( "002","022"),//银联，银联(借记卡)
								 "001",//建设银行
								 new Array( "011","027"),//华夏b2c，华夏b2b
								 "016",//兴业银行
								 "026",//骏网
								 "008",//农业银行
								 "009",//深发展银行
								 "010",//中信银行
								 "012",//工商银行
								 new Array("028","013"),//民生银行
								 "014",//光大银行
								 "015",//北京农村商业银行
								 "017",//邮政储蓄银行
								 "018",//交通银行
								 "019",//浦发银行
								 "020",//北京银行
								 "021",//东亚银行
								 "023",//中国银行
								 "024",//宁波银行
								 "025"//广发银行
								 ) ;
//网关名称
var nArray = new Array ( 
								new Array("银联支付B2C网关","银联(借记卡)网关"),
								"建设银行B2C网关",
								new Array("华夏银行B2C网关","华夏银行B2B网关"),			
								"兴业银行B2C网关",		
								"骏网支付网关",		
								"农业银行B2C网关",
								"深发展银行B2C网关",
								"中信银行B2C网关",
								"工商银行B2C网关",
								new Array("民生银行B2C网关","民生银行借记卡网关"),
								"光大银行B2C网关",
								"北京农商银行B2C网关",
								"邮政储蓄银行B2C网关",
								"交通银行B2C网关",
								"浦发银行B2C网关",
								"北京银行B2C网关",
								"东亚银行网关",
								"中国银行B2C网关",
								"宁波银行B2C网关",
								"广发银行B2C网关") ;
//网关和商户号 关联
var procMap = new ThisMap () ;
procMap.put ( "001" , "1" ) ;	//建设银行B2C网关
procMap.put ( "002" , "2" ) ;	//银联B2C网关
procMap.put ( "022" , "3" ) ;	//银联(借记卡)网关
procMap.put ( "011" , "4" ) ; 	//华夏银行B2C网关
procMap.put ( "027" , "44" ) ; 	//华夏银行B2B网关
procMap.put ( "016" , "5" ) ;	//兴业银行B2C网关
procMap.put ( "008" , "6" ) ;	//农业银行B2C网关
procMap.put ( "009", "7" ) ;	//深发展银行B2C网关
procMap.put ( "010", "8" ) ;	//中信银行B2C网关
procMap.put ( "012", "9" ) ;	//工商银行B2C网关
procMap.put ( "013", "10" ) ;	//民生银行借记卡网关
procMap.put ( "028", "19" ) ;	//民生银行B2C网关
procMap.put ( "014", "11" ) ;	//光大银行B2C网关
procMap.put ( "015", "12" ) ;	//北京农商银行B2C网关
procMap.put ( "017", "13" ) ;	//邮政储蓄银行B2C网关
procMap.put ( "018", "14" ) ;	//交通银行B2C网关
procMap.put ( "019", "15" ) ;	//浦发银行B2C网关
procMap.put ( "020", "16" ) ;	//北京银行B2C网关
procMap.put ( "021", "17" ) ;	//东亚银行B2C网关
procMap.put ( "023", "20" ) ;	//中国银行B2C网关
procMap.put ( "026", "18" ) ;	//骏网B2C网关


//商户号数据
var custMap = new ThisMap () ;
custMap.put ( "1"  	, "105290048140249,建设银行商户号" ) ;
custMap.put ( "2"  	, "808080580003711,银联" ) ;
custMap.put ( "3"  	, "808080580003725,银联(借记卡)") ;
custMap.put ( "4"  	, "2100660048,华夏B2C" ) ;
custMap.put ( "44"  , "2100670873,华夏B2B" ) ;
custMap.put ( "5" 	, "210163,兴业" ) ;
custMap.put ( "18" 	, "1285194,骏网" ) ;

custMap.put ( "6" 	, "231000001760A01,农业银行商户号" ) ;
custMap.put ( "7" 	, "10015,深发展银行测试商户号001" ) ;
custMap.put ( "8" 	, "10016,中信银行测试商户号001" ) ;
custMap.put ( "9" 	, "1001EC23715693,工商银行测试商户号001" ) ;
custMap.put ( "10" 	, "01206,民生银行借记卡" ) ;
custMap.put ( "19" 	, "01207,民生银行B2C" ) ;
custMap.put ( "11" 	, "10019,光大银行测试商户号001" ) ;
custMap.put ( "12" 	, "10020,北京农村银行测试商户号001" ) ;
custMap.put ( "13" 	, "10021,邮政银行测试商户号001" ) ;
custMap.put ( "14" 	, "10022,交通银行测试商户号001" ) ;
custMap.put ( "15" 	, "10023,浦发银行测试商户号001" ) ;
custMap.put ( "16" 	, "10024,北京银行测试商户号001" ) ;
custMap.put ( "17" 	, "1761000047,东亚银行商户号" ) ;
custMap.put ( "20" 	, "104310153006525,中国银行商户号" ) ;

//银行下拉列表框初始化
function bankSelectInit ( selectObjId , firstTxt ) {
	var selectObj = document.getElementById(selectObjId) ;
	if ( !firstTxt ){
		firstTxt = "" ;
	}
	selectObj.options[0]=new Option(firstTxt,"");
	for(var i=0; i<bankCodeArray.length; i++){
		  var oOption = new Option(bankArray[i],bankCodeArray[i]);
		  selectObj.options[i+1]=oOption;
	}
}
//银行网关下拉列表框初始化
function bankCodeSelectInit ( selectObjId ,firstTxt ) {
	var selectObj = document.getElementById(selectObjId) ;
	if ( !firstTxt ){
		firstTxt = "" ;
	}
	selectObj.options[0] = new Option(firstTxt,"");
	var cnt = 1;
	for(var i=0; i<nArray.length; i++){
		var nbank = nArray[i] ;
		if ( typeof(nbank) != "string" ) {
			var va = valueArray[i] ;
			for ( var mn=0;mn<nbank.length; mn++,cnt++){
				var nb = nbank[mn] ;
				var oOption = new Option(nb,va[mn]);
				selectObj.options[i+cnt]=oOption;
			}
			cnt-- ;
		}
		else {
			var oOption = new Option(nbank,valueArray[i]);
			selectObj.options[i+cnt]=oOption;
		}
	}
}

//网关下拉框选择改变(商户号为下拉框)
function changeProviderCode ( id , obj ) {
	$("#"+id ).html("") ;
	var selValue = obj.value ;
	//根据网关编号获得商户信息
	var busiConfig = procMap.get ( selValue ) ;
	if ( !busiConfig ) {
		return ;
	}
	var selectHtml = "&nbsp;选择商户号:<select id='merchantId' style='width:150px' name='merchantId'></sclect>" ;
	$("#"+id ).html( selectHtml ) ;
	var selectObj = document.getElementById('merchantId') ;
	var bcs = busiConfig.split ( "," ) ;
	for ( var cnt=0 ; cnt<bcs.length ; cnt++ ){
		var bc = bcs[cnt] ;
		//获取商户号信息
		var busiInfo = custMap.get( bc ) ;
		var busis = busiInfo.split ( "," ) ;
		var key = busis[0] ;
		var value = busis[1] ;
		var oOption = new Option( value,key);
		selectObj.options[cnt] = oOption;
	}
}
//银行下拉框选择改变(网关选项为下拉框)
function changeBankInfo2( id ,id2, obj ){
	$("#"+id ).html("") ;
	$("#"+id2 ).html("") ;
	for ( var cnt=0 ;cnt<bankCodeArray.length ; cnt++){
		var sv = bankCodeArray[cnt];
		if ( sv == obj.value ){
			var selectHtml = "&nbsp;选择网关:<select id='providerCode' style='width:150px' name='providerCode' onchange='changeProviderCode(\""+id2+"\",this);'></sclect>" ;
			$("#"+id ).html( selectHtml ) ;
			var selectObj = document.getElementById('providerCode') ;
			var n = nArray[cnt] ;
			//银行只有一个网关
			if ( typeof(n) == "string" ) {
				var oOption = new Option( n,valueArray[cnt]);
				selectObj.options[0]=oOption;
			}
			else {
				//银行存在多个网关
				var vArray = valueArray[cnt] ;
				for ( var xy=0;xy<n.length;xy++){
					var sn = n[xy] ;
					var oOption = new Option(sn,vArray[xy]);
					selectObj.options[xy]=oOption;
				}
			}
			changeProviderCode ( id2,selectObj ) ;
			return ;
		}
	}
}

//银行下拉框选择改变(网关选项为单选框)
function changeBankInfo(id , obj){
	$("#"+id ).html("") ;
	for ( var cnt=0 ;cnt<bankCodeArray.length ; cnt++){
		var sv = bankCodeArray[cnt];
		if ( sv == obj.value ){
			var n = nArray[cnt] ;
			//银行只有一个网关
			if ( typeof(n) == "string" ) {
				$("#"+id ).html('<input type="radio" name="providerCode" value="'+ valueArray[cnt] +'"/>'+ n);
			}
			else {
				//银行存在多个网关
				var il = "" ;
				var va = valueArray[cnt]  ;
				for ( var xy=0;xy<n.length;xy++){
					var sn = n[xy] ;
					il += '<input type="radio" name="providerCode" value="'+ va[xy] +'"/>'+ sn ;
				}
				$("#"+id ).html( il ) ;
			}
			
		}
	}
	var pcs = document.getElementsByName("providerCode");
	if ( pcs && pcs.length > 0 ){
		pcs[0].checked = "checked" ;
	}
}
