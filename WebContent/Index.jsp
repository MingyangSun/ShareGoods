<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<style>

#cycler1 img.active{z-index:3}

#cycler2 img.active{z-index:3}
</style>
<title>Share Your Goods</title>
</head>
<body>
<%@ include file="./navbar.jsp" %>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-1">
		</div>
		<div class="col-md-6 text-center">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-10" id="cycler1" >
					<img class="active" src="./pictures/1.jpg" >
					<img src="./pictures/bear.jpg" >
					<img src="./pictures/1.jpg" >
				</div>
			</div>
			<div class="row" >
				<div class="col-md-10" id="cycler2">
					<img src="./pictures/sw.jpg" >
					<img src="./pictures/sk.jpg" >
					<img class="active" src="./pictures/sw.jpg" >
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
		<div class="col-md-1">
		</div>
		<div class="col-md-3 text-center">
			<form role="form" method="post" action="Login">
				<div class="form-group text-left">
					<label><h4>Email :</h4></label>
					<input type="email" class="form-control" name="userEmail"></input>
				</div>
				<div class="form-group text-left">
					<label><h4>Password :</h4></label>
					<input type="password" class="form-control" name="pwd"></input>
				</div>
				<% if(request.getAttribute("message") != null) { %>
					<div class="alert alert-danger">
					  <%= request.getAttribute("message") %>
					</div>
				<% } %>
				<button type="submit" class="btn btn-success" style="width:50%;">Log In</button>
				<p>Don't have an account? <a href="./SignUp.jsp">Sign up</a>.</p>
			</form>
		</div>
		<div class="col-md-1">
		</div>
	</div>
</div>
<script>
function cycleImages(){
    var $active1 = $('#cycler1 .active');
    var $next1 = ($active1.next().length > 0) ? $active1.next() : $('#cycler1 img:first');
    $next1.css('z-index',2);//move the next image up the pile
    
    var $active2 = $('#cycler2 .active');
    var $next2 = ($active2.next().length > 0) ? $active2.next() : $('#cycler2 img:first');
    $next2.css('z-index',2);//move the next image up the pile
    
    var tempSrc1 = $active1.attr('src');
    var tempSrc2 = $active2.attr('src');
    $active1.attr('src', tempSrc2);
    $active2.attr('src', tempSrc1);
    
    $active1.fadeOut(1500,function(){//fade out the top image
	  $active1.css('z-index',1).show().removeClass('active');//reset the z-index and unhide the image
      $next1.css('z-index',3).addClass('active');//make the next image the top one
    });

    $active2.fadeOut(1500,function(){//fade out the top image
	  $active2.css('z-index',1).show().removeClass('active');//reset the z-index and unhide the image
      $next2.css('z-index',3).addClass('active');//make the next image the top one
    });
  }

$(document).ready(function(){
//run every 7s
setInterval('cycleImages()', 7000);
})
</script>	
</body>
</html>