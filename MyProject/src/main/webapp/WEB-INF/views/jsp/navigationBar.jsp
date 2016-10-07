<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false" %>

<div id="navigationBar" class="navigation_buttons">
	<nav>
		<a href="./inquiries"><button class="n_button" type="submit">Inquiries</button></a>
		<a href="./budgets"><button class="n_button" type="submit">Budget</button></a>  
		<a href="./obligations"><button class="n_button" type="submit">Obligations</button></a> 
		<a href="./expenses"><button class="n_button" type="submit">Expenses</button></a> 
		<a href="./incomes"><button class="n_button" type="submit">Incomes</button></a> 
		<a href="./home"><button class="n_button" type="submit">My profile</button></a> 
	</nav>
</div>