package tn.esprit.fundsphere.Entites.TrainigManagment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entites.UserManagment.User;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "trainigInscription")
public class TrainigInscription {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idTi ;
    Date DateInscription ;
    String feedback ;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "trainiginscription")
    private Set<User> users;
}
