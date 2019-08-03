<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<div class="right">
  <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>用户管理页面 >> 用户修改页面</span>
  </div>
  <div class="providerAdd">
    <form id="userForm" name="userForm" method="post" action="jsp/userupdate">
      <input type="hidden" id="usercode" name="usercode" value="${modifyusercode}" />
      <input type="hidden" id="modifyusercode" value="${modifyusercode}"/>
      <input type="hidden" id="roleid" value="${modifyuserrole}"/>
      <div>
        <label for="userName">用户名称：</label> <input type="text" name="username" id="username" value=""> <font color="red"></font>
      </div>
      <div>
        <label>用户性别：</label> <select name="gender" id="gender">
          <c:choose>
            <c:when test="${modifygender==1}">
              <option value="1" selected="selected">男</option>
              <option value="2">女</option>
            </c:when>
            <c:otherwise>
              <option value="1">男</option>
              <option value="2" selected="selected">女</option>
            </c:otherwise>
          </c:choose>
        </select>
      </div>
      <div>
        <label for="data">出生日期：</label> <input type="text" Class="Wdate" id="birthday" name="birthday" value="" readonly="readonly"
          onclick="WdatePicker();"> <font color="red"></font>
      </div>
      <div>
        <label for="userphone">用户电话：</label> <input type="text" name="phone" id="phone" value=""> <font color="red"></font>
      </div>
      <div>
        <label for="userAddress">用户地址：</label> <input type="text" name="address" id="address" value="">
      </div>
      <div>
        <label>用户角色：</label>
        <!-- 列出所有的角色分类 -->
        <input type="hidden" value="" id="rid" /> <select name="userrole" id="userrole"></select> <font color="red"></font>
      </div>
      <div class="providerAddBtn">
        <input type="button" name="save" id="save" value="保存" /> <input type="button" id="back" name="back" value="返回" />
      </div>
    </form>
  </div>
</div>
</section>
<%@include file="common/foot.jsp"%>
<script type="text/javascript" src="../../static/js/usermodify.js"></script>
