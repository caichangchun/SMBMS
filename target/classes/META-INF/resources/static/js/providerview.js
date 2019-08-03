var backBtn = null;
var procode=$("#viewprocode").val();
var proname=$("#viewproname").val();
var procontact=$("#viewprocontact").val();
var prophone=$("#viewprophone").val();
var profax=$("#viewprofax").val();
var prodesc=$("#viewprodesc").val();

$(function(){
    //alert(procode);
    $("#procode").text(procode);
    $("#proname").text(proname);
    $("#procontact").text(procontact);
    $("#prophone").text(prophone);
    $("#profax").text(profax);
    $("#prodesc").text(prodesc);

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