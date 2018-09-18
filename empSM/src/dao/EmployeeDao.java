package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Employee;

public interface EmployeeDao {

	int searchCount(@Param("emp") Employee condition);

	List<Employee> search(@Param("emp") Employee condition, @Param("begin") int begin, @Param("size") int size);

	int add(Employee emp);

	Employee searchById(int id);

	int delete(Integer id);

	int update(Employee emp);

}
