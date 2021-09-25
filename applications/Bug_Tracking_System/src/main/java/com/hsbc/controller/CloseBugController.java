package com.hsbc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.dao.BugDao;
import com.hsbc.entity.Bug;
import com.hsbc.exceptions.NoBugFoundException;
import com.hsbc.factory.BugDaoFactory;

//This class is used as Servlet Controller for closing the bug.


@WebServlet("/closebug")
public class CloseBugController extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int bugid = Integer.parseInt(req.getParameter("bugid"));
		BugDao bugdao = BugDaoFactory.getBugDao();
		List<Bug> allBug = bugdao.findAllBugs();
		System.out.println("In doGet, CloseBugController");
		System.out.println(bugid);

		try {
			bugdao.closeBugById(bugid);


			req.setAttribute("bug", allBug);
			RequestDispatcher dispatcher = req.getRequestDispatcher("bugController");		//this view we want to show on browser
			dispatcher.forward(req, resp);
		} catch (NoBugFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost");
		
		int id = Integer.parseInt(req.getParameter("bugid"));			//Fetching user request ,converting request parameters to Java types


		

		try {
			BugDao bugdao = BugDaoFactory.getBugDao();		//Delegating to appropriate Dao class
			Bug b = null;									//Creating required Java Object
			b = bugdao.closeBugById(id);
			if(b.isFlag()==true)
			{												//if block to check condition whether the bug is marked for close or not
				
				resp.sendRedirect("bugController");			//if marked for closing then redirect the control to main bugController servlet.
			}
			else
			{
				System.out.println("Not Closed");			//else  mark as not closed

			}


		} catch (NoBugFoundException e) {					//Encounters when no bug details are found.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}