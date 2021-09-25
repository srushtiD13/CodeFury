package com.hsbc.dao;


import java.util.List;

import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;
import com.hsbc.entity.User;

public interface ManagerDao {
		
	Project findProjectById(int projectId);
	List<Bug> findAllBug(int projectId);
	List<Bug> findAllBugSorted(int projectId);
	void closeBug(int porjectId, int uniqueId);
	void assignBug(int bugId, String developerName);
	User findAllUsers(int projectId);
	void addNewProject(Project project);
}