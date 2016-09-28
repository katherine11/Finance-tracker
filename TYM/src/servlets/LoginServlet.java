package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.UserException;
import trackYourMoney.User;
import trackYourMoney.UserDAO;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final int SESSION_TIME_IN_SECONDS = 60*60;
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User(0, request.getParameter("username"), "", request.getParameter("password"));
		
		try {
			user = new UserDAO().loginUser(user);
			HttpSession session = request.getSession();
			session.setAttribute("user_id", user.getUserId());
			session.setAttribute("username", user.getUsername());
			session.setMaxInactiveInterval(SESSION_TIME_IN_SECONDS);
			response.sendRedirect("http://www.google.com");
		} catch (UserException e) {
			response.sendRedirect("login.html");
		}
	}

}
