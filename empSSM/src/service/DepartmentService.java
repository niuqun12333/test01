package service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Department;

public interface DepartmentService {
	int searchCount(Department condition);

	List<Department> search(Department condition, int begin, @Param("size") int size);

	boolean add(Department dep);

	boolean delete(Integer id);

	Department searchById(Integer id);

	boolean update(Department dep);
	
	List<Department> searchAll();
}
