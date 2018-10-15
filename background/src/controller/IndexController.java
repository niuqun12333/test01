package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.IndexService;

@Controller
public class IndexController {
	@Autowired
	IndexService indexService;
	
	@RequestMapping("showIndex")
	public ModelAndView showIndex() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	

}
