package tn.esprit.fundsphere.Repositories.TransactionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long>  {
}
