package tn.esprit.fundsphere.Controllers.TransactionRestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private double amount;
    private LocalDate date;
    private String typeT;
    private AccountRequest sender;
    private AccountRequest receiver;
    private boolean automaticTransaction;
}
