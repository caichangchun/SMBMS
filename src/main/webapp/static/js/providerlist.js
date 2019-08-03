var providerObj;
var updatestatus=$("#updatestatus").val();
var addstatus=$("#addstatus").val();

//供应商管理页面上点击删除按钮弹出删除框(providerlist.jsp)
function deleteProvider(obj){
   $.ajax({
      type:"POST",
      url:"/jsp/provider.do",
      //async: false,
      data:{method:"delprovider",proid:obj.attr("proid")},
      //dataType:"json",
      success:function(data){
          status=data.delResult;
         if(data.delResult == "true"){//删除成功：移除删除行
            cancleBtn();
            changeDLGContent_remind("删除供应商【"+obj.attr("proname")+"】成功");
            openRemindDLG();
            //alert("删除成功");
            //obj.parents("tr").remove();
            var  params={"pageNum":1,"pageSize":5};
            getProviderList(params);
         }else if(data.delResult == "false"){//删除失败
            //alert("对不起，删除供应商【"+obj.attr("proname")+"】失败");
            cancleBtn();
            changeDLGContent_remind("对不起，删除供应商【"+obj.attr("proname")+"】失败");
            openRemindDLG();
         }else{
            //alert("对不起，该供应商【"+obj.attr("proname")+"】下有【"+data.delResult+"】条订单，不能删除");
            cancleBtn();
            changeDLGContent_remind("对不起，该供应商【"+obj.attr("proname")+"】下有【"+data.delResult+"】条订单，不能删除");
            openRemindDLG();
         }
      },
      error:function(data){
         //alert("对不起，删除失败");
         changeDLGContent("对不起，删除失败");
      }
   });

}

function openYesOrNoDLG(){
   $('.zhezhao').css('display', 'block');
   $('#removeProv').fadeIn();
}

function openRemindDLG(){
   $('.zhezhao').css('display', 'block');
   $('#remindProv').fadeIn();
}

function cancleBtn(){
   $('.zhezhao').css('display', 'none');
   $('#removeProv').fadeOut();
}

function cancleBtn_remind(){
   $('.zhezhao').css('display', 'none');
   $('#remindProv').fadeOut();
}

function changeDLGContent(contentStr){
   var p = $(".removeMain").find("p");
   p.html(contentStr);
}

function changeDLGContent_remind(contentStr){
   var p = $(".remindMain").find("p");
   p.html(contentStr);
}

function parseDate(milliseconds){
    var d = new Date(milliseconds);
    return d.getFullYear()+"-"+zore(d.getMonth()+1)+"-"+zore(d.getDate())+" "+zore(d.getHours())+":"+zore(d.getMinutes())+":"+zore(d.getSeconds());
}
function zore(str){
    return str<10?"0"+str:str;
}
function getProviderList(params){
   $.post("jsp/getProviderList",params,function(result){
        //params["currentPage"] = result.currentPage;
        params["pageNum"] = result.pageNum;
        params["pageSize"] = result.pageSize;
        params["procode"] = result.procode;
        params["proname"] = result.proname;

       //加载分页图标
       		 var spans = "<span class=\"first\">首页</span><span class=\"prev\">上一页</span>";
       	        $.each(result.navigatepageNums, function(){
       	            if(isNaN(this)){
       	                return;
       	            }
       	            if(result.pageNum==this){
       	                spans+="<span class=\"pageNum\">"+this+"</span>";
       	            }else{
       	                spans+="<span class=\"other\">"+this+"</span>";
       	            }
       	        });
       	        spans+="<span class=\"next\">下一页</span><span class=\"last\">尾页</span>";
       	        $(".page>span").remove();
       	        $(".page").html(spans);

       	        if(result.isFirst){
       	            $(".first,.prev").css("background","#eee");
       	        }else{
       	            $(".first").click(function(){
       	                params["pageNum"] = 1;
       	                getProviderList(params);
       	            });
       	            $(".prev").click(function(){
       	                params["pageNum"]--;
       	                getProviderList(params);
       	            });
       	        }

       	        if(result.isLast){
       	            $(".last,.next").css("background","#eee");
       	        }else{
       	            $(".last").click(function(){
       	                params["pageNum"] = result.total;
       	                getProviderList(params);
       	            });
       	            $(".next").click(function(){
       	                params["pageNum"]++;
       	                getProviderList(params);
       	            });
       	        }
       	        $(".other").click(function(){
       	            params["pageNum"] = $(this).text();
       	            getProviderList(params);
       	        });

       	        $(".providerTable>tbody>tr:gt(0)").remove();
           $.each(result.rows, function(){
               var $tr = $("<tr>"+
                             "<td>"+this.procode+"</td>"+
                             "<td>"+this.proname+"</td>"+
                             "<td>"+this.procontact+"</td>"+
                             "<td>"+this.prophone+"</td>"+
                             "<td>"+this.profax+"</td>"+
                             "<td>"+parseDate(this.creationdate)+"</td>"+
                             " <td><span><a class=\"viewProvider\" href=\"javascript:;\" proid="+this.id+"><img src=\"static/images/read.png\" alt=\"查看\" title=\"查看\" /></a></span> <span><a class=\"modifyProvider\" href=\"javascript:;\" proid="+this.id+" proname="+this.proname+"><img src=\"static/images/xiugai.png\" alt=\"修改\" title=\"修改\" /></a></span> <span><a class=\"deleteProvider\" href=\"javascript:;\" proid="+this.id+" proname="+this.proname+"><img src=\"static/images/schu.png\" alt=\"删除\" title=\"删除\" /></a></span></td>"+
                           "</tr>");
               $(".providerTable>tbody").append($tr);
           });
       });
}
$(function(){

   var  params={"pageNum":1,"pageSize":15};
   getProviderList(params);
   $("#searchbutton").click(function(){
           var procode = $("input[name=queryProCode]").val();
           var proname = $("input[name=queryProName]").val();
           params["procode"] = procode;
           params["proname"] = proname;
           params["currentPage"] = 1;
           getProviderList(params);
           $("#searchbutton").blur();
   })

   $(".providerTable>tbody").on("click",".deleteProvider",function(){
              providerObj = $(this);
              //alert(providerObj.attr("proid"));
              changeDLGContent("你确定要删除供应商【"+providerObj.attr("proname")+"】吗？");
              openYesOrNoDLG();
           });

   $(".providerTable>tbody").on("click",".viewProvider",function(){
      //将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
      var obj = $(this);
      window.location.href="/jsp/provider.do?method=view&proid="+ obj.attr("proid");
   });

   $(".providerTable>tbody").on("click",".modifyProvider",function(){
      var obj = $(this);
      window.location.href="/jsp/provider.do?method=modify&proid="+ obj.attr("proid");
   });

   $("#no").on("click",function(){
      cancleBtn();
   });

   $("#yes").on("click",function(){
      deleteProvider(providerObj);
   });

   $("#queding").on("click",function(){
          cancleBtn_remind();
       });

   	if(updatestatus=="true") alert("修改成功！");
   	if(updatestatus=="false") alert("修改成功！");
   	if(addstatus=="true") alert("增加成功！");
   	if(addstatus=="false") alert("增加失败！");

   	$("#updatestatus").val("");
   	$("#addstatus").val("");

});