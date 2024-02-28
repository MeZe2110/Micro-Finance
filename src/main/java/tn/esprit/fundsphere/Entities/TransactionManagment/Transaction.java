package tn.esprit.fundsphere.Entities.TransactionManagment;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.InvestmentManagment.Investment;
import tn.esprit.fundsphere.Entities.LoanManagment.Loan;
import tn.esprit.fundsphere.Entities.UserManagment.TypeUser;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int idTransaction;
    float amount;
    LocalDate date;
    @Enumerated(EnumType.STRING)
    TypeTransaction typeT;
    @ManyToOne
    private Investment investment;
    @ManyToOne
    private Loan loan;


}

