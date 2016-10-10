package com.example.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Expense;
import com.example.model.Payment;
import com.example.model.User;
import com.example.model.UserHasExpensesDAO;
import com.example.model.exceptions.PaymentException;

@Controller
@ContextConfiguration(classes = UserHasExpensesDAO.class)
@Scope("session")
public class ExpenseController{

	@Autowired
	private UserHasExpensesDAO userHasExpensesDAO;
	
	@RequestMapping(value="/expenses", method = RequestMethod.POST)
	public String addExpense(Expense expense, Model model, HttpServletRequest request) {
		
		if(request.getSession(false) == null){
			return "index";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		
		try {
			model.addAttribute("expense", userHasExpensesDAO.insertPayment(user.getUserId(), expense));
			userHasExpensesDAO.selectAndAddAllPaymentsOfUser(user);
			
		} catch (PaymentException e) {
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
			} catch (PaymentException e) {
				e.printStackTrace();
				return "error";
			}
		}
		try {
			
			userHasExpensesDAO.selectAndAddAllPaymentsOfUser(user);
		} catch (PaymentException e) {
			e.printStackTrace();
			return "error";
		}
			
		return "redirect:/expenses";
	}
	
	@RequestMapping(value="/getExpensesBy", method = RequestMethod.GET)
	public String getExpenses(Expense expense, Model model, HttpServletRequest request) {
		if(request.getSession(false) == null){
			return "index";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		String id = request.getParameter("categoryId");
		int categoryId = Integer.parseInt(id);
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		
		Set<Payment> expenses = user.getExpensesBy(from, to, categoryId);
		double totalAmount = 0;
		for (Payment expense2 : expenses){
			totalAmount += expense2.getAmount();
		}
		model.addAttribute("expenses", expenses);
		model.addAttribute("totalAmount", totalAmount);
		model.addAttribute("user", user);
		model.addAttribute(new Expense());
		
		return "expenses";
	}
	

}
