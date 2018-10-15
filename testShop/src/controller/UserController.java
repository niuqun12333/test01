package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.User;
import service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping("showLogin")
	public String showLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("privatePage", request.getHeader("Referer"));
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
			session.setAttribute("userShop", us);
			Object privatePage = session.getAttribute("privatePage");
			if(privatePage==null) {
				//说明直接登录
				return "redirect:classSearch.do";
			}else {
				return "redirect:"+privatePage.toString();
			}
		} else {
			return "login";
		}

	}

	@RequestMapping("showRegister")
	public String showRegister(User user) {
		return "register";
	}

	@RequestMapping("doRegister")
	public String doRegister(User user) {
		boolean flag = userService.add(user);
		if (flag) {
			return "redirect:classSearch.do";
		} else {
			return "register";
		}
	}
}
