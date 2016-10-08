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
		$("#datepickerFrom").datepicker();
		$("#datepickerTo").datepicker();
	});

	$(document).ready(function() {

		$('#main').change(function() {

			if ($(this).is(':checked')) {
				$('input[name="id"]:checkbox').prop('checked', true);

			} else {

				$('input[name="id"]:checkbox').prop('checked', false);
			}
		});

		$('input[name="id"]:checkbox').change(function() {
			var chkLength = $('input[name="id"]:checkbox').length;
			var checkedLen = $('input[name="id"]:checkbox:checked').length;
			if (chkLength == checkedLen) {
				$('#main').prop('checked', true);
			} else {
				$('#main').prop('checked', false);
			}
		});
	});

	/* 
	$('#delete').click(function () {
	    $(".checkbox input:selected").parent().remove();
	}); */
</script>


<title>My expenses</title>

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

.close, .close2 {
	color: #aaaaaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus, .close2:hover, .close2:focus {
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
		<nav> <a href="./inquiries"><button class="n_button"
				type="submit">Inquiries</button></a> <a href="./budgets"><button
				class="n_button" type="submit">Budget</button></a> <a
			href="./obligations"><button class="n_button" type="submit">Obligations</button></a>
		<a href="./expenses"><button class="n_button" type="submit">Expenses</button></a>
		<a href="./incomes"><button class="n_button" type="submit">Incomes</button></a>
		<a href="./home"><button class="n_button" type="submit">My
				profile</button></a> </nav>
	</div>
	</header>

	<div>
		<hr>
		<br />
	</div>

	<section class="">
	<div class="">
		<h1>Expenses</h1>

		<button id="myBtn">Add expense</button>

		<div id="myModal" class="modal">

			<div class="modal-content">
				<span class="close">close</span>

				<form:form commandName="expense">

					<p>
						<form:label path="categoryId">Choose category:</form:label>
						<form:select id="categoryId" class="input" path="categoryId">
							<form:option value="1">Food&Drinks</form:option>

							<form:option value="2">Transport</form:option>

							<form:option value="3">Education</form:option>

							<form:option value="4">Sport</form:option>

							<form:option value="5">Bills</form:option>

							<form:option value="6">Other</form:option>

						</form:select>
					</p>
					<p>
						<form:label path="amount">Enter ammount of money:</form:label>
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
							<form:option value="5">Yearly</form:option>

						</form:select>

					</p>
					<p>
						<form:label path="date">Choose a date:</form:label>
						<form:input id="datepicker" class="input" name="date" path="date"
							placeholder="Date" required="required" />
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

		<button id="myBtn2">Get expenses by</button>

		<div id="myModal2" class="modal">

			<div class="modal-content">
				<span class="close2">close</span>

				<form class="Forms" action="./getExpensesBy" method="get">

					<p>
						<label for="categoryId">Choose category:</label> <select
							id="categoryId" class="input" name="categoryId">

							<option value="0">All categories</option>

							<option value="1">Food&Drinks</option>

							<option value="2">Transport</option>

							<option value="3">Education</option>

							<option value="4">Sport</option>

							<option value="5">Bills</option>

							<option value="6">Other</option>

						</select>
					</p>

					<p>
						<label for="from">From:</label> <input id="datepickerFrom"
							class="input" name="from" placeholder="Date" required="required" />
					</p>

					<p>
						<label for="to">To:</label> <input id="datepickerTo" class="input"
							name="to" placeholder="Date" required="required" />
					</p>

					<p class="submit">
						<input type="submit" name="commit" value="Find">
					</p>

				</form>

			</div>

		</div>
		
				<div class="Tables">
					<table class="table" name="expense_table" cellspacing="0"
						cellpadding="2" width="60%">
						
						<thead>
							<tr style="height: 35px;">
								<th><input name="selectALL" type="checkbox" value=""
									id="main" />&nbsp;Select all<br /></th>
								<th align="left">Category</th>
								<th align="right">Amount</th>
								<th>Date</th>
								<th align="left">Description</th>
							</tr>
						</thead>
						<br />
						<tbody>
		<c:choose>
			<c:when test="${empty expenses }">
						<caption>
							<h2>All expenses</h2>
						</caption>
							<p>
								<form:form action="./deleteExpense">

									<c:forEach items="${user.expenses}" var="expense">
										<tr>
											<td align="center"><input type="checkbox" name="id"
												id="${expense.id}" value="${expense.id}" /></td>
											<td align="left"><c:out value="${expense.category}"></c:out>
											</td>
											<td align="right"><c:out value="${expense.amount}"></c:out>&nbsp;$
											</td>
											<td align="center"><c:out value="${expense.date}"></c:out>
											</td>
											<td align="left">(<c:out value="${expense.description}"></c:out>)
											</td>
										</tr>
									</c:forEach>
									<input type="submit" id="delete" name="commit"
										value="Delete selected">
								</form:form>
							</p>
						</tbody>
						<tfoot>
							<tr>
								<td align="right" colspan="2" style="padding-top: 14px";><strong>Total
										amount:</strong></td>
								<td align="right" style="padding-top: 14px"><strong><c:out
											value="${user.totalExpenses}"></strong> </c:out>&nbsp;$</td>

							</tr>
						</tfoot>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${expenses}" var="expense">
					<tr>
						<td align="center"><input type="checkbox" name="id"
							id="${expense.id}" value="${expense.id}" /></td>
						<td align="left"><c:out value="${expense.category}"></c:out>
						</td>
						<td align="right"><c:out value="${expense.amount}"></c:out>&nbsp;$
						</td>
						<td align="center"><c:out value="${expense.date}"></c:out></td>
						<td align="left">(<c:out value="${expense.description}"></c:out>)
						</td>
					</tr>
				</c:forEach>
					</tbody>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
		<div></div>

		<script>
			// Get the modal
			var modal = document.getElementById('myModal');
			var modal2 = document.getElementById('myModal2');
			// Get the button that opens the modal
			var btn = document.getElementById("myBtn");
			var btn2 = document.getElementById("myBtn2");

			// Get the <span> element that closes the modal
			var span = document.getElementsByClassName("close")[0];
			var span2 = document.getElementsByClassName("close2")[0];

			// When the user clicks the button, open the modal
			btn.onclick = function() {
				modal.style.display = "block";
			}
			btn2.onclick = function() {
				modal2.style.display = "block";
			}

			// When the user clicks on <span> (x), close the modal
			span.onclick = function() {
				modal.style.display = "none";
			}
			span2.onclick = function() {
				modal2.style.display = "none";
			}

			// When the user clicks anywhere outside of the modal, close it
			window.onclick = function(event) {
				if (event.target == modal) {
					modal.style.display = "none";
				}
				if (event.target == modal2) {
					modal2.style.display = "none";
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