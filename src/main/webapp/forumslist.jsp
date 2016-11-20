<%@ page contentType="text/html;charset=UTF-8" 
         import="com.johanlarson.sjgmf.*"
         session="false" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>SJG Forums Mobile</title>
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
	</head>
	<body>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="http://www.sjgames.com">SJG Forums Mobile</a>
				</div>
			</div>
		</nav>

		<div class="container-fluid">
			<ol class="breadcrumb">
				<li class="active"><a href="#">Home</a></li>
			</ol>
	
			<% 
				FrontPageModel model = new FrontPageModel();
				model.loadFromPage("http://forums.sjgames.com");

				for (FrontPageModel.ForumGroup g : model.groups) {
			%>
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h1 class="panel-title"><%= g.name %></h1>
						</div>
						<div class="list-group">
						<%
							for (FrontPageModel.Forum f : g.forums) {
						%>
								<a href="threadslist.jsp?f=<%= f.id %>" class="list-group-item"><%= f.name %></a>
						<%
							}
						%>
						</div>
					</div>
			<%
				}
			%>
	  
			<a class="btn btn-primary btn-block" href="http://forums.sjgames.com">View on Original Site</a>
		</div> <!-- container-fluid -->
	</body>
</html>