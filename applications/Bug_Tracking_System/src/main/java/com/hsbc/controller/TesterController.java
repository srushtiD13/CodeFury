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
import com.hsbc.dao.TesterDao;
import com.hsbc.daoImpl.ManagerDaoImpl;
import com.hsbc.daoImpl.TesterDaoImpl;
import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;
import com.hsbc.entity.User;

@WebServlet("/testermain")
public class TesterController extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Project project1 = new Project();
		List<Bug> bugs;
		String operation = request.getParameter("operation");
		System.out.println("Tester Controller : "+request.getParameter("user_id"));
		int testerId = Integer.parseInt(request.getParameter("user_id"));
		System.out.println("in tester controller"+testerId);
		HttpSession session = request.getSession();
		TesterDao dao = new TesterDaoImpl();
		ManagerDao p = new ManagerDaoImpl();
		User user = p.getUserById(testerId);
		System.out.println(user.toString());
		System.out.println("Manager role : "+user.getRole());
		System.out.println("Manager LastLogin : "+user.getLastLogin());
		user.setUserId(testerId );
		session.setAttribute("user", user);
		
		if(operation==null) {

			List<Project> projects =  dao.findProjectByTestor(testerId);
			System.out.println("project conrtroller"+projects);
			if(projects.size()>0) {
				request.setAttribute("projects", projects);
				Map<Project, List<Bug>> dict= new HashMap<Project, List<Bug>>();
				for(Project project :projects)
				{
					//				bugs = findBugByProject(project); // Function that interact with db and give project list
					bugs = dao.findBugByProject(project.getProjectId());
					dict.put(project, bugs);

				}
				request.setAttribute("dict", dict);
			}
			else {
				request.setAttribute("message","No projects assigned");			
			}
			String target="testerMainPage.jsp";      // url for next page
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
			List<Project> projects =  dao.findProjectByTestor(testerId);
			session.setAttribute("projects", projects);
			RequestDispatcher dispatcher = request.getRequestDispatcher("testerReportBug.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operation = req.getParameter("operation");

		if(operation.equals("createbug")) {
			TesterDaoImpl dao;
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

			dao.reportNewBug(bug, project);

			resp.sendRedirect("testerMainPage.jsp");
			

		}

	}
}
