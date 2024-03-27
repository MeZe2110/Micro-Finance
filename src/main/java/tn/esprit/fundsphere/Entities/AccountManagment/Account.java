package tn.esprit.fundsphere.Entities.AccountManagment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.CrediMangment.Credit;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;
import tn.esprit.fundsphere.Entities.UserManagment.TypeUser;
import tn.esprit.fundsphere.Entities.UserManagment.User;

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
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Credit>credits;
    /* @OneToOne(mappedBy = "sender")
    private List<Transaction> transactions;*/
    @ManyToOne(cascade = CascadeType.ALL )
    private User user;
    //hii





}
