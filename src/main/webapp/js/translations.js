/*
Written by: 	liumang
Date:			2018/3/28
*/
(function ($) {
	$.getUrlParam = function (name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]); return null;
	}
})(jQuery);


function translations(){

	var _this = this;
	var v_lan_data  = {
		"en":v_lang_en,
		"zh":v_lang_zh,
		"zh-tw":v_lang_zh_tw
	};

	this.initTA = function(){
		$(".translations a").click(function(){
			var lan = this.getAttribute("lan");
			$.cookie('lan', lan);
			location.reload();
		});
	};

	this.initLang = function(){

		var lan = $.cookie('lan');
		var data ;
		if(!!!lan){
			var l = $.getUrlParam("lan");
			lan = !!l ? l : "en";
		}
		data = v_lan_data[lan];
		if(!!!data)
			data = v_lan_data["en"];
		var file = "js/lang-" + lan + ".js";
	
		//$.getScript(file);		
		$(".tran-el").each(function(){
			var tag = this.innerHTML;			
			tag = tag.replace("{{",'').replace("}}",'');
			this.innerHTML = data[tag];
		 });
	}

	this.initTA();
	this.initLang();
}

new translations();