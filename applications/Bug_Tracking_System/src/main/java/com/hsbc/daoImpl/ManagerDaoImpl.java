package com.hsbc.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.dao.ManagerDao;
import com.hsbc.entity.Bug;
import com.hsbc.entity.Project;
import com.hsbc.entity.User;

public class ManagerDaoImpl implements ManagerDao {
	private Connection con;

	private final String FIND_PROJECT_BY_ID = "SELECT project_id, name, description, start_date, status FROM project WHERE project_id=?";
	private final String FIND_BUG = "SELECT unique_id, title, description, project_id, created_by, open_date, assigned_to, mark_for_closing, closed_by, closed_on, status, severity WHERE project_id=?";
	private final String CLOSE_BUG = "UPDATE bug SET closed_by=? WHERE unique_id=?";
	private final String ASSIGN_BUG = "UPDATE bug SET assigned_to=? WHERE unique_id=?";
	private final String FIND_TESTER = "SELECT tester_id FROM tester_project WHERE project_id=?";
	private final String FIND_DEVELOPER = "SELECT developer_id FROM developer_project WHERE project_id=?";
	private final String DISPLAY_USER = "SELECT user_id, name, email, role, pwd, last_logged_on FROM user WHERE user_id=?";

	private final String USER_NAME = "root";
	private final String PASSWORD = "root";
	private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/bug_tracking_system";

	public ManagerDaoImpl() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_CLASS_NAME);
		this.con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		System.out.println("connection established.");
	}

	@Override
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

		ResultSet rs2 = stmt2.executeQuery();
		rs2.next();
		int testerId = rs2.getInt("tester_id");

		PreparedStatement stmt3 = this.con.prepareStatement(FIND_DEVELOPER);
		stmt3.setInt(1, projectId);

		ResultSet rs3 = stmt3.executeQuery();
		List<Integer> developerIds = new ArrayList<Integer>();

		while (rs3.next()) {

			int developerId = rs2.getInt("developer_id");
			developerIds.add(developerId);

		}
		return new Project(proId, name, desc, startDate, status, testerId, managerId, developerIds);

	}

	@Override
	public List<Bug> findBugByProject(int project_id) throws SQLException {
		System.out.println("finding bugs from project");
		List<Bug> bugs = new ArrayList<Bug>();

		PreparedStatement stmt = this.con.prepareStatement(FIND_BUG);
		stmt.setInt(1, project_id);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int bug_id = rs.getInt("unique_id");
			String title = rs.getString("title");
			String desc = rs.getString("description");
			int procectId = rs.getInt("project_id");
			int createdBy = rs.getInt("created_by");
			String openDate = rs.getString("open_date");
			int assignedTo = rs.getInt("assigned_to");
			String markForClosing = rs.getString("mark_for_closing");
			int closedBy = rs.getInt("closed_by");
			String closedOn = rs.getString("closed_on");
			String status = rs.getString("status");
			String severity = rs.getString("severity");

			bugs.add(new Bug(bug_id, title, desc, procectId, createdBy, openDate, assignedTo, markForClosing, closedBy,
					closedOn, status, severity));

		}

		return bugs;
	}

	// Mention change in parameter
	@Override
	public void closeBug(int bugId, int managerId) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement(CLOSE_BUG);
		stmt.setInt(1, managerId);
		stmt.setInt(2, bugId);
		stmt.executeUpdate();
	}

	@Override
	public void assignBug(int bugId, int developerId) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement(ASSIGN_BUG);
		stmt.setInt(1, developerId);
		stmt.setInt(2, bugId);
		stmt.executeUpdate();
	}

	@Override
	public List<User> findAllDeveloper() throws SQLException {
		List<User> developers = new ArrayList<>();
		PreparedStatement stmt = this.con
				.prepareStatement("SELECT developer_id FROM developer_project WHERE project_id = ?");
		stmt.setInt(1, -1);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("developer_id");
			PreparedStatement stmt2 = this.con.prepareStatement(DISPLAY_USER);
			stmt2.setInt(1, id);
			ResultSet rs2 = stmt2.executeQuery();
			int userId = rs2.getInt(1);
			String name = rs2.getString(2);
			String email = rs2.getString(3);
			String role = rs2.getString(4);
			String pwd = rs2.getString(5);
			String last_lagged_on = rs2.getString(6);

			developers.add(new User(userId, name, email, role, "", last_lagged_on));
		}
		return developers;
	}

	@Override
	public List<User> findAllTestors(int managerId) throws SQLException {
		List<User> testors = new ArrayList<User>();

		PreparedStatement stmt = this.con.prepareStatement(
				"SELECT tester_id FROM tester_manager WHERE (no_of_project < 2 and (manager_id = -1 or manager_id =?))");
		stmt.setInt(1, managerId);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			int id = rs.getInt("tester_id");
			PreparedStatement stmt2 = this.con.prepareStatement(DISPLAY_USER);
			stmt2.setInt(1, id);
			ResultSet rs2 = stmt2.executeQuery();
			int userId = rs2.getInt(1);
			String name = rs2.getString(2);
			String email = rs2.getString(3);
			String role = rs2.getString(4);
			String pwd = rs2.getString(5);
			String last_lagged_on = rs2.getString(6);

			testors.add(new User(userId, name, email, role, "", last_lagged_on));
		}

		return testors;
	}

	@Override
	public void addNewProject(Project project) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement(
				"INSERT INTO project(name, description, start_date, status, manager_id) values(?,?,?,?,?)");
		stmt.setString(1, project.getProjectName());
		stmt.setString(2, project.getDescription());
		stmt.setString(3, project.getStartDate());
		stmt.setString(4, project.getStatus());
		stmt.setInt(5, project.getManagerId());

		stmt.executeUpdate();

		// update developer project
		stmt = this.con.prepareStatement("SELECT project_id FROM project WHERE name=? AND manager_id = ?");
		stmt.setString(1, project.getProjectName());
		stmt.setInt(2, project.getManagerId());

		ResultSet rs = stmt.executeQuery();
		rs.next();
		int project_id = rs.getInt("project_id");
		
		for (int dev : project.getDeveloperId()) {
			stmt = this.con.prepareStatement("INSERT INTO developer_project(developer_id,project_id) values(?,?)");
			stmt.setInt(1, dev);
			stmt.setInt(2, project_id);
			stmt.executeQuery();
		}
		// update tester
		stmt = this.con.prepareStatement("UPDATE tester_manager SET no_of_projects = no_of_projects + 1, manager_id=?");
		stmt.setInt(1, project.getManagerId());
		stmt.executeUpdate();

		stmt = this.con.prepareStatement("INSERT INTO tester_project(tester_id,project_id) values(?,?)");
		stmt.setInt(1, project.getTesterId());
		stmt.setInt(2, project_id);
		stmt.executeQuery();
	}

	@Override
	public User getUserById(int id) throws SQLException {
		
		PreparedStatement stmt = this.con.prepareStatement(DISPLAY_USER);
		stmt.setInt(1, id);
		ResultSet rs2 = stmt.executeQuery();
		int userId = rs2.getInt(1);
		String name = rs2.getString(2);
		String email = rs2.getString(3);
		String role = rs2.getString(4);
		String pwd = rs2.getString(5);
		String last_lagged_on = rs2.getString(6);
		return new User(userId, name, email, role, "", last_lagged_on);
	}

}
