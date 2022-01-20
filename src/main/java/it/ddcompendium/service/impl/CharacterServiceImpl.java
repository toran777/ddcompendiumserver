package it.ddcompendium.service.impl;

import it.ddcompendium.repository.entities.Character;
import it.ddcompendium.repository.impl.CharacterRepositoryImpl;
import it.ddcompendium.service.CharacterService;
import it.ddcompendium.service.exceptions.ServiceException;
import it.ddcompendium.utils.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class CharacterServiceImpl implements CharacterService {

    @Override
    public List<Character> findAll(Integer id) throws Exception {
        Connection connection;
        List<Character> result;

        try {
            connection = DatabaseConnection.getConnection();
            CharacterRepositoryImpl repository = new CharacterRepositoryImpl(connection);
            Character c = new Character();
            c.setId(id);
            result = repository.findAll(c);
        } catch (Exception e) {
            throw new ServiceException(-1, "Server Error, try again later");
        }

        return result;
    }

    @Override
    public void insert(Character character) throws Exception {
        Connection connection;

        try {
            connection = DatabaseConnection.getConnection();
            CharacterRepositoryImpl repository = new CharacterRepositoryImpl(connection);
            repository.insert(character);
        } catch (Exception e) {
            throw new ServiceException(-1, "No user with this id");
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        Connection connection;

        try {
            connection = DatabaseConnection.getConnection();
            CharacterRepositoryImpl repository = new CharacterRepositoryImpl(connection);
            repository.delete(id);
        } catch (Exception e) {
            throw new ServiceException(-1, "No character with this id");
        }
    }

    @Override
    public Character findOne(Integer id) throws Exception {
        Connection connection;
        Character result;

        try {
            connection = DatabaseConnection.getConnection();
            CharacterRepositoryImpl repository = new CharacterRepositoryImpl(connection);
            Character c = new Character();
            c.setId(id);
            result = repository.findOne(c);
        } catch (Exception e) {
            throw new ServiceException(-1, "No character with this id");
        }

        return result;
    }
}
