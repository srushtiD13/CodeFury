package com.hsbc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.dao.ManagerDao;
import com.hsbc.daoImpl.ManagerDaoImpl;
import com.hsbc.entity.Project;
import com.hsbc.entity.User;

@WebServlet("/addproject")
public class CreateProject extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ManagerDao userDao= new ManagerDaoImpl();
		int managerId = Integer.parseInt(req.getParameter("user_id")); //userId re check
		System.out.println("Create Project  : "+managerId);
		List<User> allDevelopers = userDao.findAllDeveloper();
		List<User> allTesters = userDao.findAllTestors(managerId);
		req.setAttribute("developers", allDevelopers);
		req.setAttribute("testers", allTesters);
		RequestDispatcher dispatcher = req.getRequestDispatcher("createNewProject.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//List<Integer>  teamMembers = new ArrayList<Integer>(); 
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		//String status= request.getParameter("status");
		int tester = Integer.parseInt(request.getParameter("testers"));
		List<Integer> developers = new ArrayList<Integer>();
		String startDate = request.getParameter("startDate");
		for (String userid :  (request.getParameterValues("developers")))
		{
			developers.add( Integer.parseInt(userid));
		}
		System.out.println(request.getParameter("user_id"));
		int managerId = Integer.parseInt(request.getParameter("user_id"));
		

		RequestDispatcher rd = null;
		Project project = new Project();
		project.setProjectName(name);
		project.setDescription(description);
		project.setStartDate(startDate);
		project.setTesterId(tester);
		project.setStatus("in-progress");
		project.setDeveloperId(developers);
		project.setManagerId(managerId);
		System.out.println(project);

		ManagerDao projectDao = new ManagerDaoImpl();
		projectDao.addNewProject(project);
		/*
		 * rd = request.getRequestDispatcher("managerMainPage.jsp"); rd.forward(request,
		 * response);
		 */
		response.sendRedirect("manager?user_id="+managerId);
	}

}