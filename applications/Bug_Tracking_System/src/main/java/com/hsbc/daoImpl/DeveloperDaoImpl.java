package com.hsbc.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.dao.DeveloperDao;
import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;

public class DeveloperDaoImpl implements DeveloperDao {
	private Connection con;

	private final String DISPLAY_PROJECT = "SELECT project_id, name, description, start_date, status FROM project WHERE project_id=?";
	private final String FIND_PROJECT = "SELECT project_id FROM developer_project WHERE developer_id =?";
	private final String FIND_BUG = "SELECT unique_id, title, description, project_id, created_by, open_date, assigned_to, mark_for_closing, closed_by, closed_on, status, severity WHERE assigned_to=?";
	private final String CLOSE_BUG = "UPDATE bug SET mark_for_closing=? WHERE unique_id=?";
	
	private final String USER_NAME = "root";
	private final String PASSWORD = "root";
	private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/bug_tracking_system";

	public DeveloperDaoImpl() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_CLASS_NAME);
		this.con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		System.out.println("connection established.");
	}

	@Override
	public List<Project> findProjectByDeveloperId(int tester_id){
		System.out.println("finding projects of testor..");
		List<Project> projects = new ArrayList<Project>();

		try {
			PreparedStatement stmt = this.con.prepareStatement(FIND_PROJECT);
			stmt.setInt(1, tester_id);
			ResultSet rs = stmt.executeQuery();

			int id = -1;
			while (rs.next()) {
				id = rs.getInt("project_id");
				if (id == -1) {
					return projects;
				}
			}
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
		} catch (SQLException e) {
			
		}

		return projects;
	}
	
	@Override
	public List<Bug> findBugByDeveloperId(int developerId){
		System.out.println("finding bugs for developer");
		List<Bug> bugs = new ArrayList<Bug>();
		
		try {
			PreparedStatement stmt = this.con.prepareStatement(FIND_BUG);
			stmt.setInt(1, developerId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int bug_id= rs.getInt("unique_id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				int projectId= rs.getInt("project_id");
				int createdBy= rs.getInt("created_by");
				String openDate = rs.getString("open_date");
				int assignedTo= rs.getInt("assigned_to");
				String markForClosing = rs.getString("mark_for_closing");
				int closedBy= rs.getInt("closed_by");
				String closedOn = rs.getString("closed_on");
				String status = rs.getString("status");
				String severity = rs.getString("severity");
				
				bugs.add(new Bug(bug_id, title, desc, projectId, createdBy, openDate, assignedTo, markForClosing, closedBy, closedOn, status, severity));
				
			}
		} catch (SQLException e) {
			
		}
		
		return bugs;
	}
	
	@Override
	public void closeBug(int bugId) {
		try {
			PreparedStatement stmt = this.con.prepareStatement(CLOSE_BUG);
			stmt.setString(1, "yes");
			stmt.setInt(2, bugId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			
			
		}
	}

}
