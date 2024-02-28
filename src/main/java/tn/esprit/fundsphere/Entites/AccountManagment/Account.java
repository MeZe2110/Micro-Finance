package tn.esprit.fundsphere.Entites.AccountManagment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entites.UserManagment.User;

import java.time.LocalDate;

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
    int idAccount;
    int numAccount;
    double balance;
    Long rib;
    LocalDate date;
    @Enumerated(EnumType.STRING)
    TypeAccount typeA;

    @ManyToOne
    User user;



}
