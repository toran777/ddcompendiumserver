package it.ddcompendium.service;

import java.util.List;

import it.ddcompendium.repository.entities.Character;

public interface CharacterService {
	public List<Character> findAll(Integer id) throws Exception;

	public void insert(Character character) throws Exception;

	public void delete(Integer id) throws Exception;
}
