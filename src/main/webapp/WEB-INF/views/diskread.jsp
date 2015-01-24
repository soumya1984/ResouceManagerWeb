<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="edu.sjsu.courseapp.domain.User"%>
<%@page import="com.amazonaws.services.ec2.model.InstanceCount"%>
<%@page import="com.sjsu.courseapp.cloudwatch.AwsCloudWatch"%>
<%@page import="edu.sjsu.courseapp.dao.jdbc.UserDaoJdbcImpl"%>
<%@page import="edu.sjsu.courseapp.dao.jdbc.CloudDaoJdbcImpl"%>
<%@page import="com.amazonaws.services.cloudwatch.model.Datapoint"%>
<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" scope="request"
	value="<%=request.getContextPath()%>" />
<%@ page import="java.util.List"%>
<%@ page import="edu.sjsu.courseapp.dao.jdbc.InstanceDaoJdbcImpl"%>
<%@ page import="java.util.ArrayList"%>;
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Disk Read Bytes Metric of Amazon EC2 Instance </title>

<!-- Bootstrap Core CSS -->
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

<script
	src="${context}/resources/includes/js/plugins/flot/jquery.flot.js"></script>
<script
	src="${context}/resources/includes/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script
	src="${context}/resources/includes/js/plugins/flot/jquery.flot.resize.js"></script>
<script
	src="${context}/resources/includes/js/plugins/flot/jquery.flot.pie.js"></script>
<script src="${context}/resources/includes/js/plugins/flot/flot-data.js"></script>

</head>

<body>
<c:set var="instanceId" scope="request" value="${instanceId}" />
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
									<p>Instance c1mi1i1 is up...</p>
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

				<font size="8">Disk Read Bytes Metric of Amazon EC2 Instances </font>
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header"></h1>
						<ol class="breadcrumb">
							<li><i class="fa fa-dashboard"></i> <a href="${context}/index">Dashboard</a>
							</li>
						</ol>
					</div>
				</div>
				<center><font size=5><bold>Instance ${instanceId1}</bold></font></center>
				<div id="cloudwatch1"></div>
				<script>
					Morris.Line({
						element : 'cloudwatch1',
						data : ${chartData1},
						xkey : 'time',
						ykeys : [ 'average', 'maximum' , 'minimum'],
						labels : [ 'Average' , 'Maximum' , 'Minimum' ],
						parseTime : false,
						ymin : 0,
						postUnits : 'Bytes'
					});
				</script>
				<br>
				<br>
				<center><font size=5><bold>Instance ${instanceId2}</bold></font></center>
				<div id="cloudwatch2"></div>
				<script>
					Morris.Line({
						element : 'cloudwatch2',
						data : ${chartData2},
						xkey : 'time',
						ykeys : [ 'average', 'maximum' , 'minimum'],
						labels : [ 'Average' , 'Maximum' , 'Minimum' ],
						parseTime : false,
						ymin : 0,
						postUnits : 'Bytes'
					});
				</script>
				<br>
				<br>
				<center><font size=5><bold>Instance ${instanceId3}</bold></font></center>
				<div id="cloudwatch3"></div>
				<script>
					Morris.Line({
						element : 'cloudwatch3',
						data : ${chartData3},
						xkey : 'time',
						ykeys : [ 'average', 'maximum' , 'minimum'],
						labels : [ 'Average ' , 'Maximum ' , 'Minimum ' ],
						parseTime : false,
						ymin : 0,
						postUnits : 'Bytes'
					});
				</script>
				<br>
				<br>
				<center><font size=5><bold>Instance ${instanceId4}</bold></font></center>
				<div id="cloudwatch4"></div>
				<script>
					Morris.Line({
						element : 'cloudwatch4',
						data : ${chartData4},
						xkey : 'time',
						ykeys : [ 'average', 'maximum' , 'minimum'],
						labels : [ 'Average ' , 'Maximum ' , 'Minimum ' ],
						parseTime : false,
						ymin : 0,
						postUnits : 'Bytes'
					});
				</script>
				<br>
				<br>
				<center><font size=5><bold>Instance ${instanceId5}</bold></font></center>
				<div id="cloudwatch5"></div>
				<script>
					Morris.Line({
						element : 'cloudwatch5',
						data : ${chartData5},
						xkey : 'time',
						ykeys : [ 'average', 'maximum' , 'minimum'],
						labels : [ 'Average ' , 'Maximum ' , 'Minimum ' ],
						parseTime : false,
						ymin : 0,
						postUnits : 'Bytes'
					});
				</script>
				<!-- /#page-wrapper -->

			</div>
			<!-- /#wrapper -->
</body>

</html>