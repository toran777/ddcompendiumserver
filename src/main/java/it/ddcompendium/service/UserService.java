package it.ddcompendium.service;

import it.ddcompendium.repository.entities.User;

public interface UserService {
    User findOne(User user) throws Exception;

    void insert(User user) throws Exception;

    User search(String query) throws Exception;

    User exists(User user) throws Exception;
}
