package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Budget;
import com.example.model.User;
import com.example.model.UserHasBudgetsDAO;
import com.example.model.exceptions.PaymentExpeption;

@Controller
@ContextConfiguration(classes = UserHasBudgetsDAO.class)
@Scope("session")
public class BudgetController {
	
	@Autowired
	private UserHasBudgetsDAO userHasBudgetsDAO;
	
	@RequestMapping(value="/budgets", method = RequestMethod.POST)
	public String addBudget(Budget budget, Model model, HttpServletRequest request) {
		
		if(request.getSession(false) == null){
			return "index";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		
		try {
			model.addAttribute("budget", userHasBudgetsDAO.insertBudget(budget));
			userHasBudgetsDAO.selectAndAddAllBudgetsOfUser(user);
		} catch (PaymentExpeption e) {
			e.printStackTrace();
			return "error";
		}
		
		return "redirect:/budgets";
	}


	

}
