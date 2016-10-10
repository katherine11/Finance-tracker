<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp"></jsp:include>


<div class="header_buttons">
	<a href="./login" ><span class="glyphicons glyphicons-lock"></span><button type="submit" class="h_butt">Login </button></a>
	<a href="./register" ><button type="submit" class="h_butt">Register</button></a> <a
		href="http://www.dnes.bg/"><button type="submit" class="h_butt">News</button></a>
</div>

</header>

<div>
	<hr>
	<br />
</div>

<section class="section">
<%-- <div class="test">
		<div class="main_heading">
		<h2>Welcome to our cool site for managing your own money!</h2>
	</div>

	<div class="sub_heading">
		<h3>Here you'll be able to:</h3>
	</div>

	<div>
		<a href="#" class="myButton">Enter your incomes and expenses</a><br />
		<a href="#" class="myButton">See where you give your money for</a><br />
		<a href="#" class="myButton">Set your own budget for each kind of
			expense</a><br />
	</div>
</div> --%>
</section>
<jsp:include page="footer.jsp"></jsp:include>