package tn.esprit.fundsphere.Entities.CrediMangment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.UserManagment.User;

import java.util.Date;
import java.util.List;
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

  @ManyToOne
  @JsonIgnore
    private Account account;

  @OneToMany(mappedBy = "credit")
    private List<Tranche> tranches;
}
