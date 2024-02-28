package tn.esprit.fundsphere.Entities.AccountManagment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.CrediMangment.Credit;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;
import tn.esprit.fundsphere.Entities.UserManagment.TypeUser;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idAccount;
    Long numAccount;
    Double balance;
    Long rib;
    LocalDate date;
    @Enumerated(EnumType.STRING)
    TypeAccount typeA;

    @OneToMany(mappedBy = "account")
    private List<Credit>credits;
    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;



}
