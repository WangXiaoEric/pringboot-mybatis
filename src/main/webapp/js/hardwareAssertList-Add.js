/*
Written by: 	liumang
Date:			2018/3/28
*/

//页面数据实体
var softwareAssertListData = {
	modalAction:null,
	actionObj:null,
	clear:function(){
		this.modalAction=null;
	}
};

//初始化模态窗口
function  initModal()
{
	new ModalExt(".new-item-submit" , submitForm);
	new ModalExt(".new-item-reset" , resetForm);
	new ModalExt(".Modal-Business-System-Owners" , chooseMultiSelect , 
		{
			beforeOpen:function(){
				var source = $('.Modal-Business-System-Owners').get(0);
				this.setData('source' , source);
				initMultiSelectModal(source);
		}
	});
}

//清除表单信息
function resetForm()
{
	var frm = $("#form2");
		frm.find('input').val("");
		frm.find('select').find("option:first").attr("selected","selected");
		frm.find('textarea').val("");

	new PNotify({
			title: '提示',
			text: '数据还原成功！',
			type: 'success',
			hide: true,
			delay: 1800
		});
}

//提交
function submitForm()
{
	var ajaxData = {};
	var frm = $("#form2");
		frm.find('input[submit-tag]').each(function(){
			ajaxData[this.getAttribute("submit-tag")] = this.value;
		});
		frm.find('select[submit-tag]').each(function(){
			ajaxData[this.getAttribute("submit-tag")] = this.value;
		});
		frm.find('textarea[submit-tag]').each(function(){
			ajaxData[this.getAttribute("submit-tag")] = this.value;
		});

	console.info(ajaxData);

	new PNotify({
			title: '提示',
			text: '数据提交成功！',
			type: 'success',
			hide: true,
			delay: 1800
		});
}


//多项选择事件
function chooseMultiSelect(modalExt)
{
	var obj = modalExt.getData('source');
	var result = [];
	var target = $(obj.getAttribute("lmc-multi-select-to")).find(".data-panel  :checkbox:checked").each(function(){
		result.push(this.value);
	});
	$(obj.getAttribute("lmc-multi-select")).val(result.join(","));
}

//初始化选择面板数据
function initMultiSelectModal(obj)
{
	var source = $(obj.getAttribute("lmc-multi-select")).val();
	var sources = source.split(',');
	var target = $(obj.getAttribute("lmc-multi-select-to")).find(".data-panel  :checkbox").each(function(){
		for(var i=0;i<sources.length;i++)
		{
			if(sources[i] == this.value)
			{
				this.checked = true;
				return;
			}
		}
		this.checked = false;
	});
}

//初始化展开、折叠按钮事件
function initBtn()
{
	$('.all-expand').click(function(){
		var panel = $($(this).attr("panel"));
		panel.find("section").removeClass("panel-collapsed");
 		panel.find(".panel-body").show(100);
	});

	$('.all-collapsing').click(function(){
		var panel = $($(this).attr("panel"));
		panel.find("section").addClass("panel-collapsed");
 		panel.find(".panel-body").hide(100);
	});
}

$(function(){
	initBtn();
	initModal();
});