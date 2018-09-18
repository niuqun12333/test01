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
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.MybatisSqlSession;
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
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = sqlSession.getMapper(EmployeeDao.class);
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		if (ye == null) {
			ye = 1;
		}
		int count = empDao.searchCount(condition);
		List<Department> depList = depDao.searchAll();
		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Employee> list = empDao.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE);
		sqlSession.close();
		mv.addObject("list", list);
		mv.addObject("c", condition);
		mv.addObject("p", p);
		mv.addObject("depList", depList);
		return mv;
	}

	@RequestMapping("add")
	public String add(Employee emp, String photo) {
		emp.setPicture(photo);
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = sqlSession.getMapper(EmployeeDao.class);
		int flag = empDao.add(emp);
		sqlSession.commit();
		sqlSession.close();
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
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = sqlSession.getMapper(EmployeeDao.class);
		int flag = empDao.add(emp);
		sqlSession.commit();
		sqlSession.close();
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
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		List<Department> depList = depDao.searchAll();
		sqlSession.commit();
		sqlSession.close();
		ModelAndView mv = new ModelAndView(path + "add");
		mv.addObject("depList", depList);
		return mv;
	}

	@RequestMapping("showAdd2")
	public ModelAndView showAdd2() {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		List<Department> depList = depDao.searchAll();
		sqlSession.commit();
		sqlSession.close();
		ModelAndView mv = new ModelAndView(path + "add2");
		mv.addObject("depList", depList);
		return mv;
	}

	@RequestMapping("delete")
	public String delete(Integer id) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = sqlSession.getMapper(EmployeeDao.class);
		int flag = empDao.delete(id);
		sqlSession.commit();
		sqlSession.close();
		return "redirect:search.do";

	}

	@RequestMapping("showUpdate")
	public ModelAndView showUpdate(int id) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		List<Department> depList = depDao.searchAll();
		EmployeeDao empDao = sqlSession.getMapper(EmployeeDao.class);
		Employee emp = empDao.searchById(id);
		sqlSession.commit();
		sqlSession.close();
		ModelAndView mv = new ModelAndView(path + "update");
		mv.addObject("depList", depList);
		mv.addObject("emp", emp);
		return mv;
	}

	@RequestMapping("update")
	public String update(Employee emp, String photo) {
		emp.setPicture(photo);
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = sqlSession.getMapper(EmployeeDao.class);
		int flag = empDao.update(emp);
		sqlSession.commit();
		sqlSession.close();
		return "redirect:search.do";

	}

}
