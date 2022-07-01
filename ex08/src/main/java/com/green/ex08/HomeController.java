package com.green.ex08;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@RequestMapping("/hello")
	public ModelAndView hello(@RequestParam(value="name", required=false)String name) {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("hello");
		mav.addObject("greeting", name+"님 안녕하세요!");
		
		
		return mav;
	}
}
