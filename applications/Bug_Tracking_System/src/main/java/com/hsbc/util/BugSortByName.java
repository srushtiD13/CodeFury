package com.hsbc.util;

import java.util.Comparator;

import com.hsbc.entity.Bug;

public class BugSortByName implements Comparator<Bug> {

	@Override
	public int compare(Bug o1, Bug o2) {
		return o1.getBugName().compareTo(o2.getBugName());
	}

}
