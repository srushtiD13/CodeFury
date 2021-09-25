package com.hsbc.controller;

import java.io.IOException;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.dao.ManagerDao;
import com.hsbc.daoImpl.ManagerDaoImpl;
import com.hsbc.daoImpl.TesterDaoImpl;
import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;
import com.hsbc.entity.User;

@WebServlet("/testermain")
public class TesterController extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ManagerDao dao = new ManagerDaoImpl();
		Project project1 = new Project();
		List<Bug> bugs;
		String operation = request.getParameter("operation");
		User loggedInUser =  new User();  //The object of the user logged in
		loggedInUser.setName("Tester1");
		loggedInUser.setEmailId("tester1@xyz.com");
		
		HttpSession session = request.getSession();
		
		if(operation==null) {
			
			session.setAttribute("user", loggedInUser);

			project1 =  dao.findProjectById(1);
			//		List<Project> projects= findProjectByTestor(loggedInUser); // function to interact with db 
			List<Project> projects = new ArrayList<Project>();
			projects.add(project1);

			if(projects.size()>0) {
				request.setAttribute("projects", projects);
				Map<Project, List<Bug>> dict= new HashMap<Project, List<Bug>>();
				for(Project project :projects)
				{
					//				bugs = findBugByProject(project); // Function that interact with db and give project list
					bugs = dao.findAllBug(project.getProjectId());
					dict.put(project, bugs);

				}
				request.setAttribute("dict", dict);
			}
			else {
				request.setAttribute("message","No projects assigned");			
			}
			String target="views/testerMainPage.jsp";      // url for next page
			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher(target); 

			rd.forward(request, response);
		}
		else if(operation.equals("reportbug")) {
			List<String> severity = new ArrayList<String>();
			severity.add("critical");
			severity.add("major");
			severity.add("minor");
			severity.add("trivial");
	
			session.setAttribute("severity", severity);
			session.setAttribute("employee", loggedInUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/reportBug.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operation = req.getParameter("operation");

		if(operation.equals("createbug")) {
			TesterDaoImpl dao;
			try {
				dao = new TesterDaoImpl();
				Bug bug = new Bug();
				Project project = new Project();
				int projectId = 1;										// this project id comes from main page of tester

				String projectName = req.getParameter("projectname");	// this project name comes from main page of tester
				String title = req.getParameter("title");
				String description = req.getParameter("description");
				String severity = req.getParameter("severity");

				bug.setBugName(title);
				bug.setDescription(description);
				bug.setCreatedBy(1);						//the id of current tester
				bug.setOpenDate(new Date(System.currentTimeMillis()));
				bug.setSeverityLevel(severity);
				project.setProjectName(projectName);
				project.setProjectId(projectId);

				try {
					dao.reportNewBug(bug, project);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				resp.sendRedirect("view/testerMainPage.jsp");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

		}

	}
}
