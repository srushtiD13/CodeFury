package com.hsbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;

public interface DeveloperDao {

	public List<Project> findProjectByDeveloperId(int tester_id) throws SQLException;

	public List<Bug> findBugByDeveloperId(int developerId) throws SQLException;

	public void closeBug(int bugId) throws SQLException;

}