package it.ddcompendium.service.impl;

import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.repository.impl.SpellRepositoryImpl;
import it.ddcompendium.service.SpellService;
import it.ddcompendium.service.exceptions.ServiceException;
import it.ddcompendium.utils.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class SpellServiceImpl implements SpellService {

    @Override
    public List<Spell> findAll(Integer offset) throws Exception {
        Connection connection;
        List<Spell> result;

        try {
            connection = DatabaseConnection.getConnection();
            SpellRepositoryImpl repository = new SpellRepositoryImpl(connection);
            result = repository.findAll(offset);
        } catch (Exception e) {
            throw new ServiceException(-1, "Server Error, try again later");
        }

        return result;
    }

    @Override
    public void addSpell(Integer idCharacter, Integer idSpell) throws Exception {
        Connection connection;

        try {
            connection = DatabaseConnection.getConnection();
            SpellRepositoryImpl repository = new SpellRepositoryImpl(connection);
            repository.addSpell(idCharacter, idSpell);
        } catch (Exception e) {
            throw new ServiceException(-1, "Server Error, try again later");
        }
    }

    @Override
    public void deleteSpell(Integer idCharacter, Integer idSpell) throws Exception {
        Connection connection;

        try {
            connection = DatabaseConnection.getConnection();
            SpellRepositoryImpl repository = new SpellRepositoryImpl(connection);
            repository.deleteSpell(idCharacter, idSpell);
        } catch (Exception e) {
            throw new ServiceException(-1, "Server Error, try again later");
        }
    }

    @Override
    public List<Spell> search(String query) throws Exception {
        Connection connection;
        List<Spell> result;

        try {
            connection = DatabaseConnection.getConnection();
            SpellRepositoryImpl repository = new SpellRepositoryImpl(connection);
            result = repository.search(query);
        } catch (Exception e) {
            throw new ServiceException(-1, "Server Error, try again later");
        }

        return result;
    }

}
