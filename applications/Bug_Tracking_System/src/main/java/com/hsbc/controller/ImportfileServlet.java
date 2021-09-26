package com.hsbc.controller;

import com.hsbc.daoImpl.IndexDaoImpl;

import java.io.IOException;
import java.sql.SQLException;
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
		IndexDaoImpl index;
		try {
			index= new IndexDaoImpl();
			String data=request.getParameter("data");
			System.out.println(data);
			String [] dataString=data.split(",");
			for (int i=0;i<dataString.length;i=i+3)
			{
				try {
					String name=dataString[i].substring(1, dataString[i].length() - 1);
					String email=dataString[i+1].substring(1, dataString[i+1].length() - 1);
					String role=dataString[i+2].substring(1, dataString[i+2].length() - 1);
					
					index.importNewUser(name,email,role);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//sending data to databse function
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}

}
