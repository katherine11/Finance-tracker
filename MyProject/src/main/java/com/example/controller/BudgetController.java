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
import com.example.model.exceptions.PaymentException;

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
			model.addAttribute("budget", userHasBudgetsDAO.insertBudget(user.getUserId(), budget));
			userHasBudgetsDAO.selectAndAddAllBudgetsOfUser(user);
		} catch (PaymentException e) {
			e.printStackTrace();
			return "error";
		}
		
		return "redirect:/budgets";
	}
	
	@RequestMapping(value="/deleteBudget", method = RequestMethod.POST)
	public String deleteBudget(HttpServletRequest req, Model model) {
		User user = (User) req.getSession().getAttribute("user");
		String [] ids = req.getParameterValues("expenseId");
		int expenseId;
		for (int index = 0; index < ids.length; index++){
			expenseId = Integer.parseInt(ids[index]);
			try {
				if(userHasBudgetsDAO.deleteBudget(user.getUserId(), expenseId)){
					user.removeBudget(expenseId);
				}
			} catch (PaymentException e) {
				model.addAttribute("insertFail", "Already exist budget for this category");
				return "budgets";
			}
		}
		try {			
			userHasBudgetsDAO.selectAndAddAllBudgetsOfUser(user);
		} catch (PaymentException e) {
			e.printStackTrace();
			return "error";
		}
			
		return "redirect:/budgets";
	}

	

}
