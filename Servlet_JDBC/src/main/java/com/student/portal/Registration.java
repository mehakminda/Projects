package com.student.portal;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Inside Registration Servlet");
		String sName=request.getParameter("sname");
		String sNumber=request.getParameter("snumber");
		String sCity=request.getParameter("scity");
		String sPassword=request.getParameter("password");
		String sConfirmPassword=request.getParameter("cpassword");
		
		if(sPassword.equals(sConfirmPassword)) {
			
			Model model=new Model();
			model.setStudentName(sName);
			model.setStudentCity(sCity);
			model.setStudentNumber(sNumber);
			model.setPassword(sPassword);
			int row=model.registerStudent();
			
			if(row!=0) {
				System.out.println("Registration sucessful");
				response.sendRedirect("/MVC_Servlet_JDBC/success_register.html");
			}
			else {
				System.out.println("Registration failed");
				response.sendRedirect("/MVC_Servlet_JDBC/fail_register.html");
			}
			
		}	
		else { 
			System.out.println("Registration failed");
			response.sendRedirect("/MVC_Servlet_JDBC/re_register.html");
		}

		
		
		
	}

}
