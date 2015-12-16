<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<title>Share Your Goods</title>
</head>
<body>
<%@ include file="./navbar.jsp" %>
<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<form role="form" method="post" action="Login">
				<div class="form-group">
					<label><h4>Email :</h4></label>
					<input type="email" class="form-control" name="userEmail"></input>
				</div>
				<div class="form-group">
					<label><h4>Password :</h4></label>
					<input type="password" class="form-control" name="pwd"></input>
				</div>
				<% if(request.getAttribute("message") != null) { %>
					<div class="alert alert-danger">
					  <%= request.getAttribute("message") %>
					</div>
				<% } %>
				<button type="submit" class="btn btn-success">Submit</button>
				<button type="button" class="btn btn-success">Sign Up</button>
			</form>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>
</body>
</html>