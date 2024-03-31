package tn.esprit.fundsphere.Repositories.TransactionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;
import tn.esprit.fundsphere.Entities.TransactionManagment.TypeTransaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transaction,Long>  {

@Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.sender.idAccount = :accountId")
double sumAmountByAccountId(@Param("accountId") Long accountId);
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.receiver.idAccount = :accountId")
    double sumAmountRetrievedByAccountId(@Param("accountId") Long accountId);

}
