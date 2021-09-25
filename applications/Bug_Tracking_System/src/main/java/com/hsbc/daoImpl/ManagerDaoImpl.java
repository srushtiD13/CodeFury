package com.hsbc.daoImpl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;

public class ManagerDaoImpl {
	private Connection con;

	private final String FIND_PROJECT_BY_ID = "SELECT project_id, name, description, start_date, status FROM project WHERE project_id=?";
	private final String FIND_BUG = "SELECT unique_id, title, description, project_id, created_by, open_date, assigned_to, mark_for_closing, closed_by, closed_on, status, severity WHERE project_id=?";
	private final String CLOSE_BUG = "UPDATE bug SET closed_by=? WHERE unique_id=?";
	private final String ASSIGN_BUG = "UPDATE bug SET assigned_to=? WHERE unique_id=?";
	private final String FIND_TESTER = "SELECT tester_id FROM tester_project WHERE project_id=?";
	private final String FIND_DEVELOPER = "SELECT developer_id FROM developer_project WHERE project_id=?";
	
	private final String USER_NAME = "root";
	private final String PASSWORD = "root";
	private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/first";

	public ManagerDaoImpl() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_CLASS_NAME);
		this.con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		System.out.println("connection established.");
	}

	public Project findProjectById(int projectId) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement(FIND_PROJECT_BY_ID);
		stmt.setInt(1, projectId);

		ResultSet rs = stmt.executeQuery();

		rs.next();
		int proId = rs.getInt("project_id");
		String name = rs.getString("name");
		String desc = rs.getString("description");
		String startDate = rs.getString("start_date");
		String status = rs.getString("status");
		int managerId = rs.getInt("manager_id");
		
		PreparedStatement stmt2 = this.con.prepareStatement(FIND_TESTER);
		stmt2.setInt(1, projectId);
		
		ResultSet rs2= stmt2.executeQuery();
		rs2.next();
		int testerId = rs2.getInt("tester_id");
		
		PreparedStatement stmt3 = this.con.prepareStatement(FIND_DEVELOPER);
		stmt3.setInt(1, projectId);
		
		ResultSet rs3= stmt3.executeQuery();
		List<Integer> developerIds = new ArrayList<Integer>();
		
		while(rs3.next()) {
			
		 int developerId= rs2.getInt("developer_id");
		 developerIds.add(developerId);
		 
		}
		return new Project(proId, name, desc, startDate, status, testerId, managerId, developerIds);

	}
	
	public List<Bug> findBugByProject(int project_id) throws SQLException {
		System.out.println("finding bugs from project");
		List<Bug> bugs = new ArrayList<Bug>();
		
		PreparedStatement stmt = this.con.prepareStatement(FIND_BUG);
		stmt.setInt(1, project_id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			int bug_id= rs.getInt("unique_id");
			String title = rs.getString("title");
			String desc = rs.getString("description");
			int procectId= rs.getInt("project_id");
			int createdBy= rs.getInt("created_by");
			String openDate = rs.getString("open_date");
			int assignedTo= rs.getInt("assigned_to");
			String markForClosing = rs.getString("mark_for_closing");
			int closedBy= rs.getInt("closed_by");
			String closedOn = rs.getString("closed_on");
			String status = rs.getString("status");
			String severity = rs.getString("severity");
			
			bugs.add(new Bug(bug_id, title, desc, procectId, createdBy, openDate, assignedTo, markForClosing, closedBy, closedOn, status, severity));
			
		}
		
		return bugs;
	}
	
	//Mention change in parameter
	public void closeBug(int bugId,int managerId) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement(CLOSE_BUG);
		stmt.setInt(1, managerId);
		stmt.setInt(2, bugId);
		stmt.executeUpdate();
	}
	
	public void assignBug(int bugId, int developerId) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement(ASSIGN_BUG);
		stmt.setInt(1, developerId);
		stmt.setInt(2, bugId);
		stmt.executeUpdate();
	}
	
	

}
