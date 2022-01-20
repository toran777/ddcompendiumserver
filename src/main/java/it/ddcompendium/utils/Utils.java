package it.ddcompendium.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.ddcompendium.repository.entities.Character;
import it.ddcompendium.repository.entities.Recommendation;
import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.repository.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static String read(BufferedReader reader) throws IOException {
        StringBuilder string = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            string.append(line);
        }

        return string.toString();
    }

    public static JsonObject dataToJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String string = read(request.getReader());

        return JsonParser.parseString(string).getAsJsonObject();
    }

    public static List<Spell> getSpellsFromSet(ResultSet set) throws Exception {
        List<Spell> result = new ArrayList<>();

        while (set.next()) {
            Spell spell = Utils.getSpellFromSet(set);
            result.add(spell);
        }

        return result;
    }

    public static Spell getSpellFromSet(ResultSet set) throws Exception {
        Spell spell = new Spell();

        spell.setId(set.getInt("id"));
        spell.setName(set.getString("name"));
        spell.setDesc(set.getString("desc"));
        spell.setPage(set.getString("page"));
        spell.setRange(set.getString("range"));
        spell.setComponents(set.getString("components"));
        spell.setRitual(set.getString("ritual"));
        spell.setDuration(set.getString("duration"));
        spell.setConcentration(set.getString("concentration"));
        spell.setCastingTime(set.getString("casting_time"));
        spell.setLevel(set.getString("level"));
        spell.setSchool(set.getString("school"));
        spell.setClasse(set.getString("class"));

        return spell;
    }

    public static List<Character> getCharactersFromSet(ResultSet set) throws Exception {
        List<Character> result = new ArrayList<>();

        while (set.next()) {
            Character character = new Character();
            character.setId(set.getInt("id"));
            character.setName(set.getString("name"));
            character.setClasse(set.getString("class"));
            character.setIdUser(set.getInt("users_id"));

            result.add(character);
        }

        return result;
    }

    public static List<Recommendation> getRecommendationsFromSet(ResultSet set) throws Exception {
        List<Recommendation> result = new ArrayList<>();

        while (set.next()) {
            Recommendation recommendation = new Recommendation();
            recommendation.setId(set.getInt("recommendation_id"));
            User by = new User();
            by.setUsername(set.getString("recommended_by_username"));
            recommendation.setRecommendedBy(by);
            Spell spell = Utils.getSpellFromSet(set);
            recommendation.setRecommendation(spell);
            result.add(recommendation);
        }

        return result;
    }

    public static User getUserFromSet(ResultSet set) throws Exception {
        User result = new User();

        while (set.next()) {
            result.setId(set.getInt("id"));
            result.setUsername(set.getString("username"));
            result.setEmail(set.getString("email"));
        }

        return result;
    }

    public static void writeResponse(HttpServletResponse resp, String jsonResponse) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.print(jsonResponse);
        writer.flush();
    }

    public static Integer getParameter(HttpServletRequest req, HttpServletResponse resp, String id) {
        resp.setContentType("application/json");
        return Integer.parseInt(req.getParameter(id));
    }
}
