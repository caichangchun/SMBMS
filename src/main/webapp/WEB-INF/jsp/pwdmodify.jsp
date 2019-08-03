<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<div class="right">
  <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>密码修改页面</span>
  </div>
  <div class="providerAdd">
    <form id="userForm" name="userForm">
      <input type="hidden" name="method" value="savepwd">
      <!--div的class 为error是验证错误，ok是验证成功-->
      <div class="info"></div>
      <div class="">
        <label for="oldPassword">旧密码：</label> <input type="password" name="oldpassword" id="oldpassword" value=""> <font color="red"></font>
      </div>
      <div>
        <label for="newPassword">新密码：</label> <input type="password" name="newpassword" id="newpassword" value=""> <font color="red"></font>
      </div>
      <div>
        <label for="reNewPassword">确认新密码：</label> <input type="password" name="rnewpassword" id="rnewpassword" value=""> <font color="red"></font>
      </div>
      <div class="providerAddBtn">
        <input type="button" name="save" id="save" value="保存" class="input-button">
      </div>
    </form>
  </div>
</div>
<input type="text" id="pwd" value="${user.userpassword}" hidden="hidden"/>
</section>
<%@include file="common/foot.jsp"%>
<script type="text/javascript" src="../../static/js/pwdmodify.js">
</script>


