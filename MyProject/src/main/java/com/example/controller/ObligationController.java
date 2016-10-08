package com.example.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Payment;
import com.example.model.User;
import com.example.model.UserHasObligationsDAO;
import com.example.model.exceptions.PaymentExpeption;

@ContextConfiguration(classes = UserHasObligationsDAO.class)
public class ObligationController extends PaymentController{

	@Override
	@RequestMapping(value="/obligations", method = RequestMethod.POST)
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
		
		return "redirect:/obligations";
	}

	@Override
	public String deletePayment(HttpServletRequest request) {
		return "";
	}
	

	
	
	

}
