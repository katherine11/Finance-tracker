<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script>
	$(document).ready(function() {
		$("#datepicker").datepicker();
	});
</script>


<title>Моите приходи</title>

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
		<h1>Контролирай лесно парите си!</h1>
	</div>
	<div class="header_buttons">
		<a href="./logout"><button type="submit">Изход</button></a>
	</div>
	<div class="navigation_buttons">

		<nav> <a href="/inquiries"><button class="n_button" type="submit">Справки</button></a>
		<a href="/home"><button class="n_button" type="submit">Бюджет</button></a>   
		<a href="./budgets"><button class="n_button" type="submit">Задължения</button></a> 
		<a href="./expenses"><button class="n_button" type="submit">Разходи</button></a> 
		<a href="./incomes"><button class="n_button" type="submit">Приходи</button></a> 
		<a href="./home"><button class="n_button" type="submit">Моят профил</button></a> 
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
		
		<c:if test="${ empty user }"> <p>Losha rabota.</p> </c:if> 
		
		<p>
			
		<c:forEach items="${user.incomes}" var="income">
			<c:out value="${income}"></c:out> 
			<br />
		</c:forEach>
			
		</p>

		<button id="myBtn">Добави приход</button>

		<div id="myModal" class="modal">

			<div class="modal-content">
				<span class="close">затвори</span>

				<form:form commandName="income">

					<p>
						<form:label path="categoryId">Изберете категория:</form:label>
						<form:select id="categoryId" class="input" path="categoryId">
							<form:option value="6">Заплата</form:option>

							<form:option value="7">Наем</form:option>

							<form:option value="8">Дарения</form:option>

							<form:option value="9">Друго</form:option>

						</form:select>
					</p>
					<p>
						<form:label path="amount">Въведете сума:</form:label>
						<form:input type="number" min="0.01" step="0.01" value="1.00"
							max="1000000" id="amount" class="input" name="amount"
							path="amount" placeholder="Сума" required="required" />
					</p>
					<p>
						<form:label path="repeatingId">Изберете повторение:</form:label>
						<form:select id="repeatingId" class="input" name="repeatingId"
							path="repeatingId">

							<form:option value="1">Еднократно</form:option>
							<form:option value="2">Дневно</form:option>
							<form:option value="3">Седмично</form:option>
							<form:option value="4">Месечно</form:option>
							<form:option value="5">Годишно</form:option>

						</form:select>

					</p>
					<p>
						<form:label path="date">Изберете дата:</form:label>
						<form:input id="datepicker" class="input" name="date" path="date"
							placeholder="Дата" required="required" />
					</p>
					<p>
						<form:label path="description">Въведете описание:</form:label>
						<form:textarea id="description" class="input" name="description"
							path="description" placeholder="Описание" required="required" />
					</p>

					<p class="submit">
						<input type="submit" name="commit" value="Добави">
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