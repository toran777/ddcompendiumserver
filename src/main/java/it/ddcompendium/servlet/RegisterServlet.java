package it.ddcompendium.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.ddcompendium.repository.entities.User;
import it.ddcompendium.responses.Response;
import it.ddcompendium.responses.Status;
import it.ddcompendium.service.UserService;
import it.ddcompendium.service.impl.UserServiceImpl;
import it.ddcompendium.utils.Utils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service;
    private final Gson gson;

    public RegisterServlet() {
        this.service = new UserServiceImpl();
        this.gson = new Gson();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject object = Utils.dataToJson(req, resp);

        User user = new User(object);
        Status status;
        User exists;

        try {
            exists = service.exists(user);

            if (exists.getId() == null) {
                service.insert(user);
                status = new Status(0, "Registration successful");
            } else status = new Status(-1, "There is already an user with these credentials");

        } catch (Exception e) {
            status = new Status(-1, e.getMessage());
        }

        Response<User> resultResponse = new Response<>();
        resultResponse.setStatus(status);

        String json = gson.toJson(resultResponse);
        Utils.writeResponse(resp, json);
    }
}
