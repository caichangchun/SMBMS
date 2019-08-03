/*
日期格式化函数
 */
Date.prototype.format = function(fmt) {
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt)) {
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
        if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
}

$(function () {
    var date = new Date();
    getStatisstical(date);
    var time= new Date(date).format("yyyy-MM-dd");
    $("#searchdate").val(time);
});
/*
查询按钮
 */
$("#searchbutton").on('click',function(){
    $(".g2-table>tbody").empty();
    $("#mountNode").empty();
    $("#mountNode1").empty();
    $("#mountNode2").empty();
    getStatisstical(new Date($("#searchdate").val()));
    $("#searchbutton").blur();
});
/*
获取报表信息
 */
getStatisstical=function(date){
/*每日商品排行*/
var topnproduct_data=[];
var maxprice=0;

$.post("jsp/getAllTopnProduct",function(result){
    console.log(result);
    $.each(result,function (i) {
        topnproduct_data.push({index:i+1,source:this.product_name,data:[{'value':this.total_price}]})
        if(this.total_price>maxprice){
            maxprice=this.total_price;
        }
    });

    var $tbody = $('.g2-table').children('tbody');
    topnproduct_data.forEach(function(d, index) {
        //create container
        var $tr = $('<tr class="g2-table-row" id="order"></tr>');
        $tbody.append($tr);
        //index
        var indexClass = index < 3 ? 'top-class' : 'normal-class';
        var $index = $('<td class="g2-table-column g2-table-column-index"><div class="' + indexClass + '">' + d.index + '</div></td>');
        $tr.append($index);
        //source
        var $source = $('<td class="g2-table-column" style="color:red;">' + d.source + '</td>');
        $tr.append($source);
        //data
        var dataID = 'bar-chart-' + index;
        var $data = $('<td class="g2-table-column"><div class="chartContainer" id="' + dataID + '"></div></td>');
        $tr.append($data);
        createSingleBarChart(dataID, d.data);
    });
    function createSingleBarChart(containerId, data) {
        var chart = new G2.Chart({
            container: containerId,
            forceFit: true,
            height: 60,
            padding: 0
        });
        chart.source(data, {
            value: {
                max: maxprice/10*11,
                min: 0
            }
        });
        chart.legend(false);
        chart.axis(false);
        chart.tooltip({
            type: 'mini'
        });
        chart.coord().transpose();
        chart.interval().position('1*value').opacity(1).size(120).label('value', {
            offset: 10,
            textStyle: {
                fontSize: 12,
                color: '#F00'
            }
        });
        chart.render();
    }
});


/*每日销售额折线图*/
function add0(m){return m<10?'0'+m:m }
function format(shijianchuo)
{
    //shijianchuo是整数，否则要parseInt转换
    var time = new Date(shijianchuo);
    var y = time.getFullYear();
    var m = time.getMonth()+1;
    var d = time.getDate();
    var h = time.getHours();
    var mm = time.getMinutes();
    var s = time.getSeconds();
    return add0(m)+'-'+add0(d);
}
$.post("jsp/getDayTotalPrice",{"date":date},function(result){
    //console.log(result);
    var daytotalprice_data=[];
    $.each(result,function (i) {
        daytotalprice_data.push({year:format(this.date),销售额:this.total
        });
    });
    var chart = new G2.Chart({
        container: 'mountNode',
        forceFit: true,
        // height: window.innerHeight
    });
    chart.source(daytotalprice_data);
    chart.scale('销售额', {
        min: 0
    });
    chart.scale('year', {
        range: [0, 1]
    });
    chart.tooltip({
        crosshairs: {
            type: 'line'
        }
    });
    chart.line().position('year*销售额');
    chart.point().position('year*销售额').size(4).shape('circle').style({
        stroke: '#fff',
        lineWidth: 1
    });
    chart.render();
});


/*每日销售额组成饼状图*/
$.post("jsp/getProductPercent",function(result){
    //console.log(result);
    var ProductPercent_data=[];
    var sum=0;
    $.each(result,function (i) {
        sum+=this.total_price;
    });
    $.each(result,function (i) {
        ProductPercent_data.push({type:this.product_name,value:this.total_price/sum});
    });

    var ds = new DataSet();
    var dv = ds.createView().source(ProductPercent_data);
    dv.transform({
        type: 'percent',
        field: 'value',
        dimension: 'type',
        as: 'percent'
    });

    var chart = new G2.Chart({
        container: 'mountNode1',
        forceFit: true,
        //height: window.innerHeight,
        padding: 14
    });
    chart.source(dv);
    chart.legend(false);
    chart.coord('theta', {
        radius: 0.75
    });
    chart.intervalStack().position('value').color('type', ['#2593fc', '#38c060', '#27c1c1', '#705dc8', '#3b4771', '#f9cb34']).opacity(1).label('value', function(val) {
        var offset = val > 0.02 ? -30 : 30;
        var label_class = val > 0.02 ? "g2-label-item-inner" : "g2-label-item-outer";
        return {
            offset: offset,
            useHtml: true,
            htmlTemplate: function htmlTemplate(text, item) {
                var d = item.point;
                var percent = String(parseInt(d.percent * 100)) + "%";
                return '<div class=' + label_class + '>' + d.type + percent + '</div>';
            }
        };
    });
    chart.render();
});

/*订单量折线图*/
$.post("jsp/getBillCount",{"date":date},function(result){
    //console.log(result);
    var bill_data=[];
    $.each(result,function (i) {
        bill_data.push({year:format(this.billcount_date),订单量:this.bill_count
        });
    });
    var chart = new G2.Chart({
        container: 'mountNode2',
        forceFit: true,
        // height: window.innerHeight
    });
    chart.source(bill_data);
    chart.scale('订单量', {
        min: 0
    });
    chart.scale('year', {
        range: [0, 1]
    });
    chart.tooltip({
        crosshairs: {
            type: 'line'
        }
    });
    chart.line().position('year*订单量');
    chart.point().position('year*订单量').size(4).shape('circle').style({
        stroke: '#fff',
        lineWidth: 1
    });
    chart.render();
});
}



