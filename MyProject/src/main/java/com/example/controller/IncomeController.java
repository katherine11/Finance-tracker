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

import com.example.model.Income;
import com.example.model.User;
import com.example.model.UserHasIncomesDAO;
import com.example.model.exceptions.PaymentExpeption;

@Controller
@ContextConfiguration(classes = UserHasIncomesDAO.class)
@Scope("session")
public class IncomeController {
	
	
	@Autowired
	private UserHasIncomesDAO userHasIncomes;
	
	@RequestMapping(value="/incomes", method = RequestMethod.POST)
	public String addIncome(@ModelAttribute Income income, Model model, HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		
		if (request.getSession(false) == null || user == null || income == null){
			return "index";
		}
		
//		System.out.println("UserID="+user.getUserId());
//		System.out.println("Income: " + income.getCategoryId());
//		System.out.println("Income: " + income.getDescription());
//		System.out.println("Income: " + income.getAmount());
//		System.out.println("Income: " + income.getRepeatingId());
//		System.out.println("Income: " + income.getDate());

		try {
			model.addAttribute("income", userHasIncomes.insertPayment(user.getUserId(), income));
		} catch (PaymentExpeption e) {
			e.printStackTrace();
			return "error";
		}
		
		return "redirect:/incomes";
	}
	
	
	@RequestMapping(value="/incomes", method = RequestMethod.DELETE)
	protected void deleteIncome(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		try {
			userHasIncomes.deletePayment(id);
		} catch (PaymentExpeption e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	

}
