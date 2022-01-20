package it.ddcompendium.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Spell {
    private int id;
    private String name;
    private String desc;
    private String page;
    private String range;
    private String components;
    private String ritual;
    private String duration;
    private String concentration;
    private String castingTime;
    private String level;
    private String school;
    private String classe;
}
