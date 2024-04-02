package tn.esprit.fundsphere.Entities.ClaimsManagment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.UserManagment.User;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "claims")
public class Claims {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idClaim ;
    Date dateCreationClaim ;
    String Content ;
    String answer ;
    Date dateAnswer ;

   /* @ManyToOne
    @JsonIgnore
    private User user ;*/
}
