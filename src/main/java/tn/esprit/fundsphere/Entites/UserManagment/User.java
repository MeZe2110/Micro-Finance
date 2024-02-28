package tn.esprit.fundsphere.Entites.UserManagment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entites.AccountManagment.Account;
import tn.esprit.fundsphere.Entites.ClaimsManagment.Claims;
import tn.esprit.fundsphere.Entites.InvestmentManagment.Investment;
import tn.esprit.fundsphere.Entites.InvestmentManagment.InvestorWin;
import tn.esprit.fundsphere.Entites.TrainigManagment.Trainig;
import tn.esprit.fundsphere.Entites.TrainigManagment.TrainigInscription;
import tn.esprit.fundsphere.Entites.CrediMangment.Credit;

import java.util.Date;
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
    private Long idUser ;

    private String name ;
    private String surname ;
    private String userName ;
    private String password ;
    private String Email ;
    private String address ;

    private Integer cin ;
    private Integer phone ;

    private Date birthdate ;
    private Date creationDate ;

    @Enumerated(EnumType.STRING)
    TypeUser role;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Account> accounts;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Claims> claims;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Investment> investments;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Trainig> trainigs;

    @ManyToOne
    Credit credit ;

    @ManyToOne
    InvestorWin investorwin;

    @ManyToOne
    TrainigInscription trainiginscription;

    

}
