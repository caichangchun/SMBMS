var id = null;
var usercode = null;
var userpassword = null;
var ruserpassword = null;
var phone = null;
var birthday = null;
var userrole = null;
var addBtn = null;
var backBtn = null;


$(function(){
	id = $("#id");
	usercode = $("#usercode");
	userpassword = $("#userpassword");
	ruserpassword = $("#ruserpassword");
	phone = $("#phone");
	birthday = $("#birthday");
	userrole = $("#userrole");
	addBtn = $("#add");
	backBtn = $("#back");
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
	id.next().html("*");
	usercode.next().html("*");
	userpassword.next().html("*");
	ruserpassword.next().html("*");
	phone.next().html("*");
	birthday.next().html("*");
	userrole.next().html("*");

	$.ajax({
		type:"GET",//请求类型
		url:"/jsp/getUserrole",//请求的url
		data:{},//请求参数
		dataType:"json",//ajax接口（请求url）返回的数据类型
		success:function(data){//data：返回数据（json对象）
			if(data != null){
				userrole.html("");
				var options = "<option value=\"0\">请选择</option>";
				$.each(data.rows, function(){
				options += "<option value=\""+this.id+"\">"+this.rolename+"</option>";
				});
				userrole.html(options);
			}
		},
		error:function(data){//当访问时候，404，500 等非200的错误状态码
			validateTip(userrole.next(),{"color":"red"},imgNo+" 获取用户角色列表error",false);
		}
	});

	/*
	 * 验证
	 * 失焦\获焦
	 * jquery的方法传递
	 */
	id.bind("blur",function(){
		//ajax后台验证--userCode是否已存在
		//user.do?method=ucexist&userCode=**
		$.ajax({
			type:"GET",//请求类型
			url:"/jsp/userexist",//请求的url
			data:{"id":id.val()},//请求参数
			//dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				if(data == "exist"){//账号已存在，错误提示
					validateTip(id.next(),{"color":"red"},imgNo+ " 该编码账号已存在",false);
				}else{//账号可用，正确提示
					validateTip(id.next(),{"color":"green"},imgYes+" 该编号可以使用",true);
				}
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				validateTip(id.next(),{"color":"red"},imgNo+" 您访问的页面不存在",false);
			}
		});


	}).bind("focus",function(){
		//显示友情提示
		validateTip(id.next(),{"color":"#666666"},"* 用户名长度必须是大于1小于10的字符",false);
	}).focus();


	usercode.bind("blur",function(){
    		//ajax后台验证--userCode是否已存在
    		//user.do?method=ucexist&userCode=**
    		$.ajax({
    			type:"GET",//请求类型
    			url:"/jsp/userview",//请求的url
    			data:{"usercode":usercode.val()},//请求参数
    			//dataType:"json",//ajax接口（请求url）返回的数据类型
    			success:function(data){//data：返回数据（json对象）
    				if(data.usercode==usercode.val()){//账号已存在，错误提示
    					validateTip(usercode.next(),{"color":"red"},imgNo+ " 该用户名已存在",false);
    				}else{//账号可用，正确提示
    					validateTip(usercode.next(),{"color":"green"},imgYes+" 该用户名可以使用",true);
    				}
    			},
    			error:function(data){//当访问时候，404，500 等非200的错误状态码
    				validateTip(usercode.next(),{"color":"red"},imgNo+" 您访问的页面不存在",false);
    			}
    		});


    	}).bind("focus",function(){
    		//显示友情提示
    		validateTip(usercode.next(),{"color":"#666666"},"* 用户名长度必须是大于1小于10的字符",false);
    	}).focus();


	userpassword.bind("focus",function(){
		validateTip(userpassword.next(),{"color":"#666666"},"* 密码长度必须是大于6小于20",false);
	}).bind("blur",function(){
		if(userpassword.val() != null && userpassword.val().length > 6
				&& userpassword.val().length < 20 ){
			validateTip(userpassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(userpassword.next(),{"color":"red"},imgNo + " 密码输入不符合规范，请重新输入",false);
		}
	});

	ruserpassword.bind("focus",function(){
		validateTip(ruserpassword.next(),{"color":"#666666"},"* 请输入与上面一只的密码",false);
	}).bind("blur",function(){
		if(ruserpassword.val() != null && ruserpassword.val().length > 6
				&& ruserpassword.val().length < 20 && userpassword.val() == ruserpassword.val()){
			validateTip(ruserpassword.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(ruserpassword.next(),{"color":"red"},imgNo + " 两次密码输入不一致，请重新输入",false);
		}
	});


	birthday.bind("focus",function(){
		validateTip(birthday.next(),{"color":"#666666"},"* 点击输入框，选择日期",false);
	}).bind("blur",function(){
		if(birthday.val() != null && birthday.val() != ""){
			validateTip(birthday.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(birthday.next(),{"color":"red"},imgNo + " 选择的日期不正确,请重新输入",false);
		}
	});

	phone.bind("focus",function(){
		validateTip(phone.next(),{"color":"#666666"},"* 请输入手机号",false);
	}).bind("blur",function(){
		var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
		if(phone.val().match(patrn)){
			validateTip(phone.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(phone.next(),{"color":"red"},imgNo + " 您输入的手机号格式不正确",false);
		}
	});

	userrole.bind("focus",function(){
		validateTip(userrole.next(),{"color":"#666666"},"* 请选择用户角色",false);
	}).bind("blur",function(){
		if(userrole.val() != null && userrole.val() > 0){
			validateTip(userrole.next(),{"color":"green"},imgYes,true);
		}else{
			validateTip(userrole.next(),{"color":"red"},imgNo + " 请重新选择用户角色",false);
		}
	});

	addBtn.bind("click",function(){
		if(id.attr("validateStatus") != "true"){
			userCode.blur();
		}else
		 if(usercode.attr("validateStatus") != "true"){
			userName.blur();
		}else if(userpassword.attr("validateStatus") != "true"){
			userPassword.blur();
		}else if(ruserpassword.attr("validateStatus") != "true"){
			ruserPassword.blur();
		}else if(birthday.attr("validateStatus") != "true"){
			birthday.blur();
		}else if(phone.attr("validateStatus") != "true"){
			phone.blur();
		}else if(userrole.attr("validateStatus") != "true"){
			userrole.blur();
		}else{
			if(confirm("是否创建新用户？")){
				$("#userForm").submit();

				if(status=="true"){
                                    alert("用户创建成功！");
                                    };
                                 if(status=="false"){
                                     alert("用户创建失败！");
                                     };
			}
		}
	});

	backBtn.on("click",function(){
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