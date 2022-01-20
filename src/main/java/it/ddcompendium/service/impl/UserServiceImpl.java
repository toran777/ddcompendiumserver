package it.ddcompendium.service.impl;

import it.ddcompendium.repository.entities.User;
import it.ddcompendium.repository.impl.UserRepositoryImpl;
import it.ddcompendium.service.UserService;
import it.ddcompendium.service.exceptions.ServiceException;
import it.ddcompendium.utils.DatabaseConnection;

import java.sql.Connection;

public class UserServiceImpl implements UserService {

    @Override
    public void insert(User user) throws Exception {
        Connection connection;

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
        }
    }

    @Override
    public User findOne(User user) throws Exception {
        Connection connection;
        User exists;

        try {
            connection = DatabaseConnection.getConnection();
            UserRepositoryImpl repository = new UserRepositoryImpl(connection);
            exists = repository.findOne(user);
        } catch (Exception e) {
            throw new ServiceException(-1, "Server Error, try again later");
        }

        return exists;
    }

    @Override
    public User search(String query) throws Exception {
        Connection connection;
        User result;

        try {
            connection = DatabaseConnection.getConnection();
            UserRepositoryImpl repository = new UserRepositoryImpl(connection);
            result = repository.search(query);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(-1, "Server Error, try again later");
        }

        return result;
    }

    @Override
    public User exists(User user) throws Exception {
        Connection connection;
        User result;

        try {
            connection = DatabaseConnection.getConnection();
            UserRepositoryImpl repository = new UserRepositoryImpl(connection);
            result = repository.exists(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(-1, "Server Error, try again later");
        }

        return result;
    }

}
