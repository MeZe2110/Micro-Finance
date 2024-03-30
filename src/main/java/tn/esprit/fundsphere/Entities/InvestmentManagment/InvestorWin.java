package tn.esprit.fundsphere.Entities.InvestmentManagment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.UserManagment.User;

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


}
