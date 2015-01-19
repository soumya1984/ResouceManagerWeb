<%@page import="edu.sjsu.courseapp.domain.Cloud"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="edu.sjsu.courseapp.domain.Instance"%>
<%@page import="com.amazonaws.services.ec2.model.InstanceCount"%>
<%@page import="edu.sjsu.courseapp.dao.jdbc.UserDaoJdbcImpl"%>
<%@page import="edu.sjsu.courseapp.dao.jdbc.CloudDaoJdbcImpl"%>
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
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin - Bootstrap Admin Template</title>

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

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>


<body>
	<%
		ApplicationContext context = new ClassPathXmlApplicationContext(
			"root-context.xml");
			InstanceDaoJdbcImpl instance = (InstanceDaoJdbcImpl) context
			.getBean("instanceServ");
			int instanceCount = instance.getInstanceCount();
			//get the list of the instances 

			List<Instance> instanceList = instance.getInstanceallList();
			
			Map<Integer, Double> billMap=instance.getBillPerUser();
			double totalCharge=0;
			for(Map.Entry map:billMap.entrySet()){
		totalCharge=totalCharge+(Double) map.getValue();
			}


			CloudDaoJdbcImpl cloud = (CloudDaoJdbcImpl) context
			.getBean("cloudServ");
			int cloudCount = cloud.getCloudCount();
			List<Cloud> cloudList=cloud.getCloudallList();

			UserDaoJdbcImpl user = (UserDaoJdbcImpl) context
			.getBean("userServ");
			int userCloud = user.getUserCount();
			//get all the active instances ...
			Map<String,Integer> instanceMap = new HashMap<String,Integer>(); 
			for(Instance instanceObj:instanceList) {
			    if( instanceObj.getStatus().equals("Active")){
			    	Integer value =instanceMap.get("Active");
			    	if(value==null){
			    		instanceMap.put("Active", 1);
			    	}else{
			    	   instanceMap.put("Active", value+1);
			    	}
			    }else if(instanceObj.getStatus().equals("Inactive")){	    	
			    	Integer inactiveValue =instanceMap.get("Inactive");
			    	if(inactiveValue==null){
			    		instanceMap.put("Inactive", 1);
			    	}else{
			    	instanceMap.put("Inactive",inactiveValue+1);
			    	}
			    }
		
			}
			int inactive=0,active=0;
			if(instanceMap.get("Active")!=null)
			{
		active= instanceMap.get("Active");
			}
			if(instanceMap.get("Inactive")!=null)
			{
		inactive= instanceMap.get("Inactive");
			}
		//	inactive = instanceMap.get("Inactive");
	%>

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
			<a class="navbar-brand" href="index.html">SB Admin</a>
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
										<strong>Adminstrator</strong>
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
					<li><a href="http://localhost:8080/courseapp"><i class="fa fa-fw fa-power-off"></i> Log
							Out</a></li>
				</ul></li>
		</ul>
		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">
				<li class="active"><a href="index.html"><i
						class="fa fa-fw fa-dashboard"></i> Dashboard</a></li>
				<li><form action="${context}/generator" method="post">
						<a href="javascript:;" onclick="parentNode.submit();"><i
							class="fa fa-fw fa-bar-chart-o"></i>
							<div>Dynamic Request Generator....</div> </a><br />
					</form></li>
				<li><form action="${context}/loadChart" method="post">
						<a href="javascript:;" onclick="parentNode.submit();"><i
							class="fa fa-fw fa-table"></i> Billing Details</a>
					</form></li>
				<%-- 				<li><form action="${context}/re" method="post">
						<a href="forms.html"><i class="fa fa-fw fa-edit"></i> Forms</a>
					</form></li>
				<li><a href="bootstrap-elements.html"><i
						class="fa fa-fw fa-desktop"></i> Bootstrap Elements</a></li>
				<li><a href="bootstrap-grid.html"><i
						class="fa fa-fw fa-wrench"></i> Bootstrap Grid</a></li>
				<li><a href="javascript:;" data-toggle="collapse"
					data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i>
						Dropdown <i class="fa fa-fw fa-caret-down"></i></a>
					<ul id="demo" class="collapse">
						<li><a href="#">Dropdown Item</a></li>
						<li><a href="#">Dropdown Item</a></li>
					</ul></li>
				<li><a href="blank-page.html"><i class="fa fa-fw fa-file"></i>
						Blank Page</a></li>
				<li><a href="index-rtl.html"><i
						class="fa fa-fw fa-dashboard"></i> RTL Dashboard</a></li> --%>
			</ul>
		</div>
		<!-- /.navbar-collapse --> </nav>

		<div id="page-wrapper">

			<div class="container-fluid">

				<!-- Page Heading -->
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">
							Dashboard :<small>Cloud Operation Overview</small>
						</h1>
						<ol class="breadcrumb">
							<li class="active"><i class="fa fa-dashboard"></i> Dashboard
							</li>
						</ol>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-12">
						<div class="alert alert-info alert-dismissable">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">&times;</button>
							<i class="fa fa-info-circle"></i> <strong>Like SB Admin?</strong>
							Try out <a
								href="http://startbootstrap.com/template-overviews/sb-admin-2"
								class="alert-link">SB Admin 2</a> for additional features!
						</div>
					</div>
				</div>
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-comments fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge"><%=cloudCount%></div>
										<div class="huge">Clouds</div>
									</div>
								</div>
							</div>
							<a href="#">
								<div class="panel-footer">
									<span class="pull-left">View Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-green">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-tasks fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge"><%=instanceCount%></div>
										<div class="huge">Instances!</div>
									</div>
								</div>
							</div>
							<a href="#">
								<div class="panel-footer">
									<span class="pull-left">View Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-yellow">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="glyphicons glyphicons-fire"></i>
										<i class="fa fa-money fa-fw"></i>
										
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge">
											$<%=totalCharge%></div>
										<div class="huge">Billing!</div>
									</div>
								</div>
							</div>
							<a href="#">
								<div class="panel-footer">
									<span class="pull-left">View Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
					<div class="col-lg-3 col-md-6">
						<div class="panel panel-red">
							<div class="panel-heading">
								<div class="row">
									<div class="col-xs-3">
										<i class="fa fa-support fa-5x"></i>
									</div>
									<div class="col-xs-9 text-right">
										<div class="huge"><%=userCloud%></div>
										<div class="huge">Users!</div>
									</div>
								</div>
							</div>
							<a href="#">
								<div class="panel-footer">
									<span class="pull-left">View Details</span> <span
										class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
									<div class="clearfix"></div>
								</div>
							</a>
						</div>
					</div>
				</div>
				<!-- /.row -->
				<div class="main-temp-back">
					<div class="panel-body">
						<div class="row">
							<div class="col-xs-6">
								<i class="fa fa-cloud fa-3x"></i> Newyork City
							</div>
							<div class="col-xs-6">
								<div class="text-temp">10°</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-bar-chart-o fa-fw"></i> Area Chart
								</h3>
							</div>
							<div class="panel-body">
								<div id="morris-area-chart"></div>
							</div>
						</div>
					</div>
				</div> -->
				<!-- /.row -->

				<div class="row">
					<div class="col-lg-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-long-arrow-right fa-fw"></i> Instance Details
									Chart
								</h3>
							</div>
							<div class="panel-body">
								<div id="morris-donut-chart"></div>
								<div class="text-right">
									<a href="#">View Details <i
										class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-clock-o fa-fw"></i> Cloud Details..
								</h3>
							</div>
							<div class="panel-body">
								<div class="panel-body">
									<div class="table-responsive">
										<table class="table table-bordered table-hover table-striped">
											<thead>
												<tr>
													<th>Cloud Id #</th>
													<th>Geo Location</th>
													<th>Cloud Name</th>
													<th>Private IP:</th>
													<th>Public IP:</th>
													<th>OS</th>
													<th>Uptime</th>
												</tr>
											</thead>


											<%
												for(Cloud obj:cloudList) {
											%>
											<tbody>
											<tbody>
												<tr>
													<td><%=obj.getCloudid()%></td>
													<td><%=obj.getGeolocation()%></td>
													<td><%=obj.getName()%></td>
													<td><%=obj.getPrivateip()%></td>
													<td><%=obj.getPublicip()%></td>
												</tr>
											</tbody>
											<%
												}
											%>

										</table>
									</div>
									<div class="text-right">
										<a href="#">View All Transactions <i
											class="fa fa-arrow-circle-right"></i></a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">
									<i class="fa fa-money fa-fw"></i> Instance Details
								</h3>
							</div>
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-bordered table-hover table-striped">
										<thead>
											<tr>
												<th>Cloud Id #</th>
												<th>CPU</th>
												<th>Instance Id</th>
												<th>Memory(GB)</th>
												<th>Instance Name</th>
												<th>OS</th>
												<th>Uptime</th>
												<!-- 											<th>Private IP</th>
												<th>Public IP</th>
												<th>Status</th>
												<th>Storage</th>
												<th>Uptime</th>
												<th>User ID</th> -->
											</tr>
										</thead>


										<%
											for(Instance instanceObj:instanceList) {
										%>
										<tbody>
										<tbody>
											<tr>
												<td><%=instanceObj.getCloudid()%></td>
												<td><%=instanceObj.getCpu()%></td>
												<td><%=instanceObj.getInstanceid()%></td>
												<td><%=instanceObj.getMemory()%></td>
												<td><%=instanceObj.getName()%></td>
												<td><%=instanceObj.getOs()%></td>
												<td><%=instanceObj.getUptime()%></td>
												<%-- 												    <td><%instanceObj.getPrivateip(); %></td> --%>
												<%-- 												    <td><%instanceObj.getPublicip(); %></td> --%>
												<%-- 												    <td><%instanceObj.getStatus(); %></td> --%>
												<%-- 												    <td><%instanceObj.getStorage(); %></td> --%>
												<%-- 												    <td><%instanceObj.getUptime(); %></td> --%>
												<%-- 												    <td><%instanceObj.getUserid(); %></td> --%>
											</tr>
										</tbody>
										<%
											}
										%>

									</table>
								</div>
								<div class="text-right">
									<a href="#">View All Transactions <i
										class="fa fa-arrow-circle-right"></i></a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- /.row -->

			</div>
			<!-- /.container-fluid -->

		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->
	<script type="text/javascript">
		// Donut Chart
		Morris.Donut({
			element : 'morris-donut-chart',
			data : [ {
				label : "Active Instances",
				value :
	<%=active%>
		,
				backgroundColor : '#40FF00'
			}, {
				label : "Inactive Instances",
				value :
	<%=inactive%>
		} ],
			resize : true
		});
	</script>
</body>

</html>
