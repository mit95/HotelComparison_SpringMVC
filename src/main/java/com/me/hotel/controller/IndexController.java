package com.me.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {
	@RequestMapping("/")
	public ModelAndView viewHome() {
		return new ModelAndView("home");
	}
	

}
