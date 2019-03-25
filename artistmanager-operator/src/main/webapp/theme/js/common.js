/**
 * 公共部分
 */
var rootPath = "/operation";
var __lang = "zh";

// 手机、平板等媒介浏览访问时显示相应比例
$("head").append('<meta name="viewport" content="width=device-width, initial-scale=1">');
// 关键词
$("head").append('<meta name="keywords" content="accounting,mystyle">');

// 新 Bootstrap 核心 CSS 文件
$("head").append('<link href="'+rootPath+'/bootstrap/css/bootstrap.min.css" rel="stylesheet"></link>');
// 可选的Bootstrap主题文件（一般不使用）
$("head").append('<link href="'+rootPath+'/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet"></link>');
// 最新的 Bootstrap 核心 JavaScript 文件
$("head").append('<script src="'+rootPath+'/bootstrap/js/bootstrap.min.js"></script>');
$("head").append('<script type="text/javascript" src="'+rootPath+'/theme/js/jquery.i18n.properties-min-1.0.9.js"></script>');

// 引入bootstrap-table样式
$("head").append('<script type="text/javascript" src="'+rootPath+'/bootstrap/js/bootstrap-table.js"></script>');
$("head").append('<script type="text/javascript" src="'+rootPath+'/bootstrap/js/bootstrap-table-zh-CN.js"></script>');
$("head").append('<link href="'+rootPath+'/bootstrap/css/bootstrap-table.css" rel="stylesheet" />');

// 引用提示框jNotify插件
$("head").append('<script type="text/javascript" src="'+rootPath+'/theme/js/jNotify.jquery.js"></script> ');
$("head").append('<link rel="stylesheet" type="text/css" href="'+rootPath+'/theme/css/jNotify.jquery.css" /> ');

//引用提示框sweet插件
$("head").append('<script type="text/javascript" src="'+rootPath+'/theme/js/sweet-alert.js"></script> ');
$("head").append('<link rel="stylesheet" type="text/css" href="'+rootPath+'/theme/css/sweet-alert.css" /> ');
//引用常量定义 constants.js
$("head").append('<script type="text/javascript" src="'+rootPath+'/startcontrol/common/constants.js"></script> ');

$("head").append('<link rel="stylesheet" type="text/css" href="'+rootPath+'/theme/css/global.css" /> ');

// 前端校验器
$("head").append('<link href="'+rootPath+'/bootstrap/css/bootstrapValidator.min.css" /> ');
$("head").append('<script src="'+rootPath+'/bootstrap/js/bootstrapValidator.min.js"></script> ');

/**
 * 初始化
 */
$(function(){
	// 初始化国际化，此方法尽量放到第一位
	//initI18nMsg();
	
	// 设置必填项的红星号，必填项
	$(".red-star").each(function(){
		$(this).prepend('<span style="color:red;">*&nbsp;</span>');
	});
	
	// 设置查询 条件样式
	$(".panel-heading").attr("style","background:#D3EDFB;font-weight:bold;font-size:16px");
	
	// 设置commonBack按钮的默认事件
	$("#commonBackBtn").click(function(){
		// 返回上一页
		window.history.back();
	});
	
	if(sessionStorage["userInfo"]&&!/login.html/.test(window.location.href)){
		var obj=window;
		if(parent.document!=document){
			obj=parent;
		}
		var userInfo=JSON.parse(sessionStorage["userInfo"]);
			//前台超时设置  
		obj.resetTime(1000*userInfo.maxInactiveInterval);
			document.onclick=function(){obj.resetTime(1000*userInfo.maxInactiveInterval);};
	}
});

//注册清空事件
/**
 * @param addSearchformValidate 回调函数
 * @param formId formid
 * @param btnId btnid
 */
function bindClearFun(addSearchformValidate, formId, btnId) {
	// clear事件
	if (formId == undefined) {
		formId = "searchform";
	} 
	if (btnId == undefined) {
		btnId = "btn_clear";
	}
	// 清空查询条件button
	$("#"+btnId).click(function() {
		document.getElementById(formId).reset();
    	// 清空提示
        $("#"+formId).data('bootstrapValidator').destroy();
        $('#'+formId).data('bootstrapValidator', null);
        
        if(addSearchformValidate) {
        	addSearchformValidate();
        }
	});
}

