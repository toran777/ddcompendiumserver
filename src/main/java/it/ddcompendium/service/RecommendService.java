package it.ddcompendium.service;

import it.ddcompendium.repository.entities.Recommendation;

import java.util.List;

public interface RecommendService {
    List<Recommendation> findAll(Integer id) throws Exception;

    void insert(Recommendation recommendation) throws Exception;

    void delete(Integer id) throws Exception;
}
