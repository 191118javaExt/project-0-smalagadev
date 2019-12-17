package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			System.out.println("Connected to database.");
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM public.employee");
            while (resultSet.next()) {
                System.out.printf( resultSet.getString("first_name") + " " + resultSet.getString("last_name") + "\n");
            }
		} catch (SQLException e) {
			logger.warn("Unable to connect to database.", e);
		}
		
		return conn;
	}
	
	
}
