package it.ddcompendium.utils;

public interface SQLQueries {
	final String GET_ONE_CHARACTER = "SELECT * FROM characters c WHERE c.id = ?";

	final String GET_USER_CHARACTERS = "SELECT * FROM characters c WHERE c.users_id = ?";

	final String GET_CHARACTER_SPELLS = "SELECT * FROM spells s WHERE s.id IN (SELECT spells_id FROM characters_has_spells c WHERE c.characters_id = ?)";

	final String INSERT_CHARACTER = "INSERT INTO characters(name, class, users_id) VALUES (?, ?, ?)";

	final String DELETE_CHARACTER_SPELLS = "DELETE FROM characters_has_spells c WHERE c.characters_id = ?";

	final String DELETE_CHARACTER = "DELETE FROM characters c WHERE c.id = ?";

	final String GET_ALL_SPELLS = "SELECT * FROM spells";

	final String GET_ALL_SPELLS_WITH_OFFSET = "SELECT * FROM spells LIMIT ?, ?";

	final String QUERY_SPELL = "SELECT * FROM spells WHERE name LIKE ? OR school LIKE ?";

	final String DELETE_CHARACTER_SPELL = "DELETE FROM characters_has_spells c WHERE c.characters_id = ? AND c.spells_id = ?";

	final String INSERT_CHARACTER_SPELL = "INSERT INTO characters_has_spells VALUES (?, ?)";

	final String GET_USER = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";

	final String GET_ALL_USERS = "SELECT * FROM users";

	final String INSERT_USER = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

	final String GET_RECOMMENDATIONS_USER = "SELECT r.id recommendation_id, u.username recommended_by_username, s.* FROM (SELECT * FROM recommendations WHERE recommended_to = ?) r INNER JOIN users u ON r.recommended_by = u.id INNER JOIN spells s ON r.spells_id = s.id";

	final String INSERT_RECOMMENDATION = "INSERT INTO recommendations(spells_id, recommended_by, recommended_to) VALUES (?, ?, ?)";

	final String DELETE_RECOMMENDATION = "DELETE FROM recommendations WHERE id = ?";

	final String FIND_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";
}
