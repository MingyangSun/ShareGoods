<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.brothers.sharegoods.model.FansBean" %>
<%@ page import="com.brothers.sharegoods.function.ModelApplier" %>
<%@ page import="com.brothers.sharegoods.model.PhotoBean" %>
<%@ page import="com.brothers.sharegoods.model.PhotoCommentBean" %>
<% String photoId = request.getParameter("id");
	if(photoId != null) {
		PhotoBean photo = ModelApplier.getPhotoByPhotoId(Integer.parseInt(photoId));
%>
<div class="row text-center">
	<div class= "col-md-12 portfolio-item">
		<a><img src="./pictures/<%= photo.getImageUri() %>.jpg"></a><br>
		<div class="caption">
		<a>Like: <%= photo.getLikePhoto()%></a>
		<p><h3><%= photo.getDescription() %></h3></p>
		<% List<PhotoCommentBean> l = ModelApplier.getCommentByPhotoId(Integer.parseInt(photoId));
			if(l.size() > 0) {
		%>
		<p><h1>Comments:</h1></p>
		<% } %>
		<% for(PhotoCommentBean pc : l) {
		%>
		<div class="media">
		<div class="media-body">
		<p><%= pc.getComments() %><a onclick="changeUser(<%= pc.getUser1Id() %>)"> Reply</a><p>
		</div>
		</div>
		<% } %>
		</div>
		<div class="well">
           <h4>Leave a Comment:</h4>
           <form role="form">
               <div class="form-group" method="post" action="Comment">
                   <textarea name="comment" class="form-control" rows="3"></textarea>
                   <input type="hidden" name="photo" value="<%= photoId %>" />
                   <input type="hidden" name="user" value="" />
                   <input type="hidden" name="owner" value="<%= photo.getUserId() %>" />
               </div>
               <button type="button" class="btn btn-primary" onclick="submitComment()" >Submit</button>
           </form>
        </div>
	</div>
</div>
<% }%>
<script>
function changeUser(userId) {
	$("input[name=user]").val(userId);
}
function submitComment() {
	var post = {
			user : $("input[name=user]").val(),
			comment : $("textarea[name=comment]").val(),
			photo : $("input[name=photo]").val(),
			owner: $("input[name=owner]").val()
	};
	$.post("Comment",post, function(data){
		console.log(data);
		$("#myModal").find("#modalBody").load("./DetailPhoto.jsp?id="+post.photo);
	});
}
</script>