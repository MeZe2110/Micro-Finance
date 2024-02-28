package tn.esprit.fundsphere.Entites.CrediMangment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entites.UserManagment.User;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "credit")
public class Credit {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idCredit ;
    String nameClient ;
    String surnameClient ;
    boolean investor ;
    float salary ;
    float creditAmount ;
    int recoverySince ;
    String decision ;
    float amountRecoveryMonth ;

    @OneToMany (mappedBy = "credit")
    Set<Tranche> tranches ;

    @OneToMany (mappedBy = "credit")
    Set<User> users ;
}
