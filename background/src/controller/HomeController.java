package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.HomeService;

@Controller
public class HomeController {
	@Autowired
	HomeService homeService;
	
	@RequestMapping("showHome")
	public ModelAndView showHome() {
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
	

}
