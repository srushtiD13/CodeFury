package com.hsbc.factory;

import com.hsbc.dao.BugDao;
import com.hsbc.dao.ProjectDao;
import com.hsbc.daoImpl.BugDaoImpl;
import com.hsbc.daoImpl.ProjectDaoImpl;

public class ProjectDaoFactory {						//Defining factory class to connnect DAO layer with DAO-Implementation Layer.
	
		public static ProjectDao getProjectDao()
		{
			ProjectDao projectdao = new ProjectDaoImpl();			//Accessing Dao layer with object of implementation layer
			return projectdao;
		}

}