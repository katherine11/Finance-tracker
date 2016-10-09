<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/canvasjs.min.js"></script>
<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
window.onload = function () {
	var incomes = ${user.getTotalIncomes()};
	var expenses = ${user.getTotalExpenses()};
	var balance = ${user.getBalance()};
	
	var chart = new CanvasJS.Chart("chartContainer",
	{
		title:{
			text: "Overview"
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
				{y: incomes, indexLabel: "Total incomes #percent%", legendText: "Total incomes" },
				{y: expenses, indexLabel: "Total expenses #percent%", legendText: "Total expenses" },
				{y: balance,  indexLabel: "Balance #percent%", legendText: "Balance" },
			]
		}
		]
	});
	chart.render();
	}
	</script>
	<script src="../../canvasjs.min.js"></script>
<title>Welcome in TYM!</title>

</head>
<body>
	
	<jsp:include page="home.header.jsp"></jsp:include> 

	<section class="section_home">
	
	<div id="chartContainer" style="height: 400px; width: 100%;"></div>	
	
	<div class="table">
		<h1>Overview</h1>
		
		<p> Total incomes: <c:out value="${user.totalIncomes}"></c:out> </p>
		<p> Total expenses: <c:out value="${user.totalExpenses}"></c:out> </p>
		<p> Balance: <c:out value="${user.balance}"></c:out> </p>

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