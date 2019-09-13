package com.wp.connector;

import java.sql.Connection;

import javax.servlet.ServletContext;

public class DatabaseConnection {

	private DatabaseConnection() {
	}

	public static Connection getDatabaseConnection(ServletContext context) {
		try {
			Connection con = (Connection) context.getAttribute("dbConnection");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
