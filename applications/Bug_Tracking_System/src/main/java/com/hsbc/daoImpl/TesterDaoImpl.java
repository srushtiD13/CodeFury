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

public class TesterDaoImpl {
	private Connection con;

	private final String DISPLAY_PROJECT = "SELECT project_id, name, description, start_date, status FROM project WHERE project_id=?";
	private final String FIND_PROJECT = "SELECT project_id FROM tester_project WHERE tester_id =?";
	private final String FIND_BUG = "SELECT unique_id, title, description, project_id, created_by, open_date, assigned_to, mark_for_closing, closed_by, closed_on, status, severity WHERE project_id=?";
	private final String INSERT_BUG = "INSERT INTO bug(title,description,project_id,created_by,open_date,severity) VALUES(?,?,?,?,?,?)";
	
	private final String USER_NAME = "root";
	private final String PASSWORD = "root";
	private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/first";

	public TesterDaoImpl() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_CLASS_NAME);
		this.con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		System.out.println("connection established.");
	}

	public List<Project> findProjectByTestor(int tester_id) throws SQLException {
		System.out.println("finding projects of testor..");
		List<Project> projects = new ArrayList<Project>();
		List<Integer> projectId = new ArrayList<Integer>();

		PreparedStatement stmt = this.con.prepareStatement(FIND_PROJECT);
		stmt.setInt(1, tester_id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("project_id");
			if (id == -1) {
				return projects;
			}
			projectId.add(id);
		}
		for (int id : projectId) {
			PreparedStatement stmt2 = this.con.prepareStatement(DISPLAY_PROJECT);
			stmt2.setInt(1, id);

			ResultSet rs2 = stmt2.executeQuery();

			while (rs2.next()) {
				int proId = rs2.getInt("project_id");
				String name = rs2.getString("name");
				String desc = rs2.getString("description");
				String startDate = rs2.getString("start_date");
				String status = rs2.getString("status");

				projects.add(new Project(proId, name, desc, startDate, status));
			}
		}
		return projects;
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
	
	public void reportNewBug(Bug bug, Project project) throws SQLException {
		
		PreparedStatement stmt = this.con.prepareStatement(INSERT_BUG);
		stmt.setString(1, bug.getTitle());
		stmt.setString(2, bug.getDescription());
		stmt.setInt(3, project.getProjectId());
		stmt.setInt(4, bug.getCreatedBy());
		stmt.setString(5, bug.getOpenDate());
		stmt.setString(6, bug.getSeverityLevel());
		int result = stmt.executeUpdate();

		System.out.println(result);
	}
}
