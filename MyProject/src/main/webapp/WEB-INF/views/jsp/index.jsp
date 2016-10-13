<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp"></jsp:include>

<div class="header_buttons">
<!-- <span class="glyphicons glyphicons-lock"></span> -->
	<a href="./login" ><button type="submit" class="h_butt">Login</button></a>
	<a href="./register" ><button type="submit" class="h_butt">Register</button></a> <a
		href="http://www.dnes.bg/"><button type="submit" class="h_butt">News</button></a>
</div>

</header>

<div>
	<hr>
	<br />
</div>

<section class="section">
<div class="front_page">
		<div class="main_heading">
		<h2>Welcome to our site for easy money management!</h2>
	</div>

	<div class="sub_heading">
		<h3>Here you'll be able to:</h3>
	</div>

	<div class="center_buttons"> 
		<a href="#"><button type="submit" class="center_button" id="open">Enter</button></a>
		<a href="#"><button type="submit" class="center_button" id="open">Track</button></a> 
		<a href="#"><button type="submit" class="center_button" id="open">Allocate</button></a>
	</div>
	
</div>
</section>
<jsp:include page="footer.jsp"></jsp:include>