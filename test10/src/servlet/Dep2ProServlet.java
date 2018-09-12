package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;
import org.apache.jasper.tagplugins.jstl.core.Out;

import com.sun.java.swing.plaf.windows.resources.windows;

import dao.DepartmentDao;
import dao.Project2DpatmentDao;
import entity.Dep2Pro;
import entity.Department;
import entity.Employee;
import entity.Project;
import net.sf.json.JSONArray;
import util.Constant;
import util.Pagination;

public class Dep2ProServlet extends HttpServlet {
	private static final String path = "WEB-INF/dep2pro/";
	//DepartmentDao depDao = new DepartmentDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);
			} else if ("add".equals(type)) {
				add(request, response);
			} else if ("delete".equals(type)) {
				delete(request, response);
			} else if ("m2".equals(type)) {
				search2(request, response);
			} else if ("add2".equals(type)) {
				add2(request, response);
			} else if ("delete2".equals(type)) {
				delete2(request, response);
			} else if ("m3".equals(type)) {
				search3(request, response);
			} else if ("addBatch".equals(type)) {
				addBatch(request, response);
			} else if ("deleteBatch".equals(type)) {
				deleteBatch(request, response);
			} else if ("m4".equals(type)) {
				search4(request, response);
			} else if ("m5".equals(type)) {
				search5(request, response);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depid = Integer.parseInt(request.getParameter("id"));
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			List<Project> list = pro2depDao.searchByDepartment(depid);
			List<Project> notProList = pro2depDao.searchByNotDepartment(depid);
			DepartmentDao depDao = new DepartmentDao();
			Department dep = depDao.searchId(depid);
			request.setAttribute("list", list);
			request.setAttribute("notProList", notProList);
			request.setAttribute("dId", depid);
			request.setAttribute("dep", dep);
			request.getRequestDispatcher(path + "list.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void search2(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depid = Integer.parseInt(request.getParameter("id"));
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			List<Project> list = pro2depDao.searchByDepartment(depid);
			List<Project> notProList = pro2depDao.searchByNotDepartment(depid);
			DepartmentDao depDao = new DepartmentDao();
			Department dep = depDao.searchId(depid);
			request.setAttribute("list", list);
			request.setAttribute("notProList", notProList);
			request.setAttribute("dId", depid);
			request.setAttribute("dep", dep);
			request.getRequestDispatcher(path + "list2.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void search3(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depid = Integer.parseInt(request.getParameter("id"));
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			List<Project> list = pro2depDao.searchByDepartment(depid);
			List<Project> notProList = pro2depDao.searchByNotDepartment(depid);
			DepartmentDao depDao = new DepartmentDao();
			Department dep = depDao.searchId(depid);
			request.setAttribute("list", list);
			request.setAttribute("noList", notProList);
			request.setAttribute("dId", depid);
			request.setAttribute("dep", dep);
			request.getRequestDispatcher(path + "list3.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void search4(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depid = Integer.parseInt(request.getParameter("id"));
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			List<Project> list = pro2depDao.searchByDepartment(depid);
			List<Project> notProList = pro2depDao.searchByNotDepartment(depid);
			DepartmentDao depDao = new DepartmentDao();
			Department dep = depDao.searchId(depid);
			request.setAttribute("list", list);
			request.setAttribute("noList", notProList);
			request.setAttribute("dId", depid);
			request.setAttribute("dep", dep);
			request.getRequestDispatcher(path + "list4.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void search5(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depid = Integer.parseInt(request.getParameter("id"));
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			List<Project> list = pro2depDao.searchByDepartment(depid);
			List<Project> notProList = pro2depDao.searchByNotDepartment(depid);
			DepartmentDao depDao = new DepartmentDao();
			Department dep = depDao.searchId(depid);
			request.setAttribute("list", list);
			request.setAttribute("noList", notProList);
			request.setAttribute("dId", depid);
			request.setAttribute("dep", dep);
			request.getRequestDispatcher(path + "list5.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void add(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId = Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			pro2depDao.add(depId, proId);
			response.sendRedirect("d2p?&id=" + depId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void add2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			int depId = Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			boolean flag = pro2depDao.add(depId, proId);
			out.print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addBatch(HttpServletRequest request, HttpServletResponse response) {
		try {
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			PrintWriter out = response.getWriter();
			String pro = request.getParameter("pro");
			String[] array = pro.split(";");
			List<Dep2Pro> list = new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				String[] temp = array[i].split(",");
				Dep2Pro dep2pro = new Dep2Pro();
				dep2pro.setDepId(Integer.parseInt(temp[0]));
				dep2pro.setProId(Integer.parseInt(temp[1]));
				list.add(dep2pro);
			}
			boolean flag = pro2depDao.addBatch(list);
			//
			// boolean flag= pro2depDao.add(depId, proId);
			out.print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId = Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			boolean flag = pro2depDao.delete(depId, proId);
			if (flag) {
				response.sendRedirect("d2p?id=" + depId);
			} else {
				request.getRequestDispatcher(path + "fail.jsp").forward(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			int depId = Integer.parseInt(request.getParameter("depId"));
			int proId = Integer.parseInt(request.getParameter("proId"));
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			boolean flag = pro2depDao.delete(depId, proId);
			out.print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteBatch(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			String pro = request.getParameter("pro");
			JSONArray jsonArray = JSONArray.fromObject(pro);
			List<Dep2Pro> list = (List<Dep2Pro>) jsonArray.toCollection(jsonArray, Dep2Pro.class);
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			boolean flag = pro2depDao.deleteBatch(list);
			out.print(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
