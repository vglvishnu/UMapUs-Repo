package com.mysoft.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/UMapUSWork")
public class AfterLoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String afterLogin(ModelMap model)
	{
		model.addAttribute("message","Shinku Tatsumaki");
		
		return "UMapUSWork";
	}
	
	

}
