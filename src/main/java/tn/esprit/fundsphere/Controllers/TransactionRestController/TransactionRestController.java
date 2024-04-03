package tn.esprit.fundsphere.Controllers.TransactionRestController;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;
import tn.esprit.fundsphere.Repositories.AccountRepository.AccountRepository;
import tn.esprit.fundsphere.Services.AccountService.AccountServiceImpl;
import tn.esprit.fundsphere.Services.AccountService.IAccountService;
import tn.esprit.fundsphere.Services.TransactionService.ITransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/transaction")
public class TransactionRestController {

    //injection de dependence
    @Autowired
    private final ITransactionService transactionService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    public AccountServiceImpl accountService;
    public IAccountService  IAccountService;

    @PostMapping(path = "/add-transaction")
    public Transaction addTransaction(@RequestBody Transaction transaction) throws MessagingException {
        return transactionService.addTransaction(transaction);
    }

    @GetMapping("/show-transaction")
    public List<Transaction> getAlltransaction() {
        List<Transaction> listTransactions = transactionService.getAlltransaction();
        return listTransactions;
    }


    @PutMapping(path = "/update-transaction")
    public Transaction updateTransaction(@RequestBody Transaction tr) {

        Transaction transaction = transactionService.updateTransaction(tr);
        return transaction;
    }


    @DeleteMapping(path = "/delete-transaction/{id}")
    public void deleteTransaction(@PathVariable("id") Long idTransaction) {
        transactionService.deleteTransaction(idTransaction);
    }
    @PostMapping("/automatic-transfer")//methode de post
    public String setAutomaticTransaction(
            @RequestParam Long senderId,//request chnowa nest7a9ou
            @RequestParam Long receiverId,
            @RequestParam double amount
    ) {
        Account sender = accountService.getAccountById(senderId);
        Account receiver = accountService.getAccountById(receiverId);

        Transaction transaction = transactionService.setAutomaticTransaction(sender, receiver, amount);

        return "Automatic transaction set up successfully!";
    }
    @PostMapping("/versement")
    public ResponseEntity<String> addMoneyToAccount(@RequestParam Long idAccount, @RequestParam double amount) {
        try {
            Account account = accountService.getAccountById(idAccount);
            if (account == null) {
                return ResponseEntity.badRequest().body("Account not found");
            }
            transactionService.addMoney(account, amount);
            return ResponseEntity.ok("versement successfully " + idAccount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error versement: " + e.getMessage());
        }
    }

    @PostMapping("/retrieve-money")
    public ResponseEntity<String> retrieveMoneyFromAccount(@RequestParam Long accountId, @RequestParam double amount) {
        try {
            Account account = accountService.getAccountById(accountId);
            if (account == null) {
                return ResponseEntity.badRequest().body("Account not found");
            }

            boolean moneyRetrieved = transactionService.retrieveMoney(account, amount);
            if (moneyRetrieved) {
                return ResponseEntity.ok("Money retrieved successfully from account " + accountId);
            } else {
                return ResponseEntity.badRequest().body("Insufficient funds in account " + accountId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving money: " + e.getMessage());
        }
    }
//    @GetMapping("/statistics")
//    public Map<String, Object> getStatistics() {
//        return transactionService.calculateStatistics();
//    }
//    @GetMapping("/transactions-by-sender/{senderId}")
//    public List<Transaction> getTransactionsBySender(@PathVariable Long senderId) {
//        Account sender = accountRepository.findById(senderId).orElse(null);
//        if (sender != null) {
//            return transactionService.getTransactionsBySender(sender);
//        } else {
//            throw new RuntimeException("Sender not found");
//        }
//    }

//    @GetMapping("/statistics/{accountId}")
//    public Map<String, Double> getTransactionStatistics(@PathVariable Long accountId) {
//        return transactionService.calculateStatistics(accountId);
//    }
    @GetMapping("/Statistics-transfers")
    public ResponseEntity<Map<String, Double>> getAutomaticTransferStatistics() {
        Map<String, Double> statistics = transactionService.calculateAutomaticTransferStatistics();

        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }



    //afectation


    @PutMapping("/affecter-Account-a-receiver/{idAccount}/{idTransaction}")
    public void assignAccountTotransactionReceiver(@PathVariable("idAccount") Long idAccount,
                                    @PathVariable("idTransaction") Long idTransaction) {
        IAccountService.assignAccountTotransactionReceiver(idTransaction, idAccount);
    }

    @PutMapping("/desaffecter-Account-a-receiver/{idTransaction}")
    public void unassignAccountToTransactionReceiver(@PathVariable("idTransaction") Long idTransaction) {
        IAccountService.unassignAccountToTransactionReceiver(idTransaction);
    }



    @PutMapping("/affecter-Account-a-Sender/{idAccount}/{idTransaction}")
    public void assignAccountToTransactionSender(@PathVariable("idAccount") Long idAccount,
                                           @PathVariable("idTransaction") Long idTransaction) {
        IAccountService.assignAccountTotransactionSender(idTransaction, idAccount);
    }

    @PutMapping("/desaffecter-Account-a-Sender/{idTransaction}")
    public void unassignAccountToTransactionSender(@PathVariable("idTransaction") Long idTransaction) {
        IAccountService.unassignAccountToTransactionSender(idTransaction);
    }


}