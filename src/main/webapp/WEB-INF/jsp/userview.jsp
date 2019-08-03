<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<div class="right">
  <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>用户管理页面 >> 用户信息查看页面</span>
  </div>
  <div class="providerView">
    <p>
      <strong>用户编号：</strong>
      <span id="id"></span>
    </p>
    <p>
      <strong>用户名称：</strong>
      <span id="usercode"></span>
    </p>
    <p>
      <strong>用户性别：</strong>
      <span id="gender"></span>
    </p>
    <p>
      <strong>出生日期：</strong>
      <span id="birthday"></span>
    </p>
    <p>
      <strong>用户电话：</strong>
      <span id="phone"></span>
    </p>
    <p>
      <strong>用户地址：</strong>
      <span id="address"></span>
    </p>
    <p>
      <strong>用户角色：</strong>
      <span id="userrole"></span>
    </p>
    <div class="providerAddBtn">
      <input type="button" id="back" name="back" value="返回">
    </div>
  </div>
</div>
<input type="text" id="viewusercode" value="${viewusercode}" hidden="hidden"/>
</section>
<%@include file="common/foot.jsp"%>
<script type="text/javascript" src="../../static/js/userview.js"></script>