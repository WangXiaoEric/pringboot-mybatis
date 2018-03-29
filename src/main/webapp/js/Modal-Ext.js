/*
Written by: 	liumang
Date: 			2018/3/23
*/

/*
	模态窗口弹出二次封装

	panel :		需要弹出窗口的控件的类名或者ID(类名前面需要添加.，ID前面需要添加#)
	confirm :	模态窗口确认按钮事件
	callback:	模态窗口自定义事件函数。
				所有事件函数中的this变量全部为ModalExt,模态窗口原框架中this表示模态窗口。
				需要使用模态窗口请使用this.popup;
				
*/
function ModalExt(panel, confirm, callback )
{
	var modalExtData = {};
	//设置数据
	this.setData = function(key,value){
		modalExtData[key] = value;
	}
	//获取数据
	this.getData = function(key)
	{
		return modalExtData[key];
	}

	if(!!!panel)
	{
		return;
	}

	var cb = {} , _this = this;
	//设置callback函数上下文
	for(var i in callback)
	{
		var fun = callback[i];
		cb[i] = function(){ return fun.apply(_this) };
	}
	//绑定模态窗口
	this.popup = $(panel).magnificPopup({
		type: 'inline',
		preloader: false,
		modal: true,
		removalDelay: 200,
		mainClass: 'my-mfp-slide-bottom',
		callbacks: cb
	});
	
	var modal = $(panel).attr("href");
	//绑定模态窗口取消按钮
	$(modal).find(".modal-dismiss").click(function(e){
		e.preventDefault();
		$.magnificPopup.close();
	});
	//绑定模态窗口确认按钮
	$(modal).find(".modal-confirm").click(function(e){
		e.preventDefault();
		$.magnificPopup.close();
		if(!!confirm)
			confirm(_this);
	});
}