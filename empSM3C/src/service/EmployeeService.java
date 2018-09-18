package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Employee;

public interface EmployeeService {
	int searchCount(Employee condition);

	List<Employee> search(Employee condition, int begin, int size);

	boolean add(Employee emp);

	Employee searchById(int id);

	boolean delete(Integer id);

	boolean update(Employee emp);

}
