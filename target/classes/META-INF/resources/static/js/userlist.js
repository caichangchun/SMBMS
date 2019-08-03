var userObj;
var addstatus=null;
var updatestatus=null;

//用户管理页面上点击删除按钮弹出删除框(userlist.jsp)
function deleteUser(userid){
if(confirm("确定删除用户？")){
	$.ajax({
		type:"POST",
		url:"/jsp/user",
		data:{"userid":userid},
		success:function(data){
			if(data.status == "true"){//删除成功：移除删除行
				cancleBtn();
				alert("删除成功");
				var  params={"pageNum":1,"pageSize":5};
                getUserList(params);
			}else if(data.status == "false"){//删除失败
				//alert("对不起，删除用户【"+obj.attr("username")+"】失败");
				changeDLGContent("对不起，删除用户失败");
			}
		},
		error:function(data){
			//alert("对不起，删除失败");
			changeDLGContent("对不起，删除失败");
		}
	});
	}
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeUse').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeUse').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}
function parseDate(milliseconds){
    var d = new Date(milliseconds);
    return d.getFullYear()+"-"+zore(d.getMonth()+1)+"-"+zore(d.getDate())+" "+zore(d.getHours())+":"+zore(d.getMinutes())+":"+zore(d.getSeconds());
}
function zore(str){
    return str<10?"0"+str:str;
}

function getUserList(params){
	$.post("jsp/getUser",params,function(result){
		 params["pageNum"] = result.pageNum;
	     params["pageSize"] = result.pageSize;
	     params["usercode"] = result.usercode;
	     params["username"] = result.username;
	        
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
	                getUserList(params);
	            });
	            $(".prev").click(function(){
	                params["pageNum"]--;
	                getUserList(params);
	            });
	        }
	        
	        if(result.isLast){
	            $(".last,.next").css("background","#eee");
	        }else{
	            $(".last").click(function(){
	                params["pageNum"] = result.total;
	                getUserList(params);
	            });
	            $(".next").click(function(){
	                params["pageNum"]++;
	                getUserList(params);
	            });
	        }
	        $(".other").click(function(){
	            params["pageNum"] = $(this).text();
	            getUserList(params);
	        });
	        
	        $(".providerTable>tbody>tr:gt(0)").remove();
	        $.each(result.rows, function(){
	        if(this.gender==1)
                            var sex="男";
                            else
                            var sex="女";
            /*
            根据出生日期计算年龄
            */
            var date = new Date();
            var startDate = new Date(this.birthday);
            var newDate = date.getTime() - startDate.getTime();
            // 向下取整  例如 10岁 20天 会计算成 10岁
            // 如果要向上取整 计算成11岁，把floor替换成 ceil
            var age = Math.ceil(newDate / 1000 / 60 / 60 / 24 /365);

	        var $tr = $("<tr id="+this.id+">"+
	               "<td>"+this.id+"</td>"+
	               "<td>"+this.usercode+"</td>"+
	               "<td>"+sex+"</td>"+
	               "<td>"+age+"</td>"+
	               "<td>"+this.phone+"</td>"+
	               "<td>"+this.rolename+"</td>"+
	               " <td><span><a class=\"viewUser\" onclick='viewUser(\""+this.usercode+"\")' href=\"javascript:;\" proid=><img src=\"static/images/read.png\" alt=\"查看\" title=\"查看\" /></a></span> <span><aclass=\"modifyProvider\" onclick='modifyUser(\""+this.usercode+"\")' href=\"javascript:;\" proid=proname=><img src=\"static/images/xiugai.png\" alt=\"修改\" title=\"修改\" /></a></span> <span><aclass=\"deleteProvider\" href=\"javascript:;\" proid=><img src=\"static/images/schu.png\" alt=\"删除\" title=\"删除\"   onclick=deleteUser("+this.id+") /></a></span></td>"+
	               "</tr>");
	        $(".providerTable>tbody").append($tr);
	        });
	    });
}

