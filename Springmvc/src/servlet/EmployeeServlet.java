package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import net.sf.json.JSONArray;
import sun.security.x509.DeltaCRLIndicatorExtension;
import util.Constant;
import util.Pagination;

public class EmployeeServlet extends HttpServlet {
	private static final String path = "WEB-INF/emp/";
	//EmployeeDao empDao = new EmployeeDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			String type = request.getParameter("type");
			Class clazz = this.getClass();
			Method method = clazz.getMethod(type, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this,request,response);
//			if (type == null) {
//				search(request, response);
//			} else if ("add".equals(type)) {
//				add(request, response);
//			} else if ("add2".equals(type)) {
//				add2(request, response);
//			} else if ("showAdd".equals(type)) {
//				showAdd(request, response);
//			} else if ("showAdd2".equals(type)) {
//				showAdd2(request, response);
//			} else if ("delete".equals(type)) {
//				delete(request, response);
//			} else if ("deleteBatch".equals(type)) {
//				deleteBatch(request, response);
//			} else if ("showUpdate".equals(type)) {
//				showUpdate(request, response);
//			} else if ("update".equals(type)) {
//				update(request, response);
//			} else if ("showUpdateBatch1".equals(type)) {
//				showUpdateBatch1(request, response);
//			} else if ("showUpdateBatch2".equals(type)) {
//				showUpdateBatch2(request, response);
//			} else if ("updateBatch1".equals(type)) {
//				updateBatch1(request, response);
//			} else if ("updateBatch2".equals(type)) {
//				updateBatch2(request, response);
//			} else if ("updateBatch3".equals(type)) {
//				updateBatch3(request, response);
//			} else if ("upload".equals(type)) {
//				upload(request, response);
//			} else if ("filedelete".equals(type)) {
//				filedelete(request, response);
//			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void search(HttpServletRequest request, HttpServletResponse response) {
		try {
			Department dep = new Department();
			Employee condition = new Employee();
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = -1;
			if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
				age = Integer.parseInt(request.getParameter("age"));
			}
			int d_id = -1;
			if (request.getParameter("d_id") != null && !"".equals(request.getParameter("d_id"))) {
				d_id = Integer.parseInt(request.getParameter("d_id"));
			}
			condition.setName(name);
			condition.setSex(sex);
			condition.setAge(age);
			// dep.setId(d_id);
			dep.setId(d_id);
			condition.setDep(dep);
			EmployeeDao empDao = new EmployeeDao();
			int count = empDao.searchCount(condition);
			int ye = 1;
			if (request.getParameter("ye") != null) {
				ye = Integer.parseInt(request.getParameter("ye"));
			}
			Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
			List<Employee> list = empDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
			request.setAttribute("list", list);
			request.setAttribute("p", p);
			request.setAttribute("c", condition);
			request.setAttribute("depList", depList);
			request.getRequestDispatcher(path + "list.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
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
	// int count = empDao.searchCount();
	// Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE,
	// Constant.EMP_NUM_OF_PAGE);
	// List<Employee> list = new ArrayList<>();
	// list = empDao.search(p.getBegin(), Constant.EMP_NUM_IN_PAGE);
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
			Integer age = null;
			Integer d_id = null;
			String name = "";
			String sex = "";
			String picture = "";
			Department dep = new Department();
			String pat = "c:/tu/";
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("picture")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					picture = uuid.toString() + houzhui;
					File savedFile = new File(pat, picture);
					item.write(savedFile);
					// System.out.println(picture);
				} else if (item.getFieldName().equals("name")) {
					name = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
					// System.out.println(name);
				} else if (item.getFieldName().equals("sex")) {
					sex = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
					// System.out.println(sex);
				} else if (item.getFieldName().equals("age")) {
					age = Integer.parseInt(item.getString());
					// System.out.println(age);
				} else if (item.getFieldName().equals("d_id")) {
					d_id = Integer.parseInt(item.getString());
					// System.out.println(d_id);
				}
			}
			// String name = request.getParameter("name");
			// String sex = request.getParameter("sex");
			// String age = request.getParameter("age");
			// Integer d_id = null;
			if (request.getParameter("d_id") != null && !"".equals(request.getParameter("d_id"))) {
				d_id = Integer.parseInt(request.getParameter("d_id"));
			}
			if (request.getParameter("age") != null && !"".equals(request.getParameter("age"))) {
				age = Integer.parseInt(request.getParameter("age"));
			}
			// Department dep = new Department();
			dep.setId(d_id);
			Employee emp = new Employee();
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			emp.setPicture(picture);
			emp.setDep(dep);
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.add(emp);
			if (flag) {
				response.sendRedirect("emp?type=search");
			} else {
				request.getRequestDispatcher(path + "fail.jsp").forward(request, response);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String age = request.getParameter("age");
			Integer d_id = null;
			if (request.getParameter("d_id") != null && !"".equals(request.getParameter("d_id"))) {
				d_id = Integer.parseInt(request.getParameter("d_id"));
			}
			// String[] picture = request.getParameterValues("photo");
			String picture = request.getParameter("photo");
			Department dep = new Department();
			dep.setId(d_id);
			Employee emp = new Employee();
			emp.setName(name);
			emp.setSex(sex);
			emp.setPicture(picture);
			emp.setAge(Integer.parseInt(age));
			emp.setDep(dep);
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.add(emp);
			if (flag) {
				response.sendRedirect("emp?type=search");
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

	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			String pictureName = "";
			String pat = "c:/tu/";
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				if (item.getFieldName().equals("picture")) {
					UUID uuid = UUID.randomUUID();
					String houzhui = item.getName().substring(item.getName().lastIndexOf("."));
					pictureName = uuid.toString() + houzhui;
					File savedFile = new File(pat, pictureName);
					item.write(savedFile);
				}
			}
			out.print(pictureName);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void filedelete(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			boolean flag = false;
			String pictureName = request.getParameter("picture");
			String pat = "c:/tu/";
			File del = new File(pat + pictureName);
			if (del.exists()) {
				del.delete();
				flag = true;
			} else {
				flag = false;
			}
			out.println(flag);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher(path + "add.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showAdd2(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			request.setAttribute("depList", depList);
			request.getRequestDispatcher(path + "add2.jsp").forward(request, response);
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
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.delete(ids);
			if (flag) {
				response.sendRedirect("emp?type=search");
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
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.deleteBatch(ids);
			if (flag) {
				response.sendRedirect("emp?type=search");
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
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDao empDao = new EmployeeDao();
			Employee emp = empDao.searchId(id);
			request.setAttribute("depList", depList);
			request.setAttribute("emp", emp);
			request.getRequestDispatcher(path + "update.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUpdateBatch1(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			String ids = request.getParameter("ids");
			EmployeeDao empDao = new EmployeeDao();
			List<Employee> list = empDao.search(ids);
			request.setAttribute("depList", depList);
			request.setAttribute("emp", list.get(0));
			request.setAttribute("ids", ids);
			request.getRequestDispatcher(path + "updateBatch1.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showUpdateBatch2(HttpServletRequest request, HttpServletResponse response) {
		try {
			DepartmentDao depDao = new DepartmentDao();
			List<Department> depList = depDao.search();
			String ids = request.getParameter("ids");
			request.setAttribute("depList", depList);
			EmployeeDao empDao = new EmployeeDao();
			List<Employee> list = empDao.search(ids);
			request.setAttribute("list", list);
			request.setAttribute("ids", ids);
			request.getRequestDispatcher(path + "updateBatch2.jsp").forward(request, response);
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
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			Integer d_id = null;
			if (request.getParameter("d_id") != null && !"".equals(request.getParameter("d_id"))) {
				d_id = Integer.parseInt(request.getParameter("d_id"));
			}
			String pictureName = request.getParameter("photo");
			Employee emp = new Employee();
			Department dep = new Department();
			dep.setId(d_id);
			emp.setPicture(pictureName);
			emp.setId(id);
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			emp.setDep(dep);
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.update(emp);
			if (flag) {
				response.sendRedirect("emp?type=search");
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

	public void updateBatch1(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ids = request.getParameter("ids");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			int age = Integer.parseInt(request.getParameter("age"));
			Integer d_id = null;
			if (request.getParameter("d_id") != null && !"".equals(request.getParameter("d_id"))) {
				d_id = Integer.parseInt(request.getParameter("d_id"));
			}
			Employee emp = new Employee();
			Department dep = new Department();
			dep.setId(d_id);
			emp.setName(name);
			emp.setSex(sex);
			emp.setAge(age);
			emp.setDep(dep);
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.updateBatch1(ids, emp);
			if (flag) {
				response.sendRedirect("emp?type=search");
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

	public void updateBatch2(HttpServletRequest request, HttpServletResponse response) {
		try {
			String emps = request.getParameter("emps");
			String[] array = emps.split(";");
			List<Employee> list = new ArrayList<>();
			for (int i = 0; i < array.length; i++) {
				String[] temp = array[i].split(",");
				Department dep = new Department();
				Employee emp = new Employee();
				emp.setId(Integer.parseInt(temp[0]));
				emp.setName(temp[1]);
				emp.setSex(temp[2]);
				emp.setAge(Integer.parseInt(temp[3]));
				Integer d_id = null;
				if (temp.length > 4) {
					d_id = Integer.parseInt(temp[4]);
				}
				dep.setId(d_id);
				emp.setDep(dep);
				// System.out.println(Integer.parseInt(temp[4]));
				list.add(emp);
			}
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.updateBatch2(list);
			if (flag) {
				response.sendRedirect("emp?type=search");
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

	public void updateBatch3(HttpServletRequest request, HttpServletResponse response) {
		try {
			String emps = request.getParameter("emps");
			JSONArray jsonArray = JSONArray.fromObject(emps);
			List<Employee> list = (List<Employee>) jsonArray.toCollection(jsonArray, Employee.class);
			EmployeeDao empDao = new EmployeeDao();
			boolean flag = empDao.updateBatch3(list);
			if (flag) {
				response.sendRedirect("emp?type=search");
			} else {
				request.getRequestDispatcher(path + "fail.jsp").forward(request, response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
