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

import com.hsbc.dao.ManagerDao;
import com.hsbc.daoImpl.ManagerDaoImpl;
import com.hsbc.entity.Project;
import com.hsbc.entity.User;

@WebServlet("/manager")
public class ManagerController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operation = req.getParameter("operation");
		HttpSession session = req.getSession();
		int managerId = Integer.parseInt(req.getParameter("user_id"));
			System.out.println("Manager id :"+managerId);
			//Problem
			ManagerDao p = new ManagerDaoImpl();
			List <Project> projectList = p.findAllProject(managerId);
			session.setAttribute("projectList", projectList);
			User user = p.getUserById(managerId);
			System.out.println(user.toString());
			System.out.println("Manager role : "+user.getRole());
			System.out.println("Manager LastLogin : "+user.getLastLogin());
			user.setUserId(managerId );
			session.setAttribute("user", user);
			RequestDispatcher dispatcher = req.getRequestDispatcher("managerMainPage.jsp");
			dispatcher.forward(req, resp);
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
