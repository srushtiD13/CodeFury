package com.hsbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;

public interface DeveloperDao {

	public List<Project> findProjectByDeveloperId(int tester_id);

	public List<Bug> findBugByDeveloperId(int developerId);

	public void closeBug(int bugId);

}