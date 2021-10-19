package it.ddcompendium.service;

import it.ddcompendium.repository.entities.User;

public interface UserService {
	public User findOne(User user) throws Exception;

	public void insert(User user) throws Exception;
}
