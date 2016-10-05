<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp"></jsp:include>

	<div class="header_buttons">
		<a href="./"><button type="submit">Начало</button></a> <a
			href="./register"><button type="submit">Регистрация</button></a> <a
			href="http://www.dnes.bg/"><button type="submit">Новини</button></a>
	</div>

	</header>

	<div>
		<hr>
		<br/>
	</div>

	<section class="section">
	<div class="login_register">

		<c:if test="${not empty loginFail}">
			<p class="invalid_input">
				<c:out value="${loginFail}"></c:out>
			<p>
		</c:if>

		<h1>Вход</h1>
		<form:form commandName="user">

			<p>
				<form:input id="username" class="input" path="username"
					placeholder="Потребителско име" />
			</p>
			<p>
				<form:password id="password" class="input" name="password"
					path="password" placeholder="Парола" />
			</p>

			<p class="submit">
				<input type="submit" name="commit" value="Вход">
			</p>

		</form:form>

		<%-- <form method="post" action="./Login">
			<p>
			
				<input type="text" name="username" value=""
					placeholder="Потребителско име">
			</p>
			<p>
				<input type="password" name="password" value="" placeholder="Парола">
			</p>
			<p class="remember_me">
				<label> <input type="checkbox" name="remember_me"
					id="remember_me"> Запомни ме на този компютър
				</label>
			</p>
			<p class="submit">
				<input type="submit" name="commit" value="Вход">
			</p>
		</form> --%>

		<div class="login-help">
			<p>
				Забравена парола? <a href="./">Кликни тук, за да я възстановиш</a>.
			</p>
		</div>
	</div>

	</section>

	<jsp:include page="footer.jsp"></jsp:include>