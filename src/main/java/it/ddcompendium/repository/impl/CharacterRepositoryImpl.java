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

public class CharacterRepositoryImpl implements CrudRepository<Character> {
	private Connection connection;

	public CharacterRepositoryImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Character findOne(Character t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Character> findAll(Character t) throws Exception {
		PreparedStatement statement = null;
		ResultSet set = null;
		ResultSet spellSet = null;
		List<Character> result = new ArrayList<>();

		try {
			statement = connection.prepareStatement("SELECT * FROM characters c WHERE c.id_utente = ?");
			statement.setInt(1, t.getIdUser());

			set = statement.executeQuery();

			while (set.next()) {
				Character character = new Character();
				character.setId(set.getInt("id"));
				character.setName(set.getString("nome"));
				character.setClasse(set.getString("classe"));
				character.setIdUser(set.getInt("id_utente"));

				List<Spell> characterSpells = new ArrayList<>();

				statement = connection.prepareStatement(
						"SELECT * FROM spells s WHERE s.id IN (SELECT spell_id FROM character_has_spells c WHERE c.character_id = ?)");
				statement.setInt(1, character.getId());

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

				character.setSpells(characterSpells);
				result.add(character);
			}

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
	public void insert(Character t) throws Exception {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("INSERT INTO characters(nome, classe, id_utente) VALUES (?, ?, ?)");
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
			statement = connection.prepareStatement("DELETE FROM character_has_spells c WHERE c.character_id = ?");
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
			statement = connection.prepareStatement("DELETE FROM characters c WHERE c.id = ?");
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			statement.close();
		}
	}

}
