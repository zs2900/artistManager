/*
 * [简要描述]:
 * [详细描述]:
 * @author 
 * @date 
 */
var channelName = "";
$(function() {
	// 初始化
	initSelectOpt('', '/country/list', 'txt_search_nationality', '全部国籍',
			'namezh', 'namezh');
	initSelectOpt('', '/artist/languageList', 'txt_edit_defaultLanguage',
			'默认语言', 'languageId', 'nativeName');
	initSelectOpt({
		"dictionaryCode" : "PROFESSION"
	}, '/datadic/list', 'txt_search_artistType', '全部职业', 'dicitemNameZh',
			'dicitemNameZh');
	// 1.初始化Table
	var oTable = new TableInit();
	oTable.Init();
	// 加上前台校验
	addSearchformValidate();

	// 查询事件
	bindQueryFun(function() {
		$('#tb_ircodetype').bootstrapTable('refresh', {
			pageNumber : 1
		});
	});

	// 清空事件
	bindClearFun(function() {
		addSearchformValidate();
	});

	// 添加事件
	$("#btn_add").on("click", function() {
		openAddModel();
	});

	// 上传图片
	$("#addPicId").on("click", function() {
		$("#imgFileId").trigger("click");
	});

	// 绑定删除图片事件
	$(".delIcon").on("click", function(e) {
		delImg();
	});
});

function delImg() {
	$(".subImg").attr("src", "");
	$(".subImg").hide();
	$(".addBgPic").show();
	$(".delIcon").hide();
	$("#imgFileId").val("");
}

function getFilePath() {
	var img = $("#imgFileId").val();
	// 校验样式
	if (!img || img == '' || img == undefined) {
		return;
	}
	if (!/.jpg|.png|.gif$/.test(img)) {
		showError("只支持格式为.jgp,.png,.gif的图片");
		return;
	}

	var img = $("#imgFileId")[0].files[0];
	console.log(img);
	// 判断大小
	if (img.size > 1024 * 1024) {
		showError("只支持小于1m的图片");
		return;
	}
	var formData = new FormData();
	formData.append("files", img);
	console.log(formData);
	var param = {
		isLoading : false,
		data : formData,
		url : '/artist/fileUpload',
		callback : function(response) {
			if (response.retCode == 0) {
				showImg(response.url);
			} else {
				showError(response.retMsg);
			}
		}
	};
	AjaxUpload(param);

}

// 展示图片
function showImg(img) {
	$("#preImgId").attr("src", img);
	$("#preImgId").show();
	$(".delIcon").show();
	// 隐藏上传框
	$("#preImgId").parent().find(".addBgPic").hide();
}

var TableInit = function() {
	// 显示字段
	var columns = [
			{
				field : 'artitsInfo',
				title : '影人信息',
				formatter : function(value, row, index) {
					if (isNotEmpty(row.artistCode) && isNotEmpty(row.name)
							&& isNotEmpty(row.headImg)) {
						return '<div style="width:300px"><div style="float:left"><img  src='
								+ row.headImg
								+ ' class="img-rounded" style="height:80px;width:60px"/></div><div style="height:80px;padding:20px 0px 20px 80px"><div style="width:280px"><span>名称：'
								+ row.name
								+ '</span><br/><span>编码：'
								+ row.artistCode + '</span></div></div></div>';
					}
				}
			}, {
				field : 'foreignName',
				title : '外文名'
			}, {
				field : 'alias',
				title : '别名'
			}, {
				field : 'homeTown',
				title : '出生地',
			}, {
				field : 'artistType',
				title : '职业'
			}, {
				field : 'nationality',
				title : '国籍'
			}, {
				field : 'operate',
				title : '操作',
				align : 'center',
				events : operateEvents,
				formatter : operateFormatter
			} ];

	return initTable("tb_ircodetype", "/artist/list", columns, function(data) {
		// 设置查询参数
		data.artistCode = $("#txt_search_artistCode").val();
		data.name = $("#txt_search_name").val();
		data.foreignName = $("#txt_search_foreignName").val();
		data.alias = $("#txt_search_alias").val();
		data.homeTown = $("#txt_search_homeTown").val();
		data.artistType = $("#txt_search_artistType").val();
		data.nationality = $("#txt_search_nationality").val();
	});
};

// 操作列
function operateFormatter(value, row, index) {
	var id = row.artistCode;

	return [ '<a class="edit" style="cursor: pointer;">编辑</a>',
			'&nbsp;|&nbsp;', '<a class="view" style="cursor: pointer;">查看</a>',
			'&nbsp;|&nbsp;', '<a class="del" style="cursor: pointer;">删除</a>', ]
			.join('');
}
/**
 * 编辑和查看
 * 
 * @param e
 * @param value
 * @param row
 * @param index
 */
