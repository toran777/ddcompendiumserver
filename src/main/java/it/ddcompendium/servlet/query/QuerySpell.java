package it.ddcompendium.servlet.query;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.responses.ListResponse;
import it.ddcompendium.responses.Status;
import it.ddcompendium.service.SpellService;
import it.ddcompendium.service.impl.SpellServiceImpl;
import it.ddcompendium.utils.Utils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/querySpell")
public class QuerySpell extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final SpellService service;
    private final Gson gson;

    public QuerySpell() {
        service = new SpellServiceImpl();
        gson = new Gson();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject object = Utils.dataToJson(req, resp);

        String query = object.get("query").getAsString();
        Status status;
        ListResponse<Spell> resultResponse = new ListResponse<>();

        try {
            List<Spell> spells = service.search(query);
            resultResponse.setData(spells);
            status = new Status(0, "Done");
        } catch (Exception e) {
            status = new Status(-1, e.getMessage());
        }

        resultResponse.setStatus(status);
        String json = gson.toJson(resultResponse);
        Utils.writeResponse(resp, json);
    }
}
