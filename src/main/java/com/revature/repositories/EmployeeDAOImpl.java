package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	private static Logger logger = Logger.getLogger(EmployeeDAOImpl.class);
	
	@Override
	public List<Employee> findAll() {
		
		List<Employee> list = new ArrayList<>();
		
		try (Connection conn = ConnectionUtil.getConnection()) {


			String sql = "SELECT * FROM employee;";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("emp_id");
				String first_name = rs.getString("first_name");
				String last_name = rs.getString("last_name");
				String username = rs.getString("username");
				
				Employee e = new Employee(id, first_name, last_name, username);
				list.add(e);
			}
			
			rs.close();
		} catch(SQLException e) {
			logger.warn("Unable to retrieve all users", e);
		}
		return list;
	}

	@Override
	public Employee findById(int id) {
		
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "SELECT * FROM employee WHERE = " + id + ";";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			 
			rs.next();
			int emp_id = rs.getInt("emp_id");
			String first_name = rs.getString("first_name");
			String last_name = rs.getString("last_name");
			String username = rs.getString("username");
			
			Employee e = new Employee(emp_id, first_name, last_name, username);
			
			rs.close();
			return e;
		} catch(SQLException ex) {
			logger.warn("Unable to retrieve user.", ex);
		}
		return null;
	}

	@Override
	public boolean insert(Employee e, String password) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO employee (first_name, last_name, username, password, emp_role) " +
					"VALUES (?, ?, ?, ?, ?);";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, e.getFirst_name());
			stmt.setString(2, e.getLast_name());
			stmt.setString(3, e.getUsername());
			stmt.setString(4, password); 
			stmt.setString(5, "employee");
			
			if(!stmt.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			logger.warn("Unable to create all user", ex);
			return false;
		}
		
		return true;
	}

	@Override
	public boolean update(Employee e) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE employee" +
			"SET first_name=?, last_name=?, username=?, password=?" + 
			"WHERE emp_id = " + e.getEmployeeId() + ";";

			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			stmt.setInt(1, e.getEmployeeId());
			stmt.setString(2, e.getFirst_name());
			stmt.setString(3, e.getLast_name());
			stmt.setString(4, e.getUsername());
			

			rs.close();
			if(!stmt.execute()) {
				return false;
			}
		} catch(SQLException ex) {
			logger.warn("Unable to edit user.", ex);
			return false;
		}
		return true;
	}

}