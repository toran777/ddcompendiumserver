
package it.ddcompendium.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.ddcompendium.repository.CrudRepository;
import it.ddcompendium.repository.entities.Character;
import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.utils.SQLQueries;

public class CharacterRepositoryImpl implements CrudRepository<Character>, SQLQueries {
	private Connection connection;

	public CharacterRepositoryImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Character findOne(Character t) throws Exception {
		PreparedStatement statement = null;
		ResultSet set = null;
		ResultSet spellSet = null;
		Character result = new Character();

		try {
			statement = connection.prepareStatement(GET_ONE_CHARACTER);
			statement.setInt(1, t.getId());

			set = statement.executeQuery();

			while (set.next()) {
				result.setId(set.getInt("id"));
				result.setName(set.getString("name"));
				result.setClasse(set.getString("class"));
				result.setIdUser(set.getInt("users_id"));
			}
			
			statement.close();
			
			List<Spell> characterSpells = new ArrayList<>();

			statement = connection.prepareStatement(GET_CHARACTER_SPELLS);
			statement.setInt(1, result.getId());

			spellSet = statement.executeQuery();

			while (spellSet.next()) {
				Spell spell = new Spell();
				spell.setId(spellSet.getInt("id"));
				spell.setName(spellSet.getString("name"));
				spell.setDesc(spellSet.getString("desc"));
				spell.setPage(spellSet.getString("page"));
				spell.setRange(spellSet.getString("range"));
				spell.setComponents(spellSet.getString("components"));
				spell.setRitual(spellSet.getString("ritual"));
				spell.setDuration(spellSet.getString("duration"));
				spell.setConcentration(spellSet.getString("concentration"));
				spell.setCastingTime(spellSet.getString("casting_time"));
				spell.setLevel(spellSet.getString("level"));
				spell.setSchool(spellSet.getString("school"));
				spell.setClasse(spellSet.getString("class"));
				characterSpells.add(spell);
			}

			result.setSpells(characterSpells);

		} catch (SQLException e) {
			throw e;
		} finally {
			statement.close();
			set.close();

			if (spellSet != null)
				spellSet.close();
		}

		return result;
	}

	@Override
	public List<Character> findAll(Character t) throws Exception {
		PreparedStatement statement = null;
		ResultSet set = null;
		List<Character> result = new ArrayList<>();

		try {
			statement = connection.prepareStatement(GET_USER_CHARACTERS);
			statement.setInt(1, t.getId());

			set = statement.executeQuery();

			while (set.next()) {
				Character character = new Character();
				character.setId(set.getInt("id"));
				character.setName(set.getString("name"));
				character.setClasse(set.getString("class"));
				character.setIdUser(set.getInt("users_id"));
				
				result.add(character);			}
		} catch (SQLException e) {
			throw e;
		} finally {
			statement.close();
			set.close();
		}

		return result;
	}

	@Override
	public void insert(Character t) throws Exception {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(INSERT_CHARACTER);
			statement.setString(1, t.getName());
			statement.setString(2, t.getClasse());
			statement.setInt(3, t.getIdUser());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			statement.close();
		}
	}

	@Override
	public void update(Character t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) throws Exception {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(DELETE_CHARACTER_SPELLS);
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
			statement = connection.prepareStatement(DELETE_CHARACTER);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			statement.close();
		}
	}

}
