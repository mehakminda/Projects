package com.student.portal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtility {

	  static {
	        try {
	            // Explicitly load the MySQL driver (helps with classloader issues in servlets)
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            throw new RuntimeException("MySQL driver not found. Ensure mysql-connector-j is in WEB-INF/lib.", e);
	      
	        }
	  }
	        
	public static void closeResources(Connection conn, ResultSet rs, Statement stmt) throws SQLException {
		if(stmt!=null) stmt.close();
		if(conn!=null) conn.close();
		if(rs!=null) rs.close();
		
	}
	public static Connection getDbConnection() throws SQLException {
		String url="jdbc:mysql://localhost:3306/testdb";
        String uname="testuser";
        String password="OpenText123$";
		Connection conn=DriverManager.getConnection(url, uname,password);
		return conn;
	}
}
