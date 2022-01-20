package it.ddcompendium.service;

import it.ddcompendium.repository.entities.Spell;

import java.util.List;

public interface SpellService {
    List<Spell> findAll(Integer offset) throws Exception;

    void addSpell(Integer idCharacter, Integer idSpell) throws Exception;

    void deleteSpell(Integer idCharacter, Integer idSpell) throws Exception;

    List<Spell> search(String query) throws Exception;
}
