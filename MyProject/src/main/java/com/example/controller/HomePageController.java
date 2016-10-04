package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.User;
import com.example.model.UserDAO;
import com.example.model.exceptions.UserException;

@Controller
@ContextConfiguration(classes = UserDAO.class)
public class HomePageController {
	
	@Autowired
	private UserDAO userDAO;

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String homePage() {
		return "index";
	}	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		model.addAttribute(new User());
		return "login";
	}	
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, Model model, HttpServletRequest request){
		HttpSession session = request.getSession();
		try {
			User loggedUser = userDAO.loginUser(user);
			System.out.println(loggedUser.getUsername());
			session.setAttribute("user", loggedUser);
			model.addAttribute("user", loggedUser);
		} catch (UserException e) {
			model.addAttribute("loginFail", "Невалидно потребителско име или парола");
			return "login";
		}
		return "home";
	}	
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerPage(Model model) {
		model.addAttribute(new User());
		return "register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(@ModelAttribute User user, Model model) {
		try {
			model.addAttribute("user", userDAO.registerUser(user));
		} catch (UserException e) {
			model.addAttribute("registerFail", "Невалидни данни за регистрация");
			return "register";
		}
		return "index";
	}
	
	

}
