<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page import="java.util.List"%>
<%@ page import="com.dev.constants.Constants"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-9">
<style>
#verticle {
	border-left: 5px solid;
	border-color: white;
}

.aciklama {
	position: relative;
	display: inline-block;
}

.aciklama::after {
	content: attr(data-title);
	position: absolute;
	left: 100%;
	padding: 15px;
	background: none;
	color: #333;
	opacity: 0;
	word-wrap: break-word;
	height: auto;
	width: auto;
	-moz-box-shadow: 0 0 4px #222;
	-webkit-box-shadow: 0 0 4px #222;
	box-shadow: 0 0 4px #222;
	pointer-events: none;
}

.aciklama:hover::after {
	opacity: 1;
	-webkit-transition: opacity 0.3s;
	-moz-transition: opacity 0.3s;
	transition: opacity 0.3s;
	pointer-events: auto;
}
</style>

<title>Eteration</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="http://<%=Constants.HOST_IP%>:8080/basicbanking/resources/static/css/flatpickr.min.css"
	rel="stylesheet" />

<link
	href="http://<%=Constants.HOST_IP%>:8080/basicbanking/resources/static/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="http://<%=Constants.HOST_IP%>:8080/basicbanking/resources/static/js/jquery-3.6.1.min.js"></script>

<script
	src="http://<%=Constants.HOST_IP%>:8080/basicbanking/resources/static/js/flatpickr.js"></script>


<script>
	flatpickr("input[type=datetime-local]", {});
</script>



<script
	src="http://<%=Constants.HOST_IP%>:8080/basicbanking/resources/static/js/popper.min.js"></script>

<script
	src="http://<%=Constants.HOST_IP%>:8080/basicbanking/resources/static/js/bootstrap.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- Hide Update Table -->
<script type="text/javascript">
	function pageLoad() {
	}
</script>


<script type="text/javascript">
	function closeAddForm() {
		document.getElementById("addFormOpen").click();
	}
</script>

<script
	src="http://<%=Constants.HOST_IP%>:8080/basicbanking/resources/static/js/font.js"></script>



<script
	src="http://<%=Constants.HOST_IP%>:8080/basicbanking/resources/static/js/sweetalert.min.js"></script>


<script>
	jQuery(function($) {
		if (document.getElementById("event1") != null) {
			console.log("event1 clicked");
			document.getElementById("event1").click();
		}

		if (document.getElementById("event2") != null)
			document.getElementById("event2").click();

		if (document.getElementById("event3") != null)
			document.getElementById("event3").click();

		if (document.getElementById("event4") != null)
			document.getElementById("event4").click();
		
		if (document.getElementById("event5") != null)
			document.getElementById("event5").click();
	});

	function msg1() {
		setTimeout(function() {
			$("#var1").val(true);
			$("#form1").submit();
		}, 2000);
		swal("Success!", "File saved to database successfully!", "success");
	}

	function msg2() {
		swal("Something went wrong!", "It should not be more than 5mb",
				"error");
	}

	function msg3() {
		swal("Something went wrong!",
				"Invalid file extension! pdf extension is supported!",
				"error");
	}

	function msg4() {
		swal("Fail!", "File could not be saved successfully!", "error");
	}
	
	function msg5() {
		swal("Fail!", "Text area empty!", "error");
	}
</script>



</head>

<form style="display: none"
	action="http://<%=Constants.HOST_IP%>:8080/basicbanking/home"
	method="POST" id="form1">
	<input type="hidden" id="form1" name="form1" value="" /> <input
		type="hidden" id="form1" name="form1" value="" />
