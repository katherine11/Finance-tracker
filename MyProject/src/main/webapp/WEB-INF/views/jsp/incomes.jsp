<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<script>
	$(document).ready(function() {
		$("#datepicker").datepicker();
	});
</script>


<title>Моите приходи</title>

<style>
/* The Modal (background) */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
	background-color: #fefefe;
	margin: auto;
	padding: 20px;
	border: 1px solid #888;
	width: 80%;
}

/* The Close Button */
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
	<header>
	<div class="logo">
		<img alt="logo" src="img/logo.jpg">
	</div>
	<div class="title-text">
		<h1>Контролирай лесно парите си!</h1>
	</div>
	<div class="header_buttons">
		<a href="./logout"><button type="submit">Изход</button></a>
	</div>
	<div class="navigation_buttons">
		<nav> <a href="./home"><button class="n_button"
				type="submit">Моят профил</button></a> <a href="./incomes"><button
				class="n_button" type="submit">Приходи</button></a> <a
			href="/expenses.jsp/">Разходи</a> <a href="/budgets.jsp/">Бюджети</a>
		<a href="/obligations.jsp/">Задължения</a> <a href="/inquiries.jsp/">Справки</a>
		</nav>
	</div>
	</header>

	<div>
		<hr>
		<br />
	</div>

	<section class="">
	<div class="">
		<h1>Приходи</h1>

		<!-- Trigger/Open The Modal -->
		<button id="myBtn">Open Modal</button>

		<!-- The Modal -->
		<div id="myModal" class="modal">

			<!-- Modal content -->
			<div class="modal-content">
				<span class="close">затвори</span>

				<form:form commandName="income">

					<p>
						<form:input id="categoryId" class="input" path="categoryId"
							placeholder="Изберете категория" />
					</p>
					<p>
						<form:input id="amount" class="input" name="amount" path="amount"
							placeholder="Сума" />
					</p>
					<p>
						<form:input id="repeatingId" class="input" name="repeatingId"
							path="repeatingId" placeholder="Повторение" />
					</p>
					<p>
						<%-- <form:input id="date" class="input" name="date" path="date"
							placeholder="Дата" /> --%>
						<form:input id="datepicker" class="input" name="date" path="date"
							placeholder="Дата" />
					</p>
					<p>
						<form:textarea id="description" class="input" name="description"
							path="description" placeholder="Описание" />
					</p>

					<p class="submit">
						<input type="submit" name="commit" value="Добави">
					</p>

				</form:form>

				<%-- <form method="post" action="index.html">
					<p>
						<input type="text" name="login" value=""
							placeholder="Потребителско име или имейл">
					</p>
					<p>
						<input type="password" name="password" value=""
							placeholder="Парола">
					</p>
					<p class="remember_me">
						<label> <input type="checkbox" name="remember_me"
							id="remember_me"> Запомни ме на този компютър
						</label>
					</p>
					<p class="submit">
						<input type="submit" name="commit" value="Вход">
					</p>
				</form> --%>

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