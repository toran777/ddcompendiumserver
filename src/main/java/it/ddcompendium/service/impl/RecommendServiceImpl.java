package it.ddcompendium.service.impl;

import it.ddcompendium.repository.entities.Recommendation;
import it.ddcompendium.repository.impl.RecommendationRepositoryImpl;
import it.ddcompendium.service.RecommendService;
import it.ddcompendium.service.exceptions.ServiceException;
import it.ddcompendium.utils.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class RecommendServiceImpl implements RecommendService {

    @Override
    public List<Recommendation> findAll(Integer id) throws Exception {
        Connection connection;
        List<Recommendation> result;

        try {
            connection = DatabaseConnection.getConnection();
            RecommendationRepositoryImpl repository = new RecommendationRepositoryImpl(connection);
            result = repository.findAll(id);
        } catch (Exception e) {
            throw new ServiceException(-1, "Server Error, try again later");
        }

        return result;
    }

    @Override
    public void insert(Recommendation recommendation) throws Exception {
        Connection connection;

        try {
            connection = DatabaseConnection.getConnection();
            RecommendationRepositoryImpl repository = new RecommendationRepositoryImpl(connection);
            repository.insert(recommendation);
        } catch (Exception e) {
            throw new ServiceException(-1, "Server Error, try again later");
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        Connection connection;

        try {
            connection = DatabaseConnection.getConnection();
            RecommendationRepositoryImpl repository = new RecommendationRepositoryImpl(connection);
            repository.delete(id);
        } catch (Exception e) {
            throw new ServiceException(-1, "Server Error, try again later");
        }
    }
}
