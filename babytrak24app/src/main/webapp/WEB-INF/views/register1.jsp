<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign-Up/Login Form</title>
<link
	href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet"
	href="https://s3-us-west-1.amazonaws.com/babytrak-assets/assets/css/style.css">
</head>

<body>
	<div class="form">


		<div id="signupTab">

			<h1>Register for free!!!</h1>

			<form:form id="regForm" modelAttribute="baby"
				action="registerProcess" method="post">

				<div class="top-row">
					<div class="field-wrap">
						<form:label path="firstname">FirstName</form:label>
						<form:input path="firstname" id="firstname" autocomplete="off" />
					</div>
					<div class="field-wrap">
						<form:label path="lastname">LastName</form:label>
						<form:input path="lastname" id="lastname" autocomplete="off" />
					</div>
				</div>
				<div class="field-wrap">
					<form:label path="email">Email Address<span
							class="req">*</span>
					</form:label>
					<form:input path="email" id="email" autocomplete="off" />
				</div>

				<div class="field-wrap">
					<form:label path="password">Set a Password<span class="req">*</span>
					</form:label>
					<form:password path="password" id="password"
						autocomplete="off" />
				</div>
				
				
				<div class="field-wrap" style="font-style: italic; color: red;">
					${message}
				</div>
				<p class="forgot">
					<a href="/login">Login here</a>
				</p>
				

				<form:button class="button button-block" id="register"
					name="register">Register</form:button>


			</form:form>
		</div>




	</div>
	<!-- /form -->
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script
		src="https://s3-us-west-1.amazonaws.com/babytrak-assets/assets/js/index.js"></script>

</body>
</html>
