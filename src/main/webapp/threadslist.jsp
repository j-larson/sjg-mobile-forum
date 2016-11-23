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
			String fId = request.getParameter("f");
			if (fId == null) {
				throw new RuntimeException("threadlist.jsp invoked without forum id parameter (f)");
			}
			String pageNum = request.getParameter("page");
			if (pageNum == null) {
				pageNum = "1";
			}
			ThreadsListModel model = new ThreadsListModel();
			String sourcePage = String.format("http://forums.sjgames.com/forumdisplay.php?f=%s&page=%s", fId, pageNum);
			model.loadFromPage(sourcePage);
			
			String paginatorHtml = RenderingHelpers.createPaginatorHtml("threadslist.jsp?f=" + fId + "&", model.curPage, model.totalPages);
		%>

		<div class="container-fluid">
			<ol class="breadcrumb">
				<li ><a href="forumslist.jsp">Home</a></li>
				<li class="active"><a href="threadslist.jsp?f=<%= fId %>"><%= model.name %></a></li>
			</ol>

			<%= paginatorHtml %>

			<div class="list-group">
				<%
					for (ThreadsListModel.Thread thread : model.threads) {
						int numPosts = thread.numReplies + 1;
						String postString = "post";
						if (numPosts > 1) {
							postString = "posts";
						}
						
						// round up; we want the link to point to the last page
						int numPages = (numPosts + 9) / 10;  
				%>
						<a href="postslist.jsp?t=<%= thread.id %>&page=<%= numPages %>" class="list-group-item">
							<p class="list-group-item-text text-primary lead"><%= thread.title %></p>
							<p class="list-group-item-text">
								<small><%= thread.threadStartAuthor %> | <%= numPosts %> <%= postString %> | <%= thread.lastPostTime %></small></p>
						</a>
				<%
					}
				%>
			</div>

			<%= paginatorHtml %>
	 
			<a class="btn btn-primary btn-block" href="<%= sourcePage %>">View on Original Site</a>
		</div> <!-- container -->
	</body>
</html>