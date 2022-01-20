package it.ddcompendium.servlet.spells;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.responses.ListResponse;
import it.ddcompendium.responses.Status;
import it.ddcompendium.responses.StatusResponse;
import it.ddcompendium.service.SpellService;
import it.ddcompendium.service.impl.SpellServiceImpl;
import it.ddcompendium.utils.Utils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/spells")
public class SpellsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final SpellService service;
    private final Gson gson;

    public SpellsServlet() {
        service = new SpellServiceImpl();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Status status;
        ListResponse<Spell> resultResponse = new ListResponse<>();

        try {
            Integer offset = Utils.getParameter(req, resp, "offset");
            List<Spell> spells = service.findAll(offset);
            resultResponse.setData(spells);
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

        Integer idCharacter = object.get("idCharacter").getAsInt();
        Integer idSpell = object.get("idSpell").getAsInt();
        Status status;
        StatusResponse resultResponse = new StatusResponse();

        try {
            service.addSpell(idCharacter, idSpell);
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
            Integer idCharacter = Utils.getParameter(req, resp, "idCharacter");
            Integer idSpell = Utils.getParameter(req, resp, "idSpell");
            service.deleteSpell(idCharacter, idSpell);
            status = new Status(0, "Done");
        } catch (Exception e) {
            status = new Status(-1, e.getMessage());
        }

        resultResponse.setStatus(status);
        String json = gson.toJson(resultResponse);
        Utils.writeResponse(resp, json);
    }
}
