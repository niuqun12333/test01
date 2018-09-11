package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;

import entity.User;

public class UserDao extends BaseDao {

	public boolean search(User user) {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			conn = getConnection();
			String sql = "select * from user where username=? and pwd=?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getUsername());
			pstat.setString(2, user.getPassword());
			rs = pstat.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, rs);
		}
		return flag;
	}

	public boolean add(User user) {
		Connection conn = null;
		PreparedStatement pstat = null;
		int rs = 0;
		boolean flag = false;
		try {
			conn = getConnection();
			String sql = "insert into user(username,pwd) values(?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, user.getUsername());
			pstat.setString(2, user.getPassword());
			rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return flag;
	}

	public void updateNum(ServletContext application) {
		int rs = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = getConnection();
			String sql = "update num set num=?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1,(Integer) application.getAttribute("anum"));
			rs = pstat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
	}

	public int searchNum() {
		int num = 0;
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select num from num";
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			if(rs.next()) {
				num=rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, rs);
		}
		return num;
	}
}
