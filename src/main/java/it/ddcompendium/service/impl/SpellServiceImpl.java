package it.ddcompendium.service.impl;

import java.sql.Connection;
import java.util.List;

import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.repository.impl.SpellRepositoryImpl;
import it.ddcompendium.service.SpellService;
import it.ddcompendium.service.exceptions.ServiceException;
import it.ddcompendium.utils.DatabaseConnection;

public class SpellServiceImpl implements SpellService {

	@Override
	public List<Spell> findAll(Integer offset) throws Exception {
		Connection connection = null;
		List<Spell> result = null;

		try {
			connection = DatabaseConnection.getConnection();
			SpellRepositoryImpl repository = new SpellRepositoryImpl(connection);
			result = repository.findAll(offset);
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
	public void addSpell(Integer idCharacter, Integer idSpell) throws Exception {
		Connection connection = null;

		try {
			connection = DatabaseConnection.getConnection();
			SpellRepositoryImpl repository = new SpellRepositoryImpl(connection);
			repository.addSpell(idCharacter, idSpell);
		} catch (Exception e) {
			throw new ServiceException(-1, "Server Error, try again later");
		} finally {
			connection.commit();
			connection.close();
		}
	}

	@Override
	public void deleteSpell(Integer idCharacter, Integer idSpell) throws Exception {
		Connection connection = null;

		try {
			connection = DatabaseConnection.getConnection();
			SpellRepositoryImpl repository = new SpellRepositoryImpl(connection);
			repository.deleteSpell(idCharacter, idSpell);
		} catch (Exception e) {
			throw new ServiceException(-1, "Server Error, try again later");
		} finally {
			connection.commit();
			connection.close();
		}
	}

	@Override
	public List<Spell> search(String query) throws Exception {
		Connection connection = null;
		List<Spell> result = null;

		try {
			connection = DatabaseConnection.getConnection();
			SpellRepositoryImpl repository = new SpellRepositoryImpl(connection);
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
