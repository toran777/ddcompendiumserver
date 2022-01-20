package it.ddcompendium.utils;

public interface SQLQueries {
    String GET_ONE_CHARACTER = "SELECT * FROM characters c WHERE c.id = ?";

    String GET_USER_CHARACTERS = "SELECT * FROM characters c WHERE c.users_id = ?";

    String GET_CHARACTER_SPELLS = "SELECT * FROM spells s WHERE s.id IN (SELECT spells_id FROM characters_has_spells c WHERE c.characters_id = ?)";

    String INSERT_CHARACTER = "INSERT INTO characters(name, class, users_id) VALUES (?, ?, ?)";

    String DELETE_CHARACTER_SPELLS = "DELETE FROM characters_has_spells c WHERE c.characters_id = ?";

    String DELETE_CHARACTER = "DELETE FROM characters c WHERE c.id = ?";

    String GET_ALL_SPELLS_WITH_OFFSET = "SELECT * FROM spells LIMIT ?, ?";

    String QUERY_SPELL = "SELECT * FROM spells WHERE name LIKE ? OR school LIKE ?";

    String DELETE_CHARACTER_SPELL = "DELETE FROM characters_has_spells c WHERE c.characters_id = ? AND c.spells_id = ?";

    String INSERT_CHARACTER_SPELL = "INSERT INTO characters_has_spells VALUES (?, ?)";

    String GET_USER = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";

    String INSERT_USER = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

    String GET_RECOMMENDATIONS_USER = "SELECT r.id recommendation_id, u.username recommended_by_username, s.* FROM (SELECT * FROM recommendations WHERE recommended_to = ?) r INNER JOIN users u ON r.recommended_by = u.id INNER JOIN spells s ON r.spells_id = s.id";

    String INSERT_RECOMMENDATION = "INSERT INTO recommendations(spells_id, recommended_by, recommended_to) VALUES (?, ?, ?)";

    String DELETE_RECOMMENDATION = "DELETE FROM recommendations WHERE id = ?";

    String FIND_USER_BY_USERNAME = "SELECT * FROM users WHERE username = ?";

    String GET_USER_BY_USERNAME_OR_MAIL = "SELECT * FROM users WHERE (username = ? OR email = ?)";
}
