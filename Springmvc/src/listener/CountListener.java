package listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;

import org.apache.catalina.SessionEvent;
import org.apache.catalina.SessionListener;

import dao.UserDao;
import util.MyWebSocket;

public class CountListener implements HttpSessionListener, ServletContextListener {
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("会话建立");
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
		UserDao userDao = new UserDao();
		int anum = userDao.searchNum();
		if (application.getAttribute("anum") != null) {
			anum = (Integer) application.getAttribute("anum");
		}
		anum++;
		application.setAttribute("anum", anum);
		userDao.updateNum(application);
		
		int num =0;
		if(application.getAttribute("num")!=null) {
			num=(Integer)application.getAttribute("num");
		}
		num++;
		application.setAttribute("num", num);
		MyWebSocket.sendMessageAll(String.valueOf(num));
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("会话销毁");
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
		int num =0;
		if(application.getAttribute("num")!=null) {
			num=(Integer)application.getAttribute("num");
		}
		num--;
		application.setAttribute("num", num);
		MyWebSocket.sendMessageAll(String.valueOf(num));

	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}
}
