package it.ddcompendium.responses;

import java.util.List;

import it.ddcompendium.repository.entities.Character;

public class CharactersStatusResponse {
	private List<Character> characters;
	private Status status;

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
