package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.model.Payment;
import com.example.model.User;
import com.example.model.UserHasDAO;
import com.example.model.exceptions.PaymentExpeption;

@Controller
@Scope("session")
public abstract class PaymentController implements IPayment {

	@Autowired
	protected UserHasDAO userHasDAO;

	protected String mapping;

	protected void addPayments(Payment payment, Model model, User user) throws PaymentExpeption {
		model.addAttribute("expense", userHasDAO.insertPayment(user.getUserId(), payment));
		userHasDAO.selectAndAddAllPaymentsOfUser(user);
	}

//	protected void deleteUserPayments(HttpServletRequest request, User user) throws PaymentExpeption {
//		String [] ids = request.getParameterValues("id");
//		for (int index = 0; index < ids.length; index++){
//		int id = Integer.parseInt(ids[index]);
//		
//			if(userHasDAO.deletePayment(id)){
		//must be changed to removePayment
//				user.removeIncome(id);
//			}
//		
//		}
//	}
	
	
	
	
}
