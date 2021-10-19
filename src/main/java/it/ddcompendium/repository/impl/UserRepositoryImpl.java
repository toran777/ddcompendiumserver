package it.ddcompendium.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.ddcompendium.repository.CrudRepository;
import it.ddcompendium.repository.entities.User;

public class UserRepositoryImpl implements CrudRepository<User> {
	private Connection connection;

	public UserRepositoryImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public User findOne(User t) throws Exception {
		PreparedStatement statement = null;
		ResultSet set = null;
		User result = null;

		try {
			statement = connection
					.prepareStatement("SELECT * FROM utenti WHERE (username = ? OR email = ?) AND password = ?");
			statement.setString(1, t.getUsername());
			statement.setString(2, t.getUsername());
			statement.setString(3, t.getPassword());

			set = statement.executeQuery();

			while (set.next()) {
				result = new User();
				result.setId(set.getInt("id"));
				result.setUsername(set.getString("username"));
				result.setEmail(set.getString("email"));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			statement.close();
			set.close();
		}

		return result;
	}

	@Override
	public List<User> findAll(User t) throws Exception {
		PreparedStatement statement = null;
		ResultSet set = null;
		List<User> result = new ArrayList<>();

		try {
			statement = connection.prepareStatement("SELECT * FROM utenti");

			set = statement.executeQuery();

			while (set.next()) {
				User user = new User();
				user.setId(set.getInt("id"));
				user.setUsername(set.getString("username"));
				user.setEmail(set.getString("email"));
				result.add(user);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			statement.close();
			set.close();
		}

		return result;
	}

	@Override
	public void insert(User t) throws Exception {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("INSERT INTO utenti(username, email, password) VALUES (?, ?, ?)");
			statement.setString(1, t.getUsername());
			statement.setString(2, t.getEmail());
			statement.setString(3, t.getPassword());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			statement.close();
		}
	}

	@Override
	public void update(User t) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
	}

}