// 注册查询事件
/**
 * @param refreshParam 回调函数
 * @param formId formid
 * @param btnId btnid
 */
function bindQueryFun(refreshParam, formId,btnId) {
	if (formId == undefined) {
		formId = "searchform";
	} 
	if (btnId == undefined) {
		btnId = "btn_query";
	}
	var bootstrapValidator = $("#"+formId).data('bootstrapValidator');
	$("#"+btnId).click(function() {
		// 开启验证
		bootstrapValidator.validate();
		// 校验通过
		if (!bootstrapValidator.isValid()) {
			return false;
		}
		if (refreshParam) {
			refreshParam();
		}
	});
}

function Ajax(param){
	if(!param || !param.url){
		// URL 必须传入
		return;
	}
	
	// 默认是异步请求true
	if(!param.async && param.async != false){
		param.async = true;
	}
	// HTTP请求类型
	if(!param.type){
		param.type = "POST";
	}
	// 参数
	if(!param.data){
		param.data = {};
	}
	// 参数类型
	if(!param.dataType){
		param.dataType = "json";
	}
	// 默认显示遮罩层
	if(!param.isLoading && param.isLoading != false){
		param.isLoading = true;
	}
	
	// 参数类型
	if (param.contentType == undefined) {
		param.contentType = "application/json;charset=utf-8";
	}
	//初始化公共参数
	initPublicParam(param.data)
	
	$.ajax({
        type : param.type,
        async : param.async, 
        url :  rootPath + param.url,
        data : JSON.stringify(param.data),
        dataType : param.dataType,
        contentType : param.contentType,  
        success : function(response) {
        	if(param.callback && typeof param.callback == "function"){
        		// 回调
        		param.callback(response);
            }
        },
		error : function(response) {
			console.log(response);
		}
    });
}


