package it.ddcompendium.service.impl;

import java.sql.Connection;
import java.util.List;

import it.ddcompendium.repository.entities.Recommendation;
import it.ddcompendium.repository.impl.RecommendationRepositoryImpl;
import it.ddcompendium.service.RecommendService;
import it.ddcompendium.service.exceptions.ServiceException;
import it.ddcompendium.utils.DatabaseConnection;

public class RecommendServiceImpl implements RecommendService {

	@Override
	public List<Recommendation> findAll(Integer id) throws Exception {
		Connection connection = null;
		List<Recommendation> result = null;

		try {
			connection = DatabaseConnection.getConnection();
			RecommendationRepositoryImpl repository = new RecommendationRepositoryImpl(connection);
			result = repository.findAll(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(-1, "Server Error, try again later");
		} finally {
			connection.commit();
			connection.close();
		}

		return result;
	}

	@Override
	public void insert(Recommendation recommendation) throws Exception {
		Connection connection = null;

		try {
			connection = DatabaseConnection.getConnection();
			RecommendationRepositoryImpl repository = new RecommendationRepositoryImpl(connection);
			repository.insert(recommendation);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(-1, "Server Error, try again later");
		} finally {
			connection.commit();
			connection.close();
		}
	}

	@Override
	public void delete(Integer id) throws Exception {
		Connection connection = null;

		try {
			connection = DatabaseConnection.getConnection();
			RecommendationRepositoryImpl repository = new RecommendationRepositoryImpl(connection);
			repository.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(-1, "Server Error, try again later");
		} finally {
			connection.commit();
			connection.close();
		}
	}

}
