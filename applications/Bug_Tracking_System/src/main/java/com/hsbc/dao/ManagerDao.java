package com.hsbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;
import com.hsbc.entity.User;

public interface ManagerDao {

	public Project findProjectById(int projectId);

	public List<Bug> findBugByProject(int project_id);

	public void closeBug(int bugId, int managerId);

	public void assignBug(int bugId, int developerId);

	public List<User> findAllDeveloper();

	public List<User> findAllTestors(int managerId);

	public void addNewProject(Project project);
	
	public User getUserById(int id);
	
	public List<Bug> findAllBugSorted(int projectId); 

	public List<Project> findAllProject(int managerId);

}