package com.hsbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;

public interface TesterDao {

	public List<Project> findProjectByTestor(int tester_id) throws SQLException;

	public List<Bug> findBugByProject(int project_id) throws SQLException;

	public void reportNewBug(Bug bug, Project project) throws SQLException;

}