<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp"></jsp:include>

	<div class="header_buttons">
		<a href="./"><button type="submit" class="h_butt">Home</button></a> <a
			href="./login"><button type="submit" class="h_butt">Login</button></a> <a
			href="http://www.dnes.bg/"><button type="submit" class="h_butt">News</button></a>
	</div>

	</header>

	<div>
		<hr>
		<br/>
	</div>

	<section class="section">
	<div class="register">
	
		<c:if test="${not empty registerFail}"> 
			<p class="invalid_input"><c:out value="${registerFail}"></c:out><p>
		</c:if> 
	
		<h1>Register</h1>
		
		<form:form commandName="user">
			
			<p><form:input type="email" id="email" class="input" path="email" placeholder="Email" required="required"/></p>
			<p><form:input id="username" class="input" path="username" placeholder="Username" minlength="4" maxlength="15" required="required"/></p>
			<p><form:password id="password" class="input" path="password" placeholder="Password" minlength="4" maxlength="15" required="required"/></p>
			
			<p class="submit"><input type="submit" name="commit" value="Register"></p>
			
		</form:form>
		
	</div>
	</section>

	<jsp:include page="footer.jsp"></jsp:include>