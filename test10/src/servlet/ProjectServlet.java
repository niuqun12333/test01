package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.media.sound.RealTimeSequencerProvider;

import dao.ProjectDao;
import entity.Project;
import net.sf.json.JSONArray;
import util.Constant;
import util.Pagination;

public class ProjectServlet extends HttpServlet {
	private static final String path="WEB-INF/project/";
	//ProjectDao proDao = new ProjectDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);
			} else if ("add".equals(type)) {
				add(request, response);
			} else if ("showAdd".equals(type)) {
				showAdd(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			} else if ("showUpdate".equals(type)) {
				showUpdate(request, response);
			} else if ("update".equals(type)) {
				update(request, response);
			} 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			Project condition = new Project();
			condition.setName(name);
			ProjectDao proDao = new ProjectDao();
			int count = proDao.searchCount(condition);
			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
			List<Project> list = proDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
			request.setAttribute("list", list);
			request.setAttribute("p", p);
			request.setAttribute("c", condition);
			request.getRequestDispatcher(path+"list.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public void show(HttpServletRequest request, HttpServletResponse response) {
	// try {
	// int ye = 1;
	// if (request.getParameter("ye") != null) {
	// ye = Integer.parseInt(request.getParameter("ye"));
	// }
	// int count = proDao.searchCount();
	// Pagination p = new Pagination(ye, count, Constant.pro_NUM_IN_PAGE,
	// Constant.pro_NUM_OF_PAGE);
	// List<Project> list = new ArrayList<>();
	// list = proDao.search(p.getBegin(), Constant.pro_NUM_IN_PAGE);
	// request.setAttribute("p", p);
	// request.setAttribute("list", list);
	// request.getRequestDispatcher("WEB-INF/list.jsp").forward(request, response);
	// } catch (ServletException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			Project pro = new Project();
			pro.setName(name);
			ProjectDao proDao = new ProjectDao();
			boolean flag = proDao.add(pro);
			if (flag) {
				response.sendRedirect("pro");
			} else {
				request.getRequestDispatcher(path+"fail.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher(path+"add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			int ids = Integer.parseInt(request.getParameter("id"));
			// String ids = request.getParameter("ids");
			ProjectDao proDao = new ProjectDao();
			boolean flag = proDao.delete(ids);
			if (flag) {
				response.sendRedirect("pro");
			} else {
				request.getRequestDispatcher(path+"fail.jsp").forward(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			ProjectDao proDao = new ProjectDao();
			Project pro = proDao.searchId(id);
			request.setAttribute("pro", pro);
			request.getRequestDispatcher(path+"update.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void update(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			Project pro = new Project();
			pro.setId(id);
			pro.setName(name);
			ProjectDao proDao = new ProjectDao();
			boolean flag = proDao.update(pro);
			if (flag) {
				response.sendRedirect("pro");
			} else {
				request.getRequestDispatcher(path+"fail.jsp").forward(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
