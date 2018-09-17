package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import util.Constant;
import util.Pagination;

@Controller
@RequestMapping("emp")
public class EmployeeController {
	private static final String path = "emp/";

	@RequestMapping("search")
	public ModelAndView search(Employee condition, Integer ye) {
		ModelAndView mv = new ModelAndView(path + "list");
		EmployeeDao empDao = new EmployeeDao();
		DepartmentDao depDao = new DepartmentDao();

		if (ye == null) {
			ye = 1;
		}
		int count = empDao.searchCount(condition);

		List<Department> depList = depDao.search();
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Employee> list = empDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);

		mv.addObject("list", list);
		mv.addObject("c", condition);
		mv.addObject("p", p);
		mv.addObject("depList", depList);
		return mv;
	}

	@RequestMapping("add")
	public String add(Employee emp, String photo) {
		emp.setPicture(photo);
		EmployeeDao empDao = new EmployeeDao();
		boolean flag = empDao.add(emp);
		if (flag) {
			return "redirect:search.do";
		} else {
			return path + "fail";
		}
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
		boolean flag = false;
		
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
			EmployeeDao empDao = new EmployeeDao();
			flag = empDao.add(emp);
			if (flag) {
				return "redirect:search.do";
			} else {
				return path + "fail";
			}
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

	// @RequestMapping("showAdd")
	// public void showAdd() {
	// try {
	// DepartmentDao depDao = new DepartmentDao();
	// List<Department> depList = depDao.search();
	// request.setAttribute("depList", depList);
	// request.getRequestDispatcher(path + "add.jsp").forward(request, response);
	// } catch (ServletException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	@RequestMapping("showAdd")
	public ModelAndView showAdd() {
		DepartmentDao depDao = new DepartmentDao();
		List<Department> depList = depDao.search();
		ModelAndView mv = new ModelAndView(path + "add");
		mv.addObject("depList", depList);
		return mv;
	}

	@RequestMapping("showAdd2")
	public ModelAndView showAdd2() {
		DepartmentDao depDao = new DepartmentDao();
		List<Department> depList = depDao.search();
		ModelAndView mv = new ModelAndView(path + "add2");
		mv.addObject("depList", depList);
		return mv;
	}

	@RequestMapping("delete")
	public String delete(Integer id) {
		EmployeeDao empDao = new EmployeeDao();
		boolean flag = empDao.delete(id);
		if (flag) {
			return "redirect:search.do";
		} else {
			return path + "fail";
		}

	}

	@RequestMapping("showUpdate")
	public ModelAndView showUpdate(int id) {
		DepartmentDao depDao = new DepartmentDao();
		List<Department> depList = depDao.search();
		EmployeeDao empDao = new EmployeeDao();
		Employee emp = empDao.searchId(id);
		ModelAndView mv = new ModelAndView(path + "update");
		mv.addObject("depList", depList);
		mv.addObject("emp", emp);
		return mv;
	}

	@RequestMapping("update")
	public String update(Employee emp,String photo) {
		emp.setPicture(photo);
		EmployeeDao empDao = new EmployeeDao();
		boolean flag = empDao.update(emp);
		if (flag) {
			return "redirect:search.do";
		} else {
			return path + "fail";
		}
	}

}
