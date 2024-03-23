package tn.esprit.fundsphere.Controllers.TransactionRestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.fundsphere.Entities.AccountManagment.Account;
import tn.esprit.fundsphere.Entities.TransactionManagment.Transaction;
import tn.esprit.fundsphere.Services.AccountService.AccountServiceImpl;
import tn.esprit.fundsphere.Services.TransactionService.ITransactionService;
import tn.esprit.fundsphere.Services.TransactionService.TransactionServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/transaction")
public class TransactionRestController {

    @Autowired
    private final ITransactionService transactionService;

    @PostMapping(path = "/add-transaction")
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @GetMapping("/show-transaction")
    public List<Transaction> getAlltransaction() {
        List<Transaction> listTransactions = transactionService.getAlltransaction();
        return listTransactions;
    }



    @PutMapping(path = "/update-transaction")
    public Transaction updateTransaction(@RequestBody Transaction tr)
    {

        Transaction transaction = transactionService.updateTransaction(tr);
        return transaction ;
    }





    @DeleteMapping(path = "/delete-transaction/{id}")
    public void deleteTransaction(@PathVariable ("id") Long idTransaction) {
        transactionService.deleteTransaction(idTransaction);
    }


}
