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
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="http://www.sjgames.com">SJG Forums Mobile</a>
				</div>
			</div>
		</nav>

		<div class="container-fluid">
			<ol class="breadcrumb">
				<li ><a href="forumslist.jsp">Home</a></li>
				<li class="active"><a href="threadlist.jsp?f=13">Munchkin</a></li>
			</ol>

			<%= RenderingHelpers.createPaginatorHtml("threadlist.jsp?f=13&", 7, 12) %>

			<div class="list-group">
				<a href="postlist.jsp?t=124" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Take. Me. Home.</p>
					<p class="list-group-item-text"><small>Mr_Spanky | 1 post | 09-24-2007 02:03 PM</small></p>
				</a>
				<a href="postlist.jsp?t=34565" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Please help me with this problem</p>
					<p class="list-group-item-text"><small>Gnomasz | 14 posts | Today 08:19 PM</small></p>
				</a>
				<a href="postlist.jsp?t=124" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Take. Me. Home.</p>
					<p class="list-group-item-text"><small>Mr_Spanky | 1 post | 09-24-2007 02:03 PM</small></p>
				</a>
				<a href="postlist.jsp?t=34565" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Please help me with this problem</p>
					<p class="list-group-item-text"><small>Gnomasz | 14 posts | Today 08:19 PM</small></p>
				</a>
				<a href="postlist.jsp?t=124" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Take. Me. Home.</p>
					<p class="list-group-item-text"><small>Mr_Spanky | 1 post | 09-24-2007 02:03 PM</small></p>
				</a>
				<a href="postlist.jsp?t=34565" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Please help me with this problem</p>
					<p class="list-group-item-text"><small>Gnomasz | 14 posts | Today 08:19 PM</small></p>
				</a>
				<a href="postlist.jsp?t=124" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Take. Me. Home.</p>
					<p class="list-group-item-text"><small>Mr_Spanky | 1 post | 09-24-2007 02:03 PM</small></p>
				</a>
				<a href="postlist.jsp?t=34565" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Please help me with this problem</p>
					<p class="list-group-item-text"><small>Gnomasz | 14 posts | Today 08:19 PM</small></p>
				</a>
				<a href="postlist.jsp?t=124" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Take. Me. Home.</p>
					<p class="list-group-item-text"><small>Mr_Spanky | 1 post | 09-24-2007 02:03 PM</small></p>
				</a>
				<a href="postlist.jsp?t=34565" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Please help me with this problem</p>
					<p class="list-group-item-text"><small>Gnomasz | 14 posts | Today 08:19 PM</small></p>
				</a>
				<a href="postlist.jsp?t=124" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Take. Me. Home.</p>
					<p class="list-group-item-text"><small>Mr_Spanky | 1 post | 09-24-2007 02:03 PM</small></p>
				</a>
				<a href="postlist.jsp?t=34565" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Please help me with this problem</p>
					<p class="list-group-item-text"><small>Gnomasz | 14 posts | Today 08:19 PM</small></p>
				</a>
				<a href="postlist.jsp?t=124" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Take. Me. Home.</p>
					<p class="list-group-item-text"><small>Mr_Spanky | 1 post | 09-24-2007 02:03 PM</small></p>
				</a>
				<a href="postlist.jsp?t=34565" class="list-group-item">
					<p class="list-group-item-text text-primary lead">Please help me with this problem</p>
					<p class="list-group-item-text"><small>Gnomasz | 14 posts | Today 08:19 PM</small></p>
				</a>
			</div>

			<%= RenderingHelpers.createPaginatorHtml("threadlist.jsp?f=13&", 1, 13) %>
	 
			<a class="btn btn-primary btn-block" href="http://forums.sjgames.com?forumdisplay.php?f=13">View on Original Site</a>
		</div> <!-- container-fluid -->
	</body>
</html>