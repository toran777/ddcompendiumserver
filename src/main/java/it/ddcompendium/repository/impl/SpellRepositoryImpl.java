package it.ddcompendium.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.ddcompendium.repository.CrudRepository;
import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.utils.SQLQueries;

public class SpellRepositoryImpl implements CrudRepository<Spell>, SQLQueries {
	private Connection connection;

	public SpellRepositoryImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Spell findOne(Spell t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Spell> findAll(Spell t) throws Exception {
		return null;
	}

	public List<Spell> findAll(Integer offset) throws Exception {
		PreparedStatement statement = null;
		ResultSet set = null;
		List<Spell> result = new ArrayList<>();

		try {
			statement = connection.prepareStatement(GET_ALL_SPELLS_WITH_OFFSET);
			statement.setInt(1, offset - 30);
			statement.setInt(2, offset);
			set = statement.executeQuery();

			while (set.next()) {
				Spell spell = new Spell();
				spell.setId(set.getInt("id"));
				spell.setName(set.getString("name"));
				spell.setDesc(set.getString("desc"));
				spell.setPage(set.getString("page"));
				spell.setRange(set.getString("range"));
				spell.setComponents(set.getString("components"));
				spell.setRitual(set.getString("ritual"));
				spell.setDuration(set.getString("duration"));
				spell.setConcentration(set.getString("concentration"));
				spell.setCastingTime(set.getString("casting_time"));
				spell.setLevel(set.getString("level"));
				spell.setSchool(set.getString("school"));
				spell.setClasse(set.getString("class"));
				result.add(spell);
			}
		} catch (SQLException e) {
			throw e;
		} catch (NullPointerException e) {
			return result;
		} finally {
			statement.close();
			set.close();
		}

		return result;
	}

	public List<Spell> search(String query) throws Exception {
		PreparedStatement statement = null;
		ResultSet set = null;
		List<Spell> result = new ArrayList<>();

		try {
			statement = connection.prepareStatement(QUERY_SPELL);
			statement.setString(1, "%" + query + "%");
			statement.setString(2, "%" + query + "%");
			set = statement.executeQuery();

			while (set.next()) {
				Spell spell = new Spell();
				spell.setId(set.getInt("id"));
				spell.setName(set.getString("name"));
				spell.setDesc(set.getString("desc"));
				spell.setPage(set.getString("page"));
				spell.setRange(set.getString("range"));
				spell.setComponents(set.getString("components"));
				spell.setRitual(set.getString("ritual"));
				spell.setDuration(set.getString("duration"));
				spell.setConcentration(set.getString("concentration"));
				spell.setCastingTime(set.getString("casting_time"));
				spell.setLevel(set.getString("level"));
				spell.setSchool(set.getString("school"));
				spell.setClasse(set.getString("class"));
				result.add(spell);
			}
		} catch (SQLException e) {
			throw e;
		} catch (NullPointerException e) {
			return result;
		} finally {
			statement.close();
			set.close();
		}

		return result;
	}

	@Override
	public void insert(Spell t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Spell t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) throws Exception {
		// TODO Auto-generated method stub

	}

	public void deleteSpell(Integer idCharacter, Integer idSpell) throws Exception {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(DELETE_CHARACTER_SPELL);
			statement.setInt(1, idCharacter);
			statement.setInt(2, idSpell);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			statement.close();
		}
	}

	public void addSpell(Integer idCharacter, Integer idSpell) throws Exception {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(INSERT_CHARACTER_SPELL);
			statement.setInt(1, idCharacter);
			statement.setInt(2, idSpell);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			statement.close();
		}
	}

}
