<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" scope="request"
	value="<%=request.getContextPath()%>" />
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Dynamic Request Generator</title>

<!-- Bootstrap CSS -->
<link href="${context}/resources/includes/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${context}/resources/includes/css/bootstrap-glyphicons.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="${context}/resources/includes/css/styles.css"
	rel="stylesheet">
<!-- jQuery -->
<script src="${context}/resources/includes/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${context}/resources/includes/js/bootstrap.min.js"></script>
<!-- Include Modernizr in the head, before any other Javascript -->
<script src="${context}/resources/includes/js/modernizr-2.6.2.min.js"></script>

<!-- Morris Charts JavaScript -->
<script
	src="${context}/resources/includes/js/plugins/morris/raphael.min.js"></script>
<script
	src="${context}/resources/includes/js/plugins/morris/morris.min.js"></script>
<script
	src="${context}/resources/includes/js/plugins/morris/morris-data.js"></script>

<!-- Bootstrap Core CSS -->

<!-- Custom CSS -->
<link href="${context}/resources/includes/css/sb-admin.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="${context}/resources/includes/css/plugins/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="${context}/resources/includes/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Include jQuery Popup Overlay -->
<script
	src="http://vast-engineering.github.io/jquery-popup-overlay/jquery.popupoverlay.js"></script>

</head>


<script type="text/javascript">
	$(document).ready(function() {
		$('#my_popup').popup('show');
	});
</script>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${context}/index">Admin</a>
		</div>
		<!-- Top Menu Items -->
		<ul class="nav navbar-right top-nav">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="fa fa-envelope"></i> <b
					class="caret"></b></a>
				<ul class="dropdown-menu message-dropdown">
					<li class="message-preview"><a href="#">
							<div class="media">
								<span class="pull-left"> <img class="media-object"
									src="http://placehold.it/50x50" alt="">
								</span>
								<div class="media-body">
									<h5 class="media-heading">
										<strong>Admin</strong>
									</h5>
									<p class="small text-muted">
										<i class="fa fa-clock-o"></i> Yesterday at 4:32 PM
									</p>
									<p>Lorem ipsum dolor sit amet, consectetur...</p>
								</div>
							</div>
					</a></li>
					<li class="message-preview"><a href="#">
							<div class="media">
								<span class="pull-left"> <img class="media-object"
									src="http://placehold.it/50x50" alt="">
								</span>
								<div class="media-body">
									<h5 class="media-heading">
										<strong>Admin</strong>
									</h5>
									<p class="small text-muted">
										<i class="fa fa-clock-o"></i> Yesterday at 4:32 PM
									</p>
									<p>Lorem ipsum dolor sit amet, consectetur...</p>
								</div>
							</div>
					</a></li>
					<li class="message-preview"><a href="#">
							<div class="media">
								<span class="pull-left"> <img class="media-object"
									src="http://placehold.it/50x50" alt="">
								</span>
								<div class="media-body">
									<h5 class="media-heading">
										<strong>Admin</strong>
									</h5>
									<p class="small text-muted">
										<i class="fa fa-clock-o"></i> Yesterday at 4:32 PM
									</p>
									<p>Lorem ipsum dolor sit amet, consectetur...</p>
								</div>
							</div>
					</a></li>
					<li class="message-footer"><a href="#">Read All New
							Messages</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="fa fa-bell"></i> <b
					class="caret"></b></a>
				<ul class="dropdown-menu alert-dropdown">
					<li><a href="#">Alert Name <span
							class="label label-default">Alert Badge</span></a></li>
					<li><a href="#">Alert Name <span
							class="label label-primary">Alert Badge</span></a></li>
					<li><a href="#">Alert Name <span
							class="label label-success">Alert Badge</span></a></li>
					<li><a href="#">Alert Name <span class="label label-info">Alert
								Badge</span></a></li>
					<li><a href="#">Alert Name <span
							class="label label-warning">Alert Badge</span></a></li>
					<li><a href="#">Alert Name <span
							class="label label-danger">Alert Badge</span></a></li>
					<li class="divider"></li>
					<li><a href="#">View All</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="fa fa-user"></i> Admin <b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="#"><i class="fa fa-fw fa-user"></i> Profile</a></li>
					<li><a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
					</li>
					<li><a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
					</li>
					<li class="divider"></li>
					<li><a href="#"><i class="fa fa-fw fa-power-off"></i> Log
							Out</a></li>
				</ul></li>
		</ul>
		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">
				<li><a href="${context}/index"><i class="fa fa-fw fa-dashboard"></i>
						Dashboard</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse --> </nav>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<ol class="breadcrumb">
							<li><i class="fa fa-dashboard"></i> <a href="${context}/index"><span><lebel>Users
										</lebel></span></a></li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<table cellpadding="10" class="table table-striped">
						<tr>

							<th>User ID</th>
							<th>User Name</th>
							<th>Credit Card</th>
							<th>Email Id</th>
							<th>Phone</th>
							<th>Total Bill</th>
							<th>Paid Bill</th>
							<th>Bill Due</th>

						</tr>
						<c:forEach var="user_list" items="${user_list}">
							<tr>
								<td>${user_list.getUserid()}</td>
								<td>${user_list.getName()}</td>
								<td>${user_list.getCreditcard()}</td>
								<td>${user_list.getEmailid()}</td>
								<td>${user_list.getPhone()}</td>
								<td>$${user_list.getTotalbill()}</td>
								<td>${user_list.getPaidbill()}</td>
								<td>$${user_list.getTotalbill()-user_list.getPaidbill()}</td>
							</tr>
						</c:forEach>
					</table>
				</div>

			</div>
		</div>
		<!-- /.container-fluid -->
		<div id="my_popup">Thank for submitting the request</div>
	</div>
	<!-- /#page-wrapper -->

	</div>

</body>

</html>