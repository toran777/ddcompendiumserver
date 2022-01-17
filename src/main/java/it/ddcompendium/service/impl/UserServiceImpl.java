package it.ddcompendium.service.impl;

import java.sql.Connection;

import it.ddcompendium.repository.entities.User;
import it.ddcompendium.repository.impl.UserRepositoryImpl;
import it.ddcompendium.service.UserService;
import it.ddcompendium.service.exceptions.ServiceException;
import it.ddcompendium.utils.DatabaseConnection;

public class UserServiceImpl implements UserService {

	@Override
	public void insert(User user) throws Exception {
		Connection connection = null;

		try {
			connection = DatabaseConnection.getConnection();
			UserRepositoryImpl repository = new UserRepositoryImpl(connection);
			repository.insert(user);
		} catch (Exception e) {
			if (e.getMessage().contains("username")) {
				throw new ServiceException(-1, "Username is not available");
			} else if (e.getMessage().contains("email")) {
				throw new ServiceException(-1, "There is already an account with this email");
			} else {
				throw new ServiceException(-1, "Server Error, try again later");
			}
		} finally {
			connection.commit();
			connection.close();
		}
	}

	@Override
	public User findOne(User user) throws Exception {
		Connection connection = null;
		User exists = null;

		try {
			connection = DatabaseConnection.getConnection();
			UserRepositoryImpl repository = new UserRepositoryImpl(connection);
			exists = repository.findOne(user);
		} catch (Exception e) {
			throw new ServiceException(-1, "Server Error, try again later");
		} finally {
			connection.commit();
			connection.close();
		}

		return exists;
	}

	@Override
	public User search(String query) throws Exception {
		Connection connection = null;
		User result = null;

		try {
			connection = DatabaseConnection.getConnection();
			UserRepositoryImpl repository = new UserRepositoryImpl(connection);
			result = repository.search(query);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(-1, "Server Error, try again later");
		} finally {
			connection.commit();
			connection.close();
		}

		return result;
	}

}
