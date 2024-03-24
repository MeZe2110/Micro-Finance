package tn.esprit.fundsphere.Services.TransactionService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.fundsphere.Config.EmailService;
import tn.esprit.fundsphere.Config.Mail;
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
    @Autowired
    private EmailService emailService;

    @Override
    @Transactional
    public Transaction addTransaction(Transaction transaction) {
        Optional<Account> optionalSender = accountRepository.findById(transaction.getSender().getIdAccount());
        Account sender = optionalSender.orElseThrow(() -> new IllegalArgumentException("Sender not found"));

        Optional<Account> optionalReceiver = accountRepository.findById(transaction.getReceiver().getIdAccount());
        Account receiver = optionalReceiver.orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        // Check if sender has enough balance
        double senderNewBalance = sender.getBalance() - transaction.getAmount();
        if (senderNewBalance < 0) {
            // Send email for insufficient funds
            Mail mail = new Mail();
            mail.setTo(sender.getUser().getEmail()); // Assuming the email is stored in the User entity
            mail.setSubject("Transaction Failed: Insufficient Funds");
            mail.setContent("Dear " + sender.getUser().getName() + ",\n\nYour transaction of " + transaction.getAmount() + " has been declined due to insufficient funds.");
            emailService.sendSimpleEmail(mail);

            throw new IllegalArgumentException("Insufficient funds in sender's account");
        }

        // Proceed with the transaction
        sender.setBalance(senderNewBalance);
        accountRepository.save(sender);

        double receiverNewBalance = receiver.getBalance() + transaction.getAmount();
        receiver.setBalance(receiverNewBalance);
        accountRepository.save(receiver);

        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Send email for successful transaction
        Mail successMail = new Mail();
        successMail.setTo(sender.getUser().getEmail()); // Again, assuming the email is stored in the User entity
        successMail.setSubject("Transaction Successful");
        successMail.setContent("Dear  " + sender.getUser().getName() + ",\n\nYour transaction of " + transaction.getAmount() + " to " + receiver.getUser().getName() + " has been successfully completed.");
        emailService.sendSimpleEmail(successMail);

        return savedTransaction;
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
