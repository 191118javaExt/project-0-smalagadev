package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Admin;
import com.revature.util.ConnectionUtil;

public class AdminDAOImpl implements AdminDAO {
	
	private static Logger logger = Logger.getLogger(AdminDAOImpl.class);

	@Override
	public List<Admin> findAll() {
		List<Admin> list = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {


			String sql = "SELECT * FROM employee WHERE emp_role = 'Bank Admin';";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("emp_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String username = rs.getString("username");
				
				Admin a = new Admin(id, first_name, last_name, username);
				list.add(a);
			}
			
			rs.close();
		} catch(SQLException e) {
			logger.warn("Unable to retrieve all admins", e);
		}
		return list;
	}

	@Override
	public Admin findById(int id) {

		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM employee WHERE = " + id + ";";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			 
			rs.next();
			int emp_id = rs.getInt("emp_id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String username = rs.getString("username");
			
			Admin a = new Admin(emp_id, first_name, last_name, username);
			
			rs.close();
			return a;
		} catch(SQLException ex) {
			logger.warn("Unable to retrieve admin.", ex);
		}
		return null;
	}

	@Override
	public boolean insert(Admin a, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO employee (first_name, last_name, username, password, emp_role) " +
					"VALUES (?, ?, ?, ?, ?);";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, a.getFirst_name());
			stmt.setString(2, a.getLast_name());
			stmt.setString(3, a.getUsername());
			stmt.setString(4, password); 
			stmt.setString(5, "Bank Admin");
			
			if(!stmt.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			logger.warn("Unable to create new admin.", ex);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean update(Admin a) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE employee" +
			"SET first_name=?, last_name=?, username=?, password=?" + 
			"WHERE emp_id = " + a.getEmployeeId() + ";";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			stmt.setInt(1, a.getEmployeeId());
			stmt.setString(2, a.getFirst_name());
			stmt.setString(3, a.getLast_name());
			stmt.setString(4, a.getUsername());
			

			rs.close();
			if(!stmt.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			logger.warn("Unable to edit admin.", ex);
			return false;
		}
		return true;
	}

}
