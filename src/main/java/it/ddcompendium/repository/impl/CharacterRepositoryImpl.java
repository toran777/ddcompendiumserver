package it.ddcompendium.repository.impl;

import it.ddcompendium.repository.CrudRepository;
import it.ddcompendium.repository.entities.Character;
import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.utils.SQLQueries;
import it.ddcompendium.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class CharacterRepositoryImpl extends CrudRepository<Character> implements SQLQueries {
    private final Connection connection;

    public CharacterRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Character findOne(Character t) throws Exception {
        PreparedStatement statement = connection.prepareStatement(GET_ONE_CHARACTER);
        statement.setInt(1, t.getId());

        ResultSet set = statement.executeQuery();
        Character result = Utils.getCharactersFromSet(set).get(0);

        statement.close();

        statement = connection.prepareStatement(GET_CHARACTER_SPELLS);
        statement.setInt(1, result.getId());

        ResultSet spellSet = statement.executeQuery();
        List<Spell> characterSpells = Utils.getSpellsFromSet(spellSet);

        result.setSpells(characterSpells);

        return result;
    }

    @Override
    public List<Character> findAll(Character t) throws Exception {
        PreparedStatement statement = connection.prepareStatement(GET_USER_CHARACTERS);
        statement.setInt(1, t.getId());
        ResultSet set = statement.executeQuery();

        return Utils.getCharactersFromSet(set);
    }

    @Override
    public void insert(Character t) throws Exception {
        PreparedStatement statement = connection.prepareStatement(INSERT_CHARACTER);
        statement.setString(1, t.getName());
        statement.setString(2, t.getClasse());
        statement.setInt(3, t.getIdUser());
        statement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        PreparedStatement statement = connection.prepareStatement(DELETE_CHARACTER_SPELLS);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
        statement = connection.prepareStatement(DELETE_CHARACTER);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
