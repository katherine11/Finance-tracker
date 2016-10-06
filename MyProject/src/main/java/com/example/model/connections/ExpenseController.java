package com.example.model.connections;

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
import com.example.model.UserHasIncomesDAO;
import com.example.model.exceptions.PaymentExpeption;

@Controller
@ContextConfiguration(classes = UserHasIncomesDAO.class)
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
		} catch (PaymentExpeption e) {
			e.printStackTrace();
		}
		return "home";
	}

}
