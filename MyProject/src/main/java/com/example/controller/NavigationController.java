package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Budget;
import com.example.model.Expense;
import com.example.model.Income;
import com.example.model.Obligation;
import com.example.model.User;
import com.example.model.UserHasBudgetsDAO;
import com.example.model.UserHasExpensesDAO;
import com.example.model.UserHasIncomesDAO;
import com.example.model.UserHasObligationsDAO;
import com.example.model.exceptions.PaymentExpeption;

@Controller
public class NavigationController {
	
//	@Autowired
//	private UserHasIncomesDAO userHasIncomesDAO;
//	
//	@Autowired
//	private UserHasExpensesDAO userHasExpensesDAO;
//	
//	@Autowired
//	private UserHasObligationsDAO userHasObligationsDAO;
	
	
	@Autowired 
	private UserHasBudgetsDAO userHasBudgetsDAO;
	
	@RequestMapping(value="/incomes", method = RequestMethod.GET)
	public String incomes(Model model, HttpServletRequest request) {
		if (request.getSession(false) == null){
			return "index";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute(new Income());
//		try {
//			userHasIncomesDAO.selectAndAddAllPaymentsOfUser(user);
//			
//		} catch (PaymentExpeption e) {
//			e.printStackTrace();
//			return "error";
//		}
		return "incomes";
	}
	
	@RequestMapping(value="/expenses", method = RequestMethod.GET)
	public String expenses(Model model, HttpServletRequest request) {
		if (request.getSession(false) == null){
			return "index";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute(new Expense());
		
//		try {
//			 userHasExpensesDAO.selectAndAddAllPaymentsOfUser(user);
//		} catch (PaymentExpeption e) {
//			e.printStackTrace();
//			return "error";
//		}
		return "expenses";
	}
	
	@RequestMapping(value="/obligations", method = RequestMethod.GET)
	public String obligations(Model model, HttpServletRequest request) {
		if (request.getSession(false) == null){
			return "index";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute(new Obligation());
		
//		try {
//			userHasObligationsDAO.selectAndAddAllPaymentsOfUser(user);
//		} catch (PaymentExpeption e) {
//			e.printStackTrace();
//			return "error";
//		}
		return "obligations";
	}
	
	@RequestMapping(value="/budgets", method = RequestMethod.GET)
	public String budgets(Model model, HttpServletRequest request){
		if(request.getSession(false) == null){
			return "index";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute(new Budget());
		
		return "budgets";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		if (request.getSession(false) == null){
			return "index";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		
		return "home";
	}
		
	
}
