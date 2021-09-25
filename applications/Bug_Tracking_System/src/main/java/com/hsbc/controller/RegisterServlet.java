package com.hsbc.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsbc.daoImpl.IndexDaoImpl;
import com.hsbc.exceptions.UserNotImported;

import com.hsbc.daoImpl.IndexDaoImpl;






@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // This Login is Dao layer login
    private IndexDaoImpl login;
    // Login userDao;

    public void init() {
       
        try {
			login = new IndexDaoImpl();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String email = request.getParameter("Email");
        String password = request.getParameter("password");
        String role = request.getParameter("Role");
       
       

        
       

        try {
            login.registerNewUser(email, role, password);
        }
        catch(UserNotImported e1) {
        	System.out.println(e1.getMessage());
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        response.sendRedirect("userdetails.jsp");
    }
}