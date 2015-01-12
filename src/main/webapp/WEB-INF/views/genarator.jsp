<%@ include file="./include.jsp"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>
<title>DashBoard</title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<!-- Bootstrap CSS -->
<link href="${context}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${context}/resources/includes/css/bootstrap-glyphicons.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="${context}/resources/includes/css/styles.css"
	rel="stylesheet">
<!-- Include Modernizr in the head, before any other Javascript -->
<script src="${context}/resources/includes/js/modernizr-2.6.2.min.js"></script>
<script src="${context}/resources/includes/js/script.js"></script>
<script src="${context}/resources/includes/js/jquery-1.8.2.min.js"><\/script>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<script src="http://code.jquery.com/jquery.js"></script>
<style type="text/css">
.btn span.glyphicon {
	opacity: 0;
}

.btn.active span.glyphicon {
	opacity: 1;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
    var i=1;
$("#add_row").click(function(){
    $('#addr'+i).html("<td>"+ (i+1) +"</td><td><input name='name"+i+"' type='text' placeholder='Name' class='form-control input-md'  /> </td><td><input  name='mail"+i+"' type='text' placeholder='Mail'  class='form-control input-md'></td><td><input  name='mobile"+i+"' type='text' placeholder='Mobile'  class='form-control input-md'></td>");

    $('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
    i++; 
});

$("#delete_row").click(function(){
  	 if(i>1){
		 $("#addr"+(i-1)).html('');
		 i--;
		 }
	 });

});
</script>
<form:form action="${context}/executeGenerator" method="post">
	<div class="container">
		<div class="row">
			<div class="span8">
				<form action="billing" method="post" class="form-horizontal"
					id="billingform" accept-charset="utf-8">
					<div class="control-group">
						<label for="email" class="control-label"> Requestor E-Mail
							Address </label>
						<div class="controls">
							<input name="email" type="email" value="" id="email"> <span
								class="help-inline"> Confirmation mail will be send
								here..?</span>
						</div>
					</div>

					<div class="control-group">
						<label for="memory" class="control-label"> Memory </label>
						<div class="controls">
							<select name="memory" id="memory">
								<option value=""></option>
								<option value="8">8GB</option>
								<option value="16">16GB</option>
								<option value="32">32GB</option>
								<option value="64">64GB</option>
								<option value="128">128GB</option>
							</select>
						</div>
					</div>

					<div class="control-group">
						<label for="request" class="control-label"> Number Of
							Request </label>
						<div class="controls">
							<select name="request" id="request">
								<option value=""></option>
								<option value="100">100</option>
								<option value="500">500</option>
								<option value="1000">100</option>
							</select>
						</div>
					</div>

					<div class="control-group">
						<label for="cpu" class="control-label"> Number Of CPU </label>
						<div class="controls">
							<select name="cpu" id="cpu">
								<option value=""></option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
							</select>
						</div>
					</div>

					<div class="control-group">
						<label for="country" class="control-label"> Country </label>
						<div class="controls">
							<select name="country" id="country">
								<option value=""></option>
								<option value="AR">Argentina</option>
								<option value="AU">Australia</option>
								<option value="AT">Austria</option>
								<option value="BY">Belarus</option>
								<option value="BE">Belgium</option>
								<option value="BA">Bosnia and Herzegovina</option>
								<option value="BR">Brazil</option>
								<option value="BG">Bulgaria</option>
								<option value="CA">Canada</option>
								<option value="CL">Chile</option>
								<option value="CN">China</option>
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
								<option value="IN">India</option>
								<option value="ID">Indonesia</option>
								<option value="IR">Iran</option>
								<option value="IQ">Iraq</option>
								<option value="IE">Ireland</option>
								<option value="IL">Israel</option>
								<option value="IT">Italy</option>
								<option value="JM">Jamaica</option>
								<option value="JP">Japan</option>
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
								<option value="GB">United Kingdom</option>
								<option value="US">USA</option>
								<option value="UZ">Uzbekistan</option>
								<option value="VN">Vietnam</option>
							</select>
						</div>
					</div>
					<div class="container">


						<div class="control-group">
							<label for="cpu" class="control-label"> Pick the
								Algorithm you wish to choose. </label>
							<div class="controls">
								<select name="algorithm" id="algorithm">
									<option value=""></option>
									<option value="ant">Ant Colony Algorithm...</option>
									<option value="honey">Honey Bee Algorithm...</option>
									<option value="pso">PSO Algorithm...</option>
									<option value="geolocation">Geo Location Algorithm...</option>
								</select>
							</div>
						</div>
					</div>
					<br />
					<br />
					<br /> <br />
					<div class="form-actions">
						<button type="submit" class="btn btn-large btn-primary">Submit
							Request Genarator</button>
					</div>
				</form>
			</div>
			<!-- .span8 -->
		</div>
	</div>
</form:form>
<!-- Bootstrap JS -->
<script src="${context}/resources/bootstrap/js/bootstrap.min.js"></script>
<!-- Custom JS -->
<script src="${context}/resources/includes/js/script.js"></script>
</body>
</html>