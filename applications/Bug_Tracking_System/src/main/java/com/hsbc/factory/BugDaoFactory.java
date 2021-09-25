package com.hsbc.factory;

import com.hsbc.dao.BugDao;
import com.hsbc.dao.UserDao;
import com.hsbc.daoImpl.BugDaoImpl;
import com.hsbc.daoImpl.UserDaoImpl;

abstract public class BugDaoFactory {				//Defining factory class to connnect DAO layer with DAO-Implementation Layer.
	public static BugDao getBugDao()
	{
		BugDao bugDao = new BugDaoImpl();			//Accessing Dao layer with object of implementation layer
		return bugDao;
	}

}