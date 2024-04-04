package tn.esprit.fundsphere.Entities.LoanManagment;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Min(value = 1000, message = "Minimum investment amount is 1000 DT")
    Float amount;
    @Min(value = 6, message = "Minimum investment period is 6 months")
    int investmentPeriodInMonths;
    Date dateInv;
    Date dateFin;
    Float interest;
    Float score;
    boolean status;

    Long account_id;


    @OneToMany(mappedBy = "loan")
    List<Transaction> transactions;
    // Lezemni nzid el fetch kol manalka onetomany

}
