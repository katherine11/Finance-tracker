package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Income;
import com.example.model.Payment;
import com.example.model.User;
import com.example.model.UserHasIncomesDAO;
import com.example.model.exceptions.PaymentExpeption;

@Controller
public class NavigationController {
	
	@Autowired
	private UserHasIncomesDAO userHasIncomes;
	
	@RequestMapping(value="/incomes", method = RequestMethod.GET)
	public String incomes(Model model, HttpServletRequest request) {
		if (request.getSession(false) == null){
			return "index";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute(new Income());
		try {
			userHasIncomes.selectAndAddAllPaymentsOfUser(user);
			for (Payment payment : user.getIncomes()){
				System.out.println(payment);
			}
		} catch (PaymentExpeption e) {
			e.printStackTrace();
			return "error";
		}
		return "incomes";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		if (request.getSession(false) == null){
			return "index";
		}
		return "home";
	}
		
	
}
