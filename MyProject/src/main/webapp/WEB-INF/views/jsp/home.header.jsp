<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false"%>

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
		<!-- <h1>Get a full control over your money!</h1> -->
	</div>
	<div class="header_buttons">
		<a href="./logout"><button type="submit" class="h_butt">Log out</button></a>
	</div>
	<div class="navigation_buttons">
		<nav>
			<!-- <a href="./inquiries"><button class="n_button" type="submit">Inquiries</button></a> -->
			<a href="./budgets"><button class="n_button" type="submit">Budgets</button></a>
			<a href="./obligations"><button class="n_button" type="submit">Obligations</button></a>
			<a href="./expenses"><button class="n_button" type="submit">Expenses</button></a>
			<a href="./incomes"><button class="n_button" type="submit">Incomes</button></a>
			<a href="./home"><button class="n_button" type="submit">Home</button></a>
		</nav>
	</div>
</header>

<div>
	<hr>
	<br />
</div>
