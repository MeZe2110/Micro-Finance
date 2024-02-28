package tn.esprit.fundsphere.Entities.ClaimsManagment;

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
@Table(name = "claims")
public class Claims {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idClaim ;
    Date dateCreationClaim ;
    String Content ;
    String answer ;
    Date dateAnswer ;
}
