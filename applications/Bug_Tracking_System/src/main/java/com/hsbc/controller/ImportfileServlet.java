package com.hsbc.controller;

import com.hsbc.daoImpl.IndexDaoImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.*;

//importing login db functions



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ImportfileServlet")
public class ImportfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ImportfileServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IndexDaoImpl login;
		try {
			login = new IndexDaoImpl();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data=request.getParameter("data");
		System.out.println(data);
		String [] dataString=data.split(",");
		for (int i=0;i<dataString.length;i=i+3)
		{
			try {
				login.importNewUser(dataString[i],dataString[i+1],dataString[i+2]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//sending data to databse function
			
		}
			



		

			
	}

}
