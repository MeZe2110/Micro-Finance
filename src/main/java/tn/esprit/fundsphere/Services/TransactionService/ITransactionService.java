package tn.esprit.fundsphere.Services.TransactionService;

import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;

import java.util.List;

public interface ITransactionService {


    public Transaction addTransaction(Transaction transaction);
    public void deleteTransaction(Long idTransaction);
    public Transaction updateTransaction(Transaction transaction);
    public List<Transaction> getAlltransaction();
    public Transaction getatransaction(Long idTransaction);

}
