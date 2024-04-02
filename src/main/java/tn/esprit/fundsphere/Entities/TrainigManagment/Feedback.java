package tn.esprit.fundsphere.Entities.TrainigManagment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.UserManagment.User;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idFeedback;

    @Positive(message = "Note doit être un nombre positif")
    int note;

    @NotBlank(message = "Le commentaire ne peut pas être vide")
    @Size(min =8 , message = "Le commentaire doit comporter au moins 8 caractères")
    String commentaire;

    @ManyToOne
    @JoinColumn(name = "idTraining")
    @JsonIgnore
    Training training;

    @ManyToOne
    @JoinColumn(name = "idUser")
    @JsonIgnore
    User user;
}
