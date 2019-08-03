var backBtn = null;
viewusercode=$("#viewusercode").val();

$(function(){
    var params={"usercode":viewusercode}
    var sex=null;
         $.post("jsp/userview",params,function(result){
                $("#id").text(result.id);
                $("#usercode").text(result.usercode);
                if(result.gender==1)
                sex="男";
                else
                sex="女";
                $("#gender").text(sex);
                $("#birthday").text(result.birthday);
                $("#phone").text(result.phone);
                $("#address").text(result.address);
                $("#userrole").text(result.rolename);

//    	        var $tr = $("<option value="+this.id+">"+this.rolename+"</option>");
//    	        $("select[name='queryUserRole']").append($tr);
    	    });

	backBtn = $("#back");
	backBtn.on("click",function(){
		//alert("view : "+referer);
		if(referer != undefined 
			&& null != referer 
			&& "" != referer
			&& "null" != referer
			&& referer.length > 4){
		 window.location.href = referer;
		}else{
			history.back(-1);
		}
	});
});