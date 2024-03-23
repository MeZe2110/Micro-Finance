package tn.esprit.fundsphere.Services.TransactionService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;
import tn.esprit.fundsphere.Repositories.TransactionRepository.TransactionRepository;

import java.sql.SQLOutput;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service

public class TransactionServiceImpl implements ITransactionService {

    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public Transaction addTransaction(Transaction transaction) {
        Optional<Account> optionalSender = accountRepository.findById(transaction.getSender().getIdAccount());
        Account sender = optionalSender.orElseThrow(() -> new IllegalArgumentException("Sender not found"));
        transaction.setSender(sender);

        Optional<Account> optionalReceiver = accountRepository.findById(transaction.getReceiver().getIdAccount());
        Account receiver = optionalReceiver.orElseThrow(() -> new IllegalArgumentException("Receiver not found"));
        transaction.setReceiver(receiver);

        double senderNewBalance = sender.getBalance() - transaction.getAmount();
        sender.setBalance(senderNewBalance);
        accountRepository.save(sender);

        double receiverNewBalance = receiver.getBalance() + transaction.getAmount();
        receiver.setBalance(receiverNewBalance);
        accountRepository.save(receiver);

        return transactionRepository.save(transaction);
    }


    @Override
    public void deleteTransaction(Long idTransaction) {
        transactionRepository.deleteById(idTransaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAlltransaction() {
        return transactionRepository.findAll();
    }


    @Override
    public Transaction getatransaction(Long idTransaction) {
        return transactionRepository.findById(idTransaction).get();
    }
}
