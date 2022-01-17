package it.ddcompendium.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.ddcompendium.repository.CrudRepository;
import it.ddcompendium.repository.entities.Recommendation;
import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.repository.entities.User;
import it.ddcompendium.utils.SQLQueries;

public class RecommendationRepositoryImpl implements CrudRepository<Recommendation>, SQLQueries {
	private Connection connection;

	public RecommendationRepositoryImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Recommendation findOne(Recommendation t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recommendation> findAll(Recommendation t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Recommendation> findAll(Integer id) throws Exception {
		PreparedStatement statement = null;
		ResultSet set = null;
		List<Recommendation> result = new ArrayList<>();

		try {
			statement = connection.prepareStatement(GET_RECOMMENDATIONS_USER);
			statement.setInt(0, id);
			set = statement.executeQuery();

			while (set.next()) {
				Recommendation recommendation = new Recommendation();
				recommendation.setId(set.getInt("recommendation_id"));
				User by = new User();
				by.setUsername(set.getString("recommended_by_username"));
				recommendation.setRecommendedBy(by);
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
				recommendation.setRecommendation(spell);
				result.add(recommendation);
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
	public void insert(Recommendation t) throws Exception {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(INSERT_RECOMMENDATION);
			statement.setInt(1, t.getRecommendation().getId());
			statement.setInt(2, t.getRecommendedBy().getId());
			statement.setInt(3, t.getRecommendedTo().getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			statement.close();
		}
	}

	@Override
	public void update(Recommendation t) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) throws Exception {
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(DELETE_RECOMMENDATION);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			statement.close();
		}
	}

}
