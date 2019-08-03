<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<div class="right">
  <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>用户管理页面</span>
  </div>
  <input type="hidden" id="addstatus" value="${addstatus}" />
  <input type="hidden" id="updatestatus" value="${updatestatus}" />
  <div class="search">
    <form action="">
      <input name="method" value="query" class="input-text" type="hidden"> <span>用户名：</span> <input name="queryname" class="input-text"
        type="text" value=""> <span>用户角色：</span>
      <select name="queryUserRole">
        <option value="0">--请选择--</option>
      </select>
      <input type="hidden" name="pageIndex" value="1" /> <input value="查 询" type="button" id="searchbutton"> <a href="/jsp/openuseradd">添加用户</a>
    </form>
  </div>
  <!--用户-->
  <table class="providerTable" cellpadding="0" cellspacing="0">
    <tr class="firstTr">
      <th width="10%">用户编码</th>
      <th width="20%">用户名称</th>
      <th width="10%">性别</th>
      <th width="10%">年龄</th>
      <th width="10%">电话</th>
      <th width="10%">用户角色</th>
      <th width="30%">操作</th>
    </tr>
  </table>
  <div id="page">
		<div class="page">
			<span>首页</span> <span>上一页</span> <span>1</span> <span>2</span> <span>3</span>
			<span>4</span> <span>5</span> <span>下一页</span> <span>尾页</span>
		</div>

	</div>
  <input type="hidden" id="totalPageCount" value="" />
</div>
</section>

<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<table class="providerTable_hide" cellpadding="0" cellspacing="0">
    <tr class="firstTr">
      <th width="10%">用户编码</th>
      <th width="20%">用户名称</th>
      <th width="10%">性别</th>
      <th width="10%">年龄</th>
      <th width="10%">电话</th>
      <th width="10%">用户角色</th>
      <th width="30%">操作</th>
    </tr>
  </table>

<div class="remove" id="removeUse">
  <div class="removerChid">
    <h2>提示</h2>
    <div class="removeMain">
      <p>你确定要删除该用户吗？</p>
      <a href="#" id="yes">确定</a> <a href="#" id="no">取消</a>
    </div>
  </div>
</div>

<%@include file="common/foot.jsp"%>
<script type="text/javascript" src="../../static/js/userlist.js"></script>