package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.java.swing.plaf.windows.resources.windows;

import dao.DepartmentDao;
import dao.MybatisSqlSession;
import entity.Department;
import entity.Employee;
import util.Constant;
import util.Pagination;

@Controller
@RequestMapping("dep")
public class DepartmentController {
	private static final String path = "dep/";

	@RequestMapping("search")
	public ModelAndView search(Department condition, Integer ye) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		int count = depDao.searchCount(condition);
		if (ye == null) {
			ye = 1;
		}
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Department> list = depDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		sqlSession.commit();
		sqlSession.close();
		ModelAndView mv = new ModelAndView(path + "list");
		mv.addObject("list", list);
		mv.addObject("c", condition);
		mv.addObject("p", p);
		return mv;
	}

	@RequestMapping("add")
	public String add(Department dep) {

		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		int flag = depDao.add(dep);
		sqlSession.commit();
		sqlSession.close();
		return "redirect:search.do";

	}

	@RequestMapping("showAdd")
	public String showAdd() {
		return path + "add";

	}

	@RequestMapping("delete")
	public String delete(Integer id) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		int flag = depDao.delete(id);
		sqlSession.commit();
		sqlSession.close();
		return "redirect:search.do";
	}

	@RequestMapping("showUpdate")
	public ModelAndView showUpdate(Integer id) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		Department dep = depDao.searchById(id);
		sqlSession.commit();
		sqlSession.close();
		ModelAndView mv = new ModelAndView(path + "update");
		mv.addObject("dep", dep);
		return mv;
	}

	@RequestMapping("update")
	public String update(Department dep) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		int flag = depDao.update(dep);
		sqlSession.commit();
		sqlSession.close();
		return "redirect:search.do";
	}

}
