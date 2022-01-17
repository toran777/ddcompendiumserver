package it.ddcompendium;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import it.ddcompendium.repository.entities.Recommendation;
import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.repository.entities.User;
import it.ddcompendium.responses.ListResponse;
import it.ddcompendium.responses.Status;
import it.ddcompendium.responses.StatusResponse;
import it.ddcompendium.service.RecommendService;
import it.ddcompendium.service.impl.RecommendServiceImpl;
import it.ddcompendium.utils.Utils;

@WebServlet("/Recommend")
public class RecommendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RecommendService service = new RecommendServiceImpl();

	public RecommendServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();

		ListResponse<Recommendation> rResponse = new ListResponse<>();
		Status status = null;

		try {
			List<Recommendation> recommendations = service.findAll(id);
			rResponse.setData(recommendations);
			status = new Status(0, "done");
		} catch (Exception e) {
			status = new Status(-1, e.getMessage());
		}

		rResponse.setStatus(status);
		String json = new Gson().toJson(rResponse);
		writer.print(json);
		writer.flush();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StatusResponse sResponse = new StatusResponse();
		response.setContentType("application/json");

		PrintWriter writer = response.getWriter();

		String string = Utils.read(request.getReader());
		JsonObject object = JsonParser.parseString(string).getAsJsonObject();

		Recommendation recommendation = new Recommendation();
		User by = new User();
		by.setId(object.get("by_id").getAsInt());
		User to = new User();
		to.setId(object.get("to_id").getAsInt());
		Spell spell = new Spell();
		spell.setId(object.get("spell_id").getAsInt());
		recommendation.setRecommendation(spell);
		recommendation.setRecommendedBy(by);
		recommendation.setRecommendedTo(to);

		Status status = null;

		try {
			service.insert(recommendation);
			status = new Status(0, "Recommendation sent");
		} catch (Exception e) {
			e.printStackTrace();
			status = new Status(-1, e.getMessage());
		}

		sResponse.setStatus(status);
		String json = new Gson().toJson(sResponse);
		writer.print(json);
		writer.flush();
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StatusResponse sResponse = new StatusResponse();
		resp.setContentType("application/json");

		PrintWriter writer = resp.getWriter();

		Integer id = Integer.parseInt(req.getParameter("id"));

		Status status = null;

		try {
			service.delete(id);
			status = new Status(0, "Recommendation deleted successfully");
		} catch (Exception e) {
			e.printStackTrace();
			status = new Status(-1, e.getMessage());
		}

		sResponse.setStatus(status);
		String json = new Gson().toJson(sResponse);
		writer.print(json);
		writer.flush();
	}

}
