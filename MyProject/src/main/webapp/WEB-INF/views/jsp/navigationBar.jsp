<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false" %>

<div id="navigationBar" class="navigation_buttons">
	<nav>
		<a href="/inquiries"><button class="n_button" type="submit">Справки</button></a> 
		<a href="/budgets"><button class="n_button" type="submit">Задължения</button></a> 
		<a href="/expenses"><button class="n_button" type="submit">Разходи</button></a> 
		<a href="/incomes"><button class="n_button" type="submit">Приходи</button></a> 
		<a href="/home"><button class="n_button" type="submit">Моят
				профил</button></a> 
	</nav>
</div>