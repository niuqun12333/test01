package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Employee;
import service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	DepartmentDao depDao;
	@Autowired
	EmployeeDao empDao;

	@Override
	public int searchCount(Employee condition) {

		int rs = empDao.searchCount(condition);

		return rs;
	}

	@Override
	public List<Employee> search(Employee condition, int begin, int size) {

		List<Employee> list = empDao.search(condition, begin, size);

		return list;
	}

	@Override
	public boolean add(Employee emp) {

		int rs = empDao.add(emp);

		return rs > 0;
	}

	@Override
	public Employee searchById(int id) {

		Employee emp = empDao.searchById(id);

		return emp;
	}

	@Override
	public boolean delete(Integer id) {

		int rs = empDao.delete(id);

		return rs > 0;
	}

	@Override
	public boolean update(Employee emp) {

		int rs = empDao.update(emp);

		return rs > 0;
	}

}
