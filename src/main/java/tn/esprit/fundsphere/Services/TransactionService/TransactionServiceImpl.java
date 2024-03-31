package tn.esprit.fundsphere.Services.TransactionService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
/*import tn.esprit.fundsphere.Config.EmailService;
import tn.esprit.fundsphere.Config.Mail;*/
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.TransactionManagment.AutomaticTransfer;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;
import tn.esprit.fundsphere.Repositories.TransactionRepository.AutomaticTransferRepository;
import tn.esprit.fundsphere.Repositories.TransactionRepository.TransactionRepository;
import org.slf4j.Logger;

import java.util.*;

@AllArgsConstructor
@Service
public class TransactionServiceImpl implements ITransactionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AutomaticTransferRepository automaticTransferRepository;
    /*@Autowired
    private EmailService emailService;*/


    @Override
    @Transactional
    public Transaction addTransaction(Transaction transaction) {
        Optional<Account> optionalSender = accountRepository.findById(transaction.getSender().getIdAccount());
        Account sender = optionalSender.orElseThrow(() -> new IllegalArgumentException("Sender not found"));

        Optional<Account> optionalReceiver = accountRepository.findById(transaction.getReceiver().getIdAccount());
        Account receiver = optionalReceiver.orElseThrow(() -> new IllegalArgumentException("Receiver not found"));

        double senderNewBalance = sender.getBalance() - transaction.getAmount();
        if (senderNewBalance < 0) {
           /* Mail mail = new Mail();
            mail.setTo(sender.getUser().getEmail());
            mail.setSubject("Transaction Failed: Insufficient Funds");
            mail.setContent("Dear " + sender.getUser().getName() + ",\n\nYour transaction of " + transaction.getAmount() + " has been declined due to insufficient funds.");
            emailService.sendSimpleEmail(mail);*/

            throw new IllegalArgumentException("Insufficient funds in sender's account");
        }

        sender.setBalance(senderNewBalance);
        accountRepository.save(sender);

        double receiverNewBalance = receiver.getBalance() + transaction.getAmount();
        receiver.setBalance(receiverNewBalance);
        accountRepository.save(receiver);

        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        Transaction savedTransaction = transactionRepository.save(transaction);

        /*Mail successMail = new Mail();
        successMail.setTo(sender.getUser().getEmail());
        successMail.setSubject("Transaction Successful");
        successMail.setContent("Dear  " + sender.getUser().getName() + ",\n\nYour transaction of " + transaction.getAmount() + " to " + receiver.getUser().getName() + " has been successfully completed.");
        emailService.sendSimpleEmail(successMail);*/

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
    @Transactional
    public Transaction setAutomaticTransaction(Account sender, Account receiver, double amount) {
        AutomaticTransfer automaticTransfer = new AutomaticTransfer();
        automaticTransfer.setSender(sender);
        automaticTransfer.setReceiver(receiver);
        automaticTransfer.setAmount(amount);

        automaticTransferRepository.save(automaticTransfer);

        sender.setAutomaticTransaction(true);
        accountRepository.save(sender);

        LOGGER.info("Automatic transaction set up for account " + sender.getNumAccount() + " to transfer " +
                amount + " to account " + receiver.getNumAccount() + " monthly.");

        return new Transaction();
    }
//    @Scheduled(cron = "0 0 0 1 * ?")
@Scheduled(cron = "*/30 * * * * *")

    protected void performTransfer() {
        List<AutomaticTransfer> automaticTransfers = automaticTransferRepository.findAll();

        for (AutomaticTransfer automaticTransfer : automaticTransfers) {
            Account sender = automaticTransfer.getSender();
            Account receiver = automaticTransfer.getReceiver();
            double amount = automaticTransfer.getAmount();

            if (sender.getBalance() >= amount) {
                double newSenderBalance = sender.getBalance() - amount;
                sender.setBalance(newSenderBalance);
                accountRepository.save(sender);

                double newReceiverBalance = receiver.getBalance() + amount;
                receiver.setBalance(newReceiverBalance);
                accountRepository.save(receiver);

                LOGGER.info("Automatic transfer executed for account " + sender.getNumAccount() +
                        " to transfer " + amount + " to account " + receiver.getNumAccount() + " monthly.");

            } else {
                LOGGER.info("Automatic transfer failed: Insufficient funds in account " + sender.getNumAccount());

            }
        }
    }
    @Override
    @Transactional
    public void addMoney(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        LOGGER.info("Added " + amount + " to account " + account.getNumAccount());
    }
    @Override
    public boolean retrieveMoney(Account account, double amount) {
        if (account.getBalance() >= amount) {
            double newBalance = account.getBalance() - amount;
            account.setBalance(newBalance);
            accountRepository.save(account);
            LOGGER.info("Money retrieved successfully from account " + account.getIdAccount());
            return true;
        } else {
            LOGGER.info("Insufficient funds in account " + account.getIdAccount() + " for money retrieval");
            return false;
        }
}





























@Override
public Map<String, Double> calculateStatistics(Long accountId) {
    Map<String, Double> statistics = new HashMap<>();

    double totalAmountAdded = transactionRepository.sumAmountByAccountId(accountId);
    double totalAmountRetrieved = transactionRepository.sumAmountRetrievedByAccountId(accountId);

    LOGGER.info("Total amount added for account {}: {}", accountId, totalAmountAdded);
    LOGGER.info("Total amount retrieved for account {}: {}", accountId, totalAmountRetrieved);

    statistics.put("totalAmountAdded", totalAmountAdded);
    statistics.put("totalAmountRetrieved", totalAmountRetrieved);
    statistics.put("balance", accountRepository.findById(accountId).map(Account::getBalance).orElse(0.0));



    return statistics;
}

    @Override
    public Map<String, Double> calculateAutomaticTransferStatistics() {
        Map<String, Double> statistics = new HashMap<>();

        List<AutomaticTransfer> automaticTransfers = automaticTransferRepository.findAll();
        double totalAmountTransferred = 0;
        int successfulTransfers = 0;
        int failedTransfers = 0;

        for (AutomaticTransfer automaticTransfer : automaticTransfers) {
            Account sender = automaticTransfer.getSender();
            double amount = automaticTransfer.getAmount();

            if (sender.getBalance() >= amount) {
                totalAmountTransferred += amount;
                successfulTransfers++;
            } else {
                failedTransfers++;
            }
        }

        statistics.put("totalAmountTransferred", totalAmountTransferred);
        statistics.put("successfulTransfers", (double) successfulTransfers);
        statistics.put("failedTransfers", (double) failedTransfers);

        return statistics;
    }
}

