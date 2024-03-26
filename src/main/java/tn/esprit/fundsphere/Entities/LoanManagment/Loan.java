package tn.esprit.fundsphere.Entities.LoanManagment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
     Integer idLoan;
     Float amount;
     int investmentPeriodInMonths;
     Date dateInv;
     Date dateFin;
     Float interest;
     Float score;
     boolean status;
     Integer id_user;


    @OneToMany(mappedBy = "loan")
    List<Transaction> transactions;

}