function editAndView(e, value, row, index, type) {
	var artistInfo = {};
	var artistI18 = [];
	var artistI18_zh = {};
	var artistI18_en = {};
	var param = {
		isLoading : false,
		data : {
			"artistCode" : row.artistCode
		},
		url : "/artist/detailInfo",
		callback : function(response) {
			if (response.retCode == 0) {
				if (null != response.artistDetialInfo
						&& null != response.artistDetialInfo.artistInfo
						&& response.artistDetialInfo.artistI18ns.length > 0) {
					artistInfo = response.artistDetialInfo.artistInfo;
					artistI18 = response.artistDetialInfo.artistI18ns;
					// 判断多语言信息
					if (artistI18 != null && artistI18.length > 0) {
						var len = artistI18.length;
						for (var i = 0; i < len; i++) {
							if (artistI18[i] != null
									&& artistI18[i]["lang"] == "en_US") {
								artistI18_en = artistI18[i];
							} else if (artistI18[i] != null
									&& artistI18[i]["lang"] == "zh_CN") {
								artistI18_zh = artistI18[i];
							}
						}
					}
					// 新增 编辑窗口的校验
					addEditformValidate();
					$("#addEditModel").find("div[class=modal-dialog]").attr(
							"style", "width:1600px;margin-top:" + "100px");
					$("#addEditModel").modal('show');
					if (type == "edit") {
						$("#addEditLabel").html("修改艺人信息");
						$('#addEditform').find('input,textarea,select')
								.removeAttr('disabled');
						$(".modal-footer #toSure").show();
						$("#addEditType").val("edit");
					} else if (type == "view") {
						$("#addEditLabel").html("查看艺人信息");
						// 不可编辑
						$('#addEditform').find('input,textarea,select').attr(
								'disabled', 'disabled');
						$(".modal-footer #toSure").hide();
						$("#addEditType").val("view");
					}

					if (artistI18_zh != null
							&& artistI18_zh.lang == artistInfo.defaultLanguage) {
						$("#chinese").attr("class", "btn btn-primary");
						$("#english").attr("class", "btn btn-default");
						$("#en_artistI18n").attr("hidden", "hidden");
						$("#zh_artistI18n").removeAttr("hidden");
					} else if (artistI18_en != null
							&& artistI18_en.lang == artistInfo.defaultLanguage) {
						$("#english").attr("class", "btn btn-primary");
						$("#chinese").attr("class", "btn btn-default");
						$("#zh_artistI18n").attr("hidden", "hidden");
						$("#en_artistI18n").removeAttr("hidden");
					}

					/**
					 * 数据回显
					 */
					// 中文
					$("#addEditform #zh_artistI18n_id").val(artistI18_zh.id);
					if (artistI18_zh.artistCode == null) {
						$("#addEditform #zh_artistI18n_artistCode").val(
								artistI18_en.artistCode);
					} else {
						$("#addEditform #zh_artistI18n_artistCode").val(
								artistI18_zh.artistCode);
					}
					$("#addEditform #txt_edit_zh_name").val(artistI18_zh.name);
					$("#addEditform #txt_edit_zh_foreignName").val(
							artistI18_zh.foreignName);
					$("#addEditform #txt_add_zh_alias").val(artistI18_zh.alias);
					$("#addEditform #txt_edit_zh_homeTown").val(
							artistI18_zh.homeTown);
					$("#addEditform #txt_edit_zh_des").val(artistI18_zh.des);
					// 英文
					$("#addEditform #en_artistI18n_id").val(artistI18_en.id);
					if (artistI18_en.artistCode == null) {
						$("#addEditform #en_artistI18n_artistCode").val(
								artistI18_zh.artistCode);
					} else {
						$("#addEditform #en_artistI18n_artistCode").val(
								artistI18_en.artistCode);
					}
					$("#addEditform #txt_edit_en_name").val(artistI18_en.name);
					$("#addEditform #txt_edit_en_foreignName").val(
							artistI18_en.foreignName);
					$("#addEditform #txt_edit_en_alias")
							.val(artistI18_en.alias);
					$("#addEditform #txt_edit_en_homeTown").val(
							artistI18_en.homeTown);
					$("#addEditform #txt_edit_en_des").val(artistI18_en.des);
					// 基本信息
					$("#addEditform #info_artistCode").val(
							artistInfo.artistCode);
					$("#addEditform #info_artistId").val(artistInfo.artistId);
					var languages = $("#txt_edit_defaultLanguage").find(
							"option");
					$("#txt_edit_defaultLanguage").empty();
					for (var i = 0; i < languages.length; i++) {
						if (artistInfo.defaultLanguage == languages[i].value) {
							$("#txt_edit_defaultLanguage").append(
									"<option value='" + languages[i].value
											+ "' selected='selected'>"
											+ languages[i].text + "</option>");
						} else {
							$("#txt_edit_defaultLanguage").append(
									"<option value=" + languages[i].value + ">"
											+ languages[i].text + "</option>");
						}
					}
					if (artistInfo.sex == "0") {
						$("#addEditform #sel_edit_sex").empty();
						$("#addEditform #sel_edit_sex")
								.append(
										"<option id='defult' value>---性别---</option><option value='1' id='man'>男</option><option value='0' id='woman' selected='selected'>女</option>");
					} else if (artistInfo.sex == "1") {
						$("#addEditform #sel_edit_sex").empty();
						$("#addEditform #sel_edit_sex")
								.append(
										"<option id='defult' value>---性别---</option><option value='1' id='man' selected='selected'>男</option><option value='0' id='woman'>女</option>");
					} else {
						$("#addEditform #sel_edit_sex").empty();
						$("#addEditform #sel_edit_sex")
								.append(
										"<option id='defult' selected='selected' value>---性别---</option><option value='1' id='man'>男</option><option value='0' id='woman'>女</option>");
					}
					$("#addEditform #txt_edit_birthday").val(
							artistInfo.birthday);
					if (artistInfo.nationality != null) {
						var aa = $("#txt_search_nationality");
						var optionlength = aa.find("option").length;
						var options = "";
						$("#addEditform #sel_edit_nationality").empty();
						for (var i = 0; i < optionlength; i++) {
							if (artistInfo.nationality == aa.find("option")[i].value) {
								$("#sel_edit_nationality").append(
										"<option value='"
												+ aa.find("option")[i].value
												+ "' selected='selected'>"
												+ aa.find("option")[i].text
												+ "</option>");
							} else {
								$("#sel_edit_nationality").append(
										"<option value="
												+ aa.find("option")[i].value
												+ ">"
												+ aa.find("option")[i].text
												+ "</option>");
							}
						}
					}
					if (artistInfo.bloodGroup == "A") {
						$("#addEditform #txt_edit_bloodGroup").empty();
						$("#addEditform #txt_edit_bloodGroup")
								.append(
										"<option id='defult' value>---血型---</option><option value='A' selected='selected'>A型</option><option value='B'>B型</option><option value='O'>O型</option><option value='AB'>AB型</option><option value='Rh'>Rh型</option>");
					} else if (artistInfo.bloodGroup == "B") {
						$("#addEditform #txt_edit_bloodGroup").empty();
						$("#addEditform #txt_edit_bloodGroup")
								.append(
										"<option id='defult' value>---血型---</option><option value='A'>A型</option><option value='B' selected='selected'>B型</option><option value='O'>O型</option><option value='AB'>AB型</option><option value='Rh'>Rh型</option>");
					} else if (artistInfo.bloodGroup == "AB") {
						$("#addEditform #txt_edit_bloodGroup").empty();
						$("#addEditform #txt_edit_bloodGroup")
								.append(
										"<option id='defult' value>---血型---</option><option value='A'>A型</option><option value='B'>B型</option><option value='O'>O型</option><option value='AB' selected='selected'>AB型</option><option value='Rh'>Rh型</option>");
					} else if (artistInfo.bloodGroup == "O") {
						$("#addEditform #txt_edit_bloodGroup").empty();
						$("#addEditform #txt_edit_bloodGroup")
								.append(
										"<option id='defult' value>---血型---</option><option value='A'>A型</option><option value='B'>B型</option><option value='O' selected='selected'>O型</option><option value='AB'>AB型</option><option value='Rh'>Rh型</option>");
					} else if (artistInfo.bloodGroup == "Rh") {
						$("#addEditform #txt_edit_bloodGroup").empty();
						$("#addEditform #txt_edit_bloodGroup")
								.append(
										"<option id='defult' value>---血型---</option><option value='A'>A型</option><option value='B'>B型</option><option value='O'>O型</option><option value='AB'>AB型</option><option value='Rh' selected='selected'>Rh型</option>");
					} else {
						$("#addEditform #txt_edit_bloodGroup").empty();
						$("#addEditform #txt_edit_bloodGroup")
								.append(
										"<option id='defult' selected='selected' value>---血型---</option><option value='A'>A型</option><option value='B'>B型</option><option value='O'>O型</option><option value='AB'>AB型</option><option value='Rh'>Rh型</option>");
					}
					$("#addEditform #txt_edit_height").val(artistInfo.height);
					$("#addEditform #txt_edit_weight").val(artistInfo.weight);
					if (artistInfo.constellation == "白羊座") {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' value>---星座---</option>"
												+ "<option value='白羊座' selected='selected'>白羊座</option>"
												+ "<option value='金牛座'>金牛座</option><option value='双子座'>双子座</option>"
												+ "<option value='巨蟹座'>巨蟹座</option><option value='狮子座'>狮子座</option>"
												+ "<option value='处女座'>处女座</option><option value='天秤座'>天秤座</option>"
												+ "<option value='天蝎座'>天蝎座</option><option value='射手座'>射手座</option>"
												+ "<option value='摩羯座'>摩羯座</option><option value='水瓶座'>水瓶座</option>"
												+ "<option value='双鱼座'>双鱼座</option>");
					} else if (artistInfo.constellation == "金牛座") {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' value>---星座---</option>"
												+ "<option value='白羊座'>白羊座</option>"
												+ "<option value='金牛座' selected='selected'>金牛座</option><option value='双子座'>双子座</option>"
												+ "<option value='巨蟹座'>巨蟹座</option><option value='狮子座'>狮子座</option>"
												+ "<option value='处女座'>处女座</option><option value='天秤座'>天秤座</option>"
												+ "<option value='天蝎座'>天蝎座</option><option value='射手座'>射手座</option>"
												+ "<option value='摩羯座'>摩羯座</option><option value='水瓶座'>水瓶座</option>"
												+ "<option value='双鱼座'>双鱼座</option>");
					} else if (artistInfo.constellation == "双子座") {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' value>---星座---</option>"
												+ "<option value='白羊座'>白羊座</option>"
												+ "<option value='金牛座'>金牛座</option><option value='双子座' selected='selected'>双子座</option>"
												+ "<option value='巨蟹座'>巨蟹座</option><option value='狮子座'>狮子座</option>"
												+ "<option value='处女座'>处女座</option><option value='天秤座'>天秤座</option>"
												+ "<option value='天蝎座'>天蝎座</option><option value='射手座'>射手座</option>"
												+ "<option value='摩羯座'>摩羯座</option><option value='水瓶座'>水瓶座</option>"
												+ "<option value='双鱼座'>双鱼座</option>");
					} else if (artistInfo.constellation == "巨蟹座") {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' value>---星座---</option>"
												+ "<option value='白羊座'>白羊座</option>"
												+ "<option value='金牛座'>金牛座</option><option value='双子座'>双子座</option>"
												+ "<option value='巨蟹座' selected='selected'>巨蟹座</option><option value='狮子座'>狮子座</option>"
												+ "<option value='处女座'>处女座</option><option value='天秤座'>天秤座</option>"
												+ "<option value='天蝎座'>天蝎座</option><option value='射手座'>射手座</option>"
												+ "<option value='摩羯座'>摩羯座</option><option value='水瓶座'>水瓶座</option>"
												+ "<option value='双鱼座'>双鱼座</option>");
					} else if (artistInfo.constellation == "狮子座") {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' value>---星座---</option>"
												+ "<option value='白羊座'>白羊座</option>"
												+ "<option value='金牛座' >金牛座</option><option value='双子座'>双子座</option>"
												+ "<option value='巨蟹座'>巨蟹座</option><option value='狮子座' selected='selected'>狮子座</option>"
												+ "<option value='处女座'>处女座</option><option value='天秤座'>天秤座</option>"
												+ "<option value='天蝎座'>天蝎座</option><option value='射手座'>射手座</option>"
												+ "<option value='摩羯座'>摩羯座</option><option value='水瓶座'>水瓶座</option>"
												+ "<option value='双鱼座'>双鱼座</option>");
					} else if (artistInfo.constellation == "处女座") {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' value>---星座---</option>"
												+ "<option value='白羊座'>白羊座</option>"
												+ "<option value='金牛座'>金牛座</option><option value='双子座'>双子座</option>"
												+ "<option value='巨蟹座'>巨蟹座</option><option value='狮子座'>狮子座</option>"
												+ "<option value='处女座' selected='selected'>处女座</option><option value='天秤座'>天秤座</option>"
												+ "<option value='天蝎座'>天蝎座</option><option value='射手座'>射手座</option>"
												+ "<option value='摩羯座'>摩羯座</option><option value='水瓶座'>水瓶座</option>"
												+ "<option value='双鱼座'>双鱼座</option>");
					} else if (artistInfo.constellation == "天秤座") {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' value>---星座---</option>"
												+ "<option value='白羊座'>白羊座</option>"
												+ "<option value='金牛座'>金牛座</option><option value='双子座'>双子座</option>"
												+ "<option value='巨蟹座'>巨蟹座</option><option value='狮子座'>狮子座</option>"
												+ "<option value='处女座'>处女座</option><option value='天秤座' selected='selected'>天秤座</option>"
												+ "<option value='天蝎座'>天蝎座</option><option value='射手座'>射手座</option>"
												+ "<option value='摩羯座'>摩羯座</option><option value='水瓶座'>水瓶座</option>"
												+ "<option value='双鱼座'>双鱼座</option>");
					} else if (artistInfo.constellation == "天蝎座") {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' value>---星座---</option>"
												+ "<option value='白羊座'>白羊座</option>"
												+ "<option value='金牛座'>金牛座</option><option value='双子座'>双子座</option>"
												+ "<option value='巨蟹座'>巨蟹座</option><option value='狮子座'>狮子座</option>"
												+ "<option value='处女座'>处女座</option><option value='天秤座'>天秤座</option>"
												+ "<option value='天蝎座' selected='selected'>天蝎座</option><option value='射手座'>射手座</option>"
												+ "<option value='摩羯座'>摩羯座</option><option value='水瓶座'>水瓶座</option>"
												+ "<option value='双鱼座'>双鱼座</option>");
					} else if (artistInfo.constellation == "射手座") {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' value>---星座---</option>"
												+ "<option value='白羊座'>白羊座</option>"
												+ "<option value='金牛座'>金牛座</option><option value='双子座'>双子座</option>"
												+ "<option value='巨蟹座'>巨蟹座</option><option value='狮子座'>狮子座</option>"
												+ "<option value='处女座'>处女座</option><option value='天秤座'>天秤座</option>"
												+ "<option value='天蝎座'>天蝎座</option><option value='射手座' selected='selected'>射手座</option>"
												+ "<option value='摩羯座'>摩羯座</option><option value='水瓶座'>水瓶座</option>"
												+ "<option value='双鱼座'>双鱼座</option>");
					} else if (artistInfo.constellation == "摩羯座") {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' value>---星座---</option>"
												+ "<option value='白羊座'>白羊座</option>"
												+ "<option value='金牛座'>金牛座</option><option value='双子座'>双子座</option>"
												+ "<option value='巨蟹座'>巨蟹座</option><option value='狮子座'>狮子座</option>"
												+ "<option value='处女座'>处女座</option><option value='天秤座'>天秤座</option>"
												+ "<option value='天蝎座'>天蝎座</option><option value='射手座'>射手座</option>"
												+ "<option value='摩羯座' selected='selected'>摩羯座</option><option value='水瓶座'>水瓶座</option>"
												+ "<option value='双鱼座'>双鱼座</option>");
					} else if (artistInfo.constellation == "水瓶座") {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' value>---星座---</option>"
												+ "<option value='白羊座'>白羊座</option>"
												+ "<option value='金牛座'>金牛座</option><option value='双子座'>双子座</option>"
												+ "<option value='巨蟹座'>巨蟹座</option><option value='狮子座'>狮子座</option>"
												+ "<option value='处女座'>处女座</option><option value='天秤座'>天秤座</option>"
												+ "<option value='天蝎座'>天蝎座</option><option value='射手座'>射手座</option>"
												+ "<option value='摩羯座'>摩羯座</option><option value='水瓶座' selected='selected'>水瓶座</option>"
												+ "<option value='双鱼座'>双鱼座</option>");
					} else if (artistInfo.constellation == "双鱼座") {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' value>---星座---</option>"
												+ "<option value='白羊座'>白羊座</option>"
												+ "<option value='金牛座'>金牛座</option><option value='双子座'>双子座</option>"
												+ "<option value='巨蟹座'>巨蟹座</option><option value='狮子座'>狮子座</option>"
												+ "<option value='处女座'>处女座</option><option value='天秤座'>天秤座</option>"
												+ "<option value='天蝎座'>天蝎座</option><option value='射手座'>射手座</option>"
												+ "<option value='摩羯座'>摩羯座</option><option value='水瓶座'>水瓶座</option>"
												+ "<option value='双鱼座' selected='selected'>双鱼座</option>");
					} else {
						$("#addEditform #txt_edit_constellation").empty();
						$("#addEditform #txt_edit_constellation")
								.append(
										"<option id='defult' selected='selected' value>---星座---</option>"
												+ "<option value='白羊座'>白羊座</option>"
												+ "<option value='金牛座'>金牛座</option><option value='双子座'>双子座</option>"
												+ "<option value='巨蟹座'>巨蟹座</option><option value='狮子座'>狮子座</option>"
												+ "<option value='处女座'>处女座</option><option value='天秤座'>天秤座</option>"
												+ "<option value='天蝎座'>天蝎座</option><option value='射手座'>射手座</option>"
												+ "<option value='摩羯座'>摩羯座</option><option value='水瓶座'>水瓶座</option>"
												+ "<option value='双鱼座'>双鱼座</option>");
					}

					if (artistInfo.isTeam == 0) {
						$("#addEditform #sel_edit_isTeam").empty();
						$("#addEditform #sel_edit_isTeam")
								.append(
										"<option id='defult' value>---是否团队---</option><option value='0' id='team_false' selected='selected'>否</option><option value='1' id='team_true'>是</option>");
					} else if (artistInfo.isTeam == 1) {
						$("#addEditform #sel_edit_isTeam").empty();
						$("#addEditform #sel_edit_isTeam")
								.append(
										"<option id='defult' value>---是否团队---</option><option value='0' id='team_false'>否</option><option value='1' id='team_true' selected='selected'>是</option>");
					} else {
						$("#addEditform #sel_edit_isTeam").empty();
						$("#addEditform #sel_edit_isTeam")
								.append(
										"<option id='defult' selected='selected' value>---是否团队---</option><option value='0' id='team_false'>否</option><option value='1' id='team_true'>是</option>");
					}
					$("#addEditform #txt_edit_firstName").val(
							artistInfo.firstName);
					$("#addEditform #txt_edit_lastName").val(
							artistInfo.lastName);
					$("#addEditform #txt_edit_searchName").val(
							artistInfo.searchName);
					$("#addEditform #txt_edit_zone").val(artistInfo.zone);
					$("#addEditform #txt_edit_firstLetter").val(
							artistInfo.firstLetter);
					$("#addEditform #txt_edit_companyName").val(
							artistInfo.companyName);
					$("#addEditform #date_edit_buildTime").val(
							artistInfo.buildTime);
					$("#addEditform #date_edit_joininTime").val(
							artistInfo.joininTime);
					$("#addEditform #txt_edit_education").val(
							artistInfo.education);
					$("#addEditform #txt_edit_favorite").val(
							artistInfo.favorite);
					$("#addEditform #txt_edit_motherTongue").val(
							artistInfo.motherTongue);
					if (artistInfo.marriage == 0) {
						$("#addEditform #sel_edit_marriage").empty();
						$("#addEditform #sel_edit_marriage")
								.append(
										"<option id='defult' value>---婚姻状态---</option><option value='0' selected='selected'>未婚</option><option value='1'>已婚</option><option value='2'>离异</option><option value='3'>丧偶</option>");
					} else if (artistInfo.marriage == 1) {
						$("#addEditform #sel_edit_marriage").empty();
						$("#addEditform #sel_edit_marriage")
								.append(
										"<option id='defult' value>---婚姻状态---</option><option value='0'>未婚</option><option value='1' selected='selected'>已婚</option><option value='2'>离异</option><option value='3'>丧偶</option>");
					} else if (artistInfo.marriage == 2) {
						$("#addEditform #sel_edit_marriage").empty();
						$("#addEditform #sel_edit_marriage")
								.append(
										"<option id='defult' value>---婚姻状态---</option><option value='0'>未婚</option><option value='1'>已婚</option><option value='2' selected='selected'>离异</option><option value='3'>丧偶</option>");
					} else if (artistInfo.marriage == 3) {
						$("#addEditform #sel_edit_marriage").empty();
						$("#addEditform #sel_edit_marriage")
								.append(
										"<option id='defult' value>---婚姻状态---</option><option value='0'>未婚</option><option value='1'>已婚</option><option value='2'>离异</option><option value='3' selected='selected'>丧偶</option>");
					} else {
						$("#addEditform #sel_edit_marriage").empty();
						$("#addEditform #sel_edit_marriage")
								.append(
										"<option id='defult' selected='selected' value>---婚姻状态---</option><option value='0'>未婚</option><option value='1'>已婚</option><option value='2'>离异</option><option value='3'>丧偶</option>");
					}
					// 艺人职业
					if (artistInfo.artistType != null) {
						var aa = $("#txt_search_artistType");
						var optionlength = aa.find("option").length;
						var options = "";
						$("#addEditform #sel_edit_artistType").empty();
						for (var i = 0; i < optionlength; i++) {
							if (artistInfo.artistType == aa.find("option")[i].value) {
								$("#addEditform #sel_edit_artistType").append(
										"<option value='"
												+ aa.find("option")[i].value
												+ "' selected='selected'>"
												+ aa.find("option")[i].text
												+ "</option>");
							} else {
								$("#addEditform #sel_edit_artistType").append(
										"<option value="
												+ aa.find("option")[i].value
												+ ">"
												+ aa.find("option")[i].text
												+ "</option>");
							}
						}
					}
					$("#addEditform #preImgId").attr("src", artistInfo.headImg);

					if (isNotEmpty(artistInfo.headImg)) {
						showImg(artistInfo.headImg);
					} else {
						delImg();
					}
				}

			} else {
				showError(response.retMsg);
			}
		}
	};
	Ajax(param);
}
/**
 * 操作项
 */
