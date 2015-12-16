<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.brothers.sharegoods.model.FansBean" %>
<%@ page import="com.brothers.sharegoods.function.ModelApplier" %>
<%@ page import="com.brothers.sharegoods.model.PhotoBean" %>
<%@ page import="com.brothers.sharegoods.model.PhotoCommentBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/test.css" rel="stylesheet">
<link href="./css/bootstrap-responsive.css" rel="stylesheet">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="./js/HomePage.js"></script>

<title>Share Your Goods</title>

</head>
<body>
<%@ include file="./navbar.jsp" %>
<% HttpSession session2 = request.getSession(false); %>
<% String isLogin = "no";%>
<% if(session2.getAttribute("isLogin") != null) {%>
<% 	isLogin = session2.getAttribute("isLogin").toString();%>
<% } else {
	response.sendRedirect("./Index.jsp");
	return ;
}%>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-4 fixed">
			<div class="list-group">
			<% if(isLogin.equals("yes")) {%>
			<% FansBean fb = ModelApplier.getFansByUserId(Integer.parseInt(session2.getAttribute("id").toString()));%>
			<% %>
				<a href="./HomePage" class="list-group-item">New Goods</a>
				<a href="./HomePage?home=0" class="list-group-item">My Goods</a>
				<a href="./Browse?following=0" class="list-group-item"><span class="badge"><%= fb.getFollowing() %></span>Following</a>
				<a href="./Browse?follower=0" class="list-group-item"><span class="badge"><%= fb.getFollower()%></span>Follower</a>
				<a onclick="UploadPicture()" class="list-group-item">Publish Your Goods</a>
				<a href="./HomePage?liked=0" class="list-group-item">Goods You Liked</a>
			<% } %>
			</div>
		</div>
		<div class="col-sm-8 scrollit">
			<div class="row text-center">
		<% if(request.getAttribute("photoes") != null) {
			List<PhotoBean> list = (List<PhotoBean>)request.getAttribute("photoes");
			for(int i = 0; i< list.size(); i++) {
				if(i == 0) { %>
					<div class="col-md-4 portfolio-item">
					<div class="thumbnail">
						<a href="./HomePage?photo=<%= list.get(i).getPhotoId() %>">
							<img class="img-responsive" src="./pictures/<%= list.get(i).getImageUri() %>.jpg">
						</a>
						<div class="caption">
						<p><h3><%= list.get(i).getDescription() %></h3></p>
						<a onclick="likePhoto(this,<%= list.get(i).getPhotoId() %>)" >Like: <%= list.get(i).getLikePhoto()%></a>
						</div>
					</div>
					</div>
				<% } else if( i%3 == 0) {%>
				</div>
				<div class="row text-center">
				<div class="col-md-4 portfolio-item">
				<div class="thumbnail">
					<a href="./HomePage?photo=<%= list.get(i).getPhotoId() %>">
						<img class="img-responsive" src="./pictures/<%= list.get(i).getImageUri() %>.jpg">
					</a>
					<div class="caption">
					<p><h3><%= list.get(i).getDescription() %></h3></p>
					<a onclick="likePhoto(this,<%= list.get(i).getPhotoId() %>)" >Like: <%= list.get(i).getLikePhoto()%></a>
					</div>
				</div>
				</div>
				<% } else {%>
				<div class="col-md-4 portfolio-item">
				<div class="thumbnail">
					<a href="./HomePage?photo=<%= list.get(i).getPhotoId() %>">
						<img class="img-responsive" src="./pictures/<%= list.get(i).getImageUri() %>.jpg">
					</a>
					<div class="caption">
					<p><h3><%= list.get(i).getDescription() %></h3></p>
					<a onclick="likePhoto(this,<%= list.get(i).getPhotoId() %>)">Like: <%= list.get(i).getLikePhoto()%></a>
					</div>
				</div>
				</div>
			<% } %>
			<% }%>
			</div>
		<% } %>
		<% if(request.getAttribute("photo") != null) {
			PhotoBean photo = (PhotoBean)request.getAttribute("photo");
		%>
		<div class ="col-md-2"></div>
		<div class= "col-md-8 portfolio-item">
		<div class="thumbnail">
			<a><img src="./pictures/<%= photo.getImageUri() %>.jpg"></a><br>
			<div class="caption">
			<a onclick="likePhoto(this,<%= photo.getPhotoId() %>)">Like: <%= photo.getLikePhoto()%></a>
			<p><h3><%= photo.getDescription() %></h3></p>
			<% List<PhotoCommentBean> l = (List<PhotoCommentBean>)request.getAttribute("comment");
				if(l.size() > 0) {
			%>
			<p><h1>Comments:</h1></p>
			<% } %>
			<% for(PhotoCommentBean pc : l) {
			%>
			<p><h3><%= pc.getComments() %></h3><p>
			<% } %>
			</div>
		</div>
		</div>
		<div class ="col-md-2"></div>
		<% } %>
		<% if(request.getAttribute("myPhoto") != null) {
			List<PhotoBean> photoList = (List<PhotoBean>)request.getAttribute("myPhoto");
			for(PhotoBean photo : photoList) {
		%>
		<div class="row text-center">
		<div class = "col-md-2"></div>
		<div class = "col-md-8 portfolio-item">
			<div class = "thumbnail">
				<a href="./HomePage?photo=<%= photo.getPhotoId() %>">
				<img class="img-responsive" src="./pictures/<%= photo.getImageUri() %>.jpg">
				</a>
				<div class ="caption">
					<p><h3><%= photo.getDescription() %></h3></p>
					<a><img src="./pic/like.png" style="height:40px;width:40px;"></a><a><h3><%= photo.getLikePhoto()%></h3></a>
				</div>
			</div>
		</div>
		<div class ="col-md-2"></div>
		</div>
		<% }} %>
		<% if(request.getAttribute("liked") != null) {
			List<PhotoBean> photoList = (List<PhotoBean>)request.getAttribute("liked");
			for(PhotoBean photo : photoList) {
		%>
		<div class="row text-center">
		<div class = "col-md-2"></div>
		<div class = "col-md-8 portfolio-item">
			<div class = "thumbnail">
				<a href="./HomePage?photo=<%= photo.getPhotoId() %>">
				<img class="img-responsive" src="./pictures/<%= photo.getImageUri() %>.jpg">
				</a>
				<div class ="caption">
					<p><h3><%= photo.getDescription() %></h3></p>
					<a><img src="./pic/like.png" style="height:40px;width:40px;"></a><a><h3><%= photo.getLikePhoto()%></h3></a>
				</div>
			</div>
		</div>
		<div class ="col-md-2"></div>
		</div>
		<% }} %>
	</div>
</div>
 <!-- Modal -->
 <div class="modal fade" id="myModal" role="dialog">
   <div class="modal-dialog"> 
     <!-- Modal content-->
     <div class="modal-content">
       <div class="modal-body" id="modalBody">
       </div>
     </div>
   </div>
</div>
</body>
</html>