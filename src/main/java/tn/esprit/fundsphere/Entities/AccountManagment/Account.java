package tn.esprit.fundsphere.Entities.AccountManagment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.CrediMangment.Credit;
import tn.esprit.fundsphere.Entities.TransactionManagment.AutomaticTransfer;
import tn.esprit.fundsphere.Entities.UserManagment.User;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "account")
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long idAccount;
    //@NotBlank(message = "Le numAccount ne doit pas etre vide")
    Long numAccount;

    Double balance;
    // @NotBlank(message = "Le rib ne doit pas etre vide")
    Long rib;
    //@FutureOrPresent(message = "La date de fin doit être dans le présent ou le future")
    LocalDate date;
    @Enumerated(EnumType.STRING)
    TypeAccount typeA;

   /* @OneToMany(mappedBy = "account")
    private List<Credit> credits;*/
    /* @OneToOne(mappedBy = "sender")
    private List<Transaction> transactions;*/

    @ManyToOne
    @JsonIgnore
    private User user;

    boolean automaticTransaction;

    @OneToMany(mappedBy = "sender")
    private List<AutomaticTransfer> automaticTransfers;
    public Account(String idAccount) {
        this.idAccount = Long.parseLong(idAccount);
    }
}
