package com.hsbc.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.dao.UserDao;
import com.hsbc.entity.User;


public class UserDaoImpl implements UserDao{
	
	// database connection parameters
		private Connection con;
		private final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver"; 	
		private final String URL = "jdbc:mysql://localhost:3306/bug_tracking_system";
		private final String USER_NAME = "root";
		private final String PASSWORD = "root";
		
		public UserDaoImpl() {
			
			try 
			{
				Class.forName(DRIVER_CLASS_NAME);
				con = DriverManager.getConnection(URL,USER_NAME,PASSWORD);
				System.out.println("connection established");
			} 
			catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		//this returns the list of developers not assigned to any project to display in the drop down menu during creation of new project
		@Override
		public List<User> findAllDevelopers()
		{
			//To-do : add jdbc logic 
			List<User>  developers = new ArrayList<User>();
	
			User user1 = new User(); 		//new User(1,"dev1", "type1" ,"user1@xyz");
			user1.setUserId(1);
			user1.setName("developer1");
			user1.setRole("developer");
			user1.setEmailId("user1@xyz");
			
			User user2 = new User(); 		//new User(2,"dev2", "type2" ,"user2@xyz");
			user2.setUserId(2);
			user2.setName("developer2");
			user2.setRole("developer");
			user2.setEmailId("user2@xyz");

			developers.add(user1);
			developers.add(user2);
			return developers;
		}
		
		//this returns the list of testers assigned to 0 or one project to display in the drop down menu during creation of new project
		@Override
		public List<User> findAllTesters()
		{
			//To-do : add jdbc logic 
			List<User>  testers = new ArrayList<User>();
			User user1 = new User();            //new User(3,"testor1", "role_testor1" ,"user1@xyz");
			user1.setUserId(3);
			user1.setName("tester1");
			user1.setRole("tester");
			user1.setEmailId("user3@xyz");
			
			User user2 = new User();				//new User(4,"testor2", "role_testor2" ,"user2@xyz");
			user2.setUserId(4);
			user2.setName("tester1");
			user2.setRole("tester");
			user2.setEmailId("user4@xyz");
			
			
			testers.add(user1);
			testers.add(user2);
			return testers;
			
		}
	
		@Override
		public List<User> findAllUsers() {
			
			List<User>  users = new ArrayList<User>();
			User user1 = new User(); 		//new User(1,"dev1", "type1" ,"user1@xyz");
			user1.setUserId(1);
			user1.setName("developer1");
			user1.setRole("developer");
			user1.setEmailId("user1@xyz");
			
			User user2 = new User(); 		//new User(2,"dev2", "type2" ,"user2@xyz");
			user2.setUserId(2);
			user2.setName("developer2");
			user2.setRole("developer");
			user2.setEmailId("user2@xyz");

			users.add(user1);
			users.add(user2);
			return users;
		}
}
