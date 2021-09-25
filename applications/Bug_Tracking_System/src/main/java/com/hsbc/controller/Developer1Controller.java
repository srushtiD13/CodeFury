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
import com.hsbc.dao.DeveloperDao;
import com.hsbc.dao.ProjectDao;
import com.hsbc.dao.UserDao;
import com.hsbc.daoImpl.BugDaoImpl;
import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;
import com.hsbc.exceptions.NoBugFoundException;
import com.hsbc.exceptions.NoProjectDetailsFound;
import com.hsbc.factory.BugDaoFactory;
import com.hsbc.factory.ProjectDaoFactory;

//This class is declared as Servlet controller for displaying Project and Bug details on the Developer Mainn Page.


@WebServlet("/developerController")
public class Developer1Controller extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		System.out.println("In Developer Controller to display Project and Bug details");
		
		System.out.println("In doGet Method");
		
		//System.out.println(req.getParameter("bugid"));
		
		ProjectDao projectdao = ProjectDaoFactory.getProjectDao();		//Declaring Data access object of DAO class of Project to fetch Project details.
		List<Project> allproject;
		try
		{
			allproject = projectdao.findAllProjects();					//Fetching the list of all Projects alloted to Developer by calling findAllProjects() method.
			req.setAttribute("project", allproject);					//Setting attribute as 'project' to shift control to JSP file  and passing list of projects.
		} 
		
		catch (NoProjectDetailsFound e)
		{
			
			e.printStackTrace();
		}		
		
		
		
		BugDao bugdao = BugDaoFactory.getBugDao();						//Declaring Data access object of DAO class of Bug to fetch Bug details.
		List<Bug> allBug = bugdao.findAllBugs();						//Fetching the list of all Bugs alloted to Developer by calling findAllBugs() method.
		
		
		req.setAttribute("bug", allBug);								//Setting attribute as 'bug' to shift control to JSP file  and passing list of bugs. 
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("developerMainPage.jsp");		////This continues with existing reqeust. Dispatching request.
		dispatcher.forward(req, resp);
		
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
//		int bugid = Integer.parseInt(req.getParameter("bugid"));
//		BugDao bugdao = BugDaoFactory.getBugDao();
//		System.out.println("In doPost");
//		System.out.println(bugid);
//		
//		try {
//			bugdao.closeBugById(bugid);
//				
//			
//			//req.setAttribute("bug", allBug);
//			RequestDispatcher dispatcher = req.getRequestDispatcher("closebug");		//this view we want to show on browser
//			dispatcher.forward(req, resp);
//		} catch (NoBugFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
		
		System.out.println("doPost");
		//1 Fetching user request ,converting request parameters to Java types
		int id = Integer.parseInt(req.getParameter("bugid"));
		
		try {
			BugDao bugdao = BugDaoFactory.getBugDao();
			Bug b = null;
			b = bugdao.closeBugById(id);
			if(b.isFlag()==true)
			{
				
				//RequestDispatcher dispatcher = req.getRequestDispatcher("employee");//This view we want to show
				//dispatcher.forward(req, resp);
				
				//This generates new Request
				resp.sendRedirect("bugController");
			}
			else
			{
				System.out.println("Not Closed");
				 
			}
		

		} catch (NoBugFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
}
