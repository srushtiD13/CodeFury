package com.hsbc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.dao.IndexDao;
import com.hsbc.daoImpl.IndexDaoImpl;





@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndexDao login ;

	public void init() {
		try {
			login = new IndexDaoImpl();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NullPointerException {
		
		RequestDispatcher dispatcher = null;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role=null;
		
		System.out.println(username );
		 
		try{
				if (login.validate(username, password)) {
			
				//HttpSession session = request.getSession();
				// session.setAttribute("username",username);
				
					//response.sendRedirect("LoginSuccess.jsp");
				}
			 else {
				HttpSession session = request.getSession();
				System.out.println("user not found");
				//session.setAttribute("user", username);
				//response.sendRedirect("login.jsp");
			}
		}
		catch(NullPointerException e) {
			System.out.println(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			role = login.getRole(username);
			System.out.println(role);
			
		} catch (SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id = login.getIdByEmail(username);
		if(role.equalsIgnoreCase("Developer"))
		{
			/*request.setAttribute("user_id", id);
			dispatcher = request.getRequestDispatcher("developerMainPage.jsp");
			dispatcher.forward(request,response);*/
			response.sendRedirect("developer?user_id="+id);
		}
		else if(role.equalsIgnoreCase("Tester"))
		{
			
			/*request.setAttribute("user_id", id);
			dispatcher = request.getRequestDispatcher("testerMainPage.jsp");
			dispatcher.forward(request,response);*/
			response.sendRedirect("testermain?user_id="+id);
		}
		else if(role.equalsIgnoreCase("ProjectManager"))
		{
			/*
			 * System.out.println("in else if  id : "+id); request.setAttribute("user_id",
			 * id); dispatcher = request.getRequestDispatcher("manager?user_id="+id);
			 * dispatcher.forward(request,response);
			 */
			response.sendRedirect("manager?user_id="+id);
		}
		
		
	}
}