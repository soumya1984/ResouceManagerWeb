<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />
<html>
<head>
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
<!-- Morris Charts CSS -->
<link href="${context}/resources/includes/css/styles.css"
	rel="stylesheet">	

<!-- Custom Fonts -->
<link
	href="${context}/resources/includes/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
</head>
<c:set var="context" scope="request"
	value="<%=request.getContextPath()%>" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Welcome to Cloud Management System</h1>
            <div class="account-wall">
                <img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                    alt="">
                <form class="form-signin" action="${context}/index" method="get">
                <input type="text" class="form-control" placeholder="Email" required autofocus>
                <input type="password" class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    Sign in</button>
                <label class="checkbox pull-left">
                    <input type="checkbox" value="remember-me">
                    Remember me
                </label>
                <a href="#" class="pull-right need-help">Need help? </a><span class="clearfix"></span>
                </form>
            </div>
            <a href="#" class="text-center new-account">Create an account </a>
        </div>
    </div>
</div>
</body>
</html>