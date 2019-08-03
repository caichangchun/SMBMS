<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<div class="right">
  <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>供应商管理页面</span>
  </div>
  <div class="search">
      <input name="method" value="query" type="hidden"> <span>供应商编码：</span> <input name="queryProCode" type="text" value=""> <span>供应商名称：</span>
      <input name="queryProName" type="text" value=""> <input value="查 询" type="submit" id="searchbutton"> <a
        href="jsp/provideradd.do">添加供应商</a>
  </div>
  <!--供应商操作表格-->
    <input type="hidden" id="updatestatus" value="${updatestatus}" />
    <input type="hidden" id="addstatus" value="${addstatus}" />
  <table class="providerTable" cellpadding="0" cellspacing="0">
    <tr class="firstTr">
      <th width="10%">供应商编码</th>
      <th width="20%">供应商名称</th>
      <th width="10%">联系人</th>
      <th width="10%">联系电话</th>
      <th width="10%">传真</th>
      <th width="10%">创建时间</th>
      <th width="30%">操作</th>
<!--     </tr> -->
<!--     <tr> -->
<!--       <td>供应商编码</td> -->
<!--       <td>供应商名称</td> -->
<!--       <td>联系人</td> -->
<!--       <td>联系电话</td> -->
<!--       <td>传真</td> -->
<!--       <td>创建时间</td> -->
<!--       <td><span><a class="viewProvider" href="javascript:;" proid=><img src="static/images/read.png" alt="查看" title="查看" /></a></span> <span><a -->
<!--           class="modifyProvider" href="javascript:;" proid=proname=><img src="static/images/xiugai.png" alt="修改" title="修改" /></a></span> <span><a -->
<!--           class="deleteProvider" href="javascript:;" proid=><img src="static/images/schu.png" alt="删除" title="删除" /></a></span></td> -->
<!--     </tr> -->
  </table>
  	<div id="page">
		<div class="page">
			<span>首页</span> <span>上一页</span> <span>1</span> <span>2</span> <span>3</span>
			<span>4</span> <span>5</span> <span>下一页</span> <span>尾页</span>
		</div>

	</div>
</div>
</section>
<!--点击删除按钮后弹出的页面-->
<div class="zhezhao"></div>
<div class="remove" id="removeProv">
  <div class="removerChid">
    <h2>提示</h2>
    <div class="removeMain">
      <p>你确定要删除该供应商吗？/删除成功</p>
      <a href="jsp/providerlist/#" id="yes">确定</a> <a href="jsp/providerlist/#" id="no">取消</a>
    </div>
  </div>
</div>
<div class="remind" id="remindProv">
  <div class="removerChid">
    <h2>提示</h2>
    <div class="remindMain">
      <p>删除成功</p>
      <a href="jsp/providerlist/#" id="queding">确定</a>
    </div>
  </div>
</div>
<%@include file="common/foot.jsp"%>
<script type="text/javascript" src="static/js/providerlist.js"></script>
