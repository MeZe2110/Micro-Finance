package tn.esprit.fundsphere.Entities.TransactionManagment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.AccountManagment.TypeAccount;
import tn.esprit.fundsphere.Entities.CrediMangment.Credit;
import tn.esprit.fundsphere.Entities.UserManagment.User;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class AutomaticTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Account receiver;

    private double amount;


}
