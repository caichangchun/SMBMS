<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<div class="right">
  <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>供应商管理页面 >> 供应商修改页</span>
  </div>
  <input type="hidden" id="modifyprocode" value="${modifyprovider.procode}" />
  <input type="hidden" id="modifyproname" value="${modifyprovider.proname}" />
  <input type="hidden" id="modifyprocontact" value="${modifyprovider.procontact}" />
  <input type="hidden" id="modifyprophone" value="${modifyprovider.prophone}" />
  <input type="hidden" id="modifyproaddress" value="${modifyprovider.proaddress}" />
  <input type="hidden" id="modifyprofax" value="${modifyprovider.profax}" />
  <input type="hidden" id="modifyprodesc" value="${modifyprovider.prodesc}" />
  <div class="providerAdd">
    <form id="providerForm" name="providerForm" method="post" action="jsp/updateprovider">
      <!--div的class 为error是验证错误，ok是验证成功-->
      <div class="">
        <input type="hidden" name="id" id="id" value="${modifyprovider.id}" />
        <label for="proCode">供应商编码：</label> <input type="text" name="procode" id="proCode" value="" readonly="readonly">
      </div>
      <div>
        <label for="proName">供应商名称：</label> <input type="text" name="proname" id="proName" value=""> <font color="red"></font>
      </div>
      <div>
        <label for="proContact">联系人：</label> <input type="text" name="procontact" id="proContact" value=""> <font
          color="red"></font>
      </div>
      <div>
        <label for="proPhone">联系电话：</label> <input type="text" name="prophone" id="proPhone" value=""> <font color="red"></font>
      </div>
      <div>
        <label for="proAddress">联系地址：</label> <input type="text" name="proaddress" id="proAddress" value="">
      </div>
      <div>
        <label for="proFax">传真：</label> <input type="text" name="profax" id="proFax" value="">
      </div>
      <div>
        <label for="proDesc">描述：</label> <textarea  name="prodesc" rows="3" cols="40" style="resize:none; vertical-align: top;" ></textarea>
      </div>
      <div class="providerAddBtn">
        <input type="button" name="save" id="save" value="保存"> <input type="button" id="back" name="back" value="返回">
      </div>
    </form>
  </div>
</div>
</section>
<%@include file="common/foot.jsp"%>
<script type="text/javascript" src="static/js/providermodify.js"></script>