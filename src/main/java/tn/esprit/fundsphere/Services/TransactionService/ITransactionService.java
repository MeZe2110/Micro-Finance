package tn.esprit.fundsphere.Services.TransactionService;

import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ITransactionService {


    public Transaction addTransaction(Transaction transaction);
    public void deleteTransaction(Long idTransaction);
    public Transaction updateTransaction(Transaction transaction);
    public List<Transaction> getAlltransaction();


    @Transactional
    Transaction setAutomaticTransaction(Account sender, Account receiver, double amount);

    @Transactional
    void addMoney(Account account, double amount);


    boolean retrieveMoney(Account account, double amount);

    Map<String, Double> calculateStatistics(Long accountId);

    Map<String, Double> calculateAutomaticTransferStatistics();


}
