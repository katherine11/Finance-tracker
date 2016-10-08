package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Payment;
import com.example.model.User;
import com.example.model.UserHasExpensesDAO;
import com.example.model.exceptions.PaymentExpeption;

@ContextConfiguration(classes = UserHasExpensesDAO.class)
public class ExpenseController extends PaymentController{

	@Override
	@RequestMapping(value="/expenses", method = RequestMethod.POST)
	public String addPayment(Payment payment, Model model, HttpServletRequest request) {
		if(request.getSession(false) == null){
			return "index";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		
		try {
			addPayments(payment, model, user);
		} catch (PaymentExpeption e) {
			e.printStackTrace();
			return "error";
		}
		
		return "redirect:/expenses";
	}

	@Override
	public String deletePayment(HttpServletRequest request) {
		return "";
	}
	
	
	

//	@RequestMapping(value="/deleteExpense", method = RequestMethod.POST)
//	public String deleteExpense(HttpServletRequest req) {
//		User user = (User) req.getSession().getAttribute("user");
//		String [] ids = req.getParameterValues("id");
//		int id;
//		for (int index = 0; index < ids.length; index++){
//			id = Integer.parseInt(ids[index]);
//			try {
//				if(userHasExpensesDAO.deletePayment(id)){
//					user.removeExpense(id);
//				}
//			} catch (PaymentExpeption e) {
//				e.printStackTrace();
//				return "error";
//			}
//		}
//		try {
//			
//			userHasExpensesDAO.selectAndAddAllPaymentsOfUser(user);
//		} catch (PaymentExpeption e) {
//			e.printStackTrace();
//			return "error";
//		}
//			
//		return "redirect:/expenses";
//	}
//	
	
	
	

}
