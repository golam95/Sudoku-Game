package com.rocky.sudoku.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rocky.sudoku.dao.ManageUserProgressDao;
import com.rocky.sudoku.model.UserProgress;

public class ManageUserProgressDaoImp implements ManageUserProgressDao {

	@Override
	public void add_userprogress(UserProgress addprogress) throws SQLException {

		Connection con = DBConnection.getConnecttion();
		String sql = "Insert into progress value(?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, addprogress.getProgressId());
			ps.setString(2, addprogress.getProgressName());
			ps.setString(3, addprogress.getProgressLevel1());
			ps.setString(4, addprogress.getProgressLevel2());
			ps.setString(5, addprogress.getProgressLevel3());
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}

	}

	@Override
	public boolean check_playerName(String check_playername) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String check_user = "select * from progress where p_name='" + check_playername + "'";
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
	public void updateprogressLevelOne(UserProgress levelOne) throws SQLException {

		Connection con = DBConnection.getConnecttion();
		String user_update = "Update progress set p_level1 =? where p_name=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_update);
			ps.setString(1, levelOne.getProgressLevel1());
			ps.setString(2, levelOne.getProgressName());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateprogressLevelTwo(UserProgress levelTwo) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_update = "Update progress set p_level1 =? where p_name=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_update);
			ps.setString(1, levelTwo.getProgressLevel1());
			ps.setString(2, levelTwo.getProgressName());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateprogressLevelThree(UserProgress levelThree) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_update = "Update progress set p_level1 =? where p_name=?";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_update);
			ps.setString(1, levelThree.getProgressLevel1());
			ps.setString(2, levelThree.getProgressName());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete_user(String id) throws SQLException {
		Connection con = DBConnection.getConnecttion();
		String user_delete = "Delete from progress where p_name='" + id + "'";
		try {
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(user_delete);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
