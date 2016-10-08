package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Payment;
import com.example.model.User;
import com.example.model.UserHasIncomesDAO;
import com.example.model.exceptions.PaymentExpeption;

@ContextConfiguration(classes = UserHasIncomesDAO.class)
public class IncomeController extends PaymentController{

	@Override
	
	@RequestMapping(value="/incomes", method = RequestMethod.POST)
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
		
		return "redirect:/incomes";
	}
	
	@Override
	@RequestMapping(value="/deleteIncome", method = RequestMethod.POST)
	public String deletePayment(HttpServletRequest request) {
		
		return "";
		
	}

	@RequestMapping(value="/deleteIncome", method = RequestMethod.POST)
	public String deleteIncome(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute("user");
		String [] ids = req.getParameterValues("id");
		int id;
		for (int index = 0; index < ids.length; index++){
			id = Integer.parseInt(ids[index]);
			try {
				if(userHasDAO.deletePayment(id)){
					user.removeIncome(id);
				}
			} catch (PaymentExpeption e) {
				e.printStackTrace();
				return "error";
			}
		}
		try {
			userHasDAO.selectAndAddAllPaymentsOfUser(user);
		} catch (PaymentExpeption e) {
			e.printStackTrace();
			return "error";
		}
		return "redirect:/incomes";
	}
	

}
