function showPhoto(photoId) {
	console.log("id:"+photoId);
	$("#myModal").find("#modalBody").load("./DetailPhoto.jsp?id="+photoId).end().modal("toggle");
}