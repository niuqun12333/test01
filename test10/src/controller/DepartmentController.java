package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.java.swing.plaf.windows.resources.windows;

import dao.DepartmentDao;
import entity.Department;
import entity.Employee;
import util.Constant;
import util.Pagination;

@Controller
@RequestMapping("dep")
public class DepartmentController {
	private static final String path = "dep/";
	
	@RequestMapping("search")
	public ModelAndView search(Department condition ,Integer ye) {
		DepartmentDao depDao = new DepartmentDao();
		int count = depDao.searchCount(condition);
		if (ye == null) {
			ye = 1;
		}
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Department> list = depDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		ModelAndView mv = new ModelAndView(path+"list");
		mv.addObject("list", list);
		mv.addObject("c", condition);
		mv.addObject("p", p);
		return mv;
	}

	@RequestMapping("add")
	public String add(Department dep) {
		
		DepartmentDao depDao = new DepartmentDao();
		boolean flag = depDao.add(dep);
		if (flag) {
			return "redirect:search.do";
		} else {
			return path+"fail";
		}
	}

	@RequestMapping("showAdd")
	public String showAdd() {
		return path+"add";

	}

	@RequestMapping("delete")
	public String delete(Integer id) {
			DepartmentDao depDao = new DepartmentDao();
			boolean flag = depDao.delete(id);
			if (flag) {
				return "redirect:search.do";
			} else {
				return path+"list";
			}
	}

	@RequestMapping("showUpdate")
	public ModelAndView showUpdate(Integer id) {
			DepartmentDao depDao = new DepartmentDao();
			Department dep = depDao.searchId(id);
			ModelAndView mv = new ModelAndView(path+"update");
			mv.addObject("dep", dep);
			return mv;
	}

	@RequestMapping("update")
	public String update(Department dep) {
			DepartmentDao depDao = new DepartmentDao();
			boolean flag = depDao.update(dep);
			if (flag) {
				return "redirect:search.do";
			} else {
				return path+"list";
			}
	}

}
