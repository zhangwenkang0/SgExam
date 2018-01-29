$(document).ready(function() {
	$(".options_add").click(function() {
		var options = $.trim($(".options").val());
		var str = $(".preview-textarea").val() + options + "@";
        $(".preview-textarea").val(str);
	});
});