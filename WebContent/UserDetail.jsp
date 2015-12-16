<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.brothers.sharegoods.function.ModelApplier" %>
<%@ page import="com.brothers.sharegoods.model.UserBean" %>
<%@ page import="com.brothers.sharegoods.model.RelationBean" %>
<%@ page import="com.brothers.sharegoods.model.PhotoBean" %>
<% int userId = Integer.parseInt(request.getAttribute("user").toString()); %>
<% UserBean user = ModelApplier.getUserById(userId); %>
<div class="col-md-4">
<a><img src="./pictures/1.jpg"></a>
</div>
<div class="col-md-8">
<p><h4><%= user.getEmail() %></h4></p><br>
<% int currentId = Integer.parseInt(request.getSession(false).getAttribute("id").toString());%>
<% RelationBean rb =  ModelApplier.getRelationByAllUser(currentId, userId);%>
<% if(rb != null && rb.getUser1Id() != 0) { %>
<button type="button" class="btn btn-success" onclick="follow(<%= userId %>)">Following</button><br>
<% } else { %>
<button type="button" class="btn btn-success" onclick="follow(<%= userId%>)">Follow</button><br>
<% } %>
<br><p><%= user.getUserName() %></p><br>
<p><%= user.getCity() %></p><p><%= user.getCountry() %></p><br>
</div>
</div>
<hr>
<div class="row text-center">
<% List<PhotoBean> list = ModelApplier.getPhotoByUserId(userId);
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