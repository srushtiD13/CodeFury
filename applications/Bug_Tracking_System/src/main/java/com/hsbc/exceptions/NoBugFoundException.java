package com.hsbc.exceptions;

public class NoBugFoundException extends Exception {
  
	public NoBugFoundException() {
		super();
		System.out.println("No Bug Details found.");
	}

}
