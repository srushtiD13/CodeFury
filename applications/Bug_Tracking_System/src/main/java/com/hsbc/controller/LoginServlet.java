package com.hsbc.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import login.Login;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Login login;

	public void init() {
		try {
			login = new Login();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NullPointerException {
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role=null;
		
		
		 
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
		}
		
		try {
			
			role = login.getRole(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(role=="Developer")
		{
			response.sendRedirect("developerMainPage.jsp");
		}
		else if(role=="Tester")
		{
			response.sendRedirect("testerMainPage.jsp");
		}
		else if(role=="Manager")
		{
			response.sendRedirect("managerMainPage.jsp");
		}
		
		
	}
}