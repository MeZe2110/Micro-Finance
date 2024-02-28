package tn.esprit.fundsphere.Entites.InvestmentManagment;

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
@Table(name = "investorWin")
public class InvestorWin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idInvestW;
    double amountInv;
    Date dateInvW ;
    Float ROI;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "investorwin")
    private Set<User> users;
}