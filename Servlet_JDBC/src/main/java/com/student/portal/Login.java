package com.student.portal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String sName=request.getParameter("sname");
		String sPassword=request.getParameter("password");
		
		
		Model model=new Model();
		model.setStudentName(sName);
		model.setPassword(sPassword);
		
		model.login();
		
		String dbUser= model.getStudentName();
		String dbPassword=model.getPassword();
		
		if(sName.equals(dbUser) && sPassword.equals(dbPassword)) {
			System.out.println("Login sucessfull");
			response.sendRedirect("/MVC_Servlet_JDBC/loginSucess.html");
		}
		else{
			System.out.println("Login Failed!");
			response.sendRedirect("/MVC_Servlet_JDBC/loginFailed.html");
		}

	}

}
