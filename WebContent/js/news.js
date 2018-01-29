$(document).ready(function(){
	$(".ss-del-btn").click(function(){
		var a_id = $(this).attr("data-id");
		if(confirm("确定删除该信息？")){
		$.post("DoDeleteAnnouncementServlet", "a_ids=" + a_id, function(data, status){
			if(status == "success"){
				alert("删除成功!");
				location.reload();
			}
		});
		}
	});
});