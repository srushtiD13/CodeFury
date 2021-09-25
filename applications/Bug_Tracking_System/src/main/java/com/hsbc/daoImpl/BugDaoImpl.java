package com.hsbc.daoImpl;

import java.util.ArrayList;
import java.util.List;

import com.hsbc.dao.BugDao;
import com.hsbc.entity.Bug;


public class BugDaoImpl implements BugDao {
	
	private Bug bug;

//	public BugDaoImpl(Bug bug) {
//		super();
//		this.bug = bug;
//	}


	@Override
	public Bug closeBugById(int bugid) {			//Defining function closeBugById by marking flag as true
		
		System.out.println("In closeBugById");
		 Bug bug1 = new Bug("Bug1",1);
		 bug1.setFlag(true);
		
		return bug1;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Bug> findAllBugs() {				//Defining function for fetching all bug details
		List<Bug> test = new ArrayList<Bug>(); 
		 Bug bug1 = new Bug("Bug1",1);
		 Bug bug2 = new Bug("Bug2",2);
		 test.add(bug1);
		 test.add(bug2);
		 System.out.println("In findAllBugs");
		return test;
	}

	

}
