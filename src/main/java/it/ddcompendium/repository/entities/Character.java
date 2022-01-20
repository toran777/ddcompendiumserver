package it.ddcompendium.repository.entities;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character {
    private int id;
    private String name;
    private String classe;
    private List<Spell> spells;
    private int idUser;

    public Character(JsonObject object) {
        for (String s : object.keySet()) {
            switch (s) {
                case "id":
                    this.id = object.get(s).getAsInt();
                    break;
                case "name":
                    this.name = object.get(s).getAsString();
                    break;
                case "classe":
                    this.classe = object.get(s).getAsString();
                    break;
                case "idUser":
                    this.idUser = object.get(s).getAsInt();
                    break;
            }
        }
    }
}
