package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Dep2Pro;
import entity.Department;
import entity.Employee;
import entity.Project;

public class Project2DpatmentDao extends BaseDao {

	public List<Project> searchByDepartment(int depId) {
		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "SELECT d.*,p.id as pId,p.name as pName FROM project as p LEFT JOIN v_dep_pro as v on p.id=v.p_id LEFT JOIN department as d on d.id=v.d_id where d.id="
					+ depId;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("pId"));
				pro.setName(rs.getString("pName"));
				list.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public List<Project> searchByNotDepartment(int depId) {
		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "SELECT * from project where id not IN(SELECT p.id as pId FROM project as p LEFT JOIN v_dep_pro as v on p.id=v.p_id LEFT JOIN department as d on d.id=v.d_id where d.id="
					+ depId + ") ";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("Id"));
				pro.setName(rs.getString("Name"));
				list.add(pro);
			}
			// 7¹Ø±Õ
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public boolean add(int depId, int proId) {

		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "insert into r_dep_pro(d_id,p_id) values(" + depId + "," + proId + ")";
			rs = stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}

		return rs > 0;
	}

	public boolean addBatch(List<Dep2Pro> list) {
		boolean flag = false;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			for (int i = 0; i < list.size(); i++) {
				Dep2Pro dep2pro = list.get(i);
				String sql = "insert into r_dep_pro(d_id,p_id) values(?,?)";
				pstat = conn.prepareStatement(sql);
				pstat.setInt(1, dep2pro.getDepId());
				pstat.setInt(2, dep2pro.getProId());
				int rs = pstat.executeUpdate();
				if (rs > 0) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}

		return flag;
	}

	public boolean delete(int depId, int proId) {

		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "delete from r_dep_pro where d_id=" + depId + " and p_id=" + proId;
			rs = stat.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}

		return rs > 0;
	}

	public boolean deleteBatch(List<Dep2Pro> list) {

		Connection conn = null;
		PreparedStatement pstat = null;
		boolean flag = false;
		try {
			conn = getConnection();
			for (int i = 0; i < list.size(); i++) {
				Dep2Pro dep2pro = list.get(i);
				String sql = "delete from r_dep_pro where d_id=? and p_id=?" ;
				pstat = conn.prepareStatement(sql);
				pstat.setInt(1, dep2pro.getDepId());
				pstat.setInt(2, dep2pro.getProId());
				int rs = pstat.executeUpdate();
				if (rs > 0) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}

		return flag;
	}

}
