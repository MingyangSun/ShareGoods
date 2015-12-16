<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.brothers.sharegoods.model.FansBean" %>
<%@ page import="com.brothers.sharegoods.function.ModelApplier" %>
<%@ page import="com.brothers.sharegoods.model.UserBean" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/test.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="./js/Browse.js"></script>
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
				<a href="./Browse.jsp?goods=0" class="list-group-item">Search Goods</a>
				<a href="./Browse.jsp?users=0" class="list-group-item">Search Users</a>
				<a href="./Browse?following=0" class="list-group-item"><span class="badge"><%= fb.getFollowing() %></span>Following</a>
				<a href="./Browse?follower=0" class="list-group-item"><span class="badge"><%= fb.getFollower()%></span>Follower</a>
			<% } %>
			</div>
		</div>
		<div class="col-sm-8 scrollit">
		<div class="row">
		<% if(request.getAttribute("user") == null && request.getAttribute("follower") == null && request.getAttribute("following") == null)  {%>
			
			<% if(request.getParameter("users") != null) { %>
			<form class="navbar-form navbar-left" role="search" action="Search" method="get">
			  <div class="form-group">
			  <% if(request.getAttribute("search") != null) { %>
			    <input type="text" name="search" class="form-control" placeholder="Search User" value="<%= request.getAttribute("search") %>">			    
			  <% } else { %>
			    <input type="text" name="search" class="form-control" placeholder="Search User">			  
			  <% } %>
			  <input type="hidden" name="category" value="users" />
			  </div>
			  <button type="submit" class="btn btn-default">Submit</button>
			</form>
			<% } else { %>
			<form class="navbar-form navbar-left" role="search" action="Search" method="get">
			  <div class="form-group">
			  <% if(request.getAttribute("search") != null) { %>
			    <input type="text" name="search" class="form-control" placeholder="Search Goods" value="<%= request.getAttribute("search") %>">			    
			  <% } else { %>
			    <input type="text" name="search" class="form-control" placeholder="Search Goods">			  
			  <% } %>
			  <input type="hidden" name="category" value="goods" />
			  </div>
			  <button type="submit" class="btn btn-default">Submit</button>
			</form>
			<% } %>
			<% if(request.getAttribute("search") != null && request.getAttribute("SearchResultPhoto") != null) { %>
			</div>
			<div class="row">
			<% List<PhotoBean> list = (List<PhotoBean>) request.getAttribute("SearchResultPhoto"); %>
			<% for(int i = 0; i< list.size(); i++) {
				if(i == 0) { %>
					<div class="col-md-4 portfolio-item">
					<div class="thumbnail">
						<a onclick="showPhoto(<%= list.get(i).getPhotoId() %>)">
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
					<a onclick="showPhoto(<%= list.get(i).getPhotoId() %>)">
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
					<a onclick="showPhoto(<%= list.get(i).getPhotoId() %>)">
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
			<% } %>
			<% if(request.getAttribute("search") != null && request.getAttribute("SearchResultUser") != null) { %>
			</div>
			<div class="row">
				<% List<UserBean> l = (List<UserBean>)request.getAttribute("SearchResultUser");
				for(int i = 0; i < l.size(); i++) {
					if(i == 0) { %>
						<div class="col-md-4 portfolio-item">
						<a href="./Browse?user=<%= ((UserBean)l.get(i)).getUserId() %>"><h3><%= ((UserBean)l.get(i)).getEmail() %></h3></a>
						<p><%= ((UserBean)l.get(i)).getUserName() %></p>
						<p><%= ((UserBean)l.get(i)).getSex() %></p>
						<p><%= ((UserBean)l.get(i)).getCity() %></p>
						</div>	
					<% } else if(i%3 == 0) {%>	
					</div>
					<div class="row">
						<div class="col-md-4 portfolio-item">
						<a href="./Browse?user=<%= ((UserBean)l.get(i)).getUserId() %>"><h3><%= ((UserBean)l.get(i)).getEmail() %></h3></a>
						<p><%= ((UserBean)l.get(i)).getUserName() %></p>
						<p><%= ((UserBean)l.get(i)).getSex() %></p>
						<p><%= ((UserBean)l.get(i)).getCity() %></p>
						</div>	
					<% } else { %>
					<div class="col-md-4 portfolio-item">
						<a href="./Browse?user=<%= ((UserBean)l.get(i)).getUserId() %>"><h3><%= ((UserBean)l.get(i)).getEmail() %></h3></a>
						<p><%= ((UserBean)l.get(i)).getUserName() %></p>
						<p><%= ((UserBean)l.get(i)).getSex() %></p>
						<p><%= ((UserBean)l.get(i)).getCity() %></p>
						</div>
					<% } %>
				<% } %>
			<% } %>
		<% } %>
		<% if(request.getAttribute("user") != null) { %>
			<%@ include file="./UserDetail.jsp" %>
		<% } %>
		<% if(request.getAttribute("following") != null) { 
			List<UserBean> l = (List<UserBean>)request.getAttribute("following");
			for(int i = 0; i < l.size(); i++) {
				if(i == 0) { %>
				<div class="row">
					<div class="col-md-4 portfolio-item">
					<a href="./Browse?user=<%= ((UserBean)l.get(i)).getUserId() %>"><h3><%= ((UserBean)l.get(i)).getEmail() %></h3></a>
					<p><%= ((UserBean)l.get(i)).getUserName() %></p>
					<p><%= ((UserBean)l.get(i)).getSex() %></p>
					<p><%= ((UserBean)l.get(i)).getCity() %></p>
					</div>	
				<% } else if(i%3 == 0) {%>	
				</div>
				<div class="row">
					<div class="col-md-4 portfolio-item">
					<a href="./Browse?user=<%= ((UserBean)l.get(i)).getUserId() %>"><h3><%= ((UserBean)l.get(i)).getEmail() %></h3></a>
					<p><%= ((UserBean)l.get(i)).getUserName() %></p>
					<p><%= ((UserBean)l.get(i)).getSex() %></p>
					<p><%= ((UserBean)l.get(i)).getCity() %></p>
					</div>	
				<% } else { %>
				<div class="col-md-4 portfolio-item">
					<a href="./Browse?user=<%= ((UserBean)l.get(i)).getUserId() %>"><h3><%= ((UserBean)l.get(i)).getEmail() %></h3></a>
					<p><%= ((UserBean)l.get(i)).getUserName() %></p>
					<p><%= ((UserBean)l.get(i)).getSex() %></p>
					<p><%= ((UserBean)l.get(i)).getCity() %></p>
					</div>
				<% } %>
		<% } %>
		</div>
		<% }%>
		<% if(request.getAttribute("follower") != null) { 
			List<UserBean> l = (List<UserBean>)request.getAttribute("follower");
			for(int i = 0; i < l.size(); i++) {
				if(i == 0) { %>
				<div class="row">
					<div class="col-md-4 portfolio-item">
					<a href="./Browse?user=<%= ((UserBean)l.get(i)).getUserId() %>"><h3><%= ((UserBean)l.get(i)).getEmail() %></h3></a>
					<p><%= ((UserBean)l.get(i)).getUserName() %></p>
					<p><%= ((UserBean)l.get(i)).getSex() %></p>
					<p><%= ((UserBean)l.get(i)).getCity() %></p>
					</div>	
				<% } else if(i%3 == 0) {%>	
				</div>
				<div class="row">
					<div class="col-md-4 portfolio-item">
					<a href="./Browse?user=<%= ((UserBean)l.get(i)).getUserId() %>"><h3><%= ((UserBean)l.get(i)).getEmail() %></h3></a>
					<p><%= ((UserBean)l.get(i)).getUserName() %></p>
					<p><%= ((UserBean)l.get(i)).getSex() %></p>
					<p><%= ((UserBean)l.get(i)).getCity() %></p>
					</div>	
				<% } else { %>
				<div class="col-md-4 portfolio-item">
					<a href="./Browse?user=<%= ((UserBean)l.get(i)).getUserId() %>"><h3><%= ((UserBean)l.get(i)).getEmail() %></h3></a>
					<p><%= ((UserBean)l.get(i)).getUserName() %></p>
					<p><%= ((UserBean)l.get(i)).getSex() %></p>
					<p><%= ((UserBean)l.get(i)).getCity() %></p>
					</div>
				<% } %>
		<% } %>
		</div>
		<% }%>
		</div>
	</div>
</div>
 <!-- Modal -->
 <div class="modal fade" id="myModal" role="dialog">
   <div class="modal-dialog"> 
     <!-- Modal content-->
     <div class="modal-content">
       <div class="modal-body" id="modalBody">
       </div>
       <div class="modal-footer">
         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       </div>
     </div>
     
   </div>
</div>
</body>
</html>