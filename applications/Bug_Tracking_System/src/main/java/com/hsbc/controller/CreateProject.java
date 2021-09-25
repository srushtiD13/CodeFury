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

import com.hsbc.dao.ProjectDao;
import com.hsbc.dao.UserDao;
import com.hsbc.daoimpl.ProjectDaoImpl;
import com.hsbc.daoimpl.UserDaoImpl;
import com.hsbc.entity.Project;
import com.hsbc.entity.User;

@WebServlet("/addproject")
public class CreateProject extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDao userDao= new UserDaoImpl();
		List<User> allDevelopers = userDao.findAllDevelopers();
		List<User> allTesters = userDao.findAllTesters();

		req.setAttribute("developers", allDevelopers);
		req.setAttribute("testers", allTesters);
		RequestDispatcher dispatcher = req.getRequestDispatcher("views/createNewProject.jsp");
		dispatcher.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Integer>  teamMembers = new ArrayList<Integer>(); 
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String status= request.getParameter("status");
		Integer tester = Integer.parseInt(request.getParameter("testers"));
		List<Integer> developers = new ArrayList<Integer>();
		String startDate = request.getParameter("startDate");
		for (String userid :  (request.getParameterValues("developers")))
		{
			developers.add( Integer.parseInt(userid));
		}
		int managerId = Integer.parseInt(request.getParameter("managerId"));
		

		RequestDispatcher rd = null;
		Project project = new Project();
		project.setProjectName(name);
		project.setDescription(description);
		project.setStartDate1(startDate);
		project.setTesterId(tester);
		project.setStatus("in-progress");
		project.setDeveloperId(developers);
		project.setManagerId(managerId);
		System.out.println(project);

		ProjectDao projectDao = new ProjectDaoImpl();
		projectDao.addNewProject(project);
		rd = request.getRequestDispatcher("views/managerMainPage.jsp");
		rd.forward(request, response);

	}

}