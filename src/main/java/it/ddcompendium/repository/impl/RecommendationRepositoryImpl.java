package it.ddcompendium.repository.impl;

import it.ddcompendium.repository.CrudRepository;
import it.ddcompendium.repository.entities.Recommendation;
import it.ddcompendium.utils.SQLQueries;
import it.ddcompendium.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class RecommendationRepositoryImpl extends CrudRepository<Recommendation> implements SQLQueries {
    private final Connection connection;

    public RecommendationRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Recommendation> findAll(Integer id) throws Exception {
        PreparedStatement statement = connection.prepareStatement(GET_RECOMMENDATIONS_USER);
        statement.setInt(1, id);
        ResultSet set = statement.executeQuery();

        return Utils.getRecommendationsFromSet(set);
    }

    @Override
    public void insert(Recommendation t) throws Exception {
        PreparedStatement statement = connection.prepareStatement(INSERT_RECOMMENDATION);
        statement.setInt(1, t.getRecommendation().getId());
        statement.setInt(2, t.getRecommendedBy().getId());
        statement.setInt(3, t.getRecommendedTo().getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(Integer id) throws Exception {
        PreparedStatement statement = connection.prepareStatement(DELETE_RECOMMENDATION);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
