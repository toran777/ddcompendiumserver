package it.ddcompendium.service;

import java.util.List;

import it.ddcompendium.repository.entities.Recommendation;

public interface RecommendService {
	public List<Recommendation> findAll(Integer id) throws Exception;

	public void insert(Recommendation recommendation) throws Exception;

	public void delete(Integer id) throws Exception;
}
