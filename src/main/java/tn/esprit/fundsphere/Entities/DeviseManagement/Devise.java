package tn.esprit.fundsphere.Entities.DeviseManagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Devise")
public class Devise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDevise;
    private String nomDevise;
    private String symboleDevise;
    private Double tauxChange;

}
