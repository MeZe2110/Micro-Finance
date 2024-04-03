package tn.esprit.fundsphere.Entities.InvestmentManagment;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "investment")
public class Investment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    Integer idInvest;
   String project_name;
    Long ownerId;
    Float amountInv;
    Float maxamount;
    @Enumerated(EnumType.STRING)
    Invest_stage stage;
    String description ;
    Date date_debut;
    Float income_by_month;
    @Enumerated(EnumType.STRING)
    InvestStatus status;


    @JsonIgnore
    @OneToMany(mappedBy = "investment")
    private List<Transaction> transactions;
// Lezemni nzid el fetch
}
