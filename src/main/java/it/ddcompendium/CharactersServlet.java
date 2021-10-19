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

import it.ddcompendium.repository.entities.Character;
import it.ddcompendium.responses.CharactersStatusResponse;
import it.ddcompendium.responses.Status;
import it.ddcompendium.responses.StatusResponse;
import it.ddcompendium.service.CharacterService;
import it.ddcompendium.service.impl.CharacterServiceImpl;
import it.ddcompendium.utils.Utils;

@WebServlet("/Character")
public class CharactersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CharacterService service = new CharacterServiceImpl();

	public CharactersServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();

		CharactersStatusResponse cResponse = new CharactersStatusResponse();
		Status status = null;

		try {
			List<Character> characters = service.findAll(id);
			cResponse.setCharacters(characters);
			status = new Status(0, "done");
		} catch (Exception e) {
			e.printStackTrace();
			status = new Status(-1, e.getMessage());
		}

		cResponse.setStatus(status);
		String json = new Gson().toJson(cResponse);
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

		Character character = new Character();
		character.setName(object.get("name").getAsString());
		character.setClasse(object.get("class").getAsString());
		character.setIdUser(object.get("idUser").getAsInt());

		Status status = null;

		try {
			service.insert(character);
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

		Integer id = Integer.parseInt(req.getParameter("id"));

		Status status = null;

		try {
			service.delete(id);
			status = new Status(0, "Character deleted successfully");
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
