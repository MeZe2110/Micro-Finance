package tn.esprit.fundsphere.Entities.TrainigManagment;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

public class Simulateur implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idSimulateur;
    int PrixTotal;
    int RevenuBrut;
    int AutreFinencement;
    int Duree;

    @Enumerated(EnumType.STRING)
    TypeCategorie typeCategorie;

    @Enumerated(EnumType.STRING)
    TypeFinencement typeFinencement;


}
