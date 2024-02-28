package tn.esprit.fundsphere.Entites.TrainigManagment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entites.UserManagment.User;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "trainig")
public class Trainig {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idTrain;
    String title;
    String description;
    Date startDate;
    Date finishDate;
    int maxCapacity;

    @ManyToOne
    User user;
}
