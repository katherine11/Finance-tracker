package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Income;

@Controller
public class NavigationController {
	
	
	@RequestMapping(value="/incomes", method = RequestMethod.GET)
	public String incomes(Model model, HttpServletRequest request) {
		if (request.getSession(false) == null){
			return "index";
		}
		model.addAttribute(new Income());
		return "incomes";
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		if (request.getSession(false) == null){
			return "index";
		}
		return "home";
	}
		
	
}
