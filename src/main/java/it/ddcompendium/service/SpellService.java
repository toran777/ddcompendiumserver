package it.ddcompendium.service;

import java.util.List;

import it.ddcompendium.repository.entities.Spell;

public interface SpellService {
	public List<Spell> findAll() throws Exception;

	public void addSpell(Integer idCharcter, Integer idSpell) throws Exception;

	public void deleteSpell(Integer idCharcter, Integer idSpell) throws Exception;
}