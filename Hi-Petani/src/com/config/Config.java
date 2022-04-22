package com.config;
import java.sql.*;

public final class Config {
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/HiPetani";
	private static String ROOT = "root";
	private static String PASSWORD = "";
	
	// method untuk koneksi ke database
	public static Connection connection() throws ClassNotFoundException, SQLException{
		
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, ROOT, PASSWORD);
			return conn;
	
	}
}
