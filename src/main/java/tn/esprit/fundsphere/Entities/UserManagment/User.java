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
import tn.esprit.fundsphere.Entities.TrainigManagment.TrainigInscription;

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