window.operateEvents = {
	'click .edit' : function(e, value, row, index) {
		editAndView(e, value, row, index, "edit");
	},
	'click .del' : function(e, value, row, index) {
		// 删除省份
		delConfirm(function() {
			var param = {
				isLoading : false,
				data : {
					"artistCode" : row.artistCode
				},
				url : '/artist/delete',
				callback : function(response) {
					if (null != response && response.retCode == 0) {
						showSuccess(response.retMsg);
						reloadTab();
					} else {
						showError(response.retMsg);
					}
				}
			};
			Ajax(param);
		});
	},
	'click .view' : function(e, value, row, index) {
		editAndView(e, value, row, index, "view");
	}
};
// 获取长度为len的随机字符串
function _getRandomString(len) {
	var x = "0123456789qwertyuioplkjhgfdsazxcvbnm";
	var tmp = "";
	var timestamp = new Date().getTime();
	for (var i = 0; i < len; i++) {
		tmp += x.charAt(Math.ceil(Math.random() * 100000000) % x.length);
	}
	return timestamp + tmp;
}

function openAddModel() {
	// 新增 编辑窗口的校验
	addEditformValidate();
	// 新增省份
	$("#addEditModel").find("div[class=modal-dialog]").attr("style",
			"width:1600px;margin-top:" + (100) + "px");
	$("#addEditModel").modal('show');
	$("#addEditLabel").html("新增艺人");
	$('#addEditform').find('input,textarea,select').removeAttr('disabled');
	$(".modal-footer #toSure").show();
	$("#addEditType").val("add");
	// 清空数据
	document.getElementById("addEditform").reset();
	// 获取当前时间戳+14位随机字符串组成的随机编号
	var random = _getRandomString(14);
	$("#addEditform #zh_artistI18n_artistCode").attr("value", random);
	$("#addEditform #en_artistI18n_artistCode").attr("value", random);
	$("#addEditform #info_artistCode").attr("value", random);
	// 初始化下拉框
	$("#addEditform #sel_edit_sex").empty();
	$("#addEditform #sel_edit_sex")
			.append(
					"<option id='defult' value>---SEX---</option><option value='0'>女</option><option value='1'>男</option>");
	$("#addEditform #sel_edit_artistType").empty();
	$("#addEditform #sel_edit_marriage").empty();
	$("#addEditform #sel_edit_isTeam").empty();
	$("#addEditform #sel_edit_nationality").empty();
	initSelectOpt({
		"dictionaryCode" : "BLOOD_TYPE"
	}, '/datadic/list', 'txt_edit_bloodGroup', '血型', 'dicitemCode',
			'dicitemNameZh');
	initSelectOpt({
		"dictionaryCode" : "CONSTELLATION"
	}, '/datadic/list', 'txt_edit_constellation', '星座', 'dicitemNameZh',
			'dicitemNameZh');
	initSelectOpt('', '/country/list', 'sel_edit_nationality', '全部国籍',
			'namezh', 'namezh');
	initSelectOpt({
		"dictionaryCode" : "PROFESSION"
	}, '/datadic/list', 'sel_edit_artistType', '全部职业', 'dicitemNameZh',
			'dicitemNameZh');
	$("#addEditform #sel_edit_marriage").empty();
	$("#addEditform #sel_edit_marriage")
			.append(
					"<option id='defult' selected='selected' value>---婚姻状态---</option><option value='0'>未婚</option><option value='1'>已婚</option><option value='2'>离异</option><option value='3'>丧偶</option>");
	$("#addEditform #sel_edit_isTeam").empty();
	$("#addEditform #sel_edit_isTeam")
			.append(
					"<option id='defult' selected='selected' value>---是否团队---</option><option value='0' id='team_false'>否</option><option value='1' id='team_true'>是</option>");
	initSelectOpt('', '/artist/languageList', 'txt_edit_defaultLanguage',
			'默认语言', 'languageId', 'nativeName');
	delImg();
}
/**
 * 重新加载table数据
 */
