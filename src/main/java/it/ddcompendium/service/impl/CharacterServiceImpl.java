package it.ddcompendium.service.impl;

import java.sql.Connection;
import java.util.List;

import it.ddcompendium.repository.entities.Character;
import it.ddcompendium.repository.impl.CharacterRepositoryImpl;
import it.ddcompendium.service.CharacterService;
import it.ddcompendium.service.exceptions.ServiceException;
import it.ddcompendium.utils.DatabaseConnection;

public class CharacterServiceImpl implements CharacterService {

	@Override
	public List<Character> findAll(Integer id) throws Exception {
		Connection connection = null;
		List<Character> result = null;

		try {
			connection = DatabaseConnection.getConnection();
			CharacterRepositoryImpl repository = new CharacterRepositoryImpl(connection);
			Character c = new Character();
			c.setId(id);
			result = repository.findAll(c);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(-1, "Server Error, try again later");
		} finally {
			connection.commit();
			connection.close();
		}

		return result;
	}

	@Override
	public void insert(Character character) throws Exception {
		Connection connection = null;

		try {
			connection = DatabaseConnection.getConnection();
			CharacterRepositoryImpl repository = new CharacterRepositoryImpl(connection);
			repository.insert(character);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(-1, "Server Error, try again later");
		} finally {
			connection.commit();
			connection.close();
		}
	}

	@Override
	public void delete(Integer id) throws Exception {
		Connection connection = null;

		try {
			connection = DatabaseConnection.getConnection();
			CharacterRepositoryImpl repository = new CharacterRepositoryImpl(connection);
			repository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(-1, "Server Error, try again later");
		} finally {
			connection.commit();
			connection.close();
		}
	}

	@Override
	public Character findOne(Integer id) throws Exception {
		Connection connection = null;
		Character result = null;

		try {
			connection = DatabaseConnection.getConnection();
			CharacterRepositoryImpl repository = new CharacterRepositoryImpl(connection);
			Character c = new Character();
			c.setId(id);
			result = repository.findOne(c);
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
