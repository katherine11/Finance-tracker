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
<script type="text/javascript" src="js/jquery.canvasjs.min.js"></script>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
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
	
</script>
<script type="text/javascript">
window.onload = function () {
	var foodAndDrinks = ${user.getAmoutByExpenseCategoryId(1)};
	var transport = ${user.getAmoutByExpenseCategoryId(2)};
	var education = ${user.getAmoutByExpenseCategoryId(3)};
	var sport = ${user.getAmoutByExpenseCategoryId(4)};
	var bills = ${user.getAmoutByExpenseCategoryId(5)};
	var other = ${user.getAmoutByExpenseCategoryId(6)};
	
	var chart = new CanvasJS.Chart("chartContainer",
	{
		title:{
			text: "Percentage expenses by  type"
		},
                animationEnabled: true,
		data: [
		{
			type: "doughnut",
			startAngle: 60,
			toolTipContent: "{legendText}: {y} - <strong>#percent% </strong>",
			showInLegend: true,
          explodeOnClick: true, 
			dataPoints: [
				{y: foodAndDrinks, indexLabel: "Food&Drinks #percent%", legendText: "Food&Drinks" },
				{y: transport, indexLabel: "Transport #percent%", legendText: "Transport" },
				{y: education,  indexLabel: "Education #percent%", legendText: "Education" },
				{y: sport, indexLabel: "Sport #percent%", legendText: "Sport" },
				{y: bills, indexLabel: "Bills #percent%", legendText: "Bills" },
				{y: other,  indexLabel: "Other #percent%", legendText: "Other" }
			]
		}
		]
	});
	chart.render();
	}
	</script>
	<script src="../../canvasjs.min.js"></script>

<title>My expenses</title>

</head>
<body>
	
	<jsp:include page="home.header.jsp"></jsp:include> 

	<section class="section_home">
	
	<div id="chartContainer" style="height: 400px; width: 100%;"></div>	
	
	<div class="">
		<!-- <h1>Expenses</h1> -->

		<button id="myBtn">Add expense</button>

		<div id="myModal" class="modal">

			<div class="modal-content">
				<span class="close">close</span>

				<form:form commandName="expense" action="./expenses">

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
						<form:label path="amount">Enter amount of money:</form:label>
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
						cellpadding="2" width="100%">
						
				<thead>
					<tr style="height: 35px;">
						<th><input name="selectALL" type="checkbox" value=""
							id="main" />&nbsp;Select all<br /></th>
						<th align="left">Category</th>
						<th align="right">Amount</th>
						<th>Repeat</th>
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
											<td align="center"><c:out value="${expense.repeating}"></c:out></td>
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
								<td align="right" colspan="2" style="padding-top: 14px"><strong>Total
										amount:</strong></td>
								<td align="right" style="padding-top: 14px"><strong>
								<c:out value="${user.getTotalExpenses(user.expenses)}"></c:out> &nbsp;$ </strong> </td>

							</tr>
						</tfoot>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<caption>
					<h2>Result</h2>
				</caption>
				
				<c:forEach items="${expenses}" var="expense">
					<tr>
						<td align="center"><input type="checkbox" name="id"
							id="${expense.id}" value="${expense.id}" /></td>
						<td align="left"><c:out value="${expense.category}"></c:out>
						</td>
						<td align="right"><c:out value="${expense.amount}"></c:out>&nbsp;$
						</td>
						<td align="center"><c:out value="${expense.repeating}"></c:out></td>
						<td align="center"><c:out value="${expense.date}"></c:out></td>
						<td align="left">(<c:out value="${expense.description}"></c:out>)
						</td>
					</tr>
				</c:forEach>
					</tbody>
					<tfoot>
							<tr>
								<td align="right" colspan="2" style="padding-top: 14px"><strong>Total
										amount:</strong></td>
								<td align="right" style="padding-top: 14px"><strong>
								<c:out value="${totalAmount}"></c:out> &nbsp;$ </strong> </td>

							</tr>
						</tfoot>
					</table>
				</div>
			</c:otherwise>
		</c:choose>

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