function reloadTab() {
	$('#tb_ircodetype').bootstrapTable('refresh', {
		"ircodeTypeId" : $("#txt_search_ircodeTypeId").val(),
		"ircodeTypeName" : $("#txt_search_ircodeTypeName").val()
	});
}

/**
 * 艺人搜索框校验
 */
function addSearchformValidate() {
	$('#searchform').bootstrapValidator({
		message : '请检查参数的合法性',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			artistCode : {
				message : '类型序号格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 16,
						message : '类型序号长度不能超过16位'
					},
					regexp : {
						regexp : "^[\\d]+$",
						message : '类型序号只能输入数字'
					}
				}
			},
			artistName : {
				message : '艺人名称格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 16,
						message : '艺人名称长度不能超过16位'
					}
				}
			},
			foreignName : {
				message : '外文名格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 16,
						message : '外文名长度不能超过16位'
					}
				}
			},
			alias : {
				message : '艺人别名格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 16,
						message : '艺人名称长度不能超过16位'
					}
				}
			},
			homeTown : {
				message : '出生地格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 16,
						message : '艺人名称长度不能超过16位'
					}
				}
			}
		}
	});
}

function addEditData() {
	var bootstrapValidator = $("#addEditform").data('bootstrapValidator');
	// 开启验证
	bootstrapValidator.validate();
	// 校验通过
	if (!bootstrapValidator.isValid()) {
		return false;
	}
	var uri = '/artist/' + $("#addEditType").val();
	var imgUrl = $("#addEditform #preImgId").attr("src");
	if (!isNotEmpty(imgUrl)) {
		showError("请上传图标")
		return;
	}
	var zh_artistI18n = {
		// 中文
		"id" : $("#addEditform #zh_artistI18n_id").val(),
		"artistCode" : $("#addEditform #zh_artistI18n_artistCode").val(),
		"name" : $("#addEditform #txt_edit_zh_name").val(),
		"foreignName" : $("#addEditform #txt_edit_zh_foreignName").val(),
		"alias" : $("#addEditform #txt_add_zh_alias").val(),
		"homeTown" : $("#addEditform #txt_edit_zh_homeTown").val(),
		"des" : $("#addEditform #txt_edit_zh_des").val(),
		"lang" : "zh_CN"
	}
	var en_artistI18n = {
		"id" : $("#addEditform #en_artistI18n_id").val(),
		"artistCode" : $("#addEditform #en_artistI18n_artistCode").val(),
		"name" : $("#addEditform #txt_edit_en_name").val(),
		"foreignName" : $("#addEditform #txt_edit_en_foreignName").val(),
		"alias" : $("#addEditform #txt_edit_en_alias").val(),
		"homeTown" : $("#addEditform #txt_edit_en_homeTown").val(),
		"des" : $("#addEditform #txt_edit_en_des").val(),
		"lang" : "en_US"
	}
	var artistInfo = {
		// 基本信息
		"artistCode" : $("#addEditform #info_artistCode").val(),
		"artistId" : $("#addEditform #info_artistId").val(),
		"defaultLanguage" : $("#addEditform #txt_edit_defaultLanguage").val(),
		"sex" : $("#addEditform #sel_edit_sex").val(),
		"nationality" : $("#addEditform #sel_edit_nationality").val(),
		"birthday" : $("#addEditform #txt_edit_birthday").val().toString(),
		"bloodGroup" : $("#addEditform #txt_edit_bloodGroup").val(),
		"height" : $("#addEditform #txt_edit_height").val(),
		"weight" : $("#addEditform #txt_edit_weight").val(),
		"constellation" : $("#addEditform #txt_edit_constellation").val(),
		"isTeam" : $("#addEditform #sel_edit_isTeam").val(),
		"firstName" : $("#addEditform #txt_edit_firstName").val(),
		"lastName" : $("#addEditform #txt_edit_lastName").val(),
		"searchName" : $("#addEditform #txt_edit_searchName").val(),
		"zone" : $("#addEditform #txt_edit_zone").val(),
		"firstLetter" : $("#addEditform #txt_edit_firstLetter").val(),
		"companyName" : $("#addEditform #txt_edit_companyName").val(),
		"buildTime" : $("#addEditform #date_edit_buildTime").val().toString(),
		"joininTime" : $("#addEditform #date_edit_joininTime").val().toString(),
		"education" : $("#addEditform #txt_edit_education").val(),
		"favorite" : $("#addEditform #txt_edit_favorite").val(),
		"motherTongue" : $("#addEditform #txt_edit_motherTongue").val(),
		"artistType" : $("#addEditform #sel_edit_artistType").val(),
		"marriage" : $("#addEditform #sel_edit_marriage").val(),
		"headImg" : $("#addEditform #preImgId").attr("src")
	}
	var artistI18ns = [];
	if ($("#addEditform #en_artistI18n_artistCode").val() != ""
			&& $("#addEditform #en_artistI18n_artistCode").val() != null
			&& $("#txt_edit_en_name").val() != ""
			&& $("#txt_edit_en_name").val() != null) {
		artistI18ns.push(en_artistI18n);
	} else {
		artistInfo.defaultLanguage = "zh_CN";
		artistI18ns.push(en_artistI18n);
	}
	if ($("#addEditform #zh_artistI18n_artistCode").val() != ""
			&& $("#addEditform #zh_artistI18n_artistCode").val() != null
			&& $("#txt_edit_zh_name").val() != ""
			&& $("#txt_edit_zh_name").val() != null) {
		artistI18ns.push(zh_artistI18n);
	}
	var data = {
		"artistInfo" : artistInfo,
		"artistI18ns" : artistI18ns
	};
	var addEditParam = {
		isLoading : false,
		'data' : data,
		url : uri,
		callback : function(response) {
			if (null != response && response.retCode == 0) {
				$('#addEditModel').modal('hide');
				showSuccess(response.retMsg);
				reloadTab();
			} else {
				showError(response.retMsg);
			}
		}
	};
	Ajax(addEditParam);
}
/**
 * 主管过虑框校验
 */
