<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/head.jsp"%>
<input type="hidden" id="viewprocode" value="${viewprovider.procode}" />
<input type="hidden" id="viewproname" value="${viewprovider.proname}" />
<input type="hidden" id="viewprocontact" value="${viewprovider.procontact}" />
<input type="hidden" id="viewprophone" value="${viewprovider.prophone}" />
<input type="hidden" id="viewprofax" value="${viewprovider.profax}" />
<input type="hidden" id="viewprodesc" value="${viewprovider.prodesc}" />
<div class="right">
  <div class="location">
    <strong>你现在所在的位置是:</strong>
    <span>供应商管理页面 >> 信息查看</span>
  </div>
  <div class="providerView">
    <p>
      <strong>供应商编码：</strong>
      <span id="procode"></span>
    </p>
    <p>
      <strong>供应商名称：</strong>
      <span id="proname"></span>
    </p>
    <p>
      <strong>联系人：</strong>
      <span id="procontact"></span>
    </p>
    <p>
      <strong>联系电话：</strong>
      <span id="prophone"></span>
    </p>
    <p>
      <strong>传真：</strong>
      <span id="profax"></span>
    </p>
    <p>
      <strong>描述：</strong>
      <span id="prodesc"></span>
    </p>
    <div class="providerAddBtn">
      <input type="button" id="back" name="back" value="返回">
    </div>
  </div>
</div>
</section>
<%@include file="common/foot.jsp"%>
<script type="text/javascript" src="static/js/providerview.js"></script>
