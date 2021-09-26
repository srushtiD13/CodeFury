package com.hsbc.exceptions;

public class NoProjectDetailsFound extends Exception {
  
    public NoProjectDetailsFound() {
		super();
		System.out.println("No project details found.");
	}

}
