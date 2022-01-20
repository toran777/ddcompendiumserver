package it.ddcompendium.service;

import it.ddcompendium.repository.entities.Character;

import java.util.List;

public interface CharacterService {
    List<Character> findAll(Integer id) throws Exception;

    void insert(Character character) throws Exception;

    void delete(Integer id) throws Exception;

    Character findOne(Integer id) throws Exception;
}
