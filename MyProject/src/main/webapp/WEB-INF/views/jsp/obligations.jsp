<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/style.css">

<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker").datepicker();
	});
	
	$(document).ready(function() {

	    $('#main').change(function() {

	        if ($(this).is(':checked')) {
	        $('input[name="current_income"]:checkbox').prop('checked', true);        

	        } else {

	            $('input[name="current_income"]:checkbox').prop('checked', false);
	        }
	    });


	$('input[name="current_income"]:checkbox').change(function() {
	        var chkLength = $('input[name="current_income"]:checkbox').length;
	        var checkedLen = $('input[name="current_income"]:checkbox:checked').length;    
	        if (chkLength == checkedLen) {
	            $('#main').prop('checked', true);
	        } else {
	            $('#main').prop('checked', false);
	        }
	    });
	});
	
	/* $('#delete').click(function () {
	    $(".checkbox input:selected").parent().remove();
	}); */
	
</script>


<title>My obligations</title>

<style>
.modal {
	display: none;
	position: fixed;
	z-index: 1;
	padding-top: 100px;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	overflow: auto;
	background-color: rgb(0, 0, 0);
	background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}

.close {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: #000;
	text-decoration: none;
	cursor: pointer;
}
</style>


</head>
<body>

	<%
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null)
			response.sendRedirect("./login");
	%>

	<header>
	<div class="logo">
		<img alt="logo" src="img/logo.jpg">
	</div>
	<div class="title-text">
		<h1>Get a full control over your money!</h1>
	</div>
	<div class="header_buttons">
		<a href="./logout"><button type="submit">Log out</button></a>
	</div>
	<div class="navigation_buttons">

		<nav> <a href="/inquiries"><button class="n_button"
				type="submit">Inquiries</button></a> 
				<a href="/home"><button
				class="n_button" type="submit">Budget</button></a> 
				<a href="./obligations"><button
				class="n_button" type="submit">Obligations</button></a> <a
			href="./expenses"><button class="n_button" type="submit">Expenses</button></a>
		<a href="./incomes"><button class="n_button" type="submit">Incomes</button></a>
		<a href="./home"><button class="n_button" type="submit">My profile</button></a> </nav>

	</div>
	</header>

	<div>
		<hr>
		<br />
	</div>

	<section class="">
	<div class="">
		<h1>Obligations</h1>

		<c:if test="${ empty user }">
			<p> This profile does not exist!</p>
		</c:if>

		<p>
			<form:form>
				<input type="submit" id="delete" name="commit" value="Delete selected"><br/>
				<input name="selectALL" type="checkbox" value="" id="main" />&nbsp;Select all<br/>
				<c:forEach items="${user.obligations}" var="obligation">
									
					<input type="checkbox" name="current_income" id="${obligation.id}"/>
					<c:out value="${obligation}"></c:out>
					<br />
				</c:forEach>
			</form:form>
		</p>

		<button id="myBtn">Add obligation</button>

		<div id="myModal" class="modal">

			<div class="modal-content">
				<span class="close">close</span>

				<form:form commandName="obligation">

					<p>
						<form:label path="categoryId">Choose category:</form:label>
						<form:select id="categoryId" class="input" path="categoryId">
							<form:option value="1">Credit</form:option>

							<form:option value="2">Loan</form:option>

							<form:option value="3">Fast Credit</form:option>

							<form:option value="4">Other</form:option>

						</form:select>
					</p>
					<p>
						<form:label path="amount">Enter an ammount of money:</form:label>
						<form:input type="number" min="0.01" step="0.01" value="1.00"
							max="1000000" id="amount" class="input" name="amount"
							path="amount" placeholder="Money" required="required" />
					</p>
					<p>
						<form:label path="repeatingId">Choose a stage of repeating:</form:label>
						<form:select id="repeatingId" class="input" name="repeatingId"
							path="repeatingId">

							<form:option value="1">Once</form:option>
							<form:option value="2">Daily</form:option>
							<form:option value="3">Weekly</form:option>
							<form:option value="4">Monthly</form:option>
							<form:option value="5">Year</form:option>

						</form:select>

					</p>
					<p>
						<form:label path="date">Choose a date:</form:label>
						<form:input id="datepicker" class="input" name="date" path="date"
							placeholder="Date" required="required" />
					</p>
					
					<p>
						<form:label path="periodQuantity">Choose a period quantity:</form:label>
						<form:input id="periodQuantity" class="input" name="periodQuantity" path="periodQuantity"
							placeholder="Quantity" required="required" />
					</p>
					
					
					<p>
						<form:label path="periodId">Choose a period:</form:label>
						<form:select id="periodId" class="input" path="periodId">
							<form:option value="1">Days</form:option>

							<form:option value="2">Weeks</form:option>

							<form:option value="3">Months</form:option>

							<form:option value="4">Years</form:option>

						</form:select>
					</p>
					
					<p>
						<form:label path="description">Enter a description:</form:label>
						<form:textarea id="description" class="input" name="description"
							path="description" placeholder="Description" required="required" />
					</p>

					<p class="submit">
						<input type="submit" name="commit" value="Add">
					</p>

				</form:form>

			</div>

		</div>

		<script>
			// Get the modal
			var modal = document.getElementById('myModal');

			// Get the button that opens the modal
			var btn = document.getElementById("myBtn");

			// Get the <span> element that closes the modal
			var span = document.getElementsByClassName("close")[0];

			// When the user clicks the button, open the modal
			btn.onclick = function() {
				modal.style.display = "block";
			}

			// When the user clicks on <span> (x), close the modal
			span.onclick = function() {
				modal.style.display = "none";
			}

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
				if (event.target == modal) {
					modal.style.display = "none";
				}
			}
		</script>

	</div>
	</section>

	<div>
		<br />
		<hr>
	</div>
	<footer>
	<div id="footer">
		<p>Webpage made by Vasil and Katerina, IT Talents Training Camp,
			Java EE, 2016</p>
	</div>
	</footer>
</body>
</html>