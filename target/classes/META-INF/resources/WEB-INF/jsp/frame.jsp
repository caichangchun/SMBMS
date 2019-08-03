<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
    <div class="right">
<%--        <img class="wColck" src="static/images/clock.jpg" alt=""/>--%>

        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>首页</span>
        </div>
        <div class="providerView">
            <script>/*Fixing iframe window.innerHeight 0 issue in Safari*/document.body.clientHeight;</script>
            <h2 style="text-align:center;">欢迎来到超市订单管理系统!</h2>

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
            <!--销售额组成饼状图-->
            <div style="float:left;width:30%;height:30%;padding-left: 10%;">
                <h2 style="text-align:center;margin: 15px;">历史订单额组成</h2>
                <div id="mountNode1" ></div>
            </div>

            <!-- 每日产品topn -->
            <div style="float:left;width:30%;height: 30%;padding-left: 10%;">
                <h2 style="text-align:center;margin: 15px;">历史订单排行</h2>
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
        </div>
    </div>
<%@include file="common/foot.jsp" %>
<script type="text/javascript" src="static/js/fram.js"></script>
