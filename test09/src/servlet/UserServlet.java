package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.dsna.util.images.ValidateCode;
import dao.UserDao;
import entity.User;
import util.CreateMD5;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		if (type == null) {
			showLogin(request, response);
		} else if ("doLogin".equals(type)) {
			doLogin(request, response);
		} else if ("showRegister".equals(type)) {
			showRegister(request, response);
		} else if ("doRegister".equals(type)) {
			doRegister(request, response);
		} else if ("getCode".equals(type)) {
			getCode(request, response);
		}
	}

	public void doLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			// Object code = "";
			// if (session.getAttribute("code") != null) {
			// code=session.getAttribute("code");
			// }
			String random = request.getParameter("random");
			if (random.equals(session.getAttribute("rand"))) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				User user = new User();
				user.setUsername(username);
				user.setPassword(CreateMD5.getMd5(password));
				UserDao ud = new UserDao();
				boolean flag = ud.search(user);
				if (flag) {
					// HttpSession session = request.getSession();
					session.setAttribute("user", username);
					Cookie cookie = new Cookie("username", username);
					cookie.setMaxAge(60);// 60*60*24*365
					response.addCookie(cookie);
					response.sendRedirect("index");
					// request.getRequestDispatcher("WEB-INF/index/index.jsp").forward(request,
					// response);
				} else {
					response.sendRedirect("user");
					// request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request,
					// response);
				}
			} else {
				String mes = "验证码错误";
				request.setAttribute("mes", mes);
				request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doRegister(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			String random = request.getParameter("random");
			if (random.equals(session.getAttribute("rand"))) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				User user = new User();
				user.setUsername(username);
				user.setPassword(CreateMD5.getMd5(password));
				UserDao ud = new UserDao();
				boolean flag = ud.add(user);
				if (flag) {
					session.setAttribute("user", username);
					Cookie cookie = new Cookie("username", username);
					cookie.setMaxAge(60);// 60*60*24*365
					response.addCookie(cookie);
					response.sendRedirect("index");
				} else {
					response.sendRedirect("register");
				}
			} else {
				String mes = "验证码错误";
				request.setAttribute("mes", mes);
				request.getRequestDispatcher("WEB-INF/user/register.jsp").forward(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void showLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = "";
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if ("username".equals(cookies[i].getName())) {
						username = cookies[i].getValue();
					}
				}
			}
			request.setAttribute("username", username);
			request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showRegister(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/user/register.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getCode(HttpServletRequest request, HttpServletResponse response) {
		try {// 获取验证码1.生成验证码的宽度2.高度3.字符个数4.干扰元素的个数
			ValidateCode code = new ValidateCode(100, 34, 4, 20);
			String code2 = code.getCode();
			request.getSession().setAttribute("rand", code2);
			code.write(response.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
