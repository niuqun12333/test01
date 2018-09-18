package service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.MybatisSqlSession;
import entity.Department;
import service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

	@Override
	public int searchCount(Department condition) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		int count = depDao.searchCount(condition);
		sqlSession.commit();
		sqlSession.close();
		return count;
	}

	@Override
	public List<Department> search(Department condition, int begin, int size) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		List<Department> list = depDao.search(condition, begin, size);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

	@Override
	public boolean add(Department dep) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		int rs = depDao.add(dep);
		sqlSession.commit();
		sqlSession.close();
		return rs > 0;
	}

	@Override
	public boolean delete(Integer id) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		EmployeeDao empDao = sqlSession.getMapper(EmployeeDao.class);
		
		int rs = depDao.delete(id);
		
		rs = empDao.updateByDepId(id);
		
		sqlSession.commit();
		sqlSession.close();
		return rs > 0;
	}

	@Override
	public Department searchById(Integer id) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		Department dep = depDao.searchById(id);
		sqlSession.commit();
		sqlSession.close();
		return dep;
	}

	@Override
	public boolean update(Department dep) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		int rs = depDao.update(dep);
		sqlSession.commit();
		sqlSession.close();
		return rs > 0;
	}

	@Override
	public List<Department> searchAll() {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		DepartmentDao depDao = sqlSession.getMapper(DepartmentDao.class);
		List<Department> list = depDao.searchAll();
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

}
