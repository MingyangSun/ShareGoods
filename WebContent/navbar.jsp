<% HttpSession se = request.getSession(false); %>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
		<% if(se.getAttribute("isLogin") == null || !se.getAttribute("isLogin").equals("yes")) {%>
			<a class="navbar-brand" href="./Index.jsp">ShareGoods</a>
		<% } else { %>
			<a class="navbar-brand" href="./HomePage">ShareGoods</a>
		<% } %>
		</div>
		<div>
			<ul class="nav navbar-nav">
			<li><a href="./HomePage">Home</a></li>
			<li><a href="./Browse.jsp?goods=0">Browse</a></li>
			<li><a href="./TopGoods?top=10">Top Goods</a></li>
			</ul>
			<% if(se.getAttribute("isLogin") == null || !se.getAttribute("isLogin").equals("yes")) {%>
			<ul class="nav navbar-nav navbar-right">
			<li><a class="text-sucess" href="./SignUp.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
			<li><a href="./Login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login  </a></li>
			</ul>
			<% } else { %>
			<ul class="nav navbar-nav navbar-right">
			<li><a href="./HomePage">Welcome, <%= se.getAttribute("username") %></a></li>
			<li><a href="./Logout"><span class="glyphicon glyphicon-log-in"></span> LogOut  </a>
			</ul>
			<% }%>
		</div>
	</div>
</nav>