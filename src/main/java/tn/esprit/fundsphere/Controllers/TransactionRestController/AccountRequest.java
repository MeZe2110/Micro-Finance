package tn.esprit.fundsphere.Controllers.TransactionRestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

        @JsonProperty("numAccount")
        private Long numAccount;

        @JsonProperty("balance")
        private Double balance;

        @JsonProperty("rib")
        private Long rib;

        @JsonProperty("date")
        private LocalDate date;

        @JsonProperty("typeA")
        private String typeA;

        @JsonProperty("automaticTransaction")
        private boolean automaticTransaction;
}
