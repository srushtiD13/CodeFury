package com.hsbc.codefury.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.codefury.dao.BugDao;
import com.hsbc.codefury.dao.ProjectDao;
import com.hsbc.codefury.daoimpl.BugDaoImpl;
import com.hsbc.codefury.daoimpl.ProjectDaoImpl;
import com.hsbc.codefury.entity.Bug;
import com.hsbc.codefury.entity.Project;
import com.hsbc.codefury.exception.NoBugFoundException;
import com.hsbc.codefury.exception.NoProjectDetailsFoundException;

//This class is declared as Servlet controller for displaying Project and Bug details on the Developer Mainn Page.

@WebServlet("/developer")
public class DeveloperController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String operation = req.getParameter("operation");

		if(operation==null) {
			ProjectDao projectdao = new ProjectDaoImpl();	//Declaring Data access object of DAO class of Project to fetch Project details.
			List<Project> allproject;
			allproject = projectdao.findAllProjects();					//Fetching the list of all Projects alloted to Developer by calling findAllProjects() method.
			System.out.println(allproject);

			BugDao bugdao = new BugDaoImpl();						//Declaring Data access object of DAO class of Bug to fetch Bug details.
			List<Bug> allBug = bugdao.findAllBugs();						//Fetching the list of all Bugs alloted to Developer by calling findAllBugs() method.

			req.setAttribute("bugs", allBug);								//Setting attribute as 'bug' to shift control to JSP file  and passing list of bugs. 
			req.setAttribute("projects", allproject);					//Setting attribute as 'project' to shift control to JSP file  and passing list of projects.		
			RequestDispatcher dispatcher = req.getRequestDispatcher("views/developerMainPage.jsp");		////This continues with existing reqeust. Dispatching request.
			dispatcher.forward(req, resp);
		}
		else if(operation.equals("closebug")) {
			int projectid = Integer.parseInt(req.getParameter("projectid"));
			int bugid = Integer.parseInt(req.getParameter("bugid"));
			BugDao bug = new BugDaoImpl();
			
			bug.closeBugById(bugid,projectid);
			
//			resp.sendRedirect("views/developerMainPage.jsp");
			RequestDispatcher dispatcher = req.getRequestDispatcher("views/developerMainPage.jsp");		////This continues with existing reqeust. Dispatching request.
			dispatcher.forward(req, resp);
		}
	}

}
