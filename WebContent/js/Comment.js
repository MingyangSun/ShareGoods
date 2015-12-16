function changeUser(userId) {
	$("input[name=user]").value(userId);
}
function submitComment() {
	var post = {
			user : $("input[name=user]").val(),
			comment : $("input[name=comment]").val(),
			photo : $("input[name=photo]").val(),
			owner: $("input[name=owner]").val()
	};
	$.post("Comment",post, function(data){
		console.log(data);
	});
}