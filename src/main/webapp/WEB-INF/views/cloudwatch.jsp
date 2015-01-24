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

<title>Monitoring Metrics of Amazon EC2 Instance </title>

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
	<%
	
		ApplicationContext context = new ClassPathXmlApplicationContext(
			"root-context.xml");
			UserDaoJdbcImpl users = (UserDaoJdbcImpl) context
			.getBean("userServ");
			
			List<User> userList = users.getUserallList();
			String result ="[";
		for(User user:userList){
			       result=result+"{"+"y:'"+user.getName()+"', ";
		       result=result+"Bill:"+user.getTotalbill()+"},";
			}
			result=result+"]";
			//String a = {instanceId}; 
			//System.out.println(${instanceId});
			
			
			//List<Datapoint> dataList = AwsCloudWatch.getCloudWatchMonitoringData(request.getParameter("instanceId"));
			
		//String result1 = "[";
		//for (Datapoint data : dataList) {
			//result1 = result1 + "{" + "time:'" + data.getTimestamp()
				//	+ "', ";
			//result1 = result1 + "maximum:" + data.getMaximum() + ",";
			//result1 = result1 + "minimum:" + data.getMinimum() + ",";
			//result1 = result1 + "average:" + data.getAverage() + "},";
		//}
		//result1 = result1 + "]";
	%>
	<c:set var="data" scope="request"
	value="<%=result%>" />
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

				<font size="6">Monitoring Metrics of Amazon EC2 Instance ${instanceId}</font>
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header"></h1>
						<ol class="breadcrumb">
							<li><i class="fa fa-dashboard"></i> <a href="${context}/index">Dashboard</a>
							</li>
						</ol>
					</div>
				</div>
				<center><font size=5><bold>CPU Utilization (%) of EC2 Instance ${instanceId}</bold></font></center>
				<div id="cpuchartData"></div>
				<script>
					Morris.Line({
						element : 'cpuchartData',
						data : ${cpuchartData},
						xkey : 'time',
						ykeys : [ 'average', 'maximum' , 'minimum'],
						labels : [ 'Average CPU' , 'Maximum CPU' , 'Minimum CPU' ],
						parseTime : false,
						ymin : 0,
						postUnits : '%'
					});
				</script>
				<br>
				<br>
				<center><font size=5><bold>Network In Bytes Metric of EC2 Instance ${instanceId}</bold></font></center>
				<div id="nwinchartData"></div>
				<script>
					Morris.Line({
						element : 'nwinchartData',
						data : ${nwinchartData},
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
				<center><font size=5><bold>Network Out Bytes Metric of EC2 Instance ${instanceId}</bold></font></center>
				<div id="nwoutchartData"></div>
				<script>
					Morris.Line({
						element : 'nwoutchartData',
						data : ${nwoutchartData},
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
				<center><font size=5><bold>Disk Read Bytes Metric of EC2 Instance ${instanceId}</bold></font></center>
				<div id="diskreadchartData"></div>
				<script>
					Morris.Line({
						element : 'diskreadchartData',
						data : ${diskreadchartData},
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
				<center><font size=5><bold>Disk Write Bytes Metric of EC2 Instance ${instanceId}</bold></font></center>
				<div id="diskwritechartData"></div>
				<script>
					Morris.Line({
						element : 'diskwritechartData',
						data : ${diskwritechartData},
						xkey : 'time',
						ykeys : [ 'average', 'maximum' , 'minimum'],
						labels : [ 'Average' , 'Maximum' , 'Minimum' ],
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