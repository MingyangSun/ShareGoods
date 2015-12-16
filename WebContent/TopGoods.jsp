<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.brothers.sharegoods.model.PhotoBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/test.css" rel="stylesheet">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="./js/TopGoods.js"></script>
<script src="./js/Comment.js"></script>
<title>Share Your Goods</title>
</head>
<body>
<%@ include file="./navbar.jsp" %>
<% HttpSession session2 = request.getSession(false); %>
<% String isLogin = "no"; %>
<% if(session2.getAttribute("isLogin") != null) { %>
<% 	isLogin = session2.getAttribute("isLogin").toString();%>
<% } %>
<% if(!isLogin.equals("yes")) { %>
<%	response.sendRedirect("./Index.jsp");%>
<% } %>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-4 fixed">
			<div class="list-group">
				<a href="./TopGoods?top=10" class="list-group-item">Top 10</a>
				<a href="./TopGoods?top=20" class="list-group-item">Top 20</a>
			</div>
		</div>
		<div class="col-sm-8 scrollit">
			<% if(request.getAttribute("top") != null) { 
				List<PhotoBean> list = (List<PhotoBean>) request.getAttribute("top");
				for(int i = 0; i < list.size(); i++) {
					if(i == 0) { %>
					<div class="row text-center">
					<div class="col-md-6 portfolio-item">
					<div class="thumbnail">
					<a onclick="showPhoto(<%= list.get(i).getPhotoId() %>)">
						<img class="img-responsive" src="./pictures/<%= list.get(i).getImageUri() %>.jpg">
					</a>
					<div class="caption">
					<p><h3><%= list.get(i).getDescription() %></h3></p>
					<a>Like: <%= list.get(i).getLikePhoto()%></a>
					</div>
					</div>
					</div>
					<% } else if(i%2 == 0) { %>
					</div>
					<div class="row text-center">
					<div class="col-md-6 portfolio-item">
					<div class="thumbnail">
					<a onclick="showPhoto(<%= list.get(i).getPhotoId() %>)">
						<img class="img-responsive" src="./pictures/<%= list.get(i).getImageUri() %>.jpg">
					</a>
					<div class="caption">
					<p><h3><%= list.get(i).getDescription() %></h3></p>
					<a>Like: <%= list.get(i).getLikePhoto()%></a>
					</div>
					</div>
					</div>
					<% } else {%>
					<div class="col-md-6 portfolio-item">
					<div class="thumbnail">
					<a onclick="showPhoto(<%= list.get(i).getPhotoId() %>)">
						<img class="img-responsive" src="./pictures/<%= list.get(i).getImageUri() %>.jpg">
					</a>
					<div class="caption">
					<p><h3><%= list.get(i).getDescription() %></h3></p>
					<a>Like: <%= list.get(i).getLikePhoto()%></a>
					</div>
					</div>
					</div>
			<% }}%>
			</div>
			<% } %>
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