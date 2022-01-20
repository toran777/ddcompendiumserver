package it.ddcompendium.repository.entities;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;

    public User(JsonObject object) {
        for (String s : object.keySet()) {
            switch (s) {
                case "id":
                    this.id = object.get(s).getAsInt();
                    break;
                case "username":
                    this.username = object.get(s).getAsString();
                    break;
                case "email":
                    this.email = object.get(s).getAsString();
                    break;
                case "password":
                    this.password = object.get(s).getAsString();
                    break;
            }
        }
    }
}
