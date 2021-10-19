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
import it.ddcompendium.responses.SpellStatusResponse;
import it.ddcompendium.responses.Status;
import it.ddcompendium.responses.StatusResponse;
import it.ddcompendium.service.SpellService;
import it.ddcompendium.service.impl.SpellServiceImpl;
import it.ddcompendium.utils.Utils;

@WebServlet("/Spell")
public class SpellsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SpellService service = new SpellServiceImpl();

	public SpellsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();

		SpellStatusResponse sResponse = new SpellStatusResponse();
		Status status = null;

		try {
			List<Spell> spells = service.findAll();
			sResponse.setSpells(spells);
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		StatusResponse sResponse = new StatusResponse();
		response.setContentType("application/json");

		PrintWriter writer = response.getWriter();

		String string = Utils.read(request.getReader());
		JsonObject object = JsonParser.parseString(string).getAsJsonObject();

		Integer idCharacter = object.get("idCharacter").getAsInt();
		Integer idSpell = object.get("idSpell").getAsInt();

		Status status = null;

		try {
			service.addSpell(idCharacter, idSpell);
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

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StatusResponse sResponse = new StatusResponse();
		resp.setContentType("application/json");

		PrintWriter writer = resp.getWriter();

		Integer idCharacter = Integer.parseInt(req.getParameter("idCharacter"));
		Integer idSpell = Integer.parseInt(req.getParameter("idSpell"));

		Status status = null;

		try {
			service.deleteSpell(idCharacter, idSpell);
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