//查询用户
function getUserPage(params){
	$.post("jsp/selectUser",params,function(result){

		 params["pageNum"] = result.pageNum;
	     params["pageSize"] = result.pageSize;
	     params["usercode"] = result.usercode;
	     params["userrole"] = result.userrole;
         params["username"] = result.username;

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
                    getUserPage(params);
                });
                $(".prev").click(function(){
                    params["pageNum"]--;
                    getUserPage(params);
                });
            }

            if(result.isLast){
                $(".last,.next").css("background","#eee");
            }else{
                $(".last").click(function(){
                    params["pageNum"] = result.total;
                    getUserPage(params);
                });
                $(".next").click(function(){
                    params["pageNum"]++;
                    getUserPage(params);
                });
            }
            $(".other").click(function(){
                params["pageNum"] = $(this).text();
                getUserPage(params);
            });

            $(".providerTable>tbody>tr:gt(0)").remove();
	        $.each(result.rows, function(){
	        if(this.gender==1)
                                        var sex="男";
                                        else
                                        var sex="女";
            /*
            根据出生日期计算年龄
            */
            var date = new Date();
            var startDate = new Date(this.birthday);
            var newDate = date.getTime() - startDate.getTime();
            // 向下取整  例如 10岁 20天 会计算成 10岁
            // 如果要向上取整 计算成11岁，把floor替换成 ceil
            var age = Math.ceil(newDate / 1000 / 60 / 60 / 24 /365);

	            var $tr = $("<tr id="+this.id+">"+
	                          "<td>"+this.id+"</td>"+
	                          "<td>"+this.usercode+"</td>"+
	                          "<td>"+sex+"</td>"+
	                          "<td>"+age+"</td>"+
	                          "<td>"+this.phone+"</td>"+
	                          "<td>"+this.rolename+"</td>"+
	                          " <td><span><a class='viewUser' onclick='viewUser(\""+this.usercode+"\")' href=\"javascript:;\" proid=><img src=\"static/images/read.png\" alt=\"查看\" title=\"查看\" /></a></span> <span><aclass=\"modifyProvider\" onclick='modifyUser(\""+this.usercode+"\")' href=\"javascript:;\" proid=proname=><img src=\"static/images/xiugai.png\" alt=\"修改\" title=\"修改\" /></a></span> <span><aclass=\"deleteProvider\" href=\"javascript:;\" proid=><img src=\"static/images/schu.png\" alt=\"删除\" title=\"删除\"   onclick=deleteUser("+this.id+") /></a></span></td>"+
	                        "</tr>");
	            $(".providerTable>tbody").append($tr);
	        });
	    });
}

//获取用户角色下拉列表
function getUserRole(){
	$.post("jsp/getUserrole",function(result){
	   //$("select[name='queryUserRole']").remove();
	    $.each(result.rows, function(){
	        var $tr = $("<option value="+this.id+">"+this.rolename+"</option>");
	        $("select[name='queryUserRole']").append($tr);
	        });
	    });
}

//查看用户详情
function viewUser(usercode){
     window.location.href="/jsp/openuserview?usercode="+usercode;
}

//编辑用户按钮
function modifyUser(usercode){
	 window.location.href="/jsp/modifyUser?usercode="+usercode;
}

$(function(){
    addstatus=$("#addstatus").val();
    updatestatus=$("#updatestatus").val();
    //获取全部用户角色
    getUserRole();
	var  params={"pageNum":1,"pageSize":15};
	getUserList(params);
	//查询按钮
	$("#searchbutton").click(function(){
		 var usercode = $("input[name=queryname]").val();
	     var userrole = $("select[name=queryUserRole]").val();
	     //alert(userrole);
	     params["usercode"] = usercode;
	     params["userrole"] = userrole;
	     params["pageNum"] = 1;
	     params["pageSize"] = 5;
	     getUserPage(params);
		$("#searchbutton").blur();
	})

    if(addstatus=="true") alert("添加用户成功！");
	if(addstatus=="false") alert("添加用户失败！");
	if(updatestatus=="true") alert("用户修改成功！");
    if(updatestatus=="false") alert("用户修改失败！");

    $("#addstatus").val("");
    $("#updatestatus").val("");

	//通过jquery的class选择器（数组）
	//对每个class为viewUser的元素进行动作绑定（click）
//	$(".viewUser").on("click",function(){
//	    alert("111");
//		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
//		var obj = $(this);
//		window.location.href="/jsp/user.view?userid="+ obj.attr("userid");
//	});

//	$('#no').click(function () {
//		cancleBtn();
//	});
//
//	$('#yes').click(function () {
//		deleteUser(userObj);
//	});

//	$(".deleteUser").on("click",function(){
//		userObj = $(this);
//		changeDLGContent("你确定要删除用户【"+userObj.attr("username")+"】吗？");
//		openYesOrNoDLG();
//	});

});