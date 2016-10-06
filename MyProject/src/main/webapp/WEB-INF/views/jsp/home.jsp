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
<title>Welcome in TYM!</title>


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

	<div id="navigationBar" class="navigation_buttons">
		<nav><a href="/inquiries"><button class="n_button" type="submit">Inquiries</button></a>
		<a href="budgets"><button class="n_button" type="submit">Budget</button></a> 
		<a href="./budgets"><button class="n_button" type="submit">Obligations</button></a> 
		<a href="./expenses"><button class="n_button" type="submit">Expenses</button></a> 
		<a href="./incomes"><button class="n_button" type="submit">Incomes</button></a> 
		<a href="./home"><button class="n_button" type="submit">My profile</button></a> 
		</nav>
	</div>

	</header>

	<div>
		<hr>
		<br />
	</div>

	<section class="">
	<div class="">
		<h1>My profile</h1>

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