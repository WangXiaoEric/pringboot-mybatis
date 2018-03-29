(function( $ ) {
    var buReport={
        init: function () {
            this.initBuCharts();
        },
        initBuCharts:function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('divBuCharts'));
            option = {
                title : {
                    text: '',
                    subtext: '',
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['人事部','财务部','IT部','运营部','策划部']
                },
                series : [
                    {
                        name: '开支',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        label: {
                            normal: {
                                formatter: "{b} : {c} ({d}%)"
                            }
                        },
                        data:[
                            {value:335, name:'人事部'},
                            {value:310, name:'财务部'},
                            {value:234, name:'IT部'},
                            {value:135, name:'运营部'},
                            {value:1548, name:'策划部'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    }
    $(document).ready(function() {
        buReport.init();
    } );
}).apply( this, [ jQuery ]);