function AjaxUpload(param){
	if(!param || !param.url){
		// URL 必须传入
		return;
	}
	
	// 默认是异步请求false
	if(!param.async && param.async != false){
		param.async = false;
	}
	// HTTP请求类型
	if(!param.type){
		param.type = "POST";
	}
	// 参数
	if(!param.data){
		param.data = {};
	}
	// 参数类型
	if(!param.dataType){
		param.dataType = 'json';
	}
	// 默认显示遮罩层
	if(!param.isLoading && param.isLoading != false){
		param.isLoading = true;
	}
	
	// 参数类型
	if (param.contentType == undefined) {
		param.contentType = false;
	}
	//初始化公共参数
	initPublicParam(param.data)
	
	$.ajax({
        type : param.type,
        async : param.async, 
        url :  rootPath + param.url,
        data : param.data,
        dataType : param.dataType,
        contentType : param.contentType,  
        processData: false,
        cache: false,
        success : function(response) {
        	if(param.callback && typeof param.callback == "function"){
        		// 回调
        		param.callback(response);
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown){
        	alert(XMLHttpRequest);
        	alert(textStatus);
        	alert(errorThrown);
        }
    });
}

$.ajaxSetup( {  
    //设置ajax请求结束后的执行动作  
	beforeSend : function(param) {showRequestLoadding();},
    complete :   
    function(XMLHttpRequest, param) {
    	hideRequestLoadding();
        // 通过XMLHttpRequest取得响应头，sessionstatus  
        var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");  
        if (sessionstatus == "TIMEOUT") {  
            var win = window;  
            while (win != win.top){  
                win = win.top;  
            }  
                win.location.href= XMLHttpRequest.getResponseHeader("CONTEXTPATH");  
        }  
    }  
}); 

//统一ajax请求
$.myajax = function(param){
	if(!param || !param.url){
		// URL 必须传入
		return;
	}
	
	// 默认是异步请求true
	if(!param.async && param.async != false){
		param.async = true;
	}
	// HTTP请求类型
	if(!param.type){
		param.type = "POST";
	}
	// 参数
	if(!param.data){
		param.data = {};
	}
	// 参数类型
	if(!param.dataType){
		param.dataType = "json";
	}
	// 参数类型
	if (param.contentType == undefined) {
		param.contentType = "application/json;charset=utf-8";
	}
	// 默认显示遮罩层
	if(!param.isLoading && param.isLoading != false){
		param.isLoading = true;
	}
	
	// 初始化公共参数
	initPublicParam(param.data)
	var tmpData = param.data;
	if(param.contentType  != false) {
		tmpData = JSON.stringify(param.data);
	}
	
	$.ajax({
        type : param.type,
        async : param.async, 
        url :  rootPath + param.url,
        data : tmpData,
        dataType : param.dataType,
        contentType : param.contentType,  
        success : function(response) {
        	if(param.callback && typeof param.callback == "function"){
        		// 回调
        		param.callback(response);
            }
        }
    });
};


/**
 * ajax-显示页面加载屏遮层，全屏
 */
function showRequestLoadding() {
    $("body").append('<div id="dv_bg_loading" class="dv_bg_loading_css" style="background-color:#000000;opacity:0.3;"><img src="'+rootPath+'/theme/images/ajax-loader.gif"></div>');
}

/**
 * ajax-关闭页面加载屏遮层，全屏
 */
function hideRequestLoadding() {
    $("#dv_bg_loading").remove();
}

/**
 * 跳转到主页
 */
function goHome(){
	window.location.href = rootPath + "/templates/index.html";
}

/**
 * 初始化国际化语言
 */
function initI18nMsg(){
	$.i18n.properties({
	    name : 'message', // 资源文件名称
	    path : rootPath+'/theme/i18n/', // 资源文件路径
	    mode : 'map', // 用Map的方式使用资源文件中的值
	    language : __lang,//
	    callback : function() {// 加载成功后设置显示内容
	    	// 给带i18n-text类样式的相关元素设置国际化
	        $('.i18n-text').each(function() {
	        	if($(this).attr("placeholder")){
	        		$(this).attr("placeholder", $.i18n.prop($(this).attr("placeholder")));
	        	}else{
	        		$(this).text($.i18n.prop($(this).text()));
	        	}
	        	//if(i18nMsg.indexOf($(this).text()) < 0){
	        	//}
	        });
	    }
	});
}

/**
 * 初始化表格
 * @param tableId 表格id
 * @param uri 请求连接
 * @param columns 显示字段
 * @param initDataFun 方法
 * @param pagination 是否分页
 * @param   clickToSelect 是否点击选中
 * @param   onCheck 选中方法
 * @param   onUncheck 去选方法
 * @param   onCheckAll 全选方法
 * @param   onUncheckAll 去全选方法
 * @returns 表格请求对象
 */
function initTable (tableId, uri, columns,initDataFun, pagination,clickToSelect,onCheck,onUncheck,onCheckAll,onUncheckAll) {
	if (pagination == undefined) {
		pagination = true;
	}
	var sidePagination = 'server';
	if (clickToSelect == undefined) {
		clickToSelect = false;
	}
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
    	$('#'+tableId).bootstrapTable('destroy').bootstrapTable({
            url: rootPath+uri,         			//请求后台的URL（*）
            method: 'POST',                     //请求方式（*）
            dataType:"json",      
            contentType:"application/json;charset=utf-8",
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: sidePagination,           //分页方式：client客户端分页，server服务端分页（*）
            pagination: pagination, //启动分页 
            clickToSelect: clickToSelect, //是否点击选中
            pageSize: 10,  //每页显示的记录数  
            pageNumber:1, //当前第几页  
            pageList: [10, 20, 50, 100],        //可供选择的每页的行数（*）
            toolbar: '#toolbar', //此处  统一风格，界面多个DIV行
            columns: columns,
            onCheck:onCheck,
            onUncheck:onUncheck,
            onCheckAll:onCheckAll,
            onUncheckAll:onUncheckAll,
            onLoadSuccess: function(response){  //加载成功时执行  
                if(response.retCode != 0) {
                	showError(response.retMsg)
                }
              }
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
    	var queryParam = {};
    	queryParam.data = {};
        if(initDataFun) {
        	initDataFun(queryParam.data);
        }
    	queryParam.data.pageSize = params.limit;
        queryParam.data.offset = params.offset;
    	//初始化公共参数
    	//initPublicParam(queryParam)
        return JSON.stringify(queryParam.data);
    };
    return oTableInit;
};

//显示错误或提示信息（需要引用jNotify相关文件）
function showError(tips, TimeShown, autoHide) {
	
	swal( {title: "操作失败",  text:tips,   type:"error" ,confirmButtonColor: "#FF3251", confirmButtonText: "确认"});
	

}

//显示提示信息
function showSuccess(tips, TimeShown, autoHide,callBackFun) {
	swal( {title: "操作成功",  text:"",   type:"success" ,confirmButtonColor: "#FF3251", confirmButtonText: "确认"},callBackFun);

}

//显示提示信息
function showTips(tips, TimeShown, autoHide) {
	swal( {title: "操作提示",  text:tips,   type:"info" ,confirmButtonColor: "#FF3251", confirmButtonText: "确认"});

}

/**
 * 删除确认
 * @param deleteFun 删除方法
 */
function delConfirm(deleteFun) {
	swal({title: "是否确认删除?",text: "", type: "warning",  showCancelButton: true,   
		confirmButtonColor: "#FF3251", confirmButtonText: "确认", cancelButtonText:"取消",
		closeOnConfirm: false }, 
		deleteFun);
}
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function initPublicParam(param) {
	// 管理台serviceId为1
	param.serverId = 1;
	// 请求时间
	param.requestTime = new Date().Format("yyyyMMddhhmmss");
	//版本
	param.version = 1.0
}

/**
 * 转小写
 */
function toLowerCase(str){
	if(str) {
		str = str.toLowerCase();
	}
	return str;
}

/**
 * 动态生成下拉框选项
 * @param countryId 国家ID
 * @param currentLevelEleId 省份下拉标签元素ID
 * @param nextLevelEleId 联动的城市下拉标签元素ID
 * @param currentLevelId 联动上级ID
 * @param isProvince 是否查询省份
 */
function initSelectOption(countryId, currentLevelEleId, nextLevelEleId, currentLevelId, isProvince){

	var pound = "#";
	var optionStartTag = "<option value='";
	var defaultSelect = optionStartTag +  "' selected='selected'>";
	var optionEndTag = "</option>";
	var please = "---请";
	var first = "";
	var choose = "选择";
	var province = "---";
	
	var isLinkage = (0 == nextLevelEleId.length) ? false : true;
	
	var interfaceName =  isProvince ? "province" : "city" ;
	
	if(isLinkage){
		var nextLevelEleObj = $(pound + nextLevelEleId);
		nextLevelEleObj.html(defaultSelect +  please + first + choose + province + optionEndTag);	
		$("#" + nextLevelEleId).selectpicker('refresh');
	}else{
		if (!isProvince && 0 == currentLevelId.length) {
			$("#"+currentLevelEleId).html(defaultSelect + please + first + choose + province + optionEndTag);
			$("#" + currentLevelEleId).selectpicker('refresh');
			return;
		}
	}

	var data = isProvince ? {"data":{"countryId" : countryId }} : {"data":{"countryId" : countryId, "provinceId":currentLevelId }} ;
	var param = {isLoading:false,data :data, url :'/'+interfaceName+'/query.do', callback:function(response){
		if (null != response && response.total >= 0) {
			var data = response.rows;
			var str = defaultSelect +  please + choose + (isProvince ?  province  :  "---")+ optionEndTag;
		    for(var i = 0; i<data.length; i++){
		    	var id = isProvince ? data[i].provinceId : data[i].cityId;
		    	var name = isProvince ? data[i].provinceName : data[i].cityName;
		        str += optionStartTag+id+ "'>"+name+optionEndTag
		    }
		    
		    if(0 == currentLevelEleId.length) {
		    	currentLevelEleId = "sel_search_provinceId";
		    }
		    var currentLevelEleObj = $(pound + currentLevelEleId);
		    currentLevelEleObj.html(str);
			$("#" + currentLevelEleId).selectpicker('refresh');
		    
		    if(isLinkage){
		    	// 绑定change事件
		    	currentLevelEleObj.on('change', function(){
			    	var currentLevelId = $(this).children('option:selected').val();
			    	initSelectOption(countryId, nextLevelEleId, "", currentLevelId, false);
			    });
		    }
		}
	}};
	Ajax(param);
}

/**
 * 初始化下拉框
 * @param data 参数
 * @param uri 请求uri
 * @param selectId 下拉框id
 * @param defaultSelect 默认选择
 * @param id 下拉框取的id
 * @param name 下拉框取的名称
 * @param funCallBack 回调函数
 */
/*function initSelectOpt(data,uri,selectId,defaultSelect,id,name, callback){
	var param = {isLoading:false,data : data,url : uri, callback:function(response){
		if (null != response.rows && response.rows.length > 0) {
			var arr = [''];
			var allValue = [];
			var data = response.rows;
			var str = "";
			if (defaultSelect && defaultSelect != '') {
				str += "<option value='' checked=''>---请选择---</option>";
			}
		    for(var i = 0;i<data.length;i++){
		        str+="<option value='"+data[i][id]+"'>"+data[i][id]+"</option>";
		        allValue.push(data[i][id]);
		    }
		    $("#"+ selectId).empty();
		    $("#"+ selectId).append(str);
		    if (defaultSelect && defaultSelect != '') {
		    	$("#"+ selectId).val(arr);
		    }
		    if (callback) {
		    	callback();
		    }
		}
	}};
	Ajax(param);
	
}*/

/**
 * 初始化下拉框
 * @param data 参数
 * @param uri 请求uri
 * @param selectId 下拉框id
 * @param defaultSelect 默认选择
 * @param id 下拉框取的id
 * @param name 下拉框取的名称
 * @param funCallBack 回调函数
 */
function initSelectOpt(data,uri,selectId,defaultSelect,id,name,async, callback){
	var param = {isLoading:false,data:data,url:uri,"async":async,callback:function(response){
		if (null != response.rows && response.rows.length > 0) {
			var arr = [''];
			var allValue = [];
			var data = response.rows;
			var str = "";
			if (defaultSelect && defaultSelect != '') {
				str += "<option value='' checked=''>---"+defaultSelect+"---</option>";
			}
		    for(var i = 0;i<data.length;i++){
		        str+="<option value='"+data[i][id]+"'>"+data[i][name]+"</option>";
		        allValue.push(data[i][id]);
		    }
		    $("#"+ selectId).empty();
		    $("#"+ selectId).append(str);
		    if (defaultSelect && defaultSelect != '') {
		    	$("#"+ selectId).val(arr);
		    }
		    // 为了全选用
		    if (callback) {
		    	callback();
		    }
		}
	}};
	Ajax(param);
	
}



/**
 * 获取页面跳转url后面附带参数方法
 * @param name
 * @returns
 */
function getQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  decodeURI(r[2]); return null;
}


//判空
function isNotEmpty(data) 
{
	return data != undefined && data != '' && $.trim(data) != '';
}

// 加remove方法
Array.prototype.remove=function(data)
{
	var minus = 0;
	 for(var i=0,n=0;i<this.length;i++)
	 {
		 if(this[i] != data)
		 {
			 this[n++]=this[i]
			 minus++;
		 }
	   }
	 this.length-=minus;
}

// 加是否包含方法
Array.prototype.contain=function(data)
{
	 for(var i=0,n=0;i<this.length;i++)
	 {
		 if(this[i] == data)
		 {
			return true;
		 }
	  }
	return false;
}

/**
 * 判断是否是数字
 * @param num 数据
 * @param min 最小值
 */
function isNumber(num,min) {
	if (num == undefined) {
		return false;
	}
	// 判断是否是数字
	if (!/^\d+$/.test(num)) {
		return false;
	}
	if (min != undefined) {
		if (parseInt(num) < min) {
			return false;
		}
	}
	if (num.length > 11) {
		return false;
	}
	return true;
}

 