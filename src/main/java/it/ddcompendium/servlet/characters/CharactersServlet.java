package it.ddcompendium.servlet.characters;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.ddcompendium.repository.entities.Character;
import it.ddcompendium.responses.ListResponse;
import it.ddcompendium.responses.Status;
import it.ddcompendium.responses.StatusResponse;
import it.ddcompendium.service.CharacterService;
import it.ddcompendium.service.impl.CharacterServiceImpl;
import it.ddcompendium.utils.Utils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/characters")
public class CharactersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CharacterService service;
    private final Gson gson;

    public CharactersServlet() {
        service = new CharacterServiceImpl();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ListResponse<Character> resultResponse = new ListResponse<>();
        Status status;

        try {
            Integer id = Utils.getParameter(req, resp, "id");
            List<Character> characters = service.findAll(id);
            resultResponse.setData(characters);
            status = new Status(0, "Done");
        } catch (Exception e) {
            status = new Status(-1, e.getMessage());
        }

        resultResponse.setStatus(status);
        String json = gson.toJson(resultResponse);
        Utils.writeResponse(resp, json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject object = Utils.dataToJson(req, resp);

        Character character = new Character(object);
        Status status;
        StatusResponse resultResponse = new StatusResponse();

        try {
            service.insert(character);
            status = new Status(0, "Done");
        } catch (Exception e) {
            status = new Status(-1, e.getMessage());
        }

        resultResponse.setStatus(status);
        String json = gson.toJson(resultResponse);
        Utils.writeResponse(resp, json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StatusResponse resultResponse = new StatusResponse();
        Status status;

        try {
            Integer id = Utils.getParameter(req, resp, "id");
            service.delete(id);
            status = new Status(0, "Character deleted successfully");
        } catch (Exception e) {
            status = new Status(-1, e.getMessage());
        }

        resultResponse.setStatus(status);
        String json = gson.toJson(resultResponse);
        Utils.writeResponse(resp, json);
    }
}
