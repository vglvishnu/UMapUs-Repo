package com.mysoft.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/UMapUSWork")
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET)
	public String login(ModelMap model)
	{
		model.addAttribute("message","Shinku Tatsumaki");
		
		return "UMapUSWork";
	}
	
	

}
