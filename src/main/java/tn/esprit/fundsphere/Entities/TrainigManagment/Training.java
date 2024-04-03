package tn.esprit.fundsphere.Entities.TrainigManagment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Training implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idTraining;

    @NotBlank(message = "Le titre ne peut pas être vide")
    @Size(min =6 , message = "Le titre doit comporter au moins 6 caractères")
    String title;

    @NotBlank(message = "Le description ne peut pas être vide")
    @Size(min =8 , message = "Le description doit comporter au moins 8 caractères")
    String description;

    @FutureOrPresent(message = "La date de début doit être dans le présent ou le future")
    @Temporal(TemporalType.DATE)
    LocalDate startDate;

    @FutureOrPresent(message = "La date de fin doit être dans le présent ou le future")
    @Temporal(TemporalType.DATE)
    LocalDate finishDate;

    @Positive(message = "La capacité maximale doit être un nombre positif")
    int maxCapacity;

    int actuelleCapacity;

    @OneToMany(mappedBy = "training", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JsonIgnore
    Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "training", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JsonIgnore
    Set<TrainingInscription> trainingInscriptions;
}