function addEditformValidate() {
	// 先清空提示
	var bootstrapValidator = $("#addEditform").data('bootstrapValidator');
	if (bootstrapValidator) {
		bootstrapValidator.destroy();
	}
	$('#addEditform').data('bootstrapValidator', null);

	$('#addEditform').bootstrapValidator({
		message : '请检查参数的合法性',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		excluded:[":disabled"],
		fields : {
			zh_artistCode : {
				message : '艺人编码格式不正确',
				validators : {
					notEmpty : {
						message : '艺人编码不能为空'
					},
				},

			},
			zh_name : {
				message : '名称格式不正确',
				validators : {
					notEmpty : {
						message : '名称不能为空'
					},
					stringLength : {
						min : 0,
						max : 128,
						message : '名称长度不能超过16位'
					}
				}
			},
			zh_foreignName : {
				message : '外文名格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 128,
						message : '外文名长度不能超过128位'
					}
				}
			},
			zh_alias : {
				message : '别名格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 128,
						message : '别名长度不能超过128位'
					}
				}
			},
			zh_homeTown : {
				message : '出生地格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 128,
						message : '出生地长度不能超过128位'
					}
				}
			},
			zh_description : {
				message : '简介格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 4096,
						message : '类型备注长度不能超过4096位'
					}
				}
			},
			en_artistCode : {
				message : '艺人编码格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 128,
						message : '名称长度不能超过128位'
					},
				}
			},
			en_name : {
				message : '名称格式不正确',
				validators : {
					notEmpty : {
						message : '名称不能为空'
					},
					stringLength : {
						min : 0,
						max : 128,
						message : '名称长度不能超过128位'
					},
					regexp : {
						regexp : "^[ a-zA-Z]*$",
						message : '名称只能输入英文'
					}
				}
			},
			en_foreignName : {
				message : '外文名格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 128,
						message : '外文名长度不能超过128位'
					}
				}
			},
			en_alias : {
				message : '别名格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 128,
						message : '别名长度不能超过128位'
					}
				}
			},
			en_homeTown : {
				message : '出生地格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 128,
						message : '出生地长度不能超过128位'
					}
				}
			},
			en_description : {
				message : '简介格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 4096,
						message : '类型备注长度不能超过4096位'
					},
				}
			},
			editInfo_sex : {
				message : '性别格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 1,
						message : '性别长度不能超过1位'
					},
					regexp : {
						regexp : "^[0,1]$",
						message : '选择男女'
					}
				}
			},
			editInfo_birthday : {
				message : '出生日期格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 14,
						message : '出生日期长度不能超过14位'
					}
				}
			},
			editInfo_nationality : {
				message : '国籍格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 256,
						message : '国籍长度不能超过256位'
					},
				}
			},
			editInfo_bloodGroup : {
				message : '血型格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 10,
						message : '血型长度不能超过10位'
					},
					regexp : {
						regexp : '^(A|B|AB|O|Rh)$',
						message : '血型必须为A、B、AB、O、Rh'
					}
				}
			},
			editInfo_height : {
				message : '身高格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 10,
						message : '身高长度不能超过10位'
					},
					regexp : {
						regexp : "^[1-9][0-9]{1,}$",
						message : '身高必须为数字'
					}
				}
			},
			editInfo_weight : {
				message : '体重格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 10,
						message : '体重长度不能超过10位'
					},
					regexp : {
						regexp : "^[1-9][0-9]{1,}$",
						message : '体重必须为数字'
					}
				}
			},
			editInfo_constellation : {
				message : '星座格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 10,
						message : '长度不能超过10位'
					}
				}
			},
			editInfo_isTeam : {
				message : '格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 1,
						message : '长度不能超过1位'
					},
					regexp : {
						regexp : "^[0,1]$",
						message : '选择是否'
					}
				}
			},
			editInfo_firstName : {
				message : '名格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 128,
						message : '名长度不能超过128位'
					}
				}
			},
			editInfo_lastName : {
				message : '姓格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 128,
						message : '姓长度不能超过128位'
					}
				}
			},
			editInfo_searchName : {
				message : '搜索名格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 128,
						message : '搜索名长度不能超过128位'
					}
				}
			},
			editInfo_zone : {
				message : '归属地格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 256,
						message : '归属地长度不能超过256位'
					}
				}
			},
			editInfo_firstLetter : {
				message : '首字母格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 1,
						message : '首字母长度不能超过1位'
					},
					regexp : {
						regexp : "^[A-Z]$",
						message : '体重必须为大写字母'
					}
				}
			},
			editInfo_companyName : {
				message : '公司名格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 128,
						message : '公司名长度不能超过128位'
					}
				}
			},
			editInfo_education : {
				message : '教育程度格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 128,
						message : '教育程度长度不能超过128位'
					}
				}
			},
			editInfo_favorite : {
				message : '爱好格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 256,
						message : '爱好长度不能超过256位'
					}
				}
			},
			editInfo_motherTongue : {
				message : '母语格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 256,
						message : '母语长度不能超过256位'
					}
				}
			},
			editInfo_marriage : {
				message : '婚姻状态格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 1,
						message : '婚姻状态长度不能超过1位'
					},
					regexp : {
						regexp : "^[0,1,2,3]$",
						message : '状态格式不正确'
					}
				}
			},
			editInfo_buildTime : {
				message : '时间格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 14,
						message : '时间长度不能超过14位'
					}
				}
			},
			editInfo_joininTime : {
				message : '时间格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 14,
						message : '时间长度不能超过14位'
					}
				}
			},
			artistType : {
				message : '格式不正确',
				validators : {
					stringLength : {
						min : 0,
						max : 64,
						message : '长度不能超过64位'
					}
				}
			},
			editInfo_defaultLanguage : {
				message : '默认语言格式不正确',
				validators : {
					notEmpty : {
						message : '默认语言不能为空'
					},
					regexp : {
						regexp : '^[a-z]{2}_[A-Z]{2}$',
						message : '默认语言必须为中英文'
					}
				}
			}
		}
	});
}