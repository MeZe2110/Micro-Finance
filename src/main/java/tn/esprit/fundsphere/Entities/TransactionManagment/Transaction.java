package tn.esprit.fundsphere.Entities.TransactionManagment;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.UserManagment.TypeUser;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "transaction")
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    long idTransaction;

    float amount;

    LocalDate date;

    @Enumerated(EnumType.STRING)
    TypeTransaction typeT;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Account receiver;
}

