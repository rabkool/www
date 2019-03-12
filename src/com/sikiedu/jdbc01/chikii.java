package com.sikiedu.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class chikii {
	public static void main(String[] args) {
		selectAll(); 
		
		//System.out.println(selectByUsernamePassword( "yin","123456' or '1'='1" ));
		//System.out.println(selectByUsernamePassword( "yin","123456" ));
		//System.out.println(selectByUP2( "yin","123456' or '1'='1" ));
		//System.out.println(selectByUP2( "jf","123456" )); 
		
//		insert("name", "1");
		//delete(9); 
	/*	update(	1, "295(2)");*/
	} 

	/* 查询所有 */
	public static void selectAll() {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();

			stmt = con.createStatement();
			rs = stmt.executeQuery("select*from ck");

			while (rs.next()) {
//				System.out.println(rs.getInt(1) + "" + rs.getString(2) + "" + rs.getString(3));
				System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("num"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			/*
			 * catch (SQLException e) { // TODO Auto-generated catch block
			 * e.printStackTrace();
			 */
		} finally {
			JDBCUtils.close(rs, stmt, con);
		}
	}

	/* 根据用户名和密码查询 */
	public static boolean selectByUsernamePassword(String name, String num) {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();

			stmt = con.createStatement();
			String sql = " select * from ck where name ='" + name + "' and num = '" + num + "'";

			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			JDBCUtils.close(rs, stmt, con);
		}

		return false;
	}

	public static boolean selectByUP2(String name, String num) {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();

			String sql = "select * from ck where name = ? and num = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, num);

			rs = pstmt.executeQuery();
			if (rs.next())
				return true;
			else
				return false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, stmt, con);
		}

		return false;

	}

	/* 按页查询 */
	public static void selectUserByPage(int pageNumber, int pageCount) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();

			stmt = con.prepareStatement("select*from ck ck limit ?,?");
			stmt.setInt(1, (pageNumber - 1) * pageCount);
			stmt.setInt(2, pageCount);

			rs = stmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getString("num"));
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			JDBCUtils.close(rs, stmt, con);
		}
	}

	/* 插入操作 */
	public static void insert(String name, String num) {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();

			String sql = "insert into ck(name,num) value(?,?)";
			stmt = con.prepareCall(sql);
			stmt.setString(1, name);
			stmt.setString(2, num);
			int result = stmt.executeUpdate();// 返回影响的行数
			if (result > 0) {
				System.out.println("插入成功");
			} else {
				System.out.println("插入失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, stmt, con);
		}
	}

	/* 删除操作 */
	public static void delete(int id) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();

			String sql = "delete from ck where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			int result = stmt.executeUpdate();
			if (result > 0) {
				System.out.println("成功");
			} else {
				System.out.println("失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, stmt, con);
		}
	}

	/* 修改操作 */
	public static void update(int id, String newnum) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();

			String sql = "update ck set num = ? where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, newnum);
			stmt.setInt(2, id);
			int result = stmt.executeUpdate();
			if (result > 0) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, stmt, con);
		}
	}

}
