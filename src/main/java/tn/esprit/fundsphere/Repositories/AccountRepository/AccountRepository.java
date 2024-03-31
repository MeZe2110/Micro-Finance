package tn.esprit.fundsphere.Repositories.AccountRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findByAutomaticTransaction(boolean automaticTransaction);

}
