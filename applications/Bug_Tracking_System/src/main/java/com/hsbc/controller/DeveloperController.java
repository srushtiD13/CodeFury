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
import javax.servlet.http.HttpSession;


import com.hsbc.dao.DeveloperDao;
import com.hsbc.daoImpl.DeveloperDaoImpl;
import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;
import com.hsbc.exceptions.NoBugFoundException;
import com.hsbc.exceptions.NoProjectDetailsFound;

//This class is declared as Servlet controller for displaying Project and Bug details on the Developer Mainn Page.

@WebServlet("/developer")
public class DeveloperController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String operation = req.getParameter("operation");
		int developerId = Integer.parseInt(req.getParameter("user_id"));
		
		DeveloperDao dao = null;													//Declaring Data access object of DAO class of Bug to fetch Bug details.
		try {

			dao = new DeveloperDaoImpl();		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(operation==null) {
		
			List<Project> allproject;
			allproject = dao.findProjectByDeveloperId(developerId);					//Fetching the list of all Projects alloted to Developer by calling findAllProjects() method.
			System.out.println(allproject);

			List<Bug> allBug = dao.findBugByDeveloperId(developerId);						//Fetching the list of all Bugs alloted to Developer by calling findAllBugs() method.

			req.setAttribute("bugs", allBug);								//Setting attribute as 'bug' to shift control to JSP file  and passing list of bugs. 
			req.setAttribute("projects", allproject);					//Setting attribute as 'project' to shift control to JSP file  and passing list of projects.		
			RequestDispatcher dispatcher = req.getRequestDispatcher("developerMainPage.jsp");		////This continues with existing reqeust. Dispatching request.
			dispatcher.forward(req, resp);
		}
		else if(operation.equals("closebug")) {
			int projectid = Integer.parseInt(req.getParameter("projectid"));
			int bugid = Integer.parseInt(req.getParameter("bugid"));
			
			
			dao.closeBug(bugid);
			
//			resp.sendRedirect("views/developerMainPage.jsp");
			RequestDispatcher dispatcher = req.getRequestDispatcher("developerMainPage.jsp");		////This continues with existing reqeust. Dispatching request.
			dispatcher.forward(req, resp);
		}
	}

}
