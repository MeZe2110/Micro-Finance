package tn.esprit.fundsphere.Entities.TrainigManagment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.UserManagment.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class TrainingInscription implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idTrainingInscription;

    String statut;

    @Temporal(TemporalType.DATE)
    LocalDate dateInscription ;

    @ManyToOne
    @JoinColumn(name = "idTraining")
    @JsonIgnore
    Training training;

    @ManyToMany
    @JsonIgnore
    Set<User> users;
}
