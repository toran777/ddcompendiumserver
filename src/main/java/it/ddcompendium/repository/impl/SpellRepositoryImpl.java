package it.ddcompendium.repository.impl;

import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.utils.SQLQueries;
import it.ddcompendium.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SpellRepositoryImpl implements SQLQueries {
    private final Connection connection;

    public SpellRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Spell> findAll(Integer offset) throws Exception {
        PreparedStatement statement = connection.prepareStatement(GET_ALL_SPELLS_WITH_OFFSET);
        statement.setInt(1, offset - 30);
        statement.setInt(2, offset);
        ResultSet set = statement.executeQuery();

        return Utils.getSpellsFromSet(set);
    }

    public List<Spell> search(String query) throws Exception {
        PreparedStatement statement = connection.prepareStatement(QUERY_SPELL);
        statement.setString(1, "%" + query + "%");
        statement.setString(2, "%" + query + "%");
        ResultSet set = statement.executeQuery();

        return Utils.getSpellsFromSet(set);
    }

    public void deleteSpell(Integer idCharacter, Integer idSpell) throws Exception {
        PreparedStatement statement = connection.prepareStatement(DELETE_CHARACTER_SPELL);
        statement.setInt(1, idCharacter);
        statement.setInt(2, idSpell);
        statement.executeUpdate();
    }

    public void addSpell(Integer idCharacter, Integer idSpell) throws Exception {
        PreparedStatement statement = connection.prepareStatement(INSERT_CHARACTER_SPELL);
        statement.setInt(1, idCharacter);
        statement.setInt(2, idSpell);
        statement.executeUpdate();
    }
}
