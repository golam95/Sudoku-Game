package com.rocky.sudoku.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rocky.sudoku.dao.ManageUserDao;
import com.rocky.sudoku.model.User;

public class ManageUserDaoImp implements ManageUserDao {

	@Override
	public void add_playerInfo(User info) throws SQLException {

		Connection con = DBConnection.getConnecttion();
		String sql = "Insert into user value(?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, info.getUserId());
			ps.setString(2, info.getUserName());
			ps.setString(3, info.getUserAge());
			ps.setString(4, info.getUserGender());
			ps.setString(5, info.getUserDate());
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	@Override
	public boolean check_playerName(String check_playername) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from user where user_name='" + check_playername + "'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(check_user);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				con.close();
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return false;
	}

	@Override
	public boolean checklogin(String name) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from user where user_name='" + name + "'";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(check_user);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				con.close();
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return false;
	}

	@Override
	public void delete_user(String id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from user where user_name='" + id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
