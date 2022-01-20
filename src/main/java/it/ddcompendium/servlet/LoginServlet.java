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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service;
    private final Gson gson;

    public LoginServlet() {
        this.service = new UserServiceImpl();
        this.gson = new Gson();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject object = Utils.dataToJson(req, resp);

        User user = new User(object);
        Status status;
        Response<User> resultResponse = new Response<>();

        try {
            User exists = service.findOne(user);

            if (exists.getUsername() == null) status = new Status(-1, "Invalid username or password");
            else {
                status = new Status(0, "Welcome " + exists.getUsername());
                resultResponse.setData(exists);
            }

        } catch (Exception e) {
            status = new Status(-1, e.getMessage());
        }

        resultResponse.setStatus(status);
        String json = gson.toJson(resultResponse);
        Utils.writeResponse(resp, json);
    }
}
