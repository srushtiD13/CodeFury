package com.hsbc.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.hsbc.exceptions.UserNotImported;
import com.hsbc.exceptions.UserNotRegisterd;
import com.hsbc.dao.IndexDao;

public class IndexDaoImpl implements IndexDao {
	private static final String IMPORT_USER = "INSERT INTO user(name, email, role) values(?,?,?)";
	private static final String FIND_USER = "SELECT COUNT(email) FROM user WHERE email = ?";
	private static final String REGISTER_USER = "UPDATE user SET pwd = ? WHERE email = ?";
	private static final String FIND_ID_ROLE_PASSWORD = "SELECT user_id, role, pwd FROM user WHERE email = ?";
	private static final String GET_PASSWORD = "SELECT pwd FROM user WHERE email = ?";

	private Connection con;

	private final String USER_NAME = "root";
	private final String PASSWORD = "root";
	private final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/bug_tracking_system";

	public IndexDaoImpl() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_CLASS_NAME);
		this.con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		System.out.println("connection established.");
	}

	@Override
	public void importNewUser(final String name, final String emailId, final String role) throws SQLException {
		System.out.println("Importing New User");
		
		PreparedStatement stmt = this.con.prepareStatement(IMPORT_USER);
		stmt.setString(1, name);
		stmt.setString(2, emailId);
		stmt.setString(3, role);
		try {
			int recordsUpdated = stmt.executeUpdate();
			System.out.println(recordsUpdated + " user added");
		} catch (SQLIntegrityConstraintViolationException error) {
			throw error;
		}
		stmt.close();
	}

	@Override
	public void registerNewUser(final String emailId, final String role, final String password)
			throws UserNotImported, SQLException {
		System.out.println("Registering New User");
		System.out.println(emailId+role+password);
		// If user is not imported send back to import page
		try {
			if (isUserExist(emailId) == 0) {
				throw new UserNotImported("User Not Imported");
			}
		} catch (UserNotImported error) {
			System.out.println(error.getMessage());
			throw error;
		} catch (SQLException error) {
			System.out.println("error in find user");
			System.out.println(error.getMessage());
		}

		// If role doesn't match with role in database, display exception
		PreparedStatement stmt = null;

		try {
			stmt = this.con.prepareStatement(FIND_ID_ROLE_PASSWORD);
			stmt.setString(1, emailId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int id = rs.getInt(1);
			String type = rs.getString(2);
			String pwd = rs.getString(3);
			if (!type.equals(role)) {
				System.out.println("Role doesn't match");
				return;
			}
			else if (pwd != null) {
				System.out.println("Already Register");
				return;
			}else {
				if(type.equalsIgnoreCase("Developer")) {
					PreparedStatement stmt2 = this.con.prepareStatement("INSERT INTO developer_project(developer_id,project_id) values(?,?)");
					stmt2.setInt(1, id);
					stmt2.setInt(2, -1);
					stmt2.executeUpdate();
				}
				if(type.equalsIgnoreCase("Tester")) {
					PreparedStatement stmt2 = this.con.prepareStatement("INSERT INTO tester_manager(tester_id,manager_id) values(?,?)");
					stmt2.setInt(1, id);
					stmt2.setInt(2, -1);
					stmt2.executeUpdate();
				}
			}
		} catch (SQLException e) {
			System.out.println("error in FIND_ROLE_PASSWORD");
			System.out.println(e.getMessage());
		}

		// If everything is correct update password
		try {
			stmt = this.con.prepareStatement(REGISTER_USER);
			stmt.setString(1, password);
			stmt.setString(2, emailId);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public int isUserExist(final String emailId) throws SQLException {
		PreparedStatement stmt = this.con.prepareStatement(FIND_USER);
		stmt.setString(1, emailId);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		System.out.println(rs.getInt(1) + "User Found");
		stmt.close();
		return rs.getInt(1);
	}

	@Override
	public String getPassword(String emailId) throws UserNotRegisterd {
		try {
			PreparedStatement stmt = this.con.prepareStatement(GET_PASSWORD);
			stmt.setString(1, emailId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			String pwd = rs.getString(1);
			if (pwd == null) {
				throw new UserNotRegisterd("User not register");
			}
			return pwd;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	@Override
	public String getRole(String username) throws SQLException {
		PreparedStatement preparedStatement = con.prepareStatement("select role from user where email=?");
		preparedStatement.setString(1, username);
		System.out.println(preparedStatement);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return rs.getString(1);
	}
	
	@Override
	public boolean validate(String username, String Password) throws Exception {
		
		return getPassword(username).equals(Password);
		
	}

	public int findIdByEmail(String email) {
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("Select user_id from user where email=?");
			stmt.setString(1, email);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int getIdByEmail(String emailId) {
		try {
			PreparedStatement stmt = this.con.prepareStatement("SELECT user_id FROM user WHERE email = ?");
			stmt.setString(1, emailId);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int id = rs.getInt("user_id");
			return id;
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return 0;
	}

}
