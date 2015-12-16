function follow(userId) {
	var post = {
		user: userId	
	};
	$.post("./Follow", post, function() {
		window.location.reload();		
	});
}
function likePhoto(obj,photoId) {
	$.get("./LikePhoto?photo=" + photoId, function(data) {
		console.log("From servlet : " + data);
		$(obj).html("Like:" + data);
	});
}
function showPhoto(photoId) {
	console.log("id:"+photoId);
	$("#myModal").find("#modalBody").load("./DetailPhoto.jsp?id="+photoId).end().modal("toggle");
}