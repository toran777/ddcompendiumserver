package it.ddcompendium.servlet.characters;

import com.google.gson.Gson;
import it.ddcompendium.repository.entities.Character;
import it.ddcompendium.responses.Response;
import it.ddcompendium.responses.Status;
import it.ddcompendium.service.CharacterService;
import it.ddcompendium.service.impl.CharacterServiceImpl;
import it.ddcompendium.utils.Utils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/character/detail")
public class CharacterDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final CharacterService service;
    private final Gson gson;

    public CharacterDetailServlet() {
        service = new CharacterServiceImpl();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Response<Character> resultResponse = new Response<>();
        Status status;

        try {
            Integer id = Utils.getParameter(req, resp, "id");
            Character character = service.findOne(id);
            resultResponse.setData(character);
            status = new Status(0, "Done");
        } catch (Exception e) {
            status = new Status(-1, e.getMessage());
        }

        resultResponse.setStatus(status);
        String json = gson.toJson(resultResponse);
        Utils.writeResponse(resp, json);
    }
}
