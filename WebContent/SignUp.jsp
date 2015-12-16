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
		<form role="form" action="SignUp" method="post">
			<div class="form-group">
				<label><h4>Email :</h4></label>
				<input type="email" class="form-control" name="userEmail">
			</div>
			<div class="form-group">
				<label><h4>Password :</h4></label>
				<input type="password" class="form-control" name="pwd">
			</div>
			<div class="form-group">
				<label><h4>User Name :</h4></label>
				<input type="text" class="form-control" name="userName">
			</div>
			<div class="form-group">
				<label><h4>Gender :</h4></label>
				<select class="form-control" name="gender">
					<option>Male</option>
					<option>Female</option>
				</select>
			</div>
			<div class="row">
				<div class="col-sm-5">
					<div class="form-group">
						<label><h4>City :</h4></label>
						<input type="text" class="form-control" name="city">
					</div>
				</div>
				<div class="col-sm-1"></div>
				<div class="col-sm-6">
					<div class="form-group">
						<label><h4>Country: </h4></label>
						<input type="text" class="form-control" name="country">
					</div>
				</div>
			</div>
			<button type="submit" class="btn btn-success"><h4>Submit</h4></button>
		</form>
		<div class="col-sm-3"></div>
	</div>
</div>
</body>
</html>