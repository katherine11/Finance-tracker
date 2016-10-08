package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Income;
import com.example.model.User;
import com.example.model.UserHasIncomesDAO;
import com.example.model.exceptions.PaymentExpeption;

@Controller
@ContextConfiguration(classes = UserHasIncomesDAO.class)
@Scope("session")
public class IncomeController{

	@Autowired
	private UserHasIncomesDAO userHasIncomesDAO;
	
	@RequestMapping(value="/incomes", method = RequestMethod.POST)
	public String addIncome(Income income, Model model, HttpServletRequest request) {
		
		if(request.getSession(false) == null){
			return "index";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		
		try {
			model.addAttribute("income", userHasIncomesDAO.insertPayment(user.getUserId(), income));
			userHasIncomesDAO.selectAndAddAllPaymentsOfUser(user);
		} catch (PaymentExpeption e) {
			e.printStackTrace();
			return "error";
		}
		
		return "redirect:/incomes";
	}

	@RequestMapping(value="/deleteIncome", method = RequestMethod.POST)
	public String deleteIncome(HttpServletRequest req) {
		User user = (User) req.getSession().getAttribute("user");
		String [] ids = req.getParameterValues("id");
		int id;
		for (int index = 0; index < ids.length; index++){
			id = Integer.parseInt(ids[index]);
			try {
				if(userHasIncomesDAO.deletePayment(id)){
					user.removeIncome(id);
				}
			} catch (PaymentExpeption e) {
				e.printStackTrace();
				return "error";
			}
		}
		try {
			userHasIncomesDAO.selectAndAddAllPaymentsOfUser(user);
		} catch (PaymentExpeption e) {
			e.printStackTrace();
			return "error";
		}
		return "redirect:/incomes";
	}
	

}
