package com.hsbc.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.dao.DeveloperDao;
import com.hsbc.daoImpl.DeveloperDaoImpl;
import com.hsbc.entity.Bug;
import com.hsbc.exceptions.NoBugFoundException;

//This class is used as Servlet Controller for closing the bug.


@WebServlet("/closebug")
public class CloseBug extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int bugid = Integer.parseInt(req.getParameter("bug_id"));
		DeveloperDao bugdao = null;
		try {
			bugdao = new DeveloperDaoImpl();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//List<Bug> allBug = bugdao.findAllBugs();
//		System.out.println("In doGet, CloseBugController");
//		System.out.println(bugid);

		bugdao.closeBug(bugid);


		//req.setAttribute("bug", allBug);
		/*
		 * RequestDispatcher dispatcher = req.getRequestDispatcher("developer"); //this
		 * view we want to show on browser dispatcher.forward(req, resp);
		 */
	}



	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost");
		
		int id = Integer.parseInt(req.getParameter("id"));			//Fetching user request ,converting request parameters to Java types

		DeveloperDao bugdao = null;
		try {
			bugdao = new DeveloperDaoImpl();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		//Delegating to appropriate Dao class
										//Creating required Java Object
		bugdao.closeBug(id);	
			resp.sendRedirect("developer");			//if marked for closing then redirect the control to main bugController servlet.
	}
}