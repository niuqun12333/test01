package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Department;
import service.DepartmentService;
import service.impl.DepartmentServiceImpl;
import util.Constant;
import util.Pagination;

@Controller
@RequestMapping("dep")
public class DepartmentController {
	private static final String path = "dep/";

	@RequestMapping("search")
	public ModelAndView search(Department condition, Integer ye) {
		
		DepartmentService depService = new DepartmentServiceImpl();
		int count = depService.searchCount(condition);
		if (ye == null) {
			ye = 1;
		}
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Department> list = depService.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		
		ModelAndView mv = new ModelAndView(path + "list");
		mv.addObject("list", list);
		mv.addObject("c", condition);
		mv.addObject("p", p);
		return mv;
	}

	@RequestMapping("add")
	public String add(Department dep) {

		
		DepartmentService depService = new DepartmentServiceImpl();
		boolean flag = depService.add(dep);
	
		return "redirect:search.do";

	}

	@RequestMapping("showAdd")
	public String showAdd() {
		return path + "add";

	}

	@RequestMapping("delete")
	public String delete(Integer id) {
		
		DepartmentService depService = new DepartmentServiceImpl();
		boolean flag = depService.delete(id);

		return "redirect:search.do";
	}

	@RequestMapping("showUpdate")
	public ModelAndView showUpdate(Integer id) {
		
		DepartmentService depService = new DepartmentServiceImpl();
		Department dep = depService.searchById(id);
	
		ModelAndView mv = new ModelAndView(path + "update");
		mv.addObject("dep", dep);
		return mv;
	}

	@RequestMapping("update")
	public String update(Department dep) {
		
		DepartmentService depService = new DepartmentServiceImpl();
		boolean flag = depService.update(dep);
	
		return "redirect:search.do";
	}

}
