package it.ddcompendium.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {
    private Integer id;
    private User recommendedBy;
    private User recommendedTo;
    private Spell recommendation;
}
