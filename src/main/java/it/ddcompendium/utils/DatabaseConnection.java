package it.ddcompendium.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DB_URL = "jdbc:mysql://2.239.18.210:12345/ddcompendium";
	private static final String USERNAME = "sincrono";
	private static final String PASSWORD = "password";

	public static Connection getConnection() throws SQLException {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw e;
		}

		return connection;
	}
}
