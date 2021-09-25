package com.hsbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;
import com.hsbc.entity.User;

public interface ManagerDao {

	public Project findProjectById(int projectId) throws SQLException;

	public List<Bug> findBugByProject(int project_id) throws SQLException;

	// Mention change in parameter
	public void closeBug(int bugId, int managerId) throws SQLException;

	public void assignBug(int bugId, int developerId) throws SQLException;

	public List<User> findAllDeveloper() throws SQLException;

	public List<User> findAllTestors(int managerId) throws SQLException;

	public void addNewProject(Project project) throws SQLException;
	
	public User getUserById(int id)throws SQLException;

}