(function( $ ) {
    var datatableInit = function() {
        $('#datatable-default').dataTable({
            serverSide: false,  //启用服务器端分页
            searching: false,  //禁用原生搜索
            "paging": false,  //是否分页
            "bInfo": false,  //显示页脚信息
        });
    };
    $(function() {
        datatableInit();
    });
}).apply( this, [ jQuery ]);