</form>
<body onload="pageLoad()" style="background-color: #F8F8FF;">


	<header class="text-white mb-3" style="background-color: #ced4da;">
		<div class="container">
			<div class="d-flex justify-content-between">
				<div>
					<h4
						style="font-family: Arial, Helvetica, sans-serif; font-weight: bold; color: #ff8400;"
						class="mt-4">Hikmet Tüzüner</h4>
				</div>

				<div>
					<img src="<c:url value="/resources/static/images/eteration.png" />"
						width="200" height="90" />
				</div>
			</div>

		</div>
	</header>


	<form style="display: none"
		action="http://<%=Constants.HOST_IP%>:8080/basicbanking/home"
		method="POST" id="form1">
		<input type="hidden" id="var1" name="" value="" /> <input
			type="hidden" id="var1" name="var1" value="" />
	</form>

	<div class="container">
		<header class="p-3 text-white"
			style="background-color: #ced4da; border-radius: 6px;">
			<div class="d-flex justify-content-between">
				<div>
					<form action="/basicbanking/home" method="post">
						<button class="btn btn-secondary text-white"
							style="font-family: Arial, Helvetica, sans-serif; background-color: #ff8400; border-style: dotted; border-width: 2px; border-color: #FFA500;"
							type="submit">Account Manage</button>
					</form>
				</div>
			</div>
		</header>
	</div>


	<!-- Transactions FORM -->
	<div class="container mt-3">
		<p></p>
		<div class="collapse mb-3" id="collapseExample">
			<div class="card border-warning mb-3">
				<div class="card-body text-primary">
					<form action="transactions" method="post"
						accept-charset="UTF-8" enctype="multipart/form-data">

						<div class="form-group">
							<label style="font-weight: bold; color: #ff8400;" for="text">Account Name</label> <input
								type="text" class="form-control mt-2" required
								placeholder="Please Entry Account Name.." id="accountName"
								name="accountName">
						</div>

						<div class="form-group">
							<label style="font-weight: bold;color : #ff8400;" for="text">Account Id </label> <input
								type="text" class="form-control mt-2" required
								placeholder="Please Entry Account Id.." id="accountId"
								name="accountId">
						</div>


						<div class="d-grid gap-2 d-md-flex justify-content-md-end">
							<button type="button" onclick="closeAddForm()"
								class="btn btn-danger mt-2 mr-1"
								style="font-family: Arial, Helvetica, sans-serif;">
								<i class="fa fa-times-circle-o" aria-hidden="true"></i> Close
							</button>
							<button class="btn btn-success  mt-2 mr-1" type="submit"
								style="font-family: Arial, Helvetica, sans-serif;">
								<i class="fa fa-cloud-upload" aria-hidden="true"></i> Sent
							</button>
						</div>

					</form>

				</div>
			</div>
		</div>
	</div>




	<div class="container mt-3">
		<div class="card border-warning">
			<div class="card-header text-white"
				style="background-color: #ced4da; border-radius: 6px;">

				<div class="d-flex justify-content-between">
					<div>
						<button class="btn btn-success text-left" style="width: 100%"
							type="button" data-bs-toggle="collapse" id="addFormOpen"
							style="font-family: Arial, Helvetica, sans-serif;"
							data-bs-target="#collapseExample" aria-expanded="false"
							aria-controls="collapseExample"
							style="font-family: Arial, Helvetica, sans-serif;">
							<i class="fa fa-plus-circle"></i> Apply Transaction
						</button>
					</div>
				</div>
			</div>


		</div>

	</div>


	<%
	if (request.getAttribute("success") != null) {
	%>
	<h1 id="event1" onclick="msg1()"></h1>
	<%
	}
	%>

	<%
	if (request.getAttribute("fileSize") != null) {
	%>
	<h1 id="event2" onclick="msg2()"></h1>
	<%
	}
	%>

	<%
	if (request.getAttribute("fileType") != null) {
	%>
	<h1 id="event3" onclick="msg3()"></h1>
	<%
	}
	%>

	<%
	if (request.getAttribute("fail") != null) {
	%>
	<h1 id="event4" onclick="msg4()"></h1>
	<%
	}
	%>

	<%
	if (request.getAttribute("textEmpty") != null) {
	%>
	<h1 id="event5" onclick="msg5()"></h1>
	<%
	}
	%>


</body>
</html>