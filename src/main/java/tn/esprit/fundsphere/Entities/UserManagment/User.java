package tn.esprit.fundsphere.Entities.UserManagment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.ClaimsManagment.Claims;
import tn.esprit.fundsphere.Entities.CrediMangment.Credit;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Investment;
import tn.esprit.fundsphere.Entities.InvestmentManagment.InvestorWin;
import tn.esprit.fundsphere.Entities.TrainigManagment.Trainig;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idUser ;

    String name ;
    String surname ;
    String userName ;
    String password ;
    String Email ;
    String address ;

    Integer cin ;
    Integer phone ;

    Date birthdate ;
    Date creationDate ;

    @Enumerated(EnumType.STRING)
    TypeUser role;

        @OneToMany(mappedBy = "user")
        private List<Claims>claims;

        @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
        private Set<Account> accounts;

        @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
        private Set<Trainig> trainigs;

        @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
        private Set<Investment> investments;

        @ManyToOne
        InvestorWin investorWin;

}
