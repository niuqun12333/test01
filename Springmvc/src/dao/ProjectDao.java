package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import entity.Project;
import entity.Project;
import entity.Project;
import entity.Project;

public class ProjectDao extends BaseDao {
	public List<Project> search(Project condition, int begin, int size) {
		List<Project> list = new ArrayList<Project>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4 ����statement sql���ִ����
			stat = conn.createStatement(); // 5ִ��sql��䲢�õ����
			String where = "where 1=1";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and name like '%" + condition.getName() + "%'";
			}
			String sql = "select * from project " + where + " limit " + begin + "," + size;
			rs = stat.executeQuery(sql);
			// 6�Խ�����д���
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}
			// 7�ر�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public List<Project> search() {
		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4 ����statement sql���ִ����
			stat = conn.createStatement();
			// 5ִ��sql��䲢�õ����
			rs = stat.executeQuery("select * from project");
			// 6�Խ�����д���
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);

			}
			// 7�ر�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;

	}

	public boolean add(Project pro) {
		boolean flag = false;
		Connection conn = null;
		Statement stat = null;
		try {
			conn = getConnection();
			// 4 ����statement sql���ִ����
			stat = conn.createStatement();
			// 5ִ��sql��䲢�õ����
			int rs = stat.executeUpdate("insert into project(name) values('" + pro.getName() + "')");
			// 6�Խ�����д���
			if (rs > 0) {
				flag = true;
			}
			// 7�ر�

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return flag;
	}

	public boolean update(Project selectPro) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			// 4 ����statement sql���ִ����

			// 5ִ��sql��䲢�õ����
			String sql = "update project set name=? where id=? ";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, selectPro.getName());
			pstat.setInt(2, selectPro.getId());
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
			// 7�ر�

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return flag;
	}

	public boolean delete(int id) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			// 4 ����statement sql���ִ����
			// ����
			conn.setAutoCommit(false);
			String sql = "delete from project where id=?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, id);
			int rs = pstat.executeUpdate();
			pstat.close();
			sql = "delete from r_dep_pro where p_id=?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, id);
			rs = pstat.executeUpdate();
			conn.commit();
			if (rs > 0) {
				flag = true;
			}
			// 7�ر�

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return flag;
	}

	public List<Project> searchByCondition(Project condition) {
		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 4 ����statement sql���ִ����
			stat = conn.createStatement();
			// 5ִ��sql��䲢�õ����
			String where = "where 1=1";
			if (!condition.getName().equals("")) {
				where += " and name='" + condition.getName() + "'";
			}
			String sql = "select * from project " + where;
			rs = stat.executeQuery(sql);
			// 6�Խ�����д���
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
				list.add(pro);
			}
			// 7�ر�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;

	}

	public int searchCount(Project condition) {
		int count = 0;
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String where = "where 1=1";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and name='" + condition.getName() + "'";
			}
			rs = stat.executeQuery("select count(*) from project "+ where);
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return count;
	}

	public Project searchId(int id) {
		Project pro = new Project();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from project where id="+ id;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return pro;
	}

}
