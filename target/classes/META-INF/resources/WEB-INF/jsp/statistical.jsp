<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>统计报表页面</span>
    </div>
    <div class="search" style="padding-left: 10%;">
<%--        <div style="padding-left: 10%;">--%>
            <label >日期：</label> <input type="text" Class="Wdate" id="searchdate" value="" readonly="readonly" onclick="WdatePicker();">
            <input value="查 询" type="button" id="searchbutton" style="cursor: pointer;">
<%--        </div>--%>
    </div>
    <div class="providerView" style="background: #f6f7f9;margin: 5px;border: 1px solid #ccc;">
        <script>/*Fixing iframe window.innerHeight 0 issue in Safari*/document.body.clientHeight;</script>

        <!-- 每日产品topn -->
        <div style="float:left;width:30%;height: 30%;padding-left: 10%;">
            <h2 style="text-align:center;margin: 15px;">当日订单排行</h2>
        <table class="g2-table">
            <!--head-->
            <thead class="g2-thead">
            <tr>
                <th class="th-index">排序</th>
                <th class="th-id">商品名</th>
                <th>销售额（单位：元）</th>
            </tr>
            </thead>
            <!--body-->
            <tbody>
            </tbody>
        </table>
        </div>

        <!--销售额组成饼状图-->
        <div style="float:left;width:30%;height:30%;padding-left: 10%;">
            <h2 style="text-align:center;margin: 15px;">当日订单额组成</h2>
            <div id="mountNode1" ></div>
        </div>
        <!--销售额折线图-->
        <div style="float:left;width:30%;height:30%;padding-left: 10%;">
            <h2 style="text-align:center;margin: 15px;">七日订单额折线图</h2>
            <div id="mountNode" ></div>
        </div>
        <!--订单量折线图-->
        <div style="float:left;width:30%;height:30%;padding-left: 10%;">
            <h2 style="text-align:center;margin: 15px;">七日订单量折线图</h2>
            <div id="mountNode2" ></div>
        </div>
    </div>
</div>
</section>
<%@include file="common/foot.jsp" %>
<script type="text/javascript" src="static/js/statistical.js"></script>