package it.ddcompendium.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.ddcompendium.repository.entities.Recommendation;
import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.repository.entities.User;
import it.ddcompendium.responses.ListResponse;
import it.ddcompendium.responses.Status;
import it.ddcompendium.responses.StatusResponse;
import it.ddcompendium.service.RecommendService;
import it.ddcompendium.service.impl.RecommendServiceImpl;
import it.ddcompendium.utils.Utils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/recommend")
public class RecommendServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RecommendService service;
    private final Gson gson;

    public RecommendServlet() {
        service = new RecommendServiceImpl();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ListResponse<Recommendation> resultResponse = new ListResponse<>();
        Status status;

        try {
            Integer id = Utils.getParameter(req, resp, "id");
            List<Recommendation> recommendations = service.findAll(id);
            resultResponse.setData(recommendations);
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

        Status status;
        StatusResponse resultResponse = new StatusResponse();

        Recommendation recommendation = new Recommendation();
        User by = new User();
        by.setId(object.get("user_by").getAsInt());
        User to = new User();
        to.setId(object.get("user_to").getAsInt());
        Spell spell = new Spell();
        spell.setId(object.get("spell_id").getAsInt());
        recommendation.setRecommendation(spell);
        recommendation.setRecommendedBy(by);
        recommendation.setRecommendedTo(to);

        try {
            service.insert(recommendation);
            status = new Status(0, "Recommendation sent");
        } catch (Exception e) {
            e.printStackTrace();
            status = new Status(-1, e.getMessage());
        }

        resultResponse.setStatus(status);
        String json = gson.toJson(resultResponse);
        Utils.writeResponse(resp, json);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Status status;
        StatusResponse resultResponse = new StatusResponse();

        try {
            Integer id = Utils.getParameter(req, resp, "id");
            service.delete(id);
            status = new Status(0, "Recommendation deleted successfully");
        } catch (Exception e) {
            status = new Status(-1, e.getMessage());
        }

        resultResponse.setStatus(status);
        String json = gson.toJson(resultResponse);
        Utils.writeResponse(resp, json);
    }
}
