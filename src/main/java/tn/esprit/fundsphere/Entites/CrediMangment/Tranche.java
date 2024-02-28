package tn.esprit.fundsphere.Entites.CrediMangment;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tranche")
public class Tranche {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idTranche;
    boolean status ;
    Date dateLimit ;
    float amount ;
    float rateRecovery ;

    @ManyToOne
    Credit credit ;
}
