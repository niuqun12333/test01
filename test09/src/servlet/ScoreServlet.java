package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.java.swing.plaf.windows.resources.windows;

import dao.ScoreDao;
import dao.DepartmentDao;
import dao.Project2DpatmentDao;
import dao.ProjectDao;
import entity.Score;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import entity.Department;
import entity.Employee;
import entity.Project;
import util.Constant;
import util.Pagination;

public class ScoreServlet extends HttpServlet {
	private static final String path = "WEB-INF/score/";
	//ScoreDao scoDao = new ScoreDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String type = request.getParameter("type");
			if (type == null) {
				search(request, response);
			} else if ("update".equals(type)) {
				update(request, response);
			} else if ("m2".equals(type)) {
				search2(request, response);
			} else if ("update2".equals(type)) {
				update2(request, response);
			} else if ("input".equals(type)) {
				input(request, response);
			} else if ("change".equals(type)) {
				change(request, response);
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		doGet(req, resp);
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			String empName = request.getParameter("empName");

			int depId = -1;
			if (request.getParameter("depId") != null && !"".equals(request.getParameter("depId"))) {
				depId = Integer.parseInt(request.getParameter("depId"));
			}
			int proId = -1;
			if (request.getParameter("proId") != null && !"".equals(request.getParameter("proId"))) {
				proId = Integer.parseInt(request.getParameter("proId"));
			}
			int value = -1;
			if (request.getParameter("value") != null && !"".equals(request.getParameter("value"))) {
				value = Integer.parseInt(request.getParameter("value"));
			}
			String grade = request.getParameter("grade");

			Employee emp = new Employee();
			emp.setName(empName);
			Department dep = new Department();
			dep.setId(depId);
			Project pro = new Project();
			pro.setId(proId);
			Score condition = new Score();
			condition.setValue(value);
			condition.setGrade(grade);
			condition.setEmp(emp);
			condition.setDep(dep);
			condition.setPro(pro);
			ScoreDao scoDao = new ScoreDao();
			int count = scoDao.searchCount(condition);
			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);

			List<Score> list = scoDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
			List<Score> gradeList = scoDao.searchGrade();
			request.setAttribute("list", list);
			request.setAttribute("gradeList", gradeList);

			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);

			ProjectDao proDao = new ProjectDao();
			List<Project> proList = proDao.search();
			request.setAttribute("proList", proList);

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

	public void search2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String empName = request.getParameter("empName");

			int depId = -1;
			if (request.getParameter("depId") != null && !"".equals(request.getParameter("depId"))) {
				depId = Integer.parseInt(request.getParameter("depId"));
			}
			int proId = -1;
			if (request.getParameter("proId") != null && !"".equals(request.getParameter("proId"))) {
				proId = Integer.parseInt(request.getParameter("proId"));
			}
			int value = -1;
			if (request.getParameter("value") != null && !"".equals(request.getParameter("value"))) {
				value = Integer.parseInt(request.getParameter("value"));
			}
			String grade = request.getParameter("grade");

			Employee emp = new Employee();
			emp.setName(empName);
			Department dep = new Department();
			dep.setId(depId);
			Project pro = new Project();
			pro.setId(proId);
			Score condition = new Score();
			condition.setValue(value);
			condition.setGrade(grade);
			condition.setEmp(emp);
			condition.setDep(dep);
			condition.setPro(pro);
			ScoreDao scoDao = new ScoreDao();
			int count = scoDao.searchCount(condition);
			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);

			List<Score> list = scoDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
			List<Score> gradeList = scoDao.searchGrade();
			request.setAttribute("list", list);
			request.setAttribute("gradeList", gradeList);

			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);

			ProjectDao proDao = new ProjectDao();
			List<Project> proList = proDao.search();
			request.setAttribute("proList", proList);

			request.setAttribute("p", p);
			request.setAttribute("c", condition);
			request.getRequestDispatcher(path + "list2.jsp").forward(request, response);
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
			String sco = request.getParameter("sco");
			JSONArray jsonArray = JSONArray.fromObject(sco);
			List<Score> list = (List<Score>) jsonArray.toCollection(jsonArray, Score.class);
			ScoreDao scoDao = new ScoreDao();
			boolean flag = scoDao.updateBatch(list);
			if (flag) {
				response.sendRedirect("sco");
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

	public void input(HttpServletRequest request, HttpServletResponse response) {
		try {
			ScoreDao scoDao = new ScoreDao();
			PrintWriter out = response.getWriter();
			boolean flag = false;
			int id = Integer.parseInt(request.getParameter("id"));
			int value = Integer.parseInt(request.getParameter("value"));
			Score sco = new Score();
			sco.setValue(value);
			if (id == 0) {
				int empId = Integer.parseInt(request.getParameter("empId"));
				int proId = Integer.parseInt(request.getParameter("proId"));
				Employee emp = new Employee();
				emp.setId(empId);
				Project pro = new Project();
				pro.setId(proId);
				sco.setEmp(emp);
				sco.setPro(pro);
				id = scoDao.add(sco);
				if (id > 0) {
					flag = true;
				}
				sco.setId(id);
			} else {
				sco.setId(id);
				flag = scoDao.update(sco);
			}
			Score sc = scoDao.search(id);
			if (flag) {
				JSON json = JSONObject.fromObject(sc);
				out.print(json);
			} else {
				out.print(false);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void change(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			int depId = -1;
			if (request.getParameter("depId") != null && !"".equals(request.getParameter("depId"))) {
				depId = Integer.parseInt(request.getParameter("depId"));
			}
			ScoreDao scoDao = new ScoreDao();
			List<Score> list = scoDao.searchDep2Pro(depId);
			// for(int i = 0;i<list.size();i++) {
			// String proName=list.get(i).getPro().getName();
			// System.out.println(proName);
			// }
			JSONArray json = new JSONArray();
			for (Score pLog : list) {
				JSONObject jo = new JSONObject();
				jo.put("dep", pLog.getDep());
				jo.put("pro", pLog.getPro());
				json.add(jo);
			}
			//JSON json = JSONObject.fromObject(list);
			out.print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update2(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			String sco = request.getParameter("sco");
			JSONArray jsonArray = JSONArray.fromObject(sco);
			List<Score> list = (List<Score>) jsonArray.toCollection(jsonArray, Score.class);
			ScoreDao scoDao = new ScoreDao();
			boolean flag = scoDao.updateBatch(list);
			out.print(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
