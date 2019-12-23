package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
	
	private static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {


			String sql = "SELECT * FROM customer;";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("customer_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				boolean approved = rs.getBoolean("approved");
				double checking = rs.getDouble("checking");
				double savings = rs.getDouble("savings");
				
				User u = new User(id, first_name, last_name, username, password, approved, checking, savings);
				list.add(u);
			}
			
			rs.close();
		} catch(SQLException e) {
			logger.warn("Unable to retrieve all users.", e);
		}
		return list;
	}

	@Override
	public User findById(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM customer WHERE customer_id = " + id + ";";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			 
			rs.next();
			int customer_id = rs.getInt("customer_id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String username = rs.getString("username");
			String password = rs.getString("password");
			boolean approved = rs.getBoolean("approved");
			double checking = rs.getDouble("checking");
			double savings = rs.getDouble("savings");
			
			User u = new User(customer_id, first_name, last_name, username, password, approved, checking, savings);
			
			rs.close();
			return u;
		} catch(SQLException ex) {
			logger.warn("Unable to retrieve user.", ex);
		}
		return null;
	}
	
	@Override
	public User findByUsername(String attemptedUsername) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM customer WHERE username = '" + attemptedUsername + "';";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			 
			rs.next();
			int customer_id = rs.getInt("customer_id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String username = rs.getString("username");
			String password = rs.getString("password");
			boolean approved = rs.getBoolean("approved");
			double checking = rs.getDouble("checking");
			double savings = rs.getDouble("savings");
			
			User u = new User(customer_id, first_name, last_name, username, password, approved, checking, savings);
			
			rs.close();
			return u;
		} catch(SQLException ex) {
			logger.warn("Unable to retrieve user.", ex);
		}
		return null;
	}

	@Override
	public boolean insert(User u, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO customer (first_name, last_name, username, password) " +
					"VALUES (?, ?, ?, ?);";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, u.getFirst_name());
			stmt.setString(2, u.getLast_name());
			stmt.setString(3, u.getUsername());
			stmt.setString(4, password); 
			
			if(!stmt.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			logger.warn("Unable to create new employee.", ex);
			return false;
		}
		
		return true;
	}
	
	
	@Override
	public boolean update(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE employee" +
			"SET first_name=?, last_name=?, username=?, password=?" + 
			"WHERE customer_id = " + u.getCustomerId() + ";";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			stmt.setInt(1, u.getCustomerId());
			stmt.setString(2, u.getFirst_name());
			stmt.setString(3, u.getLast_name());
			stmt.setString(4, u.getUsername());
			

			rs.close();
			if(!stmt.execute()) {
				return false;
			}
		} catch(SQLException e) {
			logger.warn("Unable to edit employee.", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(User u) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "DELETE FROM customers WHERE customer_id = ? ;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			stmt.setInt(1, u.getCustomerId());
			

			rs.close();
			if(!stmt.execute()) {
				return false;
			}
		} catch(SQLException e) {
			logger.warn("Unable to delete employee.", e);
			return false;
		}
		return true;
	}
}
