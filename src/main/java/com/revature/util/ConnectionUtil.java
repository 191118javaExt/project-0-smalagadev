package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ConnectionUtil {
	
	private static Logger logger = Logger.getLogger(ConnectionUtil.class);
	
	public static Connection getConnection() {
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "password";
		
		Connection conn = null;
		
		try{
			conn = DriverManager.getConnection(url, username, password);
//			logger.log(Level.INFO, "Connected to database.");
		} catch (SQLException e) {
			logger.warn("Unable to connect to database.", e);
		}
		
		return conn;
	}
	
	
}
