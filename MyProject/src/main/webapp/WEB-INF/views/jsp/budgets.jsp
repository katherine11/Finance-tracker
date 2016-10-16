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
<script type="text/javascript">
	$(document).ready(function() {
		$("#datepicker").datepicker();
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
			var array = [];
			var budgets = ${user.budgetsJson};
			for (index in budgets) {
				var budget = budgets[index];
				var expenseId = budget.expenseId;
				var remain = budget.remaindAmount; 
				array.push({y: budget.amount, label: budget.expense });
				array.push({y: remain, label: "Remain" });
			}
			var chart = new CanvasJS.Chart("chartContainer", {
				title: {
					text: "Budget amount and remain amount"
				},
				animationEnabled : true,
				data: [{
					type: "column",
					dataPoints: array
				}]
			});
			chart.render();
		}
	</script>
<title>My budgets</title>

</head>
<body>

	<jsp:include page="home.header.jsp"></jsp:include>

	<section class="">
	
	<c:choose>
		<c:when test="${not empty user.budgets}">
	<div id="chartContainer" style="height: 400px; width: 97%; margin-left: 20px;"></div>
	
	<div class="">
		
		
		
		<button id="myBtn">Add budget</button>
		
		
		<c:if test="${ not empty insertFail }">
			<div>
				<p class="invalid_input">
					<c:out value="${insertFail}"></c:out>
				<p>
			</div>
		</c:if>
		
		
		<div class="Tables">
			<table class="table" name="budget_table" cellspacing="0"
				cellpadding="2" width="100%" border="1">
				<thead>
					<tr style="height: 35px;">
						<th><input name="selectALL" type="checkbox" value=""
							id="main" />&nbsp;Select all<br /></th>
						<th align="left">Category</th>
						<th align="right">Amount</th>
						<th align="right">Remain</th>
						<th>Repeating</th>
						<th align="left">Description</th>
					</tr>
				</thead>
				<tbody>
				<caption>
					<h2>Budgets</h2>
				</caption>
		<p>
			<form:form action="./deleteBudget">
				
				<c:forEach items="${user.budgets}" var="budget">
					
					<tr>
						<td align="center"><input type="checkbox" name="id"
									id="${budget.expenseId}" value="${budget.expenseId}" /></td>
						<td align="left"><c:out value="${budget.expense}"></c:out></td>
						<td align="right"><c:out value="${budget.amount}"></c:out>&nbsp;$</td>
						<td align="right" id="remainAmount"><c:out value="${user.getRemainAmountForBudget(budget.expenseId)}"></c:out>&nbsp;$</td>
						<td align="center"><c:out value="${budget.repeating}"></c:out></td>
						<td align="left">(<c:out value="${budget.description}"></c:out>)
								</td>
					</tr>
				</c:forEach>
				<input type="submit" id="delete" name="commit" value="Delete selected">
			</form:form>
		</p>

		</tbody>
				<%-- <tfoot>
					<tr>
						<td align="right" colspan="2" style="padding-top: 14px"><strong>Total
								amount:</strong></td>
						<td align="right" style="padding-top: 14px"><strong>
								<c:out value="${user.totalBudgets}"></c:out> &nbsp;$
						</strong></td>

					</tr>
				</tfoot> --%>
			</table>
		</div>
		</div>
		</c:when>
		<c:otherwise>
			<div id="welcome_text">
			<br>
			<h1><c:out value="${user.username}"></c:out>, here you can add budgets <br> </h1>
			<button id="myBtn" style="float: inherit; margin-left: 600px;">Add budget</button>
			</div>
		</c:otherwise>
		</c:choose>
		<div id="myModal" class="modal">

			<div class="modal-content">
				<span class="close">close</span>

				<form:form commandName="budget" action="./budgets" method="POST">

				<p>
						<form:label path="expenseId">Category:</form:label>
						<form:select id="expenseId" class="input" path="expenseId">
							<form:option value="1">Food&Drinks</form:option>

							<form:option value="2">Transport</form:option>

							<form:option value="3">Education</form:option>

							<form:option value="4">Sport</form:option>

							<form:option value="5">Bills</form:option>

							<form:option value="6">Other</form:option>

						</form:select>
					</p>
					
					<p>
						<form:label path="amount">Sum:</form:label>
						<form:input type="number" min="0.01" step="0.01" value="1.00"
							max="1000000" id="amount" class="input" name="amount"
							path="amount" placeholder="Money" required="required" />
					</p>
					<p>
						<form:label path="repeatingId">Repeating:</form:label>
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
						<form:label path="date">Date:</form:label>
						<form:input id="datepicker" class="input" name="date" path="date"
							placeholder="Date" required="required" />
					</p>
					<p>
						<form:label path="description">Description:</form:label>
						<form:textarea id="description" class="input" name="description"
							path="description" placeholder="Description" required="required" />
					</p>

					<p class="submit">
						<input type="submit" name="commit" value="Add">
					</p>

				</form:form>

			</div>

		</div>
		
		<script type="text/javascript" src="<c:url value='js/modal.js'/>"></script>

	</section>

	<jsp:include page="footer.jsp"></jsp:include>