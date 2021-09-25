package com.hsbc.dao;

import java.sql.SQLException;

import com.hsbc.Exception.UserNotImported;
import com.hsbc.Exception.UserNotRegisterd;

public interface IndexDao {

	public void importNewUser(String name, String emailId, String role) throws SQLException;

	public void registerNewUser(String emailId, String role, String password) throws UserNotImported, SQLException;

	public int isUserExist(String emailId) throws SQLException;

	public String getPassword(String emailId) throws UserNotRegisterd;

	public String getRole(String username) throws SQLException;

	public boolean validate(String username, String Password) throws Exception;

}