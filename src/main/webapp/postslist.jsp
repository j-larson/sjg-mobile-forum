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
		<link href="custom_fixes.css" rel="stylesheet">
	</head>
	<body>
		<nav class="navbar navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="http://www.sjgames.com">SJG Forums Mobile</a>
				</div>
			</div>
		</nav>
		<%
			String tId = request.getParameter("t");
			if (tId == null) {
				throw new RuntimeException("postslist.jsp invoked without thread id parameter (t)");
			}
			String pageNum = request.getParameter("page");
			if (pageNum == null) {
				pageNum = "1";
			}
			String sourcePage = String.format("http://forums.sjgames.com/showthread.php?t=%s&page=%s", tId, pageNum);
			PostsListModel model = new PostsListModel();
			model.loadFromPage(sourcePage);
			
			String paginatorHtml = RenderingHelpers.createPaginatorHtml("postslist.jsp?t=" + tId + "&", model.paginatorInfo);
		%>
		

		<div class="container">
			<ol class="breadcrumb">
				<li><a href="forumslist.jsp">Home</a></li>
				<li><a href="threadslist.jsp?f=<%= model.forumId %>"><%= model.forumName %></a></li>
				<li class="active"><a href="postslist.jsp?t=<%= model.threadId %>"><%= model.threadName %></a></li>
			</ol>

			<%= paginatorHtml %>

			<%
				for (PostsListModel.Post post : model.posts) {
			%>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">#<%= post.num %> | <%= post.author %> | <%= post.time %></h3>
					</div>
					<div class="panel-body">
						<%= post.bodyHtml %>
					</div>
				</div>
			<%
				}
			%>
			
			<%= paginatorHtml %>
	 
			<a class="btn btn-primary btn-block" href="<%= sourcePage %>">View on Original Site</a>
		</div> <!-- container-fluid -->
	</body>
</html>
