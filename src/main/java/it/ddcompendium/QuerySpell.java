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

import it.ddcompendium.repository.entities.Spell;
import it.ddcompendium.responses.ListResponse;
import it.ddcompendium.responses.Status;
import it.ddcompendium.service.SpellService;
import it.ddcompendium.service.impl.SpellServiceImpl;
import it.ddcompendium.utils.Utils;

@WebServlet("/QuerySpells")
public class QuerySpell extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SpellService service = new SpellServiceImpl();

	public QuerySpell() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ListResponse<Spell> sResponse = new ListResponse<>();
		response.setContentType("application/json");

		PrintWriter writer = response.getWriter();

		String string = Utils.read(request.getReader());
		JsonObject object = JsonParser.parseString(string).getAsJsonObject();

		String query = object.get("query").getAsString();

		Status status = null;

		try {
			List<Spell> spells = service.search(query);
			sResponse.setData(spells);
			status = new Status(0, "done");
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
