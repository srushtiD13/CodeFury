package com.hsbc.dao;

import java.sql.SQLException;
import java.util.List;
import com.hsbc.entity.Project;
import com.hsbc.exceptions.NoProjectDetailsFound;



public interface ProjectDao {
	List<Project> findAllProjects() throws NoProjectDetailsFound;

}
