package com.hsbc.controller;

import java.io.IOException;
import java.sql.Date;
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

import com.hsbc.dao.ProjectDao;
import com.hsbc.dao.UserDao;
import com.hsbc.daoImpl.ProjectDaoImpl;
import com.hsbc.daoImpl.UserDaoImpl;
import com.hsbc.entity.Project;
import com.hsbc.entity.User;

@WebServlet("/manager")
public class ManagerController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operation = req.getParameter("operation");
		HttpSession session = req.getSession();

		if(operation==null) {
			ProjectDaoImpl p = new ProjectDaoImpl();
			List <Project> projectList = p.findAllProjects(0);
			session.setAttribute("projectList", projectList);
			User user = new User();
			user.setEmailId("xyz@mail.com");
		    Date date=Date.valueOf("1999-09-19");
			user.setDoj(date);
			user.setRole("Manager");
			int userId = 101;
			user.setUserId(userId );
			session.setAttribute("user", user);
			RequestDispatcher dispatcher = req.getRequestDispatcher("managerMainPage.jsp");
			dispatcher.forward(req, resp);
		}
		/*
		 * else if(operation.equals("create")) {
		 * 
		 * UserDao userDao= new UserDaoImpl(); List<User> allDevelopers =
		 * userDao.findAllDevelopers(); List<User> allTesters =
		 * userDao.findAllTesters();
		 * 
		 * req.setAttribute("developers", allDevelopers); req.setAttribute("testers",
		 * allTesters); RequestDispatcher dispatcher =
		 * req.getRequestDispatcher("createNewProject.jsp");
		 * dispatcher.forward(req, resp); }
		 */
	}

	
}
