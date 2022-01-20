package it.ddcompendium.repository.impl;

import it.ddcompendium.repository.CrudRepository;
import it.ddcompendium.repository.entities.User;
import it.ddcompendium.utils.SQLQueries;
import it.ddcompendium.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepositoryImpl extends CrudRepository<User> implements SQLQueries {
    private final Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User findOne(User t) throws Exception {
        PreparedStatement statement = connection.prepareStatement(GET_USER);
        statement.setString(1, t.getUsername());
        statement.setString(2, t.getUsername());
        statement.setString(3, t.getPassword());

        ResultSet set = statement.executeQuery();

        return Utils.getUserFromSet(set);
    }

    public User exists(User t) throws Exception {
        PreparedStatement statement = connection.prepareStatement(GET_USER_BY_USERNAME_OR_MAIL);
        statement.setString(1, t.getUsername());
        statement.setString(2, t.getEmail());

        ResultSet set = statement.executeQuery();

        return Utils.getUserFromSet(set);
    }

    @Override
    public void insert(User t) throws Exception {
        PreparedStatement statement = connection.prepareStatement(INSERT_USER);
        statement.setString(1, t.getUsername());
        statement.setString(2, t.getEmail());
        statement.setString(3, t.getPassword());
        statement.executeUpdate();
    }

    public User search(String query) throws Exception {
        PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_USERNAME);
        statement.setString(1, query);
        ResultSet set = statement.executeQuery();
        User result = null;

        while (set.next()) {
            result = new User();
            result.setId(set.getInt("id"));
            result.setUsername(set.getString("username"));
        }

        return result;
    }

}
