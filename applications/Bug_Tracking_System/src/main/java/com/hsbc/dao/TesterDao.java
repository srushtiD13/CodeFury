package com.hsbc.dao;

import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;

public interface TesterDao {

	void reportNewBug(Bug bug, Project project);

}