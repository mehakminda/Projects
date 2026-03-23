package com.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CounterServlet
 */
@WebServlet("/CounterServlet")
public class CounterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CounterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long count=Long.parseLong(request.getParameter("count"));
		for (long i=0;i<=count;i++) {
			response.getWriter().print(i+", ");
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Servlet : "+this.getServletName()+ " , thread is  "+ Thread.currentThread().getName()+" and Count is : "+i);
		}

	}



}
/*
 * In order to test this, make call to this servlet twice from different browser tab with some count number (till 50 will work)
 * and see how both the request is being processed. : http://localhost:8080/ServletCounter_MultipleHTTP/
 * 
 * For each http request a thread is created.. http-nio-8080-exec-<x>
 * */
 