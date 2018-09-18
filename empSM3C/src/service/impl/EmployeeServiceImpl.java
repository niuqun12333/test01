package service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.EmployeeDao;
import dao.MybatisSqlSession;
import entity.Employee;
import service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public int searchCount(Employee condition) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = sqlSession.getMapper(EmployeeDao.class);
		int rs = empDao.searchCount(condition);
		sqlSession.commit();
		sqlSession.close();
		return rs;
	}

	@Override
	public List<Employee> search(Employee condition, int begin, int size) {
		SqlSession sqlSession= MybatisSqlSession.getSqlSession();
		EmployeeDao empDao =sqlSession.getMapper(EmployeeDao.class);
		List<Employee> list=empDao.search(condition, begin, size);
		sqlSession.commit();
		sqlSession.close();
		return list;
	}

	@Override
	public boolean add(Employee emp) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = sqlSession.getMapper(EmployeeDao.class);
		int rs = empDao.add(emp);
		sqlSession.commit();
		sqlSession.close();
		return rs > 0;
	}

	@Override
	public Employee searchById(int id) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = sqlSession.getMapper(EmployeeDao.class);
		Employee emp = empDao.searchById(id);
		sqlSession.commit();
		sqlSession.close();
		return emp;
	}

	@Override
	public boolean delete(Integer id) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = sqlSession.getMapper(EmployeeDao.class);
		int rs = empDao.delete(id);
		sqlSession.commit();
		sqlSession.close();
		return rs > 0;
	}

	@Override
	public boolean update(Employee emp) {
		SqlSession sqlSession = MybatisSqlSession.getSqlSession();
		EmployeeDao empDao = sqlSession.getMapper(EmployeeDao.class);
		int rs = empDao.update(emp);
		sqlSession.commit();
		sqlSession.close();
		return rs > 0;
	}

}
