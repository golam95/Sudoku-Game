package com.rocky.sudoku.dao;

public interface DBIntializer {
	String DRIVER = "com.mysql.jdbc.Driver";
	String CON_STRING = "jdbc:mysql://localhost:3306/sudoku_db?useSSL=true";
	String USERNAME = "root";
	String PASSWORD = "";
}
