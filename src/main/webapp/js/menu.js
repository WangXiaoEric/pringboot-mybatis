/*
Written by: 	liumang
Date:			2018/3/28
*/

function PageMenus(){
	var main_menus = [
		{
			name:"asset",
			icon:"copy",
			children:[
				{
					name:"softwareAssetList",
					url:"softwareAssertList.html",
				},
				{
					name:"hardtwareAssetList",
					url:"hardwareAssertList.html",
				},
				{
					name:"serviceAssetList",
					url:"serviceAssertList.html",
				}	
			]
		},
		{
			name:"costAllocationManagement",
			url:"costAllocation.html",
			icon:"home"
		},
		{
			name:"reportManagement",
			icon:"copy",
			children:[
                {
                    name:"report_topAssets",
                    url:"assetReport.html",
                }
                ,
                {
                    name:"report_buCostRatio",
                    url:"buReport.html",
                }
            ]
		},
		{
			name:"systemManagement",
			icon:"copy",
			children:[
				{
					name:"deptManagement",
					url:"buManagement.html",
				},
				{
					name:"userManagement",
					url:"userManagement.html",
				},
				{
					name:"dictManagement",
					url:"dictDataManagement.html",
				}	
			]
		}
	];

	function initMenus()
	{	
		var mainMenus =  $("#mainMenus");
		if(!!mainMenus && mainMenus.length > 0)
		{
			var menuHtml = ['<ul class="nav nav-main">'];
			//add home
			menuHtml.push('<li class="nav-active"><a href="index.html" u="index.html"><i class="fa fa-home"></i><span>Home</span></a></li>');
			for(var i=0;i<main_menus.length; i++)
			{
				var m = main_menus[i];
				Array.prototype.push.apply(menuHtml,initLiMenus(m));
			}
			menuHtml.push('</ul>');		
			mainMenus.html(menuHtml.join(''));

			initSelectedMenu();
		}	
	}

	function initSelectedMenu()
	{
		var menu = $("#mainMenus").attr("menu");
		$("#mainMenus a").each(function(){
			var u = this.getAttribute("u");
			if(u == menu)
			{
				var li = $(this).parent();
				li.addClass("nav-active");

				var parent = li.parent().parent();
				parent.addClass("nav-expanded nav-active");

				var breadcrumbs ;			
				if(li.parent().attr("class") != "nav nav-main")
					{
					breadcrumbs += "<li><span class='tran-el'>"+parent.find("span").html()+"</span></li>";
				}
				breadcrumbs += "<li><span class='tran-el'>"+$(this).find("span").html()+"</span></li>";
				$("#breadcrumbsMenu").append(breadcrumbs);
			}
		});
	}

	function initLiMenus(menu)
	{
		if(!!menu)
		{
			var hasChild = "children" in menu;
			var li = [];
			li.push(hasChild?'<li class="nav-parent">':'<li>');
			li.push('<a href="');
			li.push(("url" in menu)?menu["url"]:"#");	
			li.push('" u="')
			li.push(menu["url"]);
			li.push('">');
			if('icon' in menu)
			{
				li.push('<i class="fa fa-');
				li.push(menu["icon"]);
				li.push('"></i>');
			}
			li.push('<span class="tran-el">{{');
			li.push(menu["name"]);
			li.push("}}</span>");
			li.push('</a>');
			if(hasChild)
			{
				Array.prototype.push.apply(li,initChildMenus(menu["children"]));
			}
			li.push('</li>');
			return li;
		}
		return null;
	}

	function initChildMenus(menus)
	{
		if(!!menus && menus.length > 0){
			var menuHtml = ['<ul class="nav nav-children">'];
			for(var i=0;i<menus.length; i++)
			{
				Array.prototype.push.apply(menuHtml,initLiMenus(menus[i]));
			}
			menuHtml.push('</ul>');
			return menuHtml;
		}
		return null;
	}
	
	initMenus();
}

new PageMenus();