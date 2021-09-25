package com.hsbc.dao;

import java.util.List;

import com.hsbc.entity.Bug;
import com.hsbc.exceptions.NoBugFoundException;

public interface BugDao {
	Bug closeBugById(int bugid) throws NoBugFoundException;

	List<Bug> findAllBugs();

}
