package com.hsbc.controller;
/**
 *  Displays the details of selected project along with all bugs present.
 *  The project manager can close a particular bug if it is marked to be closed by assigned developer
 *  The manager can assign the bug to any of the developer from that project only
 */
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;
import com.hsbc.entity.User;
import com.hsbc.daoImpl.ManagerDaoImpl;
import com.hsbc.dao.ManagerDao;

@WebServlet("/projectdetails")
public class ProjectController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ManagerDao dao = new ManagerDaoImpl();
		Project project = new Project();
		User employee = new User();
		List<Bug> bugs = new ArrayList<Bug>();

		String operation = req.getParameter("operation");
		
		// this projectId will come from main page of manager
		int projectId = Integer.parseInt(req.getParameter("projectId"));

		HttpSession session = req.getSession();

		if(operation==null||operation.trim().length()==0) {
			project = dao.findProjectById(projectId);
			bugs = dao.findBugByProject(projectId);
			List<User> developer = new ArrayList<User>();
			for(int i:project.getDeveloperId()) {
				developer.add(dao.getUserById(i));
			}
			developer.add(dao.getUserById(project.getTesterId()));

			session.setAttribute("developers", developer);
			session.setAttribute("project", project);
			session.setAttribute("bug", bugs);
			RequestDispatcher dispatcher = req.getRequestDispatcher("projectDetails.jsp");
			dispatcher.forward(req, resp);
		}
		else if(operation.equals("assign")) {
			int bugId = Integer.parseInt(req.getParameter("bugId"));
			project = dao.findProjectById(projectId);			
			List<User> developer = new ArrayList<User>();
			for(int i:project.getDeveloperId()) {
				developer.add(dao.getUserById(i));
			}

			session.setAttribute("developers", developer);
			session.setAttribute("bugid", bugId);
			session.setAttribute("projectId", projectId);
			//resp.sendRedirect("projectDetails.jsp");
			RequestDispatcher dispatcher = req.getRequestDispatcher("assignto.jsp");
			dispatcher.forward(req, resp);
		}
		else if(operation.equals("filter")) {
			bugs = dao.findAllBugSorted(projectId);
			for(Bug b:bugs)
				System.out.println(b.getMarkedForClosing());
			session.setAttribute("bug", bugs);
			resp.sendRedirect("projectDetails.jsp");
//			RequestDispatcher dispatcher = req.getRequestDispatcher("projectDetails.jsp");
//			dispatcher.forward(req, resp);
		}
		else if(operation.equals("close")){
			int uniqueId = Integer.parseInt(req.getParameter("bugId"));
			dao.closeBug(uniqueId, projectId);
//			bugs = dao.findAllBug(projectId);
			session.setAttribute("bug", bugs);
			RequestDispatcher dispatcher = req.getRequestDispatcher("projectDetails.jsp");
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
			int developerId = Integer.parseInt(req.getParameter("developer"));
			int bugId = Integer.parseInt(req.getParameter("bugId"));

			
			
			// TO-DO call function to set developer as assigned for the bug by passing parameters: developer name and bugid to function
			//if the bug is marked as not marked for closing then assign it to developer by checking the parameter asigned_to in database
			ManagerDao dao;
			try {
				dao = new ManagerDaoImpl();
				dao.assignBug(bugId ,developerId );
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//resp.sendRedirect("projectDetails.jsp");

			System.out.println(developerId);
			System.out.println(bugId);
			
			// TO-DO call function to set developer as assigned for the bug by passing parameters: developer name and bugid to function
			//if the bug is marked as not marked for closing then assign it to developer by checking the parameter asigned_to in database
			dao.assignBug(bugId ,developerId );
			resp.sendRedirect("projectDetails.jsp");

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