package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.java.swing.plaf.windows.resources.windows;

import dao.DepartmentDao;
import dao.Project2DpatmentDao;
import entity.Department;
import entity.Project;
import util.Constant;
import util.Pagination;

public class DepartmentServlet extends HttpServlet {
	private static final String path = "WEB-INF/dep/";
	//DepartmentDao depDao = new DepartmentDao();

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
			} else if ("deleteBatch".equals(type)) {
				deleteBatch(request, response);
			} else if ("showUpdate".equals(type)) {
				showUpdate(request, response);
			} else if ("update".equals(type)) {
				update(request, response);
			} else if ("showProject2Dpatment".equals(type)) {
				showProject2Dpatment(request, response);
			} else if ("Project2DpatmentAdd".equals(type)) {
				Project2DpatmentAdd(request, response);
			}else if ("Project2DpatmentDelete".equals(type)) {
				Project2DpatmentDelete(request, response);
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			int empCount = -1;
			if (request.getParameter("empCount") != null && !"".equals(request.getParameter("empCount"))) {
				empCount = Integer.parseInt(request.getParameter("empCount"));
			}
			Department condition = new Department();
			condition.setName(name);
			condition.setEmpCount(empCount);
			DepartmentDao depDao = new DepartmentDao();
			int count = depDao.searchCount(condition);
			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
			List<Department> list = depDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
			request.setAttribute("list", list);
			request.setAttribute("p", p);
			request.setAttribute("c", condition);
			request.getRequestDispatcher(path + "list.jsp").forward(request, response);
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
	// int count = depDao.searchCount();
	// Pagination p = new Pagination(ye, count, Constant.dep_NUM_IN_PAGE,
	// Constant.dep_NUM_OF_PAGE);
	// List<Department> list = new ArrayList<>();
	// list = depDao.search(p.getBegin(), Constant.dep_NUM_IN_PAGE);
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
			Department dep = new Department();
			dep.setName(name);
			DepartmentDao depDao = new DepartmentDao();
			boolean flag = depDao.add(dep);
			if (flag) {
				response.sendRedirect("dep");
			} else {
				request.getRequestDispatcher(path + "fail.jsp").forward(request, response);
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
			request.getRequestDispatcher(path + "add.jsp").forward(request, response);
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
			DepartmentDao depDao = new DepartmentDao();
			boolean flag = depDao.delete(ids);
			if (flag) {
				response.sendRedirect("dep");
			} else {
				request.getRequestDispatcher(path + "fail.jsp").forward(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteBatch(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("ids");
			// String ids = request.getParameter("ids");
			DepartmentDao depDao = new DepartmentDao();
			boolean flag = depDao.deleteBatch(ids);
			if (flag) {
				response.sendRedirect("dep");
			} else {
				request.getRequestDispatcher(path + "fail.jsp").forward(request, response);
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
			DepartmentDao depDao = new DepartmentDao();
			Department dep = depDao.searchId(id);
			request.setAttribute("dep", dep);
			request.getRequestDispatcher(path + "update.jsp").forward(request, response);
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
			Department dep = new Department();
			dep.setId(id);
			dep.setName(name);
			DepartmentDao depDao = new DepartmentDao();
			boolean flag = depDao.update(dep);
			if (flag) {
				response.sendRedirect("dep");
			} else {
				request.getRequestDispatcher(path + "fail.jsp").forward(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showProject2Dpatment(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depid = Integer.parseInt(request.getParameter("id"));
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			List<Project> list = pro2depDao.searchByDepartment(depid);
			List<Project> notProList = pro2depDao.searchByNotDepartment(depid);
			DepartmentDao depDao=new DepartmentDao();
			Department dep = depDao.searchId(depid);
			request.setAttribute("list", list);
			request.setAttribute("notProList", notProList);
			request.setAttribute("dId", depid);
			request.setAttribute("dep", dep);
			request.getRequestDispatcher(path + "showProject2Dpatment.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Project2DpatmentAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId = Integer.parseInt(request.getParameter("dId"));
			int proId = Integer.parseInt(request.getParameter("pId"));
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			boolean flag = pro2depDao.add(depId, proId);
			if (flag) {
				response.sendRedirect("dep?type=showProject2Dpatment&id="+depId);
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
	
	public void Project2DpatmentDelete(HttpServletRequest request, HttpServletResponse response) {
		try {
			int depId = Integer.parseInt(request.getParameter("dId"));
			int proId = Integer.parseInt(request.getParameter("pId"));
			Project2DpatmentDao pro2depDao = new Project2DpatmentDao();
			boolean flag = pro2depDao.delete(depId, proId);
			if (flag) {
				response.sendRedirect("dep?type=showProject2Dpatment&id="+depId);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
