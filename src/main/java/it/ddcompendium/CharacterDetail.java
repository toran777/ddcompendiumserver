package it.ddcompendium;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.ddcompendium.repository.entities.Character;
import it.ddcompendium.responses.Response;
import it.ddcompendium.responses.Status;
import it.ddcompendium.service.CharacterService;
import it.ddcompendium.service.impl.CharacterServiceImpl;

@WebServlet("/CharacterDetail")
public class CharacterDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CharacterService service = new CharacterServiceImpl();

	public CharacterDetail() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();

		Response<Character> cResponse = new Response<>();
		Status status = null;

		try {
			Character character = service.findOne(id);
			cResponse.setData(character);
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
