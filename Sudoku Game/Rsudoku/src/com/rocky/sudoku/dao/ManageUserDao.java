package com.rocky.sudoku.dao;

import java.sql.SQLException;
import com.rocky.sudoku.model.User;

public interface ManageUserDao {

	public void add_playerInfo(User info) throws SQLException;

	public boolean check_playerName(String check_playername) throws SQLException;

	public boolean checklogin(String name) throws SQLException;
	
	public void delete_user(String id) throws SQLException;

}
