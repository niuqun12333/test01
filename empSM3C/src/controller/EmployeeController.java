package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import entity.Department;
import entity.Employee;
import service.DepartmentService;
import service.EmployeeService;
import service.impl.DepartmentServiceImpl;
import service.impl.EmployeeServiceImpl;
import util.Constant;
import util.Pagination;

@Controller
@RequestMapping("emp")
public class EmployeeController {
	private static final String path = "emp/";

	@RequestMapping("search")
	public ModelAndView search(Employee condition, Integer ye) {
		ModelAndView mv = new ModelAndView(path + "list");
		
		EmployeeService empService = new EmployeeServiceImpl();
		DepartmentService depService = new DepartmentServiceImpl();
		if (ye == null) {
			ye = 1;
		}
		int count = empService.searchCount(condition);
		List<Department> depList = depService.searchAll();
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Employee> list = empService.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		mv.addObject("list", list);
		mv.addObject("c", condition);
		mv.addObject("p", p);
		mv.addObject("depList", depList);
		return mv;
	}

	@RequestMapping("add")
	public String add(Employee emp, String photo) {
		emp.setPicture(photo);
		EmployeeService empService = new EmployeeServiceImpl();
		boolean flag = empService.add(emp);
	
		return "redirect:search.do";
	}

	@RequestMapping("upload")
	@ResponseBody
	public String upload(@RequestParam(value = "file") MultipartFile[] myfiles) {
		String pictureName = "";
		String uploadPath = "c://tu";
		try {
			for (MultipartFile myfile : myfiles) {

				// MultipartFile myfile=myfiles[0];

				if (!myfile.isEmpty()) {
					String oldName = myfile.getOriginalFilename();
					UUID uuid = UUID.randomUUID();
					pictureName = uuid.toString() + oldName.substring(oldName.lastIndexOf("."));

					myfile.transferTo(new File(uploadPath + "/" + pictureName));

				}
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pictureName;
	}

	@RequestMapping("add2")
	public String add2(Employee emp, @RequestParam(value = "file") MultipartFile[] myfiles) {
		String pictureName = "";
		String uploadPath = "c://tu";

		for (MultipartFile myfile : myfiles) {

			// MultipartFile myfile=myfiles[0];

			if (!myfile.isEmpty()) {
				String oldName = myfile.getOriginalFilename();
				UUID uuid = UUID.randomUUID();
				pictureName = uuid.toString() + oldName.substring(oldName.lastIndexOf("."));
				try {
					myfile.transferTo(new File(uploadPath + "/" + pictureName));
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		emp.setPicture(pictureName);
		EmployeeService empService = new EmployeeServiceImpl();
		boolean flag = empService.add(emp);
		return "redirect:search.do";
	}

	@RequestMapping("filedelete")
	@ResponseBody
	public boolean filedelete(String picture) {
		boolean flag = false;
		String pat = "c:/tu/";
		File del = new File(pat + picture);
		if (del.exists()) {
			del.delete();
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	@RequestMapping("showAdd")
	public ModelAndView showAdd() {
		DepartmentService depService = new DepartmentServiceImpl();
		List<Department> depList = depService.searchAll();
		ModelAndView mv = new ModelAndView(path + "add");
		mv.addObject("depList", depList);
		return mv;
	}

	@RequestMapping("showAdd2")
	public ModelAndView showAdd2() {
		DepartmentService depService = new DepartmentServiceImpl();
		List<Department> depList = depService.searchAll();
		ModelAndView mv = new ModelAndView(path + "add2");
		mv.addObject("depList", depList);
		return mv;
	}

	@RequestMapping("delete")
	public String delete(Integer id) {
		EmployeeService empService = new EmployeeServiceImpl();
		boolean flag = empService.delete(id);
		return "redirect:search.do";

	}

	@RequestMapping("showUpdate")
	public ModelAndView showUpdate(int id) {
		DepartmentService depService = new DepartmentServiceImpl();
		List<Department> depList = depService.searchAll();
		EmployeeService empService = new EmployeeServiceImpl();
		Employee emp = empService.searchById(id);
		ModelAndView mv = new ModelAndView(path + "update");
		mv.addObject("depList", depList);
		mv.addObject("emp", emp);
		return mv;
	}

	@RequestMapping("update")
	public String update(Employee emp, String photo) {
		emp.setPicture(photo);
		EmployeeService empService = new EmployeeServiceImpl();
		boolean flag = empService.update(emp);

		return "redirect:search.do";

	}

}
