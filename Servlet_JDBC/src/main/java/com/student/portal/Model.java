package com.student.portal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.student.portal.util.JDBCUtility;

public class Model {
	
	private String studentName;
	private String studentCity;
	private String studentNumber;
	private String password;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	String registerQuery = "Insert into Servlet_StudentPortal (studentname,number,city, password) values (?,?,?,?)";
	String loginQuery = "select studentname,password from Servlet_StudentPortal where studentname=?";
	int rowAffected;
	ResultSet rs=null;
	
	

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentCity() {
		return studentCity;
	}

	public void setStudentCity(String studentCity) {
		this.studentCity = studentCity;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	@Override
	public String toString() {
		return "Model [studentName=" + studentName + ", studentCity=" + studentCity + ", studentNumber=" + studentNumber
				+ ", password=" + password + "]";
	}

	public int registerStudent() {
		try {
			System.out.println(studentName+", "+studentCity+", "+studentNumber+" : Control in Model class");
			conn = JDBCUtility.getDbConnection();
			if (conn != null)
				pstmt = conn.prepareStatement(registerQuery);
			if (pstmt != null) {
				pstmt.setString(1, studentName);
				pstmt.setInt(2, Integer.parseInt(studentName));
				pstmt.setString(3, studentCity);
				pstmt.setString(4, password);
			}
			 this.rowAffected = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtility.closeResources(conn, null, pstmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rowAffected;
	}
	
	public void login() {
		try {
			System.out.println(studentName+", "+studentCity+", "+studentNumber+" : Control in Model class");
			conn = JDBCUtility.getDbConnection();
			if (conn != null)
				pstmt = conn.prepareStatement(loginQuery);
			if (pstmt != null) {
				pstmt.setString(1, studentName);
			}
			 rs = pstmt.executeQuery();
			 while(rs.next()) {
				 studentName= rs.getString("studentname");
				 password= rs.getString("password");
			 }
			 System.out.println("Done with login verification");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCUtility.closeResources(conn, null, pstmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
	
}
