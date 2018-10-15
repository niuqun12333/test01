package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.User;
import service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping("showLogin")
	public String showLogin() {
		return "login";
	}

	@RequestMapping("doLogin")
	public String doLogin(HttpSession session, User user) {
		User us = userService.search(user);
		boolean flag = false;
		if (us != null) {
			flag = true;
		}
		if (flag) {
			session.setAttribute("userBack", us);
			return "redirect:showIndex.do";
		} else {
			return "redirect:showLogin.do";
		}

	}

	@RequestMapping("showUserlist")
	public ModelAndView showUserlist(User user) {
		ModelAndView mv = new ModelAndView("user_list");
		List<User> list = userService.searchAllUesr(user);
		mv.addObject("userList", list);
		return mv;
	}

	@RequestMapping("memberShow")
	public ModelAndView memberShow(User user) {
		ModelAndView mv = new ModelAndView("member-show");
		List<User> list = userService.searchAllUesr(user);
		mv.addObject("userList", list);
		return mv;
	}

}
