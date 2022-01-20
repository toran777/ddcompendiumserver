package it.ddcompendium.servlet.query;

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

@WebServlet("/queryUser")
public class QueryUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final UserService service;
    private final Gson gson;

    public QueryUser() {
        service = new UserServiceImpl();
        gson = new Gson();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject object = Utils.dataToJson(req, resp);

        String query = object.get("query").getAsString();
        Status status;
        Response<User> resultResponse = new Response<>();

        try {
            User user = service.search(query);

            if (user != null) {
                resultResponse.setData(user);
                status = new Status(0, "User found");
            } else status = new Status(-1, "No user was found with this username");

        } catch (Exception e) {
            status = new Status(-1, e.getMessage());
        }

        resultResponse.setStatus(status);
        String json = gson.toJson(resultResponse);
        Utils.writeResponse(resp, json);
    }
}
