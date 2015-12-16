function UploadPicture() {
	$("#myModal").find("#modalBody").load("./UploadImage.html").end().modal("toggle");
}
function likePhoto(obj,photoId) {
	$.get("./LikePhoto?photo=" + photoId, function(data) {
		console.log("From servlet : " + data);
		$(obj).html("Like:" + data);
	});
}