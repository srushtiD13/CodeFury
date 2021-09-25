package com.hsbc.codefury.controller;
/**
 *  Displays the details of selected project along with all bugs present.
 *  The project manager can close a particular bug if it is marked to be closed by assigned developer
 *  The manager can assign the bug to any of the developer from that project only
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.codefury.dao.ProjectDao;
import com.hsbc.codefury.daoimpl.ProjectDaoImpl;
import com.hsbc.codefury.entity.Bug;
import com.hsbc.codefury.entity.Project;
import com.hsbc.codefury.entity.User;

@WebServlet("/projectdetails")
public class ProjectController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProjectDao dao = new ProjectDaoImpl();
		Project project = new Project();
		User employee = new User();
		List<Bug> bugs = new ArrayList<Bug>();

		String operation = req.getParameter("operation");
		
		// this projectId will come from main page of manager
		int projectId = Integer.parseInt(req.getParameter("projectId"));
		//int projectId = 1;

		HttpSession session = req.getSession();

		if(operation==null||operation.trim().length()==0) {
			project = dao.findProjectById(projectId);
			bugs = dao.findAllBug(projectId);
			List<String> dev = new ArrayList<String>();
			dev.add("Developer1");
			dev.add("Developer2");
			dev.add("Developer3");
			String team[] = new String[]{"Amit","Sachin","Aman"};
			employee.setTeam(team);

			session.setAttribute("developers", dev);session.setAttribute("developers", dev);
			session.setAttribute("project", project);
			session.setAttribute("employee", employee);
			session.setAttribute("bug", bugs);
			RequestDispatcher dispatcher = req.getRequestDispatcher("views/projectDetails.jsp");
			dispatcher.forward(req, resp);
		}
		else if(operation.equals("assign")) {
			int bugId = Integer.parseInt(req.getParameter("bugId"));
			List<String> dev = new ArrayList<String>();
			dev.add("Developer1");
			dev.add("Developer2");
			dev.add("Developer3");
			
			session.setAttribute("developers", dev);
			session.setAttribute("bugid", bugId);
			session.setAttribute("projectId", projectId);
			//resp.sendRedirect("projectDetails.jsp");
			RequestDispatcher dispatcher = req.getRequestDispatcher("views/assignto.jsp");
			dispatcher.forward(req, resp);
		}
		else if(operation.equals("filter")) {
			bugs = dao.findAllBugSorted(projectId);
			for(Bug b:bugs)
				System.out.println(b.getMarkedForClosing());
			session.setAttribute("bug", bugs);
			resp.sendRedirect("views/projectDetails.jsp");
//			RequestDispatcher dispatcher = req.getRequestDispatcher("projectDetails.jsp");
//			dispatcher.forward(req, resp);
		}
		else if(operation.equals("close")){
			int uniqueId = Integer.parseInt(req.getParameter("bugId"));
			dao.closeBug(projectId, uniqueId);
			bugs = dao.findAllBug(projectId);
			session.setAttribute("bug", bugs);
			RequestDispatcher dispatcher = req.getRequestDispatcher("views/projectDetails.jsp");
			dispatcher.forward(req, resp);
			System.out.println("out");
		}
		else {
			System.out.println("out");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String operation = req.getParameter("operation");
		
		if(operation!=null && operation.equals("assign")) {
			String dev = req.getParameter("developer");
			int bugId = Integer.parseInt(req.getParameter("bugId"));
			System.out.println(dev);
			System.out.println(bugId);
			
			// TO-DO call function to set developer as assigned for the bug by passing parameters: developer name and bugid to function
			//if the bug is marked as not marked for closing then assign it to developer by checking the parameter asigned_to in database
			
			resp.sendRedirect("views/projectDetails.jsp");
//			RequestDispatcher dispatcher = req.getRequestDispatcher("projectDetails.jsp");
//			dispatcher.forward(req, resp);
		}
		
		
	}
}




/*project.setProjectManagerId(12);
project.setProjectName("Java Web Project");
project.setDescription("This project is related to web");
project.setProjectId(1);
//long millis=System.currentTimeMillis();  
//project.setStartDate(new java.sql.Date(millis));
project.setStartDate(Date.valueOf("2021-09-18"));
project.setStatus("in-progress");*/