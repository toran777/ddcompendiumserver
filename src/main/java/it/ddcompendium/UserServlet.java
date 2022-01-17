package it.ddcompendium;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import it.ddcompendium.repository.entities.User;
import it.ddcompendium.responses.Response;
import it.ddcompendium.responses.Status;
import it.ddcompendium.service.UserService;
import it.ddcompendium.service.impl.UserServiceImpl;
import it.ddcompendium.utils.Utils;

@WebServlet("/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService service = new UserServiceImpl();

	public UserServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Response<User> resultResponse = new Response<>();
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();

		String string = Utils.read(request.getReader());
		JsonObject object = JsonParser.parseString(string).getAsJsonObject();

		User user = new User();
		user.setUsername(object.get("username").getAsString());
		user.setPassword(object.get("password").getAsString());

		String action = object.get("action").getAsString();

		Status status = null;
		User exists = null;
		
		try {
			switch (action) {
			case "registration":
				user.setEmail(object.get("email").getAsString());
				exists = service.findOne(user);
				
				if (exists == null) {
					service.insert(user);
					status = new Status(0, "Registration successful");
				} else {
					status = new Status(-1, "There is already an user with these credentials");
				}
				break;
			case "login":
				exists = service.findOne(user);

				if (exists != null) {
					resultResponse.setData(exists);
					status = new Status(0, "Welcome " + exists.getUsername());
				} else {
					status = new Status(-2, "Invalid username or password");
				}

				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = new Status(-1, e.getMessage());
		}

		resultResponse.setStatus(status);
		String json = new Gson().toJson(resultResponse);
		writer.print(json);
		writer.flush();
	}

}
