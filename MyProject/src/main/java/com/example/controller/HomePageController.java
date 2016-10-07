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

import com.example.model.Expense;
import com.example.model.Income;
import com.example.model.User;
import com.example.model.UserDAO;
import com.example.model.UserHasExpensesDAO;
import com.example.model.UserHasIncomesDAO;
import com.example.model.exceptions.PaymentExpeption;
import com.example.model.exceptions.UserException;

@Controller
@ContextConfiguration(classes = UserDAO.class)
public class HomePageController {
	
	private static final int SESSION_TIME_IN_SECONDS = 60*60;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserHasIncomesDAO userHasIncomesDAO;
	@Autowired
	private UserHasExpensesDAO userHasExpensesDAO;

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String homePage() {
		return "index";
	}	
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		if (!model.containsAttribute("user")){
			model.addAttribute(new User());
		}
		model.addAttribute(new Expense());
		model.addAttribute(new Income());
		return "login";
	}	
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@ModelAttribute User user, Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		try {
			User loggedUser = userDAO.loginUser(user);
			System.out.println(loggedUser.getUsername());
			session.setAttribute("user", loggedUser);
			session.setMaxInactiveInterval(SESSION_TIME_IN_SECONDS);
			model.addAttribute("user", loggedUser);
			userHasIncomesDAO.selectAndAddAllPaymentsOfUser(user);
			userHasExpensesDAO.selectAndAddAllPaymentsOfUser(user);
		} catch (UserException e) {
			model.addAttribute("loginFail", "Невалидно потребителско име или парола");
			return "login";
		} catch (PaymentExpeption e) {
			e.printStackTrace();
			return "error";
		}
		
		return "redirect:/home";
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
			model.addAttribute("registerFail", "Потребителското име е заето!");
			return "register";
		}
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        if (httpSession!=null){
        	httpSession.invalidate();
        }
        return "redirect:/";
    }

	

}
