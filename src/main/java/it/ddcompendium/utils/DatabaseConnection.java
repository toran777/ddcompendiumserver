package it.ddcompendium.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseConnection {

	public static Connection getConnection() throws SQLException, NamingException {
		Connection connection = null;

		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/ddcompendium");
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
		} catch (NamingException e) {
			throw e;
		}

		return connection;
	}
}
