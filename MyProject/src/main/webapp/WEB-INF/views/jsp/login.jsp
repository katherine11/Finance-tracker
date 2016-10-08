<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp"></jsp:include>

	<div class="header_buttons">
		<a href="./"><button type="submit">Home</button></a> <a
			href="./register"><button type="submit">Register</button></a> <a
			href="http://www.dnes.bg/"><button type="submit">News</button></a>
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

		<h1>Login</h1>
		<form:form commandName="user">

			<p>
				<form:input id="username" class="input" path="username"
					placeholder="Username" minlength="4" maxlength="15" required="required"/>
			</p>
			<p>
				<form:password id="password" class="input" name="password"
					path="password" placeholder="Password" minlength="4" maxlength="15" required="required"/>
			</p>

			<p>
				<input type="checkbox" name="commit">
				<label>Remember me</label>
			</p>
			
			<p class="submit">
				<input type="submit" name="commit" value="Login">
			</p>

		</form:form>

		<div class="login-help">
			<p>
				Password forgotten? <a href="./forgottenPassword">Click here to reset it</a>.
			</p>
		</div>
	</div>

	</section>

	<jsp:include page="footer.jsp"></jsp:include>