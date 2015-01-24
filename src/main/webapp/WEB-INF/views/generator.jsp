<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="context" scope="request"
	value="<%=request.getContextPath()%>"/>
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
  <script src="http://vast-engineering.github.io/jquery-popup-overlay/jquery.popupoverlay.js"></script>	

</head>


<script type="text/javascript">
$(document).ready(function(){
	
	$('#my_popup').popup('show');

});
</script>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${context}/index">Admin</a>
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope"></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu message-dropdown">
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Admin</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Launched Instance...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Admin</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Cloud Watch Reported statistics...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-preview">
                            <a href="#">
                                <div class="media">
                                    <span class="pull-left">
                                        <img class="media-object" src="http://placehold.it/50x50" alt="">
                                    </span>
                                    <div class="media-body">
                                        <h5 class="media-heading"><strong>Admin</strong>
                                        </h5>
                                        <p class="small text-muted"><i class="fa fa-clock-o"></i> Yesterday at 4:32 PM</p>
                                        <p>Billing is updated...</p>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="message-footer">
                            <a href="#">Read All New Messages</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell"></i> <b class="caret"></b></a>
                    <ul class="dropdown-menu alert-dropdown">
                        <li>
                            <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                        </li>
                        <li>
                            <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">View All</a>
                        </li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Admin <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li>
                        <a href="${context}/index"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                           Dynamic Request Generator... 
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="${context}/index">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Dynamic Request Generator... 
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row">
                    <div class="col-lg-6">

                        <form action="${context}/executeGenerator" method="post">
                        
                            <div class="form-group">
                                <label>Please Provide Email:</label>
                                <input class="form-control" name="email" type="email" value="" id="email">
                                <p class="help-block">Example block-level help text here.</p>
                            </div>
                                                	<div class="form-group">
						<label for="User" class="control-label"> User: </label>        
						<div class="form-group">
							<select class="form-control" name="userid" id="userid">
								<option value=""></option>
								<option value="1">Sudip Aikat</option>
								<option value="2">Kumar</option>
								<option value="3">John</option>
								<option value="4">Emily</option>
								<option value="5">Philip</option>
								<option value="6">Sandra</option>
								<option value="7">Priya</option>
								<option value="8">Tom</option>
								<option value="9">Harry</option>
								<option value="10">Arnold</option>
							</select>
						</div>
						</div>	
                    	<div class="form-group">
						<label for="memory" class="control-label"> Memory: </label>        
					<div class="form-group">
							<select class="form-control" name="memory" id="memory">
								<option value=""></option>
								<option value="512">512MB</option>
								<option value="1024">1024MB</option>
								<option value="2048">2048MB</option>
							</select>
						</div>
					</div>	
					<div class="form-group">
						<label for="request" class="control-label"> Number Of
							Request </label>
					<div class="form-group">
							<select class="form-control" name="request" id="request">
								<option value=""></option>
								<option value="1">1(Just for testing.)</option>
								<option value="2">2(Just for testing.)</option>
								<option value="50">50</option>
								<option value="100">100</option>
								<option value="500">500</option>
								<option value="1000">1000</option>
							</select>
						</div>
					</div>
					
				<div class="form-group">
						<label for="storage" class="control-label"> Storage: </label>
					<div class="form-group">
							<select class="form-control" name="storage" id="storage">
								<option value=""></option>
								<option value="8">8</option>
								<option value="16">16</option>
								<option value="32">32</option>
								<option value="64">64</option>
							</select>
						</div>
					</div>
					<div class="form-group">
								<label for="cpu" class="control-label"> Number Of CPU </label>
								<div class="controls">
									<select name="cpu" id="cpu" class="form-control">
										<option value=""></option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="4">4</option>
									</select>
								</div>
							</div>
							
					<div class="form-group">
						<label for="country" class="control-label"> Country </label>
						<div class="controls">
							<select name="country" id="country" class="form-control">
								<option value="USA">USA</option>
								<option value="AR">Argentina</option>
								<option value="AUS">Australia</option>
								<option value="AT">Austria</option>
								<option value="BY">Belarus</option>
								<option value="BE">Belgium</option>
								<option value="BA">Bosnia and Herzegovina</option>
								<option value="BR">Brazil</option>
								<option value="BG">Bulgaria</option>
								<option value="CA">Canada</option>
								<option value="CL">Chile</option>
								<option value="CHINA">China</option>
								<option value="CO">Colombia</option>
								<option value="CR">Costa Rica</option>
								<option value="HR">Croatia</option>
								<option value="CU">Cuba</option>
								<option value="CY">Cyprus</option>
								<option value="CZ">Czech Republic</option>
								<option value="DK">Denmark</option>
								<option value="DO">Dominican Republic</option>
								<option value="EG">Egypt</option>
								<option value="EE">Estonia</option>
								<option value="FI">Finland</option>
								<option value="FR">France</option>
								<option value="GE">Georgia</option>
								<option value="DE">Germany</option>
								<option value="GI">Gibraltar</option>
								<option value="GR">Greece</option>
								<option value="HK">Hong Kong S.A.R., China</option>
								<option value="HU">Hungary</option>
								<option value="IS">Iceland</option>
								<option value="INDIA">India</option>
								<option value="ID">Indonesia</option>
								<option value="IR">Iran</option>
								<option value="IQ">Iraq</option>
								<option value="IE">Ireland</option>
								<option value="IL">Israel</option>
								<option value="IT">Italy</option>
								<option value="JM">Jamaica</option>
								<option value="JAPAN">Japan</option>
								<option value="KZ">Kazakhstan</option>
								<option value="KW">Kuwait</option>
								<option value="KG">Kyrgyzstan</option>
								<option value="LA">Laos</option>
								<option value="LV">Latvia</option>
								<option value="LB">Lebanon</option>
								<option value="LT">Lithuania</option>
								<option value="LU">Luxembourg</option>
								<option value="MK">Macedonia</option>
								<option value="MY">Malaysia</option>
								<option value="MT">Malta</option>
								<option value="MX">Mexico</option>
								<option value="MD">Moldova</option>
								<option value="MC">Monaco</option>
								<option value="ME">Montenegro</option>
								<option value="MA">Morocco</option>
								<option value="NL">Netherlands</option>
								<option value="NZ">New Zealand</option>
								<option value="NI">Nicaragua</option>
								<option value="KP">North Korea</option>
								<option value="NO">Norway</option>
								<option value="PK">Pakistan</option>
								<option value="PS">Palestinian Territory</option>
								<option value="PE">Peru</option>
								<option value="PH">Philippines</option>
								<option value="PL">Poland</option>
								<option value="PT">Portugal</option>
								<option value="PR">Puerto Rico</option>
								<option value="QA">Qatar</option>
								<option value="RO">Romania</option>
								<option value="RU">Russia</option>
								<option value="SA">Saudi Arabia</option>
								<option value="RS">Serbia</option>
								<option value="SG">Singapore</option>
								<option value="SK">Slovakia</option>
								<option value="SI">Slovenia</option>
								<option value="ZA">South Africa</option>
								<option value="KR">South Korea</option>
								<option value="ES">Spain</option>
								<option value="LK">Sri Lanka</option>
								<option value="SE">Sweden</option>
								<option value="CH">Switzerland</option>
								<option value="TW">Taiwan</option>
								<option value="TH">Thailand</option>
								<option value="TN">Tunisia</option>
								<option value="TR">Turkey</option>
								<option value="UA">Ukraine</option>
								<option value="AE">United Arab Emirates</option>
								<option value="UK">United Kingdom</option>
								<option value="UZ">Uzbekistan</option>
								<option value="VN">Vietnam</option>
							</select>
						</div>
					</div>
					<div class="form-group">
							<label for="osType" class="control-label"> OS Type: </label>
							<div class="controls">
								<select name="osType" id="osType" class="form-control">
									<option value=""></option>
									<option value="Computer">Computer</option>
									<option value="Mobile">Mobile</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="os" class="control-label"> OS </label>
							<div class="controls">
								<select name="os" id="os" class="form-control">
									<option value=""></option>
									<option value="ios">iOS</option>
									<option value="android">Android</option>
									<option value="linux">Linux</option>
									<option value="windows">Windows</option>
								</select>
							</div>
						</div>
					<div class="form-group">
							<label for="cpu" class="control-label"> Pick the
								Algorithm you wish to choose. </label>
							<div class="controls">
								<select name="algorithm" id="algorithm" class="form-control">
									<option value=""></option>
									<option value="ant">Ant Colony Algorithm...</option>
									<option value="honey">Honey Bee Algorithm...</option>
									<option value="pso">PSO Algorithm...</option>
									<option value="geolocation">Geo Location Algorithm...</option>
								</select>
							</div>
						</div>

							<button type="submit" class="btn btn-default">Generate Rquest...</button>
                            <button type="reset" class="btn btn-default">Reset Request...</button>

                        </form>

                    </div>
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
