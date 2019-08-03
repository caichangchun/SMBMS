<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>系统登录 - 超市订单管理系统</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" />
<script type="text/javascript" src="../static/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
  if (top.location != self.location) {
    top.location = self.location;
  }
  $(function(){
  var status=$("#status").val();
    if(status=="1"){
       alert("账号或密码错误！");
       }

});

</script>
</head>
<body class="login_bg">
  <section class="loginBox">
    <header class="loginHeader">
      <h1>超市订单管理系统</h1>
    </header>
    <section class="loginCont">
      <form class="loginForm" action="jsp/login" name="actionForm" id="actionForm" method="post">
        <div class="info"></div>
        <div class="inputbox">
          <label for="user">用户名：</label><input type="text" class="input-text" id="userCode" name="usercode" placeholder="请输入用户名" required />
        </div>
        <div class="inputbox">
          <label for="mima">密码：</label><input type="password" id="userPassword" name="userpassword" placeholder="请输入密码" required />
        </div>
        <div class="subBtn">
          <input type="submit" value="登录" /> <input type="reset" value="重置" />
          <input id="status" type="text" value="${status}" hidden="hidden" />
        </div>
      </form>
    </section>
  </section>
</body>
</html>
