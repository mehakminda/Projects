package com.student.portal.util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcApp {
	private Integer id;
	private String vname;
	private String vcompany;
	private String vcost;
	Connection conn = null;
	PreparedStatement pstmt = null;
	String query = "Insert into VaccineDetails (VaccineName, VaccineCompany, VaccineCost) values (?,?,?)";
	int rowAffected;

	public int getRowAffected() {
		return rowAffected;
	}

	public void setRowAffected(int rowAffected) {
		this.rowAffected = rowAffected;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getvcompany() {
		return vcompany;
	}

	public void setvcompany(String vcompany) {
		this.vcompany = vcompany;
	}

	public String getVcost() {
		return vcost;
	}

	public void setVcost(String vcost) {
		this.vcost = vcost;
	}

	@Override
	public String toString() {
		return "JavaApp [id=" + id + ", vname=" + vname + ", vcompany=" + vcompany + ", vcost=" + vcost + "]";
	}


	public void registerVaccine() {

		try {
			System.out.println(vname+", "+vcompany+", "+vcost+" : Control in jdbc app");
			conn = JDBCUtility.getDbConnection();
			if (conn != null)
				pstmt = conn.prepareStatement(query);
			if (pstmt != null) {
				pstmt.setString(1, vname);
				pstmt.setString(2, vcompany);
				pstmt.setInt(3, Integer.parseInt(vcost));
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

	}

}

/* Creating table and inserting data inside table
* 
* use testdb;

	CREATE TABLE VaccineDetails (
	    id INT AUTO_INCREMENT PRIMARY KEY,
	    VaccineName VARCHAR(100) NOT NULL ,
	    VaccineCompany VARCHAR(100) NOT NULL UNIQUE,
	    VaccineCost VARCHAR(100) NOT NULL UNIQUE
	);

*/
