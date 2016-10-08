package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Expense;
import com.example.model.User;
import com.example.model.UserHasExpensesDAO;
import com.example.model.exceptions.PaymentExpeption;

@Controller
@ContextConfiguration(classes = UserHasExpensesDAO.class)
@Scope("session")
public class ExpenseController {
	
	@Autowired
	private UserHasExpensesDAO userHasExpensesDAO;
	
	@RequestMapping(value="/expenses", method = RequestMethod.POST)
	public String addExpense(@ModelAttribute Expense expense, Model model, HttpServletRequest request){
		if (request.getSession(false) == null){
			return "index";
		}
		
		User user = (User) request.getSession().getAttribute("user");
//		System.out.println("UserID="+user.getUserId());
//		System.out.println("Income: " + income.getCategoryId());
//		System.out.println("Income: " + income.getDescription());
//		System.out.println("Income: " + income.getAmount());
//		System.out.println("Income: " + income.getRepeatingId());
//		System.out.println("Income: " + income.getDate());
		try {
			model.addAttribute("expense", userHasExpensesDAO.insertPayment(user.getUserId(), expense));
			userHasExpensesDAO.selectAndAddAllPaymentsOfUser(user);
		} catch (PaymentExpeption e) {
			e.printStackTrace();
			return "error";
		}
		
		return "redirect:/expenses";
	}
	
	@RequestMapping(value="/deleteExpense", method = RequestMethod.POST)
	public String deleteExpense(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute("user");
		String [] ids = req.getParameterValues("id");
		int id;
		for (int index = 0; index < ids.length; index++){
			id = Integer.parseInt(ids[index]);
			try {
				if(userHasExpensesDAO.deletePayment(id)){
					user.removeExpense(id);
				}
			} catch (PaymentExpeption e) {
				e.printStackTrace();
				return "error";
			}
		}
		try {
			
			userHasExpensesDAO.selectAndAddAllPaymentsOfUser(user);
		} catch (PaymentExpeption e) {
			e.printStackTrace();
			return "error";
		}
			
		return "redirect:/expenses";
	}

}
