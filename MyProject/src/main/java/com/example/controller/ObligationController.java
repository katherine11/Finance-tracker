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

import com.example.model.Obligation;
import com.example.model.User;
import com.example.model.UserHasObligationsDAO;
import com.example.model.exceptions.PaymentExpeption;

@Controller 
@ContextConfiguration(classes = UserHasObligationsDAO.class)
@Scope("session")
public class ObligationController {
	
	@Autowired
	private UserHasObligationsDAO userHasObligationsDAO;
	
	@RequestMapping(value="obligations",method = RequestMethod.POST)
	public String addObligation(@ModelAttribute Obligation obligation, Model model, HttpServletRequest request){
		
		User user = (User) request.getSession().getAttribute("user");
		
		if (request.getSession(false) == null || user == null || obligation == null){
			return "index";
		}

		try {
			model.addAttribute("obligation", userHasObligationsDAO.insertPayment(user.getUserId(), obligation));
			userHasObligationsDAO.selectAndAddAllPaymentsOfUser(user);
		} catch (PaymentExpeption e) {
			e.printStackTrace();
			return "error";
		}
		
		return "redirect:/obligations";
	}
	
	
	

}
