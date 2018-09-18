package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	DepartmentDao depDao;
	@Autowired
	EmployeeDao empDao;// IOC

	@Override
	public int searchCount(Department condition) {

		int count = depDao.searchCount(condition);

		return count;
	}

	@Override
	public List<Department> search(Department condition, int begin, int size) {

		List<Department> list = depDao.search(condition, begin, size);

		return list;
	}

	@Override
	public boolean add(Department dep) {

		int rs = depDao.add(dep);

		return rs > 0;
	}

	@Override
	public boolean delete(Integer id) {

		int rs = depDao.delete(id);

		rs = empDao.updateByDepId(id);

		return rs > 0;
	}

	@Override
	public Department searchById(Integer id) {

		Department dep = depDao.searchById(id);

		return dep;
	}

	@Override
	public boolean update(Department dep) {

		int rs = depDao.update(dep);

		return rs > 0;
	}

	@Override
	public List<Department> searchAll() {

		List<Department> list = depDao.searchAll();

		return list;
	}

}
