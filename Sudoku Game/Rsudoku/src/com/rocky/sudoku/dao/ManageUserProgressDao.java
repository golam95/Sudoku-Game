package com.rocky.sudoku.dao;

import java.sql.SQLException;
import com.rocky.sudoku.model.UserProgress;

public interface ManageUserProgressDao {

	public void add_userprogress(UserProgress addprogress) throws SQLException;

	public void updateprogressLevelOne(UserProgress levelOne) throws SQLException;

	public void updateprogressLevelTwo(UserProgress levelTwo) throws SQLException;

	public void updateprogressLevelThree(UserProgress levelThree) throws SQLException;

	public boolean check_playerName(String check_playername) throws SQLException;
	
	public void delete_user(String id) throws SQLException;

}
