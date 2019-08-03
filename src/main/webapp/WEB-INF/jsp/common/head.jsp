<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>超市订单管理系统</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" />
<link type="text/css" rel="stylesheet" href="static/css/public.css" />
<% response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","No-cache");
response.setDateHeader("Expires", -1);
response.setHeader("Cache-Control", "No-store"); %>
</head>
<body>
  <!--头部-->
  <header class="publicHeader">
    <h1>超市订单管理系统</h1>
    <div class="publicHeaderR">
      <p>
        <span>下午好！</span> <span style="color: #fff21b">${user.username }</span> , 欢迎你！
      </p>
      <a href="login.jsp" onclick="return confirm('确定退出?')" >退出</a>
    </div>
  </header>
  <!--时间-->
  <section class="publicTime">
    <span id="time">2015年1月1日 11:11 星期一</span> <a href="https://www.google.cn/chrome/"  target="_Blank">温馨提示：为了能正常浏览，推荐使用谷歌浏览器！</a>
  </section>
  <!--主体内容-->
    <div class="left" >
      <h2 class="leftH2">
        <span class="span1"></span> 功能列表 <span></span>
      </h2>
      <nav>
        <ul class="list">
          <li><a href="jsp/frame">首页</a></li>
          <li><a href="jsp/statistical">统计报表</a></li>
          <li><a href="jsp/billlist">订单管理</a></li>
          <li><a href="jsp/providerlist/#">供应商管理</a></li>
          <li><a href="jsp/userlist">用户管理</a></li>
          <li><a href="jsp/pwdmodify">密码修改</a></li>
          <li><a href="login.jsp" onclick="return confirm('确定退出?')">退出系统</a></li>
        </ul>
      </nav>
    </div>