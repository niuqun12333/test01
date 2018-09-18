package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;

public class ScoreDao extends BaseDao {

	public List<Score> search() {
		List<Score> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4 建立statement sql语句执行器
			stat = conn.createStatement();
			// 5执行sql语句并得到结果
			rs = stat.executeQuery(
					"select e.id as eId,e.name as eName,d.id as dId,d.name as dName,p.id as pId,p.name as pName,s.id as sId ,s.grade,s.value from employee as e left join score as s on e.id=s.e_id left join project as p on p.id=s.p_id LEFT JOIN department as d on e.d_id=d.id");
			// 6对结果进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("eId"));
				emp.setName(rs.getString("eName"));
				Department dep = new Department();
				dep.setId(rs.getInt("dId"));
				dep.setName(rs.getString("dName"));
				Project pro = new Project();
				pro.setId(rs.getInt("pId"));
				pro.setName(rs.getString("pName"));
				Score sco = new Score();
				sco.setId(rs.getInt("sId"));
				sco.setValue((Integer) rs.getObject("value"));
				sco.setGrade(rs.getString("grade"));

				sco.setEmp(emp);
				sco.setDep(dep);
				sco.setPro(pro);
				list.add(sco);

			}
			// 7关闭
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;

	}

	public Score search(int id) {
		Score sco = new Score();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4 建立statement sql语句执行器
			stat = conn.createStatement();
			// 5执行sql语句并得到结果
			rs = stat.executeQuery("select * from v_emp_sc where sId=" + id);
			// 6对结果进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("eId"));
				emp.setName(rs.getString("eName"));
				Department dep = new Department();
				dep.setId(rs.getInt("dId"));
				dep.setName(rs.getString("dName"));
				Project pro = new Project();
				pro.setId(rs.getInt("pId"));
				pro.setName(rs.getString("pName"));

				sco.setId(rs.getInt("sId"));
				sco.setValue((Integer) rs.getObject("value"));
				sco.setGrade(rs.getString("grade"));

				sco.setEmp(emp);
				sco.setDep(dep);
				sco.setPro(pro);

			}
			// 7关闭
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return sco;

	}

	public List<Score> searchDep2Pro(int depId) {
		List<Score> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4 建立statement sql语句执行器
			stat = conn.createStatement();
			// 5执行sql语句并得到结果
			String where = "";
			where += " where 1=1 ";
			if (depId != -1) {
				where += " and d.id= " + depId ;
			}
			String sql="select d.id as depId,p.id as proId,p.name as proName from department as d left join r_dep_pro on d.id=d_id  LEFT join project as p on p_id=p.id "  + where;
			rs = stat.executeQuery(sql);
			// 6对结果进行处理
			while (rs.next()) {
				Score sco = new Score();
				Project pro = new Project();
				pro.setId(rs.getInt("proId"));
				pro.setName(rs.getString("proName"));
				sco.setPro(pro);
				list.add(sco);
			}
			// 7关闭
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;

	}

	public List<Score> searchGrade() {
		List<Score> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4 建立statement sql语句执行器
			stat = conn.createStatement();
			// 5执行sql语句并得到结果
			rs = stat.executeQuery("select * from grade ");
			// 6对结果进行处理
			while (rs.next()) {

				Score sco = new Score();
				sco.setId(rs.getInt("Id"));
				sco.setGrade(rs.getString("grade"));
				list.add(sco);

			}
			// 7关闭
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;

	}

	public List<Score> search(Score condition, int begin, int size) {
		List<Score> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4 建立statement sql语句执行器
			stat = conn.createStatement();
			// 5执行sql语句并得到结果
			String where = " where 1=1";
			if (condition.getEmp().getName() != null && !condition.getEmp().getName().equals("")) {
				where += " and e.name='" + condition.getEmp().getName() + "'";
			}
			if (condition.getDep().getId() != -1) {
				where += " and d.id='" + condition.getDep().getId() + "'";
			}
			if (condition.getPro().getId() != -1) {
				where += " and p.id='" + condition.getPro().getId() + "'";
			}
			if (condition.getValue() != -1) {
				where += " and value=" + condition.getValue();
			}
			if (condition.getGrade() != null && !condition.getGrade().equals("")) {
				where += " and grade='" + condition.getGrade() + "'";
			}
			// System.out.println(begin + "===" + size);
			String sql = "select e.id as eId,e.name as eName,d.id as dId,d.name as dName,p.id as pId,p.name as pName,s.id as sId ,s.grade,s.value from employee as e left join score as s on e.id=s.e_id left join project as p on p.id=s.p_id LEFT JOIN department as d on e.d_id=d.id"
					+ where + " limit " + begin + "," + size;
			rs = stat.executeQuery(sql);
			// 6对结果进行处理
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("eId"));
				emp.setName(rs.getString("eName"));
				Department dep = new Department();
				dep.setId(rs.getInt("dId"));
				dep.setName(rs.getString("dName"));
				Project pro = new Project();
				pro.setId(rs.getInt("pId"));
				pro.setName(rs.getString("pName"));
				Score sco = new Score();
				sco.setId(rs.getInt("sId"));
				sco.setValue((Integer) rs.getObject("value"));
				sco.setGrade(rs.getString("grade"));

				sco.setEmp(emp);
				sco.setDep(dep);
				sco.setPro(pro);
				list.add(sco);
			}
			// 7关闭
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;

	}

	// public void save(Set<Score> set) {
	// for (Score sc : set) {
	// if (sc.getId() == 0) {
	// add(sc);
	// } else {
	// update(sc);
	// }
	// }
	// }

	public int add(Score sc) {
		int rs = 0;
		int id = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			// 4 建立statement sql语句执行器
			String sql = "insert into score(e_id,p_id,value) values(?,?,?) ";
			// 5执行sql语句并得到结果
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sc.getEmp().getId());
			pstat.setInt(2, sc.getPro().getId());
			pstat.setInt(3, sc.getValue());
			// 6对结果进行处理
			rs = pstat.executeUpdate();
			pstat.close();
			sql = "select last_insert_id()";
			pstat = conn.prepareStatement(sql);
			ResultSet r = pstat.executeQuery();
			if (r.next()) {
				id = r.getInt(1);
			}
			// 7关闭
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return id;
	}

	public boolean update(Score sc) {
		int rs = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			// 4 建立statement sql语句执行器
			String sql = "update score set value=? where id=?";
			// 5执行sql语句并得到结果
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sc.getValue());
			pstat.setInt(2, sc.getId());
			// 6对结果进行处理
			pstat.executeUpdate();
			rs = pstat.executeUpdate();
			// 7关闭
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return rs > 0;
	}

	public boolean updateBatch(List<Score> list) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			for (int i = 0; i < list.size(); i++) {
				Score sco = list.get(i);
				// ,d_id=?
				String sql = "update score set value=? where id=?";
				pstat = conn.prepareStatement(sql);
				pstat.setInt(1, sco.getValue());
				pstat.setInt(2, sco.getId());
				int rs = pstat.executeUpdate();
				if (rs > 0) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return flag;
	}

	public int searchCount(Score condition) {
		int count = 0;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4 建立statement sql语句执行器
			stat = conn.createStatement(); // 5执行sql语句并得到结果
			String where = " where 1=1";
			if (condition.getEmp().getName() != null && !condition.getEmp().getName().equals("")) {
				where += " and e.name='" + condition.getEmp().getName() + "'";
			}
			if (condition.getDep().getId() != -1) {
				where += " and d.id='" + condition.getDep().getId() + "'";
			}
			if (condition.getPro().getId() != -1) {
				where += " and p.id='" + condition.getPro().getId() + "'";
			}
			if (condition.getValue() != -1) {
				where += " and value=" + condition.getValue();
			}
			if (condition.getGrade() != null && !condition.getGrade().equals("")) {
				where += " and grade='" + condition.getGrade() + "'";
			}
			String sql = "select count(*) from employee as e left join score as s on e.id=s.e_id left join project as p on p.id=s.p_id LEFT JOIN department as d on e.d_id=d.id"
					+ where;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
			// 7关闭
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return count;
	}

}
