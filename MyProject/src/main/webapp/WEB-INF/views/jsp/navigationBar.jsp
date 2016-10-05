<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false" %>

<div id="navigationBar" class="navigation_buttons">
	<nav>
		<a href="./home"><button class="n_button" type="submit">Моят
				профил</button></a> <a href="./incomes"><button class="n_button"
				type="submit">Приходи</button></a> <a href="/expenses.jsp/">Разходи</a>
		<a href="/budgets.jsp/">Бюджети</a> <a href="/obligations.jsp/">Задължения</a>
		<a href="/inquiries.jsp/">Справки</a>
	</nav>
</div>